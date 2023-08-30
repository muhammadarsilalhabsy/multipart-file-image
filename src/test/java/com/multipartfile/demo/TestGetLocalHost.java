package com.multipartfile.demo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;

public class TestGetLocalHost {

  @Test
  void test() throws IOException {

    Process hostname = Runtime.getRuntime().exec("hostname");
    System.out.println(hostname);

    String host = InetAddress.getLocalHost().getHostName();
    System.out.println(host);

  }
}
