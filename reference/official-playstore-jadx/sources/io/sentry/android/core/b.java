package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Handler;
import android.os.SystemClock;
import io.sentry.ILogger;
import io.sentry.exception.ExceptionMechanismException;
import io.sentry.j5;
import io.sentry.s4;
import io.sentry.w5;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends Thread {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f16437i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final q f16438l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r0 f16439m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final com.google.firebase.installations.d f16440n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f16441o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f16442p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ILogger f16443q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile long f16444r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicBoolean f16445s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Context f16446t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final androidx.activity.n f16447u;

    public b(long j10, boolean z, q qVar, ILogger iLogger, Context context) {
        com.google.firebase.installations.d dVar = new com.google.firebase.installations.d(25);
        r0 r0Var = new r0();
        super("|ANR-WatchDog|");
        this.f16444r = 0L;
        this.f16445s = new AtomicBoolean(false);
        this.f16440n = dVar;
        this.f16442p = j10;
        this.f16441o = 500L;
        this.f16437i = z;
        this.f16438l = qVar;
        this.f16443q = iLogger;
        this.f16439m = r0Var;
        this.f16446t = context;
        this.f16447u = new androidx.activity.n(this, dVar);
        if (j10 < 1000) {
            throw new IllegalArgumentException(String.format("ANRWatchDog: timeoutIntervalMillis has to be at least %d ms", 1000L));
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() throws Throwable {
        List<ActivityManager.ProcessErrorStateInfo> processesInErrorState;
        this.f16447u.run();
        while (!isInterrupted()) {
            ((Handler) this.f16439m.f16735a).post(this.f16447u);
            try {
                Thread.sleep(this.f16441o);
                this.f16440n.getClass();
                if (SystemClock.uptimeMillis() - this.f16444r > this.f16442p) {
                    if (this.f16437i || !(Debug.isDebuggerConnected() || Debug.waitingForDebugger())) {
                        ActivityManager activityManager = (ActivityManager) this.f16446t.getSystemService("activity");
                        if (activityManager != null) {
                            try {
                                processesInErrorState = activityManager.getProcessesInErrorState();
                            } catch (Throwable th) {
                                this.f16443q.l(w5.ERROR, "Error getting ActivityManager#getProcessesInErrorState.", th);
                                processesInErrorState = null;
                            }
                            if (processesInErrorState != null) {
                                Iterator<ActivityManager.ProcessErrorStateInfo> it = processesInErrorState.iterator();
                                while (it.hasNext()) {
                                    if (it.next().condition == 2) {
                                    }
                                }
                            }
                        }
                        if (this.f16445s.compareAndSet(false, true)) {
                            ApplicationNotResponding applicationNotResponding = new ApplicationNotResponding(a0.c.j(this.f16442p, " ms.", new StringBuilder("Application Not Responding for at least ")), ((Handler) this.f16439m.f16735a).getLooper().getThread());
                            SentryAndroidOptions sentryAndroidOptions = this.f16438l.f16726l;
                            b bVar = AnrIntegration.f16318o;
                            sentryAndroidOptions.getLogger().q(w5.INFO, "ANR triggered with message: %s", applicationNotResponding.getMessage());
                            boolean zEquals = Boolean.TRUE.equals(h0.f16496o.f16500n);
                            String strC = "ANR for at least " + sentryAndroidOptions.getAnrTimeoutIntervalMillis() + " ms.";
                            if (zEquals) {
                                strC = androidx.compose.material3.d.C("Background ", strC);
                            }
                            Thread thread = applicationNotResponding.f16335i;
                            ApplicationNotResponding applicationNotResponding2 = thread == null ? new ApplicationNotResponding(strC) : new ApplicationNotResponding(strC, thread);
                            io.sentry.protocol.m mVar = new io.sentry.protocol.m();
                            mVar.f17638i = "ANR";
                            j5 j5Var = new j5(new ExceptionMechanismException(mVar, applicationNotResponding2, thread, true));
                            j5Var.E = w5.ERROR;
                            s4.b().t(j5Var, qb.l.k(new a0(zEquals)));
                        }
                    } else {
                        this.f16443q.q(w5.DEBUG, "An ANR was detected but ignored because the debugger is connected.", new Object[0]);
                        this.f16445s.set(true);
                    }
                }
            } catch (InterruptedException e5) {
                try {
                    Thread.currentThread().interrupt();
                    this.f16443q.q(w5.WARNING, "Interrupted: %s", e5.getMessage());
                    return;
                } catch (SecurityException unused) {
                    this.f16443q.q(w5.WARNING, "Failed to interrupt due to SecurityException: %s", e5.getMessage());
                    return;
                }
            }
        }
    }
}
