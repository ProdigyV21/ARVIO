package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class s2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f6381c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f6382d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f6383e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f6384f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f6385g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f6386h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d f6387i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d f6388j;

    public s2(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8, d dVar9, d dVar10) {
        this.f6379a = dVar;
        this.f6380b = dVar2;
        this.f6381c = dVar3;
        this.f6382d = dVar4;
        this.f6383e = dVar5;
        this.f6384f = dVar6;
        this.f6385g = dVar7;
        this.f6386h = dVar8;
        this.f6387i = dVar9;
        this.f6388j = dVar10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s2.class != obj.getClass()) {
            return false;
        }
        s2 s2Var = (s2) obj;
        return kotlin.jvm.internal.p.a(this.f6379a, s2Var.f6379a) && kotlin.jvm.internal.p.a(this.f6380b, s2Var.f6380b) && kotlin.jvm.internal.p.a(this.f6381c, s2Var.f6381c) && kotlin.jvm.internal.p.a(this.f6382d, s2Var.f6382d) && kotlin.jvm.internal.p.a(this.f6383e, s2Var.f6383e) && kotlin.jvm.internal.p.a(this.f6384f, s2Var.f6384f) && kotlin.jvm.internal.p.a(this.f6385g, s2Var.f6385g) && kotlin.jvm.internal.p.a(this.f6386h, s2Var.f6386h) && kotlin.jvm.internal.p.a(this.f6387i, s2Var.f6387i) && kotlin.jvm.internal.p.a(this.f6388j, s2Var.f6388j);
    }

    public final int hashCode() {
        return this.f6388j.hashCode() + androidx.fragment.app.a2.e(this.f6387i, androidx.fragment.app.a2.e(this.f6386h, androidx.fragment.app.a2.e(this.f6385g, androidx.fragment.app.a2.e(this.f6384f, androidx.fragment.app.a2.e(this.f6383e, androidx.fragment.app.a2.e(this.f6382d, androidx.fragment.app.a2.e(this.f6381c, androidx.fragment.app.a2.e(this.f6380b, this.f6379a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "SelectableChipBorder(border=" + this.f6379a + ", focusedBorder=" + this.f6380b + ", pressedBorder=" + this.f6381c + ", selectedBorder=" + this.f6382d + ", disabledBorder=" + this.f6383e + ", focusedSelectedBorder=" + this.f6384f + ", focusedDisabledBorder=" + this.f6385g + ", pressedSelectedBorder=" + this.f6386h + ", selectedDisabledBorder=" + this.f6387i + ", focusedSelectedDisabledBorder=" + this.f6388j + ')';
    }
}
