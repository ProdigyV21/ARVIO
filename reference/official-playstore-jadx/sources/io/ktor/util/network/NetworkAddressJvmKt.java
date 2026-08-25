package io.ktor.util.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\f\u001a\u00060\u0002j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0019\u0010\u0006\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\"\u0019\u0010\b\u001a\u00020\t*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b*\n\u0010\f\"\u00020\u00022\u00020\u0002*\n\u0010\r\"\u00020\u000e2\u00020\u000e¨\u0006\u000f"}, d2 = {"address", "", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "getAddress", "(Ljava/net/SocketAddress;)Ljava/lang/String;", "hostname", "getHostname", "port", "", "getPort", "(Ljava/net/SocketAddress;)I", "NetworkAddress", "UnresolvedAddressException", "Ljava/nio/channels/UnresolvedAddressException;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NetworkAddressJvmKt {
    public static final SocketAddress NetworkAddress(String str, int i10) {
        return new InetSocketAddress(str, i10);
    }

    public static final String getAddress(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        String hostString = inetSocketAddress != null ? inetSocketAddress.getHostString() : null;
        return hostString == null ? "" : hostString;
    }

    public static final String getHostname(SocketAddress socketAddress) {
        InetAddress address;
        String hostName;
        boolean z = socketAddress instanceof InetSocketAddress;
        String hostName2 = null;
        InetSocketAddress inetSocketAddress = z ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null && (hostName = inetSocketAddress.getHostName()) != null) {
            return hostName;
        }
        InetSocketAddress inetSocketAddress2 = z ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress2 != null && (address = inetSocketAddress2.getAddress()) != null) {
            hostName2 = address.getHostName();
        }
        return hostName2 == null ? "" : hostName2;
    }

    public static final int getPort(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = socketAddress instanceof InetSocketAddress ? (InetSocketAddress) socketAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 0;
    }
}
