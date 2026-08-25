package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.android.gms.cast.CredentialsData;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
class MediaSessionImpl {
    private static final String ANDROID_AUTOMOTIVE_LAUNCHER_PACKAGE_NAME = "com.android.car.carlauncher";
    private static final String ANDROID_AUTOMOTIVE_MEDIA_PACKAGE_NAME = "com.android.car.media";
    private static final String ANDROID_AUTO_PACKAGE_NAME = "com.google.android.projection.gearhead";
    private static final long DEFAULT_SESSION_POSITION_UPDATE_DELAY_MS = 3000;
    private static final String SYSTEM_UI_PACKAGE_NAME = "com.android.systemui";
    public static final String TAG = "MediaSessionImpl";
    private static final String WRONG_THREAD_ERROR_MESSAGE = "Player callback method is called from a wrong thread. See javadoc of MediaSession for details.";
    private final Handler applicationHandler;
    private final androidx.media3.common.util.BitmapLoader bitmapLoader;
    private MediaSessionServiceLegacyStub browserServiceLegacyStub;
    private final MediaSession.Callback callback;
    private boolean closed;
    private final com.google.common.collect.h1 commandButtonsForMediaItems;
    private final Context context;
    private MediaSession.ControllerInfo controllerForCurrentRequest;
    private com.google.common.collect.h1 customLayout;
    private final MediaSession instance;
    private boolean isMediaNotificationControllerConnected;
    private final boolean isPeriodicPositionUpdateEnabled;
    private final Object lock = new Object();
    private final Handler mainHandler;
    private com.google.common.collect.h1 mediaButtonPreferences;
    private final MediaPlayPauseKeyHandler mediaPlayPauseKeyHandler;
    private MediaSession.Listener mediaSessionListener;
    private final PlayerInfoChangedHandler onPlayerInfoChangedHandler;
    private final Runnable periodicSessionPositionInfoUpdateRunnable;
    private final boolean playIfSuppressed;
    private PlaybackException playbackException;
    private PlayerInfo playerInfo;
    private PlayerListener playerListener;
    private PlayerWrapper playerWrapper;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final String sessionId;
    private final MediaSessionLegacyStub sessionLegacyStub;
    private long sessionPositionUpdateDelayMs;
    private final MediaSessionStub sessionStub;
    private final SessionToken sessionToken;
    private final Uri sessionUri;
    private final boolean useLegacySurfaceHandling;
    private static final SessionResult RESULT_WHEN_CLOSED = new SessionResult(1);
    private static final com.google.common.base.c0<Integer> mediaMetadataBitmapMaxSize = com.google.common.util.concurrent.p0.v(new i2(0));

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements com.google.common.util.concurrent.v0<MediaSession.MediaItemsWithStartPosition> {
        final /* synthetic */ boolean val$callOnPlayerInteractionFinished;
        final /* synthetic */ MediaSession.ControllerInfo val$controllerForRequest;
        final /* synthetic */ Player.Commands val$playCommand;

        public AnonymousClass1(MediaSession.ControllerInfo controllerInfo, boolean z, Player.Commands commands) {
            this.val$controllerForRequest = controllerInfo;
            this.val$callOnPlayerInteractionFinished = z;
            this.val$playCommand = commands;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
            MediaUtils.setMediaItemsWithStartIndexAndPosition(MediaSessionImpl.this.playerWrapper, mediaItemsWithStartPosition);
            Util.handlePlayButtonAction(MediaSessionImpl.this.playerWrapper);
            if (z) {
                MediaSessionImpl.this.onPlayerInteractionFinishedOnHandler(controllerInfo, commands);
            }
        }

        @Override // com.google.common.util.concurrent.v0
        public void onFailure(Throwable th) {
            if (th instanceof UnsupportedOperationException) {
                Log.w(MediaSessionImpl.TAG, "UnsupportedOperationException: Make sure to implement MediaSession.Callback.onPlaybackResumption() if you add a media button receiver to your manifest or if you implement the recent media item contract with your MediaLibraryService.", th);
            } else {
                Log.e(MediaSessionImpl.TAG, "Failure calling MediaSession.Callback.onPlaybackResumption(): " + th.getMessage(), th);
            }
            Util.handlePlayButtonAction(MediaSessionImpl.this.playerWrapper);
            if (this.val$callOnPlayerInteractionFinished) {
                MediaSessionImpl.this.onPlayerInteractionFinishedOnHandler(this.val$controllerForRequest, this.val$playCommand);
            }
        }

        @Override // com.google.common.util.concurrent.v0
        public void onSuccess(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
            MediaSessionImpl mediaSessionImpl = MediaSessionImpl.this;
            MediaSession.ControllerInfo controllerInfo = this.val$controllerForRequest;
            mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new z1(this, mediaItemsWithStartPosition, this.val$callOnPlayerInteractionFinished, controllerInfo, this.val$playCommand)).run();
        }
    }

    public class MediaPlayPauseKeyHandler extends Handler {
        private Runnable playPauseTask;

        public MediaPlayPauseKeyHandler(Looper looper) {
            super(looper);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void lambda$setPendingPlayPauseTask$0(MediaSession.ControllerInfo controllerInfo, KeyEvent keyEvent) {
            if (MediaSessionImpl.this.isMediaNotificationController(controllerInfo)) {
                MediaSessionImpl.this.applyMediaButtonKeyEvent(keyEvent, false, false);
            } else {
                MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionImpl.this.sessionLegacyStub;
                MediaSessionManager.RemoteUserInfo remoteUserInfo = controllerInfo.getRemoteUserInfo();
                remoteUserInfo.getClass();
                mediaSessionLegacyStub.handleMediaPlayPauseOnHandler(remoteUserInfo);
            }
            this.playPauseTask = null;
        }

        public Runnable clearPendingPlayPauseTask() {
            Runnable runnable = this.playPauseTask;
            if (runnable == null) {
                return null;
            }
            removeCallbacks(runnable);
            Runnable runnable2 = this.playPauseTask;
            this.playPauseTask = null;
            return runnable2;
        }

        public void flush() {
            Runnable runnableClearPendingPlayPauseTask = clearPendingPlayPauseTask();
            if (runnableClearPendingPlayPauseTask != null) {
                Util.postOrRun(this, runnableClearPendingPlayPauseTask);
            }
        }

        public boolean hasPendingPlayPauseTask() {
            return this.playPauseTask != null;
        }

        public void setPendingPlayPauseTask(MediaSession.ControllerInfo controllerInfo, KeyEvent keyEvent) {
            s1 s1Var = new s1(this, controllerInfo, keyEvent, 4);
            this.playPauseTask = s1Var;
            postDelayed(s1Var, ViewConfiguration.getDoubleTapTimeout());
        }
    }

    public class PlayerInfoChangedHandler extends Handler {
        private static final int MSG_PLAYER_INFO_CHANGED = 1;
        private boolean excludeTimeline;
        private boolean excludeTracks;

        public PlayerInfoChangedHandler(Looper looper) {
            super(looper);
            this.excludeTimeline = true;
            this.excludeTracks = true;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                throw new IllegalStateException("Invalid message what=" + message.what);
            }
            MediaSessionImpl mediaSessionImpl = MediaSessionImpl.this;
            mediaSessionImpl.playerInfo = mediaSessionImpl.playerInfo.copyWithTimelineAndSessionPositionInfo(MediaSessionImpl.this.getPlayerWrapper().getCurrentTimelineWithCommandCheck(), MediaSessionImpl.this.getPlayerWrapper().createSessionPositionInfo(), MediaSessionImpl.this.playerInfo.timelineChangeReason);
            MediaSessionImpl mediaSessionImpl2 = MediaSessionImpl.this;
            mediaSessionImpl2.dispatchOnPlayerInfoChanged(mediaSessionImpl2.playerInfo, this.excludeTimeline, this.excludeTracks);
            this.excludeTimeline = true;
            this.excludeTracks = true;
        }

        public boolean hasPendingPlayerInfoChangedUpdate() {
            return hasMessages(1);
        }

        public void sendPlayerInfoChangedMessage(boolean z, boolean z5) {
            boolean z10 = false;
            this.excludeTimeline = this.excludeTimeline && z;
            if (this.excludeTracks && z5) {
                z10 = true;
            }
            this.excludeTracks = z10;
            if (hasMessages(1)) {
                return;
            }
            sendEmptyMessage(1);
        }
    }

    public static class PlayerListener implements Player.Listener {
        private final WeakReference<PlayerWrapper> player;
        private final WeakReference<MediaSessionImpl> session;

        public PlayerListener(MediaSessionImpl mediaSessionImpl, PlayerWrapper playerWrapper) {
            this.session = new WeakReference<>(mediaSessionImpl);
            this.player = new WeakReference<>(playerWrapper);
        }

        private MediaSessionImpl getSession() {
            return this.session.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onPlaybackStateChanged$4(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerCb controllerCb, int i11) throws RemoteException {
            controllerCb.onPlaybackStateChanged(i11, i10, playerWrapper.getPlayerError());
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithAudioAttributes(audioAttributes);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new c0(audioAttributes));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAudioSessionIdChanged(int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithAudioSessionId(i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new z(i10, 3));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAvailableCommandsChanged(Player.Commands commands) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.handleAvailablePlayerCommandsChanged(commands);
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onCues(List list) {
            androidx.media3.common.j.e(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithDeviceInfo(deviceInfo);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new k1(deviceInfo, 3));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceVolumeChanged(int i10, boolean z) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithDeviceVolume(i10, z);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new k2(i10, z));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onEvents(Player player, Player.Events events) {
            androidx.media3.common.j.h(this, player, events);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsLoadingChanged(boolean z) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithIsLoading(z);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new r0(z, 1));
            session.schedulePeriodicSessionPositionInfoChanges();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean z) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithIsPlaying(z);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new r0(z, 3));
            session.schedulePeriodicSessionPositionInfoChanges();
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onLoadingChanged(boolean z) {
            androidx.media3.common.j.k(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMaxSeekToPreviousPositionChanged(long j10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithMaxSeekToPreviousPositionMs(j10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaItemTransition(MediaItem mediaItem, int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithMediaItemTransitionReason(i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new l1(mediaItem, i10, 6));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithMediaMetadata(mediaMetadata);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new i0(2, mediaMetadata));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onMetadata(Metadata metadata) {
            androidx.media3.common.j.o(this, metadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean z, int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPlayWhenReady(z, i10, session.playerInfo.playbackSuppressionReason);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new k2(z, i10, 1));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPlaybackParameters(playbackParameters);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new f0(2, playbackParameters));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            PlayerWrapper playerWrapper = this.player.get();
            if (playerWrapper == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPlaybackState(i10, playerWrapper.getPlayerError());
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new l1(i10, playerWrapper, 4));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackSuppressionReasonChanged(int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPlayWhenReady(session.playerInfo.playWhenReady, session.playerInfo.playWhenReadyChangeReason, i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new z(i10, 1));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException playbackException) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPlayerError(playbackException);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new n0(4, playbackException));
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
        public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            session.playerInfo = session.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new i0(1, mediaMetadata));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onPositionDiscontinuity(int i10) {
            androidx.media3.common.j.x(this, i10);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            ConnectedControllersManager<IBinder> connectedControllersManager = session.sessionStub.getConnectedControllersManager();
            com.google.common.collect.h1 connectedControllers = connectedControllersManager.getConnectedControllers();
            for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
                MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
                if (connectedControllersManager.getPlaybackException(controllerInfo) == null) {
                    session.dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new v(4));
                }
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRepeatModeChanged(int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithRepeatMode(i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new z(i10, 2));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSeekBackIncrementChanged(long j10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithSeekBackIncrement(j10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new j2(j10, 1));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSeekForwardIncrementChanged(long j10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithSeekForwardIncrement(j10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new j2(j10, 0));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onShuffleModeEnabledChanged(boolean z) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithShuffleModeEnabled(z);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new r0(z, 2));
        }

        @Override // androidx.media3.common.Player.Listener
        public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            androidx.media3.common.j.E(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSurfaceSizeChanged(int i10, int i11) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.dispatchRemoteControllerTaskWithoutReturn(new u(i10, i11, 2));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline timeline, int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            PlayerWrapper playerWrapper = this.player.get();
            if (playerWrapper == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithTimelineAndSessionPositionInfo(timeline, playerWrapper.createSessionPositionInfo(), i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new l1(timeline, i10, 5));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskWithoutReturn(new b0(trackSelectionParameters));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks tracks) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithCurrentTracks(tracks);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, false);
            session.dispatchRemoteControllerTaskWithoutReturn(new k1(tracks, 4));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize videoSize) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            session.playerInfo = session.playerInfo.copyWithVideoSize(videoSize);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new k1(videoSize, 5));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVolumeChanged(float f10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            session.playerInfo = session.playerInfo.copyWithVolume(f10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new t(f10, 2));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup cueGroup) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = new PlayerInfo.Builder(session.playerInfo).setCues(cueGroup).build();
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
            MediaSessionImpl session = getSession();
            if (session == null) {
                return;
            }
            session.verifyApplicationThread();
            if (this.player.get() == null) {
                return;
            }
            session.playerInfo = session.playerInfo.copyWithPositionInfos(positionInfo, positionInfo2, i10);
            session.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
            session.dispatchRemoteControllerTaskToLegacyStub(new x0(positionInfo, positionInfo2, i10));
        }
    }

    public interface RemoteControllerTask {
        void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException;
    }

    public MediaSessionImpl(MediaSession mediaSession, Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, boolean z10) {
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.instance = mediaSession;
        this.context = context;
        this.sessionId = str;
        this.sessionActivity = pendingIntent;
        this.customLayout = h1Var;
        this.mediaButtonPreferences = h1Var2;
        this.commandButtonsForMediaItems = h1Var3;
        this.callback = callback;
        this.sessionExtras = bundle2;
        this.bitmapLoader = bitmapLoader;
        this.playIfSuppressed = z;
        this.isPeriodicPositionUpdateEnabled = z5;
        this.useLegacySurfaceHandling = z10;
        MediaSessionStub mediaSessionStub = new MediaSessionStub(this);
        this.sessionStub = mediaSessionStub;
        this.mainHandler = new Handler(Looper.getMainLooper());
        Looper applicationLooper = player.getApplicationLooper();
        Handler handler = new Handler(applicationLooper);
        this.applicationHandler = handler;
        this.playerInfo = PlayerInfo.DEFAULT;
        this.onPlayerInfoChangedHandler = new PlayerInfoChangedHandler(applicationLooper);
        this.mediaPlayPauseKeyHandler = new MediaPlayPauseKeyHandler(applicationLooper);
        Uri uriBuild = new Uri.Builder().scheme(MediaSessionImpl.class.getName()).appendPath(str).appendPath(String.valueOf(SystemClock.elapsedRealtime())).build();
        this.sessionUri = uriBuild;
        MediaSession.ConnectionResult connectionResultBuild = new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession).build();
        MediaSessionLegacyStub mediaSessionLegacyStub = new MediaSessionLegacyStub(this, uriBuild, handler, bundle, z, h1Var, h1Var2, connectionResultBuild.availableSessionCommands, connectionResultBuild.availablePlayerCommands, bundle2);
        this.sessionLegacyStub = mediaSessionLegacyStub;
        this.sessionToken = new SessionToken(Process.myUid(), 0, MediaLibraryInfo.VERSION_INT, 8, context.getPackageName(), mediaSessionStub, bundle, mediaSessionLegacyStub.getSessionCompat().getSessionToken().getToken());
        PlayerWrapper playerWrapper = new PlayerWrapper(player);
        this.playerWrapper = playerWrapper;
        Util.postOrRun(handler, new g(this, playerWrapper, 7));
        this.sessionPositionUpdateDelayMs = 3000L;
        this.periodicSessionPositionInfoUpdateRunnable = new h2(this, 1);
        Util.postOrRun(handler, new h2(this, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean applyMediaButtonKeyEvent(android.view.KeyEvent r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            androidx.media3.session.MediaSession r0 = r3.instance
            androidx.media3.session.MediaSession$ControllerInfo r0 = r0.getMediaNotificationControllerInfo()
            r0.getClass()
            int r4 = r4.getKeyCode()
            r1 = 85
            r2 = 79
            if (r4 == r1) goto L15
            if (r4 != r2) goto L19
        L15:
            if (r5 == 0) goto L19
            r4 = 87
        L19:
            if (r4 == r2) goto L63
            r5 = 126(0x7e, float:1.77E-43)
            if (r4 == r5) goto L5c
            r5 = 127(0x7f, float:1.78E-43)
            if (r4 == r5) goto L55
            r5 = 272(0x110, float:3.81E-43)
            if (r4 == r5) goto L4e
            r5 = 273(0x111, float:3.83E-43)
            if (r4 == r5) goto L47
            switch(r4) {
                case 85: goto L63;
                case 86: goto L3f;
                case 87: goto L4e;
                case 88: goto L47;
                case 89: goto L37;
                case 90: goto L30;
                default: goto L2e;
            }
        L2e:
            r4 = 0
            return r4
        L30:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 7
            r4.<init>(r3, r0, r5)
            goto L7a
        L37:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 8
            r4.<init>(r3, r0, r5)
            goto L7a
        L3f:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 9
            r4.<init>(r3, r0, r5)
            goto L7a
        L47:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 6
            r4.<init>(r3, r0, r5)
            goto L7a
        L4e:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 5
            r4.<init>(r3, r0, r5)
            goto L7a
        L55:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 4
            r4.<init>(r3, r0, r5)
            goto L7a
        L5c:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 3
            r4.<init>(r3, r0, r5)
            goto L7a
        L63:
            androidx.media3.session.PlayerWrapper r4 = r3.getPlayerWrapper()
            boolean r4 = r4.getPlayWhenReady()
            if (r4 == 0) goto L74
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 1
            r4.<init>(r3, r0, r5)
            goto L7a
        L74:
            androidx.media3.session.d r4 = new androidx.media3.session.d
            r5 = 2
            r4.<init>(r3, r0, r5)
        L7a:
            android.os.Handler r5 = r3.getApplicationHandler()
            androidx.media3.session.y1 r1 = new androidx.media3.session.y1
            r1.<init>(r3, r6, r0, r4)
            androidx.media3.common.util.Util.postOrRun(r5, r1)
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionImpl.applyMediaButtonKeyEvent(android.view.KeyEvent, boolean, boolean):boolean");
    }

    public static Player.Commands createPlayerCommandsForCustomErrorState(Player.Commands commands) {
        if (commands == null) {
            return null;
        }
        Player.Commands.Builder builderBuildUpon = Player.Commands.EMPTY.buildUpon();
        if (commands.contains(16)) {
            builderBuildUpon.add(16);
        }
        if (commands.contains(17)) {
            builderBuildUpon.add(17);
        }
        if (commands.contains(18)) {
            builderBuildUpon.add(18);
        }
        if (commands.contains(21)) {
            builderBuildUpon.add(21);
        }
        if (commands.contains(22)) {
            builderBuildUpon.add(22);
        }
        if (commands.contains(23)) {
            builderBuildUpon.add(23);
        }
        if (commands.contains(30)) {
            builderBuildUpon.add(30);
        }
        if (commands.contains(32)) {
            builderBuildUpon.add(32);
        }
        return builderBuildUpon.build();
    }

    public static PlayerInfo createPlayerInfoForCustomPlaybackException(PlayerInfo playerInfo, PlaybackException playbackException) {
        PlayerInfo playerInfoCopyWithPlaybackState = playerInfo.copyWithPlaybackState(1, playbackException);
        SessionPositionInfo sessionPositionInfo = playerInfo.sessionPositionInfo;
        return playerInfoCopyWithPlaybackState.copyWithSessionPositionInfo(new SessionPositionInfo(sessionPositionInfo.positionInfo, sessionPositionInfo.isPlayingAd, sessionPositionInfo.eventTimeMs, sessionPositionInfo.durationMs, 0L, 0, 0L, sessionPositionInfo.currentLiveOffsetMs, sessionPositionInfo.contentDurationMs, 0L));
    }

    private void dispatchOnPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        ConnectedControllersManager<IBinder> connectedControllersManager = this.sessionStub.getConnectedControllersManager();
        com.google.common.collect.h1 connectedControllers = connectedControllersManager.getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            if (connectedControllersManager.getPlaybackException(controllerInfo) == null) {
                dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new o0(sessionPositionInfo, connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16), connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17), controllerInfo));
            }
        }
        try {
            this.sessionLegacyStub.getControllerLegacyCbForBroadcast().onPeriodicSessionPositionInfoChanged(0, sessionPositionInfo, true, true, 0);
        } catch (RemoteException e5) {
            Log.e(TAG, "Exception in using media1 API", e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnPlayerInfoChanged(PlayerInfo playerInfo, boolean z, boolean z5) {
        boolean z10;
        boolean z11;
        int iObtainNextSequenceNumber;
        PlayerInfo playerInfoGenerateAndCacheUniqueTrackGroupIds = this.sessionStub.generateAndCacheUniqueTrackGroupIds(playerInfo);
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        int i10 = 0;
        while (i10 < connectedControllers.size()) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            try {
                ConnectedControllersManager<IBinder> connectedControllersManager = this.sessionStub.getConnectedControllersManager();
                SequencedFutureManager sequencedFutureManager = connectedControllersManager.getSequencedFutureManager(controllerInfo);
                if (sequencedFutureManager != null) {
                    iObtainNextSequenceNumber = sequencedFutureManager.obtainNextSequenceNumber();
                } else if (!isConnected(controllerInfo)) {
                    return;
                } else {
                    iObtainNextSequenceNumber = 0;
                }
                PlayerInfo playerInfoForPlaybackException = connectedControllersManager.getPlayerInfoForPlaybackException(controllerInfo);
                if (playerInfoForPlaybackException != null) {
                    z10 = z;
                    z11 = z5;
                } else {
                    PlaybackException playbackException = connectedControllersManager.getPlaybackException(controllerInfo);
                    if (playbackException != null) {
                        playerInfoForPlaybackException = createPlayerInfoForCustomPlaybackException(playerInfoGenerateAndCacheUniqueTrackGroupIds, playbackException);
                        connectedControllersManager.setPlayerInfoForPlaybackException(controllerInfo, playerInfoForPlaybackException);
                    }
                    Player.Commands commandsIntersect = MediaUtils.intersect(connectedControllersManager.getAvailablePlayerCommands(controllerInfo), getPlayerWrapper().getAvailableCommands());
                    MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
                    controllerCb.getClass();
                    z10 = z;
                    z11 = z5;
                    try {
                        controllerCb.onPlayerInfoChanged(iObtainNextSequenceNumber, playerInfoForPlaybackException == null ? playerInfoGenerateAndCacheUniqueTrackGroupIds : playerInfoForPlaybackException, commandsIntersect, z10, z11);
                    } catch (DeadObjectException unused) {
                        onDeadObjectException(controllerInfo);
                    } catch (RemoteException e5) {
                        e = e5;
                        Log.w(TAG, "Exception in " + controllerInfo, e);
                    }
                }
            } catch (DeadObjectException unused2) {
                z10 = z;
                z11 = z5;
            } catch (RemoteException e6) {
                e = e6;
                z10 = z;
                z11 = z5;
            }
            i10++;
            z = z10;
            z5 = z11;
        }
    }

    private com.google.common.util.concurrent.d1<SessionResult> dispatchRemoteControllerTask(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        int sequenceNumber;
        com.google.common.util.concurrent.d1<SessionResult> d1VarS0;
        try {
            SequencedFutureManager sequencedFutureManager = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager != null) {
                SequencedFutureManager.SequencedFuture sequencedFutureCreateSequencedFuture = sequencedFutureManager.createSequencedFuture(RESULT_WHEN_CLOSED);
                sequenceNumber = sequencedFutureCreateSequencedFuture.getSequenceNumber();
                d1VarS0 = sequencedFutureCreateSequencedFuture;
            } else {
                if (!isConnected(controllerInfo)) {
                    return xc.d.s0(new SessionResult(-100));
                }
                sequenceNumber = 0;
                d1VarS0 = xc.d.s0(new SessionResult(0));
            }
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                remoteControllerTask.run(controllerCb, sequenceNumber);
            }
            return d1VarS0;
        } catch (DeadObjectException unused) {
            onDeadObjectException(controllerInfo);
            return xc.d.s0(new SessionResult(-100));
        } catch (RemoteException e5) {
            Log.w(TAG, "Exception in " + controllerInfo, e5);
            return xc.d.s0(new SessionResult(-1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchRemoteControllerTaskToLegacyStub(RemoteControllerTask remoteControllerTask) {
        try {
            remoteControllerTask.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);
        } catch (RemoteException e5) {
            Log.e(TAG, "Exception in using media1 API", e5);
        }
    }

    public static int getBitmapDimensionLimit(Context context) {
        int iIntValue = ((Integer) mediaMetadataBitmapMaxSize.get()).intValue();
        return Build.VERSION.SDK_INT < 27 ? Math.max(iIntValue, (int) TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics())) : iIntValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getMediaMetadataBitmapMaxSize() {
        Resources system = Resources.getSystem();
        try {
            return system.getDimensionPixelSize(system.getIdentifier("config_mediaMetadataBitmapMaxSize", "dimen", CredentialsData.CREDENTIALS_TYPE_ANDROID));
        } catch (Resources.NotFoundException unused) {
            return system.getDisplayMetrics().widthPixels;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAvailablePlayerCommandsChanged(Player.Commands commands) {
        this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, false);
        dispatchRemoteControllerTaskWithoutReturn(new n1(commands));
        dispatchRemoteControllerTaskToLegacyStub(new k1(this, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$25(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.pauseForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$26(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.playForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$27(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.playForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$28(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.pauseForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$29(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekToNextForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$30(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekToPreviousForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$31(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekForwardForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$32(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekBackForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$33(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.stopForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyMediaButtonKeyEvent$34(boolean z, MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        if (z) {
            Bundle bundle = Bundle.EMPTY;
            sendCustomCommand(controllerInfo, new SessionCommand(MediaNotification.NOTIFICATION_DISMISSED_EVENT_KEY, bundle), bundle);
        }
        runnable.run();
        this.sessionStub.getConnectedControllersManager().flushCommandQueue(controllerInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$callWithControllerForCurrentRequestSet$3(MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        this.controllerForCurrentRequest = controllerInfo;
        runnable.run();
        this.controllerForCurrentRequest = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchOnPeriodicSessionPositionInfoChanged$22(SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, MediaSession.ControllerInfo controllerInfo, MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onPeriodicSessionPositionInfoChanged(i10, sessionPositionInfo, z, z5, controllerInfo.getInterfaceVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleAvailablePlayerCommandsChanged$24(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onDeviceInfoChanged(i10, this.playerInfo.deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNotificationRefreshRequired$20() {
        MediaSession.Listener listener = this.mediaSessionListener;
        if (listener != null) {
            listener.onNotificationRefreshRequired(this.instance);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPlayRequested$21(com.google.common.util.concurrent.q1 q1Var) {
        q1Var.set(Boolean.valueOf(onPlayRequested()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$release$2() {
        PlayerListener playerListener = this.playerListener;
        if (playerListener != null) {
            this.playerWrapper.removeListener(playerListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPeriodicSessionPositionInfoChangesOnHandler() {
        synchronized (this.lock) {
            try {
                if (this.closed) {
                    return;
                }
                SessionPositionInfo sessionPositionInfoCreateSessionPositionInfo = this.playerWrapper.createSessionPositionInfo();
                if (!this.onPlayerInfoChangedHandler.hasPendingPlayerInfoChangedUpdate() && MediaUtils.areSessionPositionInfosInSamePeriodOrAd(sessionPositionInfoCreateSessionPositionInfo, this.playerInfo.sessionPositionInfo)) {
                    dispatchOnPeriodicSessionPositionInfoChanged(sessionPositionInfoCreateSessionPositionInfo);
                }
                schedulePeriodicSessionPositionInfoChanges();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void onDeadObjectException(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.getConnectedControllersManager().removeController(controllerInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        Util.postOrRun(getApplicationHandler(), runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void schedulePeriodicSessionPositionInfoChanges() {
        this.applicationHandler.removeCallbacks(this.periodicSessionPositionInfoUpdateRunnable);
        if (!this.isPeriodicPositionUpdateEnabled || this.sessionPositionUpdateDelayMs <= 0) {
            return;
        }
        if (this.playerWrapper.isPlaying() || this.playerWrapper.isLoading()) {
            this.applicationHandler.postDelayed(this.periodicSessionPositionInfoUpdateRunnable, this.sessionPositionUpdateDelayMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPlayerInternal(PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) {
        this.playerWrapper = playerWrapper2;
        if (playerWrapper != null) {
            PlayerListener playerListener = this.playerListener;
            playerListener.getClass();
            playerWrapper.removeListener(playerListener);
        }
        PlayerListener playerListener2 = new PlayerListener(this, playerWrapper2);
        playerWrapper2.addListener(playerListener2);
        this.playerListener = playerListener2;
        dispatchRemoteControllerTaskToLegacyStub(new k(playerWrapper, playerWrapper2, 13));
        if (playerWrapper == null) {
            this.sessionLegacyStub.start();
        }
        this.playerInfo = playerWrapper2.createInitialPlayerInfo();
        handleAvailablePlayerCommandsChanged(playerWrapper2.getAvailableCommands());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyApplicationThread() {
        if (Looper.myLooper() != this.applicationHandler.getLooper()) {
            throw new IllegalStateException(WRONG_THREAD_ERROR_MESSAGE);
        }
    }

    public void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        dispatchRemoteControllerTaskWithoutReturn(new g2(0, sessionCommand, bundle));
    }

    public Runnable callWithControllerForCurrentRequestSet(MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        return new s1(this, controllerInfo, runnable, 3);
    }

    public boolean canResumePlaybackOnStart() {
        return this.sessionLegacyStub.canResumePlaybackOnStart();
    }

    public void clearMediaSessionListener() {
        this.mediaSessionListener = null;
    }

    public void connectFromService(IMediaController iMediaController, MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.connect(iMediaController, controllerInfo);
    }

    public MediaSessionServiceLegacyStub createLegacyBrowserService(MediaSessionCompat.Token token) {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub = new MediaSessionServiceLegacyStub(this);
        mediaSessionServiceLegacyStub.initialize(token);
        return mediaSessionServiceLegacyStub;
    }

    public void dispatchRemoteControllerTaskWithoutReturn(RemoteControllerTask remoteControllerTask) {
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            dispatchRemoteControllerTaskWithoutReturn((MediaSession.ControllerInfo) connectedControllers.get(i10), remoteControllerTask);
        }
        try {
            remoteControllerTask.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);
        } catch (RemoteException e5) {
            Log.e(TAG, "Exception in using media1 API", e5);
        }
    }

    public Handler getApplicationHandler() {
        return this.applicationHandler;
    }

    public androidx.media3.common.util.BitmapLoader getBitmapLoader() {
        return this.bitmapLoader;
    }

    public com.google.common.collect.h1 getCommandButtonsForMediaItems() {
        return this.commandButtonsForMediaItems;
    }

    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        com.google.common.collect.h1 connectedControllers2 = this.sessionLegacyStub.getConnectedControllersManager().getConnectedControllers();
        com.google.common.collect.e1 e1VarM = com.google.common.collect.h1.m(connectedControllers2.size() + connectedControllers.size());
        if (!this.isMediaNotificationControllerConnected) {
            e1VarM.d(connectedControllers);
            e1VarM.d(connectedControllers2);
            return e1VarM.f();
        }
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            if (!isSystemUiController(controllerInfo)) {
                e1VarM.c(controllerInfo);
            }
        }
        for (int i11 = 0; i11 < connectedControllers2.size(); i11++) {
            MediaSession.ControllerInfo controllerInfo2 = (MediaSession.ControllerInfo) connectedControllers2.get(i11);
            if (!isSystemUiController(controllerInfo2)) {
                e1VarM.c(controllerInfo2);
            }
        }
        return e1VarM.f();
    }

    public Context getContext() {
        return this.context;
    }

    public MediaSession.ControllerInfo getControllerForCurrentRequest() {
        MediaSession.ControllerInfo controllerInfo = this.controllerForCurrentRequest;
        if (controllerInfo != null) {
            return resolveControllerInfoForCallback(controllerInfo);
        }
        return null;
    }

    public com.google.common.collect.h1 getCustomLayout() {
        return this.customLayout;
    }

    public String getId() {
        return this.sessionId;
    }

    public MediaSessionServiceLegacyStub getLegacyBrowserService() {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub;
        synchronized (this.lock) {
            mediaSessionServiceLegacyStub = this.browserServiceLegacyStub;
        }
        return mediaSessionServiceLegacyStub;
    }

    public IBinder getLegacyBrowserServiceBinder() {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub;
        synchronized (this.lock) {
            try {
                if (this.browserServiceLegacyStub == null) {
                    this.browserServiceLegacyStub = createLegacyBrowserService(this.sessionLegacyStub.getSessionCompat().getSessionToken());
                }
                mediaSessionServiceLegacyStub = this.browserServiceLegacyStub;
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaSessionServiceLegacyStub.onBind(new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE));
    }

    public com.google.common.collect.h1 getMediaButtonPreferences() {
        return this.mediaButtonPreferences;
    }

    public MediaSession.ControllerInfo getMediaNotificationControllerInfo() {
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            if (isMediaNotificationController(controllerInfo)) {
                return controllerInfo;
            }
        }
        return null;
    }

    public MediaSessionLegacyStub getMediaSessionLegacyStub() {
        return this.sessionLegacyStub;
    }

    public MediaSession.Token getPlatformToken() {
        return this.sessionLegacyStub.getSessionCompat().getSessionToken().getToken();
    }

    public PlaybackException getPlaybackException() {
        return this.playbackException;
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }

    public PlayerWrapper getPlayerWrapper() {
        return this.playerWrapper;
    }

    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    public MediaSession.ControllerInfo getSystemUiControllerInfo() {
        com.google.common.collect.h1 connectedControllers = this.sessionLegacyStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            if (isSystemUiController(controllerInfo)) {
                return controllerInfo;
            }
        }
        com.google.common.collect.h1 connectedControllers2 = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i11 = 0; i11 < connectedControllers2.size(); i11++) {
            MediaSession.ControllerInfo controllerInfo2 = (MediaSession.ControllerInfo) connectedControllers2.get(i11);
            if (isSystemUiController(controllerInfo2)) {
                return controllerInfo2;
            }
        }
        return null;
    }

    public SessionToken getToken() {
        return this.sessionToken;
    }

    public Uri getUri() {
        return this.sessionUri;
    }

    public void handleMediaControllerPlayRequest(MediaSession.ControllerInfo controllerInfo, boolean z) {
        if (onPlayRequested()) {
            boolean z5 = this.playerWrapper.isCommandAvailable(16) && this.playerWrapper.getCurrentMediaItem() != null;
            boolean z10 = this.playerWrapper.isCommandAvailable(31) || this.playerWrapper.isCommandAvailable(20);
            MediaSession.ControllerInfo controllerInfoResolveControllerInfoForCallback = resolveControllerInfoForCallback(controllerInfo);
            Player.Commands commandsBuild = new Player.Commands.Builder().add(1).build();
            if (!z5 && z10) {
                com.google.common.util.concurrent.d1<MediaSession.MediaItemsWithStartPosition> d1VarOnPlaybackResumption = this.callback.onPlaybackResumption(this.instance, controllerInfoResolveControllerInfoForCallback, true);
                ac.b.p(d1VarOnPlaybackResumption, "Callback.onPlaybackResumption must return a non-null future");
                xc.d.G(d1VarOnPlaybackResumption, new AnonymousClass1(controllerInfoResolveControllerInfoForCallback, z, commandsBuild), new o(this, 3));
            } else {
                if (!z5) {
                    Log.w(TAG, "Play requested without current MediaItem, but playback resumption prevented by missing available commands");
                }
                Util.handlePlayButtonAction(this.playerWrapper);
                if (z) {
                    onPlayerInteractionFinishedOnHandler(controllerInfoResolveControllerInfoForCallback, commandsBuild);
                }
            }
        }
    }

    public boolean isAutoCompanionController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo.getPackageName().equals(ANDROID_AUTO_PACKAGE_NAME);
    }

    public boolean isAutomotiveController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo.getPackageName().equals(ANDROID_AUTOMOTIVE_MEDIA_PACKAGE_NAME) || controllerInfo.getPackageName().equals(ANDROID_AUTOMOTIVE_LAUNCHER_PACKAGE_NAME);
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        return this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo) || this.sessionLegacyStub.getConnectedControllersManager().isConnected(controllerInfo);
    }

    public boolean isMediaNotificationController(MediaSession.ControllerInfo controllerInfo) {
        return Objects.equals(controllerInfo.getPackageName(), this.context.getPackageName()) && controllerInfo.getControllerVersion() != 0 && controllerInfo.getConnectionHints().getBoolean(MediaController.KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG, false);
    }

    public boolean isMediaNotificationControllerConnected() {
        return this.isMediaNotificationControllerConnected;
    }

    public boolean isReleased() {
        boolean z;
        synchronized (this.lock) {
            z = this.closed;
        }
        return z;
    }

    public boolean isSystemUiController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo != null && Objects.equals(controllerInfo.getPackageName(), SYSTEM_UI_PACKAGE_NAME);
    }

    public com.google.common.util.concurrent.d1<List<MediaItem>> onAddMediaItemsOnHandler(MediaSession.ControllerInfo controllerInfo, List<MediaItem> list) {
        com.google.common.util.concurrent.d1<List<MediaItem>> d1VarOnAddMediaItems = this.callback.onAddMediaItems(this.instance, resolveControllerInfoForCallback(controllerInfo), list);
        ac.b.p(d1VarOnAddMediaItems, "Callback.onAddMediaItems must return a non-null future");
        return d1VarOnAddMediaItems;
    }

    public MediaSession.ConnectionResult onConnectOnHandler(MediaSession.ControllerInfo controllerInfo) {
        if (this.isMediaNotificationControllerConnected && isSystemUiController(controllerInfo)) {
            return this.sessionLegacyStub.getPlatformConnectionResult(this.instance);
        }
        MediaSession.ConnectionResult connectionResultOnConnect = this.callback.onConnect(this.instance, controllerInfo);
        ac.b.p(connectionResultOnConnect, "Callback.onConnect must return non-null future");
        if (isMediaNotificationController(controllerInfo) && connectionResultOnConnect.isAccepted) {
            this.isMediaNotificationControllerConnected = true;
            com.google.common.collect.h1 mediaButtonPreferences = connectionResultOnConnect.mediaButtonPreferences;
            if (mediaButtonPreferences == null) {
                mediaButtonPreferences = this.instance.getMediaButtonPreferences();
            }
            if (mediaButtonPreferences.isEmpty()) {
                MediaSessionLegacyStub mediaSessionLegacyStub = this.sessionLegacyStub;
                com.google.common.collect.h1 customLayout = connectionResultOnConnect.customLayout;
                if (customLayout == null) {
                    customLayout = this.instance.getCustomLayout();
                }
                mediaSessionLegacyStub.setPlatformCustomLayout(customLayout);
            } else {
                this.sessionLegacyStub.setPlatformMediaButtonPreferences(mediaButtonPreferences);
            }
            this.sessionLegacyStub.setAvailableCommands(connectionResultOnConnect.availableSessionCommands, connectionResultOnConnect.availablePlayerCommands);
        }
        return connectionResultOnConnect;
    }

    public com.google.common.util.concurrent.d1<SessionResult> onCustomCommandOnHandler(MediaSession.ControllerInfo controllerInfo, MediaSession.ProgressReporter progressReporter, SessionCommand sessionCommand, Bundle bundle) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnCustomCommand = this.callback.onCustomCommand(this.instance, resolveControllerInfoForCallback(controllerInfo), sessionCommand, bundle, progressReporter);
        ac.b.p(d1VarOnCustomCommand, "Callback.onCustomCommandOnHandler must return non-null future");
        return d1VarOnCustomCommand;
    }

    public void onDisconnectedOnHandler(MediaSession.ControllerInfo controllerInfo) {
        if (this.isMediaNotificationControllerConnected) {
            if (isSystemUiController(controllerInfo)) {
                return;
            }
            if (isMediaNotificationController(controllerInfo)) {
                this.isMediaNotificationControllerConnected = false;
            }
        }
        this.callback.onDisconnected(this.instance, controllerInfo);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onMediaButtonEvent(androidx.media3.session.MediaSession.ControllerInfo r9, android.content.Intent r10) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionImpl.onMediaButtonEvent(androidx.media3.session.MediaSession$ControllerInfo, android.content.Intent):boolean");
    }

    public void onNotificationRefreshRequired() {
        Util.postOrRun(this.mainHandler, new h2(this, 0));
    }

    public boolean onPlayRequested() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            MediaSession.Listener listener = this.mediaSessionListener;
            if (listener != null) {
                return listener.onPlayRequested(this.instance);
            }
            return true;
        }
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        this.mainHandler.post(new g(this, q1Var, 8));
        try {
            return ((Boolean) q1Var.get()).booleanValue();
        } catch (InterruptedException | ExecutionException e5) {
            throw new IllegalStateException(e5);
        }
    }

    public int onPlayerCommandRequestOnHandler(MediaSession.ControllerInfo controllerInfo, int i10) {
        return this.callback.onPlayerCommandRequest(this.instance, resolveControllerInfoForCallback(controllerInfo), i10);
    }

    public void onPlayerInteractionFinishedOnHandler(MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        this.callback.onPlayerInteractionFinished(this.instance, resolveControllerInfoForCallback(controllerInfo), commands);
    }

    public void onPostConnectOnHandler(MediaSession.ControllerInfo controllerInfo) {
        if (this.isMediaNotificationControllerConnected && isSystemUiController(controllerInfo)) {
            return;
        }
        this.callback.onPostConnect(this.instance, controllerInfo);
    }

    public com.google.common.util.concurrent.d1<MediaSession.MediaItemsWithStartPosition> onSetMediaItemsOnHandler(MediaSession.ControllerInfo controllerInfo, List<MediaItem> list, int i10, long j10) {
        com.google.common.util.concurrent.d1<MediaSession.MediaItemsWithStartPosition> d1VarOnSetMediaItems = this.callback.onSetMediaItems(this.instance, resolveControllerInfoForCallback(controllerInfo), list, i10, j10);
        ac.b.p(d1VarOnSetMediaItems, "Callback.onSetMediaItems must return a non-null future");
        return d1VarOnSetMediaItems;
    }

    public com.google.common.util.concurrent.d1<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo controllerInfo, String str, Rating rating) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnSetRating = this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(controllerInfo), str, rating);
        ac.b.p(d1VarOnSetRating, "Callback.onSetRating must return non-null future");
        return d1VarOnSetRating;
    }

    public void release() {
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        synchronized (this.lock) {
            try {
                if (this.closed) {
                    return;
                }
                this.closed = true;
                this.mediaPlayPauseKeyHandler.clearPendingPlayPauseTask();
                this.applicationHandler.removeCallbacksAndMessages(null);
                try {
                    Util.postOrRun(this.applicationHandler, new h2(this, 3));
                } catch (Exception e5) {
                    Log.w(TAG, "Exception thrown while closing", e5);
                }
                this.sessionLegacyStub.release();
                this.sessionStub.release();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public MediaSession.ControllerInfo resolveControllerInfoForCallback(MediaSession.ControllerInfo controllerInfo) {
        if (!this.isMediaNotificationControllerConnected || !isSystemUiController(controllerInfo)) {
            return controllerInfo;
        }
        MediaSession.ControllerInfo mediaNotificationControllerInfo = getMediaNotificationControllerInfo();
        mediaNotificationControllerInfo.getClass();
        return mediaNotificationControllerInfo;
    }

    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        return dispatchRemoteControllerTask(controllerInfo, new g2(1, sessionCommand, bundle));
    }

    public void sendCustomCommandProgressUpdate(MediaSession.ControllerInfo controllerInfo, int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new j1(i10, sessionCommand, bundle, bundle2));
    }

    public void sendError(MediaSession.ControllerInfo controllerInfo, SessionError sessionError) {
        if (controllerInfo.getControllerVersion() == 0 || controllerInfo.getInterfaceVersion() >= 4) {
            if (isMediaNotificationController(controllerInfo) || controllerInfo.getControllerVersion() == 0) {
                dispatchRemoteControllerTaskToLegacyStub(new f2(1, sessionError));
            } else {
                dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new f2(2, sessionError));
            }
        }
    }

    public void setAvailableCommands(MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        if (!this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            if (controllerInfo.getControllerVersion() == 0) {
                this.sessionLegacyStub.getConnectedControllersManager().updateCommandsFromSession(controllerInfo, sessionCommands, commands);
                return;
            }
            return;
        }
        if (isMediaNotificationController(controllerInfo)) {
            this.sessionLegacyStub.setAvailableCommands(sessionCommands, commands);
            MediaSession.ControllerInfo systemUiControllerInfo = getSystemUiControllerInfo();
            if (systemUiControllerInfo != null) {
                (systemUiControllerInfo.getControllerVersion() == 0 ? this.sessionLegacyStub.getConnectedControllersManager() : this.sessionStub.getConnectedControllersManager()).updateCommandsFromSession(systemUiControllerInfo, sessionCommands, commands);
            }
        }
        this.sessionStub.getConnectedControllersManager().updateCommandsFromSession(controllerInfo, sessionCommands, commands);
        Player.Commands availablePlayerCommands = this.sessionStub.getConnectedControllersManager().getAvailablePlayerCommands(controllerInfo);
        if (availablePlayerCommands != null) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new o1(sessionCommands, availablePlayerCommands));
            this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, false);
        }
    }

    public com.google.common.util.concurrent.d1<SessionResult> setCustomLayout(MediaSession.ControllerInfo controllerInfo, com.google.common.collect.h1 h1Var) {
        if (isMediaNotificationController(controllerInfo)) {
            this.sessionLegacyStub.setPlatformCustomLayout(h1Var);
            this.sessionLegacyStub.updateLegacySessionPlaybackState(this.playerWrapper);
        }
        return dispatchRemoteControllerTask(controllerInfo, new d2(2, h1Var));
    }

    public void setLegacyControllerConnectionTimeoutMs(long j10) {
        this.sessionLegacyStub.setLegacyControllerDisconnectTimeoutMs(j10);
    }

    public com.google.common.util.concurrent.d1<SessionResult> setMediaButtonPreferences(MediaSession.ControllerInfo controllerInfo, com.google.common.collect.h1 h1Var) {
        if (isMediaNotificationController(controllerInfo)) {
            this.sessionLegacyStub.setPlatformMediaButtonPreferences(h1Var);
            this.sessionLegacyStub.updateLegacySessionPlaybackState(this.playerWrapper);
        }
        return dispatchRemoteControllerTask(controllerInfo, new d2(0, h1Var));
    }

    public void setMediaSessionListener(MediaSession.Listener listener) {
        this.mediaSessionListener = listener;
    }

    public void setPlaybackException(MediaSession.ControllerInfo controllerInfo, PlaybackException playbackException) {
        ConnectedControllersManager<IBinder> connectedControllersManager = this.sessionStub.getConnectedControllersManager();
        PlaybackException playbackException2 = connectedControllersManager.getPlaybackException(controllerInfo);
        if (!connectedControllersManager.isConnected(controllerInfo) || PlaybackException.areErrorInfosEqual(playbackException, playbackException2)) {
            return;
        }
        Player.Commands availablePlayerCommands = playbackException2 == null ? connectedControllersManager.getAvailablePlayerCommands(controllerInfo) : connectedControllersManager.getPlayerCommandsBeforePlaybackException(controllerInfo);
        if (isMediaNotificationController(controllerInfo)) {
            this.sessionLegacyStub.setPlaybackException(playbackException, playbackException != null ? createPlayerCommandsForCustomErrorState(availablePlayerCommands) : null);
        }
        Player.Commands commandsCreatePlayerCommandsForCustomErrorState = playbackException != null ? createPlayerCommandsForCustomErrorState(availablePlayerCommands) : connectedControllersManager.getPlayerCommandsBeforePlaybackException(controllerInfo);
        SessionCommands availableSessionCommands = connectedControllersManager.getAvailableSessionCommands(controllerInfo);
        if (commandsCreatePlayerCommandsForCustomErrorState == null || availableSessionCommands == null) {
            return;
        }
        connectedControllersManager.resetPlaybackException(controllerInfo);
        setAvailableCommands(controllerInfo, availableSessionCommands, commandsCreatePlayerCommandsForCustomErrorState);
        if (playbackException != null) {
            availablePlayerCommands.getClass();
            connectedControllersManager.setPlaybackException(controllerInfo, playbackException, availablePlayerCommands);
        }
    }

    public void setPlayer(Player player) {
        if (player == this.playerWrapper.getWrappedPlayer()) {
            return;
        }
        setPlayerInternal(this.playerWrapper, new PlayerWrapper(player));
    }

    public void setSessionActivity(PendingIntent pendingIntent) {
        this.sessionActivity = pendingIntent;
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            setSessionActivity((MediaSession.ControllerInfo) connectedControllers.get(i10), pendingIntent);
        }
    }

    public void setSessionExtras(Bundle bundle) {
        this.sessionExtras = bundle;
        dispatchRemoteControllerTaskWithoutReturn(new i1(3, bundle));
    }

    public void setSessionPositionUpdateDelayMsOnHandler(long j10) {
        verifyApplicationThread();
        this.sessionPositionUpdateDelayMs = j10;
        schedulePeriodicSessionPositionInfoChanges();
    }

    public boolean shouldPlayIfSuppressed() {
        return this.playIfSuppressed;
    }

    public boolean shouldUseLegacySurfaceHandling() {
        return this.useLegacySurfaceHandling;
    }

    public void triggerPlayerInfoUpdate() {
        this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
    }

    public void setSessionExtras(MediaSession.ControllerInfo controllerInfo, Bundle bundle) {
        if (this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new i1(1, bundle));
            if (isMediaNotificationController(controllerInfo)) {
                dispatchRemoteControllerTaskToLegacyStub(new i1(2, bundle));
            }
        }
    }

    public com.google.common.util.concurrent.d1<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo controllerInfo, Rating rating) {
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnSetRating = this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(controllerInfo), rating);
        ac.b.p(d1VarOnSetRating, "Callback.onSetRating must return non-null future");
        return d1VarOnSetRating;
    }

    public void setCustomLayout(com.google.common.collect.h1 h1Var) {
        this.customLayout = h1Var;
        this.sessionLegacyStub.setPlatformCustomLayout(h1Var);
        dispatchRemoteControllerTaskWithoutReturn(new d2(3, h1Var));
    }

    public void setMediaButtonPreferences(com.google.common.collect.h1 h1Var) {
        this.mediaButtonPreferences = h1Var;
        this.sessionLegacyStub.setPlatformMediaButtonPreferences(h1Var);
        dispatchRemoteControllerTaskWithoutReturn(new d2(1, h1Var));
    }

    public void setSessionActivity(MediaSession.ControllerInfo controllerInfo, final PendingIntent pendingIntent) {
        if (controllerInfo.getControllerVersion() < 3 || !this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            return;
        }
        final int i10 = 1;
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new RemoteControllerTask() { // from class: androidx.media3.session.e2
            @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
            public final void run(MediaSession.ControllerCb controllerCb, int i11) throws RemoteException {
                switch (i10) {
                    case 0:
                        controllerCb.onSessionActivityChanged(i11, pendingIntent);
                        break;
                    default:
                        controllerCb.onSessionActivityChanged(i11, pendingIntent);
                        break;
                }
            }
        });
        if (isMediaNotificationController(controllerInfo)) {
            final int i11 = 0;
            dispatchRemoteControllerTaskToLegacyStub(new RemoteControllerTask() { // from class: androidx.media3.session.e2
                @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
                public final void run(MediaSession.ControllerCb controllerCb, int i112) throws RemoteException {
                    switch (i11) {
                        case 0:
                            controllerCb.onSessionActivityChanged(i112, pendingIntent);
                            break;
                        default:
                            controllerCb.onSessionActivityChanged(i112, pendingIntent);
                            break;
                    }
                }
            });
        }
    }

    public void sendError(SessionError sessionError) {
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i10);
            if (!isMediaNotificationController(controllerInfo)) {
                sendError(controllerInfo, sessionError);
            }
        }
        dispatchRemoteControllerTaskToLegacyStub(new f2(0, sessionError));
    }

    public void dispatchRemoteControllerTaskWithoutReturn(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        int iObtainNextSequenceNumber;
        try {
            SequencedFutureManager sequencedFutureManager = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager != null) {
                iObtainNextSequenceNumber = sequencedFutureManager.obtainNextSequenceNumber();
            } else if (!isConnected(controllerInfo)) {
                return;
            } else {
                iObtainNextSequenceNumber = 0;
            }
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                remoteControllerTask.run(controllerCb, iObtainNextSequenceNumber);
            }
        } catch (DeadObjectException unused) {
            onDeadObjectException(controllerInfo);
        } catch (RemoteException e5) {
            Log.w(TAG, "Exception in " + controllerInfo, e5);
        }
    }

    public void setPlaybackException(PlaybackException playbackException) {
        this.playbackException = playbackException;
        com.google.common.collect.h1 connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i10 = 0; i10 < connectedControllers.size(); i10++) {
            setPlaybackException((MediaSession.ControllerInfo) connectedControllers.get(i10), playbackException);
        }
    }
}
