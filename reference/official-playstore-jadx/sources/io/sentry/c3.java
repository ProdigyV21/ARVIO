package io.sentry;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes4.dex */
public final class c3 implements g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c3 f17117a = new c3();

    @Override // io.sentry.g1
    public final Future a(long j10, Runnable runnable) {
        return new FutureTask(new k0(1));
    }

    @Override // io.sentry.g1
    public final void b(long j10) {
    }

    @Override // io.sentry.g1
    public final void c() {
    }

    @Override // io.sentry.g1
    public final boolean isClosed() {
        return false;
    }

    @Override // io.sentry.g1
    public final Future submit(Runnable runnable) {
        return new FutureTask(new k0(1));
    }
}
