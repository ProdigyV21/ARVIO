package gb;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f15758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f15759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ byte[] f15760c;

    public m0(d0 d0Var, int i10, byte[] bArr) {
        this.f15758a = d0Var;
        this.f15759b = i10;
        this.f15760c = bArr;
    }

    @Override // gb.n0
    public final long a() {
        return this.f15759b;
    }

    @Override // gb.n0
    public final d0 b() {
        return this.f15758a;
    }

    @Override // gb.n0
    public final void c(xb.k kVar) {
        kVar.p0(this.f15759b, this.f15760c);
    }
}
