package io.sentry.hints;

import io.sentry.ILogger;
import io.sentry.w5;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d implements f, i {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CountDownLatch f17340i = new CountDownLatch(1);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f17341l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ILogger f17342m;

    public d(long j10, ILogger iLogger) {
        this.f17341l = j10;
        this.f17342m = iLogger;
    }

    @Override // io.sentry.hints.f
    public final void e() {
        this.f17340i.countDown();
    }

    @Override // io.sentry.hints.i
    public final boolean g() {
        try {
            return this.f17340i.await(this.f17341l, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e5) {
            Thread.currentThread().interrupt();
            this.f17342m.l(w5.ERROR, "Exception while awaiting for flush in BlockingFlushHint", e5);
            return false;
        }
    }
}
