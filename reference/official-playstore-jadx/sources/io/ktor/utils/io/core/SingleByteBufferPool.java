package io.ktor.utils.io.core;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.SingleInstancePool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/utils/io/core/SingleByteBufferPool;", "Lio/ktor/utils/io/pool/SingleInstancePool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "Ljava/nio/ByteBuffer;", "instance", "Lkotlin/Function1;", "Lx6/t0;", "release", "<init>", "(Ljava/nio/ByteBuffer;Lr7/l;)V", "produceInstance", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "disposeInstance", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "Ljava/nio/ByteBuffer;", "getInstance", "()Ljava/nio/ByteBuffer;", "Lr7/l;", "getRelease", "()Lr7/l;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SingleByteBufferPool extends SingleInstancePool<ChunkBuffer> {
    private final ByteBuffer instance;
    private final l<ByteBuffer, t0> release;

    /* JADX WARN: Multi-variable type inference failed */
    public SingleByteBufferPool(ByteBuffer byteBuffer, l<? super ByteBuffer, t0> lVar) {
        this.instance = byteBuffer;
        this.release = lVar;
    }

    public final ByteBuffer getInstance() {
        return this.instance;
    }

    public final l<ByteBuffer, t0> getRelease() {
        return this.release;
    }

    @Override // io.ktor.utils.io.pool.SingleInstancePool
    public void disposeInstance(ChunkBuffer instance) {
        this.release.invoke(this.instance);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.ktor.utils.io.pool.SingleInstancePool
    public ChunkBuffer produceInstance() {
        return BufferUtilsJvmKt.ChunkBuffer(this.instance, this);
    }
}
