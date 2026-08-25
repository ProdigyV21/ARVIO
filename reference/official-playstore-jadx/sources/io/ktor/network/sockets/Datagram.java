package io.ktor.network.sockets;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/Datagram;", "", "packet", "Lio/ktor/utils/io/core/ByteReadPacket;", "address", "Lio/ktor/network/sockets/SocketAddress;", "(Lio/ktor/utils/io/core/ByteReadPacket;Lio/ktor/network/sockets/SocketAddress;)V", "getAddress", "()Lio/ktor/network/sockets/SocketAddress;", "getPacket", "()Lio/ktor/utils/io/core/ByteReadPacket;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Datagram {
    private final SocketAddress address;
    private final ByteReadPacket packet;

    public Datagram(ByteReadPacket byteReadPacket, SocketAddress socketAddress) {
        this.packet = byteReadPacket;
        this.address = socketAddress;
        if (byteReadPacket.getRemaining() <= 65535) {
            return;
        }
        throw new IllegalArgumentException(("Datagram size limit exceeded: " + byteReadPacket.getRemaining() + " of possible 65535").toString());
    }

    public final SocketAddress getAddress() {
        return this.address;
    }

    public final ByteReadPacket getPacket() {
        return this.packet;
    }
}
