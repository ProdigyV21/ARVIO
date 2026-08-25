package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class x5 extends pa implements jb {
    private static final x5 zzh;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        x5 x5Var = new x5();
        zzh = x5Var;
        pa.i(x5.class, x5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003᠌\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", h5.f13289q, "zzg"});
        }
        if (i11 == 3) {
            return new x5();
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
