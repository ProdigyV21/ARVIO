package com.google.android.gms.internal.cast;

import android.widget.ProgressBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class x1 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ProgressBar f13680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13681b;

    public x1(ProgressBar progressBar, long j10) {
        this.f13680a = progressBar;
        this.f13681b = j10;
        progressBar.setMax(1);
        progressBar.setProgress(0);
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        ProgressBar progressBar = this.f13680a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isLiveStream()) {
            progressBar.setMax(1);
            progressBar.setProgress(0);
        } else {
            progressBar.setMax((int) remoteMediaClient.getStreamDuration());
            progressBar.setProgress((int) remoteMediaClient.getApproximateStreamPosition());
        }
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
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.f13681b);
        }
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        a();
    }
}
