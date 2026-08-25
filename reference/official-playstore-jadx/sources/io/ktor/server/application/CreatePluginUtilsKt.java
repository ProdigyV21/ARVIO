package io.ktor.server.application;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingKt;
import io.ktor.util.AttributeKey;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001ao\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\u0003\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00028\u00000\u00052\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u000f\u0010\u0010\u001aR\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u000f\u0010\u0012\u001aR\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0013\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u0015\u0010\u0016\u001ao\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\u0003\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00028\u00000\u00052\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0013\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u0015\u0010\u0017\u001a:\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u000f\u0010\u0018\u001a:\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0013\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f¢\u0006\u0004\b\u0015\u0010\u0019\u001a\u008f\u0001\u0010\"\u001a\u00020\u001d\"\b\b\u0000\u0010\u001b*\u00020\u001a\"\b\b\u0001\u0010\u0001*\u00020\u0000*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001a2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00112\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0002¢\u0006\u0004\b\"\u0010#\u001a\u008f\u0001\u0010$\u001a\u00020\u001d\"\b\b\u0000\u0010\u001b*\u00020\u001a\"\b\b\u0001\u0010\u0001*\u00020\u0000*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001a2\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0013\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00112\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0002¢\u0006\u0004\b$\u0010#\u001aF\u0010'\u001a\u00020\u000b\"\b\b\u0000\u0010%*\u00020\u0000\"\u000e\b\u0001\u0010&*\b\u0012\u0004\u0012\u00028\u00000\n*\u00028\u00012\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0002¢\u0006\u0004\b'\u0010(\u001a<\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b)\u0010\u0019\u001aT\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00112\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b)\u0010\u0016\u001aq\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\u0003\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00028\u00000\u00052\u001d\u0010\r\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b)\u0010\u0017¨\u0006*"}, d2 = {"", "PluginConfigT", "", ContentDisposition.Parameters.Name, "configurationPath", "Lkotlin/Function1;", "Lio/ktor/server/config/ApplicationConfig;", "Lx6/y;", "config", "createConfiguration", "Lio/ktor/server/application/PluginBuilder;", "Lx6/t0;", "Lx6/n;", TtmlNode.TAG_BODY, "Lio/ktor/server/application/ApplicationPlugin;", "createApplicationPlugin", "(Ljava/lang/String;Ljava/lang/String;Lr7/l;Lr7/l;)Lio/ktor/server/application/ApplicationPlugin;", "Lkotlin/Function0;", "(Ljava/lang/String;Lr7/a;Lr7/l;)Lio/ktor/server/application/ApplicationPlugin;", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "Lio/ktor/server/application/RouteScopedPlugin;", "createRouteScopedPlugin", "(Ljava/lang/String;Lr7/a;Lr7/l;)Lio/ktor/server/application/RouteScopedPlugin;", "(Ljava/lang/String;Ljava/lang/String;Lr7/l;Lr7/l;)Lio/ktor/server/application/RouteScopedPlugin;", "(Ljava/lang/String;Lr7/l;)Lio/ktor/server/application/ApplicationPlugin;", "(Ljava/lang/String;Lr7/l;)Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/application/ApplicationCallPipeline;", "PipelineT", "Lio/ktor/server/application/Plugin;", "Lio/ktor/server/application/PluginInstance;", "Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "pipeline", "configure", "createPluginInstance", "(Lio/ktor/server/application/Plugin;Lio/ktor/server/application/Application;Lio/ktor/server/application/ApplicationCallPipeline;Lr7/l;Lr7/a;Lr7/l;)Lio/ktor/server/application/PluginInstance;", "createRouteScopedPluginInstance", "Configuration", "Builder", "setupPlugin", "(Lio/ktor/server/application/PluginBuilder;Lr7/l;)V", "createRouteScopedPluginOld", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CreatePluginUtilsKt {

    /* JADX INFO: renamed from: io.ktor.server.application.CreatePluginUtilsKt$createApplicationPlugin$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements a<t0> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6706invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6706invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.CreatePluginUtilsKt$createRouteScopedPlugin$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18493 extends r implements a<t0> {
        public static final C18493 INSTANCE = new C18493();

        public C18493() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6707invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6707invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.CreatePluginUtilsKt$createRouteScopedPlugin$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements a<t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6708invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6708invoke();
            return t0.f22605a;
        }
    }

    public static final <PluginConfigT> ApplicationPlugin<PluginConfigT> createApplicationPlugin(String str, String str2, l<? super ApplicationConfig, ? extends PluginConfigT> lVar, l<? super PluginBuilder<PluginConfigT>, t0> lVar2) {
        return new ApplicationPlugin<PluginConfigT>(str, str2, lVar2, lVar) { // from class: io.ktor.server.application.CreatePluginUtilsKt.createApplicationPlugin.1
            final /* synthetic */ l<PluginBuilder<PluginConfigT>, t0> $body;
            final /* synthetic */ String $configurationPath;
            final /* synthetic */ l<ApplicationConfig, PluginConfigT> $createConfiguration;
            private final AttributeKey<PluginInstance> key;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$configurationPath = str2;
                this.$body = lVar2;
                this.$createConfiguration = lVar;
                this.key = new AttributeKey<>(str);
            }

            @Override // io.ktor.server.application.Plugin
            public AttributeKey<PluginInstance> getKey() {
                return this.key;
            }

            @Override // io.ktor.server.application.Plugin
            public PluginInstance install(Application pipeline, l<? super PluginConfigT, t0> configure) {
                ApplicationConfig mapApplicationConfig;
                try {
                    mapApplicationConfig = pipeline.getEnvironment().getConfig().config(this.$configurationPath);
                } catch (Throwable unused) {
                    mapApplicationConfig = new MapApplicationConfig();
                }
                return CreatePluginUtilsKt.createPluginInstance(this, pipeline, pipeline, this.$body, new CreatePluginUtilsKt$createApplicationPlugin$1$install$1(this.$createConfiguration, mapApplicationConfig), configure);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <PipelineT extends ApplicationCallPipeline, PluginConfigT> PluginInstance createPluginInstance(Plugin<? super PipelineT, ? extends PluginConfigT, PluginInstance> plugin, final Application application, final ApplicationCallPipeline applicationCallPipeline, l<? super PluginBuilder<PluginConfigT>, t0> lVar, a<? extends PluginConfigT> aVar, l<? super PluginConfigT, t0> lVar2) {
        final Object objInvoke = aVar.invoke();
        lVar2.invoke(objInvoke);
        final AttributeKey<PluginInstance> key = plugin.getKey();
        PluginBuilder<PluginConfigT> pluginBuilder = new PluginBuilder<PluginConfigT>(application, applicationCallPipeline, objInvoke, key) { // from class: io.ktor.server.application.CreatePluginUtilsKt$createPluginInstance$pluginBuilder$1
            private final Application application;
            private final ApplicationCallPipeline pipeline;
            private final PluginConfigT pluginConfig;

            {
                super(key);
                this.application = application;
                this.pipeline = applicationCallPipeline;
                this.pluginConfig = objInvoke;
            }

            @Override // io.ktor.server.application.PluginBuilder
            public Application getApplication() {
                return this.application;
            }

            @Override // io.ktor.server.application.PluginBuilder
            /* JADX INFO: renamed from: getPipeline$ktor_server_core, reason: from getter */
            public ApplicationCallPipeline getPipeline() {
                return this.pipeline;
            }

            @Override // io.ktor.server.application.PluginBuilder
            public PluginConfigT getPluginConfig() {
                return this.pluginConfig;
            }
        };
        setupPlugin(pluginBuilder, lVar);
        return new PluginInstance(pluginBuilder);
    }

    public static final <PluginConfigT> RouteScopedPlugin<PluginConfigT> createRouteScopedPlugin(String str, a<? extends PluginConfigT> aVar, l<? super RouteScopedPluginBuilder<PluginConfigT>, t0> lVar) {
        return new RouteScopedPlugin<PluginConfigT>(str, lVar, aVar) { // from class: io.ktor.server.application.CreatePluginUtilsKt.createRouteScopedPlugin.1
            final /* synthetic */ l<RouteScopedPluginBuilder<PluginConfigT>, t0> $body;
            final /* synthetic */ a<PluginConfigT> $createConfiguration;
            private final AttributeKey<PluginInstance> key;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$body = lVar;
                this.$createConfiguration = aVar;
                this.key = new AttributeKey<>(str);
            }

            @Override // io.ktor.server.application.Plugin
            public AttributeKey<PluginInstance> getKey() {
                return this.key;
            }

            @Override // io.ktor.server.application.Plugin
            public PluginInstance install(ApplicationCallPipeline pipeline, l<? super PluginConfigT, t0> configure) {
                Application application;
                if (pipeline instanceof Route) {
                    application = RoutingKt.getApplication((Route) pipeline);
                } else {
                    if (!(pipeline instanceof Application)) {
                        throw new IllegalStateException(("Unsupported pipeline type: " + l0.f19747a.b(pipeline.getClass())).toString());
                    }
                    application = (Application) pipeline;
                }
                return CreatePluginUtilsKt.createRouteScopedPluginInstance(this, application, pipeline, this.$body, this.$createConfiguration, configure);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <PipelineT extends ApplicationCallPipeline, PluginConfigT> PluginInstance createRouteScopedPluginInstance(Plugin<? super PipelineT, ? extends PluginConfigT, PluginInstance> plugin, final Application application, final ApplicationCallPipeline applicationCallPipeline, l<? super RouteScopedPluginBuilder<PluginConfigT>, t0> lVar, a<? extends PluginConfigT> aVar, l<? super PluginConfigT, t0> lVar2) {
        final Object objInvoke = aVar.invoke();
        lVar2.invoke(objInvoke);
        final AttributeKey<PluginInstance> key = plugin.getKey();
        RouteScopedPluginBuilder<PluginConfigT> routeScopedPluginBuilder = new RouteScopedPluginBuilder<PluginConfigT>(application, applicationCallPipeline, objInvoke, key) { // from class: io.ktor.server.application.CreatePluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1
            private final Application application;
            private final ApplicationCallPipeline pipeline;
            private final PluginConfigT pluginConfig;
            private final Route route;

            {
                super(key);
                this.application = application;
                this.pipeline = applicationCallPipeline;
                this.pluginConfig = objInvoke;
                this.route = applicationCallPipeline instanceof Route ? (Route) applicationCallPipeline : null;
            }

            @Override // io.ktor.server.application.PluginBuilder
            public Application getApplication() {
                return this.application;
            }

            @Override // io.ktor.server.application.PluginBuilder
            /* JADX INFO: renamed from: getPipeline$ktor_server_core, reason: from getter */
            public ApplicationCallPipeline getPipeline() {
                return this.pipeline;
            }

            @Override // io.ktor.server.application.PluginBuilder
            public PluginConfigT getPluginConfig() {
                return this.pluginConfig;
            }

            @Override // io.ktor.server.application.RouteScopedPluginBuilder
            public Route getRoute() {
                return this.route;
            }
        };
        setupPlugin(routeScopedPluginBuilder, lVar);
        return new PluginInstance(routeScopedPluginBuilder);
    }

    private static final <Configuration, Builder extends PluginBuilder<Configuration>> void setupPlugin(Builder builder, l<? super Builder, t0> lVar) {
        lVar.invoke(builder);
        Iterator<T> it = builder.getCallInterceptions$ktor_server_core().iterator();
        while (it.hasNext()) {
            ((Interception) it.next()).getAction().invoke(builder.getPipeline());
        }
        Iterator<T> it2 = builder.getOnReceiveInterceptions$ktor_server_core().iterator();
        while (it2.hasNext()) {
            ((Interception) it2.next()).getAction().invoke(builder.getPipeline().getReceivePipeline());
        }
        Iterator<T> it3 = builder.getOnResponseInterceptions$ktor_server_core().iterator();
        while (it3.hasNext()) {
            ((Interception) it3.next()).getAction().invoke(builder.getPipeline().getSendPipeline());
        }
        Iterator<T> it4 = builder.getAfterResponseInterceptions$ktor_server_core().iterator();
        while (it4.hasNext()) {
            ((Interception) it4.next()).getAction().invoke(builder.getPipeline().getSendPipeline());
        }
        Iterator<T> it5 = builder.getHooks$ktor_server_core().iterator();
        while (it5.hasNext()) {
            ((HookHandler) it5.next()).install(builder.getPipeline());
        }
    }

    public static final <PluginConfigT> ApplicationPlugin<PluginConfigT> createApplicationPlugin(String str, a<? extends PluginConfigT> aVar, l<? super PluginBuilder<PluginConfigT>, t0> lVar) {
        return new ApplicationPlugin<PluginConfigT>(str, lVar, aVar) { // from class: io.ktor.server.application.CreatePluginUtilsKt.createApplicationPlugin.2
            final /* synthetic */ l<PluginBuilder<PluginConfigT>, t0> $body;
            final /* synthetic */ a<PluginConfigT> $createConfiguration;
            private final AttributeKey<PluginInstance> key;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$body = lVar;
                this.$createConfiguration = aVar;
                this.key = new AttributeKey<>(str);
            }

            @Override // io.ktor.server.application.Plugin
            public AttributeKey<PluginInstance> getKey() {
                return this.key;
            }

            @Override // io.ktor.server.application.Plugin
            public PluginInstance install(Application pipeline, l<? super PluginConfigT, t0> configure) {
                return CreatePluginUtilsKt.createPluginInstance(this, pipeline, pipeline, this.$body, this.$createConfiguration, configure);
            }
        };
    }

    public static final <PluginConfigT> RouteScopedPlugin<PluginConfigT> createRouteScopedPlugin(String str, String str2, l<? super ApplicationConfig, ? extends PluginConfigT> lVar, l<? super RouteScopedPluginBuilder<PluginConfigT>, t0> lVar2) {
        return new RouteScopedPlugin<PluginConfigT>(str, str2, lVar2, lVar) { // from class: io.ktor.server.application.CreatePluginUtilsKt.createRouteScopedPlugin.2
            final /* synthetic */ l<RouteScopedPluginBuilder<PluginConfigT>, t0> $body;
            final /* synthetic */ String $configurationPath;
            final /* synthetic */ l<ApplicationConfig, PluginConfigT> $createConfiguration;
            private final AttributeKey<PluginInstance> key;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$configurationPath = str2;
                this.$body = lVar2;
                this.$createConfiguration = lVar;
                this.key = new AttributeKey<>(str);
            }

            @Override // io.ktor.server.application.Plugin
            public AttributeKey<PluginInstance> getKey() {
                return this.key;
            }

            @Override // io.ktor.server.application.Plugin
            public PluginInstance install(ApplicationCallPipeline pipeline, l<? super PluginConfigT, t0> configure) {
                ApplicationConfig mapApplicationConfig;
                Application application;
                ApplicationEnvironment environment = pipeline.getEnvironment();
                if (environment == null) {
                    throw new IllegalStateException("Can't install plugin with config: environment is not initialized.");
                }
                try {
                    mapApplicationConfig = environment.getConfig().config(this.$configurationPath);
                } catch (Throwable unused) {
                    mapApplicationConfig = new MapApplicationConfig();
                }
                if (pipeline instanceof Route) {
                    application = RoutingKt.getApplication((Route) pipeline);
                } else {
                    if (!(pipeline instanceof Application)) {
                        throw new IllegalStateException(("Unsupported pipeline type: " + l0.f19747a.b(pipeline.getClass())).toString());
                    }
                    application = (Application) pipeline;
                }
                return CreatePluginUtilsKt.createRouteScopedPluginInstance(this, application, pipeline, this.$body, new CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1(this.$createConfiguration, mapApplicationConfig), configure);
            }
        };
    }

    public static final ApplicationPlugin<t0> createApplicationPlugin(String str, l<? super PluginBuilder<t0>, t0> lVar) {
        return createApplicationPlugin(str, AnonymousClass3.INSTANCE, lVar);
    }

    public static final RouteScopedPlugin<t0> createRouteScopedPlugin(String str, l<? super RouteScopedPluginBuilder<t0>, t0> lVar) {
        return createRouteScopedPlugin(str, C18493.INSTANCE, lVar);
    }
}
