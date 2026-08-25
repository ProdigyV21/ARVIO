package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class t6 extends pa implements jb {
    private static final t6 zzg;
    private int zzb;
    private int zzd = 0;
    private Object zze;
    private long zzf;

    static {
        t6 t6Var = new t6();
        zzg = t6Var;
        pa.i(t6.class, t6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzg, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001စ\u0000\u0002:\u0000\u00035\u0000\u00048\u0000", new Object[]{"zze", "zzd", "zzb", "zzf"});
        }
        if (i11 == 3) {
            return new t6();
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
