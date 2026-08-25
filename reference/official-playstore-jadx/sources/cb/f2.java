package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class f2 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f2 f7498c = new f2(g2.f7506a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((x6.s0) obj).f22604i.length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        e2 e2Var = (e2) obj;
        short sM = bVar.o(this.f7505b, i10).m();
        e2Var.b(e2Var.d() + 1);
        short[] sArr = e2Var.f7491a;
        int i11 = e2Var.f7492b;
        e2Var.f7492b = i11 + 1;
        sArr[i11] = sM;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        short[] sArr = ((x6.s0) obj).f22604i;
        e2 e2Var = new e2();
        e2Var.f7491a = sArr;
        e2Var.f7492b = sArr.length;
        e2Var.b(10);
        return e2Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new x6.s0(new short[0]);
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        short[] sArr = ((x6.s0) obj).f22604i;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.c(this.f7505b, i11).s(sArr[i11]);
        }
    }
}
