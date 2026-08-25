package io.ktor.client.plugins;

import androidx.media3.extractor.flac.FlacConstants;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import ka.p2;
import ka.t;
import ka.u1;
import ka.v1;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpRequestLifecycle$Plugin$install$1", f = "HttpRequestLifecycle.kt", l = {FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "invokeSuspend")
public final class HttpRequestLifecycle$Plugin$install$1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpRequestLifecycle$Plugin$install$1(HttpClient httpClient, d<? super HttpRequestLifecycle$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$scope = httpClient;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
        HttpRequestLifecycle$Plugin$install$1 httpRequestLifecycle$Plugin$install$1 = new HttpRequestLifecycle$Plugin$install$1(this.$scope, dVar);
        httpRequestLifecycle$Plugin$install$1.L$0 = pipelineContext;
        return httpRequestLifecycle$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        t tVar;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            tVar = (t) this.L$0;
            try {
                c.G(obj);
                tVar.complete();
                return t0.f22605a;
            } catch (Throwable th) {
                th = th;
                try {
                    tVar.i(th);
                    throw th;
                } catch (Throwable th2) {
                    tVar.complete();
                    throw th2;
                }
            }
        }
        c.G(obj);
        PipelineContext pipelineContext = (PipelineContext) this.L$0;
        p2 p2Var = new p2(((HttpRequestBuilder) pipelineContext.getContext()).getExecutionContext());
        HttpRequestLifecycleKt.attachToClientEngineJob(p2Var, (v1) this.$scope.getCoroutineContext().get(u1.f19642i));
        try {
            ((HttpRequestBuilder) pipelineContext.getContext()).setExecutionContext$ktor_client_core(p2Var);
            this.L$0 = p2Var;
            this.label = 1;
            Object objProceed = pipelineContext.proceed(this);
            a aVar = a.f15033i;
            if (objProceed == aVar) {
                return aVar;
            }
            tVar = p2Var;
            tVar.complete();
            return t0.f22605a;
        } catch (Throwable th3) {
            th = th3;
            tVar = p2Var;
            tVar.i(th);
            throw th;
        }
    }
}
