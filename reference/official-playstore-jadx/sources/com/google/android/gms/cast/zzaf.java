package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzaf implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zza;
    final /* synthetic */ CastRemoteDisplayLocalService zzb;

    public zzaf(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zza = notificationSettings;
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zzb = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzc(this.zza);
    }
}
