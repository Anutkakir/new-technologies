package com.ivan.newtechnologies.dirlist;

public class DirectoryAnalyzerFactory {

    public DirectoryAnalyser create(final boolean nio) {
        if (nio) {
            return new NioDirectoryAnalyser();
        }

        return new SimpleIoDirectoryAnalizer();
    }

}
