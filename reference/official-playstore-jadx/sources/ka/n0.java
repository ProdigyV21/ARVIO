package ka;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends h1 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final n0 f19622r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final long f19623s;

    static {
        Long l10;
        n0 n0Var = new n0();
        f19622r = n0Var;
        n0Var.u0(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l10 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l10 = 1000L;
        }
        f19623s = timeUnit.toNanos(l10.longValue());
    }

    @Override // ka.i1
    public final Thread K0() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    @Override // ka.i1
    public final void M0(long j10, f1 f1Var) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // ka.h1
    public final void N0(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.N0(runnable);
    }

    public final synchronized void R0() {
        int i10 = debugStatus;
        if (i10 == 2 || i10 == 3) {
            debugStatus = 3;
            h1.f19595o.set(this, null);
            h1.f19596p.set(this, null);
            notifyAll();
        }
    }

    @Override // ka.h1, ka.r0
    public final z0 l(long j10, t2 t2Var, d7.j jVar) {
        long j11 = j10 > 0 ? j10 >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j10 : 0L;
        if (j11 >= 4611686018427387903L) {
            return k2.f19604i;
        }
        long jNanoTime = System.nanoTime();
        e1 e1Var = new e1(j11 + jNanoTime, t2Var);
        Q0(jNanoTime, e1Var);
        return e1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zP0;
        r2.f19629a.set(this);
        try {
            synchronized (this) {
                int i10 = debugStatus;
                if (i10 == 2 || i10 == 3) {
                    if (zP0) {
                        return;
                    } else {
                        return;
                    }
                }
                debugStatus = 1;
                notifyAll();
                long j10 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jD0 = D0();
                    if (jD0 == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j10 == Long.MAX_VALUE) {
                            j10 = f19623s + jNanoTime;
                        }
                        long j11 = j10 - jNanoTime;
                        if (j11 <= 0) {
                            _thread = null;
                            R0();
                            if (P0()) {
                                return;
                            }
                            K0();
                            return;
                        }
                        if (jD0 > j11) {
                            jD0 = j11;
                        }
                    } else {
                        j10 = Long.MAX_VALUE;
                    }
                    if (jD0 > 0) {
                        int i11 = debugStatus;
                        if (i11 == 2 || i11 == 3) {
                            _thread = null;
                            R0();
                            if (P0()) {
                                return;
                            }
                            K0();
                            return;
                        }
                        LockSupport.parkNanos(this, jD0);
                    }
                }
            }
        } finally {
            _thread = null;
            R0();
            if (!P0()) {
                K0();
            }
        }
    }

    @Override // ka.h1, ka.c1
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
