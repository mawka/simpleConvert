package com.ma3ka.simpleConvert.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class UploadingServiceImpl implements UploadingService {
    @Override
    public String upload(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();

            String fileName = file.getOriginalFilename();

            String rootPath = "C:\\path\\";
            File dir = new File(rootPath + File.separator + "loadFiles");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File loadFile = new File(dir.getAbsolutePath() + File.separator + fileName);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile));
            stream.write(bytes);
            stream.flush();
            stream.close();

            log.info("uploaded: " + loadFile.getAbsolutePath());
            return fileName;
        } catch (IOException e) {
            log.error(" Can't upload: " + file.getName());
        }
        return null;

    }
}
