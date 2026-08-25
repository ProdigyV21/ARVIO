package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f7493c = new f(g.f7499a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((boolean[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        e eVar = (e) obj;
        boolean zT = bVar.t(this.f7505b, i10);
        eVar.b(eVar.d() + 1);
        boolean[] zArr = eVar.f7489a;
        int i11 = eVar.f7490b;
        eVar.f7490b = i11 + 1;
        zArr[i11] = zT;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        boolean[] zArr = (boolean[]) obj;
        e eVar = new e();
        eVar.f7489a = zArr;
        eVar.f7490b = zArr.length;
        eVar.b(10);
        return eVar;
    }

    @Override // cb.g1
    public final Object j() {
        return new boolean[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        boolean[] zArr = (boolean[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.d(this.f7505b, i11, zArr[i11]);
        }
    }
}
