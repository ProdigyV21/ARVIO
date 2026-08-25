package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.j4;
import io.sentry.m3;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class EnvelopeFileObserverIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public x0 f16336i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ILogger f16337l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16338m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.b f16339n = new io.sentry.util.b();

    public static final class OutboxEnvelopeFileObserverIntegration extends EnvelopeFileObserverIntegration {
        private OutboxEnvelopeFileObserverIntegration() {
        }

        public /* synthetic */ OutboxEnvelopeFileObserverIntegration(int i10) {
            this();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f16339n.a();
        try {
            this.f16338m = true;
            aVarA.close();
            x0 x0Var = this.f16336i;
            if (x0Var != null) {
                x0Var.stopWatching();
                ILogger iLogger = this.f16337l;
                if (iLogger != null) {
                    iLogger.q(w5.DEBUG, "EnvelopeFileObserverIntegration removed.", new Object[0]);
                }
            }
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final void i(y6 y6Var, String str) {
        x0 x0Var = new x0(str, new m3(j4.f17401a, y6Var.getEnvelopeReader(), y6Var.getSerializer(), y6Var.getLogger(), y6Var.getFlushTimeoutMillis(), y6Var.getMaxQueueSize()), y6Var.getLogger(), y6Var.getFlushTimeoutMillis());
        this.f16336i = x0Var;
        try {
            x0Var.startWatching();
            y6Var.getLogger().q(w5.DEBUG, "EnvelopeFileObserverIntegration installed.", new Object[0]);
            xc.d.H("EnvelopeFileObserver");
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.ERROR, "Failed to initialize EnvelopeFileObserverIntegration.", th);
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        this.f16337l = y6Var.getLogger();
        String outboxPath = y6Var.getOutboxPath();
        if (outboxPath == null) {
            this.f16337l.q(w5.WARNING, "Null given as a path to EnvelopeFileObserverIntegration. Nothing will be registered.", new Object[0]);
            return;
        }
        this.f16337l.q(w5.DEBUG, "Registering EnvelopeFileObserverIntegration for path: %s", outboxPath);
        try {
            y6Var.getExecutorService().submit(new i1(this, y6Var, outboxPath, 3));
        } catch (Throwable th) {
            this.f16337l.l(w5.DEBUG, "Failed to start EnvelopeFileObserverIntegration on executor thread.", th);
        }
    }
}
