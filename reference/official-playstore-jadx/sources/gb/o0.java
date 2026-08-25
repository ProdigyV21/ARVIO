package gb;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k0 f15762a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i0 f15763b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f15765d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y f15766e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s0 f15768g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p0 f15769h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public p0 f15770i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public p0 f15771j;
    public long k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f15772l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public com.google.android.gms.common.api.internal.g0 f15773m;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f15764c = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public z f15767f = new z();

    public static void b(p0 p0Var, String str) {
        if (p0Var != null) {
            if (p0Var.f15786q != null) {
                throw new IllegalArgumentException(str.concat(".body != null").toString());
            }
            if (p0Var.f15787r != null) {
                throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
            }
            if (p0Var.f15788s != null) {
                throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
            }
            if (p0Var.f15789t != null) {
                throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
            }
        }
    }

    public final p0 a() {
        int i10 = this.f15764c;
        if (i10 < 0) {
            throw new IllegalStateException(("code < 0: " + this.f15764c).toString());
        }
        k0 k0Var = this.f15762a;
        if (k0Var == null) {
            throw new IllegalStateException("request == null");
        }
        i0 i0Var = this.f15763b;
        if (i0Var == null) {
            throw new IllegalStateException("protocol == null");
        }
        String str = this.f15765d;
        if (str != null) {
            return new p0(k0Var, i0Var, str, i10, this.f15766e, this.f15767f.e(), this.f15768g, this.f15769h, this.f15770i, this.f15771j, this.k, this.f15772l, this.f15773m);
        }
        throw new IllegalStateException("message == null");
    }
}
