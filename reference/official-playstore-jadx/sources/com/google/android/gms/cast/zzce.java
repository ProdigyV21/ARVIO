package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzce extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzce(RemoteMediaPlayer remoteMediaPlayer, q qVar, MediaQueueItem[] mediaQueueItemArr, int i10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = mediaQueueItemArr;
        this.zzb = i10;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzg().zzD(zzb(), this.zza, this.zzb, 0, -1, -1L, this.zzc);
    }
}
