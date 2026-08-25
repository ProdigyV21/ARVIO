package com.google.android.gms.internal.cast;

import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.internal.zzt;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class s1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f13600b;

    public /* synthetic */ s1(View view, int i10) {
        this.f13599a = i10;
        this.f13600b = view;
    }

    public void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        View view = this.f13600b;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || remoteMediaClient.isBuffering()) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onMediaStatusUpdated() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        String strZza;
        switch (this.f13599a) {
            case 1:
                a();
                break;
            case 2:
                RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
                if (remoteMediaClient != null && (mediaInfo = remoteMediaClient.getMediaInfo()) != null && (metadata = mediaInfo.getMetadata()) != null && (strZza = zzt.zza(metadata)) != null) {
                    ((TextView) this.f13600b).setText(strZza);
                }
                break;
            default:
                super.onMediaStatusUpdated();
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onSendingRemoteMediaRequest() {
        switch (this.f13599a) {
            case 1:
                this.f13600b.setVisibility(0);
                break;
            default:
                super.onSendingRemoteMediaRequest();
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onSessionConnected(CastSession castSession) {
        switch (this.f13599a) {
            case 0:
                super.onSessionConnected(castSession);
                this.f13600b.setEnabled(true);
                break;
            case 1:
                super.onSessionConnected(castSession);
                a();
                break;
            default:
                super.onSessionConnected(castSession);
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onSessionEnded() {
        switch (this.f13599a) {
            case 0:
                this.f13600b.setEnabled(false);
                super.onSessionEnded();
                break;
            case 1:
                this.f13600b.setVisibility(8);
                super.onSessionEnded();
                break;
            default:
                super.onSessionEnded();
                break;
        }
    }

    public s1(View view) {
        this.f13599a = 0;
        this.f13600b = view;
        view.setEnabled(false);
    }
}
