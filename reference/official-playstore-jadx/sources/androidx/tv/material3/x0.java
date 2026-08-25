package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6504b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f6505c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f6506d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f6507e;

    public x0(d dVar, d dVar2, d dVar3, d dVar4, d dVar5) {
        this.f6503a = dVar;
        this.f6504b = dVar2;
        this.f6505c = dVar3;
        this.f6506d = dVar4;
        this.f6507e = dVar5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x0.class != obj.getClass()) {
            return false;
        }
        x0 x0Var = (x0) obj;
        return kotlin.jvm.internal.p.a(this.f6503a, x0Var.f6503a) && kotlin.jvm.internal.p.a(this.f6504b, x0Var.f6504b) && kotlin.jvm.internal.p.a(this.f6505c, x0Var.f6505c) && kotlin.jvm.internal.p.a(this.f6506d, x0Var.f6506d) && kotlin.jvm.internal.p.a(this.f6507e, x0Var.f6507e);
    }

    public final int hashCode() {
        return this.f6507e.hashCode() + androidx.fragment.app.a2.e(this.f6506d, androidx.fragment.app.a2.e(this.f6505c, androidx.fragment.app.a2.e(this.f6504b, this.f6503a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ClickableChipBorder(border=" + this.f6503a + ", focusedBorder=" + this.f6504b + ", pressedBorder=" + this.f6505c + ", disabledBorder=" + this.f6506d + ", focusedDisabledBorder=" + this.f6507e + ')';
    }
}
