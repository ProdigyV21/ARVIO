package io.ktor.utils.io.core;

import a0.c;
import io.ktor.utils.io.bits.MemoryJvmKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\u0005¨\u0006\b"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "Ljava/nio/ByteBuffer;", "destination", "Lx6/t0;", "readFully", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/ByteBuffer;)V", "source", "writeFully", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferPrimitivesJvmKt {
    public static final void readFully(Buffer buffer, ByteBuffer byteBuffer) throws EOFException {
        int iRemaining = byteBuffer.remaining();
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < iRemaining) {
            throw new EOFException(c.k("Not enough bytes to read a buffer content of size ", iRemaining, '.'));
        }
        MemoryJvmKt.m6763copyTo62zg_DM(memory, byteBuffer, readPosition);
        buffer.discardExact(iRemaining);
    }

    public static final void writeFully(Buffer buffer, ByteBuffer byteBuffer) throws InsufficientSpaceException {
        int iRemaining = byteBuffer.remaining();
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        if (limit < iRemaining) {
            throw new InsufficientSpaceException("buffer content", iRemaining, limit);
        }
        MemoryJvmKt.m6767copyToSG11BkQ(byteBuffer, memory, writePosition);
        buffer.commitWritten(iRemaining);
    }
}
