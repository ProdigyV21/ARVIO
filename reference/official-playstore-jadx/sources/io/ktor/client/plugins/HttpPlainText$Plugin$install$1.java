package io.ktor.client.plugins;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpPlainText$Plugin$install$1", f = "HttpPlainText.kt", l = {TsExtractor.TS_STREAM_TYPE_HDMV_DTS}, m = "invokeSuspend")
public final class HttpPlainText$Plugin$install$1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpPlainText$Plugin$install$1(HttpPlainText httpPlainText, d<? super HttpPlainText$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$plugin = httpPlainText;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
        HttpPlainText$Plugin$install$1 httpPlainText$Plugin$install$1 = new HttpPlainText$Plugin$install$1(this.$plugin, dVar);
        httpPlainText$Plugin$install$1.L$0 = pipelineContext;
        httpPlainText$Plugin$install$1.L$1 = obj;
        return httpPlainText$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 == 0) {
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            this.$plugin.addCharsetHeaders$ktor_client_core((HttpRequestBuilder) pipelineContext.getContext());
            if (!(obj2 instanceof String)) {
                return t0Var;
            }
            ContentType contentType = HttpMessagePropertiesKt.contentType((HttpMessageBuilder) pipelineContext.getContext());
            if (contentType != null && !p.a(contentType.getContentType(), ContentType.Text.INSTANCE.getPlain().getContentType())) {
                return t0Var;
            }
            Object objWrapContent = this.$plugin.wrapContent((HttpRequestBuilder) pipelineContext.getContext(), (String) obj2, contentType);
            this.L$0 = null;
            this.label = 1;
            Object objProceedWith = pipelineContext.proceedWith(objWrapContent, this);
            a aVar = a.f15033i;
            if (objProceedWith == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0Var;
    }
}
