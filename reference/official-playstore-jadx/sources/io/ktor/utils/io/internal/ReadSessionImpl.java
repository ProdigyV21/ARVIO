package io.ktor.utils.io.internal;

import d7.d;
import io.ktor.utils.io.ByteBufferChannel;
import io.ktor.utils.io.SuspendableReadSession;
import io.ktor.utils.io.core.BufferUtilsJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/utils/io/internal/ReadSessionImpl;", "Lio/ktor/utils/io/SuspendableReadSession;", "Lio/ktor/utils/io/ByteBufferChannel;", "channel", "<init>", "(Lio/ktor/utils/io/ByteBufferChannel;)V", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "newView", "Lx6/t0;", "completed", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "()V", "", "n", "discard", "(I)I", "atLeast", "request", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "", "await", "(ILd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteBufferChannel;", "lastAvailable", "I", "lastView", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "getAvailableForRead", "()I", "availableForRead", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReadSessionImpl implements SuspendableReadSession {
    private final ByteBufferChannel channel;
    private int lastAvailable;
    private ChunkBuffer lastView = ChunkBuffer.INSTANCE.getEmpty();

    public ReadSessionImpl(ByteBufferChannel byteBufferChannel) {
        this.channel = byteBufferChannel;
    }

    @Override // io.ktor.utils.io.SuspendableReadSession
    public Object await(int i10, d<? super Boolean> dVar) {
        completed();
        return this.channel.awaitAtLeast(i10, dVar);
    }

    public final void completed() {
        completed(ChunkBuffer.INSTANCE.getEmpty());
    }

    @Override // io.ktor.utils.io.ReadSession
    public int discard(int n6) {
        completed();
        int iMin = Math.min(getAvailableForRead(), n6);
        this.channel.mo7011consumed(iMin);
        return iMin;
    }

    @Override // io.ktor.utils.io.ReadSession
    public int getAvailableForRead() {
        return this.channel.get_availableForRead();
    }

    @Override // io.ktor.utils.io.ReadSession
    public ChunkBuffer request(int atLeast) {
        ByteBuffer byteBufferRequest = this.channel.request(0, atLeast);
        if (byteBufferRequest == null) {
            return null;
        }
        ChunkBuffer chunkBufferChunkBuffer$default = BufferUtilsJvmKt.ChunkBuffer$default(byteBufferRequest, null, 2, null);
        chunkBufferChunkBuffer$default.resetForRead();
        completed(chunkBufferChunkBuffer$default);
        return chunkBufferChunkBuffer$default;
    }

    private final void completed(ChunkBuffer newView) {
        int i10 = this.lastAvailable;
        ChunkBuffer chunkBuffer = this.lastView;
        int writePosition = i10 - (chunkBuffer.getWritePosition() - chunkBuffer.getReadPosition());
        if (writePosition > 0) {
            this.channel.mo7011consumed(writePosition);
        }
        this.lastView = newView;
        this.lastAvailable = newView.getWritePosition() - newView.getReadPosition();
    }
}
