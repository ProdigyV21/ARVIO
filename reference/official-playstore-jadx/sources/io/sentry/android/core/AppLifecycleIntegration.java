package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class AppLifecycleIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.util.b f16332i = new io.sentry.util.b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile a1 f16333l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16334m;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f16332i.a();
        try {
            a1 a1Var = this.f16333l;
            this.f16333l = null;
            aVarA.close();
            if (a1Var != null) {
                h0.f16496o.l(a1Var);
                SentryAndroidOptions sentryAndroidOptions = this.f16334m;
                if (sentryAndroidOptions != null) {
                    sentryAndroidOptions.getLogger().q(w5.DEBUG, "AppLifecycleIntegration removed.", new Object[0]);
                }
            }
            h0.f16496o.o();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16334m = sentryAndroidOptions;
        ILogger logger = sentryAndroidOptions.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "enableSessionTracking enabled: %s", Boolean.valueOf(this.f16334m.isEnableAutoSessionTracking()));
        this.f16334m.getLogger().q(w5Var, "enableAppLifecycleBreadcrumbs enabled: %s", Boolean.valueOf(this.f16334m.isEnableAppLifecycleBreadcrumbs()));
        if (this.f16334m.isEnableAutoSessionTracking() || this.f16334m.isEnableAppLifecycleBreadcrumbs()) {
            io.sentry.util.a aVarA = this.f16332i.a();
            try {
                if (this.f16333l != null) {
                    aVarA.close();
                    return;
                }
                this.f16333l = new a1(this.f16334m.getSessionTrackingIntervalMillis(), this.f16334m.isEnableAutoSessionTracking(), this.f16334m.isEnableAppLifecycleBreadcrumbs());
                h0.f16496o.i(this.f16333l);
                aVarA.close();
                y6Var.getLogger().q(w5Var, "AppLifecycleIntegration installed.", new Object[0]);
                xc.d.H("AppLifecycle");
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
}
