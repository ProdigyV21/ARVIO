package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class i6 extends pa implements jb {
    private static final i6 zzi;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private long zzh;

    static {
        i6 i6Var = new i6();
        zzi = i6Var;
        pa.i(i6.class, i6Var);
    }

    public static h6 n() {
        return (h6) zzi.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004ဇ\u0003\u0006ဂ\u0004", new Object[]{"zzb", "zzd", k5.f13376l, "zze", "zzf", "zzg", "zzh"});
        }
        if (i11 == 3) {
            return new i6();
        }
        if (i11 == 4) {
            return new h6(zzi);
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

    public final /* synthetic */ void q(boolean z) {
        this.zzb |= 8;
        this.zzg = z;
    }

    public final /* synthetic */ void r(long j10) {
        this.zzb |= 16;
        this.zzh = j10;
    }

    public final /* synthetic */ void s(int i10) {
        this.zzd = i10 - 1;
        this.zzb |= 1;
    }
}
