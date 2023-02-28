package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetterSecretInformation {
  static private final String path = "src/main/resources/SecretInfoOfTelegramBot.properties";
  static private final Properties secretData;

  static {
    try {
      secretData = new Properties();
      FileInputStream file = new FileInputStream(path);
      secretData.load(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String get(String nameOfField) {
    return secretData.getProperty(nameOfField);
  }
}
