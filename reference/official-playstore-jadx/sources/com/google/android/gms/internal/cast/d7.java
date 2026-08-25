package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class d7 extends pa implements jb {
    private static final d7 zzl;
    private int zzb;
    private c7 zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private x8 zzh;
    private w8 zzi;
    private int zzj;
    private v8 zzk;

    static {
        d7 d7Var = new d7();
        zzl = d7Var;
        pa.i(d7.class, d7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007᠌\u0006\bဉ\u0007", new Object[]{"zzb", "zzd", "zze", k5.H, "zzf", "zzg", "zzh", "zzi", "zzj", k5.C, "zzk"});
        }
        if (i11 == 3) {
            return new d7();
        }
        if (i11 == 4) {
            return new l(zzl);
        }
        if (i11 == 5) {
            return zzl;
        }
        throw null;
    }
}
