package io.ktor.server.cio.backend;

import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.net.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@InternalAPI
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b\u0012\u000e\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, d2 = {"Lio/ktor/server/cio/backend/ServerIncomingConnection;", "", "input", "Lio/ktor/utils/io/ByteReadChannel;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "remoteAddress", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "localAddress", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Ljava/net/SocketAddress;Ljava/net/SocketAddress;)V", "getInput", "()Lio/ktor/utils/io/ByteReadChannel;", "getLocalAddress", "()Ljava/net/SocketAddress;", "getOutput", "()Lio/ktor/utils/io/ByteWriteChannel;", "getRemoteAddress", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ServerIncomingConnection {
    private final ByteReadChannel input;
    private final SocketAddress localAddress;
    private final ByteWriteChannel output;
    private final SocketAddress remoteAddress;

    public ServerIncomingConnection(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, SocketAddress socketAddress, SocketAddress socketAddress2) {
        this.input = byteReadChannel;
        this.output = byteWriteChannel;
        this.remoteAddress = socketAddress;
        this.localAddress = socketAddress2;
    }

    public final ByteReadChannel getInput() {
        return this.input;
    }

    public final SocketAddress getLocalAddress() {
        return this.localAddress;
    }

    public final ByteWriteChannel getOutput() {
        return this.output;
    }

    public final SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }
}
