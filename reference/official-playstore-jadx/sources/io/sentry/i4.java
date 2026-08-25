package io.sentry;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class i4 implements c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1 f17347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1 f17348b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a1 f17349c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f17350d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final androidx.appcompat.app.i1 f17351e;

    public i4(a1 a1Var, a1 a1Var2, a1 a1Var3) {
        this.f17351e = new androidx.appcompat.app.i1(a1Var3, a1Var2, a1Var, 17);
        this.f17347a = a1Var;
        this.f17348b = a1Var2;
        this.f17349c = a1Var3;
        y6 options = getOptions();
        ac.b.V(options, "SentryOptions is required.");
        if (options.getDsn() == null || options.getDsn().isEmpty()) {
            throw new IllegalArgumentException("Scopes requires a DSN to be instantiated. Considering using the NoOpScopes if no DSN is available.");
        }
        this.f17350d = options.getCompositePerformanceCollector();
    }

    @Override // io.sentry.c1
    public final c1 A(String str) {
        return new i4(this.f17347a.clone(), this.f17348b.clone(), this.f17349c);
    }

    public final io.sentry.protocol.v B(io.sentry.protocol.i iVar) {
        a1 a1Var = this.f17351e;
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureFeedback' call is a no-op.", new Object[0]);
            return vVar;
        }
        if (iVar.f17590i.isEmpty()) {
            getOptions().getLogger().q(w5.WARNING, "captureFeedback called with empty message.", new Object[0]);
            return vVar;
        }
        try {
            return a1Var.I().g(iVar, a1Var);
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error while capturing feedback: " + iVar.f17590i, th);
            return vVar;
        }
    }

    @Override // io.sentry.c1
    public final void a(boolean z) {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'close' call is a no-op.", new Object[0]);
            return;
        }
        try {
            for (s1 s1Var : getOptions().getIntegrations()) {
                if (s1Var instanceof Closeable) {
                    try {
                        ((Closeable) s1Var).close();
                    } catch (Throwable th) {
                        getOptions().getLogger().q(w5.WARNING, "Failed to close the integration {}.", s1Var, th);
                    }
                }
            }
            for (d0 d0Var : getOptions().getEventProcessors()) {
                if (d0Var instanceof Closeable) {
                    try {
                        ((Closeable) d0Var).close();
                    } catch (Throwable th2) {
                        getOptions().getLogger().q(w5.WARNING, "Failed to close the event processor {}.", d0Var, th2);
                    }
                }
            }
            boolean zIsEnabled = isEnabled();
            androidx.appcompat.app.i1 i1Var = this.f17351e;
            if (zIsEnabled) {
                try {
                    i1Var.X(null).clear();
                } catch (Throwable th3) {
                    getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th3);
                }
            } else {
                getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            }
            g4 g4Var = g4.ISOLATION;
            if (isEnabled()) {
                try {
                    i1Var.X(g4Var).clear();
                } catch (Throwable th4) {
                    getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th4);
                }
            } else {
                getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            }
            getOptions().getBackpressureMonitor().close();
            getOptions().getTransactionProfiler().close();
            getOptions().getContinuousProfiler().a(true);
            getOptions().getCompositePerformanceCollector().close();
            getOptions().getConnectionStatusProvider().close();
            final g1 executorService = getOptions().getExecutorService();
            if (z) {
                try {
                    executorService.submit(new Runnable() { // from class: io.sentry.h4
                        @Override // java.lang.Runnable
                        public final void run() {
                            executorService.b(this.f17317i.getOptions().getShutdownTimeoutMillis());
                        }
                    });
                } catch (RejectedExecutionException e5) {
                    getOptions().getLogger().l(w5.WARNING, "Failed to submit executor service shutdown task during restart. Shutting down synchronously.", e5);
                    executorService.b(getOptions().getShutdownTimeoutMillis());
                }
            } else {
                executorService.b(getOptions().getShutdownTimeoutMillis());
            }
            g4 g4Var2 = g4.CURRENT;
            if (isEnabled()) {
                try {
                    i1Var.X(g4Var2).I().a(z);
                } catch (Throwable th5) {
                    getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th5);
                }
            } else {
                getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            }
            g4 g4Var3 = g4.ISOLATION;
            if (isEnabled()) {
                try {
                    i1Var.X(g4Var3).I().a(z);
                } catch (Throwable th6) {
                    getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th6);
                }
            } else {
                getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            }
            g4 g4Var4 = g4.GLOBAL;
            if (!isEnabled()) {
                getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
                return;
            }
            try {
                i1Var.X(g4Var4).I().a(z);
            } catch (Throwable th7) {
                getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th7);
            }
        } catch (Throwable th8) {
            getOptions().getLogger().l(w5.ERROR, "Error while closing the Scopes.", th8);
        }
    }

    @Override // io.sentry.c1
    public final void b(long j10) {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'flush' call is a no-op.", new Object[0]);
            return;
        }
        try {
            this.f17351e.I().b(j10);
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error in the 'client.flush'.", th);
        }
    }

    @Override // io.sentry.c1
    public final boolean c() {
        return this.f17351e.I().c();
    }

    @Override // io.sentry.c1
    public final void d(io.sentry.protocol.i0 i0Var) {
        if (isEnabled()) {
            this.f17351e.d(i0Var);
        } else {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'setUser' call is a no-op.", new Object[0]);
        }
    }

    @Override // io.sentry.c1
    public final void e(String str, String str2) {
        if (isEnabled()) {
            this.f17351e.e(str, str2);
        } else {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'setTag' call is a no-op.", new Object[0]);
        }
    }

    @Override // io.sentry.c1
    public final io.sentry.transport.p f() {
        return this.f17351e.I().f();
    }

    @Override // io.sentry.c1
    public final void g(String str, String str2) {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'setExtra' call is a no-op.", new Object[0]);
        } else if (str2 == null) {
            getOptions().getLogger().q(w5.WARNING, "setExtra called with null parameter.", new Object[0]);
        } else {
            this.f17351e.g(str, str2);
        }
    }

    @Override // io.sentry.c1
    public final y6 getOptions() {
        return ((a1) this.f17351e.f1059l).getOptions();
    }

    @Override // io.sentry.c1
    public final void h(f fVar, j0 j0Var) {
        if (isEnabled()) {
            this.f17351e.h(fVar, j0Var);
        } else {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'addBreadcrumb' call is a no-op.", new Object[0]);
        }
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v i(fi.iki.elonen.f fVar, j0 j0Var) {
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureEnvelope' call is a no-op.", new Object[0]);
            return vVar;
        }
        try {
            io.sentry.protocol.v vVarI = this.f17351e.I().i(fVar, j0Var);
            return vVarI != null ? vVarI : vVar;
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error while capturing envelope.", th);
            return vVar;
        }
    }

    @Override // io.sentry.c1
    public final boolean isEnabled() {
        return this.f17351e.I().isEnabled();
    }

    @Override // io.sentry.c1
    public final m1 j() {
        if (isEnabled()) {
            return this.f17351e.j();
        }
        getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'getTransaction' call is a no-op.", new Object[0]);
        return null;
    }

    @Override // io.sentry.c1
    public final void k(f fVar) {
        h(fVar, new j0());
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v l(q3 q3Var) {
        ac.b.V(q3Var, "profilingContinuousData is required");
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureTransaction' call is a no-op.", new Object[0]);
            return vVar;
        }
        try {
            return this.f17351e.I().l(q3Var);
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error while capturing profile chunk with id: " + q3Var.f17750m, th);
            return vVar;
        }
    }

    @Override // io.sentry.c1
    public final void m() {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'endSession' call is a no-op.", new Object[0]);
            return;
        }
        androidx.appcompat.app.i1 i1Var = this.f17351e;
        j7 j7VarM = i1Var.m();
        if (j7VarM != null) {
            i1Var.I().h(j7VarM, qb.l.k(new a1.a()));
        }
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v n(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var) {
        return z(e0Var, s7Var, j0Var, null);
    }

    @Override // io.sentry.c1
    public final void o(e4 e4Var) {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            return;
        }
        try {
            e4Var.e(this.f17351e.X(null));
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th);
        }
    }

    @Override // io.sentry.c1
    public final /* synthetic */ boolean p() {
        return false;
    }

    @Override // io.sentry.c1
    public final void q() {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'startSession' call is a no-op.", new Object[0]);
            return;
        }
        androidx.appcompat.app.i1 i1Var = this.f17351e;
        fi.iki.elonen.f fVarQ = i1Var.q();
        if (fVarQ == null) {
            getOptions().getLogger().q(w5.WARNING, "Session could not be started.", new Object[0]);
            return;
        }
        j7 j7Var = (j7) fVarQ.f15416i;
        if (j7Var != null) {
            i1Var.I().h(j7Var, qb.l.k(new a1.a()));
        }
        i1Var.I().h((j7) fVarQ.f15417l, qb.l.k(new a1.a()));
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v r(a7 a7Var, j0 j0Var) {
        a1 a1Var = this.f17351e;
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureReplay' call is a no-op.", new Object[0]);
            return vVar;
        }
        try {
            return a1Var.I().d(a7Var, a1Var, j0Var);
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error while capturing replay", th);
            return vVar;
        }
    }

    @Override // io.sentry.c1
    public final void s(e4 e4Var) {
        o(e4Var);
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v t(j5 j5Var, j0 j0Var) {
        a1 a1Var = this.f17351e;
        io.sentry.protocol.v vVarK = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureEvent' call is a no-op.", new Object[0]);
            return vVarK;
        }
        try {
            a1Var.K(j5Var);
            vVarK = a1Var.I().k(j5Var, a1Var, j0Var);
            a1Var.O(vVarK);
            return vVarK;
        } catch (Throwable th) {
            getOptions().getLogger().l(w5.ERROR, "Error while capturing event with id: " + j5Var.f17984i, th);
            return vVarK;
        }
    }

    @Override // io.sentry.c1
    public final m1 u(u7 u7Var, w7 w7Var) {
        Double dValueOf;
        u7Var.f17443s = (String) w7Var.f17521d;
        boolean zIsEnabled = isEnabled();
        m1 m1VarA = h3.f17316a;
        if (!zIsEnabled) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
        } else if (io.sentry.util.r.a(u7Var.f17443s, getOptions().getIgnoredSpanOrigins())) {
            getOptions().getLogger().q(w5.DEBUG, "Returning no-op for span origin %s as the SDK has been configured to ignore it", u7Var.f17443s);
        } else if (!getOptions().getInstrumenter().equals(u7Var.f17446v)) {
            getOptions().getLogger().q(w5.DEBUG, "Returning no-op for instrumenter %s as the SDK has been configured to use instrumenter %s", u7Var.f17446v, getOptions().getInstrumenter());
        } else if (getOptions().isTracingEnabled()) {
            d dVar = u7Var.f17447w;
            if (dVar == null || (dValueOf = dVar.f17207d) == null) {
                Double d4 = ((d) this.f17351e.F().f1061n).f17207d;
                dValueOf = Double.valueOf(d4 == null ? 0.0d : d4.doubleValue());
            }
            androidx.core.provider.e eVarA = getOptions().getInternalTracesSampler().a(new androidx.appcompat.app.i1(u7Var, dValueOf));
            Boolean bool = (Boolean) eVarA.f2140l;
            u7Var.a(eVarA);
            l1 spanFactory = getOptions().getSpanFactory();
            if (bool.booleanValue() && getOptions().isContinuousProfilingEnabled()) {
                s3 profileLifecycle = getOptions().getProfileLifecycle();
                s3 s3Var = s3.TRACE;
                if (profileLifecycle == s3Var && u7Var.f17448y.equals(io.sentry.protocol.v.f17707l)) {
                    getOptions().getContinuousProfiler().b(s3Var, getOptions().getInternalTracesSampler());
                }
            }
            m1VarA = spanFactory.a(u7Var, this, w7Var, this.f17350d);
            if (bool.booleanValue() && ((Boolean) eVarA.f2143o).booleanValue()) {
                n1 transactionProfiler = getOptions().getTransactionProfiler();
                if (!transactionProfiler.isRunning()) {
                    transactionProfiler.start();
                    transactionProfiler.b(m1VarA);
                } else if (w7Var.f18040e) {
                    transactionProfiler.b(m1VarA);
                }
            }
        } else {
            getOptions().getLogger().q(w5.INFO, "Tracing is disabled and this 'startTransaction' returns a no-op.", new Object[0]);
        }
        if (d4.ON == ((d4) w7Var.f17520c)) {
            m1VarA.makeCurrent();
        }
        return m1VarA;
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v v(io.sentry.protocol.i iVar) {
        return B(iVar);
    }

    @Override // io.sentry.c1
    public final a1 w() {
        return this.f17347a;
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v x(Throwable th) {
        return y(th, new j0());
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v y(Throwable th, j0 j0Var) {
        io.sentry.protocol.v vVarK = io.sentry.protocol.v.f17707l;
        boolean zIsEnabled = isEnabled();
        a1 a1Var = this.f17351e;
        if (zIsEnabled) {
            try {
                j5 j5Var = new j5(th);
                a1Var.K(j5Var);
                vVarK = a1Var.I().k(j5Var, a1Var, j0Var);
            } catch (Throwable th2) {
                getOptions().getLogger().l(w5.ERROR, "Error while capturing exception: " + th.getMessage(), th2);
            }
        } else {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureException' call is a no-op.", new Object[0]);
        }
        a1Var.O(vVarK);
        return vVarK;
    }

    @Override // io.sentry.c1
    public final io.sentry.protocol.v z(io.sentry.protocol.e0 e0Var, s7 s7Var, j0 j0Var, t3 t3Var) {
        io.sentry.protocol.e0 e0Var2;
        a1 a1Var = this.f17351e;
        ArrayList arrayList = e0Var.C;
        io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'captureTransaction' call is a no-op.", new Object[0]);
            return vVar;
        }
        if (e0Var.B == null) {
            getOptions().getLogger().q(w5.WARNING, "Transaction: %s is not finished and this 'captureTransaction' call is a no-op.", e0Var.f17984i);
            return vVar;
        }
        Boolean bool = Boolean.TRUE;
        l7 l7VarI = e0Var.f17985l.i();
        androidx.core.provider.e eVar = l7VarI == null ? null : l7VarI.f17438n;
        if (bool.equals(Boolean.valueOf(eVar == null ? false : ((Boolean) eVar.f2140l).booleanValue()))) {
            try {
                e0Var2 = e0Var;
            } catch (Throwable th) {
                th = th;
                e0Var2 = e0Var;
            }
            try {
                return a1Var.I().e(e0Var2, s7Var, a1Var, j0Var, t3Var);
            } catch (Throwable th2) {
                th = th2;
                Throwable th3 = th;
                getOptions().getLogger().l(w5.ERROR, "Error while capturing transaction with id: " + e0Var2.f17984i, th3);
                return vVar;
            }
        }
        getOptions().getLogger().q(w5.DEBUG, "Transaction %s was dropped due to sampling decision.", e0Var.f17984i);
        if (getOptions().getBackpressureMonitor().a() > 0) {
            io.sentry.clientreport.f clientReportRecorder = getOptions().getClientReportRecorder();
            io.sentry.clientreport.d dVar = io.sentry.clientreport.d.BACKPRESSURE;
            clientReportRecorder.c(dVar, m.Transaction);
            getOptions().getClientReportRecorder().o(dVar, m.Span, arrayList.size() + 1);
            return vVar;
        }
        io.sentry.clientreport.f clientReportRecorder2 = getOptions().getClientReportRecorder();
        io.sentry.clientreport.d dVar2 = io.sentry.clientreport.d.SAMPLE_RATE;
        clientReportRecorder2.c(dVar2, m.Transaction);
        getOptions().getClientReportRecorder().o(dVar2, m.Span, arrayList.size() + 1);
        return vVar;
    }

    @Override // io.sentry.c1
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final t0 m7021clone() {
        if (!isEnabled()) {
            getOptions().getLogger().q(w5.WARNING, "Disabled Scopes cloned.", new Object[0]);
        }
        return new n0((i4) A("scopes clone"));
    }
}
