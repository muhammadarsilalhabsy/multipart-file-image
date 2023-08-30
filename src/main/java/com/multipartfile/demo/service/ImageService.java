package com.multipartfile.demo.service;

import com.multipartfile.demo.entity.ImageData;
import com.multipartfile.demo.repository.ImageRepository;
import com.multipartfile.demo.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageService {

  private final ImageRepository repository;

  @Autowired
  public ImageService(ImageRepository repository) {
    this.repository = repository;
  }

  public String upload(MultipartFile file) throws IOException {

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();


    String tanggal = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    String jam = time.format(DateTimeFormatter.ofPattern("HH-mm"));

    String imageName = tanggal + "-" + jam + "-" +Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s","-");

    repository.save(ImageData.builder()
              .name(imageName)
              .type(file.getContentType())
              .image(ImageUtils.compressImage(file.getBytes()))
              .build());

    return imageName;
  }

  public byte[] get(String name){
    Optional<ImageData> image = repository.findByName(name);

    return ImageUtils.decompressImage(image.get().getImage());

  }
}
