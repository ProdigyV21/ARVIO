package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class r6 extends pa implements jb {
    private static final r6 zzm;
    private int zzb;
    private long zze;
    private long zzf;
    private int zzh;
    private boolean zzi;
    private long zzk;
    private long zzl;
    private String zzd = "";
    private xa zzg = pb.f13547o;
    private String zzj = "";

    static {
        r6 r6Var = new r6();
        zzm = r6Var;
        pa.i(r6.class, r6Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004\u001b\u0005င\u0003\u0006ဇ\u0004\u0007ဈ\u0005\bဂ\u0006\tဂ\u0007", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", q6.class, "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i11 == 3) {
            return new r6();
        }
        if (i11 == 4) {
            return new l(zzm);
        }
        if (i11 == 5) {
            return zzm;
        }
        throw null;
    }
}
