package io.ktor.utils.io.core;

import g8.b;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a#\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/utils/io/core/Input;", "Ljava/nio/ByteBuffer;", "dst", "", "length", "Lx6/t0;", "readFully", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;I)V", "readAvailable", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;I)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputArraysJvmKt {
    public static final int readAvailable(Input input, ByteBuffer byteBuffer, int i10) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return 0;
        }
        int i11 = 0;
        do {
            try {
                int iLimit = byteBuffer.limit();
                byteBuffer.limit(Math.min(iLimit, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) + byteBuffer.position()));
                int iRemaining = byteBuffer.remaining();
                MemoryJvmKt.m6763copyTo62zg_DM(chunkBufferPrepareReadFirstHead.getMemory(), byteBuffer, chunkBufferPrepareReadFirstHead.getReadPosition());
                byteBuffer.limit(iLimit);
                i11 += iRemaining;
                if (!byteBuffer.hasRemaining() || i11 >= i10) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    return i11;
                }
                try {
                    chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } while (chunkBufferPrepareReadFirstHead != null);
        return i11;
    }

    public static /* synthetic */ int readAvailable$default(Input input, ByteBuffer byteBuffer, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer.remaining();
        }
        return readAvailable(input, byteBuffer, i10);
    }

    public static final void readFully(Input input, ByteBuffer byteBuffer, int i10) {
        if (readAvailable(input, byteBuffer, i10) < i10) {
            throw b.i(i10);
        }
    }

    public static /* synthetic */ void readFully$default(Input input, ByteBuffer byteBuffer, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer.remaining();
        }
        readFully(input, byteBuffer, i10);
    }
}
