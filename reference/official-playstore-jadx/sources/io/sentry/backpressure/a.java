package io.sentry.backpressure;

import io.sentry.g1;
import io.sentry.j4;
import io.sentry.w5;
import io.sentry.y6;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements b, Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17106i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j4 f17107l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17108m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile Future f17109n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.util.b f17110o;

    public a(y6 y6Var) {
        j4 j4Var = j4.f17401a;
        this.f17108m = 0;
        this.f17109n = null;
        this.f17110o = new io.sentry.util.b();
        this.f17106i = y6Var;
        this.f17107l = j4Var;
    }

    @Override // io.sentry.backpressure.b
    public final int a() {
        return this.f17108m;
    }

    public final void b(int i10) {
        g1 executorService = this.f17106i.getExecutorService();
        if (executorService.isClosed()) {
            return;
        }
        io.sentry.util.a aVarA = this.f17110o.a();
        try {
            try {
                this.f17109n = executorService.a(i10, this);
            } catch (RejectedExecutionException e5) {
                this.f17106i.getLogger().l(w5.WARNING, "Backpressure monitor reschedule task rejected", e5);
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

    @Override // io.sentry.backpressure.b
    public final void close() {
        Future future = this.f17109n;
        if (future != null) {
            io.sentry.util.a aVarA = this.f17110o.a();
            try {
                future.cancel(true);
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
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zC = this.f17107l.c();
        y6 y6Var = this.f17106i;
        if (zC) {
            if (this.f17108m > 0) {
                y6Var.getLogger().q(w5.DEBUG, "Health check positive, reverting to normal sampling.", new Object[0]);
            }
            this.f17108m = 0;
        } else {
            int i10 = this.f17108m;
            if (i10 < 10) {
                this.f17108m = i10 + 1;
                y6Var.getLogger().q(w5.DEBUG, "Health check negative, downsampling with a factor of %d", Integer.valueOf(this.f17108m));
            }
        }
        b(10000);
    }

    @Override // io.sentry.backpressure.b
    public final void start() {
        b(500);
    }
}
