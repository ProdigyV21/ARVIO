package io.ktor.client.utils;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"HttpClientDefaultPool", "Lio/ktor/utils/io/pool/ByteBufferPool;", "getHttpClientDefaultPool", "()Lio/ktor/utils/io/pool/ByteBufferPool;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CIOJvmKt {
    private static final io.ktor.utils.io.pool.ByteBufferPool HttpClientDefaultPool = new io.ktor.utils.io.pool.ByteBufferPool(0, 0, 3, null);

    public static final io.ktor.utils.io.pool.ByteBufferPool getHttpClientDefaultPool() {
        return HttpClientDefaultPool;
    }
}
