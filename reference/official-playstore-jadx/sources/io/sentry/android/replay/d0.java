package io.sentry.android.replay;

import android.graphics.Point;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 implements ViewTreeObserver.OnPreDrawListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f0 f16934i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ View f16935l;

    public d0(f0 f0Var, View view) {
        this.f16934i = f0Var;
        this.f16935l = view;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        f0 f0Var = this.f16934i;
        Point point = f0Var.f16948r;
        WeakReference weakReference = (WeakReference) kotlin.collections.x.y0(f0Var.f16947q);
        View view = weakReference != null ? (View) weakReference.get() : null;
        View view2 = this.f16935l;
        if (kotlin.jvm.internal.p.a(view2, view)) {
            if (view2.getWidth() > 0 && view2.getHeight() > 0) {
                if (view2.getViewTreeObserver() != null && view2.getViewTreeObserver().isAlive()) {
                    try {
                        view2.getViewTreeObserver().removeOnPreDrawListener(this);
                    } catch (IllegalStateException unused) {
                    }
                }
                if (view2.getWidth() != point.x && view2.getHeight() != point.y) {
                    point.set(view2.getWidth(), view2.getHeight());
                    f0Var.f16943m.V(view2.getWidth(), view2.getHeight());
                }
            }
        } else if (view2 != null && view2.getViewTreeObserver() != null && view2.getViewTreeObserver().isAlive()) {
            try {
                view2.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            } catch (IllegalStateException unused2) {
            }
        }
        return true;
    }
}
