package io.sentry.android.replay.util;

import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.text.u;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements ScheduledExecutorService, AutoCloseable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ScheduledExecutorService f17050i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y6 f17051l;

    public f(ScheduledExecutorService scheduledExecutorService, y6 y6Var) {
        this.f17050i = scheduledExecutorService;
        this.f17051l = y6Var;
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j10, TimeUnit timeUnit) {
        return this.f17050i.awaitTermination(j10, timeUnit);
    }

    @Override // java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        androidx.compose.ui.text.platform.extensions.a.k(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f17050i.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection collection) {
        return this.f17050i.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection collection) {
        return this.f17050i.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return this.f17050i.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return this.f17050i.isTerminated();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Runnable runnable, long j10, TimeUnit timeUnit) {
        return this.f17050i.schedule(runnable, j10, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        return this.f17050i.scheduleAtFixedRate(runnable, j10, j11, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j10, long j11, TimeUnit timeUnit) {
        return this.f17050i.scheduleWithFixedDelay(runnable, j10, j11, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        synchronized (this) {
            if (!this.f17050i.isShutdown()) {
                this.f17050i.shutdown();
            }
            try {
                if (!this.f17050i.awaitTermination(this.f17051l.getShutdownTimeoutMillis(), TimeUnit.MILLISECONDS)) {
                    shutdownNow();
                }
            } catch (InterruptedException unused) {
                shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public final List shutdownNow() {
        return this.f17050i.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable, Object obj) {
        return this.f17050i.submit(runnable, obj);
    }

    @Override // java.util.concurrent.ExecutorService
    public final List invokeAll(Collection collection, long j10, TimeUnit timeUnit) {
        return this.f17050i.invokeAll(collection, j10, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Object invokeAny(Collection collection, long j10, TimeUnit timeUnit) {
        return this.f17050i.invokeAny(collection, j10, timeUnit);
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final ScheduledFuture schedule(Callable callable, long j10, TimeUnit timeUnit) {
        return this.f17050i.schedule(callable, j10, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Callable callable) {
        return this.f17050i.submit(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future submit(Runnable runnable) {
        String str;
        if (u.P(Thread.currentThread().getName(), "SentryReplayIntegration", false)) {
            runnable.run();
            return null;
        }
        try {
            return this.f17050i.submit(new io.sentry.android.ndk.b(runnable, this, 6));
        } catch (Throwable th) {
            ILogger logger = this.f17051l.getLogger();
            w5 w5Var = w5.ERROR;
            StringBuilder sb2 = new StringBuilder("Failed to submit task ");
            if (runnable instanceof g) {
                str = ((g) runnable).f17052i;
            } else {
                str = "";
            }
            logger.l(w5Var, a0.c.p(sb2, str, " to executor"), th);
            return null;
        }
    }
}
