package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6583c;

    public z0(s1 s1Var, s1 s1Var2, s1 s1Var3) {
        this.f6581a = s1Var;
        this.f6582b = s1Var2;
        this.f6583c = s1Var3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z0.class != obj.getClass()) {
            return false;
        }
        z0 z0Var = (z0) obj;
        return kotlin.jvm.internal.p.a(this.f6581a, z0Var.f6581a) && kotlin.jvm.internal.p.a(this.f6582b, z0Var.f6582b) && kotlin.jvm.internal.p.a(this.f6583c, z0Var.f6583c);
    }

    public final int hashCode() {
        return this.f6583c.hashCode() + androidx.fragment.app.a2.f(this.f6582b, this.f6581a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "ClickableChipGlow(glow=" + this.f6581a + ", focusedGlow=" + this.f6582b + ", pressedGlow=" + this.f6583c + ')';
    }
}
