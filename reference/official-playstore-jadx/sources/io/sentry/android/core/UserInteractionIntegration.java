package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import io.sentry.ILogger;
import io.sentry.j4;
import io.sentry.q7;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class UserInteractionIntegration implements io.sentry.s1, Closeable, Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Application f16387i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public j4 f16388l;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final WeakHashMap f16391o = new WeakHashMap();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f16392p = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16389m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f16390n = io.sentry.util.l.l(this.f16389m, "androidx.lifecycle.Lifecycle");

    public UserInteractionIntegration(Application application) {
        this.f16387i = application;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ArrayList<Window> arrayList;
        this.f16387i.unregisterActivityLifecycleCallbacks(this);
        synchronized (this.f16392p) {
            arrayList = new ArrayList(this.f16391o.keySet());
        }
        for (Window window : arrayList) {
            if (window != null) {
                j(window);
            }
        }
        synchronized (this.f16392p) {
            this.f16391o.clear();
        }
        SentryAndroidOptions sentryAndroidOptions = this.f16389m;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "UserInteractionIntegration removed.", new Object[0]);
        }
    }

    public final void i(Activity activity) {
        Window window = activity.getWindow();
        if (window == null) {
            SentryAndroidOptions sentryAndroidOptions = this.f16389m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().q(w5.INFO, "Window was null in startTracking", new Object[0]);
                return;
            }
            return;
        }
        if (this.f16388l == null || this.f16389m == null) {
            return;
        }
        synchronized (this.f16392p) {
            try {
                WeakReference weakReference = (WeakReference) this.f16391o.get(window);
                if (weakReference == null || weakReference.get() == null) {
                    Window.Callback callback = window.getCallback();
                    if (callback == null) {
                        callback = new io.sentry.android.core.internal.gestures.b();
                    }
                    io.sentry.android.core.internal.gestures.j jVar = new io.sentry.android.core.internal.gestures.j(callback, activity, new io.sentry.android.core.internal.gestures.g(activity, this.f16388l, this.f16389m), this.f16389m);
                    window.setCallback(jVar);
                    synchronized (this.f16392p) {
                        this.f16391o.put(window, new WeakReference(jVar));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void j(Window window) {
        io.sentry.android.core.internal.gestures.j jVar;
        Window.Callback callback = window.getCallback();
        if (callback instanceof io.sentry.android.core.internal.gestures.j) {
            io.sentry.android.core.internal.gestures.j jVar2 = (io.sentry.android.core.internal.gestures.j) callback;
            jVar2.f16553q = true;
            jVar2.f16549m.d(q7.CANCELLED);
            jVar2.f16550n.a();
            Window.Callback callback2 = jVar2.f16548l;
            if (callback2 instanceof io.sentry.android.core.internal.gestures.b) {
                window.setCallback(null);
            } else {
                window.setCallback(callback2);
            }
            synchronized (this.f16392p) {
                this.f16391o.remove(window);
            }
            return;
        }
        synchronized (this.f16392p) {
            try {
                WeakReference weakReference = (WeakReference) this.f16391o.remove(window);
                jVar = weakReference != null ? (io.sentry.android.core.internal.gestures.j) weakReference.get() : null;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (jVar != null) {
            jVar.f16553q = true;
            jVar.f16549m.d(q7.CANCELLED);
            jVar.f16550n.a();
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16389m = sentryAndroidOptions;
        this.f16388l = j4.f17401a;
        boolean z = sentryAndroidOptions.isEnableUserInteractionBreadcrumbs() || this.f16389m.isEnableUserInteractionTracing();
        ILogger logger = this.f16389m.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "UserInteractionIntegration enabled: %s", Boolean.valueOf(z));
        if (z) {
            this.f16387i.registerActivityLifecycleCallbacks(this);
            this.f16389m.getLogger().q(w5Var, "UserInteractionIntegration installed.", new Object[0]);
            xc.d.H("UserInteraction");
            if (this.f16390n) {
                WeakReference weakReference = (WeakReference) r0.f16734b.f16735a;
                Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
                if ((activity instanceof androidx.lifecycle.y) && ((androidx.lifecycle.y) activity).getLifecycle().getCurrentState() == androidx.lifecycle.q.f3270o) {
                    i(activity);
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        Window window = activity.getWindow();
        if (window != null) {
            j(window);
            return;
        }
        SentryAndroidOptions sentryAndroidOptions = this.f16389m;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.INFO, "Window was null in stopTracking", new Object[0]);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        i(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
