package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class c8 extends pa implements jb {
    private static final c8 zzf;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        c8 c8Var = new c8();
        zzf = c8Var;
        pa.i(c8.class, c8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzb", "zzd", "zze"});
        }
        if (i11 == 3) {
            return new c8();
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
