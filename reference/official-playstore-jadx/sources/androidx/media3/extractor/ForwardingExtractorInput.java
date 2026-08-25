package androidx.media3.extractor;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput input;

    public ForwardingExtractorInput(ExtractorInput extractorInput) {
        this.input = extractorInput;
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean advancePeekPosition(int i10, boolean z) throws IOException {
        return this.input.advancePeekPosition(i10, z);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getLength() {
        return this.input.getLength();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPeekPosition() {
        return this.input.getPeekPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public long getPosition() {
        return this.input.getPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int peek(byte[] bArr, int i10, int i11) throws IOException {
        return this.input.peek(bArr, i10, i11);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean peekFully(byte[] bArr, int i10, int i11, boolean z) throws IOException {
        return this.input.peekFully(bArr, i10, i11, z);
    }

    @Override // androidx.media3.extractor.ExtractorInput, androidx.media3.common.DataReader
    public int read(byte[] bArr, int i10, int i11) throws IOException {
        return this.input.read(bArr, i10, i11);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean readFully(byte[] bArr, int i10, int i11, boolean z) throws IOException {
        return this.input.readFully(bArr, i10, i11, z);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void resetPeekPosition() {
        this.input.resetPeekPosition();
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j10, E e5) throws Throwable {
        this.input.setRetryPosition(j10, e5);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public int skip(int i10) throws IOException {
        return this.input.skip(i10);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public boolean skipFully(int i10, boolean z) throws IOException {
        return this.input.skipFully(i10, z);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void advancePeekPosition(int i10) throws IOException {
        this.input.advancePeekPosition(i10);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void peekFully(byte[] bArr, int i10, int i11) throws IOException {
        this.input.peekFully(bArr, i10, i11);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void readFully(byte[] bArr, int i10, int i11) throws IOException {
        this.input.readFully(bArr, i10, i11);
    }

    @Override // androidx.media3.extractor.ExtractorInput
    public void skipFully(int i10) throws IOException {
        this.input.skipFully(i10);
    }
}
