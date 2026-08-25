package com.google.firebase.crashlytics.internal.common;

import android.os.Process;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BackgroundPriorityRunnable implements Runnable {
    public abstract void onRun();

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
