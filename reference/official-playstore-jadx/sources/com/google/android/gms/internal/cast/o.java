package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class o extends pa implements jb {
    private static final o zzg;
    private xa zzb;
    private xa zzd;
    private xa zze;
    private xa zzf;

    static {
        o oVar = new o();
        zzg = oVar;
        pa.i(o.class, oVar);
    }

    public o() {
        pb pbVar = pb.f13547o;
        this.zzb = pbVar;
        this.zzd = pbVar;
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
            return new qb(zzg, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004\u001b", new Object[]{"zzb", n.class, "zzd", m.class, "zze", n.class, "zzf", m.class});
        }
        if (i11 == 3) {
            return new o();
        }
        if (i11 == 4) {
            return new l(zzg);
        }
        if (i11 == 5) {
            return zzg;
        }
        throw null;
    }
}
