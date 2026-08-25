package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultExtractorInput implements ExtractorInput {
    private static final int PEEK_MAX_FREE_SPACE = 524288;
    private static final int PEEK_MIN_FREE_SPACE_AFTER_RESIZE = 65536;
    private static final int SCRATCH_SPACE_SIZE = 4096;
    private final DataReader dataReader;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;
    private byte[] peekBuffer = new byte[65536];
    private final byte[] scratchSpace = new byte[4096];

    static {
        MediaLibraryInfo.registerModule("media3.extractor");
    }

    public DefaultExtractorInput(DataReader dataReader, long j10, long j11) {
        this.dataReader = dataReader;
        this.position = j10;
        this.streamLength = j11;
    }

    private void commitBytesRead(int i10) {
        if (i10 != -1) {
            this.position += (long) i10;
        }
    }

    private void ensureSpaceForPeek(int i10) {
        int i11 = this.peekBufferPosition + i10;
        byte[] bArr = this.peekBuffer;
        if (i11 > bArr.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Util.constrainValue(bArr.length * 2, 65536 + i11, i11 + PEEK_MAX_FREE_SPACE));
        }
    }

    private int readFromPeekBuffer(byte[] bArr, int i10, int i11) {
        int i12 = this.peekBufferLength;
        if (i12 == 0) {
            return 0;
        }
        int iMin = Math.min(i12, i11);
        System.arraycopy(this.peekBuffer, 0, bArr, i10, iMin);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private int readFromUpstream(byte[] bArr, int i10, int i11, int i12, boolean z) throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
        int i13 = this.dataReader.read(bArr, i10 + i12, i11 - i12);
        if (i13 != -1) {
            return i12 + i13;
        }
        if (i12 == 0 && z) {
            return -1;
        }
        throw new EOFException();
    }

    private int skipFromPeekBuffer(int i10) {
        int iMin = Math.min(this.peekBufferLength, i10);
        updatePeekBuffer(iMin);
        return iMin;
    }

    private void updatePeekBuffer(int i10) {
        int i11 = this.peekBufferLength - i10;
        this.peekBufferLength = i11;
        this.peekBufferPosition = 0;
        byte[] bArr = this.peekBuffer;
        byte[] bArr2 = i11 < bArr.length - PEEK_MAX_FREE_SPACE ? new byte[65536 + i11] : bArr;
        System.arraycopy(bArr, i10, bArr2, 0, i11);
        this.peekBuffer = bArr2;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean advancePeekPosition(int i10, boolean z) throws IOException {
        ensureSpaceForPeek(i10);
        int fromUpstream = this.peekBufferLength - this.peekBufferPosition;
        while (fromUpstream < i10) {
            int i11 = i10;
            boolean z5 = z;
            fromUpstream = readFromUpstream(this.peekBuffer, this.peekBufferPosition, i11, fromUpstream, z5);
            if (fromUpstream == -1) {
                return false;
            }
            this.peekBufferLength = this.peekBufferPosition + fromUpstream;
            i10 = i11;
            z = z5;
        }
        this.peekBufferPosition += i10;
        return true;
    }

    public DataReader getDataReader() {
        return this.dataReader;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getLength() {
        return this.streamLength;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.position + ((long) this.peekBufferPosition);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int peek(byte[] bArr, int i10, int i11) throws IOException {
        DefaultExtractorInput defaultExtractorInput;
        int iMin;
        ensureSpaceForPeek(i11);
        int i12 = this.peekBufferLength;
        int i13 = this.peekBufferPosition;
        int i14 = i12 - i13;
        if (i14 == 0) {
            defaultExtractorInput = this;
            iMin = defaultExtractorInput.readFromUpstream(this.peekBuffer, i13, i11, 0, true);
            if (iMin == -1) {
                return -1;
            }
            defaultExtractorInput.peekBufferLength += iMin;
        } else {
            defaultExtractorInput = this;
            iMin = Math.min(i11, i14);
        }
        System.arraycopy(defaultExtractorInput.peekBuffer, defaultExtractorInput.peekBufferPosition, bArr, i10, iMin);
        defaultExtractorInput.peekBufferPosition += iMin;
        return iMin;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int i10, int i11, boolean z) throws IOException {
        if (!advancePeekPosition(i11, z)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - i11, bArr, i10, i11);
        return true;
    }

    @Override // androidx.media3.extractor.ExtractorInput, androidx.media3.common.DataReader
    public int read(byte[] bArr, int i10, int i11) throws IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i10, i11);
        if (fromPeekBuffer == 0) {
            fromPeekBuffer = readFromUpstream(bArr, i10, i11, 0, true);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int i10, int i11, boolean z) throws IOException {
        int fromPeekBuffer = readFromPeekBuffer(bArr, i10, i11);
        while (fromPeekBuffer < i11 && fromPeekBuffer != -1) {
            fromPeekBuffer = readFromUpstream(bArr, i10, i11, fromPeekBuffer, z);
        }
        commitBytesRead(fromPeekBuffer);
        return fromPeekBuffer != -1;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Throwable */
    @Override // androidx.media3.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j10, E e5) throws Throwable {
        ac.b.j(j10 >= 0);
        this.position = j10;
        throw e5;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int skip(int i10) throws IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i10);
        if (iSkipFromPeekBuffer == 0) {
            byte[] bArr = this.scratchSpace;
            iSkipFromPeekBuffer = readFromUpstream(bArr, 0, Math.min(i10, bArr.length), 0, true);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean skipFully(int i10, boolean z) throws IOException {
        int iSkipFromPeekBuffer = skipFromPeekBuffer(i10);
        while (iSkipFromPeekBuffer < i10 && iSkipFromPeekBuffer != -1) {
            iSkipFromPeekBuffer = readFromUpstream(this.scratchSpace, -iSkipFromPeekBuffer, Math.min(i10, this.scratchSpace.length + iSkipFromPeekBuffer), iSkipFromPeekBuffer, z);
        }
        commitBytesRead(iSkipFromPeekBuffer);
        return iSkipFromPeekBuffer != -1;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int i10, int i11) throws IOException {
        peekFully(bArr, i10, i11, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void readFully(byte[] bArr, int i10, int i11) throws IOException {
        readFully(bArr, i10, i11, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void skipFully(int i10) throws IOException {
        skipFully(i10, false);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void advancePeekPosition(int i10) throws IOException {
        advancePeekPosition(i10, false);
    }
}
