package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Unsafe f13253a;

    public g(Unsafe unsafe) {
        this.f13253a = unsafe;
    }

    public abstract void a(Object obj, long j10, byte b10);

    public abstract boolean b(long j10, Object obj);

    public abstract void c(Object obj, long j10, boolean z);

    public abstract float d(long j10, Object obj);

    public abstract void e(Object obj, long j10, float f10);

    public abstract double f(long j10, Object obj);

    public abstract void g(Object obj, long j10, double d4);
}
