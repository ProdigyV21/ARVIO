package io.sentry.logger;

import androidx.activity.n;
import androidx.leanback.widget.u;
import com.google.android.gms.common.api.internal.g0;
import io.sentry.c6;
import io.sentry.d6;
import io.sentry.n5;
import io.sentry.transport.q;
import io.sentry.w5;
import io.sentry.y5;
import io.sentry.y6;
import io.sentry.z5;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class d implements b, io.sentry.metrics.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17449i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y6 f17450l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g0 f17451m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ConcurrentLinkedQueue f17452n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final n5 f17453o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile Future f17454p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final io.sentry.util.b f17455q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final a8.e f17456r;

    public d(y6 y6Var, g0 g0Var, int i10) {
        this.f17449i = i10;
        switch (i10) {
            case 1:
                this.f17455q = new io.sentry.util.b();
                this.f17456r = new a8.e(29);
                this.f17450l = y6Var;
                this.f17451m = g0Var;
                this.f17452n = new ConcurrentLinkedQueue();
                this.f17453o = new n5(y6Var);
                break;
            default:
                n5 n5Var = new n5(y6Var);
                this.f17455q = new io.sentry.util.b();
                this.f17456r = new a8.e(29);
                this.f17450l = y6Var;
                this.f17451m = g0Var;
                this.f17452n = new ConcurrentLinkedQueue();
                this.f17453o = n5Var;
                break;
        }
    }

    @Override // io.sentry.logger.b, io.sentry.metrics.b
    public void a(boolean z) {
        switch (this.f17449i) {
            case 0:
                n5 n5Var = this.f17453o;
                if (!z) {
                    n5Var.b(this.f17450l.getShutdownTimeoutMillis());
                    while (!this.f17452n.isEmpty()) {
                        d();
                    }
                } else {
                    f(true);
                    n5Var.submit(new n(this, 27));
                }
                break;
            default:
                n5 n5Var2 = this.f17453o;
                if (!z) {
                    n5Var2.b(this.f17450l.getShutdownTimeoutMillis());
                    while (!this.f17452n.isEmpty()) {
                        c();
                    }
                } else {
                    e(true);
                    n5Var2.submit(new n(this, 28));
                }
                break;
        }
    }

    @Override // io.sentry.logger.b, io.sentry.metrics.b
    public final void b(long j10) {
        switch (this.f17449i) {
            case 0:
                f(true);
                try {
                    a8.e eVar = this.f17456r;
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    eVar.getClass();
                    ((q) eVar.f183l).tryAcquireSharedNanos(1, timeUnit.toNanos(j10));
                } catch (InterruptedException e5) {
                    this.f17450l.getLogger().l(w5.ERROR, "Failed to flush log events", e5);
                    Thread.currentThread().interrupt();
                    return;
                }
                break;
            default:
                e(true);
                try {
                    a8.e eVar2 = this.f17456r;
                    TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
                    eVar2.getClass();
                    ((q) eVar2.f183l).tryAcquireSharedNanos(1, timeUnit2.toNanos(j10));
                } catch (InterruptedException e6) {
                    this.f17450l.getLogger().l(w5.ERROR, "Failed to flush metrics events", e6);
                    Thread.currentThread().interrupt();
                }
                break;
        }
    }

    public void c() {
        ArrayList arrayList = new ArrayList(1000);
        do {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.f17452n;
            c6 c6Var = (c6) concurrentLinkedQueue.poll();
            if (c6Var != null) {
                arrayList.add(c6Var);
            }
            if (concurrentLinkedQueue.isEmpty()) {
                break;
            }
        } while (arrayList.size() < 1000);
        if (arrayList.isEmpty()) {
            return;
        }
        d6 d6Var = new d6(arrayList);
        g0 g0Var = this.f17451m;
        g0Var.getClass();
        try {
            g0Var.z(g0Var.q(d6Var), null);
        } catch (IOException e5) {
            ((y6) g0Var.f12734b).getLogger().k(w5.WARNING, e5, "Capturing metrics failed.", new Object[0]);
        }
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            q qVar = (q) this.f17456r.f183l;
            int i11 = q.f17937i;
            qVar.releaseShared(1);
        }
    }

    public void d() {
        ArrayList arrayList = new ArrayList(100);
        do {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.f17452n;
            y5 y5Var = (y5) concurrentLinkedQueue.poll();
            if (y5Var != null) {
                arrayList.add(y5Var);
            }
            if (concurrentLinkedQueue.isEmpty()) {
                break;
            }
        } while (arrayList.size() < 100);
        if (arrayList.isEmpty()) {
            return;
        }
        z5 z5Var = new z5(arrayList);
        g0 g0Var = this.f17451m;
        g0Var.getClass();
        try {
            g0Var.z(g0Var.p(z5Var), null);
        } catch (IOException e5) {
            ((y6) g0Var.f12734b).getLogger().k(w5.WARNING, e5, "Capturing logs failed.", new Object[0]);
        }
        for (int i10 = 0; i10 < arrayList.size(); i10++) {
            q qVar = (q) this.f17456r.f183l;
            int i11 = q.f17937i;
            qVar.releaseShared(1);
        }
    }

    public void e(boolean z) {
        io.sentry.util.a aVarA = this.f17455q.a();
        try {
            try {
                this.f17454p = this.f17453o.a(z ? 0 : 5000, new u(this, 12));
            } catch (RejectedExecutionException e5) {
                this.f17450l.getLogger().l(w5.WARNING, "Metrics batch processor flush task rejected", e5);
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

    public void f(boolean z) {
        io.sentry.util.a aVarA = this.f17455q.a();
        try {
            try {
                this.f17454p = this.f17453o.a(z ? 0 : 5000, new u(this, 11));
            } catch (RejectedExecutionException e5) {
                this.f17450l.getLogger().l(w5.WARNING, "Logs batch processor flush task rejected", e5);
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
}
