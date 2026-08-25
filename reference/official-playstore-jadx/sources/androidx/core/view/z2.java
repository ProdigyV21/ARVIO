package androidx.core.view;

import android.view.View;
import android.view.WindowInsetsAnimation;

/* JADX INFO: loaded from: classes3.dex */
public final class z2 extends a3 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WindowInsetsAnimation f2341d;

    public z2(WindowInsetsAnimation windowInsetsAnimation) {
        super(null, 0L);
        this.f2341d = windowInsetsAnimation;
    }

    public static androidx.core.graphics.c d(WindowInsetsAnimation.Bounds bounds) {
        return androidx.core.graphics.c.c(bounds.getUpperBound());
    }

    public static androidx.core.graphics.c e(WindowInsetsAnimation.Bounds bounds) {
        return androidx.core.graphics.c.c(bounds.getLowerBound());
    }

    public static void f(View view, t2 t2Var) {
        view.setWindowInsetsAnimationCallback(t2Var != null ? new y2(t2Var) : null);
    }

    @Override // androidx.core.view.a3
    public final long a() {
        return this.f2341d.getDurationMillis();
    }

    @Override // androidx.core.view.a3
    public final float b() {
        return this.f2341d.getInterpolatedFraction();
    }

    @Override // androidx.core.view.a3
    public final void c(float f10) {
        this.f2341d.setFraction(f10);
    }
}
