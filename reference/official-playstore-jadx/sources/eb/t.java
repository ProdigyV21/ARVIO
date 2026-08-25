package eb;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final db.m f15175e;

    public t(db.b bVar, db.m mVar) {
        super(bVar);
        this.f15175e = mVar;
        this.f15107a.add("primitive");
    }

    @Override // eb.b
    public final db.m G(String str) {
        if (str == "primitive") {
            return this.f15175e;
        }
        throw new IllegalArgumentException("This input can only handle primitives with 'primitive' tag");
    }

    @Override // eb.b
    public final db.m S() {
        return this.f15175e;
    }

    @Override // bb.b
    public final int w(ab.g gVar) {
        return 0;
    }
}
