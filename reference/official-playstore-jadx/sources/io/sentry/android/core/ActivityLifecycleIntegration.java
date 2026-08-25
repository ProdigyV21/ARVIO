package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.appcompat.widget.u4;
import androidx.core.app.FrameMetricsAggregator;
import io.sentry.b6;
import io.sentry.e6;
import io.sentry.h3;
import io.sentry.j4;
import io.sentry.m2;
import io.sentry.p7;
import io.sentry.q7;
import io.sentry.u7;
import io.sentry.w5;
import io.sentry.w7;
import io.sentry.y6;
import io.sentry.z4;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityLifecycleIntegration implements io.sentry.s1, Closeable, Application.ActivityLifecycleCallbacks {
    public final u4 A;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Application f16304i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f16305l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j4 f16306m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16307n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16310q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public io.sentry.k1 f16313t;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f16308o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16309p = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f16311r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public io.sentry.i0 f16312s = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final WeakHashMap f16314u = new WeakHashMap();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final WeakHashMap f16315v = new WeakHashMap();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final WeakHashMap f16316w = new WeakHashMap();
    public z4 x = new e6(new Date(0), 0);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Future f16317y = null;
    public final WeakHashMap z = new WeakHashMap();
    public final io.sentry.util.b B = new io.sentry.util.b();
    public final io.sentry.util.b C = new io.sentry.util.b();

    public ActivityLifecycleIntegration(Application application, r0 r0Var, u4 u4Var) {
        this.f16304i = application;
        this.f16305l = r0Var;
        this.A = u4Var;
        if (Build.VERSION.SDK_INT >= 29) {
            this.f16310q = true;
        }
    }

    public static void j(io.sentry.k1 k1Var, io.sentry.k1 k1Var2) {
        if (k1Var == null || k1Var.isFinished()) {
            return;
        }
        String strA = k1Var.a();
        if (strA == null || !strA.endsWith(" - Deadline Exceeded")) {
            strA = k1Var.a() + " - Deadline Exceeded";
        }
        k1Var.e(strA);
        z4 z4VarP = k1Var2 != null ? k1Var2.p() : null;
        if (z4VarP == null) {
            z4VarP = k1Var.r();
        }
        k(k1Var, z4VarP, q7.DEADLINE_EXCEEDED);
    }

    public static void k(io.sentry.k1 k1Var, z4 z4Var, q7 q7Var) {
        if (k1Var == null || k1Var.isFinished()) {
            return;
        }
        if (q7Var == null) {
            q7Var = k1Var.getStatus() != null ? k1Var.getStatus() : q7.OK;
        }
        k1Var.q(q7Var, z4Var);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16304i.unregisterActivityLifecycleCallbacks(this);
        SentryAndroidOptions sentryAndroidOptions = this.f16307n;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "ActivityLifecycleIntegration removed.", new Object[0]);
        }
        u4 u4Var = this.A;
        io.sentry.util.a aVarA = ((io.sentry.util.b) u4Var.f1778f).a();
        try {
            if (u4Var.c()) {
                u4Var.d("FrameMetricsAggregator.stop", new androidx.activity.n(u4Var, 14));
                ((FrameMetricsAggregator) ((io.sentry.util.k) u4Var.f1773a).a()).f1949a.q();
            }
            ((ConcurrentHashMap) u4Var.f1775c).clear();
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

    public final void i() {
        b6 b6Var;
        io.sentry.android.core.performance.h hVarB = io.sentry.android.core.performance.g.c().b(this.f16307n);
        if (hVarB.f16723n != 0) {
            b6Var = new b6((hVarB.d() ? hVarB.f16721l + hVarB.a() : 0L) * 1000000);
        } else {
            b6Var = null;
        }
        if (!this.f16308o || b6Var == null) {
            return;
        }
        k(this.f16313t, b6Var, null);
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16307n = sentryAndroidOptions;
        this.f16306m = j4.f17401a;
        this.f16308o = sentryAndroidOptions.isTracingEnabled() && sentryAndroidOptions.isEnableAutoActivityLifecycleTracing();
        this.f16312s = this.f16307n.getFullyDisplayedReporter();
        this.f16309p = this.f16307n.isEnableTimeToFullDisplayTracing();
        this.f16304i.registerActivityLifecycleCallbacks(this);
        this.f16307n.getLogger().q(w5.DEBUG, "ActivityLifecycleIntegration installed.", new Object[0]);
        xc.d.H("ActivityLifecycle");
    }

    public final void o(io.sentry.m1 m1Var, io.sentry.k1 k1Var, io.sentry.k1 k1Var2) {
        if (m1Var == null || m1Var.isFinished()) {
            return;
        }
        q7 q7Var = q7.DEADLINE_EXCEEDED;
        if (k1Var != null && !k1Var.isFinished()) {
            k1Var.i(q7Var);
        }
        j(k1Var2, k1Var);
        Future future = this.f16317y;
        if (future != null) {
            future.cancel(false);
            this.f16317y = null;
        }
        q7 status = m1Var.getStatus();
        if (status == null) {
            status = q7.OK;
        }
        m1Var.i(status);
        j4 j4Var = this.f16306m;
        if (j4Var != null) {
            j4Var.o(new a3.b(this, m1Var));
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        io.sentry.i0 i0Var;
        SentryAndroidOptions sentryAndroidOptions;
        if (!this.f16310q) {
            onActivityPreCreated(activity, bundle);
        }
        io.sentry.util.a aVarA = this.B.a();
        try {
            if (this.f16306m != null && (sentryAndroidOptions = this.f16307n) != null && sentryAndroidOptions.isEnableScreenTracking()) {
                this.f16306m.o(new e(qb.l.p(activity)));
            }
            y(activity);
            io.sentry.k1 k1Var = (io.sentry.k1) this.f16314u.get(activity);
            io.sentry.k1 k1Var2 = (io.sentry.k1) this.f16315v.get(activity);
            this.f16311r = true;
            if (this.f16308o && k1Var != null && k1Var2 != null && (i0Var = this.f16312s) != null) {
                i0Var.f17345a.add(new androidx.media3.exoplayer.analytics.b(k1Var, k1Var2, 15));
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        WeakHashMap weakHashMap = this.f16315v;
        WeakHashMap weakHashMap2 = this.f16314u;
        WeakHashMap weakHashMap3 = this.f16316w;
        io.sentry.util.a aVarA = this.B.a();
        try {
            io.sentry.android.core.performance.b bVar = (io.sentry.android.core.performance.b) weakHashMap3.remove(activity);
            if (bVar != null) {
                io.sentry.k1 k1Var = bVar.f16699d;
                if (k1Var != null && !k1Var.isFinished()) {
                    bVar.f16699d.i(q7.CANCELLED);
                }
                bVar.f16699d = null;
                io.sentry.k1 k1Var2 = bVar.f16700e;
                if (k1Var2 != null && !k1Var2.isFinished()) {
                    bVar.f16700e.i(q7.CANCELLED);
                }
                bVar.f16700e = null;
            }
            boolean z = this.f16308o;
            WeakHashMap weakHashMap4 = this.z;
            if (z) {
                io.sentry.k1 k1Var3 = this.f16313t;
                q7 q7Var = q7.CANCELLED;
                if (k1Var3 != null && !k1Var3.isFinished()) {
                    k1Var3.i(q7Var);
                }
                io.sentry.k1 k1Var4 = (io.sentry.k1) weakHashMap2.get(activity);
                io.sentry.k1 k1Var5 = (io.sentry.k1) weakHashMap.get(activity);
                q7 q7Var2 = q7.DEADLINE_EXCEEDED;
                if (k1Var4 != null && !k1Var4.isFinished()) {
                    k1Var4.i(q7Var2);
                }
                j(k1Var5, k1Var4);
                Future future = this.f16317y;
                if (future != null) {
                    future.cancel(false);
                    this.f16317y = null;
                }
                if (this.f16308o) {
                    o((io.sentry.m1) weakHashMap4.get(activity), null, null);
                }
                this.f16313t = null;
                weakHashMap2.remove(activity);
                weakHashMap.remove(activity);
            }
            weakHashMap4.remove(activity);
            if (weakHashMap4.isEmpty() && !activity.isChangingConfigurations()) {
                this.f16311r = false;
                this.x = new e6(new Date(0L), 0L);
                weakHashMap3.clear();
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        io.sentry.util.a aVarA = this.B.a();
        try {
            if (!this.f16310q) {
                onActivityPrePaused(activity);
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostCreated(Activity activity, Bundle bundle) {
        io.sentry.android.core.performance.b bVar = (io.sentry.android.core.performance.b) this.f16316w.get(activity);
        if (bVar != null) {
            io.sentry.k1 k1Var = this.f16313t;
            if (k1Var == null) {
                k1Var = (io.sentry.k1) this.z.get(activity);
            }
            if (bVar.f16697b == null || k1Var == null) {
                return;
            }
            io.sentry.k1 k1VarA = io.sentry.android.core.performance.b.a(k1Var, bVar.f16696a.concat(".onCreate"), bVar.f16697b);
            bVar.f16699d = k1VarA;
            k1VarA.d();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostStarted(Activity activity) {
        io.sentry.android.core.performance.b bVar = (io.sentry.android.core.performance.b) this.f16316w.get(activity);
        if (bVar != null) {
            io.sentry.k1 k1Var = this.f16313t;
            if (k1Var == null) {
                k1Var = (io.sentry.k1) this.z.get(activity);
            }
            if (bVar.f16698c != null && k1Var != null) {
                io.sentry.k1 k1VarA = io.sentry.android.core.performance.b.a(k1Var, bVar.f16696a.concat(".onStart"), bVar.f16698c);
                bVar.f16700e = k1VarA;
                k1VarA.d();
            }
            io.sentry.k1 k1Var2 = bVar.f16699d;
            if (k1Var2 == null || bVar.f16700e == null) {
                return;
            }
            z4 z4VarP = k1Var2.p();
            z4 z4VarP2 = bVar.f16700e.p();
            if (z4VarP == null || z4VarP2 == null) {
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            k.f16655a.getClass();
            e6 e6Var = new e6();
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long millis = timeUnit.toMillis(e6Var.d(bVar.f16699d.r()));
            long millis2 = timeUnit.toMillis(e6Var.d(z4VarP));
            long millis3 = timeUnit.toMillis(e6Var.d(bVar.f16700e.r()));
            long millis4 = timeUnit.toMillis(e6Var.d(z4VarP2));
            io.sentry.android.core.performance.c cVar = new io.sentry.android.core.performance.c();
            String strA = bVar.f16699d.a();
            long millis5 = timeUnit.toMillis(bVar.f16699d.r().i());
            io.sentry.android.core.performance.h hVar = cVar.f16701i;
            hVar.f16720i = strA;
            hVar.f16721l = millis5;
            hVar.f16722m = jUptimeMillis - millis;
            hVar.f16723n = jUptimeMillis - millis2;
            String strA2 = bVar.f16700e.a();
            long millis6 = timeUnit.toMillis(bVar.f16700e.r().i());
            io.sentry.android.core.performance.h hVar2 = cVar.f16702l;
            hVar2.f16720i = strA2;
            hVar2.f16721l = millis6;
            hVar2.f16722m = jUptimeMillis - millis3;
            hVar2.f16723n = jUptimeMillis - millis4;
            io.sentry.android.core.performance.g.c().f16713r.add(cVar);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreCreated(Activity activity, Bundle bundle) {
        z4 e6Var;
        io.sentry.android.core.performance.b bVar = new io.sentry.android.core.performance.b(activity.getClass().getName());
        this.f16316w.put(activity, bVar);
        if (this.f16311r) {
            return;
        }
        j4 j4Var = this.f16306m;
        if (j4Var != null) {
            e6Var = j4Var.getOptions().getDateProvider().a();
        } else {
            k.f16655a.getClass();
            e6Var = new e6();
        }
        this.x = e6Var;
        bVar.f16697b = e6Var;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPrePaused(Activity activity) {
        z4 e6Var;
        this.f16311r = true;
        j4 j4Var = this.f16306m;
        if (j4Var != null) {
            e6Var = j4Var.getOptions().getDateProvider().a();
        } else {
            k.f16655a.getClass();
            e6Var = new e6();
        }
        this.x = e6Var;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPreStarted(Activity activity) {
        z4 e6Var;
        io.sentry.android.core.performance.b bVar = (io.sentry.android.core.performance.b) this.f16316w.get(activity);
        if (bVar != null) {
            SentryAndroidOptions sentryAndroidOptions = this.f16307n;
            if (sentryAndroidOptions != null) {
                e6Var = sentryAndroidOptions.getDateProvider().a();
            } else {
                k.f16655a.getClass();
                e6Var = new e6();
            }
            bVar.f16698c = e6Var;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        io.sentry.util.a aVarA = this.B.a();
        try {
            if (!this.f16310q) {
                onActivityPostStarted(activity);
            }
            if (this.f16308o) {
                final io.sentry.k1 k1Var = (io.sentry.k1) this.f16314u.get(activity);
                final io.sentry.k1 k1Var2 = (io.sentry.k1) this.f16315v.get(activity);
                if (activity.getWindow() != null) {
                    final int i10 = 0;
                    io.sentry.android.core.internal.util.i.a(activity, new Runnable(this) { // from class: io.sentry.android.core.f

                        /* JADX INFO: renamed from: l, reason: collision with root package name */
                        public final /* synthetic */ ActivityLifecycleIntegration f16474l;

                        {
                            this.f16474l = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i10) {
                                case 0:
                                    this.f16474l.x(k1Var2, k1Var);
                                    break;
                                default:
                                    this.f16474l.x(k1Var2, k1Var);
                                    break;
                            }
                        }
                    }, this.f16305l);
                } else {
                    final int i11 = 1;
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) { // from class: io.sentry.android.core.f

                        /* JADX INFO: renamed from: l, reason: collision with root package name */
                        public final /* synthetic */ ActivityLifecycleIntegration f16474l;

                        {
                            this.f16474l = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i11) {
                                case 0:
                                    this.f16474l.x(k1Var2, k1Var);
                                    break;
                                default:
                                    this.f16474l.x(k1Var2, k1Var);
                                    break;
                            }
                        }
                    });
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        io.sentry.util.a aVarA = this.B.a();
        try {
            if (!this.f16310q) {
                onActivityPostCreated(activity, null);
                onActivityPreStarted(activity);
            }
            if (this.f16308o) {
                this.A.a(activity);
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    public final void x(io.sentry.k1 k1Var, io.sentry.k1 k1Var2) {
        io.sentry.android.core.performance.g gVarC = io.sentry.android.core.performance.g.c();
        io.sentry.android.core.performance.h hVar = gVarC.f16709n;
        io.sentry.android.core.performance.h hVar2 = gVarC.f16710o;
        if (hVar.d() && hVar.f16723n == 0) {
            hVar.f16723n = SystemClock.uptimeMillis();
        }
        if (hVar2.d() && hVar2.f16723n == 0) {
            hVar2.f16723n = SystemClock.uptimeMillis();
        }
        i();
        io.sentry.util.a aVarA = this.C.a();
        try {
            SentryAndroidOptions sentryAndroidOptions = this.f16307n;
            if (sentryAndroidOptions != null && k1Var2 != null) {
                z4 z4VarA = sentryAndroidOptions.getDateProvider().a();
                k1Var2.n("time_to_initial_display", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(z4VarA.d(k1Var2.r()))), m2.a.MILLISECOND);
                k(k1Var2, z4VarA, null);
            } else if (k1Var2 != null && !k1Var2.isFinished()) {
                k1Var2.d();
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

    public final void y(Activity activity) {
        WeakHashMap weakHashMap;
        WeakHashMap weakHashMap2;
        Boolean boolValueOf;
        b6 b6Var;
        z4 z4Var;
        p7 p7Var;
        io.sentry.m1 m1Var;
        WeakReference weakReference = new WeakReference(activity);
        if (this.f16306m != null) {
            WeakHashMap weakHashMap3 = this.z;
            if (weakHashMap3.containsKey(activity)) {
                return;
            }
            if (!this.f16308o) {
                weakHashMap3.put(activity, h3.f17316a);
                if (this.f16307n.isEnableAutoTraceIdGeneration()) {
                    this.f16306m.s(new o0(11));
                    return;
                }
                return;
            }
            Iterator it = weakHashMap3.entrySet().iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                weakHashMap = this.f16315v;
                weakHashMap2 = this.f16314u;
                if (!zHasNext) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                o((io.sentry.m1) entry.getValue(), (io.sentry.k1) weakHashMap2.get(entry.getKey()), (io.sentry.k1) weakHashMap.get(entry.getKey()));
            }
            String simpleName = activity.getClass().getSimpleName();
            io.sentry.android.core.performance.h hVarB = io.sentry.android.core.performance.g.c().b(this.f16307n);
            androidx.core.provider.e eVar = null;
            if (q0.i() && hVarB.d()) {
                b6 b6Var2 = hVarB.d() ? new b6(hVarB.f16721l * 1000000) : null;
                boolValueOf = Boolean.valueOf(io.sentry.android.core.performance.g.c().f16706i == io.sentry.android.core.performance.f.COLD);
                b6Var = b6Var2;
            } else {
                boolValueOf = null;
                b6Var = null;
            }
            w7 w7Var = new w7();
            long deadlineTimeout = this.f16307n.getDeadlineTimeout();
            w7Var.f18043h = deadlineTimeout <= 0 ? null : Long.valueOf(deadlineTimeout);
            if (this.f16307n.isEnableActivityLifecycleTracingAutoFinish()) {
                w7Var.f18042g = this.f16307n.getIdleTimeout();
                w7Var.f17518a = true;
            }
            w7Var.f18041f = true;
            w7Var.f18044i = new g(this, weakReference, simpleName);
            if (this.f16311r || b6Var == null || boolValueOf == null) {
                z4Var = this.x;
            } else {
                androidx.core.provider.e eVar2 = io.sentry.android.core.performance.g.c().f16716u;
                io.sentry.android.core.performance.g.c().f16716u = null;
                eVar = eVar2;
                z4Var = b6Var;
            }
            w7Var.f17519b = z4Var;
            w7Var.f18040e = eVar != null;
            w7Var.f17521d = "auto.ui.activity";
            io.sentry.m1 m1VarU = this.f16306m.u(new u7(simpleName, io.sentry.protocol.h0.COMPONENT, "ui.load", eVar), w7Var);
            p7 p7Var2 = new p7();
            p7Var2.f17521d = "auto.ui.activity";
            if (this.f16311r || b6Var == null || boolValueOf == null) {
                p7Var = p7Var2;
            } else {
                io.sentry.k1 k1VarL = m1VarU.l(boolValueOf.booleanValue() ? "app.start.cold" : "app.start.warm", boolValueOf.booleanValue() ? "Cold Start" : "Warm Start", b6Var, io.sentry.r1.SENTRY, p7Var2);
                m1VarU = m1VarU;
                p7Var = p7Var2;
                this.f16313t = k1VarL;
                i();
            }
            String strConcat = simpleName.concat(" initial display");
            io.sentry.r1 r1Var = io.sentry.r1.SENTRY;
            z4 z4Var2 = z4Var;
            io.sentry.k1 k1VarL2 = m1VarU.l("ui.load.initial_display", strConcat, z4Var2, r1Var, p7Var);
            weakHashMap2.put(activity, k1VarL2);
            if (!this.f16309p || this.f16312s == null || this.f16307n == null) {
                m1Var = m1VarU;
            } else {
                io.sentry.k1 k1VarL3 = m1VarU.l("ui.load.full_display", simpleName.concat(" full display"), z4Var2, r1Var, p7Var);
                m1Var = m1VarU;
                try {
                    weakHashMap.put(activity, k1VarL3);
                    this.f16317y = this.f16307n.getExecutorService().a(25000L, new androidx.activity.s(this, k1VarL3, k1VarL2));
                } catch (RejectedExecutionException e5) {
                    this.f16307n.getLogger().l(w5.ERROR, "Failed to call the executor. Time to full display span will not be finished automatically. Did you call Sentry.close()?", e5);
                }
            }
            this.f16306m.o(new androidx.media3.exoplayer.analytics.b(this, m1Var, 17));
            weakHashMap3.put(activity, m1Var);
        }
    }
}
