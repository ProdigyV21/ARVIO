package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class x2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f6523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f6524d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f6525e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f6526f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f6527g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f6528h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f6529i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d f6530j;

    public x2(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8, d dVar9, d dVar10) {
        this.f6521a = dVar;
        this.f6522b = dVar2;
        this.f6523c = dVar3;
        this.f6524d = dVar4;
        this.f6525e = dVar5;
        this.f6526f = dVar6;
        this.f6527g = dVar7;
        this.f6528h = dVar8;
        this.f6529i = dVar9;
        this.f6530j = dVar10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x2.class != obj.getClass()) {
            return false;
        }
        x2 x2Var = (x2) obj;
        return kotlin.jvm.internal.p.a(this.f6521a, x2Var.f6521a) && kotlin.jvm.internal.p.a(this.f6522b, x2Var.f6522b) && kotlin.jvm.internal.p.a(this.f6523c, x2Var.f6523c) && kotlin.jvm.internal.p.a(this.f6524d, x2Var.f6524d) && kotlin.jvm.internal.p.a(this.f6525e, x2Var.f6525e) && kotlin.jvm.internal.p.a(this.f6526f, x2Var.f6526f) && kotlin.jvm.internal.p.a(this.f6527g, x2Var.f6527g) && kotlin.jvm.internal.p.a(this.f6528h, x2Var.f6528h) && kotlin.jvm.internal.p.a(this.f6529i, x2Var.f6529i) && kotlin.jvm.internal.p.a(this.f6530j, x2Var.f6530j);
    }

    public final int hashCode() {
        return this.f6530j.hashCode() + androidx.fragment.app.a2.e(this.f6529i, androidx.fragment.app.a2.e(this.f6528h, androidx.fragment.app.a2.e(this.f6527g, androidx.fragment.app.a2.e(this.f6526f, androidx.fragment.app.a2.e(this.f6525e, androidx.fragment.app.a2.e(this.f6524d, androidx.fragment.app.a2.e(this.f6523c, androidx.fragment.app.a2.e(this.f6522b, this.f6521a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableSurfaceBorder(border=" + this.f6521a + ", focusedBorder=" + this.f6522b + ",pressedBorder=" + this.f6523c + ", selectedBorder=" + this.f6524d + ",disabledBorder=" + this.f6525e + ", focusedSelectedBorder=" + this.f6526f + ", focusedDisabledBorder=" + this.f6527g + ",pressedSelectedBorder=" + this.f6528h + ", selectedDisabledBorder=" + this.f6529i + ", focusedSelectedDisabledBorder=" + this.f6530j + ')';
    }
}
