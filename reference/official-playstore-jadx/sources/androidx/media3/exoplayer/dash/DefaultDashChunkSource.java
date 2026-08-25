package androidx.media3.exoplayer.dash;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.chunk.ChunkHolder;
import androidx.media3.exoplayer.source.chunk.ContainerMediaChunk;
import androidx.media3.exoplayer.source.chunk.InitializationChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.SingleSampleMediaChunk;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.t3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private final CmcdConfiguration cmcdConfiguration;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long lastChunkRequestRealtimeMs = C.TIME_UNSET;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private ExoTrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final ChunkExtractor.Factory chunkExtractorFactory;
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList, int i10, int[] iArr, ExoTrackSelection exoTrackSelection, int i11, long j10, boolean z, List<Format> list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, TransferListener transferListener, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
            DataSource dataSourceCreateDataSource = this.dataSourceFactory.createDataSource();
            if (transferListener != null) {
                dataSourceCreateDataSource.addTransferListener(transferListener);
            }
            return new DefaultDashChunkSource(this.chunkExtractorFactory, loaderErrorThrower, dashManifest, baseUrlExclusionList, i10, iArr, exoTrackSelection, i11, dataSourceCreateDataSource, j10, this.maxSegmentsPerLoad, z, list, playerTrackEmsgHandler, playerId, cmcdConfiguration);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Format getOutputTextFormat(Format format) {
            return this.chunkExtractorFactory.getOutputTextFormat(format);
        }

        public Factory(DataSource.Factory factory, int i10) {
            this(new BundledChunkExtractor.Factory(), factory, i10);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.chunkExtractorFactory.experimentalParseSubtitlesDuringExtraction(z);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int i10) {
            this.chunkExtractorFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(i10);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.chunkExtractorFactory.setSubtitleParserFactory(factory);
            return this;
        }

        public Factory(ChunkExtractor.Factory factory, DataSource.Factory factory2, int i10) {
            this.chunkExtractorFactory = factory;
            this.dataSourceFactory = factory2;
            this.maxSegmentsPerLoad = i10;
        }
    }

    public static final class RepresentationHolder {
        final ChunkExtractor chunkExtractor;
        private final long periodDurationUs;
        public final Representation representation;
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;
        public final BaseUrl selectedBaseUrl;

        public RepresentationHolder(long j10, Representation representation, BaseUrl baseUrl, ChunkExtractor chunkExtractor, long j11, DashSegmentIndex dashSegmentIndex) {
            this.periodDurationUs = j10;
            this.representation = representation;
            this.selectedBaseUrl = baseUrl;
            this.segmentNumShift = j11;
            this.chunkExtractor = chunkExtractor;
            this.segmentIndex = dashSegmentIndex;
        }

        public RepresentationHolder copyWithNewRepresentation(long j10, Representation representation) throws BehindLiveWindowException {
            long segmentNum;
            long segmentNum2;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation.getIndex();
            if (index == null) {
                return new RepresentationHolder(j10, representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index);
            }
            if (!index.isExplicit()) {
                return new RepresentationHolder(j10, representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index2);
            }
            long segmentCount = index.getSegmentCount(j10);
            if (segmentCount == 0) {
                return new RepresentationHolder(j10, representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index2);
            }
            index2.getClass();
            long firstSegmentNum = index.getFirstSegmentNum();
            long timeUs = index.getTimeUs(firstSegmentNum);
            long j11 = segmentCount + firstSegmentNum;
            long j12 = j11 - 1;
            long durationUs = index.getDurationUs(j12, j10) + index.getTimeUs(j12);
            long firstSegmentNum2 = index2.getFirstSegmentNum();
            long timeUs2 = index2.getTimeUs(firstSegmentNum2);
            long j13 = this.segmentNumShift;
            if (durationUs == timeUs2) {
                segmentNum = j11 - firstSegmentNum2;
            } else {
                if (durationUs < timeUs2) {
                    throw new BehindLiveWindowException();
                }
                if (timeUs2 < timeUs) {
                    segmentNum2 = j13 - (index2.getSegmentNum(timeUs, j10) - firstSegmentNum);
                    return new RepresentationHolder(j10, representation, this.selectedBaseUrl, this.chunkExtractor, segmentNum2, index2);
                }
                segmentNum = index.getSegmentNum(timeUs2, j10) - firstSegmentNum2;
            }
            segmentNum2 = segmentNum + j13;
            return new RepresentationHolder(j10, representation, this.selectedBaseUrl, this.chunkExtractor, segmentNum2, index2);
        }

        public RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex dashSegmentIndex) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, dashSegmentIndex);
        }

        public RepresentationHolder copyWithNewSelectedBaseUrl(BaseUrl baseUrl) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, baseUrl, this.chunkExtractor, this.segmentNumShift, this.segmentIndex);
        }

        public long getFirstAvailableSegmentNum(long j10) {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getFirstAvailableSegmentNum(this.periodDurationUs, j10) + this.segmentNumShift;
        }

        public long getFirstSegmentNum() {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public long getLastAvailableSegmentNum(long j10) {
            long firstAvailableSegmentNum = getFirstAvailableSegmentNum(j10);
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return (dashSegmentIndex.getAvailableSegmentCount(this.periodDurationUs, j10) + firstAvailableSegmentNum) - 1;
        }

        public long getSegmentCount() {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentEndTimeUs(long j10) {
            long segmentStartTimeUs = getSegmentStartTimeUs(j10);
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getDurationUs(j10 - this.segmentNumShift, this.periodDurationUs) + segmentStartTimeUs;
        }

        public long getSegmentNum(long j10) {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getSegmentNum(j10, this.periodDurationUs) + this.segmentNumShift;
        }

        public long getSegmentStartTimeUs(long j10) {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getTimeUs(j10 - this.segmentNumShift);
        }

        public RangedUri getSegmentUrl(long j10) {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.getSegmentUrl(j10 - this.segmentNumShift);
        }

        public boolean isSegmentAvailableAtFullNetworkSpeed(long j10, long j11) {
            DashSegmentIndex dashSegmentIndex = this.segmentIndex;
            dashSegmentIndex.getClass();
            return dashSegmentIndex.isExplicit() || j11 == C.TIME_UNSET || getSegmentEndTimeUs(j10) <= j11;
        }
    }

    public static final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final long nowPeriodTimeUs;
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder representationHolder, long j10, long j11, long j12) {
            super(j10, j11);
            this.representationHolder = representationHolder;
            this.nowPeriodTimeUs = j12;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(getCurrentIndex());
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(getCurrentIndex());
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            checkInBounds();
            long currentIndex = getCurrentIndex();
            RangedUri segmentUrl = this.representationHolder.getSegmentUrl(currentIndex);
            int i10 = this.representationHolder.isSegmentAvailableAtFullNetworkSpeed(currentIndex, this.nowPeriodTimeUs) ? 0 : 8;
            RepresentationHolder representationHolder = this.representationHolder;
            return DashUtil.buildDataSpec(representationHolder.representation, representationHolder.selectedBaseUrl.url, segmentUrl, i10, t3.f14114q);
        }
    }

    public DefaultDashChunkSource(ChunkExtractor.Factory factory, LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList, int i10, int[] iArr, ExoTrackSelection exoTrackSelection, int i11, DataSource dataSource, long j10, int i12, boolean z, List<Format> list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.baseUrlExclusionList = baseUrlExclusionList;
        this.adaptationSetIndices = iArr;
        this.trackSelection = exoTrackSelection;
        int i13 = i11;
        this.trackType = i13;
        this.dataSource = dataSource;
        this.periodIndex = i10;
        this.elapsedRealtimeOffsetMs = j10;
        this.maxSegmentsPerLoad = i12;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = playerTrackEmsgHandler;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler2;
        this.cmcdConfiguration = cmcdConfiguration;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i10);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[exoTrackSelection.length()];
        int i14 = 0;
        while (i14 < this.representationHolders.length) {
            Representation representation = representations.get(exoTrackSelection.getIndexInTrackGroup(i14));
            BaseUrl baseUrlSelectBaseUrl = baseUrlExclusionList.selectBaseUrl(representation.baseUrls);
            RepresentationHolder[] representationHolderArr = this.representationHolders;
            BaseUrl baseUrl = baseUrlSelectBaseUrl == null ? (BaseUrl) representation.baseUrls.get(0) : baseUrlSelectBaseUrl;
            ChunkExtractor chunkExtractorCreateProgressiveMediaExtractor = factory.createProgressiveMediaExtractor(i13, representation.format, z, list, playerTrackEmsgHandler2, playerId);
            long j11 = periodDurationUs;
            int i15 = i14;
            representationHolderArr[i15] = new RepresentationHolder(j11, representation, baseUrl, chunkExtractorCreateProgressiveMediaExtractor, 0L, representation.getIndex());
            i14 = i15 + 1;
            i13 = i11;
            periodDurationUs = j11;
            playerTrackEmsgHandler2 = playerTrackEmsgHandler;
        }
    }

    private LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection, List<BaseUrl> list) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int i10 = 0;
        for (int i11 = 0; i11 < length; i11++) {
            if (exoTrackSelection.isTrackExcluded(i11, jElapsedRealtime)) {
                i10++;
            }
        }
        int priorityCount = BaseUrlExclusionList.getPriorityCount(list);
        return new LoadErrorHandlingPolicy.FallbackOptions(priorityCount, priorityCount - this.baseUrlExclusionList.getPriorityCountAfterExclusion(list), length, i10);
    }

    private long getAvailableLiveDurationUs(long j10, long j11) {
        if (!this.manifest.dynamic || this.representationHolders[0].getSegmentCount() == 0) {
            return C.TIME_UNSET;
        }
        return Math.max(0L, Math.min(getNowPeriodTimeUs(j10), this.representationHolders[0].getSegmentEndTimeUs(this.representationHolders[0].getLastAvailableSegmentNum(j10))) - j11);
    }

    private Pair<String, String> getNextObjectAndRangeRequest(long j10, RangedUri rangedUri, RepresentationHolder representationHolder) {
        long j11 = j10 + 1;
        if (j11 >= representationHolder.getSegmentCount()) {
            return null;
        }
        RangedUri segmentUrl = representationHolder.getSegmentUrl(j11);
        String relativePath = UriUtil.getRelativePath(rangedUri.resolveUri(representationHolder.selectedBaseUrl.url), segmentUrl.resolveUri(representationHolder.selectedBaseUrl.url));
        String strJ = a0.c.j(segmentUrl.start, "-", new StringBuilder());
        if (segmentUrl.length != -1) {
            StringBuilder sbS = androidx.compose.material3.d.s(strJ);
            sbS.append(segmentUrl.start + segmentUrl.length);
            strJ = sbS.toString();
        }
        return new Pair<>(relativePath, strJ);
    }

    private long getNowPeriodTimeUs(long j10) {
        DashManifest dashManifest = this.manifest;
        long j11 = dashManifest.availabilityStartTimeMs;
        return j11 == C.TIME_UNSET ? C.TIME_UNSET : j10 - Util.msToUs(j11 + dashManifest.getPeriod(this.periodIndex).startMs);
    }

    @RequiresNonNull({"manifest", "adaptationSetIndices"})
    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i10 : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i10).representations);
        }
        return arrayList;
    }

    private long getSegmentNum(RepresentationHolder representationHolder, MediaChunk mediaChunk, long j10, long j11, long j12) {
        return mediaChunk != null ? mediaChunk.getNextChunkIndex() : Util.constrainValue(representationHolder.getSegmentNum(j10), j11, j12);
    }

    private RepresentationHolder updateSelectedBaseUrl(int i10) {
        RepresentationHolder representationHolder = this.representationHolders[i10];
        BaseUrl baseUrlSelectBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder.representation.baseUrls);
        if (baseUrlSelectBaseUrl == null || baseUrlSelectBaseUrl.equals(representationHolder.selectedBaseUrl)) {
            return representationHolder;
        }
        RepresentationHolder representationHolderCopyWithNewSelectedBaseUrl = representationHolder.copyWithNewSelectedBaseUrl(baseUrlSelectBaseUrl);
        this.representationHolders[i10] = representationHolderCopyWithNewSelectedBaseUrl;
        return representationHolderCopyWithNewSelectedBaseUrl;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long j10, SeekParameters seekParameters) {
        long j11 = j10;
        RepresentationHolder[] representationHolderArr = this.representationHolders;
        int length = representationHolderArr.length;
        int i10 = 0;
        while (i10 < length) {
            RepresentationHolder representationHolder = representationHolderArr[i10];
            if (representationHolder.segmentIndex != null) {
                long segmentCount = representationHolder.getSegmentCount();
                if (segmentCount != 0) {
                    long segmentNum = representationHolder.getSegmentNum(j11);
                    long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                    return seekParameters.resolveSeekPositionUs(j11, segmentStartTimeUs, (segmentStartTimeUs >= j11 || (segmentCount != -1 && segmentNum >= (representationHolder.getFirstSegmentNum() + segmentCount) - 1)) ? segmentStartTimeUs : representationHolder.getSegmentStartTimeUs(segmentNum + 1));
                }
            }
            i10++;
            j11 = j10;
        }
        return j10;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void getNextChunk(LoadingInfo loadingInfo, long j10, List<? extends MediaChunk> list, ChunkHolder chunkHolder) {
        MediaChunkIterator[] mediaChunkIteratorArr;
        int i10;
        boolean z;
        int i11;
        DefaultDashChunkSource defaultDashChunkSource;
        MediaChunk mediaChunk;
        long j11;
        DefaultDashChunkSource defaultDashChunkSource2 = this;
        if (defaultDashChunkSource2.fatalError != null) {
            return;
        }
        long j12 = loadingInfo.playbackPositionUs;
        long j13 = j10 - j12;
        long jMsToUs = Util.msToUs(defaultDashChunkSource2.manifest.getPeriod(defaultDashChunkSource2.periodIndex).startMs) + Util.msToUs(defaultDashChunkSource2.manifest.availabilityStartTimeMs) + j10;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = defaultDashChunkSource2.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler == null || !playerTrackEmsgHandler.maybeRefreshManifestBeforeLoadingNextChunk(jMsToUs)) {
            long jMsToUs2 = Util.msToUs(Util.getNowUnixTimeMs(defaultDashChunkSource2.elapsedRealtimeOffsetMs));
            long nowPeriodTimeUs = defaultDashChunkSource2.getNowPeriodTimeUs(jMsToUs2);
            boolean z5 = true;
            MediaChunk mediaChunk2 = list.isEmpty() ? null : list.get(list.size() - 1);
            int length = defaultDashChunkSource2.trackSelection.length();
            MediaChunkIterator[] mediaChunkIteratorArr2 = new MediaChunkIterator[length];
            int i12 = 0;
            while (i12 < length) {
                RepresentationHolder representationHolder = defaultDashChunkSource2.representationHolders[i12];
                if (representationHolder.segmentIndex == null) {
                    mediaChunkIteratorArr2[i12] = MediaChunkIterator.EMPTY;
                    defaultDashChunkSource = defaultDashChunkSource2;
                    mediaChunk = mediaChunk2;
                    z = z5;
                    i11 = length;
                    mediaChunkIteratorArr = mediaChunkIteratorArr2;
                    i10 = i12;
                } else {
                    mediaChunkIteratorArr = mediaChunkIteratorArr2;
                    long firstAvailableSegmentNum = representationHolder.getFirstAvailableSegmentNum(jMsToUs2);
                    boolean z10 = z5;
                    MediaChunk mediaChunk3 = mediaChunk2;
                    long lastAvailableSegmentNum = representationHolder.getLastAvailableSegmentNum(jMsToUs2);
                    i10 = i12;
                    z = z10;
                    i11 = length;
                    long segmentNum = defaultDashChunkSource2.getSegmentNum(representationHolder, mediaChunk3, j10, firstAvailableSegmentNum, lastAvailableSegmentNum);
                    defaultDashChunkSource = defaultDashChunkSource2;
                    mediaChunk = mediaChunk3;
                    if (segmentNum < firstAvailableSegmentNum) {
                        mediaChunkIteratorArr[i10] = MediaChunkIterator.EMPTY;
                    } else {
                        long j14 = nowPeriodTimeUs;
                        j11 = j14;
                        mediaChunkIteratorArr[i10] = new RepresentationSegmentIterator(defaultDashChunkSource.updateSelectedBaseUrl(i10), segmentNum, lastAvailableSegmentNum, j14);
                        i12 = i10 + 1;
                        defaultDashChunkSource2 = defaultDashChunkSource;
                        length = i11;
                        mediaChunkIteratorArr2 = mediaChunkIteratorArr;
                        z5 = z;
                        mediaChunk2 = mediaChunk;
                        nowPeriodTimeUs = j11;
                    }
                }
                j11 = nowPeriodTimeUs;
                i12 = i10 + 1;
                defaultDashChunkSource2 = defaultDashChunkSource;
                length = i11;
                mediaChunkIteratorArr2 = mediaChunkIteratorArr;
                z5 = z;
                mediaChunk2 = mediaChunk;
                nowPeriodTimeUs = j11;
            }
            DefaultDashChunkSource defaultDashChunkSource3 = defaultDashChunkSource2;
            MediaChunk mediaChunk4 = mediaChunk2;
            boolean z11 = z5;
            long j15 = nowPeriodTimeUs;
            defaultDashChunkSource3.trackSelection.updateSelectedTrack(j12, j13, defaultDashChunkSource3.getAvailableLiveDurationUs(jMsToUs2, j12), list, mediaChunkIteratorArr2);
            int selectedIndex = defaultDashChunkSource3.trackSelection.getSelectedIndex();
            CmcdData.Factory isBufferEmpty = defaultDashChunkSource3.cmcdConfiguration == null ? null : new CmcdData.Factory(defaultDashChunkSource3.cmcdConfiguration, "d").setTrackSelection(defaultDashChunkSource3.trackSelection).setBufferedDurationUs(Math.max(0L, j13)).setPlaybackRate(loadingInfo.playbackSpeed).setIsLive(defaultDashChunkSource3.manifest.dynamic).setDidRebuffer(loadingInfo.rebufferedSince(defaultDashChunkSource3.lastChunkRequestRealtimeMs)).setIsBufferEmpty(list.isEmpty());
            defaultDashChunkSource3.lastChunkRequestRealtimeMs = SystemClock.elapsedRealtime();
            RepresentationHolder representationHolderUpdateSelectedBaseUrl = defaultDashChunkSource3.updateSelectedBaseUrl(selectedIndex);
            ChunkExtractor chunkExtractor = representationHolderUpdateSelectedBaseUrl.chunkExtractor;
            if (chunkExtractor != null) {
                Representation representation = representationHolderUpdateSelectedBaseUrl.representation;
                RangedUri initializationUri = chunkExtractor.getSampleFormats() == null ? representation.getInitializationUri() : null;
                RangedUri indexUri = representationHolderUpdateSelectedBaseUrl.segmentIndex == null ? representation.getIndexUri() : null;
                if (initializationUri != null || indexUri != null) {
                    chunkHolder.chunk = defaultDashChunkSource3.newInitializationChunk(representationHolderUpdateSelectedBaseUrl, defaultDashChunkSource3.dataSource, defaultDashChunkSource3.trackSelection.getSelectedFormat(), defaultDashChunkSource3.trackSelection.getSelectionReason(), defaultDashChunkSource3.trackSelection.getSelectionData(), initializationUri, indexUri, isBufferEmpty);
                    return;
                }
            }
            long j16 = representationHolderUpdateSelectedBaseUrl.periodDurationUs;
            DashManifest dashManifest = defaultDashChunkSource3.manifest;
            boolean z12 = (dashManifest.dynamic && defaultDashChunkSource3.periodIndex == dashManifest.getPeriodCount() + (-1)) ? z11 : false;
            boolean z13 = (z12 && j16 == C.TIME_UNSET) ? false : z11;
            if (representationHolderUpdateSelectedBaseUrl.getSegmentCount() == 0) {
                chunkHolder.endOfStream = z13;
                return;
            }
            long firstAvailableSegmentNum2 = representationHolderUpdateSelectedBaseUrl.getFirstAvailableSegmentNum(jMsToUs2);
            long lastAvailableSegmentNum2 = representationHolderUpdateSelectedBaseUrl.getLastAvailableSegmentNum(jMsToUs2);
            if (z12) {
                long segmentEndTimeUs = representationHolderUpdateSelectedBaseUrl.getSegmentEndTimeUs(lastAvailableSegmentNum2);
                z13 &= (segmentEndTimeUs - representationHolderUpdateSelectedBaseUrl.getSegmentStartTimeUs(lastAvailableSegmentNum2)) + segmentEndTimeUs >= j16 ? z11 : false;
            }
            boolean z14 = z13;
            long segmentNum2 = defaultDashChunkSource3.getSegmentNum(representationHolderUpdateSelectedBaseUrl, mediaChunk4, j10, firstAvailableSegmentNum2, lastAvailableSegmentNum2);
            if (segmentNum2 < firstAvailableSegmentNum2) {
                defaultDashChunkSource3.fatalError = new BehindLiveWindowException();
                return;
            }
            if (segmentNum2 > lastAvailableSegmentNum2 || (defaultDashChunkSource3.missingLastSegment && segmentNum2 >= lastAvailableSegmentNum2)) {
                chunkHolder.endOfStream = z14;
                return;
            }
            if (z14 && representationHolderUpdateSelectedBaseUrl.getSegmentStartTimeUs(segmentNum2) >= j16) {
                chunkHolder.endOfStream = z11;
                return;
            }
            int iMin = (int) Math.min(defaultDashChunkSource3.maxSegmentsPerLoad, (lastAvailableSegmentNum2 - segmentNum2) + 1);
            if (j16 != C.TIME_UNSET) {
                while (iMin > 1 && representationHolderUpdateSelectedBaseUrl.getSegmentStartTimeUs((((long) iMin) + segmentNum2) - 1) >= j16) {
                    iMin--;
                }
            }
            chunkHolder.chunk = defaultDashChunkSource3.newMediaChunk(representationHolderUpdateSelectedBaseUrl, defaultDashChunkSource3.dataSource, defaultDashChunkSource3.trackType, defaultDashChunkSource3.trackSelection.getSelectedFormat(), defaultDashChunkSource3.trackSelection.getSelectionReason(), defaultDashChunkSource3.trackSelection.getSelectionData(), segmentNum2, iMin, list.isEmpty() ? j10 : -9223372036854775807L, j15, isBufferEmpty);
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public int getPreferredQueueSize(long j10, List<? extends MediaChunk> list) {
        return (this.fatalError != null || this.trackSelection.length() < 2) ? list.size() : this.trackSelection.evaluateQueueSize(j10, list);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.chunkExtractor"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.media3.exoplayer.source.chunk.Chunk newInitializationChunk(androidx.media3.exoplayer.dash.DefaultDashChunkSource.RepresentationHolder r8, androidx.media3.datasource.DataSource r9, androidx.media3.common.Format r10, int r11, java.lang.Object r12, androidx.media3.exoplayer.dash.manifest.RangedUri r13, androidx.media3.exoplayer.dash.manifest.RangedUri r14, androidx.media3.exoplayer.upstream.CmcdData.Factory r15) {
        /*
            r7 = this;
            androidx.media3.exoplayer.dash.manifest.Representation r0 = r8.representation
            if (r13 == 0) goto L11
            androidx.media3.exoplayer.dash.manifest.BaseUrl r1 = r8.selectedBaseUrl
            java.lang.String r1 = r1.url
            androidx.media3.exoplayer.dash.manifest.RangedUri r14 = r13.attemptMerge(r14, r1)
            if (r14 != 0) goto Lf
            goto L15
        Lf:
            r13 = r14
            goto L15
        L11:
            r14.getClass()
            goto Lf
        L15:
            androidx.media3.exoplayer.dash.manifest.BaseUrl r14 = r8.selectedBaseUrl
            java.lang.String r14 = r14.url
            r1 = 0
            com.google.common.collect.t3 r2 = com.google.common.collect.t3.f14114q
            androidx.media3.datasource.DataSpec r13 = androidx.media3.exoplayer.dash.DashUtil.buildDataSpec(r0, r14, r13, r1, r2)
            if (r15 == 0) goto L30
            java.lang.String r14 = "i"
            androidx.media3.exoplayer.upstream.CmcdData$Factory r14 = r15.setObjectType(r14)
            androidx.media3.exoplayer.upstream.CmcdData r14 = r14.createCmcdData()
            androidx.media3.datasource.DataSpec r13 = r14.addToDataSpec(r13)
        L30:
            r2 = r13
            androidx.media3.exoplayer.source.chunk.InitializationChunk r0 = new androidx.media3.exoplayer.source.chunk.InitializationChunk
            androidx.media3.exoplayer.source.chunk.ChunkExtractor r6 = r8.chunkExtractor
            r1 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.DefaultDashChunkSource.newInitializationChunk(androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationHolder, androidx.media3.datasource.DataSource, androidx.media3.common.Format, int, java.lang.Object, androidx.media3.exoplayer.dash.manifest.RangedUri, androidx.media3.exoplayer.dash.manifest.RangedUri, androidx.media3.exoplayer.upstream.CmcdData$Factory):androidx.media3.exoplayer.source.chunk.Chunk");
    }

    public Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource, int i10, Format format, int i11, Object obj, long j10, int i12, long j11, long j12, CmcdData.Factory factory) {
        int i13;
        Representation representation = representationHolder.representation;
        long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(j10);
        RangedUri segmentUrl = representationHolder.getSegmentUrl(j10);
        ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
        t3 t3Var = t3.f14114q;
        if (chunkExtractor == null) {
            long segmentEndTimeUs = representationHolder.getSegmentEndTimeUs(j10);
            DataSpec dataSpecBuildDataSpec = DashUtil.buildDataSpec(representation, representationHolder.selectedBaseUrl.url, segmentUrl, representationHolder.isSegmentAvailableAtFullNetworkSpeed(j10, j12) ? 0 : 8, t3Var);
            if (factory != null) {
                factory.setChunkDurationUs(segmentEndTimeUs - segmentStartTimeUs);
                Pair<String, String> nextObjectAndRangeRequest = getNextObjectAndRangeRequest(j10, segmentUrl, representationHolder);
                if (nextObjectAndRangeRequest != null) {
                    factory.setNextObjectRequest((String) nextObjectAndRangeRequest.first).setNextRangeRequest((String) nextObjectAndRangeRequest.second);
                }
                dataSpecBuildDataSpec = factory.createCmcdData().addToDataSpec(dataSpecBuildDataSpec);
            }
            return new SingleSampleMediaChunk(dataSource, dataSpecBuildDataSpec, format, i11, obj, segmentStartTimeUs, segmentEndTimeUs, j10, i10, format);
        }
        int i14 = 1;
        int i15 = 1;
        while (true) {
            if (i14 >= i12) {
                i13 = i15;
                break;
            }
            int i16 = i15;
            RangedUri rangedUriAttemptMerge = segmentUrl.attemptMerge(representationHolder.getSegmentUrl(((long) i14) + j10), representationHolder.selectedBaseUrl.url);
            if (rangedUriAttemptMerge == null) {
                i13 = i16;
                break;
            }
            i15 = i16 + 1;
            i14++;
            segmentUrl = rangedUriAttemptMerge;
        }
        long j13 = (((long) i13) + j10) - 1;
        long segmentEndTimeUs2 = representationHolder.getSegmentEndTimeUs(j13);
        long j14 = representationHolder.periodDurationUs;
        if (j14 == C.TIME_UNSET || j14 > segmentEndTimeUs2) {
            j14 = -9223372036854775807L;
        }
        DataSpec dataSpecBuildDataSpec2 = DashUtil.buildDataSpec(representation, representationHolder.selectedBaseUrl.url, segmentUrl, representationHolder.isSegmentAvailableAtFullNetworkSpeed(j13, j12) ? 0 : 8, t3Var);
        if (factory != null) {
            factory.setChunkDurationUs(segmentEndTimeUs2 - segmentStartTimeUs);
            Pair<String, String> nextObjectAndRangeRequest2 = getNextObjectAndRangeRequest(j10, segmentUrl, representationHolder);
            if (nextObjectAndRangeRequest2 != null) {
                factory.setNextObjectRequest((String) nextObjectAndRangeRequest2.first).setNextRangeRequest((String) nextObjectAndRangeRequest2.second);
            }
            dataSpecBuildDataSpec2 = factory.createCmcdData().addToDataSpec(dataSpecBuildDataSpec2);
        }
        DataSpec dataSpec = dataSpecBuildDataSpec2;
        long j15 = -representation.presentationTimeOffsetUs;
        if (MimeTypes.isImage(format.sampleMimeType)) {
            j15 += segmentStartTimeUs;
        }
        return new ContainerMediaChunk(dataSource, dataSpec, format, i11, obj, segmentStartTimeUs, segmentEndTimeUs2, j11, j14, j10, i13, j15, representationHolder.chunkExtractor);
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof InitializationChunk) {
            int iIndexOf = this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat);
            RepresentationHolder representationHolder = this.representationHolders[iIndexOf];
            if (representationHolder.segmentIndex == null) {
                ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
                chunkExtractor.getClass();
                ChunkIndex chunkIndex = chunkExtractor.getChunkIndex();
                if (chunkIndex != null) {
                    this.representationHolders[iIndexOf] = representationHolder.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(chunkIndex, representationHolder.representation.presentationTimeOffsetUs));
                }
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null) {
            playerTrackEmsgHandler.onChunkLoadCompleted(chunk);
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean z, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelectionFor;
        if (!z) {
            return false;
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null && playerTrackEmsgHandler.onChunkLoadError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk)) {
            IOException iOException = loadErrorInfo.exception;
            if ((iOException instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) iOException).responseCode == 404) {
                RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
                long segmentCount = representationHolder.getSegmentCount();
                if (segmentCount != -1 && segmentCount != 0) {
                    if (((MediaChunk) chunk).getNextChunkIndex() > (representationHolder.getFirstSegmentNum() + segmentCount) - 1) {
                        this.missingLastSegment = true;
                        return true;
                    }
                }
            }
        }
        RepresentationHolder representationHolder2 = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
        BaseUrl baseUrlSelectBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder2.representation.baseUrls);
        if (baseUrlSelectBaseUrl != null && !representationHolder2.selectedBaseUrl.equals(baseUrlSelectBaseUrl)) {
            return true;
        }
        LoadErrorHandlingPolicy.FallbackOptions fallbackOptionsCreateFallbackOptions = createFallbackOptions(this.trackSelection, representationHolder2.representation.baseUrls);
        if ((fallbackOptionsCreateFallbackOptions.isFallbackAvailable(2) || fallbackOptionsCreateFallbackOptions.isFallbackAvailable(1)) && (fallbackSelectionFor = loadErrorHandlingPolicy.getFallbackSelectionFor(fallbackOptionsCreateFallbackOptions, loadErrorInfo)) != null && fallbackOptionsCreateFallbackOptions.isFallbackAvailable(fallbackSelectionFor.type)) {
            int i10 = fallbackSelectionFor.type;
            if (i10 == 2) {
                ExoTrackSelection exoTrackSelection = this.trackSelection;
                return exoTrackSelection.excludeTrack(exoTrackSelection.indexOf(chunk.trackFormat), fallbackSelectionFor.exclusionDurationMs);
            }
            if (i10 == 1) {
                this.baseUrlExclusionList.exclude(representationHolder2.selectedBaseUrl, fallbackSelectionFor.exclusionDurationMs);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void release() {
        for (RepresentationHolder representationHolder : this.representationHolders) {
            ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
            if (chunkExtractor != null) {
                chunkExtractor.release();
            }
        }
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean shouldCancelLoad(long j10, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j10, chunk, list);
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateManifest(DashManifest dashManifest, int i10) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i10;
            long periodDurationUs = dashManifest.getPeriodDurationUs(i10);
            ArrayList<Representation> representations = getRepresentations();
            for (int i11 = 0; i11 < this.representationHolders.length; i11++) {
                Representation representation = representations.get(this.trackSelection.getIndexInTrackGroup(i11));
                RepresentationHolder[] representationHolderArr = this.representationHolders;
                representationHolderArr[i11] = representationHolderArr[i11].copyWithNewRepresentation(periodDurationUs, representation);
            }
        } catch (BehindLiveWindowException e5) {
            this.fatalError = e5;
        }
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }
}
