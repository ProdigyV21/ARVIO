package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f5931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f5932b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f5933c;

    public e1(s1 s1Var, s1 s1Var2, s1 s1Var3) {
        this.f5931a = s1Var;
        this.f5932b = s1Var2;
        this.f5933c = s1Var3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e1.class != obj.getClass()) {
            return false;
        }
        e1 e1Var = (e1) obj;
        return kotlin.jvm.internal.p.a(this.f5931a, e1Var.f5931a) && kotlin.jvm.internal.p.a(this.f5932b, e1Var.f5932b) && kotlin.jvm.internal.p.a(this.f5933c, e1Var.f5933c);
    }

    public final int hashCode() {
        return this.f5933c.hashCode() + androidx.fragment.app.a2.f(this.f5932b, this.f5931a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "ClickableSurfaceGlow(glow=" + this.f5931a + ", focusedGlow=" + this.f5932b + ", pressedGlow=" + this.f5933c + ')';
    }
}
