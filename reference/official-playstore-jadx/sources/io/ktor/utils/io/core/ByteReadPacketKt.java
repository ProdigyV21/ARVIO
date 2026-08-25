package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0086\b¨\u0006\u0007"}, d2 = {"ByteReadPacket", "Lio/ktor/utils/io/core/ByteReadPacket;", "array", "", "offset", "", "length", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ByteReadPacketKt {
    public static final ByteReadPacket ByteReadPacket(byte[] bArr, int i10, int i11) {
        return ByteReadPacketExtensionsKt.ByteReadPacket(ByteBuffer.wrap(bArr, i10, i11), new ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$1(bArr));
    }

    public static /* synthetic */ ByteReadPacket ByteReadPacket$default(byte[] bArr, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        if ((i12 & 4) != 0) {
            i11 = bArr.length;
        }
        return ByteReadPacketExtensionsKt.ByteReadPacket(ByteBuffer.wrap(bArr, i10, i11), new ByteReadPacketKt$ByteReadPacket$$inlined$ByteReadPacket$1(bArr));
    }
}
