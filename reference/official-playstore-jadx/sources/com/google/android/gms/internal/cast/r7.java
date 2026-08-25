package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class r7 extends pa implements jb {
    private static final r7 zzk;
    private int zzb;
    private boolean zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;

    static {
        r7 r7Var = new r7();
        zzk = r7Var;
        pa.i(r7.class, r7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            h5 h5Var = h5.f13296y;
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007ဇ\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", h5Var, "zzi", h5Var, "zzj"});
        }
        if (i11 == 3) {
            return new r7();
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
