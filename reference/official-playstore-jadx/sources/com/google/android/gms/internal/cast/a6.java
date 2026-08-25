package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class a6 extends pa implements jb {
    private static final a6 zzk;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;

    static {
        a6 a6Var = new a6();
        zzk = a6Var;
        pa.i(a6.class, a6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0005င\u0004\u0006င\u0005\u0007ဇ\u0006", new Object[]{"zzb", "zzd", h5.D, "zze", h5.B, "zzf", h5.C, "zzg", "zzh", "zzi", "zzj"});
        }
        if (i11 == 3) {
            return new a6();
        }
        if (i11 == 4) {
            return new l(zzk);
        }
        if (i11 == 5) {
            return zzk;
        }
        throw null;
    }
}
