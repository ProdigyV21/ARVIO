package io.ktor.server.cio.backend;

import io.ktor.network.sockets.JavaSocketAddressUtilsKt;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"toNetworkAddress", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "Lio/ktor/network/sockets/SocketAddress;", "ktor-server-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SocketAddressUtilsJvmKt {
    public static final SocketAddress toNetworkAddress(io.ktor.network.sockets.SocketAddress socketAddress) {
        SocketAddress javaAddress = JavaSocketAddressUtilsKt.toJavaAddress(socketAddress);
        InetSocketAddress inetSocketAddress = javaAddress instanceof InetSocketAddress ? (InetSocketAddress) javaAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        throw new IllegalStateException("Expected inet socket address");
    }
}
