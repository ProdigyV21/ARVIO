package androidx.media3.extractor.ts;

import ac.b;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class MpegAudioReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 4;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_FRAME = 2;
    private static final int STATE_READING_HEADER = 1;
    private final String containerMimeType;
    private String formatId;
    private int frameBytesRead;
    private long frameDurationUs;
    private int frameSize;
    private boolean hasOutputFormat;
    private final MpegAudioUtil.Header header;
    private final ParsableByteArray headerScratch;
    private final String language;
    private boolean lastByteWasFF;
    private TrackOutput output;
    private final int roleFlags;
    private int state;
    private long timeUs;

    public MpegAudioReader(String str) {
        this(null, 0, str);
    }

    private void findHeader(ParsableByteArray parsableByteArray) {
        byte[] data = parsableByteArray.getData();
        int iLimit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < iLimit; position++) {
            byte b10 = data[position];
            boolean z = (b10 & 255) == 255;
            boolean z5 = this.lastByteWasFF && (b10 & 224) == 224;
            this.lastByteWasFF = z;
            if (z5) {
                parsableByteArray.setPosition(position + 1);
                this.lastByteWasFF = false;
                this.headerScratch.getData()[1] = data[position];
                this.frameBytesRead = 2;
                this.state = 1;
                return;
            }
        }
        parsableByteArray.setPosition(iLimit);
    }

    @RequiresNonNull({"output"})
    private void readFrameRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), this.frameSize - this.frameBytesRead);
        this.output.sampleData(parsableByteArray, iMin);
        int i10 = this.frameBytesRead + iMin;
        this.frameBytesRead = i10;
        if (i10 < this.frameSize) {
            return;
        }
        b.s(this.timeUs != C.TIME_UNSET);
        this.output.sampleMetadata(this.timeUs, 1, this.frameSize, 0, null);
        this.timeUs += this.frameDurationUs;
        this.frameBytesRead = 0;
        this.state = 0;
    }

    @RequiresNonNull({"output"})
    private void readHeaderRemainder(ParsableByteArray parsableByteArray) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), 4 - this.frameBytesRead);
        parsableByteArray.readBytes(this.headerScratch.getData(), this.frameBytesRead, iMin);
        int i10 = this.frameBytesRead + iMin;
        this.frameBytesRead = i10;
        if (i10 < 4) {
            return;
        }
        this.headerScratch.setPosition(0);
        if (!this.header.setForHeaderData(this.headerScratch.readInt())) {
            this.frameBytesRead = 0;
            this.state = 1;
            return;
        }
        MpegAudioUtil.Header header = this.header;
        this.frameSize = header.frameSize;
        if (!this.hasOutputFormat) {
            this.frameDurationUs = (((long) header.samplesPerFrame) * 1000000) / ((long) header.sampleRate);
            this.output.format(new Format.Builder().setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(this.header.mimeType).setMaxInputSize(4096).setChannelCount(this.header.channels).setSampleRate(this.header.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build());
            this.hasOutputFormat = true;
        }
        this.headerScratch.setPosition(0);
        this.output.sampleData(this.headerScratch, 4);
        this.state = 2;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        this.output.getClass();
        while (parsableByteArray.bytesLeft() > 0) {
            int i10 = this.state;
            if (i10 == 0) {
                findHeader(parsableByteArray);
            } else if (i10 == 1) {
                readHeaderRemainder(parsableByteArray);
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException();
                }
                readFrameRemainder(parsableByteArray);
            }
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean z) {
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j10, int i10) {
        this.timeUs = j10;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.frameBytesRead = 0;
        this.lastByteWasFF = false;
        this.timeUs = C.TIME_UNSET;
    }

    public MpegAudioReader(String str, int i10, String str2) {
        this.state = 0;
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        this.headerScratch = parsableByteArray;
        parsableByteArray.getData()[0] = -1;
        this.header = new MpegAudioUtil.Header();
        this.timeUs = C.TIME_UNSET;
        this.language = str;
        this.roleFlags = i10;
        this.containerMimeType = str2;
    }
}
