package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.e6;
import io.sentry.o0;
import io.sentry.p3;
import io.sentry.s3;
import io.sentry.s4;
import io.sentry.t7;
import io.sentry.w5;
import io.sentry.y2;
import io.sentry.y6;
import io.sentry.z4;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements io.sentry.p0, io.sentry.transport.o {
    public z4 A;
    public volatile boolean B;
    public boolean C;
    public boolean D;
    public int E;
    public final io.sentry.util.b F;
    public final io.sentry.util.b G;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ILogger f16504i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f16505l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f16506m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.j f16507n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final r0 f16508o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.q f16510q;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public io.sentry.c1 f16513t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Future f16514u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public io.sentry.l f16515v;
    public io.sentry.protocol.v x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public io.sentry.protocol.v f16517y;
    public final AtomicBoolean z;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16509p = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public u f16511r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f16512s = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f16516w = new ArrayList();

    public i(r0 r0Var, io.sentry.android.core.internal.util.q qVar, ILogger iLogger, String str, int i10, io.sentry.util.j jVar) {
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        this.x = vVar;
        this.f16517y = vVar;
        this.z = new AtomicBoolean(false);
        this.A = new e6();
        this.B = true;
        this.C = false;
        this.D = false;
        this.E = 0;
        this.F = new io.sentry.util.b();
        this.G = new io.sentry.util.b();
        this.f16504i = iLogger;
        this.f16510q = qVar;
        this.f16508o = r0Var;
        this.f16505l = str;
        this.f16506m = i10;
        this.f16507n = jVar;
    }

    @Override // io.sentry.p0
    public final void a(boolean z) {
        io.sentry.util.a aVarA = this.F.a();
        try {
            this.E = 0;
            this.C = true;
            if (z) {
                h(false);
                this.z.set(true);
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

    @Override // io.sentry.p0
    public final void b(s3 s3Var, t7 t7Var) {
        io.sentry.util.a aVarA = this.F.a();
        try {
            if (this.B) {
                double dC = io.sentry.util.q.a().c();
                Double profileSessionSampleRate = t7Var.f17900a.getProfileSessionSampleRate();
                this.D = profileSessionSampleRate != null && profileSessionSampleRate.doubleValue() >= dC;
                this.B = false;
            }
            if (!this.D) {
                this.f16504i.q(w5.DEBUG, "Profiler was not started due to sampling decision.", new Object[0]);
                aVarA.close();
                return;
            }
            int i10 = h.f16495a[s3Var.ordinal()];
            if (i10 == 1) {
                if (this.E < 0) {
                    this.E = 0;
                }
                this.E++;
            } else if (i10 == 2 && this.f16512s) {
                this.f16504i.q(w5.DEBUG, "Profiler is already running.", new Object[0]);
                aVarA.close();
                return;
            }
            if (!this.f16512s) {
                this.f16504i.q(w5.DEBUG, "Started Profiler.", new Object[0]);
                g();
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

    @Override // io.sentry.p0
    public final void c() {
        this.B = true;
    }

    @Override // io.sentry.p0
    public final void d(s3 s3Var) {
        io.sentry.util.a aVarA = this.F.a();
        try {
            int i10 = h.f16495a[s3Var.ordinal()];
            if (i10 == 1) {
                int i11 = this.E - 1;
                this.E = i11;
                if (i11 > 0) {
                    aVarA.close();
                    return;
                } else {
                    if (i11 < 0) {
                        this.E = 0;
                    }
                    this.C = true;
                }
            } else if (i10 == 2) {
                this.C = true;
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

    @Override // io.sentry.p0
    public final io.sentry.protocol.v e() {
        return this.x;
    }

    public final void f() {
        io.sentry.c1 c1Var = this.f16513t;
        if ((c1Var == null || c1Var == y2.f18061b) && s4.b() != y2.f18061b) {
            this.f16513t = s4.b();
            this.f16515v = s4.b().getOptions().getCompositePerformanceCollector();
            io.sentry.transport.p pVarF = this.f16513t.f();
            if (pVarF != null) {
                pVarF.f17934n.add(this);
            }
        }
    }

    public final void g() {
        f();
        this.f16508o.getClass();
        boolean z = this.f16509p;
        ILogger iLogger = this.f16504i;
        if (!z) {
            this.f16509p = true;
            String str = this.f16505l;
            if (str == null) {
                iLogger.q(w5.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
            } else {
                int i10 = this.f16506m;
                if (i10 <= 0) {
                    iLogger.q(w5.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(i10));
                } else {
                    this.f16511r = new u(str, ((int) TimeUnit.SECONDS.toMicros(1L)) / i10, this.f16510q, null, this.f16504i);
                }
            }
        }
        if (this.f16511r == null) {
            return;
        }
        io.sentry.c1 c1Var = this.f16513t;
        if (c1Var != null) {
            io.sentry.transport.p pVarF = c1Var.f();
            if (pVarF != null && (pVarF.j(io.sentry.m.All) || pVarF.j(io.sentry.m.ProfileChunkUi))) {
                iLogger.q(w5.WARNING, "SDK is rate limited. Stopping profiler.", new Object[0]);
                h(false);
                return;
            } else {
                if (this.f16513t.getOptions().getConnectionStatusProvider().M() == o0.a.DISCONNECTED) {
                    iLogger.q(w5.WARNING, "Device is offline. Stopping profiler.", new Object[0]);
                    h(false);
                    return;
                }
                this.A = this.f16513t.getOptions().getDateProvider().a();
            }
        } else {
            this.A = new e6();
        }
        if (this.f16511r.c() == null) {
            return;
        }
        this.f16512s = true;
        io.sentry.protocol.v vVar = this.x;
        io.sentry.protocol.v vVar2 = io.sentry.protocol.v.f17707l;
        if (vVar.equals(vVar2)) {
            this.x = new io.sentry.protocol.v();
        }
        if (this.f16517y.equals(vVar2)) {
            this.f16517y = new io.sentry.protocol.v();
        }
        io.sentry.l lVar = this.f16515v;
        if (lVar != null) {
            lVar.f(this.f16517y.toString());
        }
        try {
            this.f16514u = ((io.sentry.g1) this.f16507n.g()).a(60000L, new androidx.activity.n(this, 15));
        } catch (RejectedExecutionException e5) {
            iLogger.l(w5.ERROR, "Failed to schedule profiling chunk finish. Did you call Sentry.close()?", e5);
            this.C = true;
        }
    }

    public final void h(boolean z) {
        f();
        io.sentry.util.a aVarA = this.F.a();
        try {
            Future future = this.f16514u;
            if (future != null) {
                future.cancel(true);
            }
            if (this.f16511r != null && this.f16512s) {
                this.f16508o.getClass();
                io.sentry.l lVar = this.f16515v;
                androidx.mediarouter.media.l0 l0VarA = this.f16511r.a(lVar != null ? lVar.b(this.f16517y.toString()) : null, false);
                ILogger iLogger = this.f16504i;
                if (l0VarA == null) {
                    iLogger.q(w5.ERROR, "An error occurred while collecting a profile chunk, and it won't be sent.", new Object[0]);
                } else {
                    io.sentry.util.a aVarA2 = this.G.a();
                    try {
                        this.f16516w.add(new p3(this.x, this.f16517y, (Map) l0VarA.f4439e, (File) l0VarA.f4438d, this.A));
                        aVarA2.close();
                    } finally {
                    }
                }
                this.f16512s = false;
                this.f16517y = io.sentry.protocol.v.f17707l;
                io.sentry.c1 c1Var = this.f16513t;
                if (c1Var != null) {
                    y6 options = c1Var.getOptions();
                    try {
                        options.getExecutorService().submit(new i1(this, options, c1Var, 2));
                    } catch (Throwable th) {
                        options.getLogger().l(w5.DEBUG, "Failed to send profile chunks.", th);
                    }
                }
                if (!z || this.C) {
                    this.x = io.sentry.protocol.v.f17707l;
                    iLogger.q(w5.DEBUG, "Profile chunk finished.", new Object[0]);
                } else {
                    iLogger.q(w5.DEBUG, "Profile chunk finished. Starting a new one.", new Object[0]);
                    g();
                }
                aVarA.close();
                return;
            }
            io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
            this.x = vVar;
            this.f16517y = vVar;
            aVarA.close();
        } finally {
        }
    }

    @Override // io.sentry.transport.o
    public final void k(io.sentry.transport.p pVar) {
        if (pVar.j(io.sentry.m.All) || pVar.j(io.sentry.m.ProfileChunkUi)) {
            this.f16504i.q(w5.WARNING, "SDK is rate limited. Stopping profiler.", new Object[0]);
            h(false);
        }
    }
}
