package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.network.sockets.UDPSocketBuilder;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a.\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\f"}, d2 = {"bindUDP", "Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/UDPSocketBuilder$Companion;", "selector", "Lio/ktor/network/selector/SelectorManager;", "localAddress", "Lio/ktor/network/sockets/SocketAddress;", "options", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "connectUDP", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "remoteAddress", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UDPSocketBuilderJvmKt {
    public static final BoundDatagramSocket bindUDP(UDPSocketBuilder.Companion companion, SelectorManager selectorManager, SocketAddress socketAddress, SocketOptions.UDPSocketOptions uDPSocketOptions) throws IOException {
        DatagramChannel datagramChannelOpenDatagramChannel = selectorManager.getProvider().openDatagramChannel();
        try {
            JavaSocketOptionsKt.assignOptions(datagramChannelOpenDatagramChannel, uDPSocketOptions);
            JavaSocketOptionsKt.nonBlocking(datagramChannelOpenDatagramChannel);
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                datagramChannelOpenDatagramChannel.bind(socketAddress != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress) : null);
            } else {
                datagramChannelOpenDatagramChannel.socket().bind(socketAddress != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress) : null);
            }
            return new DatagramSocketImpl(datagramChannelOpenDatagramChannel, selectorManager);
        } catch (Throwable th) {
            datagramChannelOpenDatagramChannel.close();
            throw th;
        }
    }

    public static final ConnectedDatagramSocket connectUDP(UDPSocketBuilder.Companion companion, SelectorManager selectorManager, SocketAddress socketAddress, SocketAddress socketAddress2, SocketOptions.UDPSocketOptions uDPSocketOptions) throws IOException {
        DatagramChannel datagramChannelOpenDatagramChannel = selectorManager.getProvider().openDatagramChannel();
        try {
            JavaSocketOptionsKt.assignOptions(datagramChannelOpenDatagramChannel, uDPSocketOptions);
            JavaSocketOptionsKt.nonBlocking(datagramChannelOpenDatagramChannel);
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                datagramChannelOpenDatagramChannel.bind(socketAddress2 != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress2) : null);
            } else {
                datagramChannelOpenDatagramChannel.socket().bind(socketAddress2 != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress2) : null);
            }
            datagramChannelOpenDatagramChannel.connect(JavaSocketAddressUtilsKt.toJavaAddress(socketAddress));
            return new DatagramSocketImpl(datagramChannelOpenDatagramChannel, selectorManager);
        } catch (Throwable th) {
            datagramChannelOpenDatagramChannel.close();
            throw th;
        }
    }
}
