package com.google.android.gms.cast.framework.media.internal;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzc extends zzh {
    final /* synthetic */ zzd zza;

    public /* synthetic */ zzc(zzd zzdVar, byte[] bArr) {
        Objects.requireNonNull(zzdVar);
        this.zza = zzdVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzi
    public final void zzb(long j10, long j11) {
        this.zza.publishProgress(Long.valueOf(j10), Long.valueOf(j11));
    }
}
