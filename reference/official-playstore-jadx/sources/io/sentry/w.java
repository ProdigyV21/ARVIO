package io.sentry;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class w implements io.sentry.hints.e, io.sentry.hints.l, io.sentry.hints.o, io.sentry.hints.i, io.sentry.hints.g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f18027i = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f18028l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CountDownLatch f18029m = new CountDownLatch(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f18030n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ILogger f18031o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f18032p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Queue f18033q;

    public w(long j10, ILogger iLogger, String str, r7 r7Var) {
        this.f18030n = j10;
        this.f18032p = str;
        this.f18033q = r7Var;
        this.f18031o = iLogger;
    }

    @Override // io.sentry.hints.l
    public final boolean a() {
        return this.f18027i;
    }

    @Override // io.sentry.hints.g
    public final void b() {
        this.f18033q.add(this.f18032p);
    }

    @Override // io.sentry.hints.o
    public final void c(boolean z) {
        this.f18028l = z;
        this.f18029m.countDown();
    }

    @Override // io.sentry.hints.l
    public final void d(boolean z) {
        this.f18027i = z;
    }

    @Override // io.sentry.hints.o
    public final boolean e() {
        return this.f18028l;
    }

    @Override // io.sentry.hints.i
    public final boolean g() {
        try {
            return this.f18029m.await(this.f18030n, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e5) {
            Thread.currentThread().interrupt();
            this.f18031o.l(w5.ERROR, "Exception while awaiting on lock.", e5);
            return false;
        }
    }
}
