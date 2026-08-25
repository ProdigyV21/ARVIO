package kotlin.collections;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 extends b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19703m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19704n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ p0 f19705o;

    public o0(p0 p0Var) {
        this.f19705o = p0Var;
        this.f19703m = p0Var.size();
        this.f19704n = p0Var.f19712m;
    }

    @Override // kotlin.collections.b
    public final void a() {
        int i10 = this.f19703m;
        if (i10 == 0) {
            this.f19684i = 2;
            return;
        }
        p0 p0Var = this.f19705o;
        Object[] objArr = p0Var.f19710i;
        int i11 = this.f19704n;
        this.f19685l = objArr[i11];
        this.f19684i = 1;
        this.f19704n = (i11 + 1) % p0Var.f19711l;
        this.f19703m = i10 - 1;
    }
}
