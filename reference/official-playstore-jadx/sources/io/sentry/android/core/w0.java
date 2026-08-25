package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.w5;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class w0 implements io.sentry.hints.e, io.sentry.hints.l, io.sentry.hints.o, io.sentry.hints.i, io.sentry.hints.b, io.sentry.hints.k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f16797i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f16798l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CountDownLatch f16799m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f16800n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ILogger f16801o;

    public w0(long j10, ILogger iLogger) {
        reset();
        this.f16800n = j10;
        ac.b.V(iLogger, "ILogger is required.");
        this.f16801o = iLogger;
    }

    @Override // io.sentry.hints.l
    public final boolean a() {
        return this.f16797i;
    }

    @Override // io.sentry.hints.o
    public final void c(boolean z) {
        this.f16798l = z;
        this.f16799m.countDown();
    }

    @Override // io.sentry.hints.l
    public final void d(boolean z) {
        this.f16797i = z;
    }

    @Override // io.sentry.hints.o
    public final boolean e() {
        return this.f16798l;
    }

    @Override // io.sentry.hints.i
    public final boolean g() {
        try {
            return this.f16799m.await(this.f16800n, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e5) {
            Thread.currentThread().interrupt();
            this.f16801o.l(w5.ERROR, "Exception while awaiting on lock.", e5);
            return false;
        }
    }

    @Override // io.sentry.hints.k
    public final void reset() {
        this.f16799m = new CountDownLatch(1);
        this.f16797i = false;
        this.f16798l = false;
    }
}
