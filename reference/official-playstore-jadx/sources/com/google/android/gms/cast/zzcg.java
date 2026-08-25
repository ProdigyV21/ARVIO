package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcg extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcg(RemoteMediaPlayer remoteMediaPlayer, q qVar, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = mediaQueueItemArr;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzc = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        JSONObject jSONObject = this.zzb;
        this.zzc.zzg().zzE(zzb(), 0, -1L, this.zza, 0, null, null, jSONObject);
    }
}
