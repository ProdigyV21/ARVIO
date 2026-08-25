package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class c9 extends pa implements jb {
    private static final c9 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private long zzf;

    static {
        c9 c9Var = new c9();
        zzg = c9Var;
        pa.i(c9.class, c9Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003ဂ\u0002", new Object[]{"zzb", "zzd", k5.f13387w, "zze", "zzf"});
        }
        if (i11 == 3) {
            return new c9();
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
