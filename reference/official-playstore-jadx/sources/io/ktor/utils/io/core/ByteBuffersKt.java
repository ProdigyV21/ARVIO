package io.ktor.utils.io.core;

import g8.b;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0005\u001a$\u0010\t\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0082\u0010¢\u0006\u0004\b\t\u0010\n\u001a@\u0010\u0010\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a@\u0010\u0012\u001a\u00020\u0003*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a@\u0010\u0014\u001a\u00020\u000e*\u00020\u00002\u0006\u0010\f\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e0\rH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a@\u0010\u0014\u001a\u00020\u000e*\u00020\u00162\u0006\u0010\f\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000e0\rH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0014\u0010\u0017\u001a\u0013\u0010\u001a\u001a\u00020\u0019*\u00020\u0018H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"}, d2 = {"Lio/ktor/utils/io/core/ByteReadPacket;", "Ljava/nio/ByteBuffer;", "dst", "", "readAvailable", "(Lio/ktor/utils/io/core/ByteReadPacket;Ljava/nio/ByteBuffer;)I", "readFully", "bb", "copied", "readAsMuchAsPossible", "(Lio/ktor/utils/io/core/ByteReadPacket;Ljava/nio/ByteBuffer;I)I", "Lio/ktor/utils/io/core/BytePacketBuilder;", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "Lx6/t0;", "block", "writeDirect", "(Lio/ktor/utils/io/core/BytePacketBuilder;ILr7/l;)V", "writeByteBufferDirect", "(Lio/ktor/utils/io/core/BytePacketBuilder;ILr7/l;)I", "readDirect", "(Lio/ktor/utils/io/core/ByteReadPacket;ILr7/l;)V", "Lio/ktor/utils/io/core/Input;", "(Lio/ktor/utils/io/core/Input;ILr7/l;)V", "Lio/ktor/utils/io/core/Buffer;", "", "hasArray", "(Lio/ktor/utils/io/core/Buffer;)Z", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteBuffersKt {
    public static final boolean hasArray(Buffer buffer) {
        ByteBuffer memory = buffer.getMemory();
        return memory.hasArray() && !memory.isReadOnly();
    }

    private static final int readAsMuchAsPossible(ByteReadPacket byteReadPacket, ByteBuffer byteBuffer, int i10) throws EOFException {
        ChunkBuffer chunkBufferPrepareRead;
        while (byteBuffer.hasRemaining() && (chunkBufferPrepareRead = byteReadPacket.prepareRead(1)) != null) {
            int iRemaining = byteBuffer.remaining();
            int writePosition = chunkBufferPrepareRead.getWritePosition() - chunkBufferPrepareRead.getReadPosition();
            if (iRemaining < writePosition) {
                BufferUtilsJvmKt.readFully(chunkBufferPrepareRead, byteBuffer, iRemaining);
                byteReadPacket.setHeadPosition(chunkBufferPrepareRead.getReadPosition());
                return i10 + iRemaining;
            }
            BufferUtilsJvmKt.readFully(chunkBufferPrepareRead, byteBuffer, writePosition);
            byteReadPacket.releaseHead$ktor_io(chunkBufferPrepareRead);
            i10 += writePosition;
        }
        return i10;
    }

    public static final int readAvailable(ByteReadPacket byteReadPacket, ByteBuffer byteBuffer) {
        return readAsMuchAsPossible(byteReadPacket, byteBuffer, 0);
    }

    public static final void readDirect(ByteReadPacket byteReadPacket, int i10, l<? super ByteBuffer, t0> lVar) {
        ChunkBuffer chunkBufferPrepareRead = byteReadPacket.prepareRead(i10);
        if (chunkBufferPrepareRead == null) {
            throw b.i(i10);
        }
        int readPosition = chunkBufferPrepareRead.getReadPosition();
        try {
            ByteBuffer memory = chunkBufferPrepareRead.getMemory();
            int readPosition2 = chunkBufferPrepareRead.getReadPosition();
            int writePosition = chunkBufferPrepareRead.getWritePosition() - readPosition2;
            ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition2, writePosition);
            lVar.invoke(byteBufferM6756slice87lwejk);
            if (byteBufferM6756slice87lwejk.limit() != writePosition) {
                throw new IllegalStateException("Buffer's limit change is not allowed");
            }
            chunkBufferPrepareRead.discardExact(byteBufferM6756slice87lwejk.position());
            int readPosition3 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition3 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                byteReadPacket.ensureNext(chunkBufferPrepareRead);
            } else {
                byteReadPacket.setHeadPosition(readPosition3);
            }
        } catch (Throwable th) {
            int readPosition4 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition4 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition4 == chunkBufferPrepareRead.getWritePosition()) {
                byteReadPacket.ensureNext(chunkBufferPrepareRead);
            } else {
                byteReadPacket.setHeadPosition(readPosition4);
            }
            throw th;
        }
    }

    public static final int readFully(ByteReadPacket byteReadPacket, ByteBuffer byteBuffer) {
        int asMuchAsPossible = readAsMuchAsPossible(byteReadPacket, byteBuffer, 0);
        if (!byteBuffer.hasRemaining()) {
            return asMuchAsPossible;
        }
        throw new EOFException("Not enough data in packet to fill buffer: " + byteBuffer.remaining() + " more bytes required");
    }

    public static final int writeByteBufferDirect(BytePacketBuilder bytePacketBuilder, int i10, l<? super ByteBuffer, t0> lVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = bytePacketBuilder.prepareWriteHead(i10);
        try {
            ByteBuffer memory = chunkBufferPrepareWriteHead.getMemory();
            int writePosition = chunkBufferPrepareWriteHead.getWritePosition();
            int limit = chunkBufferPrepareWriteHead.getLimit() - writePosition;
            ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
            lVar.invoke(byteBufferM6756slice87lwejk);
            if (byteBufferM6756slice87lwejk.limit() != limit) {
                throw new IllegalStateException("Buffer's limit change is not allowed");
            }
            int iPosition = byteBufferM6756slice87lwejk.position();
            chunkBufferPrepareWriteHead.commitWritten(iPosition);
            if (iPosition < 0) {
                throw new IllegalStateException("The returned value shouldn't be negative");
            }
            bytePacketBuilder.afterHeadWrite();
            return iPosition;
        } catch (Throwable th) {
            bytePacketBuilder.afterHeadWrite();
            throw th;
        }
    }

    public static final void writeDirect(BytePacketBuilder bytePacketBuilder, int i10, l<? super ByteBuffer, t0> lVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = bytePacketBuilder.prepareWriteHead(i10);
        try {
            ByteBuffer memory = chunkBufferPrepareWriteHead.getMemory();
            int writePosition = chunkBufferPrepareWriteHead.getWritePosition();
            int limit = chunkBufferPrepareWriteHead.getLimit() - writePosition;
            ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, writePosition, limit);
            lVar.invoke(byteBufferM6756slice87lwejk);
            if (byteBufferM6756slice87lwejk.limit() != limit) {
                throw new IllegalStateException("Buffer's limit change is not allowed");
            }
            int iPosition = byteBufferM6756slice87lwejk.position();
            chunkBufferPrepareWriteHead.commitWritten(iPosition);
            if (iPosition < 0) {
                throw new IllegalStateException("The returned value shouldn't be negative");
            }
            bytePacketBuilder.afterHeadWrite();
        } catch (Throwable th) {
            bytePacketBuilder.afterHeadWrite();
            throw th;
        }
    }

    @e
    public static final void readDirect(Input input, int i10, l<? super ByteBuffer, t0> lVar) {
        ChunkBuffer chunkBufferPrepareRead = input.prepareRead(i10);
        if (chunkBufferPrepareRead != null) {
            int readPosition = chunkBufferPrepareRead.getReadPosition();
            try {
                ByteBuffer memory = chunkBufferPrepareRead.getMemory();
                int readPosition2 = chunkBufferPrepareRead.getReadPosition();
                int writePosition = chunkBufferPrepareRead.getWritePosition() - readPosition2;
                ByteBuffer byteBufferM6756slice87lwejk = Memory.m6756slice87lwejk(memory, readPosition2, writePosition);
                lVar.invoke(byteBufferM6756slice87lwejk);
                if (byteBufferM6756slice87lwejk.limit() == writePosition) {
                    chunkBufferPrepareRead.discardExact(byteBufferM6756slice87lwejk.position());
                    int readPosition3 = chunkBufferPrepareRead.getReadPosition();
                    if (readPosition3 >= readPosition) {
                        if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                            input.ensureNext(chunkBufferPrepareRead);
                            return;
                        } else {
                            input.setHeadPosition(readPosition3);
                            return;
                        }
                    }
                    throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                }
                throw new IllegalStateException("Buffer's limit change is not allowed");
            } catch (Throwable th) {
                int readPosition4 = chunkBufferPrepareRead.getReadPosition();
                if (readPosition4 >= readPosition) {
                    if (readPosition4 == chunkBufferPrepareRead.getWritePosition()) {
                        input.ensureNext(chunkBufferPrepareRead);
                    } else {
                        input.setHeadPosition(readPosition4);
                    }
                    throw th;
                }
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
        }
        throw b.i(i10);
    }
}
