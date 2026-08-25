package cb;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends d1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ab.m f7596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x6.i0 f7597m;

    public z(String str, int i10) {
        super(str, null, i10);
        this.f7596l = ab.m.f817b;
        this.f7597m = new x6.i0(new a8.s1(i10, str, this));
    }

    @Override // cb.d1
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ab.g)) {
            return false;
        }
        ab.g gVar = (ab.g) obj;
        return gVar.getKind() == ab.m.f817b && kotlin.jvm.internal.p.a(this.f7477a, gVar.h()) && kotlin.jvm.internal.p.a(b1.b(this), b1.b(gVar));
    }

    @Override // cb.d1, ab.g
    public final ab.g g(int i10) {
        return ((ab.g[]) this.f7597m.getValue())[i10];
    }

    @Override // cb.d1, ab.g
    public final a.a getKind() {
        return this.f7596l;
    }

    @Override // cb.d1
    public final int hashCode() {
        int iHashCode = this.f7477a.hashCode();
        ab.j jVar = new ab.j(this);
        int iHashCode2 = 1;
        while (jVar.hasNext()) {
            int i10 = iHashCode2 * 31;
            String str = (String) jVar.next();
            iHashCode2 = i10 + (str != null ? str.hashCode() : 0);
        }
        return (iHashCode * 31) + iHashCode2;
    }

    @Override // cb.d1
    public final String toString() {
        return kotlin.collections.x.u0(new ab.k(this, 0), ", ", androidx.compose.foundation.c.u(new StringBuilder(), this.f7477a, '('), ")", null, 56);
    }
}
