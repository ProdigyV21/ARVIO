package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class z6 extends pa implements jb {
    private static final z6 zzm;
    private int zzb;
    private boolean zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private o7 zzh;
    private int zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;

    static {
        z6 z6Var = new z6();
        zzm = z6Var;
        pa.i(z6.class, z6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005ဉ\u0004\u0006᠌\u0005\u0007ဇ\u0006\b᠌\u0007\tင\b", new Object[]{"zzb", "zzd", "zze", z7.N, "zzf", h5.f13284l, "zzg", z7.B, "zzh", "zzi", h5.f13283i, "zzj", "zzk", k5.x, "zzl"});
        }
        if (i11 == 3) {
            return new z6();
        }
        if (i11 == 4) {
            return new l(zzm);
        }
        if (i11 == 5) {
            return zzm;
        }
        throw null;
    }
}
