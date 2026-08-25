package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzz extends zzbc {
    final /* synthetic */ MediaQueueItem zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ JSONObject zzd;
    final /* synthetic */ RemoteMediaClient zze;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzz(RemoteMediaClient remoteMediaClient, MediaQueueItem mediaQueueItem, int i10, long j10, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = mediaQueueItem;
        this.zzb = i10;
        this.zzc = j10;
        this.zzd = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zze = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        MediaQueueItem mediaQueueItem = this.zza;
        this.zze.zzt().zzD(zzb(), new MediaQueueItem[]{mediaQueueItem}, this.zzb, 0, 0, this.zzc, this.zzd);
    }
}
