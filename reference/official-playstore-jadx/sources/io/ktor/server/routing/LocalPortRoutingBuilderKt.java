package io.ktor.server.routing;

import androidx.compose.foundation.c;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a2\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/routing/Route;", "", "port", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "build", "localPort", "(Lio/ktor/server/routing/Route;ILr7/l;)Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPortRoutingBuilderKt {
    public static final Route localPort(Route route, int i10, l<? super Route, t0> lVar) {
        if (1 > i10 || i10 >= 65536) {
            throw new IllegalArgumentException(c.o(i10, "Port ", " must be a positive number between 1 and 65,535").toString());
        }
        Route routeCreateChild = route.createChild(new LocalPortRouteSelector(i10));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }
}
