package androidx.media3.exoplayer.dash.offline;

import androidx.credentials.a;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.dash.BaseUrlExclusionList;
import androidx.media3.exoplayer.dash.DashSegmentIndex;
import androidx.media3.exoplayer.dash.DashUtil;
import androidx.media3.exoplayer.dash.DashWrappingSegmentIndex;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.DashManifestParser;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.offline.DownloadException;
import androidx.media3.exoplayer.offline.SegmentDownloader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.ChunkIndex;
import com.google.common.collect.t3;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class DashDownloader extends SegmentDownloader<DashManifest> {
    private final BaseUrlExclusionList baseUrlExclusionList;

    public static final class Factory extends SegmentDownloader.BaseFactory<DashManifest> {
        public Factory(CacheDataSource.Factory factory) {
            super(factory, new DashManifestParser());
        }

        public Factory setManifestParser(DashManifestParser dashManifestParser) {
            this.manifestParser = dashManifestParser;
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public DashDownloader create(MediaItem mediaItem) {
            return new DashDownloader(mediaItem, this.manifestParser, this.cacheDataSourceFactory, this.executor, this.maxMergedSegmentStartTimeDiffMs, this.startPositionUs, this.durationUs);
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloader.BaseFactory, androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public Factory setDurationUs(long j10) {
            super.setDurationUs(j10);
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloader.BaseFactory, androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public Factory setExecutor(Executor executor) {
            super.setExecutor(executor);
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloader.BaseFactory, androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public Factory setMaxMergedSegmentStartTimeDiffMs(long j10) {
            super.setMaxMergedSegmentStartTimeDiffMs(j10);
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloader.BaseFactory, androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public Factory setStartPositionUs(long j10) {
            super.setStartPositionUs(j10);
            return this;
        }
    }

    private void addSegmentsForAdaptationSet(DataSource dataSource, AdaptationSet adaptationSet, long j10, long j11, boolean z, ArrayList<SegmentDownloader.Segment> arrayList) throws InterruptedException, IOException {
        DashDownloader dashDownloader = this;
        int i10 = 0;
        while (i10 < adaptationSet.representations.size()) {
            Representation representation = adaptationSet.representations.get(i10);
            try {
            } catch (IOException e5) {
                e = e5;
            }
            try {
                DashSegmentIndex segmentIndex = dashDownloader.getSegmentIndex(dataSource, adaptationSet.type, representation, z);
                if (segmentIndex == null) {
                    throw new DownloadException("Missing segment index");
                }
                long segmentCount = segmentIndex.getSegmentCount(j11);
                if (segmentCount == -1) {
                    throw new DownloadException("Unbounded segment index");
                }
                String str = ((BaseUrl) Util.castNonNull(dashDownloader.baseUrlExclusionList.selectBaseUrl(representation.baseUrls))).url;
                RangedUri initializationUri = representation.getInitializationUri();
                if (initializationUri != null) {
                    arrayList.add(dashDownloader.createSegment(representation, str, j10, initializationUri));
                }
                RangedUri indexUri = representation.getIndexUri();
                DashDownloader dashDownloader2 = this;
                if (indexUri != null) {
                    arrayList.add(dashDownloader2.createSegment(representation, str, j10, indexUri));
                }
                long j12 = dashDownloader2.startPositionUs - j10;
                Representation representation2 = representation;
                String str2 = str;
                long j13 = dashDownloader2.durationUs;
                long j14 = j13 != C.TIME_UNSET ? j13 + j12 : -9223372036854775807L;
                long firstSegmentNum = (z || j12 <= 0) ? segmentIndex.getFirstSegmentNum() : segmentIndex.getSegmentNum(j12, j11);
                long firstSegmentNum2 = (j14 == C.TIME_UNSET || z || j14 >= j10 + j11) ? (segmentIndex.getFirstSegmentNum() + segmentCount) - 1 : segmentIndex.getSegmentNum(j14, j11);
                while (true) {
                    long j15 = firstSegmentNum;
                    if (j15 <= firstSegmentNum2) {
                        Representation representation3 = representation2;
                        String str3 = str2;
                        arrayList.add(dashDownloader2.createSegment(representation3, str3, segmentIndex.getTimeUs(j15) + j10, segmentIndex.getSegmentUrl(j15)));
                        firstSegmentNum = j15 + 1;
                        dashDownloader2 = this;
                        representation2 = representation3;
                        str2 = str3;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                if (!z) {
                    throw e;
                }
            }
            i10++;
            dashDownloader = this;
        }
    }

    private SegmentDownloader.Segment createSegment(Representation representation, String str, long j10, RangedUri rangedUri) {
        return new SegmentDownloader.Segment(j10, DashUtil.buildDataSpec(representation, str, rangedUri, 0, t3.f14114q));
    }

    private DashSegmentIndex getSegmentIndex(final DataSource dataSource, final int i10, final Representation representation, boolean z) throws InterruptedException, IOException {
        DashSegmentIndex index = representation.getIndex();
        if (index != null) {
            return index;
        }
        ChunkIndex chunkIndex = (ChunkIndex) execute(new RunnableFutureTask<ChunkIndex, IOException>() { // from class: androidx.media3.exoplayer.dash.offline.DashDownloader.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.media3.common.util.RunnableFutureTask
            public ChunkIndex doWork() throws IOException {
                return DashUtil.loadChunkIndex(dataSource, i10, representation);
            }
        }, z);
        if (chunkIndex == null) {
            return null;
        }
        return new DashWrappingSegmentIndex(chunkIndex, representation.presentationTimeOffsetUs);
    }

    @Deprecated
    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new a(0));
    }

    @Override // androidx.media3.exoplayer.offline.SegmentDownloader
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, DashManifest dashManifest, boolean z) throws InterruptedException, IOException {
        int i10;
        DashDownloader dashDownloader = this;
        ArrayList<SegmentDownloader.Segment> arrayList = new ArrayList<>();
        int i11 = 0;
        while (i11 < dashManifest.getPeriodCount()) {
            Period period = dashManifest.getPeriod(i11);
            long jMsToUs = Util.msToUs(period.startMs);
            long periodDurationUs = dashManifest.getPeriodDurationUs(i11);
            if (periodDurationUs != C.TIME_UNSET) {
                i10 = i11;
                if (jMsToUs + periodDurationUs <= dashDownloader.startPositionUs) {
                    continue;
                }
                i11 = i10 + 1;
                dashDownloader = this;
            } else {
                i10 = i11;
            }
            long j10 = dashDownloader.durationUs;
            if (j10 != C.TIME_UNSET && jMsToUs >= dashDownloader.startPositionUs + j10) {
                break;
            }
            List<AdaptationSet> list = period.adaptationSets;
            int i12 = 0;
            while (i12 < list.size()) {
                dashDownloader.addSegmentsForAdaptationSet(dataSource, list.get(i12), jMsToUs, periodDurationUs, z, arrayList);
                i12++;
                dashDownloader = this;
            }
            i11 = i10 + 1;
            dashDownloader = this;
        }
        return arrayList;
    }

    @Deprecated
    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, new DashManifestParser(), factory, executor, 20000L, 0L, C.TIME_UNSET);
    }

    private DashDownloader(MediaItem mediaItem, ParsingLoadable.Parser<DashManifest> parser, CacheDataSource.Factory factory, Executor executor, long j10, long j11, long j12) {
        super(mediaItem, parser, factory, executor, j10, j11, j12);
        this.baseUrlExclusionList = new BaseUrlExclusionList();
    }
}
