package io.sentry.android.core;

import android.content.Context;
import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class AnrIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static b f16318o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final io.sentry.util.b f16319p = new io.sentry.util.b();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16320i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f16321l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.b f16322m = new io.sentry.util.b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public y6 f16323n;

    public AnrIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f16320i = applicationContext != null ? applicationContext : context;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f16322m.a();
        try {
            this.f16321l = true;
            aVarA.close();
            aVarA = f16319p.a();
            try {
                b bVar = f16318o;
                if (bVar != null) {
                    bVar.interrupt();
                    f16318o = null;
                    y6 y6Var = this.f16323n;
                    if (y6Var != null) {
                        y6Var.getLogger().q(w5.DEBUG, "AnrIntegration removed.", new Object[0]);
                    }
                }
                aVarA.close();
            } finally {
                try {
                    aVarA.close();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final void i(SentryAndroidOptions sentryAndroidOptions) {
        io.sentry.util.a aVarA = f16319p.a();
        try {
            if (f16318o == null) {
                ILogger logger = sentryAndroidOptions.getLogger();
                w5 w5Var = w5.DEBUG;
                logger.q(w5Var, "ANR timeout in milliseconds: %d", Long.valueOf(sentryAndroidOptions.getAnrTimeoutIntervalMillis()));
                b bVar = new b(sentryAndroidOptions.getAnrTimeoutIntervalMillis(), sentryAndroidOptions.isAnrReportInDebug(), new q(this, sentryAndroidOptions), sentryAndroidOptions.getLogger(), this.f16320i);
                f16318o = bVar;
                bVar.start();
                sentryAndroidOptions.getLogger().q(w5Var, "AnrIntegration installed.", new Object[0]);
            }
            aVarA.close();
        } finally {
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        this.f16323n = y6Var;
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) y6Var;
        sentryAndroidOptions.getLogger().q(w5.DEBUG, "AnrIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isAnrEnabled()));
        if (sentryAndroidOptions.isAnrEnabled()) {
            xc.d.H("Anr");
            try {
                sentryAndroidOptions.getExecutorService().submit(new androidx.activity.s(this, sentryAndroidOptions, 24));
            } catch (Throwable th) {
                sentryAndroidOptions.getLogger().l(w5.DEBUG, "Failed to start AnrIntegration on executor thread.", th);
            }
        }
    }
}
