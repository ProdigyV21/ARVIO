package androidx.fragment.app;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class u extends j0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c0 f2844i;

    public u(c0 c0Var) {
        this.f2844i = c0Var;
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i10) {
        c0 c0Var = this.f2844i;
        View view = c0Var.mView;
        if (view != null) {
            return view.findViewById(i10);
        }
        throw new IllegalStateException("Fragment " + c0Var + " does not have a view");
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        return this.f2844i.mView != null;
    }
}
