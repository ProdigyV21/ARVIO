package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class n6 extends pa implements jb {
    private static final n6 zzh;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        n6 n6Var = new n6();
        zzh = n6Var;
        pa.i(n6.class, n6Var);
    }

    public static m6 n() {
        return (m6) zzh.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", k5.f13377m, "zze", "zzf", "zzg"});
        }
        if (i11 == 3) {
            return new n6();
        }
        if (i11 == 4) {
            return new m6(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }

    public final /* synthetic */ void o(int i10) {
        this.zzb |= 2;
        this.zze = i10;
    }

    public final /* synthetic */ void p(int i10) {
        this.zzb |= 4;
        this.zzf = i10;
    }

    public final /* synthetic */ void q(int i10) {
        this.zzb |= 8;
        this.zzg = i10;
    }

    public final /* synthetic */ void r(int i10) {
        this.zzd = i10 - 1;
        this.zzb |= 1;
    }
}
