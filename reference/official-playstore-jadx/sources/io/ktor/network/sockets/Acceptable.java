package io.ktor.network.sockets;

import d7.d;
import io.ktor.network.sockets.ASocket;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0001J\u0013\u0010\u0003\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/Acceptable;", "Lio/ktor/network/sockets/ASocket;", "S", "accept", "(Ld7/d;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Acceptable<S extends ASocket> extends ASocket {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <S extends ASocket> void dispose(Acceptable<? extends S> acceptable) {
            ASocket.DefaultImpls.dispose(acceptable);
        }
    }

    Object accept(d<? super S> dVar);
}
