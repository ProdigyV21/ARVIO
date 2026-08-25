package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w0 f4666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f4669d;

    public g(w0 w0Var, boolean z, Object obj, boolean z5) {
        if (!w0Var.f4800a && z) {
            throw new IllegalArgumentException(w0Var.b().concat(" does not allow nullable values").toString());
        }
        if (!z && z5 && obj == null) {
            throw new IllegalArgumentException(("Argument with type " + w0Var.b() + " has null value but is not nullable.").toString());
        }
        this.f4666a = w0Var;
        this.f4667b = z;
        this.f4669d = obj;
        this.f4668c = z5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !g.class.equals(obj.getClass())) {
            return false;
        }
        g gVar = (g) obj;
        Object obj2 = gVar.f4669d;
        if (this.f4667b != gVar.f4667b || this.f4668c != gVar.f4668c || !this.f4666a.equals(gVar.f4666a)) {
            return false;
        }
        Object obj3 = this.f4669d;
        return obj3 != null ? obj3.equals(obj2) : obj2 == null;
    }

    public final int hashCode() {
        int iHashCode = ((((this.f4666a.hashCode() * 31) + (this.f4667b ? 1 : 0)) * 31) + (this.f4668c ? 1 : 0)) * 31;
        Object obj = this.f4669d;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(g.class.getSimpleName());
        sb2.append(" Type: " + this.f4666a);
        sb2.append(" Nullable: " + this.f4667b);
        if (this.f4668c) {
            sb2.append(" DefaultValue: " + this.f4669d);
        }
        return sb2.toString();
    }
}
