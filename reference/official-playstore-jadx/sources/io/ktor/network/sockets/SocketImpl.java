package io.ktor.network.sockets;

import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u00032\u00020\u0004B#\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0005\u001a\u00028\u00008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010 \u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/network/sockets/SocketImpl;", "Ljava/nio/channels/SocketChannel;", "S", "Lio/ktor/network/sockets/NIOSocketImpl;", "Lio/ktor/network/sockets/Socket;", "channel", "Lio/ktor/network/selector/SelectorManager;", "selector", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "socketOptions", "<init>", "(Ljava/nio/channels/SocketChannel;Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "", "state", "Lx6/t0;", "wantConnect", "(Z)V", "selfConnect", "()Z", "Ljava/net/SocketAddress;", "target", "connect$ktor_network", "(Ljava/net/SocketAddress;Ld7/d;)Ljava/lang/Object;", "connect", "Ljava/nio/channels/SocketChannel;", "getChannel", "()Ljava/nio/channels/SocketChannel;", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "localAddress", "getRemoteAddress", "remoteAddress", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SocketImpl<S extends SocketChannel> extends NIOSocketImpl<S> implements Socket {
    private final S channel;

    public /* synthetic */ SocketImpl(SocketChannel socketChannel, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, int i10, h hVar) {
        this(socketChannel, selectorManager, (i10 & 4) != 0 ? null : tCPClientSocketOptions);
    }

    private final boolean selfConnect() {
        InetAddress address;
        InetAddress address2;
        InetAddress address3;
        java.net.SocketAddress localAddress = JavaSocketOptionsKt.getJava7NetworkApisAvailable() ? getChannel().getLocalAddress() : getChannel().socket().getLocalSocketAddress();
        java.net.SocketAddress remoteAddress = JavaSocketOptionsKt.getJava7NetworkApisAvailable() ? getChannel().getRemoteAddress() : getChannel().socket().getRemoteSocketAddress();
        if (localAddress == null || remoteAddress == null) {
            throw new IllegalStateException("localAddress and remoteAddress should not be null.");
        }
        java.net.InetSocketAddress inetSocketAddress = localAddress instanceof java.net.InetSocketAddress ? (java.net.InetSocketAddress) localAddress : null;
        java.net.InetSocketAddress inetSocketAddress2 = remoteAddress instanceof java.net.InetSocketAddress ? (java.net.InetSocketAddress) remoteAddress : null;
        String hostAddress = (inetSocketAddress == null || (address3 = inetSocketAddress.getAddress()) == null) ? null : address3.getHostAddress();
        if (hostAddress == null) {
            hostAddress = "";
        }
        String hostAddress2 = (inetSocketAddress2 == null || (address2 = inetSocketAddress2.getAddress()) == null) ? null : address2.getHostAddress();
        return p.a(inetSocketAddress != null ? Integer.valueOf(inetSocketAddress.getPort()) : null, inetSocketAddress2 != null ? Integer.valueOf(inetSocketAddress2.getPort()) : null) && (((inetSocketAddress2 == null || (address = inetSocketAddress2.getAddress()) == null) ? false : address.isAnyLocalAddress()) || hostAddress.equals(hostAddress2 != null ? hostAddress2 : ""));
    }

    private final void wantConnect(boolean state) {
        interestOp(SelectInterest.CONNECT, state);
    }

    public static /* synthetic */ void wantConnect$default(SocketImpl socketImpl, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        socketImpl.wantConnect(z);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connect$ktor_network(java.net.SocketAddress r6, d7.d<? super io.ktor.network.sockets.Socket> r7) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.network.sockets.SocketImpl$connect$1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = (io.ktor.network.sockets.SocketImpl$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.SocketImpl$connect$1 r0 = new io.ktor.network.sockets.SocketImpl$connect$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L37
            if (r1 == r3) goto L2f
            if (r1 != r2) goto L27
            goto L2f
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            java.lang.Object r6 = r0.L$0
            io.ktor.network.sockets.SocketImpl r6 = (io.ktor.network.sockets.SocketImpl) r6
            k2.c.G(r7)
            goto L5a
        L37:
            k2.c.G(r7)
            java.nio.channels.SocketChannel r7 = r5.getChannel()
            boolean r6 = r7.connect(r6)
            if (r6 == 0) goto L45
            return r5
        L45:
            r5.wantConnect(r3)
            io.ktor.network.selector.SelectorManager r6 = r5.getSelector()
            io.ktor.network.selector.SelectInterest r7 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.select(r5, r7, r0)
            if (r6 != r4) goto L59
            goto L9c
        L59:
            r6 = r5
        L5a:
            java.nio.channels.SocketChannel r7 = r6.getChannel()
            boolean r7 = r7.finishConnect()
            if (r7 == 0) goto L89
            boolean r7 = r6.selfConnect()
            if (r7 == 0) goto L84
            boolean r7 = io.ktor.network.sockets.JavaSocketOptionsKt.getJava7NetworkApisAvailable()
            if (r7 == 0) goto L78
            java.nio.channels.SocketChannel r7 = r6.getChannel()
            r7.close()
            goto L5a
        L78:
            java.nio.channels.SocketChannel r7 = r6.getChannel()
            java.net.Socket r7 = r7.socket()
            r7.close()
            goto L5a
        L84:
            r7 = 0
            r6.wantConnect(r7)
            return r6
        L89:
            r6.wantConnect(r3)
            io.ktor.network.selector.SelectorManager r7 = r6.getSelector()
            io.ktor.network.selector.SelectInterest r1 = io.ktor.network.selector.SelectInterest.CONNECT
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r7 = r7.select(r6, r1, r0)
            if (r7 != r4) goto L5a
        L9c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketImpl.connect$ktor_network(java.net.SocketAddress, d7.d):java.lang.Object");
    }

    @Override // io.ktor.network.sockets.ABoundSocket
    public SocketAddress getLocalAddress() {
        SocketAddress socketAddress;
        java.net.SocketAddress localAddress = JavaSocketOptionsKt.getJava7NetworkApisAvailable() ? getChannel().getLocalAddress() : getChannel().socket().getLocalSocketAddress();
        if (localAddress == null || (socketAddress = JavaSocketAddressUtilsKt.toSocketAddress(localAddress)) == null) {
            throw new IllegalStateException("Channel is not yet bound");
        }
        return socketAddress;
    }

    @Override // io.ktor.network.sockets.AConnectedSocket
    public SocketAddress getRemoteAddress() {
        SocketAddress socketAddress;
        java.net.SocketAddress remoteAddress = JavaSocketOptionsKt.getJava7NetworkApisAvailable() ? getChannel().getRemoteAddress() : getChannel().socket().getRemoteSocketAddress();
        if (remoteAddress == null || (socketAddress = JavaSocketAddressUtilsKt.toSocketAddress(remoteAddress)) == null) {
            throw new IllegalStateException("Channel is not yet connected");
        }
        return socketAddress;
    }

    public SocketImpl(S s10, SelectorManager selectorManager, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        super(s10, selectorManager, null, tCPClientSocketOptions);
        this.channel = s10;
        if (getChannel().isBlocking()) {
            throw new IllegalArgumentException("Channel need to be configured as non-blocking.");
        }
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    public S getChannel() {
        return this.channel;
    }
}
