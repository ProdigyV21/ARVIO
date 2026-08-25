package io.ktor.utils.io.streams;

import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ-\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u000eH\u0014¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/utils/io/streams/OutputStreamAdapter;", "Lio/ktor/utils/io/core/Output;", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "Ljava/io/OutputStream;", "stream", "<init>", "(Lio/ktor/utils/io/pool/ObjectPool;Ljava/io/OutputStream;)V", "Lio/ktor/utils/io/bits/Memory;", "source", "", "offset", "length", "Lx6/t0;", "flush-62zg_DM", "(Ljava/nio/ByteBuffer;II)V", "flush", "closeDestination", "()V", "Ljava/io/OutputStream;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OutputStreamAdapter extends Output {
    private final OutputStream stream;

    public OutputStreamAdapter(ObjectPool<ChunkBuffer> objectPool, OutputStream outputStream) {
        super(objectPool);
        this.stream = outputStream;
    }

    @Override // io.ktor.utils.io.core.Output
    public void closeDestination() throws IOException {
        this.stream.close();
    }

    @Override // io.ktor.utils.io.core.Output
    /* JADX INFO: renamed from: flush-62zg_DM */
    public void mo6925flush62zg_DM(ByteBuffer source, int offset, int length) throws IOException {
        if (source.hasArray() && !source.isReadOnly()) {
            this.stream.write(source.array(), source.arrayOffset() + offset, length);
            return;
        }
        byte[] bArrBorrow = ByteArraysKt.getByteArrayPool().borrow();
        ByteBuffer byteBufferSliceSafe = MemoryJvmKt.sliceSafe(source, offset, length);
        while (true) {
            try {
                int iMin = Math.min(byteBufferSliceSafe.remaining(), bArrBorrow.length);
                if (iMin == 0) {
                    return;
                }
                byteBufferSliceSafe.get(bArrBorrow, 0, iMin);
                this.stream.write(bArrBorrow, 0, iMin);
            } finally {
                ByteArraysKt.getByteArrayPool().recycle(bArrBorrow);
            }
        }
    }
}
