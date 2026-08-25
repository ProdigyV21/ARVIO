package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaLoadRequestData;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzan extends zzbc {
    final /* synthetic */ MediaLoadRequestData zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzan(RemoteMediaClient remoteMediaClient, MediaLoadRequestData mediaLoadRequestData) {
        super(remoteMediaClient, false);
        this.zza = mediaLoadRequestData;
        Objects.requireNonNull(remoteMediaClient);
        this.zzb = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzb.zzt().zzj(zzb(), this.zza);
    }
}
