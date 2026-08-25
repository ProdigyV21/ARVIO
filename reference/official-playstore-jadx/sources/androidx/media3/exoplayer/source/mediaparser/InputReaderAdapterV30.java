package androidx.media3.exoplayer.source.mediaparser;

import android.media.MediaParser$SeekableInputReader;
import androidx.media3.common.DataReader;
import androidx.media3.common.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class InputReaderAdapterV30 implements MediaParser$SeekableInputReader {
    private long currentPosition;
    private DataReader dataReader;
    private long lastSeekPosition;
    private long resourceLength;

    public long getAndResetSeekPosition() {
        long j10 = this.lastSeekPosition;
        this.lastSeekPosition = -1L;
        return j10;
    }

    public long getLength() {
        return this.resourceLength;
    }

    public long getPosition() {
        return this.currentPosition;
    }

    public int read(byte[] bArr, int i10, int i11) throws IOException {
        int i12 = ((DataReader) Util.castNonNull(this.dataReader)).read(bArr, i10, i11);
        this.currentPosition += (long) i12;
        return i12;
    }

    public void seekToPosition(long j10) {
        this.lastSeekPosition = j10;
    }

    public void setCurrentPosition(long j10) {
        this.currentPosition = j10;
    }

    public void setDataReader(DataReader dataReader, long j10) {
        this.dataReader = dataReader;
        this.resourceLength = j10;
        this.lastSeekPosition = -1L;
    }
}
