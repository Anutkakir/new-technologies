package com.ivan.newtechnologies.dirlist;

import io.bretty.console.table.Alignment;
import io.bretty.console.table.ColumnFormatter;
import io.bretty.console.table.Table;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final String folder = "d:\\Learning";

        final DirectoryAnalyzerFactory analyzerFactory = new DirectoryAnalyzerFactory();

        final List<Directory> dirs = analyzerFactory.create(true).analyse(folder);

        final Table.Builder tb = new Table.Builder("Name", extractNamesToArray(dirs), ColumnFormatter.text(Alignment.LEFT, 60));
        tb.addColumn("Size", extractSizesToArray(dirs), ColumnFormatter.text(Alignment.RIGHT, 10));

        System.out.println(tb.build());
    }

    private static String[] extractNamesToArray(final List<Directory> dirs) {
        return dirs.stream()
                .map(Directory::getName)
                .collect(Collectors.toList()).toArray(new String[]{});
    }

    private static String[] extractSizesToArray(final List<Directory> dirs) {
        return dirs.stream()
                .map(Directory::getSize)
                .collect(Collectors.toList()).toArray(new String[]{});
    }

}
