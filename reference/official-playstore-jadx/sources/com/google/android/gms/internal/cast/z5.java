package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class z5 extends pa implements jb {
    private static final z5 zze;
    private int zzb;
    private int zzd;

    static {
        z5 z5Var = new z5();
        zze = z5Var;
        pa.i(z5.class, z5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzb", "zzd", z7.E});
        }
        if (i11 == 3) {
            return new z5();
        }
        if (i11 == 4) {
            return new l(zze);
        }
        if (i11 == 5) {
            return zze;
        }
        throw null;
    }
}
