package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzx extends zzbc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaClient zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(RemoteMediaClient remoteMediaClient, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, long j10, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = mediaQueueItemArr;
        this.zzb = i10;
        this.zzc = i11;
        this.zzd = j10;
        this.zze = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzf = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() {
        this.zzf.zzt().zzC(zzb(), this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
