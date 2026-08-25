package eb;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final db.d f15190e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f15191f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f15192g;

    public x(db.b bVar, db.d dVar) {
        super(bVar);
        this.f15190e = dVar;
        this.f15191f = dVar.f14883i.size();
        this.f15192g = -1;
    }

    @Override // eb.b
    public final db.m G(String str) {
        return (db.m) this.f15190e.f14883i.get(Integer.parseInt(str));
    }

    @Override // eb.b
    public final String P(ab.g gVar, int i10) {
        return String.valueOf(i10);
    }

    @Override // eb.b
    public final db.m S() {
        return this.f15190e;
    }

    @Override // bb.b
    public final int w(ab.g gVar) {
        int i10 = this.f15192g;
        if (i10 >= this.f15191f - 1) {
            return -1;
        }
        int i11 = i10 + 1;
        this.f15192g = i11;
        return i11;
    }
}
