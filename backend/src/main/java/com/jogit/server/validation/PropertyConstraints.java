package com.jogit.server.validation;

import io.vertx.core.json.JsonObject;

import java.util.regex.Pattern;

public class PropertyConstraints {
  private boolean required;
  private Integer minLength;
  private Integer maxLength;
  private String name;
  private String getterName;
  private Pattern pattern;
  private Integer minimum;
  private Integer maximum;

  public PropertyConstraints(JsonObject config) {
    Boolean required = config.getBoolean("required");
    if (required != null) {
      this.required = required;
    }
    this.minLength = config.getInteger("minLength");
    this.maxLength = config.getInteger("maxLength");
    this.minimum = config.getInteger("minimum");
    this.maximum = config.getInteger("maximum");
    this.setName(config.getString("name"));
    String pattern = config.getString("pattern");
    if (pattern != null) {
      this.pattern = Pattern.compile(pattern);
    }
  }

  public PropertyConstraints(PropertyConstraints other) {
    this.required = other.required;
    this.minLength = other.minLength;
    this.maxLength = other.maxLength;
    this.minimum = other.minimum;
    this.maximum = other.maximum;
    this.name = other.name;
    this.getterName = other.getterName;
    this.pattern = other.pattern;
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
    String nameCapitalized = name.substring(0, 1).toUpperCase() + name.substring(1);
    ;
    getterName = "get" + nameCapitalized;
    return this;
  }

  public String getGetterName() {
    return getterName;
  }

  public Pattern getPattern() {
    return pattern;
  }

  public Integer getMinimum() {
    return minimum;
  }

  public Integer getMaximum() {
    return maximum;
  }
}
