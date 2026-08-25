package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class c1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f5869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5870b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f5871c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f5872d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f5873e;

    public c1(d dVar, d dVar2, d dVar3, d dVar4, d dVar5) {
        this.f5869a = dVar;
        this.f5870b = dVar2;
        this.f5871c = dVar3;
        this.f5872d = dVar4;
        this.f5873e = dVar5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c1.class != obj.getClass()) {
            return false;
        }
        c1 c1Var = (c1) obj;
        return kotlin.jvm.internal.p.a(this.f5869a, c1Var.f5869a) && kotlin.jvm.internal.p.a(this.f5870b, c1Var.f5870b) && kotlin.jvm.internal.p.a(this.f5871c, c1Var.f5871c) && kotlin.jvm.internal.p.a(this.f5872d, c1Var.f5872d) && kotlin.jvm.internal.p.a(this.f5873e, c1Var.f5873e);
    }

    public final int hashCode() {
        return this.f5873e.hashCode() + androidx.fragment.app.a2.e(this.f5872d, androidx.fragment.app.a2.e(this.f5871c, androidx.fragment.app.a2.e(this.f5870b, this.f5869a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ClickableSurfaceBorder(border=" + this.f5869a + ", focusedBorder=" + this.f5870b + ", pressedBorder=" + this.f5871c + ", disabledBorder=" + this.f5872d + ", focusedDisabledBorder=" + this.f5873e + ')';
    }
}
