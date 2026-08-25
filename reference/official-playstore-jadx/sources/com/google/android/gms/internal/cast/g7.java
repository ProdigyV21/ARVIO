package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class g7 extends pa implements jb {
    private static final g7 zzk;
    private int zzb;
    private xa zzd = pb.f13547o;
    private boolean zze;
    private boolean zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private boolean zzj;

    static {
        g7 g7Var = new g7();
        zzk = g7Var;
        pa.i(g7.class, g7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u001b\u0002ဇ\u0000\u0003ဇ\u0001\u0004ဂ\u0002\u0005ဂ\u0003\u0006ဂ\u0004\u0007ဇ\u0005", new Object[]{"zzb", "zzd", a7.class, "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i11 == 3) {
            return new g7();
        }
        if (i11 == 4) {
            return new l(zzk);
        }
        if (i11 == 5) {
            return zzk;
        }
        throw null;
    }
}
