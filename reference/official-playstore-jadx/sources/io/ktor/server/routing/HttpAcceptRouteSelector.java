package io.ktor.server.routing;

import io.ktor.http.ContentType;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/HttpAcceptRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "contentType", "Lio/ktor/http/ContentType;", "(Lio/ktor/http/ContentType;)V", "getContentType", "()Lio/ktor/http/ContentType;", "delegate", "Lio/ktor/server/routing/HttpMultiAcceptRouteSelector;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpAcceptRouteSelector extends RouteSelector {
    private final ContentType contentType;
    private final HttpMultiAcceptRouteSelector delegate;

    public HttpAcceptRouteSelector(ContentType contentType) {
        this.contentType = contentType;
        this.delegate = new HttpMultiAcceptRouteSelector(Collections.singletonList(contentType));
    }

    public static /* synthetic */ HttpAcceptRouteSelector copy$default(HttpAcceptRouteSelector httpAcceptRouteSelector, ContentType contentType, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = httpAcceptRouteSelector.contentType;
        }
        return httpAcceptRouteSelector.copy(contentType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ContentType getContentType() {
        return this.contentType;
    }

    public final HttpAcceptRouteSelector copy(ContentType contentType) {
        return new HttpAcceptRouteSelector(contentType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HttpAcceptRouteSelector) && p.a(this.contentType, ((HttpAcceptRouteSelector) other).contentType);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        return this.delegate.evaluate(context, segmentIndex);
    }

    public final ContentType getContentType() {
        return this.contentType;
    }

    public int hashCode() {
        return this.contentType.hashCode();
    }

    public String toString() {
        return "(contentType:" + this.contentType + ')';
    }
}
