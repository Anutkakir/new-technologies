package com.ivan.newtechnologies.dirlist;

import java.io.IOException;
import java.util.List;

public interface DirectoryAnalyser {

    List<Directory> analyse(String path) throws IOException;

}
