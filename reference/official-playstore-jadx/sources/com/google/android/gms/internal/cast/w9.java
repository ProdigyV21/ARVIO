package com.google.android.gms.internal.cast;

import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class w9 extends u0 implements ScheduledFuture, com.google.common.util.concurrent.d1, Future {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i9 f13669m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ScheduledFuture f13670n;

    public w9(i9 i9Var, ScheduledFuture scheduledFuture) {
        super(9);
        this.f13669m = i9Var;
        this.f13670n = scheduledFuture;
    }

    @Override // com.google.common.util.concurrent.d1
    public final void addListener(Runnable runnable, Executor executor) {
        this.f13669m.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        boolean zCancel = this.f13669m.cancel(z);
        if (zCancel) {
            this.f13670n.cancel(z);
        }
        return zCancel;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Delayed delayed) {
        return this.f13670n.compareTo(delayed);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.f13669m.get();
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.f13670n.getDelay(timeUnit);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f13669m.f13559m instanceof d9;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f13669m.isDone();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j10, TimeUnit timeUnit) {
        return this.f13669m.get(j10, timeUnit);
    }
}
