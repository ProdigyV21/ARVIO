package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class u7 extends pa implements jb {
    private static final u7 zzk;
    private int zzb;
    private boolean zzd;
    private boolean zze;
    private int zzf;
    private int zzh;
    private int zzi;
    private String zzg = "";
    private String zzj = "";

    static {
        u7 u7Var = new u7();
        zzk = u7Var;
        pa.i(u7.class, u7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006င\u0005\u0007ဈ\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i11 == 3) {
            return new u7();
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
