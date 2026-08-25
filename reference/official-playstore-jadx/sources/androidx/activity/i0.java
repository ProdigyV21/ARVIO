package androidx.activity;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements androidx.lifecycle.w, d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.lifecycle.r f954i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d0 f955l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j0 f956m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l0 f957n;

    public i0(l0 l0Var, androidx.lifecycle.r rVar, d0 d0Var) {
        this.f957n = l0Var;
        this.f954i = rVar;
        this.f955l = d0Var;
        rVar.addObserver(this);
    }

    @Override // androidx.activity.d
    public final void cancel() {
        this.f954i.removeObserver(this);
        this.f955l.f932b.remove(this);
        j0 j0Var = this.f956m;
        if (j0Var != null) {
            j0Var.cancel();
        }
        this.f956m = null;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        if (pVar != androidx.lifecycle.p.ON_START) {
            if (pVar != androidx.lifecycle.p.ON_STOP) {
                if (pVar == androidx.lifecycle.p.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                j0 j0Var = this.f956m;
                if (j0Var != null) {
                    j0Var.cancel();
                    return;
                }
                return;
            }
        }
        l0 l0Var = this.f957n;
        kotlin.collections.p pVar2 = l0Var.f964b;
        d0 d0Var = this.f955l;
        pVar2.addLast(d0Var);
        j0 j0Var2 = new j0(l0Var, d0Var);
        d0Var.f932b.add(j0Var2);
        l0Var.e();
        d0Var.f933c = new k0(0, l0Var, l0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 1);
        this.f956m = j0Var2;
    }
}
