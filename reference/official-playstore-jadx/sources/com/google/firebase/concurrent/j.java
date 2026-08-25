package com.google.firebase.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends e0.g implements ScheduledFuture {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ScheduledFuture f14335r;

    public j(i iVar) {
        this.f14335r = iVar.a(new g(this));
    }

    @Override // java.lang.Comparable
    public final int compareTo(Delayed delayed) {
        return this.f14335r.compareTo(delayed);
    }

    @Override // e0.g
    public final void d() {
        ScheduledFuture scheduledFuture = this.f14335r;
        Object obj = this.f14990i;
        scheduledFuture.cancel((obj instanceof e0.a) && ((e0.a) obj).f14971a);
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.f14335r.getDelay(timeUnit);
    }
}
