package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f9.b f19531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f9.f f19532c;

    public i(f9.b bVar, f9.f fVar) {
        super(new x6.x(bVar, fVar));
        this.f19531b = bVar;
        this.f19532c = fVar;
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        f9.b bVar = this.f19531b;
        g8.f fVarD = g8.y.d(b0Var, bVar);
        v9.b0 b0VarM = null;
        if (fVarD != null) {
            if (!i9.f.n(fVarD, 3)) {
                fVarD = null;
            }
            if (fVarD != null) {
                b0VarM = fVarD.m();
            }
        }
        if (b0VarM != null) {
            return b0VarM;
        }
        return x9.k.b(x9.j.K, bVar.toString(), this.f19532c.f15368i);
    }

    @Override // k9.g
    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f19531b.i());
        sb2.append('.');
        sb2.append(this.f19532c);
        return sb2.toString();
    }
}
