package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;

/* JADX INFO: loaded from: classes4.dex */
public final class n1 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f13483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final zza f13484b;

    public n1(View view, zza zzaVar) {
        this.f13483a = view;
        this.f13484b = zzaVar;
        view.setEnabled(false);
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        boolean z = false;
        View view = this.f13483a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isPlayingAd()) {
            view.setEnabled(false);
            return;
        }
        if (!remoteMediaClient.isLiveStream()) {
            view.setEnabled(true);
            return;
        }
        if (remoteMediaClient.zzj() && !this.f13484b.zze()) {
            z = true;
        }
        view.setEnabled(z);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j10, long j11) {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.f13483a.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, 1000L);
        }
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        this.f13483a.setEnabled(false);
        super.onSessionEnded();
        a();
    }
}
