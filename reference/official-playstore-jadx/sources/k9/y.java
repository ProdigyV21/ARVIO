package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f19540b = 0;

    public y(byte b10) {
        super(Byte.valueOf(b10));
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        switch (this.f19540b) {
            case 0:
                g8.f fVarD = g8.y.d(b0Var, d8.p.R);
                v9.b0 b0VarM = fVarD != null ? fVarD.m() : null;
                if (b0VarM == null) {
                    return x9.k.b(x9.j.J, "UByte");
                }
                return b0VarM;
            case 1:
                g8.f fVarD2 = g8.y.d(b0Var, d8.p.T);
                v9.b0 b0VarM2 = fVarD2 != null ? fVarD2.m() : null;
                if (b0VarM2 == null) {
                    return x9.k.b(x9.j.J, "UInt");
                }
                return b0VarM2;
            case 2:
                g8.f fVarD3 = g8.y.d(b0Var, d8.p.U);
                v9.b0 b0VarM3 = fVarD3 != null ? fVarD3.m() : null;
                if (b0VarM3 == null) {
                    return x9.k.b(x9.j.J, "ULong");
                }
                return b0VarM3;
            default:
                g8.f fVarD4 = g8.y.d(b0Var, d8.p.S);
                v9.b0 b0VarM4 = fVarD4 != null ? fVarD4.m() : null;
                if (b0VarM4 == null) {
                    return x9.k.b(x9.j.J, "UShort");
                }
                return b0VarM4;
        }
    }

    @Override // k9.g
    public final String toString() {
        switch (this.f19540b) {
            case 0:
                return ((Number) this.f19530a).intValue() + ".toUByte()";
            case 1:
                return ((Number) this.f19530a).intValue() + ".toUInt()";
            case 2:
                return ((Number) this.f19530a).longValue() + ".toULong()";
            default:
                return ((Number) this.f19530a).intValue() + ".toUShort()";
        }
    }

    public y(short s10) {
        super(Short.valueOf(s10));
    }

    public y(int i10) {
        super(Integer.valueOf(i10));
    }

    public y(long j10) {
        super(Long.valueOf(j10));
    }
}
