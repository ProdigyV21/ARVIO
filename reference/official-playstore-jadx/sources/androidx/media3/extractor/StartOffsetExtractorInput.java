package androidx.media3.extractor;

/* JADX INFO: loaded from: classes3.dex */
public final class StartOffsetExtractorInput extends ForwardingExtractorInput {
    private final long startOffset;

    public StartOffsetExtractorInput(ExtractorInput extractorInput, long j10) {
        super(extractorInput);
        ac.b.j(extractorInput.getPosition() >= j10);
        this.startOffset = j10;
    }

    @Override // androidx.media3.extractor.ForwardingExtractorInput, androidx.media3.extractor.ExtractorInput
    public long getLength() {
        return super.getLength() - this.startOffset;
    }

    @Override // androidx.media3.extractor.ForwardingExtractorInput, androidx.media3.extractor.ExtractorInput
    public long getPeekPosition() {
        return super.getPeekPosition() - this.startOffset;
    }

    @Override // androidx.media3.extractor.ForwardingExtractorInput, androidx.media3.extractor.ExtractorInput
    public long getPosition() {
        return super.getPosition() - this.startOffset;
    }

    @Override // androidx.media3.extractor.ForwardingExtractorInput, androidx.media3.extractor.ExtractorInput
    public <E extends Throwable> void setRetryPosition(long j10, E e5) throws Throwable {
        super.setRetryPosition(j10 + this.startOffset, e5);
    }
}
