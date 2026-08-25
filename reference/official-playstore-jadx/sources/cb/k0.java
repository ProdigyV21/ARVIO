package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k0 f7525c = new k0(l0.f7529a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((int[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        j0 j0Var = (j0) obj;
        int iC = bVar.c(this.f7505b, i10);
        j0Var.b(j0Var.d() + 1);
        int[] iArr = j0Var.f7520a;
        int i11 = j0Var.f7521b;
        j0Var.f7521b = i11 + 1;
        iArr[i11] = iC;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        int[] iArr = (int[]) obj;
        j0 j0Var = new j0();
        j0Var.f7520a = iArr;
        j0Var.f7521b = iArr.length;
        j0Var.b(10);
        return j0Var;
    }

    @Override // cb.g1
    public final Object j() {
        return new int[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        int[] iArr = (int[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.r(i11, iArr[i11], this.f7505b);
        }
    }
}
