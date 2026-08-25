package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzag extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzag(RemoteMediaClient remoteMediaClient, int i10, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = i10;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzc = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzc.zzt().zzE(zzb(), 0, -1L, null, 0, null, Integer.valueOf(this.zza), this.zzb);
    }
}
