package androidx.fragment.app;

import android.transition.Transition;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2776c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f2777d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f2778e;

    public m(z1 z1Var, androidx.core.os.g gVar, boolean z, boolean z5) {
        super(z1Var, gVar);
        int i10 = z1Var.f2890a;
        c0 c0Var = z1Var.f2892c;
        if (i10 == 2) {
            this.f2776c = z ? c0Var.getReenterTransition() : c0Var.getEnterTransition();
            this.f2777d = z ? c0Var.getAllowReturnTransitionOverlap() : c0Var.getAllowEnterTransitionOverlap();
        } else {
            this.f2776c = z ? c0Var.getReturnTransition() : c0Var.getExitTransition();
            this.f2777d = true;
        }
        if (!z5) {
            this.f2778e = null;
        } else if (z) {
            this.f2778e = c0Var.getSharedElementReturnTransition();
        } else {
            this.f2778e = c0Var.getSharedElementEnterTransition();
        }
    }

    public final u1 c(Object obj) {
        if (obj == null) {
            return null;
        }
        u1 u1Var = p1.f2822a;
        if (obj instanceof Transition) {
            return u1Var;
        }
        u1 u1Var2 = p1.f2823b;
        if (u1Var2 != null) {
            u1Var2.getClass();
            if (obj instanceof Transition) {
                return u1Var2;
            }
        }
        throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.f2768a.f2892c + " is not a valid framework Transition or AndroidX Transition");
    }
}
