package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.Allocator;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.DefaultPool;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/utils/io/core/DefaultBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "", "bufferSize", "capacity", "Lio/ktor/utils/io/bits/Allocator;", "allocator", "<init>", "(IILio/ktor/utils/io/bits/Allocator;)V", "produceInstance", "()Lio/ktor/utils/io/core/internal/ChunkBuffer;", "instance", "Lx6/t0;", "disposeInstance", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)V", "validateInstance", "clearInstance", "(Lio/ktor/utils/io/core/internal/ChunkBuffer;)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "I", "Lio/ktor/utils/io/bits/Allocator;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultBufferPool extends DefaultPool<ChunkBuffer> {
    private final Allocator allocator;
    private final int bufferSize;

    public DefaultBufferPool() {
        this(0, 0, null, 7, null);
    }

    public /* synthetic */ DefaultBufferPool(int i10, int i11, Allocator allocator, int i12, h hVar) {
        this((i12 & 1) != 0 ? 4096 : i10, (i12 & 2) != 0 ? 1000 : i11, (i12 & 4) != 0 ? DefaultAllocator.INSTANCE : allocator);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ChunkBuffer clearInstance(ChunkBuffer instance) {
        ChunkBuffer chunkBuffer = (ChunkBuffer) super.clearInstance(instance);
        chunkBuffer.unpark$ktor_io();
        chunkBuffer.reset();
        return chunkBuffer;
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public void disposeInstance(ChunkBuffer instance) {
        this.allocator.mo6741free3GNKZMM(instance.getMemory());
        super.disposeInstance(instance);
        instance.unlink$ktor_io();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.ktor.utils.io.pool.DefaultPool
    public ChunkBuffer produceInstance() {
        return new ChunkBuffer(this.allocator.mo6739allocgFvZug(this.bufferSize), null, this, null);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public void validateInstance(ChunkBuffer instance) {
        super.validateInstance(instance);
        if (instance.getMemory().limit() != this.bufferSize) {
            StringBuilder sb2 = new StringBuilder("Buffer size mismatch. Expected: ");
            sb2.append(this.bufferSize);
            sb2.append(", actual: ");
            sb2.append(instance.getMemory().limit());
            throw new IllegalStateException(sb2.toString().toString());
        }
        if (instance == ChunkBuffer.INSTANCE.getEmpty()) {
            throw new IllegalStateException("ChunkBuffer.Empty couldn't be recycled");
        }
        if (instance == Buffer.INSTANCE.getEmpty()) {
            throw new IllegalStateException("Empty instance couldn't be recycled");
        }
        if (instance.getRefCount() != 0) {
            throw new IllegalStateException("Unable to clear buffer: it is still in use.");
        }
        if (instance.getNext() != null) {
            throw new IllegalStateException("Recycled instance shouldn't be a part of a chain.");
        }
        if (instance.getOrigin() != null) {
            throw new IllegalStateException("Recycled instance shouldn't be a view or another buffer.");
        }
    }

    public DefaultBufferPool(int i10, int i11, Allocator allocator) {
        super(i11);
        this.bufferSize = i10;
        this.allocator = allocator;
    }
}
