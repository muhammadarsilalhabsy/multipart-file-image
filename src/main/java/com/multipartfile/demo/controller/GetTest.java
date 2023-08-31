package com.multipartfile.demo.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping(path = "api/con")
@RestController
public class GetTest {

  private final ServletWebServerApplicationContext server;
  private final ServerProperties properties;

  public GetTest(ServletWebServerApplicationContext server, ServerProperties properties) {
    this.server = server;
    this.properties = properties;
  }

  @GetMapping
  public ResponseEntity<?> get(){

    int port = server.getWebServer().getPort();

    GetPost get = GetPost.builder()
            .serverPort(port)
            .PWD(System.getenv("PWD"))
            .host(System.getenv("user.dir"))
            .home(System.getenv("HOME"))
            .build();


    return ResponseEntity.status(HttpStatus.OK).body(get);

  }
}

@Data
@Builder
class GetPost {

  private int serverPort;

  private String home;
  private String PWD;

  private String host;
}
