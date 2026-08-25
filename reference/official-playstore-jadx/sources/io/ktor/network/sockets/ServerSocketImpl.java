package io.ktor.network.sockets;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.network.selector.InterestSuspensionsMap;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectableBase;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.ServerSocket;
import java.io.IOException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import ka.m0;
import ka.t;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ \u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0096\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u000bJ\u000f\u0010\u0018\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001a\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u00038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\u00020!8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0014\u0010)\u001a\u00020&8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020\u00128\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010/\u001a\u00020,8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0014\u00103\u001a\u0002008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"Lio/ktor/network/sockets/ServerSocketImpl;", "Lio/ktor/network/sockets/ServerSocket;", "Lio/ktor/network/selector/Selectable;", "Ljava/nio/channels/ServerSocketChannel;", "channel", "Lio/ktor/network/selector/SelectorManager;", "selector", "<init>", "(Ljava/nio/channels/ServerSocketChannel;Lio/ktor/network/selector/SelectorManager;)V", "Lio/ktor/network/sockets/Socket;", "acceptSuspend", "(Ld7/d;)Ljava/lang/Object;", "Ljava/nio/channels/SocketChannel;", "nioChannel", "accepted", "(Ljava/nio/channels/SocketChannel;)Lio/ktor/network/sockets/Socket;", "Lio/ktor/network/selector/SelectInterest;", "interest", "", "state", "Lx6/t0;", "interestOp", "(Lio/ktor/network/selector/SelectInterest;Z)V", "accept", "close", "()V", "dispose", "Ljava/nio/channels/ServerSocketChannel;", "getChannel", "()Ljava/nio/channels/ServerSocketChannel;", "Lio/ktor/network/selector/SelectorManager;", "getSelector", "()Lio/ktor/network/selector/SelectorManager;", "Lka/t;", "socketContext", "Lka/t;", "getSocketContext", "()Lka/t;", "", "getInterestedOps", "()I", "interestedOps", "isClosed", "()Z", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "suspensions", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "localAddress", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ServerSocketImpl implements ServerSocket, Selectable {
    private final /* synthetic */ SelectableBase $$delegate_0;
    private final ServerSocketChannel channel;
    private final SelectorManager selector;
    private final t socketContext;

    /* JADX INFO: renamed from: io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.sockets.ServerSocketImpl", f = "ServerSocketImpl.kt", l = {41}, m = "acceptSuspend")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ServerSocketImpl.this.acceptSuspend(this);
        }
    }

    public ServerSocketImpl(ServerSocketChannel serverSocketChannel, SelectorManager selectorManager) {
        this.channel = serverSocketChannel;
        this.selector = selectorManager;
        this.$$delegate_0 = new SelectableBase(serverSocketChannel);
        if (getChannel().isBlocking()) {
            throw new IllegalArgumentException("Channel need to be configured as non-blocking.");
        }
        this.socketContext = m0.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0046 -> B:18:0x0049). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object acceptSuspend(d7.d<? super io.ktor.network.sockets.Socket> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.network.sockets.ServerSocketImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = (io.ktor.network.sockets.ServerSocketImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1 r0 = new io.ktor.network.sockets.ServerSocketImpl$acceptSuspend$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r1 = r0.L$0
            io.ktor.network.sockets.ServerSocketImpl r1 = (io.ktor.network.sockets.ServerSocketImpl) r1
            k2.c.G(r5)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            k2.c.G(r5)
            r1 = r4
        L35:
            io.ktor.network.selector.SelectInterest r5 = io.ktor.network.selector.SelectInterest.ACCEPT
            r1.interestOp(r5, r2)
            io.ktor.network.selector.SelectorManager r3 = r1.selector
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r5 = r3.select(r1, r5, r0)
            e7.a r3 = e7.a.f15033i
            if (r5 != r3) goto L49
            return r3
        L49:
            java.nio.channels.ServerSocketChannel r5 = r1.getChannel()
            java.nio.channels.SocketChannel r5 = r5.accept()
            if (r5 == 0) goto L35
            io.ktor.network.sockets.Socket r5 = r1.accepted(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.ServerSocketImpl.acceptSuspend(d7.d):java.lang.Object");
    }

    private final Socket accepted(SocketChannel nioChannel) throws IOException {
        interestOp(SelectInterest.ACCEPT, false);
        nioChannel.configureBlocking(false);
        if (getLocalAddress() instanceof InetSocketAddress) {
            if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                nioChannel.setOption((SocketOption<Boolean>) StandardSocketOptions.TCP_NODELAY, Boolean.TRUE);
            } else {
                nioChannel.socket().setTcpNoDelay(true);
            }
        }
        return new SocketImpl(nioChannel, this.selector, null, 4, null);
    }

    @Override // io.ktor.network.sockets.Acceptable
    public Object accept(d<? super Socket> dVar) throws IOException {
        SocketChannel socketChannelAccept = getChannel().accept();
        return socketChannelAccept != null ? accepted(socketChannelAccept) : acceptSuspend(dVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            try {
                getChannel().close();
                this.selector.notifyClosed(this);
                getSocketContext().complete();
            } catch (Throwable th) {
                this.selector.notifyClosed(this);
                throw th;
            }
        } catch (Throwable th2) {
            getSocketContext().i(th2);
        }
    }

    @Override // io.ktor.network.sockets.ASocket, ka.z0
    public void dispose() {
        ServerSocket.DefaultImpls.dispose(this);
    }

    @Override // io.ktor.network.selector.Selectable
    /* JADX INFO: renamed from: getInterestedOps */
    public int get_interestedOps() {
        return this.$$delegate_0.get_interestedOps();
    }

    @Override // io.ktor.network.sockets.ABoundSocket
    public SocketAddress getLocalAddress() {
        return JavaSocketAddressUtilsKt.toSocketAddress(JavaSocketOptionsKt.getJava7NetworkApisAvailable() ? getChannel().getLocalAddress() : getChannel().socket().getLocalSocketAddress());
    }

    public final SelectorManager getSelector() {
        return this.selector;
    }

    @Override // io.ktor.network.selector.Selectable
    public InterestSuspensionsMap getSuspensions() {
        return this.$$delegate_0.getSuspensions();
    }

    @Override // io.ktor.network.selector.Selectable
    public void interestOp(SelectInterest interest, boolean state) {
        this.$$delegate_0.interestOp(interest, state);
    }

    @Override // io.ktor.network.selector.Selectable
    public boolean isClosed() {
        return this.$$delegate_0.isClosed();
    }

    @Override // io.ktor.network.selector.Selectable
    public ServerSocketChannel getChannel() {
        return this.channel;
    }

    @Override // io.ktor.network.sockets.ASocket
    public t getSocketContext() {
        return this.socketContext;
    }
}
