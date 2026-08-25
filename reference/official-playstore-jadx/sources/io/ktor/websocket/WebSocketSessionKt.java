package io.ktor.websocket;

import androidx.compose.material3.MenuKt;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import io.ktor.websocket.CloseReason;
import io.ktor.websocket.Frame;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a1\u0010\u0004\u001a\u00028\u0000\"\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u0000*\u00020\u00022\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0004\u0010\u0005\u001a3\u0010\u0006\u001a\u0004\u0018\u00018\u0000\"\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u0000*\u00020\u00022\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001f\u0010\n\u001a\u00020\t*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\n\u001a\u00020\t*\u00020\u00022\u0006\u0010\b\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\r\u001a!\u0010\u0010\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a!\u0010\u0010\u001a\u00020\t*\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0014\u001a\u001f\u0010\u0015\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/websocket/WebSocketExtension;", "T", "Lio/ktor/websocket/WebSocketSession;", "Lio/ktor/websocket/WebSocketExtensionFactory;", "extension", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/WebSocketExtensionFactory;)Lio/ktor/websocket/WebSocketExtension;", "extensionOrNull", "", "content", "Lx6/t0;", "send", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "(Lio/ktor/websocket/WebSocketSession;[BLd7/d;)Ljava/lang/Object;", "Lio/ktor/websocket/CloseReason;", "reason", "close", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/websocket/CloseReason;Ld7/d;)Ljava/lang/Object;", "", "cause", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Throwable;Ld7/d;)Ljava/lang/Object;", "closeExceptionally", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebSocketSessionKt {

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketSessionKt$close$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.WebSocketSessionKt", f = "WebSocketSession.kt", l = {MenuKt.InTransitionDuration, 121}, m = "close")
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
            return WebSocketSessionKt.close((WebSocketSession) null, (CloseReason) null, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
    
        if (r5.flush(r0) == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object close(io.ktor.websocket.WebSocketSession r5, io.ktor.websocket.CloseReason r6, d7.d<? super x6.t0> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.websocket.WebSocketSessionKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.websocket.WebSocketSessionKt$close$1 r0 = (io.ktor.websocket.WebSocketSessionKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.WebSocketSessionKt$close$1 r0 = new io.ktor.websocket.WebSocketSessionKt$close$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L59
            goto L59
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$0
            io.ktor.websocket.WebSocketSession r5 = (io.ktor.websocket.WebSocketSession) r5
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L59
            goto L4d
        L3a:
            k2.c.G(r7)
            io.ktor.websocket.Frame$Close r7 = new io.ktor.websocket.Frame$Close     // Catch: java.lang.Throwable -> L59
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L59
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L59
            r0.label = r3     // Catch: java.lang.Throwable -> L59
            java.lang.Object r6 = r5.send(r7, r0)     // Catch: java.lang.Throwable -> L59
            if (r6 != r4) goto L4d
            goto L58
        L4d:
            r6 = 0
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L59
            r0.label = r2     // Catch: java.lang.Throwable -> L59
            java.lang.Object r5 = r5.flush(r0)     // Catch: java.lang.Throwable -> L59
            if (r5 != r4) goto L59
        L58:
            return r4
        L59:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.WebSocketSessionKt.close(io.ktor.websocket.WebSocketSession, io.ktor.websocket.CloseReason, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object close$default(WebSocketSession webSocketSession, CloseReason closeReason, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            closeReason = new CloseReason(CloseReason.Codes.NORMAL, "");
        }
        return close(webSocketSession, closeReason, (d<? super t0>) dVar);
    }

    public static final Object closeExceptionally(WebSocketSession webSocketSession, Throwable th, d<? super t0> dVar) {
        Object objClose = close(webSocketSession, th instanceof CancellationException ? new CloseReason(CloseReason.Codes.NORMAL, "") : new CloseReason(CloseReason.Codes.INTERNAL_ERROR, th.toString()), dVar);
        return objClose == a.f15033i ? objClose : t0.f22605a;
    }

    public static final <T extends WebSocketExtension<?>> T extension(WebSocketSession webSocketSession, WebSocketExtensionFactory<?, T> webSocketExtensionFactory) {
        T t2 = (T) extensionOrNull(webSocketSession, webSocketExtensionFactory);
        if (t2 != null) {
            return t2;
        }
        throw new IllegalStateException(("Extension " + webSocketExtensionFactory + " not found.").toString());
    }

    public static final <T extends WebSocketExtension<?>> T extensionOrNull(WebSocketSession webSocketSession, WebSocketExtensionFactory<?, T> webSocketExtensionFactory) {
        Object next;
        Iterator<T> it = webSocketSession.getExtensions().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((WebSocketExtension) next).getFactory().getKey() == webSocketExtensionFactory.getKey()) {
                break;
            }
        }
        if (next instanceof WebSocketExtension) {
            return (T) next;
        }
        return null;
    }

    public static final Object send(WebSocketSession webSocketSession, String str, d<? super t0> dVar) {
        Object objSend = webSocketSession.send(new Frame.Text(str), dVar);
        return objSend == a.f15033i ? objSend : t0.f22605a;
    }

    public static final Object send(WebSocketSession webSocketSession, byte[] bArr, d<? super t0> dVar) {
        Object objSend = webSocketSession.send(new Frame.Binary(true, bArr), dVar);
        return objSend == a.f15033i ? objSend : t0.f22605a;
    }

    @x6.e
    public static final Object close(WebSocketSession webSocketSession, Throwable th, d<? super t0> dVar) {
        t0 t0Var = t0.f22605a;
        a aVar = a.f15033i;
        if (th == null) {
            Object objClose$default = close$default(webSocketSession, null, dVar, 1, null);
            return objClose$default == aVar ? objClose$default : t0Var;
        }
        Object objCloseExceptionally = closeExceptionally(webSocketSession, th, dVar);
        return objCloseExceptionally == aVar ? objCloseExceptionally : t0Var;
    }
}
