package io.ktor.websocket;

import d7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a9\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/utils/io/ByteReadChannel;", "input", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "", "maxFrameSize", "", "masking", "Ld7/j;", "coroutineContext", "Lio/ktor/websocket/WebSocketSession;", "RawWebSocket", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JZLd7/j;)Lio/ktor/websocket/WebSocketSession;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RawWebSocketJvmKt {
    public static final WebSocketSession RawWebSocket(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, j jVar) {
        return new RawWebSocketJvm(byteReadChannel, byteWriteChannel, j10, z, jVar, null, 32, null);
    }

    public static /* synthetic */ WebSocketSession RawWebSocket$default(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j10, boolean z, j jVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = 2147483647L;
        }
        long j11 = j10;
        if ((i10 & 8) != 0) {
            z = false;
        }
        return RawWebSocket(byteReadChannel, byteWriteChannel, j11, z, jVar);
    }
}
