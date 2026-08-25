package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m0 f22225b = new m0(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final m0 f22226c = new m0(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m0 f22227d = new m0(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22228a;

    public /* synthetic */ m0(int i10) {
        this.f22228a = i10;
    }

    @Override // v9.c
    public final y9.i u(n0 n0Var, y9.h hVar) {
        switch (this.f22228a) {
            case 0:
                return n0Var.f22231c.M(hVar);
            case 1:
                throw new UnsupportedOperationException("Should not be called");
            default:
                return n0Var.f22231c.Q(hVar);
        }
    }
}
