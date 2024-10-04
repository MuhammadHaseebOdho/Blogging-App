package com.blog.service.impl;

import com.blog.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {
    private final String IMAGES_PATH="src/main/resources/static/img/post_imgs";

    @Override
    public InputStream getResource(String imageName) throws FileNotFoundException {
        String fullPath=IMAGES_PATH+ File.separator+imageName;
        FileInputStream fileInputStream = new FileInputStream(fullPath);
        return fileInputStream;
    }
}
