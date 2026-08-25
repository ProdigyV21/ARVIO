package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c0 f7469c = new c0(d0.f7475a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((float[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        b0 b0Var = (b0) obj;
        float fL = bVar.l(this.f7505b, i10);
        b0Var.b(b0Var.d() + 1);
        float[] fArr = b0Var.f7461a;
        int i11 = b0Var.f7462b;
        b0Var.f7462b = i11 + 1;
        fArr[i11] = fL;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        float[] fArr = (float[]) obj;
        b0 b0Var = new b0();
        b0Var.f7461a = fArr;
        b0Var.f7462b = fArr.length;
        b0Var.b(10);
        return b0Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new float[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        float[] fArr = (float[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.B(this.f7505b, i11, fArr[i11]);
        }
    }
}
