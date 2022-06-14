package com.ma3ka.simpleConvert.service;


import org.springframework.web.multipart.MultipartFile;

public interface UploadingService {

    String upload(MultipartFile file);
}
