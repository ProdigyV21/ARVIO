package androidx.media3.exoplayer;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioDeviceInfo;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.BasePlayer;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.IllegalSeekPositionException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoSize;
import androidx.media3.common.audio.AudioBecomingNoisyManager;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.StuckPlayerDetector;
import androidx.media3.common.util.StuckPlayerException;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.WakeLockManager;
import androidx.media3.common.util.WifiLockManager;
import androidx.media3.exoplayer.CodecParameters;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoPlayerImpl;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TimelineWithUpdatedMediaItem;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectionArray;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoDecoderOutputBufferRenderer;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import com.google.common.collect.o4;
import com.google.common.collect.v1;
import com.google.common.collect.w1;
import j$.util.Objects;
import j$.util.function.IntConsumer$CC;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes3.dex */
final class ExoPlayerImpl extends BasePlayer implements ExoPlayer {
    private static final String TAG = "ExoPlayerImpl";
    private final AnalyticsCollector analyticsCollector;
    private final Context applicationContext;
    private final Looper applicationLooper;
    private AudioAttributes audioAttributes;
    private final AudioBecomingNoisyManager audioBecomingNoisyManager;
    private DecoderCounters audioDecoderCounters;
    private Format audioFormat;
    private final CodecParameterListenerManager audioListenerManager;
    private final CopyOnWriteArraySet<ExoPlayer.AudioOffloadListener> audioOffloadListeners;
    private final BackgroundThreadStateHandler<Integer> audioSessionIdState;
    private Player.Commands availableCommands;
    private final BandwidthMeter bandwidthMeter;
    private CameraMotionListener cameraMotionListener;
    private final Clock clock;
    private final ComponentListener componentListener;
    private final ConditionVariable constructorFinished = new ConditionVariable();
    private CueGroup currentCueGroup;
    private final long detachSurfaceTimeoutMs;
    private DeviceInfo deviceInfo;
    private w1 disabledTrackTypesWithoutScrubbingMode;
    final TrackSelectorResult emptyTrackSelectorResult;
    private boolean foregroundMode;
    private final FrameMetadataListener frameMetadataListener;
    private boolean hasNotifiedFullWrongThreadWarning;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isPriorityTaskManagerRegistered;
    private final ListenerSet<Player.Listener> listeners;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private long maxSeekToPreviousPositionMs;
    private MediaMetadata mediaMetadata;
    private final MediaSource.Factory mediaSourceFactory;
    private final List<MediaSourceHolderSnapshot> mediaSourceHolderSnapshots;
    private Surface ownedSurface;
    private boolean pauseAtEndOfMediaItems;
    private boolean pendingDiscontinuity;
    private int pendingDiscontinuityReason;
    private int pendingOperationAcks;
    private final Timeline.Period period;
    final Player.Commands permanentAvailableCommands;
    private PlaybackInfo playbackInfo;
    private final HandlerWrapper playbackInfoUpdateHandler;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private boolean playerReleased;
    private MediaMetadata playlistMetadata;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private int priority;
    private PriorityTaskManager priorityTaskManager;
    private final Renderer[] renderers;
    private int repeatMode;
    private boolean scrubbingModeEnabled;
    private ScrubbingModeParameters scrubbingModeParameters;
    private final Renderer[] secondaryRenderers;
    private long seekBackIncrementMs;
    private long seekForwardIncrementMs;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private ShuffleOrder shuffleOrder;
    private boolean skipSilenceEnabled;
    private SphericalGLSurfaceView sphericalGLSurfaceView;
    private MediaMetadata staticAndDynamicMediaMetadata;
    private final StreamVolumeManager streamVolumeManager;
    private final StuckPlayerDetector stuckPlayerDetector;
    private final SuitableOutputChecker suitableOutputChecker;
    private SurfaceHolder surfaceHolder;
    private boolean surfaceHolderSurfaceIsVideoOutput;
    private Size surfaceSize;
    private TextureView textureView;
    private boolean throwsWhenUsingWrongThread;
    private final TrackSelector trackSelector;
    private float unmuteVolume;
    private final boolean useLazyPreparation;
    private int videoChangeFrameRateStrategy;
    private DecoderCounters videoDecoderCounters;
    private Format videoFormat;
    private VideoFrameMetadataListener videoFrameMetadataListener;
    private final CodecParameterListenerManager videoListenerManager;
    private Object videoOutput;
    private int videoScalingMode;
    private VideoSize videoSize;
    private final VirtualDeviceIdChangeListener virtualDeviceIdChangeListener;
    private float volume;
    private final WakeLockManager wakeLockManager;
    private final WifiLockManager wifiLockManager;
    private final Player wrappingPlayer;

    public static final class Api31 {
        private Api31() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$registerMediaMetricsListener$0(Context context, boolean z, ExoPlayerImpl exoPlayerImpl, PlayerId playerId) {
            MediaMetricsListener mediaMetricsListenerCreate = MediaMetricsListener.create(context);
            if (mediaMetricsListenerCreate == null) {
                Log.w(ExoPlayerImpl.TAG, "MediaMetricsService unavailable.");
                return;
            }
            if (z) {
                exoPlayerImpl.addAnalyticsListener(mediaMetricsListenerCreate);
            }
            playerId.setLogSessionId(mediaMetricsListenerCreate.getLogSessionId());
        }

        public static void registerMediaMetricsListener(final Context context, final ExoPlayerImpl exoPlayerImpl, final boolean z, final PlayerId playerId) {
            exoPlayerImpl.getClock().createHandler(exoPlayerImpl.getPlaybackLooper(), null).post(new Runnable() { // from class: androidx.media3.exoplayer.b0
                @Override // java.lang.Runnable
                public final void run() {
                    ExoPlayerImpl.Api31.lambda$registerMediaMetricsListener$0(context, z, exoPlayerImpl, playerId);
                }
            });
        }
    }

    public final class CodecParameterListenerManager {
        private CodecParameters lastNotifiedParameters;
        private final Map<CodecParametersChangeListener, List<String>> listeners;
        private final int trackType;

        /* JADX INFO: Access modifiers changed from: private */
        public void addListener(CodecParametersChangeListener codecParametersChangeListener, List<String> list) {
            this.listeners.put(codecParametersChangeListener, list);
            updateAndSendSubscribedKeysToRenderer();
            codecParametersChangeListener.onCodecParametersChanged(createFilteredCodecParameters(this.lastNotifiedParameters, list));
        }

        private CodecParameters createFilteredCodecParameters(CodecParameters codecParameters, List<String> list) {
            CodecParameters.Builder builderBuildUpon = codecParameters.buildUpon();
            HashSet hashSet = new HashSet(list);
            for (String str : codecParameters.keySet()) {
                if (!hashSet.contains(str)) {
                    builderBuildUpon.remove(str);
                }
            }
            return builderBuildUpon.build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onParametersChanged(CodecParameters codecParameters) {
            for (Map.Entry entry : new HashMap(this.listeners).entrySet()) {
                CodecParametersChangeListener codecParametersChangeListener = (CodecParametersChangeListener) entry.getKey();
                List<String> list = (List) entry.getValue();
                CodecParameters codecParametersCreateFilteredCodecParameters = createFilteredCodecParameters(codecParameters, list);
                if (!codecParametersCreateFilteredCodecParameters.equals(createFilteredCodecParameters(this.lastNotifiedParameters, list))) {
                    codecParametersChangeListener.onCodecParametersChanged(codecParametersCreateFilteredCodecParameters);
                }
            }
            this.lastNotifiedParameters = codecParameters;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeListener(CodecParametersChangeListener codecParametersChangeListener) {
            if (this.listeners.remove(codecParametersChangeListener) != null) {
                updateAndSendSubscribedKeysToRenderer();
            }
        }

        private void updateAndSendSubscribedKeysToRenderer() {
            int i10 = w1.f14132m;
            v1 v1Var = new v1(4);
            for (List<String> list : this.listeners.values()) {
                list.getClass();
                v1Var.d(list);
            }
            ExoPlayerImpl.this.sendRendererMessage(this.trackType, 22, v1Var.f());
        }

        private CodecParameterListenerManager(int i10) {
            this.trackType = i10;
            this.listeners = new HashMap();
            this.lastNotifiedParameters = CodecParameters.EMPTY;
        }
    }

    public final class ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, TextOutput, MetadataOutput, SurfaceHolder.Callback, TextureView.SurfaceTextureListener, SphericalGLSurfaceView.VideoSurfaceListener, AudioBecomingNoisyManager.Listener, StreamVolumeManager.Listener, ExoPlayer.AudioOffloadListener, StuckPlayerDetector.Callback {
        private ComponentListener() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Integer lambda$onAudioSessionIdChanged$2(int i10, Integer num) {
            return Integer.valueOf(i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ Integer lambda$onAudioSessionIdChanged$3(int i10, Integer num) {
            return Integer.valueOf(i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onMetadata$6(Player.Listener listener) {
            listener.onMediaMetadataChanged(ExoPlayerImpl.this.mediaMetadata);
        }

        @Override // androidx.media3.common.audio.AudioBecomingNoisyManager.Listener
        public void onAudioBecomingNoisy() {
            ExoPlayerImpl.this.updatePlayWhenReady(false, 3);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioCodecError(exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioCodecParametersChanged(CodecParameters codecParameters) {
            ExoPlayerImpl.this.audioListenerManager.onParametersChanged(codecParameters);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderInitialized(String str, long j10, long j11) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderInitialized(str, j10, j11);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderReleased(str);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDisabled(decoderCounters);
            ExoPlayerImpl.this.audioFormat = null;
            ExoPlayerImpl.this.audioDecoderCounters = null;
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioEnabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.audioDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onAudioEnabled(decoderCounters);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ExoPlayerImpl.this.audioFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioPositionAdvancing(long j10) {
            ExoPlayerImpl.this.analyticsCollector.onAudioPositionAdvancing(j10);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioSessionIdChanged(int i10) {
            ExoPlayerImpl.this.audioSessionIdState.updateStateAsync(new v(i10, 1), new v(i10, 2));
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioSinkError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioSinkError(exc);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            ExoPlayerImpl.this.analyticsCollector.onAudioTrackInitialized(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            ExoPlayerImpl.this.analyticsCollector.onAudioTrackReleased(audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioUnderrun(int i10, long j10, long j11) {
            ExoPlayerImpl.this.analyticsCollector.onAudioUnderrun(i10, j10, j11);
        }

        @Override // androidx.media3.exoplayer.text.TextOutput
        public void onCues(List<Cue> list) {
            ExoPlayerImpl.this.listeners.sendEvent(27, new p(list, 6));
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onDroppedFrames(int i10, long j10) {
            ExoPlayerImpl.this.analyticsCollector.onDroppedFrames(i10, j10);
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataOutput
        public void onMetadata(Metadata metadata) {
            ExoPlayerImpl exoPlayerImpl = ExoPlayerImpl.this;
            exoPlayerImpl.staticAndDynamicMediaMetadata = exoPlayerImpl.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(metadata).build();
            MediaMetadata mediaMetadataBuildUpdatedMediaMetadata = ExoPlayerImpl.this.buildUpdatedMediaMetadata();
            if (!mediaMetadataBuildUpdatedMediaMetadata.equals(ExoPlayerImpl.this.mediaMetadata)) {
                ExoPlayerImpl.this.mediaMetadata = mediaMetadataBuildUpdatedMediaMetadata;
                ExoPlayerImpl.this.listeners.queueEvent(14, new p(this, 4));
            }
            ExoPlayerImpl.this.listeners.queueEvent(28, new p(metadata, 5));
            ExoPlayerImpl.this.listeners.flushEvents();
        }

        @Override // androidx.media3.exoplayer.ExoPlayer.AudioOffloadListener
        public final /* synthetic */ void onOffloadedPlayback(boolean z) {
            f.a(this, z);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onRenderedFirstFrame(Object obj, long j10) {
            ExoPlayerImpl.this.analyticsCollector.onRenderedFirstFrame(obj, j10);
            if (ExoPlayerImpl.this.videoOutput == obj) {
                ExoPlayerImpl.this.listeners.sendEvent(26, new androidx.credentials.d(24));
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onSkipSilenceEnabledChanged(boolean z) {
            if (ExoPlayerImpl.this.skipSilenceEnabled == z) {
                return;
            }
            ExoPlayerImpl.this.skipSilenceEnabled = z;
            ExoPlayerImpl.this.listeners.sendEvent(23, new s(z, 2));
        }

        @Override // androidx.media3.exoplayer.ExoPlayer.AudioOffloadListener
        public void onSleepingForOffloadChanged(boolean z) {
            ExoPlayerImpl.this.updateWakeAndWifiLock();
        }

        @Override // androidx.media3.exoplayer.StreamVolumeManager.Listener
        public void onStreamTypeChanged(int i10) {
            DeviceInfo deviceInfoCreateDeviceInfo = ExoPlayerImpl.createDeviceInfo(ExoPlayerImpl.this.streamVolumeManager);
            if (deviceInfoCreateDeviceInfo.equals(ExoPlayerImpl.this.deviceInfo)) {
                return;
            }
            ExoPlayerImpl.this.deviceInfo = deviceInfoCreateDeviceInfo;
            ExoPlayerImpl.this.listeners.sendEvent(29, new p(deviceInfoCreateDeviceInfo, 8));
        }

        @Override // androidx.media3.exoplayer.StreamVolumeManager.Listener
        public void onStreamVolumeChanged(final int i10, final boolean z) {
            ExoPlayerImpl.this.listeners.sendEvent(30, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.c0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((Player.Listener) obj).onDeviceVolumeChanged(i10, z);
                }
            });
        }

        @Override // androidx.media3.common.util.StuckPlayerDetector.Callback
        public void onStuckPlayerDetected(StuckPlayerException stuckPlayerException) {
            ExoPlayerImpl.this.stopInternal(ExoPlaybackException.createForUnexpected(stuckPlayerException, 1003));
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i10, int i11) {
            ExoPlayerImpl.this.setSurfaceTextureInternal(surfaceTexture);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i10, i11);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            ExoPlayerImpl.this.setVideoOutputInternal(null);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i10, int i11) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i10, i11);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onVideoCodecError(exc);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoCodecParametersChanged(CodecParameters codecParameters) {
            ExoPlayerImpl.this.videoListenerManager.onParametersChanged(codecParameters);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDecoderInitialized(String str, long j10, long j11) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderInitialized(str, j10, j11);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderReleased(str);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDisabled(decoderCounters);
            ExoPlayerImpl.this.videoFormat = null;
            ExoPlayerImpl.this.videoDecoderCounters = null;
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoEnabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.videoDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onVideoEnabled(decoderCounters);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoFrameProcessingOffset(long j10, int i10) {
            ExoPlayerImpl.this.analyticsCollector.onVideoFrameProcessingOffset(j10, i10);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ExoPlayerImpl.this.videoFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoSizeChanged(VideoSize videoSize) {
            ExoPlayerImpl.this.videoSize = videoSize;
            ExoPlayerImpl.this.listeners.sendEvent(25, new p(videoSize, 7));
        }

        @Override // androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView.VideoSurfaceListener
        public void onVideoSurfaceCreated(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal(surface);
        }

        @Override // androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView.VideoSurfaceListener
        public void onVideoSurfaceDestroyed(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal(null);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i10, int i11, int i12) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i11, i12);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal(surfaceHolder.getSurface());
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal(null);
            }
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
        }

        @Override // androidx.media3.exoplayer.text.TextOutput
        public void onCues(CueGroup cueGroup) {
            ExoPlayerImpl.this.currentCueGroup = cueGroup;
            ExoPlayerImpl.this.listeners.sendEvent(27, new p(cueGroup, 3));
        }
    }

    public static final class FrameMetadataListener implements VideoFrameMetadataListener, CameraMotionListener, PlayerMessage.Target {
        public static final int MSG_SET_CAMERA_MOTION_LISTENER = 8;
        public static final int MSG_SET_SPHERICAL_SURFACE_VIEW = 10000;
        public static final int MSG_SET_VIDEO_FRAME_METADATA_LISTENER = 7;
        private CameraMotionListener cameraMotionListener;
        private CameraMotionListener internalCameraMotionListener;
        private VideoFrameMetadataListener internalVideoFrameMetadataListener;
        private VideoFrameMetadataListener videoFrameMetadataListener;

        private FrameMetadataListener() {
        }

        @Override // androidx.media3.exoplayer.PlayerMessage.Target
        public void handleMessage(int i10, Object obj) {
            if (i10 == 7) {
                this.videoFrameMetadataListener = (VideoFrameMetadataListener) obj;
                return;
            }
            if (i10 == 8) {
                this.cameraMotionListener = (CameraMotionListener) obj;
                return;
            }
            if (i10 != 10000) {
                return;
            }
            SphericalGLSurfaceView sphericalGLSurfaceView = (SphericalGLSurfaceView) obj;
            if (sphericalGLSurfaceView == null) {
                this.internalVideoFrameMetadataListener = null;
                this.internalCameraMotionListener = null;
            } else {
                this.internalVideoFrameMetadataListener = sphericalGLSurfaceView.getVideoFrameMetadataListener();
                this.internalCameraMotionListener = sphericalGLSurfaceView.getCameraMotionListener();
            }
        }

        @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
        public void onCameraMotion(long j10, float[] fArr) {
            CameraMotionListener cameraMotionListener = this.internalCameraMotionListener;
            if (cameraMotionListener != null) {
                cameraMotionListener.onCameraMotion(j10, fArr);
            }
            CameraMotionListener cameraMotionListener2 = this.cameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotion(j10, fArr);
            }
        }

        @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
        public void onCameraMotionReset() {
            CameraMotionListener cameraMotionListener = this.internalCameraMotionListener;
            if (cameraMotionListener != null) {
                cameraMotionListener.onCameraMotionReset();
            }
            CameraMotionListener cameraMotionListener2 = this.cameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotionReset();
            }
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameMetadataListener
        public void onVideoFrameAboutToBeRendered(long j10, long j11, Format format, MediaFormat mediaFormat) {
            long j12;
            long j13;
            Format format2;
            MediaFormat mediaFormat2;
            VideoFrameMetadataListener videoFrameMetadataListener = this.internalVideoFrameMetadataListener;
            if (videoFrameMetadataListener != null) {
                videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j10, j11, format, mediaFormat);
                mediaFormat2 = mediaFormat;
                format2 = format;
                j13 = j11;
                j12 = j10;
            } else {
                j12 = j10;
                j13 = j11;
                format2 = format;
                mediaFormat2 = mediaFormat;
            }
            VideoFrameMetadataListener videoFrameMetadataListener2 = this.videoFrameMetadataListener;
            if (videoFrameMetadataListener2 != null) {
                videoFrameMetadataListener2.onVideoFrameAboutToBeRendered(j12, j13, format2, mediaFormat2);
            }
        }
    }

    public static final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        private final MediaSource mediaSource;
        private Timeline timeline;
        private final Object uid;

        public MediaSourceHolderSnapshot(Object obj, MaskingMediaSource maskingMediaSource) {
            this.uid = obj;
            this.mediaSource = maskingMediaSource;
            this.timeline = maskingMediaSource.getTimeline();
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Timeline getTimeline() {
            return this.timeline;
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Object getUid() {
            return this.uid;
        }

        public void updateTimeline(Timeline timeline) {
            this.timeline = timeline;
        }
    }

    public final class VirtualDeviceIdChangeListener {
        private final WeakReference<Context> contextReference;
        private final IntConsumer listener;

        /* JADX INFO: Access modifiers changed from: private */
        public void onVirtualDeviceIdChanged(int i10) {
            if (ExoPlayerImpl.this.playerReleased) {
                return;
            }
            ExoPlayerImpl.this.sendRendererMessage(1, 19, Integer.valueOf(i10));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void release() {
            Context context = this.contextReference.get();
            if (context == null) {
                return;
            }
            context.unregisterDeviceIdChangeListener(this.listener);
        }

        private VirtualDeviceIdChangeListener(Context context) {
            this.contextReference = new WeakReference<>(context);
            IntConsumer intConsumer = new IntConsumer() { // from class: androidx.media3.exoplayer.d0
                @Override // java.util.function.IntConsumer
                public final void accept(int i10) {
                    this.f3592a.onVirtualDeviceIdChanged(i10);
                }

                public /* synthetic */ IntConsumer andThen(IntConsumer intConsumer2) {
                    return IntConsumer$CC.$default$andThen(this, intConsumer2);
                }
            };
            this.listener = intConsumer;
            HandlerWrapper handlerWrapperCreateHandler = ExoPlayerImpl.this.clock.createHandler(ExoPlayerImpl.this.applicationLooper, null);
            Objects.requireNonNull(handlerWrapperCreateHandler);
            context.registerDeviceIdChangeListener(new d(handlerWrapperCreateHandler, 1), intConsumer);
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer");
    }

    public ExoPlayerImpl(ExoPlayer.Builder builder, Player player) {
        Looper looper;
        Clock clock;
        try {
            Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "]");
            this.applicationContext = builder.context.getApplicationContext();
            this.analyticsCollector = builder.analyticsCollectorFunction.apply(builder.clock);
            this.priority = builder.priority;
            this.priorityTaskManager = builder.priorityTaskManager;
            this.audioAttributes = builder.audioAttributes;
            this.videoScalingMode = builder.videoScalingMode;
            this.videoChangeFrameRateStrategy = builder.videoChangeFrameRateStrategy;
            this.skipSilenceEnabled = builder.skipSilenceEnabled;
            this.detachSurfaceTimeoutMs = builder.detachSurfaceTimeoutMs;
            ComponentListener componentListener = new ComponentListener();
            this.componentListener = componentListener;
            this.frameMetadataListener = new FrameMetadataListener();
            Handler handler = new Handler(builder.looper);
            RenderersFactory renderersFactory = (RenderersFactory) builder.renderersFactorySupplier.get();
            Renderer[] rendererArrCreateRenderers = renderersFactory.createRenderers(handler, componentListener, componentListener, componentListener, componentListener);
            this.renderers = rendererArrCreateRenderers;
            int i10 = 1;
            ac.b.s(rendererArrCreateRenderers.length > 0);
            this.secondaryRenderers = new Renderer[rendererArrCreateRenderers.length];
            int i11 = 0;
            while (true) {
                Renderer[] rendererArr = this.secondaryRenderers;
                if (i11 >= rendererArr.length) {
                    break;
                }
                Renderer renderer = this.renderers[i11];
                ComponentListener componentListener2 = this.componentListener;
                rendererArr[i11] = renderersFactory.createSecondaryRenderer(renderer, handler, componentListener2, componentListener2, componentListener2, componentListener2);
                i11++;
            }
            TrackSelector trackSelector = (TrackSelector) builder.trackSelectorSupplier.get();
            this.trackSelector = trackSelector;
            this.mediaSourceFactory = (MediaSource.Factory) builder.mediaSourceFactorySupplier.get();
            BandwidthMeter bandwidthMeter = (BandwidthMeter) builder.bandwidthMeterSupplier.get();
            this.bandwidthMeter = bandwidthMeter;
            this.useLazyPreparation = builder.useLazyPreparation;
            this.seekParameters = builder.seekParameters;
            this.seekBackIncrementMs = builder.seekBackIncrementMs;
            this.seekForwardIncrementMs = builder.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = builder.maxSeekToPreviousPositionMs;
            this.scrubbingModeParameters = builder.scrubbingModeParameters;
            this.pauseAtEndOfMediaItems = builder.pauseAtEndOfMediaItems;
            Looper looper2 = builder.looper;
            this.applicationLooper = looper2;
            Clock clock2 = builder.clock;
            this.clock = clock2;
            Player player2 = player == null ? this : player;
            this.wrappingPlayer = player2;
            this.listeners = new ListenerSet<>(looper2, clock2, new z(this, i10));
            this.audioOffloadListeners = new CopyOnWriteArraySet<>();
            this.mediaSourceHolderSnapshots = new ArrayList();
            this.shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);
            this.preloadConfiguration = ExoPlayer.PreloadConfiguration.DEFAULT;
            Renderer[] rendererArr2 = this.renderers;
            TrackSelectorResult trackSelectorResult = new TrackSelectorResult(new RendererConfiguration[rendererArr2.length], new ExoTrackSelection[rendererArr2.length], Tracks.EMPTY, null);
            this.emptyTrackSelectorResult = trackSelectorResult;
            this.period = new Timeline.Period();
            Player.Commands commandsBuild = new Player.Commands.Builder().addAll(1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32).addIf(29, trackSelector.isSetParametersSupported()).addIf(23, builder.deviceVolumeControlEnabled).addIf(25, builder.deviceVolumeControlEnabled).addIf(33, builder.deviceVolumeControlEnabled).addIf(26, builder.deviceVolumeControlEnabled).addIf(34, builder.deviceVolumeControlEnabled).build();
            this.permanentAvailableCommands = commandsBuild;
            this.availableCommands = new Player.Commands.Builder().addAll(commandsBuild).add(4).add(10).build();
            this.playbackInfoUpdateHandler = clock2.createHandler(looper2, null);
            int i12 = 2;
            z zVar = new z(this, i12);
            this.playbackInfoUpdateListener = zVar;
            this.playbackInfo = PlaybackInfo.createDummy(trackSelectorResult);
            this.analyticsCollector.setPlayer(player2, looper2);
            PlayerId playerId = new PlayerId(builder.playerName);
            int i13 = 4;
            ExoPlayerImplInternal exoPlayerImplInternal = new ExoPlayerImplInternal(this.applicationContext, this.renderers, this.secondaryRenderers, trackSelector, trackSelectorResult, (LoadControl) builder.loadControlSupplier.get(), bandwidthMeter, this.repeatMode, this.shuffleModeEnabled, this.analyticsCollector, this.seekParameters, builder.livePlaybackSpeedControl, builder.releaseTimeoutMs, this.pauseAtEndOfMediaItems, builder.dynamicSchedulingEnabled, looper2, clock2, zVar, playerId, builder.playbackLooperProvider, this.preloadConfiguration, this.frameMetadataListener);
            this.internalPlayer = exoPlayerImplInternal;
            Looper playbackLooper = exoPlayerImplInternal.getPlaybackLooper();
            this.volume = 1.0f;
            this.repeatMode = 0;
            MediaMetadata mediaMetadata = MediaMetadata.EMPTY;
            this.mediaMetadata = mediaMetadata;
            this.playlistMetadata = mediaMetadata;
            this.staticAndDynamicMediaMetadata = mediaMetadata;
            this.maskingWindowIndex = -1;
            this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;
            this.throwsWhenUsingWrongThread = true;
            addListener(this.analyticsCollector);
            bandwidthMeter.addEventListener(new Handler(looper2), this.analyticsCollector);
            addAudioOffloadListener(this.componentListener);
            long j10 = builder.foregroundModeTimeoutMs;
            if (j10 > 0) {
                exoPlayerImplInternal.experimentalSetForegroundModeTimeoutMs(j10);
            }
            int i14 = Build.VERSION.SDK_INT;
            if (i14 >= 31) {
                Api31.registerMediaMetricsListener(this.applicationContext, this, builder.usePlatformDiagnostics, playerId);
            }
            BackgroundThreadStateHandler<Integer> backgroundThreadStateHandler = new BackgroundThreadStateHandler<>(0, playbackLooper, looper2, clock2, new z(this, 3));
            this.audioSessionIdState = backgroundThreadStateHandler;
            backgroundThreadStateHandler.runInBackground(new a(this, i12));
            AudioBecomingNoisyManager audioBecomingNoisyManager = new AudioBecomingNoisyManager(builder.context, playbackLooper, builder.looper, this.componentListener, clock2);
            Clock clock3 = clock2;
            this.audioBecomingNoisyManager = audioBecomingNoisyManager;
            audioBecomingNoisyManager.setEnabled(builder.handleAudioBecomingNoisy);
            if (builder.suppressPlaybackOnUnsuitableOutput) {
                SuitableOutputChecker suitableOutputChecker = builder.suitableOutputChecker;
                this.suitableOutputChecker = suitableOutputChecker;
                suitableOutputChecker.enable(new z(this, i13), this.applicationContext, looper2, playbackLooper, clock3);
                clock3 = clock3;
            } else {
                this.suitableOutputChecker = null;
            }
            if (builder.deviceVolumeControlEnabled) {
                Clock clock4 = clock3;
                looper = playbackLooper;
                clock = clock4;
                this.streamVolumeManager = new StreamVolumeManager(builder.context, this.componentListener, this.audioAttributes.getStreamType(), playbackLooper, looper2, clock4);
            } else {
                looper = playbackLooper;
                clock = clock3;
                this.streamVolumeManager = null;
            }
            int i15 = builder.wakeModeSet ? builder.wakeMode : (builder.stuckBufferingDetectionTimeoutMs == Integer.MAX_VALUE || builder.stuckPlayingDetectionTimeoutMs == Integer.MAX_VALUE || builder.stuckPlayingNotEndingTimeoutMs == Integer.MAX_VALUE || builder.stuckSuppressedDetectionTimeoutMs == Integer.MAX_VALUE) ? 0 : 1;
            WakeLockManager wakeLockManager = new WakeLockManager(builder.context, looper, clock);
            this.wakeLockManager = wakeLockManager;
            wakeLockManager.setEnabled(i15 != 0);
            WifiLockManager wifiLockManager = new WifiLockManager(builder.context, looper, clock);
            this.wifiLockManager = wifiLockManager;
            wifiLockManager.setEnabled(i15 == 2);
            this.deviceInfo = DeviceInfo.UNKNOWN;
            this.videoSize = VideoSize.UNKNOWN;
            this.surfaceSize = Size.UNKNOWN;
            this.virtualDeviceIdChangeListener = i14 >= 34 ? new VirtualDeviceIdChangeListener(builder.context) : null;
            this.audioListenerManager = new CodecParameterListenerManager(1);
            this.videoListenerManager = new CodecParameterListenerManager(i12);
            this.stuckPlayerDetector = new StuckPlayerDetector(this, this.componentListener, clock, builder.stuckBufferingDetectionTimeoutMs, builder.stuckPlayingDetectionTimeoutMs, builder.stuckPlayingNotEndingTimeoutMs, builder.stuckSuppressedDetectionTimeoutMs);
            exoPlayerImplInternal.setScrubbingModeParameters(this.scrubbingModeParameters);
            exoPlayerImplInternal.setAudioAttributes(this.audioAttributes, builder.handleAudioFocus);
            sendRendererMessage(1, 3, this.audioAttributes);
            sendRendererMessage(2, 4, Integer.valueOf(this.videoScalingMode));
            sendRendererMessage(2, 5, Integer.valueOf(this.videoChangeFrameRateStrategy));
            sendRendererMessage(1, 9, Boolean.valueOf(this.skipSilenceEnabled));
            sendRendererMessage(6, 8, this.frameMetadataListener);
            sendRendererMessage(16, Integer.valueOf(this.priority));
            AudioOutputProvider audioOutputProvider = builder.audioOutputProvider;
            if (audioOutputProvider != null) {
                sendRendererMessage(1, 20, audioOutputProvider);
            }
            this.constructorFinished.open();
        } catch (Throwable th) {
            this.constructorFinished.open();
            throw th;
        }
    }

    private static TrackSelectionParameters addDisabledTrackTypes(TrackSelectionParameters trackSelectionParameters, w1 w1Var) {
        TrackSelectionParameters.Builder builderBuildUpon = trackSelectionParameters.buildUpon();
        o4 o4VarG = w1Var.iterator();
        while (o4VarG.hasNext()) {
            builderBuildUpon.setTrackTypeDisabled(((Integer) o4VarG.next()).intValue(), true);
        }
        return builderBuildUpon.build();
    }

    private List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders(int i10, List<MediaSource> list) {
        ArrayList arrayList = new ArrayList();
        for (int i11 = 0; i11 < list.size(); i11++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder(list.get(i11), this.useLazyPreparation);
            arrayList.add(mediaSourceHolder);
            this.mediaSourceHolderSnapshots.add(i11 + i10, new MediaSourceHolderSnapshot(mediaSourceHolder.uid, mediaSourceHolder.mediaSource));
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(i10, arrayList.size());
        return arrayList;
    }

    private PlaybackInfo addMediaSourcesInternal(PlaybackInfo playbackInfo, int i10, List<MediaSource> list) {
        Timeline timeline = playbackInfo.timeline;
        this.pendingOperationAcks++;
        List<MediaSourceList.MediaSourceHolder> listAddMediaSourceHolders = addMediaSourceHolders(i10, list);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline();
        PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(playbackInfo, timelineCreateMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(timeline, timelineCreateMaskingTimeline, getCurrentWindowIndexInternal(playbackInfo), getContentPositionInternal(playbackInfo)));
        this.internalPlayer.addMediaSources(i10, listAddMediaSourceHolders, this.shuffleOrder);
        return playbackInfoMaskTimelineAndPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaMetadata buildUpdatedMediaMetadata() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return this.staticAndDynamicMediaMetadata;
        }
        return this.staticAndDynamicMediaMetadata.buildUpon().populate(currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem.mediaMetadata).build();
    }

    private boolean canUpdateMediaSourcesWithMediaItems(int i10, int i11, List<MediaItem> list) {
        if (i11 - i10 != list.size()) {
            return false;
        }
        for (int i12 = i10; i12 < i11; i12++) {
            if (!this.mediaSourceHolderSnapshots.get(i12).mediaSource.canUpdateMediaItem(list.get(i12 - i10))) {
                return false;
            }
        }
        return true;
    }

    private int computePlaybackSuppressionReason(boolean z) {
        if (this.scrubbingModeEnabled) {
            return 4;
        }
        SuitableOutputChecker suitableOutputChecker = this.suitableOutputChecker;
        if (suitableOutputChecker == null || suitableOutputChecker.isSelectedOutputSuitableForPlayback()) {
            return (this.playbackInfo.playbackSuppressionReason != 1 || z) ? 0 : 1;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DeviceInfo createDeviceInfo(StreamVolumeManager streamVolumeManager) {
        return new DeviceInfo.Builder(0).setMinVolume(streamVolumeManager != null ? streamVolumeManager.getMinVolume() : 0).setMaxVolume(streamVolumeManager != null ? streamVolumeManager.getMaxVolume() : 0).build();
    }

    private Timeline createMaskingTimeline() {
        return new PlaylistTimeline(this.mediaSourceHolderSnapshots, this.shuffleOrder);
    }

    private List<MediaSource> createMediaSources(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            arrayList.add(this.mediaSourceFactory.createMediaSource(list.get(i10)));
        }
        return arrayList;
    }

    private PlayerMessage createMessageInternal(PlayerMessage.Target target) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        ExoPlayerImplInternal exoPlayerImplInternal = this.internalPlayer;
        Timeline timeline = this.playbackInfo.timeline;
        if (currentWindowIndexInternal == -1) {
            currentWindowIndexInternal = 0;
        }
        return new PlayerMessage(exoPlayerImplInternal, target, timeline, currentWindowIndexInternal, this.clock, exoPlayerImplInternal.getPlaybackLooper());
    }

    private Pair<Boolean, Integer> evaluateMediaItemTransitionReason(PlaybackInfo playbackInfo, PlaybackInfo playbackInfo2, boolean z, int i10, boolean z5, boolean z10) {
        Timeline timeline = playbackInfo2.timeline;
        Timeline timeline2 = playbackInfo.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return new Pair<>(Boolean.FALSE, -1);
        }
        int i11 = 3;
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return new Pair<>(Boolean.TRUE, 3);
        }
        if (timeline.getWindow(timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex, this.window).uid.equals(timeline2.getWindow(timeline2.getPeriodByUid(playbackInfo.periodId.periodUid, this.period).windowIndex, this.window).uid)) {
            return (z && i10 == 0 && playbackInfo2.periodId.windowSequenceNumber < playbackInfo.periodId.windowSequenceNumber) ? new Pair<>(Boolean.TRUE, 0) : (z && i10 == 1 && z10) ? new Pair<>(Boolean.TRUE, 2) : new Pair<>(Boolean.FALSE, -1);
        }
        if (z && i10 == 0) {
            i11 = 1;
        } else if (z && i10 == 1) {
            i11 = 2;
        } else if (!z5) {
            throw new IllegalStateException();
        }
        return new Pair<>(Boolean.TRUE, Integer.valueOf(i11));
    }

    private long getContentPositionInternal(PlaybackInfo playbackInfo) {
        if (!playbackInfo.periodId.isAd()) {
            return Util.usToMs(getCurrentPositionUsInternal(playbackInfo));
        }
        playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid, this.period);
        if (playbackInfo.requestedContentPositionUs == C.TIME_UNSET) {
            return playbackInfo.timeline.getWindow(getCurrentWindowIndexInternal(playbackInfo), this.window).getDefaultPositionMs();
        }
        return Util.usToMs(playbackInfo.requestedContentPositionUs) + this.period.getPositionInWindowMs();
    }

    private long getCurrentPositionUsInternal(PlaybackInfo playbackInfo) {
        if (playbackInfo.timeline.isEmpty()) {
            return Util.msToUs(this.maskingWindowPositionMs);
        }
        long estimatedPositionUs = playbackInfo.sleepingForOffload ? playbackInfo.getEstimatedPositionUs() : playbackInfo.positionUs;
        return playbackInfo.periodId.isAd() ? estimatedPositionUs : periodPositionUsToWindowPositionUs(playbackInfo.timeline, playbackInfo.periodId, estimatedPositionUs);
    }

    private int getCurrentWindowIndexInternal(PlaybackInfo playbackInfo) {
        return playbackInfo.timeline.isEmpty() ? this.maskingWindowIndex : playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid, this.period).windowIndex;
    }

    private Pair<Object, Long> getPeriodPositionUsAfterTimelineChanged(Timeline timeline, Timeline timeline2, int i10, long j10) {
        boolean zIsEmpty = timeline.isEmpty();
        long j11 = C.TIME_UNSET;
        if (zIsEmpty || timeline2.isEmpty()) {
            boolean z = !timeline.isEmpty() && timeline2.isEmpty();
            int i11 = z ? -1 : i10;
            if (!z) {
                j11 = j10;
            }
            return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, i11, j11);
        }
        Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, i10, Util.msToUs(j10));
        Object obj = ((Pair) Util.castNonNull(periodPositionUs)).first;
        if (timeline2.getIndexOfPeriod(obj) != -1) {
            return periodPositionUs;
        }
        int iResolveSubsequentPeriod = ExoPlayerImplInternal.resolveSubsequentPeriod(this.window, this.period, this.repeatMode, this.shuffleModeEnabled, obj, timeline, timeline2);
        return iResolveSubsequentPeriod != -1 ? maskWindowPositionMsOrGetPeriodPositionUs(timeline2, iResolveSubsequentPeriod, timeline2.getWindow(iResolveSubsequentPeriod, this.window).getDefaultPositionMs()) : maskWindowPositionMsOrGetPeriodPositionUs(timeline2, -1, C.TIME_UNSET);
    }

    private Player.PositionInfo getPositionInfo(long j10) {
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        int currentPeriodIndex = getCurrentPeriodIndex();
        if (this.playbackInfo.timeline.isEmpty()) {
            obj = null;
            mediaItem = null;
            obj2 = null;
        } else {
            PlaybackInfo playbackInfo = this.playbackInfo;
            Object obj3 = playbackInfo.periodId.periodUid;
            playbackInfo.timeline.getPeriodByUid(obj3, this.period);
            currentPeriodIndex = this.playbackInfo.timeline.getIndexOfPeriod(obj3);
            obj2 = obj3;
            obj = this.playbackInfo.timeline.getWindow(currentMediaItemIndex, this.window).uid;
            mediaItem = this.window.mediaItem;
        }
        int i10 = currentPeriodIndex;
        long jUsToMs = Util.usToMs(j10);
        long jUsToMs2 = this.playbackInfo.periodId.isAd() ? Util.usToMs(getRequestedContentPositionUs(this.playbackInfo)) : jUsToMs;
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        return new Player.PositionInfo(obj, currentMediaItemIndex, mediaItem, obj2, i10, jUsToMs, jUsToMs2, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
    }

    private Player.PositionInfo getPreviousPositionInfo(int i10, PlaybackInfo playbackInfo, int i11) {
        int i12;
        int i13;
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        long requestedContentPositionUs;
        long requestedContentPositionUs2;
        Timeline.Period period = new Timeline.Period();
        if (playbackInfo.timeline.isEmpty()) {
            i12 = i11;
            i13 = i12;
            obj = null;
            mediaItem = null;
            obj2 = null;
        } else {
            Object obj3 = playbackInfo.periodId.periodUid;
            playbackInfo.timeline.getPeriodByUid(obj3, period);
            int i14 = period.windowIndex;
            int indexOfPeriod = playbackInfo.timeline.getIndexOfPeriod(obj3);
            Object obj4 = playbackInfo.timeline.getWindow(i14, this.window).uid;
            mediaItem = this.window.mediaItem;
            obj2 = obj3;
            i13 = indexOfPeriod;
            obj = obj4;
            i12 = i14;
        }
        if (i10 == 0) {
            if (playbackInfo.periodId.isAd()) {
                MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.periodId;
                requestedContentPositionUs = period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
                requestedContentPositionUs2 = getRequestedContentPositionUs(playbackInfo);
            } else if (playbackInfo.periodId.nextAdGroupIndex != -1) {
                requestedContentPositionUs = getRequestedContentPositionUs(this.playbackInfo);
                requestedContentPositionUs2 = requestedContentPositionUs;
            } else {
                requestedContentPositionUs2 = period.positionInWindowUs + period.durationUs;
                requestedContentPositionUs = requestedContentPositionUs2;
            }
        } else if (playbackInfo.periodId.isAd()) {
            requestedContentPositionUs = playbackInfo.positionUs;
            requestedContentPositionUs2 = getRequestedContentPositionUs(playbackInfo);
        } else {
            requestedContentPositionUs = period.positionInWindowUs + playbackInfo.positionUs;
            requestedContentPositionUs2 = requestedContentPositionUs;
        }
        long jUsToMs = Util.usToMs(requestedContentPositionUs);
        long jUsToMs2 = Util.usToMs(requestedContentPositionUs2);
        MediaSource.MediaPeriodId mediaPeriodId2 = playbackInfo.periodId;
        return new Player.PositionInfo(obj, i12, mediaItem, obj2, i13, jUsToMs, jUsToMs2, mediaPeriodId2.adGroupIndex, mediaPeriodId2.adIndexInAdGroup);
    }

    private static long getRequestedContentPositionUs(PlaybackInfo playbackInfo) {
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period = new Timeline.Period();
        playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid, period);
        return playbackInfo.requestedContentPositionUs == C.TIME_UNSET ? playbackInfo.timeline.getWindow(period.windowIndex, window).getDefaultPositionUs() : period.getPositionInWindowUs() + playbackInfo.requestedContentPositionUs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePlaybackInfo, reason: merged with bridge method [inline-methods] */
    public void lambda$new$1(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        int i10;
        long j10;
        boolean z;
        long jPeriodPositionUsToWindowPositionUs;
        int i11 = this.pendingOperationAcks - playbackInfoUpdate.operationAcks;
        this.pendingOperationAcks = i11;
        boolean z5 = true;
        if (playbackInfoUpdate.positionDiscontinuity) {
            this.pendingDiscontinuityReason = playbackInfoUpdate.discontinuityReason;
            this.pendingDiscontinuity = true;
        }
        if (i11 == 0) {
            Timeline timeline = playbackInfoUpdate.playbackInfo.timeline;
            int currentMediaItemIndex = -1;
            if (!this.playbackInfo.timeline.isEmpty() && timeline.isEmpty()) {
                this.maskingWindowIndex = -1;
                this.maskingWindowPositionMs = 0L;
            }
            if (!timeline.isEmpty()) {
                List<Timeline> childTimelines = ((PlaylistTimeline) timeline).getChildTimelines();
                ac.b.s(childTimelines.size() == this.mediaSourceHolderSnapshots.size());
                for (int i12 = 0; i12 < childTimelines.size(); i12++) {
                    this.mediaSourceHolderSnapshots.get(i12).updateTimeline(childTimelines.get(i12));
                }
            }
            boolean z10 = this.pendingDiscontinuity;
            long j11 = C.TIME_UNSET;
            if (z10) {
                boolean z11 = playbackInfoUpdate.playbackInfo.timeline.isEmpty() && this.playbackInfo.timeline.isEmpty();
                boolean zEquals = playbackInfoUpdate.playbackInfo.periodId.equals(this.playbackInfo.periodId);
                boolean z12 = playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs == this.playbackInfo.positionUs;
                if (z11 || (zEquals && z12)) {
                    z5 = false;
                }
                if (z5) {
                    currentMediaItemIndex = getCurrentMediaItemIndex();
                    if (timeline.isEmpty() || playbackInfoUpdate.playbackInfo.periodId.isAd()) {
                        jPeriodPositionUsToWindowPositionUs = playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs;
                    } else {
                        PlaybackInfo playbackInfo = playbackInfoUpdate.playbackInfo;
                        jPeriodPositionUsToWindowPositionUs = periodPositionUsToWindowPositionUs(timeline, playbackInfo.periodId, playbackInfo.discontinuityStartPositionUs);
                    }
                    j11 = jPeriodPositionUsToWindowPositionUs;
                }
                z = z5;
                long j12 = j11;
                i10 = currentMediaItemIndex;
                j10 = j12;
            } else {
                i10 = -1;
                j10 = -9223372036854775807L;
                z = false;
            }
            this.pendingDiscontinuity = false;
            updatePlaybackInfo(playbackInfoUpdate.playbackInfo, 1, z, this.pendingDiscontinuityReason, j10, i10, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this.wrappingPlayer, new Player.Events(flagSet));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.playbackInfoUpdateHandler.post(new c(this, playbackInfoUpdate, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3() {
        this.audioSessionIdState.setStateInBackground(Integer.valueOf(Util.generateAudioSessionIdV21(this.applicationContext)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$release$9(Player.Listener listener) {
        listener.onPlayerError(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(1), 1003));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$setAudioSessionId$13(int i10, Integer num) {
        if (i10 == 0) {
            i10 = num.intValue();
        }
        return Integer.valueOf(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Integer lambda$setAudioSessionId$14(int i10, Integer num) {
        if (i10 == 0) {
            i10 = Util.generateAudioSessionIdV21(this.applicationContext);
        }
        return Integer.valueOf(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaylistMetadata$11(Player.Listener listener) {
        listener.onPlaylistMetadataChanged(this.playlistMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAvailableCommands$31(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.availableCommands);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$17(PlaybackInfo playbackInfo, int i10, Player.Listener listener) {
        listener.onTimelineChanged(playbackInfo.timeline, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$18(int i10, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i10);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$20(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlayerErrorChanged(playbackInfo.playbackError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$21(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlayerError(playbackInfo.playbackError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$22(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onTracksChanged(playbackInfo.trackSelectorResult.tracks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$24(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onLoadingChanged(playbackInfo.isLoading);
        listener.onIsLoadingChanged(playbackInfo.isLoading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$25(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlayerStateChanged(playbackInfo.playWhenReady, playbackInfo.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$26(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlaybackStateChanged(playbackInfo.playbackState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$27(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlayWhenReadyChanged(playbackInfo.playWhenReady, playbackInfo.playWhenReadyChangeReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$28(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlaybackSuppressionReasonChanged(playbackInfo.playbackSuppressionReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$29(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onIsPlayingChanged(playbackInfo.isPlaying());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaybackInfo$30(PlaybackInfo playbackInfo, Player.Listener listener) {
        listener.onPlaybackParametersChanged(playbackInfo.playbackParameters);
    }

    private static PlaybackInfo maskPlaybackState(PlaybackInfo playbackInfo, int i10) {
        PlaybackInfo playbackInfoCopyWithPlaybackState = playbackInfo.copyWithPlaybackState(i10);
        return (i10 == 1 || i10 == 4) ? playbackInfoCopyWithPlaybackState.copyWithIsLoading(false) : playbackInfoCopyWithPlaybackState;
    }

    private PlaybackInfo maskTimelineAndPosition(PlaybackInfo playbackInfo, Timeline timeline, Pair<Object, Long> pair) {
        List<Metadata> list;
        ac.b.j(timeline.isEmpty() || pair != null);
        Timeline timeline2 = playbackInfo.timeline;
        long contentPositionInternal = getContentPositionInternal(playbackInfo);
        PlaybackInfo playbackInfoCopyWithTimeline = playbackInfo.copyWithTimeline(timeline);
        if (timeline.isEmpty()) {
            MediaSource.MediaPeriodId dummyPeriodForEmptyTimeline = PlaybackInfo.getDummyPeriodForEmptyTimeline();
            long jMsToUs = Util.msToUs(this.maskingWindowPositionMs);
            TrackGroupArray trackGroupArray = TrackGroupArray.EMPTY;
            TrackSelectorResult trackSelectorResult = this.emptyTrackSelectorResult;
            f1 f1Var = h1.f14020l;
            PlaybackInfo playbackInfoCopyWithLoadingMediaPeriodId = playbackInfoCopyWithTimeline.copyWithNewPosition(dummyPeriodForEmptyTimeline, jMsToUs, jMsToUs, jMsToUs, 0L, trackGroupArray, trackSelectorResult, o3.f14078o).copyWithLoadingMediaPeriodId(dummyPeriodForEmptyTimeline);
            playbackInfoCopyWithLoadingMediaPeriodId.bufferedPositionUs = playbackInfoCopyWithLoadingMediaPeriodId.positionUs;
            return playbackInfoCopyWithLoadingMediaPeriodId;
        }
        Object obj = playbackInfoCopyWithTimeline.periodId.periodUid;
        boolean zEquals = obj.equals(((Pair) Util.castNonNull(pair)).first);
        MediaSource.MediaPeriodId mediaPeriodId = !zEquals ? new MediaSource.MediaPeriodId(pair.first) : playbackInfoCopyWithTimeline.periodId;
        long jLongValue = ((Long) pair.second).longValue();
        long jMsToUs2 = Util.msToUs(contentPositionInternal);
        if (!timeline2.isEmpty()) {
            jMsToUs2 -= timeline2.getPeriodByUid(obj, this.period).getPositionInWindowUs();
            if (zEquals && jMsToUs2 - jLongValue == 1 && jMsToUs2 == timeline2.getPeriodByUid(obj, this.period).durationUs) {
                jMsToUs2--;
            }
        }
        if (!zEquals || jLongValue < jMsToUs2) {
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            ac.b.s(!mediaPeriodId2.isAd());
            TrackGroupArray trackGroupArray2 = !zEquals ? TrackGroupArray.EMPTY : playbackInfoCopyWithTimeline.trackGroups;
            TrackSelectorResult trackSelectorResult2 = !zEquals ? this.emptyTrackSelectorResult : playbackInfoCopyWithTimeline.trackSelectorResult;
            if (zEquals) {
                list = playbackInfoCopyWithTimeline.staticMetadata;
            } else {
                f1 f1Var2 = h1.f14020l;
                list = o3.f14078o;
            }
            PlaybackInfo playbackInfoCopyWithLoadingMediaPeriodId2 = playbackInfoCopyWithTimeline.copyWithNewPosition(mediaPeriodId2, jLongValue, jLongValue, jLongValue, 0L, trackGroupArray2, trackSelectorResult2, list).copyWithLoadingMediaPeriodId(mediaPeriodId2);
            playbackInfoCopyWithLoadingMediaPeriodId2.bufferedPositionUs = jLongValue;
            return playbackInfoCopyWithLoadingMediaPeriodId2;
        }
        if (jLongValue != jMsToUs2) {
            MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId;
            ac.b.s(!mediaPeriodId3.isAd());
            long jMax = Math.max(0L, playbackInfoCopyWithTimeline.totalBufferedDurationUs - (jLongValue - jMsToUs2));
            long j10 = playbackInfoCopyWithTimeline.bufferedPositionUs;
            if (playbackInfoCopyWithTimeline.loadingMediaPeriodId.equals(playbackInfoCopyWithTimeline.periodId)) {
                j10 = jLongValue + jMax;
            }
            PlaybackInfo playbackInfoCopyWithNewPosition = playbackInfoCopyWithTimeline.copyWithNewPosition(mediaPeriodId3, jLongValue, jLongValue, jLongValue, jMax, playbackInfoCopyWithTimeline.trackGroups, playbackInfoCopyWithTimeline.trackSelectorResult, playbackInfoCopyWithTimeline.staticMetadata);
            playbackInfoCopyWithNewPosition.bufferedPositionUs = j10;
            return playbackInfoCopyWithNewPosition;
        }
        int indexOfPeriod = timeline.getIndexOfPeriod(playbackInfoCopyWithTimeline.loadingMediaPeriodId.periodUid);
        if (indexOfPeriod != -1 && timeline.getPeriod(indexOfPeriod, this.period).windowIndex == timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex) {
            return playbackInfoCopyWithTimeline;
        }
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        long adDurationUs = mediaPeriodId.isAd() ? this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup) : this.period.durationUs;
        MediaSource.MediaPeriodId mediaPeriodId4 = mediaPeriodId;
        PlaybackInfo playbackInfoCopyWithLoadingMediaPeriodId3 = playbackInfoCopyWithTimeline.copyWithNewPosition(mediaPeriodId4, playbackInfoCopyWithTimeline.positionUs, playbackInfoCopyWithTimeline.positionUs, playbackInfoCopyWithTimeline.discontinuityStartPositionUs, adDurationUs - playbackInfoCopyWithTimeline.positionUs, playbackInfoCopyWithTimeline.trackGroups, playbackInfoCopyWithTimeline.trackSelectorResult, playbackInfoCopyWithTimeline.staticMetadata).copyWithLoadingMediaPeriodId(mediaPeriodId4);
        playbackInfoCopyWithLoadingMediaPeriodId3.bufferedPositionUs = adDurationUs;
        return playbackInfoCopyWithLoadingMediaPeriodId3;
    }

    private Pair<Object, Long> maskWindowPositionMsOrGetPeriodPositionUs(Timeline timeline, int i10, long j10) {
        if (timeline.isEmpty()) {
            this.maskingWindowIndex = i10;
            if (j10 == C.TIME_UNSET) {
                j10 = 0;
            }
            this.maskingWindowPositionMs = j10;
            return null;
        }
        if (i10 == -1 || i10 >= timeline.getWindowCount()) {
            i10 = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
            j10 = timeline.getWindow(i10, this.window).getDefaultPositionMs();
        }
        return timeline.getPeriodPositionUs(this.window, this.period, i10, Util.msToUs(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeNotifySurfaceSizeChanged(final int i10, final int i11) {
        if (i10 == this.surfaceSize.getWidth() && i11 == this.surfaceSize.getHeight()) {
            return;
        }
        this.surfaceSize = new Size(i10, i11);
        this.listeners.sendEvent(24, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.u
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onSurfaceSizeChanged(i10, i11);
            }
        });
        sendRendererMessage(2, 14, new Size(i10, i11));
    }

    private void maybeUpdatePlaybackSuppressionReason() {
        PlaybackInfo playbackInfo = this.playbackInfo;
        updatePlayWhenReady(playbackInfo.playWhenReady, playbackInfo.playWhenReadyChangeReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAudioSessionIdChanged(int i10, int i11) {
        verifyApplicationThread();
        sendRendererMessage(1, 10, Integer.valueOf(i11));
        sendRendererMessage(2, 10, Integer.valueOf(i11));
        this.listeners.sendEvent(21, new y(i11, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSelectedOutputSuitabilityChanged(boolean z) {
        if (this.playerReleased) {
            return;
        }
        if (!z) {
            maybeUpdatePlaybackSuppressionReason();
        } else if (this.playbackInfo.playbackSuppressionReason == 3) {
            maybeUpdatePlaybackSuppressionReason();
        }
    }

    private long periodPositionUsToWindowPositionUs(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j10) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return this.period.getPositionInWindowUs() + j10;
    }

    private PlaybackInfo removeMediaItemsInternal(PlaybackInfo playbackInfo, int i10, int i11) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(playbackInfo);
        long contentPositionInternal = getContentPositionInternal(playbackInfo);
        Timeline timeline = playbackInfo.timeline;
        int size = this.mediaSourceHolderSnapshots.size();
        this.pendingOperationAcks++;
        removeMediaSourceHolders(i10, i11);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline();
        PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(playbackInfo, timelineCreateMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(timeline, timelineCreateMaskingTimeline, currentWindowIndexInternal, contentPositionInternal));
        int i12 = playbackInfoMaskTimelineAndPosition.playbackState;
        if (i12 != 1 && i12 != 4 && i10 < i11 && i11 == size && currentWindowIndexInternal >= playbackInfoMaskTimelineAndPosition.timeline.getWindowCount()) {
            playbackInfoMaskTimelineAndPosition = maskPlaybackState(playbackInfoMaskTimelineAndPosition, 4);
        }
        this.internalPlayer.removeMediaSources(i10, i11, this.shuffleOrder);
        return playbackInfoMaskTimelineAndPosition;
    }

    private void removeMediaSourceHolders(int i10, int i11) {
        for (int i12 = i11 - 1; i12 >= i10; i12--) {
            this.mediaSourceHolderSnapshots.remove(i12);
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i10, i11);
    }

    private void removeSurfaceCallbacks() {
        if (this.sphericalGLSurfaceView != null) {
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload(null).send();
            this.sphericalGLSurfaceView.removeVideoSurfaceListener(this.componentListener);
            this.sphericalGLSurfaceView = null;
        }
        TextureView textureView = this.textureView;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != this.componentListener) {
                Log.w(TAG, "SurfaceTextureListener already unset or replaced.");
            } else {
                this.textureView.setSurfaceTextureListener(null);
            }
            this.textureView = null;
        }
        SurfaceHolder surfaceHolder = this.surfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.componentListener);
            this.surfaceHolder = null;
        }
    }

    private void sendRendererMessage(int i10, Object obj) {
        sendRendererMessage(-1, i10, obj);
    }

    private List<MediaSourceList.MediaSourceHolder> setMediaSourceHolders(List<MediaSource> list, int i10) {
        this.mediaSourceHolderSnapshots.clear();
        ArrayList arrayList = new ArrayList();
        for (int i11 = 0; i11 < list.size(); i11++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder(list.get(i11), this.useLazyPreparation);
            arrayList.add(mediaSourceHolder);
            this.mediaSourceHolderSnapshots.add(i11, new MediaSourceHolderSnapshot(mediaSourceHolder.uid, mediaSourceHolder.mediaSource));
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndSet(arrayList.size(), i10);
        return arrayList;
    }

    private void setMediaSourcesInternal(List<MediaSource> list, int i10, long j10, boolean z) {
        long j11;
        int i11;
        int i12;
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        long currentPosition = getCurrentPosition();
        this.pendingOperationAcks++;
        List<MediaSourceList.MediaSourceHolder> mediaSourceHolders = setMediaSourceHolders(list, i10);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline();
        if (!timelineCreateMaskingTimeline.isEmpty() && i10 >= timelineCreateMaskingTimeline.getWindowCount()) {
            throw new IllegalSeekPositionException(timelineCreateMaskingTimeline, i10, j10);
        }
        if (z) {
            int firstWindowIndex = timelineCreateMaskingTimeline.getFirstWindowIndex(this.shuffleModeEnabled);
            j11 = C.TIME_UNSET;
            i11 = firstWindowIndex;
        } else if (i10 == -1) {
            i11 = currentWindowIndexInternal;
            j11 = currentPosition;
        } else {
            j11 = j10;
            i11 = i10;
        }
        PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, timelineCreateMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(timelineCreateMaskingTimeline, i11, j11));
        if (playbackInfoMaskTimelineAndPosition.playbackState == 1) {
            i12 = 1;
        } else {
            i12 = 4;
            if (!timelineCreateMaskingTimeline.isEmpty()) {
                if (i11 == -1) {
                    i12 = playbackInfoMaskTimelineAndPosition.playbackState;
                } else if (i11 < timelineCreateMaskingTimeline.getWindowCount()) {
                    i12 = 2;
                }
            }
        }
        PlaybackInfo playbackInfoMaskPlaybackState = maskPlaybackState(playbackInfoMaskTimelineAndPosition, i12);
        this.internalPlayer.setMediaSources(mediaSourceHolders, i11, Util.msToUs(j11), this.shuffleOrder);
        updatePlaybackInfo(playbackInfoMaskPlaybackState, 0, (this.playbackInfo.periodId.periodUid.equals(playbackInfoMaskPlaybackState.periodId.periodUid) || this.playbackInfo.timeline.isEmpty()) ? false : true, 4, getCurrentPositionUsInternal(playbackInfoMaskPlaybackState), -1, false);
    }

    private void setNonVideoOutputSurfaceHolderInternal(SurfaceHolder surfaceHolder) {
        this.surfaceHolderSurfaceIsVideoOutput = false;
        this.surfaceHolder = surfaceHolder;
        surfaceHolder.addCallback(this.componentListener);
        Surface surface = this.surfaceHolder.getSurface();
        if (surface == null || !surface.isValid()) {
            maybeNotifySurfaceSizeChanged(0, 0);
        } else {
            Rect surfaceFrame = this.surfaceHolder.getSurfaceFrame();
            maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSurfaceTextureInternal(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        setVideoOutputInternal(surface);
        this.ownedSurface = surface;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoOutputInternal(Object obj) {
        Object obj2 = this.videoOutput;
        boolean z = (obj2 == null || obj2 == obj) ? false : true;
        boolean videoOutput = this.internalPlayer.setVideoOutput(obj, z ? this.detachSurfaceTimeoutMs : C.TIME_UNSET);
        if (z) {
            Object obj3 = this.videoOutput;
            Surface surface = this.ownedSurface;
            if (obj3 == surface) {
                surface.release();
                this.ownedSurface = null;
            }
        }
        this.videoOutput = obj;
        if (videoOutput) {
            return;
        }
        stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(3), 1003));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopInternal(ExoPlaybackException exoPlaybackException) {
        PlaybackInfo playbackInfo = this.playbackInfo;
        PlaybackInfo playbackInfoCopyWithLoadingMediaPeriodId = playbackInfo.copyWithLoadingMediaPeriodId(playbackInfo.periodId);
        playbackInfoCopyWithLoadingMediaPeriodId.bufferedPositionUs = playbackInfoCopyWithLoadingMediaPeriodId.positionUs;
        playbackInfoCopyWithLoadingMediaPeriodId.totalBufferedDurationUs = 0L;
        PlaybackInfo playbackInfoMaskPlaybackState = maskPlaybackState(playbackInfoCopyWithLoadingMediaPeriodId, 1);
        if (exoPlaybackException != null) {
            playbackInfoMaskPlaybackState = playbackInfoMaskPlaybackState.copyWithPlaybackError(exoPlaybackException);
        }
        this.pendingOperationAcks++;
        this.internalPlayer.stop();
        updatePlaybackInfo(playbackInfoMaskPlaybackState, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    private void updateAvailableCommands() {
        Player.Commands commands = this.availableCommands;
        Player.Commands availableCommands = Util.getAvailableCommands(this.wrappingPlayer, this.permanentAvailableCommands);
        this.availableCommands = availableCommands;
        if (availableCommands.equals(commands)) {
            return;
        }
        this.listeners.queueEvent(13, new z(this, 5));
    }

    private void updateMediaSourcesWithMediaItems(int i10, int i11, List<MediaItem> list) {
        this.pendingOperationAcks++;
        this.internalPlayer.updateMediaSourcesWithMediaItems(i10, i11, list);
        for (int i12 = i10; i12 < i11; i12++) {
            MediaSourceHolderSnapshot mediaSourceHolderSnapshot = this.mediaSourceHolderSnapshots.get(i12);
            mediaSourceHolderSnapshot.updateTimeline(new TimelineWithUpdatedMediaItem(mediaSourceHolderSnapshot.getTimeline(), list.get(i12 - i10)));
        }
        updatePlaybackInfo(this.playbackInfo.copyWithTimeline(createMaskingTimeline()), 0, false, 4, C.TIME_UNSET, -1, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayWhenReady(boolean z, int i10) {
        int iComputePlaybackSuppressionReason = computePlaybackSuppressionReason(z);
        PlaybackInfo playbackInfoCopyWithEstimatedPosition = this.playbackInfo;
        if (playbackInfoCopyWithEstimatedPosition.playWhenReady == z && playbackInfoCopyWithEstimatedPosition.playbackSuppressionReason == iComputePlaybackSuppressionReason && playbackInfoCopyWithEstimatedPosition.playWhenReadyChangeReason == i10) {
            return;
        }
        this.pendingOperationAcks++;
        if (playbackInfoCopyWithEstimatedPosition.sleepingForOffload) {
            playbackInfoCopyWithEstimatedPosition = playbackInfoCopyWithEstimatedPosition.copyWithEstimatedPosition();
        }
        PlaybackInfo playbackInfoCopyWithPlayWhenReady = playbackInfoCopyWithEstimatedPosition.copyWithPlayWhenReady(z, i10, iComputePlaybackSuppressionReason);
        this.internalPlayer.setPlayWhenReady(z, i10, iComputePlaybackSuppressionReason);
        updatePlaybackInfo(playbackInfoCopyWithPlayWhenReady, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    private void updatePlaybackInfo(final PlaybackInfo playbackInfo, final int i10, boolean z, final int i11, long j10, int i12, boolean z5) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        this.playbackInfo = playbackInfo;
        boolean zEquals = playbackInfo2.timeline.equals(playbackInfo.timeline);
        Pair<Boolean, Integer> pairEvaluateMediaItemTransitionReason = evaluateMediaItemTransitionReason(playbackInfo, playbackInfo2, z, i11, !zEquals, z5);
        boolean zBooleanValue = ((Boolean) pairEvaluateMediaItemTransitionReason.first).booleanValue();
        final int iIntValue = ((Integer) pairEvaluateMediaItemTransitionReason.second).intValue();
        if (zBooleanValue) {
            mediaItem = playbackInfo.timeline.isEmpty() ? null : playbackInfo.timeline.getWindow(playbackInfo.timeline.getPeriodByUid(playbackInfo.periodId.periodUid, this.period).windowIndex, this.window).mediaItem;
            this.staticAndDynamicMediaMetadata = MediaMetadata.EMPTY;
        }
        if (zBooleanValue || !playbackInfo2.staticMetadata.equals(playbackInfo.staticMetadata)) {
            this.staticAndDynamicMediaMetadata = this.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(playbackInfo.staticMetadata).build();
        }
        MediaMetadata mediaMetadataBuildUpdatedMediaMetadata = buildUpdatedMediaMetadata();
        boolean zEquals2 = mediaMetadataBuildUpdatedMediaMetadata.equals(this.mediaMetadata);
        this.mediaMetadata = mediaMetadataBuildUpdatedMediaMetadata;
        boolean z10 = playbackInfo2.playWhenReady != playbackInfo.playWhenReady;
        boolean z11 = playbackInfo2.playbackState != playbackInfo.playbackState;
        if (z11 || z10) {
            updateWakeAndWifiLock();
        }
        boolean z12 = playbackInfo2.isLoading;
        boolean z13 = playbackInfo.isLoading;
        boolean z14 = z12 != z13;
        if (z14) {
            updatePriorityTaskManagerForIsLoadingChange(z13);
        }
        if (!zEquals) {
            final int i13 = 0;
            this.listeners.queueEvent(0, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.o
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i13) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$17((PlaybackInfo) playbackInfo, i10, (Player.Listener) obj);
                            break;
                        default:
                            ((Player.Listener) obj).onMediaItemTransition((MediaItem) playbackInfo, i10);
                            break;
                    }
                }
            });
        }
        if (z) {
            final Player.PositionInfo previousPositionInfo = getPreviousPositionInfo(i11, playbackInfo2, i12);
            final Player.PositionInfo positionInfo = getPositionInfo(j10);
            this.listeners.queueEvent(11, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.a0
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ExoPlayerImpl.lambda$updatePlaybackInfo$18(i11, previousPositionInfo, positionInfo, (Player.Listener) obj);
                }
            });
        }
        if (zBooleanValue) {
            final int i14 = 1;
            this.listeners.queueEvent(1, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.o
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i14) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$17((PlaybackInfo) mediaItem, iIntValue, (Player.Listener) obj);
                            break;
                        default:
                            ((Player.Listener) obj).onMediaItemTransition((MediaItem) mediaItem, iIntValue);
                            break;
                    }
                }
            });
        }
        if (playbackInfo2.playbackError != playbackInfo.playbackError) {
            final int i15 = 7;
            this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i15) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
            if (playbackInfo.playbackError != null) {
                final int i16 = 8;
                this.listeners.queueEvent(10, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                    @Override // androidx.media3.common.util.ListenerSet.Event
                    public final void invoke(Object obj) {
                        switch (i16) {
                            case 0:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                                break;
                            case 1:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                                break;
                            case 2:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                                break;
                            case 3:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                                break;
                            case 4:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                                break;
                            case 5:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                                break;
                            case 6:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                                break;
                            case 7:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                                break;
                            case 8:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                                break;
                            default:
                                ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                                break;
                        }
                    }
                });
            }
        }
        TrackSelectorResult trackSelectorResult = playbackInfo2.trackSelectorResult;
        TrackSelectorResult trackSelectorResult2 = playbackInfo.trackSelectorResult;
        if (trackSelectorResult != trackSelectorResult2) {
            this.trackSelector.onSelectionActivated(trackSelectorResult2.info);
            final int i17 = 9;
            this.listeners.queueEvent(2, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i17) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!zEquals2) {
            this.listeners.queueEvent(14, new p(this.mediaMetadata, 0));
        }
        if (z14) {
            final int i18 = 0;
            this.listeners.queueEvent(3, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i18) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z11 || z10) {
            final int i19 = 1;
            this.listeners.queueEvent(-1, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i19) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z11) {
            final int i20 = 2;
            this.listeners.queueEvent(4, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i20) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (z10 || playbackInfo2.playWhenReadyChangeReason != playbackInfo.playWhenReadyChangeReason) {
            final int i21 = 3;
            this.listeners.queueEvent(5, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i21) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playbackInfo2.playbackSuppressionReason != playbackInfo.playbackSuppressionReason) {
            final int i22 = 4;
            this.listeners.queueEvent(6, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i22) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (playbackInfo2.isPlaying() != playbackInfo.isPlaying()) {
            final int i23 = 5;
            this.listeners.queueEvent(7, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i23) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        if (!playbackInfo2.playbackParameters.equals(playbackInfo.playbackParameters)) {
            final int i24 = 6;
            this.listeners.queueEvent(12, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    switch (i24) {
                        case 0:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$24(playbackInfo, (Player.Listener) obj);
                            break;
                        case 1:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$25(playbackInfo, (Player.Listener) obj);
                            break;
                        case 2:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$26(playbackInfo, (Player.Listener) obj);
                            break;
                        case 3:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$27(playbackInfo, (Player.Listener) obj);
                            break;
                        case 4:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$28(playbackInfo, (Player.Listener) obj);
                            break;
                        case 5:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$29(playbackInfo, (Player.Listener) obj);
                            break;
                        case 6:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$30(playbackInfo, (Player.Listener) obj);
                            break;
                        case 7:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$20(playbackInfo, (Player.Listener) obj);
                            break;
                        case 8:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$21(playbackInfo, (Player.Listener) obj);
                            break;
                        default:
                            ExoPlayerImpl.lambda$updatePlaybackInfo$22(playbackInfo, (Player.Listener) obj);
                            break;
                    }
                }
            });
        }
        updateAvailableCommands();
        this.listeners.flushEvents();
        if (playbackInfo2.sleepingForOffload != playbackInfo.sleepingForOffload) {
            Iterator<ExoPlayer.AudioOffloadListener> it = this.audioOffloadListeners.iterator();
            while (it.hasNext()) {
                it.next().onSleepingForOffloadChanged(playbackInfo.sleepingForOffload);
            }
        }
    }

    private void updatePriorityTaskManagerForIsLoadingChange(boolean z) {
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null) {
            if (z && !this.isPriorityTaskManagerRegistered) {
                priorityTaskManager.add(this.priority);
                this.isPriorityTaskManagerRegistered = true;
            } else {
                if (z || !this.isPriorityTaskManagerRegistered) {
                    return;
                }
                priorityTaskManager.remove(this.priority);
                this.isPriorityTaskManagerRegistered = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWakeAndWifiLock() {
        int playbackState = getPlaybackState();
        boolean z = false;
        if (playbackState != 1) {
            if (playbackState == 2 || playbackState == 3) {
                boolean zIsSleepingForOffload = isSleepingForOffload();
                WakeLockManager wakeLockManager = this.wakeLockManager;
                if (getPlayWhenReady() && !zIsSleepingForOffload) {
                    z = true;
                }
                wakeLockManager.setStayAwake(z);
                this.wifiLockManager.setStayAwake(getPlayWhenReady());
                return;
            }
            if (playbackState != 4) {
                throw new IllegalStateException();
            }
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
    }

    private void verifyApplicationThread() {
        this.constructorFinished.blockUninterruptible();
        if (Thread.currentThread() != getApplicationLooper().getThread()) {
            String invariant = Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), getApplicationLooper().getThread().getName());
            if (this.throwsWhenUsingWrongThread) {
                throw new IllegalStateException(invariant);
            }
            Log.w(TAG, invariant, this.hasNotifiedFullWrongThreadWarning ? null : new IllegalStateException());
            this.hasNotifiedFullWrongThreadWarning = true;
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        AnalyticsCollector analyticsCollector = this.analyticsCollector;
        analyticsListener.getClass();
        analyticsCollector.addListener(analyticsListener);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addAudioCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener, List<String> list) {
        verifyApplicationThread();
        codecParametersChangeListener.getClass();
        list.getClass();
        this.audioListenerManager.addListener(codecParametersChangeListener, list);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        this.audioOffloadListeners.add(audioOffloadListener);
    }

    @Override // androidx.media3.common.Player
    public void addListener(Player.Listener listener) {
        ListenerSet<Player.Listener> listenerSet = this.listeners;
        listener.getClass();
        listenerSet.add(listener);
    }

    @Override // androidx.media3.common.Player
    public void addMediaItems(int i10, List<MediaItem> list) {
        verifyApplicationThread();
        addMediaSources(i10, createMediaSources(list));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(Collections.singletonList(mediaSource));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        addMediaSources(this.mediaSourceHolderSnapshots.size(), list);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addVideoCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener, List<String> list) {
        verifyApplicationThread();
        codecParametersChangeListener.getClass();
        list.getClass();
        this.videoListenerManager.addListener(codecParametersChangeListener, list);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void clearAuxEffectInfo() {
        verifyApplicationThread();
        setAuxEffectInfo(new AuxEffectInfo(0, 0.0f));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void clearCameraMotionListener(CameraMotionListener cameraMotionListener) {
        verifyApplicationThread();
        if (this.cameraMotionListener != cameraMotionListener) {
            return;
        }
        createMessageInternal(this.frameMetadataListener).setType(8).setPayload(null).send();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        verifyApplicationThread();
        if (this.videoFrameMetadataListener != videoFrameMetadataListener) {
            return;
        }
        createMessageInternal(this.frameMetadataListener).setType(7).setPayload(null).send();
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurface() {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(null);
        maybeNotifySurfaceSizeChanged(0, 0);
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (surfaceHolder == null || surfaceHolder != this.surfaceHolder) {
            return;
        }
        clearVideoSurface();
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        clearVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
    }

    @Override // androidx.media3.common.Player
    public void clearVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (textureView == null || textureView != this.textureView) {
            return;
        }
        clearVideoSurface();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public PlayerMessage createMessage(PlayerMessage.Target target) {
        verifyApplicationThread();
        return createMessageInternal(target);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.decreaseVolume(1);
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public AnalyticsCollector getAnalyticsCollector() {
        verifyApplicationThread();
        return this.analyticsCollector;
    }

    @Override // androidx.media3.common.Player
    public Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    @Override // androidx.media3.common.Player
    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return this.audioAttributes;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public DecoderCounters getAudioDecoderCounters() {
        verifyApplicationThread();
        return this.audioDecoderCounters;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Format getAudioFormat() {
        verifyApplicationThread();
        return this.audioFormat;
    }

    @Override // androidx.media3.common.BasePlayer, androidx.media3.common.Player
    public int getAudioSessionId() {
        verifyApplicationThread();
        return this.audioSessionIdState.get().intValue();
    }

    @Override // androidx.media3.common.Player
    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return this.availableCommands;
    }

    @Override // androidx.media3.common.Player
    public long getBufferedPosition() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentBufferedPosition();
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        return playbackInfo.loadingMediaPeriodId.equals(playbackInfo.periodId) ? Util.usToMs(this.playbackInfo.bufferedPositionUs) : getDuration();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Clock getClock() {
        return this.clock;
    }

    @Override // androidx.media3.common.Player
    public long getContentBufferedPosition() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingWindowPositionMs;
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.loadingMediaPeriodId.windowSequenceNumber != playbackInfo.periodId.windowSequenceNumber) {
            return playbackInfo.timeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
        }
        long j10 = playbackInfo.bufferedPositionUs;
        if (this.playbackInfo.loadingMediaPeriodId.isAd()) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            Timeline.Period periodByUid = playbackInfo2.timeline.getPeriodByUid(playbackInfo2.loadingMediaPeriodId.periodUid, this.period);
            long adGroupTimeUs = periodByUid.getAdGroupTimeUs(this.playbackInfo.loadingMediaPeriodId.adGroupIndex);
            j10 = adGroupTimeUs == Long.MIN_VALUE ? periodByUid.durationUs : adGroupTimeUs;
        }
        PlaybackInfo playbackInfo3 = this.playbackInfo;
        return Util.usToMs(periodPositionUsToWindowPositionUs(playbackInfo3.timeline, playbackInfo3.loadingMediaPeriodId, j10));
    }

    @Override // androidx.media3.common.Player
    public long getContentPosition() {
        verifyApplicationThread();
        return getContentPositionInternal(this.playbackInfo);
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    @Override // androidx.media3.common.Player
    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return this.currentCueGroup;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        if (currentWindowIndexInternal == -1) {
            return 0;
        }
        return currentWindowIndexInternal;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (!this.playbackInfo.timeline.isEmpty()) {
            PlaybackInfo playbackInfo = this.playbackInfo;
            return playbackInfo.timeline.getIndexOfPeriod(playbackInfo.periodId.periodUid);
        }
        int i10 = this.maskingWindowIndex;
        if (i10 == -1) {
            return 0;
        }
        return i10;
    }

    @Override // androidx.media3.common.Player
    public long getCurrentPosition() {
        verifyApplicationThread();
        return Util.usToMs(getCurrentPositionUsInternal(this.playbackInfo));
    }

    @Override // androidx.media3.common.Player
    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return this.playbackInfo.timeline;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public TrackGroupArray getCurrentTrackGroups() {
        verifyApplicationThread();
        return this.playbackInfo.trackGroups;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public TrackSelectionArray getCurrentTrackSelections() {
        verifyApplicationThread();
        return new TrackSelectionArray(this.playbackInfo.trackSelectorResult.selections);
    }

    @Override // androidx.media3.common.Player
    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return this.playbackInfo.trackSelectorResult.tracks;
    }

    @Override // androidx.media3.common.Player
    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return this.deviceInfo;
    }

    @Override // androidx.media3.common.Player
    public int getDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            return streamVolumeManager.getVolume();
        }
        return 0;
    }

    @Override // androidx.media3.common.Player
    public long getDuration() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        PlaybackInfo playbackInfo = this.playbackInfo;
        MediaSource.MediaPeriodId mediaPeriodId = playbackInfo.periodId;
        playbackInfo.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return Util.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    @Override // androidx.media3.common.Player
    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return this.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return this.mediaMetadata;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean getPauseAtEndOfMediaItems() {
        verifyApplicationThread();
        return this.pauseAtEndOfMediaItems;
    }

    @Override // androidx.media3.common.Player
    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return this.playbackInfo.playWhenReady;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    @Override // androidx.media3.common.Player
    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return this.playbackInfo.playbackParameters;
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackState() {
        verifyApplicationThread();
        return this.playbackInfo.playbackState;
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return this.playbackInfo.playbackSuppressionReason;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return this.playlistMetadata;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public ExoPlayer.PreloadConfiguration getPreloadConfiguration() {
        return this.preloadConfiguration;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Renderer getRenderer(int i10) {
        verifyApplicationThread();
        return this.renderers[i10];
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public int getRendererCount() {
        verifyApplicationThread();
        return this.renderers.length;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public int getRendererType(int i10) {
        verifyApplicationThread();
        return this.renderers[i10].getTrackType();
    }

    @Override // androidx.media3.common.Player
    public int getRepeatMode() {
        verifyApplicationThread();
        return this.repeatMode;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public ScrubbingModeParameters getScrubbingModeParameters() {
        verifyApplicationThread();
        return this.scrubbingModeParameters;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Renderer getSecondaryRenderer(int i10) {
        verifyApplicationThread();
        return this.secondaryRenderers[i10];
    }

    @Override // androidx.media3.common.Player
    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return this.seekBackIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return this.seekForwardIncrementMs;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public SeekParameters getSeekParameters() {
        verifyApplicationThread();
        return this.seekParameters;
    }

    @Override // androidx.media3.common.Player
    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return this.shuffleModeEnabled;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public ShuffleOrder getShuffleOrder() {
        verifyApplicationThread();
        return this.shuffleOrder;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean getSkipSilenceEnabled() {
        verifyApplicationThread();
        return this.skipSilenceEnabled;
    }

    @Override // androidx.media3.common.Player
    public Size getSurfaceSize() {
        verifyApplicationThread();
        return this.surfaceSize;
    }

    @Override // androidx.media3.common.Player
    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return Util.usToMs(this.playbackInfo.totalBufferedDurationUs);
    }

    @Override // androidx.media3.common.Player
    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        TrackSelectionParameters parameters = this.trackSelector.getParameters();
        return this.scrubbingModeEnabled ? parameters.buildUpon().setDisabledTrackTypes(this.disabledTrackTypesWithoutScrubbingMode).build() : parameters;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public TrackSelector getTrackSelector() {
        verifyApplicationThread();
        return this.trackSelector;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public int getVideoChangeFrameRateStrategy() {
        verifyApplicationThread();
        return this.videoChangeFrameRateStrategy;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public DecoderCounters getVideoDecoderCounters() {
        verifyApplicationThread();
        return this.videoDecoderCounters;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Format getVideoFormat() {
        verifyApplicationThread();
        return this.videoFormat;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public int getVideoScalingMode() {
        verifyApplicationThread();
        return this.videoScalingMode;
    }

    @Override // androidx.media3.common.Player
    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return this.videoSize;
    }

    @Override // androidx.media3.common.Player
    public float getVolume() {
        verifyApplicationThread();
        return this.volume;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void increaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.increaseVolume(1);
        }
    }

    @Override // androidx.media3.common.Player
    public boolean isDeviceMuted() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            return streamVolumeManager.isMuted();
        }
        return false;
    }

    @Override // androidx.media3.common.Player
    public boolean isLoading() {
        verifyApplicationThread();
        return this.playbackInfo.isLoading;
    }

    @Override // androidx.media3.common.Player
    public boolean isPlayingAd() {
        verifyApplicationThread();
        return this.playbackInfo.periodId.isAd();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean isReleased() {
        verifyApplicationThread();
        return this.playerReleased;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean isScrubbingModeEnabled() {
        verifyApplicationThread();
        return this.scrubbingModeEnabled;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean isSleepingForOffload() {
        verifyApplicationThread();
        return this.playbackInfo.sleepingForOffload;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public boolean isTunnelingEnabled() {
        verifyApplicationThread();
        for (RendererConfiguration rendererConfiguration : this.playbackInfo.trackSelectorResult.rendererConfigurations) {
            if (rendererConfiguration != null && rendererConfiguration.tunneling) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.common.Player
    public void moveMediaItems(int i10, int i11, int i12) {
        verifyApplicationThread();
        ac.b.j(i10 >= 0 && i10 <= i11 && i12 >= 0);
        int size = this.mediaSourceHolderSnapshots.size();
        int iMin = Math.min(i11, size);
        int iMin2 = Math.min(i12, size - (iMin - i10));
        if (i10 >= size || i10 == iMin || i10 == iMin2) {
            return;
        }
        Timeline currentTimeline = getCurrentTimeline();
        this.pendingOperationAcks++;
        Util.moveItems(this.mediaSourceHolderSnapshots, i10, iMin, iMin2);
        this.shuffleOrder = this.shuffleOrder.cloneAndMove(i10, iMin, iMin2);
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline();
        PlaybackInfo playbackInfo = this.playbackInfo;
        PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(playbackInfo, timelineCreateMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(currentTimeline, timelineCreateMaskingTimeline, getCurrentWindowIndexInternal(playbackInfo), getContentPositionInternal(this.playbackInfo)));
        this.internalPlayer.moveMediaSources(i10, iMin, iMin2, this.shuffleOrder);
        updatePlaybackInfo(playbackInfoMaskTimelineAndPosition, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    @Override // androidx.media3.common.Player
    public void mute() {
        verifyApplicationThread();
        if (this.volume != 0.0f) {
            setVolume(0.0f);
        }
    }

    @Override // androidx.media3.common.Player
    public void prepare() {
        verifyApplicationThread();
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.playbackState != 1) {
            return;
        }
        PlaybackInfo playbackInfoCopyWithPlaybackError = playbackInfo.copyWithPlaybackError(null);
        PlaybackInfo playbackInfoMaskPlaybackState = maskPlaybackState(playbackInfoCopyWithPlaybackError, playbackInfoCopyWithPlaybackError.timeline.isEmpty() ? 4 : 2);
        this.pendingOperationAcks++;
        this.internalPlayer.prepare();
        updatePlaybackInfo(playbackInfoMaskPlaybackState, 1, false, 5, C.TIME_UNSET, -1, false);
    }

    @Override // androidx.media3.common.Player
    public void release() {
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.9.0] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        verifyApplicationThread();
        this.audioBecomingNoisyManager.setEnabled(false);
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.release();
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        SuitableOutputChecker suitableOutputChecker = this.suitableOutputChecker;
        if (suitableOutputChecker != null) {
            suitableOutputChecker.disable();
        }
        VirtualDeviceIdChangeListener virtualDeviceIdChangeListener = this.virtualDeviceIdChangeListener;
        if (virtualDeviceIdChangeListener != null && Build.VERSION.SDK_INT >= 34) {
            virtualDeviceIdChangeListener.release();
        }
        this.stuckPlayerDetector.release();
        if (!this.internalPlayer.release()) {
            this.listeners.sendEvent(10, new w());
        }
        this.listeners.release();
        this.playbackInfoUpdateHandler.removeCallbacksAndMessages(null);
        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (playbackInfo.sleepingForOffload) {
            this.playbackInfo = playbackInfo.copyWithEstimatedPosition();
        }
        PlaybackInfo playbackInfoMaskPlaybackState = maskPlaybackState(this.playbackInfo, 1);
        this.playbackInfo = playbackInfoMaskPlaybackState;
        PlaybackInfo playbackInfoCopyWithLoadingMediaPeriodId = playbackInfoMaskPlaybackState.copyWithLoadingMediaPeriodId(playbackInfoMaskPlaybackState.periodId);
        this.playbackInfo = playbackInfoCopyWithLoadingMediaPeriodId;
        playbackInfoCopyWithLoadingMediaPeriodId.bufferedPositionUs = playbackInfoCopyWithLoadingMediaPeriodId.positionUs;
        this.playbackInfo.totalBufferedDurationUs = 0L;
        this.analyticsCollector.release();
        removeSurfaceCallbacks();
        Surface surface = this.ownedSurface;
        if (surface != null) {
            surface.release();
            this.ownedSurface = null;
        }
        if (this.isPriorityTaskManagerRegistered) {
            PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
            priorityTaskManager.getClass();
            priorityTaskManager.remove(this.priority);
            this.isPriorityTaskManagerRegistered = false;
        }
        this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;
        this.playerReleased = true;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        verifyApplicationThread();
        AnalyticsCollector analyticsCollector = this.analyticsCollector;
        analyticsListener.getClass();
        analyticsCollector.removeListener(analyticsListener);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeAudioCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener) {
        verifyApplicationThread();
        codecParametersChangeListener.getClass();
        this.audioListenerManager.removeListener(codecParametersChangeListener);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        verifyApplicationThread();
        this.audioOffloadListeners.remove(audioOffloadListener);
    }

    @Override // androidx.media3.common.Player
    public void removeListener(Player.Listener listener) {
        verifyApplicationThread();
        ListenerSet<Player.Listener> listenerSet = this.listeners;
        listener.getClass();
        listenerSet.remove(listener);
    }

    @Override // androidx.media3.common.Player
    public void removeMediaItems(int i10, int i11) {
        verifyApplicationThread();
        ac.b.j(i10 >= 0 && i11 >= i10);
        int size = this.mediaSourceHolderSnapshots.size();
        int iMin = Math.min(i11, size);
        if (i10 >= size || i10 == iMin) {
            return;
        }
        PlaybackInfo playbackInfoRemoveMediaItemsInternal = removeMediaItemsInternal(this.playbackInfo, i10, iMin);
        updatePlaybackInfo(playbackInfoRemoveMediaItemsInternal, 0, !playbackInfoRemoveMediaItemsInternal.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(playbackInfoRemoveMediaItemsInternal), -1, false);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeVideoCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener) {
        verifyApplicationThread();
        codecParametersChangeListener.getClass();
        this.videoListenerManager.removeListener(codecParametersChangeListener);
    }

    @Override // androidx.media3.common.Player
    public void replaceMediaItems(int i10, int i11, List<MediaItem> list) {
        verifyApplicationThread();
        ac.b.j(i10 >= 0 && i11 >= i10);
        int size = this.mediaSourceHolderSnapshots.size();
        if (i10 > size) {
            return;
        }
        int iMin = Math.min(i11, size);
        if (canUpdateMediaSourcesWithMediaItems(i10, iMin, list)) {
            updateMediaSourcesWithMediaItems(i10, iMin, list);
            return;
        }
        List<MediaSource> listCreateMediaSources = createMediaSources(list);
        if (this.playbackInfo.timeline.isEmpty()) {
            setMediaSources(listCreateMediaSources, this.maskingWindowIndex == -1);
        } else {
            PlaybackInfo playbackInfoRemoveMediaItemsInternal = removeMediaItemsInternal(addMediaSourcesInternal(this.playbackInfo, iMin, listCreateMediaSources), i10, iMin);
            updatePlaybackInfo(playbackInfoRemoveMediaItemsInternal, 0, !playbackInfoRemoveMediaItemsInternal.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(playbackInfoRemoveMediaItemsInternal), -1, false);
        }
    }

    @Override // androidx.media3.common.BasePlayer
    public void seekTo(int i10, long j10, int i11, boolean z) {
        verifyApplicationThread();
        if (i10 == -1) {
            return;
        }
        ac.b.j(i10 >= 0);
        Timeline timeline = this.playbackInfo.timeline;
        if (timeline.isEmpty() || i10 < timeline.getWindowCount()) {
            this.analyticsCollector.notifySeekStarted();
            this.pendingOperationAcks++;
            if (isPlayingAd()) {
                Log.w(TAG, "seekTo ignored because an ad is playing");
                ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate = new ExoPlayerImplInternal.PlaybackInfoUpdate(this.playbackInfo);
                playbackInfoUpdate.incrementPendingOperationAcks(1);
                this.playbackInfoUpdateListener.onPlaybackInfoUpdate(playbackInfoUpdate);
                return;
            }
            PlaybackInfo playbackInfoMaskPlaybackState = this.playbackInfo;
            int i12 = playbackInfoMaskPlaybackState.playbackState;
            if (i12 == 3 || (i12 == 4 && !timeline.isEmpty())) {
                playbackInfoMaskPlaybackState = maskPlaybackState(this.playbackInfo, 2);
            }
            int currentMediaItemIndex = getCurrentMediaItemIndex();
            PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(playbackInfoMaskPlaybackState, timeline, maskWindowPositionMsOrGetPeriodPositionUs(timeline, i10, j10));
            this.internalPlayer.seekTo(timeline, i10, Util.msToUs(j10));
            updatePlaybackInfo(playbackInfoMaskTimelineAndPosition, 0, true, 1, getCurrentPositionUsInternal(playbackInfoMaskTimelineAndPosition), currentMediaItemIndex, z);
        }
    }

    @Override // androidx.media3.common.Player
    public void setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        verifyApplicationThread();
        if (this.playerReleased) {
            return;
        }
        if (!Objects.equals(this.audioAttributes, audioAttributes)) {
            this.audioAttributes = audioAttributes;
            sendRendererMessage(1, 3, audioAttributes);
            StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
            if (streamVolumeManager != null) {
                streamVolumeManager.setStreamType(audioAttributes.getStreamType());
            }
            this.listeners.queueEvent(20, new p(audioAttributes, 1));
        }
        this.internalPlayer.setAudioAttributes(this.audioAttributes, z);
        this.listeners.flushEvents();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setAudioCodecParameters(CodecParameters codecParameters) {
        verifyApplicationThread();
        codecParameters.getClass();
        sendRendererMessage(1, 21, codecParameters);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setAudioSessionId(int i10) {
        verifyApplicationThread();
        if (this.audioSessionIdState.get().intValue() == i10) {
            return;
        }
        this.audioSessionIdState.updateStateAsync(new v(i10, 0), new w0(this, i10, 3));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        verifyApplicationThread();
        sendRendererMessage(1, 6, auxEffectInfo);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setCameraMotionListener(CameraMotionListener cameraMotionListener) {
        verifyApplicationThread();
        this.cameraMotionListener = cameraMotionListener;
        createMessageInternal(this.frameMetadataListener).setType(8).setPayload(cameraMotionListener).send();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.setMuted(z, 1);
        }
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceVolume(int i10) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.setVolume(i10, 1);
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setForegroundMode(boolean z) {
        verifyApplicationThread();
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (this.internalPlayer.setForegroundMode(z)) {
                return;
            }
            stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(2), 1003));
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setHandleAudioBecomingNoisy(boolean z) {
        verifyApplicationThread();
        if (this.playerReleased) {
            return;
        }
        this.audioBecomingNoisyManager.setEnabled(z);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setImageOutput(ImageOutput imageOutput) {
        verifyApplicationThread();
        sendRendererMessage(4, 15, imageOutput);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMaxSeekToPreviousPositionMs(long j10) {
        verifyApplicationThread();
        ac.b.j(j10 >= 0);
        if (this.maxSeekToPreviousPositionMs == j10) {
            return;
        }
        this.maxSeekToPreviousPositionMs = j10;
        this.listeners.sendEvent(18, new x(j10, 1));
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), z);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        setMediaSources(list, true);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setPauseAtEndOfMediaItems(boolean z) {
        verifyApplicationThread();
        if (this.pauseAtEndOfMediaItems == z) {
            return;
        }
        this.pauseAtEndOfMediaItems = z;
        this.internalPlayer.setPauseAtEndOfWindow(z);
    }

    @Override // androidx.media3.common.Player
    public void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        updatePlayWhenReady(z, 1);
    }

    @Override // androidx.media3.common.Player
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        if (this.playbackInfo.playbackParameters.equals(playbackParameters)) {
            return;
        }
        PlaybackInfo playbackInfoCopyWithPlaybackParameters = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
        this.pendingOperationAcks++;
        this.internalPlayer.setPlaybackParameters(playbackParameters);
        updatePlaybackInfo(playbackInfoCopyWithPlaybackParameters, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    @Override // androidx.media3.common.Player
    public void setPlaylistMetadata(MediaMetadata mediaMetadata) {
        verifyApplicationThread();
        mediaMetadata.getClass();
        if (mediaMetadata.equals(this.playlistMetadata)) {
            return;
        }
        this.playlistMetadata = mediaMetadata;
        this.listeners.sendEvent(15, new z(this, 0));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setPreferredAudioDevice(AudioDeviceInfo audioDeviceInfo) {
        verifyApplicationThread();
        sendRendererMessage(1, 12, audioDeviceInfo);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setPreloadConfiguration(ExoPlayer.PreloadConfiguration preloadConfiguration) {
        verifyApplicationThread();
        if (this.preloadConfiguration.equals(preloadConfiguration)) {
            return;
        }
        this.preloadConfiguration = preloadConfiguration;
        this.internalPlayer.setPreloadConfiguration(preloadConfiguration);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setPriority(int i10) {
        verifyApplicationThread();
        if (this.priority == i10) {
            return;
        }
        if (this.isPriorityTaskManagerRegistered) {
            PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
            priorityTaskManager.getClass();
            priorityTaskManager.add(i10);
            priorityTaskManager.remove(this.priority);
        }
        this.priority = i10;
        sendRendererMessage(16, Integer.valueOf(i10));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setPriorityTaskManager(PriorityTaskManager priorityTaskManager) {
        verifyApplicationThread();
        if (Objects.equals(this.priorityTaskManager, priorityTaskManager)) {
            return;
        }
        if (this.isPriorityTaskManagerRegistered) {
            PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
            priorityTaskManager2.getClass();
            priorityTaskManager2.remove(this.priority);
        }
        if (priorityTaskManager == null || !isLoading()) {
            this.isPriorityTaskManagerRegistered = false;
        } else {
            priorityTaskManager.add(this.priority);
            this.isPriorityTaskManagerRegistered = true;
        }
        this.priorityTaskManager = priorityTaskManager;
    }

    @Override // androidx.media3.common.Player
    public void setRepeatMode(int i10) {
        verifyApplicationThread();
        if (this.repeatMode != i10) {
            this.repeatMode = i10;
            this.internalPlayer.setRepeatMode(i10);
            this.listeners.queueEvent(8, new y(i10, 0));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setScrubbingModeEnabled(boolean z) {
        TrackSelectionParameters trackSelectionParametersBuild;
        verifyApplicationThread();
        if (z == this.scrubbingModeEnabled) {
            return;
        }
        this.scrubbingModeEnabled = z;
        if (!this.scrubbingModeParameters.disabledTrackTypes.isEmpty() && this.trackSelector.isSetParametersSupported()) {
            TrackSelectionParameters parameters = this.trackSelector.getParameters();
            if (z) {
                this.disabledTrackTypesWithoutScrubbingMode = parameters.disabledTrackTypes;
                trackSelectionParametersBuild = addDisabledTrackTypes(parameters, this.scrubbingModeParameters.disabledTrackTypes);
            } else {
                trackSelectionParametersBuild = parameters.buildUpon().setDisabledTrackTypes(this.disabledTrackTypesWithoutScrubbingMode).build();
                this.disabledTrackTypesWithoutScrubbingMode = null;
            }
            if (!trackSelectionParametersBuild.equals(parameters)) {
                this.trackSelector.setParameters(trackSelectionParametersBuild);
            }
        }
        this.internalPlayer.setScrubbingModeEnabled(z);
        maybeUpdatePlaybackSuppressionReason();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setScrubbingModeParameters(ScrubbingModeParameters scrubbingModeParameters) {
        verifyApplicationThread();
        if (this.scrubbingModeParameters.equals(scrubbingModeParameters)) {
            return;
        }
        ScrubbingModeParameters scrubbingModeParameters2 = this.scrubbingModeParameters;
        this.scrubbingModeParameters = scrubbingModeParameters;
        this.internalPlayer.setScrubbingModeParameters(scrubbingModeParameters);
        if (this.scrubbingModeEnabled && this.trackSelector.isSetParametersSupported() && !scrubbingModeParameters2.disabledTrackTypes.equals(scrubbingModeParameters.disabledTrackTypes)) {
            TrackSelectionParameters trackSelectionParametersAddDisabledTrackTypes = addDisabledTrackTypes(getTrackSelectionParameters(), scrubbingModeParameters.disabledTrackTypes);
            if (trackSelectionParametersAddDisabledTrackTypes.equals(this.trackSelector.getParameters())) {
                return;
            }
            this.trackSelector.setParameters(trackSelectionParametersAddDisabledTrackTypes);
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setSeekBackIncrementMs(long j10) {
        verifyApplicationThread();
        ac.b.j(j10 > 0);
        if (this.seekBackIncrementMs == j10) {
            return;
        }
        this.seekBackIncrementMs = j10;
        this.listeners.sendEvent(16, new x(j10, 0));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setSeekForwardIncrementMs(long j10) {
        verifyApplicationThread();
        ac.b.j(j10 > 0);
        if (this.seekForwardIncrementMs == j10) {
            return;
        }
        this.seekForwardIncrementMs = j10;
        this.listeners.sendEvent(17, new x(j10, 2));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setSeekParameters(SeekParameters seekParameters) {
        verifyApplicationThread();
        if (seekParameters == null) {
            seekParameters = SeekParameters.DEFAULT;
        }
        if (this.seekParameters.equals(seekParameters)) {
            return;
        }
        this.seekParameters = seekParameters;
        this.internalPlayer.setSeekParameters(seekParameters);
    }

    @Override // androidx.media3.common.Player
    public void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        if (this.shuffleModeEnabled != z) {
            this.shuffleModeEnabled = z;
            this.internalPlayer.setShuffleModeEnabled(z);
            this.listeners.queueEvent(9, new s(z, 1));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setShuffleOrder(ShuffleOrder shuffleOrder) {
        verifyApplicationThread();
        ac.b.j(shuffleOrder.getLength() == this.mediaSourceHolderSnapshots.size());
        this.shuffleOrder = shuffleOrder;
        Timeline timelineCreateMaskingTimeline = createMaskingTimeline();
        PlaybackInfo playbackInfoMaskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, timelineCreateMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(timelineCreateMaskingTimeline, getCurrentMediaItemIndex(), getCurrentPosition()));
        this.pendingOperationAcks++;
        this.internalPlayer.setShuffleOrder(shuffleOrder);
        updatePlaybackInfo(playbackInfoMaskTimelineAndPosition, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setSkipSilenceEnabled(boolean z) {
        verifyApplicationThread();
        if (this.skipSilenceEnabled == z) {
            return;
        }
        this.skipSilenceEnabled = z;
        sendRendererMessage(1, 9, Boolean.valueOf(z));
        this.listeners.sendEvent(23, new s(z, 0));
    }

    public void setThrowsWhenUsingWrongThread(boolean z) {
        this.throwsWhenUsingWrongThread = z;
        this.listeners.setThrowsWhenUsingWrongThread(z);
        AnalyticsCollector analyticsCollector = this.analyticsCollector;
        if (analyticsCollector instanceof DefaultAnalyticsCollector) {
            ((DefaultAnalyticsCollector) analyticsCollector).setThrowsWhenUsingWrongThread(z);
        }
    }

    @Override // androidx.media3.common.Player
    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        TrackSelectionParameters trackSelectionParametersAddDisabledTrackTypes;
        verifyApplicationThread();
        if (this.trackSelector.isSetParametersSupported()) {
            TrackSelectionParameters trackSelectionParameters2 = getTrackSelectionParameters();
            if (this.scrubbingModeEnabled) {
                this.disabledTrackTypesWithoutScrubbingMode = trackSelectionParameters.disabledTrackTypes;
                trackSelectionParametersAddDisabledTrackTypes = addDisabledTrackTypes(trackSelectionParameters, this.scrubbingModeParameters.disabledTrackTypes);
            } else {
                trackSelectionParametersAddDisabledTrackTypes = trackSelectionParameters;
            }
            if (!trackSelectionParametersAddDisabledTrackTypes.equals(this.trackSelector.getParameters())) {
                this.trackSelector.setParameters(trackSelectionParametersAddDisabledTrackTypes);
            }
            if (trackSelectionParameters2.equals(trackSelectionParameters)) {
                return;
            }
            this.listeners.sendEvent(19, new p(trackSelectionParameters, 2));
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoChangeFrameRateStrategy(int i10) {
        verifyApplicationThread();
        if (this.videoChangeFrameRateStrategy == i10) {
            return;
        }
        this.videoChangeFrameRateStrategy = i10;
        sendRendererMessage(2, 5, Integer.valueOf(i10));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoCodecParameters(CodecParameters codecParameters) {
        verifyApplicationThread();
        codecParameters.getClass();
        sendRendererMessage(2, 21, codecParameters);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoEffects(List<Effect> list) {
        verifyApplicationThread();
        try {
            Class.forName("androidx.media3.effect.SingleInputVideoGraph$Factory").getConstructor(VideoFrameProcessor.Factory.class);
            sendRendererMessage(2, 13, list);
        } catch (ClassNotFoundException | NoSuchMethodException e5) {
            throw new IllegalStateException("Could not find required lib-effect dependencies.", e5);
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener) {
        verifyApplicationThread();
        this.videoFrameMetadataListener = videoFrameMetadataListener;
        createMessageInternal(this.frameMetadataListener).setType(7).setPayload(videoFrameMetadataListener).send();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoScalingMode(int i10) {
        verifyApplicationThread();
        this.videoScalingMode = i10;
        sendRendererMessage(2, 4, Integer.valueOf(i10));
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(surface);
        int i10 = surface == null ? 0 : -1;
        maybeNotifySurfaceSizeChanged(i10, i10);
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder) {
        verifyApplicationThread();
        if (surfaceHolder == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.surfaceHolderSurfaceIsVideoOutput = true;
        this.surfaceHolder = surfaceHolder;
        surfaceHolder.addCallback(this.componentListener);
        Surface surface = surfaceHolder.getSurface();
        if (surface == null || !surface.isValid()) {
            setVideoOutputInternal(null);
            maybeNotifySurfaceSizeChanged(0, 0);
        } else {
            setVideoOutputInternal(surface);
            Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
            maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
        }
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurfaceView(SurfaceView surfaceView) {
        verifyApplicationThread();
        if (surfaceView instanceof VideoDecoderOutputBufferRenderer) {
            removeSurfaceCallbacks();
            setVideoOutputInternal(surfaceView);
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        } else {
            if (!(surfaceView instanceof SphericalGLSurfaceView)) {
                setVideoSurfaceHolder(surfaceView == null ? null : surfaceView.getHolder());
                return;
            }
            removeSurfaceCallbacks();
            this.sphericalGLSurfaceView = (SphericalGLSurfaceView) surfaceView;
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload(this.sphericalGLSurfaceView).send();
            this.sphericalGLSurfaceView.addVideoSurfaceListener(this.componentListener);
            setVideoOutputInternal(this.sphericalGLSurfaceView.getVideoSurface());
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        }
    }

    @Override // androidx.media3.common.Player
    public void setVideoTextureView(TextureView textureView) {
        verifyApplicationThread();
        if (textureView == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.textureView = textureView;
        if (textureView.getSurfaceTextureListener() != null) {
            Log.w(TAG, "Replacing existing SurfaceTextureListener.");
        }
        textureView.setSurfaceTextureListener(this.componentListener);
        SurfaceTexture surfaceTexture = textureView.isAvailable() ? textureView.getSurfaceTexture() : null;
        if (surfaceTexture == null) {
            setVideoOutputInternal(null);
            maybeNotifySurfaceSizeChanged(0, 0);
        } else {
            setSurfaceTextureInternal(surfaceTexture);
            maybeNotifySurfaceSizeChanged(textureView.getWidth(), textureView.getHeight());
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVirtualDeviceId(int i10) {
        verifyApplicationThread();
        sendRendererMessage(1, 19, Integer.valueOf(i10));
    }

    @Override // androidx.media3.common.Player
    public void setVolume(float f10) {
        verifyApplicationThread();
        final float fConstrainValue = Util.constrainValue(f10, 0.0f, 1.0f);
        float f11 = this.volume;
        if (f11 == fConstrainValue) {
            return;
        }
        if (fConstrainValue != 0.0f) {
            f11 = fConstrainValue;
        }
        this.unmuteVolume = f11;
        this.volume = fConstrainValue;
        this.internalPlayer.setVolume(fConstrainValue);
        this.listeners.sendEvent(22, new ListenerSet.Event() { // from class: androidx.media3.exoplayer.t
            @Override // androidx.media3.common.util.ListenerSet.Event
            public final void invoke(Object obj) {
                ((Player.Listener) obj).onVolumeChanged(fConstrainValue);
            }
        });
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setWakeMode(int i10) {
        verifyApplicationThread();
        if (i10 == 0) {
            this.wakeLockManager.setEnabled(false);
            this.wifiLockManager.setEnabled(false);
        } else if (i10 == 1) {
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(false);
        } else {
            if (i10 != 2) {
                return;
            }
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(true);
        }
    }

    @Override // androidx.media3.common.Player
    public void stop() {
        verifyApplicationThread();
        stopInternal(null);
        this.currentCueGroup = new CueGroup(o3.f14078o, this.playbackInfo.positionUs);
    }

    @Override // androidx.media3.common.Player
    public void unmute() {
        verifyApplicationThread();
        if (this.volume == 0.0f) {
            float f10 = this.unmuteVolume;
            if (f10 != 0.0f) {
                setVolume(f10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendRendererMessage(int i10, int i11, Object obj) {
        for (Renderer renderer : this.renderers) {
            if (i10 == -1 || renderer.getTrackType() == i10) {
                createMessageInternal(renderer).setType(i11).setPayload(obj).send();
            }
        }
        for (Renderer renderer2 : this.secondaryRenderers) {
            if (renderer2 != null && (i10 == -1 || renderer2.getTrackType() == i10)) {
                createMessageInternal(renderer2).setType(i11).setPayload(obj).send();
            }
        }
    }

    @Override // androidx.media3.common.Player
    public ExoPlaybackException getPlayerError() {
        verifyApplicationThread();
        return this.playbackInfo.playbackError;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addMediaSource(int i10, MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(i10, Collections.singletonList(mediaSource));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addMediaSources(int i10, List<MediaSource> list) {
        verifyApplicationThread();
        ac.b.j(i10 >= 0);
        int iMin = Math.min(i10, this.mediaSourceHolderSnapshots.size());
        if (this.playbackInfo.timeline.isEmpty()) {
            setMediaSources(list, this.maskingWindowIndex == -1);
        } else {
            updatePlaybackInfo(addMediaSourcesInternal(this.playbackInfo, iMin, list), 0, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> list, int i10, long j10) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), i10, j10);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSource(MediaSource mediaSource, long j10) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), 0, j10);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSources(List<MediaSource> list, boolean z) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, -1, C.TIME_UNSET, z);
    }

    @Override // androidx.media3.common.Player
    public void decreaseDeviceVolume(int i10) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.decreaseVolume(i10);
        }
    }

    @Override // androidx.media3.common.Player
    public void increaseDeviceVolume(int i10) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.increaseVolume(i10);
        }
    }

    @Override // androidx.media3.common.Player
    public void setDeviceMuted(boolean z, int i10) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.setMuted(z, i10);
        }
    }

    @Override // androidx.media3.common.Player
    public void setDeviceVolume(int i10, int i11) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager = this.streamVolumeManager;
        if (streamVolumeManager != null) {
            streamVolumeManager.setVolume(i10, i11);
        }
    }

    @Override // androidx.media3.common.Player
    public void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (surface == null || surface != this.videoOutput) {
            return;
        }
        clearVideoSurface();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSources(List<MediaSource> list, int i10, long j10) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, i10, j10, false);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSource(MediaSource mediaSource, boolean z) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), z);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    @Deprecated
    public void prepare(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSource(mediaSource);
        prepare();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    @Deprecated
    public void prepare(MediaSource mediaSource, boolean z, boolean z5) {
        verifyApplicationThread();
        setMediaSource(mediaSource, z);
        prepare();
    }
}
