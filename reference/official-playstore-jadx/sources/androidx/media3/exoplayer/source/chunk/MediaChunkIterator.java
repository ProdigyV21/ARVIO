package androidx.media3.exoplayer.source.chunk;

import androidx.media3.datasource.DataSpec;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = new MediaChunkIterator() { // from class: androidx.media3.exoplayer.source.chunk.MediaChunkIterator.1
        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public boolean isEnded() {
            return true;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public boolean next() {
            return false;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public void reset() {
        }
    };

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();

    void reset();
}
