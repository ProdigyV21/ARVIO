package io.ktor.client.plugins.contentnegotiation;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.Url;
import io.ktor.serialization.ContentConverterKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import java.nio.charset.Charset;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.contentnegotiation.ContentNegotiation$Plugin$install$2", f = "ContentNegotiation.kt", l = {262, 265}, m = "invokeSuspend")
public final class ContentNegotiation$Plugin$install$2 extends j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
    final /* synthetic */ ContentNegotiation $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentNegotiation$Plugin$install$2(ContentNegotiation contentNegotiation, d<? super ContentNegotiation$Plugin$install$2> dVar) {
        super(3, dVar);
        this.$plugin = contentNegotiation;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
        ContentNegotiation$Plugin$install$2 contentNegotiation$Plugin$install$2 = new ContentNegotiation$Plugin$install$2(this.$plugin, dVar);
        contentNegotiation$Plugin$install$2.L$0 = pipelineContext;
        contentNegotiation$Plugin$install$2.L$1 = httpResponseContainer;
        return contentNegotiation$Plugin$install$2.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ContentNegotiation$Plugin$install$2 contentNegotiation$Plugin$install$2;
        PipelineContext pipelineContext;
        TypeInfo typeInfo;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
            TypeInfo expectedType = httpResponseContainer.getExpectedType();
            Object response = httpResponseContainer.getResponse();
            ContentType contentType = HttpMessagePropertiesKt.contentType(((HttpClientCall) pipelineContext2.getContext()).getResponse());
            if (contentType == null) {
                ContentNegotiationKt.LOGGER.h("Response doesn't have \"Content-Type\" header, skipping ContentNegotiation plugin");
                return t0Var;
            }
            Charset charsetSuitableCharset$default = ContentConverterKt.suitableCharset$default(((HttpClientCall) pipelineContext2.getContext()).getRequest().getHeaders(), null, 1, null);
            ContentNegotiation contentNegotiation = this.$plugin;
            Url url = ((HttpClientCall) pipelineContext2.getContext()).getRequest().getUrl();
            this.L$0 = pipelineContext2;
            this.L$1 = expectedType;
            this.label = 1;
            contentNegotiation$Plugin$install$2 = this;
            Object objConvertResponse$ktor_client_content_negotiation = contentNegotiation.convertResponse$ktor_client_content_negotiation(url, expectedType, response, contentType, charsetSuitableCharset$default, contentNegotiation$Plugin$install$2);
            if (objConvertResponse$ktor_client_content_negotiation != aVar) {
                pipelineContext = pipelineContext2;
                obj = objConvertResponse$ktor_client_content_negotiation;
                typeInfo = expectedType;
            }
            return aVar;
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return t0Var;
        }
        typeInfo = (TypeInfo) this.L$1;
        pipelineContext = (PipelineContext) this.L$0;
        c.G(obj);
        contentNegotiation$Plugin$install$2 = this;
        if (obj != null) {
            HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(typeInfo, obj);
            contentNegotiation$Plugin$install$2.L$0 = null;
            contentNegotiation$Plugin$install$2.L$1 = null;
            contentNegotiation$Plugin$install$2.label = 2;
            if (pipelineContext.proceedWith(httpResponseContainer2, this) == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }
}
