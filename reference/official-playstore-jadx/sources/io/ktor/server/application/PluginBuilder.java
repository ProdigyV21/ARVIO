package io.ktor.server.application;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import io.ktor.server.application.debug.DebugPhaseNamesKt;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.debug.ContextUtilsKt;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import java.util.ArrayList;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;
import r7.p;
import r7.q;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0017\b\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007Jº\u0001\u0010\u001e\u001a\u00020\u001b\"\b\b\u0001\u0010\b*\u00020\u0001\"\u000e\b\u0002\u0010\n*\b\u0012\u0004\u0012\u00028\u00000\t2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u001023\u0010\u0018\u001a/\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0004\u0012\u00028\u00020\u001223\u0010\u001d\u001a/\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0002\b\u001cH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJØ\u0001\u0010\"\u001a\u00020\u001b\"\b\b\u0001\u0010\b*\u00020\u0001\"\u000e\b\u0002\u0010\n*\b\u0012\u0004\u0012\u00028\u00000\t2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u001023\u0010\u0018\u001a/\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0004\u0012\u00028\u00020\u00122Q\u0010\u001d\u001aM\b\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0002\b\u001cH\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u001fJT\u0010%\u001a\u00020\u001b2B\u0010\u001d\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010#¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0004\b%\u0010&Ji\u0010(\u001a\u00020\u001b2W\u0010\u001d\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0004\b(\u0010)Ji\u0010+\u001a\u00020\u001b2W\u0010\u001d\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000*\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0019¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0004\b+\u0010)J)\u00100\u001a\u00020\u001b\"\u0004\b\u0001\u0010,2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010-2\u0006\u0010/\u001a\u00028\u0001¢\u0006\u0004\b0\u00101JT\u0010(\u001a\u00020\u001b2B\u0010\u001d\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000'\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010#¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0004\b(\u0010&JT\u0010+\u001a\u00020\u001b2B\u0010\u001d\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000*\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b( \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00010#¢\u0006\u0002\b\u001cø\u0001\u0000¢\u0006\u0004\b+\u0010&J\u000f\u00104\u001a\u00020\u000eH\u0000¢\u0006\u0004\b2\u00103R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u00105\u001a\u0004\b6\u00107R*\u00109\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001b0\fj\u0002`80\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R*\u0010>\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00010\fj\u0002`=0\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b>\u0010:\u001a\u0004\b?\u0010<R*\u0010A\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00010\fj\u0002`@0\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bA\u0010:\u001a\u0004\bB\u0010<R*\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00010\fj\u0002`@0\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bC\u0010:\u001a\u0004\bD\u0010<R$\u0010F\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030E0\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bF\u0010:\u001a\u0004\bG\u0010<R\u0014\u0010K\u001a\u00020H8&X¦\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0014\u0010\u0015\u001a\u00028\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010Q\u001a\u00020N8 X \u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0013\u0010U\u001a\u0004\u0018\u00010R8F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0013\u0010Y\u001a\u0004\u0018\u00010V8F¢\u0006\u0006\u001a\u0004\bW\u0010X\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Z"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "", "PluginConfig", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/PluginInstance;", "key", "<init>", "(Lio/ktor/util/AttributeKey;)V", "T", "Lio/ktor/server/application/CallContext;", "ContextT", "", "Lio/ktor/server/application/Interception;", "interceptions", "Lio/ktor/util/pipeline/PipelinePhase;", "phase", "", "handlerName", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "pluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "contextInit", "Lkotlin/Function4;", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "block", "onDefaultPhaseWithMessage", "(Ljava/util/List;Lio/ktor/util/pipeline/PipelinePhase;Ljava/lang/String;Lr7/p;Lr7/r;)V", "call", TtmlNode.TAG_BODY, "onDefaultPhase", "Lkotlin/Function3;", "Lio/ktor/server/application/OnCallContext;", DebugPhaseNamesKt.PHASE_ON_CALL, "(Lr7/q;)V", "Lio/ktor/server/application/OnCallReceiveContext;", DebugPhaseNamesKt.PHASE_ON_CALL_RECEIVE, "(Lr7/r;)V", "Lio/ktor/server/application/OnCallRespondContext;", DebugPhaseNamesKt.PHASE_ON_CALL_RESPOND, "HookHandler", "Lio/ktor/server/application/Hook;", "hook", "handler", "on", "(Lio/ktor/server/application/Hook;Ljava/lang/Object;)V", "newPhase$ktor_server_core", "()Lio/ktor/util/pipeline/PipelinePhase;", "newPhase", "Lio/ktor/util/AttributeKey;", "getKey$ktor_server_core", "()Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/CallInterception;", "callInterceptions", "Ljava/util/List;", "getCallInterceptions$ktor_server_core", "()Ljava/util/List;", "Lio/ktor/server/application/ReceiveInterception;", "onReceiveInterceptions", "getOnReceiveInterceptions$ktor_server_core", "Lio/ktor/server/application/ResponseInterception;", "onResponseInterceptions", "getOnResponseInterceptions$ktor_server_core", "afterResponseInterceptions", "getAfterResponseInterceptions$ktor_server_core", "Lio/ktor/server/application/HookHandler;", "hooks", "getHooks$ktor_server_core", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "getPluginConfig", "()Ljava/lang/Object;", "Lio/ktor/server/application/ApplicationCallPipeline;", "getPipeline$ktor_server_core", "()Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "Lio/ktor/server/application/ApplicationEnvironment;", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "environment", "Lio/ktor/server/config/ApplicationConfig;", "getApplicationConfig", "()Lio/ktor/server/config/ApplicationConfig;", "applicationConfig", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class PluginBuilder<PluginConfig> {
    private final AttributeKey<PluginInstance> key;
    private final List<Interception<t0>> callInterceptions = new ArrayList();
    private final List<Interception<Object>> onReceiveInterceptions = new ArrayList();
    private final List<Interception<Object>> onResponseInterceptions = new ArrayList();
    private final List<Interception<Object>> afterResponseInterceptions = new ArrayList();
    private final List<HookHandler<?>> hooks = new ArrayList();

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCall$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends m implements p<PluginConfig, PipelineContext<t0, ApplicationCall>, OnCallContext<PluginConfig>> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, OnCallContext.class, "<init>", "<init>(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", 0);
        }

        @Override // r7.p
        public final OnCallContext<PluginConfig> invoke(PluginConfig pluginconfig, PipelineContext<t0, ApplicationCall> pipelineContext) {
            return new OnCallContext<>(pluginconfig, pipelineContext);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCall$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"", "PluginConfig", "Lio/ktor/server/application/OnCallContext;", "Lio/ktor/server/application/ApplicationCall;", "call", "Lx6/t0;", "<anonymous parameter 1>", "<anonymous>", "(Lio/ktor/server/application/OnCallContext;Lio/ktor/server/application/ApplicationCall;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.PluginBuilder$onCall$2", f = "PluginBuilder.kt", l = {88}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements r<OnCallContext<PluginConfig>, ApplicationCall, t0, d<? super t0>, Object> {
        final /* synthetic */ q<OnCallContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(q<? super OnCallContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass2> dVar) {
            super(4, dVar);
            this.$block = qVar;
        }

        @Override // r7.r
        public final Object invoke(OnCallContext<PluginConfig> onCallContext, ApplicationCall applicationCall, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, dVar);
            anonymousClass2.L$0 = onCallContext;
            anonymousClass2.L$1 = applicationCall;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                OnCallContext onCallContext = (OnCallContext) this.L$0;
                ApplicationCall applicationCall = (ApplicationCall) this.L$1;
                q<OnCallContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> qVar = this.$block;
                this.L$0 = null;
                this.label = 1;
                Object objInvoke = qVar.invoke(onCallContext, applicationCall, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCallReceive$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class C18501 extends m implements p<PluginConfig, PipelineContext<Object, ApplicationCall>, OnCallReceiveContext<PluginConfig>> {
        public static final C18501 INSTANCE = new C18501();

        public C18501() {
            super(2, OnCallReceiveContext.class, "<init>", "<init>(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", 0);
        }

        @Override // r7.p
        public final OnCallReceiveContext<PluginConfig> invoke(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
            return new OnCallReceiveContext<>(pluginconfig, pipelineContext);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCallReceive$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"", "PluginConfig", "Lio/ktor/server/application/OnCallReceiveContext;", "Lio/ktor/server/application/ApplicationCall;", "call", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/OnCallReceiveContext;Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.PluginBuilder$onCallReceive$2", f = "PluginBuilder.kt", l = {107}, m = "invokeSuspend")
    public static final class C18512 extends j implements r<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, d<? super t0>, Object> {
        final /* synthetic */ r<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18512(r<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, Object, ? super d<? super t0>, ? extends Object> rVar, d<? super C18512> dVar) {
            super(4, dVar);
            this.$block = rVar;
        }

        @Override // r7.r
        public final Object invoke(OnCallReceiveContext<PluginConfig> onCallReceiveContext, ApplicationCall applicationCall, Object obj, d<? super t0> dVar) {
            C18512 c18512 = new C18512(this.$block, dVar);
            c18512.L$0 = onCallReceiveContext;
            c18512.L$1 = applicationCall;
            c18512.L$2 = obj;
            return c18512.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                OnCallReceiveContext onCallReceiveContext = (OnCallReceiveContext) this.L$0;
                ApplicationCall applicationCall = (ApplicationCall) this.L$1;
                Object obj2 = this.L$2;
                r<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, d<? super t0>, Object> rVar = this.$block;
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objInvoke = rVar.invoke(onCallReceiveContext, applicationCall, obj2, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCallReceive$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"", "PluginConfig", "Lio/ktor/server/application/OnCallReceiveContext;", "Lio/ktor/server/application/ApplicationCall;", "call", "<anonymous parameter 1>", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/OnCallReceiveContext;Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.PluginBuilder$onCallReceive$3", f = "PluginBuilder.kt", l = {163}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements r<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, d<? super t0>, Object> {
        final /* synthetic */ q<OnCallReceiveContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(q<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass3> dVar) {
            super(4, dVar);
            this.$block = qVar;
        }

        @Override // r7.r
        public final Object invoke(OnCallReceiveContext<PluginConfig> onCallReceiveContext, ApplicationCall applicationCall, Object obj, d<? super t0> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$block, dVar);
            anonymousClass3.L$0 = onCallReceiveContext;
            anonymousClass3.L$1 = applicationCall;
            return anonymousClass3.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                OnCallReceiveContext onCallReceiveContext = (OnCallReceiveContext) this.L$0;
                ApplicationCall applicationCall = (ApplicationCall) this.L$1;
                q<OnCallReceiveContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> qVar = this.$block;
                this.L$0 = null;
                this.label = 1;
                Object objInvoke = qVar.invoke(onCallReceiveContext, applicationCall, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCallRespond$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class C18521 extends m implements p<PluginConfig, PipelineContext<Object, ApplicationCall>, OnCallRespondContext<PluginConfig>> {
        public static final C18521 INSTANCE = new C18521();

        public C18521() {
            super(2, OnCallRespondContext.class, "<init>", "<init>(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", 0);
        }

        @Override // r7.p
        public final OnCallRespondContext<PluginConfig> invoke(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
            return new OnCallRespondContext<>(pluginconfig, pipelineContext);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onCallRespond$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"", "PluginConfig", "Lio/ktor/server/application/OnCallRespondContext;", "Lio/ktor/server/application/ApplicationCall;", "call", "<anonymous parameter 1>", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/OnCallRespondContext;Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.PluginBuilder$onCallRespond$2", f = "PluginBuilder.kt", l = {176}, m = "invokeSuspend")
    public static final class C18532 extends j implements r<OnCallRespondContext<PluginConfig>, ApplicationCall, Object, d<? super t0>, Object> {
        final /* synthetic */ q<OnCallRespondContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18532(q<? super OnCallRespondContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> qVar, d<? super C18532> dVar) {
            super(4, dVar);
            this.$block = qVar;
        }

        @Override // r7.r
        public final Object invoke(OnCallRespondContext<PluginConfig> onCallRespondContext, ApplicationCall applicationCall, Object obj, d<? super t0> dVar) {
            C18532 c18532 = new C18532(this.$block, dVar);
            c18532.L$0 = onCallRespondContext;
            c18532.L$1 = applicationCall;
            return c18532.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                OnCallRespondContext onCallRespondContext = (OnCallRespondContext) this.L$0;
                ApplicationCall applicationCall = (ApplicationCall) this.L$1;
                q<OnCallRespondContext<PluginConfig>, ApplicationCall, d<? super t0>, Object> qVar = this.$block;
                this.L$0 = null;
                this.label = 1;
                Object objInvoke = qVar.invoke(onCallRespondContext, applicationCall, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    /* JADX INFO: Add missing generic type declarations: [T, ContextT] */
    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onDefaultPhase$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\t\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002\"\b\b\u0002\u0010\u0004*\u00020\u0000*\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0000H\u008a@"}, d2 = {"", "T", "Lio/ktor/server/application/CallContext;", "ContextT", "PluginConfig", "Lio/ktor/server/application/ApplicationCall;", "call", TtmlNode.TAG_BODY, "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.application.PluginBuilder$onDefaultPhase$1", f = "PluginBuilder.kt", l = {215}, m = "invokeSuspend")
    public static final class C18541<ContextT, T> extends j implements r<ContextT, ApplicationCall, T, d<? super t0>, Object> {
        final /* synthetic */ r<ContextT, ApplicationCall, T, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18541(r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> rVar, d<? super C18541> dVar) {
            super(4, dVar);
            this.$block = rVar;
        }

        /* JADX WARN: Incorrect types in method signature: (TContextT;Lio/ktor/server/application/ApplicationCall;TT;Ld7/d<-Lx6/t0;>;)Ljava/lang/Object; */
        @Override // r7.r
        public final Object invoke(CallContext callContext, ApplicationCall applicationCall, Object obj, d dVar) {
            C18541 c18541 = new C18541(this.$block, dVar);
            c18541.L$0 = callContext;
            c18541.L$1 = applicationCall;
            c18541.L$2 = obj;
            return c18541.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                CallContext callContext = (CallContext) this.L$0;
                ApplicationCall applicationCall = (ApplicationCall) this.L$1;
                Object obj2 = this.L$2;
                r<ContextT, ApplicationCall, T, d<? super t0>, Object> rVar = this.$block;
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objInvoke = rVar.invoke(callContext, applicationCall, obj2, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
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

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002\"\b\b\u0002\u0010\u0004*\u00020\u00002\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"", "T", "Lio/ktor/server/application/CallContext;", "ContextT", "PluginConfig", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "pipeline", "Lx6/t0;", "invoke", "(Lio/ktor/util/pipeline/Pipeline;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18551<T> extends kotlin.jvm.internal.r implements l<Pipeline<T, ApplicationCall>, t0> {
        final /* synthetic */ r<ContextT, ApplicationCall, T, d<? super t0>, Object> $block;
        final /* synthetic */ p<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> $contextInit;
        final /* synthetic */ String $handlerName;
        final /* synthetic */ PipelinePhase $phase;
        final /* synthetic */ PluginBuilder<PluginConfig> this$0;

        /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\t\u001a\u00020\b\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002\"\b\b\u0002\u0010\u0004*\u00020\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00028\u0000H\u008a@"}, d2 = {"", "T", "Lio/ktor/server/application/CallContext;", "ContextT", "PluginConfig", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        @e(c = "io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1", f = "PluginBuilder.kt", l = {194}, m = "invokeSuspend")
        public static final class C02321 extends j implements q<PipelineContext<T, ApplicationCall>, T, d<? super t0>, Object> {
            final /* synthetic */ r<ContextT, ApplicationCall, T, d<? super t0>, Object> $block;
            final /* synthetic */ p<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> $contextInit;
            final /* synthetic */ String $handlerName;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ PluginBuilder<PluginConfig> this$0;

            /* JADX INFO: renamed from: io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00020\u0002\"\b\b\u0002\u0010\u0004*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "T", "Lio/ktor/server/application/CallContext;", "ContextT", "PluginConfig", "Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {1, 8, 0})
            @e(c = "io.ktor.server.application.PluginBuilder$onDefaultPhaseWithMessage$1$1$1", f = "PluginBuilder.kt", l = {195, 198, 200}, m = "invokeSuspend")
            public static final class C02331 extends j implements l<d<? super t0>, Object> {
                final /* synthetic */ PipelineContext<T, ApplicationCall> $$this$intercept;
                final /* synthetic */ r<ContextT, ApplicationCall, T, d<? super t0>, Object> $block;
                final /* synthetic */ p<PluginConfig, PipelineContext<T, ApplicationCall>, ContextT> $contextInit;
                final /* synthetic */ String $handlerName;
                final /* synthetic */ AttributeKey<PluginInstance> $key;
                final /* synthetic */ PluginConfig $pluginConfig;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C02331(AttributeKey<PluginInstance> attributeKey, String str, r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> rVar, p<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> pVar, PluginConfig pluginconfig, PipelineContext<T, ApplicationCall> pipelineContext, d<? super C02331> dVar) {
                    super(1, dVar);
                    this.$key = attributeKey;
                    this.$handlerName = str;
                    this.$block = rVar;
                    this.$contextInit = pVar;
                    this.$pluginConfig = pluginconfig;
                    this.$$this$intercept = pipelineContext;
                }

                @Override // f7.a
                public final d<t0> create(d<?> dVar) {
                    return new C02331(this.$key, this.$handlerName, this.$block, this.$contextInit, this.$pluginConfig, this.$$this$intercept, dVar);
                }

                @Override // r7.l
                public final Object invoke(d<? super t0> dVar) {
                    return ((C02331) create(dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Code restructure failed: missing block: B:19:0x0068, code lost:
                
                    if (io.ktor.server.application.debug.UtilsKt.ijDebugReportHandlerFinished(r7, r0, r6) != r4) goto L21;
                 */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                    /*
                        r6 = this;
                        int r0 = r6.label
                        r1 = 3
                        r2 = 2
                        r3 = 1
                        e7.a r4 = e7.a.f15033i
                        if (r0 == 0) goto L23
                        if (r0 == r3) goto L1f
                        if (r0 == r2) goto L1b
                        if (r0 != r1) goto L13
                        k2.c.G(r7)
                        goto L6b
                    L13:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r0)
                        throw r7
                    L1b:
                        k2.c.G(r7)
                        goto L5a
                    L1f:
                        k2.c.G(r7)
                        goto L37
                    L23:
                        k2.c.G(r7)
                        io.ktor.util.AttributeKey<io.ktor.server.application.PluginInstance> r7 = r6.$key
                        java.lang.String r7 = r7.getName()
                        java.lang.String r0 = r6.$handlerName
                        r6.label = r3
                        java.lang.Object r7 = io.ktor.server.application.debug.UtilsKt.ijDebugReportHandlerStarted(r7, r0, r6)
                        if (r7 != r4) goto L37
                        goto L6a
                    L37:
                        r7.r<ContextT, io.ktor.server.application.ApplicationCall, T, d7.d<? super x6.t0>, java.lang.Object> r7 = r6.$block
                        r7.p<PluginConfig, io.ktor.util.pipeline.PipelineContext<T, io.ktor.server.application.ApplicationCall>, ContextT> r0 = r6.$contextInit
                        PluginConfig r3 = r6.$pluginConfig
                        io.ktor.util.pipeline.PipelineContext<T, io.ktor.server.application.ApplicationCall> r5 = r6.$$this$intercept
                        java.lang.Object r0 = r0.invoke(r3, r5)
                        io.ktor.util.pipeline.PipelineContext<T, io.ktor.server.application.ApplicationCall> r3 = r6.$$this$intercept
                        java.lang.Object r3 = r3.getContext()
                        io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
                        io.ktor.util.pipeline.PipelineContext<T, io.ktor.server.application.ApplicationCall> r5 = r6.$$this$intercept
                        java.lang.Object r5 = r5.getSubject()
                        r6.label = r2
                        java.lang.Object r7 = r7.invoke(r0, r3, r5, r6)
                        if (r7 != r4) goto L5a
                        goto L6a
                    L5a:
                        io.ktor.util.AttributeKey<io.ktor.server.application.PluginInstance> r7 = r6.$key
                        java.lang.String r7 = r7.getName()
                        java.lang.String r0 = r6.$handlerName
                        r6.label = r1
                        java.lang.Object r7 = io.ktor.server.application.debug.UtilsKt.ijDebugReportHandlerFinished(r7, r0, r6)
                        if (r7 != r4) goto L6b
                    L6a:
                        return r4
                    L6b:
                        x6.t0 r7 = x6.t0.f22605a
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.PluginBuilder.C18551.C02321.C02331.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C02321(PluginBuilder<PluginConfig> pluginBuilder, String str, r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> rVar, p<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> pVar, d<? super C02321> dVar) {
                super(3, dVar);
                this.this$0 = pluginBuilder;
                this.$handlerName = str;
                this.$block = rVar;
                this.$contextInit = pVar;
            }

            @Override // r7.q
            public final Object invoke(PipelineContext<T, ApplicationCall> pipelineContext, T t2, d<? super t0> dVar) {
                C02321 c02321 = new C02321(this.this$0, this.$handlerName, this.$block, this.$contextInit, dVar);
                c02321.L$0 = pipelineContext;
                return c02321.invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PipelineContext pipelineContext = (PipelineContext) this.L$0;
                    AttributeKey<PluginInstance> key$ktor_server_core = this.this$0.getKey$ktor_server_core();
                    PluginConfig pluginConfig = this.this$0.getPluginConfig();
                    String name = key$ktor_server_core.getName();
                    C02331 c02331 = new C02331(key$ktor_server_core, this.$handlerName, this.$block, this.$contextInit, pluginConfig, pipelineContext, null);
                    this.label = 1;
                    Object objAddToContextInDebugMode = ContextUtilsKt.addToContextInDebugMode(name, c02331, this);
                    a aVar = a.f15033i;
                    if (objAddToContextInDebugMode == aVar) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18551(PipelinePhase pipelinePhase, PluginBuilder<PluginConfig> pluginBuilder, String str, r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> rVar, p<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> pVar) {
            super(1);
            this.$phase = pipelinePhase;
            this.this$0 = pluginBuilder;
            this.$handlerName = str;
            this.$block = rVar;
            this.$contextInit = pVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Pipeline) obj);
            return t0.f22605a;
        }

        public final void invoke(Pipeline<T, ApplicationCall> pipeline) {
            pipeline.intercept(this.$phase, new C02321(this.this$0, this.$handlerName, this.$block, this.$contextInit, null));
        }
    }

    public PluginBuilder(AttributeKey<PluginInstance> attributeKey) {
        this.key = attributeKey;
    }

    private final <T, ContextT extends CallContext<PluginConfig>> void onDefaultPhase(List<Interception<T>> interceptions, PipelinePhase phase, String handlerName, p<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> contextInit, r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> block) {
        onDefaultPhaseWithMessage(interceptions, phase, handlerName, contextInit, new C18541(block, null));
    }

    private final <T, ContextT extends CallContext<PluginConfig>> void onDefaultPhaseWithMessage(List<Interception<T>> interceptions, PipelinePhase phase, String handlerName, p<? super PluginConfig, ? super PipelineContext<T, ApplicationCall>, ? extends ContextT> contextInit, r<? super ContextT, ? super ApplicationCall, ? super T, ? super d<? super t0>, ? extends Object> block) {
        interceptions.add(new Interception<>(phase, new C18551(phase, this, handlerName, block, contextInit)));
    }

    public final List<Interception<Object>> getAfterResponseInterceptions$ktor_server_core() {
        return this.afterResponseInterceptions;
    }

    public abstract Application getApplication();

    public final ApplicationConfig getApplicationConfig() {
        ApplicationEnvironment environment = getEnvironment();
        if (environment != null) {
            return environment.getConfig();
        }
        return null;
    }

    public final List<Interception<t0>> getCallInterceptions$ktor_server_core() {
        return this.callInterceptions;
    }

    public final ApplicationEnvironment getEnvironment() {
        return getPipeline().getEnvironment();
    }

    public final List<HookHandler<?>> getHooks$ktor_server_core() {
        return this.hooks;
    }

    public final AttributeKey<PluginInstance> getKey$ktor_server_core() {
        return this.key;
    }

    public final List<Interception<Object>> getOnReceiveInterceptions$ktor_server_core() {
        return this.onReceiveInterceptions;
    }

    public final List<Interception<Object>> getOnResponseInterceptions$ktor_server_core() {
        return this.onResponseInterceptions;
    }

    /* JADX INFO: renamed from: getPipeline$ktor_server_core */
    public abstract ApplicationCallPipeline getPipeline();

    public abstract PluginConfig getPluginConfig();

    public final PipelinePhase newPhase$ktor_server_core() {
        return new PipelinePhase(this.key.getName() + "Phase" + v7.d.f22178l.d());
    }

    public final <HookHandler> void on(Hook<HookHandler> hook, HookHandler handler) {
        this.hooks.add(new HookHandler<>(hook, handler));
    }

    public final void onCall(q<? super OnCallContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> block) {
        onDefaultPhase(this.callInterceptions, ApplicationCallPipeline.INSTANCE.getPlugins(), DebugPhaseNamesKt.PHASE_ON_CALL, AnonymousClass1.INSTANCE, new AnonymousClass2(block, null));
    }

    public final void onCallReceive(r<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, Object, ? super d<? super t0>, ? extends Object> block) {
        onDefaultPhase(this.onReceiveInterceptions, ApplicationReceivePipeline.INSTANCE.getTransform(), DebugPhaseNamesKt.PHASE_ON_CALL_RECEIVE, C18501.INSTANCE, new C18512(block, null));
    }

    public final void onCallRespond(r<? super OnCallRespondContext<PluginConfig>, ? super ApplicationCall, Object, ? super d<? super t0>, ? extends Object> block) {
        onDefaultPhase(this.onResponseInterceptions, ApplicationSendPipeline.INSTANCE.getTransform(), DebugPhaseNamesKt.PHASE_ON_CALL_RESPOND, C18521.INSTANCE, block);
    }

    public final void onCallReceive(q<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> block) {
        onCallReceive(new AnonymousClass3(block, null));
    }

    public final void onCallRespond(q<? super OnCallRespondContext<PluginConfig>, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> block) {
        onCallRespond(new C18532(block, null));
    }
}
