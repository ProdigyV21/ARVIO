package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class n5 extends pa implements jb {
    private static final n5 zzg;
    private int zzb;
    private String zzd = "";
    private String zze = "";
    private va zzf = qa.f13563o;

    static {
        n5 n5Var = new n5();
        zzg = n5Var;
        pa.i(n5.class, n5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ࠞ", new Object[]{"zzb", "zzd", "zze", "zzf", k5.f13386v});
        }
        if (i11 == 3) {
            return new n5();
        }
        if (i11 == 4) {
            return new l(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
