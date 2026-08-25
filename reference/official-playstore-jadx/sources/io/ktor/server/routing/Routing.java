package io.ktor.server.routing;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.events.EventDefinition;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.server.routing.RoutingResolveResult;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelineContext;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ7\u0010\u000f\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\rH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010JX\u0010\u001a\u001a\u00028\u0002\"\b\b\u0000\u0010\u0012*\u00020\u0011\"\b\b\u0001\u0010\u0013*\u00020\u0011\"\u0014\b\u0002\u0010\u0015*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00142\u0006\u0010\u0016\u001a\u00028\u00022\u0006\u0010\u0017\u001a\u00028\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00020\u0018H\u0082\b¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001f\u001a\u00020\u00062\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00060\u001c¢\u0006\u0004\b\u001f\u0010 J'\u0010!\u001a\u00020\u00062\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\tH\u0086@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010%R&\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00060\u001c0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lio/ktor/server/routing/Routing;", "Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "<init>", "(Lio/ktor/server/application/Application;)V", "Lx6/t0;", "addDefaultTracing", "()V", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "context", "route", "Lio/ktor/http/Parameters;", "parameters", "executeResult", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/server/routing/Route;Lio/ktor/http/Parameters;Ld7/d;)Ljava/lang/Object;", "", "Subject", "Context", "Lio/ktor/util/pipeline/Pipeline;", "P", "first", "second", "Lkotlin/Function0;", "build", "merge", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/Pipeline;Lr7/a;)Lio/ktor/util/pipeline/Pipeline;", "Lkotlin/Function1;", "Lio/ktor/server/routing/RoutingResolveTrace;", "block", "trace", "(Lr7/l;)V", "interceptor", "(Lio/ktor/util/pipeline/PipelineContext;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "", "tracers", "Ljava/util/List;", "Plugin", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Routing extends Route {
    private final Application application;
    private final List<l<RoutingResolveTrace, t0>> tracers;

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final EventDefinition<RoutingApplicationCall> RoutingCallStarted = new EventDefinition<>();
    private static final EventDefinition<RoutingApplicationCall> RoutingCallFinished = new EventDefinition<>();
    private static final AttributeKey<Routing> key = new AttributeKey<>("Routing");

    /* JADX INFO: renamed from: io.ktor.server.routing.Routing$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00022\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/server/routing/Routing$Plugin;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/routing/Routing;", "<init>", "()V", "pipeline", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "install", "(Lio/ktor/server/application/Application;Lr7/l;)Lio/ktor/server/routing/Routing;", "Lio/ktor/events/EventDefinition;", "Lio/ktor/server/routing/RoutingApplicationCall;", "RoutingCallStarted", "Lio/ktor/events/EventDefinition;", "getRoutingCallStarted", "()Lio/ktor/events/EventDefinition;", "RoutingCallFinished", "getRoutingCallFinished", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements BaseApplicationPlugin<Application, Routing, Routing> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.server.application.Plugin
        public AttributeKey<Routing> getKey() {
            return Routing.key;
        }

        public final EventDefinition<RoutingApplicationCall> getRoutingCallFinished() {
            return Routing.RoutingCallFinished;
        }

        public final EventDefinition<RoutingApplicationCall> getRoutingCallStarted() {
            return Routing.RoutingCallStarted;
        }

        @Override // io.ktor.server.application.Plugin
        public /* bridge */ /* synthetic */ Object install(Pipeline pipeline, l lVar) {
            return install((Application) pipeline, (l<? super Routing, t0>) lVar);
        }

        private Companion() {
        }

        public Routing install(Application pipeline, l<? super Routing, t0> configure) {
            Routing routing = new Routing(pipeline);
            configure.invoke(routing);
            pipeline.intercept(ApplicationCallPipeline.INSTANCE.getCall(), new Routing$Plugin$install$1(routing, null));
            return routing;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.Routing$addDefaultTracing$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/routing/RoutingResolveTrace;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/RoutingResolveTrace;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<RoutingResolveTrace, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((RoutingResolveTrace) obj);
            return t0.f22605a;
        }

        public final void invoke(RoutingResolveTrace routingResolveTrace) {
            RoutingKt.getLOGGER().h(routingResolveTrace.buildText());
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.Routing$executeResult$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.routing.Routing", f = "Routing.kt", l = {TsExtractor.TS_PACKET_SIZE}, m = "executeResult")
    public static final class C19211 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C19211(d<? super C19211> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Routing.this.executeResult(null, null, null, this);
        }
    }

    public Routing(Application application) {
        super(null, new RootRouteSelector(application.getEnvironment().getRootPath()), application.getEnvironment().getDevelopmentMode(), application.getEnvironment());
        this.application = application;
        this.tracers = new ArrayList();
        addDefaultTracing();
    }

    private final void addDefaultTracing() {
        if (RoutingKt.getLOGGER().b()) {
            this.tracers.add(AnonymousClass1.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeResult(io.ktor.util.pipeline.PipelineContext<x6.t0, io.ktor.server.application.ApplicationCall> r15, io.ktor.server.routing.Route r16, io.ktor.http.Parameters r17, d7.d<? super x6.t0> r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.routing.Routing.executeResult(io.ktor.util.pipeline.PipelineContext, io.ktor.server.routing.Route, io.ktor.http.Parameters, d7.d):java.lang.Object");
    }

    private final <Subject, Context, P extends Pipeline<Subject, Context>> P merge(P first, P second, a<? extends P> build) {
        if (first.isEmpty()) {
            return second;
        }
        if (second.isEmpty()) {
            return first;
        }
        P p10 = (P) build.invoke();
        p10.merge(first);
        p10.merge(second);
        return p10;
    }

    public final Application getApplication() {
        return this.application;
    }

    public final Object interceptor(PipelineContext<t0, ApplicationCall> pipelineContext, d<? super t0> dVar) {
        RoutingResolveResult routingResolveResultResolve = new RoutingResolveContext(this, pipelineContext.getContext(), this.tracers).resolve();
        boolean z = routingResolveResultResolve instanceof RoutingResolveResult.Success;
        t0 t0Var = t0.f22605a;
        if (z) {
            Object objExecuteResult = executeResult(pipelineContext, routingResolveResultResolve.getRoute(), routingResolveResultResolve.getParameters(), dVar);
            return objExecuteResult == e7.a.f15033i ? objExecuteResult : t0Var;
        }
        if (routingResolveResultResolve instanceof RoutingResolveResult.Failure) {
            pipelineContext.getContext().getAttributes().put(RoutingKt.getRoutingFailureStatusCode(), ((RoutingResolveResult.Failure) routingResolveResultResolve).getErrorStatusCode());
        }
        return t0Var;
    }

    public final void trace(l<? super RoutingResolveTrace, t0> block) {
        this.tracers.add(block);
    }
}
