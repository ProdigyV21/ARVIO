package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class y5 extends pa implements jb {
    private static final y5 zzd;
    private xa zzb = pb.f13547o;

    static {
        y5 y5Var = new y5();
        zzd = y5Var;
        pa.i(y5.class, y5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzb"});
        }
        if (i11 == 3) {
            return new y5();
        }
        if (i11 == 4) {
            return new l(zzd);
        }
        if (i11 == 5) {
            return zzd;
        }
        throw null;
    }
}
