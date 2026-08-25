package io.ktor.client.plugins.api;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.http.ContentDisposition;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002H\u0012D\u0012B\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJb\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2F\u0010\u0010\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0002H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/api/SendingRequest;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function3;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "Lio/ktor/http/content/OutgoingContent;", "content", "Ld7/d;", "Lx6/t0;", "", "<init>", "()V", "Lio/ktor/client/HttpClient;", "client", "handler", "install", "(Lio/ktor/client/HttpClient;Lr7/q;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SendingRequest implements ClientHook<q<? super HttpRequestBuilder, ? super OutgoingContent, ? super d<? super t0>, ? extends Object>> {
    public static final SendingRequest INSTANCE = new SendingRequest();

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.SendingRequest$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.api.SendingRequest$install$1", f = "CommonHooks.kt", l = {60}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        final /* synthetic */ q<HttpRequestBuilder, OutgoingContent, d<? super t0>, Object> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super HttpRequestBuilder, ? super OutgoingContent, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = qVar;
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
                q<HttpRequestBuilder, OutgoingContent, d<? super t0>, Object> qVar = this.$handler;
                Object context = pipelineContext.getContext();
                OutgoingContent outgoingContent = (OutgoingContent) pipelineContext.getSubject();
                this.label = 1;
                Object objInvoke = qVar.invoke(context, outgoingContent, this);
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

    private SendingRequest() {
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public void install(HttpClient client, q<? super HttpRequestBuilder, ? super OutgoingContent, ? super d<? super t0>, ? extends Object> handler) {
        client.getSendPipeline().intercept(HttpSendPipeline.INSTANCE.getState(), new AnonymousClass1(handler, null));
    }
}
