package io.ktor.network.sockets;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"toJavaAddress", "Ljava/net/SocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "toSocketAddress", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JavaSocketAddressUtilsKt {
    public static final java.net.SocketAddress toJavaAddress(SocketAddress socketAddress) {
        return socketAddress.getAddress();
    }

    public static final SocketAddress toSocketAddress(java.net.SocketAddress socketAddress) {
        if (socketAddress instanceof java.net.InetSocketAddress) {
            return new InetSocketAddress((java.net.InetSocketAddress) socketAddress);
        }
        if (socketAddress.getClass().getName().equals(SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS)) {
            return new UnixSocketAddress(socketAddress);
        }
        throw new IllegalStateException("Unknown socket address type");
    }
}
