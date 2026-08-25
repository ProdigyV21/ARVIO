package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.Configurable;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J4\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0019\b\u0002\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0004\b\u000f\u0010\u0010J<\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0019\b\u0002\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\"\u0010\u0005\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lio/ktor/network/sockets/UDPSocketBuilder;", "Lio/ktor/network/sockets/Configurable;", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "Lio/ktor/network/selector/SelectorManager;", "selector", "options", "<init>", "(Lio/ktor/network/selector/SelectorManager;Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "Lio/ktor/network/sockets/SocketAddress;", "localAddress", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "Lio/ktor/network/sockets/BoundDatagramSocket;", "bind", "(Lio/ktor/network/sockets/SocketAddress;Lr7/l;)Lio/ktor/network/sockets/BoundDatagramSocket;", "remoteAddress", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "connect", "(Lio/ktor/network/sockets/SocketAddress;Lio/ktor/network/sockets/SocketAddress;Lr7/l;)Lio/ktor/network/sockets/ConnectedDatagramSocket;", "Lio/ktor/network/selector/SelectorManager;", "Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "getOptions", "()Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "setOptions", "(Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UDPSocketBuilder implements Configurable<UDPSocketBuilder, SocketOptions.UDPSocketOptions> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private SocketOptions.UDPSocketOptions options;
    private final SelectorManager selector;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/sockets/UDPSocketBuilder$Companion;", "", "()V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.UDPSocketBuilder$bind$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SocketOptions.UDPSocketOptions, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.UDPSocketOptions) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.UDPSocketBuilder$connect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;", "Lx6/t0;", "invoke", "(Lio/ktor/network/sockets/SocketOptions$UDPSocketOptions;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18441 extends r implements l<SocketOptions.UDPSocketOptions, t0> {
        public static final C18441 INSTANCE = new C18441();

        public C18441() {
            super(1);
        }

        public final void invoke(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SocketOptions.UDPSocketOptions) obj);
            return t0.f22605a;
        }
    }

    public UDPSocketBuilder(SelectorManager selectorManager, SocketOptions.UDPSocketOptions uDPSocketOptions) {
        this.selector = selectorManager;
        this.options = uDPSocketOptions;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BoundDatagramSocket bind$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            socketAddress = null;
        }
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return uDPSocketBuilder.bind(socketAddress, lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ConnectedDatagramSocket connect$default(UDPSocketBuilder uDPSocketBuilder, SocketAddress socketAddress, SocketAddress socketAddress2, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            socketAddress2 = null;
        }
        if ((i10 & 4) != 0) {
            lVar = C18441.INSTANCE;
        }
        return uDPSocketBuilder.connect(socketAddress, socketAddress2, lVar);
    }

    public final BoundDatagramSocket bind(SocketAddress localAddress, l<? super SocketOptions.UDPSocketOptions, t0> configure) {
        Companion companion = INSTANCE;
        SelectorManager selectorManager = this.selector;
        SocketOptions.UDPSocketOptions uDPSocketOptionsUdp$ktor_network = getOptions().udp$ktor_network();
        configure.invoke(uDPSocketOptionsUdp$ktor_network);
        return UDPSocketBuilderJvmKt.bindUDP(companion, selectorManager, localAddress, uDPSocketOptionsUdp$ktor_network);
    }

    @Override // io.ktor.network.sockets.Configurable
    public /* bridge */ /* synthetic */ Configurable configure(l lVar) {
        return configure((l<? super SocketOptions.UDPSocketOptions, t0>) lVar);
    }

    public final ConnectedDatagramSocket connect(SocketAddress remoteAddress, SocketAddress localAddress, l<? super SocketOptions.UDPSocketOptions, t0> configure) {
        Companion companion = INSTANCE;
        SelectorManager selectorManager = this.selector;
        SocketOptions.UDPSocketOptions uDPSocketOptionsUdp$ktor_network = getOptions().udp$ktor_network();
        configure.invoke(uDPSocketOptionsUdp$ktor_network);
        return UDPSocketBuilderJvmKt.connectUDP(companion, selectorManager, remoteAddress, localAddress, uDPSocketOptionsUdp$ktor_network);
    }

    @Override // io.ktor.network.sockets.Configurable
    public UDPSocketBuilder configure(l<? super SocketOptions.UDPSocketOptions, t0> lVar) {
        return (UDPSocketBuilder) Configurable.DefaultImpls.configure(this, lVar);
    }

    @Override // io.ktor.network.sockets.Configurable
    public SocketOptions.UDPSocketOptions getOptions() {
        return this.options;
    }

    @Override // io.ktor.network.sockets.Configurable
    public void setOptions(SocketOptions.UDPSocketOptions uDPSocketOptions) {
        this.options = uDPSocketOptions;
    }
}
