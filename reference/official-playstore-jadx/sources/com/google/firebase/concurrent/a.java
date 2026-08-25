package com.google.firebase.concurrent;

import android.os.StrictMode;
import androidx.activity.s;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements ThreadFactory {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadFactory f14309e = Executors.defaultThreadFactory();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicLong f14310a = new AtomicLong();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f14312c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StrictMode.ThreadPolicy f14313d;

    public a(String str, int i10, StrictMode.ThreadPolicy threadPolicy) {
        this.f14311b = str;
        this.f14312c = i10;
        this.f14313d = threadPolicy;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = f14309e.newThread(new s(this, runnable, 20));
        Locale locale = Locale.ROOT;
        threadNewThread.setName(this.f14311b + " Thread #" + this.f14310a.getAndIncrement());
        return threadNewThread;
    }
}
