package androidx.media3.extractor.mp4;

import androidx.compose.material3.MenuKt;
import androidx.media3.common.C;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;
import androidx.media3.extractor.ts.PsExtractor;

/* JADX INFO: loaded from: classes3.dex */
public final class SmtaAtomUtil {
    private static final int CAMCORDER_FRC_SUPERSLOW_MOTION = 9;
    private static final int CAMCORDER_FRC_SUPERSLOW_MOTION_HEVC = 22;
    private static final int CAMCORDER_NORMAL = 0;
    private static final int CAMCORDER_QFRC_SUPERSLOW_MOTION = 23;
    private static final int CAMCORDER_SINGLE_SUPERSLOW_MOTION = 7;
    private static final int CAMCORDER_SLOW_MOTION_V2 = 12;
    private static final int CAMCORDER_SLOW_MOTION_V2_120 = 13;
    private static final int CAMCORDER_SLOW_MOTION_V2_HEVC = 21;
    private static final int NO_VALUE = -1;

    private SmtaAtomUtil() {
    }

    private static int getCaptureFrameRate(int i10, ParsableByteArray parsableByteArray, int i11) {
        if (i10 == 12) {
            return PsExtractor.VIDEO_STREAM_MASK;
        }
        if (i10 == 13) {
            return MenuKt.InTransitionDuration;
        }
        if (i10 == 21 && parsableByteArray.bytesLeft() >= 8 && parsableByteArray.getPosition() + 8 <= i11) {
            int i12 = parsableByteArray.readInt();
            int i13 = parsableByteArray.readInt();
            if (i12 >= 12 && i13 == 1936877170) {
                return parsableByteArray.readUnsignedFixedPoint1616();
            }
        }
        return C.RATE_UNSET_INT;
    }

    public static Metadata parseSmta(ParsableByteArray parsableByteArray, int i10) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i10) {
            int position = parsableByteArray.getPosition();
            int i11 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1935766900) {
                if (i11 < 16) {
                    return null;
                }
                parsableByteArray.skipBytes(4);
                int i12 = -1;
                int i13 = 0;
                for (int i14 = 0; i14 < 2; i14++) {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte == 0) {
                        i12 = unsignedByte2;
                    } else if (unsignedByte == 1) {
                        i13 = unsignedByte2;
                    }
                }
                int captureFrameRate = getCaptureFrameRate(i12, parsableByteArray, i10);
                if (captureFrameRate == -2147483647) {
                    return null;
                }
                return new Metadata(new SmtaMetadataEntry(captureFrameRate, i13));
            }
            parsableByteArray.setPosition(position + i11);
        }
        return null;
    }
}
