package f8;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.b f15294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f9.b f15295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f9.b f15296c;

    public c(f9.b bVar, f9.b bVar2, f9.b bVar3) {
        this.f15294a = bVar;
        this.f15295b = bVar2;
        this.f15296c = bVar3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return kotlin.jvm.internal.p.a(this.f15294a, cVar.f15294a) && kotlin.jvm.internal.p.a(this.f15295b, cVar.f15295b) && kotlin.jvm.internal.p.a(this.f15296c, cVar.f15296c);
    }

    public final int hashCode() {
        return this.f15296c.hashCode() + ((this.f15295b.hashCode() + (this.f15294a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "PlatformMutabilityMapping(javaClass=" + this.f15294a + ", kotlinReadOnly=" + this.f15295b + ", kotlinMutable=" + this.f15296c + ')';
    }
}
