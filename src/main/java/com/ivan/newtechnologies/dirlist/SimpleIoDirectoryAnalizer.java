package com.ivan.newtechnologies.dirlist;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleIoDirectoryAnalizer implements DirectoryAnalyser {

    @Override
    public List<Directory> analyse(final String dirPath) {
        final File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            return Collections.emptyList();
        }

        return Arrays.stream(dir.listFiles(file -> file.isDirectory()))
                .sorted((f1, f2) -> String.CASE_INSENSITIVE_ORDER.compare(f1.getName(), f2.getName()))
                .map(folder -> new Directory(folder.getName(), this.readableSize(folder)))
                .collect(Collectors.toList());
    }

    private String readableSize(final File file) {
        return FileUtils.byteCountToDisplaySize(this.size(file));
    }

    private Long size(final File dir) {
        long length = 0L;

        for (File file : Arrays.asList(dir.listFiles())) {
            if (file.isDirectory()) {
                length += this.size(file);
            } else {
                length += file.length();
            }
        }

        return length;
    }

}
