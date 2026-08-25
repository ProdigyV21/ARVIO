package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class w7 extends pa implements jb {
    private static final w7 zzg;
    private int zzb;
    private xa zzd;
    private xa zze;
    private i8 zzf;

    static {
        w7 w7Var = new w7();
        zzg = w7Var;
        pa.i(w7.class, w7Var);
    }

    public w7() {
        pb pbVar = pb.f13547o;
        this.zzd = pbVar;
        this.zze = pbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zzb", "zzd", t8.class, "zze", u6.class, "zzf"});
        }
        if (i11 == 3) {
            return new w7();
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
