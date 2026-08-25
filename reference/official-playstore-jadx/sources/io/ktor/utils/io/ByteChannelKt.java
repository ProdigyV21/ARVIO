package io.ktor.utils.io;

import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\t\u001a%\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b\u0003\u0010\u000e\u001a/\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\u0010\u0011\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f¢\u0006\u0004\b\b\u0010\u0012¨\u0006\u0013"}, d2 = {"Ljava/nio/ByteBuffer;", "content", "Lio/ktor/utils/io/ByteReadChannel;", "ByteReadChannel", "(Ljava/nio/ByteBuffer;)Lio/ktor/utils/io/ByteReadChannel;", "", "autoFlush", "Lio/ktor/utils/io/ByteChannel;", "ByteChannel", "(Z)Lio/ktor/utils/io/ByteChannel;", "", "", "offset", "length", "([BII)Lio/ktor/utils/io/ByteReadChannel;", "Lkotlin/Function1;", "", "exceptionMapper", "(ZLr7/l;)Lio/ktor/utils/io/ByteChannel;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteChannelKt {
    public static final ByteChannel ByteChannel(boolean z) {
        return new ByteBufferChannel(z, null, 0, 6, null);
    }

    public static /* synthetic */ ByteChannel ByteChannel$default(boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return ByteChannel(z);
    }

    public static final ByteReadChannel ByteReadChannel(ByteBuffer byteBuffer) {
        return new ByteBufferChannel(byteBuffer);
    }

    public static final ByteChannel ByteChannel(boolean z, final l<? super Throwable, ? extends Throwable> lVar) {
        return new ByteBufferChannel(z) { // from class: io.ktor.utils.io.ByteChannelKt.ByteChannel.1
            /* JADX WARN: Multi-variable type inference failed */
            {
                int i10 = 6;
                h hVar = null;
                ObjectPool objectPool = null;
                int i11 = 0;
            }

            @Override // io.ktor.utils.io.ByteBufferChannel, io.ktor.utils.io.ByteWriteChannel
            public boolean close(Throwable cause) {
                return super.close((Throwable) lVar.invoke(cause));
            }
        };
    }

    public static /* synthetic */ ByteChannel ByteChannel$default(boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return ByteChannel(z, lVar);
    }

    public static final ByteReadChannel ByteReadChannel(byte[] bArr, int i10, int i11) {
        return new ByteBufferChannel(ByteBuffer.wrap(bArr, i10, i11));
    }
}
