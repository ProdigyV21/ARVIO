package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class a9 extends pa implements jb {
    private static final a9 zzj;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzg;
    private long zzi;
    private va zzf = qa.f13563o;
    private xa zzh = pb.f13547o;

    static {
        a9 a9Var = new a9();
        zzj = a9Var;
        pa.i(a9.class, a9Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzj, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ࠞ\u0005᠌\u0002\u0006\u001b\u0007ဂ\u0003", new Object[]{"zzb", "zzd", k5.f13385u, "zze", z7.N, "zzf", k5.f13384t, "zzg", z7.H, "zzh", z8.class, "zzi"});
        }
        if (i11 == 3) {
            return new a9();
        }
        if (i11 == 4) {
            return new q8(zzj);
        }
        if (i11 == 5) {
            return zzj;
        }
        throw null;
    }
}
