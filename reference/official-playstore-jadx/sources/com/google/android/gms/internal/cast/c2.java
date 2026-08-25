package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class c2 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f13217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f13219c;

    public c2(TextView textView, String str, View view) {
        this.f13217a = textView;
        this.f13218b = str;
        this.f13219c = view;
    }

    public final void a(long j10, boolean z) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        String str = this.f13218b;
        View view = this.f13219c;
        TextView textView = this.f13217a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            textView.setVisibility(0);
            textView.setText(str);
            if (view != null) {
                view.setVisibility(4);
                return;
            }
            return;
        }
        if (remoteMediaClient.isLiveStream()) {
            textView.setText(str);
            if (view != null) {
                textView.setVisibility(4);
                view.setVisibility(0);
                return;
            }
            return;
        }
        if (z) {
            j10 = remoteMediaClient.getStreamDuration();
        }
        textView.setVisibility(0);
        textView.setText(DateUtils.formatElapsedTime(j10 / 1000));
        if (view != null) {
            view.setVisibility(4);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a(-1L, true);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j10, long j11) {
        a(j11, false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, 1000L);
        }
        a(-1L, true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13217a.setText(this.f13218b);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
    }
}
