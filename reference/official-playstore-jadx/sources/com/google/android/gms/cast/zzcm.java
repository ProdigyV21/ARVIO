package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcm extends zzdc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaPlayer zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcm(RemoteMediaPlayer remoteMediaPlayer, q qVar, int i10, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = i10;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzc = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        this.zzc.zzg().zzE(zzb(), 0, -1L, null, 0, null, Integer.valueOf(this.zza), this.zzb);
    }
}
