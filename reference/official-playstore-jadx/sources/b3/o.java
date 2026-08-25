package b3;

/* JADX INFO: loaded from: classes4.dex */
public final class o extends w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f7190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f7191b;

    public o(v vVar, u uVar) {
        this.f7190a = vVar;
        this.f7191b = uVar;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof w) {
            w wVar = (w) obj;
            v vVar = this.f7190a;
            if (vVar != null ? vVar.equals(((o) wVar).f7190a) : ((o) wVar).f7190a == null) {
                u uVar = this.f7191b;
                if (uVar != null ? uVar.equals(((o) wVar).f7191b) : ((o) wVar).f7191b == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        v vVar = this.f7190a;
        int iHashCode = ((vVar == null ? 0 : vVar.hashCode()) ^ 1000003) * 1000003;
        u uVar = this.f7191b;
        return (uVar != null ? uVar.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "NetworkConnectionInfo{networkType=" + this.f7190a + ", mobileSubtype=" + this.f7191b + "}";
    }
}
