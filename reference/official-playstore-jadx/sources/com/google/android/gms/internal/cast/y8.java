package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class y8 extends pa implements jb {
    private static final y8 zzl;
    private int zzb;
    private int zzd;
    private int zze;
    private xa zzf;
    private xa zzg;
    private int zzh;
    private va zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        y8 y8Var = new y8();
        zzl = y8Var;
        pa.i(y8.class, y8Var);
    }

    public y8() {
        pb pbVar = pb.f13547o;
        this.zzf = pbVar;
        this.zzg = pbVar;
        this.zzi = qa.f13563o;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            k5 k5Var = k5.f13382r;
            return new qb(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003\u001b\u0004\u001b\u0005᠌\u0002\u0006ࠬ\u0007ဇ\u0003\bဇ\u0004", new Object[]{"zzb", "zzd", k5Var, "zze", k5.f13383s, "zzf", w6.class, "zzg", w6.class, "zzh", z7.N, "zzi", k5Var, "zzj", "zzk"});
        }
        if (i11 == 3) {
            return new y8();
        }
        if (i11 == 4) {
            return new q8(zzl);
        }
        if (i11 == 5) {
            return zzl;
        }
        throw null;
    }
}
