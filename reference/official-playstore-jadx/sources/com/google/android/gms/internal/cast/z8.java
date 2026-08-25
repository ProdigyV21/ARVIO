package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class z8 extends pa implements jb {
    private static final z8 zzg;
    private int zzb;
    private int zzd;
    private long zze;
    private long zzf;

    static {
        z8 z8Var = new z8();
        zzg = z8Var;
        pa.i(z8.class, z8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003ဂ\u0002", new Object[]{"zzb", "zzd", k5.f13385u, "zze", "zzf"});
        }
        if (i11 == 3) {
            return new z8();
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
