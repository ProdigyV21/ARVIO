package io.ktor.network.sockets;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a)\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\r\u001a#\u0010\u0012\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a%\u0010\u0015\u001a\n \u0011*\u0004\u0018\u00010\u00140\u0014*\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/network/selector/SelectorManager;", "selector", "Lio/ktor/network/sockets/SocketAddress;", "remoteAddress", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "socketOptions", "Lio/ktor/network/sockets/Socket;", "connect", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketAddress;Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;Ld7/d;)Ljava/lang/Object;", "localAddress", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lio/ktor/network/sockets/ServerSocket;", "bind", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketAddress;Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;)Lio/ktor/network/sockets/ServerSocket;", "Ljava/nio/channels/spi/SelectorProvider;", "address", "Ljava/nio/channels/SocketChannel;", "kotlin.jvm.PlatformType", "openSocketChannelFor", "(Ljava/nio/channels/spi/SelectorProvider;Lio/ktor/network/sockets/SocketAddress;)Ljava/nio/channels/SocketChannel;", "Ljava/nio/channels/ServerSocketChannel;", "openServerSocketChannelFor", "(Ljava/nio/channels/spi/SelectorProvider;Lio/ktor/network/sockets/SocketAddress;)Ljava/nio/channels/ServerSocketChannel;", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ConnectUtilsJvmKt {

    /* JADX INFO: renamed from: io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.sockets.ConnectUtilsJvmKt", f = "ConnectUtilsJvm.kt", l = {21}, m = "connect")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConnectUtilsJvmKt.connect(null, null, null, this);
        }
    }

    public static final ServerSocket bind(SelectorManager selectorManager, SocketAddress socketAddress, SocketOptions.AcceptorOptions acceptorOptions) throws IOException {
        ServerSocketChannel serverSocketChannelOpenServerSocketChannelFor = openServerSocketChannelFor(selectorManager.getProvider(), socketAddress);
        try {
            if (socketAddress instanceof InetSocketAddress) {
                JavaSocketOptionsKt.assignOptions(serverSocketChannelOpenServerSocketChannelFor, acceptorOptions);
            }
            JavaSocketOptionsKt.nonBlocking(serverSocketChannelOpenServerSocketChannelFor);
            ServerSocketImpl serverSocketImpl = new ServerSocketImpl(serverSocketChannelOpenServerSocketChannelFor, selectorManager);
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                serverSocketImpl.getChannel().bind(socketAddress != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress) : null, acceptorOptions.getBacklogSize());
                return serverSocketImpl;
            }
            serverSocketImpl.getChannel().socket().bind(socketAddress != null ? JavaSocketAddressUtilsKt.toJavaAddress(socketAddress) : null, acceptorOptions.getBacklogSize());
            return serverSocketImpl;
        } catch (Throwable th) {
            serverSocketChannelOpenServerSocketChannelFor.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object connect(io.ktor.network.selector.SelectorManager r4, io.ktor.network.sockets.SocketAddress r5, io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions r6, d7.d<? super io.ktor.network.sockets.Socket> r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.ktor.network.sockets.ConnectUtilsJvmKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1 r0 = (io.ktor.network.sockets.ConnectUtilsJvmKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1 r0 = new io.ktor.network.sockets.ConnectUtilsJvmKt$connect$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r4 = r0.L$1
            io.ktor.network.sockets.SocketImpl r4 = (io.ktor.network.sockets.SocketImpl) r4
            java.lang.Object r5 = r0.L$0
            java.io.Closeable r5 = (java.io.Closeable) r5
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L2d
            return r4
        L2d:
            r4 = move-exception
            goto L69
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            k2.c.G(r7)
            java.nio.channels.spi.SelectorProvider r7 = r4.getProvider()
            java.nio.channels.SocketChannel r7 = openSocketChannelFor(r7, r5)
            boolean r1 = r5 instanceof io.ktor.network.sockets.InetSocketAddress     // Catch: java.lang.Throwable -> L4a
            if (r1 == 0) goto L4d
            io.ktor.network.sockets.JavaSocketOptionsKt.assignOptions(r7, r6)     // Catch: java.lang.Throwable -> L4a
            goto L4d
        L4a:
            r4 = move-exception
            r5 = r7
            goto L69
        L4d:
            io.ktor.network.sockets.JavaSocketOptionsKt.nonBlocking(r7)     // Catch: java.lang.Throwable -> L4a
            io.ktor.network.sockets.SocketImpl r1 = new io.ktor.network.sockets.SocketImpl     // Catch: java.lang.Throwable -> L4a
            r1.<init>(r7, r4, r6)     // Catch: java.lang.Throwable -> L4a
            java.net.SocketAddress r4 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toJavaAddress(r5)     // Catch: java.lang.Throwable -> L4a
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L4a
            r0.L$1 = r1     // Catch: java.lang.Throwable -> L4a
            r0.label = r2     // Catch: java.lang.Throwable -> L4a
            java.lang.Object r4 = r1.connect$ktor_network(r4, r0)     // Catch: java.lang.Throwable -> L4a
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L68
            return r5
        L68:
            return r1
        L69:
            r5.close()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.ConnectUtilsJvmKt.connect(io.ktor.network.selector.SelectorManager, io.ktor.network.sockets.SocketAddress, io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions, d7.d):java.lang.Object");
    }

    public static final ServerSocketChannel openServerSocketChannelFor(SelectorProvider selectorProvider, SocketAddress socketAddress) {
        if (socketAddress == null) {
            return selectorProvider.openServerSocketChannel();
        }
        if (socketAddress instanceof InetSocketAddress) {
            return selectorProvider.openServerSocketChannel();
        }
        if (!(socketAddress instanceof UnixSocketAddress)) {
            throw new NoWhenBranchMatchedException();
        }
        return (ServerSocketChannel) SelectorProvider.class.getMethod("openServerSocketChannel", com.google.firebase.installations.d.b()).invoke(selectorProvider, StandardProtocolFamily.valueOf("UNIX"));
    }

    public static final SocketChannel openSocketChannelFor(SelectorProvider selectorProvider, SocketAddress socketAddress) {
        if (socketAddress instanceof InetSocketAddress) {
            return selectorProvider.openSocketChannel();
        }
        if (!(socketAddress instanceof UnixSocketAddress)) {
            throw new NoWhenBranchMatchedException();
        }
        return (SocketChannel) SelectorProvider.class.getMethod("openSocketChannel", com.google.firebase.installations.d.b()).invoke(selectorProvider, StandardProtocolFamily.valueOf("UNIX"));
    }
}
