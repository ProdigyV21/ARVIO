package androidx.media3.session;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.C;
import androidx.media3.session.MediaNotification;

/* JADX INFO: loaded from: classes3.dex */
final class DefaultActionFactory implements MediaNotification.ActionFactory {
    private static final String ACTION_CUSTOM = "androidx.media3.session.CUSTOM_NOTIFICATION_ACTION";
    private static final String EXTRAS_KEY_ACTION_CUSTOM = "androidx.media3.session.EXTRAS_KEY_CUSTOM_NOTIFICATION_ACTION";
    public static final String EXTRAS_KEY_ACTION_CUSTOM_EXTRAS = "androidx.media3.session.EXTRAS_KEY_CUSTOM_NOTIFICATION_ACTION_EXTRAS";
    private int customActionPendingIntentRequestCode = 0;
    private final Service service;

    public static final class Api26 {
        private Api26() {
        }

        public static PendingIntent createForegroundServicePendingIntent(Service service, int i10, Intent intent) {
            return PendingIntent.getForegroundService(service, i10, intent, C.BUFFER_FLAG_NOT_DEPENDED_ON);
        }
    }

    public DefaultActionFactory(Service service) {
        this.service = service;
    }

    private PendingIntent createCustomActionPendingIntent(MediaSession mediaSession, String str, Bundle bundle) {
        Intent intent = new Intent(ACTION_CUSTOM);
        intent.setData(mediaSession.getImpl().getUri());
        Service service = this.service;
        intent.setComponent(new ComponentName(service, service.getClass()));
        intent.putExtra(EXTRAS_KEY_ACTION_CUSTOM, str);
        intent.putExtra(EXTRAS_KEY_ACTION_CUSTOM_EXTRAS, bundle);
        Service service2 = this.service;
        int i10 = this.customActionPendingIntentRequestCode + 1;
        this.customActionPendingIntentRequestCode = i10;
        return PendingIntent.getService(service2, i10, intent, 201326592);
    }

    public static KeyEvent getKeyEvent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey("android.intent.extra.KEY_EVENT")) {
            return null;
        }
        return (KeyEvent) extras.getParcelable("android.intent.extra.KEY_EVENT");
    }

    private Intent getMediaButtonIntent(MediaSession mediaSession, int i10) {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setData(mediaSession.getImpl().getUri());
        Service service = this.service;
        intent.setComponent(new ComponentName(service, service.getClass()));
        intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i10));
        return intent;
    }

    private int toKeyCode(long j10) {
        if (j10 == 8 || j10 == 9) {
            return 87;
        }
        if (j10 == 6 || j10 == 7) {
            return 88;
        }
        if (j10 == 3) {
            return 86;
        }
        if (j10 == 12) {
            return 90;
        }
        if (j10 == 11) {
            return 89;
        }
        return j10 == 1 ? 85 : 0;
    }

    @Override // androidx.media3.session.MediaNotification.ActionFactory
    public androidx.core.app.x createCustomAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, String str, Bundle bundle) {
        return new androidx.core.app.x(iconCompat, charSequence, createCustomActionPendingIntent(mediaSession, str, bundle));
    }

    @Override // androidx.media3.session.MediaNotification.ActionFactory
    public androidx.core.app.x createCustomActionFromCustomCommandButton(MediaSession mediaSession, CommandButton commandButton) {
        SessionCommand sessionCommand = commandButton.sessionCommand;
        ac.b.j(sessionCommand != null && sessionCommand.commandCode == 0);
        SessionCommand sessionCommand2 = commandButton.sessionCommand;
        sessionCommand2.getClass();
        return new androidx.core.app.x(IconCompat.d(this.service, commandButton.iconResId), commandButton.displayName, createCustomActionPendingIntent(mediaSession, sessionCommand2.customAction, sessionCommand2.customExtras));
    }

    @Override // androidx.media3.session.MediaNotification.ActionFactory
    public androidx.core.app.x createMediaAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, int i10) {
        return new androidx.core.app.x(iconCompat, charSequence, createMediaActionPendingIntent(mediaSession, i10));
    }

    @Override // androidx.media3.session.MediaNotification.ActionFactory
    public PendingIntent createMediaActionPendingIntent(MediaSession mediaSession, long j10) {
        int keyCode = toKeyCode(j10);
        Intent mediaButtonIntent = getMediaButtonIntent(mediaSession, keyCode);
        return (Build.VERSION.SDK_INT < 26 || j10 != 1 || mediaSession.getPlayer().getPlayWhenReady()) ? PendingIntent.getService(this.service, keyCode, mediaButtonIntent, C.BUFFER_FLAG_NOT_DEPENDED_ON) : Api26.createForegroundServicePendingIntent(this.service, keyCode, mediaButtonIntent);
    }

    @Override // androidx.media3.session.MediaNotification.ActionFactory
    public PendingIntent createNotificationDismissalIntent(MediaSession mediaSession) {
        return PendingIntent.getService(this.service, 86, getMediaButtonIntent(mediaSession, 86).putExtra(MediaNotification.NOTIFICATION_DISMISSED_EVENT_KEY, true), C.BUFFER_FLAG_NOT_DEPENDED_ON);
    }

    public String getCustomAction(Intent intent) {
        Bundle extras = intent.getExtras();
        Object obj = extras != null ? extras.get(EXTRAS_KEY_ACTION_CUSTOM) : null;
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public Bundle getCustomActionExtras(Intent intent) {
        Bundle extras = intent.getExtras();
        Object obj = extras != null ? extras.get(EXTRAS_KEY_ACTION_CUSTOM_EXTRAS) : null;
        return obj instanceof Bundle ? (Bundle) obj : Bundle.EMPTY;
    }

    public boolean isCustomAction(Intent intent) {
        return ACTION_CUSTOM.equals(intent.getAction());
    }

    public boolean isMediaAction(Intent intent) {
        return "android.intent.action.MEDIA_BUTTON".equals(intent.getAction());
    }
}
