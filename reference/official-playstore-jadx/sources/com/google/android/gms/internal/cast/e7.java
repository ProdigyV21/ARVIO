package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class e7 extends pa implements jb {
    private static final e7 zze;
    private int zzb;
    private int zzd;

    static {
        e7 e7Var = new e7();
        zze = e7Var;
        pa.i(e7.class, e7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzb", "zzd", h5.f13286n});
        }
        if (i11 == 3) {
            return new e7();
        }
        if (i11 == 4) {
            return new l(zze);
        }
        if (i11 == 5) {
            return zze;
        }
        throw null;
    }
}
