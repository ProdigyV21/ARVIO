package io.ktor.client.plugins.api;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.http.ContentDisposition;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import r7.q;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002l\u0012h\u0012f\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\u000e0\u0001B\t\b\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0086\u0001\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112j\u0010\u0013\u001af\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0002¢\u0006\u0002\b\u000eH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/client/plugins/api/TransformRequestBodyHook;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function5;", "Lio/ktor/client/plugins/api/TransformRequestBodyContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "", "content", "Lio/ktor/util/reflect/TypeInfo;", "bodyType", "Ld7/d;", "Lio/ktor/http/content/OutgoingContent;", "Lx6/n;", "<init>", "()V", "Lio/ktor/client/HttpClient;", "client", "handler", "Lx6/t0;", "install", "(Lio/ktor/client/HttpClient;Lr7/s;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransformRequestBodyHook implements ClientHook<s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, ? super Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object>> {
    public static final TransformRequestBodyHook INSTANCE = new TransformRequestBodyHook();

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.TransformRequestBodyHook$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.api.TransformRequestBodyHook$install$1", f = "KtorCallContexts.kt", l = {82, 83}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        final /* synthetic */ s<TransformRequestBodyContext, HttpRequestBuilder, Object, TypeInfo, d<? super OutgoingContent>, Object> $handler;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object> sVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = sVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x005c, code lost:
        
            if (r0.proceedWith(r11, r10) == r3) goto L17;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                int r0 = r10.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L22
                if (r0 == r2) goto L19
                if (r0 != r1) goto L11
                k2.c.G(r11)
                r9 = r10
                goto L5f
            L11:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L19:
                java.lang.Object r0 = r10.L$0
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                k2.c.G(r11)
                r9 = r10
                goto L4f
            L22:
                k2.c.G(r11)
                java.lang.Object r11 = r10.L$0
                r0 = r11
                io.ktor.util.pipeline.PipelineContext r0 = (io.ktor.util.pipeline.PipelineContext) r0
                r7.s<io.ktor.client.plugins.api.TransformRequestBodyContext, io.ktor.client.request.HttpRequestBuilder, java.lang.Object, io.ktor.util.reflect.TypeInfo, d7.d<? super io.ktor.http.content.OutgoingContent>, java.lang.Object> r4 = r10.$handler
                io.ktor.client.plugins.api.TransformRequestBodyContext r5 = new io.ktor.client.plugins.api.TransformRequestBodyContext
                r5.<init>()
                java.lang.Object r6 = r0.getContext()
                java.lang.Object r7 = r0.getSubject()
                java.lang.Object r11 = r0.getContext()
                io.ktor.client.request.HttpRequestBuilder r11 = (io.ktor.client.request.HttpRequestBuilder) r11
                io.ktor.util.reflect.TypeInfo r8 = r11.getBodyType()
                r10.L$0 = r0
                r10.label = r2
                r9 = r10
                java.lang.Object r11 = r4.invoke(r5, r6, r7, r8, r9)
                if (r11 != r3) goto L4f
                goto L5e
            L4f:
                io.ktor.http.content.OutgoingContent r11 = (io.ktor.http.content.OutgoingContent) r11
                if (r11 == 0) goto L5f
                r2 = 0
                r9.L$0 = r2
                r9.label = r1
                java.lang.Object r11 = r0.proceedWith(r11, r10)
                if (r11 != r3) goto L5f
            L5e:
                return r3
            L5f:
                x6.t0 r11 = x6.t0.f22605a
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.api.TransformRequestBodyHook.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private TransformRequestBodyHook() {
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public /* bridge */ /* synthetic */ void install(HttpClient httpClient, s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, ? super Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object> sVar) {
        install2(httpClient, (s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object>) sVar);
    }

    /* JADX INFO: renamed from: install, reason: avoid collision after fix types in other method */
    public void install2(HttpClient client, s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object> handler) {
        client.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getTransform(), new AnonymousClass1(handler, null));
    }
}
