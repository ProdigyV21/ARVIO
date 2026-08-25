package com.google.android.gms.cast;

import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcq extends zzdc {
    final /* synthetic */ MediaInfo zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ long[] zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaPlayer zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcq(RemoteMediaPlayer remoteMediaPlayer, q qVar, MediaInfo mediaInfo, boolean z, long j10, long[] jArr, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = mediaInfo;
        this.zzb = z;
        this.zzc = j10;
        this.zzd = jArr;
        this.zze = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzf = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) {
        RemoteMediaPlayer remoteMediaPlayer = this.zzf;
        synchronized (remoteMediaPlayer.zzf()) {
            com.google.android.gms.cast.internal.zzar zzarVarZzg = remoteMediaPlayer.zzg();
            com.google.android.gms.cast.internal.zzat zzatVarZzb = zzb();
            MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
            builder.setMediaInfo(this.zza);
            builder.setAutoplay(Boolean.valueOf(this.zzb));
            builder.setCurrentTime(this.zzc);
            builder.setActiveTrackIds(this.zzd);
            builder.setCustomData(this.zze);
            zzarVarZzg.zzj(zzatVarZzb, builder.build());
        }
    }
}
