package io.ktor.client.plugins;

import d7.d;
import f7.c;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentDisposition;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelinePhase;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import r7.q;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB[\b\u0000\u00127\u0010\n\u001a3\u0012/\u0012-\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00030\u0002\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0002\u0012\u0006\u0010\u000e\u001a\u00020\rø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018RH\u0010\n\u001a3\u0012/\u0012-\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00030\u00028\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\n\u0010\u0019R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u0019R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lio/ktor/client/plugins/HttpCallValidator;", "", "", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/y;", ContentDisposition.Parameters.Name, "response", "Ld7/d;", "Lx6/t0;", "responseValidators", "Lio/ktor/client/plugins/HandlerWrapper;", "callExceptionHandlers", "", "expectSuccess", "<init>", "(Ljava/util/List;Ljava/util/List;Z)V", "validateResponse", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "", "cause", "Lio/ktor/client/request/HttpRequest;", "request", "processException", "(Ljava/lang/Throwable;Lio/ktor/client/request/HttpRequest;Ld7/d;)Ljava/lang/Object;", "Ljava/util/List;", "Z", "Companion", "Config", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpCallValidator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpCallValidator> key = new AttributeKey<>("HttpResponseValidator");
    private final List<HandlerWrapper> callExceptionHandlers;
    private final boolean expectSuccess;
    private final List<p<HttpResponse, d<? super t0>, Object>> responseValidators;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/HttpCallValidator$Companion;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpCallValidator$Config;", "Lio/ktor/client/plugins/HttpCallValidator;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpCallValidator;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpCallValidator;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, HttpCallValidator> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpCallValidator> getKey() {
            return HttpCallValidator.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpCallValidator plugin, HttpClient scope) throws InvalidPhaseException {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getBefore(), new HttpCallValidator$Companion$install$1(plugin, null));
            PipelinePhase pipelinePhase = new PipelinePhase("BeforeReceive");
            scope.getResponsePipeline().insertPhaseBefore(HttpResponsePipeline.INSTANCE.getReceive(), pipelinePhase);
            scope.getResponsePipeline().intercept(pipelinePhase, new HttpCallValidator$Companion$install$2(plugin, null));
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.INSTANCE)).intercept(new HttpCallValidator$Companion$install$3(plugin, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpCallValidator prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return new HttpCallValidator(x.P0(config.getResponseValidators$ktor_client_core()), x.P0(config.getResponseExceptionHandlers$ktor_client_core()), config.getExpectSuccess());
        }
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\f\u001a\u00020\n21\u0010\u000b\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJX\u0010\u0011\u001a\u00020\n2F\u0010\u000b\u001aB\b\u0001\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012JC\u0010\u0015\u001a\u00020\n21\u0010\u000b\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\rRN\u0010\u0017\u001a3\u0012/\u0012-\b\u0001\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u00168\u0000X\u0080\u0004ø\u0001\u0000¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00168\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001d\u0010\u001aR(\u0010\u001f\u001a\u00020\u001e8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u001f\u0010 \u0012\u0004\b%\u0010\u0003\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/client/plugins/HttpCallValidator$Config;", "", "<init>", "()V", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "cause", "Ld7/d;", "Lx6/t0;", "block", "handleResponseException", "(Lr7/p;)V", "Lkotlin/Function3;", "Lio/ktor/client/request/HttpRequest;", "request", "handleResponseExceptionWithRequest", "(Lr7/q;)V", "Lio/ktor/client/statement/HttpResponse;", "response", "validateResponse", "", "responseValidators", "Ljava/util/List;", "getResponseValidators$ktor_client_core", "()Ljava/util/List;", "Lio/ktor/client/plugins/HandlerWrapper;", "responseExceptionHandlers", "getResponseExceptionHandlers$ktor_client_core", "", "expectSuccess", "Z", "getExpectSuccess", "()Z", "setExpectSuccess", "(Z)V", "getExpectSuccess$annotations", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private final List<p<HttpResponse, d<? super t0>, Object>> responseValidators = new ArrayList();
        private final List<HandlerWrapper> responseExceptionHandlers = new ArrayList();
        private boolean expectSuccess = true;

        @e
        public static /* synthetic */ void getExpectSuccess$annotations() {
        }

        public final boolean getExpectSuccess() {
            return this.expectSuccess;
        }

        public final List<HandlerWrapper> getResponseExceptionHandlers$ktor_client_core() {
            return this.responseExceptionHandlers;
        }

        public final List<p<HttpResponse, d<? super t0>, Object>> getResponseValidators$ktor_client_core() {
            return this.responseValidators;
        }

        @e
        public final void handleResponseException(p<? super Throwable, ? super d<? super t0>, ? extends Object> block) {
            this.responseExceptionHandlers.add(new ExceptionHandlerWrapper(block));
        }

        public final void handleResponseExceptionWithRequest(q<? super Throwable, ? super HttpRequest, ? super d<? super t0>, ? extends Object> block) {
            this.responseExceptionHandlers.add(new RequestExceptionHandlerWrapper(block));
        }

        public final void setExpectSuccess(boolean z) {
            this.expectSuccess = z;
        }

        public final void validateResponse(p<? super HttpResponse, ? super d<? super t0>, ? extends Object> block) {
            this.responseValidators.add(block);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpCallValidator$processException$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.client.plugins.HttpCallValidator", f = "HttpCallValidator.kt", l = {58, 59}, m = "processException")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCallValidator.this.processException(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpCallValidator$validateResponse$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "io.ktor.client.plugins.HttpCallValidator", f = "HttpCallValidator.kt", l = {51}, m = "validateResponse")
    public static final class C17311 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C17311(d<? super C17311> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpCallValidator.this.validateResponse(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HttpCallValidator(List<? extends p<? super HttpResponse, ? super d<? super t0>, ? extends Object>> list, List<? extends HandlerWrapper> list2, boolean z) {
        this.responseValidators = list;
        this.callExceptionHandlers = list2;
        this.expectSuccess = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ad, code lost:
    
        if (r1.invoke(r9, r10, r0) == r5) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0094, code lost:
    
        r1 = r9;
        r9 = r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processException(java.lang.Throwable r8, io.ktor.client.request.HttpRequest r9, d7.d<? super x6.t0> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof io.ktor.client.plugins.HttpCallValidator.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.client.plugins.HttpCallValidator$processException$1 r0 = (io.ktor.client.plugins.HttpCallValidator.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.HttpCallValidator$processException$1 r0 = new io.ktor.client.plugins.HttpCallValidator$processException$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L3d
            if (r1 == r3) goto L2d
            if (r1 != r2) goto L25
            goto L2d
        L25:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            java.lang.Object r8 = r0.L$2
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r9 = r0.L$1
            io.ktor.client.request.HttpRequest r9 = (io.ktor.client.request.HttpRequest) r9
            java.lang.Object r1 = r0.L$0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            k2.c.G(r10)
            goto L94
        L3d:
            k2.c.G(r10)
            vc.b r10 = io.ktor.client.plugins.HttpCallValidatorKt.access$getLOGGER$p()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Processing exception "
            r1.<init>(r4)
            r1.append(r8)
            java.lang.String r4 = " for request "
            r1.append(r4)
            io.ktor.http.Url r4 = r9.getUrl()
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r10.h(r1)
            java.util.List<io.ktor.client.plugins.HandlerWrapper> r10 = r7.callExceptionHandlers
            java.util.Iterator r10 = r10.iterator()
            r6 = r9
            r9 = r8
            r8 = r10
            r10 = r6
        L6b:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto Lb0
            java.lang.Object r1 = r8.next()
            io.ktor.client.plugins.HandlerWrapper r1 = (io.ktor.client.plugins.HandlerWrapper) r1
            boolean r4 = r1 instanceof io.ktor.client.plugins.ExceptionHandlerWrapper
            e7.a r5 = e7.a.f15033i
            if (r4 == 0) goto L97
            io.ktor.client.plugins.ExceptionHandlerWrapper r1 = (io.ktor.client.plugins.ExceptionHandlerWrapper) r1
            r7.p r1 = r1.getHandler()
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r1 = r1.invoke(r9, r0)
            if (r1 != r5) goto L92
            goto Laf
        L92:
            r1 = r9
            r9 = r10
        L94:
            r10 = r9
            r9 = r1
            goto L6b
        L97:
            boolean r4 = r1 instanceof io.ktor.client.plugins.RequestExceptionHandlerWrapper
            if (r4 == 0) goto L6b
            io.ktor.client.plugins.RequestExceptionHandlerWrapper r1 = (io.ktor.client.plugins.RequestExceptionHandlerWrapper) r1
            r7.q r1 = r1.getHandler()
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r8
            r0.label = r2
            java.lang.Object r1 = r1.invoke(r9, r10, r0)
            if (r1 != r5) goto L92
        Laf:
            return r5
        Lb0:
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator.processException(java.lang.Throwable, io.ktor.client.request.HttpRequest, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object validateResponse(io.ktor.client.statement.HttpResponse r6, d7.d<? super x6.t0> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.client.plugins.HttpCallValidator.C17311
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.client.plugins.HttpCallValidator$validateResponse$1 r0 = (io.ktor.client.plugins.HttpCallValidator.C17311) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.plugins.HttpCallValidator$validateResponse$1 r0 = new io.ktor.client.plugins.HttpCallValidator$validateResponse$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$1
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r1 = r0.L$0
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            k2.c.G(r7)
            r7 = r1
            goto L63
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            k2.c.G(r7)
            vc.b r7 = io.ktor.client.plugins.HttpCallValidatorKt.access$getLOGGER$p()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Validating response for request "
            r1.<init>(r3)
            io.ktor.client.call.HttpClientCall r3 = r6.getCall()
            io.ktor.client.request.HttpRequest r3 = r3.getRequest()
            io.ktor.http.Url r3 = r3.getUrl()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r7.h(r1)
            java.util.List<r7.p<io.ktor.client.statement.HttpResponse, d7.d<? super x6.t0>, java.lang.Object>> r7 = r5.responseValidators
            java.util.Iterator r7 = r7.iterator()
            r4 = r7
            r7 = r6
            r6 = r4
        L63:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L7e
            java.lang.Object r1 = r6.next()
            r7.p r1 = (r7.p) r1
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r2
            java.lang.Object r1 = r1.invoke(r7, r0)
            e7.a r3 = e7.a.f15033i
            if (r1 != r3) goto L63
            return r3
        L7e:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator.validateResponse(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }
}
