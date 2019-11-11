package com.jogit.server.validation;

import com.google.common.io.Files;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: make this a Gradle task
public class SchemaFileGenerator {
  private String rootDirectory;

  public SchemaFileGenerator(String rootDirectory) {
    this.rootDirectory = rootDirectory;
  }

  public static void main(String[] args) throws IOException {
    String rootDirectory = args[0];
    SchemaFileGenerator schemaFileGenerator = new SchemaFileGenerator(rootDirectory);
    schemaFileGenerator.generate();
  }

  private void generate() throws IOException {
    File definitionsDirectory = new File(rootDirectory + "/definitions");
    if (!definitionsDirectory.exists()) {
      throw new RuntimeException("Directory " + definitionsDirectory + " does not exist.");
    }
    List<File> definitionFiles = getFilesInFolder(definitionsDirectory);

    // TODO: create Java instances of class and check that all properties exist
    Map<String, Map<String, PropertyConstraints>> propertyConstraintsPerEntity = new HashMap<>();
    for (File definitionFile : definitionFiles) {
      String definitionFileContent = Files.asCharSource(definitionFile, StandardCharsets.UTF_8).read();
      String fileNameWithoutExtension = Files.getNameWithoutExtension(definitionFile.getName());

      Map<String, PropertyConstraints> propertyConstraints = new HashMap<>();
      propertyConstraintsPerEntity.put(fileNameWithoutExtension, propertyConstraints);
      JsonArray definition = new JsonArray(definitionFileContent);
      for (Object field : definition) {
        PropertyConstraints propertyConstraint = new PropertyConstraints((JsonObject)field);
        propertyConstraints.put(propertyConstraint.getName(), propertyConstraint);
      }
    }


    File requestDirectory = new File(rootDirectory + "/requests");
    if (!requestDirectory.exists()) {
      throw new RuntimeException("Directory " + requestDirectory + " does not exist.");
    }

    Map<String, Map<String, Map<String, PropertyConstraints>>> schema = new HashMap<>();
    Map<String, Map<String, PropertyConstraints>> requestSchema = new HashMap<>();
    schema.put("definitions", propertyConstraintsPerEntity);
    schema.put("requests", requestSchema);
    List<File> requestFiles = getFilesInFolder(requestDirectory);
    for (File requestFile : requestFiles) {
      String requestFileContent = Files.asCharSource(requestFile, StandardCharsets.UTF_8).read();
      String fileNameWithoutExtension = Files.getNameWithoutExtension(requestFile.getName());
      JsonObject requestDefinitionJson = new JsonObject(requestFileContent);
      JsonArray propertyConstraintsList = requestDefinitionJson.getJsonArray("propertyConstraints");
      Map<String, PropertyConstraints> requestConstraints = new HashMap<>();
      requestSchema.put(fileNameWithoutExtension, requestConstraints);

      for (Object o : propertyConstraintsList) {
        JsonObject constraintJson = (JsonObject)o;

        String schemaReference = constraintJson.getString("schema");
        if (schemaReference != null) {
          String[] schemaReferenceParts = schemaReference.split("\\.");
          if (schemaReferenceParts.length == 2) {
            String entityName = schemaReferenceParts[0];
            String propertyName = schemaReferenceParts[1];
            Map<String, PropertyConstraints> entityConstraints = propertyConstraintsPerEntity.get(entityName);
            if (entityConstraints == null) {
              throw new RuntimeException("Unknown entity : " + entityName);
            }
            PropertyConstraints constraint = entityConstraints.get(propertyName);
            if (constraint == null) {
              throw new RuntimeException("Unknown property : " + entityName + "." + propertyName);
            }

            PropertyConstraints newConstraint = new PropertyConstraints(constraint);
            Boolean required = constraintJson.getBoolean("required");
            if (required != null) {
              newConstraint.setRequired(required);
            }
            String name = constraintJson.getString("name");
            if (name != null) {
              newConstraint.setName(name);
            }
            requestConstraints.put(newConstraint.getName(), newConstraint);
          } else {
            throw new RuntimeException("Schema reference must be in format <entity-name>.<property-name>");
          }
        } else {
          PropertyConstraints newConstraint = new PropertyConstraints(constraintJson);
          requestConstraints.put(newConstraint.getName(), newConstraint);
        }
      }
    }

    JsonObject schemaJson = new JsonObject((Map)schema);
    String outSchema = schemaJson.encodePrettily();
    File outSchemaFile = new File(rootDirectory + "/build/schema.json");
    Files.write(outSchema.getBytes(), outSchemaFile);
    System.out.println(schema);
  }

  private List<File> getFilesInFolder(final File folder) {
    List<File> files = new ArrayList<>();
    getFilesInFolder(folder, files);
    return files;
  }

  private void getFilesInFolder(final File folder, List<File> files) {
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        System.out.println("Warning: directory found in " + folder + " but all files must be at the top level of this directory.");
      } else {
        files.add(fileEntry);
      }
    }
  }
}
