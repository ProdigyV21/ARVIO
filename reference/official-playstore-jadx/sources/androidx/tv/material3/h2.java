package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class h2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6048c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f6049d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f6050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f6051f;

    public h2(s1 s1Var, s1 s1Var2, s1 s1Var3, s1 s1Var4, s1 s1Var5, s1 s1Var6) {
        this.f6046a = s1Var;
        this.f6047b = s1Var2;
        this.f6048c = s1Var3;
        this.f6049d = s1Var4;
        this.f6050e = s1Var5;
        this.f6051f = s1Var6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h2.class != obj.getClass()) {
            return false;
        }
        h2 h2Var = (h2) obj;
        if (kotlin.jvm.internal.p.a(this.f6046a, h2Var.f6046a) && kotlin.jvm.internal.p.a(this.f6047b, h2Var.f6047b) && kotlin.jvm.internal.p.a(this.f6048c, h2Var.f6048c) && kotlin.jvm.internal.p.a(this.f6049d, h2Var.f6049d) && kotlin.jvm.internal.p.a(this.f6050e, h2Var.f6050e)) {
            return kotlin.jvm.internal.p.a(this.f6051f, h2Var.f6051f);
        }
        return false;
    }

    public final int hashCode() {
        return this.f6051f.hashCode() + androidx.fragment.app.a2.f(this.f6050e, androidx.fragment.app.a2.f(this.f6049d, androidx.fragment.app.a2.f(this.f6048c, androidx.fragment.app.a2.f(this.f6047b, this.f6046a.hashCode() * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        return "NavigationDrawerItemGlow(glow=" + this.f6046a + ", focusedGlow=" + this.f6047b + ", pressedGlow=" + this.f6048c + ", selectedGlow=" + this.f6049d + ", focusedSelectedGlow=" + this.f6050e + ", pressedSelectedGlow=" + this.f6051f + ')';
    }
}
