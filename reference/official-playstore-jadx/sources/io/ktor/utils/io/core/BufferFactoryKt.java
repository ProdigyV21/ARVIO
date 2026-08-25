package io.ktor.utils.io.core;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a:\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a@\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u000b\u001a@\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005H\u0080\bø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000b\"\u0014\u0010\u000e\u001a\u00020\u00018\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f\"&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\t8\u0000X\u0080\u0004¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"R", "", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "Lx6/n;", "block", "withBuffer", "(ILr7/l;)Ljava/lang/Object;", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "(Lio/ktor/utils/io/pool/ObjectPool;Lr7/l;)Ljava/lang/Object;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "withChunkBuffer", "DEFAULT_BUFFER_SIZE", "I", "DefaultChunkedBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "getDefaultChunkedBufferPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "getDefaultChunkedBufferPool$annotations", "()V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BufferFactoryKt {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final ObjectPool<ChunkBuffer> DefaultChunkedBufferPool = new DefaultBufferPool(0, 0, null, 7, null);

    public static final ObjectPool<ChunkBuffer> getDefaultChunkedBufferPool() {
        return DefaultChunkedBufferPool;
    }

    public static /* synthetic */ void getDefaultChunkedBufferPool$annotations() {
    }

    public static final <R> R withBuffer(int i10, l<? super Buffer, ? extends R> lVar) {
        return (R) lVar.invoke(new Buffer(DefaultAllocator.INSTANCE.mo6739allocgFvZug(i10), null));
    }

    public static final <R> R withChunkBuffer(ObjectPool<ChunkBuffer> objectPool, l<? super ChunkBuffer, ? extends R> lVar) {
        ChunkBuffer chunkBufferBorrow = objectPool.borrow();
        try {
            return (R) lVar.invoke(chunkBufferBorrow);
        } finally {
            chunkBufferBorrow.release(objectPool);
        }
    }

    public static final <R> R withBuffer(ObjectPool<Buffer> objectPool, l<? super Buffer, ? extends R> lVar) {
        Buffer bufferBorrow = objectPool.borrow();
        try {
            return (R) lVar.invoke(bufferBorrow);
        } finally {
            objectPool.recycle(bufferBorrow);
        }
    }
}
