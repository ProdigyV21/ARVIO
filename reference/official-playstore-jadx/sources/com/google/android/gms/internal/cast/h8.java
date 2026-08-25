package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class h8 extends pa implements jb {
    private static final h8 zzh;
    private int zzb;
    private String zzd = "";
    private xa zze;
    private xa zzf;
    private boolean zzg;

    static {
        h8 h8Var = new h8();
        zzh = h8Var;
        pa.i(h8.class, h8Var);
    }

    public h8() {
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
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဈ\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001", new Object[]{"zzb", "zzd", "zze", f7.class, "zzf", w6.class, "zzg"});
        }
        if (i11 == 3) {
            return new h8();
        }
        if (i11 == 4) {
            return new h7(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }
}
