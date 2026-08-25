package io.ktor.server.application;

import io.ktor.server.routing.Route;
import io.ktor.server.routing.Routing;
import io.ktor.server.routing.RoutingKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001aE\u0010\u0006\u001a\u00028\u0001\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00028\u00002\u0014\u0010\u0006\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007\u001aG\u0010\b\u001a\u0004\u0018\u00018\u0001\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00028\u00002\u0014\u0010\u0006\u001a\u0010\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\b\u0010\u0007\u001an\u0010\u000f\u001a\u00028\u0002\"\u0012\b\u0000\u0010\t*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\n*\u00020\u0003\"\b\b\u0002\u0010\u0004*\u00020\u0003*\u00028\u00002\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\r¢\u0006\u0004\b\u000f\u0010\u0010\u001aV\u0010\u0013\u001a\u00028\u0001\"\b\b\u0000\u0010\n*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u00112\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00122\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001am\u0010\u0019\u001a\u00020\f\"\b\b\u0000\u0010\n*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\u0004\b\u0002\u0010\u0015\"\u0004\b\u0003\u0010\u0016\"\u0014\b\u0004\u0010\t*\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0000*\u00028\u00042\u0006\u0010\u0017\u001a\u00028\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00122\u0006\u0010\u0018\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u001af\u0010\u000f\u001a\u00028\u0002\"\b\b\u0000\u0010\t*\u00020\u0011\"\b\b\u0001\u0010\n*\u00020\u0003\"\b\b\u0002\u0010\u0004*\u00020\u0003*\u00028\u00002\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u001b2\u0019\b\u0002\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u000f\u0010\u001c\u001a'\u0010\u001d\u001a\u00020\f\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000*\u00028\u0000H\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001aU\u0010\u001f\u001a\u00020\f\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\n*\u00020\u0003\"\b\b\u0002\u0010\u0004*\u00020\u0003*\u00028\u00002\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005H\u0007¢\u0006\u0004\b\u001f\u0010 \u001a?\u0010#\u001a\u00020\f\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00028\u00002\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00010!H\u0007¢\u0006\u0004\b#\u0010$\" \u0010&\u001a\b\u0012\u0004\u0012\u00020%0!8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\")\u0010,\u001a\u00020%\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000*\u00028\u00008F¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "A", "", "F", "Lio/ktor/server/application/Plugin;", "plugin", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;)Ljava/lang/Object;", "pluginOrNull", "P", "B", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "install", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;Lr7/l;)Ljava/lang/Object;", "Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/BaseRouteScopedPlugin;", "installIntoRoute", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/BaseRouteScopedPlugin;Lr7/l;)Ljava/lang/Object;", "TSubject", "TContext", "fakePipeline", "pluginInstance", "addAllInterceptors", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/BaseRouteScopedPlugin;Ljava/lang/Object;)V", "Lio/ktor/server/application/BaseApplicationPlugin;", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/BaseApplicationPlugin;Lr7/l;)Ljava/lang/Object;", "uninstallAllPlugins", "(Lio/ktor/util/pipeline/Pipeline;)V", "uninstall", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/server/application/Plugin;)V", "Lio/ktor/util/AttributeKey;", "key", "uninstallPlugin", "(Lio/ktor/util/pipeline/Pipeline;Lio/ktor/util/AttributeKey;)V", "Lio/ktor/util/Attributes;", "pluginRegistryKey", "Lio/ktor/util/AttributeKey;", "getPluginRegistryKey", "()Lio/ktor/util/AttributeKey;", "getPluginRegistry", "(Lio/ktor/util/pipeline/Pipeline;)Lio/ktor/util/Attributes;", "pluginRegistry", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationPluginKt {
    private static final AttributeKey<Attributes> pluginRegistryKey = new AttributeKey<>("ApplicationPluginRegistry");

    /* JADX INFO: renamed from: io.ktor.server.application.ApplicationPluginKt$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\t\u001a\u00020\u0006\"\u0012\b\u0000\u0010\u0002*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00010\u0000\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0005*\u00020\u0003*\u00028\u0001H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "P", "", "B", "F", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6703invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6703invoke(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.ApplicationPluginKt$install$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002\"\b\b\u0002\u0010\u0004*\u00020\u0002*\u00028\u0001H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lio/ktor/server/routing/Route;", "P", "", "B", "F", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6704invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6704invoke(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.ApplicationPluginKt$installIntoRoute$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00028\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "B", "F", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18461 extends r implements l {
        public static final C18461 INSTANCE = new C18461();

        public C18461() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6705invoke(Object obj) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6705invoke(obj);
            return t0.f22605a;
        }
    }

    private static final <B, F, TSubject, TContext, P extends Pipeline<TSubject, TContext>> void addAllInterceptors(P p10, P p11, BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, F f10) {
        for (PipelinePhase pipelinePhase : p10.getItems()) {
            Iterator<T> it = p11.interceptorsForPhase(pipelinePhase).iterator();
            while (it.hasNext()) {
                p10.intercept(pipelinePhase, new ApplicationPluginKt$addAllInterceptors$1$1$1(baseRouteScopedPlugin, f10, (q) it.next(), null));
            }
        }
    }

    public static final <A extends Pipeline<?, ApplicationCall>> Attributes getPluginRegistry(A a10) {
        return (Attributes) a10.getAttributes().computeIfAbsent(pluginRegistryKey, ApplicationPluginKt$pluginRegistry$1.INSTANCE);
    }

    public static final AttributeKey<Attributes> getPluginRegistryKey() {
        return pluginRegistryKey;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <P extends Pipeline<?, ApplicationCall>, B, F> F install(P p10, Plugin<? super P, ? extends B, F> plugin, l<? super B, t0> lVar) {
        if ((p10 instanceof Route) && (plugin instanceof BaseRouteScopedPlugin)) {
            return (F) installIntoRoute((Route) p10, (BaseRouteScopedPlugin) plugin, lVar);
        }
        Attributes pluginRegistry = getPluginRegistry(p10);
        F f10 = (F) pluginRegistry.getOrNull(plugin.getKey());
        if (f10 == null) {
            F fInstall = plugin.install(p10, lVar);
            pluginRegistry.put(plugin.getKey(), fInstall);
            return fInstall;
        }
        if (f10.equals(plugin)) {
            return f10;
        }
        throw new DuplicatePluginException("Please make sure that you use unique name for the plugin and don't install it twice. Conflicting application plugin is already installed with the same key as `" + plugin.getKey().getName() + '`');
    }

    public static /* synthetic */ Object install$default(Pipeline pipeline, Plugin plugin, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return install(pipeline, (Plugin<? super Pipeline, ? extends B, F>) plugin, lVar);
    }

    private static final <B, F> F installIntoRoute(Route route, BaseRouteScopedPlugin<B, F> baseRouteScopedPlugin, l<? super B, t0> lVar) throws DuplicatePluginException {
        if (getPluginRegistry(route).getOrNull(baseRouteScopedPlugin.getKey()) != null) {
            throw new DuplicatePluginException("Please make sure that you use unique name for the plugin and don't install it twice. Plugin `" + baseRouteScopedPlugin.getKey().getName() + "` is already installed to the pipeline " + route);
        }
        if (getPluginRegistry(RoutingKt.getApplication(route)).getOrNull(baseRouteScopedPlugin.getKey()) != null) {
            throw new DuplicatePluginException("Installing RouteScopedPlugin to application and route is not supported. Consider moving application level install to routing root.");
        }
        ApplicationCallPipeline routing = route instanceof Routing ? new Routing(((Routing) route).getApplication()) : new Route(route.getParent(), route.getSelector(), route.getDevelopmentMode(), route.getEnvironment());
        F fInstall = baseRouteScopedPlugin.install(routing, lVar);
        getPluginRegistry(route).put(baseRouteScopedPlugin.getKey(), fInstall);
        route.mergePhases(routing);
        route.getReceivePipeline().mergePhases(routing.getReceivePipeline());
        route.getSendPipeline().mergePhases(routing.getSendPipeline());
        addAllInterceptors(route, routing, baseRouteScopedPlugin, fInstall);
        addAllInterceptors(route.getReceivePipeline(), routing.getReceivePipeline(), baseRouteScopedPlugin, fInstall);
        addAllInterceptors(route.getSendPipeline(), routing.getSendPipeline(), baseRouteScopedPlugin, fInstall);
        return fInstall;
    }

    public static /* synthetic */ Object installIntoRoute$default(Route route, BaseRouteScopedPlugin baseRouteScopedPlugin, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = C18461.INSTANCE;
        }
        return installIntoRoute(route, baseRouteScopedPlugin, lVar);
    }

    public static final <A extends Pipeline<?, ApplicationCall>, F> F plugin(A a10, Plugin<?, ?, F> plugin) {
        F f10 = a10 instanceof Route ? (F) RouteScopedPluginKt.findPluginInRoute((Route) a10, plugin) : (F) pluginOrNull(a10, plugin);
        if (f10 != null) {
            return f10;
        }
        throw new MissingApplicationPluginException(plugin.getKey());
    }

    public static final <A extends Pipeline<?, ApplicationCall>, F> F pluginOrNull(A a10, Plugin<?, ?, F> plugin) {
        return (F) getPluginRegistry(a10).getOrNull(plugin.getKey());
    }

    @e
    public static final <A extends Pipeline<?, ApplicationCall>, B, F> void uninstall(A a10, Plugin<? super A, ? extends B, F> plugin) throws IOException {
        uninstallPlugin(a10, plugin.getKey());
    }

    @e
    public static final <A extends Pipeline<?, ApplicationCall>> void uninstallAllPlugins(A a10) {
        Iterator<T> it = getPluginRegistry(a10).getAllKeys().iterator();
        while (it.hasNext()) {
            uninstallPlugin(a10, (AttributeKey) it.next());
        }
    }

    @e
    public static final <A extends Pipeline<?, ApplicationCall>, F> void uninstallPlugin(A a10, AttributeKey<F> attributeKey) throws IOException {
        Object orNull;
        Attributes attributes = (Attributes) a10.getAttributes().getOrNull(pluginRegistryKey);
        if (attributes == null || (orNull = attributes.getOrNull(attributeKey)) == null) {
            return;
        }
        if (orNull instanceof Closeable) {
            ((Closeable) orNull).close();
        }
        attributes.remove(attributeKey);
    }

    public static /* synthetic */ Object install$default(Route route, BaseApplicationPlugin baseApplicationPlugin, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return install(route, (BaseApplicationPlugin<? super Route, ? extends B, F>) baseApplicationPlugin, lVar);
    }

    @e
    public static final <P extends Route, B, F> F install(P p10, BaseApplicationPlugin<? super P, ? extends B, F> baseApplicationPlugin, l<? super B, t0> lVar) {
        return (F) install(p10, (Plugin<? super P, ? extends B, F>) baseApplicationPlugin, (l) lVar);
    }
}
