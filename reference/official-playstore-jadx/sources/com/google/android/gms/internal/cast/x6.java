package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class x6 extends pa implements jb {
    private static final x6 zzh;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        x6 x6Var = new x6();
        zzh = x6Var;
        pa.i(x6.class, x6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
        }
        if (i11 == 3) {
            return new x6();
        }
        if (i11 == 4) {
            return new l(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
