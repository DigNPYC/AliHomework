package com.ebanma.cloud.usertestall.service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;

public interface FileService {
    void upload(InputStream inputStream, String filename);
    void upload(File file);
}
