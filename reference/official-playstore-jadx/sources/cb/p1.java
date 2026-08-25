package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class p1 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p1 f7544c = new p1(q1.f7549a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((short[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        o1 o1Var = (o1) obj;
        short sB = bVar.B(this.f7505b, i10);
        o1Var.b(o1Var.d() + 1);
        short[] sArr = o1Var.f7539a;
        int i11 = o1Var.f7540b;
        o1Var.f7540b = i11 + 1;
        sArr[i11] = sB;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        short[] sArr = (short[]) obj;
        o1 o1Var = new o1();
        o1Var.f7539a = sArr;
        o1Var.f7540b = sArr.length;
        o1Var.b(10);
        return o1Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new short[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        short[] sArr = (short[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.D(this.f7505b, i11, sArr[i11]);
        }
    }
}
