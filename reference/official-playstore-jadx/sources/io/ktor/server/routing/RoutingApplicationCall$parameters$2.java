package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Parameters;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RoutingApplicationCall$parameters$2 extends r implements a<Parameters> {
    final /* synthetic */ Parameters $parameters;
    final /* synthetic */ RoutingApplicationCall this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoutingApplicationCall$parameters$2(RoutingApplicationCall routingApplicationCall, Parameters parameters) {
        super(0);
        this.this$0 = routingApplicationCall;
        this.$parameters = parameters;
    }

    @Override // r7.a
    public final Parameters invoke() {
        Parameters.Companion companion = Parameters.INSTANCE;
        RoutingApplicationCall routingApplicationCall = this.this$0;
        Parameters parameters = this.$parameters;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parametersBuilderParametersBuilder$default.appendAll(routingApplicationCall.getEngineCall().getParameters());
        parametersBuilderParametersBuilder$default.appendMissing(parameters);
        return parametersBuilderParametersBuilder$default.build();
    }
}
