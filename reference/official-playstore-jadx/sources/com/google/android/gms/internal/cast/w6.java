package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class w6 extends pa implements jb {
    private static final w6 zzf;
    private int zzb;
    private String zzd = "";
    private String zze = "";

    static {
        w6 w6Var = new w6();
        zzf = w6Var;
        pa.i(w6.class, w6Var);
    }

    public static v6 n() {
        return (v6) zzf.e();
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
            return new w6();
        }
        if (i11 == 4) {
            return new v6(zzf);
        }
        if (i11 == 5) {
            return zzf;
        }
        throw null;
    }

    public final /* synthetic */ void o(String str) {
        str.getClass();
        this.zzb |= 1;
        this.zzd = str;
    }
}
