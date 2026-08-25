package k9;

import g8.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f19527b = 1;

    public /* synthetic */ c(Object obj) {
        super(obj);
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        switch (this.f19527b) {
            case 0:
                d8.k kVarJ = b0Var.j();
                kVarJ.getClass();
                v9.b0 b0VarS = kVarJ.s(d8.m.BOOLEAN);
                if (b0VarS != null) {
                    return b0VarS;
                }
                d8.k.a(64);
                throw null;
            case 1:
                d8.k kVarJ2 = b0Var.j();
                kVarJ2.getClass();
                v9.b0 b0VarS2 = kVarJ2.s(d8.m.DOUBLE);
                if (b0VarS2 != null) {
                    return b0VarS2;
                }
                d8.k.a(62);
                throw null;
            default:
                d8.k kVarJ3 = b0Var.j();
                kVarJ3.getClass();
                v9.b0 b0VarS3 = kVarJ3.s(d8.m.FLOAT);
                if (b0VarS3 != null) {
                    return b0VarS3;
                }
                d8.k.a(61);
                throw null;
        }
    }

    @Override // k9.g
    public String toString() {
        switch (this.f19527b) {
            case 1:
                return ((Number) this.f19530a).doubleValue() + ".toDouble()";
            case 2:
                return ((Number) this.f19530a).floatValue() + ".toFloat()";
            default:
                return super.toString();
        }
    }

    public c(double d4) {
        super(Double.valueOf(d4));
    }

    public c(float f10) {
        super(Float.valueOf(f10));
    }
}
