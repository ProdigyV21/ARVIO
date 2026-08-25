package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class u5 extends pa implements jb {
    private static final u5 zzf;
    private int zzb;
    private String zzd = "";
    private long zze;

    static {
        u5 u5Var = new u5();
        zzf = u5Var;
        pa.i(u5.class, u5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzb", "zzd", "zze"});
        }
        if (i11 == 3) {
            return new u5();
        }
        if (i11 == 4) {
            return new l(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }
}
