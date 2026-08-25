package io.ktor.websocket.serialization;

import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.util.InternalAPI;
import io.ktor.websocket.WebSocketSession;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a=\u0010\n\u001a\u00020\t\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0087Hø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a=\u0010\n\u001a\u00020\t*\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0087@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000e\u001a5\u0010\u000f\u001a\u0004\u0018\u00010\u0002\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a5\u0010\u000f\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"T", "Lio/ktor/websocket/WebSocketSession;", "", "data", "Lio/ktor/serialization/WebsocketContentConverter;", "converter", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "Lx6/t0;", "sendSerializedBase", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Object;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "(Lio/ktor/websocket/WebSocketSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "receiveDeserializedBase", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/websocket/WebSocketSession;Lio/ktor/util/reflect/TypeInfo;Lio/ktor/serialization/WebsocketContentConverter;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "ktor-websocket-serialization"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebsocketChannelSerializationKt {

    /* JADX INFO: renamed from: io.ktor.websocket.serialization.WebsocketChannelSerializationKt$receiveDeserializedBase$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.serialization.WebsocketChannelSerializationKt", f = "WebsocketChannelSerialization.kt", l = {95, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED}, m = "receiveDeserializedBase")
    public static final class AnonymousClass2 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebsocketChannelSerializationKt.receiveDeserializedBase(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.websocket.serialization.WebsocketChannelSerializationKt", f = "WebsocketChannelSerialization.kt", l = {50, 55}, m = "sendSerializedBase")
    public static final class C20642 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C20642(d<? super C20642> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WebsocketChannelSerializationKt.sendSerializedBase(null, null, null, null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0091 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object receiveDeserializedBase(io.ktor.websocket.WebSocketSession r16, io.ktor.util.reflect.TypeInfo r17, io.ktor.serialization.WebsocketContentConverter r18, java.nio.charset.Charset r19, d7.d<java.lang.Object> r20) throws io.ktor.serialization.WebsocketDeserializeException {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase(io.ktor.websocket.WebSocketSession, io.ktor.util.reflect.TypeInfo, io.ktor.serialization.WebsocketContentConverter, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
    
        if (r5.send((io.ktor.websocket.Frame) r10, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @io.ktor.util.InternalAPI
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object sendSerializedBase(io.ktor.websocket.WebSocketSession r5, java.lang.Object r6, io.ktor.util.reflect.TypeInfo r7, io.ktor.serialization.WebsocketContentConverter r8, java.nio.charset.Charset r9, d7.d<? super x6.t0> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.websocket.serialization.WebsocketChannelSerializationKt.C20642
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2 r0 = (io.ktor.websocket.serialization.WebsocketChannelSerializationKt.C20642) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2 r0 = new io.ktor.websocket.serialization.WebsocketChannelSerializationKt$sendSerializedBase$2
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r10)
            goto L5a
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$0
            io.ktor.websocket.WebSocketSession r5 = (io.ktor.websocket.WebSocketSession) r5
            k2.c.G(r10)
            goto L48
        L3a:
            k2.c.G(r10)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r10 = r8.serializeNullable(r9, r7, r6, r0)
            if (r10 != r4) goto L48
            goto L59
        L48:
            io.ktor.websocket.Frame r10 = (io.ktor.websocket.Frame) r10
            ma.x r5 = r5.getOutgoing()
            r6 = 0
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r5 = r5.send(r10, r0)
            if (r5 != r4) goto L5a
        L59:
            return r4
        L5a:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.serialization.WebsocketChannelSerializationKt.sendSerializedBase(io.ktor.websocket.WebSocketSession, java.lang.Object, io.ktor.util.reflect.TypeInfo, io.ktor.serialization.WebsocketContentConverter, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    @InternalAPI
    public static final <T> Object sendSerializedBase(WebSocketSession webSocketSession, Object obj, WebsocketContentConverter websocketContentConverter, Charset charset, d<? super t0> dVar) {
        p.h();
        throw null;
    }

    @InternalAPI
    public static final <T> Object receiveDeserializedBase(WebSocketSession webSocketSession, WebsocketContentConverter websocketContentConverter, Charset charset, d<Object> dVar) {
        p.h();
        throw null;
    }
}
