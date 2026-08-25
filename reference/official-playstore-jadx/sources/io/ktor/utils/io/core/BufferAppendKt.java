package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Memory;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0004\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "other", "", "maxSize", "writeBufferAppend", "(Lio/ktor/utils/io/core/Buffer;Lio/ktor/utils/io/core/Buffer;I)I", "writeBufferPrepend", "(Lio/ktor/utils/io/core/Buffer;Lio/ktor/utils/io/core/Buffer;)I", "writeSize", "Lx6/t0;", "writeBufferAppendUnreserve", "(Lio/ktor/utils/io/core/Buffer;I)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferAppendKt {
    public static final int writeBufferAppend(Buffer buffer, Buffer buffer2, int i10) {
        int iMin = Math.min(buffer2.getWritePosition() - buffer2.getReadPosition(), i10);
        if (buffer.getLimit() - buffer.getWritePosition() <= iMin) {
            writeBufferAppendUnreserve(buffer, iMin);
        }
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        buffer.getLimit();
        ByteBuffer memory2 = buffer2.getMemory();
        int readPosition = buffer2.getReadPosition();
        buffer2.getWritePosition();
        Memory.m6747copyToJT6ljtQ(memory2, memory, readPosition, iMin, writePosition);
        buffer2.discardExact(iMin);
        buffer.commitWritten(iMin);
        return iMin;
    }

    private static final void writeBufferAppendUnreserve(Buffer buffer, int i10) {
        if ((buffer.getCapacity() - buffer.getLimit()) + (buffer.getLimit() - buffer.getWritePosition()) < i10) {
            throw new IllegalArgumentException("Can't append buffer: not enough free space at the end");
        }
        if ((buffer.getWritePosition() + i10) - buffer.getLimit() > 0) {
            buffer.releaseEndGap$ktor_io();
        }
    }

    public static final int writeBufferPrepend(Buffer buffer, Buffer buffer2) {
        int writePosition = buffer2.getWritePosition() - buffer2.getReadPosition();
        int readPosition = buffer.getReadPosition();
        if (readPosition < writePosition) {
            throw new IllegalArgumentException("Not enough space in the beginning to prepend bytes");
        }
        int i10 = readPosition - writePosition;
        Memory.m6747copyToJT6ljtQ(buffer2.getMemory(), buffer.getMemory(), buffer2.getReadPosition(), writePosition, i10);
        buffer2.discardExact(writePosition);
        buffer.releaseStartGap$ktor_io(i10);
        return writePosition;
    }
}
