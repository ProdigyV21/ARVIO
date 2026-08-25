package io.sentry.android.core.performance;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ApplicationStartInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.common.util.concurrent.w0;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.r0;
import io.sentry.android.core.x;
import io.sentry.u2;
import io.sentry.util.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends a {
    public static volatile g A;
    public static long z = SystemClock.uptimeMillis();
    public static final io.sentry.util.b B = new io.sentry.util.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f16706i = f.UNKNOWN;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k f16707l = new k(new a1.a());

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile long f16708m = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public x f16714s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public io.sentry.android.core.i f16715t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public androidx.core.provider.e f16716u = null;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f16717v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f16718w = true;
    public final AtomicInteger x = new AtomicInteger();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final AtomicBoolean f16719y = new AtomicBoolean(false);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final h f16709n = new h();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h f16710o = new h();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h f16711p = new h();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final HashMap f16712q = new HashMap();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f16713r = new ArrayList();

    public static void a(g gVar) {
        if (gVar.x.get() == 0) {
            gVar.f16707l.c(Boolean.FALSE);
            x xVar = gVar.f16714s;
            if (xVar != null && xVar.f16815s.get()) {
                gVar.f16714s.close();
                gVar.f16714s = null;
            }
            io.sentry.android.core.i iVar = gVar.f16715t;
            if (iVar == null || !iVar.f16512s) {
                return;
            }
            iVar.a(true);
            gVar.f16715t = null;
        }
    }

    public static g c() {
        if (A == null) {
            io.sentry.util.a aVarA = B.a();
            try {
                if (A == null) {
                    A = new g();
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
        return A;
    }

    public final h b(SentryAndroidOptions sentryAndroidOptions) {
        if (this.f16706i != f.UNKNOWN && ((Boolean) this.f16707l.a()).booleanValue()) {
            if (sentryAndroidOptions.isEnablePerformanceV2()) {
                h hVar = this.f16709n;
                if (hVar.d() && hVar.a() <= TimeUnit.MINUTES.toMillis(1L)) {
                    return hVar;
                }
            }
            h hVar2 = this.f16710o;
            if (hVar2.d() && hVar2.a() <= TimeUnit.MINUTES.toMillis(1L)) {
                return hVar2;
            }
        }
        return new h();
    }

    public final synchronized void d() {
        if (!this.f16719y.getAndSet(true)) {
            g gVarC = c();
            h hVar = gVarC.f16710o;
            hVar.getClass();
            hVar.f16723n = SystemClock.uptimeMillis();
            h hVar2 = gVarC.f16709n;
            hVar2.getClass();
            hVar2.f16723n = SystemClock.uptimeMillis();
        }
    }

    public final void e(Application application) {
        ActivityManager activityManager;
        if (this.f16717v) {
            return;
        }
        this.f16717v = true;
        this.f16707l.b();
        application.registerActivityLifecycleCallbacks(A);
        if (Build.VERSION.SDK_INT >= 35 && (activityManager = (ActivityManager) application.getSystemService("activity")) != null) {
            List historicalProcessStartReasons = activityManager.getHistoricalProcessStartReasons(1);
            if (!historicalProcessStartReasons.isEmpty()) {
                ApplicationStartInfo applicationStartInfo = (ApplicationStartInfo) historicalProcessStartReasons.get(0);
                if (applicationStartInfo.getStartupState() == 0) {
                    if (applicationStartInfo.getStartType() == 1) {
                        this.f16706i = f.COLD;
                    } else {
                        this.f16706i = f.WARM;
                    }
                }
            }
        }
        f fVar = this.f16706i;
        f fVar2 = f.UNKNOWN;
        if (fVar == fVar2) {
            Looper.getMainLooper().getQueue().addIdleHandler(new e(this));
        } else if (fVar == fVar2) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new w0(24, this, false, handler));
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        r0.f16734b.b(activity);
        if (this.x.incrementAndGet() == 1 && !this.f16719y.get()) {
            long jUptimeMillis2 = SystemClock.uptimeMillis() - this.f16709n.f16722m;
            if (!((Boolean) this.f16707l.a()).booleanValue() || jUptimeMillis2 > TimeUnit.MINUTES.toMillis(1L)) {
                this.f16706i = f.WARM;
                this.f16718w = true;
                h hVar = this.f16709n;
                hVar.f16720i = null;
                hVar.f16722m = 0L;
                hVar.f16723n = 0L;
                hVar.f16721l = 0L;
                hVar.f(jUptimeMillis);
                z = jUptimeMillis;
                this.f16712q.clear();
                h hVar2 = this.f16711p;
                hVar2.f16720i = null;
                hVar2.f16722m = 0L;
                hVar2.f16723n = 0L;
                hVar2.f16721l = 0L;
            } else if (this.f16706i == f.UNKNOWN) {
                if (bundle != null) {
                    this.f16706i = f.WARM;
                } else if (this.f16708m == -1 || jUptimeMillis <= this.f16708m) {
                    this.f16706i = f.COLD;
                } else {
                    this.f16706i = f.WARM;
                }
            }
        }
        this.f16707l.c(Boolean.TRUE);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        r0 r0Var = r0.f16734b;
        WeakReference weakReference = (WeakReference) r0Var.f16735a;
        if (weakReference == null || weakReference.get() == activity) {
            r0Var.f16735a = null;
        }
        if (this.x.decrementAndGet() != 0 || activity.isChangingConfigurations()) {
            return;
        }
        this.f16706i = f.WARM;
        this.f16707l.c(Boolean.TRUE);
        this.f16718w = true;
        this.f16719y.set(false);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        r0 r0Var = r0.f16734b;
        WeakReference weakReference = (WeakReference) r0Var.f16735a;
        if (weakReference == null || weakReference.get() == activity) {
            r0Var.f16735a = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        r0.f16734b.b(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        r0.f16734b.b(activity);
        if (this.f16719y.get()) {
            return;
        }
        if (activity.getWindow() != null) {
            final int i10 = 0;
            io.sentry.android.core.internal.util.i.a(activity, new Runnable(this) { // from class: io.sentry.android.core.performance.d

                /* JADX INFO: renamed from: l, reason: collision with root package name */
                public final /* synthetic */ g f16704l;

                {
                    this.f16704l = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i10) {
                        case 0:
                            this.f16704l.d();
                            break;
                        default:
                            this.f16704l.d();
                            break;
                    }
                }
            }, new r0(u2.f17942i));
        } else {
            final int i11 = 1;
            new Handler(Looper.getMainLooper()).post(new Runnable(this) { // from class: io.sentry.android.core.performance.d

                /* JADX INFO: renamed from: l, reason: collision with root package name */
                public final /* synthetic */ g f16704l;

                {
                    this.f16704l = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    switch (i11) {
                        case 0:
                            this.f16704l.d();
                            break;
                        default:
                            this.f16704l.d();
                            break;
                    }
                }
            });
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        r0 r0Var = r0.f16734b;
        WeakReference weakReference = (WeakReference) r0Var.f16735a;
        if (weakReference == null || weakReference.get() == activity) {
            r0Var.f16735a = null;
        }
    }
}
