package androidx.appcompat.app;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.x1;
import androidx.core.view.n2;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements ContentFrameLayout.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s0 f1034a;

    public c0(s0 s0Var) {
        this.f1034a = s0Var;
    }

    @Override // androidx.appcompat.widget.ContentFrameLayout.a
    public final void onDetachedFromWindow() {
        s0 s0Var = this.f1034a;
        x1 x1Var = s0Var.B;
        if (x1Var != null) {
            x1Var.i();
        }
        if (s0Var.G != null) {
            s0Var.f1190v.getDecorView().removeCallbacks(s0Var.H);
            if (s0Var.G.isShowing()) {
                try {
                    s0Var.G.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            s0Var.G = null;
        }
        n2 n2Var = s0Var.I;
        if (n2Var != null) {
            n2Var.b();
        }
        androidx.appcompat.view.menu.q qVar = s0Var.B(0).f1158h;
        if (qVar != null) {
            qVar.c(true);
        }
    }
}
