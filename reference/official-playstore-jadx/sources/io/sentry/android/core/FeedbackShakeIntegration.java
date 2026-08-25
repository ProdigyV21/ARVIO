package io.sentry.android.core;

import android.app.Activity;
import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.HandlerThread;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class FeedbackShakeIntegration implements io.sentry.s1, Closeable, Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Application f16340i;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16342m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile WeakReference f16343n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile Runnable f16345p;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile boolean f16344o = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o1 f16341l = new o1();

    public FeedbackShakeIntegration(Application application) {
        this.f16340i = application;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16340i.unregisterActivityLifecycleCallbacks(this);
        o1 o1Var = this.f16341l;
        o1Var.f16682f = null;
        o1Var.f16684h = 0;
        o1Var.f16685i = 0L;
        SensorManager sensorManager = o1Var.f16677a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(o1Var);
        }
        HandlerThread handlerThread = o1Var.f16679c;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            o1Var.f16679c = null;
            o1Var.f16680d = null;
        }
        if (this.f16344o) {
            this.f16344o = false;
            SentryAndroidOptions sentryAndroidOptions = this.f16342m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getFeedbackOptions().f17784h = this.f16345p;
            }
            this.f16345p = null;
        }
        this.f16343n = null;
    }

    public final void i(Activity activity) {
        if (this.f16342m == null) {
            return;
        }
        o1 o1Var = this.f16341l;
        o1Var.f16682f = null;
        o1Var.f16684h = 0;
        o1Var.f16685i = 0L;
        SensorManager sensorManager = o1Var.f16677a;
        if (sensorManager != null) {
            sensorManager.unregisterListener(o1Var);
        }
        o1 o1Var2 = this.f16341l;
        o1Var2.f16682f = new a3.b(this, 24);
        o1Var2.a(activity);
        SensorManager sensorManager2 = o1Var2.f16677a;
        if (sensorManager2 == null) {
            o1Var2.f16683g.q(w5.WARNING, "SensorManager is not available. Shake detection disabled.", new Object[0]);
            return;
        }
        Sensor sensor = o1Var2.f16678b;
        if (sensor == null) {
            o1Var2.f16683g.q(w5.WARNING, "Accelerometer sensor not available. Shake detection disabled.", new Object[0]);
        } else {
            sensorManager2.registerListener(o1Var2, sensor, 3, o1Var2.f16680d);
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16342m = sentryAndroidOptions;
        if (sentryAndroidOptions.getFeedbackOptions().f17783g) {
            o1 o1Var = this.f16341l;
            Application application = this.f16340i;
            o1Var.f16683g = this.f16342m.getLogger();
            o1Var.a(application);
            xc.d.H("FeedbackShake");
            this.f16340i.registerActivityLifecycleCallbacks(this);
            this.f16342m.getLogger().q(w5.DEBUG, "FeedbackShakeIntegration installed.", new Object[0]);
            WeakReference weakReference = (WeakReference) r0.f16734b.f16735a;
            Activity activity = weakReference != null ? (Activity) weakReference.get() : null;
            if (activity != null) {
                this.f16343n = new WeakReference(activity);
                i(activity);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        Activity activity2 = this.f16343n != null ? (Activity) this.f16343n.get() : null;
        if (this.f16344o && activity == activity2) {
            this.f16344o = false;
            this.f16343n = null;
            SentryAndroidOptions sentryAndroidOptions = this.f16342m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getFeedbackOptions().f17784h = this.f16345p;
            }
            this.f16345p = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (activity == (this.f16343n != null ? (Activity) this.f16343n.get() : null)) {
            o1 o1Var = this.f16341l;
            o1Var.f16682f = null;
            o1Var.f16684h = 0;
            o1Var.f16685i = 0L;
            SensorManager sensorManager = o1Var.f16677a;
            if (sensorManager != null) {
                sensorManager.unregisterListener(o1Var);
            }
            if (this.f16344o) {
                return;
            }
            this.f16343n = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        Activity activity2 = this.f16343n != null ? (Activity) this.f16343n.get() : null;
        if (this.f16344o && activity2 != null && activity2 != activity) {
            this.f16344o = false;
            SentryAndroidOptions sentryAndroidOptions = this.f16342m;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getFeedbackOptions().f17784h = this.f16345p;
            }
            this.f16345p = null;
        }
        this.f16343n = new WeakReference(activity);
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
