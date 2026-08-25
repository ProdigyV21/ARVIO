package androidx.fragment.app;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends j0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ j0 f2824i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r f2825l;

    public q(r rVar, j0 j0Var) {
        this.f2825l = rVar;
        this.f2824i = j0Var;
    }

    @Override // androidx.fragment.app.j0
    public final View b(int i10) {
        j0 j0Var = this.f2824i;
        return j0Var.c() ? j0Var.b(i10) : this.f2825l.onFindViewById(i10);
    }

    @Override // androidx.fragment.app.j0
    public final boolean c() {
        return this.f2824i.c() || this.f2825l.onHasView();
    }
}
