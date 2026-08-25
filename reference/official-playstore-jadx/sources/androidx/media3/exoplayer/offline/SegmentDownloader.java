package androidx.media3.exoplayer.offline;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheKeyFactory;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.offline.Downloader;
import androidx.media3.exoplayer.offline.FilterableManifest;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SegmentDownloader<M extends FilterableManifest<M>> implements Downloader {
    private static final int BUFFER_SIZE_BYTES = 131072;
    public static final long DEFAULT_MAX_MERGED_SEGMENT_START_TIME_DIFF_MS = 20000;
    private final ArrayList<RunnableFutureTask<?, ?>> activeRunnables;
    private final Cache cache;
    private final CacheDataSource.Factory cacheDataSourceFactory;
    private final CacheKeyFactory cacheKeyFactory;
    public final long durationUs;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final DataSpec manifestDataSpec;
    private final ParsingLoadable.Parser<M> manifestParser;
    private final long maxMergedSegmentStartTimeDiffUs;
    private final PriorityTaskManager priorityTaskManager;
    public final long startPositionUs;
    private final ArrayList<StreamKey> streamKeys;

    public static abstract class BaseFactory<M extends FilterableManifest<M>> implements SegmentDownloaderFactory {
        protected final CacheDataSource.Factory cacheDataSourceFactory;
        protected ParsingLoadable.Parser<M> manifestParser;
        protected long startPositionUs;
        protected Executor executor = new androidx.credentials.a(0);
        protected long maxMergedSegmentStartTimeDiffMs = 20000;
        protected long durationUs = C.TIME_UNSET;

        public BaseFactory(CacheDataSource.Factory factory, ParsingLoadable.Parser<M> parser) {
            this.cacheDataSourceFactory = factory;
            this.manifestParser = parser;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public BaseFactory<M> setDurationUs(long j10) {
            this.durationUs = j10;
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public BaseFactory<M> setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public BaseFactory<M> setMaxMergedSegmentStartTimeDiffMs(long j10) {
            this.maxMergedSegmentStartTimeDiffMs = j10;
            return this;
        }

        @Override // androidx.media3.exoplayer.offline.SegmentDownloaderFactory
        public BaseFactory<M> setStartPositionUs(long j10) {
            this.startPositionUs = j10;
            return this;
        }
    }

    public static final class ProgressNotifier implements CacheWriter.ProgressListener {
        private long bytesDownloaded;
        private final long contentLength;
        private final Downloader.ProgressListener progressListener;
        private int segmentsDownloaded;
        private final int totalSegments;

        public ProgressNotifier(Downloader.ProgressListener progressListener, long j10, int i10, long j11, int i11) {
            this.progressListener = progressListener;
            this.contentLength = j10;
            this.totalSegments = i10;
            this.bytesDownloaded = j11;
            this.segmentsDownloaded = i11;
        }

        private float getPercentDownloaded() {
            long j10 = this.contentLength;
            if (j10 != -1 && j10 != 0) {
                return Util.percentFloat(this.bytesDownloaded, j10);
            }
            int i10 = this.totalSegments;
            if (i10 != 0) {
                return Util.percentFloat(this.segmentsDownloaded, i10);
            }
            return -1.0f;
        }

        @Override // androidx.media3.datasource.cache.CacheWriter.ProgressListener
        public void onProgress(long j10, long j11, long j12) {
            long j13 = this.bytesDownloaded + j12;
            this.bytesDownloaded = j13;
            this.progressListener.onProgress(this.contentLength, j13, getPercentDownloaded());
        }

        public void onSegmentDownloaded() {
            this.segmentsDownloaded++;
            this.progressListener.onProgress(this.contentLength, this.bytesDownloaded, getPercentDownloaded());
        }
    }

    public static class Segment implements Comparable<Segment> {
        public final DataSpec dataSpec;
        public final long startTimeUs;

        public Segment(long j10, DataSpec dataSpec) {
            this.startTimeUs = j10;
            this.dataSpec = dataSpec;
        }

        @Override // java.lang.Comparable
        public int compareTo(Segment segment) {
            return Long.compare(this.startTimeUs, segment.startTimeUs);
        }
    }

    public static final class SegmentDownloadRunnable extends RunnableFutureTask<Void, IOException> {
        private final CacheWriter cacheWriter;
        public final CacheDataSource dataSource;
        private final ProgressNotifier progressNotifier;
        public final Segment segment;
        public final byte[] temporaryBuffer;

        public SegmentDownloadRunnable(Segment segment, CacheDataSource cacheDataSource, ProgressNotifier progressNotifier, byte[] bArr) {
            this.segment = segment;
            this.dataSource = cacheDataSource;
            this.progressNotifier = progressNotifier;
            this.temporaryBuffer = bArr;
            this.cacheWriter = new CacheWriter(cacheDataSource, segment.dataSpec, bArr, progressNotifier);
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        public void cancelWork() {
            this.cacheWriter.cancel();
        }

        @Override // androidx.media3.common.util.RunnableFutureTask
        public Void doWork() throws IOException {
            this.cacheWriter.cache();
            ProgressNotifier progressNotifier = this.progressNotifier;
            if (progressNotifier == null) {
                return null;
            }
            progressNotifier.onSegmentDownloaded();
            return null;
        }
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> parser, CacheDataSource.Factory factory, Executor executor, long j10) {
        this(mediaItem, parser, factory, executor, j10, 0L, C.TIME_UNSET);
    }

    private <T> void addActiveRunnable(RunnableFutureTask<T, ?> runnableFutureTask) throws InterruptedException {
        synchronized (this.activeRunnables) {
            try {
                if (this.isCanceled) {
                    throw new InterruptedException();
                }
                this.activeRunnables.add(runnableFutureTask);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean canMergeSegments(DataSpec dataSpec, DataSpec dataSpec2) {
        if (!dataSpec.uri.equals(dataSpec2.uri)) {
            return false;
        }
        long j10 = dataSpec.length;
        return j10 != -1 && dataSpec.position + j10 == dataSpec2.position && Objects.equals(dataSpec.key, dataSpec2.key) && dataSpec.flags == dataSpec2.flags && dataSpec.httpMethod == dataSpec2.httpMethod && dataSpec.httpRequestHeaders.equals(dataSpec2.httpRequestHeaders);
    }

    public static DataSpec getCompressibleDataSpec(Uri uri) {
        return new DataSpec.Builder().setUri(uri).setFlags(1).build();
    }

    private static void mergeSegments(List<Segment> list, CacheKeyFactory cacheKeyFactory, long j10) {
        HashMap map = new HashMap();
        int i10 = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            Segment segment = list.get(i11);
            String strBuildCacheKey = cacheKeyFactory.buildCacheKey(segment.dataSpec);
            Integer num = (Integer) map.get(strBuildCacheKey);
            Segment segment2 = num == null ? null : list.get(num.intValue());
            if (segment2 == null || segment.startTimeUs > segment2.startTimeUs + j10 || !canMergeSegments(segment2.dataSpec, segment.dataSpec)) {
                map.put(strBuildCacheKey, Integer.valueOf(i10));
                list.set(i10, segment);
                i10++;
            } else {
                long j11 = segment.dataSpec.length;
                DataSpec dataSpecSubrange = segment2.dataSpec.subrange(0L, j11 != -1 ? segment2.dataSpec.length + j11 : -1L);
                num.getClass();
                list.set(num.intValue(), new Segment(segment2.startTimeUs, dataSpecSubrange));
            }
        }
        Util.removeRange(list, i10, list.size());
    }

    private void removeActiveRunnable(RunnableFutureTask<?, ?> runnableFutureTask) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(runnableFutureTask);
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public void cancel() {
        synchronized (this.activeRunnables) {
            try {
                this.isCanceled = true;
                for (int i10 = 0; i10 < this.activeRunnables.size(); i10++) {
                    this.activeRunnables.get(i10).cancel(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void download(Downloader.ProgressListener progressListener) throws InterruptedException, IOException {
        CacheDataSource cacheDataSourceCreateDataSourceForDownloading;
        byte[] bArr;
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayDeque arrayDeque2 = new ArrayDeque();
        PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
        if (priorityTaskManager != null) {
            priorityTaskManager.add(-4000);
        }
        try {
            CacheDataSource cacheDataSourceCreateDataSourceForDownloading2 = this.cacheDataSourceFactory.createDataSourceForDownloading();
            FilterableManifest manifest = getManifest(cacheDataSourceCreateDataSourceForDownloading2, this.manifestDataSpec, false);
            if (!this.streamKeys.isEmpty()) {
                manifest = (FilterableManifest) manifest.copy(this.streamKeys);
            }
            List<Segment> segments = getSegments(cacheDataSourceCreateDataSourceForDownloading2, manifest, false);
            Collections.sort(segments);
            mergeSegments(segments, this.cacheKeyFactory, this.maxMergedSegmentStartTimeDiffUs);
            int size = segments.size();
            int i10 = 0;
            long j10 = 0;
            long j11 = 0;
            for (int size2 = segments.size() - 1; size2 >= 0; size2--) {
                DataSpec dataSpec = segments.get(size2).dataSpec;
                String strBuildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
                long j12 = dataSpec.length;
                if (j12 == -1) {
                    long jA = androidx.media3.datasource.cache.b.a(this.cache.getContentMetadata(strBuildCacheKey));
                    if (jA != -1) {
                        j12 = jA - dataSpec.position;
                    }
                }
                long j13 = j12;
                long cachedBytes = this.cache.getCachedBytes(strBuildCacheKey, dataSpec.position, j13);
                j11 += cachedBytes;
                if (j13 != -1) {
                    if (j13 == cachedBytes) {
                        i10++;
                        segments.remove(size2);
                    }
                    if (j10 != -1) {
                        j10 += j13;
                    }
                } else {
                    j10 = -1;
                }
            }
            ProgressNotifier progressNotifier = progressListener != null ? new ProgressNotifier(progressListener, j10, size, j11, i10) : null;
            arrayDeque.addAll(segments);
            while (!this.isCanceled && !arrayDeque.isEmpty()) {
                PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
                if (priorityTaskManager2 != null) {
                    priorityTaskManager2.proceed(-4000);
                }
                if (arrayDeque2.isEmpty()) {
                    cacheDataSourceCreateDataSourceForDownloading = this.cacheDataSourceFactory.createDataSourceForDownloading();
                    bArr = new byte[131072];
                } else {
                    SegmentDownloadRunnable segmentDownloadRunnable = (SegmentDownloadRunnable) arrayDeque2.removeFirst();
                    cacheDataSourceCreateDataSourceForDownloading = segmentDownloadRunnable.dataSource;
                    bArr = segmentDownloadRunnable.temporaryBuffer;
                }
                SegmentDownloadRunnable segmentDownloadRunnable2 = new SegmentDownloadRunnable((Segment) arrayDeque.removeFirst(), cacheDataSourceCreateDataSourceForDownloading, progressNotifier, bArr);
                addActiveRunnable(segmentDownloadRunnable2);
                this.executor.execute(segmentDownloadRunnable2);
                for (int size3 = this.activeRunnables.size() - 1; size3 >= 0; size3--) {
                    SegmentDownloadRunnable segmentDownloadRunnable3 = (SegmentDownloadRunnable) this.activeRunnables.get(size3);
                    if (arrayDeque.isEmpty() || segmentDownloadRunnable3.isDone()) {
                        try {
                            segmentDownloadRunnable3.get();
                            removeActiveRunnable(size3);
                            arrayDeque2.addLast(segmentDownloadRunnable3);
                        } catch (ExecutionException e5) {
                            Throwable cause = e5.getCause();
                            cause.getClass();
                            if (cause instanceof PriorityTaskManager.PriorityTooLowException) {
                                arrayDeque.addFirst(segmentDownloadRunnable3.segment);
                                removeActiveRunnable(size3);
                                arrayDeque2.addLast(segmentDownloadRunnable3);
                            } else {
                                if (cause instanceof IOException) {
                                    throw ((IOException) cause);
                                }
                                Util.sneakyThrow(cause);
                            }
                        }
                    }
                }
                segmentDownloadRunnable2.blockUntilStarted();
            }
            for (int i11 = 0; i11 < this.activeRunnables.size(); i11++) {
                this.activeRunnables.get(i11).cancel(true);
            }
            for (int size4 = this.activeRunnables.size() - 1; size4 >= 0; size4--) {
                this.activeRunnables.get(size4).blockUntilFinished();
                removeActiveRunnable(size4);
            }
            PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
            if (priorityTaskManager3 != null) {
                priorityTaskManager3.remove(-4000);
            }
        } catch (Throwable th) {
            for (int i12 = 0; i12 < this.activeRunnables.size(); i12++) {
                this.activeRunnables.get(i12).cancel(true);
            }
            for (int size5 = this.activeRunnables.size() - 1; size5 >= 0; size5--) {
                this.activeRunnables.get(size5).blockUntilFinished();
                removeActiveRunnable(size5);
            }
            PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
            if (priorityTaskManager4 != null) {
                priorityTaskManager4.remove(-4000);
            }
            throw th;
        }
    }

    public final <T> T execute(RunnableFutureTask<T, ?> runnableFutureTask, boolean z) throws Throwable {
        if (z) {
            runnableFutureTask.run();
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e5) {
                Throwable cause = e5.getCause();
                cause.getClass();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                Util.sneakyThrow(e5);
            }
        }
        while (!this.isCanceled) {
            PriorityTaskManager priorityTaskManager = this.priorityTaskManager;
            if (priorityTaskManager != null) {
                priorityTaskManager.proceed(-4000);
            }
            addActiveRunnable(runnableFutureTask);
            this.executor.execute(runnableFutureTask);
            try {
                return runnableFutureTask.get();
            } catch (ExecutionException e6) {
                Throwable cause2 = e6.getCause();
                cause2.getClass();
                if (!(cause2 instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (cause2 instanceof IOException) {
                        throw ((IOException) cause2);
                    }
                    Util.sneakyThrow(e6);
                }
            } finally {
                runnableFutureTask.blockUntilFinished();
                removeActiveRunnable((RunnableFutureTask<?, ?>) runnableFutureTask);
            }
        }
        throw new InterruptedException();
    }

    public final M getManifest(final DataSource dataSource, final DataSpec dataSpec, boolean z) throws InterruptedException, IOException {
        return (M) execute(new RunnableFutureTask<M, IOException>() { // from class: androidx.media3.exoplayer.offline.SegmentDownloader.1
            @Override // androidx.media3.common.util.RunnableFutureTask
            public M doWork() throws IOException {
                return (M) ParsingLoadable.load(dataSource, SegmentDownloader.this.manifestParser, dataSpec, 4);
            }
        }, z);
    }

    public abstract List<Segment> getSegments(DataSource dataSource, M m10, boolean z) throws InterruptedException, IOException;

    @Override // androidx.media3.exoplayer.offline.Downloader
    public final void remove() {
        CacheDataSource cacheDataSourceCreateDataSourceForRemovingDownload = this.cacheDataSourceFactory.createDataSourceForRemovingDownload();
        try {
            try {
                List<Segment> segments = getSegments(cacheDataSourceCreateDataSourceForRemovingDownload, getManifest(cacheDataSourceCreateDataSourceForRemovingDownload, this.manifestDataSpec, true), true);
                for (int i10 = 0; i10 < segments.size(); i10++) {
                    this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(segments.get(i10).dataSpec));
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Exception unused2) {
            }
        } finally {
            this.cache.removeResource(this.cacheKeyFactory.buildCacheKey(this.manifestDataSpec));
        }
    }

    public SegmentDownloader(MediaItem mediaItem, ParsingLoadable.Parser<M> parser, CacheDataSource.Factory factory, Executor executor, long j10, long j11, long j12) {
        mediaItem.localConfiguration.getClass();
        this.manifestDataSpec = getCompressibleDataSpec(mediaItem.localConfiguration.uri);
        this.manifestParser = parser;
        this.streamKeys = new ArrayList<>(mediaItem.localConfiguration.streamKeys);
        this.cacheDataSourceFactory = factory;
        this.executor = executor;
        this.startPositionUs = j11;
        this.durationUs = j12;
        Cache cache = factory.getCache();
        cache.getClass();
        this.cache = cache;
        this.cacheKeyFactory = factory.getCacheKeyFactory();
        this.priorityTaskManager = factory.getUpstreamPriorityTaskManager();
        this.activeRunnables = new ArrayList<>();
        this.maxMergedSegmentStartTimeDiffUs = Util.msToUs(j10);
    }

    private void removeActiveRunnable(int i10) {
        synchronized (this.activeRunnables) {
            this.activeRunnables.remove(i10);
        }
    }
}
