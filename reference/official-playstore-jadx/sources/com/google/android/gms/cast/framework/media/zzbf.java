package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzbf extends TimerTask {
    final /* synthetic */ zzbg zza;

    public zzbf(zzbg zzbgVar) {
        Objects.requireNonNull(zzbgVar);
        this.zza = zzbgVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzbg zzbgVar = this.zza;
        RemoteMediaClient remoteMediaClient = zzbgVar.zza;
        remoteMediaClient.zzo(zzbgVar.zzh());
        remoteMediaClient.zzs().postDelayed(this, zzbgVar.zzi());
    }
}
