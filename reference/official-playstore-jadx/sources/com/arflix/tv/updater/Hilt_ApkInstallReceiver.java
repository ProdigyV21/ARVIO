package com.arflix.tv.updater;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import dagger.hilt.android.internal.managers.BroadcastReceiverComponentManager;
import dagger.hilt.internal.UnsafeCasts;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Hilt_ApkInstallReceiver extends BroadcastReceiver {
    private volatile boolean injected = false;
    private final Object injectedLock = new Object();

    public void inject(Context context) {
        if (this.injected) {
            return;
        }
        synchronized (this.injectedLock) {
            try {
                if (!this.injected) {
                    ((ApkInstallReceiver_GeneratedInjector) BroadcastReceiverComponentManager.generatedComponent(context)).injectApkInstallReceiver((ApkInstallReceiver) UnsafeCasts.unsafeCast(this));
                    this.injected = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        inject(context);
    }
}
