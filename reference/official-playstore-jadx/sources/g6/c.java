package g6;

import androidx.leanback.widget.r1;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.WriterException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f15490a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[][] f15491b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[][] f15492c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, MediaError.DetailedErrorCode.MEDIA_DECODE, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, MediaError.DetailedErrorCode.MEDIA_DECODE, 126, -1}, new int[]{6, 26, 52, 78, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, -1}, new int[]{6, 30, 56, 82, 108, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, -1}, new int[]{6, 34, 60, 86, 112, TsExtractor.TS_STREAM_TYPE_DTS, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, MediaError.DetailedErrorCode.MEDIA_DECODE, 126, 150}, new int[]{6, 24, 50, 76, MediaError.DetailedErrorCode.MEDIA_DECODE, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, TsExtractor.TS_STREAM_TYPE_DTS_HD, 162}, new int[]{6, 26, 54, 82, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, TsExtractor.TS_STREAM_TYPE_DTS, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[][] f15493d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    public static int a(r1 r1Var, boolean z) {
        int i10 = r1Var.f3112b;
        int i11 = r1Var.f3113c;
        int i12 = z ? i11 : i10;
        if (!z) {
            i10 = i11;
        }
        byte[][] bArr = (byte[][]) r1Var.f3114d;
        int i13 = 0;
        for (int i14 = 0; i14 < i12; i14++) {
            byte b10 = -1;
            int i15 = 0;
            for (int i16 = 0; i16 < i10; i16++) {
                byte b11 = z ? bArr[i14][i16] : bArr[i16][i14];
                if (b11 == b10) {
                    i15++;
                } else {
                    if (i15 >= 5) {
                        i13 += i15 - 2;
                    }
                    i15 = 1;
                    b10 = b11;
                }
            }
            if (i15 >= 5) {
                i13 = (i15 - 2) + i13;
            }
        }
        return i13;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0243  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(b6.a r29, int r30, f6.b r31, int r32, androidx.leanback.widget.r1 r33) throws com.google.zxing.WriterException {
        /*
            Method dump skipped, instruction units count: 718
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.c.b(b6.a, int, f6.b, int, androidx.leanback.widget.r1):void");
    }

    public static int c(int i10, int i11) {
        if (i11 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i11);
        int i12 = 32 - iNumberOfLeadingZeros;
        int iNumberOfLeadingZeros2 = i10 << (31 - iNumberOfLeadingZeros);
        while (32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2) >= i12) {
            iNumberOfLeadingZeros2 ^= i11 << ((32 - Integer.numberOfLeadingZeros(iNumberOfLeadingZeros2)) - i12);
        }
        return iNumberOfLeadingZeros2;
    }

    public static void d(int i10, int i11, r1 r1Var) throws WriterException {
        for (int i12 = 0; i12 < 8; i12++) {
            int i13 = i10 + i12;
            if (!g(r1Var.c(i13, i11))) {
                throw new WriterException();
            }
            r1Var.k(i13, i11, 0);
        }
    }

    public static void e(int i10, int i11, r1 r1Var) {
        for (int i12 = 0; i12 < 7; i12++) {
            int[] iArr = f15490a[i12];
            for (int i13 = 0; i13 < 7; i13++) {
                r1Var.k(i10 + i13, i11 + i12, iArr[i13]);
            }
        }
    }

    public static void f(int i10, int i11, r1 r1Var) throws WriterException {
        for (int i12 = 0; i12 < 7; i12++) {
            int i13 = i11 + i12;
            if (!g(r1Var.c(i10, i13))) {
                throw new WriterException();
            }
            r1Var.k(i10, i13, 0);
        }
    }

    public static boolean g(int i10) {
        return i10 == -1;
    }
}
