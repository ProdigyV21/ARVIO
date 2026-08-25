package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class w1 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final w1 f7581c = new w1(x1.f7586a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((x6.l0) obj).f22591i.length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        v1 v1Var = (v1) obj;
        byte bD = bVar.o(this.f7505b, i10).D();
        v1Var.b(v1Var.d() + 1);
        byte[] bArr = v1Var.f7575a;
        int i11 = v1Var.f7576b;
        v1Var.f7576b = i11 + 1;
        bArr[i11] = bD;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        byte[] bArr = ((x6.l0) obj).f22591i;
        v1 v1Var = new v1();
        v1Var.f7575a = bArr;
        v1Var.f7576b = bArr.length;
        v1Var.b(10);
        return v1Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new x6.l0(new byte[0]);
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        byte[] bArr = ((x6.l0) obj).f22591i;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.c(this.f7505b, i11).h(bArr[i11]);
        }
    }
}
