package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d f14196d = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f14197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f14198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f14199c;

    public d(Runnable runnable, Executor executor) {
        this.f14197a = runnable;
        this.f14198b = executor;
    }

    public d() {
        this.f14197a = null;
        this.f14198b = null;
    }
}
