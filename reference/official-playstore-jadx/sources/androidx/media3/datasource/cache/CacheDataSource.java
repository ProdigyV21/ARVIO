package androidx.media3.datasource.cache;

import android.net.Uri;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSink;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.PlaceholderDataSource;
import androidx.media3.datasource.PriorityDataSource;
import androidx.media3.datasource.TeeDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSink;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class CacheDataSource implements DataSource {
    public static final int CACHE_IGNORED_REASON_ERROR = 0;
    public static final int CACHE_IGNORED_REASON_UNSET_LENGTH = 1;
    private static final int CACHE_NOT_IGNORED = -1;
    public static final int FLAG_BLOCK_ON_CACHE = 1;
    public static final int FLAG_IGNORE_CACHE_FOR_UNSET_LENGTH_REQUESTS = 4;
    public static final int FLAG_IGNORE_CACHE_ON_ERROR = 2;
    private static final long MIN_READ_BEFORE_CHECKING_CACHE = 102400;
    private Uri actualUri;
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final CacheKeyFactory cacheKeyFactory;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private long checkCachePosition;
    private DataSource currentDataSource;
    private long currentDataSourceBytesRead;
    private DataSpec currentDataSpec;
    private CacheSpan currentHoleSpan;
    private boolean currentRequestIgnoresCache;
    private final EventListener eventListener;
    private final boolean ignoreCacheForUnsetLengthRequests;
    private final boolean ignoreCacheOnError;
    private long readPosition;
    private DataSpec requestDataSpec;
    private boolean seenCacheError;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheIgnoredReason {
    }

    public interface EventListener {
        void onCacheIgnored(int i10);

        void onCachedBytesRead(long j10, long j11);
    }

    public static final class Factory implements DataSource.Factory {
        private Cache cache;
        private boolean cacheIsReadOnly;
        private DataSink.Factory cacheWriteDataSinkFactory;
        private EventListener eventListener;
        private int flags;
        private DataSource.Factory upstreamDataSourceFactory;
        private int upstreamPriority;
        private PriorityTaskManager upstreamPriorityTaskManager;
        private DataSource.Factory cacheReadDataSourceFactory = new FileDataSource.Factory();
        private CacheKeyFactory cacheKeyFactory = CacheKeyFactory.DEFAULT;

        private CacheDataSource createDataSourceInternal(DataSource dataSource, int i10, int i11) {
            DataSink dataSinkCreateDataSink;
            Cache cache = this.cache;
            cache.getClass();
            if (this.cacheIsReadOnly || dataSource == null) {
                dataSinkCreateDataSink = null;
            } else {
                DataSink.Factory factory = this.cacheWriteDataSinkFactory;
                dataSinkCreateDataSink = factory != null ? factory.createDataSink() : new CacheDataSink.Factory().setCache(cache).createDataSink();
            }
            return new CacheDataSource(cache, dataSource, this.cacheReadDataSourceFactory.createDataSource(), dataSinkCreateDataSink, this.cacheKeyFactory, i10, this.upstreamPriorityTaskManager, i11, this.eventListener);
        }

        public CacheDataSource createDataSourceForDownloading() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags | 1, -4000);
        }

        public CacheDataSource createDataSourceForRemovingDownload() {
            return createDataSourceInternal(null, this.flags | 1, -4000);
        }

        public Cache getCache() {
            return this.cache;
        }

        public CacheKeyFactory getCacheKeyFactory() {
            return this.cacheKeyFactory;
        }

        public PriorityTaskManager getUpstreamPriorityTaskManager() {
            return this.upstreamPriorityTaskManager;
        }

        public Factory setCache(Cache cache) {
            this.cache = cache;
            return this;
        }

        public Factory setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.cacheKeyFactory = cacheKeyFactory;
            return this;
        }

        public Factory setCacheReadDataSourceFactory(DataSource.Factory factory) {
            this.cacheReadDataSourceFactory = factory;
            return this;
        }

        public Factory setCacheWriteDataSinkFactory(DataSink.Factory factory) {
            this.cacheWriteDataSinkFactory = factory;
            this.cacheIsReadOnly = factory == null;
            return this;
        }

        public Factory setEventListener(EventListener eventListener) {
            this.eventListener = eventListener;
            return this;
        }

        public Factory setFlags(int i10) {
            this.flags = i10;
            return this;
        }

        public Factory setUpstreamDataSourceFactory(DataSource.Factory factory) {
            this.upstreamDataSourceFactory = factory;
            return this;
        }

        public Factory setUpstreamPriority(int i10) {
            this.upstreamPriority = i10;
            return this;
        }

        public Factory setUpstreamPriorityTaskManager(PriorityTaskManager priorityTaskManager) {
            this.upstreamPriorityTaskManager = priorityTaskManager;
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public CacheDataSource createDataSource() {
            DataSource.Factory factory = this.upstreamDataSourceFactory;
            return createDataSourceInternal(factory != null ? factory.createDataSource() : null, this.flags, this.upstreamPriority);
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
        } finally {
            this.currentDataSpec = null;
            this.currentDataSource = null;
            CacheSpan cacheSpan = this.currentHoleSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.currentHoleSpan = null;
            }
        }
    }

    private static Uri getRedirectedUriOrDefault(Cache cache, String str, Uri uri) {
        Uri uriB = b.b(cache.getContentMetadata(str));
        return uriB != null ? uriB : uri;
    }

    private void handleBeforeThrow(Throwable th) {
        if (isReadingFromCache() || (th instanceof Cache.CacheException)) {
            this.seenCacheError = true;
        }
    }

    private boolean isBypassingCache() {
        return this.currentDataSource == this.upstreamDataSource;
    }

    private boolean isReadingFromCache() {
        return this.currentDataSource == this.cacheReadDataSource;
    }

    private boolean isReadingFromUpstream() {
        return !isReadingFromCache();
    }

    private boolean isWritingToCache() {
        return this.currentDataSource == this.cacheWriteDataSource;
    }

    private void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }

    private void notifyCacheIgnored(int i10) {
        EventListener eventListener = this.eventListener;
        if (eventListener != null) {
            eventListener.onCacheIgnored(i10);
        }
    }

    private void openNextSource(DataSpec dataSpec, boolean z) throws IOException {
        CacheSpan cacheSpanStartReadWrite;
        long jMin;
        DataSpec dataSpecBuild;
        DataSource dataSource;
        String str = (String) Util.castNonNull(dataSpec.key);
        if (this.currentRequestIgnoresCache) {
            cacheSpanStartReadWrite = null;
        } else if (this.blockOnCache) {
            try {
                cacheSpanStartReadWrite = this.cache.startReadWrite(str, this.readPosition, this.bytesRemaining);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                throw new InterruptedIOException();
            }
        } else {
            cacheSpanStartReadWrite = this.cache.startReadWriteNonBlocking(str, this.readPosition, this.bytesRemaining);
        }
        if (cacheSpanStartReadWrite == null) {
            dataSource = this.upstreamDataSource;
            dataSpecBuild = dataSpec.buildUpon().setPosition(this.readPosition).setLength(this.bytesRemaining).build();
        } else if (cacheSpanStartReadWrite.isCached) {
            Uri uriFromFile = Uri.fromFile((File) Util.castNonNull(cacheSpanStartReadWrite.file));
            long j10 = cacheSpanStartReadWrite.position;
            long j11 = this.readPosition - j10;
            long jMin2 = cacheSpanStartReadWrite.length - j11;
            long j12 = this.bytesRemaining;
            if (j12 != -1) {
                jMin2 = Math.min(jMin2, j12);
            }
            dataSpecBuild = dataSpec.buildUpon().setUri(uriFromFile).setUriPositionOffset(j10).setPosition(j11).setLength(jMin2).build();
            dataSource = this.cacheReadDataSource;
        } else {
            if (cacheSpanStartReadWrite.isOpenEnded()) {
                jMin = this.bytesRemaining;
            } else {
                jMin = cacheSpanStartReadWrite.length;
                long j13 = this.bytesRemaining;
                if (j13 != -1) {
                    jMin = Math.min(jMin, j13);
                }
            }
            dataSpecBuild = dataSpec.buildUpon().setPosition(this.readPosition).setLength(jMin).build();
            dataSource = this.cacheWriteDataSource;
            if (dataSource == null) {
                dataSource = this.upstreamDataSource;
                this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                cacheSpanStartReadWrite = null;
            }
        }
        this.checkCachePosition = (this.currentRequestIgnoresCache || dataSource != this.upstreamDataSource) ? Long.MAX_VALUE : this.readPosition + MIN_READ_BEFORE_CHECKING_CACHE;
        if (z) {
            ac.b.s(isBypassingCache());
            if (dataSource == this.upstreamDataSource) {
                return;
            }
            try {
                closeCurrentSource();
            } catch (Throwable th) {
                if (((CacheSpan) Util.castNonNull(cacheSpanStartReadWrite)).isHoleSpan()) {
                    this.cache.releaseHoleSpan(cacheSpanStartReadWrite);
                }
                throw th;
            }
        }
        if (cacheSpanStartReadWrite != null && cacheSpanStartReadWrite.isHoleSpan()) {
            this.currentHoleSpan = cacheSpanStartReadWrite;
        }
        this.currentDataSource = dataSource;
        this.currentDataSpec = dataSpecBuild;
        this.currentDataSourceBytesRead = 0L;
        long jOpen = dataSource.open(dataSpecBuild);
        ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
        if (dataSpecBuild.length == -1 && jOpen != -1) {
            this.bytesRemaining = jOpen;
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition + jOpen);
        }
        if (isReadingFromUpstream()) {
            Uri uri = dataSource.getUri();
            this.actualUri = uri;
            ContentMetadataMutations.setRedirectedUri(contentMetadataMutations, dataSpec.uri.equals(uri) ? null : this.actualUri);
        }
        if (isWritingToCache()) {
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    private void setNoBytesRemainingAndMaybeStoreLength(String str) throws IOException {
        this.bytesRemaining = 0L;
        if (isWritingToCache()) {
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataMutations.setContentLength(contentMetadataMutations, this.readPosition);
            this.cache.applyContentMetadataMutations(str, contentMetadataMutations);
        }
    }

    private int shouldIgnoreCacheForRequest(DataSpec dataSpec) {
        if (this.ignoreCacheOnError && this.seenCacheError) {
            return 0;
        }
        return (this.ignoreCacheForUnsetLengthRequests && dataSpec.length == -1) ? 1 : -1;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener transferListener) {
        transferListener.getClass();
        this.cacheReadDataSource.addTransferListener(transferListener);
        this.upstreamDataSource.addTransferListener(transferListener);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() throws IOException {
        this.requestDataSpec = null;
        this.actualUri = null;
        this.readPosition = 0L;
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    public Cache getCache() {
        return this.cache;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        return isReadingFromUpstream() ? this.upstreamDataSource.getResponseHeaders() : Collections.EMPTY_MAP;
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.actualUri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            String strBuildCacheKey = this.cacheKeyFactory.buildCacheKey(dataSpec);
            DataSpec dataSpecBuild = dataSpec.buildUpon().setKey(strBuildCacheKey).build();
            this.requestDataSpec = dataSpecBuild;
            this.actualUri = getRedirectedUriOrDefault(this.cache, strBuildCacheKey, dataSpecBuild.uri);
            this.readPosition = dataSpec.position;
            int iShouldIgnoreCacheForRequest = shouldIgnoreCacheForRequest(dataSpec);
            boolean z = iShouldIgnoreCacheForRequest != -1;
            this.currentRequestIgnoresCache = z;
            if (z) {
                notifyCacheIgnored(iShouldIgnoreCacheForRequest);
            }
            if (this.currentRequestIgnoresCache) {
                this.bytesRemaining = -1L;
            } else {
                long jA = b.a(this.cache.getContentMetadata(strBuildCacheKey));
                this.bytesRemaining = jA;
                if (jA != -1) {
                    long j10 = jA - dataSpec.position;
                    this.bytesRemaining = j10;
                    if (j10 < 0) {
                        throw new DataSourceException(2008);
                    }
                }
            }
            long jMin = dataSpec.length;
            if (jMin != -1) {
                long j11 = this.bytesRemaining;
                if (j11 != -1) {
                    jMin = Math.min(j11, jMin);
                }
                this.bytesRemaining = jMin;
            }
            long j12 = this.bytesRemaining;
            if (j12 > 0 || j12 == -1) {
                openNextSource(dataSpecBuild, false);
            }
            long j13 = dataSpec.length;
            return j13 != -1 ? j13 : this.bytesRemaining;
        } catch (Throwable th) {
            handleBeforeThrow(th);
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
    
        if (r17.currentDataSourceBytesRead < r13) goto L29;
     */
    @Override // androidx.media3.common.DataReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read(byte[] r18, int r19, int r20) throws java.io.IOException {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            r2 = 0
            if (r0 != 0) goto L8
            return r2
        L8:
            long r3 = r1.bytesRemaining
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            r4 = -1
            if (r3 != 0) goto L12
            return r4
        L12:
            androidx.media3.datasource.DataSpec r3 = r1.requestDataSpec
            r3.getClass()
            androidx.media3.datasource.DataSpec r7 = r1.currentDataSpec
            r7.getClass()
            long r8 = r1.readPosition     // Catch: java.lang.Throwable -> L29
            long r10 = r1.checkCachePosition     // Catch: java.lang.Throwable -> L29
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 < 0) goto L2b
            r8 = 1
            r1.openNextSource(r3, r8)     // Catch: java.lang.Throwable -> L29
            goto L2b
        L29:
            r0 = move-exception
            goto L94
        L2b:
            androidx.media3.datasource.DataSource r8 = r1.currentDataSource     // Catch: java.lang.Throwable -> L29
            r8.getClass()     // Catch: java.lang.Throwable -> L29
            r9 = r18
            r10 = r19
            int r8 = r8.read(r9, r10, r0)     // Catch: java.lang.Throwable -> L29
            r11 = -1
            if (r8 == r4) goto L5d
            boolean r0 = r1.isReadingFromCache()     // Catch: java.lang.Throwable -> L29
            if (r0 == 0) goto L48
            long r2 = r1.totalCachedBytesRead     // Catch: java.lang.Throwable -> L29
            long r4 = (long) r8     // Catch: java.lang.Throwable -> L29
            long r2 = r2 + r4
            r1.totalCachedBytesRead = r2     // Catch: java.lang.Throwable -> L29
        L48:
            long r2 = r1.readPosition     // Catch: java.lang.Throwable -> L29
            long r4 = (long) r8     // Catch: java.lang.Throwable -> L29
            long r2 = r2 + r4
            r1.readPosition = r2     // Catch: java.lang.Throwable -> L29
            long r2 = r1.currentDataSourceBytesRead     // Catch: java.lang.Throwable -> L29
            long r2 = r2 + r4
            r1.currentDataSourceBytesRead = r2     // Catch: java.lang.Throwable -> L29
            long r2 = r1.bytesRemaining     // Catch: java.lang.Throwable -> L29
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L88
            long r2 = r2 - r4
            r1.bytesRemaining = r2     // Catch: java.lang.Throwable -> L29
            return r8
        L5d:
            boolean r4 = r1.isReadingFromUpstream()     // Catch: java.lang.Throwable -> L29
            if (r4 == 0) goto L7c
            long r13 = r7.length     // Catch: java.lang.Throwable -> L29
            int r4 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r4 == 0) goto L70
            r15 = r5
            long r5 = r1.currentDataSourceBytesRead     // Catch: java.lang.Throwable -> L29
            int r4 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r4 >= 0) goto L7d
        L70:
            java.lang.String r0 = r3.key     // Catch: java.lang.Throwable -> L29
            java.lang.Object r0 = androidx.media3.common.util.Util.castNonNull(r0)     // Catch: java.lang.Throwable -> L29
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L29
            r1.setNoBytesRemainingAndMaybeStoreLength(r0)     // Catch: java.lang.Throwable -> L29
            return r8
        L7c:
            r15 = r5
        L7d:
            long r4 = r1.bytesRemaining     // Catch: java.lang.Throwable -> L29
            int r6 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r6 > 0) goto L89
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 != 0) goto L88
            goto L89
        L88:
            return r8
        L89:
            r1.closeCurrentSource()     // Catch: java.lang.Throwable -> L29
            r1.openNextSource(r3, r2)     // Catch: java.lang.Throwable -> L29
            int r0 = r17.read(r18, r19, r20)     // Catch: java.lang.Throwable -> L29
            return r0
        L94:
            r1.handleBeforeThrow(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.cache.CacheDataSource.read(byte[], int, int):int");
    }

    public CacheDataSource(Cache cache, DataSource dataSource) {
        this(cache, dataSource, 0);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, int i10) {
        this(cache, dataSource, new FileDataSource(), new CacheDataSink(cache, CacheDataSink.DEFAULT_FRAGMENT_SIZE), i10, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i10, EventListener eventListener) {
        this(cache, dataSource, dataSource2, dataSink, i10, eventListener, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, int i10, EventListener eventListener, CacheKeyFactory cacheKeyFactory) {
        this(cache, dataSource, dataSource2, dataSink, cacheKeyFactory, i10, null, -1000, eventListener);
    }

    private CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, CacheKeyFactory cacheKeyFactory, int i10, PriorityTaskManager priorityTaskManager, int i11, EventListener eventListener) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.cacheKeyFactory = cacheKeyFactory == null ? CacheKeyFactory.DEFAULT : cacheKeyFactory;
        this.blockOnCache = (i10 & 1) != 0;
        this.ignoreCacheOnError = (i10 & 2) != 0;
        this.ignoreCacheForUnsetLengthRequests = (i10 & 4) != 0;
        if (dataSource != null) {
            dataSource = priorityTaskManager != null ? new PriorityDataSource(dataSource, priorityTaskManager, i11) : dataSource;
            this.upstreamDataSource = dataSource;
            this.cacheWriteDataSource = dataSink != null ? new TeeDataSource(dataSource, dataSink) : null;
        } else {
            this.upstreamDataSource = PlaceholderDataSource.INSTANCE;
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }
}
