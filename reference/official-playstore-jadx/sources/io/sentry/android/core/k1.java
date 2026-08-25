package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import androidx.appcompat.widget.u4;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import io.sentry.f6;
import io.sentry.g4;
import io.sentry.r2;
import io.sentry.r4;
import io.sentry.s4;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f16662a = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final io.sentry.util.b f16663b = new io.sentry.util.b();

    public static void a(v vVar, Context context, r4 r4Var, SentryAndroidOptions sentryAndroidOptions) {
        boolean zL = io.sentry.util.l.l(sentryAndroidOptions, "timber.log.Timber");
        boolean z = io.sentry.util.l.l(sentryAndroidOptions, "androidx.fragment.app.FragmentManager$FragmentLifecycleCallbacks") && io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.android.fragment.FragmentLifecycleIntegration");
        boolean z5 = zL && io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.android.timber.SentryTimberIntegration");
        boolean zL2 = io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.android.replay.ReplayIntegration");
        boolean zL3 = io.sentry.util.l.l(sentryAndroidOptions, "io.sentry.android.distribution.DistributionIntegration");
        r0 r0Var = new r0(vVar);
        io.sentry.util.l lVar = new io.sentry.util.l(0);
        r0 r0Var2 = new r0();
        u4 u4Var = new u4();
        u4Var.f1775c = new ConcurrentHashMap();
        u4Var.f1776d = new WeakHashMap();
        u4Var.f1778f = new io.sentry.util.b();
        u4Var.f1779g = new io.sentry.util.k(new io.sentry.cache.a(lVar, sentryAndroidOptions.getLogger(), 3));
        u4Var.f1773a = new io.sentry.util.k(new com.google.firebase.installations.d(26));
        u4Var.f1774b = sentryAndroidOptions;
        u4Var.f1777e = r0Var2;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        sentryAndroidOptions.setLogger(vVar);
        sentryAndroidOptions.setFatalLogger(new v(2));
        sentryAndroidOptions.setDefaultScopeType(g4.CURRENT);
        sentryAndroidOptions.setOpenTelemetryMode(f6.OFF);
        sentryAndroidOptions.setDateProvider(new l1());
        sentryAndroidOptions.getLogs().f17517b = new v(4);
        sentryAndroidOptions.getMetrics().f17787b = new v(5);
        sentryAndroidOptions.setFlushTimeoutMillis(4000L);
        sentryAndroidOptions.setFrameMetricsCollector(new io.sentry.android.core.internal.util.q(applicationContext, vVar, r0Var));
        b1.a(applicationContext, r0Var, sentryAndroidOptions);
        sentryAndroidOptions.setCacheDirPath(new File(applicationContext.getCacheDir(), "sentry").getAbsolutePath());
        io.sentry.android.core.anr.e.f16433a.set(true);
        PackageInfo packageInfoG = q0.g(applicationContext, r0Var);
        if (packageInfoG != null) {
            if (sentryAndroidOptions.getRelease() == null) {
                sentryAndroidOptions.setRelease(packageInfoG.packageName + "@" + packageInfoG.versionName + "+" + q0.h(packageInfoG, r0Var));
            }
            String str = packageInfoG.packageName;
            if (str != null && !str.startsWith("android.")) {
                sentryAndroidOptions.addInAppInclude(str);
            }
        }
        if (sentryAndroidOptions.getDistinctId() == null) {
            try {
                sentryAndroidOptions.setDistinctId(z0.a(applicationContext));
            } catch (RuntimeException e5) {
                sentryAndroidOptions.getLogger().l(w5.ERROR, "Could not generate distinct Id.", e5);
            }
        }
        h0 h0Var = h0.f16496o;
        if (h0Var.f16498l == null) {
            io.sentry.util.a aVarA = h0Var.f16497i.a();
            try {
                h0Var.k(sentryAndroidOptions.getLogger());
                aVarA.close();
            } finally {
            }
        }
        sentryAndroidOptions.activate();
        r.b(context, sentryAndroidOptions, r0Var, lVar, u4Var, z, z5, zL2, zL3);
        boolean z10 = z5;
        boolean z11 = z;
        try {
            r4Var.c(sentryAndroidOptions);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Error in the 'OptionsConfiguration.configure' callback.", th);
        }
        io.sentry.android.core.performance.g gVarC = io.sentry.android.core.performance.g.c();
        if (sentryAndroidOptions.isEnablePerformanceV2() && Build.VERSION.SDK_INT >= 24) {
            io.sentry.android.core.performance.h hVar = gVarC.f16709n;
            if (hVar.f16722m == 0) {
                hVar.f(Process.getStartUptimeMillis());
            }
        }
        if (context.getApplicationContext() instanceof Application) {
            gVarC.e((Application) context.getApplicationContext());
        }
        io.sentry.android.core.performance.h hVar2 = gVarC.f16710o;
        if (hVar2.f16722m == 0) {
            hVar2.f(f16662a);
        }
        r.a(sentryAndroidOptions, context, r0Var, lVar, u4Var, zL2);
        b(sentryAndroidOptions, z11, z10);
    }

    public static void b(SentryAndroidOptions sentryAndroidOptions, boolean z, boolean z5) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (io.sentry.s1 s1Var : sentryAndroidOptions.getIntegrations()) {
            if (z && (s1Var instanceof FragmentLifecycleIntegration)) {
                arrayList2.add(s1Var);
            }
            if (z5 && (s1Var instanceof SentryTimberIntegration)) {
                arrayList.add(s1Var);
            }
            if (s1Var instanceof SystemEventsBreadcrumbsIntegration) {
                arrayList3.add(s1Var);
            }
        }
        if (arrayList2.size() > 1) {
            for (int i10 = 0; i10 < arrayList2.size() - 1; i10++) {
                sentryAndroidOptions.getIntegrations().remove((io.sentry.s1) arrayList2.get(i10));
            }
        }
        if (arrayList.size() > 1) {
            for (int i11 = 0; i11 < arrayList.size() - 1; i11++) {
                sentryAndroidOptions.getIntegrations().remove((io.sentry.s1) arrayList.get(i11));
            }
        }
        if (arrayList3.size() > 1) {
            for (int i12 = 0; i12 < arrayList3.size() - 1; i12++) {
                sentryAndroidOptions.getIntegrations().remove((io.sentry.s1) arrayList3.get(i12));
            }
        }
    }

    public static void c(Context context, v vVar, r4 r4Var) {
        try {
            io.sentry.util.a aVarA = f16663b.a();
            try {
                s4.c(new r2(), new g(vVar, context, r4Var));
                io.sentry.c1 c1VarB = s4.b();
                if (q0.i()) {
                    if (c1VarB.getOptions().isEnableAutoSessionTracking()) {
                        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                        c1VarB.s(new j1(atomicBoolean));
                        if (!atomicBoolean.get()) {
                            c1VarB.q();
                        }
                    }
                    c1VarB.getOptions().getReplayController().y();
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
        } catch (IllegalAccessException e5) {
            vVar.l(w5.FATAL, "Fatal error during SentryAndroid.init(...)", e5);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e5);
        } catch (InstantiationException e6) {
            vVar.l(w5.FATAL, "Fatal error during SentryAndroid.init(...)", e6);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e6);
        } catch (NoSuchMethodException e10) {
            vVar.l(w5.FATAL, "Fatal error during SentryAndroid.init(...)", e10);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e10);
        } catch (InvocationTargetException e11) {
            vVar.l(w5.FATAL, "Fatal error during SentryAndroid.init(...)", e11);
            throw new RuntimeException("Failed to initialize Sentry's SDK", e11);
        }
    }
}
