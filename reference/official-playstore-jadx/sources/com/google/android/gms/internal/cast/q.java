package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class q extends pa implements jb {
    private static final q zzg;
    private int zzb;
    private long zzd;
    private long zze;
    private int zzf;

    static {
        q qVar = new q();
        zzg = qVar;
        pa.i(q.class, qVar);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003င\u0002", new Object[]{"zzb", "zzd", "zze", "zzf"});
        }
        if (i11 == 3) {
            return new q();
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
