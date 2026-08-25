package io.ktor.server.cio;

import androidx.compose.runtime.ComposerKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import d7.k;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.cio.Request;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.cio.backend.ServerRequestScope;
import io.ktor.server.cio.internal.CoroutineUtilsJvmKt;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.BaseApplicationEngine;
import io.ktor.server.engine.EngineConnectorConfig;
import io.ktor.server.engine.ShutdownHookJvmKt;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.ktor.util.reflect.TypeInfoJvmKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import java.util.List;
import java.util.Locale;
import k2.c;
import ka.f0;
import ka.k0;
import ka.m0;
import ka.r;
import ka.t;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001;B(\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0016\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\b!\u0010\"J\u001f\u0010%\u001a\u00020\u0006*\u00020#2\u0006\u0010\u001f\u001a\u00020$H\u0082@ø\u0001\u0000¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0002¢\u0006\u0004\b(\u0010)J\u0017\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020 H\u0016¢\u0006\u0004\b,\u0010-J\u001f\u0010.\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016¢\u0006\u0004\b.\u0010\u000fR\u0014\u0010/\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00103R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u0006058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010:\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"Lio/ktor/server/cio/CIOApplicationEngine;", "Lio/ktor/server/engine/BaseApplicationEngine;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environment", "Lkotlin/Function1;", "Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "Lx6/t0;", "Lx6/n;", "configure", "<init>", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lr7/l;)V", "", "gracePeriodMillis", "timeoutMillis", "shutdownServer", "(JJ)V", "Lka/k0;", "", "host", "", "port", "Lio/ktor/server/cio/HttpServer;", "startConnector", "(Lka/k0;Ljava/lang/String;I)Lio/ktor/server/cio/HttpServer;", "Lio/ktor/utils/io/ByteWriteChannel;", "output", "Lio/ktor/server/cio/CIOApplicationCall;", "call", "addHandlerForExpectedHeader", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/server/cio/CIOApplicationCall;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/server/cio/CIOApplicationRequest;", "request", "", "hasBody", "(Lio/ktor/server/cio/CIOApplicationRequest;)Z", "Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "handleRequest", "(Lio/ktor/server/cio/backend/ServerRequestScope;Lio/ktor/http/cio/Request;Ld7/d;)Ljava/lang/Object;", "Lka/v1;", "initServerJob", "()Lka/v1;", "wait", "Lio/ktor/server/engine/ApplicationEngine;", TtmlNode.START, "(Z)Lio/ktor/server/engine/ApplicationEngine;", "stop", "configuration", "Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "Lka/f0;", "engineDispatcher", "Lka/f0;", "userDispatcher", "Lka/r;", "startupJob", "Lka/r;", "Lka/t;", "stopRequest", "Lka/t;", "Configuration", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOApplicationEngine extends BaseApplicationEngine {
    private final Configuration configuration;
    private final f0 engineDispatcher;
    private volatile /* synthetic */ Object serverJob$delegate;
    private final r<t0> startupJob;
    private final t stopRequest;
    private final f0 userDispatcher;

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "cause", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<Throwable, t0> {
        final /* synthetic */ ApplicationEngineEnvironment $environment;
        final /* synthetic */ CIOApplicationEngine this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ApplicationEngineEnvironment applicationEngineEnvironment, CIOApplicationEngine cIOApplicationEngine) {
            super(1);
            this.$environment = applicationEngineEnvironment;
            this.this$0 = cIOApplicationEngine;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) {
            if (th != null) {
                this.this$0.stopRequest.i(th);
            }
            if (th != null) {
                this.this$0.startupJob.i(th);
            }
            this.$environment.stop();
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/cio/CIOApplicationEngine$Configuration;", "Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "()V", "connectionIdleTimeoutSeconds", "", "getConnectionIdleTimeoutSeconds", "()I", "setConnectionIdleTimeoutSeconds", "(I)V", "reuseAddress", "", "getReuseAddress", "()Z", "setReuseAddress", "(Z)V", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Configuration extends BaseApplicationEngine.Configuration {
        private int connectionIdleTimeoutSeconds = 45;
        private boolean reuseAddress;

        public final int getConnectionIdleTimeoutSeconds() {
            return this.connectionIdleTimeoutSeconds;
        }

        public final boolean getReuseAddress() {
            return this.reuseAddress;
        }

        public final void setConnectionIdleTimeoutSeconds(int i10) {
            this.connectionIdleTimeoutSeconds = i10;
        }

        public final void setReuseAddress(boolean z) {
            this.reuseAddress = z;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$addHandlerForExpectedHeader$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$addHandlerForExpectedHeader$2", f = "CIOApplicationEngine.kt", l = {244, 146}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        final /* synthetic */ CIOApplicationCall $call;
        final /* synthetic */ String $continueResponse;
        final /* synthetic */ String $expectHeaderValue;
        final /* synthetic */ ByteWriteChannel $output;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ CIOApplicationEngine this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CIOApplicationCall cIOApplicationCall, CIOApplicationEngine cIOApplicationEngine, String str, ByteWriteChannel byteWriteChannel, String str2, d<? super AnonymousClass2> dVar) {
            super(3, dVar);
            this.$call = cIOApplicationCall;
            this.this$0 = cIOApplicationEngine;
            this.$expectHeaderValue = str;
            this.$output = byteWriteChannel;
            this.$continueResponse = str2;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            return new AnonymousClass2(this.$call, this.this$0, this.$expectHeaderValue, this.$output, this.$continueResponse, dVar).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ByteWriteChannel byteWriteChannel;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                c.G(obj);
                CIOApplicationRequest request = this.$call.getRequest();
                HttpProtocolVersion.Companion companion = HttpProtocolVersion.INSTANCE;
                HttpProtocolVersion httpProtocolVersion = companion.parse(ApplicationRequestPropertiesKt.getHttpVersion(request));
                String str = this.$call.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getExpect());
                String lowerCase = str != null ? str.toLowerCase(Locale.ROOT) : null;
                boolean zHasBody = this.this$0.hasBody(request);
                if (lowerCase != null && !p.a(httpProtocolVersion, companion.getHTTP_1_0()) && zHasBody) {
                    boolean zEquals = lowerCase.equals(this.$expectHeaderValue);
                    a aVar = a.f15033i;
                    if (zEquals) {
                        ByteWriteChannel byteWriteChannel2 = this.$output;
                        String str2 = this.$continueResponse;
                        this.L$0 = byteWriteChannel2;
                        this.L$1 = byteWriteChannel2;
                        this.label = 2;
                        if (ByteWriteChannelKt.writeStringUtf8(byteWriteChannel2, str2, (d<? super t0>) this) != aVar) {
                            byteWriteChannel = byteWriteChannel2;
                        }
                    } else {
                        CIOApplicationCall cIOApplicationCall = this.$call;
                        HttpStatusCode expectationFailed = HttpStatusCode.INSTANCE.getExpectationFailed();
                        if (!(expectationFailed instanceof byte[])) {
                            ApplicationResponse response = cIOApplicationCall.getResponse();
                            kotlin.reflect.q qVarA = l0.a(HttpStatusCode.class);
                            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA), l0.f19747a.b(HttpStatusCode.class), qVarA));
                        }
                        ApplicationSendPipeline pipeline = cIOApplicationCall.getResponse().getPipeline();
                        this.label = 1;
                        if (pipeline.execute(cIOApplicationCall, expectationFailed, this) == aVar) {
                        }
                    }
                    return aVar;
                }
                return t0Var;
            }
            if (i10 == 1) {
                c.G(obj);
                return t0Var;
            }
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            byteWriteChannel = (ByteWriteChannel) this.L$1;
            c.G(obj);
            byteWriteChannel.flush();
            return t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$handleRequest$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$handleRequest$2", f = "CIOApplicationEngine.kt", l = {174, 239, 177}, m = "invokeSuspend")
    public static final class C18572 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ Request $request;
        final /* synthetic */ ServerRequestScope $this_handleRequest;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18572(Request request, ServerRequestScope serverRequestScope, d<? super C18572> dVar) {
            super(2, dVar);
            this.$request = request;
            this.$this_handleRequest = serverRequestScope;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return CIOApplicationEngine.this.new C18572(this.$request, this.$this_handleRequest, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x00a4  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00b6  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
            /*
                r16 = this;
                r1 = r16
                int r0 = r1.label
                r2 = 3
                r3 = 2
                r4 = 1
                e7.a r5 = e7.a.f15033i
                if (r0 == 0) goto L3f
                if (r0 == r4) goto L34
                if (r0 == r3) goto L26
                if (r0 != r2) goto L1e
                java.lang.Object r0 = r1.L$0
                r2 = r0
                io.ktor.server.cio.CIOApplicationCall r2 = (io.ktor.server.cio.CIOApplicationCall) r2
                k2.c.G(r17)     // Catch: java.lang.Throwable -> L1b
                goto Lb7
            L1b:
                r0 = move-exception
                goto Lbf
            L1e:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L26:
                java.lang.Object r0 = r1.L$0
                r3 = r0
                io.ktor.server.cio.CIOApplicationCall r3 = (io.ktor.server.cio.CIOApplicationCall) r3
                k2.c.G(r17)     // Catch: java.lang.Throwable -> L30
                goto La5
            L30:
                r0 = move-exception
                r4 = r3
                goto Lab
            L34:
                java.lang.Object r0 = r1.L$0
                r4 = r0
                io.ktor.server.cio.CIOApplicationCall r4 = (io.ktor.server.cio.CIOApplicationCall) r4
                k2.c.G(r17)     // Catch: java.lang.Throwable -> L3d
                goto L8d
            L3d:
                r0 = move-exception
                goto Lab
            L3f:
                k2.c.G(r17)
                io.ktor.server.cio.CIOApplicationCall r6 = new io.ktor.server.cio.CIOApplicationCall
                io.ktor.server.cio.CIOApplicationEngine r0 = io.ktor.server.cio.CIOApplicationEngine.this
                io.ktor.server.application.Application r7 = r0.getApplication()
                io.ktor.http.cio.Request r8 = r1.$request
                io.ktor.server.cio.backend.ServerRequestScope r0 = r1.$this_handleRequest
                io.ktor.utils.io.ByteReadChannel r9 = r0.getInput()
                io.ktor.server.cio.backend.ServerRequestScope r0 = r1.$this_handleRequest
                io.ktor.utils.io.ByteWriteChannel r10 = r0.getOutput()
                io.ktor.server.cio.CIOApplicationEngine r0 = io.ktor.server.cio.CIOApplicationEngine.this
                ka.f0 r11 = io.ktor.server.cio.CIOApplicationEngine.access$getEngineDispatcher$p(r0)
                io.ktor.server.cio.CIOApplicationEngine r0 = io.ktor.server.cio.CIOApplicationEngine.this
                ka.f0 r12 = io.ktor.server.cio.CIOApplicationEngine.access$getUserDispatcher$p(r0)
                io.ktor.server.cio.backend.ServerRequestScope r0 = r1.$this_handleRequest
                ka.r r13 = r0.getUpgraded()
                io.ktor.server.cio.backend.ServerRequestScope r0 = r1.$this_handleRequest
                java.net.SocketAddress r14 = r0.getRemoteAddress()
                io.ktor.server.cio.backend.ServerRequestScope r0 = r1.$this_handleRequest
                java.net.SocketAddress r15 = r0.getLocalAddress()
                r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15)
                io.ktor.server.cio.CIOApplicationEngine r0 = io.ktor.server.cio.CIOApplicationEngine.this     // Catch: java.lang.Throwable -> La9
                io.ktor.server.cio.backend.ServerRequestScope r7 = r1.$this_handleRequest     // Catch: java.lang.Throwable -> La9
                io.ktor.utils.io.ByteWriteChannel r7 = r7.getOutput()     // Catch: java.lang.Throwable -> La9
                r1.L$0 = r6     // Catch: java.lang.Throwable -> La9
                r1.label = r4     // Catch: java.lang.Throwable -> La9
                java.lang.Object r0 = io.ktor.server.cio.CIOApplicationEngine.access$addHandlerForExpectedHeader(r0, r7, r6, r1)     // Catch: java.lang.Throwable -> La9
                if (r0 != r5) goto L8c
                goto Lb5
            L8c:
                r4 = r6
            L8d:
                io.ktor.server.cio.CIOApplicationEngine r0 = io.ktor.server.cio.CIOApplicationEngine.this     // Catch: java.lang.Throwable -> L3d
                io.ktor.server.engine.EnginePipeline r0 = r0.getPipeline()     // Catch: java.lang.Throwable -> L3d
                io.ktor.server.cio.CIOApplicationEngine$handleRequest$2$invokeSuspend$$inlined$execute$1 r6 = new io.ktor.server.cio.CIOApplicationEngine$handleRequest$2$invokeSuspend$$inlined$execute$1     // Catch: java.lang.Throwable -> L3d
                r7 = 0
                r6.<init>(r0, r4, r7)     // Catch: java.lang.Throwable -> L3d
                r1.L$0 = r4     // Catch: java.lang.Throwable -> L3d
                r1.label = r3     // Catch: java.lang.Throwable -> L3d
                java.lang.Object r0 = io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(r6, r1)     // Catch: java.lang.Throwable -> L3d
                if (r0 != r5) goto La4
                goto Lb5
            La4:
                r3 = r4
            La5:
                r3.release$ktor_server_cio()
                goto Lba
            La9:
                r0 = move-exception
                r4 = r6
            Lab:
                r1.L$0 = r4     // Catch: java.lang.Throwable -> Lbd
                r1.label = r2     // Catch: java.lang.Throwable -> Lbd
                java.lang.Object r0 = io.ktor.server.engine.DefaultEnginePipelineKt.handleFailure(r4, r0, r1)     // Catch: java.lang.Throwable -> Lbd
                if (r0 != r5) goto Lb6
            Lb5:
                return r5
            Lb6:
                r2 = r4
            Lb7:
                r2.release$ktor_server_cio()
            Lba:
                x6.t0 r0 = x6.t0.f22605a
                return r0
            Lbd:
                r0 = move-exception
                r2 = r4
            Lbf:
                r2.release$ktor_server_cio()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationEngine.C18572.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18572) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$initServerJob$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$initServerJob$1", f = "CIOApplicationEngine.kt", l = {ComposerKt.referenceKey, 216, 227, 232}, m = "invokeSuspend")
    public static final class C18581 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ r<List<EngineConnectorConfig>> $cioConnectors;
        final /* synthetic */ ApplicationEngineEnvironment $environment;
        final /* synthetic */ r<t0> $startupJob;
        final /* synthetic */ t $stopRequest;
        final /* synthetic */ f0 $userDispatcher;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ CIOApplicationEngine this$0;

        /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$initServerJob$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.cio.CIOApplicationEngine$initServerJob$1$2", f = "CIOApplicationEngine.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends j implements r7.p<k0, d<? super t0>, Object> {
            final /* synthetic */ ApplicationEngineEnvironment $environment;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ApplicationEngineEnvironment applicationEngineEnvironment, d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.$environment = applicationEngineEnvironment;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass2(this.$environment, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                this.$environment.start();
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$initServerJob$1$6, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.cio.CIOApplicationEngine$initServerJob$1$6", f = "CIOApplicationEngine.kt", l = {}, m = "invokeSuspend")
        public static final class AnonymousClass6 extends j implements r7.p<k0, d<? super t0>, Object> {
            final /* synthetic */ ApplicationEngineEnvironment $environment;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass6(ApplicationEngineEnvironment applicationEngineEnvironment, d<? super AnonymousClass6> dVar) {
                super(2, dVar);
                this.$environment = applicationEngineEnvironment;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new AnonymousClass6(this.$environment, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                this.$environment.getMonitor().raise(DefaultApplicationEventsKt.getApplicationStopPreparing(), this.$environment);
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((AnonymousClass6) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18581(ApplicationEngineEnvironment applicationEngineEnvironment, f0 f0Var, r<List<EngineConnectorConfig>> rVar, t tVar, r<t0> rVar2, CIOApplicationEngine cIOApplicationEngine, d<? super C18581> dVar) {
            super(2, dVar);
            this.$environment = applicationEngineEnvironment;
            this.$userDispatcher = f0Var;
            this.$cioConnectors = rVar;
            this.$stopRequest = tVar;
            this.$startupJob = rVar2;
            this.this$0 = cIOApplicationEngine;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C18581 c18581 = new C18581(this.$environment, this.$userDispatcher, this.$cioConnectors, this.$stopRequest, this.$startupJob, this.this$0, dVar);
            c18581.L$0 = obj;
            return c18581;
        }

        /* JADX WARN: Not initialized variable reg: 11, insn: 0x01d2: INVOKE (r0 I:java.util.Iterator) = (r11 I:java.lang.Iterable) INTERFACE call: java.lang.Iterable.iterator():java.util.Iterator A[MD:():java.util.Iterator<T> (c)] (LINE:467), block:B:66:0x01d2 */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0121 A[Catch: all -> 0x0045, TryCatch #0 {all -> 0x0045, blocks: (B:14:0x0040, B:48:0x0148, B:42:0x011b, B:44:0x0121, B:49:0x0152, B:50:0x0161, B:52:0x0167, B:53:0x0185, B:19:0x0051, B:33:0x00b2, B:34:0x00c7, B:36:0x00cd, B:37:0x00e8, B:38:0x00f5, B:40:0x00fb, B:41:0x0109, B:22:0x006c, B:23:0x0076, B:25:0x007c, B:28:0x0093, B:29:0x009a, B:30:0x009b), top: B:72:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0152 A[Catch: all -> 0x0045, TryCatch #0 {all -> 0x0045, blocks: (B:14:0x0040, B:48:0x0148, B:42:0x011b, B:44:0x0121, B:49:0x0152, B:50:0x0161, B:52:0x0167, B:53:0x0185, B:19:0x0051, B:33:0x00b2, B:34:0x00c7, B:36:0x00cd, B:37:0x00e8, B:38:0x00f5, B:40:0x00fb, B:41:0x0109, B:22:0x006c, B:23:0x0076, B:25:0x007c, B:28:0x0093, B:29:0x009a, B:30:0x009b), top: B:72:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x01af A[LOOP:0: B:59:0x01a9->B:61:0x01af, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01d1 A[RETURN] */
        /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Iterable] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0147 -> B:48:0x0148). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 501
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationEngine.C18581.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18581) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1", f = "CIOApplicationEngine.kt", l = {92, 101}, m = "invokeSuspend")
    public static final class C18591 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ long $gracePeriodMillis;
        final /* synthetic */ long $timeoutMillis;
        int label;
        final /* synthetic */ CIOApplicationEngine this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18591(long j10, CIOApplicationEngine cIOApplicationEngine, long j11, d<? super C18591> dVar) {
            super(2, dVar);
            this.$gracePeriodMillis = j10;
            this.this$0 = cIOApplicationEngine;
            this.$timeoutMillis = j11;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C18591(this.$gracePeriodMillis, this.this$0, this.$timeoutMillis, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
        
            if (r10 == r4) goto L17;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
            /*
                r9 = this;
                int r0 = r9.label
                r1 = 2
                r2 = 0
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L1d
                if (r0 == r3) goto L19
                if (r0 != r1) goto L11
                k2.c.G(r10)
                goto L54
            L11:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L19:
                k2.c.G(r10)
                goto L32
            L1d:
                k2.c.G(r10)
                long r5 = r9.$gracePeriodMillis
                io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1$result$1 r10 = new io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1$result$1
                io.ktor.server.cio.CIOApplicationEngine r0 = r9.this$0
                r10.<init>(r0, r2)
                r9.label = r3
                java.lang.Object r10 = ka.m0.A(r5, r10, r9)
                if (r10 != r4) goto L32
                goto L53
            L32:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                if (r10 != 0) goto L67
                io.ktor.server.cio.CIOApplicationEngine r10 = r9.this$0
                ka.v1 r10 = io.ktor.server.cio.CIOApplicationEngine.access$getServerJob(r10)
                r10.cancel(r2)
                long r5 = r9.$timeoutMillis
                long r7 = r9.$gracePeriodMillis
                long r5 = r5 - r7
                io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1$forceShutdown$1 r10 = new io.ktor.server.cio.CIOApplicationEngine$shutdownServer$1$forceShutdown$1
                io.ktor.server.cio.CIOApplicationEngine r0 = r9.this$0
                r10.<init>(r0, r2)
                r9.label = r1
                java.lang.Object r10 = ka.m0.A(r5, r10, r9)
                if (r10 != r4) goto L54
            L53:
                return r4
            L54:
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                if (r10 == 0) goto L5c
                boolean r3 = r10.booleanValue()
            L5c:
                if (r3 == 0) goto L67
                io.ktor.server.cio.CIOApplicationEngine r10 = r9.this$0
                io.ktor.server.engine.ApplicationEngineEnvironment r10 = r10.getEnvironment()
                r10.stop()
            L67:
                x6.t0 r10 = x6.t0.f22605a
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationEngine.C18591.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18591) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$start$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18601 extends kotlin.jvm.internal.r implements r7.a<t0> {
        public C18601() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() throws Throwable {
            m6710invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6710invoke() throws Throwable {
            CIOApplicationEngine cIOApplicationEngine = CIOApplicationEngine.this;
            cIOApplicationEngine.stop(cIOApplicationEngine.configuration.getShutdownGracePeriod(), CIOApplicationEngine.this.configuration.getShutdownTimeout());
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$start$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$start$2", f = "CIOApplicationEngine.kt", l = {73, 77}, m = "invokeSuspend")
    public static final class C18612 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ boolean $wait;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18612(boolean z, d<? super C18612> dVar) {
            super(2, dVar);
            this.$wait = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return CIOApplicationEngine.this.new C18612(this.$wait, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x005f, code lost:
        
            if (r6.join(r5) == r3) goto L17;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                int r0 = r5.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1c
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r6)
                goto L62
            L10:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L18:
                k2.c.G(r6)
                goto L2e
            L1c:
                k2.c.G(r6)
                io.ktor.server.cio.CIOApplicationEngine r6 = io.ktor.server.cio.CIOApplicationEngine.this
                ka.r r6 = io.ktor.server.cio.CIOApplicationEngine.access$getStartupJob$p(r6)
                r5.label = r2
                java.lang.Object r6 = r6.o(r5)
                if (r6 != r3) goto L2e
                goto L61
            L2e:
                io.ktor.server.cio.CIOApplicationEngine r6 = io.ktor.server.cio.CIOApplicationEngine.this
                io.ktor.server.engine.ApplicationEngineEnvironment r6 = r6.getEnvironment()
                io.ktor.events.Events r6 = r6.getMonitor()
                io.ktor.events.EventDefinition r0 = io.ktor.server.application.DefaultApplicationEventsKt.getServerReady()
                io.ktor.server.cio.CIOApplicationEngine r2 = io.ktor.server.cio.CIOApplicationEngine.this
                io.ktor.server.engine.ApplicationEngineEnvironment r2 = r2.getEnvironment()
                io.ktor.server.cio.CIOApplicationEngine r4 = io.ktor.server.cio.CIOApplicationEngine.this
                io.ktor.server.engine.ApplicationEngineEnvironment r4 = r4.getEnvironment()
                vc.b r4 = r4.getLog()
                io.ktor.events.EventsKt.raiseCatching(r6, r0, r2, r4)
                boolean r6 = r5.$wait
                if (r6 == 0) goto L62
                io.ktor.server.cio.CIOApplicationEngine r6 = io.ktor.server.cio.CIOApplicationEngine.this
                ka.v1 r6 = io.ktor.server.cio.CIOApplicationEngine.access$getServerJob(r6)
                r5.label = r1
                java.lang.Object r6 = r6.join(r5)
                if (r6 != r3) goto L62
            L61:
                return r3
            L62:
                x6.t0 r6 = x6.t0.f22605a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.cio.CIOApplicationEngine.C18612.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C18612) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.cio.CIOApplicationEngine$startConnector$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/server/cio/backend/ServerRequestScope;", "Lio/ktor/http/cio/Request;", "request", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/cio/backend/ServerRequestScope;Lio/ktor/http/cio/Request;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.cio.CIOApplicationEngine$startConnector$1", f = "CIOApplicationEngine.kt", l = {122}, m = "invokeSuspend")
    public static final class C18621 extends j implements q<ServerRequestScope, Request, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public C18621(d<? super C18621> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(ServerRequestScope serverRequestScope, Request request, d<? super t0> dVar) {
            C18621 c18621 = CIOApplicationEngine.this.new C18621(dVar);
            c18621.L$0 = serverRequestScope;
            c18621.L$1 = request;
            return c18621.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ServerRequestScope serverRequestScope = (ServerRequestScope) this.L$0;
                Request request = (Request) this.L$1;
                CIOApplicationEngine cIOApplicationEngine = CIOApplicationEngine.this;
                this.L$0 = null;
                this.label = 1;
                Object objHandleRequest = cIOApplicationEngine.handleRequest(serverRequestScope, request, this);
                a aVar = a.f15033i;
                if (objHandleRequest == aVar) {
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

    /* JADX WARN: Multi-variable type inference failed */
    public CIOApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, l<? super Configuration, t0> lVar) {
        super(applicationEngineEnvironment, null, 2, 0 == true ? 1 : 0);
        Configuration configuration = new Configuration();
        lVar.invoke(configuration);
        this.configuration = configuration;
        x0 x0Var = x0.f19652a;
        this.engineDispatcher = CoroutineUtilsJvmKt.getIOBridge(x0Var);
        this.userDispatcher = CoroutineUtilsJvmKt.getIOBridge(x0Var);
        this.startupJob = m0.b();
        this.stopRequest = m0.c();
        this.serverJob$delegate = m0.c();
        setServerJob(initServerJob());
        getServerJob().invokeOnCompletion(new AnonymousClass1(applicationEngineEnvironment, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object addHandlerForExpectedHeader(ByteWriteChannel byteWriteChannel, CIOApplicationCall cIOApplicationCall, d<? super t0> dVar) throws InvalidPhaseException {
        PipelinePhase pipelinePhase = new PipelinePhase("ExpectedHeaderPhase");
        cIOApplicationCall.getRequest().getPipeline().insertPhaseBefore(ApplicationReceivePipeline.INSTANCE.getBefore(), pipelinePhase);
        cIOApplicationCall.getRequest().getPipeline().intercept(pipelinePhase, new AnonymousClass2(cIOApplicationCall, this, "100-continue", byteWriteChannel, "HTTP/1.1 100 Continue\r\n", null));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final v1 getServerJob() {
        return (v1) this.serverJob$delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object handleRequest(ServerRequestScope serverRequestScope, Request request, d<? super t0> dVar) throws Throwable {
        Object objY = m0.y(this.userDispatcher, new C18572(request, serverRequestScope, null), dVar);
        return objY == a.f15033i ? objY : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasBody(CIOApplicationRequest request) {
        Headers headers = request.getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        String str = headers.get(httpHeaders.getContentLength());
        Integer numValueOf = str != null ? Integer.valueOf(Integer.parseInt(str)) : null;
        if (request.getHeaders().get(httpHeaders.getTransferEncoding()) == null) {
            return numValueOf != null && numValueOf.intValue() > 0;
        }
        return true;
    }

    private final v1 initServerJob() {
        ApplicationEngineEnvironment environment = getEnvironment();
        f0 f0Var = this.userDispatcher;
        t tVar = this.stopRequest;
        r<t0> rVar = this.startupJob;
        return m0.p(ka.l0.a(environment.getParentCoroutineContext().plus(this.engineDispatcher)), null, 2, new C18581(environment, f0Var, getResolvedConnectors(), tVar, rVar, this, null), 1);
    }

    private final void setServerJob(v1 v1Var) {
        this.serverJob$delegate = v1Var;
    }

    private final void shutdownServer(long gracePeriodMillis, long timeoutMillis) throws Throwable {
        this.stopRequest.complete();
        m0.s(k.f14688i, new C18591(gracePeriodMillis, this, timeoutMillis, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HttpServer startConnector(k0 k0Var, String str, int i10) {
        return io.ktor.server.cio.backend.HttpServerKt.httpServer(k0Var, new HttpServerSettings(str, i10, this.configuration.getConnectionIdleTimeoutSeconds(), this.configuration.getReuseAddress()), new C18621(null));
    }

    @Override // io.ktor.server.engine.ApplicationEngine
    public ApplicationEngine start(boolean wait) throws Throwable {
        ShutdownHookJvmKt.addShutdownHook(this, new C18601());
        getServerJob().start();
        m0.s(k.f14688i, new C18612(wait, null));
        return this;
    }

    @Override // io.ktor.server.engine.ApplicationEngine
    public void stop(long gracePeriodMillis, long timeoutMillis) throws Throwable {
        shutdownServer(gracePeriodMillis, timeoutMillis);
    }
}
