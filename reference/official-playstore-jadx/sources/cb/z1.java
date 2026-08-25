package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class z1 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final z1 f7599c = new z1(a2.f7459a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((x6.n0) obj).f22593i.length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        y1 y1Var = (y1) obj;
        int iG = bVar.o(this.f7505b, i10).g();
        y1Var.b(y1Var.d() + 1);
        int[] iArr = y1Var.f7594a;
        int i11 = y1Var.f7595b;
        y1Var.f7595b = i11 + 1;
        iArr[i11] = iG;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        int[] iArr = ((x6.n0) obj).f22593i;
        y1 y1Var = new y1();
        y1Var.f7594a = iArr;
        y1Var.f7595b = iArr.length;
        y1Var.b(10);
        return y1Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new x6.n0(new int[0]);
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        int[] iArr = ((x6.n0) obj).f22593i;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.c(this.f7505b, i11).A(iArr[i11]);
        }
    }
}
