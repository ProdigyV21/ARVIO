package io.ktor.network.sockets;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/network/sockets/Connection;", "", "socket", "Lio/ktor/network/sockets/Socket;", "input", "Lio/ktor/utils/io/ByteReadChannel;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/network/sockets/Socket;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;)V", "getInput", "()Lio/ktor/utils/io/ByteReadChannel;", "getOutput", "()Lio/ktor/utils/io/ByteWriteChannel;", "getSocket", "()Lio/ktor/network/sockets/Socket;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Connection {
    private final ByteReadChannel input;
    private final ByteWriteChannel output;
    private final Socket socket;

    public Connection(Socket socket, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel) {
        this.socket = socket;
        this.input = byteReadChannel;
        this.output = byteWriteChannel;
    }

    public final ByteReadChannel getInput() {
        return this.input;
    }

    public final ByteWriteChannel getOutput() {
        return this.output;
    }

    public final Socket getSocket() {
        return this.socket;
    }
}
