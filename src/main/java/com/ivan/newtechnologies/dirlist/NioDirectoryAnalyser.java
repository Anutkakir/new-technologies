package com.ivan.newtechnologies.dirlist;

import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NioDirectoryAnalyser implements DirectoryAnalyser {

    @Override
    public List<Directory> analyse(final String dirPath) throws IOException {
        return Files.list(Paths.get(dirPath))
                .filter(path -> Files.isDirectory(path))
                .sorted(Comparator.comparing(Path::getFileName))
                .map(Unchecked.function(dir -> new Directory(dir.getFileName().toString(), directorySize(dir))))
                .collect(Collectors.toList());
    }

    private static String directorySize(final Path path) throws IOException {
        return FileUtils.byteCountToDisplaySize(Files.walk(path)
                .filter(dir -> Files.isRegularFile(dir))
                .map(Unchecked.function(Files::size))
                .collect(Collectors.summingLong(size -> size)));
    }
}
