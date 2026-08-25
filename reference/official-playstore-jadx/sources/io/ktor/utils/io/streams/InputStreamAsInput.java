package io.ktor.utils.io.streams;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ-\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0014¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lio/ktor/utils/io/streams/InputStreamAsInput;", "Lio/ktor/utils/io/core/Input;", "Ljava/io/InputStream;", "stream", "Lio/ktor/utils/io/pool/ObjectPool;", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "pool", "<init>", "(Ljava/io/InputStream;Lio/ktor/utils/io/pool/ObjectPool;)V", "Lio/ktor/utils/io/bits/Memory;", "destination", "", "offset", "length", "fill-62zg_DM", "(Ljava/nio/ByteBuffer;II)I", "fill", "Lx6/t0;", "closeSource", "()V", "Ljava/io/InputStream;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InputStreamAsInput extends Input {
    private final InputStream stream;

    public InputStreamAsInput(InputStream inputStream, ObjectPool<ChunkBuffer> objectPool) {
        super(null, 0L, objectPool, 3, null);
        this.stream = inputStream;
    }

    @Override // io.ktor.utils.io.core.Input
    public void closeSource() throws IOException {
        this.stream.close();
    }

    @Override // io.ktor.utils.io.core.Input
    /* JADX INFO: renamed from: fill-62zg_DM */
    public int mo6682fill62zg_DM(ByteBuffer destination, int offset, int length) throws IOException {
        if (destination.hasArray() && !destination.isReadOnly()) {
            int i10 = this.stream.read(destination.array(), destination.arrayOffset() + offset, length);
            if (i10 < 0) {
                return 0;
            }
            return i10;
        }
        byte[] bArrBorrow = ByteArraysKt.getByteArrayPool().borrow();
        try {
            int i11 = this.stream.read(bArrBorrow, 0, Math.min(bArrBorrow.length, length));
            if (i11 == -1) {
                return 0;
            }
            Memory.m6747copyToJT6ljtQ(Memory.m6746constructorimpl(ByteBuffer.wrap(bArrBorrow, 0, i11).slice().order(ByteOrder.BIG_ENDIAN)), destination, 0, i11, offset);
            return i11;
        } finally {
            ByteArraysKt.getByteArrayPool().recycle(bArrBorrow);
        }
    }
}
