package com.google.android.gms.internal.cast;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class y9 extends v9 implements ScheduledExecutorService {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ScheduledExecutorService f13697l;

    public y9(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.f13697l = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.cast.v9, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        androidx.compose.ui.text.platform.extensions.a.g(this);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j10, TimeUnit timeUnit) {
        aa aaVar = new aa(Executors.callable(runnable, null));
        return new w9(aaVar, this.f13697l.schedule(aaVar, j10, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        x9 x9Var = new x9(runnable);
        return new w9(x9Var, this.f13697l.scheduleAtFixedRate(x9Var, j10, j11, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        x9 x9Var = new x9(runnable);
        return new w9(x9Var, this.f13697l.scheduleWithFixedDelay(x9Var, j10, j11, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j10, TimeUnit timeUnit) {
        aa aaVar = new aa(callable);
        return new w9(aaVar, this.f13697l.schedule(aaVar, j10, timeUnit));
    }
}
