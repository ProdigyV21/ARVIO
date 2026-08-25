package androidx.media3.exoplayer;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.C;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectionArray;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import androidx.media3.extractor.DefaultExtractorsFactory;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ExoPlayer extends Player {
    public static final long DEFAULT_DETACH_SURFACE_TIMEOUT_MS = 2000;
    public static final long DEFAULT_RELEASE_TIMEOUT_MS = 500;
    public static final int DEFAULT_STUCK_BUFFERING_DETECTION_TIMEOUT_MS = 600000;
    public static final int DEFAULT_STUCK_PLAYING_DETECTION_TIMEOUT_MS;
    public static final int DEFAULT_STUCK_PLAYING_NOT_ENDING_TIMEOUT_MS = 60000;
    public static final int DEFAULT_STUCK_SUPPRESSED_DETECTION_TIMEOUT_MS = 600000;

    public interface AudioOffloadListener {
        void onOffloadedPlayback(boolean z);

        void onSleepingForOffloadChanged(boolean z);
    }

    public static final class Builder {
        public static boolean experimentalEnableStuckPlayingDetection = true;
        com.google.common.base.m<Clock, AnalyticsCollector> analyticsCollectorFunction;
        AudioAttributes audioAttributes;
        AudioOutputProvider audioOutputProvider;
        com.google.common.base.c0<BandwidthMeter> bandwidthMeterSupplier;
        boolean buildCalled;
        Clock clock;
        final Context context;
        long detachSurfaceTimeoutMs;
        boolean deviceVolumeControlEnabled;
        boolean dynamicSchedulingEnabled;
        long foregroundModeTimeoutMs;
        boolean handleAudioBecomingNoisy;
        boolean handleAudioFocus;
        LivePlaybackSpeedControl livePlaybackSpeedControl;
        com.google.common.base.c0<LoadControl> loadControlSupplier;
        Looper looper;
        long maxSeekToPreviousPositionMs;
        com.google.common.base.c0<MediaSource.Factory> mediaSourceFactorySupplier;
        boolean pauseAtEndOfMediaItems;
        PlaybackLooperProvider playbackLooperProvider;
        String playerName;
        int priority;
        PriorityTaskManager priorityTaskManager;
        long releaseTimeoutMs;
        com.google.common.base.c0<RenderersFactory> renderersFactorySupplier;
        ScrubbingModeParameters scrubbingModeParameters;
        long seekBackIncrementMs;
        long seekForwardIncrementMs;
        SeekParameters seekParameters;
        boolean skipSilenceEnabled;
        int stuckBufferingDetectionTimeoutMs;
        int stuckPlayingDetectionTimeoutMs;
        int stuckPlayingNotEndingTimeoutMs;
        int stuckSuppressedDetectionTimeoutMs;
        SuitableOutputChecker suitableOutputChecker;
        boolean suppressPlaybackOnUnsuitableOutput;
        com.google.common.base.c0<TrackSelector> trackSelectorSupplier;
        boolean useLazyPreparation;
        boolean usePlatformDiagnostics;
        int videoChangeFrameRateStrategy;
        int videoScalingMode;
        int wakeMode;
        boolean wakeModeSet;

        /* JADX WARN: Illegal instructions before constructor call */
        public Builder(final Context context) {
            final int i10 = 0;
            final int i11 = 1;
            this(context, (com.google.common.base.c0<RenderersFactory>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i10) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            }, (com.google.common.base.c0<MediaSource.Factory>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i11) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$0(Context context) {
            return new DefaultRenderersFactory(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$1(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$new$10(TrackSelector trackSelector) {
            return trackSelector;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$new$11(LoadControl loadControl) {
            return loadControl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ BandwidthMeter lambda$new$12(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ AnalyticsCollector lambda$new$13(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$new$14(Context context) {
            return new DefaultTrackSelector(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$2(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$3(Context context) {
            return new DefaultMediaSourceFactory(context, new DefaultExtractorsFactory());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$4(Context context) {
            return new DefaultRenderersFactory(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$5(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$6(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$7(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$8(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$new$9(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ AnalyticsCollector lambda$setAnalyticsCollector$21(AnalyticsCollector analyticsCollector, Clock clock) {
            return analyticsCollector;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ BandwidthMeter lambda$setBandwidthMeter$20(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$setLoadControl$19(LoadControl loadControl) {
            return loadControl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ MediaSource.Factory lambda$setMediaSourceFactory$17(MediaSource.Factory factory) {
            return factory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$setRenderersFactory$16(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ TrackSelector lambda$setTrackSelector$18(TrackSelector trackSelector) {
            return trackSelector;
        }

        public ExoPlayer build() {
            ac.b.s(!this.buildCalled);
            this.buildCalled = true;
            return new ExoPlayerImpl(this, null);
        }

        public SimpleExoPlayer buildSimpleExoPlayer() {
            ac.b.s(!this.buildCalled);
            this.buildCalled = true;
            return new SimpleExoPlayer(this);
        }

        public Builder experimentalSetDynamicSchedulingEnabled(boolean z) {
            ac.b.s(!this.buildCalled);
            this.dynamicSchedulingEnabled = z;
            return this;
        }

        public Builder experimentalSetForegroundModeTimeoutMs(long j10) {
            ac.b.s(!this.buildCalled);
            this.foregroundModeTimeoutMs = j10;
            return this;
        }

        public Builder setAnalyticsCollector(AnalyticsCollector analyticsCollector) {
            ac.b.s(!this.buildCalled);
            analyticsCollector.getClass();
            this.analyticsCollectorFunction = new h(analyticsCollector, 0);
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes, boolean z) {
            ac.b.s(!this.buildCalled);
            audioAttributes.getClass();
            this.audioAttributes = audioAttributes;
            this.handleAudioFocus = z;
            return this;
        }

        public Builder setAudioOutputProvider(AudioOutputProvider audioOutputProvider) {
            ac.b.s(!this.buildCalled);
            audioOutputProvider.getClass();
            this.audioOutputProvider = audioOutputProvider;
            return this;
        }

        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            ac.b.s(!this.buildCalled);
            bandwidthMeter.getClass();
            this.bandwidthMeterSupplier = new l(bandwidthMeter, 0);
            return this;
        }

        public Builder setClock(Clock clock) {
            ac.b.s(!this.buildCalled);
            this.clock = clock;
            return this;
        }

        public Builder setDetachSurfaceTimeoutMs(long j10) {
            ac.b.s(!this.buildCalled);
            this.detachSurfaceTimeoutMs = j10;
            return this;
        }

        public Builder setDeviceVolumeControlEnabled(boolean z) {
            ac.b.s(!this.buildCalled);
            this.deviceVolumeControlEnabled = z;
            return this;
        }

        public Builder setHandleAudioBecomingNoisy(boolean z) {
            ac.b.s(!this.buildCalled);
            this.handleAudioBecomingNoisy = z;
            return this;
        }

        public Builder setLivePlaybackSpeedControl(LivePlaybackSpeedControl livePlaybackSpeedControl) {
            ac.b.s(!this.buildCalled);
            livePlaybackSpeedControl.getClass();
            this.livePlaybackSpeedControl = livePlaybackSpeedControl;
            return this;
        }

        public Builder setLoadControl(LoadControl loadControl) {
            ac.b.s(!this.buildCalled);
            loadControl.getClass();
            this.loadControlSupplier = new g(loadControl, 0);
            return this;
        }

        public Builder setLooper(Looper looper) {
            ac.b.s(!this.buildCalled);
            looper.getClass();
            this.looper = looper;
            return this;
        }

        public Builder setMaxSeekToPreviousPositionMs(long j10) {
            ac.b.j(j10 >= 0);
            ac.b.s(!this.buildCalled);
            this.maxSeekToPreviousPositionMs = j10;
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            ac.b.s(!this.buildCalled);
            factory.getClass();
            this.mediaSourceFactorySupplier = new k(factory, 3);
            return this;
        }

        public Builder setName(String str) {
            ac.b.s(!this.buildCalled);
            ac.b.j(!str.equals(PlayerId.PRELOAD.name));
            this.playerName = str;
            return this;
        }

        public Builder setPauseAtEndOfMediaItems(boolean z) {
            ac.b.s(!this.buildCalled);
            this.pauseAtEndOfMediaItems = z;
            return this;
        }

        public Builder setPlaybackLooper(Looper looper) {
            ac.b.s((this.buildCalled || looper == Looper.getMainLooper()) ? false : true);
            this.playbackLooperProvider = new PlaybackLooperProvider(looper);
            return this;
        }

        public Builder setPlaybackLooperProvider(PlaybackLooperProvider playbackLooperProvider) {
            ac.b.s(!this.buildCalled);
            this.playbackLooperProvider = playbackLooperProvider;
            return this;
        }

        public Builder setPriority(int i10) {
            ac.b.s(!this.buildCalled);
            this.priority = i10;
            return this;
        }

        public Builder setPriorityTaskManager(PriorityTaskManager priorityTaskManager) {
            ac.b.s(!this.buildCalled);
            this.priorityTaskManager = priorityTaskManager;
            return this;
        }

        public Builder setReleaseTimeoutMs(long j10) {
            ac.b.s(!this.buildCalled);
            this.releaseTimeoutMs = j10;
            return this;
        }

        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            ac.b.s(!this.buildCalled);
            renderersFactory.getClass();
            this.renderersFactorySupplier = new j(renderersFactory, 0);
            return this;
        }

        public Builder setScrubbingModeParameters(ScrubbingModeParameters scrubbingModeParameters) {
            ac.b.s(!this.buildCalled);
            scrubbingModeParameters.getClass();
            this.scrubbingModeParameters = scrubbingModeParameters;
            return this;
        }

        public Builder setSeekBackIncrementMs(long j10) {
            ac.b.j(j10 > 0);
            ac.b.s(!this.buildCalled);
            this.seekBackIncrementMs = j10;
            return this;
        }

        public Builder setSeekForwardIncrementMs(long j10) {
            ac.b.j(j10 > 0);
            ac.b.s(!this.buildCalled);
            this.seekForwardIncrementMs = j10;
            return this;
        }

        public Builder setSeekParameters(SeekParameters seekParameters) {
            ac.b.s(!this.buildCalled);
            seekParameters.getClass();
            this.seekParameters = seekParameters;
            return this;
        }

        public Builder setSkipSilenceEnabled(boolean z) {
            ac.b.s(!this.buildCalled);
            this.skipSilenceEnabled = z;
            return this;
        }

        public Builder setStuckBufferingDetectionTimeoutMs(int i10) {
            ac.b.s(!this.buildCalled);
            ac.b.j(i10 > 0);
            this.stuckBufferingDetectionTimeoutMs = i10;
            return this;
        }

        public Builder setStuckPlayingDetectionTimeoutMs(int i10) {
            ac.b.s(!this.buildCalled);
            ac.b.j(i10 > 0);
            this.stuckPlayingDetectionTimeoutMs = i10;
            return this;
        }

        public Builder setStuckPlayingNotEndingTimeoutMs(int i10) {
            ac.b.s(!this.buildCalled);
            ac.b.j(i10 > 0);
            this.stuckPlayingNotEndingTimeoutMs = i10;
            return this;
        }

        public Builder setStuckSuppressedDetectionTimeoutMs(int i10) {
            ac.b.s(!this.buildCalled);
            ac.b.j(i10 > 0);
            this.stuckSuppressedDetectionTimeoutMs = i10;
            return this;
        }

        public Builder setSuitableOutputChecker(SuitableOutputChecker suitableOutputChecker) {
            ac.b.s(!this.buildCalled);
            this.suitableOutputChecker = suitableOutputChecker;
            return this;
        }

        public Builder setSuppressPlaybackOnUnsuitableOutput(boolean z) {
            ac.b.s(!this.buildCalled);
            this.suppressPlaybackOnUnsuitableOutput = z;
            return this;
        }

        public Builder setTrackSelector(TrackSelector trackSelector) {
            ac.b.s(!this.buildCalled);
            trackSelector.getClass();
            this.trackSelectorSupplier = new m(trackSelector, 1);
            return this;
        }

        public Builder setUseLazyPreparation(boolean z) {
            ac.b.s(!this.buildCalled);
            this.useLazyPreparation = z;
            return this;
        }

        public Builder setUsePlatformDiagnostics(boolean z) {
            ac.b.s(!this.buildCalled);
            this.usePlatformDiagnostics = z;
            return this;
        }

        public Builder setVideoChangeFrameRateStrategy(int i10) {
            ac.b.s(!this.buildCalled);
            this.videoChangeFrameRateStrategy = i10;
            return this;
        }

        public Builder setVideoScalingMode(int i10) {
            ac.b.s(!this.buildCalled);
            this.videoScalingMode = i10;
            return this;
        }

        public Builder setWakeMode(int i10) {
            ac.b.s(!this.buildCalled);
            this.wakeMode = i10;
            this.wakeModeSet = true;
            return this;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Builder(final Context context, RenderersFactory renderersFactory) {
            final int i10 = 2;
            this(context, new j(renderersFactory, 2), (com.google.common.base.c0<MediaSource.Factory>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i10) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            });
            renderersFactory.getClass();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public Builder(final Context context, MediaSource.Factory factory) {
            final int i10 = 5;
            this(context, (com.google.common.base.c0<RenderersFactory>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i10) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            }, new k(factory, 2));
            factory.getClass();
        }

        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory) {
            this(context, new j(renderersFactory, 1), new k(factory, 0));
            renderersFactory.getClass();
            factory.getClass();
        }

        public Builder(Context context, RenderersFactory renderersFactory, MediaSource.Factory factory, TrackSelector trackSelector, LoadControl loadControl, BandwidthMeter bandwidthMeter, AnalyticsCollector analyticsCollector) {
            j jVar = new j(renderersFactory, 3);
            k kVar = new k(factory, 1);
            m mVar = new m(trackSelector, 0);
            g gVar = new g(loadControl, 1);
            int i10 = 1;
            this(context, jVar, kVar, mVar, gVar, new l(bandwidthMeter, i10), new h(analyticsCollector, i10));
            renderersFactory.getClass();
            factory.getClass();
            trackSelector.getClass();
            bandwidthMeter.getClass();
            analyticsCollector.getClass();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private Builder(final Context context, com.google.common.base.c0<RenderersFactory> c0Var, com.google.common.base.c0<MediaSource.Factory> c0Var2) {
            final int i10 = 3;
            final int i11 = 4;
            this(context, c0Var, c0Var2, (com.google.common.base.c0<TrackSelector>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i10) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            }, new n(), (com.google.common.base.c0<BandwidthMeter>) new com.google.common.base.c0() { // from class: androidx.media3.exoplayer.i
                @Override // com.google.common.base.c0
                public final Object get() {
                    switch (i11) {
                        case 0:
                            return ExoPlayer.Builder.lambda$new$0(context);
                        case 1:
                            return ExoPlayer.Builder.lambda$new$1(context);
                        case 2:
                            return ExoPlayer.Builder.lambda$new$3(context);
                        case 3:
                            return ExoPlayer.Builder.lambda$new$14(context);
                        case 4:
                            return DefaultBandwidthMeter.getSingletonInstance(context);
                        default:
                            return ExoPlayer.Builder.lambda$new$4(context);
                    }
                }
            }, new v0(3));
        }

        private Builder(Context context, com.google.common.base.c0<RenderersFactory> c0Var, com.google.common.base.c0<MediaSource.Factory> c0Var2, com.google.common.base.c0<TrackSelector> c0Var3, com.google.common.base.c0<LoadControl> c0Var4, com.google.common.base.c0<BandwidthMeter> c0Var5, com.google.common.base.m<Clock, AnalyticsCollector> mVar) {
            context.getClass();
            this.context = context;
            this.renderersFactorySupplier = c0Var;
            this.mediaSourceFactorySupplier = c0Var2;
            this.trackSelectorSupplier = c0Var3;
            this.loadControlSupplier = c0Var4;
            this.bandwidthMeterSupplier = c0Var5;
            this.analyticsCollectorFunction = mVar;
            this.looper = Util.getCurrentOrMainLooper();
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.wakeMode = 0;
            this.videoScalingMode = 1;
            this.videoChangeFrameRateStrategy = 0;
            this.useLazyPreparation = true;
            this.seekParameters = SeekParameters.DEFAULT;
            this.seekBackIncrementMs = 5000L;
            this.seekForwardIncrementMs = 15000L;
            this.maxSeekToPreviousPositionMs = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
            this.scrubbingModeParameters = ScrubbingModeParameters.DEFAULT;
            this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().build();
            this.clock = Clock.DEFAULT;
            this.releaseTimeoutMs = 500L;
            this.detachSurfaceTimeoutMs = 2000L;
            this.stuckBufferingDetectionTimeoutMs = ExoPlayer.DEFAULT_STUCK_SUPPRESSED_DETECTION_TIMEOUT_MS;
            this.stuckPlayingDetectionTimeoutMs = experimentalEnableStuckPlayingDetection ? ExoPlayer.DEFAULT_STUCK_PLAYING_DETECTION_TIMEOUT_MS : Integer.MAX_VALUE;
            this.stuckPlayingNotEndingTimeoutMs = experimentalEnableStuckPlayingDetection ? ExoPlayer.DEFAULT_STUCK_PLAYING_NOT_ENDING_TIMEOUT_MS : Integer.MAX_VALUE;
            this.stuckSuppressedDetectionTimeoutMs = ExoPlayer.DEFAULT_STUCK_SUPPRESSED_DETECTION_TIMEOUT_MS;
            this.usePlatformDiagnostics = true;
            this.playerName = "";
            this.priority = -1000;
            this.suitableOutputChecker = new DefaultSuitableOutputChecker();
        }
    }

    public static class PreloadConfiguration {
        public static final PreloadConfiguration DEFAULT = new PreloadConfiguration(C.TIME_UNSET);
        public final long targetPreloadDurationUs;

        public PreloadConfiguration(long j10) {
            this.targetPreloadDurationUs = j10;
        }
    }

    static {
        DEFAULT_STUCK_PLAYING_DETECTION_TIMEOUT_MS = Util.isRunningOnEmulator() ? 30000 : 10000;
    }

    void addAnalyticsListener(AnalyticsListener analyticsListener);

    void addAudioCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener, List<String> list);

    void addAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    void addMediaSource(int i10, MediaSource mediaSource);

    void addMediaSource(MediaSource mediaSource);

    void addMediaSources(int i10, List<MediaSource> list);

    void addMediaSources(List<MediaSource> list);

    void addVideoCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener, List<String> list);

    void clearAuxEffectInfo();

    void clearCameraMotionListener(CameraMotionListener cameraMotionListener);

    void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    PlayerMessage createMessage(PlayerMessage.Target target);

    AnalyticsCollector getAnalyticsCollector();

    DecoderCounters getAudioDecoderCounters();

    Format getAudioFormat();

    Clock getClock();

    @Deprecated
    TrackGroupArray getCurrentTrackGroups();

    @Deprecated
    TrackSelectionArray getCurrentTrackSelections();

    boolean getPauseAtEndOfMediaItems();

    Looper getPlaybackLooper();

    @Override // androidx.media3.common.Player
    ExoPlaybackException getPlayerError();

    PreloadConfiguration getPreloadConfiguration();

    Renderer getRenderer(int i10);

    int getRendererCount();

    int getRendererType(int i10);

    ScrubbingModeParameters getScrubbingModeParameters();

    Renderer getSecondaryRenderer(int i10);

    SeekParameters getSeekParameters();

    ShuffleOrder getShuffleOrder();

    boolean getSkipSilenceEnabled();

    TrackSelector getTrackSelector();

    int getVideoChangeFrameRateStrategy();

    DecoderCounters getVideoDecoderCounters();

    Format getVideoFormat();

    int getVideoScalingMode();

    boolean isReleased();

    boolean isScrubbingModeEnabled();

    boolean isSleepingForOffload();

    boolean isTunnelingEnabled();

    @Deprecated
    void prepare(MediaSource mediaSource);

    @Deprecated
    void prepare(MediaSource mediaSource, boolean z, boolean z5);

    @Override // androidx.media3.common.Player
    void release();

    void removeAnalyticsListener(AnalyticsListener analyticsListener);

    void removeAudioCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener);

    void removeAudioOffloadListener(AudioOffloadListener audioOffloadListener);

    void removeVideoCodecParametersChangeListener(CodecParametersChangeListener codecParametersChangeListener);

    @Override // androidx.media3.common.Player
    void replaceMediaItem(int i10, MediaItem mediaItem);

    @Override // androidx.media3.common.Player
    void replaceMediaItems(int i10, int i11, List<MediaItem> list);

    void setAudioCodecParameters(CodecParameters codecParameters);

    void setAudioSessionId(int i10);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setCameraMotionListener(CameraMotionListener cameraMotionListener);

    void setForegroundMode(boolean z);

    void setHandleAudioBecomingNoisy(boolean z);

    void setImageOutput(ImageOutput imageOutput);

    void setMaxSeekToPreviousPositionMs(long j10);

    void setMediaSource(MediaSource mediaSource);

    void setMediaSource(MediaSource mediaSource, long j10);

    void setMediaSource(MediaSource mediaSource, boolean z);

    void setMediaSources(List<MediaSource> list);

    void setMediaSources(List<MediaSource> list, int i10, long j10);

    void setMediaSources(List<MediaSource> list, boolean z);

    void setPauseAtEndOfMediaItems(boolean z);

    void setPreferredAudioDevice(AudioDeviceInfo audioDeviceInfo);

    void setPreloadConfiguration(PreloadConfiguration preloadConfiguration);

    void setPriority(int i10);

    void setPriorityTaskManager(PriorityTaskManager priorityTaskManager);

    void setScrubbingModeEnabled(boolean z);

    void setScrubbingModeParameters(ScrubbingModeParameters scrubbingModeParameters);

    void setSeekBackIncrementMs(long j10);

    void setSeekForwardIncrementMs(long j10);

    void setSeekParameters(SeekParameters seekParameters);

    void setShuffleOrder(ShuffleOrder shuffleOrder);

    void setSkipSilenceEnabled(boolean z);

    void setVideoChangeFrameRateStrategy(int i10);

    void setVideoCodecParameters(CodecParameters codecParameters);

    void setVideoEffects(List<Effect> list);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void setVideoScalingMode(int i10);

    void setVirtualDeviceId(int i10);

    void setWakeMode(int i10);
}
