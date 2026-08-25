package androidx.media3.extractor.ts;

import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class PesReader implements TsPayloadReader {
    private static final int HEADER_SIZE = 9;
    private static final int MAX_HEADER_EXTENSION_SIZE = 10;
    private static final int PES_SCRATCH_SIZE = 10;
    private static final int STATE_FINDING_HEADER = 0;
    private static final int STATE_READING_BODY = 3;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_HEADER_EXTENSION = 2;
    private static final String TAG = "PesReader";
    private int bytesRead;
    private boolean dataAlignmentIndicator;
    private boolean dtsFlag;
    private int extendedHeaderLength;
    private int payloadSize;
    private boolean ptsFlag;
    private final ElementaryStreamReader reader;
    private boolean seenFirstDts;
    private long timeUs;
    private TimestampAdjuster timestampAdjuster;
    private final ParsableBitArray pesScratch = new ParsableBitArray(new byte[10]);
    private int state = 0;

    public PesReader(ElementaryStreamReader elementaryStreamReader) {
        this.reader = elementaryStreamReader;
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i10) {
        int iMin = Math.min(parsableByteArray.bytesLeft(), i10 - this.bytesRead);
        if (iMin <= 0) {
            return true;
        }
        if (bArr == null) {
            parsableByteArray.skipBytes(iMin);
        } else {
            parsableByteArray.readBytes(bArr, this.bytesRead, iMin);
        }
        int i11 = this.bytesRead + iMin;
        this.bytesRead = i11;
        return i11 == i10;
    }

    private boolean parseHeader() {
        this.pesScratch.setPosition(0);
        int bits = this.pesScratch.readBits(24);
        if (bits != 1) {
            a2.s(bits, "Unexpected start code prefix: ", TAG);
            this.payloadSize = -1;
            return false;
        }
        this.pesScratch.skipBits(8);
        int bits2 = this.pesScratch.readBits(16);
        this.pesScratch.skipBits(5);
        this.dataAlignmentIndicator = this.pesScratch.readBit();
        this.pesScratch.skipBits(2);
        this.ptsFlag = this.pesScratch.readBit();
        this.dtsFlag = this.pesScratch.readBit();
        this.pesScratch.skipBits(6);
        int bits3 = this.pesScratch.readBits(8);
        this.extendedHeaderLength = bits3;
        if (bits2 == 0) {
            this.payloadSize = -1;
        } else {
            int i10 = (bits2 - 3) - bits3;
            this.payloadSize = i10;
            if (i10 < 0) {
                Log.w(TAG, "Found negative packet payload size: " + this.payloadSize);
                this.payloadSize = -1;
            }
        }
        return true;
    }

    @RequiresNonNull({"timestampAdjuster"})
    private void parseHeaderExtension() {
        this.pesScratch.setPosition(0);
        this.timeUs = C.TIME_UNSET;
        if (this.ptsFlag) {
            this.pesScratch.skipBits(4);
            long bits = ((long) this.pesScratch.readBits(3)) << 30;
            this.pesScratch.skipBits(1);
            long bits2 = bits | ((long) (this.pesScratch.readBits(15) << 15));
            this.pesScratch.skipBits(1);
            long bits3 = bits2 | ((long) this.pesScratch.readBits(15));
            this.pesScratch.skipBits(1);
            if (!this.seenFirstDts && this.dtsFlag) {
                this.pesScratch.skipBits(4);
                long bits4 = ((long) this.pesScratch.readBits(3)) << 30;
                this.pesScratch.skipBits(1);
                long bits5 = bits4 | ((long) (this.pesScratch.readBits(15) << 15));
                this.pesScratch.skipBits(1);
                long bits6 = bits5 | ((long) this.pesScratch.readBits(15));
                this.pesScratch.skipBits(1);
                this.timestampAdjuster.adjustTsTimestamp(bits6);
                this.seenFirstDts = true;
            }
            this.timeUs = this.timestampAdjuster.adjustTsTimestamp(bits3);
        }
    }

    private void setState(int i10) {
        this.state = i10;
        this.bytesRead = 0;
    }

    public boolean canConsumeSynthesizedEmptyPusi(boolean z) {
        return this.state == 3 && this.payloadSize == -1 && !(z && (this.reader instanceof H262Reader)) && (!z || parseHeader());
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void consume(ParsableByteArray parsableByteArray, int i10) throws ParserException {
        this.timestampAdjuster.getClass();
        if ((i10 & 1) != 0) {
            int i11 = this.state;
            if (i11 != 0 && i11 != 1) {
                if (i11 == 2) {
                    Log.w(TAG, "Unexpected start indicator reading extended header");
                } else {
                    if (i11 != 3) {
                        throw new IllegalStateException();
                    }
                    if (this.payloadSize != -1) {
                        Log.w(TAG, "Unexpected start indicator: expected " + this.payloadSize + " more bytes");
                    }
                    this.reader.packetFinished(parsableByteArray.limit() == 0);
                }
            }
            setState(1);
        }
        while (parsableByteArray.bytesLeft() > 0) {
            int i12 = this.state;
            if (i12 == 0) {
                parsableByteArray.skipBytes(parsableByteArray.bytesLeft());
            } else if (i12 != 1) {
                if (i12 == 2) {
                    if (continueRead(parsableByteArray, this.pesScratch.data, Math.min(10, this.extendedHeaderLength)) && continueRead(parsableByteArray, null, this.extendedHeaderLength)) {
                        parseHeaderExtension();
                        i10 |= this.dataAlignmentIndicator ? 4 : 0;
                        this.reader.packetStarted(this.timeUs, i10);
                        setState(3);
                    }
                } else {
                    if (i12 != 3) {
                        throw new IllegalStateException();
                    }
                    int iBytesLeft = parsableByteArray.bytesLeft();
                    int i13 = this.payloadSize;
                    int i14 = i13 == -1 ? 0 : iBytesLeft - i13;
                    if (i14 > 0) {
                        iBytesLeft -= i14;
                        parsableByteArray.setLimit(parsableByteArray.getPosition() + iBytesLeft);
                    }
                    this.reader.consume(parsableByteArray);
                    int i15 = this.payloadSize;
                    if (i15 != -1) {
                        int i16 = i15 - iBytesLeft;
                        this.payloadSize = i16;
                        if (i16 == 0) {
                            this.reader.packetFinished(false);
                            setState(1);
                        }
                    }
                }
            } else if (continueRead(parsableByteArray, this.pesScratch.data, 9)) {
                setState(parseHeader() ? 2 : 0);
            }
        }
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.timestampAdjuster = timestampAdjuster;
        this.reader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // androidx.media3.extractor.ts.TsPayloadReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.seenFirstDts = false;
        this.reader.seek();
    }
}
