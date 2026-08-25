package com.google.android.gms.cast.framework.media;

import j$.util.Objects;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzj extends TimerTask {
    final /* synthetic */ MediaQueue zza;

    public zzj(MediaQueue mediaQueue) {
        Objects.requireNonNull(mediaQueue);
        this.zza = mediaQueue;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.zza.zzf();
    }
}
