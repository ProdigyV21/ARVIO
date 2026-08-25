package androidx.core.view;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes3.dex */
public class j3 extends i3 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public androidx.core.graphics.c f2277m;

    public j3(q3 q3Var, WindowInsets windowInsets) {
        super(q3Var, windowInsets);
        this.f2277m = null;
    }

    @Override // androidx.core.view.n3
    public q3 b() {
        return q3.c(null, this.f2253c.consumeStableInsets());
    }

    @Override // androidx.core.view.n3
    public q3 c() {
        return q3.c(null, this.f2253c.consumeSystemWindowInsets());
    }

    @Override // androidx.core.view.n3
    public final androidx.core.graphics.c i() {
        if (this.f2277m == null) {
            WindowInsets windowInsets = this.f2253c;
            this.f2277m = androidx.core.graphics.c.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f2277m;
    }

    @Override // androidx.core.view.n3
    public boolean n() {
        return this.f2253c.isConsumed();
    }

    @Override // androidx.core.view.n3
    public void s(androidx.core.graphics.c cVar) {
        this.f2277m = cVar;
    }
}
