package io.ktor.client.plugins;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", TtmlNode.RUBY_CONTAINER, "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponseContainer;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.HttpCallValidator$Companion$install$2", f = "HttpCallValidator.kt", l = {142, 145}, m = "invokeSuspend")
public final class HttpCallValidator$Companion$install$2 extends j implements q<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, d<? super t0>, Object> {
    final /* synthetic */ HttpCallValidator $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpCallValidator$Companion$install$2(HttpCallValidator httpCallValidator, d<? super HttpCallValidator$Companion$install$2> dVar) {
        super(3, dVar);
        this.$plugin = httpCallValidator;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, d<? super t0> dVar) {
        HttpCallValidator$Companion$install$2 httpCallValidator$Companion$install$2 = new HttpCallValidator$Companion$install$2(this.$plugin, dVar);
        httpCallValidator$Companion$install$2.L$0 = pipelineContext;
        httpCallValidator$Companion$install$2.L$1 = httpResponseContainer;
        return httpCallValidator$Companion$install$2.invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        if (r5 == r3) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005a, code lost:
    
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
    public final java.lang.Object invokeSuspend(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r4 = this;
            int r0 = r4.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L26
            if (r0 == r2) goto L1c
            if (r0 == r1) goto L14
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L14:
            java.lang.Object r0 = r4.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            k2.c.G(r5)
            goto L5c
        L1c:
            java.lang.Object r0 = r4.L$0
            io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
            k2.c.G(r5)     // Catch: java.lang.Throwable -> L24
            goto L3d
        L24:
            r5 = move-exception
            goto L40
        L26:
            k2.c.G(r5)
            java.lang.Object r5 = r4.L$0
            r0 = r5
            io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
            java.lang.Object r5 = r4.L$1
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            r4.L$0 = r0     // Catch: java.lang.Throwable -> L24
            r4.label = r2     // Catch: java.lang.Throwable -> L24
            java.lang.Object r5 = r0.proceedWith(r5, r4)     // Catch: java.lang.Throwable -> L24
            if (r5 != r3) goto L3d
            goto L5a
        L3d:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        L40:
            java.lang.Throwable r5 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r5)
            io.ktor.client.plugins.HttpCallValidator r2 = r4.$plugin
            java.lang.Object r0 = r0.getContext()
            io.ktor.client.call.HttpClientCall r0 = (io.ktor.client.call.HttpClientCall) r0
            io.ktor.client.request.HttpRequest r0 = r0.getRequest()
            r4.L$0 = r5
            r4.label = r1
            java.lang.Object r0 = io.ktor.client.plugins.HttpCallValidator.access$processException(r2, r5, r0, r4)
            if (r0 != r3) goto L5b
        L5a:
            return r3
        L5b:
            r0 = r5
        L5c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpCallValidator$Companion$install$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
