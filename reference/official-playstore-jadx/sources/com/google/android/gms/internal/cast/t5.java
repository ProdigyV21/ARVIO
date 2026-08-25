package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class t5 extends pa implements jb {
    private static final t5 zzf;
    private int zzb;
    private String zzd = "";
    private String zze = "";

    static {
        t5 t5Var = new t5();
        zzf = t5Var;
        pa.i(t5.class, t5Var);
    }

    public static s5 n() {
        return (s5) zzf.e();
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
            return new t5();
        }
        if (i11 == 4) {
            return new s5(zzf);
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

    public final /* synthetic */ void p(String str) {
        str.getClass();
        this.zzb |= 2;
        this.zze = str;
    }
}
