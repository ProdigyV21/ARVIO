package io.sentry.android.core;

import android.content.Context;
import android.os.Build;
import io.sentry.ILogger;
import io.sentry.h7;
import io.sentry.s4;
import io.sentry.t3;
import io.sentry.u3;
import io.sentry.w5;
import io.sentry.y6;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements io.sentry.n1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16807i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ILogger f16808l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16809m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f16810n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f16811o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.util.j f16812p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final r0 f16813q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.q f16816t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public volatile u3 f16817u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f16819w;
    public long x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Date f16820y;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f16814r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicBoolean f16815s = new AtomicBoolean(false);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile u f16818v = null;
    public final io.sentry.util.b z = new io.sentry.util.b();

    public x(Context context, r0 r0Var, io.sentry.android.core.internal.util.q qVar, ILogger iLogger, String str, boolean z, int i10, io.sentry.util.j jVar) {
        Context applicationContext = context.getApplicationContext();
        this.f16807i = applicationContext != null ? applicationContext : context;
        ac.b.V(iLogger, "ILogger is required");
        this.f16808l = iLogger;
        this.f16816t = qVar;
        ac.b.V(r0Var, "The BuildInfoProvider is required.");
        this.f16813q = r0Var;
        this.f16809m = str;
        this.f16810n = z;
        this.f16811o = i10;
        this.f16812p = jVar;
        this.f16820y = k2.c.m();
    }

    public final t3 a(String str, String str2, String str3, boolean z, List list, y6 y6Var) {
        this.f16813q.getClass();
        int i10 = Build.VERSION.SDK_INT;
        if (this.f16818v != null) {
            io.sentry.util.a aVarA = this.z.a();
            try {
                u3 u3Var = this.f16817u;
                if (u3Var == null || !u3Var.f17943i.equals(str2)) {
                    this.f16808l.q(w5.INFO, "Transaction %s (%s) finished, but was not currently being profiled. Skipping", str, str3);
                    aVarA.close();
                    return null;
                }
                this.f16817u = null;
                aVarA.close();
                this.f16808l.q(w5.DEBUG, "Transaction %s (%s) finished.", str, str3);
                androidx.mediarouter.media.l0 l0VarA = this.f16818v.a(list, false);
                this.f16815s.set(false);
                if (l0VarA != null) {
                    long j10 = l0VarA.f4435a - this.f16819w;
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(u3Var);
                    long j11 = l0VarA.f4435a;
                    long j12 = this.f16819w;
                    long j13 = l0VarA.f4436b;
                    long j14 = this.x;
                    if (u3Var.f17947o == null) {
                        u3Var.f17947o = Long.valueOf(j11 - j12);
                        u3Var.f17946n = Long.valueOf(u3Var.f17946n.longValue() - j12);
                        u3Var.f17949q = Long.valueOf(j13 - j14);
                        u3Var.f17948p = Long.valueOf(u3Var.f17948p.longValue() - j14);
                    }
                    Long l10 = y6Var instanceof SentryAndroidOptions ? u0.c(this.f16807i, (SentryAndroidOptions) y6Var).f16779h : null;
                    String string = l10 != null ? Long.toString(l10.longValue()) : "0";
                    String[] strArr = Build.SUPPORTED_ABIS;
                    File file = (File) l0VarA.f4438d;
                    Date date = this.f16820y;
                    String string2 = Long.toString(j10);
                    this.f16813q.getClass();
                    String str4 = (strArr == null || strArr.length <= 0) ? "" : strArr[0];
                    w wVar = new w();
                    this.f16813q.getClass();
                    String str5 = Build.MANUFACTURER;
                    this.f16813q.getClass();
                    String str6 = Build.MODEL;
                    this.f16813q.getClass();
                    return new t3(file, date, arrayList, str, str2, str3, string2, i10, str4, wVar, str5, str6, Build.VERSION.RELEASE, this.f16813q.a(), string, y6Var.getProguardUuid(), y6Var.getRelease(), y6Var.getEnvironment(), (l0VarA.f4437c || z) ? "timeout" : "normal", (Map) l0VarA.f4439e);
                }
            } finally {
            }
        }
        return null;
    }

    @Override // io.sentry.n1
    public final void b(io.sentry.m1 m1Var) {
        if (this.f16815s.get() && this.f16817u == null) {
            io.sentry.util.a aVarA = this.z.a();
            try {
                if (this.f16815s.get() && this.f16817u == null) {
                    this.f16817u = new u3(m1Var, Long.valueOf(this.f16819w), Long.valueOf(this.x));
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

    @Override // io.sentry.n1
    public final t3 c(h7 h7Var, List list, y6 y6Var) {
        return a(h7Var.f17327e, h7Var.f17323a.toString(), h7Var.f17324b.f17421c.f17435i.toString(), false, list, y6Var);
    }

    @Override // io.sentry.n1
    public final void close() {
        x xVar;
        u3 u3Var = this.f16817u;
        if (u3Var != null) {
            xVar = this;
            xVar.a(u3Var.f17945m, u3Var.f17943i, u3Var.f17944l, true, null, s4.b().getOptions());
        } else {
            xVar = this;
        }
        xVar.f16815s.set(false);
        if (xVar.f16818v == null) {
            return;
        }
        u uVar = xVar.f16818v;
        io.sentry.util.a aVarA = uVar.f16769o.a();
        try {
            Future future = uVar.f16759d;
            if (future != null) {
                future.cancel(true);
                uVar.f16759d = null;
            }
            if (uVar.f16768n) {
                uVar.a(null, true);
            }
            aVarA.close();
        } finally {
        }
    }

    @Override // io.sentry.n1
    public final boolean isRunning() {
        return this.f16815s.get();
    }

    @Override // io.sentry.n1
    public final void start() {
        t tVarC;
        this.f16813q.getClass();
        if (this.f16815s.getAndSet(true)) {
            return;
        }
        if (!this.f16814r) {
            this.f16814r = true;
            if (this.f16810n) {
                String str = this.f16809m;
                if (str == null) {
                    this.f16808l.q(w5.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
                } else {
                    int i10 = this.f16811o;
                    if (i10 <= 0) {
                        this.f16808l.q(w5.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(i10));
                    } else {
                        this.f16818v = new u(str, ((int) TimeUnit.SECONDS.toMicros(1L)) / this.f16811o, this.f16816t, this.f16812p, this.f16808l);
                    }
                }
            } else {
                this.f16808l.q(w5.INFO, "Profiling is disabled in options.", new Object[0]);
            }
        }
        if (this.f16818v != null && (tVarC = this.f16818v.c()) != null) {
            this.f16819w = tVarC.f16745a;
            this.x = tVarC.f16746b;
            this.f16820y = (Date) tVarC.f16747c;
            this.f16808l.q(w5.DEBUG, "Profiler started.", new Object[0]);
            return;
        }
        if (this.f16818v != null && this.f16818v.f16768n) {
            this.f16808l.q(w5.WARNING, "A profile is already running. This profile will be ignored.", new Object[0]);
            return;
        }
        io.sentry.util.a aVarA = this.z.a();
        try {
            this.f16817u = null;
            aVarA.close();
            this.f16815s.set(false);
        } finally {
        }
    }
}
