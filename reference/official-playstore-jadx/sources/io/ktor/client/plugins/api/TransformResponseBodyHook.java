package io.ktor.client.plugins.api;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentDisposition;
import io.ktor.http.content.NullBody;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import k2.c;
import kotlin.Metadata;
import r7.q;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002j\u0012f\u0012d\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0002¢\u0006\u0002\b\u000e0\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0084\u0001\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112h\u0010\u0013\u001ad\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0002¢\u0006\u0002\b\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/client/plugins/api/TransformResponseBodyHook;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function5;", "Lio/ktor/client/plugins/api/TransformResponseBodyContext;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/y;", ContentDisposition.Parameters.Name, "response", "Lio/ktor/utils/io/ByteReadChannel;", "content", "Lio/ktor/util/reflect/TypeInfo;", "requestedType", "Ld7/d;", "", "Lx6/n;", "<init>", "()V", "Lio/ktor/client/HttpClient;", "client", "handler", "Lx6/t0;", "install", "(Lio/ktor/client/HttpClient;Lr7/s;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransformResponseBodyHook implements ClientHook<s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<? super Object>, ? extends Object>> {
    public static final TransformResponseBodyHook INSTANCE = new TransformResponseBodyHook();

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.TransformResponseBodyHook$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.api.TransformResponseBodyHook$install$1", f = "KtorCallContexts.kt", l = {108, 115}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
        final /* synthetic */ s<TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, d<Object>, Object> $handler;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<Object>, ? extends Object> sVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = sVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnonymousClass1 anonymousClass1;
            PipelineContext pipelineContext;
            TypeInfo typeInfo;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            a aVar = a.f15033i;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                HttpResponseContainer httpResponseContainer = (HttpResponseContainer) pipelineContext2.getSubject();
                TypeInfo expectedType = httpResponseContainer.getExpectedType();
                Object response = httpResponseContainer.getResponse();
                if (!(response instanceof ByteReadChannel)) {
                    return t0Var;
                }
                s<TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, d<Object>, Object> sVar = this.$handler;
                TransformResponseBodyContext transformResponseBodyContext = new TransformResponseBodyContext();
                HttpResponse response2 = ((HttpClientCall) pipelineContext2.getContext()).getResponse();
                this.L$0 = pipelineContext2;
                this.L$1 = expectedType;
                this.label = 1;
                anonymousClass1 = this;
                Object objInvoke = sVar.invoke(transformResponseBodyContext, response2, response, expectedType, anonymousClass1);
                if (objInvoke != aVar) {
                    pipelineContext = pipelineContext2;
                    obj = objInvoke;
                    typeInfo = expectedType;
                }
            }
            if (i10 != 1) {
                if (i10 == 2) {
                    c.G(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            typeInfo = (TypeInfo) this.L$1;
            pipelineContext = (PipelineContext) this.L$0;
            c.G(obj);
            anonymousClass1 = this;
            if (obj == null) {
                return t0Var;
            }
            if ((obj instanceof NullBody) || typeInfo.getType().l(obj)) {
                HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(typeInfo, obj);
                anonymousClass1.L$0 = null;
                anonymousClass1.L$1 = null;
                anonymousClass1.label = 2;
                return pipelineContext.proceedWith(httpResponseContainer2, this) == aVar ? aVar : t0Var;
            }
            throw new IllegalStateException("transformResponseBody returned " + obj + " but expected value of type " + typeInfo);
        }
    }

    private TransformResponseBodyHook() {
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public /* bridge */ /* synthetic */ void install(HttpClient httpClient, s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<? super Object>, ? extends Object> sVar) {
        install2(httpClient, (s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<Object>, ? extends Object>) sVar);
    }

    /* JADX INFO: renamed from: install, reason: avoid collision after fix types in other method */
    public void install2(HttpClient client, s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<Object>, ? extends Object> handler) {
        client.getResponsePipeline().intercept(HttpResponsePipeline.INSTANCE.getTransform(), new AnonymousClass1(handler, null));
    }
}
