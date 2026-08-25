package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.text.m;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0002\u001a<\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0002\u0010\t\u001a<\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0002\u0010\f\u001aH\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0002\u0010\u0010\u001aV\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0002\u0010\u0012\u001a6\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\n\u0010\u000f\u001a\u00020\u0013\"\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\u0004\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/server/routing/Route;", "", "host", "", "port", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "build", "(Lio/ktor/server/routing/Route;Ljava/lang/String;ILr7/l;)Lio/ktor/server/routing/Route;", "Lkotlin/text/m;", "hostPattern", "(Lio/ktor/server/routing/Route;Lkotlin/text/m;ILr7/l;)Lio/ktor/server/routing/Route;", "", "hosts", "ports", "(Lio/ktor/server/routing/Route;Ljava/util/List;Ljava/util/List;Lr7/l;)Lio/ktor/server/routing/Route;", "hostPatterns", "(Lio/ktor/server/routing/Route;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lr7/l;)Lio/ktor/server/routing/Route;", "", "(Lio/ktor/server/routing/Route;[ILr7/l;)Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HostsRoutingBuilderKt {
    public static final Route host(Route route, String str, int i10, l<? super Route, t0> lVar) {
        List listSingletonList = Collections.singletonList(str);
        z zVar = z.f19728i;
        return host(route, listSingletonList, zVar, i10 > 0 ? Collections.singletonList(Integer.valueOf(i10)) : zVar, lVar);
    }

    public static /* synthetic */ Route host$default(Route route, String str, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return host(route, str, i10, (l<? super Route, t0>) lVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList] */
    public static final Route port(Route route, int[] iArr, l<? super Route, t0> lVar) {
        ?? SingletonList;
        if (iArr.length == 0) {
            throw new IllegalArgumentException("At least one port need to be specified");
        }
        int length = iArr.length;
        z zVar = z.f19728i;
        if (length != 0) {
            if (length != 1) {
                SingletonList = new ArrayList(iArr.length);
                for (int i10 : iArr) {
                    SingletonList.add(Integer.valueOf(i10));
                }
            } else {
                SingletonList = Collections.singletonList(Integer.valueOf(iArr[0]));
            }
        } else {
            SingletonList = zVar;
        }
        Route routeCreateChild = route.createChild(new HostRouteSelector(zVar, zVar, SingletonList));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }

    public static /* synthetic */ Route host$default(Route route, m mVar, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        return host(route, mVar, i10, (l<? super Route, t0>) lVar);
    }

    public static /* synthetic */ Route host$default(Route route, List list, List list2, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            list2 = z.f19728i;
        }
        return host(route, (List<String>) list, (List<Integer>) list2, (l<? super Route, t0>) lVar);
    }

    public static /* synthetic */ Route host$default(Route route, List list, List list2, List list3, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            list3 = z.f19728i;
        }
        return host(route, list, list2, list3, lVar);
    }

    public static final Route host(Route route, m mVar, int i10, l<? super Route, t0> lVar) {
        List listSingletonList = Collections.singletonList(mVar);
        z zVar = z.f19728i;
        return host(route, zVar, listSingletonList, i10 > 0 ? Collections.singletonList(Integer.valueOf(i10)) : zVar, lVar);
    }

    public static final Route host(Route route, List<String> list, List<Integer> list2, l<? super Route, t0> lVar) {
        return host(route, list, z.f19728i, list2, lVar);
    }

    public static final Route host(Route route, List<String> list, List<m> list2, List<Integer> list3, l<? super Route, t0> lVar) {
        Route routeCreateChild = route.createChild(new HostRouteSelector(list, list2, list3));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }
}
