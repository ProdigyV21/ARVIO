package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class s6 extends pa implements jb {
    private static final s6 zzf;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        s6 s6Var = new s6();
        zzf = s6Var;
        pa.i(s6.class, s6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zzd", z7.J, "zze", z7.I});
        }
        if (i11 == 3) {
            return new s6();
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
