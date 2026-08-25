package com.google.android.gms.internal.cast;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class k6 extends pa implements jb {
    private static final k6 zzm;
    private int zzb;
    private w6 zzd;
    private long zze;
    private int zzf;
    private xa zzg;
    private xa zzh;
    private xa zzi;
    private xa zzj;
    private xa zzk;
    private int zzl;

    static {
        k6 k6Var = new k6();
        zzm = k6Var;
        pa.i(k6.class, k6Var);
    }

    public k6() {
        pb pbVar = pb.f13547o;
        this.zzg = pbVar;
        this.zzh = pbVar;
        this.zzi = pbVar;
        this.zzj = pbVar;
        this.zzk = pbVar;
    }

    public static j6 n() {
        return (j6) zzm.e();
    }

    @Override // com.google.android.gms.internal.cast.pa
    public final Object k(int i10, pa paVar) {
        int i11 = i10 - 1;
        if (i11 == 0) {
            return (byte) 1;
        }
        if (i11 == 2) {
            return new qb(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0005\u0000\u0001ဉ\u0000\u0002စ\u0001\u0003᠌\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007\u001b\b\u001b\tင\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", k5.f13379o, "zzg", i6.class, "zzh", e6.class, "zzi", p6.class, "zzj", n6.class, "zzk", g6.class, "zzl"});
        }
        if (i11 == 3) {
            return new k6();
        }
        if (i11 == 4) {
            return new j6(zzm);
        }
        if (i11 == 5) {
            return zzm;
        }
        throw null;
    }

    public final /* synthetic */ void o(w6 w6Var) {
        this.zzd = w6Var;
        this.zzb |= 1;
    }

    public final /* synthetic */ void p(long j10) {
        this.zzb |= 2;
        this.zze = j10;
    }

    public final void q(ArrayList arrayList) {
        xa xaVar = this.zzg;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzg = xaVar.zzf(size + size);
        }
        ba.d(arrayList, this.zzg);
    }

    public final void r(ArrayList arrayList) {
        xa xaVar = this.zzh;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzh = xaVar.zzf(size + size);
        }
        ba.d(arrayList, this.zzh);
    }

    public final void s(ArrayList arrayList) {
        xa xaVar = this.zzi;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzi = xaVar.zzf(size + size);
        }
        ba.d(arrayList, this.zzi);
    }

    public final void t(ArrayList arrayList) {
        xa xaVar = this.zzj;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzj = xaVar.zzf(size + size);
        }
        ba.d(arrayList, this.zzj);
    }

    public final void u(ArrayList arrayList) {
        xa xaVar = this.zzk;
        if (!xaVar.zza()) {
            int size = xaVar.size();
            this.zzk = xaVar.zzf(size + size);
        }
        ba.d(arrayList, this.zzk);
    }

    public final /* synthetic */ void v(int i10) {
        this.zzb |= 8;
        this.zzl = i10;
    }
}
