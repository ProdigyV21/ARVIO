package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class m extends pa implements jb {
    private static final m zzg;
    private int zzb;
    private p zzd;
    private int zze;
    private int zzf;

    static {
        m mVar = new m();
        zzg = mVar;
        pa.i(m.class, mVar);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", "zze", z7.f13720m, "zzf", z7.f13719l});
        }
        if (i11 == 3) {
            return new m();
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
