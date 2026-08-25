package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class k8 extends pa implements jb {
    private static final k8 zzm;
    private int zzb;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private boolean zzj;
    private int zzk;
    private boolean zzl;

    static {
        k8 k8Var = new k8();
        zzm = k8Var;
        pa.i(k8.class, k8Var);
    }

    public static j8 n() {
        return (j8) zzm.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဇ\u0006\b᠌\u0007\tဇ\b", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", z7.D, "zzl"});
        }
        if (i11 == 3) {
            return new k8();
        }
        if (i11 == 4) {
            return new j8(zzm);
        }
        if (i11 == 5) {
            return zzm;
        }
        throw null;
    }

    public final /* synthetic */ void o(String str) {
        str.getClass();
        this.zzb |= 1;
        this.zzd = str;
    }

    public final /* synthetic */ void p(String str) {
        str.getClass();
        this.zzb |= 2;
        this.zze = str;
    }

    public final /* synthetic */ void q(String str) {
        str.getClass();
        this.zzb |= 4;
        this.zzf = str;
    }

    public final /* synthetic */ void r(String str) {
        str.getClass();
        this.zzb |= 8;
        this.zzg = str;
    }

    public final /* synthetic */ void s(String str) {
        str.getClass();
        this.zzb |= 16;
        this.zzh = str;
    }

    public final /* synthetic */ void t(String str) {
        str.getClass();
        this.zzb |= 32;
        this.zzi = str;
    }

    public final /* synthetic */ void u(int i10) {
        this.zzk = i10 - 1;
        this.zzb |= 128;
    }
}
