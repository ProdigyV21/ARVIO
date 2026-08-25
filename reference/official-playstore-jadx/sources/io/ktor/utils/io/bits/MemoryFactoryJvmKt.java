package io.ktor.utils.io.bits;

import io.ktor.utils.io.bits.Memory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aS\u0010\b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005H\u0086\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\f\u001a\u00020\u0006*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u0086\bø\u0001\u0001¢\u0006\u0004\b\f\u0010\r\u001a\u001f\u0010\f\u001a\u00020\u0006*\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000eH\u0086\bø\u0001\u0001¢\u0006\u0004\b\f\u0010\u0010\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"R", "", "", "offset", "length", "Lkotlin/Function1;", "Lio/ktor/utils/io/bits/Memory;", "block", "useMemory", "([BIILr7/l;)Ljava/lang/Object;", "Lio/ktor/utils/io/bits/Memory$Companion;", "array", "of", "(Lio/ktor/utils/io/bits/Memory$Companion;[BII)Ljava/nio/ByteBuffer;", "Ljava/nio/ByteBuffer;", "buffer", "(Lio/ktor/utils/io/bits/Memory$Companion;Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MemoryFactoryJvmKt {
    public static final ByteBuffer of(Memory.Companion companion, byte[] bArr, int i10, int i11) {
        return Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN));
    }

    public static /* synthetic */ ByteBuffer of$default(Memory.Companion companion, byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length - i10;
        }
        return Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN));
    }

    public static final <R> R useMemory(byte[] bArr, int i10, int i11, l<? super Memory, ? extends R> lVar) {
        return (R) lVar.invoke(Memory.m6745boximpl(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN))));
    }

    public static /* synthetic */ Object useMemory$default(byte[] bArr, int i10, int i11, l lVar, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        return lVar.invoke(Memory.m6745boximpl(Memory.m6746constructorimpl(ByteBuffer.wrap(bArr, i10, i11).slice().order(ByteOrder.BIG_ENDIAN))));
    }

    public static final ByteBuffer of(Memory.Companion companion, ByteBuffer byteBuffer) {
        return Memory.m6746constructorimpl(byteBuffer.slice().order(ByteOrder.BIG_ENDIAN));
    }
}
