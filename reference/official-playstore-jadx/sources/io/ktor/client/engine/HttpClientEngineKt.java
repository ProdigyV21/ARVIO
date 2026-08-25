package io.ktor.client.engine;

import d7.d;
import d7.j;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.engine.UtilsKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import io.ktor.util.AttributeKey;
import java.util.ArrayList;
import java.util.Set;
import ka.j0;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a@\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\r\u001a\u00020\f*\u00020\t2\u0006\u0010\u000b\u001a\u00020\nH\u0080@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\"\u001a\u0010\u0014\u001a\u00020\u00138\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"$\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u00188\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lio/ktor/client/engine/HttpClientEngineFactory;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "nested", "config", "(Lio/ktor/client/engine/HttpClientEngineFactory;Lr7/l;)Lio/ktor/client/engine/HttpClientEngineFactory;", "Lio/ktor/client/engine/HttpClientEngine;", "Lka/v1;", "parentJob", "Ld7/j;", "createCallContext", "(Lio/ktor/client/engine/HttpClientEngine;Lka/v1;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/request/HttpRequestData;", "request", "validateHeaders", "(Lio/ktor/client/request/HttpRequestData;)V", "Lka/j0;", "CALL_COROUTINE", "Lka/j0;", "getCALL_COROUTINE", "()Lka/j0;", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/HttpClientConfig;", "CLIENT_CONFIG", "Lio/ktor/util/AttributeKey;", "getCLIENT_CONFIG", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientEngineKt {
    private static final j0 CALL_COROUTINE = new j0("call-context");
    private static final AttributeKey<HttpClientConfig<?>> CLIENT_CONFIG = new AttributeKey<>("client-config");

    public static final <T extends HttpClientEngineConfig> HttpClientEngineFactory<T> config(final HttpClientEngineFactory<? extends T> httpClientEngineFactory, final l<? super T, t0> lVar) {
        return (HttpClientEngineFactory<T>) new HttpClientEngineFactory<T>() { // from class: io.ktor.client.engine.HttpClientEngineKt.config.1
            @Override // io.ktor.client.engine.HttpClientEngineFactory
            public HttpClientEngine create(l<? super T, t0> block) {
                return httpClientEngineFactory.create(new HttpClientEngineKt$config$1$create$1(lVar, block));
            }
        };
    }

    public static final Object createCallContext(HttpClientEngine httpClientEngine, v1 v1Var, d<? super j> dVar) {
        x1 x1Var = new x1(v1Var);
        j jVarPlus = httpClientEngine.getCoroutineContext().plus(x1Var).plus(CALL_COROUTINE);
        v1 v1Var2 = (v1) dVar.getContext().get(u1.f19642i);
        if (v1Var2 == null) {
            return jVarPlus;
        }
        x1Var.invokeOnCompletion(new UtilsKt.AnonymousClass2(v1Var2.invokeOnCompletion((2 & 1) == 0, (2 & 2) != 0, new UtilsKt$attachToUserJob$cleanupHandler$1(x1Var))));
        return jVarPlus;
    }

    public static final j0 getCALL_COROUTINE() {
        return CALL_COROUTINE;
    }

    public static final AttributeKey<HttpClientConfig<?>> getCLIENT_CONFIG() {
        return CLIENT_CONFIG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateHeaders(HttpRequestData httpRequestData) {
        Set<String> setNames = httpRequestData.getHeaders().names();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setNames) {
            if (HttpHeaders.INSTANCE.getUnsafeHeadersList().contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new UnsafeHeaderException(arrayList.toString());
        }
    }
}
