package io.sentry;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class n5 implements g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScheduledThreadPoolExecutor f17488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.util.b f17489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l5 f17490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y6 f17491d;

    public n5(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, y6 y6Var) {
        this.f17489b = new io.sentry.util.b();
        this.f17490c = new l5();
        this.f17488a = scheduledThreadPoolExecutor;
        this.f17491d = y6Var;
    }

    @Override // io.sentry.g1
    public final Future a(long j10, Runnable runnable) {
        return this.f17488a.schedule(runnable, j10, TimeUnit.MILLISECONDS);
    }

    @Override // io.sentry.g1
    public final void b(long j10) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.f17488a;
        io.sentry.util.a aVarA = this.f17489b.a();
        try {
            if (!scheduledThreadPoolExecutor.isShutdown()) {
                scheduledThreadPoolExecutor.shutdown();
                try {
                    if (!scheduledThreadPoolExecutor.awaitTermination(j10, TimeUnit.MILLISECONDS)) {
                        scheduledThreadPoolExecutor.shutdownNow();
                    }
                } catch (InterruptedException unused) {
                    scheduledThreadPoolExecutor.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.g1
    public final void c() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.f17488a;
        try {
            scheduledThreadPoolExecutor.submit(new q4(this, 1));
        } catch (RejectedExecutionException e5) {
            y6 y6Var = this.f17491d;
            if (y6Var != null) {
                y6Var.getLogger().l(w5.WARNING, "Prewarm task rejected from " + scheduledThreadPoolExecutor, e5);
            }
        }
    }

    @Override // io.sentry.g1
    public final boolean isClosed() {
        io.sentry.util.a aVarA = this.f17489b.a();
        try {
            boolean zIsShutdown = this.f17488a.isShutdown();
            aVarA.close();
            return zIsShutdown;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.g1
    public final Future submit(Runnable runnable) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = this.f17488a;
        if (scheduledThreadPoolExecutor.getQueue().size() >= 271) {
            scheduledThreadPoolExecutor.purge();
        }
        if (scheduledThreadPoolExecutor.getQueue().size() < 271) {
            return scheduledThreadPoolExecutor.submit(runnable);
        }
        y6 y6Var = this.f17491d;
        if (y6Var != null) {
            y6Var.getLogger().q(w5.WARNING, "Task " + runnable + " rejected from " + scheduledThreadPoolExecutor, new Object[0]);
        }
        return new m5();
    }

    public n5(y6 y6Var) {
        this(new ScheduledThreadPoolExecutor(1, new l0(1)), y6Var);
    }

    public n5() {
        this(new ScheduledThreadPoolExecutor(1, new l0(1)), null);
    }
}
