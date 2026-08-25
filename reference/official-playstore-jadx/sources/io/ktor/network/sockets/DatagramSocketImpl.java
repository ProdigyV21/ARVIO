package io.ktor.network.sockets;

import d7.d;
import f7.c;
import f7.e;
import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.BoundDatagramSocket;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import ka.c0;
import ka.x0;
import kotlin.Metadata;
import m2.f0;
import ma.t;
import ma.w;
import ma.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0082Pø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001a8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u0013R\u0014\u0010!\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010#\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lio/ktor/network/sockets/DatagramSocketImpl;", "Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/ConnectedDatagramSocket;", "Lio/ktor/network/sockets/NIOSocketImpl;", "Ljava/nio/channels/DatagramChannel;", "channel", "Lio/ktor/network/selector/SelectorManager;", "selector", "<init>", "(Ljava/nio/channels/DatagramChannel;Lio/ktor/network/selector/SelectorManager;)V", "Lio/ktor/network/sockets/Datagram;", "receiveImpl", "(Ld7/d;)Ljava/lang/Object;", "Ljava/nio/ByteBuffer;", "buffer", "receiveSuspend", "(Ljava/nio/ByteBuffer;Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "close", "()V", "Ljava/nio/channels/DatagramChannel;", "getChannel", "()Ljava/nio/channels/DatagramChannel;", "Lma/x;", "sender", "Lma/x;", "Lma/w;", "receiver", "Lma/w;", "getReceiver$annotations", "Lio/ktor/network/sockets/SocketAddress;", "getLocalAddress", "()Lio/ktor/network/sockets/SocketAddress;", "localAddress", "getRemoteAddress", "remoteAddress", "getIncoming", "()Lma/w;", "incoming", "getOutgoing", "()Lma/x;", "outgoing", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DatagramSocketImpl extends NIOSocketImpl<DatagramChannel> implements BoundDatagramSocket, ConnectedDatagramSocket {
    private final DatagramChannel channel;
    private final w<Datagram> receiver;
    private final x<Datagram> sender;

    /* JADX INFO: renamed from: io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.sockets.DatagramSocketImpl", f = "DatagramSocketImpl.kt", l = {88}, m = "receiveSuspend")
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
            return DatagramSocketImpl.this.receiveSuspend(null, this);
        }
    }

    public DatagramSocketImpl(DatagramChannel datagramChannel, SelectorManager selectorManager) {
        super(datagramChannel, selectorManager, PoolsKt.getDefaultDatagramByteBufferPool(), null, 8, null);
        this.channel = datagramChannel;
        this.sender = new DatagramSendChannel(getChannel(), this);
        ra.c cVar = x0.f19655d;
        p datagramSocketImpl$receiver$1 = new DatagramSocketImpl$receiver$1(this, null);
        t tVar = new t(c0.b(this, cVar), f0.a(0, 1, 4));
        tVar.S(1, tVar, datagramSocketImpl$receiver$1);
        this.receiver = tVar;
    }

    private static /* synthetic */ void getReceiver$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object receiveImpl(d<? super Datagram> dVar) {
        ByteBuffer byteBufferBorrow = PoolsKt.getDefaultDatagramByteBufferPool().borrow();
        try {
            java.net.SocketAddress socketAddressReceive = getChannel().receive(byteBufferBorrow);
            if (socketAddressReceive == null) {
                return receiveSuspend(byteBufferBorrow, dVar);
            }
            interestOp(SelectInterest.READ, false);
            byteBufferBorrow.flip();
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
            try {
                OutputArraysJVMKt.writeFully(bytePacketBuilder, byteBufferBorrow);
                Datagram datagram = new Datagram(bytePacketBuilder.build(), JavaSocketAddressUtilsKt.toSocketAddress(socketAddressReceive));
                return datagram;
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        } finally {
            PoolsKt.getDefaultDatagramByteBufferPool().recycle(byteBufferBorrow);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0050 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004e -> B:33:0x0051). Please report as a decompilation issue!!! */
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
    public final java.lang.Object receiveSuspend(java.nio.ByteBuffer r5, d7.d<? super io.ktor.network.sockets.Datagram> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.network.sockets.DatagramSocketImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = (io.ktor.network.sockets.DatagramSocketImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1 r0 = new io.ktor.network.sockets.DatagramSocketImpl$receiveSuspend$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.nio.ByteBuffer r5 = (java.nio.ByteBuffer) r5
            java.lang.Object r1 = r0.L$0
            io.ktor.network.sockets.DatagramSocketImpl r1 = (io.ktor.network.sockets.DatagramSocketImpl) r1
            k2.c.G(r6)
            goto L51
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            r1 = r4
        L39:
            io.ktor.network.selector.SelectInterest r6 = io.ktor.network.selector.SelectInterest.READ
            r1.interestOp(r6, r2)
            io.ktor.network.selector.SelectorManager r3 = r1.getSelector()
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = r3.select(r1, r6, r0)
            e7.a r3 = e7.a.f15033i
            if (r6 != r3) goto L51
            return r3
        L51:
            java.nio.channels.DatagramChannel r6 = r1.getChannel()     // Catch: java.lang.Throwable -> L88
            java.net.SocketAddress r6 = r6.receive(r5)     // Catch: java.lang.Throwable -> L88
            if (r6 != 0) goto L5c
            goto L39
        L5c:
            io.ktor.network.selector.SelectInterest r0 = io.ktor.network.selector.SelectInterest.READ
            r3 = 0
            r1.interestOp(r0, r3)
            r5.flip()
            io.ktor.utils.io.core.BytePacketBuilder r0 = new io.ktor.utils.io.core.BytePacketBuilder
            r1 = 0
            r0.<init>(r1, r2, r1)
            io.ktor.utils.io.core.OutputArraysJVMKt.writeFully(r0, r5)     // Catch: java.lang.Throwable -> L83
            io.ktor.utils.io.core.ByteReadPacket r0 = r0.build()     // Catch: java.lang.Throwable -> L83
            io.ktor.network.sockets.SocketAddress r6 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toSocketAddress(r6)
            io.ktor.network.sockets.Datagram r1 = new io.ktor.network.sockets.Datagram
            r1.<init>(r0, r6)
            io.ktor.utils.io.pool.ObjectPool r6 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r6.recycle(r5)
            return r1
        L83:
            r5 = move-exception
            r0.release()
            throw r5
        L88:
            r6 = move-exception
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.network.util.PoolsKt.getDefaultDatagramByteBufferPool()
            r0.recycle(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSocketImpl.receiveSuspend(java.nio.ByteBuffer, d7.d):java.lang.Object");
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IllegalAccessException, InvocationTargetException {
        this.receiver.cancel(null);
        super.close();
        this.sender.close(null);
    }

    @Override // io.ktor.network.sockets.DatagramReadChannel
    public w<Datagram> getIncoming() {
        return this.receiver;
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

    @Override // io.ktor.network.sockets.DatagramWriteChannel
    public x<Datagram> getOutgoing() {
        return this.sender;
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

    @Override // io.ktor.network.sockets.DatagramReadChannel
    public Object receive(d<? super Datagram> dVar) {
        return BoundDatagramSocket.DefaultImpls.receive(this, dVar);
    }

    @Override // io.ktor.network.sockets.DatagramWriteChannel
    public Object send(Datagram datagram, d<? super t0> dVar) {
        return BoundDatagramSocket.DefaultImpls.send(this, datagram, dVar);
    }

    @Override // io.ktor.network.sockets.NIOSocketImpl, io.ktor.network.selector.SelectableBase, io.ktor.network.selector.Selectable
    public DatagramChannel getChannel() {
        return this.channel;
    }
}
