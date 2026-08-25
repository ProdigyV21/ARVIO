package com.google.android.gms.cast;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzae implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ CastRemoteDisplayLocalService zzb;

    public zzae(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zza = z;
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zzb = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
