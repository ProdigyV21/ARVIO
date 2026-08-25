package na;

/* JADX INFO: loaded from: classes5.dex */
public final class v0 implements ka.z0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final x0 f20659i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f20660l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f20661m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ka.l f20662n;

    public v0(x0 x0Var, long j10, Object obj, ka.l lVar) {
        this.f20659i = x0Var;
        this.f20660l = j10;
        this.f20661m = obj;
        this.f20662n = lVar;
    }

    @Override // ka.z0
    public final void dispose() {
        x0 x0Var = this.f20659i;
        synchronized (x0Var) {
            if (this.f20660l < x0Var.n()) {
                return;
            }
            Object[] objArr = x0Var.f20679r;
            long j10 = this.f20660l;
            if (objArr[((int) j10) & (objArr.length - 1)] != this) {
                return;
            }
            y0.d(objArr, j10, y0.f20689a);
            x0Var.i();
        }
    }
}
