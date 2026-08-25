package kotlin.text;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19943a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x7.i f19944b;

    public f(String str, x7.i iVar) {
        this.f19943a = str;
        this.f19944b = iVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return kotlin.jvm.internal.p.a(this.f19943a, fVar.f19943a) && kotlin.jvm.internal.p.a(this.f19944b, fVar.f19944b);
    }

    public final int hashCode() {
        return this.f19944b.hashCode() + (this.f19943a.hashCode() * 31);
    }

    public final String toString() {
        return "MatchGroup(value=" + this.f19943a + ", range=" + this.f19944b + ')';
    }
}
