package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/application/TransformBodyContext;", "", "requestedType", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/util/reflect/TypeInfo;)V", "getRequestedType", "()Lio/ktor/util/reflect/TypeInfo;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransformBodyContext {
    private final TypeInfo requestedType;

    public TransformBodyContext(TypeInfo typeInfo) {
        this.requestedType = typeInfo;
    }

    public final TypeInfo getRequestedType() {
        return this.requestedType;
    }
}
