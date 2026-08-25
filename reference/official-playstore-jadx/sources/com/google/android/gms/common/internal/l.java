package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.HandlerThread;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f12888a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static x0 f12889b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static HandlerThread f12890c;

    public static x0 a(Context context) {
        synchronized (f12888a) {
            try {
                if (f12889b == null) {
                    f12889b = new x0(context.getApplicationContext(), context.getMainLooper());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f12889b;
    }

    public static HandlerThread b() {
        synchronized (f12888a) {
            try {
                HandlerThread handlerThread = f12890c;
                if (handlerThread != null) {
                    return handlerThread;
                }
                HandlerThread handlerThread2 = new HandlerThread("GoogleApiHandler", 9);
                f12890c = handlerThread2;
                handlerThread2.start();
                return f12890c;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract o3.b c(u0 u0Var, ServiceConnection serviceConnection, String str, Executor executor);

    public abstract void d(u0 u0Var, ServiceConnection serviceConnection);
}
