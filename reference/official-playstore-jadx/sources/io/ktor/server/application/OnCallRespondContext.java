package io.ktor.server.application;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.http.ContentDisposition;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B%\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJQ\u0010\u0013\u001a\u00020\u00122<\u0010\u0011\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n¢\u0006\u0002\b\u0010H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u00058\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lio/ktor/server/application/OnCallRespondContext;", "", "PluginConfig", "Lio/ktor/server/application/CallContext;", "pluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "context", "<init>", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "Lkotlin/Function3;", "Lio/ktor/server/application/TransformBodyContext;", "Lx6/y;", ContentDisposition.Parameters.Name, TtmlNode.TAG_BODY, "Ld7/d;", "Lx6/n;", "transform", "Lx6/t0;", "transformBody", "(Lr7/q;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/pipeline/PipelineContext;", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OnCallRespondContext<PluginConfig> extends CallContext<PluginConfig> {
    private final PipelineContext<Object, ApplicationCall> context;

    /* JADX INFO: renamed from: io.ktor.server.application.OnCallRespondContext$transformBody$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.application.OnCallRespondContext", f = "KtorCallContexts.kt", l = {86}, m = "transformBody")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OnCallRespondContext<PluginConfig> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OnCallRespondContext<PluginConfig> onCallRespondContext, d<? super AnonymousClass1> dVar) {
            super(dVar);
            this.this$0 = onCallRespondContext;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.transformBody(null, this);
        }
    }

    public OnCallRespondContext(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
        super(pluginconfig, pipelineContext);
        this.context = pipelineContext;
    }

    @Override // io.ktor.server.application.CallContext
    public PipelineContext<Object, ApplicationCall> getContext() {
        return this.context;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object transformBody(r7.q<? super io.ktor.server.application.TransformBodyContext, java.lang.Object, ? super d7.d<java.lang.Object>, ? extends java.lang.Object> r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.server.application.OnCallRespondContext.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.server.application.OnCallRespondContext$transformBody$1 r0 = (io.ktor.server.application.OnCallRespondContext.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.application.OnCallRespondContext$transformBody$1 r0 = new io.ktor.server.application.OnCallRespondContext$transformBody$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r5 = (io.ktor.util.pipeline.PipelineContext) r5
            k2.c.G(r6)
            goto L65
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            io.ktor.server.application.TransformBodyContext r6 = new io.ktor.server.application.TransformBodyContext
            io.ktor.util.pipeline.PipelineContext r1 = r4.getContext()
            java.lang.Object r1 = r1.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            io.ktor.server.response.ApplicationResponse r1 = r1.getResponse()
            io.ktor.util.reflect.TypeInfo r1 = io.ktor.server.response.ResponseTypeKt.getResponseType(r1)
            r6.<init>(r1)
            io.ktor.util.pipeline.PipelineContext r1 = r4.getContext()
            io.ktor.util.pipeline.PipelineContext r3 = r4.getContext()
            java.lang.Object r3 = r3.getSubject()
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r6 = r5.invoke(r6, r3, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L64
            return r5
        L64:
            r5 = r1
        L65:
            r5.setSubject(r6)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.OnCallRespondContext.transformBody(r7.q, d7.d):java.lang.Object");
    }
}
