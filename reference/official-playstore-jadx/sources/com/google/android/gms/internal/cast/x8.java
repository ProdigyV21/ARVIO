package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class x8 extends pa implements jb {
    private static final x8 zzg;
    private int zzb;
    private String zzd = "";
    private int zze;
    private p7 zzf;

    static {
        x8 x8Var = new x8();
        zzg = x8Var;
        pa.i(x8.class, x8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zzd", "zze", k5.A, "zzf"});
        }
        if (i11 == 3) {
            return new x8();
        }
        if (i11 == 4) {
            return new q8(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
