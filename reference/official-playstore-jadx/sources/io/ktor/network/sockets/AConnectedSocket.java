package io.ktor.network.sockets;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/AConnectedSocket;", "Lio/ktor/network/sockets/AWritable;", "remoteAddress", "Lio/ktor/network/sockets/SocketAddress;", "getRemoteAddress", "()Lio/ktor/network/sockets/SocketAddress;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface AConnectedSocket extends AWritable {
    SocketAddress getRemoteAddress();
}
