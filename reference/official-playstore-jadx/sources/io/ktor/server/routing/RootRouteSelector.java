package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/ktor/server/routing/RootRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "rootPath", "", "(Ljava/lang/String;)V", "parts", "", "successEvaluationResult", "Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RootRouteSelector extends RouteSelector {
    private final List<String> parts;
    private final RouteSelectorEvaluation.Success successEvaluationResult;

    /* JADX WARN: Multi-variable type inference failed */
    public RootRouteSelector() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        if (segmentIndex != 0) {
            throw new IllegalStateException("Root selector should be evaluated first.");
        }
        if (this.parts.isEmpty()) {
            return RouteSelectorEvaluation.INSTANCE.getConstant();
        }
        List<String> list = this.parts;
        List<String> segments = context.getSegments();
        if (segments.size() < list.size()) {
            return RouteSelectorEvaluation.INSTANCE.getFailedPath();
        }
        int size = list.size() + segmentIndex;
        while (segmentIndex < size) {
            if (!p.a(segments.get(segmentIndex), list.get(segmentIndex))) {
                return RouteSelectorEvaluation.INSTANCE.getFailedPath();
            }
            segmentIndex++;
        }
        return this.successEvaluationResult;
    }

    public String toString() {
        return x.u0(this.parts, DomExceptionUtils.SEPARATOR, null, null, null, 62);
    }

    public RootRouteSelector(String str) {
        List<RoutingPathSegment> parts = RoutingPath.INSTANCE.parse(str).getParts();
        ArrayList arrayList = new ArrayList(s.U(parts, 10));
        for (RoutingPathSegment routingPathSegment : parts) {
            if (routingPathSegment.getKind() != RoutingPathSegmentKind.Constant) {
                throw new IllegalArgumentException("rootPath should be constant, no wildcards supported.");
            }
            arrayList.add(routingPathSegment.getValue());
        }
        this.parts = arrayList;
        this.successEvaluationResult = new RouteSelectorEvaluation.Success(1.0d, null, arrayList.size(), 2, null);
    }

    public /* synthetic */ RootRouteSelector(String str, int i10, h hVar) {
        this((i10 & 1) != 0 ? "" : str);
    }
}
