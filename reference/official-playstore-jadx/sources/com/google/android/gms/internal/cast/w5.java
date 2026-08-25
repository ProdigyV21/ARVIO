package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class w5 extends pa implements jb {
    private static final w5 zzs;
    private int zzb;
    private w6 zzd;
    private boolean zze;
    private long zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private e8 zzl;
    private int zzm;
    private int zzn;
    private boolean zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;

    static {
        w5 w5Var = new w5();
        zzs = w5Var;
        pa.i(w5.class, w5Var);
    }

    public static v5 n() {
        return (v5) zzs.e();
    }

    public static v5 o(w5 w5Var) {
        oa oaVarE = zzs.e();
        pa paVar = oaVarE.f13529i;
        if (!paVar.equals(w5Var)) {
            if (!oaVarE.f13530l.l()) {
                pa paVar2 = (pa) paVar.k(4, null);
                ob.f13531c.a(paVar2.getClass()).c(paVar2, oaVarE.f13530l);
                oaVarE.f13530l = paVar2;
            }
            pa paVar3 = oaVarE.f13530l;
            ob.f13531c.a(paVar3.getClass()).c(paVar3, w5Var);
        }
        return (v5) oaVarE;
    }

    public static w5 p() {
        return zzs;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzs, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဇ\u0001\u0003စ\u0002\u0004ဆ\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007င\u0006\bင\u0007\tဉ\b\n᠌\t\u000bင\n\fဇ\u000b\rင\f\u000eင\r\u000fဇ\u000e", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", z7.A, "zzi", z7.z, "zzj", "zzk", "zzl", "zzm", h5.f13283i, "zzn", "zzo", "zzp", "zzq", "zzr"});
        }
        if (i11 == 3) {
            return new w5();
        }
        if (i11 == 4) {
            return new v5(zzs);
        }
        if (i11 == 5) {
            return zzs;
        }
        throw null;
    }

    public final /* synthetic */ void q(w6 w6Var) {
        this.zzd = w6Var;
        this.zzb |= 1;
    }

    public final /* synthetic */ void r(boolean z) {
        this.zzb |= 2;
        this.zze = z;
    }

    public final /* synthetic */ void s(long j10) {
        this.zzb |= 4;
        this.zzf = j10;
    }

    public final /* synthetic */ void t(int i10) {
        this.zzb |= 64;
        this.zzj = i10;
    }

    public final /* synthetic */ void u(int i10) {
        this.zzb |= 128;
        this.zzk = i10;
    }

    public final /* synthetic */ void v(int i10) {
        this.zzb |= 1024;
        this.zzn = i10;
    }

    public final /* synthetic */ void w(boolean z) {
        this.zzb |= 2048;
        this.zzo = z;
    }

    public final /* synthetic */ void x(int i10) {
        this.zzb |= 4096;
        this.zzp = i10;
    }

    public final /* synthetic */ void y(int i10) {
        this.zzb |= 8192;
        this.zzq = i10;
    }

    public final /* synthetic */ void z(boolean z) {
        this.zzb |= 16384;
        this.zzr = z;
    }
}
