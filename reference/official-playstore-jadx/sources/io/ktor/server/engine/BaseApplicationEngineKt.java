package io.ktor.server.engine;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.http.content.HttpStatusCodeContent;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.server.routing.RoutingKt;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.ktor.util.reflect.TypeInfoJvmKt;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.reflect.b0;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0003\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0006\u001a\u00020\u0001*\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0013\u0010\b\u001a\u00020\u0001*\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "verifyHostHeader", "(Lio/ktor/util/pipeline/PipelineContext;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/server/application/Application;", "installDefaultInterceptors", "(Lio/ktor/server/application/Application;)V", "installDefaultTransformationChecker", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BaseApplicationEngineKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngineKt$installDefaultInterceptors$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultInterceptors$1", f = "BaseApplicationEngine.kt", l = {148}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0Var;
            }
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (!ApplicationCallKt.isHandled((ApplicationCall) pipelineContext.getContext())) {
                HttpStatusCode httpStatusCodeStatus = ((ApplicationCall) pipelineContext.getContext()).getResponse().get_status();
                if (httpStatusCodeStatus == null && (httpStatusCodeStatus = (HttpStatusCode) ((ApplicationCall) pipelineContext.getContext()).getAttributes().getOrNull(RoutingKt.getRoutingFailureStatusCode())) == null) {
                    httpStatusCodeStatus = HttpStatusCode.INSTANCE.getNotFound();
                }
                ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                if (!(httpStatusCodeStatus instanceof byte[])) {
                    ApplicationResponse response = applicationCall.getResponse();
                    kotlin.reflect.q qVarA = l0.a(HttpStatusCode.class);
                    ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA), l0.f19747a.b(HttpStatusCode.class), qVarA));
                }
                ApplicationSendPipeline pipeline = applicationCall.getResponse().getPipeline();
                this.label = 1;
                Object objExecute = pipeline.execute(applicationCall, httpStatusCodeStatus, this);
                a aVar = a.f15033i;
                if (objExecute == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngineKt$installDefaultInterceptors$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultInterceptors$2", f = "BaseApplicationEngine.kt", l = {115}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = pipelineContext;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                this.label = 1;
                Object objVerifyHostHeader = BaseApplicationEngineKt.verifyHostHeader(pipelineContext, this);
                a aVar = a.f15033i;
                if (objVerifyHostHeader == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1", f = "BaseApplicationEngine.kt", l = {124, 145}, m = "invokeSuspend")
    public static final class C18731 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C18731(d<? super C18731> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18731 c18731 = new C18731(dVar);
            c18731.L$0 = pipelineContext;
            return c18731.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        
            if (r9 == r3) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006f, code lost:
        
            if (r2.execute(r9, r0, r8) != r3) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0071, code lost:
        
            return r3;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [io.ktor.util.pipeline.PipelineContext] */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v9 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                int r0 = r8.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r9)
                goto L72
            L10:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L18:
                java.lang.Object r0 = r8.L$0
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                k2.c.G(r9)     // Catch: io.ktor.server.plugins.CannotTransformContentToTypeException -> L33
                goto L72
            L20:
                k2.c.G(r9)
                java.lang.Object r9 = r8.L$0
                r0 = r9
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                r8.L$0 = r0     // Catch: io.ktor.server.plugins.CannotTransformContentToTypeException -> L33
                r8.label = r2     // Catch: io.ktor.server.plugins.CannotTransformContentToTypeException -> L33
                java.lang.Object r9 = r0.proceed(r8)     // Catch: io.ktor.server.plugins.CannotTransformContentToTypeException -> L33
                if (r9 != r3) goto L72
                goto L71
            L33:
                java.lang.Object r9 = r0.getContext()
                io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
                io.ktor.http.HttpStatusCode$Companion r0 = io.ktor.http.HttpStatusCode.INSTANCE
                io.ktor.http.HttpStatusCode r0 = r0.getUnsupportedMediaType()
                boolean r2 = r0 instanceof byte[]
                if (r2 != 0) goto L5e
                io.ktor.server.response.ApplicationResponse r2 = r9.getResponse()
                java.lang.Class<io.ktor.http.HttpStatusCode> r4 = io.ktor.http.HttpStatusCode.class
                kotlin.reflect.q r5 = kotlin.jvm.internal.l0.a(r4)
                java.lang.reflect.Type r6 = kotlin.reflect.b0.t(r5)
                kotlin.jvm.internal.m0 r7 = kotlin.jvm.internal.l0.f19747a
                kotlin.reflect.d r4 = r7.b(r4)
                io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r6, r4, r5)
                io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r4)
            L5e:
                io.ktor.server.response.ApplicationResponse r2 = r9.getResponse()
                io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
                r4 = 0
                r8.L$0 = r4
                r8.label = r1
                java.lang.Object r9 = r2.execute(r9, r0, r8)
                if (r9 != r3) goto L72
            L71:
                return r3
            L72:
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationEngineKt.C18731.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "subject", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$2", f = "BaseApplicationEngine.kt", l = {TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "invokeSuspend")
    public static final class C18742 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public C18742(d<? super C18742> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            C18742 c18742 = new C18742(dVar);
            c18742.L$0 = pipelineContext;
            c18742.L$1 = obj;
            return c18742.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                if (!(this.L$1 instanceof OutgoingContent)) {
                    HttpStatusCodeContent httpStatusCodeContent = new HttpStatusCodeContent(HttpStatusCode.INSTANCE.getNotAcceptable());
                    this.L$0 = null;
                    this.label = 1;
                    Object objProceedWith = pipelineContext.proceedWith(httpStatusCodeContent, this);
                    a aVar = a.f15033i;
                    if (objProceedWith == aVar) {
                        return aVar;
                    }
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

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.BaseApplicationEngineKt", f = "BaseApplicationEngine.kt", l = {146}, m = "verifyHostHeader")
    public static final class C18751 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18751(d<? super C18751> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseApplicationEngineKt.verifyHostHeader(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installDefaultInterceptors(Application application) {
        ApplicationCallPipeline.Companion companion = ApplicationCallPipeline.INSTANCE;
        application.intercept(companion.getFallback(), new AnonymousClass1(null));
        application.intercept(companion.getCall(), new AnonymousClass2(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void installDefaultTransformationChecker(Application application) throws InvalidPhaseException {
        application.intercept(ApplicationCallPipeline.INSTANCE.getPlugins(), new C18731(null));
        PipelinePhase pipelinePhase = new PipelinePhase("BodyTransformationCheckPostRender");
        application.getSendPipeline().insertPhaseAfter(ApplicationSendPipeline.INSTANCE.getRender(), pipelinePhase);
        application.getSendPipeline().intercept(pipelinePhase, new C18742(null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object verifyHostHeader(io.ktor.util.pipeline.PipelineContext<x6.t0, io.ktor.server.application.ApplicationCall> r9, d7.d<? super x6.t0> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.engine.BaseApplicationEngineKt.C18751
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1 r0 = (io.ktor.server.engine.BaseApplicationEngineKt.C18751) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1 r0 = new io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            java.lang.Object r9 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r9 = (io.ktor.util.pipeline.PipelineContext) r9
            k2.c.G(r10)
            goto L97
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            k2.c.G(r10)
            java.lang.Object r10 = r9.getContext()
            io.ktor.server.application.ApplicationCall r10 = (io.ktor.server.application.ApplicationCall) r10
            io.ktor.server.request.ApplicationRequest r10 = r10.getRequest()
            io.ktor.http.Headers r10 = r10.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getHost()
            java.util.List r10 = r10.getAll(r1)
            if (r10 != 0) goto L51
            goto L9a
        L51:
            int r10 = r10.size()
            if (r10 <= r3) goto L9a
            java.lang.Object r10 = r9.getContext()
            io.ktor.server.application.ApplicationCall r10 = (io.ktor.server.application.ApplicationCall) r10
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r1 = r1.getBadRequest()
            boolean r4 = r1 instanceof byte[]
            if (r4 != 0) goto L82
            io.ktor.server.response.ApplicationResponse r4 = r10.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r5 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.q r6 = kotlin.jvm.internal.l0.a(r5)
            java.lang.reflect.Type r7 = kotlin.reflect.b0.t(r6)
            kotlin.jvm.internal.m0 r8 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r5 = r8.b(r5)
            io.ktor.util.reflect.TypeInfo r5 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r7, r5, r6)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r4, r5)
        L82:
            io.ktor.server.response.ApplicationResponse r4 = r10.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r4 = r4.getPipeline()
            r0.L$0 = r9
            r0.label = r3
            java.lang.Object r10 = r4.execute(r10, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r10 != r0) goto L97
            return r0
        L97:
            r9.finish()
        L9a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationEngineKt.verifyHostHeader(io.ktor.util.pipeline.PipelineContext, d7.d):java.lang.Object");
    }
}
