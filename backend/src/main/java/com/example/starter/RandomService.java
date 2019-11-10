package com.example.starter;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomService {
  String getRandomString(int length) {
    return RandomStringUtils.random(length, true, true);
  }
}
