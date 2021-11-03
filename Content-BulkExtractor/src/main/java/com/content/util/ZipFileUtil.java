package com.content.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileUtil {

    private ZipFileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void zipCSVFiles(String destDir, String zipDir) throws IOException {
        Path zipDirPath = Files.createFile(Paths.get(zipDir + File.separator + "ContentBulkExtractor" + System.currentTimeMillis() + ".zip"));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipDirPath))) {
            Path destDirPath = Paths.get(destDir);
            Files.walk(destDirPath)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(destDirPath.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

}
