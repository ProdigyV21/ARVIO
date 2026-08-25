package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class o7 extends pa implements jb {
    private static final o7 zzk;
    private int zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;

    static {
        o7 o7Var = new o7();
        zzk = o7Var;
        pa.i(o7.class, o7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005᠌\u0004\u0006ဆ\u0005\u0007ဇ\u0006", new Object[]{"zzb", "zzd", "zze", h5.x, "zzf", "zzg", "zzh", z7.K, "zzi", "zzj"});
        }
        if (i11 == 3) {
            return new o7();
        }
        if (i11 == 4) {
            return new h7(zzk);
        }
        if (i11 == 5) {
            return zzk;
        }
        throw null;
    }
}
