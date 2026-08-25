package io.sentry.android.core;

import io.sentry.ILogger;

/* JADX INFO: loaded from: classes5.dex */
public final class x1 extends io.sentry.hints.d implements io.sentry.hints.c, io.sentry.hints.j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16825n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f16826o;

    public x1(long j10, ILogger iLogger, long j11, boolean z) {
        super(j10, iLogger);
        this.f16825n = j11;
        this.f16826o = z;
    }

    @Override // io.sentry.hints.j
    public final Long a() {
        return Long.valueOf(this.f16825n);
    }

    @Override // io.sentry.hints.c
    public final boolean b() {
        return this.f16826o;
    }

    @Override // io.sentry.hints.f
    public final boolean c(io.sentry.protocol.v vVar) {
        return true;
    }

    @Override // io.sentry.hints.f
    public final void d(io.sentry.protocol.v vVar) {
    }
}
