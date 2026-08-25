package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends o {
    public t(long j10) {
        super(Long.valueOf(j10));
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        d8.k kVarJ = b0Var.j();
        kVarJ.getClass();
        v9.b0 b0VarS = kVarJ.s(d8.m.LONG);
        if (b0VarS != null) {
            return b0VarS;
        }
        d8.k.a(60);
        throw null;
    }

    @Override // k9.g
    public final String toString() {
        return ((Number) this.f19530a).longValue() + ".toLong()";
    }
}
