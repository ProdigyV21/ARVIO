package io.ktor.client.plugins;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpCallValidator$Companion$install$1", f = "HttpCallValidator.kt", l = {TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 133}, m = "invokeSuspend")
public final class HttpCallValidator$Companion$install$1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpCallValidator$Companion$install$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Boolean> {
        final /* synthetic */ HttpCallValidator $plugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpCallValidator httpCallValidator) {
            super(0);
            this.$plugin = httpCallValidator;
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.valueOf(this.$plugin.expectSuccess);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCallValidator$Companion$install$1(HttpCallValidator httpCallValidator, d<? super HttpCallValidator$Companion$install$1> dVar) {
        super(3, dVar);
        this.$plugin = httpCallValidator;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
        HttpCallValidator$Companion$install$1 httpCallValidator$Companion$install$1 = new HttpCallValidator$Companion$install$1(this.$plugin, dVar);
        httpCallValidator$Companion$install$1.L$0 = pipelineContext;
        httpCallValidator$Companion$install$1.L$1 = obj;
        return httpCallValidator$Companion$install$1.invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
    
        if (r9 == r3) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0070, code lost:
    
        return r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1, types: [io.ktor.util.pipeline.PipelineContext] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v9 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            int r0 = r8.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L26
            if (r0 == r2) goto L1c
            if (r0 == r1) goto L14
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L14:
            java.lang.Object r0 = r8.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            k2.c.G(r9)
            goto L72
        L1c:
            java.lang.Object r0 = r8.L$0
            io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
            k2.c.G(r9)     // Catch: java.lang.Throwable -> L24
            goto L53
        L24:
            r9 = move-exception
            goto L56
        L26:
            k2.c.G(r9)
            java.lang.Object r9 = r8.L$0
            r0 = r9
            io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
            java.lang.Object r9 = r8.L$1
            java.lang.Object r4 = r0.getContext()     // Catch: java.lang.Throwable -> L24
            io.ktor.client.request.HttpRequestBuilder r4 = (io.ktor.client.request.HttpRequestBuilder) r4     // Catch: java.lang.Throwable -> L24
            io.ktor.util.Attributes r4 = r4.getAttributes()     // Catch: java.lang.Throwable -> L24
            io.ktor.util.AttributeKey r5 = io.ktor.client.plugins.HttpCallValidatorKt.getExpectSuccessAttributeKey()     // Catch: java.lang.Throwable -> L24
            io.ktor.client.plugins.HttpCallValidator$Companion$install$1$1 r6 = new io.ktor.client.plugins.HttpCallValidator$Companion$install$1$1     // Catch: java.lang.Throwable -> L24
            io.ktor.client.plugins.HttpCallValidator r7 = r8.$plugin     // Catch: java.lang.Throwable -> L24
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L24
            r4.computeIfAbsent(r5, r6)     // Catch: java.lang.Throwable -> L24
            r8.L$0 = r0     // Catch: java.lang.Throwable -> L24
            r8.label = r2     // Catch: java.lang.Throwable -> L24
            java.lang.Object r9 = r0.proceedWith(r9, r8)     // Catch: java.lang.Throwable -> L24
            if (r9 != r3) goto L53
            goto L70
        L53:
            x6.t0 r9 = x6.t0.f22605a
            return r9
        L56:
            java.lang.Throwable r9 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r9)
            io.ktor.client.plugins.HttpCallValidator r2 = r8.$plugin
            java.lang.Object r0 = r0.getContext()
            io.ktor.client.request.HttpRequestBuilder r0 = (io.ktor.client.request.HttpRequestBuilder) r0
            io.ktor.client.plugins.HttpCallValidatorKt$HttpRequest$1 r0 = io.ktor.client.plugins.HttpCallValidatorKt.access$HttpRequest(r0)
            r8.L$0 = r9
            r8.label = r1
            java.lang.Object r0 = io.ktor.client.plugins.HttpCallValidator.access$processException(r2, r9, r0, r8)
            if (r0 != r3) goto L71
        L70:
            return r3
        L71:
            r0 = r9
        L72:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator$Companion$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
