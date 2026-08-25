package io.ktor.server.routing;

import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/TrailingSlashRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "()V", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TrailingSlashRouteSelector extends RouteSelector {
    public static final TrailingSlashRouteSelector INSTANCE = new TrailingSlashRouteSelector();

    private TrailingSlashRouteSelector() {
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        return IgnoreTrailingSlashKt.getIgnoreTrailingSlash(context.getCall()) ? RouteSelectorEvaluation.INSTANCE.getTransparent() : context.getSegments().isEmpty() ? RouteSelectorEvaluation.INSTANCE.getConstant() : segmentIndex < a.w(context.getSegments()) ? RouteSelectorEvaluation.INSTANCE.getTransparent() : segmentIndex > a.w(context.getSegments()) ? RouteSelectorEvaluation.INSTANCE.getFailedPath() : context.getSegments().get(segmentIndex).length() > 0 ? RouteSelectorEvaluation.INSTANCE.getTransparent() : context.getHasTrailingSlash() ? RouteSelectorEvaluation.INSTANCE.getConstantPath() : RouteSelectorEvaluation.INSTANCE.getFailedPath();
    }

    public String toString() {
        return "<slash>";
    }
}
