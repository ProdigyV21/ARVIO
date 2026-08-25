package com.google.firebase.crashlytics.internal.stacktrace;

/* JADX INFO: loaded from: classes4.dex */
public interface StackTraceTrimmingStrategy {
    StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stackTraceElementArr);
}
