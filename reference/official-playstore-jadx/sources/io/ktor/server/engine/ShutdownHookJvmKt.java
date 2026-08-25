package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.DefaultApplicationEventsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u0002*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "Lkotlin/Function0;", "Lx6/t0;", "stop", "addShutdownHook", "(Lio/ktor/server/engine/ApplicationEngine;Lr7/a;)V", "", "SHUTDOWN_HOOK_DISABLED", "Z", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShutdownHookJvmKt {
    private static final boolean SHUTDOWN_HOOK_DISABLED = p.a(System.getProperty("io.ktor.server.engine.ShutdownHook", "true"), "false");

    /* JADX INFO: renamed from: io.ktor.server.engine.ShutdownHookJvmKt$addShutdownHook$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Application, t0> {
        final /* synthetic */ ShutdownHook $hook;
        final /* synthetic */ ApplicationEngine $this_addShutdownHook;

        /* JADX INFO: renamed from: io.ktor.server.engine.ShutdownHookJvmKt$addShutdownHook$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/Application;", "it", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/Application;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C02371 extends r implements l<Application, t0> {
            final /* synthetic */ ShutdownHook $hook;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02371(ShutdownHook shutdownHook) {
                super(1);
                this.$hook = shutdownHook;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Application) obj);
                return t0.f22605a;
            }

            public final void invoke(Application application) {
                try {
                    Runtime.getRuntime().removeShutdownHook(this.$hook);
                } catch (IllegalStateException unused) {
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ApplicationEngine applicationEngine, ShutdownHook shutdownHook) {
            super(1);
            this.$this_addShutdownHook = applicationEngine;
            this.$hook = shutdownHook;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Application) obj);
            return t0.f22605a;
        }

        public final void invoke(Application application) {
            this.$this_addShutdownHook.getEnvironment().getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStopping(), new C02371(this.$hook));
            Runtime.getRuntime().addShutdownHook(this.$hook);
        }
    }

    public static final void addShutdownHook(ApplicationEngine applicationEngine, a<t0> aVar) {
        if (SHUTDOWN_HOOK_DISABLED) {
            return;
        }
        applicationEngine.getEnvironment().getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarting(), new AnonymousClass1(applicationEngine, new ShutdownHook(aVar)));
    }
}
