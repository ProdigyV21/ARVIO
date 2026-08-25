package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzcx extends zzdc {
    final /* synthetic */ RemoteMediaPlayer zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcx(RemoteMediaPlayer remoteMediaPlayer, q qVar) {
        super(remoteMediaPlayer, qVar);
        Objects.requireNonNull(remoteMediaPlayer);
        this.zza = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) {
        this.zza.zzg().zzs(zzb());
    }
}
