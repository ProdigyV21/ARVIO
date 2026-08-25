package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes3.dex */
public final class u0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final View f2315i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ViewTreeObserver f2316l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Runnable f2317m;

    public u0(View view, Runnable runnable) {
        this.f2315i = view;
        this.f2316l = view.getViewTreeObserver();
        this.f2317m = runnable;
    }

    public static void a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        u0 u0Var = new u0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(u0Var);
        view.addOnAttachStateChangeListener(u0Var);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.f2316l.isAlive();
        View view = this.f2315i;
        if (zIsAlive) {
            this.f2316l.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.f2317m.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.f2316l = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.f2316l.isAlive();
        View view2 = this.f2315i;
        if (zIsAlive) {
            this.f2316l.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
