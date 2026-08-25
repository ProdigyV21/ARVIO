package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzco extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzco(RemoteMediaPlayer remoteMediaPlayer, q qVar, int i10, long j10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = i10;
        this.zzb = j10;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        RemoteMediaPlayer remoteMediaPlayer = this.zzd;
        int i10 = this.zza;
        if (remoteMediaPlayer.zza(i10) == -1) {
            setResult(new zzdb(this, new Status(0, null, null, null)));
        } else {
            remoteMediaPlayer.zzg().zzE(zzb(), i10, this.zzb, null, 0, null, null, this.zzc);
        }
    }
}
