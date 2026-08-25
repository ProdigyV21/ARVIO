package io.sentry.android.core;

import io.sentry.ILogger;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends io.sentry.hints.d implements io.sentry.hints.c, io.sentry.hints.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16448n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f16449o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f16450p;

    public b0(long j10, ILogger iLogger, long j11, boolean z, boolean z5) {
        super(j10, iLogger);
        this.f16448n = j11;
        this.f16449o = z;
        this.f16450p = z5;
    }

    @Override // io.sentry.hints.a
    public final Long a() {
        return Long.valueOf(this.f16448n);
    }

    @Override // io.sentry.hints.c
    public final boolean b() {
        return this.f16449o;
    }

    @Override // io.sentry.hints.f
    public final boolean c(io.sentry.protocol.v vVar) {
        return true;
    }

    @Override // io.sentry.hints.f
    public final void d(io.sentry.protocol.v vVar) {
    }

    @Override // io.sentry.hints.a
    public final boolean f() {
        return false;
    }

    @Override // io.sentry.hints.a
    public final String h() {
        return this.f16450p ? "anr_background" : "anr_foreground";
    }
}
