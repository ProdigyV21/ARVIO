package com.google.android.gms.internal.cast;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.cast.framework.media.uicontroller.zza;

/* JADX INFO: loaded from: classes4.dex */
public final class g2 extends UIController implements RemoteMediaClient.ProgressListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f13256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ImageView f13257b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final zza f13258c;

    public g2(View view, zza zzaVar) {
        TextView textView = (TextView) view.findViewById(R.id.live_indicator_text);
        this.f13256a = textView;
        ImageView imageView = (ImageView) view.findViewById(R.id.live_indicator_dot);
        this.f13257b = imageView;
        this.f13258c = zzaVar;
        TypedArray typedArrayObtainStyledAttributes = imageView.getContext().obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CastExpandedController_castLiveIndicatorColor, 0);
        typedArrayObtainStyledAttributes.recycle();
        imageView.getDrawable().setColorFilter(imageView.getContext().getResources().getColor(resourceId), PorterDuff.Mode.SRC_IN);
        textView.setVisibility(8);
        imageView.setVisibility(8);
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        ImageView imageView = this.f13257b;
        TextView textView = this.f13256a;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream()) {
            textView.setVisibility(8);
            imageView.setVisibility(8);
        } else {
            boolean zIsPlaying = !remoteMediaClient.zzj() ? remoteMediaClient.isPlaying() : this.f13258c.zze();
            textView.setVisibility(0);
            imageView.setVisibility(true == zIsPlaying ? 0 : 8);
            l6.a(m5.CAF_EXPANDED_CONTROLLER_WITH_LIVE_CONTENT);
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
