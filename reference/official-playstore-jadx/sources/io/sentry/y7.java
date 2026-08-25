package io.sentry;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class y7 extends io.sentry.hints.d implements io.sentry.hints.m, io.sentry.hints.p {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicReference f18077n;

    public y7(long j10, ILogger iLogger) {
        super(j10, iLogger);
        this.f18077n = new AtomicReference();
    }

    @Override // io.sentry.hints.f
    public final boolean c(io.sentry.protocol.v vVar) {
        io.sentry.protocol.v vVar2 = (io.sentry.protocol.v) this.f18077n.get();
        return vVar2 != null && vVar2.equals(vVar);
    }

    @Override // io.sentry.hints.f
    public final void d(io.sentry.protocol.v vVar) {
        this.f18077n.set(vVar);
    }
}
