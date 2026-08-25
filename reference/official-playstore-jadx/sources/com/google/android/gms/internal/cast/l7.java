package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class l7 extends pa implements jb {
    private static final l7 zzk;
    private int zzb;
    private String zzd = "";
    private String zze = "";
    private boolean zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        l7 l7Var = new l7();
        zzk = l7Var;
        pa.i(l7.class, l7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", h5.f13291s, "zzh", h5.f13293u, "zzi", h5.f13292t, "zzj", h5.f13294v});
        }
        if (i11 == 3) {
            return new l7();
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
