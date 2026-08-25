package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f4727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4729c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f4730d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f4731e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4732f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4733g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f4734h;

    public n0(boolean z, boolean z5, int i10, boolean z10, boolean z11, int i11, int i12) {
        this.f4727a = z;
        this.f4728b = z5;
        this.f4729c = i10;
        this.f4730d = z10;
        this.f4731e = z11;
        this.f4732f = i11;
        this.f4733g = i12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof n0)) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return this.f4727a == n0Var.f4727a && this.f4728b == n0Var.f4728b && this.f4729c == n0Var.f4729c && kotlin.jvm.internal.p.a(this.f4734h, n0Var.f4734h) && this.f4730d == n0Var.f4730d && this.f4731e == n0Var.f4731e && this.f4732f == n0Var.f4732f && this.f4733g == n0Var.f4733g;
    }

    public final int hashCode() {
        int i10 = (((((this.f4727a ? 1 : 0) * 31) + (this.f4728b ? 1 : 0)) * 31) + this.f4729c) * 31;
        return ((((((((((((i10 + (this.f4734h != null ? r1.hashCode() : 0)) * 31) + (this.f4730d ? 1 : 0)) * 31) + (this.f4731e ? 1 : 0)) * 31) + this.f4732f) * 31) + this.f4733g) * 31) - 1) * 31) - 1;
    }

    public final String toString() {
        String str = this.f4734h;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(n0.class.getSimpleName());
        sb2.append("(");
        if (this.f4727a) {
            sb2.append("launchSingleTop ");
        }
        if (this.f4728b) {
            sb2.append("restoreState ");
        }
        if ((str != null || this.f4729c != -1) && str != null) {
            sb2.append("popUpTo(");
            sb2.append(str);
            if (this.f4730d) {
                sb2.append(" inclusive");
            }
            if (this.f4731e) {
                sb2.append(" saveState");
            }
            sb2.append(")");
        }
        int i10 = this.f4733g;
        int i11 = this.f4732f;
        if (i11 != -1 || i10 != -1) {
            sb2.append("anim(enterAnim=0x");
            sb2.append(Integer.toHexString(i11));
            sb2.append(" exitAnim=0x");
            sb2.append(Integer.toHexString(i10));
            sb2.append(" popEnterAnim=0x");
            sb2.append(Integer.toHexString(-1));
            sb2.append(" popExitAnim=0x");
            sb2.append(Integer.toHexString(-1));
            sb2.append(")");
        }
        return sb2.toString();
    }
}
