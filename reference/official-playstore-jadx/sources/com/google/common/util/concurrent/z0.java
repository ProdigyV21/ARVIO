package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes4.dex */
public final class z0 implements d1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final z0 f14245l = new z0(null);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c1 f14246m = new c1(z0.class);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f14247i;

    public z0(Object obj) {
        this.f14247i = obj;
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        ac.b.p(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (Exception e5) {
            f14246m.a().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e5);
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f14247i;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.f14247i + "]]";
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        timeUnit.getClass();
        return this.f14247i;
    }
}
