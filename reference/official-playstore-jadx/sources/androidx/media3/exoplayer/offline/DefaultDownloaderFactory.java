package androidx.media3.exoplayer.offline;

import android.util.SparseArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.dash.offline.DashDownloader;
import androidx.media3.exoplayer.hls.offline.HlsDownloader;
import androidx.media3.exoplayer.offline.DownloadRequest;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultDownloaderFactory implements DownloaderFactory {
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final Executor executor;
    private final SparseArray<SegmentDownloaderFactory> segmentDownloaderFactories;

    @Deprecated
    public DefaultDownloaderFactory(CacheDataSource.Factory factory) {
        this(factory, new androidx.credentials.a(0));
    }

    private Downloader createSegmentDownloader(DownloadRequest downloadRequest, int i10) {
        SegmentDownloaderFactory segmentDownloaderFactory = getSegmentDownloaderFactory(i10, this.cacheDataSourceFactory);
        MediaItem mediaItemBuild = new MediaItem.Builder().setUri(downloadRequest.uri).setStreamKeys(downloadRequest.streamKeys).setCustomCacheKey(downloadRequest.customCacheKey).build();
        DownloadRequest.TimeRange timeRange = downloadRequest.timeRange;
        if (timeRange != null) {
            segmentDownloaderFactory.setStartPositionUs(timeRange.startPositionUs).setDurationUs(downloadRequest.timeRange.durationUs);
        }
        return segmentDownloaderFactory.setExecutor(this.executor).create(mediaItemBuild);
    }

    private static SegmentDownloaderFactory createSegmentDownloaderFactory(Class<? extends SegmentDownloaderFactory> cls, CacheDataSource.Factory factory) {
        try {
            return cls.getConstructor(CacheDataSource.Factory.class).newInstance(factory);
        } catch (Exception e5) {
            throw new IllegalStateException("Downloader factory missing", e5);
        }
    }

    private SegmentDownloaderFactory getSegmentDownloaderFactory(int i10, CacheDataSource.Factory factory) {
        if (Util.contains(this.segmentDownloaderFactories, i10)) {
            return this.segmentDownloaderFactories.get(i10);
        }
        try {
            return loadSegmentDownloaderFactory(i10, factory);
        } catch (ClassNotFoundException e5) {
            throw new IllegalStateException(a0.c.i(i10, "Module missing for content type "), e5);
        }
    }

    private SegmentDownloaderFactory loadSegmentDownloaderFactory(int i10, CacheDataSource.Factory factory) throws ClassNotFoundException {
        SegmentDownloaderFactory segmentDownloaderFactoryCreateSegmentDownloaderFactory;
        if (i10 == 0) {
            segmentDownloaderFactoryCreateSegmentDownloaderFactory = createSegmentDownloaderFactory(DashDownloader.Factory.class.asSubclass(SegmentDownloaderFactory.class), factory);
        } else if (i10 == 1) {
            segmentDownloaderFactoryCreateSegmentDownloaderFactory = createSegmentDownloaderFactory(Class.forName("androidx.media3.exoplayer.smoothstreaming.offline.SsDownloader$Factory").asSubclass(SegmentDownloaderFactory.class), factory);
        } else {
            if (i10 != 2) {
                throw new IllegalArgumentException(a0.c.i(i10, "Unsupported type: "));
            }
            segmentDownloaderFactoryCreateSegmentDownloaderFactory = createSegmentDownloaderFactory(HlsDownloader.Factory.class.asSubclass(SegmentDownloaderFactory.class), factory);
        }
        this.segmentDownloaderFactories.put(i10, segmentDownloaderFactoryCreateSegmentDownloaderFactory);
        return segmentDownloaderFactoryCreateSegmentDownloaderFactory;
    }

    @Override // androidx.media3.exoplayer.offline.DownloaderFactory
    public Downloader createDownloader(DownloadRequest downloadRequest) {
        int iInferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(downloadRequest.uri, downloadRequest.mimeType);
        if (iInferContentTypeForUriAndMimeType == 0 || iInferContentTypeForUriAndMimeType == 1 || iInferContentTypeForUriAndMimeType == 2) {
            return createSegmentDownloader(downloadRequest, iInferContentTypeForUriAndMimeType);
        }
        if (iInferContentTypeForUriAndMimeType != 4) {
            throw new IllegalArgumentException(a0.c.i(iInferContentTypeForUriAndMimeType, "Unsupported type: "));
        }
        DownloadRequest.ByteRange byteRange = downloadRequest.byteRange;
        return new ProgressiveDownloader(new MediaItem.Builder().setUri(downloadRequest.uri).setCustomCacheKey(downloadRequest.customCacheKey).build(), this.cacheDataSourceFactory, this.executor, byteRange != null ? byteRange.offset : 0L, byteRange != null ? byteRange.length : -1L);
    }

    public DefaultDownloaderFactory(CacheDataSource.Factory factory, Executor executor) {
        factory.getClass();
        this.cacheDataSourceFactory = factory;
        executor.getClass();
        this.executor = executor;
        this.segmentDownloaderFactories = new SparseArray<>();
    }
}
