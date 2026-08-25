package io.ktor.utils.io.internal;

import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0000\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\r\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u000f\u001a\u00020\t*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0001H\u0000¨\u0006\u0012"}, d2 = {"getIOIntProperty", "", ContentDisposition.Parameters.Name, "", "default", "indexOfPartial", "Ljava/nio/ByteBuffer;", "sub", "isEmpty", "", "putAtMost", "src", "n", "putLimited", "limit", "startsWith", "prefix", "prefixSkip", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    public static final int getIOIntProperty(String str, int i10) {
        String property;
        Integer numR;
        try {
            property = System.getProperty("io.ktor.utils.io.".concat(str));
        } catch (SecurityException unused) {
            property = null;
        }
        return (property == null || (numR = u.R(property)) == null) ? i10 : numR.intValue();
    }

    public static final int indexOfPartial(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int i10;
        int iPosition = byteBuffer2.position();
        int iRemaining = byteBuffer2.remaining();
        byte b10 = byteBuffer2.get(iPosition);
        int iLimit = byteBuffer.limit();
        loop0: for (int iPosition2 = byteBuffer.position(); iPosition2 < iLimit; iPosition2++) {
            if (byteBuffer.get(iPosition2) == b10) {
                while (i10 < iRemaining) {
                    int i11 = iPosition2 + i10;
                    if (i11 == iLimit) {
                        break loop0;
                    }
                    i10 = byteBuffer.get(i11) == byteBuffer2.get(iPosition + i10) ? i10 + 1 : 1;
                }
                return iPosition2 - byteBuffer.position();
            }
        }
        return -1;
    }

    public static final boolean isEmpty(ByteBuffer byteBuffer) {
        return !byteBuffer.hasRemaining();
    }

    public static final int putAtMost(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        int iRemaining = byteBuffer.remaining();
        int iRemaining2 = byteBuffer2.remaining();
        if (iRemaining2 <= iRemaining && iRemaining2 <= i10) {
            byteBuffer.put(byteBuffer2);
            return iRemaining2;
        }
        int iMin = Math.min(iRemaining, Math.min(iRemaining2, i10));
        int i11 = 1;
        if (1 <= iMin) {
            while (true) {
                byteBuffer.put(byteBuffer2.get());
                if (i11 == iMin) {
                    break;
                }
                i11++;
            }
        }
        return iMin;
    }

    public static /* synthetic */ int putAtMost$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer2.remaining();
        }
        return putAtMost(byteBuffer, byteBuffer2, i10);
    }

    public static final int putLimited(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        return putAtMost(byteBuffer, byteBuffer2, i10 - byteBuffer2.position());
    }

    public static /* synthetic */ int putLimited$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = byteBuffer.limit();
        }
        return putLimited(byteBuffer, byteBuffer2, i10);
    }

    public static final boolean startsWith(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10) {
        int iMin = Math.min(byteBuffer.remaining(), byteBuffer2.remaining() - i10);
        if (iMin <= 0) {
            return false;
        }
        int iPosition = byteBuffer.position();
        int iPosition2 = byteBuffer2.position() + i10;
        for (int i11 = 0; i11 < iMin; i11++) {
            if (byteBuffer.get(iPosition + i11) != byteBuffer2.get(iPosition2 + i11)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean startsWith$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i10, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return startsWith(byteBuffer, byteBuffer2, i10);
    }
}
