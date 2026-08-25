package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class c2 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2 f7472c = new c2(d2.f7487a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((x6.p0) obj).f22595i.length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        b2 b2Var = (b2) obj;
        long jI = bVar.o(this.f7505b, i10).i();
        b2Var.b(b2Var.d() + 1);
        long[] jArr = b2Var.f7466a;
        int i11 = b2Var.f7467b;
        b2Var.f7467b = i11 + 1;
        jArr[i11] = jI;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        long[] jArr = ((x6.p0) obj).f22595i;
        b2 b2Var = new b2();
        b2Var.f7466a = jArr;
        b2Var.f7467b = jArr.length;
        b2Var.b(10);
        return b2Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new x6.p0(new long[0]);
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        long[] jArr = ((x6.p0) obj).f22595i;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.c(this.f7505b, i11).p(jArr[i11]);
        }
    }
}
