package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class t7 extends pa implements jb {
    private static final t7 zzf;
    private int zzb;
    private int zzd;
    private String zze = "";

    static {
        t7 t7Var = new t7();
        zzf = t7Var;
        pa.i(t7.class, t7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001", new Object[]{"zzb", "zzd", "zze"});
        }
        if (i11 == 3) {
            return new t7();
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
