package io.sentry.android.core;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import io.sentry.ILogger;
import io.sentry.j4;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class AppComponentsBreadcrumbsIntegration implements io.sentry.s1, Closeable, ComponentCallbacks2 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final io.sentry.j0 f16327o = new io.sentry.j0();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16328i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public j4 f16329l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16330m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f16331n = new io.sentry.android.core.internal.util.g(60000, 0);

    public AppComponentsBreadcrumbsIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f16328i = applicationContext != null ? applicationContext : context;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            this.f16328i.unregisterComponentCallbacks(this);
        } catch (Throwable th) {
            SentryAndroidOptions sentryAndroidOptions = this.f16330m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(w5.DEBUG, th, "It was not possible to unregisterComponentCallbacks", new Object[0]);
            }
        }
        SentryAndroidOptions sentryAndroidOptions2 = this.f16330m;
        if (sentryAndroidOptions2 != null) {
            sentryAndroidOptions2.getLogger().q(w5.DEBUG, "AppComponentsBreadcrumbsIntegration removed.", new Object[0]);
        }
    }

    public final void i(Runnable runnable) {
        SentryAndroidOptions sentryAndroidOptions = this.f16330m;
        if (sentryAndroidOptions != null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(runnable);
            } catch (Throwable th) {
                this.f16330m.getLogger().k(w5.ERROR, th, "Failed to submit app components breadcrumb task", new Object[0]);
            }
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        this.f16329l = j4.f17401a;
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16330m = sentryAndroidOptions;
        ILogger logger = sentryAndroidOptions.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "AppComponentsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16330m.isEnableAppComponentBreadcrumbs()));
        if (this.f16330m.isEnableAppComponentBreadcrumbs()) {
            try {
                this.f16328i.registerComponentCallbacks(this);
                y6Var.getLogger().q(w5Var, "AppComponentsBreadcrumbsIntegration installed.", new Object[0]);
                xc.d.H("AppComponentsBreadcrumbs");
            } catch (Throwable th) {
                this.f16330m.setEnableAppComponentBreadcrumbs(false);
                y6Var.getLogger().k(w5.INFO, th, "ComponentCallbacks2 is not available.", new Object[0]);
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        i(new androidx.media3.exoplayer.video.l(this, System.currentTimeMillis(), configuration));
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i10) {
        if (i10 >= 40 && !this.f16331n.b()) {
            i(new androidx.media3.exoplayer.video.k(this, i10, 2, System.currentTimeMillis()));
        }
    }
}
