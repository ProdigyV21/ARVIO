package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class w1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f13651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f13652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f13653c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Drawable f13654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f13655e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Drawable f13656f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f13657g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Drawable f13658h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f13659i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f13660j = false;

    public w1(ImageView imageView, Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        this.f13651a = imageView;
        this.f13654d = drawable;
        this.f13656f = drawable2;
        this.f13658h = drawable3 != null ? drawable3 : drawable2;
        this.f13655e = context.getString(R.string.cast_play);
        this.f13657g = context.getString(R.string.cast_pause);
        this.f13659i = context.getString(R.string.cast_stop);
        this.f13652b = view;
        this.f13653c = z;
        imageView.setEnabled(false);
    }

    public final void a() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.f13651a.setEnabled(false);
            return;
        }
        if (remoteMediaClient.isPlaying()) {
            if (remoteMediaClient.isLiveStream()) {
                b(this.f13658h, this.f13659i);
                return;
            } else {
                b(this.f13656f, this.f13657g);
                return;
            }
        }
        if (remoteMediaClient.isBuffering()) {
            c(false);
        } else if (remoteMediaClient.isPaused()) {
            b(this.f13654d, this.f13655e);
        } else if (remoteMediaClient.isLoadingNextItem()) {
            c(true);
        }
    }

    public final void b(Drawable drawable, String str) {
        ImageView imageView = this.f13651a;
        boolean zEquals = drawable.equals(imageView.getDrawable());
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(str);
        imageView.setVisibility(0);
        imageView.setEnabled(true);
        View view = this.f13652b;
        if (view != null) {
            view.setVisibility(8);
        }
        if (zEquals || !this.f13660j) {
            return;
        }
        imageView.sendAccessibilityEvent(8);
    }

    public final void c(boolean z) {
        ImageView imageView = this.f13651a;
        this.f13660j = imageView.isAccessibilityFocused();
        View view = this.f13652b;
        if (view != null) {
            view.setVisibility(0);
            if (this.f13660j) {
                view.sendAccessibilityEvent(8);
            }
        }
        imageView.setVisibility(true == this.f13653c ? 4 : 0);
        imageView.setEnabled(!z);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        c(true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13651a.setEnabled(false);
        super.onSessionEnded();
    }
}
