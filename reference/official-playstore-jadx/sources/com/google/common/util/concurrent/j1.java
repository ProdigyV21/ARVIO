package com.google.common.util.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class j1 extends com.google.common.collect.u0 implements e1, d1, Future {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f14208i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ScheduledFuture f14209l;

    public j1(p pVar, ScheduledFuture scheduledFuture) {
        this.f14208i = pVar;
        this.f14209l = scheduledFuture;
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        this.f14208i.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        boolean zI = i(z);
        if (zI) {
            this.f14209l.cancel(z);
        }
        return zI;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Delayed delayed) {
        return this.f14209l.compareTo(delayed);
    }

    @Override // com.google.common.collect.u0
    public final Object delegate() {
        return this.f14208i;
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f14208i.get();
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.f14209l.getDelay(timeUnit);
    }

    public final boolean i(boolean z) {
        return this.f14208i.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f14208i.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f14208i.isDone();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        return this.f14208i.get(j10, timeUnit);
    }
}
