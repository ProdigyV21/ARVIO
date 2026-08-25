package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzaa extends zzbc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaa(RemoteMediaClient remoteMediaClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = mediaQueueItemArr;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzc = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        JSONObject jSONObject = this.zzb;
        this.zzc.zzt().zzE(zzb(), 0, -1L, this.zza, 0, null, null, jSONObject);
    }
}
