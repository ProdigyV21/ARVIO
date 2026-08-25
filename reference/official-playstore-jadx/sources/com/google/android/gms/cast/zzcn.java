package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcn extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcn(RemoteMediaPlayer remoteMediaPlayer, q qVar, int i10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = i10;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzc = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        RemoteMediaPlayer remoteMediaPlayer = this.zzc;
        int i10 = this.zza;
        if (remoteMediaPlayer.zza(i10) == -1) {
            setResult(new zzdb(this, new Status(0, null, null, null)));
        } else {
            remoteMediaPlayer.zzg().zzF(zzb(), new int[]{i10}, this.zzb);
        }
    }
}
