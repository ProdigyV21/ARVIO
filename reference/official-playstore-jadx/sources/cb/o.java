package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f7536c = new o(p.f7541a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((char[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        n nVar = (n) obj;
        char cY = bVar.y(this.f7505b, i10);
        nVar.b(nVar.d() + 1);
        char[] cArr = nVar.f7533a;
        int i11 = nVar.f7534b;
        nVar.f7534b = i11 + 1;
        cArr[i11] = cY;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        char[] cArr = (char[]) obj;
        n nVar = new n();
        nVar.f7533a = cArr;
        nVar.f7534b = cArr.length;
        nVar.b(10);
        return nVar;
    }

    @Override // cb.g1
    public final Object j() {
        return new char[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        char[] cArr = (char[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.o(this.f7505b, i11, cArr[i11]);
        }
    }
}
