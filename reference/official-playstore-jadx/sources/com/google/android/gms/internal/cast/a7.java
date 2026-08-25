package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class a7 extends pa implements jb {
    private static final a7 zzl;
    private int zzb;
    private boolean zze;
    private boolean zzf;
    private a9 zzg;
    private boolean zzh;
    private long zzj;
    private long zzk;
    private String zzd = "";
    private va zzi = qa.f13563o;

    static {
        a7 a7Var = new a7();
        zzl = a7Var;
        pa.i(a7.class, a7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဉ\u0003\u0004ဇ\u0004\u0005ࠬ\u0006ဇ\u0002\u0007ဂ\u0005\bဂ\u0006", new Object[]{"zzb", "zzd", "zze", "zzg", "zzh", "zzi", k5.f13384t, "zzf", "zzj", "zzk"});
        }
        if (i11 == 3) {
            return new a7();
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
