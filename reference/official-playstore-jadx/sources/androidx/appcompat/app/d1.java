package androidx.appcompat.app;

import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class d1 implements d0.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1038i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f1 f1039l;

    public d1(f1 f1Var) {
        this.f1039l = f1Var;
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
        if (this.f1038i) {
            return;
        }
        this.f1038i = true;
        f1 f1Var = this.f1039l;
        f1Var.f1042a.n();
        f1Var.f1043b.onPanelClosed(108, qVar);
        this.f1038i = false;
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final boolean c(androidx.appcompat.view.menu.q qVar) {
        this.f1039l.f1043b.onMenuOpened(108, qVar);
        return true;
    }
}
