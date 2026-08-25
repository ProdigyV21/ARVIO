package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f5919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f5921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f5922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f5923e;

    public e(d dVar, d dVar2, d dVar3, d dVar4, d dVar5) {
        this.f5919a = dVar;
        this.f5920b = dVar2;
        this.f5921c = dVar3;
        this.f5922d = dVar4;
        this.f5923e = dVar5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e.class != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return kotlin.jvm.internal.p.a(this.f5919a, eVar.f5919a) && kotlin.jvm.internal.p.a(this.f5920b, eVar.f5920b) && kotlin.jvm.internal.p.a(this.f5921c, eVar.f5921c) && kotlin.jvm.internal.p.a(this.f5922d, eVar.f5922d) && kotlin.jvm.internal.p.a(this.f5923e, eVar.f5923e);
    }

    public final int hashCode() {
        return this.f5923e.hashCode() + androidx.fragment.app.a2.e(this.f5922d, androidx.fragment.app.a2.e(this.f5921c, androidx.fragment.app.a2.e(this.f5920b, this.f5919a.hashCode() * 31, 31), 31), 31);
    }

    public final String toString() {
        return "ButtonBorder(border=" + this.f5919a + ", focusedBorder=" + this.f5920b + ",pressedBorder=" + this.f5921c + ", disabledBorder=" + this.f5922d + ", focusedDisabledBorder=" + this.f5923e + ')';
    }
}
