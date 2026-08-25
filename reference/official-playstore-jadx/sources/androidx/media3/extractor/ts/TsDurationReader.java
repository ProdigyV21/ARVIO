package androidx.media3.extractor.ts;

import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class TsDurationReader {
    private static final String TAG = "TsDurationReader";
    private boolean isDurationRead;
    private boolean isFirstPcrValueRead;
    private boolean isLastPcrValueRead;
    private final int timestampSearchBytes;
    private final TimestampAdjuster pcrTimestampAdjuster = new TimestampAdjuster(0);
    private long firstPcrValue = C.TIME_UNSET;
    private long lastPcrValue = C.TIME_UNSET;
    private long durationUs = C.TIME_UNSET;
    private final ParsableByteArray packetBuffer = new ParsableByteArray();

    public TsDurationReader(int i10) {
        this.timestampSearchBytes = i10;
    }

    private int finishReadDuration(ExtractorInput extractorInput) {
        this.packetBuffer.reset(Util.EMPTY_BYTE_ARRAY);
        this.isDurationRead = true;
        extractorInput.resetPeekPosition();
        return 0;
    }

    private int readFirstPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i10) throws IOException {
        int iMin = (int) Math.min(this.timestampSearchBytes, extractorInput.getLength());
        long j10 = 0;
        if (extractorInput.getPosition() != j10) {
            positionHolder.position = j10;
            return 1;
        }
        this.packetBuffer.reset(iMin);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, iMin);
        this.firstPcrValue = readFirstPcrValueFromBuffer(this.packetBuffer, i10);
        this.isFirstPcrValueRead = true;
        return 0;
    }

    private long readFirstPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i10) {
        int iLimit = parsableByteArray.limit();
        for (int position = parsableByteArray.getPosition(); position < iLimit; position++) {
            if (parsableByteArray.getData()[position] == 71) {
                long pcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, position, i10);
                if (pcrFromPacket != C.TIME_UNSET) {
                    return pcrFromPacket;
                }
            }
        }
        return C.TIME_UNSET;
    }

    private int readLastPcrValue(ExtractorInput extractorInput, PositionHolder positionHolder, int i10) throws IOException {
        long length = extractorInput.getLength();
        int iMin = (int) Math.min(this.timestampSearchBytes, length);
        long j10 = length - ((long) iMin);
        if (extractorInput.getPosition() != j10) {
            positionHolder.position = j10;
            return 1;
        }
        this.packetBuffer.reset(iMin);
        extractorInput.resetPeekPosition();
        extractorInput.peekFully(this.packetBuffer.getData(), 0, iMin);
        this.lastPcrValue = readLastPcrValueFromBuffer(this.packetBuffer, i10);
        this.isLastPcrValueRead = true;
        return 0;
    }

    private long readLastPcrValueFromBuffer(ParsableByteArray parsableByteArray, int i10) {
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        for (int i11 = iLimit - 188; i11 >= position; i11--) {
            if (TsUtil.isStartOfTsPacket(parsableByteArray.getData(), position, iLimit, i11)) {
                long pcrFromPacket = TsUtil.readPcrFromPacket(parsableByteArray, i11, i10);
                if (pcrFromPacket != C.TIME_UNSET) {
                    return pcrFromPacket;
                }
            }
        }
        return C.TIME_UNSET;
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public TimestampAdjuster getPcrTimestampAdjuster() {
        return this.pcrTimestampAdjuster;
    }

    public boolean isDurationReadFinished() {
        return this.isDurationRead;
    }

    public int readDuration(ExtractorInput extractorInput, PositionHolder positionHolder, int i10) throws IOException {
        if (i10 <= 0) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isLastPcrValueRead) {
            return readLastPcrValue(extractorInput, positionHolder, i10);
        }
        if (this.lastPcrValue == C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        if (!this.isFirstPcrValueRead) {
            return readFirstPcrValue(extractorInput, positionHolder, i10);
        }
        long j10 = this.firstPcrValue;
        if (j10 == C.TIME_UNSET) {
            return finishReadDuration(extractorInput);
        }
        this.durationUs = this.pcrTimestampAdjuster.adjustTsTimestampGreaterThanPreviousTimestamp(this.lastPcrValue) - this.pcrTimestampAdjuster.adjustTsTimestamp(j10);
        return finishReadDuration(extractorInput);
    }
}
