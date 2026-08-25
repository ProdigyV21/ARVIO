package xb;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f22760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22761b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f22762c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f22763d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f22764e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public j0 f22765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j0 f22766g;

    public j0() {
        this.f22760a = new byte[8192];
        this.f22764e = true;
        this.f22763d = false;
    }

    public final j0 a() {
        j0 j0Var = this.f22765f;
        j0 j0Var2 = j0Var != this ? j0Var : null;
        j0 j0Var3 = this.f22766g;
        j0Var3.f22765f = j0Var;
        this.f22765f.f22766g = j0Var3;
        this.f22765f = null;
        this.f22766g = null;
        return j0Var2;
    }

    public final void b(j0 j0Var) {
        j0Var.f22766g = this;
        j0Var.f22765f = this.f22765f;
        this.f22765f.f22766g = j0Var;
        this.f22765f = j0Var;
    }

    public final j0 c() {
        this.f22763d = true;
        return new j0(this.f22760a, this.f22761b, this.f22762c, true, false);
    }

    public final void d(j0 j0Var, int i10) {
        boolean z = j0Var.f22764e;
        byte[] bArr = j0Var.f22760a;
        if (!z) {
            throw new IllegalStateException("only owner can write");
        }
        int i11 = j0Var.f22762c;
        int i12 = i11 + i10;
        if (i12 > 8192) {
            if (j0Var.f22763d) {
                throw new IllegalArgumentException();
            }
            int i13 = j0Var.f22761b;
            if (i12 - i13 > 8192) {
                throw new IllegalArgumentException();
            }
            kotlin.collections.r.P(bArr, i13, i11, bArr);
            j0Var.f22762c -= j0Var.f22761b;
            j0Var.f22761b = 0;
        }
        int i14 = j0Var.f22762c;
        int i15 = this.f22761b;
        kotlin.collections.r.L(this.f22760a, i14, i15, bArr, i15 + i10);
        j0Var.f22762c += i10;
        this.f22761b += i10;
    }

    public j0(byte[] bArr, int i10, int i11, boolean z, boolean z5) {
        this.f22760a = bArr;
        this.f22761b = i10;
        this.f22762c = i11;
        this.f22763d = z;
        this.f22764e = z5;
    }
}
