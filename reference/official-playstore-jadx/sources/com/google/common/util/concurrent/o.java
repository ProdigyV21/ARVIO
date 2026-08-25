package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f14225c = new o();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Thread f14226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile o f14227b;

    public o() {
        p.ATOMIC_HELPER.g(this, Thread.currentThread());
    }
}
