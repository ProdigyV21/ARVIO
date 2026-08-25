package io.ktor.client.plugins.contentnegotiation;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$1", f = "ContentNegotiation.kt", l = {251, 252}, m = "invokeSuspend")
public final class ContentNegotiation$Plugin$install$1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
    final /* synthetic */ ContentNegotiation $plugin;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$Plugin$install$1(ContentNegotiation contentNegotiation, d<? super ContentNegotiation$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$plugin = contentNegotiation;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
        ContentNegotiation$Plugin$install$1 contentNegotiation$Plugin$install$1 = new ContentNegotiation$Plugin$install$1(this.$plugin, dVar);
        contentNegotiation$Plugin$install$1.L$0 = pipelineContext;
        return contentNegotiation$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws ContentConverterException {
        PipelineContext pipelineContext;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            pipelineContext = (PipelineContext) this.L$0;
            ContentNegotiation contentNegotiation = this.$plugin;
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) pipelineContext.getContext();
            Object subject = pipelineContext.getSubject();
            this.L$0 = pipelineContext;
            this.label = 1;
            obj = contentNegotiation.convertRequest$ktor_client_content_negotiation(httpRequestBuilder, subject, this);
            if (obj != aVar) {
            }
        }
        if (i10 != 1) {
            if (i10 == 2) {
                c.G(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        pipelineContext = (PipelineContext) this.L$0;
        c.G(obj);
        if (obj == null) {
            return t0Var;
        }
        this.L$0 = null;
        this.label = 2;
        return pipelineContext.proceedWith(obj, this) == aVar ? aVar : t0Var;
    }
}
