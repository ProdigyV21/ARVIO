package io.sentry.android.core;

import android.content.Context;
import android.os.Build;
import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class NetworkBreadcrumbsIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16348i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r0 f16349l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.util.b f16350m = new io.sentry.util.b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile g1 f16351n;

    public NetworkBreadcrumbsIntegration(Context context, r0 r0Var) {
        Context applicationContext = context.getApplicationContext();
        this.f16348i = applicationContext != null ? applicationContext : context;
        this.f16349l = r0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f16350m.a();
        try {
            g1 g1Var = this.f16351n;
            this.f16351n = null;
            aVarA.close();
            if (g1Var != null) {
                io.sentry.util.a aVarA2 = io.sentry.android.core.internal.util.c.x.a();
                try {
                    io.sentry.android.core.internal.util.c.f16587y.remove(g1Var);
                    aVarA2.close();
                } catch (Throwable th) {
                    try {
                        aVarA2.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            try {
                aVarA.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        ILogger logger = y6Var.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "NetworkBreadcrumbsIntegration enabled: %s", Boolean.valueOf(sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()));
        if (sentryAndroidOptions.isEnableNetworkEventBreadcrumbs()) {
            this.f16349l.getClass();
            if (Build.VERSION.SDK_INT < 24) {
                y6Var.getLogger().q(w5Var, "NetworkCallbacks need Android N+.", new Object[0]);
                return;
            }
            io.sentry.util.a aVarA = this.f16350m.a();
            try {
                this.f16351n = new g1(this.f16349l, y6Var.getDateProvider());
                if (io.sentry.android.core.internal.util.c.k(this.f16348i, y6Var.getLogger(), this.f16349l, this.f16351n)) {
                    y6Var.getLogger().q(w5Var, "NetworkBreadcrumbsIntegration installed.", new Object[0]);
                    xc.d.H("NetworkBreadcrumbs");
                } else {
                    y6Var.getLogger().q(w5Var, "NetworkBreadcrumbsIntegration not installed.", new Object[0]);
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
}
