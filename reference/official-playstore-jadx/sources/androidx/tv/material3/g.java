package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f5994a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f5995b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f5996c;

    public g(s1 s1Var, s1 s1Var2, s1 s1Var3) {
        this.f5994a = s1Var;
        this.f5995b = s1Var2;
        this.f5996c = s1Var3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return kotlin.jvm.internal.p.a(this.f5994a, gVar.f5994a) && kotlin.jvm.internal.p.a(this.f5995b, gVar.f5995b) && kotlin.jvm.internal.p.a(this.f5996c, gVar.f5996c);
    }

    public final int hashCode() {
        return this.f5996c.hashCode() + androidx.fragment.app.a2.f(this.f5995b, this.f5994a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "ButtonGlow(glow=" + this.f5994a + ", focusedGlow=" + this.f5995b + ", pressedGlow=" + this.f5996c + ')';
    }
}
