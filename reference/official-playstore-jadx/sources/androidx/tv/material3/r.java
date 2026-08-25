package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6339b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6340c;

    public r(s1 s1Var, s1 s1Var2, s1 s1Var3) {
        this.f6338a = s1Var;
        this.f6339b = s1Var2;
        this.f6340c = s1Var3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r.class != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        return kotlin.jvm.internal.p.a(this.f6338a, rVar.f6338a) && kotlin.jvm.internal.p.a(this.f6339b, rVar.f6339b) && kotlin.jvm.internal.p.a(this.f6340c, rVar.f6340c);
    }

    public final int hashCode() {
        return this.f6340c.hashCode() + androidx.fragment.app.a2.f(this.f6339b, this.f6338a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "CardGlow(glow=" + this.f6338a + ", focusedGlow=" + this.f6339b + ", pressedGlow=" + this.f6340c + ')';
    }
}
