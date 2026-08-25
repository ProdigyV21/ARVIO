package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class t8 extends pa implements jb {
    private static final t8 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private q5 zzf;

    static {
        t8 t8Var = new t8();
        zzg = t8Var;
        pa.i(t8.class, t8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zzd", h5.L, "zze", "zzf"});
        }
        if (i11 == 3) {
            return new t8();
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
