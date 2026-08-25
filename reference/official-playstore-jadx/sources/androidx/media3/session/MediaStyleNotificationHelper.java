package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;

/* JADX INFO: loaded from: classes3.dex */
public class MediaStyleNotificationHelper {
    public static final String EXTRA_MEDIA3_SESSION = "androidx.media3.session";

    public static class Api24Impl {
        private Api24Impl() {
        }

        public static Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle() {
            return new Notification.DecoratedMediaCustomViewStyle();
        }
    }

    public static class Api34Impl {
        private Api34Impl() {
        }

        public static Notification.MediaStyle setRemotePlaybackInfo(Notification.MediaStyle mediaStyle, CharSequence charSequence, int i10, PendingIntent pendingIntent) {
            mediaStyle.setRemotePlaybackInfo(charSequence, i10, pendingIntent);
            return mediaStyle;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        public DecoratedMediaCustomViewStyle(MediaSession mediaSession) {
            super(mediaSession);
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            androidx.core.app.d0 d0Var = this.mBuilder;
            int color = d0Var.f1982v;
            if (color == 0) {
                color = d0Var.f1962a.getResources().getColor(R.color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle, androidx.core.app.y0
        public void apply(androidx.core.app.t tVar) {
            CharSequence charSequence;
            int i10 = Build.VERSION.SDK_INT;
            if (i10 < 24) {
                super.apply(tVar);
                return;
            }
            Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle = Api24Impl.createDecoratedMediaCustomViewStyle();
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle.setShowActionsInCompactView(iArr);
            }
            if (i10 >= 34 && (charSequence = this.remoteDeviceName) != null) {
                Api34Impl.setRemotePlaybackInfo(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle, charSequence, this.remoteDeviceIconRes, this.remoteDeviceIntent);
                tVar.a().setStyle(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle);
            } else {
                tVar.a().setStyle(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                tVar.a().addExtras(bundle);
            }
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle
        public int getBigContentViewLayoutResource(int i10) {
            return i10 <= 3 ? R.layout.media3_notification_template_big_media_narrow_custom : R.layout.media3_notification_template_big_media_custom;
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle
        public int getContentViewLayoutResource() {
            this.mBuilder.getClass();
            return super.getContentViewLayoutResource();
        }

        @Override // androidx.core.app.y0
        public RemoteViews makeBigContentView(androidx.core.app.t tVar) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            this.mBuilder.getClass();
            this.mBuilder.getClass();
            return null;
        }

        @Override // androidx.core.app.y0
        public RemoteViews makeContentView(androidx.core.app.t tVar) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            this.mBuilder.getClass();
            this.mBuilder.getClass();
            return null;
        }

        @Override // androidx.core.app.y0
        public RemoteViews makeHeadsUpContentView(androidx.core.app.t tVar) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            this.mBuilder.getClass();
            this.mBuilder.getClass();
            return null;
        }
    }

    public static class MediaStyle extends androidx.core.app.y0 {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] actionsToShowInCompact;
        int remoteDeviceIconRes;
        PendingIntent remoteDeviceIntent;
        CharSequence remoteDeviceName;
        final MediaSession session;

        public MediaStyle(MediaSession mediaSession) {
            this.session = mediaSession;
        }

        private RemoteViews generateMediaActionButton(androidx.core.app.x xVar) {
            boolean z = xVar.f2074h == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.f1962a.getPackageName(), R.layout.media3_notification_media_action);
            IconCompat iconCompatA = xVar.a();
            if (iconCompatA != null) {
                remoteViews.setImageViewResource(R.id.action0, iconCompatA.f());
            }
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action0, xVar.f2074h);
            }
            remoteViews.setContentDescription(R.id.action0, xVar.f2073g);
            return remoteViews;
        }

        public static SessionToken getSessionToken(Notification notification) {
            Bundle bundle;
            Bundle bundle2 = notification.extras;
            if (bundle2 == null || (bundle = bundle2.getBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION)) == null) {
                return null;
            }
            return SessionToken.fromBundle(bundle);
        }

        @Override // androidx.core.app.y0
        public void apply(androidx.core.app.t tVar) {
            CharSequence charSequence;
            Notification.MediaStyle mediaSession = new Notification.MediaStyle().setMediaSession(this.session.getPlatformToken());
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                mediaSession.setShowActionsInCompactView(iArr);
            }
            if (Build.VERSION.SDK_INT >= 34 && (charSequence = this.remoteDeviceName) != null) {
                Api34Impl.setRemotePlaybackInfo(mediaSession, charSequence, this.remoteDeviceIconRes, this.remoteDeviceIntent);
                tVar.a().setStyle(mediaSession);
            } else {
                tVar.a().setStyle(mediaSession);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                tVar.a().addExtras(bundle);
            }
        }

        public RemoteViews generateBigContentView() {
            int iMin = Math.min(this.mBuilder.f1963b.size(), 5);
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(iMin), false);
            remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
            if (iMin > 0) {
                for (int i10 = 0; i10 < iMin; i10++) {
                    remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton((androidx.core.app.x) this.mBuilder.f1963b.get(i10)));
                }
            }
            return remoteViewsApplyStandardTemplate;
        }

        public RemoteViews generateContentView() {
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.f1963b.size();
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                int iMin = Math.min(iArr.length, 3);
                remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
                if (iMin > 0) {
                    for (int i10 = 0; i10 < iMin; i10++) {
                        if (i10 >= size) {
                            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i10), Integer.valueOf(size - 1)));
                        }
                        remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton((androidx.core.app.x) this.mBuilder.f1963b.get(iArr[i10])));
                    }
                }
            }
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
            return remoteViewsApplyStandardTemplate;
        }

        public int getBigContentViewLayoutResource(int i10) {
            return i10 <= 3 ? R.layout.media3_notification_template_big_media_narrow : R.layout.media3_notification_template_big_media;
        }

        public int getContentViewLayoutResource() {
            return R.layout.media3_notification_template_media;
        }

        @Deprecated
        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            return this;
        }

        public MediaStyle setRemotePlaybackInfo(CharSequence charSequence, int i10, PendingIntent pendingIntent) {
            ac.b.j(charSequence != null);
            this.remoteDeviceName = charSequence;
            this.remoteDeviceIconRes = i10;
            this.remoteDeviceIntent = pendingIntent;
            return this;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.actionsToShowInCompact = iArr;
            return this;
        }

        @Deprecated
        public MediaStyle setShowCancelButton(boolean z) {
            return this;
        }
    }

    private MediaStyleNotificationHelper() {
    }
}
