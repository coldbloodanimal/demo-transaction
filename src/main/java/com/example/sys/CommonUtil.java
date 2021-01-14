package com.example.sys;

public class CommonUtil {
    public static void throwException() throws Exception {
        throw new Exception();
    }

    public static void throwRuntimeException() throws Exception {
        throw new RuntimeException();
    }

    public static void throwSubClassOfRuntimeException() throws Exception {
        throw new SysException();
    }

    public static void throwSubClassOfException() throws Exception {
        throw new HeartBeatException();
    }
}
