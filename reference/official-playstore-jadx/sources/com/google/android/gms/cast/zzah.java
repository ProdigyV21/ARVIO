package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import b4.a;
import com.google.android.gms.tasks.c;
import com.google.android.gms.tasks.h;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzah implements c {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzah(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        Objects.requireNonNull(castRemoteDisplayLocalService);
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.c
    public final void onComplete(h hVar) {
        ServiceConnection serviceConnectionZzq;
        if (!hVar.h()) {
            CastRemoteDisplayLocalService.zzb.e("Connection was not successful", new Object[0]);
            this.zza.zzf();
            return;
        }
        CastRemoteDisplayLocalService.zzb.d("startRemoteDisplay successful", new Object[0]);
        synchronized (CastRemoteDisplayLocalService.zzd) {
            try {
                if (CastRemoteDisplayLocalService.zzv == null) {
                    CastRemoteDisplayLocalService.zzb.d("Remote Display started but session already cancelled", new Object[0]);
                    this.zza.zzf();
                    return;
                }
                Display display = (Display) hVar.f();
                CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
                castRemoteDisplayLocalService.zzd(display);
                CastRemoteDisplayLocalService.zze.set(false);
                Context contextZzo = castRemoteDisplayLocalService.zzo();
                if (contextZzo != null && (serviceConnectionZzq = castRemoteDisplayLocalService.zzq()) != null) {
                    try {
                        a.a().b(contextZzo, serviceConnectionZzq);
                    } catch (IllegalArgumentException unused) {
                        CastRemoteDisplayLocalService.zzb.d("No need to unbind service, already unbound", new Object[0]);
                    }
                }
                CastRemoteDisplayLocalService castRemoteDisplayLocalService2 = this.zza;
                castRemoteDisplayLocalService2.zzr(null);
                castRemoteDisplayLocalService2.zzp(null);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
