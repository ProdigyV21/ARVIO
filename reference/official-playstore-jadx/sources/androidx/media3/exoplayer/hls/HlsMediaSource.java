package androidx.media3.exoplayer.hls;

import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistTracker;
import androidx.media3.exoplayer.hls.playlist.FilteringHlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.BaseMediaSource;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.DefaultCompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.MediaSourceFactory;
import androidx.media3.exoplayer.source.SinglePeriodTimeline;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.c0;
import j$.util.Objects;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class HlsMediaSource extends BaseMediaSource implements HlsPlaylistTracker.PrimaryPlaylistListener {
    public static final int METADATA_TYPE_EMSG = 3;
    public static final int METADATA_TYPE_ID3 = 1;
    private final boolean allowChunklessPreparation;
    private final CmcdConfiguration cmcdConfiguration;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final c0<ReleasableExecutor> downloadExecutorSupplier;
    private final DrmSessionManager drmSessionManager;
    private final long elapsedRealTimeOffsetMs;
    private final HlsExtractorFactory extractorFactory;
    private MediaItem.LiveConfiguration liveConfiguration;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private MediaItem mediaItem;
    private TransferListener mediaTransferListener;
    private final int metadataType;
    private final HlsPlaylistTracker playlistTracker;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final boolean useSessionKeys;

    public static final class Factory implements MediaSourceFactory {
        private boolean allowChunklessPreparation;
        private CmcdConfiguration.Factory cmcdConfigurationFactory;
        private int codecsToParseWithinGopSampleDependencies;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private c0<ReleasableExecutor> downloadExecutorSupplier;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private long elapsedRealTimeOffsetMs;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private int metadataType;
        private boolean parseSubtitlesDuringExtraction;
        private HlsPlaylistParserFactory playlistParserFactory;
        private HlsPlaylistTracker.Factory playlistTrackerFactory;
        private SubtitleParser.Factory subtitleParserFactoryOverride;
        private long timestampAdjusterInitializationTimeoutMs;
        private boolean useSessionKeys;

        public Factory(DataSource.Factory factory) {
            this(new DefaultHlsDataSourceFactory(factory));
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public int[] getSupportedTypes() {
            return new int[]{2};
        }

        public Factory setAllowChunklessPreparation(boolean z) {
            this.allowChunklessPreparation = z;
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory) {
            ac.b.p(compositeSequenceableLoaderFactory, "HlsMediaSource.Factory#setCompositeSequenceableLoaderFactory no longer handles null by instantiating a new DefaultCompositeSequenceableLoaderFactory. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setDownloadExecutor(c0 c0Var) {
            return setDownloadExecutor((c0<ReleasableExecutor>) c0Var);
        }

        public Factory setElapsedRealTimeOffsetMs(long j10) {
            this.elapsedRealTimeOffsetMs = j10;
            return this;
        }

        public Factory setExtractorFactory(HlsExtractorFactory hlsExtractorFactory) {
            this.extractorFactory = hlsExtractorFactory;
            return this;
        }

        public Factory setMetadataType(int i10) {
            this.metadataType = i10;
            return this;
        }

        public Factory setPlaylistParserFactory(HlsPlaylistParserFactory hlsPlaylistParserFactory) {
            ac.b.p(hlsPlaylistParserFactory, "HlsMediaSource.Factory#setPlaylistParserFactory no longer handles null by instantiating a new DefaultHlsPlaylistParserFactory. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.playlistParserFactory = hlsPlaylistParserFactory;
            return this;
        }

        public Factory setPlaylistTrackerFactory(HlsPlaylistTracker.Factory factory) {
            ac.b.p(factory, "HlsMediaSource.Factory#setPlaylistTrackerFactory no longer handles null by defaulting to DefaultHlsPlaylistTracker.FACTORY. Explicitly pass a reference to this instance in order to retain the old behavior.");
            this.playlistTrackerFactory = factory;
            return this;
        }

        public Factory setTimestampAdjusterInitializationTimeoutMs(long j10) {
            this.timestampAdjusterInitializationTimeoutMs = j10;
            return this;
        }

        public Factory setUseSessionKeys(boolean z) {
            this.useSessionKeys = z;
            return this;
        }

        public Factory(HlsDataSourceFactory hlsDataSourceFactory) {
            hlsDataSourceFactory.getClass();
            this.hlsDataSourceFactory = hlsDataSourceFactory;
            this.drmSessionManagerProvider = new DefaultDrmSessionManagerProvider();
            this.playlistParserFactory = new DefaultHlsPlaylistParserFactory();
            this.playlistTrackerFactory = DefaultHlsPlaylistTracker.FACTORY;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
            this.downloadExecutorSupplier = null;
            this.metadataType = 1;
            this.elapsedRealTimeOffsetMs = C.TIME_UNSET;
            this.allowChunklessPreparation = true;
            experimentalParseSubtitlesDuringExtraction(true);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public HlsMediaSource createMediaSource(MediaItem mediaItem) {
            mediaItem.localConfiguration.getClass();
            if (this.extractorFactory == null) {
                this.extractorFactory = new DefaultHlsExtractorFactory();
            }
            SubtitleParser.Factory factory = this.subtitleParserFactoryOverride;
            if (factory != null) {
                this.extractorFactory.setSubtitleParserFactory(factory);
            }
            this.extractorFactory.experimentalParseSubtitlesDuringExtraction(this.parseSubtitlesDuringExtraction);
            this.extractorFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(this.codecsToParseWithinGopSampleDependencies);
            HlsExtractorFactory hlsExtractorFactory = this.extractorFactory;
            HlsPlaylistParserFactory hlsPlaylistParserFactory = this.playlistParserFactory;
            List<StreamKey> list = mediaItem.localConfiguration.streamKeys;
            HlsPlaylistParserFactory filteringHlsPlaylistParserFactory = !list.isEmpty() ? new FilteringHlsPlaylistParserFactory(hlsPlaylistParserFactory, list) : hlsPlaylistParserFactory;
            CmcdConfiguration.Factory factory2 = this.cmcdConfigurationFactory;
            CmcdConfiguration cmcdConfigurationCreateCmcdConfiguration = factory2 == null ? null : factory2.createCmcdConfiguration(mediaItem);
            HlsDataSourceFactory hlsDataSourceFactory = this.hlsDataSourceFactory;
            CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory = this.compositeSequenceableLoaderFactory;
            DrmSessionManager drmSessionManager = this.drmSessionManagerProvider.get(mediaItem);
            LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.loadErrorHandlingPolicy;
            return new HlsMediaSource(mediaItem, hlsDataSourceFactory, hlsExtractorFactory, compositeSequenceableLoaderFactory, cmcdConfigurationCreateCmcdConfiguration, drmSessionManager, loadErrorHandlingPolicy, this.playlistTrackerFactory.createTracker(this.hlsDataSourceFactory, loadErrorHandlingPolicy, filteringHlsPlaylistParserFactory, cmcdConfigurationCreateCmcdConfiguration, this.downloadExecutorSupplier), this.elapsedRealTimeOffsetMs, this.allowChunklessPreparation, this.metadataType, this.useSessionKeys, this.timestampAdjusterInitializationTimeoutMs, this.downloadExecutorSupplier);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        @Deprecated
        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
            this.codecsToParseWithinGopSampleDependencies = i10;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
            factory.getClass();
            this.cmcdConfigurationFactory = factory;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDownloadExecutor(c0<ReleasableExecutor> c0Var) {
            this.downloadExecutorSupplier = c0Var;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            ac.b.p(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.drmSessionManagerProvider = drmSessionManagerProvider;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            ac.b.p(loadErrorHandlingPolicy, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.subtitleParserFactoryOverride = factory;
            return this;
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MetadataType {
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer.hls");
    }

    private SinglePeriodTimeline createTimelineForLive(HlsMediaPlaylist hlsMediaPlaylist, long j10, long j11, HlsManifest hlsManifest) {
        long initialStartTimeUs = hlsMediaPlaylist.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long j12 = hlsMediaPlaylist.hasEndTag ? initialStartTimeUs + hlsMediaPlaylist.durationUs : -9223372036854775807L;
        long liveEdgeOffsetUs = getLiveEdgeOffsetUs(hlsMediaPlaylist);
        long j13 = this.liveConfiguration.targetOffsetMs;
        updateLiveConfiguration(hlsMediaPlaylist, Util.constrainValue(j13 != C.TIME_UNSET ? Util.msToUs(j13) : getTargetLiveOffsetUs(hlsMediaPlaylist, liveEdgeOffsetUs), liveEdgeOffsetUs, hlsMediaPlaylist.durationUs + liveEdgeOffsetUs));
        return new SinglePeriodTimeline(j10, j11, C.TIME_UNSET, j12, hlsMediaPlaylist.durationUs, initialStartTimeUs, getLiveWindowDefaultStartPositionUs(hlsMediaPlaylist, liveEdgeOffsetUs), true, !hlsMediaPlaylist.hasEndTag, hlsMediaPlaylist.playlistType == 2 && hlsMediaPlaylist.hasPositiveStartOffset, hlsManifest, getMediaItem(), this.liveConfiguration);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private androidx.media3.exoplayer.source.SinglePeriodTimeline createTimelineForOnDemand(androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist r25, long r26, long r28, androidx.media3.exoplayer.hls.HlsManifest r30) {
        /*
            r24 = this;
            r0 = r25
            long r1 = r0.startOffsetUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L31
            java.util.List<androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist$Segment> r1 = r0.segments
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L16
            goto L31
        L16:
            boolean r1 = r0.preciseStart
            if (r1 != 0) goto L2e
            long r1 = r0.startOffsetUs
            long r3 = r0.durationUs
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 != 0) goto L23
            goto L2e
        L23:
            java.util.List<androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist$Segment> r3 = r0.segments
            androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist$Segment r1 = findClosestPrecedingSegment(r3, r1)
            long r1 = r1.relativeStartTimeUs
        L2b:
            r16 = r1
            goto L34
        L2e:
            long r1 = r0.startOffsetUs
            goto L2b
        L31:
            r1 = 0
            goto L2b
        L34:
            androidx.media3.exoplayer.source.SinglePeriodTimeline r3 = new androidx.media3.exoplayer.source.SinglePeriodTimeline
            long r10 = r0.durationUs
            androidx.media3.common.MediaItem r22 = r24.getMediaItem()
            r23 = 0
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r14 = 0
            r18 = 1
            r19 = 0
            r20 = 1
            r12 = r10
            r4 = r26
            r6 = r28
            r21 = r30
            r3.<init>(r4, r6, r8, r10, r12, r14, r16, r18, r19, r20, r21, r22, r23)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.HlsMediaSource.createTimelineForOnDemand(androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, long, long, androidx.media3.exoplayer.hls.HlsManifest):androidx.media3.exoplayer.source.SinglePeriodTimeline");
    }

    private static HlsMediaPlaylist.Part findClosestPrecedingIndependentPart(List<HlsMediaPlaylist.Part> list, long j10) {
        HlsMediaPlaylist.Part part = null;
        for (int i10 = 0; i10 < list.size(); i10++) {
            HlsMediaPlaylist.Part part2 = list.get(i10);
            long j11 = part2.relativeStartTimeUs;
            if (j11 > j10 || !part2.isIndependent) {
                if (j11 > j10) {
                    break;
                }
            } else {
                part = part2;
            }
        }
        return part;
    }

    private static HlsMediaPlaylist.Segment findClosestPrecedingSegment(List<HlsMediaPlaylist.Segment> list, long j10) {
        return list.get(Util.binarySearchFloor((List<? extends Comparable<? super Long>>) list, Long.valueOf(j10), true, true));
    }

    private long getLiveEdgeOffsetUs(HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsMediaPlaylist.hasProgramDateTime) {
            return Util.msToUs(Util.getNowUnixTimeMs(this.elapsedRealTimeOffsetMs)) - hlsMediaPlaylist.getEndTimeUs();
        }
        return 0L;
    }

    private long getLiveWindowDefaultStartPositionUs(HlsMediaPlaylist hlsMediaPlaylist, long j10) {
        long jMsToUs = hlsMediaPlaylist.startOffsetUs;
        if (jMsToUs == C.TIME_UNSET) {
            jMsToUs = (hlsMediaPlaylist.durationUs + j10) - Util.msToUs(this.liveConfiguration.targetOffsetMs);
        }
        if (hlsMediaPlaylist.preciseStart) {
            return jMsToUs;
        }
        HlsMediaPlaylist.Part partFindClosestPrecedingIndependentPart = findClosestPrecedingIndependentPart(hlsMediaPlaylist.trailingParts, jMsToUs);
        if (partFindClosestPrecedingIndependentPart != null) {
            return partFindClosestPrecedingIndependentPart.relativeStartTimeUs;
        }
        if (hlsMediaPlaylist.segments.isEmpty()) {
            return 0L;
        }
        HlsMediaPlaylist.Segment segmentFindClosestPrecedingSegment = findClosestPrecedingSegment(hlsMediaPlaylist.segments, jMsToUs);
        HlsMediaPlaylist.Part partFindClosestPrecedingIndependentPart2 = findClosestPrecedingIndependentPart(segmentFindClosestPrecedingSegment.parts, jMsToUs);
        return partFindClosestPrecedingIndependentPart2 != null ? partFindClosestPrecedingIndependentPart2.relativeStartTimeUs : segmentFindClosestPrecedingSegment.relativeStartTimeUs;
    }

    private static long getTargetLiveOffsetUs(HlsMediaPlaylist hlsMediaPlaylist, long j10) {
        long j11;
        HlsMediaPlaylist.ServerControl serverControl = hlsMediaPlaylist.serverControl;
        long j12 = hlsMediaPlaylist.startOffsetUs;
        if (j12 != C.TIME_UNSET) {
            j11 = hlsMediaPlaylist.durationUs - j12;
        } else {
            long j13 = serverControl.partHoldBackUs;
            if (j13 == C.TIME_UNSET || hlsMediaPlaylist.partTargetDurationUs == C.TIME_UNSET) {
                long j14 = serverControl.holdBackUs;
                j11 = j14 != C.TIME_UNSET ? j14 : hlsMediaPlaylist.targetDurationUs * 3;
            } else {
                j11 = j13;
            }
        }
        return j11 + j10;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateLiveConfiguration(androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist r5, long r6) {
        /*
            r4 = this;
            androidx.media3.common.MediaItem r0 = r4.getMediaItem()
            androidx.media3.common.MediaItem$LiveConfiguration r0 = r0.liveConfiguration
            float r1 = r0.minPlaybackSpeed
            r2 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 != 0) goto L2a
            float r0 = r0.maxPlaybackSpeed
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L2a
            androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist$ServerControl r5 = r5.serverControl
            long r0 = r5.holdBackUs
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L2a
            long r0 = r5.partHoldBackUs
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 != 0) goto L2a
            r5 = 1
            goto L2b
        L2a:
            r5 = 0
        L2b:
            androidx.media3.common.MediaItem$LiveConfiguration r0 = r4.liveConfiguration
            androidx.media3.common.MediaItem$LiveConfiguration$Builder r0 = r0.buildUpon()
            long r6 = androidx.media3.common.util.Util.usToMs(r6)
            androidx.media3.common.MediaItem$LiveConfiguration$Builder r6 = r0.setTargetOffsetMs(r6)
            r7 = 1065353216(0x3f800000, float:1.0)
            if (r5 == 0) goto L3f
            r0 = r7
            goto L43
        L3f:
            androidx.media3.common.MediaItem$LiveConfiguration r0 = r4.liveConfiguration
            float r0 = r0.minPlaybackSpeed
        L43:
            androidx.media3.common.MediaItem$LiveConfiguration$Builder r6 = r6.setMinPlaybackSpeed(r0)
            if (r5 == 0) goto L4a
            goto L4e
        L4a:
            androidx.media3.common.MediaItem$LiveConfiguration r5 = r4.liveConfiguration
            float r7 = r5.maxPlaybackSpeed
        L4e:
            androidx.media3.common.MediaItem$LiveConfiguration$Builder r5 = r6.setMaxPlaybackSpeed(r7)
            androidx.media3.common.MediaItem$LiveConfiguration r5 = r5.build()
            r4.liveConfiguration = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.hls.HlsMediaSource.updateLiveConfiguration(androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist, long):void");
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem mediaItem) {
        MediaItem mediaItem2 = getMediaItem();
        MediaItem.LocalConfiguration localConfiguration = mediaItem2.localConfiguration;
        localConfiguration.getClass();
        MediaItem.LocalConfiguration localConfiguration2 = mediaItem.localConfiguration;
        return localConfiguration2 != null && localConfiguration2.uri.equals(localConfiguration.uri) && localConfiguration2.streamKeys.equals(localConfiguration.streamKeys) && Objects.equals(localConfiguration2.drmConfiguration, localConfiguration.drmConfiguration) && mediaItem2.liveConfiguration.equals(mediaItem.liveConfiguration);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j10) {
        MediaSourceEventListener.EventDispatcher eventDispatcherCreateEventDispatcher = createEventDispatcher(mediaPeriodId);
        return new HlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.mediaTransferListener, this.cmcdConfiguration, this.drmSessionManager, createDrmEventDispatcher(mediaPeriodId), this.loadErrorHandlingPolicy, eventDispatcherCreateEventDispatcher, allocator, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation, this.metadataType, this.useSessionKeys, getPlayerId(), this.timestampAdjusterInitializationTimeoutMs, this.downloadExecutorSupplier);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PrimaryPlaylistListener
    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist) {
        long jUsToMs = hlsMediaPlaylist.hasProgramDateTime ? Util.usToMs(hlsMediaPlaylist.startTimeUs) : -9223372036854775807L;
        int i10 = hlsMediaPlaylist.playlistType;
        long j10 = (i10 == 2 || i10 == 1) ? jUsToMs : -9223372036854775807L;
        HlsMultivariantPlaylist multivariantPlaylist = this.playlistTracker.getMultivariantPlaylist();
        multivariantPlaylist.getClass();
        HlsManifest hlsManifest = new HlsManifest(multivariantPlaylist, hlsMediaPlaylist);
        refreshSourceInfo(this.playlistTracker.isLive() ? createTimelineForLive(hlsMediaPlaylist, j10, jUsToMs, hlsManifest) : createTimelineForOnDemand(hlsMediaPlaylist, j10, jUsToMs, hlsManifest));
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener transferListener) {
        this.mediaTransferListener = transferListener;
        DrmSessionManager drmSessionManager = this.drmSessionManager;
        Looper looperMyLooper = Looper.myLooper();
        looperMyLooper.getClass();
        drmSessionManager.setPlayer(looperMyLooper, getPlayerId());
        this.drmSessionManager.prepare();
        MediaSourceEventListener.EventDispatcher eventDispatcherCreateEventDispatcher = createEventDispatcher(null);
        HlsPlaylistTracker hlsPlaylistTracker = this.playlistTracker;
        MediaItem.LocalConfiguration localConfiguration = getMediaItem().localConfiguration;
        localConfiguration.getClass();
        hlsPlaylistTracker.start(localConfiguration.uri, eventDispatcherCreateEventDispatcher, this);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((HlsMediaPeriod) mediaPeriod).release();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.playlistTracker.stop();
        this.drmSessionManager.release();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource, androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    private HlsMediaSource(MediaItem mediaItem, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory, CmcdConfiguration cmcdConfiguration, DrmSessionManager drmSessionManager, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistTracker hlsPlaylistTracker, long j10, boolean z, int i10, boolean z5, long j11, c0<ReleasableExecutor> c0Var) {
        this.mediaItem = mediaItem;
        this.liveConfiguration = mediaItem.liveConfiguration;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.extractorFactory = hlsExtractorFactory;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory;
        this.cmcdConfiguration = cmcdConfiguration;
        this.drmSessionManager = drmSessionManager;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy;
        this.playlistTracker = hlsPlaylistTracker;
        this.elapsedRealTimeOffsetMs = j10;
        this.allowChunklessPreparation = z;
        this.metadataType = i10;
        this.useSessionKeys = z5;
        this.timestampAdjusterInitializationTimeoutMs = j11;
        this.downloadExecutorSupplier = c0Var;
    }
}
