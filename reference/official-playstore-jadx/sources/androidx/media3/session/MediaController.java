package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
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
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.o3;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: loaded from: classes3.dex */
@x4.f
public class MediaController implements Player {
    protected static final long DEFAULT_PLATFORM_CALLBACK_AGGREGATION_TIMEOUT_MS = 100;
    public static final String KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG = "androidx.media3.session.MediaNotificationManager";
    public static final long RELEASE_UNBIND_TIMEOUT_MS = 30000;
    private static final String TAG = "MediaController";
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaController method is called from a wrong thread. See javadoc of MediaController for details.";
    final Handler applicationHandler;
    final ConnectionCallback connectionCallback;
    private boolean connectionNotified;

    @NotOnlyInitialized
    private final MediaControllerImpl impl;
    final Listener listener;
    private final int maxCommandsForMediaItems;
    private boolean released;
    private long timeDiffMs;
    private final Timeline.Window window;

    public static final class Builder {
        private Looper applicationLooper;
        private androidx.media3.common.util.BitmapLoader bitmapLoader;
        private Bundle connectionHints;
        private final Context context;
        private Listener listener;
        private int maxCommandsForMediaItems;
        private long platformSessionCallbackAggregationTimeoutMs;
        private final SessionToken token;

        public Builder(Context context, SessionToken sessionToken) {
            context.getClass();
            this.context = context;
            sessionToken.getClass();
            this.token = sessionToken;
            this.connectionHints = Bundle.EMPTY;
            this.listener = new Listener() { // from class: androidx.media3.session.MediaController.Builder.1
                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
                    n.a(this, mediaController, sessionCommands);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ com.google.common.util.concurrent.d1 onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
                    return n.b(this, mediaController, sessionCommand, bundle);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
                    n.c(this, mediaController, list);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onDisconnected(MediaController mediaController) {
                    n.d(this, mediaController);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
                    n.e(this, mediaController, sessionError);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
                    n.f(this, mediaController, bundle);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onMediaButtonPreferencesChanged(MediaController mediaController, List list) {
                    n.g(this, mediaController, list);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
                    n.h(this, mediaController, pendingIntent);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ com.google.common.util.concurrent.d1 onSetCustomLayout(MediaController mediaController, List list) {
                    return n.i(this, mediaController, list);
                }
            };
            this.applicationLooper = Util.getCurrentOrMainLooper();
            this.platformSessionCallbackAggregationTimeoutMs = 100L;
        }

        public com.google.common.util.concurrent.d1<MediaController> buildAsync() {
            MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).build());
            }
            Util.postOrRun(new Handler(this.applicationLooper), new m(mediaControllerHolder, new MediaController(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems, this.platformSessionCallbackAggregationTimeoutMs), 0));
            return mediaControllerHolder;
        }

        public Builder experimentalSetPlatformSessionCallbackAggregationTimeoutMs(long j10) {
            this.platformSessionCallbackAggregationTimeoutMs = j10;
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            looper.getClass();
            this.applicationLooper = looper;
            return this;
        }

        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            bitmapLoader.getClass();
            this.bitmapLoader = bitmapLoader;
            return this;
        }

        public Builder setConnectionHints(Bundle bundle) {
            bundle.getClass();
            this.connectionHints = new Bundle(bundle);
            return this;
        }

        public Builder setListener(Listener listener) {
            listener.getClass();
            this.listener = listener;
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i10) {
            ac.b.j(i10 >= 0);
            this.maxCommandsForMediaItems = i10;
            return this;
        }
    }

    public interface ConnectionCallback {
        void onAccepted();

        void onRejected();
    }

    public interface Listener {
        void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands);

        com.google.common.util.concurrent.d1<SessionResult> onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle);

        void onCustomLayoutChanged(MediaController mediaController, List<CommandButton> list);

        void onDisconnected(MediaController mediaController);

        void onError(MediaController mediaController, SessionError sessionError);

        void onExtrasChanged(MediaController mediaController, Bundle bundle);

        void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list);

        void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent);

        com.google.common.util.concurrent.d1<SessionResult> onSetCustomLayout(MediaController mediaController, List<CommandButton> list);
    }

    public interface MediaControllerImpl {
        void addListener(Player.Listener listener);

        void addMediaItem(int i10, MediaItem mediaItem);

        void addMediaItem(MediaItem mediaItem);

        void addMediaItems(int i10, List<MediaItem> list);

        void addMediaItems(List<MediaItem> list);

        void clearMediaItems();

        void clearVideoSurface();

        void clearVideoSurface(Surface surface);

        void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void clearVideoSurfaceView(SurfaceView surfaceView);

        void clearVideoTextureView(TextureView textureView);

        void connect();

        void decreaseDeviceVolume();

        void decreaseDeviceVolume(int i10);

        AudioAttributes getAudioAttributes();

        int getAudioSessionId();

        Player.Commands getAvailableCommands();

        SessionCommands getAvailableSessionCommands();

        IMediaController getBinder();

        MediaBrowserCompat getBrowserCompat();

        int getBufferedPercentage();

        long getBufferedPosition();

        com.google.common.collect.h1 getCommandButtonsForMediaItem(MediaItem mediaItem);

        SessionToken getConnectedToken();

        Bundle getConnectionHints();

        long getContentBufferedPosition();

        long getContentDuration();

        long getContentPosition();

        Context getContext();

        int getCurrentAdGroupIndex();

        int getCurrentAdIndexInAdGroup();

        CueGroup getCurrentCues();

        long getCurrentLiveOffset();

        int getCurrentMediaItemIndex();

        int getCurrentPeriodIndex();

        long getCurrentPosition();

        Timeline getCurrentTimeline();

        Tracks getCurrentTracks();

        com.google.common.collect.h1 getCustomLayout();

        DeviceInfo getDeviceInfo();

        int getDeviceVolume();

        long getDuration();

        long getMaxSeekToPreviousPosition();

        com.google.common.collect.h1 getMediaButtonPreferences();

        MediaMetadata getMediaMetadata();

        int getNextMediaItemIndex();

        boolean getPlayWhenReady();

        PlaybackParameters getPlaybackParameters();

        int getPlaybackState();

        int getPlaybackSuppressionReason();

        PlaybackException getPlayerError();

        MediaMetadata getPlaylistMetadata();

        int getPreviousMediaItemIndex();

        int getRepeatMode();

        long getSeekBackIncrement();

        long getSeekForwardIncrement();

        PendingIntent getSessionActivity();

        Bundle getSessionExtras();

        boolean getShuffleModeEnabled();

        Size getSurfaceSize();

        long getTotalBufferedDuration();

        TrackSelectionParameters getTrackSelectionParameters();

        VideoSize getVideoSize();

        float getVolume();

        boolean hasNextMediaItem();

        boolean hasPreviousMediaItem();

        void increaseDeviceVolume();

        void increaseDeviceVolume(int i10);

        boolean isConnected();

        boolean isDeviceMuted();

        boolean isLoading();

        boolean isPlaying();

        boolean isPlayingAd();

        void moveMediaItem(int i10, int i11);

        void moveMediaItems(int i10, int i11, int i12);

        void mute();

        void pause();

        void play();

        void prepare();

        void release();

        void removeListener(Player.Listener listener);

        void removeMediaItem(int i10);

        void removeMediaItems(int i10, int i11);

        void replaceMediaItem(int i10, MediaItem mediaItem);

        void replaceMediaItems(int i10, int i11, List<MediaItem> list);

        void seekBack();

        void seekForward();

        void seekTo(int i10, long j10);

        void seekTo(long j10);

        void seekToDefaultPosition();

        void seekToDefaultPosition(int i10);

        void seekToNext();

        void seekToNextMediaItem();

        void seekToPrevious();

        void seekToPreviousMediaItem();

        com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle);

        com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, ProgressListener progressListener);

        void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

        void setDeviceMuted(boolean z);

        void setDeviceMuted(boolean z, int i10);

        void setDeviceVolume(int i10);

        void setDeviceVolume(int i10, int i11);

        void setMediaItem(MediaItem mediaItem);

        void setMediaItem(MediaItem mediaItem, long j10);

        void setMediaItem(MediaItem mediaItem, boolean z);

        void setMediaItems(List<MediaItem> list);

        void setMediaItems(List<MediaItem> list, int i10, long j10);

        void setMediaItems(List<MediaItem> list, boolean z);

        void setPlayWhenReady(boolean z);

        void setPlaybackParameters(PlaybackParameters playbackParameters);

        void setPlaybackSpeed(float f10);

        void setPlaylistMetadata(MediaMetadata mediaMetadata);

        com.google.common.util.concurrent.d1<SessionResult> setRating(Rating rating);

        com.google.common.util.concurrent.d1<SessionResult> setRating(String str, Rating rating);

        void setRepeatMode(int i10);

        void setShuffleModeEnabled(boolean z);

        void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters);

        void setVideoSurface(Surface surface);

        void setVideoSurfaceHolder(SurfaceHolder surfaceHolder);

        void setVideoSurfaceView(SurfaceView surfaceView);

        void setVideoTextureView(TextureView textureView);

        void setVolume(float f10);

        void stop();

        void unmute();
    }

    public interface ProgressListener {
        void onProgress(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2);
    }

    public MediaController(Context context, SessionToken sessionToken, Bundle bundle, Listener listener, Looper looper, ConnectionCallback connectionCallback, androidx.media3.common.util.BitmapLoader bitmapLoader, int i10, long j10) {
        ac.b.p(context, "context must not be null");
        ac.b.p(sessionToken, "token must not be null");
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.window = new Timeline.Window();
        this.timeDiffMs = C.TIME_UNSET;
        this.listener = listener;
        this.applicationHandler = new Handler(looper);
        this.connectionCallback = connectionCallback;
        this.maxCommandsForMediaItems = i10;
        MediaControllerImpl mediaControllerImplCreateImpl = createImpl(context, sessionToken, bundle, looper, bitmapLoader, j10);
        this.impl = mediaControllerImplCreateImpl;
        mediaControllerImplCreateImpl.connect();
    }

    private static com.google.common.util.concurrent.d1<SessionResult> createDisconnectedFuture() {
        return xc.d.s0(new SessionResult(-100));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$release$0(Listener listener) {
        listener.onDisconnected(this);
    }

    public static void releaseFuture(Future<? extends MediaController> future) {
        if (future.cancel(false)) {
            return;
        }
        try {
            ((MediaController) xc.d.j0(future)).release();
        } catch (CancellationException | ExecutionException e5) {
            Log.w(TAG, "MediaController future failed (so we couldn't release it)", e5);
        }
    }

    private void verifyApplicationThread() {
        ac.b.t(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }

    @Override // androidx.media3.common.Player
    public final void addListener(Player.Listener listener) {
        ac.b.p(listener, "listener must not be null");
        this.impl.addListener(listener);
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.addMediaItem(mediaItem);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.addMediaItems(list);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        }
    }

    @Override // androidx.media3.common.Player
    public final boolean canAdvertiseSession() {
        return false;
    }

    @Override // androidx.media3.common.Player
    public final void clearMediaItems() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.clearMediaItems();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring clearMediaItems().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.clearVideoSurface();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.clearVideoSurfaceHolder(surfaceHolder);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceHolder().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.clearVideoSurfaceView(surfaceView);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurfaceView().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.clearVideoTextureView(textureView);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoTextureView().");
        }
    }

    public MediaControllerImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j10) {
        if (!sessionToken.isLegacySession()) {
            return new MediaControllerImplBase(context, this, sessionToken, bundle, looper);
        }
        bitmapLoader.getClass();
        return new MediaControllerImplLegacy(context, this, sessionToken, bundle, looper, bitmapLoader, j10);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void decreaseDeviceVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.decreaseDeviceVolume();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        }
    }

    @Override // androidx.media3.common.Player
    public final Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    @Override // androidx.media3.common.Player
    public final AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return !isConnected() ? AudioAttributes.DEFAULT : this.impl.getAudioAttributes();
    }

    @Override // androidx.media3.common.Player
    public final int getAudioSessionId() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getAudioSessionId();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return !isConnected() ? Player.Commands.EMPTY : this.impl.getAvailableCommands();
    }

    public final SessionCommands getAvailableSessionCommands() {
        verifyApplicationThread();
        return !isConnected() ? SessionCommands.EMPTY : this.impl.getAvailableSessionCommands();
    }

    public final IMediaController getBinder() {
        return this.impl.getBinder();
    }

    @Override // androidx.media3.common.Player
    public final int getBufferedPercentage() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPercentage();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final long getBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getBufferedPosition();
        }
        return 0L;
    }

    public final com.google.common.collect.h1 getCommandButtonsForMediaItem(MediaItem mediaItem) {
        return this.impl.getCommandButtonsForMediaItem(mediaItem);
    }

    public final SessionToken getConnectedToken() {
        if (isConnected()) {
            return this.impl.getConnectedToken();
        }
        return null;
    }

    public Bundle getConnectionHints() {
        return this.impl.getConnectionHints();
    }

    @Override // androidx.media3.common.Player
    public final long getContentBufferedPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentBufferedPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final long getContentDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getContentDuration() : C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player
    public final long getContentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getContentPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdGroupIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentAdIndexInAdGroup();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final CueGroup getCurrentCues() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentCues() : CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentLiveOffset() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentLiveOffset() : C.TIME_UNSET;
    }

    @Override // androidx.media3.common.Player
    public final Object getCurrentManifest() {
        return null;
    }

    @Override // androidx.media3.common.Player
    public final MediaItem getCurrentMediaItem() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPeriodIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public final long getCurrentPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCurrentPosition();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTimeline() : Timeline.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final Tracks getCurrentTracks() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getCurrentTracks() : Tracks.EMPTY;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getCurrentWindowIndex() {
        return getCurrentMediaItemIndex();
    }

    public final com.google.common.collect.h1 getCustomLayout() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getCustomLayout();
        }
        com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
        return o3.f14078o;
    }

    @Override // androidx.media3.common.Player
    public final DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return !isConnected() ? DeviceInfo.UNKNOWN : this.impl.getDeviceInfo();
    }

    @Override // androidx.media3.common.Player
    public final int getDeviceVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getDeviceVolume();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final long getDuration() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getDuration() : C.TIME_UNSET;
    }

    public int getMaxCommandsForMediaItems() {
        return this.maxCommandsForMediaItems;
    }

    @Override // androidx.media3.common.Player
    public final long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getMaxSeekToPreviousPosition();
        }
        return 0L;
    }

    public final com.google.common.collect.h1 getMediaButtonPreferences() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getMediaButtonPreferences();
        }
        com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
        return o3.f14078o;
    }

    @Override // androidx.media3.common.Player
    public final MediaItem getMediaItemAt(int i10) {
        return getCurrentTimeline().getWindow(i10, this.window).mediaItem;
    }

    @Override // androidx.media3.common.Player
    public final int getMediaItemCount() {
        return getCurrentTimeline().getWindowCount();
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getMediaMetadata() : MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final int getNextMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getNextMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getNextWindowIndex() {
        return getNextMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public final boolean getPlayWhenReady() {
        verifyApplicationThread();
        return isConnected() && this.impl.getPlayWhenReady();
    }

    @Override // androidx.media3.common.Player
    public final PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaybackParameters() : PlaybackParameters.DEFAULT;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackState() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackState();
        }
        return 1;
    }

    @Override // androidx.media3.common.Player
    public final int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlaybackSuppressionReason();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final PlaybackException getPlayerError() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPlayerError();
        }
        return null;
    }

    @Override // androidx.media3.common.Player
    public final MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getPlaylistMetadata() : MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final int getPreviousMediaItemIndex() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getPreviousMediaItemIndex();
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final int getPreviousWindowIndex() {
        return getPreviousMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public final int getRepeatMode() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getRepeatMode();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekBackIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekBackIncrement();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final long getSeekForwardIncrement() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getSeekForwardIncrement();
        }
        return 0L;
    }

    public final PendingIntent getSessionActivity() {
        if (isConnected()) {
            return this.impl.getSessionActivity();
        }
        return null;
    }

    public final Bundle getSessionExtras() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSessionExtras() : Bundle.EMPTY;
    }

    @Override // androidx.media3.common.Player
    public final boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return isConnected() && this.impl.getShuffleModeEnabled();
    }

    @Override // androidx.media3.common.Player
    public final Size getSurfaceSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getSurfaceSize() : Size.UNKNOWN;
    }

    public final long getTimeDiffMs() {
        return this.timeDiffMs;
    }

    @Override // androidx.media3.common.Player
    public final long getTotalBufferedDuration() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getTotalBufferedDuration();
        }
        return 0L;
    }

    @Override // androidx.media3.common.Player
    public final TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        return !isConnected() ? TrackSelectionParameters.DEFAULT : this.impl.getTrackSelectionParameters();
    }

    @Override // androidx.media3.common.Player
    public final VideoSize getVideoSize() {
        verifyApplicationThread();
        return isConnected() ? this.impl.getVideoSize() : VideoSize.UNKNOWN;
    }

    @Override // androidx.media3.common.Player
    public final float getVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.getVolume();
        }
        return 1.0f;
    }

    @Override // androidx.media3.common.Player
    public final boolean hasNextMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasNextMediaItem();
    }

    @Override // androidx.media3.common.Player
    public final boolean hasPreviousMediaItem() {
        verifyApplicationThread();
        return isConnected() && this.impl.hasPreviousMediaItem();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void increaseDeviceVolume() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.increaseDeviceVolume();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        }
    }

    @Override // androidx.media3.common.Player
    public final boolean isCommandAvailable(int i10) {
        return getAvailableCommands().contains(i10);
    }

    public final boolean isConnected() {
        return this.impl.isConnected();
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemDynamic() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isDynamic;
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemLive() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isLive();
    }

    @Override // androidx.media3.common.Player
    public final boolean isCurrentMediaItemSeekable() {
        verifyApplicationThread();
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).isSeekable;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowDynamic() {
        return isCurrentMediaItemDynamic();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowLive() {
        return isCurrentMediaItemLive();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final boolean isCurrentWindowSeekable() {
        return isCurrentMediaItemSeekable();
    }

    @Override // androidx.media3.common.Player
    public final boolean isDeviceMuted() {
        verifyApplicationThread();
        if (isConnected()) {
            return this.impl.isDeviceMuted();
        }
        return false;
    }

    @Override // androidx.media3.common.Player
    public final boolean isLoading() {
        verifyApplicationThread();
        return isConnected() && this.impl.isLoading();
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlaying() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlaying();
    }

    @Override // androidx.media3.common.Player
    public final boolean isPlayingAd() {
        verifyApplicationThread();
        return isConnected() && this.impl.isPlayingAd();
    }

    public final boolean isSessionCommandAvailable(int i10) {
        return getAvailableSessionCommands().contains(i10);
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItem(int i10, int i11) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.moveMediaItem(i10, i11);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void moveMediaItems(int i10, int i11, int i12) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.moveMediaItems(i10, i11, i12);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring moveMediaItems().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void mute() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.mute();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring mute().");
        }
    }

    public final void notifyAccepted() {
        ac.b.s(Looper.myLooper() == getApplicationLooper());
        ac.b.s(!this.connectionNotified);
        this.connectionNotified = true;
        this.connectionCallback.onAccepted();
    }

    public final void notifyControllerListener(Consumer<Listener> consumer) {
        ac.b.s(Looper.myLooper() == getApplicationLooper());
        consumer.accept(this.listener);
    }

    @Override // androidx.media3.common.Player
    public final void pause() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.pause();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring pause().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void play() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.play();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring play().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void prepare() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.prepare();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring prepare().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void release() {
        verifyApplicationThread();
        if (this.released) {
            return;
        }
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        this.released = true;
        this.applicationHandler.removeCallbacksAndMessages(null);
        try {
            this.impl.release();
        } catch (Exception e5) {
            Log.d(TAG, "Exception while releasing impl", e5);
        }
        if (this.connectionNotified) {
            notifyControllerListener(new k1(this, 10));
        } else {
            this.connectionNotified = true;
            this.connectionCallback.onRejected();
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeListener(Player.Listener listener) {
        verifyApplicationThread();
        ac.b.p(listener, "listener must not be null");
        this.impl.removeListener(listener);
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItem(int i10) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.removeMediaItem(i10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void removeMediaItems(int i10, int i11) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.removeMediaItems(i10, i11);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring removeMediaItems().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItem(int i10, MediaItem mediaItem) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.replaceMediaItem(i10, mediaItem);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void replaceMediaItems(int i10, int i11, List<MediaItem> list) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.replaceMediaItems(i10, i11, list);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring replaceMediaItems().");
        }
    }

    public final void runOnApplicationLooper(Runnable runnable) {
        Util.postOrRun(this.applicationHandler, runnable);
    }

    @Override // androidx.media3.common.Player
    public final void seekBack() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekBack();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekBack().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekForward() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekForward();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekForward().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekTo(long j10) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekTo(j10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekToDefaultPosition();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToNext() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekToNext();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNext().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToNextMediaItem() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekToNextMediaItem();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekToNextMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToPrevious() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekToPrevious();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPrevious().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToPreviousMediaItem() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.seekToPreviousMediaItem();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring seekToPreviousMediaItem().");
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        verifyApplicationThread();
        ac.b.p(sessionCommand, "command must not be null");
        ac.b.k(sessionCommand.commandCode == 0, "command must be a custom command");
        return isConnected() ? this.impl.sendCustomCommand(sessionCommand, bundle) : createDisconnectedFuture();
    }

    @Override // androidx.media3.common.Player
    public final void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setAudioAttributes(audioAttributes, z);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setAudioAttributes().");
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setDeviceMuted(z);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public final void setDeviceVolume(int i10) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setDeviceVolume(i10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem) {
        verifyApplicationThread();
        ac.b.p(mediaItem, "mediaItems must not be null");
        if (isConnected()) {
            this.impl.setMediaItem(mediaItem);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list) {
        verifyApplicationThread();
        ac.b.p(list, "mediaItems must not be null");
        for (int i10 = 0; i10 < list.size(); i10++) {
            ac.b.f(i10, "items must not contain null, index=%s", list.get(i10) != null);
        }
        if (isConnected()) {
            this.impl.setMediaItems(list);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setPlayWhenReady(z);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        ac.b.p(playbackParameters, "playbackParameters must not be null");
        if (isConnected()) {
            this.impl.setPlaybackParameters(playbackParameters);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackParameters().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaybackSpeed(float f10) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setPlaybackSpeed(f10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaybackSpeed().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThread();
        ac.b.p(mediaMetadata, "playlistMetadata must not be null");
        if (isConnected()) {
            this.impl.setPlaylistMetadata(mediaMetadata);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setPlaylistMetadata().");
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> setRating(String str, Rating rating) {
        verifyApplicationThread();
        ac.b.p(str, "mediaId must not be null");
        ac.b.k(!TextUtils.isEmpty(str), "mediaId must not be empty");
        ac.b.p(rating, "rating must not be null");
        return isConnected() ? this.impl.setRating(str, rating) : createDisconnectedFuture();
    }

    @Override // androidx.media3.common.Player
    public final void setRepeatMode(int i10) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setRepeatMode(i10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setRepeatMode().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setShuffleModeEnabled(z);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setShuffleMode().");
        }
    }

    public final void setTimeDiffMs(long j10) {
        verifyApplicationThread();
        this.timeDiffMs = j10;
    }

    @Override // androidx.media3.common.Player
    public final void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setTrackSelectionParameters().");
        }
        this.impl.setTrackSelectionParameters(trackSelectionParameters);
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setVideoSurface(surface);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurface().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setVideoSurfaceHolder(surfaceHolder);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceHolder().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setVideoSurfaceView(surfaceView);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoSurfaceView().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.setVideoTextureView(textureView);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setVideoTextureView().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void setVolume(float f10) {
        verifyApplicationThread();
        ac.b.k(f10 >= 0.0f && f10 <= 1.0f, "volume must be between 0 and 1");
        if (isConnected()) {
            this.impl.setVolume(f10);
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring setVolume().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void stop() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.stop();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring stop().");
        }
    }

    @Override // androidx.media3.common.Player
    public final void unmute() {
        verifyApplicationThread();
        if (isConnected()) {
            this.impl.unmute();
        } else {
            Log.w(TAG, "The controller is not connected. Ignoring unmute().");
        }
    }

    public final boolean isSessionCommandAvailable(SessionCommand sessionCommand) {
        return getAvailableSessionCommands().contains(sessionCommand);
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItem(int i10, MediaItem mediaItem) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItem().");
        } else {
            this.impl.addMediaItem(i10, mediaItem);
        }
    }

    @Override // androidx.media3.common.Player
    public final void addMediaItems(int i10, List<MediaItem> list) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring addMediaItems().");
        } else {
            this.impl.addMediaItems(i10, list);
        }
    }

    @Override // androidx.media3.common.Player
    public final void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring clearVideoSurface().");
        } else {
            this.impl.clearVideoSurface(surface);
        }
    }

    @Override // androidx.media3.common.Player
    public final void decreaseDeviceVolume(int i10) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring decreaseDeviceVolume().");
        } else {
            this.impl.decreaseDeviceVolume(i10);
        }
    }

    @Override // androidx.media3.common.Player
    public final void increaseDeviceVolume(int i10) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring increaseDeviceVolume().");
        } else {
            this.impl.increaseDeviceVolume(i10);
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekTo(int i10, long j10) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekTo(i10, j10);
        }
    }

    @Override // androidx.media3.common.Player
    public final void seekToDefaultPosition(int i10) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring seekTo().");
        } else {
            this.impl.seekToDefaultPosition(i10);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceMuted(boolean z, int i10) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceMuted().");
        } else {
            this.impl.setDeviceMuted(z, i10);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setDeviceVolume(int i10, int i11) {
        verifyApplicationThread();
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setDeviceVolume().");
        } else {
            this.impl.setDeviceVolume(i10, i11);
        }
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem, long j10) {
        verifyApplicationThread();
        ac.b.p(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItem().");
        } else {
            this.impl.setMediaItem(mediaItem, j10);
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle, ProgressListener progressListener) {
        verifyApplicationThread();
        ac.b.p(sessionCommand, "command must not be null");
        ac.b.k(sessionCommand.commandCode == 0, "command must be a custom command");
        if (isConnected()) {
            return this.impl.sendCustomCommand(sessionCommand, bundle, progressListener);
        }
        return createDisconnectedFuture();
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        ac.b.p(list, "mediaItems must not be null");
        for (int i10 = 0; i10 < list.size(); i10++) {
            ac.b.f(i10, "items must not contain null, index=%s", list.get(i10) != null);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, z);
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> setRating(Rating rating) {
        verifyApplicationThread();
        ac.b.p(rating, "rating must not be null");
        if (isConnected()) {
            return this.impl.setRating(rating);
        }
        return createDisconnectedFuture();
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItem(MediaItem mediaItem, boolean z) {
        verifyApplicationThread();
        ac.b.p(mediaItem, "mediaItems must not be null");
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItem(mediaItem, z);
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, MediaItem mediaItem, Bundle bundle) {
        return sendCustomCommand(sessionCommand, mediaItem, bundle, null);
    }

    public final com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(SessionCommand sessionCommand, MediaItem mediaItem, Bundle bundle, ProgressListener progressListener) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID", mediaItem.mediaId);
        return sendCustomCommand(sessionCommand, bundle2, progressListener);
    }

    @Override // androidx.media3.common.Player
    public final void setMediaItems(List<MediaItem> list, int i10, long j10) {
        verifyApplicationThread();
        ac.b.p(list, "mediaItems must not be null");
        for (int i11 = 0; i11 < list.size(); i11++) {
            ac.b.f(i11, "items must not contain null, index=%s", list.get(i11) != null);
        }
        if (!isConnected()) {
            Log.w(TAG, "The controller is not connected. Ignoring setMediaItems().");
        } else {
            this.impl.setMediaItems(list, i10, j10);
        }
    }
}
