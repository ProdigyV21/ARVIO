package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;

/* JADX INFO: loaded from: classes4.dex */
public interface CrashlyticsNativeComponent {
    NativeSessionFileProvider getSessionFileProvider(String str);

    boolean hasCrashDataForCurrentSession();

    boolean hasCrashDataForSession(String str);

    void prepareNativeSession(String str, String str2, long j10, StaticSessionData staticSessionData);
}
