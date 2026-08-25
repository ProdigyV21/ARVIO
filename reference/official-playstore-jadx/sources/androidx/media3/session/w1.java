package androidx.media3.session;

import android.app.PendingIntent;
import androidx.media3.session.MediaNotification;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class w1 {
    public static PendingIntent a(MediaNotification.ActionFactory actionFactory, MediaSession mediaSession) {
        return actionFactory.createMediaActionPendingIntent(mediaSession, 3L);
    }
}
