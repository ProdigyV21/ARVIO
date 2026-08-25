package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import b4.a;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzad implements ServiceConnection {
    final /* synthetic */ String zza;
    final /* synthetic */ CastDevice zzb;
    final /* synthetic */ CastRemoteDisplayLocalService.Options zzc;
    final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzd;
    final /* synthetic */ Context zze;
    final /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzf;

    public zzad(String str, CastDevice castDevice, CastRemoteDisplayLocalService.Options options, CastRemoteDisplayLocalService.NotificationSettings notificationSettings, Context context, CastRemoteDisplayLocalService.Callbacks callbacks) {
        this.zza = str;
        this.zzb = castDevice;
        this.zzc = options;
        this.zzd = notificationSettings;
        this.zze = context;
        this.zzf = callbacks;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = ((zzaj) iBinder).zza;
        String str = this.zza;
        CastDevice castDevice = this.zzb;
        CastRemoteDisplayLocalService.Options options = this.zzc;
        CastRemoteDisplayLocalService.NotificationSettings notificationSettings = this.zzd;
        Context context = this.zze;
        CastRemoteDisplayLocalService.Callbacks callbacks = this.zzf;
        if (castRemoteDisplayLocalService.zze(str, castDevice, options, notificationSettings, context, this, callbacks)) {
            return;
        }
        CastRemoteDisplayLocalService.zzb.e("Connected but unable to get the service instance", new Object[0]);
        callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED, null, null, null));
        CastRemoteDisplayLocalService.zze.set(false);
        try {
            a.a().b(context, this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzb.d("No need to unbind service, already unbound", new Object[0]);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzb.d("onServiceDisconnected", new Object[0]);
        this.zzf.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected", null, null));
        CastRemoteDisplayLocalService.zze.set(false);
        try {
            a.a().b(this.zze, this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzb.d("No need to unbind service, already unbound", new Object[0]);
        }
    }
}
