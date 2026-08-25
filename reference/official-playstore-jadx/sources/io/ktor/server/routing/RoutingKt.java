package io.ktor.server.routing;

import androidx.media3.common.MimeTypes;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.routing.Routing;
import io.ktor.util.AttributeKey;
import io.ktor.util.InternalAPI;
import io.ktor.util.KtorDsl;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0006\u001a\u00020\u0002*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\"&\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\"\u001e\u0010\u0012\u001a\u00060\u0010j\u0002`\u00118\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0015\u0010\u0019\u001a\u00020\u0000*\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lio/ktor/server/application/Application;", "Lkotlin/Function1;", "Lio/ktor/server/routing/Routing;", "Lx6/t0;", "Lx6/n;", "configuration", "routing", "(Lio/ktor/server/application/Application;Lr7/l;)Lio/ktor/server/routing/Routing;", "Lio/ktor/util/AttributeKey;", "Lio/ktor/http/HttpStatusCode;", "RoutingFailureStatusCode", "Lio/ktor/util/AttributeKey;", "getRoutingFailureStatusCode", "()Lio/ktor/util/AttributeKey;", "getRoutingFailureStatusCode$annotations", "()V", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "getLOGGER", "()Lvc/b;", "Lio/ktor/server/routing/Route;", "getApplication", "(Lio/ktor/server/routing/Route;)Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RoutingKt {
    private static final AttributeKey<HttpStatusCode> RoutingFailureStatusCode = new AttributeKey<>("RoutingFailureStatusCode");
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.routing.Routing");

    public static final Application getApplication(Route route) {
        Application application;
        if (route instanceof Routing) {
            return ((Routing) route).getApplication();
        }
        Route parent = route.getParent();
        if (parent == null || (application = getApplication(parent)) == null) {
            throw new UnsupportedOperationException("Cannot retrieve application from unattached routing entry");
        }
        return application;
    }

    public static final b getLOGGER() {
        return LOGGER;
    }

    public static final AttributeKey<HttpStatusCode> getRoutingFailureStatusCode() {
        return RoutingFailureStatusCode;
    }

    @InternalAPI
    public static /* synthetic */ void getRoutingFailureStatusCode$annotations() {
    }

    @KtorDsl
    public static final Routing routing(Application application, l<? super Routing, t0> lVar) {
        Routing.Companion plugin = Routing.INSTANCE;
        Routing routing = (Routing) ApplicationPluginKt.pluginOrNull(application, plugin);
        if (routing == null) {
            return (Routing) ApplicationPluginKt.install(application, plugin, lVar);
        }
        lVar.invoke(routing);
        return routing;
    }
}
