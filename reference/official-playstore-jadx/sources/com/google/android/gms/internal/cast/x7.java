package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class x7 extends pa implements jb {
    private static final x7 zze;
    private int zzb;
    private int zzd;

    static {
        x7 x7Var = new x7();
        zze = x7Var;
        pa.i(x7.class, x7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzb", "zzd", k5.E});
        }
        if (i11 == 3) {
            return new x7();
        }
        if (i11 == 4) {
            return new h7(zze);
        }
        if (i11 == 5) {
            return zze;
        }
        throw null;
    }
}
