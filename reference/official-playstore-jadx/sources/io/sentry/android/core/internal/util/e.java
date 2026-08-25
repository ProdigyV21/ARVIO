package io.sentry.android.core.internal.util;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements io.sentry.util.thread.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f16600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile long f16601b;

    static {
        e eVar = new e();
        new Handler(Looper.getMainLooper()).post(new com.discord.org.webrtc.i(4));
        f16600a = eVar;
        f16601b = Process.myTid();
    }

    public static long d(Thread thread) {
        return Build.VERSION.SDK_INT >= 36 ? thread.threadId() : thread.getId();
    }

    @Override // io.sentry.util.thread.a
    public final boolean a() {
        return d(Looper.getMainLooper().getThread()) == d(Thread.currentThread());
    }

    @Override // io.sentry.util.thread.a
    public final String b() {
        return a() ? MediaTrack.ROLE_MAIN : Thread.currentThread().getName();
    }

    @Override // io.sentry.util.thread.a
    public final long c() {
        return Process.myTid();
    }
}
