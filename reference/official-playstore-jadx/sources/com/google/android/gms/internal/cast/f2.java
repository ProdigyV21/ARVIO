package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;

/* JADX INFO: loaded from: classes4.dex */
public final class f2 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f13249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final zza f13250b;

    public f2(TextView textView, zza zzaVar) {
        this.f13249a = textView;
        this.f13250b = zzaVar;
        textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        TextView textView = this.f13249a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
            return;
        }
        boolean zIsLiveStream = remoteMediaClient.isLiveStream();
        zza zzaVar = this.f13250b;
        if (zIsLiveStream && zzaVar.zzj() == null) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(zzaVar.zzm(zzaVar.zzh() + ((long) zzaVar.zzb())));
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
