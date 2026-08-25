package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class u2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6441b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6442c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f6443d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f6444e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f6445f;

    public u2(s1 s1Var, s1 s1Var2, s1 s1Var3, s1 s1Var4, s1 s1Var5, s1 s1Var6) {
        this.f6440a = s1Var;
        this.f6441b = s1Var2;
        this.f6442c = s1Var3;
        this.f6443d = s1Var4;
        this.f6444e = s1Var5;
        this.f6445f = s1Var6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || u2.class != obj.getClass()) {
            return false;
        }
        u2 u2Var = (u2) obj;
        return kotlin.jvm.internal.p.a(this.f6440a, u2Var.f6440a) && kotlin.jvm.internal.p.a(this.f6441b, u2Var.f6441b) && kotlin.jvm.internal.p.a(this.f6442c, u2Var.f6442c) && kotlin.jvm.internal.p.a(this.f6443d, u2Var.f6443d) && kotlin.jvm.internal.p.a(this.f6444e, u2Var.f6444e) && kotlin.jvm.internal.p.a(this.f6445f, u2Var.f6445f);
    }

    public final int hashCode() {
        return this.f6445f.hashCode() + androidx.fragment.app.a2.f(this.f6444e, androidx.fragment.app.a2.f(this.f6443d, androidx.fragment.app.a2.f(this.f6442c, androidx.fragment.app.a2.f(this.f6441b, this.f6440a.hashCode() * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableChipGlow(glow=" + this.f6440a + ", focusedGlow=" + this.f6441b + ", pressedGlow=" + this.f6442c + ", selectedGlow=" + this.f6443d + ", focusedSelectedGlow=" + this.f6444e + ", pressedSelectedGlow=" + this.f6445f + ')';
    }
}
