package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class p extends pa implements jb {
    private static final p zzd;
    private xa zzb = pb.f13547o;

    static {
        p pVar = new p();
        zzd = pVar;
        pa.i(p.class, pVar);
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzb", q.class});
        }
        if (i11 == 3) {
            return new p();
        }
        if (i11 == 4) {
            return new l(zzd);
        }
        if (i11 == 5) {
            return zzd;
        }
        throw null;
    }
}
