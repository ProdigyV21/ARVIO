package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\t\u001a\u00020\b2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", "", "args", "Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "Lx6/n;", "environmentBuilder", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "commandLineEnvironment", "([Ljava/lang/String;Lr7/l;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CommandLineJvmKt {

    /* JADX INFO: renamed from: io.ktor.server.engine.CommandLineJvmKt$commandLineEnvironment$1, reason: invalid class name */
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

    public static final ApplicationEngineEnvironment commandLineEnvironment(String[] strArr, l<? super ApplicationEngineEnvironmentBuilder, t0> lVar) {
        return CommandLineKt.buildCommandLineEnvironment(strArr, lVar);
    }

    public static /* synthetic */ ApplicationEngineEnvironment commandLineEnvironment$default(String[] strArr, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return commandLineEnvironment(strArr, lVar);
    }
}
