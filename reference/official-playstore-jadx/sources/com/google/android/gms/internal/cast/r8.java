package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class r8 extends pa implements jb {
    private static final r8 zzi;
    private int zzb;
    private int zzd;
    private boolean zze;
    private xa zzf;
    private xa zzg;
    private boolean zzh;

    static {
        r8 r8Var = new r8();
        zzi = r8Var;
        pa.i(r8.class, r8Var);
    }

    public r8() {
        pb pbVar = pb.f13547o;
        this.zzf = pbVar;
        this.zzg = pbVar;
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003\u001b\u0004\u001b\u0005ဇ\u0002", new Object[]{"zzb", "zzd", h5.K, "zze", "zzf", n8.class, "zzg", p8.class, "zzh"});
        }
        if (i11 == 3) {
            return new r8();
        }
        if (i11 == 4) {
            return new q8(zzi);
        }
        if (i11 == 5) {
            return zzi;
        }
        throw null;
    }
}
