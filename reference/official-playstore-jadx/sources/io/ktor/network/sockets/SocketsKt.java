package io.ktor.network.sockets;

import androidx.media3.extractor.flac.FlacConstants;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\f\u001a\u00020\u000b*\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\r\u001a\u0011\u0010\u0010\u001a\u00020\u000f*\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011\"\u0015\u0010\u0012\u001a\u00020\t*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/network/sockets/ASocket;", "Lx6/t0;", "awaitClosed", "(Lio/ktor/network/sockets/ASocket;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/network/sockets/AReadable;", "Lio/ktor/utils/io/ByteReadChannel;", "openReadChannel", "(Lio/ktor/network/sockets/AReadable;)Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/network/sockets/AWritable;", "", "autoFlush", "Lio/ktor/utils/io/ByteWriteChannel;", "openWriteChannel", "(Lio/ktor/network/sockets/AWritable;Z)Lio/ktor/utils/io/ByteWriteChannel;", "Lio/ktor/network/sockets/Socket;", "Lio/ktor/network/sockets/Connection;", "connection", "(Lio/ktor/network/sockets/Socket;)Lio/ktor/network/sockets/Connection;", "isClosed", "(Lio/ktor/network/sockets/ASocket;)Z", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SocketsKt {

    /* JADX INFO: renamed from: io.ktor.network.sockets.SocketsKt$awaitClosed$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.network.sockets.SocketsKt", f = "Sockets.kt", l = {FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "awaitClosed")
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
            return SocketsKt.awaitClosed(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitClosed(io.ktor.network.sockets.ASocket r4, d7.d<? super x6.t0> r5) {
        /*
            boolean r0 = r5 instanceof io.ktor.network.sockets.SocketsKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.network.sockets.SocketsKt$awaitClosed$1 r0 = (io.ktor.network.sockets.SocketsKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.network.sockets.SocketsKt$awaitClosed$1 r0 = new io.ktor.network.sockets.SocketsKt$awaitClosed$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            io.ktor.network.sockets.ASocket r4 = (io.ktor.network.sockets.ASocket) r4
            k2.c.G(r5)
            goto L45
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r5)
            ka.v1 r5 = r4.getSocketContext()
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r5 = r5.join(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L45
            return r0
        L45:
            ka.v1 r5 = r4.getSocketContext()
            boolean r5 = r5.isCancelled()
            if (r5 != 0) goto L52
            x6.t0 r4 = x6.t0.f22605a
            return r4
        L52:
            ka.v1 r4 = r4.getSocketContext()
            java.util.concurrent.CancellationException r4 = r4.getCancellationException()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketsKt.awaitClosed(io.ktor.network.sockets.ASocket, d7.d):java.lang.Object");
    }

    public static final Connection connection(Socket socket) {
        return new Connection(socket, openReadChannel(socket), openWriteChannel$default(socket, false, 1, null));
    }

    public static final boolean isClosed(ASocket aSocket) {
        return aSocket.getSocketContext().isCompleted();
    }

    public static final ByteReadChannel openReadChannel(AReadable aReadable) {
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(false);
        aReadable.attachForReading(ByteChannel);
        return ByteChannel;
    }

    public static final ByteWriteChannel openWriteChannel(AWritable aWritable, boolean z) {
        ByteChannel ByteChannel = ByteChannelKt.ByteChannel(z);
        aWritable.attachForWriting(ByteChannel);
        return ByteChannel;
    }

    public static /* synthetic */ ByteWriteChannel openWriteChannel$default(AWritable aWritable, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return openWriteChannel(aWritable, z);
    }
}
