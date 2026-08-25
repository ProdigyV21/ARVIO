package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0001HÆ\u0003J\t\u0010\t\u001a\u00020\u0001HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/OrRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "first", "second", "(Lio/ktor/server/routing/RouteSelector;Lio/ktor/server/routing/RouteSelector;)V", "getFirst", "()Lio/ktor/server/routing/RouteSelector;", "getSecond", "component1", "component2", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class OrRouteSelector extends RouteSelector {
    private final RouteSelector first;
    private final RouteSelector second;

    public OrRouteSelector(RouteSelector routeSelector, RouteSelector routeSelector2) {
        this.first = routeSelector;
        this.second = routeSelector2;
    }

    public static /* synthetic */ OrRouteSelector copy$default(OrRouteSelector orRouteSelector, RouteSelector routeSelector, RouteSelector routeSelector2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            routeSelector = orRouteSelector.first;
        }
        if ((i10 & 2) != 0) {
            routeSelector2 = orRouteSelector.second;
        }
        return orRouteSelector.copy(routeSelector, routeSelector2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final RouteSelector getFirst() {
        return this.first;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RouteSelector getSecond() {
        return this.second;
    }

    public final OrRouteSelector copy(RouteSelector first, RouteSelector second) {
        return new OrRouteSelector(first, second);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrRouteSelector)) {
            return false;
        }
        OrRouteSelector orRouteSelector = (OrRouteSelector) other;
        return p.a(this.first, orRouteSelector.first) && p.a(this.second, orRouteSelector.second);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        RouteSelectorEvaluation routeSelectorEvaluationEvaluate = this.first.evaluate(context, segmentIndex);
        return routeSelectorEvaluationEvaluate.getSucceeded() ? routeSelectorEvaluationEvaluate : this.second.evaluate(context, segmentIndex);
    }

    public final RouteSelector getFirst() {
        return this.first;
    }

    public final RouteSelector getSecond() {
        return this.second;
    }

    public int hashCode() {
        return this.second.hashCode() + (this.first.hashCode() * 31);
    }

    public String toString() {
        return "{" + this.first + " | " + this.second + '}';
    }
}
