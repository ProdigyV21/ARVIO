package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class a8 extends pa implements jb {
    private static final a8 zze;
    private int zzb;
    private int zzd;

    static {
        a8 a8Var = new a8();
        zze = a8Var;
        pa.i(a8.class, a8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzb", "zzd", k5.G});
        }
        if (i11 == 3) {
            return new a8();
        }
        if (i11 == 4) {
            return new h7(zze);
        }
        if (i11 == 5) {
            return zze;
        }
        throw null;
    }
}
