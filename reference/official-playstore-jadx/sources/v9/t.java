package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends v0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g8.b1[] f22247b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r0[] f22248c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f22249d;

    public t(g8.b1[] b1VarArr, r0[] r0VarArr, boolean z) {
        this.f22247b = b1VarArr;
        this.f22248c = r0VarArr;
        this.f22249d = z;
    }

    @Override // v9.v0
    public final boolean b() {
        return this.f22249d;
    }

    @Override // v9.v0
    public final r0 d(w wVar) {
        g8.h hVarE = wVar.I0().e();
        g8.b1 b1Var = hVarE instanceof g8.b1 ? (g8.b1) hVarE : null;
        if (b1Var != null) {
            int index = b1Var.getIndex();
            g8.b1[] b1VarArr = this.f22247b;
            if (index < b1VarArr.length && kotlin.jvm.internal.p.a(b1VarArr[index].g(), b1Var.g())) {
                return this.f22248c[index];
            }
        }
        return null;
    }

    @Override // v9.v0
    public final boolean e() {
        return this.f22248c.length == 0;
    }
}
