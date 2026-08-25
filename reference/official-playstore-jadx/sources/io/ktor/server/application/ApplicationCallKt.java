package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"(\u0010\b\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00028F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"RECEIVE_TYPE_KEY", "Lio/ktor/util/AttributeKey;", "Lio/ktor/util/reflect/TypeInfo;", "isHandled", "", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)Z", "value", "receiveType", "getReceiveType", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/util/reflect/TypeInfo;", "setReceiveType", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/util/reflect/TypeInfo;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationCallKt {
    private static final AttributeKey<TypeInfo> RECEIVE_TYPE_KEY = new AttributeKey<>("ReceiveType");

    public static final TypeInfo getReceiveType(ApplicationCall applicationCall) {
        return (TypeInfo) applicationCall.getAttributes().get(RECEIVE_TYPE_KEY);
    }

    public static final boolean isHandled(ApplicationCall applicationCall) {
        return applicationCall.getResponse().isCommitted();
    }

    public static final void setReceiveType(ApplicationCall applicationCall, TypeInfo typeInfo) {
        applicationCall.getAttributes().put(RECEIVE_TYPE_KEY, typeInfo);
    }
}
