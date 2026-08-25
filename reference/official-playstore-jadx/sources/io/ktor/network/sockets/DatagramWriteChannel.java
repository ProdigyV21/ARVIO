package io.ktor.network.sockets;

import d7.d;
import e7.a;
import kotlin.Metadata;
import ma.x;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/DatagramWriteChannel;", "", "Lio/ktor/network/sockets/Datagram;", "datagram", "Lx6/t0;", "send", "(Lio/ktor/network/sockets/Datagram;Ld7/d;)Ljava/lang/Object;", "Lma/x;", "getOutgoing", "()Lma/x;", "outgoing", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DatagramWriteChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object send(DatagramWriteChannel datagramWriteChannel, Datagram datagram, d<? super t0> dVar) {
            Object objSend = datagramWriteChannel.getOutgoing().send(datagram, dVar);
            return objSend == a.f15033i ? objSend : t0.f22605a;
        }
    }

    x<Datagram> getOutgoing();

    Object send(Datagram datagram, d<? super t0> dVar);
}
