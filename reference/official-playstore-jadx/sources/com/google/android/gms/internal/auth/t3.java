package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes4.dex */
public abstract class t3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Unsafe f13125a;

    public t3(Unsafe unsafe) {
        this.f13125a = unsafe;
    }

    public abstract double a(long j10, Object obj);

    public abstract float b(long j10, Object obj);

    public abstract void c(Object obj, long j10, boolean z);

    public abstract void d(Object obj, long j10, double d4);

    public abstract void e(Object obj, long j10, float f10);

    public abstract boolean f(long j10, Object obj);
}
