package io.ktor.utils.io.streams;

import androidx.compose.material3.d;
import io.ktor.utils.io.pool.DefaultPool;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"ByteArrayPool", "Lio/ktor/utils/io/pool/DefaultPool;", "", "getByteArrayPool", "()Lio/ktor/utils/io/pool/DefaultPool;", "ByteArrayPoolBufferSize", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteArraysKt {
    private static final DefaultPool<byte[]> ByteArrayPool = new DefaultPool<byte[]>() { // from class: io.ktor.utils.io.streams.ByteArraysKt$ByteArrayPool$1
        @Override // io.ktor.utils.io.pool.DefaultPool
        public final byte[] produceInstance() {
            return new byte[4096];
        }

        @Override // io.ktor.utils.io.pool.DefaultPool
        public final void validateInstance(byte[] instance) {
            if (instance.length == 4096) {
                super.validateInstance(instance);
            } else {
                throw new IllegalArgumentException(d.j(instance.length, " != 4096", new StringBuilder("Unable to recycle buffer of wrong size: ")).toString());
            }
        }
    };
    public static final int ByteArrayPoolBufferSize = 4096;

    public static final DefaultPool<byte[]> getByteArrayPool() {
        return ByteArrayPool;
    }
}
