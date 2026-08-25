package io.ktor.utils.io.core;

import g8.b;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\n\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\f\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\u000e\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\u0010\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00112\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\u0012\u001a#\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00132\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\u0014\u001a/\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a/\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u001aø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001d\u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\t2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001e\u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001f\u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010 \u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000f2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010!\u001a-\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00112\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\"\u001a#\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00132\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010#\u001a/\u0010\u001c\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001a/\u0010\u001c\u001a\u00020\u001a*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u001aø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010&\u001aq\u0010/\u001a\u00020\u0003*\u00020\u00002\u0006\u0010'\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032K\u0010.\u001aG\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00060(H\u0082\b¢\u0006\u0004\b/\u00100\u001a\u0089\u0001\u0010/\u001a\u00020\u001a*\u00020\u00002\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u001a2`\u0010.\u001a\\\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(2\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u000601H\u0082\bø\u0001\u0001¢\u0006\u0004\b/\u00103\u001ay\u00105\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u00032K\u0010.\u001aG\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00060(H\u0082\b¢\u0006\u0004\b5\u00106\u001a\u0014\u00107\u001a\u00020\u0006*\u00020\u0003H\u0082\b¢\u0006\u0004\b7\u00108\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00069"}, d2 = {"Lio/ktor/utils/io/core/Input;", "", "dst", "", "offset", "length", "Lx6/t0;", "readFully", "(Lio/ktor/utils/io/core/Input;[BII)V", "", "(Lio/ktor/utils/io/core/Input;[SII)V", "", "(Lio/ktor/utils/io/core/Input;[III)V", "", "(Lio/ktor/utils/io/core/Input;[JII)V", "", "(Lio/ktor/utils/io/core/Input;[FII)V", "", "(Lio/ktor/utils/io/core/Input;[DII)V", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/Buffer;I)V", "Lio/ktor/utils/io/bits/Memory;", "destination", "destinationOffset", "readFully-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)V", "", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)V", "readAvailable", "(Lio/ktor/utils/io/core/Input;[BII)I", "(Lio/ktor/utils/io/core/Input;[SII)I", "(Lio/ktor/utils/io/core/Input;[III)I", "(Lio/ktor/utils/io/core/Input;[JII)I", "(Lio/ktor/utils/io/core/Input;[FII)I", "(Lio/ktor/utils/io/core/Input;[DII)I", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/Buffer;I)I", "readAvailable-UAd2zVI", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;II)I", "(Lio/ktor/utils/io/core/Input;Ljava/nio/ByteBuffer;JJ)J", "initialDstOffset", "Lkotlin/Function3;", "Lx6/y;", ContentDisposition.Parameters.Name, "src", "dstOffset", "count", "readBlock", "readFullyBytesTemplate", "(Lio/ktor/utils/io/core/Input;IILr7/q;)I", "Lkotlin/Function4;", "srcOffset", "(Lio/ktor/utils/io/core/Input;JJLr7/r;)J", "componentSize", "readFullyTemplate", "(Lio/ktor/utils/io/core/Input;IIILr7/q;)I", "requireNoRemaining", "(I)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputArraysKt {
    public static final int readAvailable(Input input, byte[] bArr, int i10, int i11) {
        int i12;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            while (true) {
                try {
                    int iMin = Math.min(i12, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                    BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, bArr, i10, iMin);
                    i12 -= iMin;
                    i10 += iMin;
                    if (i12 <= 0) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                    try {
                        chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        if (chunkBufferPrepareReadFirstHead == null) {
                            break;
                        }
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
            }
        }
        return i11 - i12;
    }

    public static /* synthetic */ int readAvailable$default(Input input, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        return readAvailable(input, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-UAd2zVI, reason: not valid java name */
    public static final int m6931readAvailableUAd2zVI(Input input, ByteBuffer byteBuffer, int i10, int i11) {
        return (int) m6932readAvailableUAd2zVI(input, byteBuffer, i10, i11);
    }

    public static final void readFully(Input input, byte[] bArr, int i10, int i11) {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            while (true) {
                try {
                    int iMin = Math.min(i11, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                    BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, bArr, i10, iMin);
                    i11 -= iMin;
                    i10 += iMin;
                    if (i11 <= 0) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                    try {
                        chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        if (chunkBufferPrepareReadFirstHead == null) {
                            break;
                        }
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
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static /* synthetic */ void readFully$default(Input input, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        readFully(input, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-UAd2zVI, reason: not valid java name */
    public static final void m6933readFullyUAd2zVI(Input input, ByteBuffer byteBuffer, int i10, int i11) throws EOFException {
        m6934readFullyUAd2zVI(input, byteBuffer, i10, i11);
    }

    private static final int readFullyBytesTemplate(Input input, int i10, int i11, q<? super Buffer, ? super Integer, ? super Integer, t0> qVar) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return i11;
        }
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                qVar.invoke(chunkBufferPrepareReadFirstHead, Integer.valueOf(i10), Integer.valueOf(iMin));
                i11 -= iMin;
                i10 += iMin;
                if (i11 <= 0) {
                    break;
                }
                try {
                    ChunkBuffer chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
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
        }
        if (z) {
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        }
        return i11;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int readFullyTemplate(io.ktor.utils.io.core.Input r6, int r7, int r8, int r9, r7.q<? super io.ktor.utils.io.core.Buffer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r10) throws java.lang.Throwable {
        /*
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r0)
            if (r1 != 0) goto L8
            return r8
        L8:
            r2 = r0
        L9:
            int r3 = r1.getWritePosition()     // Catch: java.lang.Throwable -> L3f
            int r4 = r1.getReadPosition()     // Catch: java.lang.Throwable -> L3f
            int r3 = r3 - r4
            r4 = 0
            if (r3 < r2) goto L49
            int r2 = r1.getWritePosition()     // Catch: java.lang.Throwable -> L41
            int r3 = r1.getReadPosition()     // Catch: java.lang.Throwable -> L41
            int r2 = r2 - r3
            int r2 = r2 / r9
            int r2 = java.lang.Math.min(r8, r2)     // Catch: java.lang.Throwable -> L41
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L41
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L41
            r10.invoke(r1, r3, r5)     // Catch: java.lang.Throwable -> L41
            int r8 = r8 - r2
            int r7 = r7 + r2
            if (r8 <= 0) goto L34
            r2 = r9
            goto L35
        L34:
            r2 = r4
        L35:
            int r3 = r1.getWritePosition()     // Catch: java.lang.Throwable -> L3f
            int r5 = r1.getReadPosition()     // Catch: java.lang.Throwable -> L3f
            int r3 = r3 - r5
            goto L49
        L3f:
            r7 = move-exception
            goto L79
        L41:
            r7 = move-exception
            r1.getWritePosition()     // Catch: java.lang.Throwable -> L3f
            r1.getReadPosition()     // Catch: java.lang.Throwable -> L3f
            throw r7     // Catch: java.lang.Throwable -> L3f
        L49:
            if (r3 != 0) goto L53
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r6, r1)     // Catch: java.lang.Throwable -> L50
            goto L6c
        L50:
            r7 = move-exception
            r0 = r4
            goto L79
        L53:
            if (r3 < r2) goto L65
            int r3 = r1.getCapacity()     // Catch: java.lang.Throwable -> L50
            int r5 = r1.getLimit()     // Catch: java.lang.Throwable -> L50
            int r3 = r3 - r5
            r5 = 8
            if (r3 >= r5) goto L63
            goto L65
        L63:
            r3 = r1
            goto L6c
        L65:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)     // Catch: java.lang.Throwable -> L50
            io.ktor.utils.io.core.internal.ChunkBuffer r3 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r6, r2)     // Catch: java.lang.Throwable -> L50
        L6c:
            if (r3 != 0) goto L70
            r0 = r4
            goto L73
        L70:
            r1 = r3
            if (r2 > 0) goto L9
        L73:
            if (r0 == 0) goto L78
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L78:
            return r8
        L79:
            if (r0 == 0) goto L7e
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r6, r1)
        L7e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysKt.readFullyTemplate(io.ktor.utils.io.core.Input, int, int, int, r7.q):int");
    }

    private static final void requireNoRemaining(int i10) {
        if (i10 > 0) {
            throw b.i(i10);
        }
    }

    public static /* synthetic */ int readAvailable$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return readAvailable(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailable-UAd2zVI, reason: not valid java name */
    public static final long m6932readAvailableUAd2zVI(Input input, ByteBuffer byteBuffer, long j10, long j11) throws Throwable {
        long j12;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            j12 = j11;
        } else {
            long j13 = j10;
            j12 = j11;
            ChunkBuffer chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
            while (true) {
                try {
                    int iMin = (int) Math.min(j12, chunkBufferPrepareReadNextHead.getWritePosition() - chunkBufferPrepareReadNextHead.getReadPosition());
                    long j14 = iMin;
                    Memory.m6748copyToJT6ljtQ(chunkBufferPrepareReadNextHead.getMemory(), byteBuffer, chunkBufferPrepareReadNextHead.getReadPosition(), j14, j13);
                    chunkBufferPrepareReadNextHead.discardExact(iMin);
                    j12 -= j14;
                    j13 += j14;
                    if (j12 <= 0) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadNextHead);
                        break;
                    }
                    try {
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadNextHead);
                        if (chunkBufferPrepareReadNextHead == null) {
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadNextHead);
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        long j15 = j11 - j12;
        if (j15 == 0 && input.getEndOfInput()) {
            return -1L;
        }
        return j15;
    }

    public static /* synthetic */ void readFully$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        readFully(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFully-UAd2zVI, reason: not valid java name */
    public static final void m6934readFullyUAd2zVI(Input input, ByteBuffer byteBuffer, long j10, long j11) throws EOFException {
        if (m6932readAvailableUAd2zVI(input, byteBuffer, j10, j11) == j11) {
            return;
        }
        StringsKt.prematureEndOfStream(j11);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ int readAvailable$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return readAvailable(input, iArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        readFully(input, iArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return readAvailable(input, jArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        readFully(input, jArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Input input, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        return readAvailable(input, fArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Input input, float[] fArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        readFully(input, fArr, i10, i11);
    }

    public static /* synthetic */ int readAvailable$default(Input input, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        return readAvailable(input, dArr, i10, i11);
    }

    public static /* synthetic */ void readFully$default(Input input, double[] dArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        readFully(input, dArr, i10, i11);
    }

    public static final int readAvailable(Input input, short[] sArr, int i10, int i11) throws Throwable {
        int i12;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            int i13 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i13) {
                        try {
                            int iMin = Math.min(i12, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 2);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, sArr, i10, iMin);
                            i12 -= iMin;
                            i10 += iMin;
                            i13 = i12 > 0 ? 2 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i13 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i13);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i13 <= 0) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        return i11 - i12;
    }

    public static /* synthetic */ int readAvailable$default(Input input, Buffer buffer, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = buffer.getLimit() - buffer.getWritePosition();
        }
        return readAvailable(input, buffer, i10);
    }

    public static /* synthetic */ void readFully$default(Input input, Buffer buffer, int i10, int i11, Object obj) throws Throwable {
        if ((i11 & 2) != 0) {
            i10 = buffer.getLimit() - buffer.getWritePosition();
        }
        readFully(input, buffer, i10);
    }

    private static final long readFullyBytesTemplate(Input input, long j10, long j11, r<? super Memory, ? super Long, ? super Long, ? super Integer, t0> rVar) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return j11;
        }
        while (true) {
            try {
                int iMin = (int) Math.min(j11, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                rVar.invoke(Memory.m6745boximpl(chunkBufferPrepareReadFirstHead.getMemory()), Long.valueOf(chunkBufferPrepareReadFirstHead.getReadPosition()), Long.valueOf(j10), Integer.valueOf(iMin));
                chunkBufferPrepareReadFirstHead.discardExact(iMin);
                long j12 = iMin;
                j11 -= j12;
                j10 += j12;
                if (j11 <= 0) {
                    break;
                }
                try {
                    ChunkBuffer chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
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
        }
        if (z) {
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        }
        return j11;
    }

    public static final void readFully(Input input, short[] sArr, int i10, int i11) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            int i12 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i12) {
                        try {
                            int iMin = Math.min(i11, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 2);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, sArr, i10, iMin);
                            i11 -= iMin;
                            i10 += iMin;
                            i12 = i11 > 0 ? 2 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i12 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i12);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    } else {
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        if (i12 <= 0) {
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static final int readAvailable(Input input, int[] iArr, int i10, int i11) throws Throwable {
        int i12;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            int i13 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i13) {
                        try {
                            int iMin = Math.min(i12, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 4);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, iArr, i10, iMin);
                            i12 -= iMin;
                            i10 += iMin;
                            i13 = i12 > 0 ? 4 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i13 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i13);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i13 <= 0) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        return i11 - i12;
    }

    public static final int readAvailable(Input input, long[] jArr, int i10, int i11) throws Throwable {
        int i12;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            int i13 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i13) {
                        try {
                            int iMin = Math.min(i12, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 8);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, jArr, i10, iMin);
                            i12 -= iMin;
                            i10 += iMin;
                            i13 = i12 > 0 ? 8 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i13 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i13);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i13 <= 0) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        return i11 - i12;
    }

    public static final void readFully(Input input, int[] iArr, int i10, int i11) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            int i12 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i12) {
                        try {
                            int iMin = Math.min(i11, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 4);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, iArr, i10, iMin);
                            i11 -= iMin;
                            i10 += iMin;
                            i12 = i11 > 0 ? 4 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i12 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i12);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    } else {
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        if (i12 <= 0) {
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static final int readAvailable(Input input, float[] fArr, int i10, int i11) throws Throwable {
        int i12;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            int i13 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i13) {
                        try {
                            int iMin = Math.min(i12, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 4);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, fArr, i10, iMin);
                            i12 -= iMin;
                            i10 += iMin;
                            i13 = i12 > 0 ? 4 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i13 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i13);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i13 <= 0) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        return i11 - i12;
    }

    public static final void readFully(Input input, long[] jArr, int i10, int i11) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            int i12 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i12) {
                        try {
                            int iMin = Math.min(i11, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 8);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, jArr, i10, iMin);
                            i11 -= iMin;
                            i10 += iMin;
                            i12 = i11 > 0 ? 8 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i12 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i12);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    } else {
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        if (i12 <= 0) {
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static final int readAvailable(Input input, double[] dArr, int i10, int i11) throws Throwable {
        int i12;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i12 = i11;
        } else {
            i12 = i11;
            int i13 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i13) {
                        try {
                            int iMin = Math.min(i12, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 8);
                            BufferPrimitivesKt.readFully(chunkBufferPrepareReadFirstHead, dArr, i10, iMin);
                            i12 -= iMin;
                            i10 += iMin;
                            i13 = i12 > 0 ? 8 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i13 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i13);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i13 <= 0) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        return i11 - i12;
    }

    public static final int readAvailable(Input input, Buffer buffer, int i10) throws Throwable {
        int i11;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            i11 = i10;
        } else {
            i11 = i10;
            while (true) {
                try {
                    int iMin = Math.min(i11, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                    BufferPrimitivesKt.readFully(chunkBufferPrepareReadFirstHead, buffer, iMin);
                    i11 -= iMin;
                    if (i11 > 0) {
                        try {
                            chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                            if (chunkBufferPrepareReadFirstHead == null) {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        return i10 - i11;
    }

    public static final void readFully(Input input, float[] fArr, int i10, int i11) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            int i12 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i12) {
                        try {
                            int iMin = Math.min(i11, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 4);
                            BufferPrimitivesKt.readFully((Buffer) chunkBufferPrepareReadFirstHead, fArr, i10, iMin);
                            i11 -= iMin;
                            i10 += iMin;
                            i12 = i11 > 0 ? 4 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i12 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i12);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    } else {
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        if (i12 <= 0) {
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static final void readFully(Input input, double[] dArr, int i10, int i11) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            int i12 = 1;
            while (true) {
                try {
                    int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    if (writePosition >= i12) {
                        try {
                            int iMin = Math.min(i11, (chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition()) / 8);
                            BufferPrimitivesKt.readFully(chunkBufferPrepareReadFirstHead, dArr, i10, iMin);
                            i11 -= iMin;
                            i10 += iMin;
                            i12 = i11 > 0 ? 8 : 0;
                            writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                        } finally {
                        }
                    }
                    if (writePosition == 0) {
                        try {
                            chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else if (writePosition < i12 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i12);
                    } else {
                        chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                    }
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    } else {
                        chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                        if (i12 <= 0) {
                            break;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (z) {
                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
            }
        }
        if (i11 > 0) {
            throw b.i(i11);
        }
    }

    public static final void readFully(Input input, Buffer buffer, int i10) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead != null) {
            while (true) {
                try {
                    int iMin = Math.min(i10, chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition());
                    BufferPrimitivesKt.readFully(chunkBufferPrepareReadFirstHead, buffer, iMin);
                    i10 -= iMin;
                    if (i10 > 0) {
                        try {
                            chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                            if (chunkBufferPrepareReadFirstHead == null) {
                                break;
                            }
                        } catch (Throwable th) {
                            th = th;
                            z = false;
                            if (z) {
                                UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                            }
                            throw th;
                        }
                    } else {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        if (i10 > 0) {
            throw b.i(i10);
        }
    }
}
