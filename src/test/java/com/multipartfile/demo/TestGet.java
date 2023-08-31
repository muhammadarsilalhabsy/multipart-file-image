package com.multipartfile.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.util.Properties;

public class TestGet {

  @Test
  void get() {

    System.getProperties().forEach((k,v)->{
      System.out.println(k + " : " + v);
    });

    System.getenv().forEach((k,v)-> {
      System.out.println(k + " : " + v);
    });

    ServerProperties properties = new ServerProperties();
    System.out.println(properties.getAddress());
    System.out.println(properties.getPort());
    System.out.println(properties.getTomcat());

  }
}
