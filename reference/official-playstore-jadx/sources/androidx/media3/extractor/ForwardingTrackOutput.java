package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.TrackOutput;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingTrackOutput implements TrackOutput {
    private final TrackOutput trackOutput;

    public ForwardingTrackOutput(TrackOutput trackOutput) {
        this.trackOutput = trackOutput;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void durationUs(long j10) {
        this.trackOutput.durationUs(j10);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void format(Format format) {
        this.trackOutput.format(format);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i10, boolean z) throws IOException {
        return this.trackOutput.sampleData(dataReader, i10, z);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(long j10, int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
        this.trackOutput.sampleMetadata(j10, i10, i11, i12, cryptoData);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i10) {
        this.trackOutput.sampleData(parsableByteArray, i10);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i10, boolean z, int i11) throws IOException {
        return this.trackOutput.sampleData(dataReader, i10, z, i11);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i10, int i11) {
        this.trackOutput.sampleData(parsableByteArray, i10, i11);
    }
}
