package com.rnkrsoft.gitserver.http.loader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件加载器，用于将文件路径指定的文件加载读取成输入流
 */
public interface FileLoader {
    InputStream load(String fileName)throws IOException;
}
