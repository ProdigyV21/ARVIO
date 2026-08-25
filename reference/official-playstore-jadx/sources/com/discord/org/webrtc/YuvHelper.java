package com.discord.org.webrtc;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public class YuvHelper {
    public static void ABGRToI420(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15) {
        nativeABGRToI420((ByteBuffer) checkNotNull(byteBuffer, "src"), i10, (ByteBuffer) checkNotNull(byteBuffer2, "dstY"), i11, (ByteBuffer) checkNotNull(byteBuffer3, "dstU"), i12, (ByteBuffer) checkNotNull(byteBuffer4, "dstV"), i13, i14, i15);
    }

    public static void I420Copy(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15, int i16, int i17, int i18) {
        int i19 = (i14 + 1) / 2;
        int i20 = i15 * i14;
        int i21 = i15 * i16;
        int i22 = (i17 * i19) + i21;
        int i23 = (i17 * i18) + i21;
        int i24 = ((i19 - 1) * i17) + i23 + ((i13 + 1) / 2);
        if (byteBuffer4.capacity() < i24) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i24, byteBuffer4.capacity(), "Expected destination buffer capacity to be at least ", " was "));
        }
        ByteBuffer byteBufferSlice = byteBuffer4.slice();
        ByteBuffer byteBufferSlice2 = byteBuffer4.slice();
        I420Copy(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBufferSlice, i15, byteBufferSlice2, i17, byteBuffer4.slice(), i17, i13, i14);
    }

    public static void I420Rotate(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15) {
        checkNotNull(byteBuffer, "srcY");
        checkNotNull(byteBuffer2, "srcU");
        checkNotNull(byteBuffer3, "srcV");
        checkNotNull(byteBuffer4, "dst");
        int i16 = i15 % 180;
        int i17 = i16 == 0 ? i13 : i14;
        int i18 = i16 == 0 ? i14 : i13;
        int i19 = (i18 + 1) / 2;
        int i20 = (i17 + 1) / 2;
        int i21 = i18 * i17;
        int i22 = i19 * i20;
        int i23 = (i22 * 2) + i21;
        if (byteBuffer4.capacity() < i23) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i23, byteBuffer4.capacity(), "Expected destination buffer capacity to be at least ", " was "));
        }
        int i24 = i22 + i21;
        ByteBuffer byteBufferSlice = byteBuffer4.slice();
        ByteBuffer byteBufferSlice2 = byteBuffer4.slice();
        nativeI420Rotate(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBufferSlice, i17, byteBufferSlice2, i20, byteBuffer4.slice(), i20, i13, i14, i15);
    }

    public static void I420ToNV12(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15, int i16) {
        int i17 = (i13 + 1) / 2;
        int i18 = i15 * i14;
        int i19 = i15 * i16;
        int i20 = (((i14 + 1) / 2) * i17 * 2) + i19;
        if (byteBuffer4.capacity() < i20) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i20, byteBuffer4.capacity(), "Expected destination buffer capacity to be at least ", " was "));
        }
        ByteBuffer byteBufferSlice = byteBuffer4.slice();
        I420ToNV12(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBufferSlice, i15, byteBuffer4.slice(), i17 * 2, i13, i14);
    }

    private static <T> T checkNotNull(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(androidx.compose.material3.d.m(str, " should not be null"));
    }

    public static void copyPlane(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, int i12, int i13) {
        nativeCopyPlane((ByteBuffer) checkNotNull(byteBuffer, "src"), i10, (ByteBuffer) checkNotNull(byteBuffer2, "dst"), i11, i12, i13);
    }

    private static native void nativeABGRToI420(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15);

    private static native void nativeCopyPlane(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, int i12, int i13);

    private static native void nativeI420Copy(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, ByteBuffer byteBuffer6, int i15, int i16, int i17);

    private static native void nativeI420Rotate(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, ByteBuffer byteBuffer6, int i15, int i16, int i17, int i18);

    private static native void nativeI420ToNV12(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, int i15, int i16);

    public static void I420ToNV12(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14) {
        I420ToNV12(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, i14, i13, i14);
    }

    public static void I420ToNV12(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, int i15, int i16) {
        checkNotNull(byteBuffer, "srcY");
        checkNotNull(byteBuffer2, "srcU");
        checkNotNull(byteBuffer3, "srcV");
        checkNotNull(byteBuffer4, "dstY");
        checkNotNull(byteBuffer5, "dstUV");
        if (i15 > 0 && i16 > 0) {
            nativeI420ToNV12(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, byteBuffer5, i14, i15, i16);
            return;
        }
        throw new IllegalArgumentException("I420ToNV12: width and height should not be negative");
    }

    public static void I420Copy(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14) {
        I420Copy(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, i14, i13, i14, (i13 + 1) / 2, (i14 + 1) / 2);
    }

    public static void I420Copy(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, int i14, int i15, int i16) {
        I420Copy(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, i14, i15, i16, (i15 + 1) / 2, (i16 + 1) / 2);
    }

    public static void I420Copy(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, ByteBuffer byteBuffer6, int i15, int i16, int i17) {
        checkNotNull(byteBuffer, "srcY");
        checkNotNull(byteBuffer2, "srcU");
        checkNotNull(byteBuffer3, "srcV");
        checkNotNull(byteBuffer4, "dstY");
        checkNotNull(byteBuffer5, "dstU");
        checkNotNull(byteBuffer6, "dstV");
        if (i16 > 0 && i17 > 0) {
            nativeI420Copy(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, byteBuffer5, i14, byteBuffer6, i15, i16, i17);
            return;
        }
        throw new IllegalArgumentException("I420Copy: width and height should not be negative");
    }

    public static void I420Rotate(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13, ByteBuffer byteBuffer5, int i14, ByteBuffer byteBuffer6, int i15, int i16, int i17, int i18) {
        checkNotNull(byteBuffer, "srcY");
        checkNotNull(byteBuffer2, "srcU");
        checkNotNull(byteBuffer3, "srcV");
        checkNotNull(byteBuffer4, "dstY");
        checkNotNull(byteBuffer5, "dstU");
        checkNotNull(byteBuffer6, "dstV");
        nativeI420Rotate(byteBuffer, i10, byteBuffer2, i11, byteBuffer3, i12, byteBuffer4, i13, byteBuffer5, i14, byteBuffer6, i15, i16, i17, i18);
    }
}
