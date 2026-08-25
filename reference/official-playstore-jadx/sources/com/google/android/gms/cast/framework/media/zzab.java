package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzab extends zzbc {
    final /* synthetic */ int[] zza;
    final /* synthetic */ JSONObject zzb;
    final /* synthetic */ RemoteMediaClient zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzab(RemoteMediaClient remoteMediaClient, int[] iArr, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = iArr;
        this.zzb = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzc = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzc.zzt().zzF(zzb(), this.zza, this.zzb);
    }
}
