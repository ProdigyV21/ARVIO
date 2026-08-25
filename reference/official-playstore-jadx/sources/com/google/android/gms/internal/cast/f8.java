package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class f8 extends pa implements jb {
    private static final f8 zzk;
    private int zzb;
    private w6 zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private long zzi;
    private xa zzj = pb.f13547o;

    static {
        f8 f8Var = new f8();
        zzk = f8Var;
        pa.i(f8.class, f8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ဂ\u0005\u0007\u001b", new Object[]{"zzb", "zzd", "zze", h5.F, "zzf", h5.E, "zzg", z7.N, "zzh", z7.C, "zzi", "zzj", w6.class});
        }
        if (i11 == 3) {
            return new f8();
        }
        if (i11 == 4) {
            return new h7(zzk);
        }
        if (i11 == 5) {
            return zzk;
        }
        throw null;
    }
}
