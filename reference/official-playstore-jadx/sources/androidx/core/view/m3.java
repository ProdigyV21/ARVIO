package androidx.core.view;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes3.dex */
public final class m3 extends l3 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final q3 f2291q = q3.c(null, WindowInsets.CONSUMED);

    public m3(q3 q3Var, WindowInsets windowInsets) {
        super(q3Var, windowInsets);
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public final void d(View view) {
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public androidx.core.graphics.c f(int i10) {
        return androidx.core.graphics.c.c(this.f2253c.getInsets(p3.a(i10)));
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public androidx.core.graphics.c g(int i10) {
        return androidx.core.graphics.c.c(this.f2253c.getInsetsIgnoringVisibility(p3.a(i10)));
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public boolean p(int i10) {
        return this.f2253c.isVisible(p3.a(i10));
    }
}
