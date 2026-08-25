package io.ktor.util.cio;

import io.ktor.util.InternalAPI;
import io.ktor.utils.io.pool.DefaultPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0002H\u0014¨\u0006\u0007"}, d2 = {"Lio/ktor/util/cio/ByteBufferPool;", "Lio/ktor/utils/io/pool/DefaultPool;", "Ljava/nio/ByteBuffer;", "()V", "clearInstance", "instance", "produceInstance", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class ByteBufferPool extends DefaultPool<ByteBuffer> {
    public ByteBufferPool() {
        super(2048);
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer clearInstance(ByteBuffer instance) {
        instance.clear();
        return instance;
    }

    @Override // io.ktor.utils.io.pool.DefaultPool
    public ByteBuffer produceInstance() {
        return ByteBuffer.allocate(ByteBufferPoolKt.DEFAULT_BUFFER_SIZE);
    }
}
