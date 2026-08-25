package io.ktor.client.plugins.websocket;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.websocket.WebSocketSession;
import k2.c;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.websocket.WebSockets$Plugin$install$2", f = "WebSockets.kt", l = {212}, m = "invokeSuspend")
public final class WebSockets$Plugin$install$2 extends j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
    final /* synthetic */ boolean $extensionsSupported;
    final /* synthetic */ WebSockets $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSockets$Plugin$install$2(WebSockets webSockets, boolean z, d<? super WebSockets$Plugin$install$2> dVar) {
        super(3, dVar);
        this.$plugin = webSockets;
        this.$extensionsSupported = z;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
        WebSockets$Plugin$install$2 webSockets$Plugin$install$2 = new WebSockets$Plugin$install$2(this.$plugin, this.$extensionsSupported, dVar);
        webSockets$Plugin$install$2.L$0 = pipelineContext;
        webSockets$Plugin$install$2.L$1 = httpResponseContainer;
        return webSockets$Plugin$install$2.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object delegatingClientWebSocketSession;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return t0Var;
        }
        c.G(obj);
        PipelineContext pipelineContext = (PipelineContext) this.L$0;
        HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
        TypeInfo expectedType = httpResponseContainer.getExpectedType();
        Object response = httpResponseContainer.getResponse();
        HttpResponse response2 = ((HttpClientCall) pipelineContext.getContext()).getResponse();
        HttpStatusCode status = response2.getStatus();
        if (!(HttpResponseKt.getRequest(response2).getContent() instanceof WebSocketContent)) {
            WebSocketsKt.getLOGGER().h("Skipping non-websocket response from " + ((HttpClientCall) pipelineContext.getContext()).getRequest().getUrl() + ": " + response);
            return t0Var;
        }
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        if (!p.a(status, companion.getSwitchingProtocols())) {
            throw new WebSocketException("Handshake exception, expected status code " + companion.getSwitchingProtocols().getValue() + " but was " + status.getValue());
        }
        if (!(response instanceof WebSocketSession)) {
            throw new WebSocketException("Handshake exception, expected `WebSocketSession` content but was " + response);
        }
        WebSocketsKt.getLOGGER().h("Receive websocket session from " + ((HttpClientCall) pipelineContext.getContext()).getRequest().getUrl() + ": " + response);
        if (p.a(expectedType.getType(), l0.f19747a.b(DefaultClientWebSocketSession.class))) {
            DefaultClientWebSocketSession defaultClientWebSocketSession = new DefaultClientWebSocketSession((HttpClientCall) pipelineContext.getContext(), this.$plugin.convertSessionToDefault$ktor_client_core((WebSocketSession) response));
            defaultClientWebSocketSession.start(this.$extensionsSupported ? this.$plugin.completeNegotiation((HttpClientCall) pipelineContext.getContext()) : z.f19728i);
            delegatingClientWebSocketSession = defaultClientWebSocketSession;
        } else {
            delegatingClientWebSocketSession = new DelegatingClientWebSocketSession((HttpClientCall) pipelineContext.getContext(), (WebSocketSession) response);
        }
        HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(expectedType, delegatingClientWebSocketSession);
        this.L$0 = null;
        this.label = 1;
        Object objProceedWith = pipelineContext.proceedWith(httpResponseContainer2, this);
        a aVar = a.f15033i;
        return objProceedWith == aVar ? aVar : t0Var;
    }
}
