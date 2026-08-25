package io.ktor.client.request;

import io.ktor.client.engine.HttpClientEngineCapability;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lio/ktor/client/engine/HttpClientEngineCapability;", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestBuilder$setCapability$capabilities$1 extends r implements a<Map<HttpClientEngineCapability<?>, Object>> {
    public static final HttpRequestBuilder$setCapability$capabilities$1 INSTANCE = new HttpRequestBuilder$setCapability$capabilities$1();

    public HttpRequestBuilder$setCapability$capabilities$1() {
        super(0);
    }

    @Override // r7.a
    public final Map<HttpClientEngineCapability<?>, Object> invoke() {
        return new LinkedHashMap();
    }
}
