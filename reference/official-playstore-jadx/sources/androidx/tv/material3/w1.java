package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class w1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6474b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f6475c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f6476d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f6477e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f6478f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f6479g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f6480h;

    public w1(d dVar, d dVar2, d dVar3, d dVar4, d dVar5, d dVar6, d dVar7, d dVar8) {
        this.f6473a = dVar;
        this.f6474b = dVar2;
        this.f6475c = dVar3;
        this.f6476d = dVar4;
        this.f6477e = dVar5;
        this.f6478f = dVar6;
        this.f6479g = dVar7;
        this.f6480h = dVar8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w1.class != obj.getClass()) {
            return false;
        }
        w1 w1Var = (w1) obj;
        return kotlin.jvm.internal.p.a(this.f6473a, w1Var.f6473a) && kotlin.jvm.internal.p.a(this.f6474b, w1Var.f6474b) && kotlin.jvm.internal.p.a(this.f6475c, w1Var.f6475c) && kotlin.jvm.internal.p.a(this.f6476d, w1Var.f6476d) && kotlin.jvm.internal.p.a(this.f6477e, w1Var.f6477e) && kotlin.jvm.internal.p.a(this.f6478f, w1Var.f6478f) && kotlin.jvm.internal.p.a(this.f6479g, w1Var.f6479g) && kotlin.jvm.internal.p.a(this.f6480h, w1Var.f6480h);
    }

    public final int hashCode() {
        return this.f6480h.hashCode() + androidx.fragment.app.a2.e(this.f6479g, androidx.fragment.app.a2.e(this.f6478f, androidx.fragment.app.a2.e(this.f6477e, androidx.fragment.app.a2.e(this.f6476d, androidx.fragment.app.a2.e(this.f6475c, androidx.fragment.app.a2.e(this.f6474b, this.f6473a.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    public final String toString() {
        return "ListItemBorder(border=" + this.f6473a + ", focusedBorder=" + this.f6474b + ", pressedBorder=" + this.f6475c + ", selectedBorder=" + this.f6476d + ", disabledBorder=" + this.f6477e + ", focusedSelectedBorder=" + this.f6478f + ", focusedDisabledBorder=" + this.f6479g + ", pressedSelectedBorder=" + this.f6480h + ')';
    }
}
