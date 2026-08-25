package io.ktor.network.util;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.pool.DefaultPool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/network/util/DirectByteBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "", "bufferSize", ContentDisposition.Parameters.Size, "<init>", "(II)V", "produceInstance", "()Ljava/nio/ByteBuffer;", "instance", "clearInstance", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "Lx6/t0;", "validateInstance", "(Ljava/nio/ByteBuffer;)V", "I", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class DirectByteBufferPool extends DefaultPool<ByteBuffer> {
    private final int bufferSize;

    public DirectByteBufferPool(int i10, int i11) {
        super(i11);
        this.bufferSize = i10;
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer clearInstance(ByteBuffer instance) {
        instance.clear();
        instance.order(ByteOrder.BIG_ENDIAN);
        return instance;
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer produceInstance() {
        return ByteBuffer.allocateDirect(this.bufferSize);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public void validateInstance(ByteBuffer instance) {
        if (!instance.isDirect()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (instance.capacity() != this.bufferSize) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }
}
