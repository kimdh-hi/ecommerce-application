package com.shopme.admin.utils;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FIleUploadUtilsTest {

    @Test
    void pathTest() {
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        String uploadDir = "user-thumbnail";

        System.out.println(path);
        System.out.println(Path.of(path, uploadDir));

        assertThat(Path.of(path, uploadDir).equals(path + "/" + uploadDir));
    }
}
