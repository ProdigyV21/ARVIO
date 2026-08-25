package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f6202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f6203b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f6204c;

    public m(d dVar, d dVar2, d dVar3) {
        this.f6202a = dVar;
        this.f6203b = dVar2;
        this.f6204c = dVar3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return kotlin.jvm.internal.p.a(this.f6202a, mVar.f6202a) && kotlin.jvm.internal.p.a(this.f6203b, mVar.f6203b) && kotlin.jvm.internal.p.a(this.f6204c, mVar.f6204c);
    }

    public final int hashCode() {
        return this.f6204c.hashCode() + androidx.fragment.app.a2.e(this.f6203b, this.f6202a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "CardBorder(border=" + this.f6202a + ", focusedBorder=" + this.f6203b + ", pressedBorder=" + this.f6204c + ')';
    }
}
