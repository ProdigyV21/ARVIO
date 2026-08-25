package com.google.android.gms.internal.cast;

import android.content.Context;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class v1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f13631a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f13633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f13634d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public u1 f13635e;

    public v1(ImageView imageView, Context context) {
        this.f13631a = imageView;
        Context applicationContext = context.getApplicationContext();
        this.f13634d = applicationContext;
        this.f13632b = applicationContext.getString(R.string.cast_mute);
        this.f13633c = applicationContext.getString(R.string.cast_unmute);
        imageView.setEnabled(false);
        this.f13635e = null;
    }

    public final void a() {
        CastSession currentCastSession = CastContext.getSharedInstance(this.f13634d).getSessionManager().getCurrentCastSession();
        ImageView imageView = this.f13631a;
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            imageView.setEnabled(false);
            return;
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            imageView.setEnabled(false);
        } else {
            imageView.setEnabled(true);
        }
        boolean zIsMute = currentCastSession.isMute();
        imageView.setSelected(zIsMute);
        imageView.setContentDescription(zIsMute ? this.f13633c : this.f13632b);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.f13631a.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        if (this.f13635e == null) {
            this.f13635e = new u1(this);
        }
        castSession.addCastListener(this.f13635e);
        super.onSessionConnected(castSession);
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        u1 u1Var;
        this.f13631a.setEnabled(false);
        CastSession currentCastSession = CastContext.getSharedInstance(this.f13634d).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && (u1Var = this.f13635e) != null) {
            currentCastSession.removeCastListener(u1Var);
        }
        super.onSessionEnded();
    }
}
