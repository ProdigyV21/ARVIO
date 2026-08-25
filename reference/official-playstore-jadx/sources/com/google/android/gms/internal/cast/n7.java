package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class n7 extends pa implements jb {
    private static final n7 zzg;
    private int zzb;
    private int zzd;
    private long zze;
    private int zzf;

    static {
        n7 n7Var = new n7();
        zzg = n7Var;
        pa.i(n7.class, n7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", h5.f13295w, "zze", "zzf", z7.C});
        }
        if (i11 == 3) {
            return new n7();
        }
        if (i11 == 4) {
            return new h7(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
