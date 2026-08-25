package io.ktor.client.plugins.websocket;

import d7.d;
import e7.a;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.serialization.ContentConverterKt;
import io.ktor.serialization.WebsocketContentConverter;
import io.ktor.serialization.WebsocketConverterNotFoundException;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.serialization.WebsocketChannelSerializationKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a)\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\u0006\u001a\u00020\u0005\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\t\u001a%\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\b*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\n\u001a\u00028\u0000\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u0000H\u0086Hø\u0001\u0000¢\u0006\u0004\b\n\u0010\f\"\u0017\u0010\u0010\u001a\u0004\u0018\u00010\r*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;", "", "data", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "Lx6/t0;", "sendSerialized", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "T", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "receiveDeserialized", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/serialization/WebsocketContentConverter;", "getConverter", "(Lio/ktor/client/plugins/websocket/DefaultClientWebSocketSession;)Lio/ktor/serialization/WebsocketContentConverter;", "converter", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ClientSessionsKt {
    public static final WebsocketContentConverter getConverter(DefaultClientWebSocketSession defaultClientWebSocketSession) {
        WebSockets webSockets = (WebSockets) HttpClientPluginKt.pluginOrNull(defaultClientWebSocketSession.getCall().getClient(), WebSockets.INSTANCE);
        if (webSockets != null) {
            return webSockets.getContentConverter();
        }
        return null;
    }

    public static final <T> Object receiveDeserialized(DefaultClientWebSocketSession defaultClientWebSocketSession, TypeInfo typeInfo, d<? super T> dVar) throws WebsocketConverterNotFoundException {
        WebsocketContentConverter converter = getConverter(defaultClientWebSocketSession);
        if (converter != null) {
            return WebsocketChannelSerializationKt.receiveDeserializedBase(defaultClientWebSocketSession, typeInfo, converter, ContentConverterKt.suitableCharset$default(defaultClientWebSocketSession.getCall().getRequest().getHeaders(), null, 1, null), dVar);
        }
        throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
    }

    public static final Object sendSerialized(DefaultClientWebSocketSession defaultClientWebSocketSession, Object obj, TypeInfo typeInfo, d<? super t0> dVar) throws WebsocketConverterNotFoundException {
        WebsocketContentConverter converter = getConverter(defaultClientWebSocketSession);
        if (converter == null) {
            throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
        }
        Object objSendSerializedBase = WebsocketChannelSerializationKt.sendSerializedBase(defaultClientWebSocketSession, obj, typeInfo, converter, ContentConverterKt.suitableCharset$default(defaultClientWebSocketSession.getCall().getRequest().getHeaders(), null, 1, null), dVar);
        return objSendSerializedBase == a.f15033i ? objSendSerializedBase : t0.f22605a;
    }

    public static final <T> Object receiveDeserialized(DefaultClientWebSocketSession defaultClientWebSocketSession, d<? super T> dVar) {
        p.h();
        throw null;
    }

    public static final <T> Object sendSerialized(DefaultClientWebSocketSession defaultClientWebSocketSession, T t2, d<? super t0> dVar) {
        p.h();
        throw null;
    }
}
