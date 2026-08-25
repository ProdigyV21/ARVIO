package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class n8 extends pa implements jb {
    private static final n8 zzi;
    private int zzb;
    private long zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private long zzh;

    static {
        n8 n8Var = new n8();
        zzi = n8Var;
        pa.i(n8.class, n8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i11 == 3) {
            return new n8();
        }
        if (i11 == 4) {
            return new h7(zzi);
        }
        if (i11 == 5) {
            return zzi;
        }
        throw null;
    }
}
