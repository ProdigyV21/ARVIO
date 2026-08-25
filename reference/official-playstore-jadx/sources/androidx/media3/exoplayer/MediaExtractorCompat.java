package androidx.media3.exoplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaDataSource;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.net.Uri;
import android.os.PersistableBundle;
import androidx.media3.common.DrmInitData;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public final class MediaExtractorCompat {
    public static final int SEEK_TO_CLOSEST_SYNC = 2;
    public static final int SEEK_TO_NEXT_SYNC = 1;
    public static final int SEEK_TO_PREVIOUS_SYNC = 0;
    private final MediaExtractorCompatInternal delegate;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeekMode {
    }

    public MediaExtractorCompat(Context context) {
        this(new DefaultExtractorsFactory(), new DefaultDataSource.Factory(context));
    }

    public boolean advance() {
        return this.delegate.advance();
    }

    public Allocator getAllocator() {
        return this.delegate.getAllocator();
    }

    public long getCachedDuration() {
        return this.delegate.getCachedDuration();
    }

    public DrmInitData getDrmInitData() {
        return this.delegate.getDrmInitData();
    }

    public LogSessionId getLogSessionId() {
        return this.delegate.getLogSessionId();
    }

    public PersistableBundle getMetrics() {
        return this.delegate.getMetrics();
    }

    public Map<UUID, byte[]> getPsshInfo() {
        return this.delegate.getPsshInfo();
    }

    public boolean getSampleCryptoInfo(MediaCodec.CryptoInfo cryptoInfo) {
        return this.delegate.getSampleCryptoInfo(cryptoInfo);
    }

    public int getSampleFlags() {
        return this.delegate.getSampleFlags();
    }

    public long getSampleSize() {
        return this.delegate.getSampleSize();
    }

    public long getSampleTime() {
        return this.delegate.getSampleTime();
    }

    public int getSampleTrackIndex() {
        return this.delegate.getSampleTrackIndex();
    }

    public int getTrackCount() {
        return this.delegate.getTrackCount();
    }

    public MediaFormat getTrackFormat(int i10) {
        return this.delegate.getTrackFormat(i10);
    }

    public boolean hasCacheReachedEndOfStream() {
        return this.delegate.hasCacheReachedEndOfStream();
    }

    public int readSampleData(ByteBuffer byteBuffer, int i10) {
        return this.delegate.readSampleData(byteBuffer, i10);
    }

    public void release() {
        this.delegate.release();
    }

    public void seekTo(long j10, int i10) {
        this.delegate.seekTo(j10, i10);
    }

    public void selectTrack(int i10) {
        this.delegate.selectTrack(i10);
    }

    public void setDataSource(Uri uri, long j10) throws IOException {
        this.delegate.setDataSource(uri, j10);
    }

    public void setLogSessionId(LogSessionId logSessionId) {
        this.delegate.setLogSessionId(logSessionId);
    }

    public void unselectTrack(int i10) {
        this.delegate.unselectTrack(i10);
    }

    public MediaExtractorCompat(ExtractorsFactory extractorsFactory, DataSource.Factory factory) {
        this.delegate = new MediaExtractorCompatInternal(extractorsFactory, factory);
    }

    public void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IOException {
        this.delegate.setDataSource(assetFileDescriptor);
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        this.delegate.setDataSource(fileDescriptor);
    }

    public void setDataSource(FileDescriptor fileDescriptor, long j10, long j11) throws IOException {
        this.delegate.setDataSource(fileDescriptor, j10, j11);
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException {
        this.delegate.setDataSource(context, uri, map);
    }

    public void setDataSource(String str) throws IOException {
        this.delegate.setDataSource(str);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException {
        this.delegate.setDataSource(str, map);
    }

    public void setDataSource(MediaDataSource mediaDataSource) throws IOException {
        this.delegate.setDataSource(mediaDataSource);
    }
}
