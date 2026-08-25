package androidx.media3.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.RatingCompat;
import androidx.media3.session.legacy.VolumeProviderCompat;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 300000;
    private static final String DEFAULT_MEDIA_SESSION_TAG_DELIM = ".";
    private static final String DEFAULT_MEDIA_SESSION_TAG_PREFIX = "androidx.media3.session.id";
    private static final int PENDING_INTENT_FLAG_MUTABLE;
    private static final String TAG = "MediaSessionLegacyStub";
    private Player.Commands availablePlayerCommands;
    private SessionCommands availableSessionCommands;
    private final ComponentName broadcastReceiverComponentName;
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final ConnectionTimeoutHandler connectionTimeoutHandler;
    private volatile long connectionTimeoutMs;
    private final ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast;
    private com.google.common.collect.h1 customLayout;
    private PlaybackException customPlaybackException;
    private LegacyError legacyError;
    private Bundle legacyExtras;
    private com.google.common.collect.h1 mediaButtonPreferences;
    private com.google.common.util.concurrent.v0<Bitmap> pendingBitmapLoadCallback;
    private final boolean playIfSuppressed;
    private Player.Commands playerCommandsForErrorState;
    private final MediaButtonReceiver runtimeBroadcastReceiver;
    private final MediaSessionCompat sessionCompat;
    private int sessionFlags;
    private final MediaSessionImpl sessionImpl;
    private final MediaSessionManager sessionManager;
    private VolumeProviderCompat volumeProviderCompat;

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$1, reason: invalid class name */
    public class AnonymousClass1 implements com.google.common.util.concurrent.v0<MediaSession.MediaItemsWithStartPosition> {
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ boolean val$play;
        final /* synthetic */ boolean val$prepare;

        public AnonymousClass1(MediaSession.ControllerInfo controllerInfo, boolean z, boolean z5) {
            this.val$controller = controllerInfo;
            this.val$prepare = z;
            this.val$play = z5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, boolean z5, MediaSession.ControllerInfo controllerInfo) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaUtils.setMediaItemsWithStartIndexAndPosition(playerWrapper, mediaItemsWithStartPosition);
            int playbackState = playerWrapper.getPlaybackState();
            if (z) {
                if (playbackState == 1) {
                    playerWrapper.prepareIfCommandAvailable();
                } else if (playbackState == 4) {
                    playerWrapper.seekToDefaultPositionIfCommandAvailable();
                }
            }
            if (z5) {
                playerWrapper.playIfCommandAvailable();
            }
            MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().addAll(31, 2).addIf(1, z5).build());
        }

        @Override // com.google.common.util.concurrent.v0
        public void onFailure(Throwable th) {
        }

        @Override // com.google.common.util.concurrent.v0
        public void onSuccess(final MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
            Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
            MediaSessionImpl mediaSessionImpl = MediaSessionLegacyStub.this.sessionImpl;
            final MediaSession.ControllerInfo controllerInfo = this.val$controller;
            final boolean z = this.val$prepare;
            final boolean z5 = this.val$play;
            Util.postOrRun(applicationHandler, mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new Runnable() { // from class: androidx.media3.session.q2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f4120i.lambda$onSuccess$0(mediaItemsWithStartPosition, z, z5, controllerInfo);
                }
            }));
        }
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$2, reason: invalid class name */
    public class AnonymousClass2 implements com.google.common.util.concurrent.v0<List<MediaItem>> {
        final /* synthetic */ MediaSession.ControllerInfo val$controller;
        final /* synthetic */ int val$index;

        public AnonymousClass2(MediaSession.ControllerInfo controllerInfo, int i10) {
            this.val$controller = controllerInfo;
            this.val$index = i10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0(int i10, List list, MediaSession.ControllerInfo controllerInfo) {
            if (i10 == -1) {
                MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().addMediaItems(list);
            } else {
                MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().addMediaItems(i10, list);
            }
            MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().add(20).build());
        }

        @Override // com.google.common.util.concurrent.v0
        public void onFailure(Throwable th) {
        }

        @Override // com.google.common.util.concurrent.v0
        public void onSuccess(List<MediaItem> list) {
            Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
            MediaSessionImpl mediaSessionImpl = MediaSessionLegacyStub.this.sessionImpl;
            MediaSession.ControllerInfo controllerInfo = this.val$controller;
            Util.postOrRun(applicationHandler, mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new x1(this.val$index, 1, this, list, controllerInfo)));
        }
    }

    /* JADX INFO: renamed from: androidx.media3.session.MediaSessionLegacyStub$3, reason: invalid class name */
    public class AnonymousClass3 extends VolumeProviderCompat {
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ int val$legacyVolumeFlag;
        final /* synthetic */ PlayerWrapper val$player;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(int i10, int i11, int i12, String str, Handler handler, PlayerWrapper playerWrapper, int i13) {
            super(i10, i11, i12, str);
            this.val$handler = handler;
            this.val$player = playerWrapper;
            this.val$legacyVolumeFlag = i13;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onAdjustVolume$1(PlayerWrapper playerWrapper, int i10, int i11) {
            if (playerWrapper.isCommandAvailable(26) || playerWrapper.isCommandAvailable(34)) {
                if (i10 == -100) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.setDeviceMuted(true, i11);
                        return;
                    } else {
                        playerWrapper.setDeviceMuted(true);
                        return;
                    }
                }
                if (i10 == -1) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.decreaseDeviceVolume(i11);
                        return;
                    } else {
                        playerWrapper.decreaseDeviceVolume();
                        return;
                    }
                }
                if (i10 == 1) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.increaseDeviceVolume(i11);
                        return;
                    } else {
                        playerWrapper.increaseDeviceVolume();
                        return;
                    }
                }
                if (i10 == 100) {
                    if (playerWrapper.isCommandAvailable(34)) {
                        playerWrapper.setDeviceMuted(false, i11);
                        return;
                    } else {
                        playerWrapper.setDeviceMuted(false);
                        return;
                    }
                }
                if (i10 != 101) {
                    androidx.fragment.app.a2.s(i10, "onAdjustVolume: Ignoring unknown direction: ", "VolumeProviderCompat");
                } else if (playerWrapper.isCommandAvailable(34)) {
                    playerWrapper.setDeviceMuted(!playerWrapper.isDeviceMutedWithCommandCheck(), i11);
                } else {
                    playerWrapper.setDeviceMuted(!playerWrapper.isDeviceMutedWithCommandCheck());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onSetVolumeTo$0(PlayerWrapper playerWrapper, int i10, int i11) {
            if (playerWrapper.isCommandAvailable(25) || playerWrapper.isCommandAvailable(33)) {
                if (playerWrapper.isCommandAvailable(33)) {
                    playerWrapper.setDeviceVolume(i10, i11);
                } else {
                    playerWrapper.setDeviceVolume(i10);
                }
            }
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onAdjustVolume(int i10) {
            Util.postOrRun(this.val$handler, new r2(i10, this.val$legacyVolumeFlag, 1, this.val$player));
        }

        @Override // androidx.media3.session.legacy.VolumeProviderCompat
        public void onSetVolumeTo(int i10) {
            Util.postOrRun(this.val$handler, new r2(i10, this.val$legacyVolumeFlag, 0, this.val$player));
        }
    }

    public static final class Api31 {
        private Api31() {
        }

        public static void setMediaButtonBroadcastReceiver(MediaSessionCompat mediaSessionCompat, ComponentName componentName) {
            try {
                Object mediaSession = mediaSessionCompat.getMediaSession();
                mediaSession.getClass();
                ((android.media.session.MediaSession) mediaSession).setMediaButtonBroadcastReceiver(componentName);
            } catch (IllegalArgumentException e5) {
                if (!Build.MANUFACTURER.equals("motorola")) {
                    throw e5;
                }
                Log.e(MediaSessionLegacyStub.TAG, "caught IllegalArgumentException on a motorola device when attempting to set the media button broadcast receiver. See https://github.com/androidx/media/issues/1730 for details.", e5);
            }
        }
    }

    public static class ConnectionTimeoutHandler extends Handler {
        private static final int MSG_CONNECTION_TIMED_OUT = 1001;
        private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;

        public ConnectionTimeoutHandler(Looper looper, ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager) {
            super(looper);
            this.connectedControllersManager = connectedControllersManager;
        }

        public void disconnectControllerAfterTimeout(MediaSession.ControllerInfo controllerInfo, long j10) {
            removeMessages(1001, controllerInfo);
            sendMessageDelayed(obtainMessage(1001, controllerInfo), j10);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) message.obj;
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
                controllerCb.getClass();
                controllerCb.onDisconnected(0);
                this.connectedControllersManager.removeController(controllerInfo);
            }
        }
    }

    public static final class ControllerLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        public ControllerLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.remoteUserInfo = remoteUserInfo;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ControllerLegacyCb.class) {
                return false;
            }
            return Objects.equals(this.remoteUserInfo, ((ControllerLegacyCb) obj).remoteUserInfo);
        }

        public int hashCode() {
            return Objects.hash(this.remoteUserInfo);
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
        public final /* synthetic */ void onAvailableCommandsChangedFromPlayer(int i10, Player.Commands commands) {
            c2.c(this, i10, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onAvailableCommandsChangedFromSession(int i10, SessionCommands sessionCommands, Player.Commands commands) {
            c2.d(this, i10, sessionCommands, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) {
            c2.e(this, i10, str, i11, libraryParams);
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
        public final /* synthetic */ void onDisconnected(int i10) {
            c2.h(this, i10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onError(int i10, SessionError sessionError) {
            c2.i(this, i10, sessionError);
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
        public final /* synthetic */ void onLibraryResult(int i10, LibraryResult libraryResult) {
            c2.l(this, i10, libraryResult);
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
        public final /* synthetic */ void onPeriodicSessionPositionInfoChanged(int i10, SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, int i11) {
            c2.o(this, i10, sessionPositionInfo, z, z5, i11);
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
        public final /* synthetic */ void onPlayerInfoChanged(int i10, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z5) {
            c2.v(this, i10, playerInfo, commands, z, z5);
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
        public final /* synthetic */ void onRenderedFirstFrame(int i10) {
            c2.y(this, i10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onRepeatModeChanged(int i10, int i11) {
            c2.z(this, i10, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) {
            c2.A(this, i10, str, i11, libraryParams);
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
        public final /* synthetic */ void onSessionActivityChanged(int i10, PendingIntent pendingIntent) {
            c2.D(this, i10, pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSessionExtrasChanged(int i10, Bundle bundle) {
            c2.E(this, i10, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSessionResult(int i10, SessionResult sessionResult) {
            c2.F(this, i10, sessionResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onShuffleModeEnabledChanged(int i10, boolean z) {
            c2.G(this, i10, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11, int i12) {
            c2.H(this, i10, i11, i12);
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
        public final /* synthetic */ void sendCustomCommand(int i10, SessionCommand sessionCommand, Bundle bundle) {
            c2.N(this, i10, sessionCommand, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void sendCustomCommandProgressUpdate(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
            c2.O(this, i10, sessionCommand, bundle, bundle2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void setCustomLayout(int i10, List list) {
            c2.P(this, i10, list);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void setMediaButtonPreferences(int i10, List list) {
            c2.Q(this, i10, list);
        }
    }

    public final class ControllerLegacyCbForBroadcast implements MediaSession.ControllerCb {
        private Uri lastMediaUri;
        private MediaMetadata lastMediaMetadata = MediaMetadata.EMPTY;
        private String lastMediaId = "";
        private long lastDurationMs = C.TIME_UNSET;

        public ControllerLegacyCbForBroadcast() {
        }

        private void handleBitmapFuturesAllCompletedAndSetQueue(List<com.google.common.util.concurrent.d1<Bitmap>> list, List<MediaItem> list2) {
            Bitmap bitmap;
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < list.size(); i10++) {
                com.google.common.util.concurrent.d1<Bitmap> d1Var = list.get(i10);
                if (d1Var != null) {
                    try {
                        bitmap = (Bitmap) xc.d.j0(d1Var);
                    } catch (CancellationException | ExecutionException e5) {
                        Log.d(MediaSessionLegacyStub.TAG, "Failed to get bitmap", e5);
                        bitmap = null;
                    }
                } else {
                    bitmap = null;
                }
                arrayList.add(LegacyConversions.convertToQueueItem(list2.get(i10), i10, bitmap));
            }
            MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$updateQueue$0(AtomicInteger atomicInteger, List list, List list2) {
            if (atomicInteger.incrementAndGet() == list.size()) {
                handleBitmapFuturesAllCompletedAndSetQueue(list2, list);
            }
        }

        private void updateMetadataIfChanged() {
            final MediaMetadata mediaMetadata;
            Uri uri;
            ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast;
            Bitmap bitmap;
            Uri uri2;
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaItem currentMediaItemWithCommandCheck = playerWrapper.getCurrentMediaItemWithCommandCheck();
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper.getMediaMetadataWithCommandCheck();
            final long durationWithCommandCheck = playerWrapper.isCurrentMediaItemLiveWithCommandCheck() ? C.TIME_UNSET : playerWrapper.getDurationWithCommandCheck();
            final String str = currentMediaItemWithCommandCheck != null ? currentMediaItemWithCommandCheck.mediaId : "";
            Uri uri3 = (currentMediaItemWithCommandCheck == null || (uri2 = currentMediaItemWithCommandCheck.requestMetadata.mediaUri) == null) ? null : uri2;
            if (Objects.equals(this.lastMediaMetadata, mediaMetadataWithCommandCheck) && Objects.equals(this.lastMediaId, str) && Objects.equals(this.lastMediaUri, uri3) && this.lastDurationMs == durationWithCommandCheck) {
                return;
            }
            this.lastMediaId = str;
            this.lastMediaUri = uri3;
            this.lastMediaMetadata = mediaMetadataWithCommandCheck;
            this.lastDurationMs = durationWithCommandCheck;
            com.google.common.util.concurrent.d1<Bitmap> d1VarLoadBitmapFromMetadata = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().loadBitmapFromMetadata(mediaMetadataWithCommandCheck);
            if (d1VarLoadBitmapFromMetadata != null) {
                MediaSessionLegacyStub.this.pendingBitmapLoadCallback = null;
                if (d1VarLoadBitmapFromMetadata.isDone()) {
                    try {
                        Uri uri4 = uri3;
                        mediaMetadata = mediaMetadataWithCommandCheck;
                        uri = uri4;
                        controllerLegacyCbForBroadcast = this;
                        bitmap = (Bitmap) xc.d.j0(d1VarLoadBitmapFromMetadata);
                    } catch (CancellationException | ExecutionException e5) {
                        Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(e5));
                        Uri uri5 = uri3;
                        mediaMetadata = mediaMetadataWithCommandCheck;
                        uri = uri5;
                        controllerLegacyCbForBroadcast = this;
                        bitmap = null;
                    }
                } else {
                    MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
                    final Uri uri6 = uri3;
                    mediaMetadata = mediaMetadataWithCommandCheck;
                    com.google.common.util.concurrent.v0<Bitmap> v0Var = new com.google.common.util.concurrent.v0<Bitmap>() { // from class: androidx.media3.session.MediaSessionLegacyStub.ControllerLegacyCbForBroadcast.1
                        @Override // com.google.common.util.concurrent.v0
                        public void onFailure(Throwable th) {
                            if (this != MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                return;
                            }
                            Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(th));
                        }

                        @Override // com.google.common.util.concurrent.v0
                        public void onSuccess(Bitmap bitmap2) {
                            if (this != MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                return;
                            }
                            MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadata, str, uri6, durationWithCommandCheck, bitmap2));
                            MediaSessionLegacyStub.this.sessionImpl.onNotificationRefreshRequired();
                        }
                    };
                    controllerLegacyCbForBroadcast = this;
                    str = str;
                    uri = uri6;
                    durationWithCommandCheck = durationWithCommandCheck;
                    mediaSessionLegacyStub.pendingBitmapLoadCallback = v0Var;
                    com.google.common.util.concurrent.v0 v0Var2 = MediaSessionLegacyStub.this.pendingBitmapLoadCallback;
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    xc.d.G(d1VarLoadBitmapFromMetadata, v0Var2, new androidx.media3.common.util.d(applicationHandler));
                    bitmap = null;
                }
            } else {
                Uri uri52 = uri3;
                mediaMetadata = mediaMetadataWithCommandCheck;
                uri = uri52;
                controllerLegacyCbForBroadcast = this;
                bitmap = null;
            }
            MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadata, str, uri, durationWithCommandCheck, bitmap));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateQueue(Timeline timeline) {
            if (!MediaSessionLegacyStub.this.isQueueEnabled() || timeline.isEmpty()) {
                MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, null);
                return;
            }
            List<MediaItem> listConvertToMediaItemList = LegacyConversions.convertToMediaItemList(timeline);
            ArrayList arrayList = new ArrayList();
            c cVar = new c(this, new AtomicInteger(0), listConvertToMediaItemList, arrayList, 6);
            for (int i10 = 0; i10 < listConvertToMediaItemList.size(); i10++) {
                MediaMetadata mediaMetadata = listConvertToMediaItemList.get(i10).mediaMetadata;
                if (mediaMetadata.artworkData == null) {
                    arrayList.add(null);
                    cVar.run();
                } else {
                    com.google.common.util.concurrent.d1<Bitmap> d1VarDecodeBitmap = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                    arrayList.add(d1VarDecodeBitmap);
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    d1VarDecodeBitmap.addListener(cVar, new androidx.media3.common.util.d(applicationHandler));
                }
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAudioAttributesChanged(int i10, AudioAttributes audioAttributes) {
            if (MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().getDeviceInfo().playbackType == 0) {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToLocal(audioAttributes.getStreamType());
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onAudioSessionIdChanged(int i10, int i11) {
            c2.b(this, i10, i11);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onAvailableCommandsChangedFromPlayer(int i10, Player.Commands commands) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper);
            MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onAvailableCommandsChangedFromSession(int i10, SessionCommands sessionCommands, Player.Commands commands) {
            c2.d(this, i10, sessionCommands, commands);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) {
            c2.e(this, i10, str, i11, libraryParams);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceInfoChanged(int i10, DeviceInfo deviceInfo) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.volumeProviderCompat = MediaSessionLegacyStub.createVolumeProviderCompat(playerWrapper);
            if (MediaSessionLegacyStub.this.volumeProviderCompat != null) {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToRemote(MediaSessionLegacyStub.this.volumeProviderCompat);
            } else {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToLocal(playerWrapper.getAudioAttributesWithCommandCheck().getStreamType());
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDeviceVolumeChanged(int i10, int i11, boolean z) {
            if (MediaSessionLegacyStub.this.volumeProviderCompat != null) {
                VolumeProviderCompat volumeProviderCompat = MediaSessionLegacyStub.this.volumeProviderCompat;
                if (z) {
                    i11 = 0;
                }
                volumeProviderCompat.setCurrentVolume(i11);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onDisconnected(int i10) {
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onError(int i10, SessionError sessionError) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.legacyError = new LegacyError(false, LegacyConversions.convertToLegacyErrorCode(sessionError.code), sessionError.message, sessionError.extras, null);
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(MediaSessionLegacyStub.this.createPlaybackStateCompat(playerWrapper));
            MediaSessionLegacyStub.this.legacyError = null;
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(MediaSessionLegacyStub.this.createPlaybackStateCompat(playerWrapper));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onIsLoadingChanged(int i10, boolean z) {
            c2.j(this, i10, z);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onIsPlayingChanged(int i10, boolean z) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onLibraryResult(int i10, LibraryResult libraryResult) {
            c2.l(this, i10, libraryResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaItemTransition(int i10, MediaItem mediaItem, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateMetadataIfChanged();
            if (mediaItem == null) {
                MediaSessionLegacyStub.this.sessionCompat.setRatingType(0);
            } else {
                MediaSessionLegacyStub.this.sessionCompat.setRatingType(LegacyConversions.getRatingCompatStyle(mediaItem.mediaMetadata.userRating));
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onMediaMetadataChanged(int i10, MediaMetadata mediaMetadata) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateMetadataIfChanged();
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPeriodicSessionPositionInfoChanged(int i10, SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayWhenReadyChanged(int i10, boolean z, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackParametersChanged(int i10, PlaybackParameters playbackParameters) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackStateChanged(int i10, int i11, PlaybackException playbackException) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaybackSuppressionReasonChanged(int i10, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerChanged(int i10, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
            Timeline currentTimelineWithCommandCheck = playerWrapper2.getCurrentTimelineWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getCurrentTimelineWithCommandCheck(), currentTimelineWithCommandCheck)) {
                onTimelineChanged(i10, currentTimelineWithCommandCheck, 0);
            }
            MediaMetadata playlistMetadataWithCommandCheck = playerWrapper2.getPlaylistMetadataWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getPlaylistMetadataWithCommandCheck(), playlistMetadataWithCommandCheck)) {
                onPlaylistMetadataChanged(i10, playlistMetadataWithCommandCheck);
            }
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper2.getMediaMetadataWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getMediaMetadataWithCommandCheck(), mediaMetadataWithCommandCheck)) {
                onMediaMetadataChanged(i10, mediaMetadataWithCommandCheck);
            }
            if (playerWrapper == null || playerWrapper.getShuffleModeEnabled() != playerWrapper2.getShuffleModeEnabled()) {
                onShuffleModeEnabledChanged(i10, playerWrapper2.getShuffleModeEnabled());
            }
            if (playerWrapper == null || playerWrapper.getRepeatMode() != playerWrapper2.getRepeatMode()) {
                onRepeatModeChanged(i10, playerWrapper2.getRepeatMode());
            }
            onDeviceInfoChanged(i10, playerWrapper2.getDeviceInfo());
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper2);
            MediaItem currentMediaItemWithCommandCheck = playerWrapper2.getCurrentMediaItemWithCommandCheck();
            if (playerWrapper == null || !Objects.equals(playerWrapper.getCurrentMediaItemWithCommandCheck(), currentMediaItemWithCommandCheck)) {
                onMediaItemTransition(i10, currentMediaItemWithCommandCheck, 3);
            } else {
                if (skipLegacySessionPlaybackStateUpdates()) {
                    return;
                }
                MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper2);
            }
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlayerError(int i10, PlaybackException playbackException) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onPlayerInfoChanged(int i10, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z5) {
            c2.v(this, i10, playerInfo, commands, z, z5);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPlaylistMetadataChanged(int i10, MediaMetadata mediaMetadata) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            CharSequence queueTitle = MediaSessionLegacyStub.this.sessionCompat.getController().getQueueTitle();
            CharSequence charSequence = mediaMetadata.title;
            if (TextUtils.equals(queueTitle, charSequence)) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.setQueueTitle(mediaSessionLegacyStub.sessionCompat, charSequence);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onPositionDiscontinuity(int i10, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onRenderedFirstFrame(int i10) {
            c2.y(this, i10);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onRepeatModeChanged(int i10, int i11) throws RemoteException {
            MediaSessionLegacyStub.this.sessionCompat.setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i11));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) {
            c2.A(this, i10, str, i11, libraryParams);
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
        public void onSessionActivityChanged(int i10, PendingIntent pendingIntent) {
            MediaSessionLegacyStub.this.sessionCompat.setSessionActivity(pendingIntent);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onSessionExtrasChanged(int i10, Bundle bundle) {
            ac.b.j(!bundle.containsKey(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT));
            ac.b.j(!bundle.containsKey("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID"));
            MediaSessionLegacyStub.this.legacyExtras = new Bundle(bundle);
            if (!MediaSessionLegacyStub.this.mediaButtonPreferences.isEmpty()) {
                MediaSessionLegacyStub.this.updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
            }
            MediaSessionLegacyStub.this.sessionCompat.setExtras(MediaSessionLegacyStub.this.legacyExtras);
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSessionResult(int i10, SessionResult sessionResult) {
            c2.F(this, i10, sessionResult);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onShuffleModeEnabledChanged(int i10, boolean z) {
            MediaSessionLegacyStub.this.sessionCompat.setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11, int i12) {
            c2.H(this, i10, i11, i12);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void onTimelineChanged(int i10, Timeline timeline, int i11) {
            if (skipLegacySessionPlaybackStateUpdates()) {
                return;
            }
            updateQueue(timeline);
            updateMetadataIfChanged();
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
        public void sendCustomCommand(int i10, SessionCommand sessionCommand, Bundle bundle) {
            if (bundle.isEmpty()) {
                bundle = sessionCommand.customExtras;
            } else if (!sessionCommand.customExtras.isEmpty()) {
                Bundle bundle2 = new Bundle(sessionCommand.customExtras);
                bundle2.putAll(bundle);
                bundle = bundle2;
            }
            MediaSessionLegacyStub.this.sessionCompat.sendSessionEvent(sessionCommand.customAction, bundle);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public final /* synthetic */ void sendCustomCommandProgressUpdate(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) {
            c2.O(this, i10, sessionCommand, bundle, bundle2);
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setCustomLayout(int i10, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        @Override // androidx.media3.session.MediaSession.ControllerCb
        public void setMediaButtonPreferences(int i10, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public boolean skipLegacySessionPlaybackStateUpdates() {
            return MediaSessionLegacyStub.this.customPlaybackException != null;
        }
    }

    public static final class LegacyError {
        public final int code;
        public final Bundle extras;
        public final boolean isFatal;
        public final String message;

        public /* synthetic */ LegacyError(boolean z, int i10, String str, Bundle bundle, AnonymousClass1 anonymousClass1) {
            this(z, i10, str, bundle);
        }

        private LegacyError(boolean z, int i10, String str, Bundle bundle) {
            this.isFatal = z;
            this.code = i10;
            this.message = str;
            this.extras = bundle == null ? Bundle.EMPTY : bundle;
        }
    }

    public final class MediaButtonReceiver extends BroadcastReceiver {
        private MediaButtonReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            KeyEvent keyEvent;
            if (Objects.equals(intent.getAction(), "android.intent.action.MEDIA_BUTTON") && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
                MediaSessionLegacyStub.this.sessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
            }
        }

        public /* synthetic */ MediaButtonReceiver(MediaSessionLegacyStub mediaSessionLegacyStub, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public interface SessionTask {
        void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    static {
        PENDING_INTENT_FLAG_MUTABLE = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
    }

    public MediaSessionLegacyStub(MediaSessionImpl mediaSessionImpl, Uri uri, Handler handler, Bundle bundle, boolean z, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, SessionCommands sessionCommands, Player.Commands commands, Bundle bundle2) {
        ComponentName serviceComponentByAction;
        boolean z5;
        PendingIntent foregroundService;
        this.sessionImpl = mediaSessionImpl;
        this.playIfSuppressed = z;
        this.customLayout = h1Var;
        this.mediaButtonPreferences = h1Var2;
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
        this.legacyExtras = new Bundle(bundle2);
        Context context = mediaSessionImpl.getContext();
        this.sessionManager = MediaSessionManager.getSessionManager(context);
        this.controllerLegacyCbForBroadcast = new ControllerLegacyCbForBroadcast();
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
        this.connectedControllersManager = connectedControllersManager;
        this.connectionTimeoutMs = 300000L;
        this.connectionTimeoutHandler = new ConnectionTimeoutHandler(mediaSessionImpl.getApplicationHandler().getLooper(), connectedControllersManager);
        if (!h1Var2.isEmpty()) {
            updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        }
        ComponentName componentNameQueryPackageManagerForMediaButtonReceiver = queryPackageManagerForMediaButtonReceiver(context);
        this.broadcastReceiverComponentName = componentNameQueryPackageManagerForMediaButtonReceiver;
        if (componentNameQueryPackageManagerForMediaButtonReceiver == null || Build.VERSION.SDK_INT < 31) {
            serviceComponentByAction = getServiceComponentByAction(context, MediaLibraryService.SERVICE_INTERFACE);
            serviceComponentByAction = serviceComponentByAction == null ? getServiceComponentByAction(context, MediaSessionService.SERVICE_INTERFACE) : serviceComponentByAction;
            z5 = (serviceComponentByAction == null || serviceComponentByAction.equals(componentNameQueryPackageManagerForMediaButtonReceiver)) ? false : true;
        } else {
            z5 = false;
            serviceComponentByAction = componentNameQueryPackageManagerForMediaButtonReceiver;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", uri);
        AnonymousClass1 anonymousClass1 = null;
        if (serviceComponentByAction == null) {
            MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver(this, anonymousClass1);
            this.runtimeBroadcastReceiver = mediaButtonReceiver;
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            intentFilter.addDataScheme((String) Util.castNonNull(uri.getScheme()));
            Util.registerReceiverNotExported(context, mediaButtonReceiver, intentFilter);
            intent.setPackage(context.getPackageName());
            foregroundService = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            serviceComponentByAction = new ComponentName(context, context.getClass());
        } else {
            intent.setComponent(serviceComponentByAction);
            foregroundService = z5 ? Build.VERSION.SDK_INT >= 26 ? PendingIntent.getForegroundService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE) : PendingIntent.getService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE) : PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            this.runtimeBroadcastReceiver = null;
        }
        String strJoin = TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, mediaSessionImpl.getId()});
        int i10 = Build.VERSION.SDK_INT;
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, strJoin, i10 >= 31 ? null : serviceComponentByAction, i10 < 31 ? foregroundService : null, bundle);
        this.sessionCompat = mediaSessionCompat;
        if (i10 >= 31 && componentNameQueryPackageManagerForMediaButtonReceiver != null) {
            Api31.setMediaButtonBroadcastReceiver(mediaSessionCompat, componentNameQueryPackageManagerForMediaButtonReceiver);
        }
        PendingIntent sessionActivity = mediaSessionImpl.getSessionActivity();
        if (sessionActivity != null) {
            mediaSessionCompat.setSessionActivity(sessionActivity);
        }
        mediaSessionCompat.setCallback(this, handler);
    }

    private static long convertCommandToPlaybackStateActions(int i10, boolean z) {
        if (i10 == 1) {
            return z ? 516L : 514L;
        }
        if (i10 == 2) {
            return 16384L;
        }
        if (i10 == 3) {
            return 1L;
        }
        if (i10 == 31) {
            return 240640L;
        }
        switch (i10) {
            case 5:
                return 256L;
            case 6:
            case 7:
                return 16L;
            case 8:
            case 9:
                return 32L;
            case 10:
                return 4096L;
            case 11:
                return 8L;
            case 12:
                return 64L;
            case 13:
                return 4194304L;
            case 14:
                return 2621440L;
            case 15:
                return 262144L;
            default:
                return 0L;
        }
    }

    private static MediaItem createMediaItemForMediaRequest(String str, Uri uri, String str2, Bundle bundle) {
        MediaItem.Builder builder = new MediaItem.Builder();
        if (str == null) {
            str = "";
        }
        return builder.setMediaId(str).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(uri).setSearchQuery(str2).setExtras(bundle).build()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlaybackStateCompat createPlaybackStateCompat(PlayerWrapper playerWrapper) {
        PlaybackException playbackException;
        long bufferedPosition;
        LegacyError legacyError = this.legacyError;
        PlaybackException playerError = this.customPlaybackException;
        if (playerError == null && legacyError != null && legacyError.isFatal) {
            Bundle bundle = new Bundle(legacyError.extras);
            bundle.putAll(this.legacyExtras);
            PlaybackStateCompat.Builder extras = new PlaybackStateCompat.Builder().setState(7, -1L, 0.0f, SystemClock.elapsedRealtime()).setActions(0L).setBufferedPosition(0L).setExtras(bundle);
            int i10 = legacyError.code;
            String str = legacyError.message;
            str.getClass();
            return extras.setErrorMessage(i10, str).setExtras(legacyError.extras).build();
        }
        if (playerError == null) {
            playerError = playerWrapper.getPlayerError();
        }
        boolean z = playerWrapper.isCommandAvailable(16) && !playerWrapper.isCurrentMediaItemLive();
        boolean z5 = playerError != null || Util.shouldShowPlayButton(playerWrapper, this.playIfSuppressed);
        int iConvertToPlaybackStateCompatState = playerError != null ? 7 : LegacyConversions.convertToPlaybackStateCompatState(playerWrapper, z5);
        Player.Commands availableCommands = playerWrapper.getAvailableCommands();
        Player.Commands commands = this.playerCommandsForErrorState;
        Player.Commands commandsIntersect = commands != null ? MediaUtils.intersect(commands, availableCommands) : MediaUtils.intersect(this.availablePlayerCommands, availableCommands);
        long jConvertCommandToPlaybackStateActions = 128;
        for (int i11 = 0; i11 < commandsIntersect.size(); i11++) {
            jConvertCommandToPlaybackStateActions |= convertCommandToPlaybackStateActions(commandsIntersect.get(i11), z5);
        }
        if (!this.mediaButtonPreferences.isEmpty() && !this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS")) {
            jConvertCommandToPlaybackStateActions &= -17;
        }
        if (!this.mediaButtonPreferences.isEmpty() && !this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT")) {
            jConvertCommandToPlaybackStateActions &= -33;
        }
        if (!z) {
            jConvertCommandToPlaybackStateActions &= -257;
        }
        long jConvertToQueueItemId = playerWrapper.isCommandAvailable(17) ? LegacyConversions.convertToQueueItemId(playerWrapper.getCurrentMediaItemIndex()) : -1L;
        float f10 = playerWrapper.getPlaybackParameters().speed;
        float f11 = (playerWrapper.isPlaying() && z) ? f10 : 0.0f;
        Bundle bundle2 = playerError != null ? new Bundle(playerError.extras) : new Bundle();
        if (playerError == null && legacyError != null) {
            bundle2.putAll(legacyError.extras);
        }
        bundle2.putAll(this.legacyExtras);
        bundle2.putFloat(MediaConstants.EXTRAS_KEY_PLAYBACK_SPEED_COMPAT, f10);
        MediaItem currentMediaItemWithCommandCheck = playerWrapper.getCurrentMediaItemWithCommandCheck();
        if (currentMediaItemWithCommandCheck != null && !"".equals(currentMediaItemWithCommandCheck.mediaId)) {
            bundle2.putString("androidx.media.PlaybackStateCompat.Extras.KEY_MEDIA_ID", currentMediaItemWithCommandCheck.mediaId);
        }
        long currentPosition = z ? playerWrapper.getCurrentPosition() : -1L;
        if (z) {
            playbackException = playerError;
            bufferedPosition = playerWrapper.getBufferedPosition();
        } else {
            playbackException = playerError;
            bufferedPosition = -1;
        }
        PlaybackException playbackException2 = playbackException;
        PlaybackStateCompat.Builder extras2 = new PlaybackStateCompat.Builder().setState(iConvertToPlaybackStateCompatState, currentPosition, f11, SystemClock.elapsedRealtime()).setActions(jConvertCommandToPlaybackStateActions).setActiveQueueItemId(jConvertToQueueItemId).setBufferedPosition(bufferedPosition).setExtras(bundle2);
        for (int i12 = 0; i12 < this.customLayout.size(); i12++) {
            CommandButton commandButton = (CommandButton) this.customLayout.get(i12);
            SessionCommand sessionCommand = commandButton.sessionCommand;
            if (sessionCommand != null && commandButton.isEnabled && sessionCommand.commandCode == 0 && (CommandButton.isButtonCommandAvailable(commandButton, this.availableSessionCommands, commandsIntersect) || CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction))) {
                boolean z10 = commandButton.icon != 0;
                boolean z11 = commandButton.iconUri != null;
                Bundle bundle3 = (z10 || z11 || !commandButton.extras.isEmpty()) ? new Bundle(sessionCommand.customExtras) : sessionCommand.customExtras;
                if (!commandButton.extras.isEmpty()) {
                    bundle3.putAll(commandButton.extras);
                }
                if (z10) {
                    bundle3.putInt(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_COMPAT, commandButton.icon);
                }
                if (z11) {
                    Uri uri = commandButton.iconUri;
                    uri.getClass();
                    bundle3.putString(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_URI_COMPAT, uri.toString());
                }
                extras2.addCustomAction(new PlaybackStateCompat.CustomAction.Builder(sessionCommand.customAction, commandButton.displayName, commandButton.iconResId).setExtras(bundle3).build());
            }
        }
        if (playbackException2 != null) {
            extras2.setErrorMessage(LegacyConversions.convertToLegacyErrorCode(playbackException2), playbackException2.getMessage());
        } else if (legacyError != null) {
            extras2.setErrorMessage(legacyError.code, legacyError.message);
        }
        return extras2.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static VolumeProviderCompat createVolumeProviderCompat(PlayerWrapper playerWrapper) {
        if (playerWrapper.getDeviceInfo().playbackType == 0) {
            return null;
        }
        Player.Commands availableCommands = playerWrapper.getAvailableCommands();
        int i10 = availableCommands.containsAny(26, 34) ? availableCommands.containsAny(25, 33) ? 2 : 1 : 0;
        Handler handler = new Handler(playerWrapper.getApplicationLooper());
        int deviceVolumeWithCommandCheck = playerWrapper.getDeviceVolumeWithCommandCheck();
        DeviceInfo deviceInfo = playerWrapper.getDeviceInfo();
        return new AnonymousClass3(i10, deviceInfo.maxVolume, deviceVolumeWithCommandCheck, deviceInfo.routingControllerId, handler, playerWrapper, 1);
    }

    private void dispatchCustomCommandAsPredefinedCommand(SessionCommand sessionCommand) {
        try {
            CommandButton commandButtonConvertFromPredefinedCustomCommand = CommandButton.convertFromPredefinedCustomCommand(sessionCommand);
            if (!commandButtonConvertFromPredefinedCustomCommand.canExecuteAction()) {
                Log.w(TAG, "Can't execute predefined custom command: " + sessionCommand.customAction);
                return;
            }
            SessionCommand sessionCommand2 = commandButtonConvertFromPredefinedCustomCommand.sessionCommand;
            if (sessionCommand2 != null) {
                ac.b.s(sessionCommand2.commandCode == 40010);
                Object obj = commandButtonConvertFromPredefinedCustomCommand.parameter;
                obj.getClass();
                dispatchSessionTaskWithSetRatingSessionCommand((Rating) obj);
                return;
            }
            if (commandButtonConvertFromPredefinedCustomCommand.isPlayRequestPlayerAction(this.sessionImpl.getPlayerWrapper())) {
                dispatchSessionTaskWithPlayRequest();
                return;
            }
            int i10 = commandButtonConvertFromPredefinedCustomCommand.playerCommand;
            if (i10 != 31) {
                dispatchSessionTaskWithPlayerCommand(i10, new k(this, commandButtonConvertFromPredefinedCustomCommand, 16), this.sessionCompat.getCurrentControllerInfo(), true);
                return;
            }
            Object obj2 = commandButtonConvertFromPredefinedCustomCommand.parameter;
            obj2.getClass();
            handleMediaRequest((MediaItem) obj2, false, false);
        } catch (RuntimeException e5) {
            Log.w(TAG, "Failed to convert predefined custom command: " + sessionCommand.customAction, e5);
        }
    }

    private void dispatchSessionTaskWithPlayRequest() {
        dispatchSessionTaskWithPlayerCommand(1, new l2(this, 5), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    private void dispatchSessionTaskWithPlayerCommand(final int i10, final SessionTask sessionTask, final MediaSessionManager.RemoteUserInfo remoteUserInfo, final boolean z) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (remoteUserInfo != null) {
            Util.postOrRun(this.sessionImpl.getApplicationHandler(), new Runnable() { // from class: androidx.media3.session.p2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f4106i.lambda$dispatchSessionTaskWithPlayerCommand$20(i10, remoteUserInfo, sessionTask, z);
                }
            });
            return;
        }
        Log.d(TAG, "RemoteUserInfo is null, ignoring command=" + i10);
    }

    private void dispatchSessionTaskWithSessionCommand(SessionCommand sessionCommand, SessionTask sessionTask) {
        dispatchSessionTaskWithSessionCommandInternal(sessionCommand, 0, sessionTask, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommandInternal(SessionCommand sessionCommand, int i10, SessionTask sessionTask, MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo != null) {
            Util.postOrRun(this.sessionImpl.getApplicationHandler(), new g1(this, sessionCommand, i10, remoteUserInfo, sessionTask));
            return;
        }
        StringBuilder sb2 = new StringBuilder("RemoteUserInfo is null, ignoring command=");
        Object objValueOf = sessionCommand;
        if (sessionCommand == null) {
            objValueOf = Integer.valueOf(i10);
        }
        sb2.append(objValueOf);
        Log.d(TAG, sb2.toString());
    }

    private void dispatchSessionTaskWithSetRatingSessionCommand(Rating rating) {
        dispatchSessionTaskWithSessionCommandInternal(null, SessionCommand.COMMAND_CODE_SESSION_SET_RATING, new k(this, rating, 14), this.sessionCompat.getCurrentControllerInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            return null;
        }
        ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name);
    }

    private void handleMediaRequest(MediaItem mediaItem, boolean z) {
        handleMediaRequest(mediaItem, true, z);
    }

    private void handleOnAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        if (mediaDescriptionCompat != null) {
            if (i10 == -1 || i10 >= 0) {
                dispatchSessionTaskWithPlayerCommand(20, new x0(this, mediaDescriptionCompat, i10), this.sessionCompat.getCurrentControllerInfo(), false);
            }
        }
    }

    private static <T> void ignoreFuture(Future<T> future) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isQueueEnabled() {
        return this.availablePlayerCommands.contains(17) && this.sessionImpl.getPlayerWrapper().getAvailableCommands().contains(17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchCustomCommandAsPredefinedCommand$23(CommandButton commandButton, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        commandButton.executePlayerAction(this.sessionImpl.getPlayerWrapper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchSessionTaskWithPlayRequest$18(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.handleMediaControllerPlayRequest(controllerInfo, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchSessionTaskWithPlayerCommand$19(SessionTask sessionTask, MediaSession.ControllerInfo controllerInfo) {
        try {
            sessionTask.run(controllerInfo);
        } catch (RemoteException e5) {
            Log.w(TAG, "Exception in " + controllerInfo, e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchSessionTaskWithPlayerCommand$20(int i10, MediaSessionManager.RemoteUserInfo remoteUserInfo, SessionTask sessionTask, boolean z) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (!this.sessionCompat.isActive()) {
            StringBuilder sbS = a0.c.s(i10, "Ignore incoming player command before initialization. command=", ", pid=");
            sbS.append(remoteUserInfo.getPid());
            Log.w(TAG, sbS.toString());
            return;
        }
        MediaSession.ControllerInfo controllerInfoTryGetController = tryGetController(remoteUserInfo);
        if (controllerInfoTryGetController == null) {
            return;
        }
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfoTryGetController, i10)) {
            if (i10 != 1 || this.sessionImpl.getPlayerWrapper().getPlayWhenReady()) {
                return;
            }
            Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
            return;
        }
        if (this.sessionImpl.onPlayerCommandRequestOnHandler(controllerInfoTryGetController, i10) != 0) {
            return;
        }
        this.sessionImpl.callWithControllerForCurrentRequestSet(controllerInfoTryGetController, new g(sessionTask, controllerInfoTryGetController, 10)).run();
        if (z) {
            this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfoTryGetController, new Player.Commands.Builder().add(i10).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchSessionTaskWithSessionCommandInternal$22(SessionCommand sessionCommand, int i10, MediaSessionManager.RemoteUserInfo remoteUserInfo, SessionTask sessionTask) {
        if (this.sessionImpl.isReleased()) {
            return;
        }
        if (!this.sessionCompat.isActive()) {
            StringBuilder sb2 = new StringBuilder("Ignore incoming session command before initialization. command=");
            sb2.append(sessionCommand == null ? Integer.valueOf(i10) : sessionCommand.customAction);
            sb2.append(", pid=");
            sb2.append(remoteUserInfo.getPid());
            Log.w(TAG, sb2.toString());
            return;
        }
        MediaSession.ControllerInfo controllerInfoTryGetController = tryGetController(remoteUserInfo);
        if (controllerInfoTryGetController == null) {
            return;
        }
        if (sessionCommand != null) {
            if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfoTryGetController, sessionCommand)) {
                return;
            }
        } else if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfoTryGetController, i10)) {
            return;
        }
        try {
            sessionTask.run(controllerInfoTryGetController);
        } catch (RemoteException e5) {
            Log.w(TAG, "Exception in " + controllerInfoTryGetController, e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchSessionTaskWithSetRatingSessionCommand$21(Rating rating, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaItem currentMediaItemWithCommandCheck = this.sessionImpl.getPlayerWrapper().getCurrentMediaItemWithCommandCheck();
        if (currentMediaItemWithCommandCheck == null) {
            return;
        }
        ignoreFuture(this.sessionImpl.onSetRatingOnHandler(controllerInfo, currentMediaItemWithCommandCheck.mediaId, rating));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMediaPlayPauseOnHandler$2(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePlayPauseButtonAction(this.sessionImpl.getPlayerWrapper(), this.sessionImpl.shouldPlayIfSuppressed());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMediaRequest$26(MediaItem mediaItem, boolean z, boolean z5, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        xc.d.G(this.sessionImpl.onSetMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(mediaItem), -1, C.TIME_UNSET), new AnonymousClass1(controllerInfo, z, z5), com.google.common.util.concurrent.s0.f14233i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleOnAddQueueItem$27(MediaDescriptionCompat mediaDescriptionCompat, int i10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
            Log.w(TAG, "onAddQueueItem(): Media ID shouldn't be empty");
        } else {
            xc.d.G(this.sessionImpl.onAddMediaItemsOnHandler(controllerInfo, com.google.common.collect.h1.s(LegacyConversions.convertToMediaItem(mediaDescriptionCompat))), new AnonymousClass2(controllerInfo, i10), com.google.common.util.concurrent.s0.f14233i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCommand$0(SessionCommand sessionCommand, Bundle bundle, ResultReceiver resultReceiver, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnCustomCommandOnHandler = mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, null, sessionCommand, bundle);
        if (resultReceiver != null) {
            sendCustomCommandResultWhenReady(resultReceiver, d1VarOnCustomCommandOnHandler);
        } else {
            ignoreFuture(d1VarOnCustomCommandOnHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCustomAction$1(SessionCommand sessionCommand, Bundle bundle, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ignoreFuture(this.sessionImpl.onCustomCommandOnHandler(controllerInfo, null, sessionCommand, bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onFastForward$13(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekForward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPause$4(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePauseButtonAction(this.sessionImpl.getPlayerWrapper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepare$3(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().prepare();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRemoveQueueItem$17(MediaDescriptionCompat mediaDescriptionCompat, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        String mediaId = mediaDescriptionCompat.getMediaId();
        if (TextUtils.isEmpty(mediaId)) {
            Log.w(TAG, "onRemoveQueueItem(): Media ID shouldn't be null");
            return;
        }
        PlayerWrapper playerWrapper = this.sessionImpl.getPlayerWrapper();
        if (!playerWrapper.isCommandAvailable(17)) {
            Log.w(TAG, "Can't remove item by ID without COMMAND_GET_TIMELINE being available");
            return;
        }
        Timeline currentTimeline = playerWrapper.getCurrentTimeline();
        Timeline.Window window = new Timeline.Window();
        for (int i10 = 0; i10 < currentTimeline.getWindowCount(); i10++) {
            if (TextUtils.equals(currentTimeline.getWindow(i10, window).mediaItem.mediaId, mediaId)) {
                playerWrapper.removeMediaItem(i10);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onRewind$14(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSeekTo$6(long j10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekTo(j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetPlaybackSpeed$11(float f10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setPlaybackSpeed(f10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetRepeatMode$15(int i10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setRepeatMode(LegacyConversions.convertToRepeatMode(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSetShuffleMode$16(int i10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setShuffleModeEnabled(LegacyConversions.convertToShuffleModeEnabled(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSkipToNext$7(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSkipToNext$8(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNextMediaItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSkipToPrevious$10(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPreviousMediaItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSkipToPrevious$9(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPrevious();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSkipToQueueItem$12(long j10, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToDefaultPosition((int) j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStop$5(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$sendCustomCommandResultWhenReady$28(com.google.common.util.concurrent.d1 d1Var, ResultReceiver resultReceiver) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) d1Var.get();
            ac.b.p(sessionResult, "SessionResult must not be null");
        } catch (InterruptedException e5) {
            e = e5;
            Log.w(TAG, "Custom command failed", e);
            sessionResult = new SessionResult(-1);
        } catch (CancellationException e6) {
            Log.w(TAG, "Custom command cancelled", e6);
            sessionResult = new SessionResult(1);
        } catch (ExecutionException e10) {
            e = e10;
            Log.w(TAG, "Custom command failed", e);
            sessionResult = new SessionResult(-1);
        }
        resultReceiver.send(sessionResult.resultCode, sessionResult.extras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLegacySessionPlaybackState$24(PlayerWrapper playerWrapper) {
        this.sessionCompat.setPlaybackState(createPlaybackStateCompat(playerWrapper));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLegacySessionPlaybackStateAndQueue$25(PlayerWrapper playerWrapper) {
        this.sessionCompat.setPlaybackState(createPlaybackStateCompat(playerWrapper));
        this.controllerLegacyCbForBroadcast.updateQueue(playerWrapper.getAvailableCommands().contains(17) ? playerWrapper.getCurrentTimeline() : Timeline.EMPTY);
    }

    private static ComponentName queryPackageManagerForMediaButtonReceiver(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers.size() == 1) {
            ActivityInfo activityInfo = listQueryBroadcastReceivers.get(0).activityInfo;
            return new ComponentName(activityInfo.packageName, activityInfo.name);
        }
        if (listQueryBroadcastReceivers.isEmpty()) {
            return null;
        }
        throw new IllegalStateException("Expected 1 broadcast receiver that handles android.intent.action.MEDIA_BUTTON, found " + listQueryBroadcastReceivers.size());
    }

    private static void sendCustomCommandResultWhenReady(ResultReceiver resultReceiver, com.google.common.util.concurrent.d1<SessionResult> d1Var) {
        d1Var.addListener(new g(d1Var, resultReceiver, 9), com.google.common.util.concurrent.s0.f14233i);
    }

    private static void setMediaButtonReceiver(MediaSessionCompat mediaSessionCompat, PendingIntent pendingIntent) {
        mediaSessionCompat.setMediaButtonReceiver(pendingIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setMetadata(MediaSessionCompat mediaSessionCompat, MediaMetadataCompat mediaMetadataCompat) {
        mediaSessionCompat.setMetadata(mediaMetadataCompat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setQueue(MediaSessionCompat mediaSessionCompat, List<MediaSessionCompat.QueueItem> list) {
        mediaSessionCompat.setQueue(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueueTitle(MediaSessionCompat mediaSessionCompat, CharSequence charSequence) {
        if (!isQueueEnabled()) {
            charSequence = null;
        }
        mediaSessionCompat.setQueueTitle(charSequence);
    }

    private MediaSession.ControllerInfo tryGetController(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(remoteUserInfo);
        if (controller == null) {
            ControllerLegacyCb controllerLegacyCb = new ControllerLegacyCb(remoteUserInfo);
            MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.sessionManager.isTrustedForMediaControl(remoteUserInfo), controllerLegacyCb, Bundle.EMPTY, 0, Build.VERSION.SDK_INT >= 33);
            MediaSession.ConnectionResult connectionResultOnConnectOnHandler = this.sessionImpl.onConnectOnHandler(controllerInfo);
            if (!connectionResultOnConnectOnHandler.isAccepted) {
                c2.h(controllerLegacyCb, 0);
                return null;
            }
            this.connectedControllersManager.addController(controllerInfo.getRemoteUserInfo(), controllerInfo, connectionResultOnConnectOnHandler.availableSessionCommands, connectionResultOnConnectOnHandler.availablePlayerCommands);
            this.sessionImpl.onPostConnectOnHandler(controllerInfo);
            controller = controllerInfo;
        }
        this.connectionTimeoutHandler.disconnectControllerAfterTimeout(controller, this.connectionTimeoutMs);
        return controller;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences() {
        com.google.common.collect.h1 h1Var = this.mediaButtonPreferences;
        SessionCommands sessionCommands = this.availableSessionCommands;
        Player.Commands commands = this.playerCommandsForErrorState;
        if (commands == null) {
            commands = this.availablePlayerCommands;
        }
        this.customLayout = CommandButton.getCustomLayoutFromMediaButtonPreferences(CommandButton.copyWithUnavailableButtonsDisabled(h1Var, sessionCommands, commands), true, true);
        this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", !CommandButton.containsButtonForSlot(r0, 2));
        this.legacyExtras.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", true ^ CommandButton.containsButtonForSlot(this.customLayout, 3));
    }

    public boolean canResumePlaybackOnStart() {
        return this.broadcastReceiverComponentName != null;
    }

    public void clearLegacyErrorStatus() {
        if (this.legacyError != null) {
            this.legacyError = null;
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
        }
    }

    public ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    public MediaSession.ControllerCb getControllerLegacyCbForBroadcast() {
        return this.controllerLegacyCbForBroadcast;
    }

    public MediaSession.ConnectionResult getPlatformConnectionResult(MediaSession mediaSession) {
        MediaSession.ConnectionResult.AcceptedResultBuilder availablePlayerCommands = new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession).setAvailableSessionCommands(this.availableSessionCommands).setAvailablePlayerCommands(this.availablePlayerCommands);
        if (this.mediaButtonPreferences.isEmpty()) {
            availablePlayerCommands.setCustomLayout(this.customLayout);
        } else {
            availablePlayerCommands.setMediaButtonPreferences(this.mediaButtonPreferences);
        }
        return availablePlayerCommands.build();
    }

    public MediaSessionCompat getSessionCompat() {
        return this.sessionCompat;
    }

    public void handleMediaPlayPauseOnHandler(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        dispatchSessionTaskWithPlayerCommand(1, new l2(this, 1), remoteUserInfo, true);
    }

    public void maybeUpdateFlags(PlayerWrapper playerWrapper) {
        int i10 = playerWrapper.isCommandAvailable(20) ? 4 : 0;
        if (this.sessionFlags != i10) {
            this.sessionFlags = i10;
            this.sessionCompat.setFlags(i10);
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        handleOnAddQueueItem(mediaDescriptionCompat, -1);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        str.getClass();
        if (str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            return;
        }
        if (str.equals("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN") && resultReceiver != null) {
            resultReceiver.send(0, this.sessionImpl.getToken().toBundle());
        } else {
            SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
            dispatchSessionTaskWithSessionCommand(sessionCommand, new l(this, sessionCommand, bundle, resultReceiver));
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onCustomAction(String str, Bundle bundle) {
        if (str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            return;
        }
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        SessionCommand sessionCommand = new SessionCommand(str, bundle);
        if (CommandButton.isPredefinedCustomCommandButtonCode(sessionCommand.customAction)) {
            dispatchCustomCommandAsPredefinedCommand(sessionCommand);
        } else {
            dispatchSessionTaskWithSessionCommand(sessionCommand, new j(this, sessionCommand, bundle, 6));
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onFastForward() {
        dispatchSessionTaskWithPlayerCommand(12, new l2(this, 0), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public boolean onMediaButtonEvent(Intent intent) {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        MediaSessionManager.RemoteUserInfo currentControllerInfo = this.sessionCompat.getCurrentControllerInfo();
        currentControllerInfo.getClass();
        return mediaSessionImpl.onMediaButtonEvent(new MediaSession.ControllerInfo(currentControllerInfo, 0, 0, false, null, Bundle.EMPTY, 0, Build.VERSION.SDK_INT >= 33), intent);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPause() {
        dispatchSessionTaskWithPlayerCommand(1, new l2(this, 10), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlay() {
        dispatchSessionTaskWithPlayRequest();
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, null, null, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, str, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPlayFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, uri, null, bundle), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepare() {
        dispatchSessionTaskWithPlayerCommand(2, new l2(this, 6), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, null, null, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, null, str, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onPrepareFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(null, uri, null, bundle), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(20, new k(this, mediaDescriptionCompat, 15), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onRewind() {
        dispatchSessionTaskWithPlayerCommand(11, new l2(this, 4), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSeekTo(long j10) {
        dispatchSessionTaskWithPlayerCommand(5, new m2(this, j10, 1), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetCaptioningEnabled(boolean z) {
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetPlaybackSpeed(float f10) {
        if (f10 <= 0.0f) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(13, new s(this, f10, 3), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat ratingCompat) {
        onSetRating(ratingCompat, null);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRepeatMode(int i10) {
        dispatchSessionTaskWithPlayerCommand(15, new o2(this, i10, 0), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetShuffleMode(int i10) {
        dispatchSessionTaskWithPlayerCommand(14, new o2(this, i10, 1), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToNext() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(9)) {
            dispatchSessionTaskWithPlayerCommand(9, new l2(this, 8), this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(8, new l2(this, 9), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToPrevious() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(7)) {
            dispatchSessionTaskWithPlayerCommand(7, new l2(this, 2), this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(6, new l2(this, 3), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSkipToQueueItem(long j10) {
        if (j10 < 0) {
            return;
        }
        dispatchSessionTaskWithPlayerCommand(10, new m2(this, j10, 0), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onStop() {
        dispatchSessionTaskWithPlayerCommand(3, new l2(this, 7), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    public void release() {
        if (Build.VERSION.SDK_INT < 31) {
            if (this.broadcastReceiverComponentName == null) {
                setMediaButtonReceiver(this.sessionCompat, null);
            } else {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", this.sessionImpl.getUri());
                intent.setComponent(this.broadcastReceiverComponentName);
                setMediaButtonReceiver(this.sessionCompat, PendingIntent.getBroadcast(this.sessionImpl.getContext(), 0, intent, PENDING_INTENT_FLAG_MUTABLE));
            }
        }
        if (this.runtimeBroadcastReceiver != null) {
            this.sessionImpl.getContext().unregisterReceiver(this.runtimeBroadcastReceiver);
        }
        this.sessionCompat.release();
    }

    public void setAvailableCommands(SessionCommands sessionCommands, Player.Commands commands) {
        if (this.customPlaybackException != null) {
            return;
        }
        boolean z = this.availablePlayerCommands.contains(17) != commands.contains(17);
        this.availableSessionCommands = sessionCommands;
        this.availablePlayerCommands = commands;
        if (!this.mediaButtonPreferences.isEmpty()) {
            boolean z5 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
            boolean z10 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
            updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
            if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false) != z5 || this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false) != z10) {
                getSessionCompat().setExtras(this.legacyExtras);
            }
        }
        if (z) {
            updateLegacySessionPlaybackStateAndQueue(this.sessionImpl.getPlayerWrapper());
        } else {
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
        }
    }

    public void setLegacyControllerDisconnectTimeoutMs(long j10) {
        this.connectionTimeoutMs = j10;
    }

    public void setLegacyError(LibraryResult<?> libraryResult, boolean z) {
        int iConvertToLegacyErrorCode = LegacyConversions.convertToLegacyErrorCode(libraryResult.resultCode);
        LegacyError legacyError = this.legacyError;
        if (legacyError == null || legacyError.code != iConvertToLegacyErrorCode) {
            SessionError sessionError = libraryResult.sessionError;
            String str = sessionError != null ? sessionError.message : "no error message provided";
            Bundle bundle = Bundle.EMPTY;
            MediaLibraryService.LibraryParams libraryParams = libraryResult.params;
            if (libraryParams == null || !libraryParams.extras.containsKey("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT")) {
                SessionError sessionError2 = libraryResult.sessionError;
                if (sessionError2 != null) {
                    bundle = sessionError2.extras;
                }
            } else {
                bundle = libraryResult.params.extras;
            }
            this.legacyError = new LegacyError(z, iConvertToLegacyErrorCode, str, bundle, null);
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
        }
    }

    public void setPlatformCustomLayout(com.google.common.collect.h1 h1Var) {
        this.customLayout = h1Var;
    }

    public void setPlatformMediaButtonPreferences(com.google.common.collect.h1 h1Var) {
        this.mediaButtonPreferences = h1Var;
        boolean z = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean z5 = this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        updateCustomLayoutAndLegacyExtrasForMediaButtonPreferences();
        if (this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false) == z && this.legacyExtras.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false) == z5) {
            return;
        }
        getSessionCompat().setExtras(this.legacyExtras);
    }

    public void setPlaybackException(PlaybackException playbackException, Player.Commands commands) {
        ac.b.j((playbackException == null && commands == null) || !(playbackException == null || commands == null));
        this.customPlaybackException = playbackException;
        this.playerCommandsForErrorState = commands;
        if (playbackException != null) {
            updateLegacySessionPlaybackState(this.sessionImpl.getPlayerWrapper());
            maybeUpdateFlags(this.sessionImpl.getPlayerWrapper());
        }
    }

    public void start() {
        this.sessionCompat.setActive(true);
    }

    public void updateLegacySessionPlaybackState(PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new n2(this, playerWrapper, 1));
    }

    public void updateLegacySessionPlaybackStateAndQueue(PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new n2(this, playerWrapper, 0));
    }

    private void handleMediaRequest(MediaItem mediaItem, boolean z, boolean z5) {
        dispatchSessionTaskWithPlayerCommand(31, new o0(this, z, mediaItem, z5), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        handleOnAddQueueItem(mediaDescriptionCompat, i10);
    }

    @Override // androidx.media3.session.legacy.MediaSessionCompat.Callback
    public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        Rating ratingConvertToRating = LegacyConversions.convertToRating(ratingCompat);
        if (ratingConvertToRating != null) {
            dispatchSessionTaskWithSetRatingSessionCommand(ratingConvertToRating);
            return;
        }
        Log.w(TAG, "Ignoring invalid RatingCompat " + ratingCompat);
    }
}
