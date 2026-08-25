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
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B%\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJQ\u0010\u0014\u001a\u00020\u00132<\u0010\u0012\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n¢\u0006\u0002\b\u0011H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R&\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u00058\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/server/application/OnCallReceiveContext;", "", "PluginConfig", "Lio/ktor/server/application/CallContext;", "pluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "context", "<init>", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "Lkotlin/Function3;", "Lio/ktor/server/application/TransformBodyContext;", "Lio/ktor/utils/io/ByteReadChannel;", "Lx6/y;", ContentDisposition.Parameters.Name, TtmlNode.TAG_BODY, "Ld7/d;", "Lx6/n;", "transform", "Lx6/t0;", "transformBody", "(Lr7/q;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/pipeline/PipelineContext;", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class OnCallReceiveContext<PluginConfig> extends CallContext<PluginConfig> {
    private final PipelineContext<Object, ApplicationCall> context;

    /* JADX INFO: renamed from: io.ktor.server.application.OnCallReceiveContext$transformBody$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.application.OnCallReceiveContext", f = "KtorCallContexts.kt", l = {65}, m = "transformBody")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ OnCallReceiveContext<PluginConfig> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OnCallReceiveContext<PluginConfig> onCallReceiveContext, d<? super AnonymousClass1> dVar) {
            super(dVar);
            this.this$0 = onCallReceiveContext;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.transformBody(null, this);
        }
    }

    public OnCallReceiveContext(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
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
    public final java.lang.Object transformBody(r7.q<? super io.ktor.server.application.TransformBodyContext, ? super io.ktor.utils.io.ByteReadChannel, ? super d7.d<java.lang.Object>, ? extends java.lang.Object> r9, d7.d<? super x6.t0> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.server.application.OnCallReceiveContext.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.server.application.OnCallReceiveContext$transformBody$1 r0 = (io.ktor.server.application.OnCallReceiveContext.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.application.OnCallReceiveContext$transformBody$1 r0 = new io.ktor.server.application.OnCallReceiveContext$transformBody$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            x6.t0 r3 = x6.t0.f22605a
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r9 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r9 = (io.ktor.util.pipeline.PipelineContext) r9
            k2.c.G(r10)
            goto L89
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L33:
            k2.c.G(r10)
            io.ktor.util.pipeline.PipelineContext r10 = r8.getContext()
            java.lang.Object r10 = r10.getSubject()
            boolean r1 = r10 instanceof io.ktor.utils.io.ByteReadChannel
            if (r1 == 0) goto L45
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            goto L46
        L45:
            r10 = 0
        L46:
            if (r10 != 0) goto L49
            goto L71
        L49:
            io.ktor.util.pipeline.PipelineContext r1 = r8.getContext()
            java.lang.Object r1 = r1.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            io.ktor.util.reflect.TypeInfo r1 = io.ktor.server.application.ApplicationCallKt.getReceiveType(r1)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r4 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.q r5 = kotlin.jvm.internal.l0.a(r4)
            java.lang.reflect.Type r6 = kotlin.reflect.b0.t(r5)
            kotlin.jvm.internal.m0 r7 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r4 = r7.b(r4)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r6, r4, r5)
            boolean r4 = kotlin.jvm.internal.p.a(r1, r4)
            if (r4 == 0) goto L72
        L71:
            return r3
        L72:
            io.ktor.server.application.TransformBodyContext r4 = new io.ktor.server.application.TransformBodyContext
            r4.<init>(r1)
            io.ktor.util.pipeline.PipelineContext r1 = r8.getContext()
            r0.L$0 = r1
            r0.label = r2
            java.lang.Object r10 = r9.invoke(r4, r10, r0)
            e7.a r9 = e7.a.f15033i
            if (r10 != r9) goto L88
            return r9
        L88:
            r9 = r1
        L89:
            r9.setSubject(r10)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.OnCallReceiveContext.transformBody(r7.q, d7.d):java.lang.Object");
    }
}
