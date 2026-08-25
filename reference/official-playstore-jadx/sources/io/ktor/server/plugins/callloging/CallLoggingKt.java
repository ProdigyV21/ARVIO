package io.ktor.server.plugins.callloging;

import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.events.Events;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.ApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.application.PluginBuilder;
import io.ktor.util.AttributeKey;
import io.ktor.util.date.DateJvmKt;
import java.util.List;
import k2.c;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import ta.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0004\u001a\u00020\u0002*\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a-\u0010\u000b\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a-\u0010\r\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00070\u00062\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\r\u0010\f\u001a+\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\" \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00148\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "Lkotlin/Function0;", "", "clock", "processingTimeMillis", "(Lio/ktor/server/application/ApplicationCall;Lr7/a;)J", "Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "Lkotlin/Function1;", "Lx6/t0;", "logSuccess", "logCompletedCalls", "(Lio/ktor/server/application/PluginBuilder;Lr7/l;)V", "logCallsWithMDC", "Lio/ktor/events/Events;", "events", "", "log", "setupLogging", "(Lio/ktor/events/Events;Lr7/l;)V", "Lio/ktor/util/AttributeKey;", "CALL_START_TIME", "Lio/ktor/util/AttributeKey;", "getCALL_START_TIME", "()Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/ApplicationPlugin;", "CallLogging", "Lio/ktor/server/application/ApplicationPlugin;", "getCallLogging", "()Lio/ktor/server/application/ApplicationPlugin;", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CallLoggingKt {
    private static final AttributeKey<Long> CALL_START_TIME = new AttributeKey<>("CallStartTime");
    private static final ApplicationPlugin<CallLoggingConfig> CallLogging = CreatePluginUtilsKt.createApplicationPlugin("CallLogging", CallLoggingKt$CallLogging$1.INSTANCE, CallLoggingKt$CallLogging$2.INSTANCE);

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$1", f = "CallLogging.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<ApplicationCall, l<? super d<? super t0>, ? extends Object>, d<? super t0>, Object> {
        final /* synthetic */ List<MDCEntry> $entries;
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(List<MDCEntry> list, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$entries = list;
        }

        @Override // r7.q
        public final Object invoke(ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$entries, dVar);
            anonymousClass1.L$0 = applicationCall;
            anonymousClass1.L$1 = lVar;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                l lVar = (l) this.L$1;
                List<MDCEntry> list = this.$entries;
                b bVar = new b(MDCEntryUtilsKt.setup(list, applicationCall));
                CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1 callLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1 = new CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1(lVar, list, null);
                this.L$0 = null;
                this.label = 1;
                Object objY = m0.y(bVar, callLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1, this);
                a aVar = a.f15033i;
                if (objY == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u001c\u0010\u0006\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0002H\u008a@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "call", "Lkotlin/Function1;", "Ld7/d;", "Lx6/t0;", "", "block", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$2", f = "CallLogging.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<ApplicationCall, l<? super d<? super t0>, ? extends Object>, d<? super t0>, Object> {
        final /* synthetic */ List<MDCEntry> $entries;
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List<MDCEntry> list, d<? super AnonymousClass2> dVar) {
            super(3, dVar);
            this.$entries = list;
        }

        @Override // r7.q
        public final Object invoke(ApplicationCall applicationCall, l<? super d<? super t0>, ? extends Object> lVar, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$entries, dVar);
            anonymousClass2.L$0 = applicationCall;
            anonymousClass2.L$1 = lVar;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                l lVar = (l) this.L$1;
                List<MDCEntry> list = this.$entries;
                b bVar = new b(MDCEntryUtilsKt.setup(list, applicationCall));
                CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1 callLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1 = new CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1(lVar, list, null);
                this.L$0 = null;
                this.label = 1;
                Object objY = m0.y(bVar, callLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1, this);
                a aVar = a.f15033i;
                if (objY == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "call", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$3", f = "CallLogging.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ List<MDCEntry> $entries;
        final /* synthetic */ l<ApplicationCall, t0> $logSuccess;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(List<MDCEntry> list, l<? super ApplicationCall, t0> lVar, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$entries = list;
            this.$logSuccess = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$entries, this.$logSuccess, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // r7.p
        public final Object invoke(ApplicationCall applicationCall, d<? super t0> dVar) {
            return ((AnonymousClass3) create(applicationCall, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                List<MDCEntry> list = this.$entries;
                l<ApplicationCall, t0> lVar = this.$logSuccess;
                b bVar = new b(MDCEntryUtilsKt.setup(list, applicationCall));
                CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1 callLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1 = new CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(list, null, lVar, applicationCall);
                this.label = 1;
                Object objY = m0.y(bVar, callLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1, this);
                a aVar = a.f15033i;
                if (objY == aVar) {
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

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$logCompletedCalls$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "call", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCompletedCalls$1", f = "CallLogging.kt", l = {}, m = "invokeSuspend")
    public static final class C19031 extends j implements p<ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ l<ApplicationCall, t0> $logSuccess;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19031(l<? super ApplicationCall, t0> lVar, d<? super C19031> dVar) {
            super(2, dVar);
            this.$logSuccess = lVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C19031 c19031 = new C19031(this.$logSuccess, dVar);
            c19031.L$0 = obj;
            return c19031;
        }

        @Override // r7.p
        public final Object invoke(ApplicationCall applicationCall, d<? super t0> dVar) {
            return ((C19031) create(applicationCall, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            this.$logSuccess.invoke((ApplicationCall) this.L$0);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$processingTimeMillis$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C19041 extends r implements r7.a<Long> {
        public static final C19041 INSTANCE = new C19041();

        public C19041() {
            super(0);
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(DateJvmKt.getTimeMillis());
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$setupLogging$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19051 extends r implements l<Application, t0> {
        final /* synthetic */ Events $events;
        final /* synthetic */ l<String, t0> $log;
        final /* synthetic */ l<Application, t0> $started;
        final /* synthetic */ l<Application, t0> $starting;
        final /* synthetic */ k0 $stopped;
        final /* synthetic */ l<Application, t0> $stopping;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19051(l<? super String, t0> lVar, Events events, l<? super Application, t0> lVar2, l<? super Application, t0> lVar3, l<? super Application, t0> lVar4, k0 k0Var) {
            super(1);
            this.$log = lVar;
            this.$events = events;
            this.$starting = lVar2;
            this.$started = lVar3;
            this.$stopping = lVar4;
            this.$stopped = k0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Application) obj);
            return t0.f22605a;
        }

        public final void invoke(Application application) {
            this.$log.invoke("Application stopped: " + application);
            this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStarting(), this.$starting);
            this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStarted(), this.$started);
            this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStopping(), this.$stopping);
            this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStopped(), (l) this.$stopped.f19746i);
        }
    }

    public static final AttributeKey<Long> getCALL_START_TIME() {
        return CALL_START_TIME;
    }

    public static final ApplicationPlugin<CallLoggingConfig> getCallLogging() {
        return CallLogging;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logCallsWithMDC(PluginBuilder<CallLoggingConfig> pluginBuilder, l<? super ApplicationCall, t0> lVar) {
        List<MDCEntry> mdcEntries$ktor_server_call_logging = pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging();
        ApplicationCallPipeline.Companion companion = ApplicationCallPipeline.INSTANCE;
        pluginBuilder.on(MDCHookKt.MDCHook(companion.getMonitoring()), new AnonymousClass1(mdcEntries$ktor_server_call_logging, null));
        pluginBuilder.on(MDCHookKt.MDCHook(companion.getCall()), new AnonymousClass2(mdcEntries$ktor_server_call_logging, null));
        pluginBuilder.on(ResponseSent.INSTANCE, new AnonymousClass3(mdcEntries$ktor_server_call_logging, lVar, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logCompletedCalls(PluginBuilder<CallLoggingConfig> pluginBuilder, l<? super ApplicationCall, t0> lVar) {
        pluginBuilder.on(ResponseSent.INSTANCE, new C19031(lVar, null));
    }

    public static final long processingTimeMillis(ApplicationCall applicationCall, r7.a<Long> aVar) {
        return ((Number) aVar.invoke()).longValue() - ((Number) applicationCall.getAttributes().get(CALL_START_TIME)).longValue();
    }

    public static /* synthetic */ long processingTimeMillis$default(ApplicationCall applicationCall, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            aVar = C19041.INSTANCE;
        }
        return processingTimeMillis(applicationCall, aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupLogging(Events events, l<? super String, t0> lVar) {
        CallLoggingKt$setupLogging$starting$1 callLoggingKt$setupLogging$starting$1 = new CallLoggingKt$setupLogging$starting$1(lVar);
        CallLoggingKt$setupLogging$started$1 callLoggingKt$setupLogging$started$1 = new CallLoggingKt$setupLogging$started$1(lVar);
        CallLoggingKt$setupLogging$stopping$1 callLoggingKt$setupLogging$stopping$1 = new CallLoggingKt$setupLogging$stopping$1(lVar);
        k0 k0Var = new k0();
        k0Var.f19746i = CallLoggingKt$setupLogging$stopped$1.INSTANCE;
        k0Var.f19746i = new C19051(lVar, events, callLoggingKt$setupLogging$starting$1, callLoggingKt$setupLogging$started$1, callLoggingKt$setupLogging$stopping$1, k0Var);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStarting(), callLoggingKt$setupLogging$starting$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStarted(), callLoggingKt$setupLogging$started$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStopping(), callLoggingKt$setupLogging$stopping$1);
        events.subscribe(DefaultApplicationEventsKt.getApplicationStopped(), (l) k0Var.f19746i);
    }
}
