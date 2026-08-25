package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00000\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a!\u0010\u0002\u001a\u00020\u0006*\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004H\u0002¢\u0006\u0004\b\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/routing/Route;", "", "getAllRoutes", "(Lio/ktor/server/routing/Route;)Ljava/util/List;", "", "endpoints", "Lx6/t0;", "(Lio/ktor/server/routing/Route;Ljava/util/List;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RouteKt {
    public static final List<Route> getAllRoutes(Route route) {
        ArrayList arrayList = new ArrayList();
        getAllRoutes(route, arrayList);
        return arrayList;
    }

    private static final void getAllRoutes(Route route, List<Route> list) {
        if (!route.getHandlers$ktor_server_core().isEmpty()) {
            list.add(route);
        }
        Iterator<T> it = route.getChildren().iterator();
        while (it.hasNext()) {
            getAllRoutes((Route) it.next(), list);
        }
    }
}
