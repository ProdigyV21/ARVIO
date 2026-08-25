package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaNotification {
    public static final String NOTIFICATION_DISMISSED_EVENT_KEY = "androidx.media3.session.NOTIFICATION_DISMISSED_EVENT_KEY";
    public final Notification notification;
    public final int notificationId;

    public interface ActionFactory {
        androidx.core.app.x createCustomAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, String str, Bundle bundle);

        androidx.core.app.x createCustomActionFromCustomCommandButton(MediaSession mediaSession, CommandButton commandButton);

        androidx.core.app.x createMediaAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, int i10);

        PendingIntent createMediaActionPendingIntent(MediaSession mediaSession, long j10);

        PendingIntent createNotificationDismissalIntent(MediaSession mediaSession);
    }

    public interface Provider {

        public interface Callback {
            void onNotificationChanged(MediaNotification mediaNotification);
        }

        MediaNotification createNotification(MediaSession mediaSession, com.google.common.collect.h1 h1Var, ActionFactory actionFactory, Callback callback);

        boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle);
    }

    public MediaNotification(int i10, Notification notification) {
        this.notificationId = i10;
        notification.getClass();
        this.notification = notification;
    }
}
