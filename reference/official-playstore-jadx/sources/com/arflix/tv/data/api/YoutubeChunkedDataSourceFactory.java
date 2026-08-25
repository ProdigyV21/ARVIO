package com.arflix.tv.data.api;

import android.net.Uri;
import android.util.Log;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/arflix/tv/data/api/YoutubeChunkedDataSourceFactory;", "Landroidx/media3/datasource/DataSource$Factory;", "chunkSizeBytes", "", "<init>", "(J)V", "createDataSource", "Landroidx/media3/datasource/DataSource;", "Companion", "YoutubeChunkedDataSource", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class YoutubeChunkedDataSourceFactory implements DataSource.Factory {
    public static final int $stable = 0;
    private static final long CHUNK_SIZE = 10485760;
    private static final String TAG = "YTChunkedDS";
    private final long chunkSizeBytes;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010 R\u0016\u0010'\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010 R\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010 R\u0016\u0010)\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010 R\u0018\u0010*\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/arflix/tv/data/api/YoutubeChunkedDataSourceFactory$YoutubeChunkedDataSource;", "Landroidx/media3/datasource/DataSource;", "Landroidx/media3/datasource/DefaultHttpDataSource;", "upstream", "", "chunkSize", "<init>", "(Landroidx/media3/datasource/DefaultHttpDataSource;J)V", "openNextChunk", "()J", "Landroidx/media3/datasource/TransferListener;", "transferListener", "Lx6/t0;", "addTransferListener", "(Landroidx/media3/datasource/TransferListener;)V", "Landroidx/media3/datasource/DataSpec;", "dataSpec", TtmlNode.TEXT_EMPHASIS_MARK_OPEN, "(Landroidx/media3/datasource/DataSpec;)J", "", "buffer", "", "offset", "length", "read", "([BII)I", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "close", "()V", "Landroidx/media3/datasource/DefaultHttpDataSource;", "J", "currentUri", "Landroid/net/Uri;", "", "isYouTubeStream", "Z", "totalContentLength", "currentChunkStart", "currentChunkEnd", "bytesReadInChunk", "originalDataSpec", "Landroidx/media3/datasource/DataSpec;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class YoutubeChunkedDataSource implements DataSource {
        private long bytesReadInChunk;
        private final long chunkSize;
        private long currentChunkEnd;
        private long currentChunkStart;
        private Uri currentUri;
        private boolean isYouTubeStream;
        private DataSpec originalDataSpec;
        private long totalContentLength = -1;
        private final DefaultHttpDataSource upstream;

        public YoutubeChunkedDataSource(DefaultHttpDataSource defaultHttpDataSource, long j10) {
            this.upstream = defaultHttpDataSource;
            this.chunkSize = j10;
        }

        private final long openNextChunk() throws HttpDataSource.HttpDataSourceException {
            long jMin;
            DataSpec dataSpec = this.originalDataSpec;
            if (dataSpec == null) {
                throw new IllegalStateException("No DataSpec");
            }
            long j10 = this.totalContentLength;
            if (j10 != -1) {
                long j11 = this.currentChunkStart;
                jMin = Math.min((this.chunkSize + j11) - 1, (j11 + j10) - 1);
            } else {
                jMin = (this.currentChunkStart + this.chunkSize) - 1;
            }
            this.currentChunkEnd = jMin;
            DataSpec dataSpecBuild = dataSpec.buildUpon().setUri(dataSpec.uri.buildUpon().appendQueryParameter("range", this.currentChunkStart + "-" + this.currentChunkEnd).build()).setPosition(0L).setLength(-1L).build();
            this.bytesReadInChunk = 0L;
            this.upstream.open(dataSpecBuild);
            long j12 = this.totalContentLength;
            if (j12 != -1) {
                return j12;
            }
            return -1L;
        }

        @Override // androidx.media3.datasource.DataSource
        public void addTransferListener(TransferListener transferListener) {
            this.upstream.addTransferListener(transferListener);
        }

        @Override // androidx.media3.datasource.DataSource
        public void close() throws HttpDataSource.HttpDataSourceException {
            this.upstream.close();
            this.currentUri = null;
            this.originalDataSpec = null;
        }

        @Override // androidx.media3.datasource.DataSource
        public final /* synthetic */ Map getResponseHeaders() {
            return androidx.media3.datasource.a.a(this);
        }

        @Override // androidx.media3.datasource.DataSource
        public Uri getUri() {
            Uri uri = this.upstream.getUri();
            return uri == null ? this.currentUri : uri;
        }

        @Override // androidx.media3.datasource.DataSource
        public long open(DataSpec dataSpec) {
            String host = dataSpec.uri.getHost();
            if (host == null) {
                host = "";
            }
            boolean zT = o.T(host, "googlevideo.com", false);
            this.isYouTubeStream = zT;
            if (!zT) {
                return this.upstream.open(dataSpec);
            }
            this.originalDataSpec = dataSpec;
            this.currentChunkStart = dataSpec.position;
            this.totalContentLength = dataSpec.length;
            return openNextChunk();
        }

        @Override // androidx.media3.common.DataReader
        public int read(byte[] buffer, int offset, int length) throws Exception {
            if (!this.isYouTubeStream) {
                return this.upstream.read(buffer, offset, length);
            }
            int i10 = this.upstream.read(buffer, offset, length);
            if (i10 != -1) {
                this.bytesReadInChunk += (long) i10;
                return i10;
            }
            long j10 = this.bytesReadInChunk;
            this.upstream.close();
            long j11 = this.currentChunkEnd;
            long j12 = this.currentChunkStart;
            if (j10 < (j11 - j12) + 1) {
                return -1;
            }
            this.currentChunkStart = j12 + j10;
            long j13 = this.totalContentLength;
            if (j13 != -1) {
                long j14 = j13 - j10;
                this.totalContentLength = j14;
                if (j14 <= 0) {
                    return -1;
                }
            }
            try {
                openNextChunk();
                return this.upstream.read(buffer, offset, length);
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                Log.w(YoutubeChunkedDataSourceFactory.TAG, "Failed to open next chunk at " + this.currentChunkStart + ": " + e5.getMessage());
                return -1;
            }
        }
    }

    public YoutubeChunkedDataSourceFactory() {
        this(0L, 1, null);
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public DataSource createDataSource() {
        return new YoutubeChunkedDataSource(new DefaultHttpDataSource.Factory().setConnectTimeoutMs(15000).setReadTimeoutMs(15000).setAllowCrossProtocolRedirects(true).createDataSource(), this.chunkSizeBytes);
    }

    public YoutubeChunkedDataSourceFactory(long j10) {
        this.chunkSizeBytes = j10;
    }

    public /* synthetic */ YoutubeChunkedDataSourceFactory(long j10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? CHUNK_SIZE : j10);
    }
}
