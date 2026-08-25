package io.ktor.server.routing;

import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/HttpHeaderRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", ContentDisposition.Parameters.Name, "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getValue", "component1", "component2", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpHeaderRouteSelector extends RouteSelector {
    private final String name;
    private final String value;

    public HttpHeaderRouteSelector(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public static /* synthetic */ HttpHeaderRouteSelector copy$default(HttpHeaderRouteSelector httpHeaderRouteSelector, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = httpHeaderRouteSelector.name;
        }
        if ((i10 & 2) != 0) {
            str2 = httpHeaderRouteSelector.value;
        }
        return httpHeaderRouteSelector.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    public final HttpHeaderRouteSelector copy(String name, String value) {
        return new HttpHeaderRouteSelector(name, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpHeaderRouteSelector)) {
            return false;
        }
        HttpHeaderRouteSelector httpHeaderRouteSelector = (HttpHeaderRouteSelector) other;
        return p.a(this.name, httpHeaderRouteSelector.name) && p.a(this.value, httpHeaderRouteSelector.value);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        Object next;
        Iterator<T> it = HttpHeaderValueParserKt.parseAndSortHeader(context.getCall().getRequest().getHeaders().get(this.name)).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (u.L(((HeaderValue) next).getValue(), this.value, true)) {
                break;
            }
        }
        HeaderValue headerValue = (HeaderValue) next;
        return headerValue == null ? RouteSelectorEvaluation.INSTANCE.getFailedParameter() : new RouteSelectorEvaluation.Success(headerValue.getQuality(), null, 0, 6, null);
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() + (this.name.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("(header:");
        sb2.append(this.name);
        sb2.append(" = ");
        return c.u(sb2, this.value, ')');
    }
}
