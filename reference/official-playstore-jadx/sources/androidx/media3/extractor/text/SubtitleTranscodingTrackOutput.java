package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.e;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class SubtitleTranscodingTrackOutput implements TrackOutput {
    private static final String TAG = "SubtitleTranscodingTO";
    private Format currentFormat;
    private SubtitleParser currentSubtitleParser;
    private final TrackOutput delegate;
    private boolean shouldSuppressParsingErrors;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final CueEncoder cueEncoder = new CueEncoder();
    private int sampleDataStart = 0;
    private int sampleDataEnd = 0;
    private byte[] sampleData = Util.EMPTY_BYTE_ARRAY;
    private final ParsableByteArray parsableScratch = new ParsableByteArray();

    public SubtitleTranscodingTrackOutput(TrackOutput trackOutput, SubtitleParser.Factory factory) {
        this.delegate = trackOutput;
        this.subtitleParserFactory = factory;
    }

    private void ensureSampleDataCapacity(int i10) {
        int length = this.sampleData.length;
        int i11 = this.sampleDataEnd;
        if (length - i11 >= i10) {
            return;
        }
        int i12 = i11 - this.sampleDataStart;
        int iMax = Math.max(i12 * 2, i10 + i12);
        byte[] bArr = this.sampleData;
        byte[] bArr2 = iMax <= bArr.length ? bArr : new byte[iMax];
        System.arraycopy(bArr, this.sampleDataStart, bArr2, 0, i12);
        this.sampleDataStart = 0;
        this.sampleDataEnd = i12;
        this.sampleData = bArr2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: outputSample, reason: merged with bridge method [inline-methods] */
    public void lambda$sampleMetadata$0(CuesWithTiming cuesWithTiming, long j10, int i10) {
        this.currentFormat.getClass();
        byte[] bArrEncode = this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs);
        this.parsableScratch.reset(bArrEncode);
        this.delegate.sampleData(this.parsableScratch, bArrEncode.length);
        long j11 = cuesWithTiming.startTimeUs;
        if (j11 == C.TIME_UNSET) {
            ac.b.s(this.currentFormat.subsampleOffsetUs == Long.MAX_VALUE);
        } else {
            long j12 = this.currentFormat.subsampleOffsetUs;
            j10 = j12 == Long.MAX_VALUE ? j10 + j11 : j11 + j12;
        }
        this.delegate.sampleMetadata(j10, i10 | 1, bArrEncode.length, 0, null);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final /* synthetic */ void durationUs(long j10) {
        e.a(this, j10);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void format(Format format) {
        format.sampleMimeType.getClass();
        ac.b.j(MimeTypes.getTrackType(format.sampleMimeType) == 3);
        if (!format.equals(this.currentFormat)) {
            this.currentFormat = format;
            this.currentSubtitleParser = this.subtitleParserFactory.supportsFormat(format) ? this.subtitleParserFactory.create(format) : null;
        }
        if (this.currentSubtitleParser == null) {
            this.delegate.format(format);
        } else {
            this.delegate.format(format.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCodecs(format.sampleMimeType).setSubsampleOffsetUs(Long.MAX_VALUE).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(format)).build());
        }
    }

    public void resetSubtitleParser() {
        SubtitleParser subtitleParser = this.currentSubtitleParser;
        if (subtitleParser != null) {
            subtitleParser.reset();
        }
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final /* synthetic */ int sampleData(DataReader dataReader, int i10, boolean z) {
        return e.b(this, dataReader, i10, z);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleMetadata(final long j10, final int i10, int i11, int i12, TrackOutput.CryptoData cryptoData) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleMetadata(j10, i10, i11, i12, cryptoData);
            return;
        }
        ac.b.k(cryptoData == null, "DRM on subtitles is not supported");
        int i13 = (this.sampleDataEnd - i12) - i11;
        try {
            this.currentSubtitleParser.parse(this.sampleData, i13, i11, SubtitleParser.OutputOptions.allCues(), new Consumer() { // from class: androidx.media3.extractor.text.c
                @Override // androidx.media3.common.util.Consumer
                public final void accept(Object obj) {
                    this.f3901i.lambda$sampleMetadata$0(j10, i10, (CuesWithTiming) obj);
                }
            });
        } catch (RuntimeException e5) {
            if (!this.shouldSuppressParsingErrors) {
                throw e5;
            }
            Log.w(TAG, "Parsing subtitles failed, ignoring sample.", e5);
        }
        int i14 = i13 + i11;
        this.sampleDataStart = i14;
        if (i14 == this.sampleDataEnd) {
            this.sampleDataStart = 0;
            this.sampleDataEnd = 0;
        }
    }

    public void shouldSuppressParsingErrors(boolean z) {
        this.shouldSuppressParsingErrors = z;
    }

    @Override // androidx.media3.extractor.TrackOutput
    public final /* synthetic */ void sampleData(ParsableByteArray parsableByteArray, int i10) {
        e.c(this, parsableByteArray, i10);
    }

    @Override // androidx.media3.extractor.TrackOutput
    public int sampleData(DataReader dataReader, int i10, boolean z, int i11) throws IOException {
        if (this.currentSubtitleParser == null) {
            return this.delegate.sampleData(dataReader, i10, z, i11);
        }
        ensureSampleDataCapacity(i10);
        int i12 = dataReader.read(this.sampleData, this.sampleDataEnd, i10);
        if (i12 != -1) {
            this.sampleDataEnd += i12;
            return i12;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // androidx.media3.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i10, int i11) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleData(parsableByteArray, i10, i11);
            return;
        }
        ensureSampleDataCapacity(i10);
        parsableByteArray.readBytes(this.sampleData, this.sampleDataEnd, i10);
        this.sampleDataEnd += i10;
    }
}
