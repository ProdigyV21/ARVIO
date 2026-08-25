package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class u6 extends pa implements jb {
    private static final u6 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private q5 zzf;

    static {
        u6 u6Var = new u6();
        zzg = u6Var;
        pa.i(u6.class, u6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zzd", z7.L, "zze", "zzf"});
        }
        if (i11 == 3) {
            return new u6();
        }
        if (i11 == 4) {
            return new l(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
