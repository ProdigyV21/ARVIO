package io.sentry.android.core.internal.util;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import io.sentry.android.core.r0;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements ViewTreeObserver.OnDrawListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Handler f16612i = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicReference f16613l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Runnable f16614m;

    public i(View view, Runnable runnable) {
        this.f16613l = new AtomicReference(view);
        this.f16614m = runnable;
    }

    public static void a(Activity activity, Runnable runnable, r0 r0Var) {
        Window window = activity.getWindow();
        if (window != null) {
            View viewPeekDecorView = window.peekDecorView();
            if (viewPeekDecorView != null) {
                b(viewPeekDecorView, runnable, r0Var);
            } else {
                Window.Callback callback = window.getCallback();
                window.setCallback(new io.sentry.android.core.performance.i(callback != null ? callback : new io.sentry.android.core.internal.gestures.b(), new androidx.media3.exoplayer.source.preload.b(window, callback, runnable, r0Var, 4)));
            }
        }
    }

    public static void b(View view, Runnable runnable, r0 r0Var) {
        i iVar = new i(view, runnable);
        r0Var.getClass();
        if (Build.VERSION.SDK_INT >= 26 || (view.getViewTreeObserver().isAlive() && view.isAttachedToWindow())) {
            view.getViewTreeObserver().addOnDrawListener(iVar);
        } else {
            view.addOnAttachStateChangeListener(new androidx.appcompat.view.menu.g(iVar, 2));
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        final View view = (View) this.f16613l.getAndSet(null);
        if (view == null) {
            return;
        }
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: io.sentry.android.core.internal.util.h
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                i iVar = this.f16610i;
                iVar.getClass();
                view.getViewTreeObserver().removeOnDrawListener(iVar);
            }
        });
        this.f16612i.postAtFrontOfQueue(this.f16614m);
    }
}
