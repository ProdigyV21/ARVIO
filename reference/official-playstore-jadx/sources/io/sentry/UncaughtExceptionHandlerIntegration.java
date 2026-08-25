package io.sentry;

import io.sentry.exception.ExceptionMechanismException;
import java.io.Closeable;
import java.lang.Thread;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class UncaughtExceptionHandlerIntegration implements s1, Thread.UncaughtExceptionHandler, Closeable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final io.sentry.util.b f16283o = new io.sentry.util.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Thread.UncaughtExceptionHandler f16284i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public j4 f16285l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y6 f16286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f16287n = false;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = f16283o.a();
        try {
            if (this == Thread.getDefaultUncaughtExceptionHandler()) {
                Thread.setDefaultUncaughtExceptionHandler(this.f16284i);
                y6 y6Var = this.f16286m;
                if (y6Var != null) {
                    y6Var.getLogger().q(w5.DEBUG, "UncaughtExceptionHandlerIntegration removed.", new Object[0]);
                }
            } else {
                i(Thread.getDefaultUncaughtExceptionHandler(), new HashSet());
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

    public final void i(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, HashSet hashSet) {
        if (uncaughtExceptionHandler == null) {
            y6 y6Var = this.f16286m;
            if (y6Var != null) {
                y6Var.getLogger().q(w5.DEBUG, "Found no UncaughtExceptionHandler to remove.", new Object[0]);
                return;
            }
            return;
        }
        if (!hashSet.add(uncaughtExceptionHandler)) {
            y6 y6Var2 = this.f16286m;
            if (y6Var2 != null) {
                y6Var2.getLogger().q(w5.WARNING, "Cycle detected in UncaughtExceptionHandler chain while removing handler.", new Object[0]);
                return;
            }
            return;
        }
        if (uncaughtExceptionHandler instanceof UncaughtExceptionHandlerIntegration) {
            UncaughtExceptionHandlerIntegration uncaughtExceptionHandlerIntegration = (UncaughtExceptionHandlerIntegration) uncaughtExceptionHandler;
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandlerIntegration.f16284i;
            if (this != uncaughtExceptionHandler2) {
                i(uncaughtExceptionHandler2, hashSet);
                return;
            }
            uncaughtExceptionHandlerIntegration.f16284i = this.f16284i;
            y6 y6Var3 = this.f16286m;
            if (y6Var3 != null) {
                y6Var3.getLogger().q(w5.DEBUG, "UncaughtExceptionHandlerIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        UncaughtExceptionHandlerIntegration uncaughtExceptionHandlerIntegration;
        j4 j4Var;
        j4 j4Var2 = j4.f17401a;
        if (this.f16287n) {
            y6Var.getLogger().q(w5.ERROR, "Attempt to register a UncaughtExceptionHandlerIntegration twice.", new Object[0]);
            return;
        }
        this.f16287n = true;
        this.f16285l = j4Var2;
        this.f16286m = y6Var;
        ILogger logger = y6Var.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "UncaughtExceptionHandlerIntegration enabled: %s", Boolean.valueOf(this.f16286m.isEnableUncaughtExceptionHandler()));
        if (this.f16286m.isEnableUncaughtExceptionHandler()) {
            io.sentry.util.a aVarA = f16283o.a();
            try {
                Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (defaultUncaughtExceptionHandler != null) {
                    this.f16286m.getLogger().q(w5Var, "default UncaughtExceptionHandler class='" + defaultUncaughtExceptionHandler.getClass().getName() + "'", new Object[0]);
                    if (!(defaultUncaughtExceptionHandler instanceof UncaughtExceptionHandlerIntegration) || (j4Var = (uncaughtExceptionHandlerIntegration = (UncaughtExceptionHandlerIntegration) defaultUncaughtExceptionHandler).f16285l) == null) {
                        this.f16284i = defaultUncaughtExceptionHandler;
                    } else {
                        d1 d1Var = s4.f17849a;
                        j4Var.getClass();
                        this.f16284i = uncaughtExceptionHandlerIntegration.f16284i;
                    }
                }
                Thread.setDefaultUncaughtExceptionHandler(this);
                aVarA.close();
                this.f16286m.getLogger().q(w5Var, "UncaughtExceptionHandlerIntegration installed.", new Object[0]);
                xc.d.H("UncaughtExceptionHandler");
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

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        io.sentry.protocol.v vVar;
        y6 y6Var = this.f16286m;
        if (y6Var == null || this.f16285l == null) {
            return;
        }
        y6Var.getLogger().q(w5.INFO, "Uncaught exception received.", new Object[0]);
        try {
            y7 y7Var = new y7(this.f16286m.getFlushTimeoutMillis(), this.f16286m.getLogger());
            io.sentry.protocol.m mVar = new io.sentry.protocol.m();
            mVar.f17641n = Boolean.FALSE;
            mVar.f17638i = "UncaughtExceptionHandler";
            j5 j5Var = new j5(new ExceptionMechanismException(mVar, th, thread, false));
            j5Var.E = w5.FATAL;
            if (this.f16285l.j() == null && (vVar = j5Var.f17984i) != null) {
                y7Var.d(vVar);
            }
            j0 j0VarK = qb.l.k(y7Var);
            boolean zEquals = this.f16285l.t(j5Var, j0VarK).equals(io.sentry.protocol.v.f17707l);
            io.sentry.hints.h hVar = (io.sentry.hints.h) j0VarK.c(io.sentry.hints.h.class, "sentry:eventDropReason");
            if ((!zEquals || io.sentry.hints.h.MULTITHREADED_DEDUPLICATION.equals(hVar)) && !y7Var.g()) {
                this.f16286m.getLogger().q(w5.WARNING, "Timed out waiting to flush event to disk before crashing. Event: %s", j5Var.f17984i);
            }
        } catch (Throwable th2) {
            this.f16286m.getLogger().l(w5.ERROR, "Error sending uncaught exception to Sentry.", th2);
        }
        if (this.f16284i != null) {
            this.f16286m.getLogger().q(w5.INFO, "Invoking inner uncaught exception handler.", new Object[0]);
            this.f16284i.uncaughtException(thread, th);
        } else if (this.f16286m.isPrintUncaughtStackTrace()) {
            th.printStackTrace();
        }
    }
}
