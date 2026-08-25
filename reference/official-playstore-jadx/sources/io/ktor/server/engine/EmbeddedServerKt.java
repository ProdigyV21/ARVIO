package io.ktor.server.engine;

import d7.j;
import d7.k;
import io.ktor.server.application.Application;
import io.ktor.server.engine.ApplicationEngine;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ka.k0;
import ka.n1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008d\u0001\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u009b\u0001\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00020\u00142\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u0012\u0010\u0017\u001a\u009d\u0001\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00020\u00142\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0014\b\u0002\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u0018\"\u00020\u00192\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e2\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u0012\u0010\u001b\u001aX\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u001c2\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e¢\u0006\u0004\b\u0012\u0010\u001e¨\u0006\u001f"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngineFactory;", "factory", "", "port", "", "host", "", "watchPaths", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "Lio/ktor/server/application/Application;", "module", "embeddedServer", "(Lio/ktor/server/engine/ApplicationEngineFactory;ILjava/lang/String;Ljava/util/List;Lr7/l;Lr7/l;)Lio/ktor/server/engine/ApplicationEngine;", "Lka/k0;", "Ld7/j;", "parentCoroutineContext", "(Lka/k0;Lio/ktor/server/engine/ApplicationEngineFactory;ILjava/lang/String;Ljava/util/List;Ld7/j;Lr7/l;Lr7/l;)Lio/ktor/server/engine/ApplicationEngine;", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "connectors", "(Lka/k0;Lio/ktor/server/engine/ApplicationEngineFactory;[Lio/ktor/server/engine/EngineConnectorConfig;Ljava/util/List;Ld7/j;Lr7/l;Lr7/l;)Lio/ktor/server/engine/ApplicationEngine;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environment", "(Lio/ktor/server/engine/ApplicationEngineFactory;Lio/ktor/server/engine/ApplicationEngineEnvironment;Lr7/l;)Lio/ktor/server/engine/ApplicationEngine;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmbeddedServerKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.EmbeddedServerKt$embeddedServer$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00028\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngine$Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(ApplicationEngine.Configuration configuration) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngine.Configuration) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.EmbeddedServerKt$embeddedServer$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00028\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngine$Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(ApplicationEngine.Configuration configuration) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngine.Configuration) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.EmbeddedServerKt$embeddedServer$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00028\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngine$Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(ApplicationEngine.Configuration configuration) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngine.Configuration) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.EmbeddedServerKt$embeddedServer$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00028\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "TConfiguration", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngine$Configuration;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(ApplicationEngine.Configuration configuration) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngine.Configuration) obj);
            return t0.f22605a;
        }
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, int i10, String str, List<String> list, l<? super TConfiguration, t0> lVar, l<? super Application, t0> lVar2) {
        return (TEngine) embeddedServer(n1.f19624i, applicationEngineFactory, i10, str, list, k.f14688i, lVar, lVar2);
    }

    public static ApplicationEngine embeddedServer$default(ApplicationEngineFactory applicationEngineFactory, int i10, String str, List list, l lVar, l lVar2, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 80;
        }
        if ((i11 & 4) != 0) {
            str = "0.0.0.0";
        }
        if ((i11 & 8) != 0) {
            list = Collections.singletonList(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
        }
        if ((i11 & 16) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        List list2 = list;
        return embeddedServer(applicationEngineFactory, i10, str, list2, lVar, lVar2);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(k0 k0Var, ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, int i10, String str, List<String> list, j jVar, l<? super TConfiguration, t0> lVar, l<? super Application, t0> lVar2) {
        EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder(null, 1, null);
        engineConnectorBuilder.setPort(i10);
        engineConnectorBuilder.setHost(str);
        return (TEngine) embeddedServer(k0Var, applicationEngineFactory, (EngineConnectorConfig[]) Arrays.copyOf(new EngineConnectorConfig[]{engineConnectorBuilder}, 1), list, jVar, lVar, lVar2);
    }

    public static ApplicationEngine embeddedServer$default(k0 k0Var, ApplicationEngineFactory applicationEngineFactory, int i10, String str, List list, j jVar, l lVar, l lVar2, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 80;
        }
        int i12 = i10;
        if ((i11 & 4) != 0) {
            str = "0.0.0.0";
        }
        String str2 = str;
        if ((i11 & 8) != 0) {
            list = Collections.singletonList(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
        }
        List list2 = list;
        if ((i11 & 16) != 0) {
            jVar = k.f14688i;
        }
        j jVar2 = jVar;
        if ((i11 & 32) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return embeddedServer(k0Var, applicationEngineFactory, i12, str2, list2, jVar2, lVar, lVar2);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(k0 k0Var, ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, EngineConnectorConfig[] engineConnectorConfigArr, List<String> list, j jVar, l<? super TConfiguration, t0> lVar, l<? super Application, t0> lVar2) {
        return (TEngine) embeddedServer(applicationEngineFactory, ApplicationEngineEnvironmentKt.applicationEngineEnvironment(new EmbeddedServerKt$embeddedServer$environment$1(k0Var, jVar, list, lVar2, engineConnectorConfigArr)), lVar);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, ApplicationEngineEnvironment applicationEngineEnvironment, l<? super TConfiguration, t0> lVar) {
        return (TEngine) applicationEngineFactory.create(applicationEngineEnvironment, lVar);
    }

    public static ApplicationEngine embeddedServer$default(k0 k0Var, ApplicationEngineFactory applicationEngineFactory, EngineConnectorConfig[] engineConnectorConfigArr, List list, j jVar, l lVar, l lVar2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            engineConnectorConfigArr = new EngineConnectorBuilder[]{new EngineConnectorBuilder(null, 1, null)};
        }
        EngineConnectorConfig[] engineConnectorConfigArr2 = engineConnectorConfigArr;
        if ((i10 & 4) != 0) {
            list = Collections.singletonList(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
        }
        List list2 = list;
        if ((i10 & 8) != 0) {
            jVar = k.f14688i;
        }
        j jVar2 = jVar;
        if ((i10 & 16) != 0) {
            lVar = AnonymousClass3.INSTANCE;
        }
        return embeddedServer(k0Var, applicationEngineFactory, engineConnectorConfigArr2, list2, jVar2, lVar, lVar2);
    }

    public static /* synthetic */ ApplicationEngine embeddedServer$default(ApplicationEngineFactory applicationEngineFactory, ApplicationEngineEnvironment applicationEngineEnvironment, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        return embeddedServer(applicationEngineFactory, applicationEngineEnvironment, lVar);
    }
}
