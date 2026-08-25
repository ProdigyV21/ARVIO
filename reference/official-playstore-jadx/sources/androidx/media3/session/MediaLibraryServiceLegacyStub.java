package androidx.media3.session;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
class MediaLibraryServiceLegacyStub extends MediaSessionServiceLegacyStub {
    private static final String TAG = "MLSLegacyStub";
    private final MediaSession.ControllerCb browserLegacyCbForBroadcast;
    private final MediaLibrarySessionImpl librarySessionImpl;

    public final class BrowserLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        private final Object lock = new Object();
        private final List<SearchRequest> searchRequests = new ArrayList();

        public BrowserLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.remoteUserInfo = remoteUserInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSearchResultChanged$0(List list) {
            int i10;
            int i11;
            int i12;
            int i13;
            for (int i14 = 0; i14 < list.size(); i14++) {
                SearchRequest searchRequest = (SearchRequest) list.get(i14);
                Bundle bundle = searchRequest.extras;
                if (bundle != null) {
                    try {
                        bundle.setClassLoader(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext().getClassLoader());
                        i10 = searchRequest.extras.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                        i11 = searchRequest.extras.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                    } catch (BadParcelableException unused) {
                        searchRequest.result.sendResult(null);
                        return;
                    }
                } else {
                    i10 = 0;
                    i11 = Integer.MAX_VALUE;
                }
                if (i10 < 0 || i11 < 1) {
                    i12 = 0;
                    i13 = Integer.MAX_VALUE;
                } else {
                    i12 = i10;
                    i13 = i11;
                }
                MediaLibraryServiceLegacyStub.sendLibraryResultWithMediaItemsWhenReady(searchRequest.result, Util.transformFutureAsync(MediaLibraryServiceLegacyStub.this.librarySessionImpl.onGetSearchResultOnHandler(searchRequest.controller, searchRequest.query, i12, i13, LegacyConversions.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext(), searchRequest.extras)), MediaLibraryServiceLegacyStub.this.createMediaItemsToBrowserItemsAsyncFunction()));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void registerSearchRequest(MediaSession.ControllerInfo controllerInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            synchronized (this.lock) {
                this.searchRequests.add(new SearchRequest(controllerInfo, controllerInfo.getRemoteUserInfo(), str, bundle, result));
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof BrowserLegacyCb) {
                return Objects.equals(this.remoteUserInfo, ((BrowserLegacyCb) obj).remoteUserInfo);
            }
            return false;
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
        public void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle = libraryParams != null ? libraryParams.extras : null;
            MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub = MediaLibraryServiceLegacyStub.this;
            MediaSessionManager.RemoteUserInfo remoteUserInfo = this.remoteUserInfo;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            mediaLibraryServiceLegacyStub.notifyChildrenChanged(remoteUserInfo, str, bundle);
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
        public void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            ArrayList arrayList = new ArrayList();
            synchronized (this.lock) {
                try {
                    for (int size = this.searchRequests.size() - 1; size >= 0; size--) {
                        SearchRequest searchRequest = this.searchRequests.get(size);
                        if (Objects.equals(this.remoteUserInfo, searchRequest.remoteUserInfo) && searchRequest.query.equals(str)) {
                            arrayList.add(searchRequest);
                            this.searchRequests.remove(size);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return;
                    }
                    Util.postOrRun(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getApplicationHandler(), new g(this, arrayList, 6));
                } catch (Throwable th) {
                    throw th;
                }
            }
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

    public final class BrowserLegacyCbForBroadcast implements MediaSession.ControllerCb {
        private BrowserLegacyCbForBroadcast() {
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
        public void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle;
            if (libraryParams == null || (bundle = libraryParams.extras) == null) {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str);
            } else {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str, (Bundle) Util.castNonNull(bundle));
            }
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
        public void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
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

    public static class ProgressReporter implements MediaSession.ProgressReporter {
        private com.google.common.util.concurrent.d1<SessionResult> future;
        private final MediaBrowserServiceCompat.Result<Bundle> result;
        private final MediaLibrarySessionImpl session;

        public ProgressReporter(MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaBrowserServiceCompat.Result<Bundle> result) {
            this.session = mediaLibrarySessionImpl;
            this.result = result;
        }

        @Override // androidx.media3.session.MediaSession.ProgressReporter
        public void sendProgressUpdate(Bundle bundle) {
            com.google.common.util.concurrent.d1<SessionResult> d1Var = this.future;
            if ((d1Var == null || !d1Var.isDone()) && !this.session.isReleased()) {
                this.result.sendProgressUpdate(bundle);
            }
        }

        public void setFuture(com.google.common.util.concurrent.d1<SessionResult> d1Var) {
            this.future = d1Var;
        }
    }

    public static class SearchRequest {
        public final MediaSession.ControllerInfo controller;
        public final Bundle extras;
        public final String query;
        public final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        public final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result;

        public SearchRequest(MediaSession.ControllerInfo controllerInfo, MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            this.controller = controllerInfo;
            this.remoteUserInfo = remoteUserInfo;
            this.query = str;
            this.extras = bundle;
            this.result = result;
        }
    }

    public MediaLibraryServiceLegacyStub(MediaLibrarySessionImpl mediaLibrarySessionImpl) {
        super(mediaLibrarySessionImpl);
        this.librarySessionImpl = mediaLibrarySessionImpl;
        this.browserLegacyCbForBroadcast = new BrowserLegacyCbForBroadcast();
    }

    private static <T> void cancelAllFutures(List<com.google.common.util.concurrent.d1<T>> list) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            if (list.get(i10) != null) {
                list.get(i10).cancel(false);
            }
        }
    }

    private com.google.common.util.concurrent.w<LibraryResult<MediaItem>, MediaBrowserCompat.MediaItem> createMediaItemToBrowserItemAsyncFunction() {
        return new t1(this, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.google.common.util.concurrent.w<LibraryResult<com.google.common.collect.h1>, List<MediaBrowserCompat.MediaItem>> createMediaItemsToBrowserItemsAsyncFunction() {
        return new t1(this, 1);
    }

    private MediaSession.ControllerInfo getCurrentController() {
        return getConnectedControllersManager().getController(getCurrentBrowserInfo());
    }

    private void handleBitmapFuturesAllCompletedAndSetOutputFuture(List<com.google.common.util.concurrent.d1<Bitmap>> list, List<MediaItem> list2, com.google.common.util.concurrent.q1 q1Var) {
        Bitmap bitmap;
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            com.google.common.util.concurrent.d1<Bitmap> d1Var = list.get(i10);
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
            arrayList.add(LegacyConversions.convertToBrowserItem(list2.get(i10), bitmap));
        }
        q1Var.set(arrayList);
    }

    private static <T> void ignoreFuture(Future<T> future) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$13(com.google.common.util.concurrent.q1 q1Var, com.google.common.util.concurrent.d1 d1Var) {
        if (q1Var.isCancelled()) {
            d1Var.cancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$14(com.google.common.util.concurrent.d1 d1Var, com.google.common.util.concurrent.q1 q1Var, MediaItem mediaItem) {
        Bitmap bitmap;
        try {
            bitmap = (Bitmap) xc.d.j0(d1Var);
        } catch (CancellationException | ExecutionException e5) {
            Log.d(TAG, "failed to get bitmap", e5);
            bitmap = null;
        }
        q1Var.set(LegacyConversions.convertToBrowserItem(mediaItem, bitmap));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public com.google.common.util.concurrent.d1 lambda$createMediaItemToBrowserItemAsyncFunction$15(LibraryResult libraryResult) throws Exception {
        V v2;
        ac.b.p(libraryResult, "LibraryResult must not be null");
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        if (libraryResult.resultCode != 0 || (v2 = libraryResult.value) == 0) {
            q1Var.set(null);
            return q1Var;
        }
        MediaItem mediaItem = (MediaItem) v2;
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        if (mediaMetadata.artworkData == null) {
            q1Var.set(LegacyConversions.convertToBrowserItem(mediaItem, null));
            return q1Var;
        }
        com.google.common.util.concurrent.d1<Bitmap> d1VarDecodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
        g gVar = new g(q1Var, d1VarDecodeBitmap, 5);
        com.google.common.util.concurrent.s0 s0Var = com.google.common.util.concurrent.s0.f14233i;
        q1Var.addListener(gVar, s0Var);
        d1VarDecodeBitmap.addListener(new s1(d1VarDecodeBitmap, q1Var, mediaItem, 1), s0Var);
        return q1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createMediaItemsToBrowserItemsAsyncFunction$10(com.google.common.util.concurrent.q1 q1Var, List list) {
        if (q1Var.isCancelled()) {
            cancelAllFutures(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createMediaItemsToBrowserItemsAsyncFunction$11(AtomicInteger atomicInteger, com.google.common.collect.h1 h1Var, List list, com.google.common.util.concurrent.q1 q1Var) {
        if (atomicInteger.incrementAndGet() == h1Var.size()) {
            handleBitmapFuturesAllCompletedAndSetOutputFuture(list, h1Var, q1Var);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public com.google.common.util.concurrent.d1 lambda$createMediaItemsToBrowserItemsAsyncFunction$12(LibraryResult libraryResult) throws Exception {
        V v2;
        ac.b.p(libraryResult, "LibraryResult must not be null");
        com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        if (libraryResult.resultCode != 0 || (v2 = libraryResult.value) == 0) {
            q1Var.set(null);
            return q1Var;
        }
        com.google.common.collect.h1 h1Var = (com.google.common.collect.h1) v2;
        if (h1Var.isEmpty()) {
            q1Var.set(new ArrayList());
            return q1Var;
        }
        ArrayList arrayList = new ArrayList();
        g gVar = new g(q1Var, arrayList, 4);
        com.google.common.util.concurrent.s0 s0Var = com.google.common.util.concurrent.s0.f14233i;
        q1Var.addListener(gVar, s0Var);
        a aVar = new a(this, new AtomicInteger(0), h1Var, arrayList, q1Var, 1);
        for (int i10 = 0; i10 < h1Var.size(); i10++) {
            MediaMetadata mediaMetadata = ((MediaItem) h1Var.get(i10)).mediaMetadata;
            if (mediaMetadata.artworkData == null) {
                arrayList.add(null);
                aVar.run();
            } else {
                com.google.common.util.concurrent.d1<Bitmap> d1VarDecodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                arrayList.add(d1VarDecodeBitmap);
                d1VarDecodeBitmap.addListener(aVar, s0Var);
            }
        }
        return q1Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCustomAction$6(String str, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle) {
        SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, sessionCommand)) {
            result.sendError(null);
            return;
        }
        ProgressReporter progressReporter = new ProgressReporter(this.librarySessionImpl, result);
        com.google.common.util.concurrent.d1<SessionResult> d1VarOnCustomCommandOnHandler = this.librarySessionImpl.onCustomCommandOnHandler(controllerInfo, progressReporter, sessionCommand, bundle);
        progressReporter.setFuture(d1VarOnCustomCommandOnHandler);
        sendCustomActionResultWhenReady(result, d1VarOnCustomCommandOnHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetRoot$0(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams, ConditionVariable conditionVariable) {
        atomicReference.set(this.librarySessionImpl.onGetLibraryRootOnHandler(controllerInfo, libraryParams));
        conditionVariable.open();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadChildren$3(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle, String str) {
        MediaSession.ControllerInfo controllerInfo2;
        String str2;
        MediaLibraryService.LibraryParams libraryParams;
        MediaLibraryService.LibraryParams libraryParams2 = null;
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            result.sendResult(null);
            return;
        }
        if (bundle != null) {
            bundle.setClassLoader(this.librarySessionImpl.getContext().getClassLoader());
            try {
                int i10 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE);
                int i11 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE);
                MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle);
                if (i10 < 0 || i11 <= 0) {
                    controllerInfo2 = controllerInfo;
                    str2 = str;
                    libraryParams = libraryParamsConvertToLibraryParams;
                } else {
                    try {
                        controllerInfo2 = controllerInfo;
                        try {
                            str2 = str;
                            try {
                                sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str, i10, i11, libraryParamsConvertToLibraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
                                return;
                            } catch (BadParcelableException unused) {
                                libraryParams2 = libraryParamsConvertToLibraryParams;
                                libraryParams = libraryParams2;
                                sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str2, 0, Integer.MAX_VALUE, libraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
                            }
                        } catch (BadParcelableException unused2) {
                            str2 = str;
                        }
                    } catch (BadParcelableException unused3) {
                        controllerInfo2 = controllerInfo;
                        str2 = str;
                    }
                }
            } catch (BadParcelableException unused4) {
                controllerInfo2 = controllerInfo;
                str2 = str;
            }
        } else {
            controllerInfo2 = controllerInfo;
            str2 = str;
            libraryParams = libraryParams2;
        }
        sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo2, str2, 0, Integer.MAX_VALUE, libraryParams), createMediaItemsToBrowserItemsAsyncFunction()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onLoadItem$4(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            sendLibraryResultWithMediaItemWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetItemOnHandler(controllerInfo, str), createMediaItemToBrowserItemAsyncFunction()));
        } else {
            result.sendResult(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onSearch$5(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str, Bundle bundle) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            result.sendResult(null);
            return;
        }
        MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
        controllerCb.getClass();
        ((BrowserLegacyCb) controllerCb).registerSearchRequest(controllerInfo, str, bundle, result);
        ignoreFuture(this.librarySessionImpl.onSearchOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribe$1(MediaSession.ControllerInfo controllerInfo, Bundle bundle, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onSubscribeOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onUnsubscribe$2(MediaSession.ControllerInfo controllerInfo, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onUnsubscribeOnHandler(controllerInfo, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$sendCustomActionResultWhenReady$7(com.google.common.util.concurrent.d1 d1Var, MediaBrowserServiceCompat.Result result) {
        try {
            SessionResult sessionResult = (SessionResult) d1Var.get();
            ac.b.p(sessionResult, "SessionResult must not be null");
            result.sendResult(sessionResult.extras);
        } catch (InterruptedException | CancellationException | ExecutionException e5) {
            Log.w(TAG, "Custom action failed", e5);
            result.sendError(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$sendLibraryResultWithMediaItemWhenReady$8(com.google.common.util.concurrent.d1 d1Var, MediaBrowserServiceCompat.Result result) {
        try {
            result.sendResult((MediaBrowserCompat.MediaItem) d1Var.get());
        } catch (InterruptedException | CancellationException | ExecutionException e5) {
            Log.w(TAG, "Library operation failed", e5);
            result.sendResult(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sendLibraryResultWithMediaItemsWhenReady$9(com.google.common.util.concurrent.d1 d1Var, MediaBrowserServiceCompat.Result result) {
        try {
            List list = (List) d1Var.get();
            result.sendResult(list == null ? null : MediaUtils.truncateListBySize(list, MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES));
        } catch (InterruptedException | CancellationException | ExecutionException e5) {
            Log.w(TAG, "Library operation failed", e5);
            result.sendResult(null);
        }
    }

    private static void sendCustomActionResultWhenReady(MediaBrowserServiceCompat.Result<Bundle> result, com.google.common.util.concurrent.d1<SessionResult> d1Var) {
        d1Var.addListener(new q1(d1Var, result, 1), com.google.common.util.concurrent.s0.f14233i);
    }

    private static void sendLibraryResultWithMediaItemWhenReady(MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result, com.google.common.util.concurrent.d1<MediaBrowserCompat.MediaItem> d1Var) {
        d1Var.addListener(new q1(d1Var, result, 0), com.google.common.util.concurrent.s0.f14233i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendLibraryResultWithMediaItemsWhenReady(MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, com.google.common.util.concurrent.d1<List<MediaBrowserCompat.MediaItem>> d1Var) {
        d1Var.addListener(new q1(d1Var, result, 2), com.google.common.util.concurrent.s0.f14233i);
    }

    @Override // androidx.media3.session.MediaSessionServiceLegacyStub
    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, getMediaSessionManager().isTrustedForMediaControl(remoteUserInfo), new BrowserLegacyCb(remoteUserInfo), bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle), true);
    }

    public MediaSession.ControllerCb getBrowserLegacyCbForBroadcast() {
        return this.browserLegacyCbForBroadcast;
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onCustomAction(String str, Bundle bundle, MediaBrowserServiceCompat.Result<Bundle> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendError(null);
        } else {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new r1(this, str, currentController, result, bundle));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.media3.session.MediaSessionServiceLegacyStub, androidx.media3.session.legacy.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i10, Bundle bundle) {
        MediaSession.ControllerInfo currentController;
        LibraryResult libraryResult;
        if (super.onGetRoot(str, i10, bundle) == null || (currentController = getCurrentController()) == null || !getConnectedControllersManager().isSessionCommandAvailable(currentController, 50000)) {
            return null;
        }
        MediaLibraryService.LibraryParams libraryParamsConvertToLibraryParams = LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle);
        AtomicReference atomicReference = new AtomicReference();
        ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new a(this, atomicReference, currentController, libraryParamsConvertToLibraryParams, conditionVariable, 2));
        try {
            conditionVariable.block();
            libraryResult = (LibraryResult) ((com.google.common.util.concurrent.d1) atomicReference.get()).get();
            ac.b.p(libraryResult, "LibraryResult must not be null");
        } catch (InterruptedException | CancellationException | ExecutionException e5) {
            Log.e(TAG, "Couldn't get a result from onGetLibraryRoot", e5);
            libraryResult = null;
        }
        if (libraryResult == null || libraryResult.resultCode != 0 || libraryResult.value == 0) {
            if (libraryResult == null || libraryResult.resultCode == 0) {
                return MediaUtils.defaultBrowserRoot;
            }
            return null;
        }
        MediaLibraryService.LibraryParams libraryParams = libraryResult.params;
        Bundle bundleConvertToRootHints = libraryParams != null ? LegacyConversions.convertToRootHints(libraryParams) : new Bundle();
        boolean zIsSessionCommandAvailable = getConnectedControllersManager().isSessionCommandAvailable(currentController, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH);
        bundleConvertToRootHints.getClass();
        bundleConvertToRootHints.putBoolean(androidx.media3.session.legacy.MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_SEARCH_SUPPORTED, zIsSessionCommandAvailable);
        com.google.common.collect.h1 commandButtonsForMediaItems = this.librarySessionImpl.getCommandButtonsForMediaItems();
        if (!commandButtonsForMediaItems.isEmpty()) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (int i11 = 0; i11 < commandButtonsForMediaItems.size(); i11++) {
                CommandButton commandButton = (CommandButton) commandButtonsForMediaItems.get(i11);
                SessionCommand sessionCommand = commandButton.sessionCommand;
                if (sessionCommand != null && sessionCommand.commandCode == 0) {
                    arrayList.add(LegacyConversions.convertToBundle(commandButton));
                }
            }
            if (!arrayList.isEmpty()) {
                bundleConvertToRootHints.putParcelableArrayList(androidx.media3.session.legacy.MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ROOT_LIST, arrayList);
            }
        }
        return new MediaBrowserServiceCompat.BrowserRoot(((MediaItem) libraryResult.value).mediaId, bundleConvertToRootHints);
    }

    @Override // androidx.media3.session.MediaSessionServiceLegacyStub, androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, null);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadItem(String str, MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new c(this, currentController, result, str, 2));
        } else {
            Log.w(TAG, "Ignoring empty itemId from " + currentController);
            result.sendResult(null);
        }
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onSearch(String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty query from " + currentController);
            result.sendResult(null);
            return;
        }
        if (currentController.getControllerCb() instanceof BrowserLegacyCb) {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new r1(this, currentController, result, str, bundle));
        }
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onSubscribe(String str, Bundle bundle) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new c(this, currentController, bundle, str, 1));
            return;
        }
        Log.w(TAG, "onSubscribe(): Ignoring empty id from " + currentController);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onUnsubscribe(String str) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new s1(this, currentController, str, 0));
            return;
        }
        Log.w(TAG, "onUnsubscribe(): Ignoring empty id from " + currentController);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, Bundle bundle) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new r1(this, currentController, result, bundle, str));
        } else {
            Log.w(TAG, "onLoadChildren(): Ignoring empty parentId from " + currentController);
            result.sendResult(null);
        }
    }
}
