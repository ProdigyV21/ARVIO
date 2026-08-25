package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public class SubtitleExtractor implements Extractor {
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int STATE_CREATED = 0;
    private static final int STATE_EXTRACTING = 2;
    private static final int STATE_FINISHED = 4;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_RELEASED = 5;
    private static final int STATE_SEEKING = 3;
    public static final int TRACK_ID = 0;
    private int bytesRead;
    private final Format format;
    private final List<Sample> samples;
    private long seekTimeUs;
    private int state;
    private final SubtitleParser subtitleParser;
    private long[] timestamps;
    private TrackOutput trackOutput;
    private final CueEncoder cueEncoder = new CueEncoder();
    private byte[] subtitleData = Util.EMPTY_BYTE_ARRAY;
    private final ParsableByteArray scratchSampleArray = new ParsableByteArray();

    public static class Sample implements Comparable<Sample> {
        private final byte[] data;
        private final long timeUs;

        private Sample(long j10, byte[] bArr) {
            this.timeUs = j10;
            this.data = bArr;
        }

        @Override // java.lang.Comparable
        public int compareTo(Sample sample) {
            return Long.compare(this.timeUs, sample.timeUs);
        }
    }

    public SubtitleExtractor(SubtitleParser subtitleParser, Format format) {
        this.subtitleParser = subtitleParser;
        this.format = format != null ? format.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCodecs(format.sampleMimeType).setCueReplacementBehavior(subtitleParser.getCueReplacementBehavior()).build() : null;
        this.samples = new ArrayList();
        this.state = 0;
        this.timestamps = Util.EMPTY_LONG_ARRAY;
        this.seekTimeUs = C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$parseAndWriteToOutput$0(CuesWithTiming cuesWithTiming) {
        Sample sample = new Sample(cuesWithTiming.startTimeUs, this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs));
        this.samples.add(sample);
        long j10 = this.seekTimeUs;
        if (j10 == C.TIME_UNSET || cuesWithTiming.endTimeUs >= j10) {
            writeToOutput(sample);
        }
    }

    private void parseAndWriteToOutput() throws IOException {
        try {
            long j10 = this.seekTimeUs;
            this.subtitleParser.parse(this.subtitleData, 0, this.bytesRead, j10 != C.TIME_UNSET ? SubtitleParser.OutputOptions.cuesAfterThenRemainingCuesBefore(j10) : SubtitleParser.OutputOptions.allCues(), new a3.b(this, 9));
            Collections.sort(this.samples);
            this.timestamps = new long[this.samples.size()];
            for (int i10 = 0; i10 < this.samples.size(); i10++) {
                this.timestamps[i10] = this.samples.get(i10).timeUs;
            }
            this.subtitleData = Util.EMPTY_BYTE_ARRAY;
        } catch (RuntimeException e5) {
            throw ParserException.createForMalformedContainer("SubtitleParser failed.", e5);
        }
    }

    private boolean readFromInput(ExtractorInput extractorInput) throws IOException {
        byte[] bArr = this.subtitleData;
        if (bArr.length == this.bytesRead) {
            this.subtitleData = Arrays.copyOf(bArr, bArr.length + 1024);
        }
        byte[] bArr2 = this.subtitleData;
        int i10 = this.bytesRead;
        int i11 = extractorInput.read(bArr2, i10, bArr2.length - i10);
        if (i11 != -1) {
            this.bytesRead += i11;
        }
        long length = extractorInput.getLength();
        return (length != -1 && ((long) this.bytesRead) == length) || i11 == -1;
    }

    private boolean skipInput(ExtractorInput extractorInput) throws IOException {
        return extractorInput.skip((extractorInput.getLength() > (-1L) ? 1 : (extractorInput.getLength() == (-1L) ? 0 : -1)) != 0 ? f0.f(extractorInput.getLength()) : 1024) == -1;
    }

    private void writeToOutput() {
        long j10 = this.seekTimeUs;
        for (int iBinarySearchFloor = j10 == C.TIME_UNSET ? 0 : Util.binarySearchFloor(this.timestamps, j10, true, true); iBinarySearchFloor < this.samples.size(); iBinarySearchFloor++) {
            writeToOutput(this.samples.get(iBinarySearchFloor));
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ List getSniffFailureDetails() {
        return androidx.media3.extractor.b.a(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public final /* synthetic */ Extractor getUnderlyingImplementation() {
        return androidx.media3.extractor.b.b(this);
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        ac.b.s(this.state == 0);
        TrackOutput trackOutputTrack = extractorOutput.track(0, 3);
        this.trackOutput = trackOutputTrack;
        Format format = this.format;
        if (format != null) {
            trackOutputTrack.format(format);
            extractorOutput.endTracks();
            extractorOutput.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, C.TIME_UNSET));
        }
        this.state = 1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i10 = this.state;
        ac.b.s((i10 == 0 || i10 == 5) ? false : true);
        if (this.state == 1) {
            int iF = extractorInput.getLength() != -1 ? f0.f(extractorInput.getLength()) : 1024;
            if (iF > this.subtitleData.length) {
                this.subtitleData = new byte[iF];
            }
            this.bytesRead = 0;
            this.state = 2;
        }
        if (this.state == 2 && readFromInput(extractorInput)) {
            parseAndWriteToOutput();
            this.state = 4;
        }
        if (this.state == 3 && skipInput(extractorInput)) {
            writeToOutput();
            this.state = 4;
        }
        return this.state == 4 ? -1 : 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        if (this.state == 5) {
            return;
        }
        this.subtitleParser.reset();
        this.state = 5;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long j10, long j11) {
        int i10 = this.state;
        ac.b.s((i10 == 0 || i10 == 5) ? false : true);
        this.seekTimeUs = j11;
        if (this.state == 2) {
            this.state = 1;
        }
        if (this.state == 4) {
            this.state = 3;
        }
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return true;
    }

    private void writeToOutput(Sample sample) {
        this.trackOutput.getClass();
        int length = sample.data.length;
        this.scratchSampleArray.reset(sample.data);
        this.trackOutput.sampleData(this.scratchSampleArray, length);
        this.trackOutput.sampleMetadata(sample.timeUs, 1, length, 0, null);
    }
}
