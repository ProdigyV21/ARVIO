package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0004\b\b\u0010\t\u001a5\u0010\u000f\u001a\u00020\f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010\u001a=\u0010\u0011\u001a\u00020\f*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012\u001a!\u0010\u000f\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0013¢\u0006\u0004\b\u000f\u0010\u0014\u001a)\u0010\u0011\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0013¢\u0006\u0004\b\u0011\u0010\u0015\u001a\u001f\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a'\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aC\u0010\u001f\u001a\u00020\f*\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a3\u0010\u001f\u001a\u00020\f*\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u000b\u001a\u00020\u0013H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001f\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"Lio/ktor/utils/io/core/Input;", "", TtmlNode.RUBY_DELIMITER, "", "discardUntilDelimiter", "(Lio/ktor/utils/io/core/Input;B)J", "delimiter1", "delimiter2", "discardUntilDelimiters", "(Lio/ktor/utils/io/core/Input;BB)J", "", "dst", "", "offset", "length", "readUntilDelimiter", "(Lio/ktor/utils/io/core/Input;B[BII)I", "readUntilDelimiters", "(Lio/ktor/utils/io/core/Input;BB[BII)I", "Lio/ktor/utils/io/core/Output;", "(Lio/ktor/utils/io/core/Input;BLio/ktor/utils/io/core/Output;)J", "(Lio/ktor/utils/io/core/Input;BBLio/ktor/utils/io/core/Output;)J", "Lio/ktor/utils/io/core/Buffer;", "buffer", "discardUntilDelimiterImplMemory", "(Lio/ktor/utils/io/core/Buffer;B)I", "discardUntilDelimitersImplMemory", "(Lio/ktor/utils/io/core/Buffer;BB)I", "Lkotlin/Function1;", "", "predicate", "copyUntil", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;[BII)I", "(Lio/ktor/utils/io/core/Buffer;Lr7/l;Lio/ktor/utils/io/core/Output;)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScannerKt {
    public static final int copyUntil(Buffer buffer, l<? super Byte, Boolean> lVar, byte[] bArr, int i10, int i11) {
        int readPosition = buffer.getReadPosition();
        int iMin = Math.min(buffer.getWritePosition(), i11 + readPosition);
        ByteBuffer memory = buffer.getMemory();
        int i12 = readPosition;
        while (true) {
            if (i12 >= iMin) {
                break;
            }
            if (((Boolean) lVar.invoke(Byte.valueOf(memory.get(i12)))).booleanValue()) {
                iMin = i12;
                break;
            }
            i12++;
        }
        int i13 = iMin - readPosition;
        MemoryJvmKt.m6765copyTo9zorpBc(memory, bArr, readPosition, i13, i10);
        return i13;
    }

    public static final long discardUntilDelimiter(Input input, byte b10) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        long j10 = 0;
        if (chunkBufferPrepareReadFirstHead == null) {
            return 0L;
        }
        do {
            try {
                int iDiscardUntilDelimiterImpl = ScannerJVMKt.discardUntilDelimiterImpl(chunkBufferPrepareReadFirstHead, b10);
                j10 += (long) iDiscardUntilDelimiterImpl;
                if (iDiscardUntilDelimiterImpl <= 0 || chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    return j10;
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
        return j10;
    }

    public static final int discardUntilDelimiterImplMemory(Buffer buffer, byte b10) {
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        int i10 = readPosition;
        while (i10 < writePosition && memory.get(i10) != b10) {
            i10++;
        }
        buffer.discardUntilIndex$ktor_io(i10);
        return i10 - readPosition;
    }

    public static final long discardUntilDelimiters(Input input, byte b10, byte b11) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        long j10 = 0;
        if (chunkBufferPrepareReadFirstHead == null) {
            return 0L;
        }
        do {
            try {
                int iDiscardUntilDelimitersImpl = ScannerJVMKt.discardUntilDelimitersImpl(chunkBufferPrepareReadFirstHead, b10, b11);
                j10 += (long) iDiscardUntilDelimitersImpl;
                if (iDiscardUntilDelimitersImpl <= 0 || chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    return j10;
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
        return j10;
    }

    public static final int discardUntilDelimitersImplMemory(Buffer buffer, byte b10, byte b11) {
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        int i10 = readPosition;
        while (i10 < writePosition) {
            byte b12 = memory.get(i10);
            if (b12 == b10 || b12 == b11) {
                break;
            }
            i10++;
        }
        buffer.discardUntilIndex$ktor_io(i10);
        return i10 - readPosition;
    }

    public static final int readUntilDelimiter(Input input, byte b10, byte[] bArr, int i10, int i11) throws Throwable {
        int i12;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i10;
        } else {
            i12 = i10;
            do {
                try {
                    int untilDelimiterImpl = ScannerJVMKt.readUntilDelimiterImpl(chunkBufferPrepareReadFirstHead, b10, bArr, i12, i11);
                    i12 += untilDelimiterImpl;
                    i11 -= untilDelimiterImpl;
                    if (i11 <= 0 || chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
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
        }
        return i12 - i10;
    }

    public static /* synthetic */ int readUntilDelimiter$default(Input input, byte b10, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 0;
        }
        if ((i12 & 8) != 0) {
            i11 = bArr.length;
        }
        return readUntilDelimiter(input, b10, bArr, i10, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int readUntilDelimiters(io.ktor.utils.io.core.Input r8, byte r9, byte r10, byte[] r11, int r12, int r13) throws java.lang.Throwable {
        /*
            if (r9 != r10) goto L7
            int r8 = readUntilDelimiter(r8, r9, r11, r12, r13)
            return r8
        L7:
            r1 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r1)
            if (r0 != 0) goto L10
            r6 = r12
            goto L3b
        L10:
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
        L14:
            r7 = r13
            r2 = r0
            int r9 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimitersImpl(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L40
            r10 = r2
            r11 = r6
            r13 = r7
            int r6 = r11 + r9
            int r13 = r13 - r9
            int r9 = r10.getWritePosition()     // Catch: java.lang.Throwable -> L3d
            int r11 = r10.getReadPosition()     // Catch: java.lang.Throwable -> L3d
            if (r9 <= r11) goto L2b
            goto L38
        L2b:
            if (r13 <= 0) goto L38
            io.ktor.utils.io.core.internal.ChunkBuffer r0 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r10)     // Catch: java.lang.Throwable -> L34
            if (r0 != 0) goto L14
            goto L3b
        L34:
            r0 = move-exception
            r9 = r0
            r1 = 0
            goto L43
        L38:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r10)
        L3b:
            int r6 = r6 - r12
            return r6
        L3d:
            r0 = move-exception
        L3e:
            r9 = r0
            goto L43
        L40:
            r0 = move-exception
            r10 = r2
            goto L3e
        L43:
            if (r1 == 0) goto L48
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r10)
        L48:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiters(io.ktor.utils.io.core.Input, byte, byte, byte[], int, int):int");
    }

    public static /* synthetic */ int readUntilDelimiters$default(Input input, byte b10, byte b11, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            i10 = 0;
        }
        int i13 = i10;
        if ((i12 & 16) != 0) {
            i11 = bArr.length;
        }
        return readUntilDelimiters(input, b10, b11, bArr, i13, i11);
    }

    public static final long readUntilDelimiter(Input input, byte b10, Output output) {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        long untilDelimiterImpl = 0;
        if (chunkBufferPrepareReadFirstHead == null) {
            return 0L;
        }
        do {
            try {
                untilDelimiterImpl += (long) ScannerJVMKt.readUntilDelimiterImpl(chunkBufferPrepareReadFirstHead, b10, output);
                if (chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    return untilDelimiterImpl;
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
        return untilDelimiterImpl;
    }

    public static final int copyUntil(Buffer buffer, l<? super Byte, Boolean> lVar, Output output) {
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer memory = buffer.getMemory();
        while (readPosition != writePosition && !((Boolean) lVar.invoke(Byte.valueOf(memory.get(readPosition)))).booleanValue()) {
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    public static final long readUntilDelimiters(Input input, byte b10, byte b11, Output output) {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        long untilDelimitersImpl = 0;
        if (chunkBufferPrepareReadFirstHead == null) {
            return 0L;
        }
        do {
            try {
                untilDelimitersImpl += (long) ScannerJVMKt.readUntilDelimitersImpl(chunkBufferPrepareReadFirstHead, b10, b11, output);
                if (chunkBufferPrepareReadFirstHead.getWritePosition() > chunkBufferPrepareReadFirstHead.getReadPosition()) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    return untilDelimitersImpl;
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
        return untilDelimitersImpl;
    }
}
