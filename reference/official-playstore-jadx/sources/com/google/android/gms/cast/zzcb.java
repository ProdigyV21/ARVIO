package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzcb extends zzdc {
    final /* synthetic */ long[] zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcb(RemoteMediaPlayer remoteMediaPlayer, q qVar, long[] jArr) {
        super(remoteMediaPlayer, qVar);
        this.zza = jArr;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzb = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        this.zzb.zzg().zzt(zzb(), this.zza);
    }
}
