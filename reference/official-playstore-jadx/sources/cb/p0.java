package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final p0 f7543c = new p0(q0.f7547a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((long[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        o0 o0Var = (o0) obj;
        long jK = bVar.k(this.f7505b, i10);
        o0Var.b(o0Var.d() + 1);
        long[] jArr = o0Var.f7537a;
        int i11 = o0Var.f7538b;
        o0Var.f7538b = i11 + 1;
        jArr[i11] = jK;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        long[] jArr = (long[]) obj;
        o0 o0Var = new o0();
        o0Var.f7537a = jArr;
        o0Var.f7538b = jArr.length;
        o0Var.b(10);
        return o0Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new long[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        long[] jArr = (long[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.g(this.f7505b, i11, jArr[i11]);
        }
    }
}
