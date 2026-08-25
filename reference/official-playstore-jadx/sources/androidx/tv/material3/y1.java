package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s1 f6551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f6552b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f6553c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s1 f6554d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final s1 f6555e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final s1 f6556f;

    public y1(s1 s1Var, s1 s1Var2, s1 s1Var3, s1 s1Var4, s1 s1Var5, s1 s1Var6) {
        this.f6551a = s1Var;
        this.f6552b = s1Var2;
        this.f6553c = s1Var3;
        this.f6554d = s1Var4;
        this.f6555e = s1Var5;
        this.f6556f = s1Var6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || y1.class != obj.getClass()) {
            return false;
        }
        y1 y1Var = (y1) obj;
        return kotlin.jvm.internal.p.a(this.f6551a, y1Var.f6551a) && kotlin.jvm.internal.p.a(this.f6552b, y1Var.f6552b) && kotlin.jvm.internal.p.a(this.f6553c, y1Var.f6553c) && kotlin.jvm.internal.p.a(this.f6554d, y1Var.f6554d) && kotlin.jvm.internal.p.a(this.f6555e, y1Var.f6555e) && kotlin.jvm.internal.p.a(this.f6556f, y1Var.f6556f);
    }

    public final int hashCode() {
        return this.f6556f.hashCode() + androidx.fragment.app.a2.f(this.f6555e, androidx.fragment.app.a2.f(this.f6554d, androidx.fragment.app.a2.f(this.f6553c, androidx.fragment.app.a2.f(this.f6552b, this.f6551a.hashCode() * 31, 31), 31), 31), 31);
    }

    public final String toString() {
        return "ListItemGlow(glow=" + this.f6551a + ", focusedGlow=" + this.f6552b + ", pressedGlow=" + this.f6553c + ", selectedGlow=" + this.f6554d + ", focusedSelectedGlow=" + this.f6555e + ", pressedSelectedGlow=" + this.f6556f + ')';
    }
}
