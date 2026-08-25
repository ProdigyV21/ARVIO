package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class m8 extends pa implements jb {
    private static final m8 zzo;
    private int zzb;
    private w6 zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private long zzj;
    private long zzk;
    private int zzl;
    private int zzm;
    private String zzd = "";
    private va zzn = qa.f13563o;

    static {
        m8 m8Var = new m8();
        zzo = m8Var;
        pa.i(m8.class, m8Var);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzo, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001ဉ\u0001\u0002ဇ\u0002\u0003ဇ\u0003\u0004ဇ\u0004\u0005ဂ\u0006\u0006ဂ\u0007\u0007င\b\bင\t\t'\nဈ\u0000\u000bဇ\u0005", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzj", "zzk", "zzl", "zzm", "zzn", "zzd", "zzi"});
        }
        if (i11 == 3) {
            return new m8();
        }
        if (i11 == 4) {
            return new h7(zzo);
        }
        if (i11 == 5) {
            return zzo;
        }
        throw null;
    }
}
