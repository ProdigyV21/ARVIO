package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaNotification;
import com.google.common.collect.o4;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
final class MediaNotificationManager implements Handler.Callback {
    private static final int MSG_USER_ENGAGED_TIMEOUT = 1;
    private static final String TAG = "MediaNtfMng";
    private final MediaNotification.ActionFactory actionFactory;
    private boolean isUserEngaged;
    private MediaNotification mediaNotification;
    private MediaNotification.Provider mediaNotificationProvider;
    private final MediaSessionService mediaSessionService;
    private final androidx.core.app.p1 notificationManagerCompat;
    private final Intent startSelfIntent;
    private int totalNotificationCount;
    private final Handler mainHandler = Util.createHandler(Looper.getMainLooper(), this);
    private final Executor mainExecutor = new o(this, 2);
    private final Map<MediaSession, ControllerInfo> controllerMap = new HashMap();
    private boolean startedInForeground = false;
    private boolean isUserEngagedTimeoutEnabled = true;
    private long userEngagedTimeoutMs = MediaSessionService.DEFAULT_FOREGROUND_SERVICE_TIMEOUT_MS;
    int showNotificationForIdlePlayerMode = 3;

    public static class Api24 {
        private Api24() {
        }

        public static void stopForeground(MediaSessionService mediaSessionService, boolean z) {
            mediaSessionService.stopForeground(z ? 1 : 2);
        }
    }

    public static final class ControllerInfo {
        public final com.google.common.util.concurrent.d1<MediaController> controllerFuture;
        public boolean hasBeenPrepared;
        public boolean wasNotificationDismissed;

        public ControllerInfo(com.google.common.util.concurrent.d1<MediaController> d1Var) {
            this.controllerFuture = d1Var;
        }
    }

    public final class MediaControllerListener implements MediaController.Listener, Player.Listener {
        private final MediaSessionService mediaSessionService;
        private final MediaSession session;

        public MediaControllerListener(MediaSessionService mediaSessionService, MediaSession mediaSession) {
            this.mediaSessionService = mediaSessionService;
            this.session = mediaSession;
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            androidx.media3.common.j.a(this, audioAttributes);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
            androidx.media3.common.j.b(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            androidx.media3.common.j.c(this, commands);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        public void onConnected(boolean z) {
            if (z) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(CueGroup cueGroup) {
            androidx.media3.common.j.d(this, cueGroup);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public com.google.common.util.concurrent.d1<SessionResult> onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
            int i10;
            if (sessionCommand.customAction.equals(MediaNotification.NOTIFICATION_DISMISSED_EVENT_KEY)) {
                MediaNotificationManager.this.onNotificationDismissed(this.session);
                i10 = 0;
            } else {
                i10 = -6;
            }
            return xc.d.s0(new SessionResult(i10));
        }

        @Override // androidx.media3.session.MediaController.Listener
        public final /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
            n.c(this, mediaController, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            androidx.media3.common.j.f(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onDeviceVolumeChanged(int i10, boolean z) {
            androidx.media3.common.j.g(this, i10, z);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onDisconnected(MediaController mediaController) {
            if (this.mediaSessionService.isSessionAdded(this.session)) {
                this.mediaSessionService.removeSession(this.session);
            }
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public final /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
            n.e(this, mediaController, sessionError);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 14, 0)) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }

        @Override // androidx.media3.session.MediaController.Listener
        public final /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
            n.f(this, mediaController, bundle);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsLoadingChanged(boolean z) {
            androidx.media3.common.j.i(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onIsPlayingChanged(boolean z) {
            androidx.media3.common.j.j(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onLoadingChanged(boolean z) {
            androidx.media3.common.j.k(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j10) {
            androidx.media3.common.j.l(this, j10);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i10) {
            androidx.media3.common.j.m(this, mediaItem, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            androidx.media3.common.j.n(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMetadata(Metadata metadata) {
            androidx.media3.common.j.o(this, metadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i10) {
            androidx.media3.common.j.p(this, z, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            androidx.media3.common.j.q(this, playbackParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackStateChanged(int i10) {
            androidx.media3.common.j.r(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
            androidx.media3.common.j.s(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerError(PlaybackException playbackException) {
            androidx.media3.common.j.t(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            androidx.media3.common.j.u(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlayerStateChanged(boolean z, int i10) {
            androidx.media3.common.j.v(this, z, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            androidx.media3.common.j.w(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPositionDiscontinuity(int i10) {
            androidx.media3.common.j.x(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onRenderedFirstFrame() {
            androidx.media3.common.j.z(this);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onRepeatModeChanged(int i10) {
            androidx.media3.common.j.A(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekBackIncrementChanged(long j10) {
            androidx.media3.common.j.B(this, j10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSeekForwardIncrementChanged(long j10) {
            androidx.media3.common.j.C(this, j10);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public final /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
            n.h(this, mediaController, pendingIntent);
        }

        @Override // androidx.media3.session.MediaController.Listener
        public final /* synthetic */ com.google.common.util.concurrent.d1 onSetCustomLayout(MediaController mediaController, List list) {
            return n.i(this, mediaController, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            androidx.media3.common.j.D(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            androidx.media3.common.j.E(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11) {
            androidx.media3.common.j.F(this, i10, i11);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTimelineChanged(Timeline timeline, int i10) {
            androidx.media3.common.j.G(this, timeline, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            androidx.media3.common.j.H(this, trackSelectionParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onTracksChanged(Tracks tracks) {
            androidx.media3.common.j.I(this, tracks);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
            androidx.media3.common.j.J(this, videoSize);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onVolumeChanged(float f10) {
            androidx.media3.common.j.K(this, f10);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(List list) {
            androidx.media3.common.j.e(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
            androidx.media3.common.j.y(this, positionInfo, positionInfo2, i10);
        }
    }

    public MediaNotificationManager(MediaSessionService mediaSessionService, MediaNotification.Provider provider, MediaNotification.ActionFactory actionFactory) {
        this.mediaSessionService = mediaSessionService;
        this.mediaNotificationProvider = provider;
        this.actionFactory = actionFactory;
        this.notificationManagerCompat = new androidx.core.app.p1(mediaSessionService);
        this.startSelfIntent = new Intent(mediaSessionService, mediaSessionService.getClass());
    }

    private MediaController getConnectedControllerForSession(MediaSession mediaSession) {
        ControllerInfo controllerInfo = this.controllerMap.get(mediaSession);
        if (controllerInfo == null || !controllerInfo.controllerFuture.isDone()) {
            return null;
        }
        try {
            return (MediaController) xc.d.j0(controllerInfo.controllerFuture);
        } catch (ExecutionException e5) {
            throw new IllegalStateException(e5);
        }
    }

    private boolean isAnySessionUserEngaged(boolean z) {
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i10 = 0; i10 < sessions.size(); i10++) {
            MediaController connectedControllerForSession = getConnectedControllerForSession(sessions.get(i10));
            if (connectedControllerForSession != null && ((connectedControllerForSession.getPlayWhenReady() || z) && (connectedControllerForSession.getPlaybackState() == 3 || connectedControllerForSession.getPlaybackState() == 2))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$addSession$1(com.google.common.util.concurrent.d1 d1Var, MediaControllerListener mediaControllerListener, MediaSession mediaSession) {
        try {
            MediaController mediaController = (MediaController) d1Var.get(0L, TimeUnit.MILLISECONDS);
            mediaControllerListener.onConnected(shouldShowNotification(mediaSession));
            mediaController.addListener(mediaControllerListener);
        } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException unused) {
            this.mediaSessionService.removeSession(mediaSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Runnable runnable) {
        Util.postOrRun(this.mainHandler, runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCustomAction$3(MediaSession mediaSession, String str, Bundle bundle, MediaController mediaController) {
        if (this.mediaNotificationProvider.handleCustomCommand(mediaSession, str, bundle)) {
            return;
        }
        this.mainExecutor.execute(new c(this, mediaController, str, bundle, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNotification$5(int i10, MediaSession mediaSession, MediaNotification mediaNotification) {
        this.mainExecutor.execute(new x1(i10, 0, this, mediaSession, mediaNotification));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateNotification$7(MediaSession mediaSession, com.google.common.collect.h1 h1Var, MediaNotification.Provider.Callback callback, boolean z) {
        this.mainExecutor.execute(new y1(this, mediaSession, this.mediaNotificationProvider.createNotification(mediaSession, h1Var, this.actionFactory, callback), z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNotificationDismissed(MediaSession mediaSession) {
        ControllerInfo controllerInfo = this.controllerMap.get(mediaSession);
        if (controllerInfo != null) {
            controllerInfo.wasNotificationDismissed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onNotificationUpdated, reason: merged with bridge method [inline-methods] */
    public void lambda$updateNotification$4(int i10, MediaSession mediaSession, MediaNotification mediaNotification) {
        if (i10 == this.totalNotificationCount) {
            lambda$updateNotification$6(mediaSession, mediaNotification, shouldRunInForeground(false));
        }
    }

    private void removeNotification() {
        stopForeground(true);
        MediaNotification mediaNotification = this.mediaNotification;
        if (mediaNotification != null) {
            this.notificationManagerCompat.f2041b.cancel(null, mediaNotification.notificationId);
            this.totalNotificationCount++;
            this.mediaNotification = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendCustomCommandIfCommandIsAvailable, reason: merged with bridge method [inline-methods] */
    public void lambda$onCustomAction$2(MediaController mediaController, final String str, Bundle bundle) {
        SessionCommand sessionCommand;
        o4 o4VarG = mediaController.getAvailableSessionCommands().commands.iterator();
        while (true) {
            if (!o4VarG.hasNext()) {
                sessionCommand = null;
                break;
            }
            sessionCommand = (SessionCommand) o4VarG.next();
            if (sessionCommand.commandCode == 0 && sessionCommand.customAction.equals(str)) {
                break;
            }
        }
        if (sessionCommand != null || CommandButton.isPredefinedCustomCommandButtonCode(str)) {
            xc.d.G(mediaController.sendCustomCommand(new SessionCommand(str, bundle), Bundle.EMPTY), new com.google.common.util.concurrent.v0<SessionResult>() { // from class: androidx.media3.session.MediaNotificationManager.1
                @Override // com.google.common.util.concurrent.v0
                public void onFailure(Throwable th) {
                    Log.w(MediaNotificationManager.TAG, "custom command " + str + " produced an error: " + th.getMessage(), th);
                }

                @Override // com.google.common.util.concurrent.v0
                public void onSuccess(SessionResult sessionResult) {
                }
            }, com.google.common.util.concurrent.s0.f14233i);
        }
    }

    private boolean shouldShowNotification(MediaSession mediaSession) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        if (connectedControllerForSession == null || connectedControllerForSession.getCurrentTimeline().isEmpty()) {
            return false;
        }
        ControllerInfo controllerInfo = this.controllerMap.get(mediaSession);
        controllerInfo.getClass();
        if (connectedControllerForSession.getPlaybackState() != 1) {
            controllerInfo.wasNotificationDismissed = false;
            controllerInfo.hasBeenPrepared = true;
            return true;
        }
        int i10 = this.showNotificationForIdlePlayerMode;
        if (i10 == 1) {
            return !controllerInfo.wasNotificationDismissed;
        }
        if (i10 == 2) {
            return false;
        }
        if (i10 == 3) {
            return !controllerInfo.wasNotificationDismissed && controllerInfo.hasBeenPrepared;
        }
        throw new IllegalStateException();
    }

    private void startForeground(MediaNotification mediaNotification) {
        qb.d.U(this.mediaSessionService, this.startSelfIntent);
        Util.setForegroundServiceNotification(this.mediaSessionService, mediaNotification.notificationId, mediaNotification.notification, 2, "mediaPlayback");
        this.startedInForeground = true;
    }

    private void stopForeground(boolean z) {
        if (Build.VERSION.SDK_INT >= 24) {
            Api24.stopForeground(this.mediaSessionService, z);
        } else {
            this.mediaSessionService.stopForeground(z);
        }
        this.startedInForeground = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateNotificationInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$updateNotification$6(MediaSession mediaSession, MediaNotification mediaNotification, boolean z) {
        mediaNotification.notification.extras.putParcelable("android.mediaSession", mediaSession.getPlatformToken());
        this.mediaNotification = mediaNotification;
        if (z) {
            startForeground(mediaNotification);
        } else {
            this.notificationManagerCompat.a(mediaNotification.notificationId, mediaNotification.notification);
            stopForeground(false);
        }
    }

    public void addSession(MediaSession mediaSession) {
        if (this.controllerMap.containsKey(mediaSession)) {
            return;
        }
        MediaControllerListener mediaControllerListener = new MediaControllerListener(this.mediaSessionService, mediaSession);
        Bundle bundle = new Bundle();
        bundle.putBoolean(MediaController.KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG, true);
        com.google.common.util.concurrent.d1<MediaController> d1VarBuildAsync = new MediaController.Builder(this.mediaSessionService, mediaSession.getToken()).setConnectionHints(bundle).setListener(mediaControllerListener).setApplicationLooper(Looper.getMainLooper()).buildAsync();
        this.controllerMap.put(mediaSession, new ControllerInfo(d1VarBuildAsync));
        d1VarBuildAsync.addListener(new c(this, d1VarBuildAsync, mediaControllerListener, mediaSession, 5), this.mainExecutor);
    }

    public void disableUserEngagedTimeout() {
        this.isUserEngagedTimeoutEnabled = false;
        if (this.mainHandler.hasMessages(1)) {
            this.mainHandler.removeMessages(1);
            List<MediaSession> sessions = this.mediaSessionService.getSessions();
            for (int i10 = 0; i10 < sessions.size(); i10++) {
                this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i10), false);
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i10 = 0; i10 < sessions.size(); i10++) {
            this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i10), false);
        }
        return true;
    }

    public boolean isStartedInForeground() {
        return this.startedInForeground;
    }

    public void onCustomAction(MediaSession mediaSession, String str, Bundle bundle) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        if (connectedControllerForSession == null) {
            return;
        }
        Util.postOrRun(new Handler(mediaSession.getPlayer().getApplicationLooper()), new a(this, mediaSession, str, bundle, connectedControllerForSession, 3));
    }

    public void removeSession(MediaSession mediaSession) {
        ControllerInfo controllerInfoRemove = this.controllerMap.remove(mediaSession);
        if (controllerInfoRemove != null) {
            MediaController.releaseFuture(controllerInfoRemove.controllerFuture);
        }
    }

    public void setMediaNotificationProvider(MediaNotification.Provider provider) {
        this.mediaNotificationProvider = provider;
    }

    public void setShowNotificationForIdlePlayer(int i10) {
        this.showNotificationForIdlePlayerMode = i10;
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        for (int i11 = 0; i11 < sessions.size(); i11++) {
            this.mediaSessionService.onUpdateNotificationInternal(sessions.get(i11), false);
        }
    }

    public void setUserEngagedTimeoutMs(long j10) {
        this.userEngagedTimeoutMs = j10;
    }

    public boolean shouldRunInForeground(boolean z) {
        boolean zIsAnySessionUserEngaged = isAnySessionUserEngaged(z);
        boolean z5 = this.isUserEngagedTimeoutEnabled && this.userEngagedTimeoutMs > 0;
        if (this.isUserEngaged && !zIsAnySessionUserEngaged && z5) {
            this.mainHandler.sendEmptyMessageDelayed(1, this.userEngagedTimeoutMs);
        } else if (zIsAnySessionUserEngaged) {
            this.mainHandler.removeMessages(1);
        }
        this.isUserEngaged = zIsAnySessionUserEngaged;
        return zIsAnySessionUserEngaged || this.mainHandler.hasMessages(1);
    }

    public void updateNotification(MediaSession mediaSession, boolean z) {
        if (!this.mediaSessionService.isSessionAdded(mediaSession) || !shouldShowNotification(mediaSession)) {
            removeNotification();
            return;
        }
        int i10 = this.totalNotificationCount + 1;
        this.totalNotificationCount = i10;
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        connectedControllerForSession.getClass();
        Util.postOrRun(new Handler(mediaSession.getPlayer().getApplicationLooper()), new z1(0, this, mediaSession, connectedControllerForSession.getMediaButtonPreferences(), new x0(this, i10, mediaSession), z));
    }
}
