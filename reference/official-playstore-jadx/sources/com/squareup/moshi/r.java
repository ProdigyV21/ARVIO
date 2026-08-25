package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes4.dex */
public abstract class r implements Closeable, Flushable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14458i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f14459l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String[] f14460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f14461n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f14462o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14463p;

    public final String i() {
        return e0.c(this.f14458i, this.f14459l, this.f14460m, this.f14461n);
    }

    public final int j() {
        int i10 = this.f14458i;
        if (i10 != 0) {
            return this.f14459l[i10 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }
}
