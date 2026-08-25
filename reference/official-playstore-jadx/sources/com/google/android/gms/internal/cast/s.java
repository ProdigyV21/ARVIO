package com.google.android.gms.internal.cast;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f13592a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f13593b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f13594c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicInteger f13595d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f13596e;

    public s(androidx.leanback.widget.r rVar) {
        this.f13596e = rVar.f3109a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.f13592a = jCurrentTimeMillis;
        this.f13593b = jCurrentTimeMillis;
        this.f13595d = new AtomicInteger(1);
    }
}
