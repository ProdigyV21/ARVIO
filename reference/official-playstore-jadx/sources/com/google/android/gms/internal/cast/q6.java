package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class q6 extends pa implements jb {
    private static final q6 zzf;
    private int zzb;
    private String zzd = "";
    private long zze;

    static {
        q6 q6Var = new q6();
        zzf = q6Var;
        pa.i(q6.class, q6Var);
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
            return new q6();
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
