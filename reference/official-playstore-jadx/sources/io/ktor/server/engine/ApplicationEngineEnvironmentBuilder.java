package io.ktor.server.engine;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.j;
import d7.k;
import io.ktor.server.application.Application;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.util.PlatformUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import r7.l;
import vc.b;
import vc.c;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\t\u001a\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\t\u0010\nJ&\u0010\r\u001a\u00020\f2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010&\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010-\u001a\u00020,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001d\u00105\u001a\b\u0012\u0004\u0012\u000204038\u0006¢\u0006\f\n\u0004\b5\u0010 \u001a\u0004\b6\u0010\"R.\u00107\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007038\u0006¢\u0006\f\n\u0004\b7\u0010 \u001a\u0004\b8\u0010\"R\"\u00109\u001a\u00020\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\"\u0010@\u001a\u00020?8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b@\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E¨\u0006F"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "", "<init>", "()V", "Lkotlin/Function1;", "Lio/ktor/server/application/Application;", "Lx6/t0;", "Lx6/n;", TtmlNode.TAG_BODY, "module", "(Lr7/l;)V", "builder", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "build", "(Lr7/l;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Ljava/lang/ClassLoader;", "classLoader", "Ljava/lang/ClassLoader;", "getClassLoader", "()Ljava/lang/ClassLoader;", "setClassLoader", "(Ljava/lang/ClassLoader;)V", "Ld7/j;", "parentCoroutineContext", "Ld7/j;", "getParentCoroutineContext", "()Ld7/j;", "setParentCoroutineContext", "(Ld7/j;)V", "", "", "watchPaths", "Ljava/util/List;", "getWatchPaths", "()Ljava/util/List;", "setWatchPaths", "(Ljava/util/List;)V", "Lvc/b;", "log", "Lvc/b;", "getLog", "()Lvc/b;", "setLog", "(Lvc/b;)V", "Lio/ktor/server/config/ApplicationConfig;", "config", "Lio/ktor/server/config/ApplicationConfig;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "setConfig", "(Lio/ktor/server/config/ApplicationConfig;)V", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "connectors", "getConnectors", "modules", "getModules", "rootPath", "Ljava/lang/String;", "getRootPath", "()Ljava/lang/String;", "setRootPath", "(Ljava/lang/String;)V", "", "developmentMode", "Z", "getDevelopmentMode", "()Z", "setDevelopmentMode", "(Z)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ApplicationEngineEnvironmentBuilder {
    private ClassLoader classLoader = ApplicationEngineEnvironment.class.getClassLoader();
    private j parentCoroutineContext = k.f14688i;
    private List<String> watchPaths = Collections.singletonList(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
    private b log = c.d("Application");
    private ApplicationConfig config = new MapApplicationConfig();
    private final List<EngineConnectorConfig> connectors = new ArrayList();
    private final List<l<Application, t0>> modules = new ArrayList();
    private String rootPath = "";
    private boolean developmentMode = PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE();

    public final ApplicationEngineEnvironment build(l<? super ApplicationEngineEnvironmentBuilder, t0> builder) {
        builder.invoke(this);
        return new ApplicationEngineEnvironmentReloading(this.classLoader, this.log, this.config, this.connectors, this.modules, this.watchPaths, this.parentCoroutineContext, this.rootPath, this.developmentMode);
    }

    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public final ApplicationConfig getConfig() {
        return this.config;
    }

    public final List<EngineConnectorConfig> getConnectors() {
        return this.connectors;
    }

    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final b getLog() {
        return this.log;
    }

    public final List<l<Application, t0>> getModules() {
        return this.modules;
    }

    public final j getParentCoroutineContext() {
        return this.parentCoroutineContext;
    }

    public final String getRootPath() {
        return this.rootPath;
    }

    public final List<String> getWatchPaths() {
        return this.watchPaths;
    }

    public final void module(l<? super Application, t0> body) {
        this.modules.add(body);
    }

    public final void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public final void setConfig(ApplicationConfig applicationConfig) {
        this.config = applicationConfig;
    }

    public final void setDevelopmentMode(boolean z) {
        this.developmentMode = z;
    }

    public final void setLog(b bVar) {
        this.log = bVar;
    }

    public final void setParentCoroutineContext(j jVar) {
        this.parentCoroutineContext = jVar;
    }

    public final void setRootPath(String str) {
        this.rootPath = str;
    }

    public final void setWatchPaths(List<String> list) {
        this.watchPaths = list;
    }
}
