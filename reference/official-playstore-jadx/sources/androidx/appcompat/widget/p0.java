package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends k2 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ y0 f1711t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ c1 f1712u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(c1 c1Var, c1 c1Var2, y0 y0Var) {
        super(c1Var2);
        this.f1712u = c1Var;
        this.f1711t = y0Var;
    }

    @Override // androidx.appcompat.widget.k2
    public final androidx.appcompat.view.menu.g0 b() {
        return this.f1711t;
    }

    @Override // androidx.appcompat.widget.k2
    public final boolean c() {
        c1 c1Var = this.f1712u;
        if (c1Var.getInternalPopup().a()) {
            return true;
        }
        c1Var.f1533p.j(s0.b(c1Var), s0.a(c1Var));
        return true;
    }
}
