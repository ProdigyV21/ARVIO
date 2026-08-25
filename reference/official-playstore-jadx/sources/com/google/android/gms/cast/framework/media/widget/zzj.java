package com.google.android.gms.cast.framework.media.widget;

import android.os.Looper;
import androidx.loader.content.j;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import j$.util.Objects;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzj extends TimerTask {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ ExpandedControllerActivity zzb;

    public zzj(ExpandedControllerActivity expandedControllerActivity, RemoteMediaClient remoteMediaClient) {
        this.zza = remoteMediaClient;
        Objects.requireNonNull(expandedControllerActivity);
        this.zzb = expandedControllerActivity;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        new j(Looper.getMainLooper(), 2).post(new zzi(this, this.zza));
    }
}
