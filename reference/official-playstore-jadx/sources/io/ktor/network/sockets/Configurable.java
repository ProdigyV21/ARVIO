package io.ktor.network.sockets;

import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u0000*\u0016\b\u0000\u0010\u0001 \u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0004J(\u0010\t\u001a\u00028\u00002\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0004\b\t\u0010\nR\u001c\u0010\u000f\u001a\u00028\u00018&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lio/ktor/network/sockets/Configurable;", "T", "Lio/ktor/network/sockets/SocketOptions;", "Options", "", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "configure", "(Lr7/l;)Lio/ktor/network/sockets/Configurable;", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "options", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Configurable<T extends Configurable<? extends T, Options>, Options extends SocketOptions> {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <T extends Configurable<? extends T, Options>, Options extends SocketOptions> T configure(Configurable<? extends T, Options> configurable, l<? super Options, t0> lVar) {
            SocketOptions socketOptionsCopy$ktor_network = configurable.getOptions().copy$ktor_network();
            lVar.invoke(socketOptionsCopy$ktor_network);
            configurable.setOptions(socketOptionsCopy$ktor_network);
            return configurable;
        }
    }

    T configure(l<? super Options, t0> block);

    Options getOptions();

    void setOptions(Options options);
}
