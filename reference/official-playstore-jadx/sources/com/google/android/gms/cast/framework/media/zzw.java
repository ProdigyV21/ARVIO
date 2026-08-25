package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.TextTrackStyle;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzw extends zzbc {
    final /* synthetic */ TextTrackStyle zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzw(RemoteMediaClient remoteMediaClient, TextTrackStyle textTrackStyle) {
        super(remoteMediaClient, false);
        this.zza = textTrackStyle;
        Objects.requireNonNull(remoteMediaClient);
        this.zzb = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzb.zzt().zzu(zzb(), this.zza);
    }
}
