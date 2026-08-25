package j$.util;

import io.ktor.util.date.GMTDateParser;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Base64 {
    public static Encoder getEncoder() {
        return Encoder.f18426c;
    }

    public static Decoder getDecoder() {
        return Decoder.f18423c;
    }

    public static class Encoder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final char[] f18424a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', GMTDateParser.YEAR, 'Z', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', 'u', 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final char[] f18425b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', GMTDateParser.MONTH, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', GMTDateParser.YEAR, 'Z', 'a', 'b', 'c', GMTDateParser.DAY_OF_MONTH, 'e', 'f', 'g', GMTDateParser.HOURS, 'i', 'j', 'k', 'l', GMTDateParser.MINUTES, 'n', 'o', 'p', 'q', 'r', GMTDateParser.SECONDS, 't', 'u', 'v', 'w', 'x', 'y', GMTDateParser.ZONE, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Encoder f18426c = new Encoder();

        public String encodeToString(byte[] bArr) {
            int length = ((bArr.length + 2) / 3) * 4;
            byte[] bArrCopyOf = new byte[length];
            int length2 = bArr.length;
            int i10 = (length2 / 3) * 3;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                char[] cArr = f18424a;
                if (i11 >= i10) {
                    if (i11 < length2) {
                        int i13 = i11 + 1;
                        int i14 = bArr[i11] & 255;
                        int i15 = i12 + 1;
                        bArrCopyOf[i12] = (byte) cArr[i14 >> 2];
                        if (i13 == length2) {
                            bArrCopyOf[i15] = (byte) cArr[(i14 << 4) & 63];
                            int i16 = i12 + 3;
                            bArrCopyOf[i12 + 2] = 61;
                            i12 += 4;
                            bArrCopyOf[i16] = 61;
                        } else {
                            int i17 = bArr[i13] & 255;
                            bArrCopyOf[i15] = (byte) cArr[((i14 << 4) & 63) | (i17 >> 4)];
                            int i18 = i12 + 3;
                            bArrCopyOf[i12 + 2] = (byte) cArr[(i17 << 2) & 63];
                            i12 += 4;
                            bArrCopyOf[i18] = 61;
                        }
                    }
                    if (i12 != length) {
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, i12);
                    }
                    return new String(bArrCopyOf, 0, 0, bArrCopyOf.length);
                }
                int iMin = Math.min(i11 + i10, i10);
                int i19 = i11;
                int i20 = i12;
                while (i19 < iMin) {
                    int i21 = i19 + 2;
                    int i22 = ((bArr[i19 + 1] & 255) << 8) | ((bArr[i19] & 255) << 16);
                    i19 += 3;
                    int i23 = i22 | (bArr[i21] & 255);
                    bArrCopyOf[i20] = (byte) cArr[(i23 >>> 18) & 63];
                    bArrCopyOf[i20 + 1] = (byte) cArr[(i23 >>> 12) & 63];
                    int i24 = i20 + 3;
                    bArrCopyOf[i20 + 2] = (byte) cArr[(i23 >>> 6) & 63];
                    i20 += 4;
                    bArrCopyOf[i24] = (byte) cArr[i23 & 63];
                }
                int i25 = ((iMin - i11) / 3) * 4;
                i12 += i25;
                if (i25 == -1 && iMin < length2) {
                    throw null;
                }
                i11 = iMin;
            }
        }
    }

    public static class Decoder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f18421a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int[] f18422b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Decoder f18423c;

        static {
            int[] iArr = new int[256];
            f18421a = iArr;
            Arrays.fill(iArr, -1);
            for (int i10 = 0; i10 < 64; i10++) {
                f18421a[Encoder.f18424a[i10]] = i10;
            }
            f18421a[61] = -2;
            int[] iArr2 = new int[256];
            f18422b = iArr2;
            Arrays.fill(iArr2, -1);
            for (int i11 = 0; i11 < 64; i11++) {
                f18422b[Encoder.f18425b[i11]] = i11;
            }
            f18422b[61] = -2;
            f18423c = new Decoder();
        }

        /* JADX WARN: Code restructure failed: missing block: B:45:0x00bf, code lost:
        
            if (r9 != 18) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x010a, code lost:
        
            if (r9 != 6) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x010c, code lost:
        
            r5[r10] = (byte) (r11 >> 16);
            r10 = r10 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0117, code lost:
        
            if (r9 != 0) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0119, code lost:
        
            r0 = r10 + 1;
            r5[r10] = (byte) (r11 >> 16);
            r10 = r10 + 2;
            r5[r0] = (byte) (r11 >> 8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x012a, code lost:
        
            if (r9 == 12) goto L69;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x012c, code lost:
        
            if (r8 < r6) goto L67;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x012e, code lost:
        
            if (r10 == r1) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0134, code lost:
        
            return java.util.Arrays.copyOf(r5, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0135, code lost:
        
            return r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0149, code lost:
        
            throw new java.lang.IllegalArgumentException("Input byte array has incorrect ending byte at " + r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0151, code lost:
        
            throw new java.lang.IllegalArgumentException("Last unit does not have enough valid bits");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public byte[] decode(java.lang.String r21) {
            /*
                Method dump skipped, instruction units count: 346
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: j$.util.Base64.Decoder.decode(java.lang.String):byte[]");
        }
    }
}
