package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class l1 extends i1 implements h1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ScheduledExecutorService f14211l;

    public l1(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.f14211l = scheduledExecutorService;
    }

    @Override // com.google.common.util.concurrent.i1, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        androidx.compose.ui.text.platform.extensions.a.i(this);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j10, TimeUnit timeUnit) {
        t1 t1Var = new t1(callable);
        return new j1(t1Var, this.f14211l.schedule(t1Var, j10, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        k1 k1Var = new k1(runnable);
        return new j1(k1Var, this.f14211l.scheduleAtFixedRate(k1Var, j10, j11, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        k1 k1Var = new k1(runnable);
        return new j1(k1Var, this.f14211l.scheduleWithFixedDelay(k1Var, j10, j11, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j10, TimeUnit timeUnit) {
        t1 t1Var = new t1(Executors.callable(runnable, null));
        return new j1(t1Var, this.f14211l.schedule(t1Var, j10, timeUnit));
    }
}
