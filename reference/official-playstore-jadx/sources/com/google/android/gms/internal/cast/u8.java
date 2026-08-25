package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class u8 extends pa implements jb {
    private static final u8 zzh;
    private int zzb;
    private int zzd;
    private xa zze;
    private xa zzf;
    private int zzg;

    static {
        u8 u8Var = new u8();
        zzh = u8Var;
        pa.i(u8.class, u8Var);
    }

    public u8() {
        pb pbVar = pb.f13547o;
        this.zze = pbVar;
        this.zzf = pbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001᠌\u0000\u0002\u001b\u0003\u001b\u0004င\u0001", new Object[]{"zzb", "zzd", k5.f13381q, "zze", t7.class, "zzf", t7.class, "zzg"});
        }
        if (i11 == 3) {
            return new u8();
        }
        if (i11 == 4) {
            return new q8(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
