package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
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
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.session.LegacyConversions;
import androidx.media3.session.MediaController;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
class MediaControllerImplLegacy implements MediaController.MediaControllerImpl {
    private static final String TAG = "MCImplLegacy";
    private static final long WAIT_TIME_MS_FOR_COMPAT_EXTRA_BINDER = 500;
    private final androidx.media3.common.util.BitmapLoader bitmapLoader;
    private MediaBrowserCompat browserCompat;
    private final com.google.common.collect.h1 commandButtonsForMediaItems;
    private boolean connected;
    private final Bundle connectionHints;
    final Context context;
    private MediaControllerCompat controllerCompat;
    private final ControllerCompatCallback controllerCompatCallback;
    private boolean hasPendingExtrasChange;
    private final MediaController instance;
    private final ListenerSet<Player.Listener> listeners;
    private final long platformSessionCallbackAggregationTimeoutMs;
    private boolean released;
    private final SessionToken token;
    private LegacyPlayerInfo legacyPlayerInfo = new LegacyPlayerInfo();
    private LegacyPlayerInfo pendingLegacyPlayerInfo = new LegacyPlayerInfo();
    private ControllerInfo controllerInfo = new ControllerInfo();
    private long currentPositionMs = C.TIME_UNSET;
    private long lastSetPlayWhenReadyCalledTimeMs = C.TIME_UNSET;

    public class ConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private ConnectionCallback() {
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            MediaBrowserCompat browserCompat = MediaControllerImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                MediaControllerImplLegacy.this.connectToSession(browserCompat.getSessionToken());
            }
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        @Override // androidx.media3.session.legacy.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            MediaControllerImplLegacy.this.getInstance().release();
        }
    }

    public final class ControllerCompatCallback extends MediaControllerCompat.Callback {
        private static final int MSG_HANDLE_PENDING_UPDATES = 1;
        private final Handler pendingChangesHandler;

        public ControllerCompatCallback(Looper looper) {
            this.pendingChangesHandler = new Handler(looper, new a1(this, 1));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$new$0(Message message) {
            if (message.what == 1) {
                MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
                mediaControllerImplLegacy.handleNewLegacyParameters(false, mediaControllerImplLegacy.pendingLegacyPlayerInfo);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onCaptioningEnabledChanged$2(boolean z, MediaController.Listener listener) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("androidx.media3.session.ARGUMENT_CAPTIONING_ENABLED", z);
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(MediaControllerImplLegacy.this.getInstance(), new SessionCommand("androidx.media3.session.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED", Bundle.EMPTY), bundle));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSessionEvent$1(String str, Bundle bundle, MediaController.Listener listener) {
            MediaControllerImplLegacy.ignoreFuture(listener.onCustomCommand(MediaControllerImplLegacy.this.getInstance(), new SessionCommand(str, bundle), bundle));
        }

        private void startWaitingForPendingChanges() {
            if (this.pendingChangesHandler.hasMessages(1)) {
                return;
            }
            this.pendingChangesHandler.sendEmptyMessageDelayed(1, MediaControllerImplLegacy.this.platformSessionCallbackAggregationTimeoutMs);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo playbackInfo) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackInfoCompat(playbackInfo);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onCaptioningEnabledChanged(boolean z) {
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new h1(0, this, z));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onExtrasChanged(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithSessionExtras(bundle);
            MediaControllerImplLegacy.this.hasPendingExtrasChange = true;
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithMediaMetadataCompat(mediaMetadataCompat);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithPlaybackStateCompat(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(playbackStateCompat));
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueue(MediaControllerImplLegacy.convertToNonNullQueueItemList(list));
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onQueueTitleChanged(CharSequence charSequence) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithQueueTitle(charSequence);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onRepeatModeChanged(int i10) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithRepeatMode(i10);
            startWaitingForPendingChanges();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            MediaControllerImplLegacy.this.getInstance().release();
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionEvent(String str, Bundle bundle) {
            if (str == null) {
                return;
            }
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            MediaControllerImplLegacy.this.getInstance().notifyControllerListener(new j(this, str, bundle, 5));
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onSessionReady() {
            if (!MediaControllerImplLegacy.this.connected) {
                MediaControllerImplLegacy.this.onConnected();
                return;
            }
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithExtraBinderGetters(MediaControllerImplLegacy.convertToSafePlaybackStateCompat(MediaControllerImplLegacy.this.controllerCompat.getPlaybackState()), MediaControllerImplLegacy.this.controllerCompat.getRepeatMode(), MediaControllerImplLegacy.this.controllerCompat.getShuffleMode());
            onCaptioningEnabledChanged(MediaControllerImplLegacy.this.controllerCompat.isCaptioningEnabled());
            this.pendingChangesHandler.removeMessages(1);
            MediaControllerImplLegacy mediaControllerImplLegacy2 = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy2.handleNewLegacyParameters(false, mediaControllerImplLegacy2.pendingLegacyPlayerInfo);
        }

        @Override // androidx.media3.session.legacy.MediaControllerCompat.Callback
        public void onShuffleModeChanged(int i10) {
            MediaControllerImplLegacy mediaControllerImplLegacy = MediaControllerImplLegacy.this;
            mediaControllerImplLegacy.pendingLegacyPlayerInfo = mediaControllerImplLegacy.pendingLegacyPlayerInfo.copyWithShuffleMode(i10);
            startWaitingForPendingChanges();
        }

        public void release() {
            this.pendingChangesHandler.removeCallbacksAndMessages(null);
        }
    }

    public MediaControllerImplLegacy(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j10) {
        this.listeners = new ListenerSet<>(looper, Clock.DEFAULT, new f1(this));
        this.context = context;
        this.instance = mediaController;
        this.controllerCompatCallback = new ControllerCompatCallback(looper);
        this.token = sessionToken;
        this.connectionHints = bundle;
        this.bitmapLoader = bitmapLoader;
        this.platformSessionCallbackAggregationTimeoutMs = j10;
        com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
        this.commandButtonsForMediaItems = o3.f14078o;
    }

    private void addQueueItems(List<MediaItem> list, int i10) {
        ArrayList arrayList = new ArrayList();
        g1 g1Var = new g1(this, new AtomicInteger(0), list, arrayList, i10);
        for (int i11 = 0; i11 < list.size(); i11++) {
            byte[] bArr = list.get(i11).mediaMetadata.artworkData;
            if (bArr == null) {
                arrayList.add(null);
                g1Var.run();
            } else {
                com.google.common.util.concurrent.d1<Bitmap> d1VarDecodeBitmap = this.bitmapLoader.decodeBitmap(bArr);
                arrayList.add(d1VarDecodeBitmap);
                Handler handler = getInstance().applicationHandler;
                Objects.requireNonNull(handler);
                d1VarDecodeBitmap.addListener(g1Var, new androidx.media3.common.util.d(handler));
            }
        }
    }

    private static ControllerInfo buildNewControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo, ControllerInfo controllerInfo, LegacyPlayerInfo legacyPlayerInfo2, String str, long j10, boolean z5, int i10, long j11, boolean z10, Context context) {
        int iFindQueueItemIndex;
        MediaMetadata mediaMetadata;
        SessionCommands sessionCommandsConvertToSessionCommands;
        com.google.common.collect.h1 h1VarConvertToMediaButtonPreferences;
        int iConvertToPlaybackState;
        preserveExistingBitmapData(legacyPlayerInfo, legacyPlayerInfo2);
        List<MediaSessionCompat.QueueItem> list = legacyPlayerInfo.queue;
        List<MediaSessionCompat.QueueItem> list2 = legacyPlayerInfo2.queue;
        boolean z11 = list != list2;
        QueueTimeline queueTimelineCreate = z11 ? QueueTimeline.create(list2) : ((QueueTimeline) controllerInfo.playerInfo.timeline).copy();
        boolean z12 = legacyPlayerInfo.mediaMetadataCompat != legacyPlayerInfo2.mediaMetadataCompat || z;
        long activeQueueId = getActiveQueueId(legacyPlayerInfo.playbackStateCompat);
        long activeQueueId2 = getActiveQueueId(legacyPlayerInfo2.playbackStateCompat);
        boolean z13 = activeQueueId != activeQueueId2 || z;
        long jConvertToDurationMs = LegacyConversions.convertToDurationMs(legacyPlayerInfo2.mediaMetadataCompat);
        boolean z14 = z11;
        if (z12 || z13 || z14) {
            boolean z15 = z12;
            iFindQueueItemIndex = findQueueItemIndex(legacyPlayerInfo2.queue, activeQueueId2);
            MediaMetadataCompat mediaMetadataCompat = legacyPlayerInfo2.mediaMetadataCompat;
            boolean z16 = mediaMetadataCompat != null;
            MediaMetadata mediaMetadataConvertToMediaMetadata = (z16 && z15) ? LegacyConversions.convertToMediaMetadata(mediaMetadataCompat, i10) : (z16 || !z13) ? controllerInfo.playerInfo.mediaMetadata : iFindQueueItemIndex == -1 ? MediaMetadata.EMPTY : LegacyConversions.convertToMediaMetadata(legacyPlayerInfo2.queue.get(iFindQueueItemIndex).getDescription(), i10);
            if (iFindQueueItemIndex != -1 || !z15) {
                if (iFindQueueItemIndex != -1) {
                    queueTimelineCreate = queueTimelineCreate.copyWithClearedFakeMediaItem();
                    if (z16) {
                        MediaItem mediaItemAt = queueTimelineCreate.getMediaItemAt(iFindQueueItemIndex);
                        mediaItemAt.getClass();
                        queueTimelineCreate = queueTimelineCreate.copyWithNewMediaItem(iFindQueueItemIndex, LegacyConversions.convertToMediaItem(mediaItemAt.mediaId, legacyPlayerInfo2.mediaMetadataCompat, i10), jConvertToDurationMs);
                    }
                } else {
                    iFindQueueItemIndex = 0;
                }
                mediaMetadata = mediaMetadataConvertToMediaMetadata;
            } else if (z16) {
                Log.w(TAG, "Adding a fake MediaItem at the end of the list because there's no QueueItem with the active queue id and current Timeline should have currently playing MediaItem.");
                queueTimelineCreate = queueTimelineCreate.copyWithFakeMediaItem(LegacyConversions.convertToMediaItem(legacyPlayerInfo2.mediaMetadataCompat, i10), jConvertToDurationMs);
                iFindQueueItemIndex = queueTimelineCreate.getWindowCount() - 1;
                mediaMetadata = mediaMetadataConvertToMediaMetadata;
            } else {
                queueTimelineCreate = queueTimelineCreate.copyWithClearedFakeMediaItem();
                iFindQueueItemIndex = 0;
                mediaMetadata = mediaMetadataConvertToMediaMetadata;
            }
        } else {
            PlayerInfo playerInfo = controllerInfo.playerInfo;
            iFindQueueItemIndex = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
            mediaMetadata = playerInfo.mediaMetadata;
        }
        QueueTimeline queueTimeline = queueTimelineCreate;
        int i11 = iFindQueueItemIndex;
        MediaControllerCompat.PlaybackInfo playbackInfo = legacyPlayerInfo2.playbackInfoCompat;
        Player.Commands commandsConvertToPlayerCommands = LegacyConversions.convertToPlayerCommands(legacyPlayerInfo2.playbackStateCompat, playbackInfo != null ? playbackInfo.getVolumeControl() : 0, j10, z5);
        CharSequence charSequence = legacyPlayerInfo.queueTitle;
        CharSequence charSequence2 = legacyPlayerInfo2.queueTitle;
        MediaMetadata mediaMetadataConvertToMediaMetadata2 = charSequence == charSequence2 ? controllerInfo.playerInfo.playlistMetadata : LegacyConversions.convertToMediaMetadata(charSequence2);
        int iConvertToRepeatMode = LegacyConversions.convertToRepeatMode(legacyPlayerInfo2.repeatMode);
        boolean zConvertToShuffleModeEnabled = LegacyConversions.convertToShuffleModeEnabled(legacyPlayerInfo2.shuffleMode);
        PlaybackStateCompat playbackStateCompat = legacyPlayerInfo.playbackStateCompat;
        PlaybackStateCompat playbackStateCompat2 = legacyPlayerInfo2.playbackStateCompat;
        if (playbackStateCompat != playbackStateCompat2 || z10) {
            sessionCommandsConvertToSessionCommands = LegacyConversions.convertToSessionCommands(playbackStateCompat2, z5);
            h1VarConvertToMediaButtonPreferences = LegacyConversions.convertToMediaButtonPreferences(legacyPlayerInfo2.playbackStateCompat, commandsConvertToPlayerCommands, legacyPlayerInfo2.sessionExtras);
        } else {
            sessionCommandsConvertToSessionCommands = controllerInfo.availableSessionCommands;
            h1VarConvertToMediaButtonPreferences = controllerInfo.mediaButtonPreferences;
        }
        SessionCommands sessionCommands = sessionCommandsConvertToSessionCommands;
        com.google.common.collect.h1 h1Var = h1VarConvertToMediaButtonPreferences;
        PlaybackException playbackExceptionConvertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo2.playbackStateCompat, context);
        SessionError sessionErrorConvertToSessionError = LegacyConversions.convertToSessionError(legacyPlayerInfo2.playbackStateCompat, context);
        long jConvertToCurrentPositionMs = LegacyConversions.convertToCurrentPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j11);
        long jConvertToBufferedPositionMs = LegacyConversions.convertToBufferedPositionMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j11);
        int iConvertToBufferedPercentage = LegacyConversions.convertToBufferedPercentage(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j11);
        long jConvertToTotalBufferedDurationMs = LegacyConversions.convertToTotalBufferedDurationMs(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j11);
        boolean zConvertToIsPlayingAd = LegacyConversions.convertToIsPlayingAd(legacyPlayerInfo2.mediaMetadataCompat);
        PlaybackParameters playbackParametersConvertToPlaybackParameters = LegacyConversions.convertToPlaybackParameters(legacyPlayerInfo2.playbackStateCompat);
        MediaControllerCompat.PlaybackInfo playbackInfo2 = legacyPlayerInfo2.playbackInfoCompat;
        AudioAttributes audioAttributes = playbackInfo2 == null ? AudioAttributes.DEFAULT : playbackInfo2.getAudioAttributes();
        boolean zConvertToPlayWhenReady = LegacyConversions.convertToPlayWhenReady(legacyPlayerInfo2.playbackStateCompat);
        try {
            iConvertToPlaybackState = LegacyConversions.convertToPlaybackState(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo2.mediaMetadataCompat, j11);
        } catch (LegacyConversions.ConversionException unused) {
            Log.e(TAG, "Received invalid playback state " + legacyPlayerInfo2.playbackStateCompat.getState() + " from package " + str + ". Keeping the previous state.");
            iConvertToPlaybackState = controllerInfo.playerInfo.playbackState;
        }
        int i12 = iConvertToPlaybackState;
        boolean zConvertToIsPlaying = LegacyConversions.convertToIsPlaying(legacyPlayerInfo2.playbackStateCompat);
        DeviceInfo deviceInfoConvertToDeviceInfo = LegacyConversions.convertToDeviceInfo(legacyPlayerInfo2.playbackInfoCompat);
        int iConvertToDeviceVolume = LegacyConversions.convertToDeviceVolume(legacyPlayerInfo2.playbackInfoCompat);
        boolean zConvertToIsDeviceMuted = LegacyConversions.convertToIsDeviceMuted(legacyPlayerInfo2.playbackInfoCompat);
        PlayerInfo playerInfo2 = controllerInfo.playerInfo;
        return createControllerInfo(queueTimeline, mediaMetadata, i11, mediaMetadataConvertToMediaMetadata2, iConvertToRepeatMode, zConvertToShuffleModeEnabled, sessionCommands, commandsConvertToPlayerCommands, h1Var, legacyPlayerInfo2.sessionExtras, playbackExceptionConvertToPlaybackException, sessionErrorConvertToSessionError, jConvertToDurationMs, jConvertToCurrentPositionMs, jConvertToBufferedPositionMs, iConvertToBufferedPercentage, jConvertToTotalBufferedDurationMs, zConvertToIsPlayingAd, playbackParametersConvertToPlaybackParameters, audioAttributes, zConvertToPlayWhenReady, i12, zConvertToIsPlaying, deviceInfoConvertToDeviceInfo, iConvertToDeviceVolume, zConvertToIsDeviceMuted, playerInfo2.seekBackIncrementMs, playerInfo2.seekForwardIncrementMs, playerInfo2.maxSeekToPreviousPositionMs);
    }

    private static int calculateCurrentItemIndexAfterAddItems(int i10, int i11, int i12) {
        return i10 < i11 ? i10 : i10 + i12;
    }

    private static int calculateCurrentItemIndexAfterRemoveItems(int i10, int i11, int i12) {
        int i13 = i12 - i11;
        if (i10 < i11) {
            return i10;
        }
        if (i10 < i12) {
            return -1;
        }
        return i10 - i13;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.util.Pair<java.lang.Integer, java.lang.Integer> calculateDiscontinuityAndTransitionReason(androidx.media3.session.MediaControllerImplLegacy.LegacyPlayerInfo r5, androidx.media3.session.MediaControllerImplLegacy.ControllerInfo r6, androidx.media3.session.MediaControllerImplLegacy.LegacyPlayerInfo r7, androidx.media3.session.MediaControllerImplLegacy.ControllerInfo r8, long r9) {
        /*
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1 = 0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            androidx.media3.session.PlayerInfo r2 = r6.playerInfo
            androidx.media3.common.Timeline r2 = r2.timeline
            boolean r2 = r2.isEmpty()
            androidx.media3.session.PlayerInfo r3 = r8.playerInfo
            androidx.media3.common.Timeline r3 = r3.timeline
            boolean r3 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L22
            if (r3 == 0) goto L22
        L1f:
            r0 = r4
            r1 = r0
            goto L83
        L22:
            if (r2 == 0) goto L27
            if (r3 != 0) goto L27
            goto L83
        L27:
            androidx.media3.session.PlayerInfo r6 = r6.playerInfo
            androidx.media3.common.MediaItem r6 = r6.getCurrentMediaItem()
            r6.getClass()
            androidx.media3.session.PlayerInfo r2 = r8.playerInfo
            androidx.media3.common.Timeline r2 = r2.timeline
            androidx.media3.session.QueueTimeline r2 = (androidx.media3.session.QueueTimeline) r2
            boolean r2 = r2.contains(r6)
            if (r2 != 0) goto L42
            r5 = 4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            goto L83
        L42:
            androidx.media3.session.PlayerInfo r0 = r8.playerInfo
            androidx.media3.common.MediaItem r0 = r0.getCurrentMediaItem()
            boolean r6 = r6.equals(r0)
            r0 = 1
            if (r6 == 0) goto L7f
            androidx.media3.session.legacy.PlaybackStateCompat r6 = r5.playbackStateCompat
            androidx.media3.session.legacy.MediaMetadataCompat r5 = r5.mediaMetadataCompat
            long r5 = androidx.media3.session.LegacyConversions.convertToCurrentPositionMs(r6, r5, r9)
            androidx.media3.session.legacy.PlaybackStateCompat r2 = r7.playbackStateCompat
            androidx.media3.session.legacy.MediaMetadataCompat r7 = r7.mediaMetadataCompat
            long r9 = androidx.media3.session.LegacyConversions.convertToCurrentPositionMs(r2, r7, r9)
            r2 = 0
            int r7 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r7 != 0) goto L6d
            androidx.media3.session.PlayerInfo r7 = r8.playerInfo
            int r7 = r7.repeatMode
            if (r7 != r0) goto L6d
            r0 = r1
            goto L83
        L6d:
            long r5 = r5 - r9
            long r5 = java.lang.Math.abs(r5)
            r7 = 100
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L1f
            r5 = 5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            r0 = r4
            goto L83
        L7f:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L83:
            android.util.Pair r5 = android.util.Pair.create(r1, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaControllerImplLegacy.calculateDiscontinuityAndTransitionReason(androidx.media3.session.MediaControllerImplLegacy$LegacyPlayerInfo, androidx.media3.session.MediaControllerImplLegacy$ControllerInfo, androidx.media3.session.MediaControllerImplLegacy$LegacyPlayerInfo, androidx.media3.session.MediaControllerImplLegacy$ControllerInfo, long):android.util.Pair");
    }

    private void connectToService() {
        getInstance().runOnApplicationLooper(new e1(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToSession(MediaSessionCompat.Token token) {
        getInstance().runOnApplicationLooper(new g(this, token, 1));
        getInstance().applicationHandler.postDelayed(new e1(this, 0), 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<MediaSessionCompat.QueueItem> convertToNonNullQueueItemList(List<MediaSessionCompat.QueueItem> list) {
        return list == null ? Collections.EMPTY_LIST : MediaUtils.removeNullElements(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PlaybackStateCompat convertToSafePlaybackStateCompat(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return null;
        }
        if (playbackStateCompat.getPlaybackSpeed() > 0.0f) {
            return playbackStateCompat;
        }
        Log.w(TAG, "Adjusting playback speed to 1.0f because negative playback speed isn't supported.");
        return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), playbackStateCompat.getPosition(), 1.0f, playbackStateCompat.getLastPositionUpdateTime()).build();
    }

    private static ControllerInfo createControllerInfo(QueueTimeline queueTimeline, MediaMetadata mediaMetadata, int i10, MediaMetadata mediaMetadata2, int i11, boolean z, SessionCommands sessionCommands, Player.Commands commands, com.google.common.collect.h1 h1Var, Bundle bundle, PlaybackException playbackException, SessionError sessionError, long j10, long j11, long j12, int i12, long j13, boolean z5, PlaybackParameters playbackParameters, AudioAttributes audioAttributes, boolean z10, int i13, boolean z11, DeviceInfo deviceInfo, int i14, boolean z12, long j14, long j15, long j16) {
        SessionPositionInfo sessionPositionInfo = new SessionPositionInfo(createPositionInfo(i10, queueTimeline.getMediaItemAt(i10), j11, z5), z5, SystemClock.elapsedRealtime(), j10, j12, i12, j13, C.TIME_UNSET, j10, j12);
        Player.PositionInfo positionInfo = SessionPositionInfo.DEFAULT_POSITION_INFO;
        return new ControllerInfo(new PlayerInfo(playbackException, 0, sessionPositionInfo, positionInfo, positionInfo, 0, playbackParameters, i11, z, VideoSize.UNKNOWN, queueTimeline, 0, mediaMetadata2, 1.0f, 1.0f, audioAttributes, 0, CueGroup.EMPTY_TIME_ZERO, deviceInfo, i14, z12, z10, 1, 0, i13, z11, false, mediaMetadata, j14, j15, j16, Tracks.EMPTY, TrackSelectionParameters.DEFAULT), sessionCommands, commands, h1Var, bundle, sessionError);
    }

    private static Player.PositionInfo createPositionInfo(int i10, MediaItem mediaItem, long j10, boolean z) {
        return new Player.PositionInfo(null, i10, mediaItem, null, i10, j10, j10, z ? 0 : -1, z ? 0 : -1);
    }

    private static SessionPositionInfo createSessionPositionInfo(Player.PositionInfo positionInfo, boolean z, long j10, long j11, int i10, long j12) {
        return new SessionPositionInfo(positionInfo, z, SystemClock.elapsedRealtime(), j10, j11, i10, j12, C.TIME_UNSET, j10, j11);
    }

    private static int findQueueItemIndex(List<MediaSessionCompat.QueueItem> list, long j10) {
        if (list != null && j10 != -1) {
            for (int i10 = 0; i10 < list.size(); i10++) {
                if (list.get(i10).getQueueId() == j10) {
                    return i10;
                }
            }
        }
        return -1;
    }

    private static long getActiveQueueId(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return -1L;
        }
        return playbackStateCompat.getActiveQueueItemId();
    }

    private static Bundle getOrEmptyBundle(Bundle bundle) {
        return bundle == null ? Bundle.EMPTY : bundle;
    }

    private void handleBitmapFuturesAllCompletedAndAddQueueItems(List<com.google.common.util.concurrent.d1<Bitmap>> list, List<MediaItem> list2, int i10) {
        Bitmap bitmap;
        for (int i11 = 0; i11 < list.size(); i11++) {
            com.google.common.util.concurrent.d1<Bitmap> d1Var = list.get(i11);
            if (d1Var != null) {
                try {
                    bitmap = (Bitmap) xc.d.j0(d1Var);
                } catch (CancellationException | ExecutionException e5) {
                    Log.d(TAG, "Failed to get bitmap", e5);
                    bitmap = null;
                }
            } else {
                bitmap = null;
            }
            this.controllerCompat.addQueueItem(LegacyConversions.convertToMediaDescriptionCompat(list2.get(i11), bitmap), i10 + i11);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewLegacyParameters(boolean z, LegacyPlayerInfo legacyPlayerInfo) {
        if (this.released || !this.connected) {
            return;
        }
        ControllerInfo controllerInfoBuildNewControllerInfo = buildNewControllerInfo(z, this.legacyPlayerInfo, this.controllerInfo, legacyPlayerInfo, this.controllerCompat.getPackageName(), this.controllerCompat.getFlags(), this.controllerCompat.isSessionReady(), this.controllerCompat.getRatingType(), getInstance().getTimeDiffMs(), this.hasPendingExtrasChange, this.context);
        Pair<Integer, Integer> pairCalculateDiscontinuityAndTransitionReason = calculateDiscontinuityAndTransitionReason(this.legacyPlayerInfo, this.controllerInfo, legacyPlayerInfo, controllerInfoBuildNewControllerInfo, getInstance().getTimeDiffMs());
        updateControllerInfo(z, legacyPlayerInfo, true, controllerInfoBuildNewControllerInfo, (Integer) pairCalculateDiscontinuityAndTransitionReason.first, (Integer) pairCalculateDiscontinuityAndTransitionReason.second);
        if (this.hasPendingExtrasChange) {
            this.hasPendingExtrasChange = false;
            getInstance().notifyControllerListener(new k(this, legacyPlayerInfo, 11));
        }
    }

    private boolean hasMedia() {
        return !this.controllerInfo.playerInfo.timeline.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void ignoreFuture(Future<T> future) {
    }

    private void initializeLegacyPlaylist() {
        Timeline.Window window = new Timeline.Window();
        ac.b.s(isPrepared() && hasMedia());
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        QueueTimeline queueTimeline = (QueueTimeline) playerInfo.timeline;
        int i10 = playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
        MediaItem mediaItem = queueTimeline.getWindow(i10, window).mediaItem;
        if (queueTimeline.getQueueId(i10) == -1) {
            MediaItem.RequestMetadata requestMetadata = mediaItem.requestMetadata;
            if (requestMetadata.mediaUri != null) {
                if (this.controllerInfo.playerInfo.playWhenReady) {
                    MediaControllerCompat.TransportControls transportControls = this.controllerCompat.getTransportControls();
                    MediaItem.RequestMetadata requestMetadata2 = mediaItem.requestMetadata;
                    transportControls.playFromUri(requestMetadata2.mediaUri, getOrEmptyBundle(requestMetadata2.extras));
                } else {
                    MediaControllerCompat.TransportControls transportControls2 = this.controllerCompat.getTransportControls();
                    MediaItem.RequestMetadata requestMetadata3 = mediaItem.requestMetadata;
                    transportControls2.prepareFromUri(requestMetadata3.mediaUri, getOrEmptyBundle(requestMetadata3.extras));
                }
            } else if (requestMetadata.searchQuery != null) {
                if (this.controllerInfo.playerInfo.playWhenReady) {
                    MediaControllerCompat.TransportControls transportControls3 = this.controllerCompat.getTransportControls();
                    MediaItem.RequestMetadata requestMetadata4 = mediaItem.requestMetadata;
                    transportControls3.playFromSearch(requestMetadata4.searchQuery, getOrEmptyBundle(requestMetadata4.extras));
                } else {
                    MediaControllerCompat.TransportControls transportControls4 = this.controllerCompat.getTransportControls();
                    MediaItem.RequestMetadata requestMetadata5 = mediaItem.requestMetadata;
                    transportControls4.prepareFromSearch(requestMetadata5.searchQuery, getOrEmptyBundle(requestMetadata5.extras));
                }
            } else if (this.controllerInfo.playerInfo.playWhenReady) {
                this.controllerCompat.getTransportControls().playFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            } else {
                this.controllerCompat.getTransportControls().prepareFromMediaId(mediaItem.mediaId, getOrEmptyBundle(mediaItem.requestMetadata.extras));
            }
        } else if (this.controllerInfo.playerInfo.playWhenReady) {
            this.controllerCompat.getTransportControls().play();
        } else {
            this.controllerCompat.getTransportControls().prepare();
        }
        if (this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs != 0) {
            this.controllerCompat.getTransportControls().seekTo(this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.positionMs);
        }
        if (getAvailableCommands().contains(20)) {
            ArrayList arrayList = new ArrayList();
            for (int i11 = 0; i11 < queueTimeline.getWindowCount(); i11++) {
                if (i11 != i10 && queueTimeline.getQueueId(i11) == -1) {
                    arrayList.add(queueTimeline.getWindow(i11, window).mediaItem);
                }
            }
            addQueueItems(arrayList, 0);
        }
    }

    private boolean isPrepared() {
        return this.controllerInfo.playerInfo.playbackState != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addQueueItems$4(AtomicInteger atomicInteger, List list, List list2, int i10) {
        if (atomicInteger.incrementAndGet() == list.size()) {
            handleBitmapFuturesAllCompletedAndAddQueueItems(list2, list, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$connectToService$3() {
        MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(this.context, this.token.getComponentName(), new ConnectionCallback(), this.instance.getConnectionHints());
        this.browserCompat = mediaBrowserCompat;
        mediaBrowserCompat.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$connectToSession$1(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(this.context, token);
        this.controllerCompat = mediaControllerCompat;
        mediaControllerCompat.registerCallback(this.controllerCompatCallback, getInstance().applicationHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$connectToSession$2() {
        if (this.released || this.controllerCompat.isSessionReady()) {
            return;
        }
        onConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleNewLegacyParameters$5(LegacyPlayerInfo legacyPlayerInfo, MediaController.Listener listener) {
        listener.onExtrasChanged(getInstance(), legacyPlayerInfo.sessionExtras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(getInstance(), new Player.Events(flagSet));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$10(ControllerInfo controllerInfo, ControllerInfo controllerInfo2, Integer num, Player.Listener listener) {
        listener.onPositionDiscontinuity(controllerInfo.playerInfo.sessionPositionInfo.positionInfo, controllerInfo2.playerInfo.sessionPositionInfo.positionInfo, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$11(ControllerInfo controllerInfo, Integer num, Player.Listener listener) {
        listener.onMediaItemTransition(controllerInfo.playerInfo.getCurrentMediaItem(), num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$14(Player.Listener listener) {
        listener.onMediaMetadataChanged(this.controllerInfo.playerInfo.mediaMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$15(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onPlaybackStateChanged(controllerInfo.playerInfo.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$16(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onPlayWhenReadyChanged(controllerInfo.playerInfo.playWhenReady, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$17(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onIsPlayingChanged(controllerInfo.playerInfo.isPlaying);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$18(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onPlaybackParametersChanged(controllerInfo.playerInfo.playbackParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$19(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onRepeatModeChanged(controllerInfo.playerInfo.repeatMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$20(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onShuffleModeEnabledChanged(controllerInfo.playerInfo.shuffleModeEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$21(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onAudioAttributesChanged(controllerInfo.playerInfo.audioAttributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$22(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onAudioSessionIdChanged(controllerInfo.playerInfo.audioSessionId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$23(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onDeviceInfoChanged(controllerInfo.playerInfo.deviceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$24(ControllerInfo controllerInfo, Player.Listener listener) {
        PlayerInfo playerInfo = controllerInfo.playerInfo;
        listener.onDeviceVolumeChanged(playerInfo.deviceVolume, playerInfo.deviceMuted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$25(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onAvailableCommandsChanged(controllerInfo.availablePlayerCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$26(ControllerInfo controllerInfo, MediaController.Listener listener) {
        listener.onAvailableSessionCommandsChanged(getInstance(), controllerInfo.availableSessionCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$27(ControllerInfo controllerInfo, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo.mediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$28(ControllerInfo controllerInfo, MediaController.Listener listener) {
        listener.onError(getInstance(), controllerInfo.sessionError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$6(ControllerInfo controllerInfo, MediaController.Listener listener) {
        ignoreFuture(listener.onSetCustomLayout(getInstance(), controllerInfo.mediaButtonPreferences));
        listener.onCustomLayoutChanged(getInstance(), controllerInfo.mediaButtonPreferences);
        listener.onMediaButtonPreferencesChanged(getInstance(), controllerInfo.mediaButtonPreferences);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateControllerInfo$7(ControllerInfo controllerInfo) {
        getInstance().notifyControllerListener(new d1(this, controllerInfo, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$8(ControllerInfo controllerInfo, Player.Listener listener) {
        PlayerInfo playerInfo = controllerInfo.playerInfo;
        listener.onTimelineChanged(playerInfo.timeline, playerInfo.timelineChangeReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateControllerInfo$9(ControllerInfo controllerInfo, Player.Listener listener) {
        listener.onPlaylistMetadataChanged(controllerInfo.playerInfo.playlistMetadata);
    }

    private static void preserveExistingBitmapData(LegacyPlayerInfo legacyPlayerInfo, LegacyPlayerInfo legacyPlayerInfo2) {
        MediaSessionCompat.QueueItem queueItem;
        MediaMetadataCompat mediaMetadataCompat;
        MediaMetadataCompat mediaMetadataCompat2 = legacyPlayerInfo.mediaMetadataCompat;
        if (mediaMetadataCompat2 != null && (mediaMetadataCompat = legacyPlayerInfo2.mediaMetadataCompat) != null) {
            mediaMetadataCompat.preserveArtworkBitmapData(mediaMetadataCompat2);
        }
        if (legacyPlayerInfo.queue != legacyPlayerInfo2.queue) {
            HashMap map = new HashMap();
            for (int i10 = 0; i10 < legacyPlayerInfo.queue.size(); i10++) {
                MediaSessionCompat.QueueItem queueItem2 = legacyPlayerInfo.queue.get(i10);
                if (queueItem2.getDescription().getIconBitmap() != null) {
                    map.put(Long.valueOf(queueItem2.getQueueId()), queueItem2);
                }
            }
            for (int i11 = 0; i11 < legacyPlayerInfo2.queue.size(); i11++) {
                MediaSessionCompat.QueueItem queueItem3 = legacyPlayerInfo2.queue.get(i11);
                if (queueItem3.getDescription().getIconBitmap() != null && (queueItem = (MediaSessionCompat.QueueItem) map.get(Long.valueOf(queueItem3.getQueueId()))) != null) {
                    queueItem3.getDescription().preserveIconBitmapData(queueItem.getDescription());
                }
            }
        }
    }

    private void seekToInternal(int i10, long j10) {
        Integer num;
        Integer num2;
        int i11;
        long j11;
        long j12;
        long j13;
        int i12 = i10;
        long j14 = j10;
        ac.b.j(i12 >= 0);
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        Timeline timeline = this.controllerInfo.playerInfo.timeline;
        if ((timeline.isEmpty() || i12 < timeline.getWindowCount()) && !isPlayingAd()) {
            if (i12 != currentMediaItemIndex) {
                long queueId = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).getQueueId(i12);
                if (queueId != -1) {
                    this.controllerCompat.getTransportControls().skipToQueueItem(queueId);
                    num = 2;
                } else {
                    androidx.fragment.app.a2.s(i12, "Cannot seek to new media item due to the missing queue Id at media item, mediaItemIndex=", TAG);
                    i12 = currentMediaItemIndex;
                    num = null;
                }
            } else {
                i12 = currentMediaItemIndex;
                num = null;
            }
            long currentPosition = getCurrentPosition();
            if (j14 == C.TIME_UNSET) {
                num2 = null;
                j14 = currentPosition;
            } else {
                this.controllerCompat.getTransportControls().seekTo(j14);
                num2 = 1;
            }
            if (num == null) {
                long bufferedPosition = getBufferedPosition();
                long duration = getDuration();
                long jMax = j14 < currentPosition ? j14 : Math.max(j14, bufferedPosition);
                j11 = jMax;
                i11 = duration == C.TIME_UNSET ? 0 : (int) ((100 * jMax) / duration);
                j12 = jMax - j14;
                j13 = duration;
            } else {
                i11 = 0;
                j11 = 0;
                j12 = 0;
                j13 = -9223372036854775807L;
            }
            PlayerInfo playerInfoCopyWithSessionPositionInfo = this.controllerInfo.playerInfo.copyWithSessionPositionInfo(createSessionPositionInfo(createPositionInfo(i12, !timeline.isEmpty() ? timeline.getWindow(i12, new Timeline.Window()).mediaItem : null, j14, false), false, j13, j11, i11, j12));
            if (playerInfoCopyWithSessionPositionInfo.playbackState != 1) {
                playerInfoCopyWithSessionPositionInfo = playerInfoCopyWithSessionPositionInfo.copyWithPlaybackState(2, null);
            }
            PlayerInfo playerInfo = playerInfoCopyWithSessionPositionInfo;
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfo, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), num2, num);
        }
    }

    private void updateControllerInfo(boolean z, LegacyPlayerInfo legacyPlayerInfo, boolean z5, final ControllerInfo controllerInfo, Integer num, Integer num2) {
        LegacyPlayerInfo legacyPlayerInfo2 = this.legacyPlayerInfo;
        ControllerInfo controllerInfo2 = this.controllerInfo;
        if (legacyPlayerInfo2 != legacyPlayerInfo) {
            this.legacyPlayerInfo = new LegacyPlayerInfo(legacyPlayerInfo);
        }
        if (z5) {
            this.pendingLegacyPlayerInfo = this.legacyPlayerInfo;
        }
        this.controllerInfo = controllerInfo;
        if (z) {
            getInstance().notifyAccepted();
            if (controllerInfo2.mediaButtonPreferences.equals(controllerInfo.mediaButtonPreferences)) {
                return;
            }
            getInstance().applicationHandler.post(new g(this, controllerInfo, 2));
            return;
        }
        if (!controllerInfo2.playerInfo.timeline.equals(controllerInfo.playerInfo.timeline)) {
            final int i10 = 4;
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i10) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!Objects.equals(legacyPlayerInfo2.queueTitle, legacyPlayerInfo.queueTitle)) {
            final int i11 = 5;
            this.listeners.queueEvent(15, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i11) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (num != null) {
            this.listeners.queueEvent(11, new j(controllerInfo2, controllerInfo, num, 4));
        }
        if (num2 != null) {
            this.listeners.queueEvent(1, new k(controllerInfo, num2, 10));
        }
        if (!MediaUtils.areEqualError(legacyPlayerInfo2.playbackStateCompat, legacyPlayerInfo.playbackStateCompat)) {
            PlaybackException playbackExceptionConvertToPlaybackException = LegacyConversions.convertToPlaybackException(legacyPlayerInfo.playbackStateCompat, this.context);
            this.listeners.queueEvent(10, new n0(2, playbackExceptionConvertToPlaybackException));
            if (playbackExceptionConvertToPlaybackException != null) {
                this.listeners.queueEvent(10, new n0(3, playbackExceptionConvertToPlaybackException));
            }
        }
        if (legacyPlayerInfo2.mediaMetadataCompat != legacyPlayerInfo.mediaMetadataCompat) {
            this.listeners.queueEvent(14, new f1(this));
        }
        if (controllerInfo2.playerInfo.playbackState != controllerInfo.playerInfo.playbackState) {
            final int i12 = 6;
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i12) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (controllerInfo2.playerInfo.playWhenReady != controllerInfo.playerInfo.playWhenReady) {
            final int i13 = 7;
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i13) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (controllerInfo2.playerInfo.isPlaying != controllerInfo.playerInfo.isPlaying) {
            final int i14 = 8;
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i14) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!controllerInfo2.playerInfo.playbackParameters.equals(controllerInfo.playerInfo.playbackParameters)) {
            final int i15 = 9;
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i15) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (controllerInfo2.playerInfo.repeatMode != controllerInfo.playerInfo.repeatMode) {
            final int i16 = 10;
            this.listeners.queueEvent(8, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i16) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (controllerInfo2.playerInfo.shuffleModeEnabled != controllerInfo.playerInfo.shuffleModeEnabled) {
            final int i17 = 11;
            this.listeners.queueEvent(9, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i17) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!controllerInfo2.playerInfo.audioAttributes.equals(controllerInfo.playerInfo.audioAttributes)) {
            final int i18 = 12;
            this.listeners.queueEvent(20, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i18) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (controllerInfo2.playerInfo.audioSessionId != controllerInfo.playerInfo.audioSessionId) {
            final int i19 = 0;
            this.listeners.queueEvent(21, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i19) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!controllerInfo2.playerInfo.deviceInfo.equals(controllerInfo.playerInfo.deviceInfo)) {
            final int i20 = 1;
            this.listeners.queueEvent(29, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i20) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        PlayerInfo playerInfo = controllerInfo2.playerInfo;
        int i21 = playerInfo.deviceVolume;
        PlayerInfo playerInfo2 = controllerInfo.playerInfo;
        if (i21 != playerInfo2.deviceVolume || playerInfo.deviceMuted != playerInfo2.deviceMuted) {
            final int i22 = 2;
            this.listeners.queueEvent(30, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i22) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!controllerInfo2.availablePlayerCommands.equals(controllerInfo.availablePlayerCommands)) {
            final int i23 = 3;
            this.listeners.queueEvent(13, new ListenerSet.Event() { // from class: androidx.media3.session.c1
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i23) {
                        case 0:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$22(controllerInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$23(controllerInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$24(controllerInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$25(controllerInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$8(controllerInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$9(controllerInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$15(controllerInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$16(controllerInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$17(controllerInfo, (Player.Listener) obj);
                            break;
                        case 9:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$18(controllerInfo, (Player.Listener) obj);
                            break;
                        case 10:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$19(controllerInfo, (Player.Listener) obj);
                            break;
                        case 11:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$20(controllerInfo, (Player.Listener) obj);
                            break;
                        default:
                            MediaControllerImplLegacy.lambda$updateControllerInfo$21(controllerInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!controllerInfo2.availableSessionCommands.equals(controllerInfo.availableSessionCommands)) {
            getInstance().notifyControllerListener(new d1(this, controllerInfo, 0));
        }
        if (!controllerInfo2.mediaButtonPreferences.equals(controllerInfo.mediaButtonPreferences)) {
            getInstance().notifyControllerListener(new d1(this, controllerInfo, 1));
        }
        if (controllerInfo.sessionError != null) {
            getInstance().notifyControllerListener(new d1(this, controllerInfo, 2));
        }
        this.listeners.flushEvents();
    }

    private void updateStateMaskedControllerInfo(ControllerInfo controllerInfo, Integer num, Integer num2) {
        updateControllerInfo(false, this.legacyPlayerInfo, false, controllerInfo, num, num2);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addListener(Player.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(MediaItem mediaItem) {
        addMediaItems(Integer.MAX_VALUE, Collections.singletonList(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(List<MediaItem> list) {
        addMediaItems(Integer.MAX_VALUE, list);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface() {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support clearing SurfaceHolder");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support clearing SurfaceView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support clearing TextureView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void connect() {
        if (this.token.getType() != 0) {
            connectToService();
            return;
        }
        Object binder = this.token.getBinder();
        binder.getClass();
        connectToSession((MediaSessionCompat.Token) binder);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void decreaseDeviceVolume() {
        decreaseDeviceVolume(1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public AudioAttributes getAudioAttributes() {
        return this.controllerInfo.playerInfo.audioAttributes;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getAudioSessionId() {
        return this.controllerInfo.playerInfo.audioSessionId;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Player.Commands getAvailableCommands() {
        return this.controllerInfo.availablePlayerCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionCommands getAvailableSessionCommands() {
        return this.controllerInfo.availableSessionCommands;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public IMediaController getBinder() {
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaBrowserCompat getBrowserCompat() {
        return this.browserCompat;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getBufferedPercentage() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPercentage;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getBufferedPosition() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.bufferedPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.commandButtonsForMediaItems;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public SessionToken getConnectedToken() {
        if (this.connected) {
            return this.token;
        }
        return null;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getConnectionHints() {
        return this.connectionHints;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentBufferedPosition() {
        return getBufferedPosition();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentDuration() {
        return getDuration();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getContentPosition() {
        return getCurrentPosition();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Context getContext() {
        return this.context;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdGroupIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentAdIndexInAdGroup() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public CueGroup getCurrentCues() {
        Log.w(TAG, "Session doesn't support getting Cue");
        return CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentLiveOffset() {
        return C.TIME_UNSET;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentMediaItemIndex() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.positionInfo.mediaItemIndex;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getCurrentPeriodIndex() {
        return getCurrentMediaItemIndex();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getCurrentPosition() {
        long updatedCurrentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(this.controllerInfo.playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.currentPositionMs = updatedCurrentPositionMs;
        return updatedCurrentPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Timeline getCurrentTimeline() {
        return this.controllerInfo.playerInfo.timeline;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Tracks getCurrentTracks() {
        return Tracks.EMPTY;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getCustomLayout() {
        return this.controllerInfo.mediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public DeviceInfo getDeviceInfo() {
        return this.controllerInfo.playerInfo.deviceInfo;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getDeviceVolume() {
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        if (playerInfo.deviceInfo.playbackType == 1) {
            return playerInfo.deviceVolume;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat != null) {
            return LegacyConversions.convertToDeviceVolume(mediaControllerCompat.getPlaybackInfo());
        }
        return 0;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.durationMs;
    }

    public MediaController getInstance() {
        return this.instance;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getMaxSeekToPreviousPosition() {
        return this.controllerInfo.playerInfo.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.collect.h1 getMediaButtonPreferences() {
        return this.controllerInfo.mediaButtonPreferences;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getMediaMetadata() {
        MediaItem currentMediaItem = this.controllerInfo.playerInfo.getCurrentMediaItem();
        return currentMediaItem == null ? MediaMetadata.EMPTY : currentMediaItem.mediaMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getNextMediaItemIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getPlayWhenReady() {
        return this.controllerInfo.playerInfo.playWhenReady;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackParameters getPlaybackParameters() {
        return this.controllerInfo.playerInfo.playbackParameters;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackState() {
        return this.controllerInfo.playerInfo.playbackState;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPlaybackSuppressionReason() {
        return 0;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PlaybackException getPlayerError() {
        return this.controllerInfo.playerInfo.playerError;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public MediaMetadata getPlaylistMetadata() {
        return this.controllerInfo.playerInfo.playlistMetadata;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getPreviousMediaItemIndex() {
        return -1;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public int getRepeatMode() {
        return this.controllerInfo.playerInfo.repeatMode;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekBackIncrement() {
        return this.controllerInfo.playerInfo.seekBackIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getSeekForwardIncrement() {
        return this.controllerInfo.playerInfo.seekForwardIncrementMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public PendingIntent getSessionActivity() {
        return this.controllerCompat.getSessionActivity();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Bundle getSessionExtras() {
        return this.controllerInfo.sessionExtras;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean getShuffleModeEnabled() {
        return this.controllerInfo.playerInfo.shuffleModeEnabled;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public Size getSurfaceSize() {
        Log.w(TAG, "Session doesn't support getting VideoSurfaceSize");
        return Size.UNKNOWN;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public long getTotalBufferedDuration() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.totalBufferedDurationMs;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public TrackSelectionParameters getTrackSelectionParameters() {
        return TrackSelectionParameters.DEFAULT;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public VideoSize getVideoSize() {
        Log.w(TAG, "Session doesn't support getting VideoSize");
        return VideoSize.UNKNOWN;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public float getVolume() {
        return 1.0f;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasNextMediaItem() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean hasPreviousMediaItem() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void increaseDeviceVolume() {
        increaseDeviceVolume(1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isConnected() {
        return this.connected;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isDeviceMuted() {
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        if (playerInfo.deviceInfo.playbackType == 1) {
            return playerInfo.deviceMuted;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        return mediaControllerCompat != null && LegacyConversions.convertToIsDeviceMuted(mediaControllerCompat.getPlaybackInfo());
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isLoading() {
        return false;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlaying() {
        return this.controllerInfo.playerInfo.isPlaying;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public boolean isPlayingAd() {
        return this.controllerInfo.playerInfo.sessionPositionInfo.isPlayingAd;
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItem(int i10, int i11) {
        moveMediaItems(i10, i10 + 1, i11);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void moveMediaItems(int i10, int i11, int i12) {
        ac.b.j(i10 >= 0 && i10 <= i11 && i12 >= 0);
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        int windowCount = queueTimeline.getWindowCount();
        int iMin = Math.min(i11, windowCount);
        int i13 = iMin - i10;
        int i14 = windowCount - i13;
        int i15 = i14 - 1;
        int iMin2 = Math.min(i12, i14);
        if (i10 >= windowCount || i10 == iMin || i10 == iMin2) {
            return;
        }
        int iCalculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i10, iMin);
        if (iCalculateCurrentItemIndexAfterRemoveItems == -1) {
            iCalculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i10, 0, i15);
            Log.w(TAG, "Currently playing item will be removed and added back to mimic move. Assumes item at " + iCalculateCurrentItemIndexAfterRemoveItems + " would be the new current item");
        }
        PlayerInfo playerInfoCopyWithTimelineAndMediaItemIndex = this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithMovedMediaItems(i10, iMin, iMin2), calculateCurrentItemIndexAfterAddItems(iCalculateCurrentItemIndexAfterRemoveItems, iMin2, i13), 0);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithTimelineAndMediaItemIndex, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            ArrayList arrayList = new ArrayList();
            for (int i16 = 0; i16 < i13; i16++) {
                arrayList.add(this.legacyPlayerInfo.queue.get(i10));
                this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i10).getDescription());
            }
            for (int i17 = 0; i17 < arrayList.size(); i17++) {
                this.controllerCompat.addQueueItem(((MediaSessionCompat.QueueItem) arrayList.get(i17)).getDescription(), i17 + iMin2);
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void mute() {
        Log.w(TAG, "Session doesn't support muting the player");
    }

    public void onConnected() {
        if (this.released || this.connected) {
            return;
        }
        this.connected = true;
        handleNewLegacyParameters(true, new LegacyPlayerInfo(this.controllerCompat.getPlaybackInfo(), convertToSafePlaybackStateCompat(this.controllerCompat.getPlaybackState()), this.controllerCompat.getMetadata(), convertToNonNullQueueItemList(this.controllerCompat.getQueue()), this.controllerCompat.getQueueTitle(), this.controllerCompat.getRepeatMode(), this.controllerCompat.getShuffleMode(), this.controllerCompat.getExtras()));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void pause() {
        setPlayWhenReady(false);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void play() {
        setPlayWhenReady(true);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void prepare() {
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        if (playerInfo.playbackState != 1) {
            return;
        }
        PlayerInfo playerInfoCopyWithPlaybackState = playerInfo.copyWithPlaybackState(playerInfo.timeline.isEmpty() ? 4 : 2, null);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithPlaybackState, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (hasMedia()) {
            initializeLegacyPlaylist();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        MediaBrowserCompat mediaBrowserCompat = this.browserCompat;
        if (mediaBrowserCompat != null) {
            mediaBrowserCompat.disconnect();
            this.browserCompat = null;
        }
        MediaControllerCompat mediaControllerCompat = this.controllerCompat;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.controllerCompatCallback);
            this.controllerCompatCallback.release();
            this.controllerCompat = null;
        }
        this.connected = false;
        this.listeners.release();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeListener(Player.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItem(int i10) {
        removeMediaItems(i10, i10 + 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void removeMediaItems(int i10, int i11) {
        ac.b.j(i10 >= 0 && i11 >= i10);
        int windowCount = getCurrentTimeline().getWindowCount();
        int iMin = Math.min(i11, windowCount);
        if (i10 >= windowCount || i10 == iMin) {
            return;
        }
        QueueTimeline queueTimelineCopyWithRemovedMediaItems = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).copyWithRemovedMediaItems(i10, iMin);
        int iCalculateCurrentItemIndexAfterRemoveItems = calculateCurrentItemIndexAfterRemoveItems(getCurrentMediaItemIndex(), i10, iMin);
        if (iCalculateCurrentItemIndexAfterRemoveItems == -1) {
            iCalculateCurrentItemIndexAfterRemoveItems = Util.constrainValue(i10, 0, queueTimelineCopyWithRemovedMediaItems.getWindowCount() - 1);
            Log.w(TAG, "Currently playing item is removed. Assumes item at " + iCalculateCurrentItemIndexAfterRemoveItems + " is the new current item");
        }
        PlayerInfo playerInfoCopyWithTimelineAndMediaItemIndex = this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimelineCopyWithRemovedMediaItems, iCalculateCurrentItemIndexAfterRemoveItems, 0);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithTimelineAndMediaItemIndex, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            while (i10 < iMin && i10 < this.legacyPlayerInfo.queue.size()) {
                this.controllerCompat.removeQueueItem(this.legacyPlayerInfo.queue.get(i10).getDescription());
                i10++;
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItem(int i10, MediaItem mediaItem) {
        replaceMediaItems(i10, i10 + 1, com.google.common.collect.h1.s(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void replaceMediaItems(int i10, int i11, List<MediaItem> list) {
        ac.b.j(i10 >= 0 && i10 <= i11);
        int windowCount = ((QueueTimeline) this.controllerInfo.playerInfo.timeline).getWindowCount();
        if (i10 > windowCount) {
            return;
        }
        int iMin = Math.min(i11, windowCount);
        addMediaItems(iMin, list);
        removeMediaItems(i10, iMin);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekBack() {
        this.controllerCompat.getTransportControls().rewind();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekForward() {
        this.controllerCompat.getTransportControls().fastForward();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(long j10) {
        seekToInternal(getCurrentMediaItemIndex(), j10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition() {
        seekToInternal(getCurrentMediaItemIndex(), 0L);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNext() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToNextMediaItem() {
        this.controllerCompat.getTransportControls().skipToNext();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPrevious() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToPreviousMediaItem() {
        this.controllerCompat.getTransportControls().skipToPrevious();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        if (this.controllerCompat == null) {
            return xc.d.s0(new SessionResult(-100));
        }
        if (bundle.isEmpty()) {
            bundle = sessionCommand.customExtras;
        } else if (!sessionCommand.customExtras.isEmpty()) {
            Bundle bundle2 = new Bundle(sessionCommand.customExtras);
            bundle2.putAll(bundle);
            bundle = bundle2;
        }
        this.controllerCompat.getTransportControls().sendCustomAction(sessionCommand.customAction, bundle);
        return xc.d.s0(new SessionResult(0));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        Log.w(TAG, "Legacy session doesn't support setting audio attributes remotely");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceMuted(boolean z) {
        setDeviceMuted(z, 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    @Deprecated
    public void setDeviceVolume(int i10) {
        setDeviceVolume(i10, 1);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem) {
        setMediaItem(mediaItem, C.TIME_UNSET);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, 0, C.TIME_UNSET);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlayWhenReady(boolean z) {
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        if (playerInfo.playWhenReady == z) {
            return;
        }
        this.currentPositionMs = MediaUtils.getUpdatedCurrentPositionMs(playerInfo, this.currentPositionMs, this.lastSetPlayWhenReadyCalledTimeMs, getInstance().getTimeDiffMs());
        this.lastSetPlayWhenReadyCalledTimeMs = SystemClock.elapsedRealtime();
        PlayerInfo playerInfoCopyWithPlayWhenReady = this.controllerInfo.playerInfo.copyWithPlayWhenReady(z, 1, 0);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithPlayWhenReady, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (isPrepared() && hasMedia()) {
            if (z) {
                this.controllerCompat.getTransportControls().play();
            } else {
                this.controllerCompat.getTransportControls().pause();
            }
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (!playbackParameters.equals(getPlaybackParameters())) {
            PlayerInfo playerInfoCopyWithPlaybackParameters = this.controllerInfo.playerInfo.copyWithPlaybackParameters(playbackParameters);
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithPlaybackParameters, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(playbackParameters.speed);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaybackSpeed(float f10) {
        if (f10 != getPlaybackParameters().speed) {
            PlayerInfo playerInfoCopyWithPlaybackParameters = this.controllerInfo.playerInfo.copyWithPlaybackParameters(new PlaybackParameters(f10));
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithPlaybackParameters, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setPlaybackSpeed(f10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        Log.w(TAG, "Session doesn't support setting playlist metadata");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> setRating(String str, Rating rating) {
        if (str.equals(this.legacyPlayerInfo.mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID))) {
            this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        }
        return xc.d.s0(new SessionResult(0));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setRepeatMode(int i10) {
        if (i10 != getRepeatMode()) {
            PlayerInfo playerInfoCopyWithRepeatMode = this.controllerInfo.playerInfo.copyWithRepeatMode(i10);
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithRepeatMode, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i10));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setShuffleModeEnabled(boolean z) {
        if (z != getShuffleModeEnabled()) {
            PlayerInfo playerInfoCopyWithShuffleModeEnabled = this.controllerInfo.playerInfo.copyWithShuffleModeEnabled(z);
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithShuffleModeEnabled, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.getTransportControls().setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support setting Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        Log.w(TAG, "Session doesn't support setting SurfaceHolder");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        Log.w(TAG, "Session doesn't support setting SurfaceView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVideoTextureView(TextureView textureView) {
        Log.w(TAG, "Session doesn't support setting TextureView");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setVolume(float f10) {
        Log.w(TAG, "Session doesn't support setting player volume");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void stop() {
        PlayerInfo playerInfo = this.controllerInfo.playerInfo;
        if (playerInfo.playbackState == 1) {
            return;
        }
        SessionPositionInfo sessionPositionInfo = playerInfo.sessionPositionInfo;
        Player.PositionInfo positionInfo = sessionPositionInfo.positionInfo;
        long j10 = sessionPositionInfo.durationMs;
        long j11 = positionInfo.positionMs;
        PlayerInfo playerInfoCopyWithSessionPositionInfo = playerInfo.copyWithSessionPositionInfo(createSessionPositionInfo(positionInfo, false, j10, j11, MediaUtils.calculateBufferedPercentage(j11, j10), 0L));
        PlayerInfo playerInfo2 = this.controllerInfo.playerInfo;
        if (playerInfo2.playbackState != 1) {
            playerInfoCopyWithSessionPositionInfo = playerInfoCopyWithSessionPositionInfo.copyWithPlaybackState(1, playerInfo2.playerError);
        }
        PlayerInfo playerInfo3 = playerInfoCopyWithSessionPositionInfo;
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfo3, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        this.controllerCompat.getTransportControls().stop();
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void unmute() {
        Log.w(TAG, "Session doesn't support unmuting the player");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItem(int i10, MediaItem mediaItem) {
        addMediaItems(i10, Collections.singletonList(mediaItem));
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void addMediaItems(int i10, List<MediaItem> list) {
        ac.b.j(i10 >= 0);
        if (list.isEmpty()) {
            return;
        }
        QueueTimeline queueTimeline = (QueueTimeline) this.controllerInfo.playerInfo.timeline;
        if (queueTimeline.isEmpty()) {
            setMediaItems(list);
            return;
        }
        int iMin = Math.min(i10, getCurrentTimeline().getWindowCount());
        PlayerInfo playerInfoCopyWithTimelineAndMediaItemIndex = this.controllerInfo.playerInfo.copyWithTimelineAndMediaItemIndex(queueTimeline.copyWithNewMediaItems(iMin, list), calculateCurrentItemIndexAfterAddItems(getCurrentMediaItemIndex(), iMin, list.size()), 0);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithTimelineAndMediaItemIndex, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            addQueueItems(list, iMin);
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void clearVideoSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support clearing Surface");
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void decreaseDeviceVolume(int i10) {
        int deviceVolume = getDeviceVolume() - 1;
        if (deviceVolume >= getDeviceInfo().minVolume) {
            PlayerInfo playerInfoCopyWithDeviceVolume = this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume, isDeviceMuted());
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithDeviceVolume, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(-1, i10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void increaseDeviceVolume(int i10) {
        int deviceVolume = getDeviceVolume();
        int i11 = getDeviceInfo().maxVolume;
        if (i11 == 0 || deviceVolume + 1 <= i11) {
            PlayerInfo playerInfoCopyWithDeviceVolume = this.controllerInfo.playerInfo.copyWithDeviceVolume(deviceVolume + 1, isDeviceMuted());
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithDeviceVolume, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(1, i10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekTo(int i10, long j10) {
        seekToInternal(i10, j10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void seekToDefaultPosition(int i10) {
        seekToInternal(i10, 0L);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceMuted(boolean z, int i10) {
        if (z != isDeviceMuted()) {
            PlayerInfo playerInfoCopyWithDeviceVolume = this.controllerInfo.playerInfo.copyWithDeviceVolume(getDeviceVolume(), z);
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithDeviceVolume, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.adjustVolume(z ? -100 : 100, i10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setDeviceVolume(int i10, int i11) {
        DeviceInfo deviceInfo = getDeviceInfo();
        int i12 = deviceInfo.minVolume;
        int i13 = deviceInfo.maxVolume;
        if (i12 <= i10 && (i13 == 0 || i10 <= i13)) {
            PlayerInfo playerInfoCopyWithDeviceVolume = this.controllerInfo.playerInfo.copyWithDeviceVolume(i10, isDeviceMuted());
            ControllerInfo controllerInfo = this.controllerInfo;
            updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithDeviceVolume, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        }
        this.controllerCompat.setVolumeTo(i10, i11);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem, long j10) {
        setMediaItems(com.google.common.collect.h1.s(mediaItem), 0, j10);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list, boolean z) {
        setMediaItems(list);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItem(MediaItem mediaItem, boolean z) {
        setMediaItem(mediaItem);
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public void setMediaItems(List<MediaItem> list, int i10, long j10) {
        if (list.isEmpty()) {
            clearMediaItems();
            return;
        }
        PlayerInfo playerInfoCopyWithTimelineAndSessionPositionInfo = this.controllerInfo.playerInfo.copyWithTimelineAndSessionPositionInfo(QueueTimeline.DEFAULT.copyWithNewMediaItems(0, list), createSessionPositionInfo(createPositionInfo(i10, list.get(i10), j10 == C.TIME_UNSET ? 0L : j10, false), false, C.TIME_UNSET, 0L, 0, 0L), 0);
        ControllerInfo controllerInfo = this.controllerInfo;
        updateStateMaskedControllerInfo(new ControllerInfo(playerInfoCopyWithTimelineAndSessionPositionInfo, controllerInfo.availableSessionCommands, controllerInfo.availablePlayerCommands, controllerInfo.mediaButtonPreferences, controllerInfo.sessionExtras, null), null, null);
        if (isPrepared()) {
            initializeLegacyPlaylist();
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> setRating(Rating rating) {
        this.controllerCompat.getTransportControls().setRating(LegacyConversions.convertToRatingCompat(rating));
        return xc.d.s0(new SessionResult(0));
    }

    public static class ControllerInfo {
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final com.google.common.collect.h1 mediaButtonPreferences;
        public final PlayerInfo playerInfo;
        public final SessionError sessionError;
        public final Bundle sessionExtras;

        public ControllerInfo() {
            this.playerInfo = PlayerInfo.DEFAULT.copyWithTimeline(QueueTimeline.DEFAULT);
            this.availableSessionCommands = SessionCommands.EMPTY;
            this.availablePlayerCommands = Player.Commands.EMPTY;
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            this.mediaButtonPreferences = o3.f14078o;
            this.sessionExtras = Bundle.EMPTY;
            this.sessionError = null;
        }

        public ControllerInfo(PlayerInfo playerInfo, SessionCommands sessionCommands, Player.Commands commands, com.google.common.collect.h1 h1Var, Bundle bundle, SessionError sessionError) {
            this.playerInfo = playerInfo;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.mediaButtonPreferences = h1Var;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
            this.sessionError = sessionError;
        }
    }

    public static final class LegacyPlayerInfo {
        public final MediaMetadataCompat mediaMetadataCompat;
        public final MediaControllerCompat.PlaybackInfo playbackInfoCompat;
        public final PlaybackStateCompat playbackStateCompat;
        public final List<MediaSessionCompat.QueueItem> queue;
        public final CharSequence queueTitle;
        public final int repeatMode;
        public final Bundle sessionExtras;
        public final int shuffleMode;

        public LegacyPlayerInfo() {
            this.playbackInfoCompat = null;
            this.playbackStateCompat = null;
            this.mediaMetadataCompat = null;
            this.queue = Collections.EMPTY_LIST;
            this.queueTitle = null;
            this.repeatMode = 0;
            this.shuffleMode = 0;
            this.sessionExtras = Bundle.EMPTY;
        }

        public LegacyPlayerInfo copyWithExtraBinderGetters(PlaybackStateCompat playbackStateCompat, int i10, int i11) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, i10, i11, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithMediaMetadataCompat(MediaMetadataCompat mediaMetadataCompat) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackInfoCompat(MediaControllerCompat.PlaybackInfo playbackInfo) {
            return new LegacyPlayerInfo(playbackInfo, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithPlaybackStateCompat(PlaybackStateCompat playbackStateCompat) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueue(List<MediaSessionCompat.QueueItem> list) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, list, this.queueTitle, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithQueueTitle(CharSequence charSequence) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, charSequence, this.repeatMode, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithRepeatMode(int i10) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, i10, this.shuffleMode, this.sessionExtras);
        }

        public LegacyPlayerInfo copyWithSessionExtras(Bundle bundle) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, this.shuffleMode, bundle);
        }

        public LegacyPlayerInfo copyWithShuffleMode(int i10) {
            return new LegacyPlayerInfo(this.playbackInfoCompat, this.playbackStateCompat, this.mediaMetadataCompat, this.queue, this.queueTitle, this.repeatMode, i10, this.sessionExtras);
        }

        public LegacyPlayerInfo(MediaControllerCompat.PlaybackInfo playbackInfo, PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, List<MediaSessionCompat.QueueItem> list, CharSequence charSequence, int i10, int i11, Bundle bundle) {
            this.playbackInfoCompat = playbackInfo;
            this.playbackStateCompat = playbackStateCompat;
            this.mediaMetadataCompat = mediaMetadataCompat;
            list.getClass();
            this.queue = list;
            this.queueTitle = charSequence;
            this.repeatMode = i10;
            this.shuffleMode = i11;
            this.sessionExtras = bundle == null ? Bundle.EMPTY : bundle;
        }

        public LegacyPlayerInfo(LegacyPlayerInfo legacyPlayerInfo) {
            this.playbackInfoCompat = legacyPlayerInfo.playbackInfoCompat;
            this.playbackStateCompat = legacyPlayerInfo.playbackStateCompat;
            this.mediaMetadataCompat = legacyPlayerInfo.mediaMetadataCompat;
            this.queue = legacyPlayerInfo.queue;
            this.queueTitle = legacyPlayerInfo.queueTitle;
            this.repeatMode = legacyPlayerInfo.repeatMode;
            this.shuffleMode = legacyPlayerInfo.shuffleMode;
            this.sessionExtras = legacyPlayerInfo.sessionExtras;
        }
    }

    @Override // androidx.media3.session.MediaController.MediaControllerImpl
    public com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, MediaController.ProgressListener progressListener) {
        return sendCustomCommand(sessionCommand, bundle);
    }
}
