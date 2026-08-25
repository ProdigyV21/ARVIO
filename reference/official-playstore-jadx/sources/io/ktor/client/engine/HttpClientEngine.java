package io.ktor.client.engine;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.utils.ClientEventsKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.pipeline.PipelineContext;
import java.io.Closeable;
import java.util.Set;
import ka.f0;
import ka.k0;
import ka.u1;
import ka.v1;
import kotlin.Metadata;
import kotlin.collections.b0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003J\u001b\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H§@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c0\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000b\u0010!\u001a\u00020 8BX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/client/engine/HttpClientEngine;", "Lka/k0;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lio/ktor/client/request/HttpRequestData;", "data", "Lio/ktor/client/request/HttpResponseData;", "execute", "(Lio/ktor/client/request/HttpRequestData;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/HttpClient;", "client", "Lx6/t0;", "install", "(Lio/ktor/client/HttpClient;)V", "requestData", "checkExtensions", "(Lio/ktor/client/request/HttpRequestData;)V", "executeWithinCallContext", "(Lio/ktor/client/request/HttpRequestData;)Lio/ktor/client/request/HttpResponseData;", "Lka/f0;", "getDispatcher", "()Lka/f0;", "dispatcher", "Lio/ktor/client/engine/HttpClientEngineConfig;", "getConfig", "()Lio/ktor/client/engine/HttpClientEngineConfig;", "config", "", "Lio/ktor/client/engine/HttpClientEngineCapability;", "getSupportedCapabilities", "()Ljava/util/Set;", "supportedCapabilities", "", "closed", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface HttpClientEngine extends k0, Closeable {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX INFO: Access modifiers changed from: private */
        public static void checkExtensions(HttpClientEngine httpClientEngine, HttpRequestData httpRequestData) {
            for (HttpClientEngineCapability<?> httpClientEngineCapability : httpRequestData.getRequiredCapabilities$ktor_client_core()) {
                if (!httpClientEngine.getSupportedCapabilities().contains(httpClientEngineCapability)) {
                    throw new IllegalArgumentException(("Engine doesn't support " + httpClientEngineCapability).toString());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static java.lang.Object executeWithinCallContext(io.ktor.client.engine.HttpClientEngine r5, io.ktor.client.request.HttpRequestData r6, d7.d<? super io.ktor.client.request.HttpResponseData> r7) {
            /*
                boolean r0 = r7 instanceof io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$1
                if (r0 == 0) goto L13
                r0 = r7
                io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$1 r0 = (io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$1 r0 = new io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$1
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                int r1 = r0.label
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r1 == 0) goto L3f
                if (r1 == r3) goto L32
                if (r1 != r2) goto L2a
                k2.c.G(r7)
                return r7
            L2a:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L32:
                java.lang.Object r5 = r0.L$1
                r6 = r5
                io.ktor.client.request.HttpRequestData r6 = (io.ktor.client.request.HttpRequestData) r6
                java.lang.Object r5 = r0.L$0
                io.ktor.client.engine.HttpClientEngine r5 = (io.ktor.client.engine.HttpClientEngine) r5
                k2.c.G(r7)
                goto L53
            L3f:
                k2.c.G(r7)
                ka.v1 r7 = r6.getExecutionContext()
                r0.L$0 = r5
                r0.L$1 = r6
                r0.label = r3
                java.lang.Object r7 = io.ktor.client.engine.HttpClientEngineKt.createCallContext(r5, r7, r0)
                if (r7 != r4) goto L53
                goto L74
            L53:
                d7.j r7 = (d7.j) r7
                io.ktor.client.engine.KtorCallContextElement r1 = new io.ktor.client.engine.KtorCallContextElement
                r1.<init>(r7)
                d7.j r7 = r7.plus(r1)
                io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$2 r1 = new io.ktor.client.engine.HttpClientEngine$executeWithinCallContext$2
                r3 = 0
                r1.<init>(r5, r6, r3)
                ka.q0 r5 = ka.m0.e(r2, r7, r5, r1)
                r0.L$0 = r3
                r0.L$1 = r3
                r0.label = r2
                java.lang.Object r5 = r5.j(r0)
                if (r5 != r4) goto L75
            L74:
                return r4
            L75:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.HttpClientEngine.DefaultImpls.executeWithinCallContext(io.ktor.client.engine.HttpClientEngine, io.ktor.client.request.HttpRequestData, d7.d):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean getClosed(HttpClientEngine httpClientEngine) {
            return !(((v1) httpClientEngine.getCoroutineContext().get(u1.f19642i)) != null ? r1.isActive() : false);
        }

        public static Set<HttpClientEngineCapability<?>> getSupportedCapabilities(HttpClientEngine httpClientEngine) {
            return b0.f19686i;
        }

        @InternalAPI
        public static void install(HttpClientEngine httpClientEngine, HttpClient httpClient) {
            httpClient.getSendPipeline().intercept(HttpSendPipeline.INSTANCE.getEngine(), new AnonymousClass1(httpClient, httpClientEngine, null));
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.engine.HttpClientEngine$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.engine.HttpClientEngine$install$1", f = "HttpClientEngine.kt", l = {70, 82}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        final /* synthetic */ HttpClient $client;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;
        final /* synthetic */ HttpClientEngine this$0;

        /* JADX INFO: renamed from: io.ktor.client.engine.HttpClientEngine$install$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C02291 extends r implements l<Throwable, t0> {
            final /* synthetic */ HttpClient $client;
            final /* synthetic */ HttpResponse $response;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02291(HttpClient httpClient, HttpResponse httpResponse) {
                super(1);
                this.$client = httpClient;
                this.$response = httpResponse;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return t0.f22605a;
            }

            public final void invoke(Throwable th) {
                if (th != null) {
                    this.$client.getMonitor().raise(ClientEventsKt.getHttpResponseCancelled(), this.$response);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpClient httpClient, HttpClientEngine httpClientEngine, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$client = httpClient;
            this.this$0 = httpClientEngine;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$client, this.this$0, dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x00e2, code lost:
        
            if (r2.proceedWith(r5, r10) == r4) goto L23;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 232
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.HttpClientEngine.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @InternalAPI
    Object execute(HttpRequestData httpRequestData, d<? super HttpResponseData> dVar);

    HttpClientEngineConfig getConfig();

    @Override // ka.k0
    /* synthetic */ d7.j getCoroutineContext();

    f0 getDispatcher();

    Set<HttpClientEngineCapability<?>> getSupportedCapabilities();

    @InternalAPI
    void install(HttpClient client);
}
