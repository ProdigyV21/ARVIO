package io.ktor.server.engine;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.engine.BaseApplicationResponse$Companion$setupSendPipeline$1", f = "BaseApplicationResponse.kt", l = {317}, m = "invokeSuspend")
public final class BaseApplicationResponse$Companion$setupSendPipeline$1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public BaseApplicationResponse$Companion$setupSendPipeline$1(d<? super BaseApplicationResponse$Companion$setupSendPipeline$1> dVar) {
        super(3, dVar);
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
        BaseApplicationResponse$Companion$setupSendPipeline$1 baseApplicationResponse$Companion$setupSendPipeline$1 = new BaseApplicationResponse$Companion$setupSendPipeline$1(dVar);
        baseApplicationResponse$Companion$setupSendPipeline$1.L$0 = pipelineContext;
        baseApplicationResponse$Companion$setupSendPipeline$1.L$1 = obj;
        return baseApplicationResponse$Companion$setupSendPipeline$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
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
        Object obj2 = this.L$1;
        if (!ApplicationCallKt.isHandled((ApplicationCall) pipelineContext.getContext())) {
            if (!(obj2 instanceof OutgoingContent)) {
                throw new IllegalArgumentException("Response pipeline couldn't transform '" + l0.f19747a.b(obj2.getClass()) + "' to the OutgoingContent");
            }
            ApplicationResponse response = ((ApplicationCall) pipelineContext.getContext()).getResponse();
            BaseApplicationResponse baseApplicationResponse = response instanceof BaseApplicationResponse ? (BaseApplicationResponse) response : null;
            if (baseApplicationResponse == null) {
                baseApplicationResponse = (BaseApplicationResponse) ((ApplicationCall) pipelineContext.getContext()).getAttributes().get(BaseApplicationResponse.INSTANCE.getEngineResponseAttributeKey());
            }
            this.L$0 = null;
            this.label = 1;
            Object objRespondOutgoingContent = baseApplicationResponse.respondOutgoingContent((OutgoingContent) obj2, this);
            a aVar = a.f15033i;
            if (objRespondOutgoingContent == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }
}
