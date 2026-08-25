package com.google.android.gms.cast.framework.media;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzam extends zzbc {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzam(RemoteMediaClient remoteMediaClient, boolean z, int i10, int i11, int i12) {
        super(remoteMediaClient, true);
        this.zza = i10;
        this.zzb = i11;
        this.zzc = i12;
        Objects.requireNonNull(remoteMediaClient);
        this.zzd = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbc
    public final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzt().zzI(zzb(), this.zza, this.zzb, this.zzc);
    }
}
