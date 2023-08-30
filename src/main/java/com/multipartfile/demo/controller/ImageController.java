package com.multipartfile.demo.controller;


import com.multipartfile.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/image")
public class ImageController {


  private final ImageService service;

  @Autowired
  public ImageController(ImageService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<?> post(@RequestParam(name = "image")MultipartFile file) throws IOException {
    String upload = service.upload(file);

    return ResponseEntity.status(HttpStatus.OK).body(upload);
  }

  @GetMapping(path = "/{name}")
  public ResponseEntity<?> get(@PathVariable("name") String name){
    byte[] data = service.get(name);
    return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("image/png"))
            .body(data);
  }
}
