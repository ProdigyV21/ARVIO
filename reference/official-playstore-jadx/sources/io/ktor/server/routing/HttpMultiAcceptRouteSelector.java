package io.ktor.server.routing;

import androidx.compose.material3.d;
import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.ContentType;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/server/routing/HttpMultiAcceptRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "contentTypes", "", "Lio/ktor/http/ContentType;", "(Ljava/util/List;)V", "getContentTypes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpMultiAcceptRouteSelector extends RouteSelector {
    private final List<ContentType> contentTypes;

    public HttpMultiAcceptRouteSelector(List<ContentType> list) {
        this.contentTypes = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpMultiAcceptRouteSelector copy$default(HttpMultiAcceptRouteSelector httpMultiAcceptRouteSelector, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = httpMultiAcceptRouteSelector.contentTypes;
        }
        return httpMultiAcceptRouteSelector.copy(list);
    }

    public final List<ContentType> component1() {
        return this.contentTypes;
    }

    public final HttpMultiAcceptRouteSelector copy(List<ContentType> contentTypes) {
        return new HttpMultiAcceptRouteSelector(contentTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HttpMultiAcceptRouteSelector) && p.a(this.contentTypes, ((HttpMultiAcceptRouteSelector) other).contentTypes);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) throws BadRequestException {
        Object next;
        String str = context.getCall().getRequest().getHeaders().get(HttpHeaders.INSTANCE.getAccept());
        try {
            List<HeaderValue> andSortContentTypeHeader = HttpHeaderValueParserKt.parseAndSortContentTypeHeader(str);
            if (andSortContentTypeHeader.isEmpty()) {
                return RouteSelectorEvaluation.INSTANCE.getMissing();
            }
            Iterator<T> it = andSortContentTypeHeader.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                HeaderValue headerValue = (HeaderValue) next;
                List<ContentType> list = this.contentTypes;
                if (!(list != null) || !list.isEmpty()) {
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        if (((ContentType) it2.next()).match(headerValue.getValue())) {
                            break loop0;
                        }
                    }
                }
            }
            HeaderValue headerValue2 = (HeaderValue) next;
            return headerValue2 != null ? new RouteSelectorEvaluation.Success(headerValue2.getQuality(), null, 0, 6, null) : RouteSelectorEvaluation.INSTANCE.getFailedParameter();
        } catch (BadContentTypeFormatException e5) {
            throw new BadRequestException(d.C("Illegal Accept header format: ", str), e5);
        }
    }

    public final List<ContentType> getContentTypes() {
        return this.contentTypes;
    }

    public int hashCode() {
        return this.contentTypes.hashCode();
    }

    public String toString() {
        return "(contentTypes:" + this.contentTypes + ')';
    }
}
