package com.multipartfile.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

  @Id
  private String username;

  private String password;

  @Column(name = "full_name")
  private String fullName;


  private String avatar;

}
