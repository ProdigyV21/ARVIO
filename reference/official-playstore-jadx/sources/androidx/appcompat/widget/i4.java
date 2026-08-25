package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i4 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1600i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1601l;

    public /* synthetic */ i4(Toolbar toolbar, int i10) {
        this.f1600i = i10;
        this.f1601l = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1600i) {
            case 0:
                l4 l4Var = this.f1601l.W;
                androidx.appcompat.view.menu.t tVar = l4Var == null ? null : l4Var.f1657l;
                if (tVar != null) {
                    tVar.collapseActionView();
                }
                break;
            default:
                this.f1601l.m();
                break;
        }
    }
}
