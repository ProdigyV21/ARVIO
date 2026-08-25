package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class b9 extends pa implements jb {
    private static final b9 zzh;
    private int zzb;
    private String zzd = "";
    private long zze;
    private long zzf;
    private c9 zzg;

    static {
        b9 b9Var = new b9();
        zzh = b9Var;
        pa.i(b9.class, b9Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဉ\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
        }
        if (i11 == 3) {
            return new b9();
        }
        if (i11 == 4) {
            return new q8(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
