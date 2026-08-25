package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f3309i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final w0 f3310l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3311m;

    public x0(String str, w0 w0Var) {
        this.f3309i = str;
        this.f3310l = w0Var;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        if (pVar == p.ON_DESTROY) {
            this.f3311m = false;
            yVar.getLifecycle().removeObserver(this);
        }
    }
}
