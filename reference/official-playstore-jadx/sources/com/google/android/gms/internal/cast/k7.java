package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class k7 extends pa implements jb {
    private static final k7 zzh;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg = 2;

    static {
        k7 k7Var = new k7();
        zzh = k7Var;
        pa.i(k7.class, k7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return Byte.valueOf(this.zzg);
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ᴌ\u0000\u0002င\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", h5.f13290r, "zze", "zzf", k5.f13388y});
        }
        if (i11 == 3) {
            return new k7();
        }
        if (i11 == 4) {
            return new h7(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        this.zzg = paVar == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
