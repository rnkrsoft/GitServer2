package com.rnkrsoft.gitserver.http.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DefaultFileLoader implements FileLoader {
    @Override
    public InputStream load(String fileName) throws IOException {
        FileInputStream fis = null;
        fis = new FileInputStream(fileName);
        return fis;
    }
}
