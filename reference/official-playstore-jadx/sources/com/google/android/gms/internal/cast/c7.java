package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class c7 extends pa implements jb {
    private static final c7 zzi;
    private int zzb;
    private Object zze;
    private int zzf;
    private int zzd = 0;
    private String zzg = "";
    private xa zzh = pb.f13547o;

    static {
        c7 c7Var = new c7();
        zzi = c7Var;
        pa.i(c7.class, c7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzi, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003\u001b\u0004<\u0000\u0005<\u0000", new Object[]{"zze", "zzd", "zzb", "zzf", k5.D, "zzg", "zzh", p7.class, x8.class, w8.class});
        }
        if (i11 == 3) {
            return new c7();
        }
        if (i11 == 4) {
            return new l(zzi);
        }
        if (i11 == 5) {
            return zzi;
        }
        throw null;
    }
}
