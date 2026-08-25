package com.google.android.gms.cast;

import com.google.android.gms.common.api.q;
import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzck extends zzdc {
    final /* synthetic */ JSONObject zza;
    final /* synthetic */ RemoteMediaPlayer zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzck(RemoteMediaPlayer remoteMediaPlayer, q qVar, JSONObject jSONObject) {
        super(remoteMediaPlayer, qVar);
        this.zza = jSONObject;
        Objects.requireNonNull(remoteMediaPlayer);
        this.zzb = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdc
    public final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        this.zzb.zzg().zzE(zzb(), 0, -1L, null, 1, null, null, this.zza);
    }
}
