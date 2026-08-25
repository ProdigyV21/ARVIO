package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final v f7573c = new v(w.f7577a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((double[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        u uVar = (u) obj;
        double dA = bVar.A(this.f7505b, i10);
        uVar.b(uVar.d() + 1);
        double[] dArr = uVar.f7567a;
        int i11 = uVar.f7568b;
        uVar.f7568b = i11 + 1;
        dArr[i11] = dA;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        double[] dArr = (double[]) obj;
        u uVar = new u();
        uVar.f7567a = dArr;
        uVar.f7568b = dArr.length;
        uVar.b(10);
        return uVar;
    }

    @Override // cb.g1
    public final Object j() {
        return new double[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        double[] dArr = (double[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.u(this.f7505b, i11, dArr[i11]);
        }
    }
}
