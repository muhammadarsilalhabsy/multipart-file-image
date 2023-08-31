package com.multipartfile.demo.controller;


import com.multipartfile.demo.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/image-path")
public class ImagePathController {


  private final FileDataService service;

  @Autowired
  public ImagePathController(FileDataService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<?> post(@RequestParam(name = "image")MultipartFile file) throws IOException {
    String upload = service.uploadToFolder(file);

    return ResponseEntity.status(HttpStatus.OK).body(upload);
  }

  @GetMapping(path = "/{name}")
  public ResponseEntity<?> get(@PathVariable("name") String name) throws IOException {
    byte[] data = service.getImageFromFolder(name);
    return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.valueOf("image/png"))
            .body(data);
  }
}
