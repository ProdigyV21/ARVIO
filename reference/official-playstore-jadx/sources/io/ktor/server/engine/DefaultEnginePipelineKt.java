package io.ktor.server.engine;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.engine.internal.ApplicationUtilsJvmKt;
import io.ktor.server.logging.LoggingKt;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.plugins.NotFoundException;
import io.ktor.server.plugins.UnsupportedMediaTypeException;
import io.ktor.util.cio.ChannelIOException;
import io.ktor.util.logging.LoggerKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlinx.coroutines.TimeoutCancellationException;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\f\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000b\u001a\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000eH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a#\u0010\u0014\u001a\u00020\t*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/ApplicationEnvironment;", "environment", "Lio/ktor/server/engine/EnginePipeline;", "defaultEnginePipeline", "(Lio/ktor/server/application/ApplicationEnvironment;)Lio/ktor/server/engine/EnginePipeline;", "Lio/ktor/server/application/ApplicationCall;", "call", "", "error", "Lx6/t0;", "handleFailure", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Throwable;Ld7/d;)Ljava/lang/Object;", "logError", "cause", "Lio/ktor/http/HttpStatusCode;", "defaultExceptionStatusCode", "(Ljava/lang/Throwable;)Lio/ktor/http/HttpStatusCode;", "statusCode", "tryRespondError", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Ld7/d;)Ljava/lang/Object;", "logFailure", "(Lio/ktor/server/application/ApplicationEnvironment;Lio/ktor/server/application/ApplicationCall;Ljava/lang/Throwable;)V", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultEnginePipelineKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1", f = "DefaultEnginePipeline.kt", l = {123, 43, TsExtractor.TS_STREAM_TYPE_H265, 43, 40, 43, 43}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$1", f = "DefaultEnginePipeline.kt", l = {}, m = "invokeSuspend")
        public static final class C02361 extends j implements l<d<? super t0>, Object> {
            final /* synthetic */ PipelineContext<t0, ApplicationCall> $$this$intercept;
            final /* synthetic */ ChannelIOException $error;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02361(PipelineContext<t0, ApplicationCall> pipelineContext, ChannelIOException channelIOException, d<? super C02361> dVar) {
                super(1, dVar);
                this.$$this$intercept = pipelineContext;
                this.$error = channelIOException;
            }

            @Override // f7.a
            public final d<t0> create(d<?> dVar) {
                return new C02361(this.$$this$intercept, this.$error, dVar);
            }

            @Override // r7.l
            public final Object invoke(d<? super t0> dVar) {
                return ((C02361) create(dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                DefaultEnginePipelineKt.logFailure(this.$$this$intercept.getContext().getApplication().getEnvironment(), this.$$this$intercept.getContext(), this.$error);
                return t0.f22605a;
            }
        }

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
        
            if (io.ktor.utils.io.ByteReadChannelKt.discard(r8, r7) != r2) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00b1, code lost:
        
            if (io.ktor.utils.io.ByteReadChannelKt.discard(r8, r7) == r2) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00f0, code lost:
        
            if (io.ktor.utils.io.ByteReadChannelKt.discard(r8, r7) != r2) goto L54;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [io.ktor.util.pipeline.PipelineContext, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v2, types: [io.ktor.util.pipeline.PipelineContext, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v22 */
        /* JADX WARN: Type inference failed for: r0v23 */
        /* JADX WARN: Type inference failed for: r0v24 */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r0v26 */
        /* JADX WARN: Type inference failed for: r0v27 */
        /* JADX WARN: Type inference failed for: r0v28 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v6, types: [io.ktor.util.pipeline.PipelineContext] */
        /* JADX WARN: Type inference failed for: r0v8, types: [io.ktor.util.pipeline.PipelineContext] */
        /* JADX WARN: Type inference failed for: r6v0, types: [io.ktor.util.pipeline.PipelineContext] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 294
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.DefaultEnginePipelineKt", f = "DefaultEnginePipeline.kt", l = {56, 57}, m = "handleFailure")
    public static final class C18801 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18801(d<? super C18801> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultEnginePipelineKt.handleFailure(null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultEnginePipelineKt$logError$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.DefaultEnginePipelineKt$logError$2", f = "DefaultEnginePipeline.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements l<d<? super t0>, Object> {
        final /* synthetic */ ApplicationCall $call;
        final /* synthetic */ Throwable $error;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ApplicationCall applicationCall, Throwable th, d<? super AnonymousClass2> dVar) {
            super(1, dVar);
            this.$call = applicationCall;
            this.$error = th;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new AnonymousClass2(this.$call, this.$error, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super t0> dVar) {
            return ((AnonymousClass2) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            DefaultEnginePipelineKt.logFailure(this.$call.getApplication().getEnvironment(), this.$call, this.$error);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.DefaultEnginePipelineKt", f = "DefaultEnginePipeline.kt", l = {127}, m = "tryRespondError")
    public static final class C18811 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C18811(d<? super C18811> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultEnginePipelineKt.tryRespondError(null, null, this);
        }
    }

    public static final EnginePipeline defaultEnginePipeline(ApplicationEnvironment applicationEnvironment) {
        EnginePipeline enginePipeline = new EnginePipeline(applicationEnvironment.getDevelopmentMode());
        ApplicationUtilsJvmKt.configureShutdownUrl(applicationEnvironment, enginePipeline);
        enginePipeline.intercept(EnginePipeline.INSTANCE.getCall(), new AnonymousClass1(null));
        return enginePipeline;
    }

    public static final HttpStatusCode defaultExceptionStatusCode(Throwable th) {
        if (th instanceof BadRequestException) {
            return HttpStatusCode.INSTANCE.getBadRequest();
        }
        if (th instanceof NotFoundException) {
            return HttpStatusCode.INSTANCE.getNotFound();
        }
        if (th instanceof UnsupportedMediaTypeException) {
            return HttpStatusCode.INSTANCE.getUnsupportedMediaType();
        }
        if (th instanceof TimeoutException ? true : th instanceof TimeoutCancellationException) {
            return HttpStatusCode.INSTANCE.getGatewayTimeout();
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0066, code lost:
    
        if (tryRespondError(r5, r6, r0) == r4) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object handleFailure(io.ktor.server.application.ApplicationCall r5, java.lang.Throwable r6, d7.d<? super x6.t0> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.engine.DefaultEnginePipelineKt.C18801
            if (r0 == 0) goto L13
            r0 = r7
            io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1 r0 = (io.ktor.server.engine.DefaultEnginePipelineKt.C18801) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1 r0 = new io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1
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
            goto L69
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            java.lang.Object r5 = r0.L$1
            r6 = r5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            k2.c.G(r7)
            goto L4f
        L3f:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = logError(r5, r6, r0)
            if (r7 != r4) goto L4f
            goto L68
        L4f:
            io.ktor.http.HttpStatusCode r6 = defaultExceptionStatusCode(r6)
            if (r6 != 0) goto L5b
            io.ktor.http.HttpStatusCode$Companion r6 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r6 = r6.getInternalServerError()
        L5b:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r2
            java.lang.Object r5 = tryRespondError(r5, r6, r0)
            if (r5 != r4) goto L69
        L68:
            return r4
        L69:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.handleFailure(io.ktor.server.application.ApplicationCall, java.lang.Throwable, d7.d):java.lang.Object");
    }

    public static final Object logError(ApplicationCall applicationCall, Throwable th, d<? super t0> dVar) {
        Object objWithMDCBlock = LoggingKt.getMdcProvider(applicationCall.getApplication()).withMDCBlock(applicationCall, new AnonymousClass2(applicationCall, th, null), dVar);
        return objWithMDCBlock == a.f15033i ? objWithMDCBlock : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logFailure(ApplicationEnvironment applicationEnvironment, ApplicationCall applicationCall, Throwable th) {
        String logString;
        try {
            try {
                Object objStatus = applicationCall.getResponse().get_status();
                if (objStatus == null) {
                    objStatus = "Unhandled";
                }
                try {
                    logString = LoggingKt.toLogString(applicationCall.getRequest());
                } catch (Throwable th2) {
                    logString = "(request error: " + th2 + ')';
                }
                String str = objStatus + ": " + logString + ". Exception " + l0.f19747a.b(th.getClass()) + ": " + th.getMessage();
                boolean z = true;
                if (!(th instanceof CancellationException ? true : th instanceof ClosedChannelException ? true : th instanceof ChannelIOException ? true : th instanceof IOException ? true : th instanceof BadRequestException ? true : th instanceof NotFoundException)) {
                    z = th instanceof UnsupportedMediaTypeException;
                }
                if (z) {
                    applicationEnvironment.getLog().e(str, th);
                    return;
                }
                applicationEnvironment.getLog().c(objStatus + ": " + logString, th);
            } catch (OutOfMemoryError unused) {
                LoggerKt.error(applicationEnvironment.getLog(), th);
            }
        } catch (OutOfMemoryError unused2) {
            ApplicationUtilsJvmKt.printError("OutOfMemoryError: ");
            ApplicationUtilsJvmKt.printError(th.getMessage());
            ApplicationUtilsJvmKt.printError("\n");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object tryRespondError(io.ktor.server.application.ApplicationCall r6, io.ktor.http.HttpStatusCode r7, d7.d<? super x6.t0> r8) {
        /*
            java.lang.Class<io.ktor.http.HttpStatusCode> r0 = io.ktor.http.HttpStatusCode.class
            boolean r1 = r8 instanceof io.ktor.server.engine.DefaultEnginePipelineKt.C18811
            if (r1 == 0) goto L15
            r1 = r8
            io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1 r1 = (io.ktor.server.engine.DefaultEnginePipelineKt.C18811) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1 r1 = new io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1
            r1.<init>(r8)
        L1a:
            java.lang.Object r8 = r1.result
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            k2.c.G(r8)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            goto L6c
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            k2.c.G(r8)
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            io.ktor.http.HttpStatusCode r8 = r8.get_status()     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            if (r8 != 0) goto L6c
            boolean r8 = r7 instanceof byte[]     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            if (r8 != 0) goto L59
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            kotlin.reflect.q r2 = kotlin.jvm.internal.l0.a(r0)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            java.lang.reflect.Type r4 = kotlin.reflect.b0.t(r2)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            kotlin.jvm.internal.m0 r5 = kotlin.jvm.internal.l0.f19747a     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            kotlin.reflect.d r0 = r5.b(r0)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r0, r2)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            io.ktor.server.response.ResponseTypeKt.setResponseType(r8, r0)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
        L59:
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            io.ktor.server.response.ApplicationSendPipeline r8 = r8.getPipeline()     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            r1.label = r3     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            java.lang.Object r6 = r8.execute(r6, r7, r1)     // Catch: io.ktor.server.engine.BaseApplicationResponse.ResponseAlreadySentException -> L6c
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L6c
            return r7
        L6c:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.tryRespondError(io.ktor.server.application.ApplicationCall, io.ktor.http.HttpStatusCode, d7.d):java.lang.Object");
    }
}
