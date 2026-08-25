package io.ktor.client;

import d7.d;
import d7.j;
import e7.a;
import f7.e;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.plugins.BodyProgress;
import io.ktor.client.plugins.DefaultResponseValidationKt;
import io.ktor.client.plugins.HttpCallValidator;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.HttpPlainText;
import io.ktor.client.plugins.HttpRedirect;
import io.ktor.client.plugins.HttpRequestLifecycle;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.client.utils.ClientEventsKt;
import io.ktor.client.utils.HttpResponseReceiveFail;
import io.ktor.events.Events;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import k2.c;
import ka.k0;
import ka.l0;
import ka.t;
import ka.u1;
import ka.v1;
import ka.x1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B!\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nB)\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\t\u0010\rJ\u001b\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0016\u001a\u00020\u000b2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0014¢\u0006\u0004\b\u0016\u0010\u0017J*\u0010\u001c\u001a\u00020\u00002\u001b\u0010\u001b\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010%R\u001c\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010&R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u001a\u0010,\u001a\u00020+8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0017\u00101\u001a\u0002008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u0017\u0010;\u001a\u00020:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0017\u0010@\u001a\u00020?8\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0017\u0010E\u001a\u00020D8\u0006¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR\u0017\u0010I\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010LR\u0017\u0010N\u001a\u00020M8\u0006¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010&\u001a\u0004\bR\u0010S\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006T"}, d2 = {"Lio/ktor/client/HttpClient;", "Lka/k0;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lio/ktor/client/engine/HttpClientEngine;", "engine", "Lio/ktor/client/HttpClientConfig;", "Lio/ktor/client/engine/HttpClientEngineConfig;", "userConfig", "<init>", "(Lio/ktor/client/engine/HttpClientEngine;Lio/ktor/client/HttpClientConfig;)V", "", "manageEngine", "(Lio/ktor/client/engine/HttpClientEngine;Lio/ktor/client/HttpClientConfig;Z)V", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "Lio/ktor/client/call/HttpClientCall;", "execute$ktor_client_core", "(Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "execute", "Lio/ktor/client/engine/HttpClientEngineCapability;", "capability", "isSupported", "(Lio/ktor/client/engine/HttpClientEngineCapability;)Z", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "config", "(Lr7/l;)Lio/ktor/client/HttpClient;", "close", "()V", "", "toString", "()Ljava/lang/String;", "Lio/ktor/client/engine/HttpClientEngine;", "getEngine", "()Lio/ktor/client/engine/HttpClientEngine;", "Lio/ktor/client/HttpClientConfig;", "Z", "Lka/t;", "clientJob", "Lka/t;", "Ld7/j;", "coroutineContext", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "Lio/ktor/client/request/HttpRequestPipeline;", "requestPipeline", "Lio/ktor/client/request/HttpRequestPipeline;", "getRequestPipeline", "()Lio/ktor/client/request/HttpRequestPipeline;", "Lio/ktor/client/statement/HttpResponsePipeline;", "responsePipeline", "Lio/ktor/client/statement/HttpResponsePipeline;", "getResponsePipeline", "()Lio/ktor/client/statement/HttpResponsePipeline;", "Lio/ktor/client/request/HttpSendPipeline;", "sendPipeline", "Lio/ktor/client/request/HttpSendPipeline;", "getSendPipeline", "()Lio/ktor/client/request/HttpSendPipeline;", "Lio/ktor/client/statement/HttpReceivePipeline;", "receivePipeline", "Lio/ktor/client/statement/HttpReceivePipeline;", "getReceivePipeline", "()Lio/ktor/client/statement/HttpReceivePipeline;", "Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "engineConfig", "Lio/ktor/client/engine/HttpClientEngineConfig;", "getEngineConfig", "()Lio/ktor/client/engine/HttpClientEngineConfig;", "Lio/ktor/events/Events;", "monitor", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "getConfig$ktor_client_core", "()Lio/ktor/client/HttpClientConfig;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpClient implements k0, Closeable {
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClient.class, "closed");
    private final Attributes attributes;
    private final t clientJob;
    private volatile /* synthetic */ int closed;
    private final HttpClientConfig<HttpClientEngineConfig> config;
    private final j coroutineContext;
    private final HttpClientEngine engine;
    private final HttpClientEngineConfig engineConfig;
    private boolean manageEngine;
    private final Events monitor;
    private final HttpReceivePipeline receivePipeline;
    private final HttpRequestPipeline requestPipeline;
    private final HttpResponsePipeline responsePipeline;
    private final HttpSendPipeline sendPipeline;
    private final HttpClientConfig<? extends HttpClientEngineConfig> userConfig;

    /* JADX INFO: renamed from: io.ktor.client.HttpClient$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Throwable, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (th != null) {
                l0.b(HttpClient.this.getEngine(), null);
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClient$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "call", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.HttpClient$2", f = "HttpClient.kt", l = {144, 146}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends f7.j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = HttpClient.this.new AnonymousClass2(dVar);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = obj;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            PipelineContext pipelineContext;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            a aVar = a.f15033i;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                obj2 = this.L$1;
                if (!(obj2 instanceof HttpClientCall)) {
                    throw new IllegalStateException(("Error: HttpClientCall expected, but found " + obj2 + '(' + kotlin.jvm.internal.l0.f19747a.b(obj2.getClass()) + ").").toString());
                }
                HttpReceivePipeline receivePipeline = HttpClient.this.getReceivePipeline();
                HttpResponse response = ((HttpClientCall) obj2).getResponse();
                this.L$0 = pipelineContext2;
                this.L$1 = obj2;
                this.label = 1;
                Object objExecute = receivePipeline.execute(t0Var, response, this);
                if (objExecute != aVar) {
                    pipelineContext = pipelineContext2;
                    obj = objExecute;
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0Var;
            }
            obj2 = this.L$1;
            pipelineContext = (PipelineContext) this.L$0;
            c.G(obj);
            ((HttpClientCall) obj2).setResponse$ktor_client_core((HttpResponse) obj);
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            return pipelineContext.proceedWith(obj2, this) == aVar ? aVar : t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClient$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.HttpClient$4", f = "HttpClient.kt", l = {177}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends f7.j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass4(d<? super AnonymousClass4> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
            AnonymousClass4 anonymousClass4 = HttpClient.this.new AnonymousClass4(dVar);
            anonymousClass4.L$0 = pipelineContext;
            return anonymousClass4.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [io.ktor.util.pipeline.PipelineContext] */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v8 */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ?? r02 = this.label;
            try {
                if (r02 == 0) {
                    c.G(obj);
                    PipelineContext pipelineContext = (PipelineContext) this.L$0;
                    this.L$0 = pipelineContext;
                    this.label = 1;
                    Object objProceed = pipelineContext.proceed(this);
                    a aVar = a.f15033i;
                    r02 = aVar;
                    if (objProceed == aVar) {
                        return aVar;
                    }
                } else {
                    if (r02 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
                    c.G(obj);
                    r02 = pipelineContext2;
                }
                return t0.f22605a;
            } catch (Throwable th) {
                HttpClient.this.getMonitor().raise(ClientEventsKt.getHttpResponseReceiveFailed(), new HttpResponseReceiveFail(((HttpClientCall) r02.getContext()).getResponse(), th));
                throw th;
            }
        }
    }

    public HttpClient(HttpClientEngine httpClientEngine, HttpClientConfig<? extends HttpClientEngineConfig> httpClientConfig) {
        this.engine = httpClientEngine;
        this.userConfig = httpClientConfig;
        this.closed = 0;
        x1 x1Var = new x1((v1) httpClientEngine.getCoroutineContext().get(u1.f19642i));
        this.clientJob = x1Var;
        this.coroutineContext = httpClientEngine.getCoroutineContext().plus(x1Var);
        this.requestPipeline = new HttpRequestPipeline(httpClientConfig.getDevelopmentMode());
        HttpResponsePipeline httpResponsePipeline = new HttpResponsePipeline(httpClientConfig.getDevelopmentMode());
        this.responsePipeline = httpResponsePipeline;
        HttpSendPipeline httpSendPipeline = new HttpSendPipeline(httpClientConfig.getDevelopmentMode());
        this.sendPipeline = httpSendPipeline;
        this.receivePipeline = new HttpReceivePipeline(httpClientConfig.getDevelopmentMode());
        this.attributes = AttributesJvmKt.Attributes(true);
        this.engineConfig = httpClientEngine.getConfig();
        this.monitor = new Events();
        HttpClientConfig<HttpClientEngineConfig> httpClientConfig2 = new HttpClientConfig<>();
        this.config = httpClientConfig2;
        if (this.manageEngine) {
            x1Var.invokeOnCompletion(new AnonymousClass1());
        }
        httpClientEngine.install(this);
        httpSendPipeline.intercept(HttpSendPipeline.INSTANCE.getReceive(), new AnonymousClass2(null));
        HttpClientConfig.install$default(httpClientConfig2, HttpRequestLifecycle.INSTANCE, null, 2, null);
        HttpClientConfig.install$default(httpClientConfig2, BodyProgress.INSTANCE, null, 2, null);
        if (httpClientConfig.getUseDefaultTransformers()) {
            httpClientConfig2.install("DefaultTransformers", HttpClient$3$1.INSTANCE);
        }
        HttpClientConfig.install$default(httpClientConfig2, HttpSend.INSTANCE, null, 2, null);
        HttpClientConfig.install$default(httpClientConfig2, HttpCallValidator.INSTANCE, null, 2, null);
        if (httpClientConfig.getFollowRedirects()) {
            HttpClientConfig.install$default(httpClientConfig2, HttpRedirect.INSTANCE, null, 2, null);
        }
        httpClientConfig2.plusAssign(httpClientConfig);
        if (httpClientConfig.getUseDefaultTransformers()) {
            HttpClientConfig.install$default(httpClientConfig2, HttpPlainText.INSTANCE, null, 2, null);
        }
        DefaultResponseValidationKt.addDefaultResponseValidation(httpClientConfig2);
        httpClientConfig2.install(this);
        httpResponsePipeline.intercept(HttpResponsePipeline.INSTANCE.getReceive(), new AnonymousClass4(null));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (closed$FU.compareAndSet(this, 0, 1)) {
            Attributes attributes = (Attributes) this.attributes.get(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST());
            Iterator<T> it = attributes.getAllKeys().iterator();
            while (it.hasNext()) {
                Object obj = attributes.get((AttributeKey) it.next());
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                }
            }
            this.clientJob.complete();
            if (this.manageEngine) {
                this.engine.close();
            }
        }
    }

    public final HttpClient config(l<? super HttpClientConfig<?>, t0> block) {
        HttpClientEngine httpClientEngine = this.engine;
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        httpClientConfig.plusAssign(this.userConfig);
        block.invoke(httpClientConfig);
        return new HttpClient(httpClientEngine, httpClientConfig, this.manageEngine);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder r5, d7.d<? super io.ktor.client.call.HttpClientCall> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.HttpClient$execute$1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.HttpClient$execute$1 r0 = (io.ktor.client.HttpClient$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.HttpClient$execute$1 r0 = new io.ktor.client.HttpClient$execute$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L4a
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r6)
            io.ktor.events.Events r6 = r4.monitor
            io.ktor.events.EventDefinition r1 = io.ktor.client.utils.ClientEventsKt.getHttpRequestCreated()
            r6.raise(r1, r5)
            io.ktor.client.request.HttpRequestPipeline r6 = r4.requestPipeline
            java.lang.Object r1 = r5.getBody()
            r0.label = r2
            java.lang.Object r6 = r6.execute(r5, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L4a
            return r5
        L4a:
            io.ktor.client.call.HttpClientCall r6 = (io.ktor.client.call.HttpClientCall) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.HttpClient.execute$ktor_client_core(io.ktor.client.request.HttpRequestBuilder, d7.d):java.lang.Object");
    }

    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final HttpClientConfig<HttpClientEngineConfig> getConfig$ktor_client_core() {
        return this.config;
    }

    @Override // ka.k0
    public j getCoroutineContext() {
        return this.coroutineContext;
    }

    public final HttpClientEngine getEngine() {
        return this.engine;
    }

    public final HttpClientEngineConfig getEngineConfig() {
        return this.engineConfig;
    }

    public final Events getMonitor() {
        return this.monitor;
    }

    public final HttpReceivePipeline getReceivePipeline() {
        return this.receivePipeline;
    }

    public final HttpRequestPipeline getRequestPipeline() {
        return this.requestPipeline;
    }

    public final HttpResponsePipeline getResponsePipeline() {
        return this.responsePipeline;
    }

    public final HttpSendPipeline getSendPipeline() {
        return this.sendPipeline;
    }

    public final boolean isSupported(HttpClientEngineCapability<?> capability) {
        return this.engine.getSupportedCapabilities().contains(capability);
    }

    public String toString() {
        return "HttpClient[" + this.engine + ']';
    }

    public /* synthetic */ HttpClient(HttpClientEngine httpClientEngine, HttpClientConfig httpClientConfig, int i10, h hVar) {
        this(httpClientEngine, (i10 & 2) != 0 ? new HttpClientConfig() : httpClientConfig);
    }

    public HttpClient(HttpClientEngine httpClientEngine, HttpClientConfig<? extends HttpClientEngineConfig> httpClientConfig, boolean z) {
        this(httpClientEngine, httpClientConfig);
        this.manageEngine = z;
    }
}
