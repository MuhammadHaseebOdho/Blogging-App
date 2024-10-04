package com.blog.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface FileService {
    InputStream getResource(String imageName) throws FileNotFoundException;
}
