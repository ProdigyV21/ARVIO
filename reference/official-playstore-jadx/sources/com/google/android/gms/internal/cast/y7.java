package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class y7 extends pa implements jb {
    private static final y7 zzr;
    private int zzb;
    private a8 zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private String zzh = "";
    private int zzi;
    private int zzj;
    private int zzk;
    private long zzl;
    private x7 zzm;
    private long zzn;
    private b8 zzo;
    private c8 zzp;
    private int zzq;

    static {
        y7 y7Var = new y7();
        zzr = y7Var;
        pa.i(y7.class, y7Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzr, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005ဈ\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tဂ\b\nဉ\t\u000bဂ\n\fဉ\u000b\rဉ\f\u000e᠌\r", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", k5.F});
        }
        if (i11 == 3) {
            return new y7();
        }
        if (i11 == 4) {
            return new h7(zzr);
        }
        if (i11 == 5) {
            return zzr;
        }
        throw null;
    }
}
