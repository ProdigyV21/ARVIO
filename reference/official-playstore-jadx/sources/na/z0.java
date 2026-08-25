package na;

/* JADX INFO: loaded from: classes5.dex */
public final class z0 extends oa.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f20696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ka.l f20697b;

    @Override // oa.c
    public final boolean a(oa.a aVar) {
        x0 x0Var = (x0) aVar;
        if (this.f20696a >= 0) {
            return false;
        }
        long j10 = x0Var.f20680s;
        if (j10 < x0Var.f20681t) {
            x0Var.f20681t = j10;
        }
        this.f20696a = j10;
        return true;
    }

    @Override // oa.c
    public final d7.d[] b(oa.a aVar) {
        long j10 = this.f20696a;
        this.f20696a = -1L;
        this.f20697b = null;
        return ((x0) aVar).t(j10);
    }
}
