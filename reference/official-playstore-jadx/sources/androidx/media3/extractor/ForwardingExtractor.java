package androidx.media3.extractor;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingExtractor implements Extractor {
    private final Extractor delegate;

    public ForwardingExtractor(Extractor extractor) {
        this.delegate = extractor;
    }

    @Override // androidx.media3.extractor.Extractor
    public List<SniffFailure> getSniffFailureDetails() {
        return this.delegate.getSniffFailureDetails();
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this.delegate.getUnderlyingImplementation();
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.delegate.init(extractorOutput);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        return this.delegate.read(extractorInput, positionHolder);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        this.delegate.release();
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        this.delegate.seek(j10, j11);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return this.delegate.sniff(extractorInput);
    }
}
