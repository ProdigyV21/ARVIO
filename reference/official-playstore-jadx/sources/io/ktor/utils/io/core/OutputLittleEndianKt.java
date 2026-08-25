package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\t\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\n\u001a!\u0010\f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\r\u001a!\u0010\u000f\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010\u001a!\u0010\u0012\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0019\u0010\u0014\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0019\u0010\u0016\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0019\u0010\u0018\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000b¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000e¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0019\u0010\u001c\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0011¢\u0006\u0004\b\u001c\u0010\u001d\u001a3\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010#\u001a\u0019\u0010\u0014\u001a\u00020\u0005*\u00020%2\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0014\u0010&\u001a\u0019\u0010\u0016\u001a\u00020\u0005*\u00020%2\u0006\u0010\u0002\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010'\u001a\u0019\u0010\u0018\u001a\u00020\u0005*\u00020%2\u0006\u0010\u0002\u001a\u00020\u000b¢\u0006\u0004\b\u0018\u0010(\u001a\u0019\u0010\u001a\u001a\u00020\u0005*\u00020%2\u0006\u0010\u0002\u001a\u00020\u000e¢\u0006\u0004\b\u001a\u0010)\u001a\u0019\u0010\u001c\u001a\u00020\u0005*\u00020%2\u0006\u0010\u0002\u001a\u00020\u0011¢\u0006\u0004\b\u001c\u0010*\u001a3\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010+\u001a-\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u00020,2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010#\u001a3\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u00020-2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/\u001a-\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u0002002\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010/\u001a3\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u0002012\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00103\u001a-\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u0002042\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u00103\u001a-\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u0002052\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u00106\u001a-\u0010$\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u001f\u001a\u0002072\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u00108\u001a-\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u00020,2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010+\u001a3\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u00020-2\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u00109\u001a-\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u0002002\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u00109\u001a3\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u0002012\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u0010:\u001a-\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u0002042\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010:\u001a-\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u0002052\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010;\u001a-\u0010$\u001a\u00020\u0005*\u00020%2\u0006\u0010\u001f\u001a\u0002072\b\b\u0002\u0010 \u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020\b¢\u0006\u0004\b$\u0010<\u001aO\u0010C\u001a\u00020\u0005\"\b\b\u0000\u0010>*\u00020=2\u0006\u0010\u0002\u001a\u00028\u00002\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050?2\u0017\u0010B\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000?¢\u0006\u0002\bAH\u0082\b¢\u0006\u0004\bC\u0010D\u001aW\u0010C\u001a\u00020\u0005\"\b\b\u0000\u0010>*\u00020=2\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050?2\u0017\u0010B\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000?¢\u0006\u0002\bAH\u0082\b¢\u0006\u0004\bC\u0010E\u001aK\u0010I\u001a\u00020\u0005*\u00020\u00002\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u001d\u0010H\u001a\u0019\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050G¢\u0006\u0002\bAH\u0082\b¢\u0006\u0004\bI\u0010J\u001aK\u0010I\u001a\u00020\u0005*\u00020%2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b2\u001d\u0010H\u001a\u0019\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050G¢\u0006\u0002\bAH\u0082\b¢\u0006\u0004\bI\u0010K\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006L"}, d2 = {"Lio/ktor/utils/io/core/Output;", "", "value", "Lio/ktor/utils/io/core/ByteOrder;", "byteOrder", "Lx6/t0;", "writeShort", "(Lio/ktor/utils/io/core/Output;SLio/ktor/utils/io/core/ByteOrder;)V", "", "writeInt", "(Lio/ktor/utils/io/core/Output;ILio/ktor/utils/io/core/ByteOrder;)V", "", "writeLong", "(Lio/ktor/utils/io/core/Output;JLio/ktor/utils/io/core/ByteOrder;)V", "", "writeFloat", "(Lio/ktor/utils/io/core/Output;FLio/ktor/utils/io/core/ByteOrder;)V", "", "writeDouble", "(Lio/ktor/utils/io/core/Output;DLio/ktor/utils/io/core/ByteOrder;)V", "writeShortLittleEndian", "(Lio/ktor/utils/io/core/Output;S)V", "writeIntLittleEndian", "(Lio/ktor/utils/io/core/Output;I)V", "writeLongLittleEndian", "(Lio/ktor/utils/io/core/Output;J)V", "writeFloatLittleEndian", "(Lio/ktor/utils/io/core/Output;F)V", "writeDoubleLittleEndian", "(Lio/ktor/utils/io/core/Output;D)V", "Lx6/s0;", "source", "offset", "length", "writeFullyLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Output;[SII)V", "writeFullyLittleEndian", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;S)V", "(Lio/ktor/utils/io/core/Buffer;I)V", "(Lio/ktor/utils/io/core/Buffer;J)V", "(Lio/ktor/utils/io/core/Buffer;F)V", "(Lio/ktor/utils/io/core/Buffer;D)V", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "", "Lx6/n0;", "writeFullyLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Output;[III)V", "", "Lx6/p0;", "writeFullyLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Output;[JII)V", "", "", "(Lio/ktor/utils/io/core/Output;[FII)V", "", "(Lio/ktor/utils/io/core/Output;[DII)V", "(Lio/ktor/utils/io/core/Buffer;[III)V", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "(Lio/ktor/utils/io/core/Buffer;[FII)V", "(Lio/ktor/utils/io/core/Buffer;[DII)V", "", "T", "Lkotlin/Function1;", "write", "Lx6/n;", "reverse", "writePrimitiveTemplate", "(Ljava/lang/Object;Lr7/l;Lr7/l;)V", "(Ljava/lang/Object;Lio/ktor/utils/io/core/ByteOrder;Lr7/l;Lr7/l;)V", "componentSize", "Lkotlin/Function2;", "writeComponent", "writeArrayTemplate", "(Lio/ktor/utils/io/core/Output;IIILr7/p;)V", "(Lio/ktor/utils/io/core/Buffer;IIILr7/p;)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutputLittleEndianKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ByteOrder.values().length];
            try {
                iArr[ByteOrder.BIG_ENDIAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final void writeArrayTemplate(Output output, int i10, int i11, int i12, p<? super Buffer, ? super Integer, t0> pVar) {
        int i13 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i12, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / i12, i13 - i10) + i10;
                int i14 = iMin - 1;
                if (i10 <= i14) {
                    while (true) {
                        pVar.invoke(chunkBufferPrepareWriteHead, Integer.valueOf(i10));
                        if (i10 == i14) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i15 = iMin < i13 ? i12 : 0;
                if (i15 <= 0) {
                    output.afterHeadWrite();
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i15, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static final void writeDouble(Output output, double d4, ByteOrder byteOrder) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            d4 = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d4)));
        }
        OutputPrimitivesKt.writeDouble(output, d4);
    }

    public static final void writeDoubleLittleEndian(Output output, double d4) {
        OutputPrimitivesKt.writeDouble(output, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d4))));
    }

    public static final void writeFloat(Output output, float f10, ByteOrder byteOrder) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            f10 = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f10)));
        }
        OutputPrimitivesKt.writeFloat(output, f10);
    }

    public static final void writeFloatLittleEndian(Output output, float f10) {
        OutputPrimitivesKt.writeFloat(output, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f10))));
    }

    public static final void writeFullyLittleEndian(Output output, short[] sArr, int i10, int i11) {
        int i12 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 2, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / 2, i12 - i10) + i10;
                int i13 = iMin - 1;
                if (i10 <= i13) {
                    while (true) {
                        BufferPrimitivesKt.writeShort((Buffer) chunkBufferPrepareWriteHead, Short.reverseBytes(sArr[i10]));
                        if (i10 == i13) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i14 = iMin < i12 ? 2 : 0;
                if (i14 <= 0) {
                    output.afterHeadWrite();
                    return;
                } else {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i14, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        writeFullyLittleEndian(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m6964writeFullyLittleEndianWt3Bwxc(Output output, short[] sArr, int i10, int i11) {
        writeFullyLittleEndian(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static void m6965writeFullyLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6963writeFullyLittleEndianWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m6968writeFullyLittleEndiano2ZM2JE(Output output, int[] iArr, int i10, int i11) {
        writeFullyLittleEndian(output, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static void m6969writeFullyLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6967writeFullyLittleEndiano2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m6972writeFullyLittleEndianpqYNikA(Output output, long[] jArr, int i10, int i11) {
        writeFullyLittleEndian(output, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static void m6973writeFullyLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6971writeFullyLittleEndianpqYNikA(buffer, jArr, i10, i11);
    }

    public static final void writeInt(Output output, int i10, ByteOrder byteOrder) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            i10 = Integer.reverseBytes(i10);
        }
        OutputPrimitivesKt.writeInt(output, i10);
    }

    public static final void writeIntLittleEndian(Output output, int i10) {
        OutputPrimitivesKt.writeInt(output, Integer.reverseBytes(i10));
    }

    public static final void writeLong(Output output, long j10, ByteOrder byteOrder) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            j10 = Long.reverseBytes(j10);
        }
        OutputPrimitivesKt.writeLong(output, j10);
    }

    public static final void writeLongLittleEndian(Output output, long j10) {
        OutputPrimitivesKt.writeLong(output, Long.reverseBytes(j10));
    }

    private static final <T> void writePrimitiveTemplate(T t2, l<? super T, t0> lVar, l<? super T, ? extends T> lVar2) {
        lVar.invoke(lVar2.invoke(t2));
    }

    public static final void writeShort(Output output, short s10, ByteOrder byteOrder) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            s10 = Short.reverseBytes(s10);
        }
        OutputPrimitivesKt.writeShort(output, s10);
    }

    public static final void writeShortLittleEndian(Output output, short s10) {
        OutputPrimitivesKt.writeShort(output, Short.reverseBytes(s10));
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        writeFullyLittleEndian(output, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m6963writeFullyLittleEndianWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) {
        writeFullyLittleEndian(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m6967writeFullyLittleEndiano2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) {
        writeFullyLittleEndian(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m6971writeFullyLittleEndianpqYNikA(Buffer buffer, long[] jArr, int i10, int i11) {
        writeFullyLittleEndian(buffer, jArr, i10, i11);
    }

    private static final <T> void writePrimitiveTemplate(T t2, ByteOrder byteOrder, l<? super T, t0> lVar, l<? super T, ? extends T> lVar2) {
        if (WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] != 1) {
            t2 = (T) lVar2.invoke(t2);
        }
        lVar.invoke(t2);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        writeFullyLittleEndian(output, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static void m6966writeFullyLittleEndianWt3Bwxc$default(Output output, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6964writeFullyLittleEndianWt3Bwxc(output, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static void m6970writeFullyLittleEndiano2ZM2JE$default(Output output, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6968writeFullyLittleEndiano2ZM2JE(output, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: writeFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static void m6974writeFullyLittleEndianpqYNikA$default(Output output, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6972writeFullyLittleEndianpqYNikA(output, jArr, i10, i11);
    }

    public static final void writeIntLittleEndian(Buffer buffer, int i10) {
        BufferPrimitivesKt.writeInt(buffer, Integer.reverseBytes(i10));
    }

    public static final void writeLongLittleEndian(Buffer buffer, long j10) {
        BufferPrimitivesKt.writeLong(buffer, Long.reverseBytes(j10));
    }

    public static final void writeShortLittleEndian(Buffer buffer, short s10) {
        BufferPrimitivesKt.writeShort(buffer, Short.reverseBytes(s10));
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        writeFullyLittleEndian(output, fArr, i10, i11);
    }

    public static final void writeDoubleLittleEndian(Buffer buffer, double d4) throws InsufficientSpaceException {
        BufferPrimitivesKt.writeDouble(buffer, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(d4))));
    }

    public static final void writeFloatLittleEndian(Buffer buffer, float f10) throws InsufficientSpaceException {
        BufferPrimitivesKt.writeFloat(buffer, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(f10))));
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Output output, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        writeFullyLittleEndian(output, dArr, i10, i11);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        writeFullyLittleEndian(buffer, sArr, i10, i11);
    }

    private static final void writeArrayTemplate(Buffer buffer, int i10, int i11, int i12, p<? super Buffer, ? super Integer, t0> pVar) {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / i12, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            pVar.invoke(buffer, Integer.valueOf(i10));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        writeFullyLittleEndian(buffer, iArr, i10, i11);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        writeFullyLittleEndian(buffer, jArr, i10, i11);
    }

    public static final void writeFullyLittleEndian(Output output, int[] iArr, int i10, int i11) {
        int i12 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 4, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / 4, i12 - i10) + i10;
                int i13 = iMin - 1;
                if (i10 <= i13) {
                    while (true) {
                        BufferPrimitivesKt.writeInt((Buffer) chunkBufferPrepareWriteHead, Integer.reverseBytes(iArr[i10]));
                        if (i10 == i13) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i14 = iMin < i12 ? 4 : 0;
                if (i14 > 0) {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i14, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                } else {
                    output.afterHeadWrite();
                    return;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        writeFullyLittleEndian(buffer, fArr, i10, i11);
    }

    public static /* synthetic */ void writeFullyLittleEndian$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) throws InsufficientSpaceException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        writeFullyLittleEndian(buffer, dArr, i10, i11);
    }

    public static final void writeFullyLittleEndian(Output output, long[] jArr, int i10, int i11) {
        int i12 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 8, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / 8, i12 - i10) + i10;
                int i13 = iMin - 1;
                if (i10 <= i13) {
                    while (true) {
                        BufferPrimitivesKt.writeLong((Buffer) chunkBufferPrepareWriteHead, Long.reverseBytes(jArr[i10]));
                        if (i10 == i13) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i14 = iMin < i12 ? 8 : 0;
                if (i14 > 0) {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i14, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                } else {
                    output.afterHeadWrite();
                    return;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static final void writeFullyLittleEndian(Output output, float[] fArr, int i10, int i11) {
        int i12 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 4, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / 4, i12 - i10) + i10;
                int i13 = iMin - 1;
                if (i10 <= i13) {
                    while (true) {
                        BufferPrimitivesKt.writeFloat((Buffer) chunkBufferPrepareWriteHead, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10]))));
                        if (i10 == i13) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i14 = iMin < i12 ? 4 : 0;
                if (i14 > 0) {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i14, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                } else {
                    output.afterHeadWrite();
                    return;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static final void writeFullyLittleEndian(Output output, double[] dArr, int i10, int i11) {
        int i12 = i11 + i10;
        ChunkBuffer chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, 8, null);
        while (true) {
            try {
                int iMin = Math.min((chunkBufferPrepareWriteHead.getLimit() - chunkBufferPrepareWriteHead.getWritePosition()) / 8, i12 - i10) + i10;
                int i13 = iMin - 1;
                if (i10 <= i13) {
                    while (true) {
                        BufferPrimitivesKt.writeDouble((Buffer) chunkBufferPrepareWriteHead, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10]))));
                        if (i10 == i13) {
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                int i14 = iMin < i12 ? 8 : 0;
                if (i14 > 0) {
                    chunkBufferPrepareWriteHead = UnsafeKt.prepareWriteHead(output, i14, chunkBufferPrepareWriteHead);
                    i10 = iMin;
                } else {
                    output.afterHeadWrite();
                    return;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
    }

    public static final void writeFullyLittleEndian(Buffer buffer, short[] sArr, int i10, int i11) {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / 2, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            BufferPrimitivesKt.writeShort(buffer, Short.reverseBytes(sArr[i10]));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final void writeFullyLittleEndian(Buffer buffer, int[] iArr, int i10, int i11) {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / 4, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            BufferPrimitivesKt.writeInt(buffer, Integer.reverseBytes(iArr[i10]));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final void writeFullyLittleEndian(Buffer buffer, long[] jArr, int i10, int i11) {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / 8, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            BufferPrimitivesKt.writeLong(buffer, Long.reverseBytes(jArr[i10]));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final void writeFullyLittleEndian(Buffer buffer, float[] fArr, int i10, int i11) throws InsufficientSpaceException {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / 4, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            BufferPrimitivesKt.writeFloat(buffer, Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10]))));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final void writeFullyLittleEndian(Buffer buffer, double[] dArr, int i10, int i11) throws InsufficientSpaceException {
        int iMin = (Math.min((buffer.getLimit() - buffer.getWritePosition()) / 8, (i11 + i10) - i10) + i10) - 1;
        if (i10 > iMin) {
            return;
        }
        while (true) {
            BufferPrimitivesKt.writeDouble(buffer, Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10]))));
            if (i10 == iMin) {
                return;
            } else {
                i10++;
            }
        }
    }
}
