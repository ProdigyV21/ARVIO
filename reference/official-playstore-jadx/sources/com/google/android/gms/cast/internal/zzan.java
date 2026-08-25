package com.google.android.gms.cast.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzan implements zzat {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzar zzb;

    public zzan(zzar zzarVar, zzat zzatVar) {
        this.zza = zzatVar;
        Objects.requireNonNull(zzarVar);
        this.zzb = zzarVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j10, long j11, long j12) {
        zzat zzatVar = this.zza;
        if (zzatVar != null) {
            zzatVar.zza(str, j10, j11, j12);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j10, int i10, Object obj, long j11, long j12) {
        int i11;
        String str2;
        long j13;
        Object obj2;
        long j14;
        long j15;
        zzat zzatVar = this.zza;
        if (zzatVar != null) {
            if (i10 == 2001) {
                zzar zzarVar = this.zzb;
                zzarVar.zza.w("Possibility of local queue out of sync with receiver queue. Refetching sequence number. Current Local Sequence Number = %d", Integer.valueOf(zzarVar.zzQ()));
                zzarVar.zzP().zzm();
                i11 = 2001;
                j13 = j10;
                obj2 = obj;
                j14 = j11;
                j15 = j12;
                str2 = str;
            } else {
                i11 = i10;
                str2 = str;
                j13 = j10;
                obj2 = obj;
                j14 = j11;
                j15 = j12;
            }
            zzatVar.zzb(str2, j13, i11, obj2, j14, j15);
        }
    }
}
