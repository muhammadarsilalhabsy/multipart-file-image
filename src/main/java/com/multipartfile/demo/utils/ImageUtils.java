package com.multipartfile.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

  public static byte[] compressImage(byte[] data) {
    Deflater deflater = new Deflater();

    deflater.setLevel(Deflater.BEST_COMPRESSION);
    deflater.setInput(data);
    deflater.finish();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] temp = new byte[4 * 1024];

    while (!deflater.finished()) {
      int size = deflater.deflate(temp);
      outputStream.write(temp, 0, size);
    }

    try {
      outputStream.close();
    } catch (Exception ignore) {
    }

    return outputStream.toByteArray();

  }

  public static byte[] decompressImage(byte[] data) {
    Inflater inflater = new Inflater();
    inflater.setInput(data);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
    byte[] temp = new byte[4 * 1024];

    try {
      while (!inflater.finished()) {
        int count = inflater.inflate(temp);
        outputStream.write(temp, 0, count);
      }
      outputStream.close();
    } catch (Exception ignore) {
    }
    return outputStream.toByteArray();
  }

  public static String nameConversion(MultipartFile file){
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    String tanggal = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    String jam = time.format(DateTimeFormatter.ofPattern("HH-mm-ss"));

    return tanggal + "-" + jam + "-" + Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s","-");
  }
}
