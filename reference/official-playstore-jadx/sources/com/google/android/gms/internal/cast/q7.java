package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class q7 extends pa implements jb {
    private static final q7 zzg;
    private int zzb;
    private long zzd;
    private wa zze;
    private wa zzf;

    static {
        q7 q7Var = new q7();
        zzg = q7Var;
        pa.i(q7.class, q7Var);
    }

    public q7() {
        cb cbVar = cb.f13223o;
        this.zze = cbVar;
        this.zzf = cbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001စ\u0000\u0002\u0017\u0003\u0017", new Object[]{"zzb", "zzd", "zze", "zzf"});
        }
        if (i11 == 3) {
            return new q7();
        }
        if (i11 == 4) {
            return new h7(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
