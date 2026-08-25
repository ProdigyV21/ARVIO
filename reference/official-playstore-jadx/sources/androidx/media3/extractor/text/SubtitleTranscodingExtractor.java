package androidx.media3.extractor.text;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class SubtitleTranscodingExtractor implements Extractor {
    private final Extractor delegate;
    private final SubtitleParser.Factory subtitleParserFactory;
    private SubtitleTranscodingExtractorOutput transcodingExtractorOutput;

    public SubtitleTranscodingExtractor(Extractor extractor, SubtitleParser.Factory factory) {
        this.delegate = extractor;
        this.subtitleParserFactory = factory;
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return androidx.media3.extractor.b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public Extractor getUnderlyingImplementation() {
        return this.delegate;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        SubtitleTranscodingExtractorOutput subtitleTranscodingExtractorOutput = new SubtitleTranscodingExtractorOutput(extractorOutput, this.subtitleParserFactory);
        this.transcodingExtractorOutput = subtitleTranscodingExtractorOutput;
        this.delegate.init(subtitleTranscodingExtractorOutput);
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
        SubtitleTranscodingExtractorOutput subtitleTranscodingExtractorOutput = this.transcodingExtractorOutput;
        if (subtitleTranscodingExtractorOutput != null) {
            subtitleTranscodingExtractorOutput.resetSubtitleParsers();
        }
        this.delegate.seek(j10, j11);
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return this.delegate.sniff(extractorInput);
    }
}
