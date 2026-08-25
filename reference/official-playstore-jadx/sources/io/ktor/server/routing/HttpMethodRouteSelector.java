package io.ktor.server.routing;

import io.ktor.http.HttpMethod;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lio/ktor/server/routing/HttpMethodRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "method", "Lio/ktor/http/HttpMethod;", "(Lio/ktor/http/HttpMethod;)V", "getMethod", "()Lio/ktor/http/HttpMethod;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpMethodRouteSelector extends RouteSelector {
    private final HttpMethod method;

    public HttpMethodRouteSelector(HttpMethod httpMethod) {
        this.method = httpMethod;
    }

    public static /* synthetic */ HttpMethodRouteSelector copy$default(HttpMethodRouteSelector httpMethodRouteSelector, HttpMethod httpMethod, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            httpMethod = httpMethodRouteSelector.method;
        }
        return httpMethodRouteSelector.copy(httpMethod);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final HttpMethod getMethod() {
        return this.method;
    }

    public final HttpMethodRouteSelector copy(HttpMethod method) {
        return new HttpMethodRouteSelector(method);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HttpMethodRouteSelector) && p.a(this.method, ((HttpMethodRouteSelector) other).method);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        return p.a(ApplicationRequestPropertiesKt.getHttpMethod(context.getCall().getRequest()), this.method) ? RouteSelectorEvaluation.INSTANCE.getConstant() : RouteSelectorEvaluation.INSTANCE.getFailedMethod();
    }

    public final HttpMethod getMethod() {
        return this.method;
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    public String toString() {
        return "(method:" + this.method.getValue() + ')';
    }
}
