package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class zzi implements Runnable {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ zzj zzb;

    public zzi(zzj zzjVar, RemoteMediaClient remoteMediaClient) {
        this.zza = remoteMediaClient;
        Objects.requireNonNull(zzjVar);
        this.zzb = zzjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zze(this.zza);
    }
}
