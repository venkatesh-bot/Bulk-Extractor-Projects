package com.content.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static List<File> readFiles(String srcDir, String fileExtension) throws IOException {
        Path path = Paths.get(srcDir);
        Stream<File> pathStream = Files.list(path).filter(s -> s.toString().endsWith(fileExtension)).map(Path::toFile);
        List<File> files = pathStream.collect(Collectors.toList());
        pathStream.close();
        return files;
    }

    public static void moveFile(File srcFile, String destFile) throws IOException {
        Files.copy(Paths.get(srcFile.toString()), Paths.get(destFile), StandardCopyOption.REPLACE_EXISTING);
    }

}
