package io.ktor.utils.io.core;

import java.io.EOFException;
import kotlin.Metadata;
import r7.a;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0019\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u0019\u0010\n\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a\u0019\u0010\r\u001a\u00020\f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u0010\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0011\u0010\u0012\u001a\u00020\u0003*\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0011\u0010\u0014\u001a\u00020\u0006*\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0011\u0010\u0016\u001a\u00020\t*\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u0018\u001a\u00020\f*\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0011\u0010\u001a\u001a\u00020\u000f*\u00020\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0011\u0010\u0012\u001a\u00020\u0003*\u00020\u001c¢\u0006\u0004\b\u0012\u0010\u001d\u001a\u0011\u0010\u0014\u001a\u00020\u0006*\u00020\u001c¢\u0006\u0004\b\u0014\u0010\u001e\u001a\u0011\u0010\u0016\u001a\u00020\t*\u00020\u001c¢\u0006\u0004\b\u0016\u0010\u001f\u001a\u0011\u0010\u0018\u001a\u00020\f*\u00020\u001c¢\u0006\u0004\b\u0018\u0010 \u001a\u0011\u0010\u001a\u001a\u00020\u000f*\u00020\u001c¢\u0006\u0004\b\u001a\u0010!\u001a3\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(\u001a-\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u00020*2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010(\u001a3\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u00020+2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010-\u001a-\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u00020.2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010-\u001a3\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u00020/2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u001a-\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u00101\u001a-\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u0002032\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u00104\u001a-\u0010)\u001a\u00020&*\u00020\u00002\u0006\u0010#\u001a\u0002052\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u00106\u001a3\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u00108\u001a-\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u00020*2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u00108\u001a3\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u00020+2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u0010;\u001a-\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u00020.2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010;\u001a3\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u00020/2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010=\u001a-\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010=\u001a-\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u0002032\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010>\u001a-\u00109\u001a\u00020\u0006*\u00020\u00002\u0006\u0010#\u001a\u0002052\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010?\u001a3\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010@\u001a-\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u00020*2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010@\u001a3\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u00020+2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010A\u001a-\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u00020.2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010A\u001a3\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u00020/2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u0010B\u001a-\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010B\u001a-\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u0002032\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010C\u001a-\u0010)\u001a\u00020&*\u00020\u001c2\u0006\u0010#\u001a\u0002052\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b)\u0010D\u001a3\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b7\u0010E\u001a-\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u00020*2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010E\u001a3\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u00020+2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u0010F\u001a-\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u00020.2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010F\u001a3\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u00020/2\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u0010G\u001a-\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010G\u001a-\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u0002032\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010H\u001a-\u00109\u001a\u00020\u0006*\u00020\u001c2\u0006\u0010#\u001a\u0002052\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u0006¢\u0006\u0004\b9\u0010I\u001aA\u0010Q\u001a\u00028\u0000\"\b\b\u0000\u0010K*\u00020J2\f\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000L2\u0017\u0010P\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000N¢\u0006\u0002\bOH\u0082\b¢\u0006\u0004\bQ\u0010R\u001aI\u0010Q\u001a\u00028\u0000\"\b\b\u0000\u0010K*\u00020J2\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000L2\u0017\u0010P\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000N¢\u0006\u0002\bOH\u0082\b¢\u0006\u0004\bQ\u0010S\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006T"}, d2 = {"Lio/ktor/utils/io/core/Input;", "Lio/ktor/utils/io/core/ByteOrder;", "byteOrder", "", "readShort", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/ByteOrder;)S", "", "readInt", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/ByteOrder;)I", "", "readLong", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/ByteOrder;)J", "", "readFloat", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/ByteOrder;)F", "", "readDouble", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/ByteOrder;)D", "readShortLittleEndian", "(Lio/ktor/utils/io/core/Input;)S", "readIntLittleEndian", "(Lio/ktor/utils/io/core/Input;)I", "readLongLittleEndian", "(Lio/ktor/utils/io/core/Input;)J", "readFloatLittleEndian", "(Lio/ktor/utils/io/core/Input;)F", "readDoubleLittleEndian", "(Lio/ktor/utils/io/core/Input;)D", "Lio/ktor/utils/io/core/Buffer;", "(Lio/ktor/utils/io/core/Buffer;)S", "(Lio/ktor/utils/io/core/Buffer;)I", "(Lio/ktor/utils/io/core/Buffer;)J", "(Lio/ktor/utils/io/core/Buffer;)F", "(Lio/ktor/utils/io/core/Buffer;)D", "Lx6/s0;", "dst", "offset", "length", "Lx6/t0;", "readFullyLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Input;[SII)V", "readFullyLittleEndian", "", "Lx6/n0;", "readFullyLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Input;[III)V", "", "Lx6/p0;", "readFullyLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Input;[JII)V", "", "", "(Lio/ktor/utils/io/core/Input;[FII)V", "", "(Lio/ktor/utils/io/core/Input;[DII)V", "readAvailableLittleEndian-Wt3Bwxc", "(Lio/ktor/utils/io/core/Input;[SII)I", "readAvailableLittleEndian", "readAvailableLittleEndian-o2ZM2JE", "(Lio/ktor/utils/io/core/Input;[III)I", "readAvailableLittleEndian-pqYNikA", "(Lio/ktor/utils/io/core/Input;[JII)I", "(Lio/ktor/utils/io/core/Input;[FII)I", "(Lio/ktor/utils/io/core/Input;[DII)I", "(Lio/ktor/utils/io/core/Buffer;[SII)V", "(Lio/ktor/utils/io/core/Buffer;[III)V", "(Lio/ktor/utils/io/core/Buffer;[JII)V", "(Lio/ktor/utils/io/core/Buffer;[FII)V", "(Lio/ktor/utils/io/core/Buffer;[DII)V", "(Lio/ktor/utils/io/core/Buffer;[SII)I", "(Lio/ktor/utils/io/core/Buffer;[III)I", "(Lio/ktor/utils/io/core/Buffer;[JII)I", "(Lio/ktor/utils/io/core/Buffer;[FII)I", "(Lio/ktor/utils/io/core/Buffer;[DII)I", "", "T", "Lkotlin/Function0;", "read", "Lkotlin/Function1;", "Lx6/n;", "reverse", "readPrimitiveTemplate", "(Lr7/a;Lr7/l;)Ljava/lang/Object;", "(Lio/ktor/utils/io/core/ByteOrder;Lr7/a;Lr7/l;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputLittleEndianKt {

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

    public static final int readAvailableLittleEndian(Input input, short[] sArr, int i10, int i11) throws Throwable {
        int i12;
        int available = InputArraysKt.readAvailable(input, sArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                sArr[i10] = Short.reverseBytes(sArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return readAvailableLittleEndian(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final int m6936readAvailableLittleEndianWt3Bwxc(Input input, short[] sArr, int i10, int i11) {
        return readAvailableLittleEndian(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static int m6937readAvailableLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return m6935readAvailableLittleEndianWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final int m6940readAvailableLittleEndiano2ZM2JE(Input input, int[] iArr, int i10, int i11) {
        return readAvailableLittleEndian(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static int m6941readAvailableLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return m6939readAvailableLittleEndiano2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-pqYNikA, reason: not valid java name */
    public static final int m6944readAvailableLittleEndianpqYNikA(Input input, long[] jArr, int i10, int i11) {
        return readAvailableLittleEndian(input, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-pqYNikA$default, reason: not valid java name */
    public static int m6945readAvailableLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return m6943readAvailableLittleEndianpqYNikA(buffer, jArr, i10, i11);
    }

    public static final double readDouble(Input input, ByteOrder byteOrder) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? InputPrimitivesKt.readDouble(input) : Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(InputPrimitivesKt.readDouble(input))));
    }

    public static final double readDoubleLittleEndian(Input input) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(InputPrimitivesKt.readDouble(input))));
    }

    public static final float readFloat(Input input, ByteOrder byteOrder) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? InputPrimitivesKt.readFloat(input) : Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(InputPrimitivesKt.readFloat(input))));
    }

    public static final float readFloatLittleEndian(Input input) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(InputPrimitivesKt.readFloat(input))));
    }

    public static final void readFullyLittleEndian(Input input, short[] sArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, sArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            sArr[i10] = Short.reverseBytes(sArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        readFullyLittleEndian(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m6948readFullyLittleEndianWt3Bwxc(Input input, short[] sArr, int i10, int i11) throws Throwable {
        readFullyLittleEndian(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static void m6949readFullyLittleEndianWt3Bwxc$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6947readFullyLittleEndianWt3Bwxc(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m6952readFullyLittleEndiano2ZM2JE(Input input, int[] iArr, int i10, int i11) throws Throwable {
        readFullyLittleEndian(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static void m6953readFullyLittleEndiano2ZM2JE$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6951readFullyLittleEndiano2ZM2JE(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m6956readFullyLittleEndianpqYNikA(Input input, long[] jArr, int i10, int i11) throws Throwable {
        readFullyLittleEndian(input, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static void m6957readFullyLittleEndianpqYNikA$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6955readFullyLittleEndianpqYNikA(buffer, jArr, i10, i11);
    }

    public static final int readInt(Input input, ByteOrder byteOrder) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? InputPrimitivesKt.readInt(input) : Integer.reverseBytes(InputPrimitivesKt.readInt(input));
    }

    public static final int readIntLittleEndian(Input input) {
        return Integer.reverseBytes(InputPrimitivesKt.readInt(input));
    }

    public static final long readLong(Input input, ByteOrder byteOrder) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? InputPrimitivesKt.readLong(input) : Long.reverseBytes(InputPrimitivesKt.readLong(input));
    }

    public static final long readLongLittleEndian(Input input) {
        return Long.reverseBytes(InputPrimitivesKt.readLong(input));
    }

    private static final <T> T readPrimitiveTemplate(a<? extends T> aVar, l<? super T, ? extends T> lVar) {
        return (T) lVar.invoke(aVar.invoke());
    }

    public static final short readShort(Input input, ByteOrder byteOrder) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? InputPrimitivesKt.readShort(input) : Short.reverseBytes(InputPrimitivesKt.readShort(input));
    }

    public static final short readShortLittleEndian(Input input) {
        return Short.reverseBytes(InputPrimitivesKt.readShort(input));
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return readAvailableLittleEndian(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final int m6935readAvailableLittleEndianWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) {
        return readAvailableLittleEndian(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final int m6939readAvailableLittleEndiano2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) {
        return readAvailableLittleEndian(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-pqYNikA, reason: not valid java name */
    public static final int m6943readAvailableLittleEndianpqYNikA(Buffer buffer, long[] jArr, int i10, int i11) {
        return readAvailableLittleEndian(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        readFullyLittleEndian(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-Wt3Bwxc, reason: not valid java name */
    public static final void m6947readFullyLittleEndianWt3Bwxc(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        readFullyLittleEndian(buffer, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-o2ZM2JE, reason: not valid java name */
    public static final void m6951readFullyLittleEndiano2ZM2JE(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        readFullyLittleEndian(buffer, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-pqYNikA, reason: not valid java name */
    public static final void m6955readFullyLittleEndianpqYNikA(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        readFullyLittleEndian(buffer, jArr, i10, i11);
    }

    private static final <T> T readPrimitiveTemplate(ByteOrder byteOrder, a<? extends T> aVar, l<? super T, ? extends T> lVar) {
        return WhenMappings.$EnumSwitchMapping$0[byteOrder.ordinal()] == 1 ? (T) aVar.invoke() : (T) lVar.invoke(aVar.invoke());
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return readAvailableLittleEndian(input, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static int m6938readAvailableLittleEndianWt3Bwxc$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return m6936readAvailableLittleEndianWt3Bwxc(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static int m6942readAvailableLittleEndiano2ZM2JE$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return m6940readAvailableLittleEndiano2ZM2JE(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readAvailableLittleEndian-pqYNikA$default, reason: not valid java name */
    public static int m6946readAvailableLittleEndianpqYNikA$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return m6944readAvailableLittleEndianpqYNikA(input, jArr, i10, i11);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        readFullyLittleEndian(input, jArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-Wt3Bwxc$default, reason: not valid java name */
    public static void m6950readFullyLittleEndianWt3Bwxc$default(Input input, short[] sArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        m6948readFullyLittleEndianWt3Bwxc(input, sArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-o2ZM2JE$default, reason: not valid java name */
    public static void m6954readFullyLittleEndiano2ZM2JE$default(Input input, int[] iArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        m6952readFullyLittleEndiano2ZM2JE(input, iArr, i10, i11);
    }

    /* JADX INFO: renamed from: readFullyLittleEndian-pqYNikA$default, reason: not valid java name */
    public static void m6958readFullyLittleEndianpqYNikA$default(Input input, long[] jArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        m6956readFullyLittleEndianpqYNikA(input, jArr, i10, i11);
    }

    public static final int readIntLittleEndian(Buffer buffer) {
        return Integer.reverseBytes(BufferPrimitivesKt.readInt(buffer));
    }

    public static final long readLongLittleEndian(Buffer buffer) {
        return Long.reverseBytes(BufferPrimitivesKt.readLong(buffer));
    }

    public static final short readShortLittleEndian(Buffer buffer) {
        return Short.reverseBytes(BufferPrimitivesKt.readShort(buffer));
    }

    public static final int readAvailableLittleEndian(Input input, int[] iArr, int i10, int i11) throws Throwable {
        int i12;
        int available = InputArraysKt.readAvailable(input, iArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                iArr[i10] = Integer.reverseBytes(iArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        return readAvailableLittleEndian(input, fArr, i10, i11);
    }

    public static final void readFullyLittleEndian(Input input, int[] iArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, iArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            iArr[i10] = Integer.reverseBytes(iArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, float[] fArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        readFullyLittleEndian(input, fArr, i10, i11);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Input input, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        return readAvailableLittleEndian(input, dArr, i10, i11);
    }

    public static final double readDoubleLittleEndian(Buffer buffer) {
        return Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(BufferPrimitivesKt.readDouble(buffer))));
    }

    public static final float readFloatLittleEndian(Buffer buffer) {
        return Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(BufferPrimitivesKt.readFloat(buffer))));
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Input input, double[] dArr, int i10, int i11, int i12, Object obj) throws Throwable {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        readFullyLittleEndian(input, dArr, i10, i11);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        return readAvailableLittleEndian(buffer, sArr, i10, i11);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, short[] sArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = sArr.length - i10;
        }
        readFullyLittleEndian(buffer, sArr, i10, i11);
    }

    public static final int readAvailableLittleEndian(Input input, long[] jArr, int i10, int i11) throws Throwable {
        int i12;
        int available = InputArraysKt.readAvailable(input, jArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                jArr[i10] = Long.reverseBytes(jArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        return readAvailableLittleEndian(buffer, iArr, i10, i11);
    }

    public static final void readFullyLittleEndian(Input input, long[] jArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, jArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            jArr[i10] = Long.reverseBytes(jArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, int[] iArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = iArr.length - i10;
        }
        readFullyLittleEndian(buffer, iArr, i10, i11);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        return readAvailableLittleEndian(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, long[] jArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = jArr.length - i10;
        }
        readFullyLittleEndian(buffer, jArr, i10, i11);
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        return readAvailableLittleEndian(buffer, fArr, i10, i11);
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, float[] fArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = fArr.length - i10;
        }
        readFullyLittleEndian(buffer, fArr, i10, i11);
    }

    public static final int readAvailableLittleEndian(Input input, float[] fArr, int i10, int i11) throws Throwable {
        int i12;
        int available = InputArraysKt.readAvailable(input, fArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                fArr[i10] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10])));
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static /* synthetic */ int readAvailableLittleEndian$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        return readAvailableLittleEndian(buffer, dArr, i10, i11);
    }

    public static final void readFullyLittleEndian(Input input, float[] fArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, fArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            fArr[i10] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10])));
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static /* synthetic */ void readFullyLittleEndian$default(Buffer buffer, double[] dArr, int i10, int i11, int i12, Object obj) throws EOFException {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = dArr.length - i10;
        }
        readFullyLittleEndian(buffer, dArr, i10, i11);
    }

    public static final int readAvailableLittleEndian(Input input, double[] dArr, int i10, int i11) throws Throwable {
        int i12;
        int available = InputArraysKt.readAvailable(input, dArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                dArr[i10] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10])));
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Input input, double[] dArr, int i10, int i11) throws Throwable {
        InputArraysKt.readFully(input, dArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            dArr[i10] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10])));
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final int readAvailableLittleEndian(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        int available = BufferPrimitivesKt.readAvailable(buffer, sArr, i10, i11);
        int i12 = (i10 + available) - 1;
        if (i10 <= i12) {
            while (true) {
                sArr[i10] = Short.reverseBytes(sArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Buffer buffer, short[] sArr, int i10, int i11) throws EOFException {
        BufferPrimitivesKt.readFully(buffer, sArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            sArr[i10] = Short.reverseBytes(sArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final int readAvailableLittleEndian(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        int available = BufferPrimitivesKt.readAvailable(buffer, iArr, i10, i11);
        int i12 = (i10 + available) - 1;
        if (i10 <= i12) {
            while (true) {
                iArr[i10] = Integer.reverseBytes(iArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Buffer buffer, int[] iArr, int i10, int i11) throws EOFException {
        BufferPrimitivesKt.readFully(buffer, iArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            iArr[i10] = Integer.reverseBytes(iArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final int readAvailableLittleEndian(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        int i12;
        int available = BufferPrimitivesKt.readAvailable(buffer, jArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                jArr[i10] = Long.reverseBytes(jArr[i10]);
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Buffer buffer, long[] jArr, int i10, int i11) throws EOFException {
        BufferPrimitivesKt.readFully(buffer, jArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            jArr[i10] = Long.reverseBytes(jArr[i10]);
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final int readAvailableLittleEndian(Buffer buffer, float[] fArr, int i10, int i11) throws EOFException {
        int i12;
        int available = BufferPrimitivesKt.readAvailable(buffer, fArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                fArr[i10] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10])));
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Buffer buffer, float[] fArr, int i10, int i11) throws EOFException {
        BufferPrimitivesKt.readFully(buffer, fArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            fArr[i10] = Float.intBitsToFloat(Integer.reverseBytes(Float.floatToRawIntBits(fArr[i10])));
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }

    public static final int readAvailableLittleEndian(Buffer buffer, double[] dArr, int i10, int i11) throws EOFException {
        int i12;
        int available = BufferPrimitivesKt.readAvailable(buffer, dArr, i10, i11);
        if (available > 0 && i10 <= (i10 + available) - 1) {
            while (true) {
                dArr[i10] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10])));
                if (i10 == i12) {
                    break;
                }
                i10++;
            }
        }
        return available;
    }

    public static final void readFullyLittleEndian(Buffer buffer, double[] dArr, int i10, int i11) throws EOFException {
        BufferPrimitivesKt.readFully(buffer, dArr, i10, i11);
        int i12 = (i11 + i10) - 1;
        if (i10 > i12) {
            return;
        }
        while (true) {
            dArr[i10] = Double.longBitsToDouble(Long.reverseBytes(Double.doubleToRawLongBits(dArr[i10])));
            if (i10 == i12) {
                return;
            } else {
                i10++;
            }
        }
    }
}
