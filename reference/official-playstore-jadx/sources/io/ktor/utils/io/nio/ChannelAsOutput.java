package io.ktor.utils.io.nio;

import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ-\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/utils/io/nio/ChannelAsOutput;", "Lio/ktor/utils/io/core/Output;", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "Ljava/nio/channels/WritableByteChannel;", "channel", "<init>", "(Lio/ktor/utils/io/pool/ObjectPool;Ljava/nio/channels/WritableByteChannel;)V", "Lio/ktor/utils/io/bits/Memory;", "source", "", "offset", "length", "Lx6/t0;", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "flush", "closeDestination", "()V", "Ljava/nio/channels/WritableByteChannel;", "getChannel", "()Ljava/nio/channels/WritableByteChannel;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ChannelAsOutput extends Output {
    private final WritableByteChannel channel;

    public ChannelAsOutput(ObjectPool<ChunkBuffer> objectPool, WritableByteChannel writableByteChannel) {
        super(objectPool);
        this.channel = writableByteChannel;
    }

    @Override // io.ktor.utils.io.core.Output
    public void closeDestination() throws IOException {
        this.channel.close();
    }

    @Override // io.ktor.utils.io.core.Output
    /* JADX INFO: renamed from: flush-62zg_DM */
    public void mo6925flush62zg_DM(ByteBuffer source, int offset, int length) throws IOException {
        ByteBuffer byteBufferSliceSafe = MemoryJvmKt.sliceSafe(source, offset, length);
        while (byteBufferSliceSafe.hasRemaining()) {
            this.channel.write(byteBufferSliceSafe);
        }
    }

    public final WritableByteChannel getChannel() {
        return this.channel;
    }
}
