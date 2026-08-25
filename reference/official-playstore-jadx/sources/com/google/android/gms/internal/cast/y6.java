package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class y6 extends pa implements jb {
    private static final y6 zzh;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private xa zzg = pb.f13547o;

    static {
        y6 y6Var = new y6();
        zzh = y6Var;
        pa.i(y6.class, y6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004\u001b", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", x6.class});
        }
        if (i11 == 3) {
            return new y6();
        }
        if (i11 == 4) {
            return new l(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
