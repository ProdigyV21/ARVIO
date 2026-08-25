package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class e8 extends pa implements jb {
    private static final e8 zzf;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        e8 e8Var = new e8();
        zzf = e8Var;
        pa.i(e8.class, e8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            h5 h5Var = h5.D;
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zzd", h5Var, "zze", h5Var});
        }
        if (i11 == 3) {
            return new e8();
        }
        if (i11 == 4) {
            return new h7(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }
}
