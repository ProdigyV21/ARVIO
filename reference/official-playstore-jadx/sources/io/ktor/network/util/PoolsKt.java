package io.ktor.network.util;

import io.ktor.network.sockets.DatagramKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u00020\u0001X\u0080D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0003\u001a\u0004\b\u0006\u0010\u0007\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"DEFAULT_BYTE_BUFFER_BUFFER_SIZE", "", "getDEFAULT_BYTE_BUFFER_BUFFER_SIZE$annotations", "()V", "DEFAULT_BYTE_BUFFER_POOL_SIZE", "getDEFAULT_BYTE_BUFFER_POOL_SIZE$annotations", "getDEFAULT_BYTE_BUFFER_POOL_SIZE", "()I", "DefaultByteBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "getDefaultByteBufferPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "DefaultDatagramByteBufferPool", "getDefaultDatagramByteBufferPool", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PoolsKt {
    public static final int DEFAULT_BYTE_BUFFER_BUFFER_SIZE = 4096;
    private static final int DEFAULT_BYTE_BUFFER_POOL_SIZE = 4096;
    private static final ObjectPool<ByteBuffer> DefaultByteBufferPool = new io.ktor.utils.io.pool.DirectByteBufferPool(4096, 4096);
    private static final ObjectPool<ByteBuffer> DefaultDatagramByteBufferPool = new io.ktor.utils.io.pool.DirectByteBufferPool(2048, DatagramKt.MAX_DATAGRAM_SIZE);

    public static /* synthetic */ void getDEFAULT_BYTE_BUFFER_BUFFER_SIZE$annotations() {
    }

    public static final int getDEFAULT_BYTE_BUFFER_POOL_SIZE() {
        return DEFAULT_BYTE_BUFFER_POOL_SIZE;
    }

    public static /* synthetic */ void getDEFAULT_BYTE_BUFFER_POOL_SIZE$annotations() {
    }

    public static final ObjectPool<ByteBuffer> getDefaultByteBufferPool() {
        return DefaultByteBufferPool;
    }

    public static final ObjectPool<ByteBuffer> getDefaultDatagramByteBufferPool() {
        return DefaultDatagramByteBufferPool;
    }
}
