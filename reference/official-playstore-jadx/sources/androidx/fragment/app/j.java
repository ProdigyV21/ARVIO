package androidx.fragment.app;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2743i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2744l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f2745m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f2746n;

    public j(androidx.lifecycle.a0 a0Var, androidx.lifecycle.p pVar) {
        this.f2745m = a0Var;
        this.f2746n = pVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i10 = this.f2743i;
        Object obj = this.f2746n;
        Object obj2 = this.f2745m;
        switch (i10) {
            case 0:
                c0 c0Var = ((z1) obj2).f2892c;
                c0 c0Var2 = ((z1) obj).f2892c;
                boolean z = this.f2744l;
                u1 u1Var = p1.f2822a;
                if (!z) {
                    c0Var.getEnterTransitionCallback();
                } else {
                    c0Var2.getEnterTransitionCallback();
                }
                break;
            default:
                if (!this.f2744l) {
                    ((androidx.lifecycle.a0) obj2).c((androidx.lifecycle.p) obj);
                    this.f2744l = true;
                }
                break;
        }
    }

    public j(z1 z1Var, z1 z1Var2, boolean z, t.e eVar) {
        this.f2745m = z1Var;
        this.f2746n = z1Var2;
        this.f2744l = z;
    }
}
