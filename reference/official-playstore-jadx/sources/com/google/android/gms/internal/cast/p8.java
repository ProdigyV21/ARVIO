package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class p8 extends pa implements jb {
    private static final p8 zzi;
    private int zzb;
    private int zzd;
    private long zze;
    private xa zzf;
    private xa zzg;
    private xa zzh;

    static {
        p8 p8Var = new p8();
        zzi = p8Var;
        pa.i(p8.class, p8Var);
    }

    public p8() {
        pb pbVar = pb.f13547o;
        this.zzf = pbVar;
        this.zzg = pbVar;
        this.zzh = pbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0003\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003\u001b\u0004\u001b\u0005\u001b", new Object[]{"zzb", "zzd", h5.J, "zze", "zzf", k8.class, "zzg", e6.class, "zzh", n8.class});
        }
        if (i11 == 3) {
            return new p8();
        }
        if (i11 == 4) {
            return new h7(zzi);
        }
        if (i11 == 5) {
            return zzi;
        }
        throw null;
    }
}
