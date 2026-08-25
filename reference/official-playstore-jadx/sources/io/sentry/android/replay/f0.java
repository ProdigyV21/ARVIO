package io.sentry.android.replay;

import android.graphics.Point;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import io.sentry.w5;
import io.sentry.y6;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 implements i, h, e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f16941i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ReplayIntegration f16942l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ReplayIntegration f16943m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.work.impl.c f16944n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ScheduledExecutorService f16945o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AtomicBoolean f16946p = new AtomicBoolean(false);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f16947q = new ArrayList();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Point f16948r = new Point();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final io.sentry.util.b f16949s = new io.sentry.util.b();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.util.b f16950t = new io.sentry.util.b();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final io.sentry.util.b f16951u = new io.sentry.util.b();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile androidx.work.impl.utils.q f16952v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public volatile HandlerThread f16953w;
    public volatile Handler x;

    public f0(y6 y6Var, ReplayIntegration replayIntegration, ReplayIntegration replayIntegration2, androidx.work.impl.c cVar, io.sentry.android.replay.util.f fVar) {
        this.f16941i = y6Var;
        this.f16942l = replayIntegration;
        this.f16943m = replayIntegration2;
        this.f16944n = cVar;
        this.f16945o = fVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IllegalAccessException, InvocationTargetException {
        reset();
        androidx.work.impl.c cVar = this.f16944n;
        androidx.work.impl.utils.q qVar = this.f16952v;
        Handler handler = cVar.f6788a;
        if (qVar != null) {
            handler.removeCallbacks(qVar);
        }
        io.sentry.util.a aVarA = this.f16951u.a();
        try {
            Handler handler2 = this.x;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
            }
            HandlerThread handlerThread = this.f16953w;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
            androidx.work.impl.t.h(aVarA, null);
            stop();
        } finally {
        }
    }

    @Override // io.sentry.android.replay.i
    public final void d() {
        View view;
        androidx.work.impl.utils.q qVar = this.f16952v;
        if (qVar != null) {
            androidx.work.impl.c cVar = (androidx.work.impl.c) qVar.f7064m;
            y6 y6Var = (y6) qVar.f7063l;
            if (y6Var.getSessionReplay().f17226w) {
                y6Var.getLogger().q(w5.DEBUG, "Resuming the capture runnable.", new Object[0]);
            }
            w wVar = (w) qVar.f7065n;
            if (wVar != null) {
                WeakReference weakReference = wVar.f17085l;
                if (weakReference != null && (view = (View) weakReference.get()) != null && view.getViewTreeObserver() != null && view.getViewTreeObserver().isAlive()) {
                    try {
                        view.getViewTreeObserver().addOnDrawListener(wVar);
                    } catch (IllegalStateException unused) {
                    }
                }
                wVar.f17086m.set(true);
            }
            ((AtomicBoolean) qVar.f7067p).getAndSet(true);
            cVar.f6788a.removeCallbacks(qVar);
            if (cVar.f6788a.post(qVar)) {
                return;
            }
            y6Var.getLogger().q(w5.WARNING, "Failed to post the capture runnable, main looper is not ready.", new Object[0]);
        }
    }

    @Override // io.sentry.android.replay.i
    public final void g(y yVar) throws IllegalAccessException, InvocationTargetException {
        androidx.work.impl.utils.q qVar;
        w wVar;
        if (this.f16946p.get()) {
            if (this.f16952v == null) {
                io.sentry.util.a aVarA = this.f16950t.a();
                try {
                    if (this.f16952v == null) {
                        this.f16952v = new androidx.work.impl.utils.q(this.f16941i, this.f16944n);
                    }
                    androidx.work.impl.t.h(aVarA, null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        androidx.work.impl.t.h(aVarA, th);
                        throw th2;
                    }
                }
            }
            androidx.work.impl.utils.q qVar2 = this.f16952v;
            if (qVar2 != null) {
                qVar2.f7066o = yVar;
            }
            androidx.work.impl.utils.q qVar3 = this.f16952v;
            if (qVar3 != null) {
                qVar3.f7065n = new w(this.f16941i, this.f16942l, yVar, this);
            }
            WeakReference weakReference = (WeakReference) kotlin.collections.x.y0(this.f16947q);
            View view = weakReference != null ? (View) weakReference.get() : null;
            if (view != null && (qVar = this.f16952v) != null && (wVar = (w) qVar.f7065n) != null) {
                wVar.a(view);
            }
            androidx.work.impl.c cVar = this.f16944n;
            androidx.work.impl.utils.q qVar4 = this.f16952v;
            Handler handler = cVar.f6788a;
            if (qVar4 != null) {
                handler.removeCallbacks(qVar4);
            }
            androidx.work.impl.c cVar2 = this.f16944n;
            androidx.work.impl.utils.q qVar5 = this.f16952v;
            if (qVar5 == null ? false : cVar2.f6788a.postDelayed(qVar5, 100L)) {
                return;
            }
            this.f16941i.getLogger().q(w5.WARNING, "Failed to post the capture runnable, main looper is shutting down.", new Object[0]);
        }
    }

    @Override // io.sentry.android.replay.h
    public final void i(View view, boolean z) throws IllegalAccessException, InvocationTargetException {
        w wVar;
        w wVar2;
        w wVar3;
        io.sentry.util.a aVarA = this.f16949s.a();
        try {
            if (!z) {
                androidx.work.impl.utils.q qVar = this.f16952v;
                if (qVar != null && (wVar2 = (w) qVar.f7065n) != null) {
                    wVar2.c(view);
                }
                kotlin.collections.x.M0(this.f16947q, new e0(view, 0));
                WeakReference weakReference = (WeakReference) kotlin.collections.x.y0(this.f16947q);
                View view2 = weakReference != null ? (View) weakReference.get() : null;
                if (view2 != null && !view.equals(view2)) {
                    androidx.work.impl.utils.q qVar2 = this.f16952v;
                    if (qVar2 != null && (wVar = (w) qVar2.f7065n) != null) {
                        wVar.a(view2);
                    }
                    j(view2);
                }
            } else {
                if (a.a.R(view) == null) {
                    this.f16941i.getLogger().q(w5.WARNING, "Root view does not have a phone window, skipping.", new Object[0]);
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
                this.f16947q.add(new WeakReference(view));
                androidx.work.impl.utils.q qVar3 = this.f16952v;
                if (qVar3 != null && (wVar3 = (w) qVar3.f7065n) != null) {
                    wVar3.a(view);
                }
                j(view);
            }
            androidx.work.impl.t.h(aVarA, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                androidx.work.impl.t.h(aVarA, th);
                throw th2;
            }
        }
    }

    public final void j(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            d0 d0Var = new d0(this, view);
            if (view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
                return;
            }
            try {
                view.getViewTreeObserver().addOnPreDrawListener(d0Var);
                return;
            } catch (IllegalStateException unused) {
                return;
            }
        }
        int width = view.getWidth();
        Point point = this.f16948r;
        if (width == point.x || view.getHeight() == point.y) {
            return;
        }
        point.set(view.getWidth(), view.getHeight());
        this.f16943m.V(view.getWidth(), view.getHeight());
    }

    public final Handler k() throws IllegalAccessException, InvocationTargetException {
        if (this.x == null) {
            io.sentry.util.a aVarA = this.f16951u.a();
            try {
                if (this.x == null) {
                    this.f16953w = new HandlerThread("SentryReplayBackgroundProcessing");
                    HandlerThread handlerThread = this.f16953w;
                    if (handlerThread != null) {
                        handlerThread.start();
                    }
                    this.x = new Handler(this.f16953w.getLooper());
                }
                androidx.work.impl.t.h(aVarA, null);
            } finally {
            }
        }
        return this.x;
    }

    @Override // io.sentry.android.replay.i
    public final void pause() {
        androidx.work.impl.utils.q qVar = this.f16952v;
        if (qVar != null) {
            w wVar = (w) qVar.f7065n;
            if (wVar != null) {
                wVar.f17086m.set(false);
                WeakReference weakReference = wVar.f17085l;
                wVar.c(weakReference != null ? (View) weakReference.get() : null);
            }
            ((AtomicBoolean) qVar.f7067p).getAndSet(false);
        }
    }

    @Override // io.sentry.android.replay.i
    public final void reset() throws IllegalAccessException, InvocationTargetException {
        w wVar;
        this.f16948r.set(0, 0);
        io.sentry.util.a aVarA = this.f16949s.a();
        try {
            for (WeakReference weakReference : this.f16947q) {
                androidx.work.impl.utils.q qVar = this.f16952v;
                if (qVar != null && (wVar = (w) qVar.f7065n) != null) {
                    wVar.c((View) weakReference.get());
                }
            }
            this.f16947q.clear();
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    @Override // io.sentry.android.replay.i
    public final void start() {
        this.f16946p.getAndSet(true);
    }

    @Override // io.sentry.android.replay.i
    public final void stop() throws IllegalAccessException, InvocationTargetException {
        androidx.work.impl.utils.q qVar = this.f16952v;
        if (qVar != null) {
            w wVar = (w) qVar.f7065n;
            if (wVar != null) {
                wVar.f17086m.set(false);
                WeakReference weakReference = wVar.f17085l;
                wVar.c(weakReference != null ? (View) weakReference.get() : null);
                WeakReference weakReference2 = wVar.f17085l;
                if (weakReference2 != null) {
                    weakReference2.clear();
                }
                wVar.f17088o.close();
            }
            qVar.f7065n = null;
            ((AtomicBoolean) qVar.f7067p).getAndSet(false);
        }
        io.sentry.util.a aVarA = this.f16950t.a();
        try {
            this.f16952v = null;
            androidx.work.impl.t.h(aVarA, null);
            this.f16946p.set(false);
        } finally {
        }
    }
}
