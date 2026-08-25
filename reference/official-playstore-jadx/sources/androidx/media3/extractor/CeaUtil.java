package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;

/* JADX INFO: loaded from: classes3.dex */
public final class CeaUtil {
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE_ATSC = 49;
    private static final int PROVIDER_CODE_DIRECTV = 47;
    private static final String TAG = "CeaUtil";
    public static final int USER_DATA_IDENTIFIER_GA94 = 1195456820;
    public static final int USER_DATA_TYPE_CODE_MPEG_CC = 3;

    private CeaUtil() {
    }

    public static void consume(long j10, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        while (true) {
            if (parsableByteArray.bytesLeft() <= 1) {
                return;
            }
            int non255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
            int non255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
            int position = parsableByteArray.getPosition() + non255TerminatedValue2;
            if (non255TerminatedValue2 == -1 || non255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                Log.w(TAG, "Skipping remainder of malformed SEI NAL unit.");
                position = parsableByteArray.limit();
            } else if (non255TerminatedValue == 4 && non255TerminatedValue2 >= 8) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                int unsignedShort = parsableByteArray.readUnsignedShort();
                int i10 = unsignedShort == PROVIDER_CODE_ATSC ? parsableByteArray.readInt() : 0;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                if (unsignedShort == PROVIDER_CODE_DIRECTV) {
                    parsableByteArray.skipBytes(1);
                }
                boolean z = unsignedByte == COUNTRY_CODE && (unsignedShort == PROVIDER_CODE_ATSC || unsignedShort == PROVIDER_CODE_DIRECTV) && unsignedByte2 == 3;
                if (unsignedShort == PROVIDER_CODE_ATSC) {
                    z &= i10 == 1195456820;
                }
                if (z) {
                    consumeCcData(j10, parsableByteArray, trackOutputArr);
                }
            }
            parsableByteArray.setPosition(position);
        }
    }

    public static void consumeCcData(long j10, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(1);
            int i10 = (unsignedByte & 31) * 3;
            int position = parsableByteArray.getPosition();
            for (TrackOutput trackOutput : trackOutputArr) {
                parsableByteArray.setPosition(position);
                trackOutput.sampleData(parsableByteArray, i10);
                ac.b.s(j10 != C.TIME_UNSET);
                trackOutput.sampleMetadata(j10, 1, i10, 0, null);
            }
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i10 = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            i10 += unsignedByte;
            if (unsignedByte != 255) {
                return i10;
            }
        }
        return -1;
    }
}
