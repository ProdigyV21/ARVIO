package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class d8 extends pa implements jb {
    private static final d8 zzl;
    private int zzb;
    private o zzd;
    private xa zze;
    private xa zzf;
    private xa zzg;
    private String zzh;
    private long zzi;
    private int zzj;
    private String zzk;

    static {
        d8 d8Var = new d8();
        zzl = d8Var;
        pa.i(d8.class, d8Var);
    }

    public d8() {
        pb pbVar = pb.f13547o;
        this.zze = pbVar;
        this.zzf = pbVar;
        this.zzg = pbVar;
        this.zzh = "";
        this.zzk = "";
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001ဉ\u0000\u0002\u001a\u0003\u001b\u0004\u001b\u0005ဈ\u0001\u0006ဂ\u0002\u0007᠌\u0003\bဈ\u0004", new Object[]{"zzb", "zzd", "zze", "zzf", u5.class, "zzg", y7.class, "zzh", "zzi", "zzj", k5.z, "zzk"});
        }
        if (i11 == 3) {
            return new d8();
        }
        if (i11 == 4) {
            return new h7(zzl);
        }
        if (i11 == 5) {
            return zzl;
        }
        throw null;
    }
}
