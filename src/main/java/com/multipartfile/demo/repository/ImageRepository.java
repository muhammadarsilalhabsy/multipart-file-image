package com.multipartfile.demo.repository;

import com.multipartfile.demo.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageData, Integer> {

  Optional<ImageData> findByName(String name);
}
