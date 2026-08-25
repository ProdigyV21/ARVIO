package io.ktor.utils.io.bits;

import g8.b;
import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\b\u0087@\u0018\u0000 42\u00020\u0001:\u00014B\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\b¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\fH\u0086\b¢\u0006\u0004\b\t\u0010\rJ \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0013J&\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J&\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0019J3\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ3\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001fJ\u0010\u0010#\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010&\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010+\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b)\u0010*R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010.R\u0012\u00101\u001a\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00103\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\b2\u0010%\u0088\u0001\u0003\u0092\u0001\u00020\u0002ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Lio/ktor/utils/io/bits/Memory;", "", "Ljava/nio/ByteBuffer;", "buffer", "constructor-impl", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "", "index", "", "loadAt-impl", "(Ljava/nio/ByteBuffer;I)B", "loadAt", "", "(Ljava/nio/ByteBuffer;J)B", "value", "Lx6/t0;", "storeAt-impl", "(Ljava/nio/ByteBuffer;IB)V", "storeAt", "(Ljava/nio/ByteBuffer;JB)V", "offset", "length", "slice-87lwejk", "(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;", "slice", "(Ljava/nio/ByteBuffer;JJ)Ljava/nio/ByteBuffer;", "destination", "destinationOffset", "copyTo-JT6ljtQ", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;III)V", "copyTo", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;JJJ)V", "", "toString-impl", "(Ljava/nio/ByteBuffer;)Ljava/lang/String;", "toString", "hashCode-impl", "(Ljava/nio/ByteBuffer;)I", "hashCode", "other", "", "equals-impl", "(Ljava/nio/ByteBuffer;Ljava/lang/Object;)Z", "equals", "Ljava/nio/ByteBuffer;", "getBuffer", "()Ljava/nio/ByteBuffer;", "getSize-impl", "(Ljava/nio/ByteBuffer;)J", ContentDisposition.Parameters.Size, "getSize32-impl", "size32", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@g
public final class Memory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ByteBuffer Empty = m6746constructorimpl(ByteBuffer.allocate(0).order(ByteOrder.BIG_ENDIAN));
    private final ByteBuffer buffer;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Lio/ktor/utils/io/bits/Memory$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/bits/Memory;", "getEmpty-SK3TCg8", "()Ljava/nio/ByteBuffer;", "Ljava/nio/ByteBuffer;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getEmpty-SK3TCg8, reason: not valid java name */
        public final ByteBuffer m6762getEmptySK3TCg8() {
            return Memory.Empty;
        }

        private Companion() {
        }
    }

    private /* synthetic */ Memory(ByteBuffer byteBuffer) {
        this.buffer = byteBuffer;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Memory m6745boximpl(ByteBuffer byteBuffer) {
        return new Memory(byteBuffer);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static ByteBuffer m6746constructorimpl(ByteBuffer byteBuffer) {
        return byteBuffer;
    }

    /* JADX INFO: renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m6747copyToJT6ljtQ(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, int i12) {
        if (byteBuffer.hasArray() && byteBuffer2.hasArray() && !byteBuffer.isReadOnly() && !byteBuffer2.isReadOnly()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + i10, byteBuffer2.array(), byteBuffer2.arrayOffset() + i12, i11);
            return;
        }
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(i10);
        byteBufferDuplicate.limit(i10 + i11);
        ByteBuffer byteBufferDuplicate2 = byteBuffer2.duplicate();
        byteBufferDuplicate2.position(i12);
        byteBufferDuplicate2.put(byteBufferDuplicate);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6749equalsimpl(ByteBuffer byteBuffer, Object obj) {
        return (obj instanceof Memory) && p.a(byteBuffer, ((Memory) obj).m6761unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6750equalsimpl0(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        return p.a(byteBuffer, byteBuffer2);
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static final long m6751getSizeimpl(ByteBuffer byteBuffer) {
        return byteBuffer.limit();
    }

    /* JADX INFO: renamed from: getSize32-impl, reason: not valid java name */
    public static final int m6752getSize32impl(ByteBuffer byteBuffer) {
        return byteBuffer.limit();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6753hashCodeimpl(ByteBuffer byteBuffer) {
        return byteBuffer.hashCode();
    }

    /* JADX INFO: renamed from: loadAt-impl, reason: not valid java name */
    public static final byte m6754loadAtimpl(ByteBuffer byteBuffer, int i10) {
        return byteBuffer.get(i10);
    }

    /* JADX INFO: renamed from: slice-87lwejk, reason: not valid java name */
    public static final ByteBuffer m6756slice87lwejk(ByteBuffer byteBuffer, int i10, int i11) {
        return m6746constructorimpl(MemoryJvmKt.sliceSafe(byteBuffer, i10, i11));
    }

    /* JADX INFO: renamed from: storeAt-impl, reason: not valid java name */
    public static final void m6758storeAtimpl(ByteBuffer byteBuffer, int i10, byte b10) {
        byteBuffer.put(i10, b10);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6760toStringimpl(ByteBuffer byteBuffer) {
        return "Memory(buffer=" + byteBuffer + ')';
    }

    public boolean equals(Object obj) {
        return m6749equalsimpl(this.buffer, obj);
    }

    public final ByteBuffer getBuffer() {
        return this.buffer;
    }

    public int hashCode() {
        return m6753hashCodeimpl(this.buffer);
    }

    public String toString() {
        return m6760toStringimpl(this.buffer);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ ByteBuffer m6761unboximpl() {
        return this.buffer;
    }

    /* JADX INFO: renamed from: loadAt-impl, reason: not valid java name */
    public static final byte m6755loadAtimpl(ByteBuffer byteBuffer, long j10) {
        if (j10 < 2147483647L) {
            return byteBuffer.get((int) j10);
        }
        throw b.j(j10, "index");
    }

    /* JADX INFO: renamed from: slice-87lwejk, reason: not valid java name */
    public static final ByteBuffer m6757slice87lwejk(ByteBuffer byteBuffer, long j10, long j11) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        int i10 = (int) j10;
        if (j11 < 2147483647L) {
            return m6756slice87lwejk(byteBuffer, i10, (int) j11);
        }
        throw b.j(j11, "length");
    }

    /* JADX INFO: renamed from: storeAt-impl, reason: not valid java name */
    public static final void m6759storeAtimpl(ByteBuffer byteBuffer, long j10, byte b10) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "index");
        }
        byteBuffer.put((int) j10, b10);
    }

    /* JADX INFO: renamed from: copyTo-JT6ljtQ, reason: not valid java name */
    public static final void m6748copyToJT6ljtQ(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, long j10, long j11, long j12) {
        if (j10 >= 2147483647L) {
            throw b.j(j10, "offset");
        }
        int i10 = (int) j10;
        if (j11 >= 2147483647L) {
            throw b.j(j11, "length");
        }
        int i11 = (int) j11;
        if (j12 < 2147483647L) {
            m6747copyToJT6ljtQ(byteBuffer, byteBuffer2, i10, i11, (int) j12);
            return;
        }
        throw b.j(j12, "destinationOffset");
    }
}
