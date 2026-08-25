package io.ktor.network.sockets;

import d7.d;
import kotlin.Metadata;
import ma.w;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0003\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/network/sockets/DatagramReadChannel;", "", "Lio/ktor/network/sockets/Datagram;", "receive", "(Ld7/d;)Ljava/lang/Object;", "Lma/w;", "getIncoming", "()Lma/w;", "incoming", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DatagramReadChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object receive(DatagramReadChannel datagramReadChannel, d<? super Datagram> dVar) {
            return datagramReadChannel.getIncoming().receive(dVar);
        }
    }

    w<Datagram> getIncoming();

    Object receive(d<? super Datagram> dVar);
}
