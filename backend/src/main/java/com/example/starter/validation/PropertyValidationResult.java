package com.example.starter.validation;

import java.util.HashSet;
import java.util.Set;

public class PropertyValidationResult {
  private String fieldName;
  private Set<String> errors = new HashSet<>();

  public String getFieldName() {
    return fieldName;
  }

  public PropertyValidationResult(String fieldName) {
    this.fieldName = fieldName;
  }

  public void addError(String error) {
    this.errors.add(error);
  }

  public void removeError(String error) {
    this.errors.remove(error);
  }
}
