package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class e2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f5934a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5935b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f5936c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f5937d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f5938e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f5939f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f5940g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f5941h;

    public e2(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8) {
        this.f5934a = dVar;
        this.f5935b = dVar2;
        this.f5936c = dVar3;
        this.f5937d = dVar4;
        this.f5938e = dVar5;
        this.f5939f = dVar6;
        this.f5940g = dVar7;
        this.f5941h = dVar8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e2.class != obj.getClass()) {
            return false;
        }
        e2 e2Var = (e2) obj;
        if (kotlin.jvm.internal.p.a(this.f5934a, e2Var.f5934a) && kotlin.jvm.internal.p.a(this.f5935b, e2Var.f5935b) && kotlin.jvm.internal.p.a(this.f5936c, e2Var.f5936c) && kotlin.jvm.internal.p.a(this.f5937d, e2Var.f5937d) && kotlin.jvm.internal.p.a(this.f5938e, e2Var.f5938e) && kotlin.jvm.internal.p.a(this.f5939f, e2Var.f5939f) && kotlin.jvm.internal.p.a(this.f5940g, e2Var.f5940g)) {
            return kotlin.jvm.internal.p.a(this.f5941h, e2Var.f5941h);
        }
        return false;
    }

    public final int hashCode() {
        return this.f5941h.hashCode() + androidx.fragment.app.a2.e(this.f5940g, androidx.fragment.app.a2.e(this.f5939f, androidx.fragment.app.a2.e(this.f5938e, androidx.fragment.app.a2.e(this.f5937d, androidx.fragment.app.a2.e(this.f5936c, androidx.fragment.app.a2.e(this.f5935b, this.f5934a.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "NavigationDrawerItemBorder(border=" + this.f5934a + ", focusedBorder=" + this.f5935b + ", pressedBorder=" + this.f5936c + ", selectedBorder=" + this.f5937d + ", disabledBorder=" + this.f5938e + ", focusedSelectedBorder=" + this.f5939f + ", focusedDisabledBorder=" + this.f5940g + ", pressedSelectedBorder=" + this.f5941h + ')';
    }
}
