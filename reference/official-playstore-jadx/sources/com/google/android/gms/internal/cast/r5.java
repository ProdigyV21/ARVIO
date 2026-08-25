package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class r5 extends pa implements jb {
    private static final r5 zzk;
    private int zzb;
    private int zzd;
    private boolean zze;
    private int zzf;
    private boolean zzg;
    private xa zzh;
    private xa zzi;
    private String zzj;

    static {
        r5 r5Var = new r5();
        zzk = r5Var;
        pa.i(r5.class, r5Var);
    }

    public r5() {
        pb pbVar = pb.f13547o;
        this.zzh = pbVar;
        this.zzi = pbVar;
        this.zzj = "";
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0007\u001b\b\u001b\tဈ\u0004", new Object[]{"zzb", "zzd", z7.f13731y, "zze", "zzf", z7.N, "zzg", "zzh", t7.class, "zzi", t7.class, "zzj"});
        }
        if (i11 == 3) {
            return new r5();
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
