package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcf extends zzdc {
    final /* synthetic */ MediaQueueItem zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ JSONObject zzd;
    final /* synthetic */ RemoteMediaPlayer zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcf(RemoteMediaPlayer remoteMediaPlayer, q qVar, MediaQueueItem mediaQueueItem, int i10, long j10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = mediaQueueItem;
        this.zzb = i10;
        this.zzc = j10;
        this.zzd = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zze = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        MediaQueueItem mediaQueueItem = this.zza;
        this.zze.zzg().zzD(zzb(), new MediaQueueItem[]{mediaQueueItem}, this.zzb, 0, 0, this.zzc, this.zzd);
    }
}
