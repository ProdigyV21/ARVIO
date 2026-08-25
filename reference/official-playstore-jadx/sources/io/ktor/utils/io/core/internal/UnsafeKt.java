package io.ktor.utils.io.core.internal;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.PacketJVMKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\t\u001a\u0004\u0018\u00010\b*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\r\u001a\u00020\f*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a\u001d\u0010\u000f\u001a\u0004\u0018\u00010\b*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a%\u0010\u0013\u001a\u00020\b*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00158\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lio/ktor/utils/io/core/ByteReadPacket;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "builder", "", "unsafeAppend", "(Lio/ktor/utils/io/core/ByteReadPacket;Lio/ktor/utils/io/core/BytePacketBuilder;)I", "Lio/ktor/utils/io/core/Input;", "minSize", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "prepareReadFirstHead", "(Lio/ktor/utils/io/core/Input;I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "current", "Lx6/t0;", "completeReadHead", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "prepareReadNextHead", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Lio/ktor/utils/io/core/Output;", "capacity", "prepareWriteHead", "(Lio/ktor/utils/io/core/Output;ILio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "", "EmptyByteArray", "[B", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UnsafeKt {
    public static final byte[] EmptyByteArray = new byte[0];

    public static final void completeReadHead(Input input, ChunkBuffer chunkBuffer) {
        if (chunkBuffer == input) {
            return;
        }
        if (chunkBuffer.getWritePosition() <= chunkBuffer.getReadPosition()) {
            input.ensureNext(chunkBuffer);
        } else if (chunkBuffer.getCapacity() - chunkBuffer.getLimit() < 8) {
            input.fixGapAfterRead$ktor_io(chunkBuffer);
        } else {
            input.setHeadPosition(chunkBuffer.getReadPosition());
        }
    }

    public static final ChunkBuffer prepareReadFirstHead(Input input, int i10) {
        return input.prepareReadHead$ktor_io(i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ChunkBuffer prepareReadNextHead(Input input, ChunkBuffer chunkBuffer) {
        if (chunkBuffer != input) {
            return input.ensureNextHead$ktor_io(chunkBuffer);
        }
        if (input.canRead()) {
            return (ChunkBuffer) input;
        }
        return null;
    }

    public static final ChunkBuffer prepareWriteHead(Output output, int i10, ChunkBuffer chunkBuffer) {
        if (chunkBuffer != null) {
            output.afterHeadWrite();
        }
        return output.prepareWriteHead(i10);
    }

    public static final int unsafeAppend(ByteReadPacket byteReadPacket, BytePacketBuilder bytePacketBuilder) {
        int size = bytePacketBuilder.getSize();
        ChunkBuffer chunkBufferStealAll$ktor_io = bytePacketBuilder.stealAll$ktor_io();
        if (chunkBufferStealAll$ktor_io == null) {
            return 0;
        }
        if (size <= PacketJVMKt.getPACKET_MAX_COPY_SIZE() && chunkBufferStealAll$ktor_io.getNext() == null && byteReadPacket.tryWriteAppend$ktor_io(chunkBufferStealAll$ktor_io)) {
            bytePacketBuilder.afterBytesStolen$ktor_io();
            return size;
        }
        byteReadPacket.append$ktor_io(chunkBufferStealAll$ktor_io);
        return size;
    }
}
