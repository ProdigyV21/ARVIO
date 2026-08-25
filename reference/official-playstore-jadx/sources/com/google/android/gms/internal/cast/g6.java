package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class g6 extends pa implements jb {
    private static final g6 zzf;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        g6 g6Var = new g6();
        zzf = g6Var;
        pa.i(g6.class, g6Var);
    }

    public static f6 n() {
        return (f6) zzf.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"zzb", "zzd", h5.N, "zze"});
        }
        if (i11 == 3) {
            return new g6();
        }
        if (i11 == 4) {
            return new f6(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }

    public final /* synthetic */ void o(int i10) {
        this.zzb |= 2;
        this.zze = i10;
    }

    public final /* synthetic */ void p(int i10) {
        this.zzd = i10 - 1;
        this.zzb |= 1;
    }
}
