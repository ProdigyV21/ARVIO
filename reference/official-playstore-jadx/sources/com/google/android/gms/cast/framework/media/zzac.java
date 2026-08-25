package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzac extends zzbc {
    final /* synthetic */ int[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzac(RemoteMediaClient remoteMediaClient, int[] iArr, int i10, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = iArr;
        this.zzb = i10;
        this.zzc = jSONObject;
        Objects.requireNonNull(remoteMediaClient);
        this.zzd = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzt().zzG(zzb(), this.zza, this.zzb, this.zzc);
    }
}
