package io.ktor.utils.io.core;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a1\u0010\b\u001a\u00060\u0006j\u0002`\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\t\u001a1\u0010\b\u001a\u00060\u0006j\u0002`\u0007*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\u000b\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0014\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00152\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0016\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00172\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0018\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u00192\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u001a\u001a-\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u001b2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u001c\u001a#\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u001d2\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u001e\u001a/\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!\u001a/\u0010\u0011\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\r\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\"2\u0006\u0010\u000f\u001a\u00020\"ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010#\u001a#\u0010'\u001a\u00020\u0010*\u00020\u00002\u0006\u0010$\u001a\u00020\"2\b\b\u0002\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(\u001a+\u0010,\u001a\u00020\u0010*\u00020\u00002\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020*0)H\u0080\bø\u0001\u0002¢\u0006\u0004\b,\u0010-\u001a5\u0010/\u001a\u00020\u0010*\u00020\u00002\b\b\u0002\u0010.\u001a\u00020\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00030)H\u0080\bø\u0001\u0002¢\u0006\u0004\b/\u00100\u001ab\u00106\u001a\u00020\u0010*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032<\u0010+\u001a8\u0012\u0004\u0012\u00020\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u001001H\u0082\b¢\u0006\u0004\b6\u00107\u001a\u0089\u0001\u00106\u001a\u00020\u0010*\u00020\u00002\u0006\u00108\u001a\u00020\"2\u0006\u0010\u000f\u001a\u00020\"2`\u0010+\u001a\\\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(:\u0012\u0013\u0012\u00110\"¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\"¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\"¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u001009H\u0082\bø\u0001\u0001¢\u0006\u0004\b6\u0010<\u001aj\u0010>\u001a\u00020\u0010*\u00020\u00002\u0006\u0010=\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032<\u0010+\u001a8\u0012\u0004\u0012\u00020\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(5\u0012\u0004\u0012\u00020\u001001H\u0082\b¢\u0006\u0004\b>\u0010?\u0082\u0002\u0012\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006@"}, d2 = {"Lio/ktor/utils/io/core/Output;", "", "csq", "", TtmlNode.START, TtmlNode.END, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "append", "(Lio/ktor/utils/io/core/Output;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "", "(Lio/ktor/utils/io/core/Output;[CII)Ljava/lang/Appendable;", "", "src", "offset", "length", "Lx6/t0;", "writeFully", "(Lio/ktor/utils/io/core/Output;[BII)V", "", "(Lio/ktor/utils/io/core/Output;[SII)V", "", "(Lio/ktor/utils/io/core/Output;[III)V", "", "(Lio/ktor/utils/io/core/Output;[JII)V", "", "(Lio/ktor/utils/io/core/Output;[FII)V", "", "(Lio/ktor/utils/io/core/Output;[DII)V", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Output;Lio/ktor/utils/io/core/Buffer;I)V", "Lio/ktor/utils/io/bits/Memory;", "writeFully-UAd2zVI", "(Lio/ktor/utils/io/core/Output;Ljava/nio/ByteBuffer;II)V", "", "(Lio/ktor/utils/io/core/Output;Ljava/nio/ByteBuffer;JJ)V", "times", "", "value", "fill", "(Lio/ktor/utils/io/core/Output;JB)V", "Lkotlin/Function1;", "", "block", "writeWhile", "(Lio/ktor/utils/io/core/Output;Lr7/l;)V", "initialSize", "writeWhileSize", "(Lio/ktor/utils/io/core/Output;ILr7/l;)V", "Lkotlin/Function3;", "Lx6/y;", ContentDisposition.Parameters.Name, "currentOffset", "count", "writeFullyBytesTemplate", "(Lio/ktor/utils/io/core/Output;IILr7/q;)V", "initialOffset", "Lkotlin/Function4;", "destination", "destinationOffset", "(Lio/ktor/utils/io/core/Output;JJLr7/r;)V", "componentSize", "writeFullyTemplate", "(Lio/ktor/utils/io/core/Output;IIILr7/q;)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutputKt {
    public static final Appendable append(Output output, CharSequence charSequence, int i10, int i11) {
        return output.append(charSequence, i10, i11);
    }

    public static /* synthetic */ Appendable append$default(Output output, CharSequence charSequence, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = charSequence.length();
        }
        return append(output, charSequence, i10, i11);
    }

    public static final void fill(Output output, long j10, byte b10) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        long j11 = 0;
        while (true) {
            try {
                int iMin = (int) Math.min(chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition(), j10 - j11);
                BufferCompatibilityKt.fill((Buffer) chunkBufferPrepareWriteHead, iMin, b10);
                j11 += (long) iMin;
                if (j11 >= j10) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void fill$default(Output output, long j10, byte b10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            b10 = 0;
        }
        fill(output, j10, b10);
    }

    public static final void writeFully(Output output, byte[] bArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, bArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                if (i11 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        writeFully(output, bArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-UAd2zVI, reason: not valid java name */
    public static final void m6961writeFullyUAd2zVI(Output output, ByteBuffer byteBuffer, int i10, int i11) {
        m6962writeFullyUAd2zVI(output, byteBuffer, i10, i11);
    }

    private static final void writeFullyBytesTemplate(Output output, int i10, int i11, q<? super Buffer, ? super Integer, ? super Integer, t0> qVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                qVar.invoke(chunkBufferPrepareWriteHead, Integer.valueOf(i10), Integer.valueOf(iMin));
                i10 += iMin;
                i11 -= iMin;
                if (i11 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    private static final void writeFullyTemplate(Output output, int i10, int i11, int i12, q<? super Buffer, ? super Integer, ? super Integer, t0> qVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i10, null);
        while (true) {
            try {
                int iMin = Math.min(i12, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                qVar.invoke(chunkBufferPrepareWriteHead, Integer.valueOf(i11), Integer.valueOf(iMin));
                i11 += iMin;
                i12 -= iMin;
                int i13 = i12 * i10;
                if (i13 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i13, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeWhile(Output output, l<? super Buffer, Boolean> lVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (((Boolean) lVar.invoke(chunkBufferPrepareWriteHead)).booleanValue()) {
            try {
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeWhileSize(Output output, int i10, l<? super Buffer, Integer> lVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i10, null);
        while (true) {
            try {
                int iIntValue = ((Number) lVar.invoke(chunkBufferPrepareWriteHead)).intValue();
                if (iIntValue <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, iIntValue, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeWhileSize$default(Output output, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 1;
        }
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i10, null);
        while (true) {
            try {
                int iIntValue = ((Number) lVar.invoke(chunkBufferPrepareWriteHead)).intValue();
                if (iIntValue <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, iIntValue, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final Appendable append(Output output, char[] cArr, int i10, int i11) {
        return output.append(cArr, i10, i11);
    }

    public static /* synthetic */ Appendable append$default(Output output, char[] cArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = cArr.length;
        }
        return append(output, cArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Output output, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        writeFully(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFully-UAd2zVI, reason: not valid java name */
    public static final void m6962writeFullyUAd2zVI(Output output, ByteBuffer byteBuffer, long j10, long j11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        long j12 = j10;
        while (true) {
            try {
                long jMin = Math.min(j11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                ByteBuffer byteBuffer2 = byteBuffer;
                Memory.m6748copyToJT6ljtQ(byteBuffer2, chunkBufferPrepareWriteHead.getMemory(), j12, jMin, chunkBufferPrepareWriteHead.getWritePosition());
                chunkBufferPrepareWriteHead.commitWritten((int) jMin);
                j12 += jMin;
                j11 -= jMin;
                if (j11 <= 0) {
                    return;
                }
                chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                byteBuffer = byteBuffer2;
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        writeFully(output, iArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Output output, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        writeFully(output, jArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Output output, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        writeFully(output, fArr, i10, i11);
    }

    public static /* synthetic */ void writeFully$default(Output output, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        writeFully(output, dArr, i10, i11);
    }

    public static final void writeFully(Output output, short[] sArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 2, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, sArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                int i12 = i11 * 2;
                if (i12 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static /* synthetic */ void writeFully$default(Output output, Buffer buffer, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = buffer.getWritePosition() - buffer.getReadPosition();
        }
        writeFully(output, buffer, i10);
    }

    private static final void writeFullyBytesTemplate(Output output, long j10, long j11, r<? super Memory, ? super Long, ? super Long, ? super Long, t0> rVar) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                long jMin = Math.min(j11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                rVar.invoke(Memory.m6745boximpl(chunkBufferPrepareWriteHead.getMemory()), Long.valueOf(chunkBufferPrepareWriteHead.getWritePosition()), Long.valueOf(j10), Long.valueOf(jMin));
                chunkBufferPrepareWriteHead.commitWritten((int) jMin);
                j10 += jMin;
                j11 -= jMin;
                if (j11 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeFully(Output output, int[] iArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 4, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, iArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                int i12 = i11 * 4;
                if (i12 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeFully(Output output, long[] jArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 8, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, jArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                int i12 = i11 * 8;
                if (i12 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeFully(Output output, float[] fArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 4, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully((Buffer) chunkBufferPrepareWriteHead, fArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                int i12 = i11 * 4;
                if (i12 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeFully(Output output, double[] dArr, int i10, int i11) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 8, null);
        while (true) {
            try {
                int iMin = Math.min(i11, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully(chunkBufferPrepareWriteHead, dArr, i10, iMin);
                i10 += iMin;
                i11 -= iMin;
                int i12 = i11 * 8;
                if (i12 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }

    public static final void writeFully(Output output, Buffer buffer, int i10) {
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, null);
        while (true) {
            try {
                int iMin = Math.min(i10, chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition());
                BufferPrimitivesKt.writeFully(chunkBufferPrepareWriteHead, buffer, iMin);
                i10 -= iMin;
                if (i10 <= 0) {
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, chunkBufferPrepareWriteHead);
                }
            } finally {
                output.afterHeadWrite();
            }
        }
    }
}
