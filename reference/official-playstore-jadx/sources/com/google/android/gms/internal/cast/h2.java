package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;

/* JADX INFO: loaded from: classes4.dex */
public final class h2 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f13277a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final zza f13278b;

    public h2(TextView textView, zza zzaVar) {
        this.f13277a = textView;
        this.f13278b = zzaVar;
        textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        TextView textView = this.f13277a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
            return;
        }
        long approximateStreamPosition = remoteMediaClient.getApproximateStreamPosition();
        if (approximateStreamPosition == MediaInfo.zza) {
            approximateStreamPosition = remoteMediaClient.getStreamDuration();
        }
        textView.setText(this.f13278b.zzm(approximateStreamPosition));
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
        super.onSessionEnded();
        a();
    }
}
