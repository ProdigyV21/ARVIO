package io.ktor.http.cio.internals;

import io.ktor.utils.io.pool.DefaultPool;
import io.ktor.utils.io.pool.NoPoolImpl;
import io.ktor.utils.io.pool.ObjectPool;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"CHAR_ARRAY_POOL_SIZE", "", "CHAR_BUFFER_ARRAY_LENGTH", "CharArrayPool", "Lio/ktor/utils/io/pool/ObjectPool;", "", "getCharArrayPool", "()Lio/ktor/utils/io/pool/ObjectPool;", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CharArrayPoolKt {
    public static final int CHAR_ARRAY_POOL_SIZE = 4096;
    public static final int CHAR_BUFFER_ARRAY_LENGTH = 2048;
    private static final ObjectPool<char[]> CharArrayPool;

    static {
        CharArrayPool = CharArrayPoolJvmKt.isPoolingDisabled() ? new NoPoolImpl<char[]>() { // from class: io.ktor.http.cio.internals.CharArrayPoolKt$CharArrayPool$1
            @Override // io.ktor.utils.io.pool.ObjectPool
            public char[] borrow() {
                return new char[2048];
            }
        } : new DefaultPool<char[]>() { // from class: io.ktor.http.cio.internals.CharArrayPoolKt$CharArrayPool$2
            @Override // io.ktor.utils.io.pool.DefaultPool
            public char[] produceInstance() {
                return new char[2048];
            }
        };
    }

    public static final ObjectPool<char[]> getCharArrayPool() {
        return CharArrayPool;
    }
}
