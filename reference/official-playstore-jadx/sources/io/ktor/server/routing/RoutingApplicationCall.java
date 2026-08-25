package io.ktor.server.routing;

import androidx.media3.common.MimeTypes;
import d7.j;
import f4.f;
import io.ktor.http.Parameters;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.Attributes;
import ka.k0;
import kotlin.Metadata;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\u00020!8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u001b\u0010\r\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0014\u0010-\u001a\u00020*8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u00101\u001a\u00020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00062"}, d2 = {"Lio/ktor/server/routing/RoutingApplicationCall;", "Lio/ktor/server/application/ApplicationCall;", "Lka/k0;", "engineCall", "Lio/ktor/server/routing/Route;", "route", "Ld7/j;", "coroutineContext", "Lio/ktor/server/request/ApplicationReceivePipeline;", "receivePipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "responsePipeline", "Lio/ktor/http/Parameters;", "parameters", "<init>", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/server/routing/Route;Ld7/j;Lio/ktor/server/request/ApplicationReceivePipeline;Lio/ktor/server/response/ApplicationSendPipeline;Lio/ktor/http/Parameters;)V", "", "toString", "()Ljava/lang/String;", "Lio/ktor/server/application/ApplicationCall;", "getEngineCall", "()Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/routing/Route;", "getRoute", "()Lio/ktor/server/routing/Route;", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lio/ktor/server/routing/RoutingApplicationRequest;", "request", "Lio/ktor/server/routing/RoutingApplicationRequest;", "getRequest", "()Lio/ktor/server/routing/RoutingApplicationRequest;", "Lio/ktor/server/routing/RoutingApplicationResponse;", "response", "Lio/ktor/server/routing/RoutingApplicationResponse;", "getResponse", "()Lio/ktor/server/routing/RoutingApplicationResponse;", "parameters$delegate", "Lx6/s;", "getParameters", "()Lio/ktor/http/Parameters;", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "attributes", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RoutingApplicationCall implements ApplicationCall, k0 {
    private final j coroutineContext;
    private final ApplicationCall engineCall;

    /* JADX INFO: renamed from: parameters$delegate, reason: from kotlin metadata */
    private final s parameters;
    private final RoutingApplicationRequest request;
    private final RoutingApplicationResponse response;
    private final Route route;

    public RoutingApplicationCall(ApplicationCall applicationCall, Route route, j jVar, ApplicationReceivePipeline applicationReceivePipeline, ApplicationSendPipeline applicationSendPipeline, Parameters parameters) {
        this.engineCall = applicationCall;
        this.route = route;
        this.coroutineContext = jVar;
        this.request = new RoutingApplicationRequest(this, applicationReceivePipeline, applicationCall.getRequest());
        this.response = new RoutingApplicationResponse(this, applicationSendPipeline, applicationCall.getResponse());
        this.parameters = f.o(3, new RoutingApplicationCall$parameters$2(this, parameters));
    }

    @Override // io.ktor.server.application.ApplicationCall
    public Application getApplication() {
        return this.engineCall.getApplication();
    }

    @Override // io.ktor.server.application.ApplicationCall
    public Attributes getAttributes() {
        return this.engineCall.getAttributes();
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    public final ApplicationCall getEngineCall() {
        return this.engineCall;
    }

    @Override // io.ktor.server.application.ApplicationCall
    public Parameters getParameters() {
        return (Parameters) this.parameters.getValue();
    }

    public final Route getRoute() {
        return this.route;
    }

    public String toString() {
        return "RoutingApplicationCall(route=" + this.route + ')';
    }

    @Override // io.ktor.server.application.ApplicationCall
    public RoutingApplicationRequest getRequest() {
        return this.request;
    }

    @Override // io.ktor.server.application.ApplicationCall
    public RoutingApplicationResponse getResponse() {
        return this.response;
    }
}
