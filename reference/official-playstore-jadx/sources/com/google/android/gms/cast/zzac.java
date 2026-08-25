package com.google.android.gms.cast;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzac implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzac(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        boolean zZzs = castRemoteDisplayLocalService.zzs();
        StringBuilder sb2 = new StringBuilder(String.valueOf(zZzs).length() + 54);
        sb2.append("onCreate after delay. The local service been started: ");
        sb2.append(zZzs);
        castRemoteDisplayLocalService.zzg(sb2.toString());
        if (castRemoteDisplayLocalService.zzs()) {
            return;
        }
        castRemoteDisplayLocalService.zzh("The local service has not been been started, stopping it");
        castRemoteDisplayLocalService.stopSelf();
    }
}
