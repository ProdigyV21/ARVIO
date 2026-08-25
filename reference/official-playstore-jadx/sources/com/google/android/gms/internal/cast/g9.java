package com.google.android.gms.internal.cast;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes4.dex */
public final class g9 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g9 f13262d = new g9();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f13263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f13264b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g9 f13265c;

    public g9() {
        this.f13263a = null;
        this.f13264b = null;
    }

    public g9(Runnable runnable, Executor executor) {
        this.f13263a = runnable;
        this.f13264b = executor;
    }
}
