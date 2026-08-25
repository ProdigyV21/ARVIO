package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.Session;

/* JADX INFO: loaded from: classes4.dex */
public interface SessionManagerListener<T extends Session> {
    void onSessionEnded(T t2, int i10);

    void onSessionEnding(T t2);

    void onSessionResumeFailed(T t2, int i10);

    void onSessionResumed(T t2, boolean z);

    void onSessionResuming(T t2, String str);

    void onSessionStartFailed(T t2, int i10);

    void onSessionStarted(T t2, String str);

    void onSessionStarting(T t2);

    void onSessionSuspended(T t2, int i10);
}
