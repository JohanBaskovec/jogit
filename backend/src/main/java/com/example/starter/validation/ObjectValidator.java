package com.example.starter.validation;

import com.google.common.base.Utf8;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ObjectValidator {
  private final JsonObject config;
  private final List<PropertyConstraints> propertiesConstraints = new ArrayList<>();

  public ObjectValidator(JsonObject config) {
    this.config = config;

    JsonArray propertyConstraints = config.getJsonArray("propertyConstraints");

    if (propertyConstraints != null) {
      parsePropertyConstraints(propertyConstraints);
    }
  }

  private void parsePropertyConstraints(JsonArray propertyConstraintsJsonArray) {
    for (Object jsonElement : propertyConstraintsJsonArray) {
      if (!(jsonElement instanceof JsonObject)) {
        throw new RuntimeException("Field constraint must be a json object");
      }
      JsonObject jsonObject = (JsonObject) jsonElement;
      PropertyConstraints propertyConstraints = new PropertyConstraints(jsonObject);
      propertiesConstraints.add(propertyConstraints);
    }
  }

  public ObjectValidationResult validate(Object object) {
    ObjectValidationResult objectValidationResult = new ObjectValidationResult();
    for (PropertyConstraints propertyConstraints : propertiesConstraints) {
      PropertyValidationResult propertyValidationResult = new PropertyValidationResult(propertyConstraints.getName());
      objectValidationResult.addPropertyValidationResult(propertyValidationResult);
      Class clazz = object.getClass();
      try {
        Method getter = clazz.getMethod(propertyConstraints.getGetterName());
        Object value = getter.invoke(object);
        if (value == null) {
          if (propertyConstraints.isRequired()) {
            propertyValidationResult.addError("Property " + propertyConstraints.getName() + " is required but is null");
          }
        } else {
          int stringLength = -1;
          if (propertyConstraints.getMaxLength() != null) {
            String valueAsString = (String) value;
            stringLength = Utf8.encodedLength(valueAsString);
            if (stringLength > propertyConstraints.getMaxLength()) {
              propertyValidationResult.addError("Property "
                + propertyConstraints.getName() + " has length of "
                + stringLength + " but maximum length is "
                + propertyConstraints.getMaxLength());
            }
          }
          if (propertyConstraints.getMinLength() != null) {
            String valueAsString = (String) value;
            stringLength = Utf8.encodedLength(valueAsString);
            if (stringLength < propertyConstraints.getMinLength()) {
              propertyValidationResult.addError("Property "
                + propertyConstraints.getName() + " has length of "
                + stringLength + " but minimum length is "
                + propertyConstraints.getMaxLength());
            }
          }
          if (propertyConstraints.getPattern() != null) {
            String valueAsString = (String) value;
            Matcher matcher = propertyConstraints.getPattern().matcher(valueAsString);
            if (!matcher.matches()) {
              propertyValidationResult.addError("Property "
              + propertyConstraints.getName() + " does not match pattern "
              + propertyConstraints.getPattern().toString());
            }
          }
        }
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }
    }
    return objectValidationResult;
  }
}
