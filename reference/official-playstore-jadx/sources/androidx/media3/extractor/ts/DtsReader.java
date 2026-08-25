package androidx.media3.extractor.ts;

import ac.b;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import j$.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import m2.f0;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class DtsReader implements ElementaryStreamReader {
    private static final int CORE_HEADER_SIZE = 18;
    static final int EXTSS_HEADER_SIZE_MAX = 4096;
    static final int FTOC_MAX_HEADER_SIZE = 5408;
    private static final int STATE_FINDING_EXTSS_HEADER_SIZE = 2;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_FINDING_UHD_HEADER_SIZE = 4;
    private static final int STATE_READING_CORE_HEADER = 1;
    private static final int STATE_READING_EXTSS_HEADER = 3;
    private static final int STATE_READING_SAMPLE = 6;
    private static final int STATE_READING_UHD_HEADER = 5;
    private int bytesRead;
    private final String containerMimeType;
    private Format format;
    private String formatId;
    private int frameType;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int syncBytes;
    private int state = 0;
    private long timeUs = C.TIME_UNSET;
    private final AtomicInteger uhdAudioChunkId = new AtomicInteger();
    private int extensionSubstreamHeaderSize = -1;
    private int uhdHeaderSize = -1;

    public DtsReader(String str, int i10, int i11, String str2) {
        this.headerScratchBytes = new ParsableByteArray(new byte[i11]);
        this.language = str;
        this.roleFlags = i10;
        this.containerMimeType = str2;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i10) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i10 - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        int i11 = this.bytesRead + iMin;
        this.bytesRead = i11;
        return i11 == i10;
    }

    @RequiresNonNull({"output"})
    private void parseCoreHeader() {
        byte[] data = this.headerScratchBytes.getData();
        if (this.format == null) {
            Format dtsFormat = DtsUtil.parseDtsFormat(data, this.formatId, this.language, this.roleFlags, this.containerMimeType, null);
            this.format = dtsFormat;
            this.output.format(dtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(data);
        this.sampleDurationUs = f0.f(Util.sampleCountToDurationUs(DtsUtil.parseDtsAudioSampleCount(data), this.format.sampleRate));
    }

    @RequiresNonNull({"output"})
    private void parseExtensionSubstreamHeader() throws ParserException {
        DtsUtil.DtsHeader dtsHdHeader = DtsUtil.parseDtsHdHeader(this.headerScratchBytes.getData());
        updateFormatWithDtsHeaderInfo(dtsHdHeader);
        this.sampleSize = dtsHdHeader.frameSize;
        long j10 = dtsHdHeader.frameDurationUs;
        if (j10 == C.TIME_UNSET) {
            j10 = 0;
        }
        this.sampleDurationUs = j10;
    }

    @RequiresNonNull({"output"})
    private void parseUhdHeader() throws ParserException {
        DtsUtil.DtsHeader dtsUhdHeader = DtsUtil.parseDtsUhdHeader(this.headerScratchBytes.getData(), this.uhdAudioChunkId);
        if (this.frameType == 3) {
            updateFormatWithDtsHeaderInfo(dtsUhdHeader);
        }
        this.sampleSize = dtsUhdHeader.frameSize;
        long j10 = dtsUhdHeader.frameDurationUs;
        if (j10 == C.TIME_UNSET) {
            j10 = 0;
        }
        this.sampleDurationUs = j10;
    }

    private boolean skipToNextSyncWord(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i10 = this.syncBytes << 8;
            this.syncBytes = i10;
            int unsignedByte = i10 | parsableByteArray.readUnsignedByte();
            this.syncBytes = unsignedByte;
            int frameType = DtsUtil.getFrameType(unsignedByte);
            this.frameType = frameType;
            if (frameType != 0) {
                byte[] data = this.headerScratchBytes.getData();
                int i11 = this.syncBytes;
                data[0] = (byte) ((i11 >> 24) & 255);
                data[1] = (byte) ((i11 >> 16) & 255);
                data[2] = (byte) ((i11 >> 8) & 255);
                data[3] = (byte) (i11 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @RequiresNonNull({"output"})
    private void updateFormatWithDtsHeaderInfo(DtsUtil.DtsHeader dtsHeader) {
        int i10;
        int i11 = dtsHeader.sampleRate;
        if (i11 == -2147483647 || (i10 = dtsHeader.channelCount) == -1) {
            return;
        }
        Format format = this.format;
        if (format != null && i10 == format.channelCount && i11 == format.sampleRate && Objects.equals(dtsHeader.mimeType, format.sampleMimeType)) {
            return;
        }
        Format format2 = this.format;
        Format formatBuild = (format2 == null ? new Format.Builder() : format2.buildUpon()).setId(this.formatId).setContainerMimeType(this.containerMimeType).setSampleMimeType(dtsHeader.mimeType).setChannelCount(dtsHeader.channelCount).setSampleRate(dtsHeader.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
        this.format = formatBuild;
        this.output.format(formatBuild);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        this.output.getClass();
        while (parsableByteArray.bytesLeft() > 0) {
            switch (this.state) {
                case 0:
                    if (skipToNextSyncWord(parsableByteArray)) {
                        int i10 = this.frameType;
                        if (i10 == 3 || i10 == 4) {
                            this.state = 4;
                        } else if (i10 != 1) {
                            this.state = 2;
                        } else {
                            this.state = 1;
                        }
                    }
                    break;
                case 1:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 18)) {
                        parseCoreHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, 18);
                        this.state = 6;
                    }
                    break;
                case 2:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 7)) {
                        this.extensionSubstreamHeaderSize = DtsUtil.parseDtsHdHeaderSize(this.headerScratchBytes.getData());
                        this.state = 3;
                    }
                    break;
                case 3:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.extensionSubstreamHeaderSize)) {
                        parseExtensionSubstreamHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.extensionSubstreamHeaderSize);
                        this.state = 6;
                    }
                    break;
                case 4:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), 6)) {
                        int dtsUhdHeaderSize = DtsUtil.parseDtsUhdHeaderSize(this.headerScratchBytes.getData());
                        this.uhdHeaderSize = dtsUhdHeaderSize;
                        int i11 = this.bytesRead;
                        if (i11 > dtsUhdHeaderSize) {
                            int i12 = i11 - dtsUhdHeaderSize;
                            this.bytesRead = i11 - i12;
                            parsableByteArray.setPosition(parsableByteArray.getPosition() - i12);
                        }
                        this.state = 5;
                    }
                    break;
                case 5:
                    if (continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.uhdHeaderSize)) {
                        parseUhdHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.uhdHeaderSize);
                        this.state = 6;
                    }
                    break;
                case 6:
                    int iMin = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    this.output.sampleData(parsableByteArray, iMin);
                    int i13 = this.bytesRead + iMin;
                    this.bytesRead = i13;
                    if (i13 == this.sampleSize) {
                        b.s(this.timeUs != C.TIME_UNSET);
                        this.output.sampleMetadata(this.timeUs, this.frameType == 4 ? 0 : 1, this.sampleSize, 0, null);
                        this.timeUs += this.sampleDurationUs;
                        this.state = 0;
                    }
                    break;
                default:
                    throw new IllegalStateException();
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
        this.bytesRead = 0;
        this.syncBytes = 0;
        this.timeUs = C.TIME_UNSET;
        this.uhdAudioChunkId.set(0);
    }
}
