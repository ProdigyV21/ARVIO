package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcd extends zzdc {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaPlayer zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcd(RemoteMediaPlayer remoteMediaPlayer, q qVar, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, long j10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = mediaQueueItemArr;
        this.zzb = i10;
        this.zzc = i11;
        this.zzd = j10;
        this.zze = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzf = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) {
        this.zzf.zzg().zzC(zzb(), this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
