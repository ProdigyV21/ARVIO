package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g8.b1 f22243a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u8.a f22244b;

    public q0(g8.b1 b1Var, u8.a aVar) {
        this.f22243a = b1Var;
        this.f22244b = aVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof q0)) {
            return false;
        }
        q0 q0Var = (q0) obj;
        return kotlin.jvm.internal.p.a(q0Var.f22243a, this.f22243a) && kotlin.jvm.internal.p.a(q0Var.f22244b, this.f22244b);
    }

    public final int hashCode() {
        int iHashCode = this.f22243a.hashCode();
        return this.f22244b.hashCode() + (iHashCode * 31) + iHashCode;
    }

    public final String toString() {
        return "DataToEraseUpperBound(typeParameter=" + this.f22243a + ", typeAttr=" + this.f22244b + ')';
    }
}
