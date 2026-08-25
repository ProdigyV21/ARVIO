package com.google.zxing;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ReaderException extends Exception {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final StackTraceElement[] f14396i;

    static {
        System.getProperty("surefire.test.class.path");
        f14396i = new StackTraceElement[0];
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
