package io.sentry.android.core.internal.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Choreographer;
import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window$OnFrameMetricsAvailableListener;
import androidx.activity.s;
import io.sentry.ILogger;
import io.sentry.android.core.r0;
import io.sentry.android.core.v;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements Application.ActivityLifecycleCallbacks {
    public static final long x = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final long f16631y = TimeUnit.MILLISECONDS.toNanos(700);
    public static final /* synthetic */ int z = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r0 f16632i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final CopyOnWriteArraySet f16633l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ILogger f16634m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Handler f16635n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public WeakReference f16636o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ConcurrentHashMap f16637p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16638q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final d f16639r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final n f16640s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Choreographer f16641t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Field f16642u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f16643v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f16644w;

    /* JADX WARN: Type inference failed for: r4v5, types: [io.sentry.android.core.internal.util.n] */
    public q(Context context, final v vVar, final r0 r0Var) {
        d dVar = new d();
        this.f16633l = new CopyOnWriteArraySet();
        this.f16637p = new ConcurrentHashMap();
        this.f16638q = false;
        this.f16643v = 0L;
        this.f16644w = 0L;
        Context applicationContext = context.getApplicationContext();
        context = applicationContext != null ? applicationContext : context;
        ac.b.V(vVar, "Logger is required");
        this.f16634m = vVar;
        ac.b.V(r0Var, "BuildInfoProvider is required");
        this.f16632i = r0Var;
        this.f16639r = dVar;
        if ((context instanceof Application) && Build.VERSION.SDK_INT >= 24) {
            this.f16638q = true;
            HandlerThread handlerThread = new HandlerThread("io.sentry.android.core.internal.util.SentryFrameMetricsCollector");
            handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.sentry.android.core.internal.util.m
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    vVar.l(w5.ERROR, "Error during frames measurements.", th);
                }
            });
            handlerThread.start();
            this.f16635n = new Handler(handlerThread.getLooper());
            ((Application) context).registerActivityLifecycleCallbacks(this);
            new Handler(Looper.getMainLooper()).post(new s(this, vVar, 29));
            try {
                Field declaredField = Choreographer.class.getDeclaredField("mLastFrameTimeNanos");
                this.f16642u = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e5) {
                vVar.l(w5.ERROR, "Unable to get the frame timestamp from the choreographer: ", e5);
            }
            this.f16640s = new Window$OnFrameMetricsAvailableListener() { // from class: io.sentry.android.core.internal.util.n
                public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i10) {
                    q.a(this.f16629a, r0Var, window, frameMetrics);
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(io.sentry.android.core.internal.util.q r18, io.sentry.android.core.r0 r19, android.view.Window r20, android.view.FrameMetrics r21) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.util.q.a(io.sentry.android.core.internal.util.q, io.sentry.android.core.r0, android.view.Window, android.view.FrameMetrics):void");
    }

    public final void b(String str) {
        if (this.f16638q) {
            ConcurrentHashMap concurrentHashMap = this.f16637p;
            if (str != null) {
                concurrentHashMap.remove(str);
            }
            WeakReference weakReference = this.f16636o;
            Window window = weakReference != null ? (Window) weakReference.get() : null;
            if (window == null || !concurrentHashMap.isEmpty()) {
                return;
            }
            new Handler(Looper.getMainLooper()).post(new l(this, window, 1));
        }
    }

    public final void c() {
        WeakReference weakReference = this.f16636o;
        Window window = weakReference != null ? (Window) weakReference.get() : null;
        if (window == null || !this.f16638q || this.f16637p.isEmpty() || this.f16635n == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new l(this, window, 0));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        Window window = activity.getWindow();
        WeakReference weakReference = this.f16636o;
        if (weakReference == null || weakReference.get() != window) {
            this.f16636o = new WeakReference(window);
            c();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        new Handler(Looper.getMainLooper()).post(new l(this, activity.getWindow(), 1));
        WeakReference weakReference = this.f16636o;
        if (weakReference == null || weakReference.get() != activity.getWindow()) {
            return;
        }
        this.f16636o = null;
    }
}
