package com.multipartfile.demo.service;

import com.multipartfile.demo.entity.User;
import com.multipartfile.demo.model.CreateUserRequest;
import com.multipartfile.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class UserService {

  private final UserRepository repository;
  private final ImageService imageService;


  @Autowired
  public UserService(UserRepository repository, ImageService imageService) {
    this.repository = repository;
    this.imageService = imageService;
  }

  public void create(CreateUserRequest request) throws IOException {
    User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .avatar(imageService.upload(request.getAvatar()))
            .fullName(request.getFullName())
            .build();

    repository.save(user);
  }

  public User get(String username){
    return repository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
  }
}
