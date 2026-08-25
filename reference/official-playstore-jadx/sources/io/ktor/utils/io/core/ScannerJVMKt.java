package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\u0005\u001a#\u0010\n\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\f\u0010\u000b\u001a3\u0010\u0011\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a3\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0013\u0010\u0012\u001a7\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0014\u0010\u0012\u001a;\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a;\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0017\u0010\u0016\u001a;\u0010\u0018\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0018\u0010\u0016\u001a#\u0010\u0011\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0011\u0010\u001a\u001a#\u0010\u0013\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0013\u0010\u001a\u001a#\u0010\u0014\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0014\u0010\u001a\u001a+\u0010\u0015\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0015\u0010\u001b\u001a+\u0010\u0017\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0017\u0010\u001b\u001a+\u0010\u0018\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u0018\u0010\u001b\u001a@\u0010 \u001a\u00020\u0003*\u00020\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b \u0010!\u001aH\u0010#\u001a\u00020\u0003*\u00020\u001c2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0003H\u0082\b¢\u0006\u0004\b#\u0010$\u001a0\u0010#\u001a\u00020\u0003*\u00020\u00002\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u000e\u001a\u00020\u0019H\u0082\b¢\u0006\u0004\b#\u0010%¨\u0006&"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", TtmlNode.RUBY_DELIMITER, "", "discardUntilDelimiterImpl", "(Lio/ktor/utils/io/core/Buffer;B)I", "buffer", "discardUntilDelimiterImplArrays", "delimiter1", "delimiter2", "discardUntilDelimitersImpl", "(Lio/ktor/utils/io/core/Buffer;BB)I", "discardUntilDelimitersImplArrays", "", "dst", "offset", "length", "readUntilDelimiterImpl", "(Lio/ktor/utils/io/core/Buffer;B[BII)I", "readUntilDelimiterDirect", "readUntilDelimiterArrays", "readUntilDelimitersImpl", "(Lio/ktor/utils/io/core/Buffer;BB[BII)I", "readUntilDelimitersDirect", "readUntilDelimitersArrays", "Lio/ktor/utils/io/core/Output;", "(Lio/ktor/utils/io/core/Buffer;BLio/ktor/utils/io/core/Output;)I", "(Lio/ktor/utils/io/core/Buffer;BBLio/ktor/utils/io/core/Output;)I", "Ljava/nio/ByteBuffer;", "Lkotlin/Function1;", "", "predicate", "copyUntilDirect", "(Ljava/nio/ByteBuffer;Lr7/l;[BII)I", "bufferOffset", "copyUntilArrays", "(Ljava/nio/ByteBuffer;Lr7/l;I[BII)I", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;Lio/ktor/utils/io/core/Output;)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScannerJVMKt {
    private static final int copyUntilArrays(ByteBuffer byteBuffer, l<? super Byte, Boolean> lVar, int i10, byte[] bArr, int i11, int i12) {
        int i13;
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position() + i10;
        int iMin = Math.min(i12, byteBuffer.remaining()) + iArrayOffset;
        if (iMin <= bArrArray.length) {
            i13 = iArrayOffset;
            while (i13 < iMin && !((Boolean) lVar.invoke(Byte.valueOf(bArrArray[i13]))).booleanValue()) {
                i13++;
            }
        } else {
            i13 = iArrayOffset;
        }
        int i14 = i13 - iArrayOffset;
        System.arraycopy(bArrArray, iArrayOffset, bArr, i11, i14);
        return i14;
    }

    private static final int copyUntilDirect(ByteBuffer byteBuffer, l<? super Byte, Boolean> lVar, byte[] bArr, int i10, int i11) {
        int iPosition = byteBuffer.position();
        int i12 = i11 + iPosition;
        int i13 = iPosition;
        while (i13 < byteBuffer.limit() && i13 < i12 && !((Boolean) lVar.invoke(Byte.valueOf(byteBuffer.get(i13)))).booleanValue()) {
            i13++;
        }
        int i14 = i13 - iPosition;
        byteBuffer.get(bArr, i10, i14);
        return i14;
    }

    public static final int discardUntilDelimiterImpl(Buffer buffer, byte b10) {
        return ByteBuffersKt.hasArray(buffer) ? discardUntilDelimiterImplArrays(buffer, b10) : ScannerKt.discardUntilDelimiterImplMemory(buffer, b10);
    }

    private static final int discardUntilDelimiterImplArrays(Buffer buffer, byte b10) {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        byte[] bArrArray = memory.array();
        int readPosition = buffer.getReadPosition() + memory.position() + memory.arrayOffset();
        int writePosition = (buffer.getWritePosition() - buffer.getReadPosition()) + readPosition;
        if (writePosition <= bArrArray.length) {
            i10 = readPosition;
            while (i10 < writePosition && bArrArray[i10] != b10) {
                i10++;
            }
        } else {
            i10 = readPosition;
        }
        buffer.discardUntilIndex$ktor_io(i10);
        return i10 - readPosition;
    }

    public static final int discardUntilDelimitersImpl(Buffer buffer, byte b10, byte b11) {
        return ByteBuffersKt.hasArray(buffer) ? discardUntilDelimitersImplArrays(buffer, b10, b11) : ScannerKt.discardUntilDelimitersImplMemory(buffer, b10, b11);
    }

    private static final int discardUntilDelimitersImplArrays(Buffer buffer, byte b10, byte b11) {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        byte[] bArrArray = memory.array();
        int readPosition = buffer.getReadPosition() + memory.position() + memory.arrayOffset();
        int writePosition = (buffer.getWritePosition() - buffer.getReadPosition()) + readPosition;
        if (writePosition <= bArrArray.length) {
            i10 = readPosition;
            while (i10 < writePosition) {
                byte b12 = bArrArray[i10];
                if (b12 == b10 || b12 == b11) {
                    break;
                }
                i10++;
            }
        } else {
            i10 = readPosition;
        }
        buffer.discardUntilIndex$ktor_io(i10);
        return i10 - readPosition;
    }

    private static final int readUntilDelimiterArrays(Buffer buffer, byte b10, byte[] bArr, int i10, int i11) {
        int i12;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int iMin = Math.min(i11, buffer.getWritePosition() - buffer.getReadPosition());
        byte[] bArrArray = memory.array();
        int iArrayOffset = memory.arrayOffset() + memory.position() + readPosition;
        int iMin2 = Math.min(iMin, memory.remaining()) + iArrayOffset;
        if (iMin2 <= bArrArray.length) {
            i12 = iArrayOffset;
            while (i12 < iMin2 && bArrArray[i12] != b10) {
                i12++;
            }
        } else {
            i12 = iArrayOffset;
        }
        int i13 = i12 - iArrayOffset;
        System.arraycopy(bArrArray, iArrayOffset, bArr, i10, i13);
        buffer.discardExact(i13);
        return i13;
    }

    private static final int readUntilDelimiterDirect(Buffer buffer, byte b10, byte[] bArr, int i10, int i11) {
        int readPosition = buffer.getReadPosition();
        int iMin = Math.min(buffer.getWritePosition(), i11 + readPosition);
        ByteBuffer memory = buffer.getMemory();
        int i12 = readPosition;
        while (true) {
            if (i12 >= iMin) {
                break;
            }
            if (memory.get(i12) == b10) {
                iMin = i12;
                break;
            }
            i12++;
        }
        int i13 = iMin - readPosition;
        MemoryJvmKt.m6765copyTo9zorpBc(memory, bArr, readPosition, i13, i10);
        buffer.discardExact(i13);
        return i13;
    }

    public static final int readUntilDelimiterImpl(Buffer buffer, byte b10, byte[] bArr, int i10, int i11) {
        return ByteBuffersKt.hasArray(buffer) ? readUntilDelimiterArrays(buffer, b10, bArr, i10, i11) : readUntilDelimiterDirect(buffer, b10, bArr, i10, i11);
    }

    private static final int readUntilDelimitersArrays(Buffer buffer, byte b10, byte b11, byte[] bArr, int i10, int i11) {
        int i12;
        ByteBuffer memory = buffer.getMemory();
        int readPosition = buffer.getReadPosition();
        int iMin = Math.min(i11, buffer.getWritePosition() - buffer.getReadPosition());
        byte[] bArrArray = memory.array();
        int iArrayOffset = memory.arrayOffset() + memory.position() + readPosition;
        int iMin2 = Math.min(iMin, memory.remaining()) + iArrayOffset;
        if (iMin2 <= bArrArray.length) {
            i12 = iArrayOffset;
            while (i12 < iMin2) {
                byte b12 = bArrArray[i12];
                if (b12 == b10 || b12 == b11) {
                    break;
                }
                i12++;
            }
        } else {
            i12 = iArrayOffset;
        }
        int i13 = i12 - iArrayOffset;
        System.arraycopy(bArrArray, iArrayOffset, bArr, i10, i13);
        buffer.discardExact(i13);
        return i13;
    }

    private static final int readUntilDelimitersDirect(Buffer buffer, byte b10, byte b11, byte[] bArr, int i10, int i11) {
        int readPosition = buffer.getReadPosition();
        int iMin = Math.min(buffer.getWritePosition(), i11 + readPosition);
        ByteBuffer memory = buffer.getMemory();
        for (int i12 = readPosition; i12 < iMin; i12++) {
            byte b12 = memory.get(i12);
            if (b12 == b10 || b12 == b11) {
                iMin = i12;
                break;
            }
        }
        int i13 = iMin - readPosition;
        MemoryJvmKt.m6765copyTo9zorpBc(memory, bArr, readPosition, i13, i10);
        buffer.discardExact(i13);
        return i13;
    }

    public static final int readUntilDelimitersImpl(Buffer buffer, byte b10, byte b11, byte[] bArr, int i10, int i11) {
        return ByteBuffersKt.hasArray(buffer) ? readUntilDelimitersArrays(buffer, b10, b11, bArr, i10, i11) : readUntilDelimitersDirect(buffer, b10, b11, bArr, i10, i11);
    }

    public static final int readUntilDelimiterImpl(Buffer buffer, byte b10, Output output) {
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimiterArrays(buffer, b10, output);
        }
        return readUntilDelimiterDirect(buffer, b10, output);
    }

    public static final int readUntilDelimitersImpl(Buffer buffer, byte b10, byte b11, Output output) {
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimitersArrays(buffer, b10, b11, output);
        }
        return readUntilDelimitersDirect(buffer, b10, b11, output);
    }

    /* JADX WARN: Finally extract failed */
    private static final int copyUntilArrays(Buffer buffer, l<? super Byte, Boolean> lVar, Output output) {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        byte[] bArrArray = memory.array();
        int readPosition = buffer.getReadPosition() + memory.arrayOffset() + memory.position();
        int writePosition = buffer.getWritePosition() + memory.arrayOffset() + memory.position();
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i11 = 0;
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) + readPosition, writePosition);
                if (iMin <= bArrArray.length) {
                    i10 = readPosition;
                    while (i10 < iMin && !((Boolean) lVar.invoke(Byte.valueOf(bArrArray[i10]))).booleanValue()) {
                        i10++;
                    }
                } else {
                    i10 = readPosition;
                }
                int i12 = i10 - readPosition;
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, bArrArray, readPosition, i12);
                i11 += i12;
                if (chunkBufferPrepareWriteHead.getLimit() > chunkBufferPrepareWriteHead.getWritePosition() || i10 >= writePosition) {
                    break;
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                readPosition = i10;
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
        output.afterHeadWrite();
        buffer.discardUntilIndex$ktor_io(i10);
        return i11;
    }

    public static final int readUntilDelimiterDirect(Buffer buffer, byte b10, Output output) {
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        while (readPosition != writePosition && memory.get(readPosition) != b10) {
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    public static final int readUntilDelimitersDirect(Buffer buffer, byte b10, byte b11, Output output) {
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        while (readPosition != writePosition) {
            byte b12 = memory.get(readPosition);
            if (b12 == b10 || b12 == b11) {
                break;
            }
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    /* JADX WARN: Finally extract failed */
    public static final int readUntilDelimiterArrays(Buffer buffer, byte b10, Output output) {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        byte[] bArrArray = memory.array();
        int readPosition = buffer.getReadPosition() + memory.arrayOffset() + memory.position();
        int writePosition = buffer.getWritePosition() + memory.arrayOffset() + memory.position();
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i11 = 0;
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) + readPosition, writePosition);
                if (iMin <= bArrArray.length) {
                    i10 = readPosition;
                    while (i10 < iMin && bArrArray[i10] != b10) {
                        i10++;
                    }
                } else {
                    i10 = readPosition;
                }
                int i12 = i10 - readPosition;
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, bArrArray, readPosition, i12);
                i11 += i12;
                if (chunkBufferPrepareWriteHead.getLimit() > chunkBufferPrepareWriteHead.getWritePosition() || i10 >= writePosition) {
                    break;
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                readPosition = i10;
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
        output.afterHeadWrite();
        buffer.discardUntilIndex$ktor_io(i10);
        return i11;
    }

    /* JADX WARN: Finally extract failed */
    public static final int readUntilDelimitersArrays(Buffer buffer, byte b10, byte b11, Output output) {
        int i10;
        ByteBuffer memory = buffer.getMemory();
        byte[] bArrArray = memory.array();
        int readPosition = buffer.getReadPosition() + memory.arrayOffset() + memory.position();
        int writePosition = buffer.getWritePosition() + memory.arrayOffset() + memory.position();
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        int i11 = 0;
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) + readPosition, writePosition);
                if (iMin <= bArrArray.length) {
                    i10 = readPosition;
                    while (i10 < iMin) {
                        byte b12 = bArrArray[i10];
                        if (b12 == b10 || b12 == b11) {
                            break;
                        }
                        i10++;
                    }
                } else {
                    i10 = readPosition;
                }
                int i12 = i10 - readPosition;
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, bArrArray, readPosition, i12);
                i11 += i12;
                if (chunkBufferPrepareWriteHead.getLimit() > chunkBufferPrepareWriteHead.getWritePosition() || i10 >= writePosition) {
                    break;
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                readPosition = i10;
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
        output.afterHeadWrite();
        buffer.discardUntilIndex$ktor_io(i10);
        return i11;
    }
}
