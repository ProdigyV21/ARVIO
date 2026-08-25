package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class e5 extends pa implements jb {
    private static final e5 zzp;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private boolean zzo;

    static {
        e5 e5Var = new e5();
        zzp = e5Var;
        pa.i(e5.class, e5Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzp, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006\b᠌\u0007\tင\b\nင\t\u000bင\n\fဇ\u000b", new Object[]{"zzb", "zzd", "zze", "zzf", z7.f13726s, "zzg", z7.f13727t, "zzh", z7.f13725r, "zzi", z7.f13728u, "zzj", z7.f13729v, "zzk", z7.f13724q, "zzl", "zzm", "zzn", "zzo"});
        }
        if (i11 == 3) {
            return new e5();
        }
        if (i11 == 4) {
            return new l(zzp);
        }
        if (i11 == 5) {
            return zzp;
        }
        throw null;
    }
}
