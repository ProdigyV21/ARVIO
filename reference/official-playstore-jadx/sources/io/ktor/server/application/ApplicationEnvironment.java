package io.ktor.server.application;

import d7.j;
import io.ktor.events.Events;
import io.ktor.server.config.ApplicationConfig;
import kotlin.Metadata;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u000e\u001a\u00060\nj\u0002`\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u001b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lio/ktor/server/application/ApplicationEnvironment;", "", "Ljava/lang/ClassLoader;", "getClassLoader", "()Ljava/lang/ClassLoader;", "classLoader", "Ld7/j;", "getParentCoroutineContext", "()Ld7/j;", "parentCoroutineContext", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "getLog", "()Lvc/b;", "log", "Lio/ktor/server/config/ApplicationConfig;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "config", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "monitor", "", "getRootPath", "()Ljava/lang/String;", "rootPath", "", "getDevelopmentMode", "()Z", "developmentMode", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationEnvironment {
    ClassLoader getClassLoader();

    ApplicationConfig getConfig();

    boolean getDevelopmentMode();

    b getLog();

    Events getMonitor();

    j getParentCoroutineContext();

    String getRootPath();
}
