package io.ktor.server.plugins.callloging;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationKt;
import io.ktor.server.application.PluginBuilder;
import io.ktor.server.application.hooks.CallSetup;
import io.ktor.server.http.content.StaticContentKt;
import java.util.Iterator;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/PluginBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CallLoggingKt$CallLogging$2 extends r implements l<PluginBuilder<CallLoggingConfig>, t0> {
    public static final CallLoggingKt$CallLogging$2 INSTANCE = new CallLoggingKt$CallLogging$2();

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends m implements l<String, t0> {
        final /* synthetic */ b $log;
        final /* synthetic */ PluginBuilder<CallLoggingConfig> $this_createApplicationPlugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PluginBuilder<CallLoggingConfig> pluginBuilder, b bVar) {
            super(1, o.class, "log", "invoke$log(Lio/ktor/server/application/PluginBuilder;Lorg/slf4j/Logger;Ljava/lang/String;)V", 0);
            this.$this_createApplicationPlugin = pluginBuilder;
            this.$log = bVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((String) obj);
            return t0.f22605a;
        }

        public final void invoke(String str) {
            CallLoggingKt$CallLogging$2.invoke$log(this.$this_createApplicationPlugin, this.$log, str);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "call", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$2", f = "CallLogging.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ a<Long> $clock;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(a<Long> aVar, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$clock = aVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clock, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ApplicationCall applicationCall, d<? super t0> dVar) {
            return ((AnonymousClass2) create(applicationCall, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            ((ApplicationCall) this.L$0).getAttributes().put(CallLoggingKt.getCALL_START_TIME(), this.$clock.invoke());
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass3 extends m implements l<ApplicationCall, t0> {
        final /* synthetic */ List<l<ApplicationCall, Boolean>> $filters;
        final /* synthetic */ l<ApplicationCall, String> $formatCall;
        final /* synthetic */ boolean $ignoreStaticContent;
        final /* synthetic */ b $log;
        final /* synthetic */ PluginBuilder<CallLoggingConfig> $this_createApplicationPlugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(boolean z, List<l<ApplicationCall, Boolean>> list, l<? super ApplicationCall, String> lVar, PluginBuilder<CallLoggingConfig> pluginBuilder, b bVar) {
            super(1, o.class, "logSuccess", "invoke$logSuccess(ZLjava/util/List;Lkotlin/jvm/functions/Function1;Lio/ktor/server/application/PluginBuilder;Lorg/slf4j/Logger;Lio/ktor/server/application/ApplicationCall;)V", 0);
            this.$ignoreStaticContent = z;
            this.$filters = list;
            this.$formatCall = lVar;
            this.$this_createApplicationPlugin = pluginBuilder;
            this.$log = bVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationCall) obj);
            return t0.f22605a;
        }

        public final void invoke(ApplicationCall applicationCall) {
            CallLoggingKt$CallLogging$2.invoke$logSuccess(this.$ignoreStaticContent, this.$filters, this.$formatCall, this.$this_createApplicationPlugin, this.$log, applicationCall);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingKt$CallLogging$2$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass4 extends m implements l<ApplicationCall, t0> {
        final /* synthetic */ List<l<ApplicationCall, Boolean>> $filters;
        final /* synthetic */ l<ApplicationCall, String> $formatCall;
        final /* synthetic */ boolean $ignoreStaticContent;
        final /* synthetic */ b $log;
        final /* synthetic */ PluginBuilder<CallLoggingConfig> $this_createApplicationPlugin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(boolean z, List<l<ApplicationCall, Boolean>> list, l<? super ApplicationCall, String> lVar, PluginBuilder<CallLoggingConfig> pluginBuilder, b bVar) {
            super(1, o.class, "logSuccess", "invoke$logSuccess(ZLjava/util/List;Lkotlin/jvm/functions/Function1;Lio/ktor/server/application/PluginBuilder;Lorg/slf4j/Logger;Lio/ktor/server/application/ApplicationCall;)V", 0);
            this.$ignoreStaticContent = z;
            this.$filters = list;
            this.$formatCall = lVar;
            this.$this_createApplicationPlugin = pluginBuilder;
            this.$log = bVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationCall) obj);
            return t0.f22605a;
        }

        public final void invoke(ApplicationCall applicationCall) {
            CallLoggingKt$CallLogging$2.invoke$logSuccess(this.$ignoreStaticContent, this.$filters, this.$formatCall, this.$this_createApplicationPlugin, this.$log, applicationCall);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[wc.b.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CallLoggingKt$CallLogging$2() {
        super(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$log(PluginBuilder<CallLoggingConfig> pluginBuilder, b bVar, String str) {
        int iOrdinal = pluginBuilder.getPluginConfig().getLevel().ordinal();
        if (iOrdinal == 0) {
            bVar.a(str);
            return;
        }
        if (iOrdinal == 1) {
            bVar.g(str);
            return;
        }
        if (iOrdinal == 2) {
            bVar.f(str);
        } else if (iOrdinal == 3) {
            bVar.d(str);
        } else {
            if (iOrdinal != 4) {
                throw new NoWhenBranchMatchedException();
            }
            bVar.h(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$logSuccess(boolean z, List<l<ApplicationCall, Boolean>> list, l<? super ApplicationCall, String> lVar, PluginBuilder<CallLoggingConfig> pluginBuilder, b bVar, ApplicationCall applicationCall) {
        if (z && StaticContentKt.isStaticContent(applicationCall)) {
            return;
        }
        if (!list.isEmpty()) {
            if (list.isEmpty()) {
                return;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((Boolean) ((l) it.next()).invoke(applicationCall)).booleanValue()) {
                }
            }
            return;
        }
        invoke$log(pluginBuilder, bVar, (String) lVar.invoke(applicationCall));
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<CallLoggingConfig>) obj);
        return t0.f22605a;
    }

    public final void invoke(PluginBuilder<CallLoggingConfig> pluginBuilder) {
        b logger = pluginBuilder.getPluginConfig().getLogger();
        if (logger == null) {
            logger = ApplicationKt.getLog(pluginBuilder.getApplication());
        }
        b bVar = logger;
        List<l<ApplicationCall, Boolean>> filters$ktor_server_call_logging = pluginBuilder.getPluginConfig().getFilters$ktor_server_call_logging();
        l<ApplicationCall, String> formatCall$ktor_server_call_logging = pluginBuilder.getPluginConfig().getFormatCall$ktor_server_call_logging();
        a<Long> clock$ktor_server_call_logging = pluginBuilder.getPluginConfig().getClock$ktor_server_call_logging();
        boolean ignoreStaticContent = pluginBuilder.getPluginConfig().getIgnoreStaticContent();
        MDCProviderKt.setupMDCProvider(pluginBuilder);
        CallLoggingKt.setupLogging(pluginBuilder.getApplication().getEnvironment().getMonitor(), new AnonymousClass1(pluginBuilder, bVar));
        pluginBuilder.on(CallSetup.INSTANCE, new AnonymousClass2(clock$ktor_server_call_logging, null));
        if (pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging().isEmpty()) {
            CallLoggingKt.logCompletedCalls(pluginBuilder, new AnonymousClass3(ignoreStaticContent, filters$ktor_server_call_logging, formatCall$ktor_server_call_logging, pluginBuilder, bVar));
        } else {
            CallLoggingKt.logCallsWithMDC(pluginBuilder, new AnonymousClass4(ignoreStaticContent, filters$ktor_server_call_logging, formatCall$ktor_server_call_logging, pluginBuilder, bVar));
        }
    }
}
