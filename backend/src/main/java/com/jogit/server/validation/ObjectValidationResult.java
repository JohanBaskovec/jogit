package com.jogit.server.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectValidationResult {
  private List<String> globalErrors = new ArrayList<>();
  private Map<String, PropertyValidationResult> propertyValidationResultMap = new HashMap<>();

  public List<String> getGlobalErrors() {
    return globalErrors;
  }

  public void addPropertyValidationResult(PropertyValidationResult propertyValidationResult) {
    this.propertyValidationResultMap.put(propertyValidationResult.getFieldName(), propertyValidationResult);
  }

  public Map<String, PropertyValidationResult> getPropertyValidationResultMap() {
    return propertyValidationResultMap;
  }

  public boolean isValid() {
    boolean valid = true;
    for (PropertyValidationResult propertyValidationResult : propertyValidationResultMap.values()) {
      if (propertyValidationResult.getErrorsCount() != 0) {
        return false;
      }
    }
    return globalErrors.size() == 0;
  }

  public boolean isInvalid() {
    return !isValid();
  }

  @Override
  public String toString() {
    return "ObjectValidationResult{" +
      "globalErrors=" + globalErrors +
      ", propertyValidationResultMap=" + propertyValidationResultMap +
      '}';
  }
}
