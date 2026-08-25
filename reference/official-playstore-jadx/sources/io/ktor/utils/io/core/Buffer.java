package io.ktor.utils.io.core;

import a0.c;
import com.google.common.util.concurrent.r0;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0017\u0018\u0000 F2\u00020\u0001:\u0001FB\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0010\u0010\nJ\u0017\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\nJ\u0015\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0006¢\u0006\u0004\b\u0014\u0010\nJ\u0015\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0006¢\u0006\u0004\b\u0016\u0010\nJ\r\u0010\u0017\u001a\u00020\b¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\nJ\u000f\u0010\u001c\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001b\u0010\u0018J\u000f\u0010\u001e\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001d\u0010\u0018J\u0017\u0010!\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0006H\u0000¢\u0006\u0004\b \u0010\nJ\u0017\u0010#\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0000H\u0014¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0000H\u0016¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\u0006¢\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\u0006¢\u0006\u0004\b)\u0010(J\r\u0010+\u001a\u00020*¢\u0006\u0004\b+\u0010,J\u0015\u0010.\u001a\u00020\b2\u0006\u0010-\u001a\u00020*¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\bH\u0016¢\u0006\u0004\b0\u0010\u0018J\u000f\u00102\u001a\u000201H\u0016¢\u0006\u0004\b2\u00103R \u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u00106R$\u00108\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010(R$\u0010;\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b;\u00109\u001a\u0004\b<\u0010(R$\u0010\u0013\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0013\u00109\u001a\u0004\b=\u0010(R$\u0010\u001a\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00068\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001a\u00109\u001a\u0004\b>\u0010(R\u0017\u0010?\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b?\u00109\u001a\u0004\b@\u0010(R\u0012\u0010\u0015\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\bA\u0010(R\u0012\u0010C\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\bB\u0010(R\u0012\u0010E\u001a\u00020\u00068Æ\u0002¢\u0006\u0006\u001a\u0004\bD\u0010(\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Lio/ktor/utils/io/core/Buffer;", "", "Lio/ktor/utils/io/bits/Memory;", "memory", "<init>", "(Ljava/nio/ByteBuffer;Lkotlin/jvm/internal/h;)V", "", "count", "Lx6/t0;", "discardExact", "(I)V", "commitWritten", "position", "", "commitWrittenUntilIndex", "(I)Z", "discardUntilIndex$ktor_io", "discardUntilIndex", "rewind", "startGap", "reserveStartGap", "endGap", "reserveEndGap", "resetForRead", "()V", "resetForWrite", "limit", "releaseGaps$ktor_io", "releaseGaps", "releaseEndGap$ktor_io", "releaseEndGap", "newReadPosition", "releaseStartGap$ktor_io", "releaseStartGap", "copy", "duplicateTo", "(Lio/ktor/utils/io/core/Buffer;)V", "duplicate", "()Lio/ktor/utils/io/core/Buffer;", "tryPeekByte", "()I", "tryReadByte", "", "readByte", "()B", "value", "writeByte", "(B)V", "reset", "", "toString", "()Ljava/lang/String;", "Ljava/nio/ByteBuffer;", "getMemory-SK3TCg8", "()Ljava/nio/ByteBuffer;", "<set-?>", "readPosition", "I", "getReadPosition", "writePosition", "getWritePosition", "getStartGap", "getLimit", "capacity", "getCapacity", "getEndGap", "getReadRemaining", "readRemaining", "getWriteRemaining", "writeRemaining", "Companion", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public class Buffer {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int ReservedSize = 8;
    private final int capacity;
    private int limit;
    private final ByteBuffer memory;
    private int readPosition;
    private int startGap;
    private int writePosition;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/core/Buffer$Companion;", "", "()V", "Empty", "Lio/ktor/utils/io/core/Buffer;", "getEmpty", "()Lio/ktor/utils/io/core/Buffer;", "ReservedSize", "", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final Buffer getEmpty() {
            return ChunkBuffer.INSTANCE.getEmpty();
        }

        private Companion() {
        }
    }

    public /* synthetic */ Buffer(ByteBuffer byteBuffer, h hVar) {
        this(byteBuffer);
    }

    public static /* synthetic */ void discardExact$default(Buffer buffer, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: discardExact");
        }
        if ((i11 & 1) != 0) {
            i10 = buffer.getWritePosition() - buffer.getReadPosition();
        }
        buffer.discardExact(i10);
    }

    public static /* synthetic */ void rewind$default(Buffer buffer, int i10, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rewind");
        }
        if ((i11 & 1) != 0) {
            i10 = buffer.readPosition - buffer.startGap;
        }
        buffer.rewind(i10);
    }

    public final void commitWritten(int count) {
        int i10 = this.writePosition + count;
        if (count < 0 || i10 > this.limit) {
            BufferKt.commitWrittenFailed(count, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        this.writePosition = i10;
    }

    public final boolean commitWrittenUntilIndex(int position) {
        int i10 = this.limit;
        int i11 = this.writePosition;
        if (position < i11) {
            BufferKt.commitWrittenFailed(position - i11, getLimit() - getWritePosition());
            throw new KotlinNothingValueException();
        }
        if (position < i10) {
            this.writePosition = position;
            return true;
        }
        if (position == i10) {
            this.writePosition = position;
            return false;
        }
        BufferKt.commitWrittenFailed(position - i11, getLimit() - getWritePosition());
        throw new KotlinNothingValueException();
    }

    public final void discardExact(int count) {
        if (count == 0) {
            return;
        }
        int i10 = this.readPosition + count;
        if (count < 0 || i10 > this.writePosition) {
            BufferKt.discardFailed(count, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        this.readPosition = i10;
    }

    public final void discardUntilIndex$ktor_io(int position) {
        if (position < 0 || position > this.writePosition) {
            BufferKt.discardFailed(position - this.readPosition, getWritePosition() - getReadPosition());
            throw new KotlinNothingValueException();
        }
        if (this.readPosition != position) {
            this.readPosition = position;
        }
    }

    public Buffer duplicate() {
        Buffer buffer = new Buffer(this.memory, null);
        buffer.duplicateTo(buffer);
        return buffer;
    }

    public void duplicateTo(Buffer copy) {
        copy.limit = this.limit;
        copy.startGap = this.startGap;
        copy.readPosition = this.readPosition;
        copy.writePosition = this.writePosition;
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final int getEndGap() {
        return getCapacity() - getLimit();
    }

    public final int getLimit() {
        return this.limit;
    }

    /* JADX INFO: renamed from: getMemory-SK3TCg8, reason: not valid java name and from getter */
    public final ByteBuffer getMemory() {
        return this.memory;
    }

    public final int getReadPosition() {
        return this.readPosition;
    }

    public final int getReadRemaining() {
        return getWritePosition() - getReadPosition();
    }

    public final int getStartGap() {
        return this.startGap;
    }

    public final int getWritePosition() {
        return this.writePosition;
    }

    public final int getWriteRemaining() {
        return getLimit() - getWritePosition();
    }

    public final byte readByte() throws EOFException {
        int i10 = this.readPosition;
        if (i10 == this.writePosition) {
            throw new EOFException("No readable bytes available.");
        }
        this.readPosition = i10 + 1;
        return this.memory.get(i10);
    }

    public final void releaseEndGap$ktor_io() {
        this.limit = this.capacity;
    }

    public final void releaseGaps$ktor_io() {
        releaseStartGap$ktor_io(0);
        releaseEndGap$ktor_io();
    }

    public final void releaseStartGap$ktor_io(int newReadPosition) {
        if (newReadPosition < 0) {
            throw new IllegalArgumentException(c.i(newReadPosition, "newReadPosition shouldn't be negative: ").toString());
        }
        if (newReadPosition > this.readPosition) {
            StringBuilder sbS = c.s(newReadPosition, "newReadPosition shouldn't be ahead of the read position: ", " > ");
            sbS.append(this.readPosition);
            throw new IllegalArgumentException(sbS.toString().toString());
        }
        this.readPosition = newReadPosition;
        if (this.startGap > newReadPosition) {
            this.startGap = newReadPosition;
        }
    }

    public final void reserveEndGap(int endGap) {
        if (endGap < 0) {
            throw new IllegalArgumentException(c.i(endGap, "endGap shouldn't be negative: ").toString());
        }
        int i10 = this.capacity - endGap;
        if (i10 >= this.writePosition) {
            this.limit = i10;
            return;
        }
        if (i10 < 0) {
            BufferKt.endGapReservationFailedDueToCapacity(this, endGap);
        }
        if (i10 < this.startGap) {
            BufferKt.endGapReservationFailedDueToStartGap(this, endGap);
        }
        if (this.readPosition != this.writePosition) {
            BufferKt.endGapReservationFailedDueToContent(this, endGap);
            return;
        }
        this.limit = i10;
        this.readPosition = i10;
        this.writePosition = i10;
    }

    public final void reserveStartGap(int startGap) {
        if (startGap < 0) {
            throw new IllegalArgumentException(c.i(startGap, "startGap shouldn't be negative: ").toString());
        }
        int i10 = this.readPosition;
        if (i10 >= startGap) {
            this.startGap = startGap;
            return;
        }
        if (i10 != this.writePosition) {
            BufferKt.startGapReservationFailed(this, startGap);
            throw new KotlinNothingValueException();
        }
        if (startGap > this.limit) {
            BufferKt.startGapReservationFailedDueToLimit(this, startGap);
            throw new KotlinNothingValueException();
        }
        this.writePosition = startGap;
        this.readPosition = startGap;
        this.startGap = startGap;
    }

    public void reset() {
        releaseGaps$ktor_io();
        resetForWrite();
    }

    public final void resetForRead() {
        this.startGap = 0;
        this.readPosition = 0;
        this.writePosition = this.capacity;
    }

    public final void resetForWrite() {
        resetForWrite(this.capacity - this.startGap);
    }

    public final void rewind(int count) {
        int i10 = this.readPosition;
        int i11 = i10 - count;
        int i12 = this.startGap;
        if (i11 >= i12) {
            this.readPosition = i11;
        } else {
            BufferKt.rewindFailed(count, i10 - i12);
            throw new KotlinNothingValueException();
        }
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Buffer[0x");
        int iHashCode = hashCode();
        r0.c(16);
        sb2.append(Integer.toString(iHashCode, 16));
        sb2.append("](");
        sb2.append(getWritePosition() - getReadPosition());
        sb2.append(" used, ");
        sb2.append(getLimit() - getWritePosition());
        sb2.append(" free, ");
        sb2.append((getCapacity() - getLimit()) + this.startGap);
        sb2.append(" reserved of ");
        return c.o(sb2, this.capacity, ')');
    }

    public final int tryPeekByte() {
        int i10 = this.readPosition;
        if (i10 == this.writePosition) {
            return -1;
        }
        return this.memory.get(i10) & 255;
    }

    public final int tryReadByte() {
        int i10 = this.readPosition;
        if (i10 == this.writePosition) {
            return -1;
        }
        this.readPosition = i10 + 1;
        return this.memory.get(i10) & 255;
    }

    public final void writeByte(byte value) throws InsufficientSpaceException {
        int i10 = this.writePosition;
        if (i10 == this.limit) {
            throw new InsufficientSpaceException("No free space in the buffer to write a byte");
        }
        this.memory.put(i10, value);
        this.writePosition = i10 + 1;
    }

    private Buffer(ByteBuffer byteBuffer) {
        this.memory = byteBuffer;
        this.limit = byteBuffer.limit();
        this.capacity = byteBuffer.limit();
    }

    public final void resetForWrite(int limit) {
        int i10 = this.startGap;
        this.readPosition = i10;
        this.writePosition = i10;
        this.limit = limit;
    }
}
