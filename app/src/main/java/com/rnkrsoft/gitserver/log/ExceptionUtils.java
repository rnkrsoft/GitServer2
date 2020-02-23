package com.rnkrsoft.gitserver.log;

import java.io.PrintWriter;

public class ExceptionUtils {
    /**
     * 将异常对象输出为字符串形式
     * @param e 异常
     * @return 异常字符串形式
     */
    public static String toString(Throwable e) {
        UnsafeStringWriter w = new UnsafeStringWriter();

        PrintWriter p = new PrintWriter(w);
        try {
            e.printStackTrace(p);
            return w.toString();
        } finally {
            p.close();
        }
    }
}
