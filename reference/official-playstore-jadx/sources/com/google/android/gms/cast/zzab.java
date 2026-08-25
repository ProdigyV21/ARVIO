package com.google.android.gms.cast;

import androidx.mediarouter.media.a0;
import androidx.mediarouter.media.i0;
import androidx.mediarouter.media.k0;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzab extends a0 {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzab(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteUnselected(k0 k0Var, i0 i0Var) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        castRemoteDisplayLocalService.zzg("onRouteUnselected");
        if (castRemoteDisplayLocalService.zzm() == null) {
            castRemoteDisplayLocalService.zzg("onRouteUnselected, no device was selected");
            return;
        }
        CastDevice fromBundle = CastDevice.getFromBundle(i0Var.f4403s);
        if (fromBundle != null) {
            if (fromBundle.getDeviceId().equals(castRemoteDisplayLocalService.zzm().getDeviceId())) {
                CastRemoteDisplayLocalService.stopService();
                return;
            }
        }
        castRemoteDisplayLocalService.zzg("onRouteUnselected, device does not match");
    }
}
