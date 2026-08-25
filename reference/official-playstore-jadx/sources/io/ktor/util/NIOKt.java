package io.ktor.util;

import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u001c\u0010\n\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\f\u001a\u00020\u0005\u001a\n\u0010\r\u001a\u00020\u000e*\u00020\u0001¨\u0006\u000f"}, d2 = {"copy", "Ljava/nio/ByteBuffer;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", ContentDisposition.Parameters.Size, "", "decodeString", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "moveTo", "destination", "limit", "moveToByteArray", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NIOKt {
    public static final ByteBuffer copy(ByteBuffer byteBuffer, int i10) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i10);
        moveTo$default(byteBuffer.slice(), byteBufferAllocate, 0, 2, null);
        byteBufferAllocate.clear();
        return byteBufferAllocate;
    }

    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = byteBuffer.remaining();
        }
        return copy(byteBuffer, i10);
    }

    public static final String decodeString(ByteBuffer byteBuffer, Charset charset) {
        return charset.decode(byteBuffer).toString();
    }

    public static /* synthetic */ String decodeString$default(ByteBuffer byteBuffer, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return decodeString(byteBuffer, charset);
    }

    public static final int moveTo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        int iMin = Math.min(i10, Math.min(byteBuffer.remaining(), byteBuffer2.remaining()));
        if (iMin == byteBuffer.remaining()) {
            byteBuffer2.put(byteBuffer);
            return iMin;
        }
        int iLimit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + iMin);
        byteBuffer2.put(byteBuffer);
        byteBuffer.limit(iLimit);
        return iMin;
    }

    public static /* synthetic */ int moveTo$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        return moveTo(byteBuffer, byteBuffer2, i10);
    }

    public static final byte[] moveToByteArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static /* synthetic */ ByteBuffer copy$default(ByteBuffer byteBuffer, ObjectPool objectPool, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer.remaining();
        }
        return copy(byteBuffer, objectPool, i10);
    }

    public static final ByteBuffer copy(ByteBuffer byteBuffer, ObjectPool<ByteBuffer> objectPool, int i10) {
        ByteBuffer byteBufferBorrow = objectPool.borrow();
        byteBufferBorrow.limit(i10);
        moveTo$default(byteBuffer.slice(), byteBufferBorrow, 0, 2, null);
        byteBufferBorrow.flip();
        return byteBufferBorrow;
    }
}
