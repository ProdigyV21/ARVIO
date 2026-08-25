package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class b7 extends pa implements jb {
    private static final b7 zzk;
    private int zzb;
    private int zzd;
    private int zze;
    private va zzf;
    private va zzg;
    private xa zzh;
    private xa zzi;
    private int zzj;

    static {
        b7 b7Var = new b7();
        zzk = b7Var;
        pa.i(b7.class, b7Var);
    }

    public b7() {
        qa qaVar = qa.f13563o;
        this.zzf = qaVar;
        this.zzg = qaVar;
        pb pbVar = pb.f13547o;
        this.zzh = pbVar;
        this.zzi = pbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0004\u0000\u0001င\u0000\u0002᠌\u0001\u0003\u0016\u0004\u0016\u0005\u001a\u0006\u001a\u0007᠌\u0002", new Object[]{"zzb", "zzd", "zze", h5.f13286n, "zzf", "zzg", "zzh", "zzi", "zzj", z7.N});
        }
        if (i11 == 3) {
            return new b7();
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
