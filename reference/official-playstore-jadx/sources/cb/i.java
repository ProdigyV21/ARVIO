package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends g1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final i f7515c = new i(j.f7518a);

    @Override // cb.a
    public final int d(Object obj) {
        return ((byte[]) obj).length;
    }

    @Override // cb.t, cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        h hVar = (h) obj;
        byte bE = bVar.E(this.f7505b, i10);
        hVar.b(hVar.d() + 1);
        byte[] bArr = hVar.f7508a;
        int i11 = hVar.f7509b;
        hVar.f7509b = i11 + 1;
        bArr[i11] = bE;
    }

    @Override // cb.a
    public final Object g(Object obj) {
        byte[] bArr = (byte[]) obj;
        h hVar = new h();
        hVar.f7508a = bArr;
        hVar.f7509b = bArr.length;
        hVar.b(10);
        return hVar;
    }

    @Override // cb.g1
    public final Object j() {
        return new byte[0];
    }

    @Override // cb.g1
    public final void k(bb.c cVar, Object obj, int i10) {
        byte[] bArr = (byte[]) obj;
        for (int i11 = 0; i11 < i10; i11++) {
            cVar.n(this.f7505b, i11, bArr[i11]);
        }
    }
}
