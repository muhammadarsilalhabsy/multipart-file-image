package com.multipartfile.demo.controller;

import com.multipartfile.demo.entity.User;
import com.multipartfile.demo.model.CreateUserRequest;
import com.multipartfile.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("fullname") String fullName,
                                  @RequestParam("avatar") MultipartFile avatar) throws IOException {

    CreateUserRequest request = CreateUserRequest.builder()
            .username(username)
            .password(password)
            .fullName(fullName)
            .avatar(avatar)
            .build();

    service.create(request);

    return ResponseEntity.status(HttpStatus.OK).body("Success create user");
  }

  @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> get(@PathVariable("username") String username){
    User user = service.get(username);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }



}
