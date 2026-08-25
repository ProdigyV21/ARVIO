package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class z2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6599b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6600c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f6601d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f6602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f6603f;

    public z2(s1 s1Var, s1 s1Var2, s1 s1Var3, s1 s1Var4, s1 s1Var5, s1 s1Var6) {
        this.f6598a = s1Var;
        this.f6599b = s1Var2;
        this.f6600c = s1Var3;
        this.f6601d = s1Var4;
        this.f6602e = s1Var5;
        this.f6603f = s1Var6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z2.class != obj.getClass()) {
            return false;
        }
        z2 z2Var = (z2) obj;
        return kotlin.jvm.internal.p.a(this.f6598a, z2Var.f6598a) && kotlin.jvm.internal.p.a(this.f6599b, z2Var.f6599b) && kotlin.jvm.internal.p.a(this.f6600c, z2Var.f6600c) && kotlin.jvm.internal.p.a(this.f6601d, z2Var.f6601d) && kotlin.jvm.internal.p.a(this.f6602e, z2Var.f6602e) && kotlin.jvm.internal.p.a(this.f6603f, z2Var.f6603f);
    }

    public final int hashCode() {
        return this.f6603f.hashCode() + androidx.fragment.app.a2.f(this.f6602e, androidx.fragment.app.a2.f(this.f6601d, androidx.fragment.app.a2.f(this.f6600c, androidx.fragment.app.a2.f(this.f6599b, this.f6598a.hashCode() * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableSurfaceGlow(glow=" + this.f6598a + ", focusedGlow=" + this.f6599b + ",pressedGlow=" + this.f6600c + ", selectedGlow=" + this.f6601d + ",focusedSelectedGlow=" + this.f6602e + ", pressedSelectedGlow=" + this.f6603f + ')';
    }
}
