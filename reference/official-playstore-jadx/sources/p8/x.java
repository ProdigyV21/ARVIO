package p8;

/* JADX INFO: loaded from: classes5.dex */
public final class x {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final x f21189c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f21190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f21191b;

    static {
        f9.c cVar = u.f21180a;
        x6.r rVar = x6.r.f22598o;
        v vVar = u.f21183d;
        x6.r rVar2 = vVar.f21186b;
        f0 f0Var = (rVar2 == null || rVar2.f22602n - rVar.f22602n > 0) ? vVar.f21185a : vVar.f21187c;
        z zVar = new z(f0Var, f0Var == f0.WARN ? null : f0Var);
        w wVar = w.f21188i;
        f21189c = new x(zVar);
    }

    public x(z zVar) {
        w wVar = w.f21188i;
        this.f21190a = zVar;
        this.f21191b = zVar.f21195c || wVar.invoke(u.f21180a) == f0.IGNORE;
    }

    public final String toString() {
        return "JavaTypeEnhancementState(jsr305=" + this.f21190a + ", getReportLevelForAnnotation=" + w.f21188i + ')';
    }
}
