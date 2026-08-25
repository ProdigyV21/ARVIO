package io.sentry.android.core;

import android.content.Context;
import android.content.IntentFilter;
import android.os.HandlerThread;
import io.sentry.j4;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class SystemEventsBreadcrumbsIntegration implements io.sentry.s1, Closeable, e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16372i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile w1 f16373l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public j4 f16375n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String[] f16376o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile boolean f16377p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile boolean f16378q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile IntentFilter f16379r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile HandlerThread f16380s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AtomicBoolean f16381t = new AtomicBoolean(false);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.util.b f16382u = new io.sentry.util.b();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public v1 f16383v;

    public SystemEventsBreadcrumbsIntegration(Context context) {
        String[] strArr = {"android.intent.action.ACTION_SHUTDOWN", "android.intent.action.AIRPLANE_MODE", "android.intent.action.BATTERY_CHANGED", "android.intent.action.CAMERA_BUTTON", "android.intent.action.CONFIGURATION_CHANGED", "android.intent.action.DATE_CHANGED", "android.intent.action.DEVICE_STORAGE_LOW", "android.intent.action.DEVICE_STORAGE_OK", "android.intent.action.DOCK_EVENT", "android.intent.action.DREAMING_STARTED", "android.intent.action.DREAMING_STOPPED", "android.intent.action.INPUT_METHOD_CHANGED", "android.intent.action.LOCALE_CHANGED", "android.intent.action.SCREEN_OFF", "android.intent.action.SCREEN_ON", "android.intent.action.TIMEZONE_CHANGED", "android.intent.action.TIME_SET", "android.os.action.DEVICE_IDLE_MODE_CHANGED", "android.os.action.POWER_SAVE_MODE_CHANGED"};
        Context applicationContext = context.getApplicationContext();
        this.f16372i = applicationContext == null ? context : applicationContext;
        this.f16376o = strArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.util.a aVarA = this.f16382u.a();
        try {
            this.f16377p = true;
            this.f16379r = null;
            if (this.f16380s != null) {
                this.f16380s.quit();
            }
            this.f16380s = null;
            aVarA.close();
            h0.f16496o.l(this);
            SentryAndroidOptions sentryAndroidOptions = this.f16374m;
            if (sentryAndroidOptions != null) {
                try {
                    sentryAndroidOptions.getExecutorService().submit(new androidx.activity.n(this, 18));
                } catch (RejectedExecutionException unused) {
                    o(this.f16374m);
                }
            }
            SentryAndroidOptions sentryAndroidOptions2 = this.f16374m;
            if (sentryAndroidOptions2 != null) {
                sentryAndroidOptions2.getLogger().q(w5.DEBUG, "SystemEventsBreadcrumbsIntegration removed.", new Object[0]);
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

    @Override // io.sentry.android.core.e0
    public final void i() {
        if (this.f16375n == null || this.f16374m == null) {
            return;
        }
        this.f16378q = false;
        k(this.f16375n, this.f16374m);
    }

    @Override // io.sentry.android.core.e0
    public final void j() {
        SentryAndroidOptions sentryAndroidOptions = this.f16374m;
        if (sentryAndroidOptions == null) {
            return;
        }
        try {
            sentryAndroidOptions.getExecutorService().submit(new androidx.activity.n(this, 18));
        } catch (RejectedExecutionException unused) {
            o(this.f16374m);
        }
    }

    public final void k(j4 j4Var, SentryAndroidOptions sentryAndroidOptions) {
        if (sentryAndroidOptions.isEnableSystemEventBreadcrumbs() && !this.f16377p && !this.f16378q && this.f16373l == null) {
            try {
                sentryAndroidOptions.getExecutorService().submit(new i1(this, j4Var, sentryAndroidOptions));
            } catch (Throwable unused) {
                sentryAndroidOptions.getLogger().q(w5.WARNING, "Failed to start SystemEventsBreadcrumbsIntegration on executor thread.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16374m = sentryAndroidOptions;
        this.f16375n = j4.f17401a;
        sentryAndroidOptions.getLogger().q(w5.DEBUG, "SystemEventsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16374m.isEnableSystemEventBreadcrumbs()));
        if (this.f16374m.isEnableSystemEventBreadcrumbs()) {
            h0.f16496o.i(this);
            if (q0.i()) {
                k(this.f16375n, this.f16374m);
            }
        }
    }

    public final void o(SentryAndroidOptions sentryAndroidOptions) {
        io.sentry.util.a aVarA = this.f16382u.a();
        try {
            this.f16378q = true;
            w1 w1Var = this.f16373l;
            this.f16373l = null;
            aVarA.close();
            if (w1Var != null) {
                try {
                    this.f16372i.unregisterReceiver(w1Var);
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().k(w5.ERROR, th, "Failed to unregister SystemEventsBroadcastReceiver", new Object[0]);
                }
            }
        } catch (Throwable th2) {
            try {
                aVarA.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }
}
