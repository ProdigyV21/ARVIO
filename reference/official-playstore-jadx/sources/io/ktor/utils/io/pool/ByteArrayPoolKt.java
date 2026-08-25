package io.ktor.utils.io.pool;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"ByteArrayPool", "Lio/ktor/utils/io/pool/ObjectPool;", "", "getByteArrayPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteArrayPoolKt {
    private static final ObjectPool<byte[]> ByteArrayPool = new DefaultPool<byte[]>() { // from class: io.ktor.utils.io.pool.ByteArrayPoolKt$ByteArrayPool$1
        @Override // io.ktor.utils.io.pool.DefaultPool
        public byte[] produceInstance() {
            return new byte[4096];
        }
    };

    public static final ObjectPool<byte[]> getByteArrayPool() {
        return ByteArrayPool;
    }
}
