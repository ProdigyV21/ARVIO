package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class i7 extends pa implements jb {
    private static final i7 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private f7 zzf;

    static {
        i7 i7Var = new i7();
        zzg = i7Var;
        pa.i(i7.class, i7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zzd", "zze", "zzf"});
        }
        if (i11 == 3) {
            return new i7();
        }
        if (i11 == 4) {
            return new h7(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
