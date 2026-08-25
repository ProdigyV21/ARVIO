package ka;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class k1 extends j1 implements r0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f19603i;

    public k1(Executor executor) {
        Method method;
        this.f19603i = executor;
        Method method2 = pa.c.f21256a;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor : null;
            if (scheduledThreadPoolExecutor != null && (method = pa.c.f21256a) != null) {
                method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Executor executor = this.f19603i;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // ka.f0
    public final void dispatch(d7.j jVar, Runnable runnable) {
        try {
            this.f19603i.execute(runnable);
        } catch (RejectedExecutionException e5) {
            m0.g(jVar, m0.a("The task was rejected", e5));
            x0.f19655d.dispatch(jVar, runnable);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof k1) && ((k1) obj).f19603i == this.f19603i;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f19603i);
    }

    @Override // ka.r0
    public final z0 l(long j10, t2 t2Var, d7.j jVar) {
        Executor executor = this.f19603i;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(t2Var, j10, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e5) {
                m0.g(jVar, m0.a("The task was rejected", e5));
            }
        }
        return scheduledFutureSchedule != null ? new y0(scheduledFutureSchedule) : n0.f19622r.l(j10, t2Var, jVar);
    }

    @Override // ka.f0
    public final String toString() {
        return this.f19603i.toString();
    }

    @Override // ka.r0
    public final void y(long j10, l lVar) {
        Executor executor = this.f19603i;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            com.google.common.util.concurrent.w0 w0Var = new com.google.common.util.concurrent.w0(this, lVar, 25);
            d7.j jVar = lVar.f19609o;
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(w0Var, j10, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e5) {
                m0.g(jVar, m0.a("The task was rejected", e5));
            }
        }
        if (scheduledFutureSchedule != null) {
            lVar.c(new i(scheduledFutureSchedule, 0));
        } else {
            n0.f19622r.y(j10, lVar);
        }
    }
}
