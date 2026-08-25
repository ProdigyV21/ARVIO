package androidx.media3.exoplayer.hls.offline;

import android.net.Uri;
import androidx.credentials.a;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UriUtil;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser;
import androidx.media3.exoplayer.offline.SegmentDownloader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class HlsDownloader extends SegmentDownloader<HlsPlaylist> {

    public static final class Factory extends SegmentDownloader.BaseFactory<HlsPlaylist> {
        public Factory(CacheDataSource.Factory factory) {
            super(factory, new HlsPlaylistParser());
        }

        public Factory setManifestParser(HlsPlaylistParser hlsPlaylistParser) {
            this.manifestParser = hlsPlaylistParser;
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public HlsDownloader create(MediaItem mediaItem) {
            return new HlsDownloader(mediaItem, this.manifestParser, this.cacheDataSourceFactory, this.executor, this.maxMergedSegmentStartTimeDiffMs, this.startPositionUs, this.durationUs);
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

    private void addMediaPlaylistDataSpecs(List<Uri> list, List<DataSpec> list2) {
        for (int i10 = 0; i10 < list.size(); i10++) {
            list2.add(SegmentDownloader.getCompressibleDataSpec(list.get(i10)));
        }
    }

    private void addSegment(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist.Segment segment, HashSet<Uri> hashSet, ArrayList<SegmentDownloader.Segment> arrayList) {
        String str = hlsMediaPlaylist.baseUri;
        long j10 = hlsMediaPlaylist.startTimeUs + segment.relativeStartTimeUs;
        String str2 = segment.fullSegmentEncryptionKeyUri;
        if (str2 != null) {
            Uri uriResolveToUri = UriUtil.resolveToUri(str, str2);
            if (hashSet.add(uriResolveToUri)) {
                arrayList.add(new SegmentDownloader.Segment(j10, SegmentDownloader.getCompressibleDataSpec(uriResolveToUri)));
            }
        }
        arrayList.add(new SegmentDownloader.Segment(j10, new DataSpec(UriUtil.resolveToUri(str, segment.url), segment.byteRangeOffset, segment.byteRangeLength)));
    }

    @Deprecated
    public HlsDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new a(0));
    }

    @Override // androidx.media3.exoplayer.offline.SegmentDownloader
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, HlsPlaylist hlsPlaylist, boolean z) throws InterruptedException, IOException {
        Iterator it;
        boolean z5 = z;
        ArrayList arrayList = new ArrayList();
        if (hlsPlaylist instanceof HlsMultivariantPlaylist) {
            addMediaPlaylistDataSpecs(((HlsMultivariantPlaylist) hlsPlaylist).mediaPlaylistUrls, arrayList);
        } else {
            arrayList.add(SegmentDownloader.getCompressibleDataSpec(Uri.parse(hlsPlaylist.baseUri)));
        }
        ArrayList<SegmentDownloader.Segment> arrayList2 = new ArrayList<>();
        HashSet<Uri> hashSet = new HashSet<>();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            DataSpec dataSpec = (DataSpec) it2.next();
            arrayList2.add(new SegmentDownloader.Segment(0L, dataSpec));
            try {
                HlsMediaPlaylist hlsMediaPlaylist = (HlsMediaPlaylist) getManifest(dataSource, dataSpec, z5);
                List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
                long j10 = z5 ? 0L : this.startPositionUs;
                long j11 = z5 ? C.TIME_UNSET : this.durationUs;
                HlsMediaPlaylist.Segment segment = null;
                int i10 = 0;
                while (true) {
                    if (i10 >= list.size()) {
                        it = it2;
                        break;
                    }
                    HlsMediaPlaylist.Segment segment2 = list.get(i10);
                    it = it2;
                    long j12 = hlsMediaPlaylist.startTimeUs + segment2.relativeStartTimeUs;
                    if (j12 + segment2.durationUs > j10) {
                        if (j11 == C.TIME_UNSET || j12 < j10 + j11) {
                            HlsMediaPlaylist.Segment segment3 = segment2.initializationSegment;
                            if (segment3 != null && segment3 != segment) {
                                addSegment(hlsMediaPlaylist, segment3, hashSet, arrayList2);
                                segment = segment3;
                            }
                            addSegment(hlsMediaPlaylist, segment2, hashSet, arrayList2);
                        }
                    }
                    i10++;
                    it2 = it;
                }
            } catch (IOException e5) {
                it = it2;
                if (!z) {
                    throw e5;
                }
            }
            it2 = it;
            z5 = z;
        }
        return arrayList2;
    }

    @Deprecated
    public HlsDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, new HlsPlaylistParser(), factory, executor, 20000L, 0L, C.TIME_UNSET);
    }

    private HlsDownloader(MediaItem mediaItem, ParsingLoadable.Parser<HlsPlaylist> parser, CacheDataSource.Factory factory, Executor executor, long j10, long j11, long j12) {
        super(mediaItem, parser, factory, executor, j10, j11, j12);
    }
}
