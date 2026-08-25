package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import g8.b;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UTF8Kt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.Closeable;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.text.a;
import r7.l;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b4\b'\u0018\u00002\u00060\u0001j\u0002`\u00022\u00060\u0003j\u0002`\u0004B\u0015\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tB\t\b\u0016¢\u0006\u0004\b\b\u0010\nJ-\u0010\u0013\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH$ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0010H$¢\u0006\u0004\b\u0014\u0010\nJ\r\u0010\u0013\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\nJ\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u001d\u0010\u001aJ\u0015\u0010!\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0010¢\u0006\u0004\b#\u0010\nJ\u0017\u0010&\u001a\u00020\u00002\u0006\u0010%\u001a\u00020$H\u0016¢\u0006\u0004\b&\u0010'J\u0019\u0010&\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010(H\u0016¢\u0006\u0004\b&\u0010)J)\u0010&\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010(2\u0006\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\rH\u0016¢\u0006\u0004\b&\u0010,J\u0015\u0010/\u001a\u00020\u00102\u0006\u0010.\u001a\u00020-¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u00102\u0006\u00101\u001a\u00020\u0006H\u0000¢\u0006\u0004\b2\u0010\u001aJ\u001d\u0010/\u001a\u00020\u00102\u0006\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\r¢\u0006\u0004\b/\u00106J\u001d\u0010/\u001a\u00020\u00102\u0006\u00104\u001a\u00020-2\u0006\u00105\u001a\u000207¢\u0006\u0004\b/\u00108J)\u0010&\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020\r2\u0006\u0010<\u001a\u00020\r¢\u0006\u0004\b&\u0010=J\r\u0010>\u001a\u00020\u0010¢\u0006\u0004\b>\u0010\nJ\u0017\u0010?\u001a\u00020\u00062\u0006\u00105\u001a\u00020\rH\u0001¢\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020\u0010H\u0001¢\u0006\u0004\bA\u0010\nJ/\u0010F\u001a\u00020\r2\u0006\u0010B\u001a\u00020\r2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\r0CH\u0081\bø\u0001\u0002¢\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0006H\u0010¢\u0006\u0004\bH\u0010\u001aJ\u000f\u0010K\u001a\u00020\u0010H\u0000¢\u0006\u0004\bJ\u0010\nJ\u000f\u0010L\u001a\u00020\u0010H\u0002¢\u0006\u0004\bL\u0010\nJ\u000f\u0010M\u001a\u00020\u0006H\u0002¢\u0006\u0004\bM\u0010\u0016J'\u0010P\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020\rH\u0002¢\u0006\u0004\bP\u0010QJ\u0017\u0010R\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\bR\u0010\"J\u0017\u0010T\u001a\u00020\u00102\u0006\u0010S\u001a\u00020$H\u0002¢\u0006\u0004\bT\u0010UJ-\u0010X\u001a\u00020\u00102\u0006\u0010V\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002¢\u0006\u0004\bX\u0010YJ\u001f\u0010Z\u001a\u00020\u00102\u0006\u0010W\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u0006H\u0002¢\u0006\u0004\bZ\u0010[R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0007\u0010\\\u001a\u0004\b]\u0010^R\u0018\u0010_\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010a\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010`R+\u0010b\u001a\u00020\u000b8\u0000@\u0000X\u0080\u000eø\u0001\u0001ø\u0001\u0000ø\u0001\u0003¢\u0006\u0012\n\u0004\bb\u0010c\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\"\u0010h\u001a\u00020\r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bh\u0010i\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\"\u0010n\u001a\u00020\r8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bn\u0010i\u001a\u0004\bo\u0010k\"\u0004\bp\u0010mR\u0016\u0010q\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bq\u0010iR\u0016\u0010r\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010iR\u0014\u0010t\u001a\u00020\r8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bs\u0010kR\u0014\u0010\u001c\u001a\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bu\u0010\u0016R\u0015\u0010w\u001a\u00020\r8À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\bv\u0010k\u0082\u0002\u0016\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001\n\u0002\b!¨\u0006x"}, d2 = {"Lio/ktor/utils/io/core/Output;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "<init>", "(Lio/ktor/utils/io/pool/ObjectPool;)V", "()V", "Lio/ktor/utils/io/bits/Memory;", "source", "", "offset", "length", "Lx6/t0;", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "flush", "closeDestination", "stealAll$ktor_io", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "stealAll", "buffer", "appendSingleChunk$ktor_io", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "appendSingleChunk", TtmlNode.TAG_HEAD, "appendChain$ktor_io", "appendChain", "", "v", "writeByte", "(B)V", "close", "", "value", "append", "(C)Lio/ktor/utils/io/core/Output;", "", "(Ljava/lang/CharSequence;)Lio/ktor/utils/io/core/Output;", "startIndex", "endIndex", "(Ljava/lang/CharSequence;II)Lio/ktor/utils/io/core/Output;", "Lio/ktor/utils/io/core/ByteReadPacket;", "packet", "writePacket", "(Lio/ktor/utils/io/core/ByteReadPacket;)V", "chunkBuffer", "writeChunkBuffer$ktor_io", "writeChunkBuffer", TtmlNode.TAG_P, "n", "(Lio/ktor/utils/io/core/ByteReadPacket;I)V", "", "(Lio/ktor/utils/io/core/ByteReadPacket;J)V", "", "csq", TtmlNode.START, TtmlNode.END, "([CII)Ljava/lang/Appendable;", "release", "prepareWriteHead", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "afterHeadWrite", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "block", "write", "(ILr7/l;)I", "last$ktor_io", "last", "afterBytesStolen$ktor_io", "afterBytesStolen", "flushChain", "appendNewChunk", "newTail", "chainedSizeDelta", "appendChainImpl", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;I)V", "writeByteFallback", "c", "appendCharFallback", "(C)V", "tail", "foreignStolen", "writePacketMerging", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/pool/ObjectPool;)V", "writePacketSlowPrepend", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "Lio/ktor/utils/io/pool/ObjectPool;", "getPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "_head", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "_tail", "tailMemory", "Ljava/nio/ByteBuffer;", "getTailMemory-SK3TCg8$ktor_io", "()Ljava/nio/ByteBuffer;", "setTailMemory-3GNKZMM$ktor_io", "(Ljava/nio/ByteBuffer;)V", "tailPosition", "I", "getTailPosition$ktor_io", "()I", "setTailPosition$ktor_io", "(I)V", "tailEndExclusive", "getTailEndExclusive$ktor_io", "setTailEndExclusive$ktor_io", "tailInitialPosition", "chainedSize", "get_size", "_size", "getHead$ktor_io", "getTailRemaining$ktor_io", "tailRemaining", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public abstract class Output implements Appendable, Closeable {
    private ChunkBuffer _head;
    private ChunkBuffer _tail;
    private int chainedSize;
    private final ObjectPool<ChunkBuffer> pool;
    private int tailEndExclusive;
    private int tailInitialPosition;
    private ByteBuffer tailMemory;
    private int tailPosition;

    public Output(ObjectPool<ChunkBuffer> objectPool) {
        this.pool = objectPool;
        this.tailMemory = Memory.INSTANCE.m6762getEmptySK3TCg8();
    }

    private final void appendChainImpl(ChunkBuffer head, ChunkBuffer newTail, int chainedSizeDelta) {
        ChunkBuffer chunkBuffer = this._tail;
        if (chunkBuffer == null) {
            this._head = head;
            this.chainedSize = 0;
        } else {
            chunkBuffer.setNext(head);
            int i10 = this.tailPosition;
            chunkBuffer.commitWrittenUntilIndex(i10);
            this.chainedSize = (i10 - this.tailInitialPosition) + this.chainedSize;
        }
        this._tail = newTail;
        this.chainedSize += chainedSizeDelta;
        this.tailMemory = newTail.getMemory();
        this.tailPosition = newTail.getWritePosition();
        this.tailInitialPosition = newTail.getReadPosition();
        this.tailEndExclusive = newTail.getLimit();
    }

    private final void appendCharFallback(char c10) {
        int i10 = 3;
        ChunkBuffer chunkBufferPrepareWriteHead = prepareWriteHead(3);
        try {
            ByteBuffer memory = chunkBufferPrepareWriteHead.getMemory();
            int writePosition = chunkBufferPrepareWriteHead.getWritePosition();
            if (c10 >= 0 && c10 < 128) {
                memory.put(writePosition, (byte) c10);
                i10 = 1;
            } else if (128 <= c10 && c10 < 2048) {
                memory.put(writePosition, (byte) (((c10 >> 6) & 31) | PsExtractor.AUDIO_STREAM));
                memory.put(writePosition + 1, (byte) ((c10 & '?') | 128));
                i10 = 2;
            } else if (2048 <= c10 && c10 < 0) {
                memory.put(writePosition, (byte) (((c10 >> '\f') & 15) | 224));
                memory.put(writePosition + 1, (byte) (((c10 >> 6) & 63) | 128));
                memory.put(writePosition + 2, (byte) ((c10 & '?') | 128));
            } else {
                if (0 > c10 || c10 >= 0) {
                    UTF8Kt.malformedCodePoint(c10);
                    throw new KotlinNothingValueException();
                }
                memory.put(writePosition, (byte) (((c10 >> 18) & 7) | PsExtractor.VIDEO_STREAM_MASK));
                memory.put(writePosition + 1, (byte) (((c10 >> '\f') & 63) | 128));
                memory.put(writePosition + 2, (byte) (((c10 >> 6) & 63) | 128));
                memory.put(writePosition + 3, (byte) ((c10 & '?') | 128));
                i10 = 4;
            }
            chunkBufferPrepareWriteHead.commitWritten(i10);
            afterHeadWrite();
        } catch (Throwable th) {
            afterHeadWrite();
            throw th;
        }
    }

    private final ChunkBuffer appendNewChunk() {
        ChunkBuffer chunkBufferBorrow = this.pool.borrow();
        chunkBufferBorrow.reserveEndGap(8);
        appendSingleChunk$ktor_io(chunkBufferBorrow);
        return chunkBufferBorrow;
    }

    private final void flushChain() {
        ChunkBuffer chunkBufferStealAll$ktor_io = stealAll$ktor_io();
        if (chunkBufferStealAll$ktor_io == null) {
            return;
        }
        ChunkBuffer next = chunkBufferStealAll$ktor_io;
        do {
            try {
                mo6925flush62zg_DM(next.getMemory(), next.getReadPosition(), next.getWritePosition() - next.getReadPosition());
                next = next.getNext();
            } finally {
                BuffersKt.releaseAll(chunkBufferStealAll$ktor_io, this.pool);
            }
        } while (next != null);
    }

    private final void writeByteFallback(byte v2) throws InsufficientSpaceException {
        appendNewChunk().writeByte(v2);
        this.tailPosition++;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void writePacketMerging(io.ktor.utils.io.core.internal.ChunkBuffer r8, io.ktor.utils.io.core.internal.ChunkBuffer r9, io.ktor.utils.io.pool.ObjectPool<io.ktor.utils.io.core.internal.ChunkBuffer> r10) {
        /*
            r7 = this;
            int r0 = r7.tailPosition
            r8.commitWrittenUntilIndex(r0)
            int r0 = r8.getWritePosition()
            int r1 = r8.getReadPosition()
            int r0 = r0 - r1
            int r1 = r9.getWritePosition()
            int r2 = r9.getReadPosition()
            int r1 = r1 - r2
            int r2 = io.ktor.utils.io.core.PacketJVMKt.getPACKET_MAX_COPY_SIZE()
            r3 = -1
            if (r1 >= r2) goto L34
            int r4 = r8.getCapacity()
            int r5 = r8.getLimit()
            int r4 = r4 - r5
            int r5 = r8.getLimit()
            int r6 = r8.getWritePosition()
            int r5 = r5 - r6
            int r5 = r5 + r4
            if (r1 > r5) goto L34
            goto L35
        L34:
            r1 = r3
        L35:
            if (r0 >= r2) goto L44
            int r2 = r9.getStartGap()
            if (r0 > r2) goto L44
            boolean r2 = io.ktor.utils.io.core.internal.ChunkBufferKt.isExclusivelyOwned(r9)
            if (r2 == 0) goto L44
            goto L45
        L44:
            r0 = r3
        L45:
            if (r1 != r3) goto L4d
            if (r0 != r3) goto L4d
            r7.appendChain$ktor_io(r9)
            return
        L4d:
            if (r0 == r3) goto L69
            if (r1 > r0) goto L52
            goto L69
        L52:
            if (r1 == r3) goto L65
            if (r0 >= r1) goto L57
            goto L65
        L57:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "prep = "
            java.lang.String r10 = ", app = "
            java.lang.String r9 = androidx.compose.foundation.c.n(r0, r1, r9, r10)
            r8.<init>(r9)
            throw r8
        L65:
            r7.writePacketSlowPrepend(r9, r8)
            return
        L69:
            int r0 = r8.getLimit()
            int r1 = r8.getWritePosition()
            int r0 = r0 - r1
            int r1 = r8.getCapacity()
            int r2 = r8.getLimit()
            int r1 = r1 - r2
            int r1 = r1 + r0
            io.ktor.utils.io.core.BufferAppendKt.writeBufferAppend(r8, r9, r1)
            r7.afterHeadWrite()
            io.ktor.utils.io.core.internal.ChunkBuffer r8 = r9.cleanNext()
            if (r8 == 0) goto L8b
            r7.appendChain$ktor_io(r8)
        L8b:
            r9.release(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.Output.writePacketMerging(io.ktor.utils.io.core.internal.ChunkBuffer, io.ktor.utils.io.core.internal.ChunkBuffer, io.ktor.utils.io.pool.ObjectPool):void");
    }

    private final void writePacketSlowPrepend(ChunkBuffer foreignStolen, ChunkBuffer tail) {
        BufferAppendKt.writeBufferPrepend(foreignStolen, tail);
        ChunkBuffer chunkBuffer = this._head;
        if (chunkBuffer == null) {
            throw new IllegalStateException("head should't be null since it is already handled in the fast-path");
        }
        if (chunkBuffer == tail) {
            this._head = foreignStolen;
        } else {
            while (true) {
                ChunkBuffer next = chunkBuffer.getNext();
                if (next == tail) {
                    break;
                } else {
                    chunkBuffer = next;
                }
            }
            chunkBuffer.setNext(foreignStolen);
        }
        tail.release(this.pool);
        this._tail = BuffersKt.findTail(foreignStolen);
    }

    public final void afterBytesStolen$ktor_io() {
        ChunkBuffer head$ktor_io = getHead$ktor_io();
        if (head$ktor_io != ChunkBuffer.INSTANCE.getEmpty()) {
            if (head$ktor_io.getNext() != null) {
                throw new IllegalStateException("Check failed.");
            }
            head$ktor_io.resetForWrite();
            head$ktor_io.reserveEndGap(8);
            int writePosition = head$ktor_io.getWritePosition();
            this.tailPosition = writePosition;
            this.tailInitialPosition = writePosition;
            this.tailEndExclusive = head$ktor_io.getLimit();
        }
    }

    public final void afterHeadWrite() {
        ChunkBuffer chunkBuffer = this._tail;
        if (chunkBuffer != null) {
            this.tailPosition = chunkBuffer.getWritePosition();
        }
    }

    public final void appendChain$ktor_io(ChunkBuffer head) {
        ChunkBuffer chunkBufferFindTail = BuffersKt.findTail(head);
        long jRemainingAll = BuffersKt.remainingAll(head) - ((long) (chunkBufferFindTail.getWritePosition() - chunkBufferFindTail.getReadPosition()));
        if (jRemainingAll >= 2147483647L) {
            throw b.j(jRemainingAll, "total size increase");
        }
        appendChainImpl(head, chunkBufferFindTail, (int) jRemainingAll);
    }

    public final void appendSingleChunk$ktor_io(ChunkBuffer buffer) {
        if (buffer.getNext() != null) {
            throw new IllegalStateException("It should be a single buffer chunk.");
        }
        appendChainImpl(buffer, buffer, 0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            flush();
        } finally {
            closeDestination();
        }
    }

    public abstract void closeDestination();

    public final void flush() {
        flushChain();
    }

    /* JADX INFO: renamed from: flush-62zg_DM */
    public abstract void mo6925flush62zg_DM(ByteBuffer source, int offset, int length);

    public final ChunkBuffer getHead$ktor_io() {
        ChunkBuffer chunkBuffer = this._head;
        return chunkBuffer == null ? ChunkBuffer.INSTANCE.getEmpty() : chunkBuffer;
    }

    public final ObjectPool<ChunkBuffer> getPool() {
        return this.pool;
    }

    /* JADX INFO: renamed from: getTailEndExclusive$ktor_io, reason: from getter */
    public final int getTailEndExclusive() {
        return this.tailEndExclusive;
    }

    /* JADX INFO: renamed from: getTailMemory-SK3TCg8$ktor_io, reason: not valid java name and from getter */
    public final ByteBuffer getTailMemory() {
        return this.tailMemory;
    }

    /* JADX INFO: renamed from: getTailPosition$ktor_io, reason: from getter */
    public final int getTailPosition() {
        return this.tailPosition;
    }

    public final int getTailRemaining$ktor_io() {
        return getTailEndExclusive() - getTailPosition();
    }

    public final int get_size() {
        return (this.tailPosition - this.tailInitialPosition) + this.chainedSize;
    }

    public void last$ktor_io(ChunkBuffer buffer) {
        appendSingleChunk$ktor_io(buffer);
    }

    public final ChunkBuffer prepareWriteHead(int n6) {
        ChunkBuffer chunkBuffer;
        if (getTailEndExclusive() - getTailPosition() < n6 || (chunkBuffer = this._tail) == null) {
            return appendNewChunk();
        }
        chunkBuffer.commitWrittenUntilIndex(this.tailPosition);
        return chunkBuffer;
    }

    public final void release() {
        close();
    }

    public final void setTailEndExclusive$ktor_io(int i10) {
        this.tailEndExclusive = i10;
    }

    /* JADX INFO: renamed from: setTailMemory-3GNKZMM$ktor_io, reason: not valid java name */
    public final void m6960setTailMemory3GNKZMM$ktor_io(ByteBuffer byteBuffer) {
        this.tailMemory = byteBuffer;
    }

    public final void setTailPosition$ktor_io(int i10) {
        this.tailPosition = i10;
    }

    public final ChunkBuffer stealAll$ktor_io() {
        ChunkBuffer chunkBuffer = this._head;
        if (chunkBuffer == null) {
            return null;
        }
        ChunkBuffer chunkBuffer2 = this._tail;
        if (chunkBuffer2 != null) {
            chunkBuffer2.commitWrittenUntilIndex(this.tailPosition);
        }
        this._head = null;
        this._tail = null;
        this.tailPosition = 0;
        this.tailEndExclusive = 0;
        this.tailInitialPosition = 0;
        this.chainedSize = 0;
        this.tailMemory = Memory.INSTANCE.m6762getEmptySK3TCg8();
        return chunkBuffer;
    }

    public final int write(int size, l<? super Buffer, Integer> block) {
        try {
            int iIntValue = ((Number) block.invoke(prepareWriteHead(size))).intValue();
            if (iIntValue >= 0) {
                return iIntValue;
            }
            throw new IllegalStateException("The returned value shouldn't be negative");
        } finally {
            afterHeadWrite();
        }
    }

    public final void writeByte(byte v2) throws InsufficientSpaceException {
        int i10 = this.tailPosition;
        if (i10 >= this.tailEndExclusive) {
            writeByteFallback(v2);
        } else {
            this.tailPosition = i10 + 1;
            this.tailMemory.put(i10, v2);
        }
    }

    public final void writeChunkBuffer$ktor_io(ChunkBuffer chunkBuffer) {
        ChunkBuffer chunkBuffer2 = this._tail;
        if (chunkBuffer2 == null) {
            appendChain$ktor_io(chunkBuffer);
        } else {
            writePacketMerging(chunkBuffer2, chunkBuffer, this.pool);
        }
    }

    public final void writePacket(ByteReadPacket packet) {
        ChunkBuffer chunkBufferStealAll$ktor_io = packet.stealAll$ktor_io();
        if (chunkBufferStealAll$ktor_io == null) {
            packet.release();
            return;
        }
        ChunkBuffer chunkBuffer = this._tail;
        if (chunkBuffer == null) {
            appendChain$ktor_io(chunkBufferStealAll$ktor_io);
        } else {
            writePacketMerging(chunkBuffer, chunkBufferStealAll$ktor_io, packet.getPool());
        }
    }

    public Output() {
        this(ChunkBuffer.INSTANCE.getPool());
    }

    @Override // java.lang.Appendable
    public Output append(char value) {
        int i10 = this.tailPosition;
        int i11 = 3;
        if (this.tailEndExclusive - i10 >= 3) {
            ByteBuffer byteBuffer = this.tailMemory;
            if (value >= 0 && value < 128) {
                byteBuffer.put(i10, (byte) value);
                i11 = 1;
            } else if (128 <= value && value < 2048) {
                byteBuffer.put(i10, (byte) (((value >> 6) & 31) | PsExtractor.AUDIO_STREAM));
                byteBuffer.put(i10 + 1, (byte) ((value & '?') | 128));
                i11 = 2;
            } else if (2048 <= value && value < 0) {
                byteBuffer.put(i10, (byte) (((value >> '\f') & 15) | 224));
                byteBuffer.put(i10 + 1, (byte) (((value >> 6) & 63) | 128));
                byteBuffer.put(i10 + 2, (byte) ((value & '?') | 128));
            } else {
                if (0 > value || value >= 0) {
                    UTF8Kt.malformedCodePoint(value);
                    throw new KotlinNothingValueException();
                }
                byteBuffer.put(i10, (byte) (((value >> 18) & 7) | PsExtractor.VIDEO_STREAM_MASK));
                byteBuffer.put(i10 + 1, (byte) (((value >> '\f') & 63) | 128));
                byteBuffer.put(i10 + 2, (byte) (((value >> 6) & 63) | 128));
                byteBuffer.put(i10 + 3, (byte) ((value & '?') | 128));
                i11 = 4;
            }
            this.tailPosition = i10 + i11;
            return this;
        }
        appendCharFallback(value);
        return this;
    }

    public final void writePacket(ByteReadPacket p10, int n6) throws EOFException {
        while (n6 > 0) {
            int headEndExclusive = p10.getHeadEndExclusive() - p10.getHeadPosition();
            if (headEndExclusive <= n6) {
                n6 -= headEndExclusive;
                ChunkBuffer chunkBufferSteal$ktor_io = p10.steal$ktor_io();
                if (chunkBufferSteal$ktor_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                appendSingleChunk$ktor_io(chunkBufferSteal$ktor_io);
            } else {
                ChunkBuffer chunkBufferPrepareRead = p10.prepareRead(1);
                if (chunkBufferPrepareRead != null) {
                    int readPosition = chunkBufferPrepareRead.getReadPosition();
                    try {
                        OutputKt.writeFully(this, chunkBufferPrepareRead, n6);
                        int readPosition2 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition2 >= readPosition) {
                            if (readPosition2 == chunkBufferPrepareRead.getWritePosition()) {
                                p10.ensureNext(chunkBufferPrepareRead);
                                return;
                            } else {
                                p10.setHeadPosition(readPosition2);
                                return;
                            }
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    } catch (Throwable th) {
                        int readPosition3 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition3 >= readPosition) {
                            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                                p10.ensureNext(chunkBufferPrepareRead);
                            } else {
                                p10.setHeadPosition(readPosition3);
                            }
                            throw th;
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                }
                throw b.i(1);
            }
        }
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence value) {
        if (value == null) {
            append("null", 0, 4);
            return this;
        }
        append(value, 0, value.length());
        return this;
    }

    @Override // java.lang.Appendable
    public Output append(CharSequence value, int startIndex, int endIndex) {
        if (value == null) {
            return append("null", startIndex, endIndex);
        }
        StringsKt.writeText(this, value, startIndex, endIndex, a.f19924a);
        return this;
    }

    public final Appendable append(char[] csq, int start, int end) {
        StringsKt.writeText(this, csq, start, end, a.f19924a);
        return this;
    }

    public final void writePacket(ByteReadPacket p10, long n6) throws EOFException {
        while (n6 > 0) {
            long headEndExclusive = p10.getHeadEndExclusive() - p10.getHeadPosition();
            if (headEndExclusive <= n6) {
                n6 -= headEndExclusive;
                ChunkBuffer chunkBufferSteal$ktor_io = p10.steal$ktor_io();
                if (chunkBufferSteal$ktor_io == null) {
                    throw new EOFException("Unexpected end of packet");
                }
                appendSingleChunk$ktor_io(chunkBufferSteal$ktor_io);
            } else {
                ChunkBuffer chunkBufferPrepareRead = p10.prepareRead(1);
                if (chunkBufferPrepareRead != null) {
                    int readPosition = chunkBufferPrepareRead.getReadPosition();
                    try {
                        OutputKt.writeFully(this, chunkBufferPrepareRead, (int) n6);
                        int readPosition2 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition2 >= readPosition) {
                            if (readPosition2 == chunkBufferPrepareRead.getWritePosition()) {
                                p10.ensureNext(chunkBufferPrepareRead);
                                return;
                            } else {
                                p10.setHeadPosition(readPosition2);
                                return;
                            }
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    } catch (Throwable th) {
                        int readPosition3 = chunkBufferPrepareRead.getReadPosition();
                        if (readPosition3 >= readPosition) {
                            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                                p10.ensureNext(chunkBufferPrepareRead);
                            } else {
                                p10.setHeadPosition(readPosition3);
                            }
                            throw th;
                        }
                        throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                    }
                }
                throw b.i(1);
            }
        }
    }
}
