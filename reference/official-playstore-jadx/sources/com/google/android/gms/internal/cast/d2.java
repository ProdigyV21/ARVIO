package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: loaded from: classes4.dex */
public final class d2 extends e2 implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f13227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f13228c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f13229d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f13230e = true;

    public d2(TextView textView, long j10, String str) {
        this.f13227b = textView;
        this.f13228c = j10;
        this.f13229d = str;
    }

    @Override // com.google.android.gms.internal.cast.e2
    public final void a(long j10) {
        this.f13227b.setText(DateUtils.formatElapsedTime(j10 / 1000));
    }

    @Override // com.google.android.gms.internal.cast.e2
    public final void b(boolean z) {
        this.f13230e = z;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j10, long j11) {
        if (this.f13230e) {
            if (j10 == -1000) {
                j10 = j11;
            }
            this.f13227b.setText(DateUtils.formatElapsedTime(j10 / 1000));
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.f13228c);
            boolean zHasMediaSession = remoteMediaClient.hasMediaSession();
            TextView textView = this.f13227b;
            if (zHasMediaSession) {
                textView.setText(DateUtils.formatElapsedTime(remoteMediaClient.getApproximateStreamPosition() / 1000));
            } else {
                textView.setText(this.f13229d);
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13227b.setText(this.f13229d);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
    }
}
