package io.ktor.server.engine;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.Application;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.internal.EngineUtilsJvmKt;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.pipeline.InvalidPhaseException;
import java.util.List;
import java.util.Locale;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.r;
import ka.s;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0001\u0016B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R&\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00128\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\n\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/server/engine/BaseApplicationEngine;", "Lio/ktor/server/engine/ApplicationEngine;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environment", "Lio/ktor/server/engine/EnginePipeline;", "pipeline", "<init>", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lio/ktor/server/engine/EnginePipeline;)V", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "resolvedConnectors", "(Ld7/d;)Ljava/lang/Object;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "getEnvironment", "()Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Lio/ktor/server/engine/EnginePipeline;", "getPipeline", "()Lio/ktor/server/engine/EnginePipeline;", "Lka/r;", "Lka/r;", "getResolvedConnectors", "()Lka/r;", "Configuration", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BaseApplicationEngine implements ApplicationEngine {
    private final ApplicationEngineEnvironment environment;
    private final EnginePipeline pipeline;
    private final r<List<EngineConnectorConfig>> resolvedConnectors;

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngine$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<Application, t0> {
        final /* synthetic */ StartupInfo $info;
        final /* synthetic */ EnginePipeline $pipeline;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StartupInfo startupInfo, EnginePipeline enginePipeline) {
            super(1);
            this.$info = startupInfo;
            this.$pipeline = enginePipeline;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws InvalidPhaseException {
            invoke((Application) obj);
            return t0.f22605a;
        }

        public final void invoke(Application application) throws InvalidPhaseException {
            if (!this.$info.getIsFirstLoading()) {
                this.$info.setInitializedStartAt(DateJvmKt.getTimeMillis());
            }
            application.getReceivePipeline().merge(this.$pipeline.getReceivePipeline());
            application.getSendPipeline().merge(this.$pipeline.getSendPipeline());
            DefaultTransformKt.installDefaultTransformations(application.getReceivePipeline());
            DefaultTransformKt.installDefaultTransformations(application.getSendPipeline());
            BaseApplicationEngineKt.installDefaultInterceptors(application);
            BaseApplicationEngineKt.installDefaultTransformationChecker(application);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngine$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements l<Application, t0> {
        final /* synthetic */ ApplicationEngineEnvironment $environment;
        final /* synthetic */ StartupInfo $info;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(StartupInfo startupInfo, ApplicationEngineEnvironment applicationEngineEnvironment) {
            super(1);
            this.$info = startupInfo;
            this.$environment = applicationEngineEnvironment;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Application) obj);
            return t0.f22605a;
        }

        public final void invoke(Application application) {
            double timeMillis = (DateJvmKt.getTimeMillis() - this.$info.getInitializedStartAt()) / 1000.0d;
            if (!this.$info.getIsFirstLoading()) {
                this.$environment.getLog().f("Application auto-reloaded in " + timeMillis + " seconds.");
                return;
            }
            this.$environment.getLog().f("Application started in " + timeMillis + " seconds.");
            this.$info.setFirstLoading(false);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationEngine$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.BaseApplicationEngine$3", f = "BaseApplicationEngine.kt", l = {75}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ r<List<EngineConnectorConfig>> $connectors;
        final /* synthetic */ b $log;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(r<List<EngineConnectorConfig>> rVar, b bVar, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$connectors = rVar;
            this.$log = bVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass3(this.$connectors, this.$log, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                r<List<EngineConnectorConfig>> rVar = this.$connectors;
                this.label = 1;
                obj = rVar.o(this);
                a aVar = a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            b bVar = this.$log;
            for (EngineConnectorConfig engineConnectorConfig : (Iterable) obj) {
                bVar.f("Responding at " + engineConnectorConfig.getType().getName().toLowerCase(Locale.ROOT) + "://" + EngineUtilsJvmKt.escapeHostname(engineConnectorConfig.getHost()) + ':' + engineConnectorConfig.getPort());
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "()V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class Configuration extends ApplicationEngine.Configuration {
    }

    public BaseApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, EnginePipeline enginePipeline) {
        this.environment = applicationEngineEnvironment;
        this.pipeline = enginePipeline;
        s sVarB = m0.b();
        this.resolvedConnectors = sVarB;
        StartupInfo startupInfo = new StartupInfo();
        BaseApplicationResponse.INSTANCE.setupSendPipeline(enginePipeline.getSendPipeline());
        applicationEngineEnvironment.getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarting(), new AnonymousClass1(startupInfo, enginePipeline));
        applicationEngineEnvironment.getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarted(), new AnonymousClass2(startupInfo, applicationEngineEnvironment));
        m0.p(l0.a(applicationEngineEnvironment.getApplication().getCoroutineContext()), null, 0, new AnonymousClass3(sVarB, applicationEngineEnvironment.getLog(), null), 3);
    }

    public static /* synthetic */ Object resolvedConnectors$suspendImpl(BaseApplicationEngine baseApplicationEngine, d<? super List<? extends EngineConnectorConfig>> dVar) {
        return baseApplicationEngine.resolvedConnectors.o(dVar);
    }

    @Override // io.ktor.server.engine.ApplicationEngine
    public Application getApplication() {
        return ApplicationEngine.DefaultImpls.getApplication(this);
    }

    @Override // io.ktor.server.engine.ApplicationEngine
    public final ApplicationEngineEnvironment getEnvironment() {
        return this.environment;
    }

    public final EnginePipeline getPipeline() {
        return this.pipeline;
    }

    public final r<List<EngineConnectorConfig>> getResolvedConnectors() {
        return this.resolvedConnectors;
    }

    @Override // io.ktor.server.engine.ApplicationEngine
    public Object resolvedConnectors(d<? super List<? extends EngineConnectorConfig>> dVar) {
        return resolvedConnectors$suspendImpl(this, dVar);
    }

    public /* synthetic */ BaseApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, EnginePipeline enginePipeline, int i10, h hVar) {
        this(applicationEngineEnvironment, (i10 & 2) != 0 ? DefaultEnginePipelineKt.defaultEnginePipeline(applicationEngineEnvironment) : enginePipeline);
    }
}
