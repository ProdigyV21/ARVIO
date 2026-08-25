package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class e6 extends pa implements jb {
    private static final e6 zzi;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        e6 e6Var = new e6();
        zzi = e6Var;
        pa.i(e6.class, e6Var);
    }

    public static d6 n() {
        return (d6) zzi.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004", new Object[]{"zzb", "zzd", h5.M, "zze", "zzf", "zzg", "zzh"});
        }
        if (i11 == 3) {
            return new e6();
        }
        if (i11 == 4) {
            return new d6(zzi);
        }
        if (i11 == 5) {
            return zzi;
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
        this.zzb |= 16;
        this.zzh = i10;
    }

    public final /* synthetic */ void s(int i10) {
        this.zzd = i10 - 1;
        this.zzb |= 1;
    }
}
