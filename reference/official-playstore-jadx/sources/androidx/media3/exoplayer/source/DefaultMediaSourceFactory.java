package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ExternallyLoadedMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.c0;
import com.google.common.collect.a0;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import j$.util.Objects;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private static final String TAG = "DMediaSourceFactory";
    private AdViewProvider adViewProvider;
    private AdsLoader.Provider adsLoaderProvider;
    private DataSource.Factory dataSourceFactory;
    private final DelegateFactoryLoader delegateFactoryLoader;
    private ExternalLoader externalImageLoader;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private boolean loadOnlySelectedTracks;
    private boolean parseSubtitlesDuringExtraction;
    private MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    private SubtitleParser.Factory subtitleParserFactory;

    @Deprecated
    public interface AdsLoaderProvider extends AdsLoader.Provider {
    }

    public static final class DelegateFactoryLoader {
        private CmcdConfiguration.Factory cmcdConfigurationFactory;
        private int codecsToParseWithinGopSampleDependencies;
        private DataSource.Factory dataSourceFactory;
        private c0<ReleasableExecutor> downloadExecutorSupplier;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private final ExtractorsFactory extractorsFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private boolean loadOnlySelectedTracks;
        private SubtitleParser.Factory subtitleParserFactory;
        private final Map<Integer, c0<MediaSource.Factory>> mediaSourceFactorySuppliers = new HashMap();
        private final Map<Integer, MediaSource.Factory> mediaSourceFactories = new HashMap();
        private boolean parseSubtitlesDuringExtraction = true;

        public DelegateFactoryLoader(ExtractorsFactory extractorsFactory, SubtitleParser.Factory factory) {
            this.extractorsFactory = extractorsFactory;
            this.subtitleParserFactory = factory;
        }

        private void ensureAllSuppliersAreLoaded() {
            maybeLoadSupplier(0);
            maybeLoadSupplier(1);
            maybeLoadSupplier(2);
            maybeLoadSupplier(3);
            maybeLoadSupplier(4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$loadSupplier$4(DataSource.Factory factory) {
            return new ProgressiveMediaSource.Factory(factory, this.extractorsFactory).setLoadOnlySelectedTracks(this.loadOnlySelectedTracks);
        }

        private c0<MediaSource.Factory> loadSupplier(int i10) throws ClassNotFoundException {
            c0<MediaSource.Factory> oVar;
            c0<MediaSource.Factory> c0Var;
            c0<MediaSource.Factory> c0Var2 = this.mediaSourceFactorySuppliers.get(Integer.valueOf(i10));
            if (c0Var2 != null) {
                return c0Var2;
            }
            final DataSource.Factory factory = this.dataSourceFactory;
            factory.getClass();
            if (i10 != 0) {
                final int i11 = 1;
                if (i10 != 1) {
                    final int i12 = 2;
                    if (i10 == 2) {
                        final Class clsAsSubclass = HlsMediaSource.Factory.class.asSubclass(MediaSource.Factory.class);
                        c0Var = new c0() { // from class: androidx.media3.exoplayer.source.d
                            @Override // com.google.common.base.c0
                            public final Object get() {
                                switch (i12) {
                                }
                                return DefaultMediaSourceFactory.access$300(clsAsSubclass, factory);
                            }
                        };
                    } else if (i10 == 3) {
                        final Class<? extends U> clsAsSubclass2 = Class.forName("androidx.media3.exoplayer.rtsp.RtspMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                        oVar = new c0() { // from class: androidx.media3.exoplayer.source.e
                            @Override // com.google.common.base.c0
                            public final Object get() {
                                return DefaultMediaSourceFactory.access$200(clsAsSubclass2);
                            }
                        };
                    } else {
                        if (i10 != 4) {
                            throw new IllegalArgumentException(a0.c.i(i10, "Unrecognized contentType: "));
                        }
                        oVar = new o(this, factory, i12);
                    }
                } else {
                    final Class<? extends U> clsAsSubclass3 = Class.forName("androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory").asSubclass(MediaSource.Factory.class);
                    c0Var = new c0() { // from class: androidx.media3.exoplayer.source.d
                        @Override // com.google.common.base.c0
                        public final Object get() {
                            switch (i11) {
                            }
                            return DefaultMediaSourceFactory.access$300(clsAsSubclass3, factory);
                        }
                    };
                }
                oVar = c0Var;
            } else {
                final Class clsAsSubclass4 = DashMediaSource.Factory.class.asSubclass(MediaSource.Factory.class);
                final int i13 = 0;
                oVar = new c0() { // from class: androidx.media3.exoplayer.source.d
                    @Override // com.google.common.base.c0
                    public final Object get() {
                        switch (i13) {
                        }
                        return DefaultMediaSourceFactory.access$300(clsAsSubclass4, factory);
                    }
                };
            }
            this.mediaSourceFactorySuppliers.put(Integer.valueOf(i10), oVar);
            return oVar;
        }

        private c0<MediaSource.Factory> maybeLoadSupplier(int i10) {
            try {
                return loadSupplier(i10);
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHeifExtractorFlags(int i10) {
            ExtractorsFactory extractorsFactory = this.extractorsFactory;
            if (extractorsFactory instanceof DefaultExtractorsFactory) {
                ((DefaultExtractorsFactory) extractorsFactory).setHeifExtractorFlags(i10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLoadOnlySelectedTracks(boolean z) {
            this.loadOnlySelectedTracks = z;
        }

        public MediaSource.Factory getMediaSourceFactory(int i10) throws ClassNotFoundException {
            MediaSource.Factory factory = this.mediaSourceFactories.get(Integer.valueOf(i10));
            if (factory != null) {
                return factory;
            }
            MediaSource.Factory factory2 = (MediaSource.Factory) loadSupplier(i10).get();
            CmcdConfiguration.Factory factory3 = this.cmcdConfigurationFactory;
            if (factory3 != null) {
                factory2.setCmcdConfigurationFactory(factory3);
            }
            DrmSessionManagerProvider drmSessionManagerProvider = this.drmSessionManagerProvider;
            if (drmSessionManagerProvider != null) {
                factory2.setDrmSessionManagerProvider(drmSessionManagerProvider);
            }
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
            if (loadErrorHandlingPolicy != null) {
                factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            }
            c0<ReleasableExecutor> c0Var = this.downloadExecutorSupplier;
            if (c0Var != null) {
                factory2.setDownloadExecutor(c0Var);
            }
            factory2.setSubtitleParserFactory(this.subtitleParserFactory);
            factory2.experimentalParseSubtitlesDuringExtraction(this.parseSubtitlesDuringExtraction);
            factory2.experimentalSetCodecsToParseWithinGopSampleDependencies(this.codecsToParseWithinGopSampleDependencies);
            this.mediaSourceFactories.put(Integer.valueOf(i10), factory2);
            return factory2;
        }

        public int[] getSupportedTypes() {
            ensureAllSuppliersAreLoaded();
            return f0.O(this.mediaSourceFactorySuppliers.keySet());
        }

        public void setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            this.cmcdConfigurationFactory = factory;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setCmcdConfigurationFactory(factory);
            }
        }

        public void setCodecsToParseWithinGopSampleDependencies(int i10) {
            this.codecsToParseWithinGopSampleDependencies = i10;
            this.extractorsFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(i10);
        }

        public void setDataSourceFactory(DataSource.Factory factory) {
            if (factory != this.dataSourceFactory) {
                this.dataSourceFactory = factory;
                this.mediaSourceFactorySuppliers.clear();
                this.mediaSourceFactories.clear();
            }
        }

        public void setDownloadExecutor(c0<ReleasableExecutor> c0Var) {
            this.downloadExecutorSupplier = c0Var;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setDownloadExecutor(c0Var);
            }
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setDrmSessionManagerProvider(drmSessionManagerProvider);
            }
        }

        public void setJpegExtractorFlags(int i10) {
            ExtractorsFactory extractorsFactory = this.extractorsFactory;
            if (extractorsFactory instanceof DefaultExtractorsFactory) {
                ((DefaultExtractorsFactory) extractorsFactory).setJpegExtractorFlags(i10);
            }
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
            }
        }

        public void setParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            this.extractorsFactory.experimentalSetTextTrackTranscodingEnabled(z);
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().experimentalParseSubtitlesDuringExtraction(z);
            }
        }

        public void setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.subtitleParserFactory = factory;
            this.extractorsFactory.setSubtitleParserFactory(factory);
            Iterator<MediaSource.Factory> it = this.mediaSourceFactories.values().iterator();
            while (it.hasNext()) {
                it.next().setSubtitleParserFactory(factory);
            }
        }
    }

    public static final class UnknownSubtitlesExtractor implements Extractor {
        private final Format format;

        public UnknownSubtitlesExtractor(Format format) {
            this.format = format;
        }

        @Override // androidx.media3.extractor.Extractor
        public final /* synthetic */ List getSniffFailureDetails() {
            return androidx.media3.extractor.b.a(this);
        }

        @Override // androidx.media3.extractor.Extractor
        public final /* synthetic */ Extractor getUnderlyingImplementation() {
            return androidx.media3.extractor.b.b(this);
        }

        @Override // androidx.media3.extractor.Extractor
        public void init(ExtractorOutput extractorOutput) {
            TrackOutput trackOutputTrack = extractorOutput.track(0, 3);
            extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
            extractorOutput.endTracks();
            trackOutputTrack.format(this.format.buildUpon().setSampleMimeType(MimeTypes.TEXT_UNKNOWN).setCodecs(this.format.sampleMimeType).build());
        }

        @Override // androidx.media3.extractor.Extractor
        public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
            return extractorInput.skip(Integer.MAX_VALUE) == -1 ? -1 : 0;
        }

        @Override // androidx.media3.extractor.Extractor
        public void release() {
        }

        @Override // androidx.media3.extractor.Extractor
        public void seek(long j10, long j11) {
        }

        @Override // androidx.media3.extractor.Extractor
        public boolean sniff(ExtractorInput extractorInput) {
            return true;
        }
    }

    public DefaultMediaSourceFactory(Context context) {
        this(new DefaultDataSource.Factory(context));
    }

    public static /* synthetic */ MediaSource.Factory access$200(Class cls) {
        return newInstance(cls);
    }

    public static /* synthetic */ MediaSource.Factory access$300(Class cls, DataSource.Factory factory) {
        return newInstance(cls, factory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Extractor[] lambda$createMediaSource$0(Format format) {
        return new Extractor[]{this.subtitleParserFactory.supportsFormat(format) ? new SubtitleExtractor(this.subtitleParserFactory.create(format), null) : new UnknownSubtitlesExtractor(format)};
    }

    private static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        return (clippingConfiguration.startPositionUs == 0 && clippingConfiguration.endPositionUs == Long.MIN_VALUE && !clippingConfiguration.relativeToDefaultPosition) ? mediaSource : new ClippingMediaSource.Builder(mediaSource).setStartPositionUs(mediaItem.clippingConfiguration.startPositionUs).setEndPositionUs(mediaItem.clippingConfiguration.endPositionUs).setEnableInitialDiscontinuity(!mediaItem.clippingConfiguration.startsAtKeyFrame).setAllowDynamicClippingUpdates(mediaItem.clippingConfiguration.relativeToLiveWindow).setRelativeToDefaultPosition(mediaItem.clippingConfiguration.relativeToDefaultPosition).setAllowUnseekableMedia(mediaItem.clippingConfiguration.allowUnseekableMedia).build();
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        mediaItem.localConfiguration.getClass();
        MediaItem.AdsConfiguration adsConfiguration = mediaItem.localConfiguration.adsConfiguration;
        if (adsConfiguration == null) {
            return mediaSource;
        }
        AdsLoader.Provider provider = this.adsLoaderProvider;
        AdViewProvider adViewProvider = this.adViewProvider;
        if (provider == null || adViewProvider == null) {
            Log.w(TAG, "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
            return mediaSource;
        }
        AdsLoader adsLoader = provider.getAdsLoader(adsConfiguration);
        if (adsLoader == null) {
            Log.w(TAG, "Playing media without ads, as no AdsLoader was provided.");
            return mediaSource;
        }
        DataSpec dataSpec = new DataSpec(adsConfiguration.adTagUri);
        Object objH = adsConfiguration.adsId;
        if (objH == null) {
            String str = mediaItem.mediaId;
            Uri uri = mediaItem.localConfiguration.uri;
            Uri uri2 = adsConfiguration.adTagUri;
            f1 f1Var = h1.f14020l;
            Object[] objArr = {str, uri, uri2};
            a0.a(3, objArr);
            objH = h1.h(3, objArr);
        }
        return new AdsMediaSource(mediaSource, dataSpec, objH, this, adsLoader, adViewProvider, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls, DataSource.Factory factory) {
        try {
            return cls.getConstructor(DataSource.Factory.class).newInstance(factory);
        } catch (Exception e5) {
            throw new IllegalStateException(e5);
        }
    }

    public DefaultMediaSourceFactory clearLocalAdInsertionComponents() {
        this.adsLoaderProvider = null;
        this.adViewProvider = null;
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public MediaSource createMediaSource(MediaItem mediaItem) {
        mediaItem.localConfiguration.getClass();
        String scheme = mediaItem.localConfiguration.uri.getScheme();
        if (scheme != null && scheme.equals(C.SSAI_SCHEME)) {
            MediaSource.Factory factory = this.serverSideAdInsertionMediaSourceFactory;
            factory.getClass();
            return factory.createMediaSource(mediaItem);
        }
        if (Objects.equals(mediaItem.localConfiguration.mimeType, MimeTypes.APPLICATION_EXTERNALLY_LOADED_IMAGE)) {
            long jMsToUs = Util.msToUs(mediaItem.localConfiguration.imageDurationMs);
            ExternalLoader externalLoader = this.externalImageLoader;
            externalLoader.getClass();
            return new ExternallyLoadedMediaSource.Factory(jMsToUs, externalLoader).createMediaSource(mediaItem);
        }
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        if (mediaItem.localConfiguration.imageDurationMs != C.TIME_UNSET) {
            this.delegateFactoryLoader.setJpegExtractorFlags(1);
            this.delegateFactoryLoader.setHeifExtractorFlags(1);
        }
        try {
            MediaSource.Factory mediaSourceFactory = this.delegateFactoryLoader.getMediaSourceFactory(iInferContentTypeForUriAndMimeType);
            MediaItem.LiveConfiguration.Builder builderBuildUpon = mediaItem.liveConfiguration.buildUpon();
            if (mediaItem.liveConfiguration.targetOffsetMs == C.TIME_UNSET) {
                builderBuildUpon.setTargetOffsetMs(this.liveTargetOffsetMs);
            }
            if (mediaItem.liveConfiguration.minPlaybackSpeed == -3.4028235E38f) {
                builderBuildUpon.setMinPlaybackSpeed(this.liveMinSpeed);
            }
            if (mediaItem.liveConfiguration.maxPlaybackSpeed == -3.4028235E38f) {
                builderBuildUpon.setMaxPlaybackSpeed(this.liveMaxSpeed);
            }
            if (mediaItem.liveConfiguration.minOffsetMs == C.TIME_UNSET) {
                builderBuildUpon.setMinOffsetMs(this.liveMinOffsetMs);
            }
            if (mediaItem.liveConfiguration.maxOffsetMs == C.TIME_UNSET) {
                builderBuildUpon.setMaxOffsetMs(this.liveMaxOffsetMs);
            }
            MediaItem.LiveConfiguration liveConfigurationBuild = builderBuildUpon.build();
            if (!liveConfigurationBuild.equals(mediaItem.liveConfiguration)) {
                mediaItem = mediaItem.buildUpon().setLiveConfiguration(liveConfigurationBuild).build();
            }
            MediaSource mediaSourceCreateMediaSource = mediaSourceFactory.createMediaSource(mediaItem);
            h1 h1Var = ((MediaItem.LocalConfiguration) Util.castNonNull(mediaItem.localConfiguration)).subtitleConfigurations;
            if (!h1Var.isEmpty()) {
                MediaSource[] mediaSourceArr = new MediaSource[h1Var.size() + 1];
                mediaSourceArr[0] = mediaSourceCreateMediaSource;
                for (int i10 = 0; i10 < h1Var.size(); i10++) {
                    if (this.parseSubtitlesDuringExtraction) {
                        Format formatBuild = new Format.Builder().setSampleMimeType(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).mimeType).setLanguage(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).language).setSelectionFlags(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).selectionFlags).setRoleFlags(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).roleFlags).setLabel(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).label).setId(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).id).build();
                        ProgressiveMediaSource.Factory factory2 = new ProgressiveMediaSource.Factory(this.dataSourceFactory, new c(this, formatBuild));
                        if (this.subtitleParserFactory.supportsFormat(formatBuild)) {
                            formatBuild = formatBuild.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCodecs(formatBuild.sampleMimeType).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(formatBuild)).build();
                        }
                        ProgressiveMediaSource.Factory loadOnlySelectedTracks = factory2.enableLazyLoadingWithSingleTrack(0, formatBuild).setLoadOnlySelectedTracks(this.loadOnlySelectedTracks);
                        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
                        if (loadErrorHandlingPolicy != null) {
                            loadOnlySelectedTracks.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
                        }
                        mediaSourceArr[i10 + 1] = loadOnlySelectedTracks.createMediaSource(MediaItem.fromUri(((MediaItem.SubtitleConfiguration) h1Var.get(i10)).uri.toString()));
                    } else {
                        SingleSampleMediaSource.Factory factory3 = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
                        LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
                        if (loadErrorHandlingPolicy2 != null) {
                            factory3.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
                        }
                        mediaSourceArr[i10 + 1] = factory3.createMediaSource((MediaItem.SubtitleConfiguration) h1Var.get(i10), C.TIME_UNSET);
                    }
                }
                mediaSourceCreateMediaSource = new MergingMediaSource(mediaSourceArr);
            }
            return maybeWrapWithAdsMediaSource(mediaItem, maybeClipMediaSource(mediaItem, mediaSourceCreateMediaSource));
        } catch (ClassNotFoundException e5) {
            throw new IllegalStateException(e5);
        }
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public int[] getSupportedTypes() {
        return this.delegateFactoryLoader.getSupportedTypes();
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdViewProvider(AdViewProvider adViewProvider) {
        this.adViewProvider = adViewProvider;
        return this;
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdsLoaderProvider(AdsLoader.Provider provider) {
        this.adsLoaderProvider = provider;
        return this;
    }

    public DefaultMediaSourceFactory setDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.delegateFactoryLoader.setDataSourceFactory(factory);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public MediaSource.Factory setDownloadExecutor(c0<ReleasableExecutor> c0Var) {
        this.delegateFactoryLoader.setDownloadExecutor(c0Var);
        return this;
    }

    public DefaultMediaSourceFactory setExternalImageLoader(ExternalLoader externalLoader) {
        this.externalImageLoader = externalLoader;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxOffsetMs(long j10) {
        this.liveMaxOffsetMs = j10;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxSpeed(float f10) {
        this.liveMaxSpeed = f10;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinOffsetMs(long j10) {
        this.liveMinOffsetMs = j10;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinSpeed(float f10) {
        this.liveMinSpeed = f10;
        return this;
    }

    public DefaultMediaSourceFactory setLiveTargetOffsetMs(long j10) {
        this.liveTargetOffsetMs = j10;
        return this;
    }

    public DefaultMediaSourceFactory setLoadOnlySelectedTracks(boolean z) {
        this.loadOnlySelectedTracks = z;
        this.delegateFactoryLoader.setLoadOnlySelectedTracks(z);
        return this;
    }

    public DefaultMediaSourceFactory setLocalAdInsertionComponents(AdsLoader.Provider provider, AdViewProvider adViewProvider) {
        provider.getClass();
        this.adsLoaderProvider = provider;
        adViewProvider.getClass();
        this.adViewProvider = adViewProvider;
        return this;
    }

    public DefaultMediaSourceFactory setServerSideAdInsertionMediaSourceFactory(MediaSource.Factory factory) {
        this.serverSideAdInsertionMediaSourceFactory = factory;
        return this;
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this(new DefaultDataSource.Factory(context), extractorsFactory);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    @Deprecated
    public DefaultMediaSourceFactory experimentalParseSubtitlesDuringExtraction(boolean z) {
        this.parseSubtitlesDuringExtraction = z;
        this.delegateFactoryLoader.setParseSubtitlesDuringExtraction(z);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
        this.delegateFactoryLoader.setCodecsToParseWithinGopSampleDependencies(i10);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
        DelegateFactoryLoader delegateFactoryLoader = this.delegateFactoryLoader;
        factory.getClass();
        delegateFactoryLoader.setCmcdConfigurationFactory(factory);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        DelegateFactoryLoader delegateFactoryLoader = this.delegateFactoryLoader;
        ac.b.p(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
        delegateFactoryLoader.setDrmSessionManagerProvider(drmSessionManagerProvider);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        ac.b.p(loadErrorHandlingPolicy, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        return this;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource.Factory
    public DefaultMediaSourceFactory setSubtitleParserFactory(SubtitleParser.Factory factory) {
        factory.getClass();
        this.subtitleParserFactory = factory;
        this.delegateFactoryLoader.setSubtitleParserFactory(factory);
        return this;
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory) {
        this(factory, new DefaultExtractorsFactory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls) {
        try {
            return cls.getConstructor(null).newInstance(null);
        } catch (Exception e5) {
            throw new IllegalStateException(e5);
        }
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = factory;
        DefaultSubtitleParserFactory defaultSubtitleParserFactory = new DefaultSubtitleParserFactory();
        this.subtitleParserFactory = defaultSubtitleParserFactory;
        DelegateFactoryLoader delegateFactoryLoader = new DelegateFactoryLoader(extractorsFactory, defaultSubtitleParserFactory);
        this.delegateFactoryLoader = delegateFactoryLoader;
        delegateFactoryLoader.setDataSourceFactory(factory);
        this.liveTargetOffsetMs = C.TIME_UNSET;
        this.liveMinOffsetMs = C.TIME_UNSET;
        this.liveMaxOffsetMs = C.TIME_UNSET;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
        this.parseSubtitlesDuringExtraction = true;
    }
}
