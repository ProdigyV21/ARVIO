package p8;

/* JADX INFO: loaded from: classes5.dex */
public final class v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f21184d = new v(f0.STRICT, 6);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f21185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x6.r f21186b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f0 f21187c;

    public v(f0 f0Var, x6.r rVar, f0 f0Var2) {
        this.f21185a = f0Var;
        this.f21186b = rVar;
        this.f21187c = f0Var2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f21185a == vVar.f21185a && kotlin.jvm.internal.p.a(this.f21186b, vVar.f21186b) && this.f21187c == vVar.f21187c;
    }

    public final int hashCode() {
        int iHashCode = this.f21185a.hashCode() * 31;
        x6.r rVar = this.f21186b;
        return this.f21187c.hashCode() + ((iHashCode + (rVar == null ? 0 : rVar.f22602n)) * 31);
    }

    public final String toString() {
        return "JavaNullabilityAnnotationsStatus(reportLevelBefore=" + this.f21185a + ", sinceVersion=" + this.f21186b + ", reportLevelAfter=" + this.f21187c + ')';
    }

    public v(f0 f0Var, int i10) {
        this(f0Var, (i10 & 2) != 0 ? new x6.r(1, 0, 0) : null, f0Var);
    }
}
