package androidx.media3.session;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.C;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaStyleNotificationHelper;
import j$.util.Objects;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultMediaNotificationProvider implements MediaNotification.Provider {
    public static final String COMMAND_KEY_COMPACT_VIEW_INDEX = "androidx.media3.session.command.COMPACT_VIEW_INDEX";
    public static final String DEFAULT_CHANNEL_ID = "default_channel_id";
    public static final int DEFAULT_CHANNEL_NAME_RESOURCE_ID = R.string.default_notification_channel_name;
    public static final int DEFAULT_NOTIFICATION_ID = 1001;
    public static final String GROUP_KEY = "media3_group_key";
    private static final String TAG = "NotificationProvider";
    private final String channelId;
    private final int channelNameResourceId;
    private final Context context;
    private final NotificationIdProvider notificationIdProvider;
    private final NotificationManager notificationManager;
    private OnBitmapLoadedFutureCallback pendingOnBitmapLoadedFutureCallback;
    private int smallIconResourceId;

    public static class Api26 {
        private Api26() {
        }

        public static void createNotificationChannel(NotificationManager notificationManager, String str, String str2) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 2);
            if (Build.VERSION.SDK_INT <= 27) {
                notificationChannel.setShowBadge(false);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static class Api31 {
        private Api31() {
        }

        public static void setForegroundServiceBehavior(androidx.core.app.d0 d0Var) {
            d0Var.z = 1;
        }
    }

    public static final class Builder {
        private boolean built;
        private final Context context;
        private NotificationIdProvider notificationIdProvider = new v(24);
        private String channelId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_ID;
        private int channelNameResourceId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_NAME_RESOURCE_ID;

        public Builder(Context context) {
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
            return 1001;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ int lambda$setNotificationId$1(int i10, MediaSession mediaSession) {
            return i10;
        }

        public DefaultMediaNotificationProvider build() {
            ac.b.s(!this.built);
            DefaultMediaNotificationProvider defaultMediaNotificationProvider = new DefaultMediaNotificationProvider(this);
            this.built = true;
            return defaultMediaNotificationProvider;
        }

        public Builder setChannelId(String str) {
            this.channelId = str;
            return this;
        }

        public Builder setChannelName(int i10) {
            this.channelNameResourceId = i10;
            return this;
        }

        public Builder setNotificationId(int i10) {
            this.notificationIdProvider = new z(i10, 8);
            return this;
        }

        public Builder setNotificationIdProvider(NotificationIdProvider notificationIdProvider) {
            this.notificationIdProvider = notificationIdProvider;
            return this;
        }
    }

    public interface NotificationIdProvider {
        int getNotificationId(MediaSession mediaSession);
    }

    public static class OnBitmapLoadedFutureCallback implements com.google.common.util.concurrent.v0<Bitmap> {
        private final androidx.core.app.d0 builder;
        private boolean discarded;
        private final int notificationId;
        private final MediaNotification.Provider.Callback onNotificationChangedCallback;

        public OnBitmapLoadedFutureCallback(int i10, androidx.core.app.d0 d0Var, MediaNotification.Provider.Callback callback) {
            this.notificationId = i10;
            this.builder = d0Var;
            this.onNotificationChangedCallback = callback;
        }

        public void discardIfPending() {
            this.discarded = true;
        }

        @Override // com.google.common.util.concurrent.v0
        public void onFailure(Throwable th) {
            if (this.discarded) {
                return;
            }
            Log.w(DefaultMediaNotificationProvider.TAG, DefaultMediaNotificationProvider.getBitmapLoadErrorMessage(th));
        }

        @Override // com.google.common.util.concurrent.v0
        public void onSuccess(Bitmap bitmap) {
            if (this.discarded) {
                return;
            }
            this.builder.d(bitmap);
            this.onNotificationChangedCallback.onNotificationChanged(new MediaNotification(this.notificationId, this.builder.a()));
        }
    }

    private void ensureNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26 || this.notificationManager.getNotificationChannel(this.channelId) != null) {
            return;
        }
        Api26.createNotificationChannel(this.notificationManager, this.channelId, this.context.getString(this.channelNameResourceId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }

    private static long getPlaybackStartTimeEpochMs(Player player) {
        return (!player.isPlaying() || player.isPlayingAd() || player.isCurrentMediaItemDynamic() || player.getPlaybackParameters().speed != 1.0f) ? C.TIME_UNSET : System.currentTimeMillis() - player.getContentPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
        return 1001;
    }

    public int[] addNotificationActions(MediaSession mediaSession, com.google.common.collect.h1 h1Var, androidx.core.app.d0 d0Var, MediaNotification.ActionFactory actionFactory) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[3];
        Arrays.fill(iArr, -1);
        Arrays.fill(iArr2, -1);
        boolean z = false;
        for (int i10 = 0; i10 < h1Var.size(); i10++) {
            CommandButton commandButton = (CommandButton) h1Var.get(i10);
            if (commandButton.sessionCommand != null) {
                androidx.core.app.x xVarCreateCustomActionFromCustomCommandButton = actionFactory.createCustomActionFromCustomCommandButton(mediaSession, commandButton);
                if (xVarCreateCustomActionFromCustomCommandButton != null) {
                    d0Var.f1963b.add(xVarCreateCustomActionFromCustomCommandButton);
                } else {
                    d0Var.getClass();
                }
            } else {
                ac.b.s(commandButton.playerCommand != -1);
                androidx.core.app.x xVarCreateMediaAction = actionFactory.createMediaAction(mediaSession, IconCompat.d(this.context, commandButton.iconResId), commandButton.displayName, commandButton.playerCommand);
                if (xVarCreateMediaAction != null) {
                    d0Var.f1963b.add(xVarCreateMediaAction);
                } else {
                    d0Var.getClass();
                }
            }
            int i11 = commandButton.extras.getInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
            if (i11 >= 0 && i11 < 3) {
                iArr[i11] = i10;
                z = true;
            } else if (commandButton.slots.a(0) == 2) {
                iArr2[0] = i10;
            } else if (commandButton.slots.a(0) == 1) {
                iArr2[1] = i10;
            } else if (commandButton.slots.a(0) == 3) {
                iArr2[2] = i10;
            }
        }
        if (!z) {
            int i12 = 0;
            for (int i13 = 0; i13 < 3; i13++) {
                int i14 = iArr2[i13];
                if (i14 != -1) {
                    iArr[i12] = i14;
                    i12++;
                }
            }
        }
        for (int i15 = 0; i15 < 3; i15++) {
            if (iArr[i15] == -1) {
                return Arrays.copyOf(iArr, i15);
            }
        }
        return iArr;
    }

    @Override // androidx.media3.session.MediaNotification.Provider
    public final MediaNotification createNotification(MediaSession mediaSession, com.google.common.collect.h1 h1Var, MediaNotification.ActionFactory actionFactory, MediaNotification.Provider.Callback callback) {
        ensureNotificationChannel();
        Player player = mediaSession.getPlayer();
        androidx.core.app.d0 d0Var = new androidx.core.app.d0(this.context, this.channelId);
        int notificationId = this.notificationIdProvider.getNotificationId(mediaSession);
        MediaStyleNotificationHelper.MediaStyle mediaStyle = new MediaStyleNotificationHelper.MediaStyle(mediaSession);
        mediaStyle.setShowActionsInCompactView(addNotificationActions(mediaSession, getMediaButtons(mediaSession, player.getAvailableCommands(), h1Var, !Util.shouldShowPlayButton(player, mediaSession.getShowPlayButtonIfPlaybackIsSuppressed())), d0Var, actionFactory));
        if (player.isCommandAvailable(18)) {
            MediaMetadata mediaMetadata = player.getMediaMetadata();
            d0Var.f1966e = androidx.core.app.d0.b(getNotificationContentTitle(mediaMetadata));
            d0Var.f1967f = androidx.core.app.d0.b(getNotificationContentText(mediaMetadata));
            com.google.common.util.concurrent.d1<Bitmap> d1VarLoadBitmapFromMetadata = mediaSession.getBitmapLoader().loadBitmapFromMetadata(mediaMetadata);
            if (d1VarLoadBitmapFromMetadata != null) {
                OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback = this.pendingOnBitmapLoadedFutureCallback;
                if (onBitmapLoadedFutureCallback != null) {
                    onBitmapLoadedFutureCallback.discardIfPending();
                }
                if (d1VarLoadBitmapFromMetadata.isDone()) {
                    try {
                        d0Var.d((Bitmap) xc.d.j0(d1VarLoadBitmapFromMetadata));
                    } catch (CancellationException | ExecutionException e5) {
                        Log.w(TAG, getBitmapLoadErrorMessage(e5));
                    }
                } else {
                    OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback2 = new OnBitmapLoadedFutureCallback(notificationId, d0Var, callback);
                    this.pendingOnBitmapLoadedFutureCallback = onBitmapLoadedFutureCallback2;
                    Handler applicationHandler = mediaSession.getImpl().getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    xc.d.G(d1VarLoadBitmapFromMetadata, onBitmapLoadedFutureCallback2, new androidx.media3.common.util.d(applicationHandler));
                }
            }
        }
        long playbackStartTimeEpochMs = getPlaybackStartTimeEpochMs(player);
        boolean z = playbackStartTimeEpochMs != C.TIME_UNSET;
        if (!z) {
            playbackStartTimeEpochMs = 0;
        }
        d0Var.B.when = playbackStartTimeEpochMs;
        d0Var.f1971j = z;
        d0Var.k = z;
        if (Build.VERSION.SDK_INT >= 31) {
            Api31.setForegroundServiceBehavior(d0Var);
        }
        d0Var.f1968g = mediaSession.getSessionActivity();
        d0Var.B.deleteIntent = actionFactory.createNotificationDismissalIntent(mediaSession);
        d0Var.c(8, true);
        d0Var.B.icon = this.smallIconResourceId;
        d0Var.e(mediaStyle);
        d0Var.f1983w = 1;
        d0Var.c(2, false);
        d0Var.f1977q = GROUP_KEY;
        return new MediaNotification(notificationId, d0Var.a());
    }

    public com.google.common.collect.h1 getMediaButtons(MediaSession mediaSession, Player.Commands commands, com.google.common.collect.h1 h1Var, boolean z) {
        com.google.common.collect.h1 customLayoutFromMediaButtonPreferences = CommandButton.getCustomLayoutFromMediaButtonPreferences(h1Var, true, true);
        boolean zContainsButtonForSlot = CommandButton.containsButtonForSlot(customLayoutFromMediaButtonPreferences, 2);
        boolean zContainsButtonForSlot2 = CommandButton.containsButtonForSlot(customLayoutFromMediaButtonPreferences, 3);
        com.google.common.collect.e1 e1Var = new com.google.common.collect.e1(4);
        int i10 = 0;
        if (zContainsButtonForSlot) {
            e1Var.c((CommandButton) customLayoutFromMediaButtonPreferences.get(0));
            i10 = 1;
        } else if (commands.containsAny(7, 6)) {
            e1Var.c(new CommandButton.Builder(CommandButton.ICON_PREVIOUS).setPlayerCommand(6).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_previous_description)).build());
        }
        if (commands.contains(1)) {
            if (z) {
                e1Var.c(new CommandButton.Builder(CommandButton.ICON_PAUSE).setPlayerCommand(1).setDisplayName(this.context.getString(R.string.media3_controls_pause_description)).build());
            } else {
                e1Var.c(new CommandButton.Builder(CommandButton.ICON_PLAY).setPlayerCommand(1).setDisplayName(this.context.getString(R.string.media3_controls_play_description)).build());
            }
        }
        if (zContainsButtonForSlot2) {
            e1Var.c((CommandButton) customLayoutFromMediaButtonPreferences.get(i10));
            i10++;
        } else if (commands.containsAny(9, 8)) {
            e1Var.c(new CommandButton.Builder(CommandButton.ICON_NEXT).setPlayerCommand(8).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_next_description)).build());
        }
        while (i10 < customLayoutFromMediaButtonPreferences.size()) {
            e1Var.c((CommandButton) customLayoutFromMediaButtonPreferences.get(i10));
            i10++;
        }
        return e1Var.f();
    }

    public CharSequence getNotificationContentText(MediaMetadata mediaMetadata) {
        return mediaMetadata.artist;
    }

    public CharSequence getNotificationContentTitle(MediaMetadata mediaMetadata) {
        return mediaMetadata.title;
    }

    @Override // androidx.media3.session.MediaNotification.Provider
    public final boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle) {
        return false;
    }

    public final void setSmallIcon(int i10) {
        this.smallIconResourceId = i10;
    }

    public DefaultMediaNotificationProvider(Context context) {
        this(context, new v(23), DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME_RESOURCE_ID);
    }

    public DefaultMediaNotificationProvider(Context context, NotificationIdProvider notificationIdProvider, String str, int i10) {
        this.context = context;
        this.notificationIdProvider = notificationIdProvider;
        this.channelId = str;
        this.channelNameResourceId = i10;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        notificationManager.getClass();
        this.notificationManager = notificationManager;
        this.smallIconResourceId = R.drawable.media3_notification_small_icon;
    }

    private DefaultMediaNotificationProvider(Builder builder) {
        this(builder.context, builder.notificationIdProvider, builder.channelId, builder.channelNameResourceId);
    }
}
