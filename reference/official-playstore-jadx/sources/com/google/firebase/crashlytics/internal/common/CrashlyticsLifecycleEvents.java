package com.google.firebase.crashlytics.internal.common;

/* JADX INFO: loaded from: classes4.dex */
interface CrashlyticsLifecycleEvents {
    void onBeginSession(String str, long j10);

    void onCustomKey(String str, String str2);

    void onLog(long j10, String str);

    void onUserId(String str);
}
