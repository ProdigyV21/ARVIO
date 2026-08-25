package io.sentry.android.replay;

import android.view.View;
import android.view.ViewTreeObserver;
import io.sentry.w5;
import io.sentry.y6;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class w implements ViewTreeObserver.OnDrawListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final y6 f17084i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public WeakReference f17085l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f17086m = new AtomicBoolean(true);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AtomicBoolean f17087n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.android.replay.screenshot.f f17088o;

    public w(y6 y6Var, ReplayIntegration replayIntegration, y yVar, f0 f0Var) {
        io.sentry.android.replay.screenshot.f dVar;
        this.f17084i = y6Var;
        io.sentry.android.replay.util.b bVar = new io.sentry.android.replay.util.b();
        this.f17087n = new AtomicBoolean(false);
        int i10 = v.f17055a[y6Var.getSessionReplay().x.ordinal()];
        if (i10 == 1) {
            dVar = new io.sentry.android.replay.screenshot.d(y6Var, replayIntegration, yVar, f0Var);
        } else {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            dVar = new io.sentry.android.replay.screenshot.e(f0Var, replayIntegration, y6Var, yVar, bVar);
        }
        this.f17088o = dVar;
    }

    public final void a(View view) {
        WeakReference weakReference = this.f17085l;
        c(weakReference != null ? (View) weakReference.get() : null);
        WeakReference weakReference2 = this.f17085l;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.f17085l = new WeakReference(view);
        if (view != null && view.getViewTreeObserver() != null && view.getViewTreeObserver().isAlive()) {
            try {
                view.getViewTreeObserver().addOnDrawListener(this);
            } catch (IllegalStateException unused) {
            }
        }
        this.f17087n.set(true);
        this.f17088o.onContentChanged();
    }

    public final void b() {
        y6 y6Var = this.f17084i;
        boolean z = y6Var.getSessionReplay().f17226w;
        AtomicBoolean atomicBoolean = this.f17086m;
        if (z) {
            y6Var.getLogger().q(w5.DEBUG, "Capturing screenshot, isCapturing: %s", Boolean.valueOf(atomicBoolean.get()));
        }
        if (!atomicBoolean.get()) {
            if (y6Var.getSessionReplay().f17226w) {
                y6Var.getLogger().q(w5.DEBUG, "ScreenshotRecorder is paused, not capturing screenshot", new Object[0]);
                return;
            }
            return;
        }
        boolean z5 = y6Var.getSessionReplay().f17226w;
        io.sentry.android.replay.screenshot.f fVar = this.f17088o;
        AtomicBoolean atomicBoolean2 = this.f17087n;
        if (z5) {
            y6Var.getLogger().q(w5.DEBUG, "Capturing screenshot, contentChanged: %s, lastCaptureSuccessful: %s", Boolean.valueOf(atomicBoolean2.get()), Boolean.valueOf(fVar.a()));
        }
        if (!atomicBoolean2.get()) {
            fVar.b();
            return;
        }
        WeakReference weakReference = this.f17085l;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            y6Var.getLogger().q(w5.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        if (a.a.R(view) == null) {
            y6Var.getLogger().q(w5.DEBUG, "Window is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        try {
            atomicBoolean2.set(false);
            fVar.c(view);
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.WARNING, "Failed to capture replay recording", th);
        }
    }

    public final void c(View view) {
        this.f17084i.getReplayController().getClass();
        if (view == null || view.getViewTreeObserver() == null || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        try {
            view.getViewTreeObserver().removeOnDrawListener(this);
        } catch (IllegalStateException unused) {
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        if (this.f17086m.get()) {
            WeakReference weakReference = this.f17085l;
            View view = weakReference != null ? (View) weakReference.get() : null;
            if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
                this.f17084i.getLogger().q(w5.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            } else {
                this.f17087n.set(true);
                this.f17088o.onContentChanged();
            }
        }
    }
}
