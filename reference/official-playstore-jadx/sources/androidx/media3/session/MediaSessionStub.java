package androidx.media3.session;

import android.app.PendingIntent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.session.MediaSession;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.n3;
import com.google.common.collect.o4;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
final class MediaSessionStub extends IMediaSession.Stub {
    private static final String TAG = "MediaSessionStub";
    public static final int UNKNOWN_SEQUENCE_NUMBER = Integer.MIN_VALUE;
    public static final int VERSION_INT = 8;
    private final ConnectedControllersManager<IBinder> connectedControllersManager;
    private int nextUniqueTrackGroupIdPrefix;
    private final WeakReference<MediaSessionImpl> sessionImpl;
    private SurfaceHolderWithSize surfaceHolderWithSize;
    private final Set<MediaSession.ControllerInfo> pendingControllers = Collections.synchronizedSet(new HashSet());
    private com.google.common.collect.z0 trackGroupIdMap = n3.f14068s;

    public static final class Controller2Cb implements MediaSession.ControllerCb {
        private final int controllerInterfaceVersion;
        private final IMediaController iController;

        public Controller2Cb(IMediaController iMediaController, int i10) {
            this.iController = iMediaController;
            this.controllerInterfaceVersion = i10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Controller2Cb.class) {
                return false;
            }
            return Objects.equals(getCallbackBinder(), ((Controller2Cb) obj).getCallbackBinder());
        }

        public IBinder getCallbackBinder() {
            return this.iController.asBinder();
        }

        public int hashCode() {
            return Objects.hash(getCallbackBinder());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onAudioAttributesChanged(int i10, AudioAttributes audioAttributes) {
            c2.a(this, i10, audioAttributes);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onAudioSessionIdChanged(int i10, int i11) {
            c2.b(this, i10, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int i10, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromPlayer(i10, commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromSession(int i10, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromSession(i10, sessionCommands.toBundle(), commands.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onChildrenChanged(i10, str, i11, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onDeviceInfoChanged(int i10, DeviceInfo deviceInfo) {
            c2.f(this, i10, deviceInfo);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onDeviceVolumeChanged(int i10, int i11, boolean z) {
            c2.g(this, i10, i11, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int i10) {
            SessionUtil.disconnectIMediaController(this.iController);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onError(int i10, SessionError sessionError) throws RemoteException {
            this.iController.onError(i10, sessionError.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onIsLoadingChanged(int i10, boolean z) {
            c2.j(this, i10, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onIsPlayingChanged(int i10, boolean z) {
            c2.k(this, i10, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onLibraryResult(int i10, LibraryResult<?> libraryResult) throws RemoteException {
            this.iController.onLibraryResult(i10, libraryResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onMediaItemTransition(int i10, MediaItem mediaItem, int i11) {
            c2.m(this, i10, mediaItem, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onMediaMetadataChanged(int i10, MediaMetadata mediaMetadata) {
            c2.n(this, i10, mediaMetadata);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int i10, SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, int i11) throws RemoteException {
            this.iController.onPeriodicSessionPositionInfoChanged(i10, sessionPositionInfo.filterByAvailableCommands(z, z5).toBundle(i11));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlayWhenReadyChanged(int i10, boolean z, int i11) {
            c2.p(this, i10, z, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlaybackParametersChanged(int i10, PlaybackParameters playbackParameters) {
            c2.q(this, i10, playbackParameters);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlaybackStateChanged(int i10, int i11, PlaybackException playbackException) {
            c2.r(this, i10, i11, playbackException);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10, int i11) {
            c2.s(this, i10, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlayerChanged(int i10, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) {
            c2.t(this, i10, playerWrapper, playerWrapper2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlayerError(int i10, PlaybackException playbackException) {
            c2.u(this, i10, playbackException);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerInfoChanged(int i10, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z5) throws RemoteException {
            ac.b.s(this.controllerInterfaceVersion != 0);
            boolean z10 = z || !commands.contains(17);
            boolean z11 = z5 || !commands.contains(30);
            if (this.controllerInterfaceVersion < 2) {
                this.iController.onPlayerInfoChanged(i10, playerInfo.filterByAvailableCommands(commands, z, true).toBundleForRemoteProcess(this.controllerInterfaceVersion), z10);
            } else {
                PlayerInfo playerInfoFilterByAvailableCommands = playerInfo.filterByAvailableCommands(commands, z, z5);
                this.iController.onPlayerInfoChangedWithExclusions(i10, this.iController instanceof MediaControllerStub ? playerInfoFilterByAvailableCommands.toBundleInProcess() : playerInfoFilterByAvailableCommands.toBundleForRemoteProcess(this.controllerInterfaceVersion), new PlayerInfo.BundlingExclusions(z10, z11).toBundle());
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlaylistMetadataChanged(int i10, MediaMetadata mediaMetadata) {
            c2.w(this, i10, mediaMetadata);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPositionDiscontinuity(int i10, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i11) {
            c2.x(this, i10, positionInfo, positionInfo2, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRenderedFirstFrame(int i10) throws RemoteException {
            this.iController.onRenderedFirstFrame(i10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onRepeatModeChanged(int i10, int i11) {
            c2.z(this, i10, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.iController.onSearchResultChanged(i10, str, i11, libraryParams == null ? null : libraryParams.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSeekBackIncrementChanged(int i10, long j10) {
            c2.B(this, i10, j10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSeekForwardIncrementChanged(int i10, long j10) {
            c2.C(this, i10, j10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException {
            this.iController.onSessionActivityChanged(i10, pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionExtrasChanged(int i10, Bundle bundle) throws RemoteException {
            this.iController.onExtrasChanged(i10, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionResult(int i10, SessionResult sessionResult) throws RemoteException {
            this.iController.onSessionResult(i10, sessionResult.toBundle());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onShuffleModeEnabledChanged(int i10, boolean z) {
            c2.G(this, i10, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSurfaceSizeChanged(int i10, int i11, int i12) throws RemoteException {
            this.iController.onSurfaceSizeChanged(i10, i11, i12);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onTimelineChanged(int i10, Timeline timeline, int i11) {
            c2.I(this, i10, timeline, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onTrackSelectionParametersChanged(int i10, TrackSelectionParameters trackSelectionParameters) {
            c2.J(this, i10, trackSelectionParameters);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onTracksChanged(int i10, Tracks tracks) {
            c2.K(this, i10, tracks);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onVideoSizeChanged(int i10, VideoSize videoSize) {
            c2.L(this, i10, videoSize);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onVolumeChanged(int i10, float f10) {
            c2.M(this, i10, f10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommand(int i10, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            this.iController.onCustomCommand(i10, sessionCommand.toBundle(), bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void sendCustomCommandProgressUpdate(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException {
            this.iController.onCustomCommandProgressUpdate(i10, sessionCommand.toBundle(), bundle, bundle2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setCustomLayout(int i10, List<CommandButton> list) throws RemoteException {
            this.iController.onSetCustomLayout(i10, BundleCollectionUtil.toBundleList(list, new f(0)));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setMediaButtonPreferences(int i10, List<CommandButton> list) throws RemoteException {
            if (this.controllerInterfaceVersion >= 7) {
                this.iController.onSetMediaButtonPreferences(i10, BundleCollectionUtil.toBundleList(list, new f(0)));
            } else {
                this.iController.onSetCustomLayout(i10, BundleCollectionUtil.toBundleList(CommandButton.getCustomLayoutFromMediaButtonPreferences(list, true, true), new f(0)));
            }
        }
    }

    public interface ControllerPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo);
    }

    public interface MediaItemPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List<MediaItem> list);
    }

    public interface MediaItemsWithStartPositionPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition);
    }

    public static class ProgressReporter implements MediaSession.ProgressReporter {
        private final SessionCommand command;
        private final MediaSession.ControllerInfo controller;
        private final int customCommandFutureSequence;
        private final Bundle extras;
        private com.google.common.util.concurrent.d1<SessionResult> future;
        private final MediaSessionImpl session;

        public ProgressReporter(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10, SessionCommand sessionCommand, Bundle bundle) {
            this.session = mediaSessionImpl;
            this.controller = controllerInfo;
            this.customCommandFutureSequence = i10;
            this.command = sessionCommand;
            this.extras = bundle;
        }

        @Override // androidx.media3.session.MediaSession.ProgressReporter
        public void sendProgressUpdate(Bundle bundle) {
            com.google.common.util.concurrent.d1<SessionResult> d1Var = this.future;
            if ((d1Var == null || !d1Var.isDone()) && !this.session.isReleased()) {
                this.session.sendCustomCommandProgressUpdate(this.controller, this.customCommandFutureSequence, this.command, this.extras, bundle);
            }
        }

        public void setFuture(com.google.common.util.concurrent.d1<SessionResult> d1Var) {
            this.future = d1Var;
        }
    }

    public interface SessionTask<T, K extends MediaSessionImpl> {
        T run(K k, MediaSession.ControllerInfo controllerInfo, int i10);
    }

    public static class SurfaceHolderWithSize implements SurfaceHolder {
        private SurfaceHolder.Callback callback;
        private final Surface surface;
        private final Rect surfaceFrame;

        public SurfaceHolderWithSize(Surface surface) {
            this.surfaceFrame = new Rect();
            this.surface = surface;
        }

        @Override // android.view.SurfaceHolder
        public void addCallback(SurfaceHolder.Callback callback) {
            this.callback = callback;
        }

        @Override // android.view.SurfaceHolder
        public Surface getSurface() {
            return this.surface;
        }

        @Override // android.view.SurfaceHolder
        public Rect getSurfaceFrame() {
            return this.surfaceFrame;
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return false;
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas() {
            throw new UnsupportedOperationException();
        }

        @Override // android.view.SurfaceHolder
        public void removeCallback(SurfaceHolder.Callback callback) {
            if (this.callback == callback) {
                this.callback = null;
            }
        }

        @Override // android.view.SurfaceHolder
        public void setFixedSize(int i10, int i11) {
            this.surfaceFrame.set(0, 0, i10, i11);
            SurfaceHolder.Callback callback = this.callback;
            if (callback != null) {
                callback.surfaceChanged(this, 1, i10, i11);
            }
        }

        @Override // android.view.SurfaceHolder
        public void setFormat(int i10) {
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(boolean z) {
        }

        @Override // android.view.SurfaceHolder
        public void setSizeFromLayout() {
        }

        @Override // android.view.SurfaceHolder
        public void setType(int i10) {
        }

        @Override // android.view.SurfaceHolder
        public void unlockCanvasAndPost(Canvas canvas) {
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas(Rect rect) {
            throw new UnsupportedOperationException();
        }

        public SurfaceHolderWithSize(Surface surface, int i10, int i11) {
            Rect rect = new Rect();
            this.surfaceFrame = rect;
            this.surface = surface;
            rect.set(0, 0, i10, i11);
        }
    }

    public MediaSessionStub(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    private void dispatchCustomCommandAsPredefinedCommand(final IMediaController iMediaController, final int i10, final SessionCommand sessionCommand) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller == null) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                } else {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.i3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f4025i.lambda$dispatchCustomCommandAsPredefinedCommand$27(controller, sessionCommand, mediaSessionImpl, i10, iMediaController);
                        }
                    });
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i10, int i11, SessionTask<com.google.common.util.concurrent.d1<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, null, i11, sessionTask);
    }

    private String generateUniqueTrackGroupId(TrackGroup trackGroup) {
        StringBuilder sb2 = new StringBuilder();
        int i10 = this.nextUniqueTrackGroupIdPrefix;
        this.nextUniqueTrackGroupIdPrefix = i10 + 1;
        sb2.append(Util.intToStringMaxRadix(i10));
        sb2.append("-");
        sb2.append(trackGroup.id);
        return sb2.toString();
    }

    private static <K extends MediaSessionImpl> SessionTask<com.google.common.util.concurrent.d1<SessionResult>, K> handleMediaItemsWhenReady(SessionTask<com.google.common.util.concurrent.d1<List<MediaItem>>, K> sessionTask, MediaItemPlayerTask mediaItemPlayerTask) {
        return new k(sessionTask, mediaItemPlayerTask, 22);
    }

    private static <K extends MediaSessionImpl> SessionTask<com.google.common.util.concurrent.d1<SessionResult>, K> handleMediaItemsWithStartPositionWhenReady(SessionTask<com.google.common.util.concurrent.d1<MediaSession.MediaItemsWithStartPosition>, K> sessionTask, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        return new k(sessionTask, mediaItemsWithStartPositionPlayerTask, 20);
    }

    private static <T, K extends MediaSessionImpl> com.google.common.util.concurrent.d1<Void> handleSessionTaskWhenReady(K k, MediaSession.ControllerInfo controllerInfo, int i10, SessionTask<com.google.common.util.concurrent.d1<T>, K> sessionTask, Consumer<com.google.common.util.concurrent.d1<T>> consumer) {
        if (k.isReleased()) {
            return com.google.common.util.concurrent.z0.f14245l;
        }
        com.google.common.util.concurrent.d1<T> d1VarRun = sessionTask.run(k, controllerInfo, i10);
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        d1VarRun.addListener(new c(k, q1Var, consumer, d1VarRun, 8), com.google.common.util.concurrent.s0.f14233i);
        return q1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$addMediaItem$37(MediaItem mediaItem, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$addMediaItemWithIndex$39(MediaItem mediaItem, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItemWithIndex$40(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$addMediaItems$41(List list, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$addMediaItemsWithIndex$43(List list, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addMediaItemsWithIndex$44(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$connect$17(MediaSession.ControllerInfo controllerInfo, MediaSessionImpl mediaSessionImpl, IMediaController iMediaController) throws Throwable {
        IMediaController iMediaController2;
        Player.Commands commandsCreatePlayerCommandsForCustomErrorState;
        boolean z = false;
        try {
            this.pendingControllers.remove(controllerInfo);
            if (mediaSessionImpl.isReleased()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            Controller2Cb controller2Cb = (Controller2Cb) controllerInfo.getControllerCb();
            controller2Cb.getClass();
            IBinder callbackBinder = controller2Cb.getCallbackBinder();
            MediaSession.ConnectionResult connectionResultOnConnectOnHandler = mediaSessionImpl.onConnectOnHandler(controllerInfo);
            if (!connectionResultOnConnectOnHandler.isAccepted && !controllerInfo.isTrusted()) {
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            if (!connectionResultOnConnectOnHandler.isAccepted) {
                connectionResultOnConnectOnHandler = MediaSession.ConnectionResult.accept(SessionCommands.EMPTY, Player.Commands.EMPTY);
            }
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                Log.w(TAG, "Controller " + controllerInfo + " has sent connection request multiple times");
            }
            this.connectedControllersManager.addController(callbackBinder, controllerInfo, connectionResultOnConnectOnHandler.availableSessionCommands, connectionResultOnConnectOnHandler.availablePlayerCommands);
            SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager == null) {
                Log.w(TAG, "Ignoring connection request from unknown controller info");
                SessionUtil.disconnectIMediaController(iMediaController);
                return;
            }
            PlayerWrapper playerWrapper = mediaSessionImpl.getPlayerWrapper();
            PlayerInfo playerInfo = mediaSessionImpl.getPlayerInfo();
            PlaybackException playbackException = mediaSessionImpl.getPlaybackException();
            if (playbackException == null) {
                commandsCreatePlayerCommandsForCustomErrorState = connectionResultOnConnectOnHandler.availablePlayerCommands;
            } else {
                this.connectedControllersManager.setPlaybackException(controllerInfo, playbackException, connectionResultOnConnectOnHandler.availablePlayerCommands);
                playerInfo = MediaSessionImpl.createPlayerInfoForCustomPlaybackException(playerInfo, playbackException);
                commandsCreatePlayerCommandsForCustomErrorState = MediaSessionImpl.createPlayerCommandsForCustomErrorState(connectionResultOnConnectOnHandler.availablePlayerCommands);
                commandsCreatePlayerCommandsForCustomErrorState.getClass();
            }
            Player.Commands commands = commandsCreatePlayerCommandsForCustomErrorState;
            PlayerInfo playerInfoGenerateAndCacheUniqueTrackGroupIds = generateAndCacheUniqueTrackGroupIds(playerInfo);
            MediaSession.Token platformToken = mediaSessionImpl.getPlatformToken();
            PendingIntent sessionActivity = connectionResultOnConnectOnHandler.sessionActivity;
            if (sessionActivity == null) {
                sessionActivity = mediaSessionImpl.getSessionActivity();
            }
            com.google.common.collect.h1 customLayout = connectionResultOnConnectOnHandler.customLayout;
            if (customLayout == null) {
                customLayout = mediaSessionImpl.getCustomLayout();
            }
            com.google.common.collect.h1 mediaButtonPreferences = connectionResultOnConnectOnHandler.mediaButtonPreferences;
            if (mediaButtonPreferences == null) {
                mediaButtonPreferences = mediaSessionImpl.getMediaButtonPreferences();
            }
            com.google.common.collect.h1 commandButtonsForMediaItems = mediaSessionImpl.getCommandButtonsForMediaItems();
            SessionCommands sessionCommands = connectionResultOnConnectOnHandler.availableSessionCommands;
            Player.Commands availableCommands = playerWrapper.getAvailableCommands();
            Bundle extras = mediaSessionImpl.getToken().getExtras();
            Bundle sessionExtras = connectionResultOnConnectOnHandler.sessionExtras;
            if (sessionExtras == null) {
                sessionExtras = mediaSessionImpl.getSessionExtras();
            }
            iMediaController2 = iMediaController;
            try {
                ConnectionState connectionState = new ConnectionState(MediaLibraryInfo.VERSION_INT, 8, this, sessionActivity, customLayout, mediaButtonPreferences, commandButtonsForMediaItems, sessionCommands, commands, availableCommands, extras, sessionExtras, playerInfoGenerateAndCacheUniqueTrackGroupIds, platformToken);
                if (mediaSessionImpl.isReleased()) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                    return;
                }
                try {
                    iMediaController2.onConnected(sequencedFutureManager.obtainNextSequenceNumber(), iMediaController2 instanceof MediaControllerStub ? connectionState.toBundleInProcess() : connectionState.toBundleForRemoteProcess(controllerInfo.getInterfaceVersion()));
                    z = true;
                } catch (RemoteException unused) {
                }
                if (z) {
                    mediaSessionImpl.onPostConnectOnHandler(controllerInfo);
                }
                if (z) {
                    return;
                }
                SessionUtil.disconnectIMediaController(iMediaController2);
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    SessionUtil.disconnectIMediaController(iMediaController2);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            iMediaController2 = iMediaController;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.google.common.util.concurrent.d1 lambda$dispatchCustomCommandAsPredefinedCommand$25(CommandButton commandButton, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        Object obj = commandButton.parameter;
        obj.getClass();
        return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, (Rating) obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$dispatchCustomCommandAsPredefinedCommand$27(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, MediaSessionImpl mediaSessionImpl, int i10, IMediaController iMediaController) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            try {
                CommandButton commandButtonConvertFromPredefinedCustomCommand = CommandButton.convertFromPredefinedCustomCommand(sessionCommand);
                if (!commandButtonConvertFromPredefinedCustomCommand.canExecuteAction()) {
                    Log.w(TAG, "Can't execute predefined custom command: " + sessionCommand.customAction);
                    sendSessionResult(mediaSessionImpl, controllerInfo, i10, new SessionResult(-6));
                    return;
                }
                SessionCommand sessionCommand2 = commandButtonConvertFromPredefinedCustomCommand.sessionCommand;
                if (sessionCommand2 != null) {
                    ac.b.s(sessionCommand2.commandCode == 40010);
                    dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new d3(commandButtonConvertFromPredefinedCustomCommand)));
                    return;
                }
                if (commandButtonConvertFromPredefinedCustomCommand.isPlayRequestPlayerAction(mediaSessionImpl.getPlayerWrapper())) {
                    playForControllerInfo(controllerInfo, i10);
                } else {
                    int i11 = commandButtonConvertFromPredefinedCustomCommand.playerCommand;
                    if (i11 == 31) {
                        Object obj = commandButtonConvertFromPredefinedCustomCommand.parameter;
                        obj.getClass();
                        setMediaItemItemWithResetPositionForControllerInfo(controllerInfo, i10, (MediaItem) obj, true);
                    } else {
                        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, i11, sendSessionResultSuccess(new d3(commandButtonConvertFromPredefinedCustomCommand)));
                    }
                }
                this.connectedControllersManager.flushCommandQueue(controllerInfo);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Failed to convert predefined custom command: " + sessionCommand.customAction, e5);
                sendSessionResult(mediaSessionImpl, controllerInfo, i10, new SessionResult(-3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchSessionTaskWithSessionCommand$15(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, MediaSessionImpl mediaSessionImpl, int i10, int i11, SessionTask sessionTask) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            if (sessionCommand != null) {
                if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, sessionCommand)) {
                    sendSessionResult(mediaSessionImpl, controllerInfo, i10, new SessionResult(-4));
                    return;
                }
            } else if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, i11)) {
                sendSessionResult(mediaSessionImpl, controllerInfo, i10, new SessionResult(-4));
                return;
            }
            sessionTask.run(mediaSessionImpl, controllerInfo, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$flushCommandQueue$69(MediaSession.ControllerInfo controllerInfo) {
        this.connectedControllersManager.flushCommandQueue(controllerInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$getChildren$73(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i12) {
        return mediaLibrarySessionImpl.onGetChildrenOnHandler(controllerInfo, str, i10, i11, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$getItem$72(String str, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaLibrarySessionImpl.onGetItemOnHandler(controllerInfo, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$getLibraryRoot$71(MediaLibraryService.LibraryParams libraryParams, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaLibrarySessionImpl.onGetLibraryRootOnHandler(controllerInfo, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$getSearchResult$75(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i12) {
        return mediaLibrarySessionImpl.onGetSearchResultOnHandler(controllerInfo, str, i10, i11, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleMediaItemsWhenReady$4(MediaSessionImpl mediaSessionImpl, MediaItemPlayerTask mediaItemPlayerTask, MediaSession.ControllerInfo controllerInfo, List list) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$handleMediaItemsWhenReady$5(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, MediaItemPlayerTask mediaItemPlayerTask, List list) throws Exception {
        return Util.postOrRunWithCompletion(mediaSessionImpl.getApplicationHandler(), mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new c(mediaSessionImpl, mediaItemPlayerTask, controllerInfo, list, 10)), new SessionResult(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$handleMediaItemsWhenReady$6(SessionTask sessionTask, MediaItemPlayerTask mediaItemPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.isReleased() ? xc.d.s0(new SessionResult(-100)) : Util.transformFutureAsync((com.google.common.util.concurrent.d1) sessionTask.run(mediaSessionImpl, controllerInfo, i10), new j(mediaSessionImpl, controllerInfo, mediaItemPlayerTask, 8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleMediaItemsWithStartPositionWhenReady$7(MediaSessionImpl mediaSessionImpl, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (mediaSessionImpl.isReleased()) {
            return;
        }
        mediaItemsWithStartPositionPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), mediaItemsWithStartPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$handleMediaItemsWithStartPositionWhenReady$8(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) throws Exception {
        return Util.postOrRunWithCompletion(mediaSessionImpl.getApplicationHandler(), mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new s1(mediaSessionImpl, mediaItemsWithStartPositionPlayerTask, mediaItemsWithStartPosition, 5)), new SessionResult(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$handleMediaItemsWithStartPositionWhenReady$9(SessionTask sessionTask, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.isReleased() ? xc.d.s0(new SessionResult(-100)) : Util.transformFutureAsync((com.google.common.util.concurrent.d1) sessionTask.run(mediaSessionImpl, controllerInfo, i10), new j(mediaSessionImpl, controllerInfo, mediaItemsWithStartPositionPlayerTask, 7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleSessionTaskWhenReady$16(MediaSessionImpl mediaSessionImpl, com.google.common.util.concurrent.q1 q1Var, Consumer consumer, com.google.common.util.concurrent.d1 d1Var) {
        if (mediaSessionImpl.isReleased()) {
            q1Var.set(null);
            return;
        }
        try {
            consumer.accept(d1Var);
            q1Var.set(null);
        } catch (Throwable th) {
            q1Var.setException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$onCustomCommandWithProgressUpdate$24(boolean z, SessionCommand sessionCommand, Bundle bundle, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        Bundle bundle2;
        MediaSession.ControllerInfo controllerInfo2;
        SessionCommand sessionCommand2;
        MediaSessionImpl mediaSessionImpl2;
        ProgressReporter progressReporter;
        if (z) {
            sessionCommand2 = sessionCommand;
            mediaSessionImpl2 = mediaSessionImpl;
            bundle2 = bundle;
            controllerInfo2 = controllerInfo;
            progressReporter = new ProgressReporter(mediaSessionImpl2, controllerInfo2, i10, sessionCommand2, bundle2);
        } else {
            bundle2 = bundle;
            controllerInfo2 = controllerInfo;
            sessionCommand2 = sessionCommand;
            mediaSessionImpl2 = mediaSessionImpl;
            progressReporter = null;
        }
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnCustomCommandOnHandler = mediaSessionImpl2.onCustomCommandOnHandler(controllerInfo2, progressReporter, sessionCommand2, bundle2);
        if (progressReporter != null) {
            progressReporter.setFuture(d1VarOnCustomCommandOnHandler);
        }
        return d1VarOnCustomCommandOnHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onSurfaceSizeChanged$57(int i10, int i11, PlayerWrapper playerWrapper) {
        SurfaceHolderWithSize surfaceHolderWithSize;
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        mediaSessionImpl.getClass();
        if (mediaSessionImpl.shouldUseLegacySurfaceHandling() || (surfaceHolderWithSize = this.surfaceHolderWithSize) == null) {
            return;
        }
        surfaceHolderWithSize.setFixedSize(i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$playForControllerInfo$19(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            return;
        }
        mediaSessionImpl.handleMediaControllerPlayRequest(controllerInfo, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return (com.google.common.util.concurrent.d1) sessionTask.run(mediaSessionImpl, controllerInfo, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14(final MediaSession.ControllerInfo controllerInfo, int i10, final MediaSessionImpl mediaSessionImpl, final int i11, final SessionTask sessionTask) {
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, i10)) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i11, new SessionResult(-4));
            return;
        }
        int iOnPlayerCommandRequestOnHandler = mediaSessionImpl.onPlayerCommandRequestOnHandler(controllerInfo, i10);
        if (iOnPlayerCommandRequestOnHandler != 0) {
            sendSessionResult(mediaSessionImpl, controllerInfo, i11, new SessionResult(iOnPlayerCommandRequestOnHandler));
        } else if (i10 != 27) {
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i10, new ConnectedControllersManager.AsyncCommand() { // from class: androidx.media3.session.z2
                @Override // androidx.media3.session.ConnectedControllersManager.AsyncCommand
                public final com.google.common.util.concurrent.d1 run() {
                    return MediaSessionStub.lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(sessionTask, mediaSessionImpl, controllerInfo, i11);
                }
            });
        } else {
            mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new x1(sessionTask, mediaSessionImpl, controllerInfo, i11)).run();
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i10, new y2());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$release$18(IMediaController iMediaController) {
        this.connectedControllersManager.removeController(iMediaController.asBinder());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeMediaItem$45(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeMediaItems$46(int i10, int i11, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$replaceMediaItem$49(MediaItem mediaItem, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceMediaItem$50(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        if (list.size() == 1) {
            playerWrapper.replaceMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), (MediaItem) list.get(0));
        } else {
            playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10 + 1), list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$replaceMediaItems$51(com.google.common.collect.h1 h1Var, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onAddMediaItemsOnHandler(controllerInfo, h1Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$replaceMediaItems$52(int i10, int i11, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i11), list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$search$74(String str, MediaLibraryService.LibraryParams libraryParams, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaLibrarySessionImpl.onSearchOnHandler(controllerInfo, str, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToDefaultPositionWithMediaItemIndex$21(int i10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekToDefaultPosition(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$seekToWithMediaItemIndex$23(int i10, long j10, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekTo(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i10), j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$sendLibraryResultWhenReady$10(MediaSession.ControllerInfo controllerInfo, int i10, com.google.common.util.concurrent.d1 d1Var) {
        LibraryResult libraryResultOfError;
        try {
            libraryResultOfError = (LibraryResult) d1Var.get();
            ac.b.p(libraryResultOfError, "LibraryResult must not be null");
        } catch (InterruptedException e5) {
            e = e5;
            Log.w(TAG, "Library operation failed", e);
            libraryResultOfError = LibraryResult.ofError(-1);
        } catch (CancellationException e6) {
            Log.w(TAG, "Library operation cancelled", e6);
            libraryResultOfError = LibraryResult.ofError(1);
        } catch (ExecutionException e10) {
            e = e10;
            Log.w(TAG, "Library operation failed", e);
            libraryResultOfError = LibraryResult.ofError(-1);
        }
        sendLibraryResult(controllerInfo, i10, libraryResultOfError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$sendLibraryResultWhenReady$11(SessionTask sessionTask, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return handleSessionTaskWhenReady(mediaLibrarySessionImpl, controllerInfo, i10, sessionTask, new l1(controllerInfo, i10, 7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.google.common.util.concurrent.d1 lambda$sendSessionResultSuccess$1(ControllerPlayerTask controllerPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        if (mediaSessionImpl.isReleased()) {
            return com.google.common.util.concurrent.z0.f14245l;
        }
        controllerPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo);
        sendSessionResult(mediaSessionImpl, controllerInfo, i10, new SessionResult(0));
        return com.google.common.util.concurrent.z0.f14245l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$sendSessionResultWhenReady$2(androidx.media3.session.MediaSessionImpl r2, androidx.media3.session.MediaSession.ControllerInfo r3, int r4, com.google.common.util.concurrent.d1 r5) {
        /*
            java.lang.String r0 = "MediaSessionStub"
            java.lang.Object r5 = r5.get()     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10 java.util.concurrent.CancellationException -> L12
            androidx.media3.session.SessionResult r5 = (androidx.media3.session.SessionResult) r5     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10 java.util.concurrent.CancellationException -> L12
            java.lang.String r1 = "SessionResult must not be null"
            ac.b.p(r5, r1)     // Catch: java.lang.InterruptedException -> Le java.util.concurrent.ExecutionException -> L10 java.util.concurrent.CancellationException -> L12
            goto L36
        Le:
            r5 = move-exception
            goto L14
        L10:
            r5 = move-exception
            goto L14
        L12:
            r5 = move-exception
            goto L2b
        L14:
            java.lang.String r1 = "Session operation failed"
            androidx.media3.common.util.Log.w(r0, r1, r5)
            androidx.media3.session.SessionResult r0 = new androidx.media3.session.SessionResult
            java.lang.Throwable r5 = r5.getCause()
            boolean r5 = r5 instanceof java.lang.UnsupportedOperationException
            if (r5 == 0) goto L25
            r5 = -6
            goto L26
        L25:
            r5 = -1
        L26:
            r0.<init>(r5)
            r5 = r0
            goto L36
        L2b:
            java.lang.String r1 = "Session operation cancelled"
            androidx.media3.common.util.Log.w(r0, r1, r5)
            androidx.media3.session.SessionResult r5 = new androidx.media3.session.SessionResult
            r0 = 1
            r5.<init>(r0)
        L36:
            sendSessionResult(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionStub.lambda$sendSessionResultWhenReady$2(androidx.media3.session.MediaSessionImpl, androidx.media3.session.MediaSession$ControllerInfo, int, com.google.common.util.concurrent.d1):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$sendSessionResultWhenReady$3(SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return handleSessionTaskWhenReady(mediaSessionImpl, controllerInfo, i10, sessionTask, new x0(mediaSessionImpl, controllerInfo, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setMediaItemItemWithResetPositionForControllerInfo$33(MediaItem mediaItem, boolean z, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem), z ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), z ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setMediaItemWithStartPosition$32(MediaItem mediaItem, long j10, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem), 0, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setMediaItemsWithResetPosition$34(List list, boolean z, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, z ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex(), z ? C.TIME_UNSET : mediaSessionImpl.getPlayerWrapper().getCurrentPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setMediaItemsWithStartIndex$35(List list, int i10, long j10, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i11) {
        int currentMediaItemIndex = i10 == -1 ? mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex() : i10;
        if (i10 == -1) {
            j10 = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, currentMediaItemIndex, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setRating$29(Rating rating, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, rating);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$setRatingWithMediaId$28(String str, Rating rating, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaSessionImpl.onSetRatingOnHandler(controllerInfo, str, rating);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setTrackSelectionParameters$70(TrackSelectionParameters trackSelectionParameters, PlayerWrapper playerWrapper) {
        playerWrapper.setTrackSelectionParameters(updateOverridesUsingUniqueTrackGroupIds(trackSelectionParameters));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$setVideoSurface$55(Surface surface, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        mediaSessionImpl.getClass();
        if (mediaSessionImpl.shouldUseLegacySurfaceHandling()) {
            playerWrapper.setVideoSurface(surface);
            return;
        }
        if (surface == null) {
            playerWrapper.setVideoSurfaceHolder(null);
            this.surfaceHolderWithSize = null;
        } else {
            SurfaceHolderWithSize surfaceHolderWithSize = new SurfaceHolderWithSize(surface);
            this.surfaceHolderWithSize = surfaceHolderWithSize;
            playerWrapper.setVideoSurfaceHolder(surfaceHolderWithSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$setVideoSurfaceWithSize$56(Surface surface, int i10, int i11, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        mediaSessionImpl.getClass();
        if (mediaSessionImpl.shouldUseLegacySurfaceHandling()) {
            playerWrapper.setVideoSurface(surface);
            return;
        }
        if (surface == null) {
            playerWrapper.setVideoSurfaceHolder(null);
            this.surfaceHolderWithSize = null;
        } else {
            SurfaceHolderWithSize surfaceHolderWithSize = new SurfaceHolderWithSize(surface, i10, i11);
            this.surfaceHolderWithSize = surfaceHolderWithSize;
            playerWrapper.setVideoSurfaceHolder(surfaceHolderWithSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$subscribe$76(String str, MediaLibraryService.LibraryParams libraryParams, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaLibrarySessionImpl.onSubscribeOnHandler(controllerInfo, str, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ com.google.common.util.concurrent.d1 lambda$unsubscribe$77(String str, MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return mediaLibrarySessionImpl.onUnsubscribeOnHandler(controllerInfo, str);
    }

    private int maybeCorrectMediaItemIndex(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper, int i10) {
        return (playerWrapper.isCommandAvailable(17) && !this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17) && this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16)) ? i10 + playerWrapper.getCurrentMediaItemIndex() : i10;
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommand(IMediaController iMediaController, int i10, int i11, SessionTask<com.google.common.util.concurrent.d1<Void>, K> sessionTask) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
        if (controller != null) {
            queueSessionTaskWithPlayerCommandForControllerInfo(controller, i10, i11, sessionTask);
        }
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommandForControllerInfo(final MediaSession.ControllerInfo controllerInfo, final int i10, final int i11, final SessionTask<com.google.common.util.concurrent.d1<Void>, K> sessionTask) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.f3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f3980i.lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14(controllerInfo, i11, mediaSessionImpl, i10, sessionTask);
                    }
                });
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i10, LibraryResult<?> libraryResult) {
        try {
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            controllerCb.getClass();
            controllerCb.onLibraryResult(i10, libraryResult);
        } catch (RemoteException e5) {
            Log.w(TAG, "Failed to send result to browser " + controllerInfo, e5);
        }
    }

    private static <V, K extends MediaLibrarySessionImpl> SessionTask<com.google.common.util.concurrent.d1<Void>, K> sendLibraryResultWhenReady(SessionTask<com.google.common.util.concurrent.d1<LibraryResult<V>>, K> sessionTask) {
        return new g3(sessionTask, 0);
    }

    private static void sendSessionResult(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10, SessionResult sessionResult) {
        try {
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            controllerCb.getClass();
            controllerCb.onSessionResult(i10, sessionResult);
            mediaSessionImpl.triggerPlayerInfoUpdate();
        } catch (RemoteException e5) {
            Log.w(TAG, "Failed to send result to controller " + controllerInfo, e5);
        }
    }

    private static <K extends MediaSessionImpl> SessionTask<com.google.common.util.concurrent.d1<Void>, K> sendSessionResultSuccess(Consumer<PlayerWrapper> consumer) {
        return sendSessionResultSuccess((ControllerPlayerTask) new k1(consumer, 8));
    }

    private static <K extends MediaSessionImpl> SessionTask<com.google.common.util.concurrent.d1<Void>, K> sendSessionResultWhenReady(SessionTask<com.google.common.util.concurrent.d1<SessionResult>, K> sessionTask) {
        return new g3(sessionTask, 1);
    }

    private void setMediaItemItemWithResetPositionForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10, MediaItem mediaItem, boolean z) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new h1(1, mediaItem, z), new v(5))));
    }

    private TrackSelectionParameters updateOverridesUsingUniqueTrackGroupIds(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters.overrides.isEmpty()) {
            return trackSelectionParameters;
        }
        TrackSelectionParameters.Builder builderClearOverrides = trackSelectionParameters.buildUpon().clearOverrides();
        o4 it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) it.next();
            TrackGroup trackGroup = (TrackGroup) ((n3) this.trackGroupIdMap).f14073r.get(trackSelectionOverride.mediaTrackGroup.id);
            if (trackGroup == null || trackSelectionOverride.mediaTrackGroup.length != trackGroup.length) {
                builderClearOverrides.addOverride(trackSelectionOverride);
            } else {
                builderClearOverrides.addOverride(new TrackSelectionOverride(trackGroup, trackSelectionOverride.trackIndices));
            }
        }
        return builderClearOverrides.build();
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItem(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new a3(MediaItem.fromBundle(bundle), 2), new v(17))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemWithIndex(IMediaController iMediaController, int i10, int i11, Bundle bundle) {
        if (iMediaController == null || bundle == null || i11 < 0) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new a3(MediaItem.fromBundle(bundle), 0), new x2(this, i11, 1))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new b3(1, BundleCollectionUtil.fromBundleList(new f(2), BundleListRetriever.getList(iBinder))), new v(16))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void addMediaItemsWithIndex(IMediaController iMediaController, int i10, int i11, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i11 < 0) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new b3(0, BundleCollectionUtil.fromBundleList(new f(2), BundleListRetriever.getList(iBinder))), new x2(this, i11, 3))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void clearMediaItems(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultSuccess(new v(22)));
    }

    public void connect(IMediaController iMediaController, MediaSession.ControllerInfo controllerInfo) {
        if (iMediaController == null || controllerInfo == null) {
            SessionUtil.disconnectIMediaController(iMediaController);
            return;
        }
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
            SessionUtil.disconnectIMediaController(iMediaController);
        } else {
            this.pendingControllers.add(controllerInfo);
            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new c(this, controllerInfo, mediaSessionImpl, iMediaController, 9));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolume(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 26, sendSessionResultSuccess(new v(15)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 34, sendSessionResultSuccess(new z(i11, 4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void flushCommandQueue(IMediaController iMediaController) {
        if (iMediaController == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller != null) {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new g(this, controller, 13));
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public PlayerInfo generateAndCacheUniqueTrackGroupIds(PlayerInfo playerInfo) {
        com.google.common.collect.h1 groups = playerInfo.currentTracks.getGroups();
        com.google.common.collect.e1 e1VarK = com.google.common.collect.h1.k();
        com.google.common.collect.y0 y0Var = new com.google.common.collect.y0(4);
        for (int i10 = 0; i10 < groups.size(); i10++) {
            Tracks.Group group = (Tracks.Group) groups.get(i10);
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            String strGenerateUniqueTrackGroupId = (String) this.trackGroupIdMap.get(mediaTrackGroup);
            if (strGenerateUniqueTrackGroupId == null) {
                strGenerateUniqueTrackGroupId = generateUniqueTrackGroupId(mediaTrackGroup);
            }
            y0Var.f(mediaTrackGroup, strGenerateUniqueTrackGroupId);
            e1VarK.c(group.copyWithId(strGenerateUniqueTrackGroupId));
        }
        this.trackGroupIdMap = y0Var.f14041b == 0 ? n3.f14068s : new n3(y0Var.f14040a, y0Var.f14041b);
        PlayerInfo playerInfoCopyWithCurrentTracks = playerInfo.copyWithCurrentTracks(new Tracks(e1VarK.f()));
        if (playerInfoCopyWithCurrentTracks.trackSelectionParameters.overrides.isEmpty()) {
            return playerInfoCopyWithCurrentTracks;
        }
        TrackSelectionParameters.Builder builderClearOverrides = playerInfoCopyWithCurrentTracks.trackSelectionParameters.buildUpon().clearOverrides();
        o4 it = playerInfoCopyWithCurrentTracks.trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride trackSelectionOverride = (TrackSelectionOverride) it.next();
            TrackGroup trackGroup = trackSelectionOverride.mediaTrackGroup;
            String str = (String) this.trackGroupIdMap.get(trackGroup);
            if (str != null) {
                builderClearOverrides.addOverride(new TrackSelectionOverride(trackGroup.copyWithId(str), trackSelectionOverride.trackIndices));
            } else {
                builderClearOverrides.addOverride(trackSelectionOverride);
            }
        }
        return playerInfoCopyWithCurrentTracks.copyWithTrackSelectionParameters(builderClearOverrides.build());
    }

    @Override // androidx.media3.session.IMediaSession
    public void getChildren(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getChildren(): Ignoring empty parentId");
            return;
        }
        if (i11 < 0) {
            Log.w(TAG, "getChildren(): Ignoring negative page");
            return;
        }
        if (i12 < 1) {
            Log.w(TAG, "getChildren(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, sendLibraryResultWhenReady(new w2(str, i11, i12, libraryParamsFromBundle, 0)));
    }

    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    @Override // androidx.media3.session.IMediaSession
    public void getItem(IMediaController iMediaController, int i10, String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getItem(): Ignoring empty mediaId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, sendLibraryResultWhenReady(new v2(str, 1)));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void getLibraryRoot(IMediaController iMediaController, int i10, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, 50000, sendLibraryResultWhenReady(new k1(libraryParamsFromBundle, 7)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void getSearchResult(IMediaController iMediaController, int i10, String str, int i11, int i12, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "getSearchResult(): Ignoring empty query");
            return;
        }
        if (i11 < 0) {
            Log.w(TAG, "getSearchResult(): Ignoring negative page");
            return;
        }
        if (i12 < 1) {
            Log.w(TAG, "getSearchResult(): Ignoring pageSize less than 1");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, sendLibraryResultWhenReady(new w2(str, i11, i12, libraryParamsFromBundle, 1)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolume(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 26, sendSessionResultSuccess(new v(9)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 34, sendSessionResultSuccess(new z(i11, 5)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItem(IMediaController iMediaController, int i10, int i11, int i12) {
        if (iMediaController == null || i11 < 0 || i12 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultSuccess(new u(i11, i12, 4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void moveMediaItems(IMediaController iMediaController, int i10, final int i11, final int i12, final int i13) {
        if (iMediaController == null || i11 < 0 || i12 < i11 || i13 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new Consumer() { // from class: androidx.media3.session.c3
            @Override // androidx.media3.common.util.Consumer
            public final void accept(Object obj) {
                ((PlayerWrapper) obj).moveMediaItems(i11, i12, i13);
            }
        }));
    }

    @Override // androidx.media3.session.IMediaSession
    public void mute(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 24, sendSessionResultSuccess(new v(6)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void onControllerResult(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            SessionResult sessionResultFromBundle = SessionResult.fromBundle(bundle);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(iMediaController.asBinder());
                if (sequencedFutureManager == null) {
                    return;
                }
                sequencedFutureManager.setFutureResult(i10, sessionResultFromBundle);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommand(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2) {
        onCustomCommandWithProgressUpdate(iMediaController, i10, bundle, bundle2, false);
    }

    @Override // androidx.media3.session.IMediaSession
    public void onCustomCommandWithProgressUpdate(IMediaController iMediaController, int i10, Bundle bundle, Bundle bundle2, boolean z) {
        if (iMediaController == null || bundle == null || bundle2 == null) {
            return;
        }
        try {
            SessionCommand sessionCommandFromBundle = SessionCommand.fromBundle(bundle);
            if (CommandButton.isPredefinedCustomCommandButtonCode(sessionCommandFromBundle.customAction)) {
                dispatchCustomCommandAsPredefinedCommand(iMediaController, i10, sessionCommandFromBundle);
            } else {
                dispatchSessionTaskWithSessionCommand(iMediaController, i10, sessionCommandFromBundle, sendSessionResultWhenReady(new a0(z, sessionCommandFromBundle, bundle2)));
            }
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void onSurfaceSizeChanged(IMediaController iMediaController, int i10, int i11, int i12) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new u2(this, i11, i12)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void pause(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        pauseForControllerInfo(controller, i10);
    }

    public void pauseForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 1, sendSessionResultSuccess(new v(7)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void play(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        playForControllerInfo(controller, i10);
    }

    public void playForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 1, sendSessionResultSuccess(new k(this, controllerInfo, 18)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void prepare(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 2, sendSessionResultSuccess(new v(21)));
    }

    public void release() {
        for (MediaSession.ControllerInfo controllerInfo : this.connectedControllersManager.getConnectedControllers()) {
            this.connectedControllersManager.removeController(controllerInfo);
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                controllerCb.onDisconnected(0);
            }
        }
        Iterator<MediaSession.ControllerInfo> it = this.pendingControllers.iterator();
        while (it.hasNext()) {
            MediaSession.ControllerCb controllerCb2 = it.next().getControllerCb();
            if (controllerCb2 != null) {
                controllerCb2.onDisconnected(0);
            }
        }
        this.pendingControllers.clear();
        this.sessionImpl.clear();
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItem(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null || i11 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultSuccess(new x2(this, i11, 4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void removeMediaItems(IMediaController iMediaController, int i10, int i11, int i12) {
        if (iMediaController == null || i11 < 0 || i12 < i11) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultSuccess((ControllerPlayerTask) new u2(this, i11, i12)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItem(IMediaController iMediaController, int i10, int i11, Bundle bundle) {
        if (iMediaController == null || bundle == null || i11 < 0) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new a3(MediaItem.fromBundle(bundle), 1), new x2(this, i11, 2))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void replaceMediaItems(IMediaController iMediaController, int i10, int i11, int i12, IBinder iBinder) {
        if (iMediaController == null || iBinder == null || i11 < 0 || i12 < i11) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new d2(4, BundleCollectionUtil.fromBundleList(new f(2), BundleListRetriever.getList(iBinder))), new u2(this, i11, i12))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void search(IMediaController iMediaController, int i10, String str, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "search(): Ignoring empty query");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, sendLibraryResultWhenReady(new h3(str, 1, libraryParamsFromBundle)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekBack(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekBackForControllerInfo(controller, i10);
    }

    public void seekBackForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 11, sendSessionResultSuccess(new v(10)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekForward(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekForwardForControllerInfo(controller, i10);
    }

    public void seekForwardForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 12, sendSessionResultSuccess(new v(13)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekTo(IMediaController iMediaController, int i10, long j10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 5, sendSessionResultSuccess(new j2(j10, 2)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPosition(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 4, sendSessionResultSuccess(new v(18)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null || i11 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 10, sendSessionResultSuccess(new x2(this, i11, 0)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNext(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToNextForControllerInfo(controller, i10);
    }

    public void seekToNextForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 9, sendSessionResultSuccess(new v(14)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToNextMediaItem(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 8, sendSessionResultSuccess(new v(8)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPrevious(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        seekToPreviousForControllerInfo(controller, i10);
    }

    public void seekToPreviousForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 7, sendSessionResultSuccess(new v(11)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToPreviousMediaItem(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 6, sendSessionResultSuccess(new v(12)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void seekToWithMediaItemIndex(IMediaController iMediaController, int i10, int i11, long j10) {
        if (iMediaController == null || i11 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 10, sendSessionResultSuccess(new z0(i11, j10, this)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setAudioAttributes(IMediaController iMediaController, int i10, Bundle bundle, boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 35, sendSessionResultSuccess(new h1(2, AudioAttributes.fromBundle(bundle), z)));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for AudioAttributes", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMuted(IMediaController iMediaController, int i10, boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 26, sendSessionResultSuccess(new r0(z, 5)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceMutedWithFlags(IMediaController iMediaController, int i10, boolean z, int i11) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 34, sendSessionResultSuccess(new k2(z, i11, 2)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolume(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null || i11 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 25, sendSessionResultSuccess(new z(i11, 7)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i10, int i11, int i12) {
        if (iMediaController == null || i11 < 0) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 33, sendSessionResultSuccess(new u(i11, i12, 3)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItem(IMediaController iMediaController, int i10, Bundle bundle) {
        setMediaItemWithResetPosition(iMediaController, i10, bundle, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithResetPosition(IMediaController iMediaController, int i10, Bundle bundle, boolean z) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            MediaItem mediaItemFromBundle = MediaItem.fromBundle(bundle);
            MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
            if (controller != null) {
                setMediaItemItemWithResetPositionForControllerInfo(controller, i10, mediaItemFromBundle, z);
            }
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemWithStartPosition(IMediaController iMediaController, int i10, Bundle bundle, long j10) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new g0(MediaItem.fromBundle(bundle), j10), new v(5))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItems(IMediaController iMediaController, int i10, IBinder iBinder) {
        setMediaItemsWithResetPosition(iMediaController, i10, iBinder, true);
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i10, IBinder iBinder, boolean z) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new h1(3, BundleCollectionUtil.fromBundleList(new f(2), BundleListRetriever.getList(iBinder)), z), new v(5))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i10, IBinder iBinder, int i11, long j10) {
        if (iMediaController == null || iBinder == null) {
            return;
        }
        if (i11 == -1 || i11 >= 0) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i10, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new z0(i11, j10, BundleCollectionUtil.fromBundleList(new f(2), BundleListRetriever.getList(iBinder))), new v(5))));
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e5);
            }
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlayWhenReady(IMediaController iMediaController, int i10, boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 1, sendSessionResultSuccess(new r0(z, 4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackParameters(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 13, sendSessionResultSuccess(new f0(3, PlaybackParameters.fromBundle(bundle))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for PlaybackParameters", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaybackSpeed(IMediaController iMediaController, int i10, float f10) {
        if (iMediaController == null || f10 <= 0.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 13, sendSessionResultSuccess(new t(f10, 3)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setPlaylistMetadata(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 19, sendSessionResultSuccess(new i0(3, MediaMetadata.fromBundle(bundle))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for MediaMetadata", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRating(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new k1(Rating.fromBundle(bundle), 6)));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRatingWithMediaId(IMediaController iMediaController, int i10, String str, Bundle bundle) {
        if (iMediaController == null || str == null || bundle == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "setRatingWithMediaId(): Ignoring empty mediaId");
            return;
        }
        try {
            dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new k(str, Rating.fromBundle(bundle))));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for Rating", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setRepeatMode(IMediaController iMediaController, int i10, int i11) {
        if (iMediaController == null) {
            return;
        }
        if (i11 == 2 || i11 == 0 || i11 == 1) {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 15, sendSessionResultSuccess(new z(i11, 6)));
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setShuffleModeEnabled(IMediaController iMediaController, int i10, boolean z) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 14, sendSessionResultSuccess(new r0(z, 6)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setTrackSelectionParameters(IMediaController iMediaController, int i10, Bundle bundle) {
        if (iMediaController == null || bundle == null) {
            return;
        }
        try {
            queueSessionTaskWithPlayerCommand(iMediaController, i10, 29, sendSessionResultSuccess(new k(this, TrackSelectionParameters.fromBundle(bundle), 17)));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for TrackSelectionParameters", e5);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurface(IMediaController iMediaController, int i10, Surface surface) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 27, sendSessionResultSuccess(new k(this, surface, 21)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVideoSurfaceWithSize(IMediaController iMediaController, int i10, Surface surface, int i11, int i12) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 27, sendSessionResultSuccess(new h0(this, surface, i11, i12, 2)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void setVolume(IMediaController iMediaController, int i10, float f10) {
        if (iMediaController == null || f10 < 0.0f || f10 > 1.0f) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 24, sendSessionResultSuccess(new t(f10, 4)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void stop(IMediaController iMediaController, int i10) {
        MediaSession.ControllerInfo controller;
        if (iMediaController == null || (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) == null) {
            return;
        }
        stopForControllerInfo(controller, i10);
    }

    public void stopForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i10) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i10, 3, sendSessionResultSuccess(new v(19)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void subscribe(IMediaController iMediaController, int i10, String str, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "subscribe(): Ignoring empty parentId");
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, sendLibraryResultWhenReady(new h3(str, 0, libraryParamsFromBundle)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void unmute(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        queueSessionTaskWithPlayerCommand(iMediaController, i10, 24, sendSessionResultSuccess(new v(20)));
    }

    @Override // androidx.media3.session.IMediaSession
    public void unsubscribe(IMediaController iMediaController, int i10, String str) {
        if (iMediaController == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "unsubscribe(): Ignoring empty parentId");
        } else {
            dispatchSessionTaskWithSessionCommand(iMediaController, i10, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, sendLibraryResultWhenReady(new v2(str, 0)));
        }
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i10, SessionCommand sessionCommand, SessionTask<com.google.common.util.concurrent.d1<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i10, sessionCommand, 0, sessionTask);
    }

    private static <K extends MediaSessionImpl> SessionTask<com.google.common.util.concurrent.d1<Void>, K> sendSessionResultSuccess(ControllerPlayerTask controllerPlayerTask) {
        return new k1(controllerPlayerTask, 9);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, final int i10, final SessionCommand sessionCommand, final int i11, final SessionTask<com.google.common.util.concurrent.d1<Void>, K> sessionTask) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                final MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                if (controller == null) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                } else {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.e3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f3967i.lambda$dispatchSessionTaskWithSessionCommand$15(controller, sessionCommand, mediaSessionImpl, i10, i11, sessionTask);
                        }
                    });
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // androidx.media3.session.IMediaSession
    public void connect(IMediaController iMediaController, int i10, Bundle bundle) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
        if (iMediaController != null && bundle != null && mediaSessionImpl != null) {
            try {
                ConnectionRequest connectionRequestFromBundle = ConnectionRequest.fromBundle(bundle);
                int callingUid = Binder.getCallingUid();
                int callingPid = Binder.getCallingPid();
                String str = connectionRequestFromBundle.packageName;
                int iCheckPackageValidity = SessionUtil.checkPackageValidity(mediaSessionImpl.getContext(), str, callingUid);
                if (iCheckPackageValidity == 1) {
                    Log.w(TAG, "Ignoring connection from invalid package name " + str + " (uid=" + callingUid + ")");
                    SessionUtil.disconnectIMediaController(iMediaController);
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                if (callingPid == 0) {
                    callingPid = connectionRequestFromBundle.pid;
                }
                try {
                    MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(str, callingPid, callingUid);
                    boolean zIsTrustedForMediaControl = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext()).isTrustedForMediaControl(remoteUserInfo);
                    int i11 = connectionRequestFromBundle.libraryVersion;
                    int i12 = connectionRequestFromBundle.controllerInterfaceVersion;
                    connect(iMediaController, new MediaSession.ControllerInfo(remoteUserInfo, i11, i12, zIsTrustedForMediaControl, new Controller2Cb(iMediaController, i12), connectionRequestFromBundle.connectionHints, connectionRequestFromBundle.maxCommandsForMediaItems, iCheckPackageValidity == 0));
                    return;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for ConnectionRequest", e5);
                return;
            }
        }
        SessionUtil.disconnectIMediaController(iMediaController);
    }

    @Override // androidx.media3.session.IMediaSession
    public void release(IMediaController iMediaController, int i10) {
        if (iMediaController == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = this.sessionImpl.get();
            if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new g(this, iMediaController, 12));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
