package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends o {
    public v(short s10) {
        super(Short.valueOf(s10));
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        d8.k kVarJ = b0Var.j();
        kVarJ.getClass();
        v9.b0 b0VarS = kVarJ.s(d8.m.SHORT);
        if (b0VarS != null) {
            return b0VarS;
        }
        d8.k.a(58);
        throw null;
    }

    @Override // k9.g
    public final String toString() {
        return ((Number) this.f19530a).intValue() + ".toShort()";
    }
}
