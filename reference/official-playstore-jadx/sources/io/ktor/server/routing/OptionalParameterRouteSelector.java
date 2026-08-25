package io.ktor.server.routing;

import a0.c;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lio/ktor/server/routing/OptionalParameterRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", ContentDisposition.Parameters.Name, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class OptionalParameterRouteSelector extends RouteSelector {
    private final String name;

    public OptionalParameterRouteSelector(String str) {
        this.name = str;
    }

    public static /* synthetic */ OptionalParameterRouteSelector copy$default(OptionalParameterRouteSelector optionalParameterRouteSelector, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = optionalParameterRouteSelector.name;
        }
        return optionalParameterRouteSelector.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final OptionalParameterRouteSelector copy(String name) {
        return new OptionalParameterRouteSelector(name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof OptionalParameterRouteSelector) && p.a(this.name, ((OptionalParameterRouteSelector) other).name);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        List<String> all = context.getCall().getParameters().getAll(this.name);
        return all != null ? new RouteSelectorEvaluation.Success(1.0d, ParametersKt.parametersOf(this.name, all), 0, 4, null) : RouteSelectorEvaluation.INSTANCE.getMissing();
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return c.p(new StringBuilder("["), this.name, "?]");
    }
}
