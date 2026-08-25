package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class g8 extends pa implements jb {
    private static final g8 zzg;
    private int zzb;
    private long zzd;
    private long zze;
    private xa zzf = pb.f13547o;

    static {
        g8 g8Var = new g8();
        zzg = g8Var;
        pa.i(g8.class, g8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003\u001b", new Object[]{"zzb", "zzd", "zze", "zzf", h8.class});
        }
        if (i11 == 3) {
            return new g8();
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
