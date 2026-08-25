package com.google.android.gms.internal.cast;

import android.view.View;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class b2 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13194a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f13195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f13196c;

    public /* synthetic */ b2(View view, int i10, int i11, boolean z) {
        this.f13194a = i11;
        this.f13195b = view;
        this.f13196c = i10;
    }

    public void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        View view = this.f13195b;
        if (remoteMediaClient == null || !remoteMediaClient.zzm() || remoteMediaClient.isPlayingAd()) {
            view.setVisibility(this.f13196c);
            view.setEnabled(false);
        } else {
            view.setVisibility(0);
            view.setEnabled(true);
        }
    }

    public void b() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        View view = this.f13195b;
        if (remoteMediaClient == null || !remoteMediaClient.zzl() || remoteMediaClient.isPlayingAd()) {
            view.setVisibility(this.f13196c);
            view.setEnabled(false);
        } else {
            view.setVisibility(0);
            view.setEnabled(true);
        }
    }

    public void c() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        int i10 = this.f13196c;
        View view = this.f13195b;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            view.setVisibility(i10);
            return;
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        com.google.android.gms.common.internal.t.i(mediaStatus);
        if (mediaStatus.getPreloadedItemId() == 0) {
            view.setVisibility(i10);
        } else {
            view.setVisibility(0);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        switch (this.f13194a) {
            case 0:
                a();
                break;
            case 1:
                b();
                break;
            case 2:
                c();
                break;
            default:
                RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
                View view = this.f13195b;
                if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(this.f13196c);
                }
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onSendingRemoteMediaRequest() {
        switch (this.f13194a) {
            case 0:
                this.f13195b.setEnabled(false);
                break;
            case 1:
                this.f13195b.setEnabled(false);
                break;
            default:
                super.onSendingRemoteMediaRequest();
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        switch (this.f13194a) {
            case 0:
                super.onSessionConnected(castSession);
                a();
                break;
            case 1:
                super.onSessionConnected(castSession);
                b();
                break;
            case 2:
                super.onSessionConnected(castSession);
                c();
                break;
            default:
                super.onSessionConnected(castSession);
                RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
                View view = this.f13195b;
                if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
                    view.setVisibility(0);
                } else {
                    view.setVisibility(this.f13196c);
                }
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        switch (this.f13194a) {
            case 0:
                this.f13195b.setEnabled(false);
                super.onSessionEnded();
                break;
            case 1:
                this.f13195b.setEnabled(false);
                super.onSessionEnded();
                break;
            case 2:
                this.f13195b.setVisibility(this.f13196c);
                super.onSessionEnded();
                break;
            default:
                this.f13195b.setVisibility(this.f13196c);
                super.onSessionEnded();
                break;
        }
    }

    public b2(View view, int i10, int i11) {
        this.f13194a = i11;
        switch (i11) {
            case 1:
                this.f13195b = view;
                this.f13196c = i10;
                view.setEnabled(false);
                break;
            default:
                this.f13195b = view;
                this.f13196c = i10;
                view.setEnabled(false);
                break;
        }
    }
}
