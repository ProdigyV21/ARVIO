package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzaq extends zzbc {
    final /* synthetic */ JSONObject zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaq(RemoteMediaClient remoteMediaClient, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzb = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzb.zzt().zzl(zzb(), this.zza);
    }
}
