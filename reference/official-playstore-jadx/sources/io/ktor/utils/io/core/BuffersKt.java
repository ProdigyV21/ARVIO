package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\n\u001a\u00020\t*\u0004\u0018\u00010\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a8\u0010\u000e\u001a\u00020\t*\u00020\u00062\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\fH\u0080\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0010\u001a\u00020\u0006*\u00020\u0006H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a$\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0082\u0010¢\u0006\u0004\b\u0010\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u00020\u0006*\u00020\u0006H\u0080\u0010¢\u0006\u0004\b\u0015\u0010\u0011\u001a\u0013\u0010\u0017\u001a\u00020\u0016*\u00020\u0006H\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u0017\u001a\u00020\u0016*\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0016H\u0082\u0010¢\u0006\u0004\b\u0017\u0010\u001a\u001a\u0014\u0010\u001c\u001a\u00020\u001b*\u00020\u0006H\u0080\u0010¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0014\u0010\u001e\u001a\u00020\u0001*\u00020\u0016H\u0080\b¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001c\u0010\"\u001a\u00020\u0001*\u00020\u00162\u0006\u0010!\u001a\u00020 H\u0080\b¢\u0006\u0004\b\"\u0010#\u001a9\u0010+\u001a\u00020\u0016*\u00020\u00002\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0016H\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b)\u0010*\u0082\u0002\u0012\n\u0005\b\u009920\u0001\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", "count", "", "readBytes", "(Lio/ktor/utils/io/core/Buffer;I)[B", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "Lx6/t0;", "releaseAll", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/pool/ObjectPool;)V", "Lkotlin/Function1;", "block", "forEachChunk", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lr7/l;)V", "copyAll", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", TtmlNode.TAG_HEAD, "prev", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "findTail", "", "remainingAll", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)J", "n", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;J)J", "", "isEmpty", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)Z", "coerceAtMostMaxInt", "(J)I", "", "message", "coerceAtMostMaxIntOrFail", "(JLjava/lang/String;)I", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", "offset", "max", "peekTo-yRinSxo", "(Lio/ktor/utils/io/core/Buffer;Ljava/nio/ByteBuffer;JJJ)J", "peekTo", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BuffersKt {
    public static final int coerceAtMostMaxInt(long j10) {
        return (int) Math.min(j10, 2147483647L);
    }

    public static final int coerceAtMostMaxIntOrFail(long j10, String str) {
        if (j10 <= 2147483647L) {
            return (int) j10;
        }
        throw new IllegalArgumentException(str);
    }

    public static final ChunkBuffer copyAll(ChunkBuffer chunkBuffer) {
        ChunkBuffer chunkBufferDuplicate = chunkBuffer.duplicate();
        ChunkBuffer next = chunkBuffer.getNext();
        return next == null ? chunkBufferDuplicate : copyAll(next, chunkBufferDuplicate, chunkBufferDuplicate);
    }

    public static final ChunkBuffer findTail(ChunkBuffer chunkBuffer) {
        while (true) {
            ChunkBuffer next = chunkBuffer.getNext();
            if (next == null) {
                return chunkBuffer;
            }
            chunkBuffer = next;
        }
    }

    public static final void forEachChunk(ChunkBuffer chunkBuffer, l<? super ChunkBuffer, t0> lVar) {
        do {
            lVar.invoke(chunkBuffer);
            chunkBuffer = chunkBuffer.getNext();
        } while (chunkBuffer != null);
    }

    public static final boolean isEmpty(ChunkBuffer chunkBuffer) {
        while (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition() <= 0) {
            chunkBuffer = chunkBuffer.getNext();
            if (chunkBuffer == null) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: peekTo-yRinSxo, reason: not valid java name */
    public static final long m6924peekToyRinSxo(Buffer buffer, ByteBuffer byteBuffer, long j10, long j11, long j12) {
        long jMin = Math.min(((long) byteBuffer.limit()) - j10, Math.min(j12, buffer.getWritePosition() - buffer.getReadPosition()));
        Memory.m6748copyToJT6ljtQ(buffer.getMemory(), byteBuffer, ((long) buffer.getReadPosition()) + j11, jMin, j10);
        return jMin;
    }

    public static final byte[] readBytes(Buffer buffer, int i10) {
        if (i10 == 0) {
            return UnsafeKt.EmptyByteArray;
        }
        byte[] bArr = new byte[i10];
        BufferPrimitivesKt.readFully$default(buffer, bArr, 0, 0, 6, (Object) null);
        return bArr;
    }

    public static /* synthetic */ byte[] readBytes$default(Buffer buffer, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = buffer.getWritePosition() - buffer.getReadPosition();
        }
        return readBytes(buffer, i10);
    }

    public static final void releaseAll(ChunkBuffer chunkBuffer, ObjectPool<ChunkBuffer> objectPool) {
        while (chunkBuffer != null) {
            ChunkBuffer chunkBufferCleanNext = chunkBuffer.cleanNext();
            chunkBuffer.release(objectPool);
            chunkBuffer = chunkBufferCleanNext;
        }
    }

    public static final long remainingAll(ChunkBuffer chunkBuffer) {
        return remainingAll(chunkBuffer, 0L);
    }

    private static final long remainingAll(ChunkBuffer chunkBuffer, long j10) {
        do {
            j10 += (long) (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition());
            chunkBuffer = chunkBuffer.getNext();
        } while (chunkBuffer != null);
        return j10;
    }

    private static final ChunkBuffer copyAll(ChunkBuffer chunkBuffer, ChunkBuffer chunkBuffer2, ChunkBuffer chunkBuffer3) {
        while (true) {
            ChunkBuffer chunkBufferDuplicate = chunkBuffer.duplicate();
            chunkBuffer3.setNext(chunkBufferDuplicate);
            chunkBuffer = chunkBuffer.getNext();
            if (chunkBuffer == null) {
                return chunkBuffer2;
            }
            chunkBuffer3 = chunkBufferDuplicate;
        }
    }
}
