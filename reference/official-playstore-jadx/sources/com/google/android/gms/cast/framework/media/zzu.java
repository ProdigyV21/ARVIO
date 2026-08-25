package com.google.android.gms.cast.framework.media;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzu extends zzbc {
    final /* synthetic */ RemoteMediaClient zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzu(RemoteMediaClient remoteMediaClient) {
        super(remoteMediaClient, false);
        Objects.requireNonNull(remoteMediaClient);
        this.zza = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zza.zzt().zzs(zzb());
    }
}
