package io.ktor.utils.io.core;

import com.google.android.gms.cast.MediaTrack;
import g8.b;
import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import kotlin.Metadata;
import r7.a;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\b\u001a\u00020\u0005*\u00020\u0000H\u0002¢\u0006\u0004\b\b\u0010\u0007\u001a\u0011\u0010\n\u001a\u00020\t*\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0013\u0010\f\u001a\u00020\t*\u00020\u0000H\u0002¢\u0006\u0004\b\f\u0010\u000b\u001a\u0011\u0010\u000e\u001a\u00020\r*\u00020\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0011\u0010\u0010\u001a\u00020\r*\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u000f\u001a\u0011\u0010\u0012\u001a\u00020\u0011*\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0011\u0010\u0014\u001a\u00020\u0011*\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0013\u001aM\u0010\u001c\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0015*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00052\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0082\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a6\u0010!\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0015*\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00052\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00028\u00000\u001eH\u0082\b¢\u0006\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/utils/io/core/Input;", "", "readShort", "(Lio/ktor/utils/io/core/Input;)S", "readShortFallback", "", "readInt", "(Lio/ktor/utils/io/core/Input;)I", "readIntFallback", "", "readLong", "(Lio/ktor/utils/io/core/Input;)J", "readLongFallback", "", "readFloat", "(Lio/ktor/utils/io/core/Input;)F", "readFloatFallback", "", "readDouble", "(Lio/ktor/utils/io/core/Input;)D", "readDoubleFallback", "R", ContentDisposition.Parameters.Size, "Lkotlin/Function2;", "Lio/ktor/utils/io/bits/Memory;", MediaTrack.ROLE_MAIN, "Lkotlin/Function0;", "fallback", "readPrimitive", "(Lio/ktor/utils/io/core/Input;ILr7/p;Lr7/a;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "read", "readPrimitiveFallback", "(Lio/ktor/utils/io/core/Input;ILr7/l;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputPrimitivesKt {
    public static final double readDouble(Input input) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 8) {
            return readDoubleFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 8);
        return input.getHeadMemory().getDouble(headPosition);
    }

    public static final double readDoubleFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 8);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(8);
        }
        double d4 = BufferPrimitivesKt.readDouble((Buffer) chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return d4;
    }

    public static final float readFloat(Input input) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 4) {
            return readFloatFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 4);
        return input.getHeadMemory().getFloat(headPosition);
    }

    public static final float readFloatFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 4);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(4);
        }
        float f10 = BufferPrimitivesKt.readFloat((Buffer) chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return f10;
    }

    public static final int readInt(Input input) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 4) {
            return readIntFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 4);
        return input.getHeadMemory().getInt(headPosition);
    }

    private static final int readIntFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 4);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(4);
        }
        int i10 = BufferPrimitivesKt.readInt((Buffer) chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return i10;
    }

    public static final long readLong(Input input) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 8) {
            return readLongFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 8);
        return input.getHeadMemory().getLong(headPosition);
    }

    private static final long readLongFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 8);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(8);
        }
        long j10 = BufferPrimitivesKt.readLong((Buffer) chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return j10;
    }

    private static final <R> R readPrimitive(Input input, int i10, p<? super Memory, ? super Integer, ? extends R> pVar, a<? extends R> aVar) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= i10) {
            return (R) aVar.invoke();
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(i10 + headPosition);
        return (R) pVar.invoke(Memory.m6745boximpl(input.getHeadMemory()), Integer.valueOf(headPosition));
    }

    private static final <R> R readPrimitiveFallback(Input input, int i10, l<? super Buffer, ? extends R> lVar) {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, i10);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(i10);
        }
        R r4 = (R) lVar.invoke(chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return r4;
    }

    public static final short readShort(Input input) {
        if (input.getHeadEndExclusive() - input.getHeadPosition() <= 2) {
            return readShortFallback(input);
        }
        int headPosition = input.getHeadPosition();
        input.setHeadPosition(headPosition + 2);
        return input.getHeadMemory().getShort(headPosition);
    }

    private static final short readShortFallback(Input input) throws EOFException {
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 2);
        if (chunkBufferPrepareReadFirstHead == null) {
            throw b.i(2);
        }
        short s10 = BufferPrimitivesKt.readShort((Buffer) chunkBufferPrepareReadFirstHead);
        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        return s10;
    }
}
