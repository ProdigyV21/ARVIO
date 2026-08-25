package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class w8 extends pa implements jb {
    private static final w8 zzf;
    private int zzb;
    private int zzd;
    private p7 zze;

    static {
        w8 w8Var = new w8();
        zzf = w8Var;
        pa.i(w8.class, w8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001", new Object[]{"zzb", "zzd", k5.B, "zze"});
        }
        if (i11 == 3) {
            return new w8();
        }
        if (i11 == 4) {
            return new q8(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }
}
