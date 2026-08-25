package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface ExtractorInput extends DataReader {
    void advancePeekPosition(int i10) throws IOException;

    boolean advancePeekPosition(int i10, boolean z) throws IOException;

    long getLength();

    long getPeekPosition();

    long getPosition();

    int peek(byte[] bArr, int i10, int i11) throws IOException;

    void peekFully(byte[] bArr, int i10, int i11) throws IOException;

    boolean peekFully(byte[] bArr, int i10, int i11, boolean z) throws IOException;

    @Override // androidx.media3.common.DataReader
    int read(byte[] bArr, int i10, int i11) throws IOException;

    void readFully(byte[] bArr, int i10, int i11) throws IOException;

    boolean readFully(byte[] bArr, int i10, int i11, boolean z) throws IOException;

    void resetPeekPosition();

    <E extends Throwable> void setRetryPosition(long j10, E e5) throws Throwable;

    int skip(int i10) throws IOException;

    void skipFully(int i10) throws IOException;

    boolean skipFully(int i10, boolean z) throws IOException;
}
