package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class g2 extends o2 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d7.d f19593n;

    public g2(d7.j jVar, r7.p pVar) {
        super(jVar, false);
        this.f19593n = t7.a.m(this, this, pVar);
    }

    @Override // ka.f2
    public final void K() {
        try {
            pa.a.g(t7.a.A(this.f19593n), x6.t0.f22605a);
        } catch (Throwable th) {
            resumeWith(new x6.c0(th));
            throw th;
        }
    }
}
