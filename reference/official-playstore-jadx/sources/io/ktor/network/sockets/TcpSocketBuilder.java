package io.ktor.network.sockets;

import d7.d;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J>\u0010\u0012\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J<\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0004\b\u0016\u0010\u0017J6\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00182\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u001aJ4\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0004\b\u0016\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\"\u0010\u0005\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/network/sockets/TcpSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions;", "Lio/ktor/network/selector/SelectorManager;", "selector", "options", "<init>", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions;)V", "", "hostname", "", "port", "Lkotlin/Function1;", "Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Lx6/t0;", "Lx6/n;", "configure", "Lio/ktor/network/sockets/Socket;", "connect", "(Ljava/lang/String;ILr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lio/ktor/network/sockets/ServerSocket;", "bind", "(Ljava/lang/String;ILr7/l;)Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/sockets/SocketAddress;", "remoteAddress", "(Lio/ktor/network/sockets/SocketAddress;Lr7/l;Ld7/d;)Ljava/lang/Object;", "localAddress", "(Lio/ktor/network/sockets/SocketAddress;Lr7/l;)Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/selector/SelectorManager;", "Lio/ktor/network/sockets/SocketOptions;", "getOptions", "()Lio/ktor/network/sockets/SocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions;)V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TcpSocketBuilder implements Configurable<TcpSocketBuilder, SocketOptions> {
    private SocketOptions options;
    private final SelectorManager selector;

    /* JADX INFO: renamed from: io.ktor.network.sockets.TcpSocketBuilder$bind$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SocketOptions.AcceptorOptions, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(SocketOptions.AcceptorOptions acceptorOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.AcceptorOptions) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.TcpSocketBuilder$bind$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$AcceptorOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<SocketOptions.AcceptorOptions, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(SocketOptions.AcceptorOptions acceptorOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.AcceptorOptions) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.TcpSocketBuilder$connect$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18432 extends r implements l<SocketOptions.TCPClientSocketOptions, t0> {
        public static final C18432 INSTANCE = new C18432();

        public C18432() {
            super(1);
        }

        public final void invoke(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.TCPClientSocketOptions) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.TcpSocketBuilder$connect$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$TCPClientSocketOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<SocketOptions.TCPClientSocketOptions, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(SocketOptions.TCPClientSocketOptions tCPClientSocketOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.TCPClientSocketOptions) obj);
            return t0.f22605a;
        }
    }

    public TcpSocketBuilder(SelectorManager selectorManager, SocketOptions socketOptions) {
        this.selector = selectorManager;
        this.options = socketOptions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, String str, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = "0.0.0.0";
        }
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return tcpSocketBuilder.bind(str, i10, lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, String str, int i10, l lVar, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            lVar = C18432.INSTANCE;
        }
        return tcpSocketBuilder.connect(str, i10, lVar, dVar);
    }

    public final ServerSocket bind(String hostname, int port, l<? super SocketOptions.AcceptorOptions, t0> configure) {
        return bind(new InetSocketAddress(hostname, port), configure);
    }

    @Override // io.ktor.network.sockets.Configurable
    public /* bridge */ /* synthetic */ Configurable configure(l lVar) {
        return configure((l<? super SocketOptions, t0>) lVar);
    }

    public final Object connect(String str, int i10, l<? super SocketOptions.TCPClientSocketOptions, t0> lVar, d<? super Socket> dVar) {
        return connect(new InetSocketAddress(str, i10), lVar, dVar);
    }

    @Override // io.ktor.network.sockets.Configurable
    public SocketOptions getOptions() {
        return this.options;
    }

    @Override // io.ktor.network.sockets.Configurable
    public void setOptions(SocketOptions socketOptions) {
        this.options = socketOptions;
    }

    public final ServerSocket bind(SocketAddress localAddress, l<? super SocketOptions.AcceptorOptions, t0> configure) {
        SelectorManager selectorManager = this.selector;
        SocketOptions.AcceptorOptions acceptorOptionsAcceptor$ktor_network = getOptions().peer$ktor_network().acceptor$ktor_network();
        configure.invoke(acceptorOptionsAcceptor$ktor_network);
        return ConnectUtilsJvmKt.bind(selectorManager, localAddress, acceptorOptionsAcceptor$ktor_network);
    }

    @Override // io.ktor.network.sockets.Configurable
    public TcpSocketBuilder configure(l<? super SocketOptions, t0> lVar) {
        return (TcpSocketBuilder) Configurable.DefaultImpls.configure(this, lVar);
    }

    public final Object connect(SocketAddress socketAddress, l<? super SocketOptions.TCPClientSocketOptions, t0> lVar, d<? super Socket> dVar) {
        SelectorManager selectorManager = this.selector;
        SocketOptions.TCPClientSocketOptions tCPClientSocketOptionsTcp$ktor_network = getOptions().peer$ktor_network().tcp$ktor_network();
        lVar.invoke(tCPClientSocketOptionsTcp$ktor_network);
        return ConnectUtilsJvmKt.connect(selectorManager, socketAddress, tCPClientSocketOptionsTcp$ktor_network, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object connect$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        return tcpSocketBuilder.connect(socketAddress, lVar, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ServerSocket bind$default(TcpSocketBuilder tcpSocketBuilder, SocketAddress socketAddress, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            socketAddress = null;
        }
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return tcpSocketBuilder.bind(socketAddress, lVar);
    }
}
