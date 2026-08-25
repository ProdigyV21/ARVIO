package io.ktor.network.sockets;

import io.ktor.http.ContentDisposition;
import io.ktor.utils.io.core.ByteBuffersKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a6\u0010\u0007\u001a\u00020\u00052%\u0010\u0006\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\f\u001a\u00020\u0005*\u00020\t2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\r\"\"\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00050\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f\"\"\u0010\u0010\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00050\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Lx6/t0;", "handler", "failInvokeOnClose", "(Lr7/l;)V", "Lio/ktor/network/sockets/Datagram;", "Ljava/nio/ByteBuffer;", "buffer", "writeMessageTo", "(Lio/ktor/network/sockets/Datagram;Ljava/nio/ByteBuffer;)V", "CLOSED", "Lr7/l;", "CLOSED_INVOKED", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DatagramSendChannelKt {
    private static final l<Throwable, t0> CLOSED = DatagramSendChannelKt$CLOSED$1.INSTANCE;
    private static final l<Throwable, t0> CLOSED_INVOKED = DatagramSendChannelKt$CLOSED_INVOKED$1.INSTANCE;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void failInvokeOnClose(l<? super Throwable, t0> lVar) {
        String str;
        if (lVar == CLOSED_INVOKED) {
            str = "Another handler was already registered and successfully invoked";
        } else {
            str = "Another handler was already registered: " + lVar;
        }
        throw new IllegalStateException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeMessageTo(Datagram datagram, ByteBuffer byteBuffer) {
        ByteBuffersKt.readAvailable(datagram.getPacket(), byteBuffer);
        byteBuffer.flip();
    }
}
