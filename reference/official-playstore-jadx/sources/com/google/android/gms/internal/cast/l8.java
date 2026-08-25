package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class l8 extends pa implements jb {
    private static final l8 zzh;
    private int zzb;
    private long zzd;
    private boolean zze;
    private long zzf;
    private boolean zzg;

    static {
        l8 l8Var = new l8();
        zzh = l8Var;
        pa.i(l8.class, l8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001\u0003ဂ\u0002\u0004ဇ\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
        }
        if (i11 == 3) {
            return new l8();
        }
        if (i11 == 4) {
            return new h7(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
