package io.ktor.server.engine;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.application.BaseApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.application.PluginInstance;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.Pipeline;
import ka.k0;
import ka.r;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00152\u00020\u0001:\u0003\u0015\u0016\u0017B(\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R(\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl;", "", "", "url", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lx6/n;", "exitCode", "<init>", "(Ljava/lang/String;Lr7/l;)V", "call", "Lx6/t0;", "doShutdown", "(Lio/ktor/server/application/ApplicationCall;Ld7/d;)Ljava/lang/Object;", "Ljava/lang/String;", "getUrl", "()Ljava/lang/String;", "Lr7/l;", "getExitCode", "()Lr7/l;", "Companion", "Config", "EnginePlugin", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ShutDownUrl {
    private final l<ApplicationCall, Integer> exitCode;
    private final String url;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final BaseApplicationPlugin<Application, Config, PluginInstance> ApplicationCallPlugin = CreatePluginUtilsKt.createApplicationPlugin("shutdown.url", ShutDownUrl$Companion$ApplicationCallPlugin$1.INSTANCE, ShutDownUrl$Companion$ApplicationCallPlugin$2.INSTANCE);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$Companion;", "", "()V", "ApplicationCallPlugin", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/application/Application;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lio/ktor/server/application/PluginInstance;", "getApplicationCallPlugin", "()Lio/ktor/server/application/BaseApplicationPlugin;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final BaseApplicationPlugin<Application, Config, PluginInstance> getApplicationCallPlugin() {
            return ShutDownUrl.ApplicationCallPlugin;
        }

        private Companion() {
        }
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR3\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$Config;", "", "<init>", "()V", "", "shutDownUrl", "Ljava/lang/String;", "getShutDownUrl", "()Ljava/lang/String;", "setShutDownUrl", "(Ljava/lang/String;)V", "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "", "Lx6/n;", "exitCodeSupplier", "Lr7/l;", "getExitCodeSupplier", "()Lr7/l;", "setExitCodeSupplier", "(Lr7/l;)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private String shutDownUrl = "/ktor/application/shutdown";
        private l<? super ApplicationCall, Integer> exitCodeSupplier = ShutDownUrl$Config$exitCodeSupplier$1.INSTANCE;

        public final l<ApplicationCall, Integer> getExitCodeSupplier() {
            return this.exitCodeSupplier;
        }

        public final String getShutDownUrl() {
            return this.shutDownUrl;
        }

        public final void setExitCodeSupplier(l<? super ApplicationCall, Integer> lVar) {
            this.exitCodeSupplier = lVar;
        }

        public final void setShutDownUrl(String str) {
            this.shutDownUrl = str;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J0\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00022\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH\u0016¢\u0006\u0004\b\f\u0010\rR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/engine/ShutDownUrl$EnginePlugin;", "Lio/ktor/server/application/BaseApplicationPlugin;", "Lio/ktor/server/engine/EnginePipeline;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lio/ktor/server/engine/ShutDownUrl;", "<init>", "()V", "pipeline", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "install", "(Lio/ktor/server/engine/EnginePipeline;Lr7/l;)Lio/ktor/server/engine/ShutDownUrl;", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class EnginePlugin implements BaseApplicationPlugin<EnginePipeline, Config, ShutDownUrl> {
        public static final EnginePlugin INSTANCE = new EnginePlugin();
        private static final AttributeKey<ShutDownUrl> key = new AttributeKey<>("shutdown.url");

        private EnginePlugin() {
        }

        @Override // io.ktor.server.application.Plugin
        public AttributeKey<ShutDownUrl> getKey() {
            return key;
        }

        @Override // io.ktor.server.application.Plugin
        public /* bridge */ /* synthetic */ Object install(Pipeline pipeline, l lVar) {
            return install((EnginePipeline) pipeline, (l<? super Config, t0>) lVar);
        }

        public ShutDownUrl install(EnginePipeline pipeline, l<? super Config, t0> configure) {
            Config config = new Config();
            configure.invoke(config);
            ShutDownUrl shutDownUrl = new ShutDownUrl(config.getShutDownUrl(), config.getExitCodeSupplier());
            pipeline.intercept(EnginePipeline.INSTANCE.getBefore(), new ShutDownUrl$EnginePlugin$install$1(shutDownUrl, null));
            return shutDownUrl;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.ShutDownUrl$doShutdown$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.ShutDownUrl", f = "ShutDownUrl.kt", l = {115}, m = "doShutdown")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ShutDownUrl.this.doShutdown(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.ShutDownUrl$doShutdown$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.ShutDownUrl$doShutdown$2", f = "ShutDownUrl.kt", l = {TsExtractor.TS_STREAM_TYPE_H265}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Application $application;
        final /* synthetic */ ApplicationEnvironment $environment;
        final /* synthetic */ int $exitCode;
        final /* synthetic */ r $latch;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(r rVar, ApplicationEnvironment applicationEnvironment, Application application, int i10, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$latch = rVar;
            this.$environment = applicationEnvironment;
            this.$application = application;
            this.$exitCode = i10;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$latch, this.$environment, this.$application, this.$exitCode, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                r rVar = this.$latch;
                this.label = 1;
                Object objJoin = rVar.join(this);
                a aVar = a.f15033i;
                if (objJoin == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            this.$environment.getMonitor().raise(DefaultApplicationEventsKt.getApplicationStopPreparing(), this.$environment);
            ApplicationEnvironment applicationEnvironment = this.$environment;
            if (applicationEnvironment instanceof ApplicationEngineEnvironment) {
                ((ApplicationEngineEnvironment) applicationEnvironment).stop();
            } else {
                this.$application.dispose();
            }
            System.exit(this.$exitCode);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ShutDownUrl(String str, l<? super ApplicationCall, Integer> lVar) {
        this.url = str;
        this.exitCode = lVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object doShutdown(io.ktor.server.application.ApplicationCall r12, d7.d<? super x6.t0> r13) {
        /*
            r11 = this;
            java.lang.Class<io.ktor.http.HttpStatusCode> r0 = io.ktor.http.HttpStatusCode.class
            boolean r1 = r13 instanceof io.ktor.server.engine.ShutDownUrl.AnonymousClass1
            if (r1 == 0) goto L15
            r1 = r13
            io.ktor.server.engine.ShutDownUrl$doShutdown$1 r1 = (io.ktor.server.engine.ShutDownUrl.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            io.ktor.server.engine.ShutDownUrl$doShutdown$1 r1 = new io.ktor.server.engine.ShutDownUrl$doShutdown$1
            r1.<init>(r13)
        L1a:
            java.lang.Object r13 = r1.result
            int r2 = r1.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r12 = r1.L$0
            ka.r r12 = (ka.r) r12
            k2.c.G(r13)     // Catch: java.lang.Throwable -> L2d
            goto Lb2
        L2d:
            r0 = move-exception
            r13 = r0
            goto Lb8
        L31:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L39:
            k2.c.G(r13)
            io.ktor.server.application.Application r13 = r12.getApplication()
            vc.b r13 = io.ktor.server.application.ApplicationKt.getLog(r13)
            java.lang.String r2 = "Shutdown URL was called: server is going down"
            r13.g(r2)
            io.ktor.server.application.Application r8 = r12.getApplication()
            io.ktor.server.application.ApplicationEnvironment r7 = r8.getEnvironment()
            r7.l<io.ktor.server.application.ApplicationCall, java.lang.Integer> r13 = r11.exitCode
            java.lang.Object r13 = r13.invoke(r12)
            java.lang.Number r13 = (java.lang.Number) r13
            int r9 = r13.intValue()
            ka.s r6 = ka.m0.b()
            io.ktor.server.application.Application r13 = r12.getApplication()
            io.ktor.server.engine.ShutDownUrl$doShutdown$2 r5 = new io.ktor.server.engine.ShutDownUrl$doShutdown$2
            r10 = 0
            r5.<init>(r6, r7, r8, r9, r10)
            r2 = 3
            r7 = 0
            ka.m0.p(r13, r3, r7, r5, r2)
            io.ktor.http.HttpStatusCode$Companion r13 = io.ktor.http.HttpStatusCode.INSTANCE     // Catch: java.lang.Throwable -> L94
            io.ktor.http.HttpStatusCode r13 = r13.getGone()     // Catch: java.lang.Throwable -> L94
            boolean r2 = r13 instanceof byte[]     // Catch: java.lang.Throwable -> L94
            if (r2 != 0) goto L9c
            io.ktor.server.response.ApplicationResponse r2 = r12.getResponse()     // Catch: java.lang.Throwable -> L94
            kotlin.reflect.q r5 = kotlin.jvm.internal.l0.a(r0)     // Catch: java.lang.Throwable -> L94
            java.lang.reflect.Type r7 = kotlin.reflect.b0.t(r5)     // Catch: java.lang.Throwable -> L94
            kotlin.jvm.internal.m0 r8 = kotlin.jvm.internal.l0.f19747a     // Catch: java.lang.Throwable -> L98
            kotlin.reflect.d r0 = r8.b(r0)     // Catch: java.lang.Throwable -> L98
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r7, r0, r5)     // Catch: java.lang.Throwable -> L94
            io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r0)     // Catch: java.lang.Throwable -> L94
            goto L9c
        L94:
            r0 = move-exception
            r13 = r0
        L96:
            r12 = r6
            goto Lb8
        L98:
            r0 = move-exception
            r12 = r0
            r13 = r12
            goto L96
        L9c:
            io.ktor.server.response.ApplicationResponse r0 = r12.getResponse()     // Catch: java.lang.Throwable -> L94
            io.ktor.server.response.ApplicationSendPipeline r0 = r0.getPipeline()     // Catch: java.lang.Throwable -> L94
            r1.L$0 = r6     // Catch: java.lang.Throwable -> L94
            r1.label = r4     // Catch: java.lang.Throwable -> L94
            java.lang.Object r12 = r0.execute(r12, r13, r1)     // Catch: java.lang.Throwable -> L94
            e7.a r13 = e7.a.f15033i
            if (r12 != r13) goto Lb1
            return r13
        Lb1:
            r12 = r6
        Lb2:
            r12.cancel(r3)
            x6.t0 r12 = x6.t0.f22605a
            return r12
        Lb8:
            r12.cancel(r3)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ShutDownUrl.doShutdown(io.ktor.server.application.ApplicationCall, d7.d):java.lang.Object");
    }

    public final l<ApplicationCall, Integer> getExitCode() {
        return this.exitCode;
    }

    public final String getUrl() {
        return this.url;
    }
}
