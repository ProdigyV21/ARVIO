package androidx.core.view;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes3.dex */
public class l3 extends k3 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public androidx.core.graphics.c f2280n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public androidx.core.graphics.c f2281o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public androidx.core.graphics.c f2282p;

    public l3(q3 q3Var, WindowInsets windowInsets) {
        super(q3Var, windowInsets);
        this.f2280n = null;
        this.f2281o = null;
        this.f2282p = null;
    }

    @Override // androidx.core.view.n3
    public androidx.core.graphics.c h() {
        if (this.f2281o == null) {
            this.f2281o = androidx.core.graphics.c.c(this.f2253c.getMandatorySystemGestureInsets());
        }
        return this.f2281o;
    }

    @Override // androidx.core.view.n3
    public androidx.core.graphics.c j() {
        if (this.f2280n == null) {
            this.f2280n = androidx.core.graphics.c.c(this.f2253c.getSystemGestureInsets());
        }
        return this.f2280n;
    }

    @Override // androidx.core.view.n3
    public androidx.core.graphics.c l() {
        if (this.f2282p == null) {
            this.f2282p = androidx.core.graphics.c.c(this.f2253c.getTappableElementInsets());
        }
        return this.f2282p;
    }

    @Override // androidx.core.view.i3, androidx.core.view.n3
    public q3 m(int i10, int i11, int i12, int i13) {
        return q3.c(null, this.f2253c.inset(i10, i11, i12, i13));
    }

    @Override // androidx.core.view.j3, androidx.core.view.n3
    public void s(androidx.core.graphics.c cVar) {
    }
}
