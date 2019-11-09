package com.example.starter.validation;

import io.vertx.core.json.JsonObject;

public class PropertyConstraints {
  private boolean required;
  private Integer minLength;
  private Integer maxLength;
  private String name;
  private String getterName;

  public PropertyConstraints(JsonObject config) {
    Boolean required = config.getBoolean("required");
    if (required != null) {
      this.required = required;
    }
    this.minLength = config.getInteger("minLength");
    this.maxLength = config.getInteger("maxLength");
    this.setName(config.getString("name"));
  }

  public boolean isRequired() {
    return required;
  }

  public PropertyConstraints setRequired(boolean required) {
    this.required = required;
    return this;
  }

  public Integer getMinLength() {
    return minLength;
  }

  public PropertyConstraints setMinLength(Integer minLength) {
    this.minLength = minLength;
    return this;
  }

  public Integer getMaxLength() {
    return maxLength;
  }

  public PropertyConstraints setMaxLength(Integer maxLength) {
    this.maxLength = maxLength;
    return this;
  }

  public String getName() {
    return name;
  }

  public PropertyConstraints setName(String name) {
    if (name == null) {
      throw new RuntimeException("Property constraints must have a name.");
    }
    this.name = name;
    String nameCapitalized = name.substring(0, 1).toUpperCase() + name.substring(1);;
    getterName = "get" + nameCapitalized;
    return this;
  }

  public String getGetterName() {
    return getterName;
  }
}
