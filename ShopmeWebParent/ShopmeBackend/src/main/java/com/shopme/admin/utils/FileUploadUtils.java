package com.shopme.admin.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtils {

    public static String USER_THUMBNAIL_PATH = "user-thumbnail";

    public static void save(String dir, String fileName, MultipartFile file) throws IOException {
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();

        Path uploadDir = Path.of(path, dir);

        System.out.println("path = " + path);
        System.out.println("uploadDir = " + uploadDir);
        if(!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        try(InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("파일을 저장할 수 없습니다. 파일이름: " + fileName, e);
        }
    }
}
