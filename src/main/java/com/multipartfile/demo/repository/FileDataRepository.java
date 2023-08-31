package com.multipartfile.demo.repository;

import com.multipartfile.demo.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, String> {
  Optional<FileData> findByName(String fileName);
  Optional<FileData> findByPath(String filePath);
}
