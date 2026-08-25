package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class m7 extends pa implements jb {
    private static final m7 zzf;
    private int zzb;
    private String zzd = "";
    private String zze = "";

    static {
        m7 m7Var = new m7();
        zzf = m7Var;
        pa.i(m7.class, m7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzb", "zzd", "zze"});
        }
        if (i11 == 3) {
            return new m7();
        }
        if (i11 == 4) {
            return new h7(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }
}
