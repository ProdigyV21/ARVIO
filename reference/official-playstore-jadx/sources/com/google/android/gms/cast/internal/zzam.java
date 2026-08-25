package com.google.android.gms.cast.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzam implements zzat {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzar zzb;

    public zzam(zzar zzarVar, zzat zzatVar) {
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
        this.zzb.zzO(null);
        zzat zzatVar = this.zza;
        if (zzatVar != null) {
            zzatVar.zzb(str, j10, i10, obj, j11, j12);
        }
    }
}
