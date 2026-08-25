package io.ktor.websocket;

import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"io/ktor/websocket/UtilsKt__UtilsJvmKt", "io/ktor/websocket/UtilsKt__UtilsKt"}, k = 4, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {
    public static final int flagAt(boolean z, int i10) {
        return UtilsKt__UtilsKt.flagAt(z, i10);
    }

    public static final int getOUTGOING_CHANNEL_CAPACITY() {
        return UtilsKt__UtilsJvmKt.getOUTGOING_CHANNEL_CAPACITY();
    }

    public static final byte xor(byte b10, byte b11) {
        return UtilsKt__UtilsKt.xor(b10, b11);
    }

    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        UtilsKt__UtilsJvmKt.xor(byteBuffer, byteBuffer2);
    }
}
