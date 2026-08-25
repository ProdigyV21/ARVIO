package io.ktor.websocket;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0080\b\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0080\f¨\u0006\u0007"}, d2 = {"flagAt", "", "", "at", "xor", "", "other", "ktor-websockets"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "io/ktor/websocket/UtilsKt")
final /* synthetic */ class UtilsKt__UtilsKt {
    public static final int flagAt(boolean z, int i10) {
        if (z) {
            return 1 << i10;
        }
        return 0;
    }

    public static final byte xor(byte b10, byte b11) {
        return (byte) (b10 ^ b11);
    }
}
