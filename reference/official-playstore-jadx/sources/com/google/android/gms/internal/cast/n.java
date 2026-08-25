package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class n extends pa implements jb {
    private static final n zzg;
    private int zzb;
    private double zzd;
    private int zze;
    private int zzf;

    static {
        n nVar = new n();
        zzg = nVar;
        pa.i(n.class, nVar);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001က\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", "zze", z7.f13722o, "zzf", z7.f13721n});
        }
        if (i11 == 3) {
            return new n();
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
