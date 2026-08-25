package io.ktor.client.call;

import d7.d;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u001f\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0004H\u0087Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0005\u001a\u001f\u0010\u0002\u001a\u00020\b*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"T", "Lio/ktor/client/call/HttpClientCall;", "receive", "(Lio/ktor/client/call/HttpClientCall;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpResponse;", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/reflect/TypeInfo;", "info", "", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompatibilityKt {
    @e
    public static final /* synthetic */ <T> Object receive(HttpClientCall httpClientCall, d<? super T> dVar) {
        throw new IllegalStateException("Use `body` method instead");
    }

    @e
    public static final /* synthetic */ <T> Object receive(HttpResponse httpResponse, d<? super T> dVar) {
        throw new IllegalStateException("Use `body` method instead");
    }

    @e
    public static final Object receive(HttpClientCall httpClientCall, TypeInfo typeInfo, d<Object> dVar) {
        throw new IllegalStateException("Use `body` method instead");
    }
}
