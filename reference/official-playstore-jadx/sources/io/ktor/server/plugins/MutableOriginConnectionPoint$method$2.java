package io.ktor.server.plugins;

import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/HttpMethod;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class MutableOriginConnectionPoint$method$2 extends r implements a<HttpMethod> {
    final /* synthetic */ RequestConnectionPoint $delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableOriginConnectionPoint$method$2(RequestConnectionPoint requestConnectionPoint) {
        super(0);
        this.$delegate = requestConnectionPoint;
    }

    @Override // r7.a
    public final HttpMethod invoke() {
        return this.$delegate.getMethod();
    }
}
