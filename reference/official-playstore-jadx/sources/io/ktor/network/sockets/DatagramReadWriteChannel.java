package io.ktor.network.sockets;

import d7.d;
import e7.a;
import io.ktor.network.sockets.DatagramReadChannel;
import io.ktor.network.sockets.DatagramWriteChannel;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/sockets/DatagramReadWriteChannel;", "Lio/ktor/network/sockets/DatagramReadChannel;", "Lio/ktor/network/sockets/DatagramWriteChannel;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DatagramReadWriteChannel extends DatagramReadChannel, DatagramWriteChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static Object receive(DatagramReadWriteChannel datagramReadWriteChannel, d<? super Datagram> dVar) {
            return DatagramReadChannel.DefaultImpls.receive(datagramReadWriteChannel, dVar);
        }

        public static Object send(DatagramReadWriteChannel datagramReadWriteChannel, Datagram datagram, d<? super t0> dVar) {
            Object objSend = DatagramWriteChannel.DefaultImpls.send(datagramReadWriteChannel, datagram, dVar);
            return objSend == a.f15033i ? objSend : t0.f22605a;
        }
    }
}
