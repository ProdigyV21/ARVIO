package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class v7 extends pa implements jb {
    private static final v7 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        v7 v7Var = new v7();
        zzg = v7Var;
        pa.i(v7.class, v7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"zzb", "zzd", "zze", h5.A, "zzf"});
        }
        if (i11 == 3) {
            return new v7();
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
