package io.ktor.utils.io.pool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/utils/io/pool/ByteBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "", "capacity", "bufferSize", "<init>", "(II)V", "produceInstance", "()Ljava/nio/ByteBuffer;", "instance", "clearInstance", "(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;", "Lx6/t0;", "validateInstance", "(Ljava/nio/ByteBuffer;)V", "I", "getBufferSize", "()I", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ByteBufferPool extends DefaultPool<ByteBuffer> {
    private final int bufferSize;

    /* JADX WARN: Illegal instructions before constructor call */
    public ByteBufferPool() {
        int i10 = 0;
        this(i10, i10, 3, null);
    }

    public final int getBufferSize() {
        return this.bufferSize;
    }

    public /* synthetic */ ByteBufferPool(int i10, int i11, int i12, h hVar) {
        this((i12 & 1) != 0 ? 2000 : i10, (i12 & 2) != 0 ? 4096 : i11);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer clearInstance(ByteBuffer instance) {
        instance.clear();
        instance.order(ByteOrder.BIG_ENDIAN);
        return instance;
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer produceInstance() {
        return ByteBuffer.allocate(this.bufferSize);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public void validateInstance(ByteBuffer instance) {
        if (instance.capacity() != this.bufferSize) {
            throw new IllegalStateException("Check failed.");
        }
        if (instance.isDirect()) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public ByteBufferPool(int i10, int i11) {
        super(i10);
        this.bufferSize = i11;
    }
}
