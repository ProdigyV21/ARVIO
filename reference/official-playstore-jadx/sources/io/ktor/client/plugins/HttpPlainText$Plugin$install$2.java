package io.ktor.client.plugins;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpPlainText$Plugin$install$2", f = "HttpPlainText.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD, TsExtractor.TS_STREAM_TYPE_DTS}, m = "invokeSuspend")
public final class HttpPlainText$Plugin$install$2 extends j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpPlainText$Plugin$install$2(HttpPlainText httpPlainText, d<? super HttpPlainText$Plugin$install$2> dVar) {
        super(3, dVar);
        this.$plugin = httpPlainText;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
        HttpPlainText$Plugin$install$2 httpPlainText$Plugin$install$2 = new HttpPlainText$Plugin$install$2(this.$plugin, dVar);
        httpPlainText$Plugin$install$2.L$0 = pipelineContext;
        httpPlainText$Plugin$install$2.L$1 = httpResponseContainer;
        return httpPlainText$Plugin$install$2.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        HttpPlainText$Plugin$install$2 httpPlainText$Plugin$install$2;
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
            if (p.a(expectedType.getType(), l0.f19747a.b(String.class)) && (response instanceof ByteReadChannel)) {
                this.L$0 = pipelineContext2;
                this.L$1 = expectedType;
                this.label = 1;
                httpPlainText$Plugin$install$2 = this;
                Object remaining$default = ByteReadChannel.DefaultImpls.readRemaining$default((ByteReadChannel) response, 0L, httpPlainText$Plugin$install$2, 1, null);
                if (remaining$default != aVar) {
                    pipelineContext = pipelineContext2;
                    obj = remaining$default;
                    typeInfo = expectedType;
                }
            }
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
        httpPlainText$Plugin$install$2 = this;
        HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(typeInfo, httpPlainText$Plugin$install$2.$plugin.read$ktor_client_core((HttpClientCall) pipelineContext.getContext(), (ByteReadPacket) obj));
        httpPlainText$Plugin$install$2.L$0 = null;
        httpPlainText$Plugin$install$2.L$1 = null;
        httpPlainText$Plugin$install$2.label = 2;
        return pipelineContext.proceedWith(httpResponseContainer2, this) == aVar ? aVar : t0Var;
    }
}
