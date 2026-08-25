package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.j2;
import io.sentry.n5;
import io.sentry.t4;
import io.sentry.t7;
import io.sentry.w5;
import io.sentry.y6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes5.dex */
public final class SentryPerformanceProvider extends v0 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final long f16367o = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f16368p = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Application f16369l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final v f16370m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r0 f16371n;

    public SentryPerformanceProvider() {
        new io.sentry.util.b();
        v vVar = new v(3);
        this.f16370m = vVar;
        this.f16371n = new r0(vVar);
    }

    public final void a(Context context, t4 t4Var, io.sentry.android.core.performance.g gVar) {
        boolean z = t4Var.f17894s;
        v vVar = this.f16370m;
        if (!z) {
            vVar.q(w5.DEBUG, "App start profiling was not sampled. It will not start.", new Object[0]);
            return;
        }
        i iVar = new i(this.f16371n, new io.sentry.android.core.internal.util.q(context.getApplicationContext(), vVar, this.f16371n), vVar, t4Var.f17890o, t4Var.f17893r, new a3.b(new n5(), 26));
        gVar.f16714s = null;
        gVar.f16715t = iVar;
        vVar.q(w5.DEBUG, "App start continuous profiling started.", new Object[0]);
        y6 y6VarEmpty = y6.empty();
        y6VarEmpty.setProfileSessionSampleRate(Double.valueOf(t4Var.f17894s ? 1.0d : 0.0d));
        iVar.b(t4Var.f17897v, new t7(y6VarEmpty));
    }

    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        if (SentryPerformanceProvider.class.getName().equals(providerInfo.authority)) {
            throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
        }
        super.attachInfo(context, providerInfo);
    }

    public final void b(Context context, t4 t4Var, io.sentry.android.core.performance.g gVar) {
        boolean z = t4Var.f17888m;
        androidx.core.provider.e eVar = new androidx.core.provider.e(Boolean.valueOf(z), t4Var.f17889n, null, Boolean.valueOf(t4Var.f17886i), t4Var.f17887l);
        gVar.f16716u = eVar;
        boolean zBooleanValue = ((Boolean) eVar.f2143o).booleanValue();
        v vVar = this.f16370m;
        if (!zBooleanValue || !z) {
            vVar.q(w5.DEBUG, "App start profiling was not sampled. It will not start.", new Object[0]);
            return;
        }
        n5 n5Var = new n5();
        x xVar = new x(context, this.f16371n, new io.sentry.android.core.internal.util.q(context, vVar, this.f16371n), vVar, t4Var.f17890o, t4Var.f17891p, t4Var.f17893r, new a3.b(n5Var, 26));
        gVar.f16715t = null;
        gVar.f16714s = xVar;
        vVar.q(w5.DEBUG, "App start profiling started.", new Object[0]);
        xVar.start();
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        io.sentry.android.core.performance.g gVarC = io.sentry.android.core.performance.g.c();
        Context context = getContext();
        gVarC.f16710o.f(f16367o);
        this.f16371n.getClass();
        if (Build.VERSION.SDK_INT >= 24) {
            gVarC.f16709n.f(Process.getStartUptimeMillis());
        }
        if (context instanceof Application) {
            this.f16369l = (Application) context;
        }
        Application application = this.f16369l;
        if (application != null) {
            gVarC.e(application);
        }
        Context context2 = getContext();
        v vVar = this.f16370m;
        if (context2 == null) {
            vVar.q(w5.FATAL, "App. Context from ContentProvider is null", new Object[0]);
            return true;
        }
        File file = new File(new File(context2.getCacheDir(), "sentry"), "app_start_profiling_config");
        if (!file.exists() || !file.canRead()) {
            return true;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                t4 t4Var = (t4) new j2(y6.empty()).e(bufferedReader, t4.class);
                if (t4Var == null) {
                    vVar.q(w5.WARNING, "Unable to deserialize the SentryAppStartProfilingOptions. App start profiling will not start.", new Object[0]);
                } else if (t4Var.f17892q && t4Var.f17896u) {
                    a(context2, t4Var, gVarC);
                } else if (!t4Var.f17891p) {
                    vVar.q(w5.INFO, "Profiling is not enabled. App start profiling will not start.", new Object[0]);
                } else if (t4Var.f17895t) {
                    b(context2, t4Var, gVarC);
                }
                bufferedReader.close();
                return true;
            } finally {
            }
        } catch (FileNotFoundException e5) {
            vVar.l(w5.ERROR, "App start profiling config file not found. ", e5);
            return true;
        } catch (Throwable th) {
            vVar.l(w5.ERROR, "Error reading app start profiling config file. ", th);
            return true;
        }
    }

    @Override // android.content.ContentProvider
    public final void shutdown() {
        io.sentry.util.a aVarA = io.sentry.android.core.performance.g.B.a();
        try {
            x xVar = io.sentry.android.core.performance.g.c().f16714s;
            if (xVar != null) {
                xVar.close();
            }
            i iVar = io.sentry.android.core.performance.g.c().f16715t;
            if (iVar != null) {
                iVar.a(true);
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
