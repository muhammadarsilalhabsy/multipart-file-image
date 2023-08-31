package com.multipartfile.demo.service;

import com.multipartfile.demo.entity.FileData;
import com.multipartfile.demo.repository.FileDataRepository;
import com.multipartfile.demo.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileDataService {


  private final FileDataRepository repository;

  private final String PATH_PUBLIC_FOLDER = System.getenv("PWD").concat("/public/assets/");
  @Autowired
  public FileDataService(FileDataRepository repository) {
    this.repository = repository;
  }

  public String uploadToFolder(MultipartFile file) throws IOException {

    String fullPath = PATH_PUBLIC_FOLDER.concat(ImageUtils.nameConversion(file));

    repository.save(FileData.builder()
            .name(ImageUtils.nameConversion(file))
            .type(file.getContentType())
            .path(fullPath)
            .build());

    file.transferTo(new File(fullPath));
    return fullPath;

  }

  public byte[] getImageFromFolder(String fileName) throws IOException {

    Optional<FileData> fileFromDb = repository.findByName(fileName);

    String fullPath = fileFromDb.get().getPath();

    return Files.readAllBytes(new File(fullPath).toPath());
  }
}
