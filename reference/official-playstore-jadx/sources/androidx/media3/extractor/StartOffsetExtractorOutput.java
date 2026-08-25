package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* JADX INFO: loaded from: classes3.dex */
public final class StartOffsetExtractorOutput implements ExtractorOutput {
    private final ExtractorOutput extractorOutput;
    private final long startOffset;

    public StartOffsetExtractorOutput(long j10, ExtractorOutput extractorOutput) {
        this.startOffset = j10;
        this.extractorOutput = extractorOutput;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.extractorOutput.endTracks();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(final SeekMap seekMap) {
        this.extractorOutput.seekMap(new ForwardingSeekMap(seekMap) { // from class: androidx.media3.extractor.StartOffsetExtractorOutput.1
            @Override // androidx.media3.extractor.ForwardingSeekMap, androidx.media3.extractor.SeekMap
            public SeekMap.SeekPoints getSeekPoints(long j10) {
                SeekMap.SeekPoints seekPoints = seekMap.getSeekPoints(j10);
                SeekPoint seekPoint = seekPoints.first;
                SeekPoint seekPoint2 = new SeekPoint(seekPoint.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint.position);
                SeekPoint seekPoint3 = seekPoints.second;
                return new SeekMap.SeekPoints(seekPoint2, new SeekPoint(seekPoint3.timeUs, StartOffsetExtractorOutput.this.startOffset + seekPoint3.position));
            }
        });
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int i10, int i11) {
        return this.extractorOutput.track(i10, i11);
    }
}
