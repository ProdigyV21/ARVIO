package io.ktor.network.sockets;

import a8.x1;
import d7.d;
import f7.c;
import io.ktor.http.ContentDisposition;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.core.ByteBuffersKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import ma.m;
import ma.n;
import ma.o;
import ma.x;
import r7.l;
import ua.a;
import ua.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010\u0017\u001a\u00020\u0002H\u0016ø\u0001\u0001ø\u0001\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ4\u0010\"\u001a\u00020\r2#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\u001eH\u0017¢\u0006\u0004\b\"\u0010#R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010'\u001a\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u00148VX\u0097\u0004¢\u0006\f\u0012\u0004\b/\u0010\u0011\u001a\u0004\b-\u0010.R&\u00103\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Lio/ktor/network/sockets/DatagramSendChannel;", "Lma/x;", "Lio/ktor/network/sockets/Datagram;", "Ljava/nio/channels/DatagramChannel;", "channel", "Lio/ktor/network/sockets/DatagramSocketImpl;", "socket", "<init>", "(Ljava/nio/channels/DatagramChannel;Lio/ktor/network/sockets/DatagramSocketImpl;)V", "Ljava/nio/ByteBuffer;", "buffer", "Lio/ktor/network/sockets/SocketAddress;", "address", "Lx6/t0;", "sendSuspend", "(Ljava/nio/ByteBuffer;Lio/ktor/network/sockets/SocketAddress;Ld7/d;)Ljava/lang/Object;", "closeAndCheckHandler", "()V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "element", "Lma/o;", "trySend-JP2dKIU", "(Lio/ktor/network/sockets/Datagram;)Ljava/lang/Object;", "trySend", "send", "(Lio/ktor/network/sockets/Datagram;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "handler", "invokeOnClose", "(Lr7/l;)V", "Ljava/nio/channels/DatagramChannel;", "getChannel", "()Ljava/nio/channels/DatagramChannel;", "Lio/ktor/network/sockets/DatagramSocketImpl;", "getSocket", "()Lio/ktor/network/sockets/DatagramSocketImpl;", "Lua/a;", "lock", "Lua/a;", "isClosedForSend", "()Z", "isClosedForSend$annotations", "Lsa/d;", "getOnSend", "()Lsa/d;", "onSend", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DatagramSendChannel implements x<Datagram> {
    private final DatagramChannel channel;
    private final DatagramSocketImpl socket;
    private static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(DatagramSendChannel.class, Object.class, "onCloseHandler");
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(DatagramSendChannel.class, "closed");
    private volatile /* synthetic */ Object onCloseHandler = null;
    private volatile /* synthetic */ int closed = 0;
    private volatile /* synthetic */ Object closedCause = null;
    private final a lock = e.a();

    /* JADX INFO: renamed from: io.ktor.network.sockets.DatagramSendChannel$send$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.network.sockets.DatagramSendChannel", f = "DatagramSendChannel.kt", l = {160, 76}, m = "send")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DatagramSendChannel.this.send((Datagram) null, (d<? super t0>) this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.network.sockets.DatagramSendChannel", f = "DatagramSendChannel.kt", l = {95}, m = "sendSuspend")
    public static final class C18401 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C18401(d<? super C18401> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DatagramSendChannel.this.sendSuspend(null, null, this);
        }
    }

    public DatagramSendChannel(DatagramChannel datagramChannel, DatagramSocketImpl datagramSocketImpl) {
        this.channel = datagramChannel;
        this.socket = datagramSocketImpl;
    }

    private final void closeAndCheckHandler() {
        while (true) {
            l lVar = (l) this.onCloseHandler;
            if (lVar != DatagramSendChannelKt.CLOSED_INVOKED) {
                if (lVar == null) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
                    l lVar2 = DatagramSendChannelKt.CLOSED;
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, null, lVar2)) {
                        if (atomicReferenceFieldUpdater.get(this) != null) {
                            break;
                        }
                    }
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = onCloseHandler$FU;
                l lVar3 = DatagramSendChannelKt.CLOSED_INVOKED;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, lVar, lVar3)) {
                    if (atomicReferenceFieldUpdater2.get(this) != lVar) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                }
                lVar.invoke(this.closedCause);
                return;
            }
            return;
        }
    }

    public static /* synthetic */ void isClosedForSend$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x005d -> B:18:0x0060). Please report as a decompilation issue!!! */
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
    public final java.lang.Object sendSuspend(java.nio.ByteBuffer r7, io.ktor.network.sockets.SocketAddress r8, d7.d<? super x6.t0> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.network.sockets.DatagramSendChannel.C18401
            if (r0 == 0) goto L13
            r0 = r9
            io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1 r0 = (io.ktor.network.sockets.DatagramSendChannel.C18401) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1 r0 = new io.ktor.network.sockets.DatagramSendChannel$sendSuspend$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            java.lang.Object r7 = r0.L$2
            io.ktor.network.sockets.SocketAddress r7 = (io.ktor.network.sockets.SocketAddress) r7
            java.lang.Object r8 = r0.L$1
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            java.lang.Object r1 = r0.L$0
            io.ktor.network.sockets.DatagramSendChannel r1 = (io.ktor.network.sockets.DatagramSendChannel) r1
            k2.c.G(r9)
            r5 = r8
            r8 = r7
            r7 = r5
            goto L60
        L34:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3c:
            k2.c.G(r9)
            r1 = r6
        L40:
            io.ktor.network.sockets.DatagramSocketImpl r9 = r1.socket
            io.ktor.network.selector.SelectInterest r3 = io.ktor.network.selector.SelectInterest.WRITE
            r9.interestOp(r3, r2)
            io.ktor.network.sockets.DatagramSocketImpl r9 = r1.socket
            io.ktor.network.selector.SelectorManager r9 = r9.getSelector()
            io.ktor.network.sockets.DatagramSocketImpl r4 = r1.socket
            r0.L$0 = r1
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r2
            java.lang.Object r9 = r9.select(r4, r3, r0)
            e7.a r3 = e7.a.f15033i
            if (r9 != r3) goto L60
            return r3
        L60:
            java.nio.channels.DatagramChannel r9 = r1.channel
            java.net.SocketAddress r3 = io.ktor.network.sockets.JavaSocketAddressUtilsKt.toJavaAddress(r8)
            int r9 = r9.send(r7, r3)
            if (r9 == 0) goto L40
            io.ktor.network.sockets.DatagramSocketImpl r7 = r1.socket
            io.ktor.network.selector.SelectInterest r8 = io.ktor.network.selector.SelectInterest.WRITE
            r9 = 0
            r7.interestOp(r8, r9)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSendChannel.sendSuspend(java.nio.ByteBuffer, io.ktor.network.sockets.SocketAddress, d7.d):java.lang.Object");
    }

    @Override // ma.x
    public boolean close(Throwable cause) throws IllegalAccessException, InvocationTargetException {
        if (!closed$FU.compareAndSet(this, 0, 1)) {
            return false;
        }
        this.closedCause = cause;
        if (!this.socket.isClosed()) {
            this.socket.close();
        }
        closeAndCheckHandler();
        return true;
    }

    public final DatagramChannel getChannel() {
        return this.channel;
    }

    public sa.d<Datagram, x<Datagram>> getOnSend() {
        throw new x1("An operation is not implemented: [DatagramSendChannel] doesn't support [onSend] select clause");
    }

    public final DatagramSocketImpl getSocket() {
        return this.socket;
    }

    @Override // ma.x
    public void invokeOnClose(l<? super Throwable, t0> handler) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = onCloseHandler$FU;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, null, handler)) {
            if (atomicReferenceFieldUpdater.get(this) != null) {
                if (this.onCloseHandler != DatagramSendChannelKt.CLOSED) {
                    DatagramSendChannelKt.failInvokeOnClose((l) this.onCloseHandler);
                    return;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = onCloseHandler$FU;
                l lVar = DatagramSendChannelKt.CLOSED;
                l lVar2 = DatagramSendChannelKt.CLOSED_INVOKED;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, lVar, lVar2)) {
                    if (atomicReferenceFieldUpdater2.get(this) != lVar) {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                }
                handler.invoke(this.closedCause);
                return;
            }
        }
    }

    @Override // ma.x
    public boolean isClosedForSend() {
        return this.socket.isClosed();
    }

    @Override // ma.x
    public /* bridge */ /* synthetic */ Object send(Object obj, d dVar) {
        return send((Datagram) obj, (d<? super t0>) dVar);
    }

    @x6.e
    public boolean offer(Datagram datagram) throws Throwable {
        Object objMo6685trySendJP2dKIU = mo6685trySendJP2dKIU((Object) datagram);
        if (!(objMo6685trySendJP2dKIU instanceof n)) {
            return true;
        }
        m mVar = objMo6685trySendJP2dKIU instanceof m ? (m) objMo6685trySendJP2dKIU : null;
        Throwable th = mVar != null ? mVar.f20345a : null;
        if (th == null) {
            return false;
        }
        int i10 = pa.x.f21299a;
        throw th;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object send(io.ktor.network.sockets.Datagram r9, d7.d<? super x6.t0> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.network.sockets.DatagramSendChannel.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.network.sockets.DatagramSendChannel$send$1 r0 = (io.ktor.network.sockets.DatagramSendChannel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.DatagramSendChannel$send$1 r0 = new io.ktor.network.sockets.DatagramSendChannel$send$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L4b
            if (r1 == r3) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r9 = r0.L$0
            ua.a r9 = (ua.a) r9
            k2.c.G(r10)     // Catch: java.lang.Throwable -> L2f
            goto L77
        L2f:
            r10 = move-exception
            goto L81
        L31:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L39:
            java.lang.Object r9 = r0.L$2
            ua.a r9 = (ua.a) r9
            java.lang.Object r1 = r0.L$1
            io.ktor.network.sockets.Datagram r1 = (io.ktor.network.sockets.Datagram) r1
            java.lang.Object r3 = r0.L$0
            io.ktor.network.sockets.DatagramSendChannel r3 = (io.ktor.network.sockets.DatagramSendChannel) r3
            k2.c.G(r10)
            r10 = r9
            r9 = r1
            goto L60
        L4b:
            k2.c.G(r10)
            ua.a r10 = r8.lock
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r1 = r10.c(r0)
            if (r1 != r5) goto L5f
            goto L75
        L5f:
            r3 = r8
        L60:
            ra.c r1 = ka.x0.f19655d     // Catch: java.lang.Throwable -> L7d
            io.ktor.network.sockets.DatagramSendChannel$send$2$1 r6 = new io.ktor.network.sockets.DatagramSendChannel$send$2$1     // Catch: java.lang.Throwable -> L7d
            r6.<init>(r9, r3, r4)     // Catch: java.lang.Throwable -> L7d
            r0.L$0 = r10     // Catch: java.lang.Throwable -> L7d
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L7d
            r0.L$2 = r4     // Catch: java.lang.Throwable -> L7d
            r0.label = r2     // Catch: java.lang.Throwable -> L7d
            java.lang.Object r9 = ka.m0.y(r1, r6, r0)     // Catch: java.lang.Throwable -> L7d
            if (r9 != r5) goto L76
        L75:
            return r5
        L76:
            r9 = r10
        L77:
            r9.b(r4)
            x6.t0 r9 = x6.t0.f22605a
            return r9
        L7d:
            r9 = move-exception
            r7 = r10
            r10 = r9
            r9 = r7
        L81:
            r9.b(r4)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.DatagramSendChannel.send(io.ktor.network.sockets.Datagram, d7.d):java.lang.Object");
    }

    @Override // ma.x
    /* JADX INFO: renamed from: trySend-JP2dKIU, reason: not valid java name and merged with bridge method [inline-methods] */
    public Object mo6685trySendJP2dKIU(Datagram element) {
        if (!this.lock.tryLock()) {
            return o.f20346b;
        }
        try {
            ObjectPool<ByteBuffer> defaultDatagramByteBufferPool = PoolsKt.getDefaultDatagramByteBufferPool();
            ByteBuffer byteBufferBorrow = defaultDatagramByteBufferPool.borrow();
            try {
                ByteBuffer byteBuffer = byteBufferBorrow;
                ByteBuffersKt.readAvailable(element.getPacket().copy(), byteBuffer);
                if (this.channel.send(byteBuffer, JavaSocketAddressUtilsKt.toJavaAddress(element.getAddress())) == 0) {
                    element.getPacket().release();
                }
                return t0.f22605a;
            } finally {
                defaultDatagramByteBufferPool.recycle(byteBufferBorrow);
            }
        } finally {
            this.lock.b(null);
        }
    }
}
