package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public final class PcmAudioUtil {
    private PcmAudioUtil() {
    }

    public static ByteBuffer rampUpVolume(ByteBuffer byteBuffer, int i10, int i11, int i12, int i13) {
        ByteBuffer byteBufferOrder = ByteBuffer.allocateDirect(byteBuffer.remaining()).order(ByteOrder.nativeOrder());
        int iPosition = byteBuffer.position();
        while (byteBuffer.hasRemaining() && i12 < i13) {
            write32BitIntPcm(byteBufferOrder, (int) ((((long) readAs32BitIntPcm(byteBuffer, i10)) * ((long) i12)) / ((long) i13)), i10);
            if (byteBuffer.position() == iPosition + i11) {
                i12++;
                iPosition = byteBuffer.position();
            }
        }
        byteBufferOrder.put(byteBuffer);
        byteBufferOrder.flip();
        return byteBufferOrder;
    }

    public static int readAs32BitIntPcm(ByteBuffer byteBuffer, int i10) {
        if (i10 == 2) {
            return ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 16);
        }
        if (i10 == 3) {
            return (byteBuffer.get() & 255) << 24;
        }
        if (i10 == 4) {
            float fConstrainValue = Util.constrainValue(byteBuffer.getFloat(), -1.0f, 1.0f);
            return fConstrainValue < 0.0f ? (int) ((-fConstrainValue) * (-2.1474836E9f)) : (int) (fConstrainValue * 2.1474836E9f);
        }
        if (i10 == 21) {
            return ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16);
        }
        if (i10 == 22) {
            return ((byteBuffer.get() & 255) << 24) | (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16);
        }
        if (i10 == 268435456) {
            return ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 24);
        }
        if (i10 == 1342177280) {
            return ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 16);
        }
        if (i10 == 1610612736) {
            return (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 24) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 8);
        }
        throw new IllegalStateException();
    }

    public static void write32BitIntPcm(ByteBuffer byteBuffer, int i10, int i11) {
        if (i11 == 2) {
            byteBuffer.put((byte) (i10 >> 16));
            byteBuffer.put((byte) (i10 >> 24));
            return;
        }
        if (i11 == 3) {
            byteBuffer.put((byte) (i10 >> 24));
            return;
        }
        if (i11 == 4) {
            if (i10 < 0) {
                byteBuffer.putFloat((-i10) / (-2.1474836E9f));
                return;
            } else {
                byteBuffer.putFloat(i10 / 2.1474836E9f);
                return;
            }
        }
        if (i11 == 21) {
            byteBuffer.put((byte) (i10 >> 8));
            byteBuffer.put((byte) (i10 >> 16));
            byteBuffer.put((byte) (i10 >> 24));
            return;
        }
        if (i11 == 22) {
            byteBuffer.put((byte) i10);
            byteBuffer.put((byte) (i10 >> 8));
            byteBuffer.put((byte) (i10 >> 16));
            byteBuffer.put((byte) (i10 >> 24));
            return;
        }
        if (i11 == 268435456) {
            byteBuffer.put((byte) (i10 >> 24));
            byteBuffer.put((byte) (i10 >> 16));
            return;
        }
        if (i11 == 1342177280) {
            byteBuffer.put((byte) (i10 >> 24));
            byteBuffer.put((byte) (i10 >> 16));
            byteBuffer.put((byte) (i10 >> 8));
        } else {
            if (i11 != 1610612736) {
                throw new IllegalStateException();
            }
            byteBuffer.put((byte) (i10 >> 24));
            byteBuffer.put((byte) (i10 >> 16));
            byteBuffer.put((byte) (i10 >> 8));
            byteBuffer.put((byte) i10);
        }
    }
}
