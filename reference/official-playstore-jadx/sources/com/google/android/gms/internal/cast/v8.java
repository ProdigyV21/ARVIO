package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class v8 extends pa implements jb {
    private static final v8 zzf;
    private int zzb;
    private long zzd;
    private boolean zze;

    static {
        v8 v8Var = new v8();
        zzf = v8Var;
        pa.i(v8.class, v8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001", new Object[]{"zzb", "zzd", "zze"});
        }
        if (i11 == 3) {
            return new v8();
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
