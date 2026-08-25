package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzcz implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaPlayer zza;
    private q zzb;
    private long zzc;

    public zzcz(RemoteMediaPlayer remoteMediaPlayer) {
        Objects.requireNonNull(remoteMediaPlayer);
        this.zza = remoteMediaPlayer;
        this.zzc = 0L;
    }

    public final void zza(q qVar) {
        this.zzb = qVar;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String str, String str2, long j10, String str3) {
        q qVar = this.zzb;
        if (qVar == null) {
            throw new IllegalStateException("No GoogleApiClient available");
        }
        Cast.CastApi.sendMessage(qVar, str, str2).setResultCallback(new zzcy(this, j10));
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zzc() {
        long j10 = this.zzc + 1;
        this.zzc = j10;
        return j10;
    }
}
