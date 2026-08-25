package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class q5 extends pa implements jb {
    private static final q5 zzi;
    private int zzb;
    private int zzd;
    private double zze;
    private double zzf;
    private double zzg;
    private double zzh;

    static {
        q5 q5Var = new q5();
        zzi = q5Var;
        pa.i(q5.class, q5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဋ\u0000\u0002က\u0001\u0003က\u0002\u0004က\u0003\u0005က\u0004", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i11 == 3) {
            return new q5();
        }
        if (i11 == 4) {
            return new l(zzi);
        }
        if (i11 == 5) {
            return zzi;
        }
        throw null;
    }
}
