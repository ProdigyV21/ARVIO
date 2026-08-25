package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final class p5 extends pa implements jb {
    private static final p5 zzh;
    private int zzb;
    private t5 zzd;
    private m7 zze;
    private xa zzf = pb.f13547o;
    private va zzg = qa.f13563o;

    static {
        p5 p5Var = new p5();
        zzh = p5Var;
        pa.i(p5.class, p5Var);
    }

    public static o5 n() {
        return (o5) zzh.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b\u0004ࠞ", new Object[]{"zzb", "zzd", "zze", "zzf", j7.class, "zzg", k5.f13386v});
        }
        if (i11 == 3) {
            return new p5();
        }
        if (i11 == 4) {
            return new o5(zzh);
        }
        if (i11 == 5) {
            return zzh;
        }
        throw null;
    }

    public final /* synthetic */ void o(t5 t5Var) {
        this.zzd = t5Var;
        this.zzb |= 1;
    }

    public final void p(ArrayList arrayList) {
        va vaVar = this.zzg;
        if (!vaVar.zza()) {
            int size = vaVar.size();
            this.zzg = vaVar.zzf(size + size);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.zzg.zzh(((m5) it.next()).f13472i);
        }
    }
}
