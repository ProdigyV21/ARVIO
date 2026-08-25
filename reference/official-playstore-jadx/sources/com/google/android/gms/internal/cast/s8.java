package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class s8 extends pa implements jb {
    private static final s8 zze;
    private int zzb;
    private String zzd = "";

    static {
        s8 s8Var = new s8();
        zze = s8Var;
        pa.i(s8.class, s8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzb", "zzd"});
        }
        if (i11 == 3) {
            return new s8();
        }
        if (i11 == 4) {
            return new q8(zze);
        }
        if (i11 == 5) {
            return zze;
        }
        throw null;
    }
}
