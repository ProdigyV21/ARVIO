package x8;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f22652a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22653b;

    public h(g gVar, boolean z) {
        this.f22652a = gVar;
        this.f22653b = z;
    }

    public static h a(h hVar, g gVar, boolean z, int i10) {
        if ((i10 & 1) != 0) {
            gVar = hVar.f22652a;
        }
        if ((i10 & 2) != 0) {
            z = hVar.f22653b;
        }
        hVar.getClass();
        return new h(gVar, z);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f22652a == hVar.f22652a && this.f22653b == hVar.f22653b;
    }

    public final int hashCode() {
        return (this.f22652a.hashCode() * 31) + (this.f22653b ? 1231 : 1237);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("NullabilityQualifierWithMigrationStatus(qualifier=");
        sb2.append(this.f22652a);
        sb2.append(", isForWarningOnly=");
        return a0.c.r(sb2, this.f22653b, ')');
    }
}
