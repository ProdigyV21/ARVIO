package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class f5 extends pa implements jb {
    private static final f5 zzj;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private e5 zzh;
    private int zzi;

    static {
        f5 f5Var = new f5();
        zzj = f5Var;
        pa.i(f5.class, f5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003င\u0002\u0004င\u0003\u0005ဉ\u0004\u0006᠌\u0005", new Object[]{"zzb", "zzd", "zze", z7.x, "zzf", "zzg", "zzh", "zzi", z7.f13730w});
        }
        if (i11 == 3) {
            return new f5();
        }
        if (i11 == 4) {
            return new l(zzj);
        }
        if (i11 == 5) {
            return zzj;
        }
        throw null;
    }
}
