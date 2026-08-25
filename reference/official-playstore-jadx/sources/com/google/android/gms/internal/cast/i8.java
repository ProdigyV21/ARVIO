package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class i8 extends pa implements jb {
    private static final i8 zze;
    private int zzb;
    private q5 zzd;

    static {
        i8 i8Var = new i8();
        zze = i8Var;
        pa.i(i8.class, i8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzb", "zzd"});
        }
        if (i11 == 3) {
            return new i8();
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
