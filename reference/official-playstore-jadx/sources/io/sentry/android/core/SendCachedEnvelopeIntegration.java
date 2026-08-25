package io.sentry.android.core;

import io.sentry.j4;
import io.sentry.l4;
import io.sentry.n4;
import io.sentry.o0;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
final class SendCachedEnvelopeIntegration implements io.sentry.s1, o0.b, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n4 f16357i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.util.k f16358l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.o0 f16360n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public j4 f16361o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SentryAndroidOptions f16362p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public l4 f16363q;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f16359m = new AtomicBoolean(false);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final AtomicBoolean f16364r = new AtomicBoolean(false);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AtomicBoolean f16365s = new AtomicBoolean(false);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.util.b f16366t = new io.sentry.util.b();

    public SendCachedEnvelopeIntegration(n4 n4Var, io.sentry.util.k kVar) {
        this.f16357i = n4Var;
        this.f16358l = kVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16365s.set(true);
        io.sentry.o0 o0Var = this.f16360n;
        if (o0Var != null) {
            o0Var.G0(this);
        }
    }

    @Override // io.sentry.o0.b
    public final void i(o0.a aVar) {
        SentryAndroidOptions sentryAndroidOptions;
        j4 j4Var = this.f16361o;
        if (j4Var == null || (sentryAndroidOptions = this.f16362p) == null || aVar == o0.a.DISCONNECTED) {
            return;
        }
        j(j4Var, sentryAndroidOptions);
    }

    public final void j(j4 j4Var, SentryAndroidOptions sentryAndroidOptions) {
        try {
            io.sentry.util.a aVarA = this.f16366t.a();
            try {
                Future futureSubmit = sentryAndroidOptions.getExecutorService().submit(new i1(this, sentryAndroidOptions, j4Var, 0));
                if (((Boolean) this.f16358l.a()).booleanValue() && this.f16359m.compareAndSet(false, true)) {
                    sentryAndroidOptions.getLogger().q(w5.DEBUG, "Startup Crash marker exists, blocking flush.", new Object[0]);
                    try {
                        futureSubmit.get(sentryAndroidOptions.getStartupCrashFlushTimeoutMillis(), TimeUnit.MILLISECONDS);
                    } catch (TimeoutException unused) {
                        sentryAndroidOptions.getLogger().q(w5.DEBUG, "Synchronous send timed out, continuing in the background.", new Object[0]);
                    }
                }
                sentryAndroidOptions.getLogger().q(w5.DEBUG, "SendCachedEnvelopeIntegration installed.", new Object[0]);
                aVarA.close();
            } finally {
            }
        } catch (RejectedExecutionException e5) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to call the executor. Cached events will not be sent. Did you call Sentry.close()?", e5);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().l(w5.ERROR, "Failed to call the executor. Cached events will not be sent", th);
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        j4 j4Var = j4.f17401a;
        this.f16361o = j4Var;
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16362p = sentryAndroidOptions;
        if (!this.f16357i.b(y6Var.getCacheDirPath(), y6Var.getLogger())) {
            y6Var.getLogger().q(w5.ERROR, "No cache dir path is defined in options.", new Object[0]);
        } else {
            xc.d.H("SendCachedEnvelope");
            j(j4Var, this.f16362p);
        }
    }
}
