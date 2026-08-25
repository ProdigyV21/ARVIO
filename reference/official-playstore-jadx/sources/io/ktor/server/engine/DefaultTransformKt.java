package io.ktor.server.engine;

import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.HttpHeaders;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.p;
import r7.q;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0005\u001a/\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00062\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a#\u0010\u0012\u001a\u00020\u0011*\u00020\r2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fH\u0080@ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\"\u001e\u0010\u0016\u001a\u00060\u0014j\u0002`\u00158\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/response/ApplicationSendPipeline;", "Lx6/t0;", "installDefaultTransformations", "(Lio/ktor/server/response/ApplicationSendPipeline;)V", "Lio/ktor/server/request/ApplicationReceivePipeline;", "(Lio/ktor/server/request/ApplicationReceivePipeline;)V", "R", "Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function0;", "block", "withContentType", "(Lio/ktor/server/application/ApplicationCall;Lr7/a;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", HttpAuthHeader.Parameters.Charset, "", "readText", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "getLOGGER", "()Lvc/b;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultTransformKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.engine.DefaultTransform");

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "value", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$1", f = "DefaultTransform.kt", l = {29}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                OutgoingContent outgoingContentTransformDefaultContent = io.ktor.server.http.content.DefaultTransformKt.transformDefaultContent((ApplicationCall) pipelineContext.getContext(), this.L$1);
                if (outgoingContentTransformDefaultContent != null) {
                    this.L$0 = null;
                    this.label = 1;
                    Object objProceedWith = pipelineContext.proceedWith(outgoingContentTransformDefaultContent, this);
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

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2", f = "DefaultTransform.kt", l = {AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE, 47, 53, 69, 73}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = obj;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:29:0x009f, code lost:
        
            if (r4 == r9) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0169, code lost:
        
            if (r4 == r9) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x016c, code lost:
        
            r5 = r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0171  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01c6  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) throws io.ktor.server.plugins.BadRequestException {
            /*
                Method dump skipped, instruction units count: 526
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultTransformKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$3", f = "DefaultTransform.kt", l = {87, 88}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements q<PipelineContext<Object, ApplicationCall>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass3(d<? super AnonymousClass3> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar);
            anonymousClass3.L$0 = pipelineContext;
            anonymousClass3.L$1 = obj;
            return anonymousClass3.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws BadRequestException {
            PipelineContext pipelineContext;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            a aVar = a.f15033i;
            if (i10 == 0) {
                c.G(obj);
                pipelineContext = (PipelineContext) this.L$0;
                Object obj2 = this.L$1;
                ByteReadChannel byteReadChannel = obj2 instanceof ByteReadChannel ? (ByteReadChannel) obj2 : null;
                if (byteReadChannel != null && p.a(ApplicationCallKt.getReceiveType((ApplicationCall) pipelineContext.getContext()).getType(), l0.f19747a.b(String.class))) {
                    ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                    try {
                        Charset charsetContentCharset = ApplicationRequestPropertiesKt.contentCharset(((ApplicationCall) pipelineContext.getContext()).getRequest());
                        if (charsetContentCharset == null) {
                            charsetContentCharset = kotlin.text.a.f19924a;
                        }
                        this.L$0 = pipelineContext;
                        this.label = 1;
                        obj = DefaultTransformKt.readText(byteReadChannel, charsetContentCharset, this);
                        if (obj != aVar) {
                        }
                    } catch (BadContentTypeFormatException e5) {
                        throw new BadRequestException("Illegal Content-Type header format: " + applicationCall.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getContentType()), e5);
                    }
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0Var;
            }
            pipelineContext = (PipelineContext) this.L$0;
            c.G(obj);
            this.L$0 = null;
            this.label = 2;
            return pipelineContext.proceedWith((String) obj, this) == aVar ? aVar : t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.DefaultTransformKt$readText$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.DefaultTransformKt", f = "DefaultTransform.kt", l = {MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE}, m = "readText")
    public static final class C18821 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18821(d<? super C18821> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DefaultTransformKt.readText(null, null, this);
        }
    }

    public static final b getLOGGER() {
        return LOGGER;
    }

    public static final void installDefaultTransformations(ApplicationSendPipeline applicationSendPipeline) {
        applicationSendPipeline.intercept(ApplicationSendPipeline.INSTANCE.getRender(), new AnonymousClass1(null));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object readText(io.ktor.utils.io.ByteReadChannel r4, java.nio.charset.Charset r5, d7.d<? super java.lang.String> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.engine.DefaultTransformKt.C18821
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.engine.DefaultTransformKt$readText$1 r0 = (io.ktor.server.engine.DefaultTransformKt.C18821) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.engine.DefaultTransformKt$readText$1 r0 = new io.ktor.server.engine.DefaultTransformKt$readText$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            java.lang.Object r4 = r0.L$0
            r5 = r4
            java.nio.charset.Charset r5 = (java.nio.charset.Charset) r5
            k2.c.G(r6)
            goto L47
        L2a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L32:
            k2.c.G(r6)
            r0.L$0 = r5
            r0.label = r2
            r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r6 = r4.readRemaining(r1, r0)
            e7.a r4 = e7.a.f15033i
            if (r6 != r4) goto L47
            return r4
        L47:
            io.ktor.utils.io.core.ByteReadPacket r6 = (io.ktor.utils.io.core.ByteReadPacket) r6
            boolean r4 = r6.getEndOfInput()
            if (r4 == 0) goto L52
            java.lang.String r4 = ""
            return r4
        L52:
            java.nio.charset.Charset r4 = kotlin.text.a.f19924a     // Catch: java.lang.Throwable -> L68
            boolean r4 = kotlin.jvm.internal.p.a(r5, r4)     // Catch: java.lang.Throwable -> L68
            if (r4 != 0) goto L6a
            java.nio.charset.Charset r4 = kotlin.text.a.f19926c     // Catch: java.lang.Throwable -> L68
            boolean r4 = kotlin.jvm.internal.p.a(r5, r4)     // Catch: java.lang.Throwable -> L68
            if (r4 == 0) goto L63
            goto L6a
        L63:
            java.lang.String r4 = io.ktor.server.engine.DefaultTransformJvmKt.readTextWithCustomCharset(r6, r5)     // Catch: java.lang.Throwable -> L68
            goto L71
        L68:
            r4 = move-exception
            goto L75
        L6a:
            r4 = 3
            r5 = 0
            r0 = 0
            java.lang.String r4 = io.ktor.utils.io.core.Input.readText$default(r6, r0, r0, r4, r5)     // Catch: java.lang.Throwable -> L68
        L71:
            r6.release()
            return r4
        L75:
            r6.release()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultTransformKt.readText(io.ktor.utils.io.ByteReadChannel, java.nio.charset.Charset, d7.d):java.lang.Object");
    }

    public static final <R> R withContentType(ApplicationCall applicationCall, r7.a<? extends R> aVar) throws BadRequestException {
        try {
            return (R) aVar.invoke();
        } catch (BadContentTypeFormatException e5) {
            throw new BadRequestException("Illegal Content-Type header format: " + applicationCall.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getContentType()), e5);
        }
    }

    public static final void installDefaultTransformations(ApplicationReceivePipeline applicationReceivePipeline) {
        ApplicationReceivePipeline.Companion companion = ApplicationReceivePipeline.INSTANCE;
        applicationReceivePipeline.intercept(companion.getTransform(), new AnonymousClass2(null));
        PipelinePhase pipelinePhase = new PipelinePhase("AfterTransform");
        applicationReceivePipeline.insertPhaseAfter(companion.getTransform(), pipelinePhase);
        applicationReceivePipeline.intercept(pipelinePhase, new AnonymousClass3(null));
    }
}
