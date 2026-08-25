package io.ktor.utils.io.nio;

import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ-\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0014¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/utils/io/nio/ChannelAsInput;", "Lio/ktor/utils/io/core/Input;", "Ljava/nio/channels/ReadableByteChannel;", "channel", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "<init>", "(Ljava/nio/channels/ReadableByteChannel;Lio/ktor/utils/io/pool/ObjectPool;)V", "Lio/ktor/utils/io/bits/Memory;", "destination", "", "offset", "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "fill", "Lx6/t0;", "closeSource", "()V", "Ljava/nio/channels/ReadableByteChannel;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ChannelAsInput extends Input {
    private final ReadableByteChannel channel;

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelAsInput(ReadableByteChannel readableByteChannel, ObjectPool<ChunkBuffer> objectPool) {
        super(null, 0L, objectPool, 3, null);
        this.channel = readableByteChannel;
        if ((readableByteChannel instanceof SelectableChannel) && ((SelectableChannel) readableByteChannel).isBlocking()) {
            throw new IllegalArgumentException("Non-blocking channels are not supported");
        }
    }

    @Override // io.ktor.utils.io.core.Input
    public void closeSource() throws IOException {
        this.channel.close();
    }

    @Override // io.ktor.utils.io.core.Input
    /* JADX INFO: renamed from: fill-62zg_DM */
    public int mo6682fill62zg_DM(ByteBuffer destination, int offset, int length) throws IOException {
        int i10 = this.channel.read(MemoryJvmKt.sliceSafe(destination, offset, length));
        if (i10 < 0) {
            return 0;
        }
        return i10;
    }
}
