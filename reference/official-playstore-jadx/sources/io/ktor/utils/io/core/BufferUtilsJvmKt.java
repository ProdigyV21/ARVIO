package io.ktor.utils.io.core;

import a0.c;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.internal.jvm.ErrorsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u001a'\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a+\u0010\u000b\u001a\u00020\n*\u00020\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a3\u0010\u000e\u001a\u00020\n*\u00020\u00032\u0006\u0010\r\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001b\u0010\u0011\u001a\u00020\b*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u0016\u001a\u00020\b*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0017\u001a#\u0010\u0018\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u0019\u001a8\u0010\u000b\u001a\u00020\n*\u00020\u00132\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\u000b\u0010\u001a\u001aB\u0010\u000e\u001a\u00020\n*\u00020\u00132\b\b\u0002\u0010\r\u001a\u00020\n2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0007H\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u000e\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, d2 = {"Ljava/nio/ByteBuffer;", "buffer", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "ChunkBuffer", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/pool/ObjectPool;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lkotlin/Function1;", "Lx6/t0;", "block", "", "readDirect", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lr7/l;)I", ContentDisposition.Parameters.Size, "writeDirect", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;ILr7/l;)I", "child", "resetFromContentToWrite", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Ljava/nio/ByteBuffer;)V", "Lio/ktor/utils/io/core/Buffer;", "dst", "length", "readFully", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/ByteBuffer;I)V", "readAvailable", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/ByteBuffer;I)I", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;)I", "(Lio/ktor/utils/io/core/Buffer;ILr7/l;)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferUtilsJvmKt {
    public static final ChunkBuffer ChunkBuffer(ByteBuffer byteBuffer, ObjectPool<ChunkBuffer> objectPool) {
        Memory.Companion companion = Memory.INSTANCE;
        return new ChunkBuffer(Memory.m6746constructorimpl(byteBuffer.slice().order(java.nio.ByteOrder.BIG_ENDIAN)), null, objectPool, null);
    }

    public static /* synthetic */ ChunkBuffer ChunkBuffer$default(ByteBuffer byteBuffer, ObjectPool objectPool, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            objectPool = null;
        }
        return ChunkBuffer(byteBuffer, objectPool);
    }

    public static final int readAvailable(Buffer buffer, ByteBuffer byteBuffer, int i10) throws EOFException {
        if (buffer.getWritePosition() <= buffer.getReadPosition()) {
            return -1;
        }
        int iMin = Math.min(buffer.getWritePosition() - buffer.getReadPosition(), i10);
        readFully(buffer, byteBuffer, iMin);
        return iMin;
    }

    public static /* synthetic */ int readAvailable$default(Buffer buffer, ByteBuffer byteBuffer, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer.remaining();
        }
        return readAvailable(buffer, byteBuffer, i10);
    }

    public static final int readDirect(ChunkBuffer chunkBuffer, l<? super ByteBuffer, t0> lVar) {
        int readPosition = chunkBuffer.getReadPosition();
        int writePosition = chunkBuffer.getWritePosition();
        ByteBuffer byteBufferDuplicate = chunkBuffer.getMemory().duplicate();
        byteBufferDuplicate.limit(writePosition);
        byteBufferDuplicate.position(readPosition);
        lVar.invoke(byteBufferDuplicate);
        int iPosition = byteBufferDuplicate.position() - readPosition;
        if (iPosition < 0) {
            ErrorsKt.negativeShiftError(iPosition);
            throw new KotlinNothingValueException();
        }
        if (byteBufferDuplicate.limit() == writePosition) {
            chunkBuffer.discardExact(iPosition);
            return iPosition;
        }
        ErrorsKt.limitChangeError();
        throw new KotlinNothingValueException();
    }

    public static final void readFully(Buffer buffer, ByteBuffer byteBuffer, int i10) throws EOFException {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        if (buffer.getWritePosition() - readPosition < i10) {
            throw new EOFException(c.k("Not enough bytes to read a buffer content of size ", i10, '.'));
        }
        int iLimit = byteBuffer.limit();
        try {
            byteBuffer.limit(byteBuffer.position() + i10);
            MemoryJvmKt.m6763copyTo62zg_DM(memory, byteBuffer, readPosition);
            byteBuffer.limit(iLimit);
            buffer.discardExact(i10);
        } catch (Throwable th) {
            byteBuffer.limit(iLimit);
            throw th;
        }
    }

    public static final void resetFromContentToWrite(ChunkBuffer chunkBuffer, ByteBuffer byteBuffer) {
        chunkBuffer.resetForWrite(byteBuffer.limit());
        chunkBuffer.commitWrittenUntilIndex(byteBuffer.position());
    }

    public static final int writeDirect(ChunkBuffer chunkBuffer, int i10, l<? super ByteBuffer, t0> lVar) {
        int limit = chunkBuffer.getLimit() - chunkBuffer.getWritePosition();
        if (i10 > limit) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, limit, "size ", " is greater than buffer's remaining capacity ").toString());
        }
        ByteBuffer byteBufferDuplicate = chunkBuffer.getMemory().duplicate();
        int writePosition = chunkBuffer.getWritePosition();
        byteBufferDuplicate.limit(chunkBuffer.getLimit());
        byteBufferDuplicate.position(writePosition);
        lVar.invoke(byteBufferDuplicate);
        int iPosition = byteBufferDuplicate.position() - writePosition;
        if (iPosition < 0 || iPosition > limit) {
            ErrorsKt.wrongBufferPositionChangeError(iPosition, i10);
            throw new KotlinNothingValueException();
        }
        chunkBuffer.commitWritten(iPosition);
        return iPosition;
    }

    public static /* synthetic */ int writeDirect$default(Buffer buffer, int i10, l lVar, int i11, Object obj) {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
        lVar.invoke(byteBufferM6756slice87lwejk);
        if (byteBufferM6756slice87lwejk.limit() != limit) {
            throw new IllegalStateException("Buffer's limit change is not allowed");
        }
        int iPosition = byteBufferM6756slice87lwejk.position();
        buffer.commitWritten(iPosition);
        return iPosition;
    }

    public static final int readDirect(Buffer buffer, l<? super ByteBuffer, t0> lVar) {
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition() - readPosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition, writePosition);
        lVar.invoke(byteBufferM6756slice87lwejk);
        if (byteBufferM6756slice87lwejk.limit() == writePosition) {
            int iPosition = byteBufferM6756slice87lwejk.position();
            buffer.discardExact(iPosition);
            return iPosition;
        }
        throw new IllegalStateException("Buffer's limit change is not allowed");
    }

    public static final int writeDirect(Buffer buffer, int i10, l<? super ByteBuffer, t0> lVar) {
        ByteBuffer memory = buffer.getMemory();
        int writePosition = buffer.getWritePosition();
        int limit = buffer.getLimit() - writePosition;
        ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
        lVar.invoke(byteBufferM6756slice87lwejk);
        if (byteBufferM6756slice87lwejk.limit() == limit) {
            int iPosition = byteBufferM6756slice87lwejk.position();
            buffer.commitWritten(iPosition);
            return iPosition;
        }
        throw new IllegalStateException("Buffer's limit change is not allowed");
    }
}
