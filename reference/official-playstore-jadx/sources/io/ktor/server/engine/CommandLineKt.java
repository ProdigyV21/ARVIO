package io.ktor.server.engine;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigKt;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.config.ConfigLoader;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.server.config.MergedApplicationConfigKt;
import io.ktor.server.engine.BaseApplicationEngine;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.s;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;
import vc.b;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\t\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u000b\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u000e\u001a\u00020\r2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0012\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013\u001a)\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"", "", "args", "Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "Lx6/n;", "environmentBuilder", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "buildCommandLineEnvironment", "([Ljava/lang/String;Lr7/l;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "commandLineEnvironment", "([Ljava/lang/String;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Lio/ktor/server/config/ApplicationConfig;", "buildApplicationConfig", "([Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfig;", "Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "deploymentConfig", "loadCommonConfiguration", "(Lio/ktor/server/engine/BaseApplicationEngine$Configuration;Lio/ktor/server/config/ApplicationConfig;)V", "", "ch", "Lx6/x;", "splitPair", "(Ljava/lang/String;C)Lx6/x;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CommandLineKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.CommandLineKt$buildCommandLineEnvironment$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<ApplicationEngineEnvironmentBuilder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngineEnvironmentBuilder) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.CommandLineKt$commandLineEnvironment$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18791 extends r implements l<ApplicationEngineEnvironmentBuilder, t0> {
        public static final C18791 INSTANCE = new C18791();

        public C18791() {
            super(1);
        }

        public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ApplicationEngineEnvironmentBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final ApplicationConfig buildApplicationConfig(String[] strArr) {
        ApplicationConfig applicationConfigLoad$default;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            x xVarSplitPair = splitPair(str, '=');
            if (xVarSplitPair != null) {
                arrayList.add(xVarSplitPair);
            }
        }
        ArrayList<x> arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (u.P((String) ((x) obj).f22608i, "-P:", false)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(s.U(arrayList2, 10));
        for (x xVar : arrayList2) {
            arrayList3.add(new x(o.r0((String) xVar.f22608i, "-P:"), xVar.f22609l));
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (p.a(((x) obj2).f22608i, "-config")) {
                arrayList4.add(obj2);
            }
        }
        ArrayList<String> arrayList5 = new ArrayList(s.U(arrayList4, 10));
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            arrayList5.add((String) ((x) it.next()).f22609l);
        }
        MapApplicationConfig mapApplicationConfig = new MapApplicationConfig(arrayList3);
        ApplicationConfig configFromEnvironment = EnvironmentUtilsJvmKt.getConfigFromEnvironment();
        int size = arrayList5.size();
        if (size == 0) {
            ConfigLoader.Companion companion = ConfigLoader.INSTANCE;
            applicationConfigLoad$default = ConfigLoader.Companion.load$default(companion, companion, null, 1, null);
        } else if (size != 1) {
            ArrayList arrayList6 = new ArrayList(s.U(arrayList5, 10));
            for (String str2 : arrayList5) {
                ConfigLoader.Companion companion2 = ConfigLoader.INSTANCE;
                arrayList6.add(companion2.load(companion2, str2));
            }
            Iterator it2 = arrayList6.iterator();
            if (!it2.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = it2.next();
            while (it2.hasNext()) {
                next = MergedApplicationConfigKt.mergeWith((ApplicationConfig) next, (ApplicationConfig) it2.next());
            }
            applicationConfigLoad$default = (ApplicationConfig) next;
        } else {
            ConfigLoader.Companion companion3 = ConfigLoader.INSTANCE;
            applicationConfigLoad$default = companion3.load(companion3, (String) kotlin.collections.x.R0(arrayList5));
        }
        return MergedApplicationConfigKt.mergeWith(MergedApplicationConfigKt.mergeWith(applicationConfigLoad$default, configFromEnvironment), mapApplicationConfig);
    }

    public static final ApplicationEngineEnvironment buildCommandLineEnvironment(String[] strArr, l<? super ApplicationEngineEnvironmentBuilder, t0> lVar) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            x xVarSplitPair = splitPair(str, '=');
            if (xVarSplitPair != null) {
                arrayList.add(xVarSplitPair);
            }
        }
        Map mapA0 = h0.A0(arrayList);
        ApplicationConfig applicationConfigBuildApplicationConfig = buildApplicationConfig(strArr);
        String strTryGetString = ApplicationConfigKt.tryGetString(applicationConfigBuildApplicationConfig, ConfigKeys.applicationIdPath);
        if (strTryGetString == null) {
            strTryGetString = "Application";
        }
        b bVarKtorSimpleLogger = KtorSimpleLoggerJvmKt.KtorSimpleLogger(strTryGetString);
        String strTryGetString2 = (String) mapA0.get("-path");
        if (strTryGetString2 == null && (strTryGetString2 = ApplicationConfigKt.tryGetString(applicationConfigBuildApplicationConfig, ConfigKeys.rootPathPath)) == null) {
            strTryGetString2 = "";
        }
        return ApplicationEngineEnvironmentKt.applicationEngineEnvironment(new CommandLineKt$buildCommandLineEnvironment$environment$1(bVarKtorSimpleLogger, strArr, applicationConfigBuildApplicationConfig, strTryGetString2, mapA0, lVar));
    }

    public static /* synthetic */ ApplicationEngineEnvironment buildCommandLineEnvironment$default(String[] strArr, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return buildCommandLineEnvironment(strArr, lVar);
    }

    public static final ApplicationEngineEnvironment commandLineEnvironment(String[] strArr) {
        return buildCommandLineEnvironment(strArr, C18791.INSTANCE);
    }

    public static final void loadCommonConfiguration(BaseApplicationEngine.Configuration configuration, ApplicationConfig applicationConfig) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        ApplicationConfigValue applicationConfigValuePropertyOrNull = applicationConfig.propertyOrNull("callGroupSize");
        if (applicationConfigValuePropertyOrNull != null && (string5 = applicationConfigValuePropertyOrNull.getString()) != null) {
            configuration.setCallGroupSize(Integer.parseInt(string5));
        }
        ApplicationConfigValue applicationConfigValuePropertyOrNull2 = applicationConfig.propertyOrNull("connectionGroupSize");
        if (applicationConfigValuePropertyOrNull2 != null && (string4 = applicationConfigValuePropertyOrNull2.getString()) != null) {
            configuration.setConnectionGroupSize(Integer.parseInt(string4));
        }
        ApplicationConfigValue applicationConfigValuePropertyOrNull3 = applicationConfig.propertyOrNull("workerGroupSize");
        if (applicationConfigValuePropertyOrNull3 != null && (string3 = applicationConfigValuePropertyOrNull3.getString()) != null) {
            configuration.setWorkerGroupSize(Integer.parseInt(string3));
        }
        ApplicationConfigValue applicationConfigValuePropertyOrNull4 = applicationConfig.propertyOrNull("shutdownGracePeriod");
        if (applicationConfigValuePropertyOrNull4 != null && (string2 = applicationConfigValuePropertyOrNull4.getString()) != null) {
            configuration.setShutdownGracePeriod(Long.parseLong(string2));
        }
        ApplicationConfigValue applicationConfigValuePropertyOrNull5 = applicationConfig.propertyOrNull("shutdownTimeout");
        if (applicationConfigValuePropertyOrNull5 == null || (string = applicationConfigValuePropertyOrNull5.getString()) == null) {
            return;
        }
        configuration.setShutdownTimeout(Long.parseLong(string));
    }

    public static final x splitPair(String str, char c10) {
        int iE0 = o.e0(str, c10, 0, false, 6);
        if (iE0 == -1) {
            return null;
        }
        return new x(o.I0(iE0, str), o.V(iE0 + 1, str));
    }
}
