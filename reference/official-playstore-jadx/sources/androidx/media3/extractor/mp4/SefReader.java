package androidx.media3.extractor.mp4;

import androidx.media3.common.Metadata;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import com.google.common.base.b0;
import io.ktor.util.date.GMTDateParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class SefReader {
    private static final int LENGTH_OF_ONE_SDR = 12;
    private static final int SAMSUNG_TAIL_SIGNATURE = 1397048916;
    private static final int STATE_CHECKING_FOR_SEF = 1;
    private static final int STATE_READING_SDRS = 2;
    private static final int STATE_READING_SEF_DATA = 3;
    private static final int STATE_SHOULD_CHECK_FOR_SEF = 0;
    private static final String TAG = "SefReader";
    private static final int TAIL_FOOTER_LENGTH = 8;
    private static final int TAIL_HEADER_LENGTH = 12;
    private static final int TYPE_SLOW_MOTION_DATA = 2192;
    private static final int TYPE_SUPER_SLOW_DEFLICKERING_ON = 2820;
    private static final int TYPE_SUPER_SLOW_MOTION_BGM = 2817;
    private static final int TYPE_SUPER_SLOW_MOTION_DATA = 2816;
    private static final int TYPE_SUPER_SLOW_MOTION_EDIT_DATA = 2819;
    private final List<DataReference> dataReferences = new ArrayList();
    private int readerState = 0;
    private int tailLength;
    private static final b0 COLON_SPLITTER = b0.a(':');
    private static final b0 ASTERISK_SPLITTER = b0.a(GMTDateParser.ANY);

    public static final class DataReference {
        public final int dataType;
        public final int size;
        public final long startOffset;

        public DataReference(int i10, long j10, int i11) {
            this.dataType = i10;
            this.startOffset = j10;
            this.size = i11;
        }
    }

    private void checkForSefData(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(8);
        extractorInput.readFully(parsableByteArray.getData(), 0, 8);
        this.tailLength = parsableByteArray.readLittleEndianInt() + 8;
        if (parsableByteArray.readInt() != SAMSUNG_TAIL_SIGNATURE) {
            positionHolder.position = 0L;
        } else {
            positionHolder.position = extractorInput.getPosition() - ((long) (this.tailLength - 12));
            this.readerState = 2;
        }
    }

    private static int nameToDataType(String str) throws ParserException {
        str.getClass();
        switch (str) {
            case "SlowMotion_Data":
                return TYPE_SLOW_MOTION_DATA;
            case "Super_SlowMotion_Edit_Data":
                return TYPE_SUPER_SLOW_MOTION_EDIT_DATA;
            case "Super_SlowMotion_Data":
                return TYPE_SUPER_SLOW_MOTION_DATA;
            case "Super_SlowMotion_Deflickering_On":
                return TYPE_SUPER_SLOW_DEFLICKERING_ON;
            case "Super_SlowMotion_BGM":
                return TYPE_SUPER_SLOW_MOTION_BGM;
            default:
                throw ParserException.createForMalformedContainer("Invalid SEF name", null);
        }
    }

    private void readSdrs(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        long length = extractorInput.getLength();
        int i10 = this.tailLength - 20;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i10);
        extractorInput.readFully(parsableByteArray.getData(), 0, i10);
        for (int i11 = 0; i11 < i10 / 12; i11++) {
            parsableByteArray.skipBytes(2);
            short littleEndianShort = parsableByteArray.readLittleEndianShort();
            if (littleEndianShort == TYPE_SLOW_MOTION_DATA || littleEndianShort == TYPE_SUPER_SLOW_MOTION_DATA || littleEndianShort == TYPE_SUPER_SLOW_MOTION_BGM || littleEndianShort == TYPE_SUPER_SLOW_MOTION_EDIT_DATA || littleEndianShort == TYPE_SUPER_SLOW_DEFLICKERING_ON) {
                this.dataReferences.add(new DataReference(littleEndianShort, (length - ((long) this.tailLength)) - ((long) parsableByteArray.readLittleEndianInt()), parsableByteArray.readLittleEndianInt()));
            } else {
                parsableByteArray.skipBytes(8);
            }
        }
        if (this.dataReferences.isEmpty()) {
            positionHolder.position = 0L;
        } else {
            this.readerState = 3;
            positionHolder.position = this.dataReferences.get(0).startOffset;
        }
    }

    private void readSefData(ExtractorInput extractorInput, List<Metadata.Entry> list) throws IOException {
        long position = extractorInput.getPosition();
        int length = (int) ((extractorInput.getLength() - extractorInput.getPosition()) - ((long) this.tailLength));
        ParsableByteArray parsableByteArray = new ParsableByteArray(length);
        extractorInput.readFully(parsableByteArray.getData(), 0, length);
        for (int i10 = 0; i10 < this.dataReferences.size(); i10++) {
            DataReference dataReference = this.dataReferences.get(i10);
            parsableByteArray.setPosition((int) (dataReference.startOffset - position));
            parsableByteArray.skipBytes(4);
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int iNameToDataType = nameToDataType(parsableByteArray.readString(littleEndianInt));
            int i11 = dataReference.size - (littleEndianInt + 8);
            if (iNameToDataType == TYPE_SLOW_MOTION_DATA) {
                list.add(readSlowMotionData(parsableByteArray, i11));
            } else if (iNameToDataType != TYPE_SUPER_SLOW_MOTION_DATA && iNameToDataType != TYPE_SUPER_SLOW_MOTION_BGM && iNameToDataType != TYPE_SUPER_SLOW_MOTION_EDIT_DATA && iNameToDataType != TYPE_SUPER_SLOW_DEFLICKERING_ON) {
                throw new IllegalStateException();
            }
        }
    }

    private static SlowMotionData readSlowMotionData(ParsableByteArray parsableByteArray, int i10) throws ParserException {
        ArrayList arrayList = new ArrayList();
        List listC = ASTERISK_SPLITTER.c(parsableByteArray.readString(i10));
        for (int i11 = 0; i11 < listC.size(); i11++) {
            List listC2 = COLON_SPLITTER.c((CharSequence) listC.get(i11));
            if (listC2.size() != 3) {
                throw ParserException.createForMalformedContainer(null, null);
            }
            try {
                arrayList.add(new SlowMotionData.Segment(Long.parseLong((String) listC2.get(0)), Long.parseLong((String) listC2.get(1)), 1 << (Integer.parseInt((String) listC2.get(2)) - 1)));
            } catch (NumberFormatException e5) {
                throw ParserException.createForMalformedContainer(null, e5);
            }
        }
        return new SlowMotionData(arrayList);
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder, List<Metadata.Entry> list) throws IOException {
        int i10 = this.readerState;
        long j10 = 0;
        if (i10 == 0) {
            long length = extractorInput.getLength();
            if (length != -1 && length >= 8) {
                j10 = length - 8;
            }
            positionHolder.position = j10;
            this.readerState = 1;
        } else if (i10 == 1) {
            checkForSefData(extractorInput, positionHolder);
        } else if (i10 == 2) {
            readSdrs(extractorInput, positionHolder);
        } else {
            if (i10 != 3) {
                throw new IllegalStateException();
            }
            readSefData(extractorInput, list);
            positionHolder.position = 0L;
        }
        return 1;
    }

    public void reset() {
        this.dataReferences.clear();
        this.readerState = 0;
    }
}
