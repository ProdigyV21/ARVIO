package io.ktor.server.cio.backend;

import io.ktor.network.sockets.InetSocketAddress;
import io.ktor.network.sockets.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"port", "", "Lio/ktor/network/sockets/SocketAddress;", "getPort", "(Lio/ktor/network/sockets/SocketAddress;)I", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SocketAddressUtilsKt {
    public static final int getPort(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        throw new IllegalStateException("Expected inet socket address");
    }
}
