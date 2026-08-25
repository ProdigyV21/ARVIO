package io.ktor.client.plugins.api;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.http.ContentDisposition;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002S\u0012O\u0012M\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\f0\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJm\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000f2Q\u0010\u0011\u001aM\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lio/ktor/client/plugins/api/RequestHook;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function4;", "Lio/ktor/client/plugins/api/OnRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "", "content", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "<init>", "()V", "Lio/ktor/client/HttpClient;", "client", "handler", "install", "(Lio/ktor/client/HttpClient;Lr7/r;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RequestHook implements ClientHook<r<? super OnRequestContext, ? super HttpRequestBuilder, ? super Object, ? super d<? super t0>, ? extends Object>> {
    public static final RequestHook INSTANCE = new RequestHook();

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.RequestHook$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.api.RequestHook$install$1", f = "KtorCallContexts.kt", l = {46}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        final /* synthetic */ r<OnRequestContext, HttpRequestBuilder, Object, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(r<? super OnRequestContext, ? super HttpRequestBuilder, Object, ? super d<? super t0>, ? extends Object> rVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = rVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                r<OnRequestContext, HttpRequestBuilder, Object, d<? super t0>, Object> rVar = this.$handler;
                OnRequestContext onRequestContext = new OnRequestContext();
                Object context = pipelineContext.getContext();
                Object subject = pipelineContext.getSubject();
                this.label = 1;
                Object objInvoke = rVar.invoke(onRequestContext, context, subject, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    private RequestHook() {
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public /* bridge */ /* synthetic */ void install(HttpClient httpClient, r<? super OnRequestContext, ? super HttpRequestBuilder, ? super Object, ? super d<? super t0>, ? extends Object> rVar) {
        install2(httpClient, (r<? super OnRequestContext, ? super HttpRequestBuilder, Object, ? super d<? super t0>, ? extends Object>) rVar);
    }

    /* JADX INFO: renamed from: install, reason: avoid collision after fix types in other method */
    public void install2(HttpClient client, r<? super OnRequestContext, ? super HttpRequestBuilder, Object, ? super d<? super t0>, ? extends Object> handler) {
        client.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getState(), new AnonymousClass1(handler, null));
    }
}
