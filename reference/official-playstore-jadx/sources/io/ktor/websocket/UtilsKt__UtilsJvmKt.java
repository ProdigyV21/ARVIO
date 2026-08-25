package io.ktor.websocket;

import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\"\u0014\u0010\b\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Ljava/nio/ByteBuffer;", "other", "Lx6/t0;", "xor", "(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)V", "", "getOUTGOING_CHANNEL_CAPACITY", "()I", "OUTGOING_CHANNEL_CAPACITY", "ktor-websockets"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/websocket/UtilsKt")
final /* synthetic */ class UtilsKt__UtilsJvmKt {
    public static final int getOUTGOING_CHANNEL_CAPACITY() {
        String property = System.getProperty("io.ktor.websocket.outgoingChannelCapacity");
        if (property != null) {
            return Integer.parseInt(property);
        }
        return 8;
    }

    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        ByteBuffer byteBufferSlice2 = byteBuffer2.slice();
        int iRemaining = byteBufferSlice2.remaining();
        int iRemaining2 = byteBufferSlice.remaining();
        for (int i10 = 0; i10 < iRemaining2; i10++) {
            byteBufferSlice.put(i10, (byte) (byteBufferSlice.get(i10) ^ byteBufferSlice2.get(i10 % iRemaining)));
        }
    }
}
