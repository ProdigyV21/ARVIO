package androidx.media3.datasource.cache;

import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes3.dex */
public final class CacheWriter {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;
    private long bytesCached;
    private final Cache cache;
    private final String cacheKey;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private long endPosition;
    private volatile boolean isCanceled;
    private long nextPosition;
    private final ProgressListener progressListener;
    private final byte[] temporaryBuffer;

    public interface ProgressListener {
        void onProgress(long j10, long j11, long j12);
    }

    public CacheWriter(CacheDataSource cacheDataSource, DataSpec dataSpec, byte[] bArr, ProgressListener progressListener) {
        this.dataSource = cacheDataSource;
        this.cache = cacheDataSource.getCache();
        this.dataSpec = dataSpec;
        this.temporaryBuffer = bArr == null ? new byte[131072] : bArr;
        this.progressListener = progressListener;
        this.cacheKey = cacheDataSource.getCacheKeyFactory().buildCacheKey(dataSpec);
        this.nextPosition = dataSpec.position;
    }

    private long getLength() {
        long j10 = this.endPosition;
        if (j10 == -1) {
            return -1L;
        }
        return j10 - this.dataSpec.position;
    }

    private void onNewBytesCached(long j10) {
        this.bytesCached += j10;
        ProgressListener progressListener = this.progressListener;
        if (progressListener != null) {
            progressListener.onProgress(getLength(), this.bytesCached, j10);
        }
    }

    private void onRequestEndPosition(long j10) {
        if (this.endPosition == j10) {
            return;
        }
        this.endPosition = j10;
        ProgressListener progressListener = this.progressListener;
        if (progressListener != null) {
            progressListener.onProgress(getLength(), this.bytesCached, 0L);
        }
    }

    private long readBlockToCache(long j10, long j11) throws Exception {
        long jOpen;
        boolean z = true;
        boolean z5 = j10 + j11 == this.endPosition || j11 == -1;
        if (j11 != -1) {
            try {
                jOpen = this.dataSource.open(this.dataSpec.buildUpon().setPosition(j10).setLength(j11).build());
            } catch (Exception unused) {
                DataSourceUtil.closeQuietly(this.dataSource);
                z = false;
                jOpen = -1;
            }
        } else {
            z = false;
            jOpen = -1;
        }
        if (!z) {
            throwIfCanceled();
            try {
                jOpen = this.dataSource.open(this.dataSpec.buildUpon().setPosition(j10).setLength(-1L).build());
            } catch (Exception e5) {
                DataSourceUtil.closeQuietly(this.dataSource);
                throw e5;
            }
        }
        if (z5 && jOpen != -1) {
            try {
                onRequestEndPosition(jOpen + j10);
            } catch (Exception e6) {
                DataSourceUtil.closeQuietly(this.dataSource);
                throw e6;
            }
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 != -1) {
            throwIfCanceled();
            CacheDataSource cacheDataSource = this.dataSource;
            byte[] bArr = this.temporaryBuffer;
            i10 = cacheDataSource.read(bArr, 0, bArr.length);
            if (i10 != -1) {
                onNewBytesCached(i10);
                i11 += i10;
            }
        }
        if (z5) {
            onRequestEndPosition(j10 + ((long) i11));
        }
        this.dataSource.close();
        return i11;
    }

    private void throwIfCanceled() throws InterruptedIOException {
        if (this.isCanceled) {
            throw new InterruptedIOException();
        }
    }

    public void cache() throws IOException {
        throwIfCanceled();
        Cache cache = this.cache;
        String str = this.cacheKey;
        DataSpec dataSpec = this.dataSpec;
        this.bytesCached = cache.getCachedBytes(str, dataSpec.position, dataSpec.length);
        DataSpec dataSpec2 = this.dataSpec;
        long j10 = dataSpec2.length;
        if (j10 != -1) {
            this.endPosition = dataSpec2.position + j10;
        } else {
            long jA = b.a(this.cache.getContentMetadata(this.cacheKey));
            if (jA == -1) {
                jA = -1;
            }
            this.endPosition = jA;
        }
        ProgressListener progressListener = this.progressListener;
        if (progressListener != null) {
            progressListener.onProgress(getLength(), this.bytesCached, 0L);
        }
        while (true) {
            long j11 = this.endPosition;
            if (j11 != -1 && this.nextPosition >= j11) {
                return;
            }
            throwIfCanceled();
            long j12 = this.endPosition;
            long cachedLength = this.cache.getCachedLength(this.cacheKey, this.nextPosition, j12 == -1 ? Long.MAX_VALUE : j12 - this.nextPosition);
            if (cachedLength > 0) {
                this.nextPosition += cachedLength;
            } else {
                long j13 = -cachedLength;
                if (j13 == Long.MAX_VALUE) {
                    j13 = -1;
                }
                long j14 = this.nextPosition;
                this.nextPosition = j14 + readBlockToCache(j14, j13);
            }
        }
    }

    public void cancel() {
        this.isCanceled = true;
    }
}
