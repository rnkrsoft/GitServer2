package com.rnkrsoft.gitserver.log;

public abstract class AbstractLogger implements Logger{
    protected String getCaller(){
        return ClassUtils.getCallerClassName(false, 2);
    }
}
