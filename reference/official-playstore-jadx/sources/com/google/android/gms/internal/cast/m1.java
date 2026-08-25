package com.google.android.gms.internal.cast;

import android.content.Context;
import android.view.View;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class m1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f13437a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f13439c;

    public m1(Context context, View view) {
        this.f13437a = view;
        this.f13438b = context.getString(R.string.cast_closed_captions);
        this.f13439c = context.getString(R.string.cast_closed_captions_unavailable);
        view.setEnabled(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r9 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r9.getRemoteMediaClient()
            android.view.View r1 = r9.f13437a
            r2 = 0
            if (r0 == 0) goto L55
            boolean r3 = r0.hasMediaSession()
            if (r3 == 0) goto L55
            com.google.android.gms.cast.MediaInfo r3 = r0.getMediaInfo()
            if (r3 != 0) goto L16
            goto L55
        L16:
            java.util.List r3 = r3.getMediaTracks()
            if (r3 == 0) goto L55
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L55
            java.util.Iterator r3 = r3.iterator()
            r4 = r2
        L27:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L55
            java.lang.Object r5 = r3.next()
            com.google.android.gms.cast.MediaTrack r5 = (com.google.android.gms.cast.MediaTrack) r5
            int r6 = r5.getType()
            r7 = 2
            r8 = 1
            if (r6 != r7) goto L40
            int r4 = r4 + 1
            if (r4 <= r8) goto L27
            goto L46
        L40:
            int r5 = r5.getType()
            if (r5 != r8) goto L27
        L46:
            boolean r0 = r0.isPlayingAd()
            if (r0 != 0) goto L55
            r1.setEnabled(r8)
            java.lang.String r0 = r9.f13438b
            r1.setContentDescription(r0)
            return
        L55:
            r1.setEnabled(r2)
            java.lang.String r0 = r9.f13439c
            r1.setContentDescription(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.m1.a():void");
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.f13437a.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.f13437a.setEnabled(true);
        a();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.f13437a.setEnabled(false);
        super.onSessionEnded();
    }
}
