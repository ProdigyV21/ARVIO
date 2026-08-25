package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class f7 extends pa implements jb {
    private static final f7 zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        f7 f7Var = new f7();
        zzg = f7Var;
        pa.i(f7.class, f7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzb", "zzd", h5.f13286n, "zze", "zzf"});
        }
        if (i11 == 3) {
            return new f7();
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
