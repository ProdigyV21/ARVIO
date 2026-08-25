package io.sentry.transport;

import io.sentry.ILogger;
import io.sentry.a5;
import io.sentry.l0;
import io.sentry.w5;
import io.sentry.z4;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends ThreadPoolExecutor implements AutoCloseable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f17926i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public z4 f17927l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ILogger f17928m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a5 f17929n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final a8.e f17930o;

    public n(int i10, l0 l0Var, a aVar, ILogger iLogger, a5 a5Var) {
        super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), l0Var, aVar);
        this.f17927l = null;
        this.f17930o = new a8.e(29);
        this.f17926i = i10;
        this.f17928m = iLogger;
        this.f17929n = a5Var;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public final void afterExecute(Runnable runnable, Throwable th) {
        a8.e eVar = this.f17930o;
        try {
            super.afterExecute(runnable, th);
        } finally {
            q qVar = (q) eVar.f183l;
            int i10 = q.f17937i;
            qVar.releaseShared(1);
        }
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        androidx.compose.ui.text.platform.extensions.a.l(this);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        a8.e eVar = this.f17930o;
        q qVar = (q) eVar.f183l;
        q qVar2 = (q) eVar.f183l;
        int iA = q.a(qVar);
        int i10 = this.f17926i;
        ILogger iLogger = this.f17928m;
        a5 a5Var = this.f17929n;
        if (iA >= i10) {
            this.f17927l = a5Var.a();
            iLogger.q(w5.WARNING, "Submit cancelled", new Object[0]);
            return new m();
        }
        q.b(qVar2);
        try {
            return super.submit(runnable);
        } catch (RejectedExecutionException e5) {
            qVar2.releaseShared(1);
            this.f17927l = a5Var.a();
            iLogger.l(w5.WARNING, "Submit rejected by thread pool executor", e5);
            return new m();
        }
    }
}
