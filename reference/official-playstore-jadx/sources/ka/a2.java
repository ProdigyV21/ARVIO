package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class a2 extends y1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f2 f19546l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b2 f19547m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final p f19548n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Object f19549o;

    public a2(f2 f2Var, b2 b2Var, p pVar, Object obj) {
        this.f19546l = f2Var;
        this.f19547m = b2Var;
        this.f19548n = pVar;
        this.f19549o = obj;
    }

    @Override // ka.y1
    public final void invoke(Throwable th) {
        p pVarH = f2.H(this.f19548n);
        f2 f2Var = this.f19546l;
        b2 b2Var = this.f19547m;
        Object obj = this.f19549o;
        if (pVarH != null) {
            while (pVarH.f19627l.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new a2(f2Var, b2Var, pVarH, obj)) == k2.f19604i) {
                pVarH = f2.H(pVarH);
                if (pVarH == null) {
                }
            }
            return;
        }
        f2Var.g(f2Var.t(b2Var, obj));
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x6.t0.f22605a;
    }
}
