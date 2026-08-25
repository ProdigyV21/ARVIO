package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.sentry.ILogger;
import io.sentry.j4;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityBreadcrumbsIntegration implements io.sentry.s1, Closeable, Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Application f16300i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public j4 f16301l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16302m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.b f16303n = new io.sentry.util.b();

    public ActivityBreadcrumbsIntegration(Application application) {
        this.f16300i = application;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f16302m) {
            this.f16300i.unregisterActivityLifecycleCallbacks(this);
            j4 j4Var = this.f16301l;
            if (j4Var != null) {
                j4Var.getOptions().getLogger().q(w5.DEBUG, "ActivityBreadcrumbsIntegration removed.", new Object[0]);
            }
        }
    }

    public final void i(Activity activity, String str) {
        if (this.f16301l == null) {
            return;
        }
        io.sentry.f fVar = new io.sentry.f();
        fVar.f17247o = "navigation";
        fVar.f(str, "state");
        fVar.f(activity.getClass().getSimpleName(), "screen");
        fVar.f17249q = "ui.lifecycle";
        fVar.f17251s = w5.INFO;
        io.sentry.j0 j0Var = new io.sentry.j0();
        j0Var.d("android:activity", activity);
        this.f16301l.h(fVar, j0Var);
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16301l = j4.f17401a;
        this.f16302m = sentryAndroidOptions.isEnableActivityLifecycleBreadcrumbs();
        ILogger logger = y6Var.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "ActivityBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16302m));
        if (this.f16302m) {
            this.f16300i.registerActivityLifecycleCallbacks(this);
            y6Var.getLogger().q(w5Var, "ActivityBreadcrumbIntegration installed.", new Object[0]);
            xc.d.H("ActivityBreadcrumbs");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "created");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "destroyed");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "paused");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "resumed");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "saveInstanceState");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "started");
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

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        io.sentry.util.a aVarA = this.f16303n.a();
        try {
            i(activity, "stopped");
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
