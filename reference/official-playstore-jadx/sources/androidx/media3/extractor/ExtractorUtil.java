package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.ParserException;
import java.io.EOFException;
import java.io.IOException;
import org.checkerframework.dataflow.qual.Pure;

/* JADX INFO: loaded from: classes3.dex */
public final class ExtractorUtil {
    private ExtractorUtil() {
    }

    @Pure
    public static void checkContainerInput(boolean z, String str) throws ParserException {
        if (!z) {
            throw ParserException.createForMalformedContainer(str, null);
        }
    }

    public static int getMaximumEncodedRateBytesPerSecond(int i10) {
        if (i10 == 20) {
            return OpusUtil.MAX_BYTES_PER_SECOND;
        }
        if (i10 == 30) {
            return DtsUtil.DTS_HD_MAX_RATE_BYTES_PER_SECOND;
        }
        switch (i10) {
            case 5:
                return Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND;
            case 6:
                return 768000;
            case 7:
                return DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND;
            case 8:
                return DtsUtil.DTS_HD_MAX_RATE_BYTES_PER_SECOND;
            case 9:
                return MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND;
            case 10:
                return 100000;
            case 11:
                return AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND;
            case 12:
                return 7000;
            default:
                switch (i10) {
                    case 14:
                        return Ac3Util.TRUEHD_MAX_RATE_BYTES_PER_SECOND;
                    case 15:
                        return 8000;
                    case 16:
                        return AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND;
                    case 17:
                        return Ac4Util.MAX_RATE_BYTES_PER_SECOND;
                    case 18:
                        return 768000;
                    default:
                        return C.RATE_UNSET_INT;
                }
        }
    }

    public static boolean peekFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i10, int i11, boolean z) throws IOException {
        try {
            return extractorInput.peekFully(bArr, i10, i11, z);
        } catch (EOFException e5) {
            if (z) {
                return false;
            }
            throw e5;
        }
    }

    public static int peekToLength(ExtractorInput extractorInput, byte[] bArr, int i10, int i11) throws IOException {
        int i12 = 0;
        while (i12 < i11) {
            int iPeek = extractorInput.peek(bArr, i10 + i12, i11 - i12);
            if (iPeek == -1) {
                break;
            }
            i12 += iPeek;
        }
        return i12;
    }

    public static boolean readFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int i10, int i11) throws IOException {
        try {
            extractorInput.readFully(bArr, i10, i11);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean skipFullyQuietly(ExtractorInput extractorInput, int i10) throws IOException {
        try {
            extractorInput.skipFully(i10);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
