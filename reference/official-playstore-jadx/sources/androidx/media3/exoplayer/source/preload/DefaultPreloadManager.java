package androidx.media3.exoplayer.source.preload;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.PlaybackLooperProvider;
import androidx.media3.exoplayer.RendererCapabilitiesList;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.BasePreloadManager;
import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import androidx.media3.exoplayer.source.preload.PreCacheHelper;
import androidx.media3.exoplayer.source.preload.PreloadMediaSource;
import androidx.media3.exoplayer.source.preload.RankingDataComparator;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.c0;
import com.google.common.util.concurrent.p0;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultPreloadManager extends BasePreloadManager<Integer, PreloadStatus> {
    private final PreCacheHelper.Factory preCacheHelperFactory;
    private final HandlerThread preCacheThread;
    private final HandlerWrapper preloadHandler;
    private final PlaybackLooperProvider preloadLooperProvider;
    private final PreloadMediaSource.Factory preloadMediaSourceFactory;
    private boolean releaseCalled;
    private final RendererCapabilitiesList rendererCapabilitiesList;
    private final TrackSelector trackSelector;

    public static final class Builder extends BasePreloadManager.BuilderBase<Integer, PreloadStatus> {
        private c0<BandwidthMeter> bandwidthMeterSupplier;
        private boolean buildCalled;
        private boolean buildExoPlayerCalled;
        private Cache cache;
        private Executor cachingExecutor;
        private Clock clock;
        private final Context context;
        private c0<LoadControl> loadControlSupplier;
        private PlaybackLooperProvider preloadLooperProvider;
        private c0<RenderersFactory> renderersFactorySupplier;
        private TrackSelector.Factory trackSelectorFactory;

        public Builder(Context context, TargetPreloadStatusControl<Integer, PreloadStatus> targetPreloadStatusControl) {
            super(new SimpleRankingDataComparator(), targetPreloadStatusControl, new MediaSourceFactorySupplier(context));
            this.context = context;
            this.preloadLooperProvider = new PlaybackLooperProvider();
            this.trackSelectorFactory = new f();
            this.bandwidthMeterSupplier = new h(context, 1);
            this.renderersFactorySupplier = p0.v(new h(context, 2));
            this.loadControlSupplier = p0.v(new androidx.media3.exoplayer.n());
            this.cachingExecutor = new androidx.credentials.a(0);
            this.clock = Clock.DEFAULT;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$new$1(Context context) {
            return new DefaultRenderersFactory(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ BandwidthMeter lambda$setBandwidthMeter$4(BandwidthMeter bandwidthMeter) {
            return bandwidthMeter;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ LoadControl lambda$setLoadControl$3(LoadControl loadControl) {
            return loadControl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ RenderersFactory lambda$setRenderersFactory$2(RenderersFactory renderersFactory) {
            return renderersFactory;
        }

        public ExoPlayer buildExoPlayer() {
            return buildExoPlayer(new ExoPlayer.Builder(this.context));
        }

        public Builder setBandwidthMeter(BandwidthMeter bandwidthMeter) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.bandwidthMeterSupplier = new g(bandwidthMeter, 2);
            return this;
        }

        public Builder setCache(Cache cache) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.cache = cache;
            ((MediaSourceFactorySupplier) this.mediaSourceFactorySupplier).setCache(cache);
            return this;
        }

        public Builder setCachingExecutor(Executor executor) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.cachingExecutor = executor;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder setLoadControl(LoadControl loadControl) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.loadControlSupplier = new g(loadControl, 0);
            return this;
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            ((MediaSourceFactorySupplier) this.mediaSourceFactorySupplier).setCustomMediaSourceFactory(factory);
            return this;
        }

        public Builder setPreloadLooper(Looper looper) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled || looper == Looper.getMainLooper()) ? false : true);
            this.preloadLooperProvider = new PlaybackLooperProvider(looper);
            return this;
        }

        public Builder setRenderersFactory(RenderersFactory renderersFactory) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.renderersFactorySupplier = new g(renderersFactory, 1);
            return this;
        }

        public Builder setTrackSelectorFactory(TrackSelector.Factory factory) {
            ac.b.s((this.buildCalled || this.buildExoPlayerCalled) ? false : true);
            this.trackSelectorFactory = factory;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager.BuilderBase
        public BasePreloadManager<Integer, PreloadStatus> build() {
            ac.b.s(!this.buildCalled);
            this.buildCalled = true;
            return new DefaultPreloadManager(this);
        }

        public ExoPlayer buildExoPlayer(ExoPlayer.Builder builder) {
            this.buildExoPlayerCalled = true;
            return builder.setMediaSourceFactory((MediaSource.Factory) this.mediaSourceFactorySupplier.get()).setBandwidthMeter((BandwidthMeter) this.bandwidthMeterSupplier.get()).setRenderersFactory((RenderersFactory) this.renderersFactorySupplier.get()).setLoadControl((LoadControl) this.loadControlSupplier.get()).setPlaybackLooperProvider(this.preloadLooperProvider).setTrackSelector(this.trackSelectorFactory.createTrackSelector(this.context)).build();
        }
    }

    public static class MediaSourceFactorySupplier implements c0<MediaSource.Factory> {
        private Cache cache;
        private final Context context;
        private MediaSource.Factory customMediaSourceFactory;
        private final c0<DefaultMediaSourceFactory> defaultMediaSourceFactorySupplier;

        public MediaSourceFactorySupplier(Context context) {
            this.context = context;
            this.defaultMediaSourceFactorySupplier = p0.v(new h(context, 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ DefaultMediaSourceFactory lambda$new$0(Context context) {
            return new DefaultMediaSourceFactory(context);
        }

        public void setCache(Cache cache) {
            this.cache = cache;
        }

        public void setCustomMediaSourceFactory(MediaSource.Factory factory) {
            this.customMediaSourceFactory = factory;
        }

        @Override // com.google.common.base.c0
        public MediaSource.Factory get() {
            MediaSource.Factory factory = this.customMediaSourceFactory;
            if (factory != null) {
                return factory;
            }
            DefaultMediaSourceFactory defaultMediaSourceFactory = (DefaultMediaSourceFactory) this.defaultMediaSourceFactorySupplier.get();
            Cache cache = this.cache;
            if (cache != null) {
                defaultMediaSourceFactory.setDataSourceFactory(new CacheDataSource.Factory().setUpstreamDataSourceFactory(new DefaultDataSource.Factory(this.context)).setCache(cache).setCacheWriteDataSinkFactory(null));
            }
            return defaultMediaSourceFactory;
        }
    }

    public final class PreCacheHelperListener implements PreCacheHelper.Listener {
        private PreCacheHelperListener() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onDownloadError$2(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onPreCacheProgress$0(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onPrepareError$1(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        @Override // androidx.media3.exoplayer.source.preload.PreCacheHelper.Listener
        public void onDownloadError(MediaItem mediaItem, IOException iOException) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(mediaItem);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreCachingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onError(new PreloadException(mediaItem, null, iOException), mediaItem, new i(targetPreloadStatusIfCurrentlyPreloading, 2));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreCacheHelper.Listener
        public void onPreCacheProgress(MediaItem mediaItem, long j10, long j11, float f10) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading;
            if (f10 == 100.0f && (targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(mediaItem)) != null && targetPreloadStatusIfCurrentlyPreloading.isPreCachingCategory()) {
                DefaultPreloadManager.this.onCompleted(mediaItem, new i(targetPreloadStatusIfCurrentlyPreloading, 1));
            }
        }

        @Override // androidx.media3.exoplayer.source.preload.PreCacheHelper.Listener
        public void onPrepareError(MediaItem mediaItem, IOException iOException) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(mediaItem);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreCachingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onError(new PreloadException(mediaItem, null, iOException), mediaItem, new i(targetPreloadStatusIfCurrentlyPreloading, 0));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreCacheHelper.Listener
        public void onPrepared(MediaItem mediaItem, MediaItem mediaItem2) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(mediaItem);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreCachingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onMediaSourceUpdated(mediaItem, DefaultPreloadManager.this.preloadMediaSourceFactory.createMediaSource(mediaItem2));
        }
    }

    public final class PreloadMediaSourceControl implements PreloadMediaSource.PreloadControl {
        private PreloadMediaSourceControl() {
        }

        private boolean continueOrCompletePreloading(PreloadMediaSource preloadMediaSource, com.google.common.base.r<PreloadStatus> rVar) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(preloadMediaSource);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreloadingCategory()) {
                return false;
            }
            if (rVar.apply(targetPreloadStatusIfCurrentlyPreloading)) {
                return true;
            }
            DefaultPreloadManager.this.onCompleted(preloadMediaSource, new i(targetPreloadStatusIfCurrentlyPreloading, 6));
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$continueOrCompletePreloading$6(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onContinueLoadingRequested$2(long j10, PreloadStatus preloadStatus) {
            if (preloadStatus.stage != 2) {
                return false;
            }
            long j11 = preloadStatus.durationMs;
            return j11 != C.TIME_UNSET && j11 > Util.usToMs(j10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onLoadedToTheEndOfSource$4(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onPreloadError$5(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onSourcePrepared$0(PreloadStatus preloadStatus) {
            return preloadStatus.stage > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onTracksSelected$1(PreloadStatus preloadStatus) {
            return preloadStatus.stage > 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$onUsedByPlayer$3(PreloadStatus preloadStatus, PreloadStatus preloadStatus2) {
            return preloadStatus2.equals(preloadStatus);
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public boolean onContinueLoadingRequested(PreloadMediaSource preloadMediaSource, final long j10) {
            return continueOrCompletePreloading(preloadMediaSource, new com.google.common.base.r() { // from class: androidx.media3.exoplayer.source.preload.k
                @Override // com.google.common.base.r
                public final boolean apply(Object obj) {
                    return DefaultPreloadManager.PreloadMediaSourceControl.lambda$onContinueLoadingRequested$2(j10, (DefaultPreloadManager.PreloadStatus) obj);
                }
            });
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public void onLoadedToTheEndOfSource(PreloadMediaSource preloadMediaSource) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(preloadMediaSource);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreloadingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onCompleted(preloadMediaSource, new i(targetPreloadStatusIfCurrentlyPreloading, 5));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public boolean onLoadingUnableToContinue(PreloadMediaSource preloadMediaSource) {
            BasePreloadManager<Integer, PreloadStatus>.MediaSourceHolder mediaSourceHolderToClear = DefaultPreloadManager.this.getMediaSourceHolderToClear();
            if (mediaSourceHolderToClear == null) {
                return false;
            }
            ((PreloadMediaSource) mediaSourceHolderToClear.getMediaSource()).clear();
            DefaultPreloadManager.this.onSourceCleared();
            return true;
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public void onPreloadError(PreloadException preloadException, PreloadMediaSource preloadMediaSource) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(preloadMediaSource);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreloadingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onError(preloadException, preloadMediaSource, new i(targetPreloadStatusIfCurrentlyPreloading, 4));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public boolean onSourcePrepared(PreloadMediaSource preloadMediaSource) {
            return continueOrCompletePreloading(preloadMediaSource, new j(0));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public boolean onTracksSelected(PreloadMediaSource preloadMediaSource) {
            return continueOrCompletePreloading(preloadMediaSource, new j(1));
        }

        @Override // androidx.media3.exoplayer.source.preload.PreloadMediaSource.PreloadControl
        public void onUsedByPlayer(PreloadMediaSource preloadMediaSource) {
            PreloadStatus targetPreloadStatusIfCurrentlyPreloading = DefaultPreloadManager.this.getTargetPreloadStatusIfCurrentlyPreloading(preloadMediaSource);
            if (targetPreloadStatusIfCurrentlyPreloading == null || !targetPreloadStatusIfCurrentlyPreloading.isPreloadingCategory()) {
                return;
            }
            DefaultPreloadManager.this.onSkipped(preloadMediaSource, new i(targetPreloadStatusIfCurrentlyPreloading, 3));
        }
    }

    public final class PreloadMediaSourceHolder extends BasePreloadManager<Integer, PreloadStatus>.MediaSourceHolder {
        public PreCacheHelper preCacheHelper;

        public PreloadMediaSourceHolder(MediaItem mediaItem, PreloadMediaSource preloadMediaSource, Integer num) {
            super(mediaItem, num, preloadMediaSource);
        }

        @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager.MediaSourceHolder
        public synchronized void setMediaSource(MediaSource mediaSource) {
            getMediaSource().releasePreloadMediaSource();
            super.setMediaSource(mediaSource);
        }

        @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager.MediaSourceHolder
        public synchronized PreloadMediaSource getMediaSource() {
            return (PreloadMediaSource) super.getMediaSource();
        }
    }

    public static final class PreloadStatus {
        public static final PreloadStatus PRELOAD_STATUS_NOT_PRELOADED = new PreloadStatus(Integer.MIN_VALUE, C.TIME_UNSET, 0);
        public static final PreloadStatus PRELOAD_STATUS_SOURCE_PREPARED = new PreloadStatus(0, C.TIME_UNSET, 0);
        public static final PreloadStatus PRELOAD_STATUS_TRACKS_SELECTED = new PreloadStatus(1, C.TIME_UNSET, 0);
        public static final int STAGE_NOT_PRELOADED = Integer.MIN_VALUE;
        public static final int STAGE_SOURCE_PREPARED = 0;
        public static final int STAGE_SPECIFIED_RANGE_CACHED = -1;
        public static final int STAGE_SPECIFIED_RANGE_LOADED = 2;
        public static final int STAGE_TRACKS_SELECTED = 1;
        public final long durationMs;
        public final int stage;
        public final long startPositionMs;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Stage {
        }

        private PreloadStatus(int i10, long j10, long j11) {
            boolean z = true;
            ac.b.j(j10 == C.TIME_UNSET || j10 >= 0);
            if (j11 != C.TIME_UNSET && j11 < 0) {
                z = false;
            }
            ac.b.j(z);
            this.stage = i10;
            this.startPositionMs = j10;
            this.durationMs = j11;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPreCachingCategory() {
            return this.stage == -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isPreloadingCategory() {
            int i10 = this.stage;
            return i10 == 0 || i10 == 1 || i10 == 2;
        }

        public static PreloadStatus specifiedRangeCached(long j10) {
            return new PreloadStatus(-1, C.TIME_UNSET, j10);
        }

        public static PreloadStatus specifiedRangeLoaded(long j10) {
            return new PreloadStatus(2, C.TIME_UNSET, j10);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && PreloadStatus.class == obj.getClass()) {
                PreloadStatus preloadStatus = (PreloadStatus) obj;
                if (this.stage == preloadStatus.stage && this.startPositionMs == preloadStatus.startPositionMs && this.durationMs == preloadStatus.durationMs) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.stage) * 31) + ((int) this.startPositionMs)) * 31) + ((int) this.durationMs);
        }

        public static PreloadStatus specifiedRangeCached(long j10, long j11) {
            return new PreloadStatus(-1, j10, j11);
        }

        public static PreloadStatus specifiedRangeLoaded(long j10, long j11) {
            return new PreloadStatus(2, j10, j11);
        }
    }

    public static final class SimpleRankingDataComparator implements RankingDataComparator<Integer> {
        private int currentPlayingIndex = -1;
        private RankingDataComparator.InvalidationListener invalidationListener;

        public void setCurrentPlayingIndex(int i10) {
            if (i10 != this.currentPlayingIndex) {
                this.currentPlayingIndex = i10;
                RankingDataComparator.InvalidationListener invalidationListener = this.invalidationListener;
                if (invalidationListener != null) {
                    invalidationListener.onRankingDataComparatorInvalidated();
                }
            }
        }

        @Override // androidx.media3.exoplayer.source.preload.RankingDataComparator
        public void setInvalidationListener(RankingDataComparator.InvalidationListener invalidationListener) {
            this.invalidationListener = invalidationListener;
        }

        @Override // java.util.Comparator
        public int compare(Integer num, Integer num2) {
            return Integer.compare(Math.abs(num.intValue() - this.currentPlayingIndex), Math.abs(num2.intValue() - this.currentPlayingIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$preloadMediaSourceHolderInternal$1(PreloadStatus preloadStatus) {
        return preloadStatus.stage == Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$releasePreloadUtils$2() {
        this.rendererCapabilitiesList.release();
        this.trackSelector.release();
        this.preloadLooperProvider.releaseLooper();
    }

    private void maybeClearPreloadMediaSource(PreloadMediaSource preloadMediaSource, PreloadStatus preloadStatus) {
        int i10 = preloadStatus.stage;
        if (i10 == Integer.MIN_VALUE || i10 == -1 || i10 == 0) {
            preloadMediaSource.clear();
        }
    }

    private void releasePreCacheUtils() {
        HandlerThread handlerThread = this.preCacheThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    private void releasePreloadUtils() {
        this.preloadHandler.post(new e(this, 0));
    }

    @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager
    public void releaseInternal() {
        this.releaseCalled = true;
        releasePreloadUtils();
        releasePreCacheUtils();
    }

    @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager
    public void releaseMediaSourceHolderInternal(BasePreloadManager<Integer, PreloadStatus>.MediaSourceHolder mediaSourceHolder) {
        if (this.releaseCalled) {
            return;
        }
        super.releaseMediaSourceHolderInternal(mediaSourceHolder);
        ac.b.j(mediaSourceHolder instanceof PreloadMediaSourceHolder);
        PreloadMediaSourceHolder preloadMediaSourceHolder = (PreloadMediaSourceHolder) mediaSourceHolder;
        preloadMediaSourceHolder.getMediaSource().releasePreloadMediaSource();
        PreCacheHelper preCacheHelper = preloadMediaSourceHolder.preCacheHelper;
        if (preCacheHelper != null) {
            preCacheHelper.release(true);
            preloadMediaSourceHolder.preCacheHelper = null;
        }
    }

    public void setCurrentPlayingIndex(int i10) {
        ((SimpleRankingDataComparator) this.rankingDataComparator).setCurrentPlayingIndex(i10);
    }

    private DefaultPreloadManager(Builder builder) {
        super(new SimpleRankingDataComparator(), builder.targetPreloadStatusControl, (MediaSource.Factory) builder.mediaSourceFactorySupplier.get());
        DefaultRendererCapabilitiesList defaultRendererCapabilitiesListCreateRendererCapabilitiesList = new DefaultRendererCapabilitiesList.Factory((RenderersFactory) builder.renderersFactorySupplier.get()).createRendererCapabilitiesList();
        this.rendererCapabilitiesList = defaultRendererCapabilitiesListCreateRendererCapabilitiesList;
        PlaybackLooperProvider playbackLooperProvider = builder.preloadLooperProvider;
        this.preloadLooperProvider = playbackLooperProvider;
        TrackSelector trackSelectorCreateTrackSelector = builder.trackSelectorFactory.createTrackSelector(builder.context);
        this.trackSelector = trackSelectorCreateTrackSelector;
        BandwidthMeter bandwidthMeter = (BandwidthMeter) builder.bandwidthMeterSupplier.get();
        trackSelectorCreateTrackSelector.init(new f(), bandwidthMeter);
        Looper looperObtainLooper = playbackLooperProvider.obtainLooper();
        this.preloadMediaSourceFactory = new PreloadMediaSource.Factory((MediaSource.Factory) builder.mediaSourceFactorySupplier.get(), new PreloadMediaSourceControl(), trackSelectorCreateTrackSelector, bandwidthMeter, defaultRendererCapabilitiesListCreateRendererCapabilitiesList.getRendererCapabilities(), (LoadControl) builder.loadControlSupplier.get(), looperObtainLooper).setClock(builder.clock);
        Cache cache = builder.cache;
        if (cache != null) {
            HandlerThread handlerThread = new HandlerThread("DefaultPreloadManager:PreCacheHelper");
            this.preCacheThread = handlerThread;
            handlerThread.start();
            this.preCacheHelperFactory = new PreCacheHelper.Factory(builder.context, cache, handlerThread.getLooper()).setDownloadExecutor(builder.cachingExecutor).setListener(new PreCacheHelperListener());
        } else {
            this.preCacheThread = null;
            this.preCacheHelperFactory = null;
        }
        this.preloadHandler = builder.clock.createHandler(looperObtainLooper, null);
    }

    @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager
    public BasePreloadManager<Integer, PreloadStatus>.MediaSourceHolder createMediaSourceHolder(MediaItem mediaItem, MediaSource mediaSource, Integer num) {
        return new PreloadMediaSourceHolder(mediaItem, mediaSource != null ? this.preloadMediaSourceFactory.createMediaSource(mediaSource) : this.preloadMediaSourceFactory.createMediaSource(mediaItem), num);
    }

    @Override // androidx.media3.exoplayer.source.preload.BasePreloadManager
    public void preloadMediaSourceHolderInternal(BasePreloadManager<Integer, PreloadStatus>.MediaSourceHolder mediaSourceHolder, PreloadStatus preloadStatus) {
        if (this.releaseCalled) {
            return;
        }
        ac.b.j(mediaSourceHolder instanceof PreloadMediaSourceHolder);
        PreloadMediaSourceHolder preloadMediaSourceHolder = (PreloadMediaSourceHolder) mediaSourceHolder;
        PreloadMediaSource mediaSource = preloadMediaSourceHolder.getMediaSource();
        maybeClearPreloadMediaSource(mediaSource, preloadStatus);
        if (preloadStatus.equals(PreloadStatus.PRELOAD_STATUS_NOT_PRELOADED)) {
            onSkipped(mediaSource, new j(2));
            return;
        }
        if (preloadStatus.stage != -1) {
            mediaSource.preload(Util.msToUs(preloadStatus.startPositionMs));
            return;
        }
        if (preloadMediaSourceHolder.preCacheHelper == null) {
            PreCacheHelper.Factory factory = this.preCacheHelperFactory;
            ac.b.p(factory, "DefaultPreloadManager wasn't configured with a Cache");
            preloadMediaSourceHolder.preCacheHelper = factory.create(mediaSourceHolder.mediaItem);
        }
        PreCacheHelper preCacheHelper = preloadMediaSourceHolder.preCacheHelper;
        preCacheHelper.getClass();
        preCacheHelper.preCache(preloadStatus.startPositionMs, preloadStatus.durationMs);
    }
}
