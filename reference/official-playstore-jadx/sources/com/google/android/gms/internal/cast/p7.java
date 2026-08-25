package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class p7 extends pa implements jb {
    private static final p7 zzh;
    private int zzb;
    private float zze;
    private int zzg;
    private String zzd = "";
    private ua zzf = na.f13493o;

    static {
        p7 p7Var = new p7();
        zzh = p7Var;
        pa.i(p7.class, p7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဈ\u0000\u0002ခ\u0001\u0003$\u0004င\u0002", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
        }
        if (i11 == 3) {
            return new p7();
        }
        if (i11 == 4) {
            return new h7(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
