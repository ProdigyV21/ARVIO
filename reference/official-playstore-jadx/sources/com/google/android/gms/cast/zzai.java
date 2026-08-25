package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.h;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzai implements c {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzai(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.c
    public final void onComplete(h hVar) {
        if (hVar.h()) {
            this.zza.zzg("remote display stopped");
        } else {
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
            castRemoteDisplayLocalService.zzg("Unable to stop the remote display, result unsuccessful");
            CastRemoteDisplayLocalService.Callbacks callbacks = (CastRemoteDisplayLocalService.Callbacks) castRemoteDisplayLocalService.zzl().get();
            if (callbacks != null) {
                callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED, null, null, null));
            }
        }
        this.zza.zzn(null);
    }
}
