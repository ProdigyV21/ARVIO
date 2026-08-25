package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import r7.l;
import r7.q;
import t7.a;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nB'\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J)\u0010\u0014\u001a\u00020\f2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\f0\u0011¢\u0006\u0002\b\u0012H\u0086\u0002¢\u0006\u0004\b\u0014\u0010\u0015JK\u0010\u001c\u001a\u00020\f29\u0010\u001b\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016¢\u0006\u0002\b\u0012ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001e\u0010\u000eJ\u000f\u0010!\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$R\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u00008\u0006¢\u0006\f\n\u0004\b\u0002\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010(\u001a\u0004\b)\u0010*R \u0010,\u001a\b\u0012\u0004\u0012\u00020\u00000+8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b,\u0010-\u0012\u0004\b.\u0010\u000eR\u0018\u0010/\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\\\u00101\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016¢\u0006\u0002\b\u00120+8\u0000X\u0080\u0004ø\u0001\u0000¢\u0006\u0012\n\u0004\b1\u0010-\u0012\u0004\b4\u0010\u000e\u001a\u0004\b2\u00103R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u0000058F¢\u0006\u0006\u001a\u0004\b6\u00103\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/ApplicationCallPipeline;", "parent", "Lio/ktor/server/routing/RouteSelector;", "selector", "", "developmentMode", "Lio/ktor/server/application/ApplicationEnvironment;", "environment", "<init>", "(Lio/ktor/server/routing/Route;Lio/ktor/server/routing/RouteSelector;ZLio/ktor/server/application/ApplicationEnvironment;)V", "(Lio/ktor/server/routing/Route;Lio/ktor/server/routing/RouteSelector;Lio/ktor/server/application/ApplicationEnvironment;)V", "Lx6/t0;", "invalidateCachesRecursively", "()V", "createChild", "(Lio/ktor/server/routing/RouteSelector;)Lio/ktor/server/routing/Route;", "Lkotlin/Function1;", "Lx6/n;", TtmlNode.TAG_BODY, "invoke", "(Lr7/l;)V", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "Ld7/d;", "", "handler", "handle", "(Lr7/q;)V", "afterIntercepted", "buildPipeline$ktor_server_core", "()Lio/ktor/server/application/ApplicationCallPipeline;", "buildPipeline", "", "toString", "()Ljava/lang/String;", "Lio/ktor/server/routing/Route;", "getParent", "()Lio/ktor/server/routing/Route;", "Lio/ktor/server/routing/RouteSelector;", "getSelector", "()Lio/ktor/server/routing/RouteSelector;", "", "childList", "Ljava/util/List;", "getChildList$annotations", "cachedPipeline", "Lio/ktor/server/application/ApplicationCallPipeline;", "handlers", "getHandlers$ktor_server_core", "()Ljava/util/List;", "getHandlers$ktor_server_core$annotations", "", "getChildren", "children", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class Route extends ApplicationCallPipeline {
    private ApplicationCallPipeline cachedPipeline;
    private final List<Route> childList;
    private final List<q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object>> handlers;
    private final Route parent;
    private final RouteSelector selector;

    public /* synthetic */ Route(Route route, RouteSelector routeSelector, boolean z, ApplicationEnvironment applicationEnvironment, int i10, h hVar) {
        this(route, routeSelector, (i10 & 4) != 0 ? false : z, (i10 & 8) != 0 ? null : applicationEnvironment);
    }

    private static /* synthetic */ void getChildList$annotations() {
    }

    public static /* synthetic */ void getHandlers$ktor_server_core$annotations() {
    }

    private final void invalidateCachesRecursively() {
        this.cachedPipeline = null;
        Iterator<T> it = this.childList.iterator();
        while (it.hasNext()) {
            ((Route) it.next()).invalidateCachesRecursively();
        }
    }

    @Override // io.ktor.util.pipeline.Pipeline
    public void afterIntercepted() {
        invalidateCachesRecursively();
    }

    public final ApplicationCallPipeline buildPipeline$ktor_server_core() {
        ApplicationCallPipeline applicationCallPipeline = this.cachedPipeline;
        if (applicationCallPipeline == null) {
            applicationCallPipeline = new ApplicationCallPipeline(getDevelopmentMode(), RoutingKt.getApplication(this).getEnvironment());
            ArrayList arrayList = new ArrayList();
            for (Route route = this; route != null; route = route.parent) {
                arrayList.add(route);
            }
            for (int iW = a.w(arrayList); -1 < iW; iW--) {
                ApplicationCallPipeline applicationCallPipeline2 = (ApplicationCallPipeline) arrayList.get(iW);
                applicationCallPipeline.merge(applicationCallPipeline2);
                applicationCallPipeline.getReceivePipeline().merge(applicationCallPipeline2.getReceivePipeline());
                applicationCallPipeline.getSendPipeline().merge(applicationCallPipeline2.getSendPipeline());
            }
            List<q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object>> list = this.handlers;
            int iW2 = a.w(list);
            if (iW2 >= 0) {
                int i10 = 0;
                while (true) {
                    applicationCallPipeline.intercept(ApplicationCallPipeline.INSTANCE.getCall(), new Route$buildPipeline$1$1(list, i10, null));
                    if (i10 == iW2) {
                        break;
                    }
                    i10++;
                }
            }
            this.cachedPipeline = applicationCallPipeline;
        }
        return applicationCallPipeline;
    }

    public final Route createChild(RouteSelector selector) {
        Object next;
        Iterator<T> it = this.childList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((Route) next).selector, selector)) {
                break;
            }
        }
        Route route = (Route) next;
        if (route != null) {
            return route;
        }
        Route route2 = new Route(this, selector, getDevelopmentMode(), getEnvironment());
        this.childList.add(route2);
        return route2;
    }

    public final List<Route> getChildren() {
        return this.childList;
    }

    public final List<q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object>> getHandlers$ktor_server_core() {
        return this.handlers;
    }

    public final Route getParent() {
        return this.parent;
    }

    public final RouteSelector getSelector() {
        return this.selector;
    }

    public final void handle(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> handler) {
        this.handlers.add(handler);
        this.cachedPipeline = null;
    }

    public final void invoke(l<? super Route, t0> body) {
        body.invoke(this);
    }

    public String toString() {
        Route route = this.parent;
        String string = route != null ? route.toString() : null;
        if (string == null) {
            if (this.selector instanceof TrailingSlashRouteSelector) {
                return DomExceptionUtils.SEPARATOR;
            }
            return DomExceptionUtils.SEPARATOR + this.selector;
        }
        if (this.selector instanceof TrailingSlashRouteSelector) {
            return o.X(string, '/') ? string : string.concat(DomExceptionUtils.SEPARATOR);
        }
        if (o.X(string, '/')) {
            StringBuilder sbS = androidx.compose.material3.d.s(string);
            sbS.append(this.selector);
            return sbS.toString();
        }
        return string + '/' + this.selector;
    }

    public Route(Route route, RouteSelector routeSelector, boolean z, ApplicationEnvironment applicationEnvironment) {
        super(z, applicationEnvironment);
        this.parent = route;
        this.selector = routeSelector;
        this.childList = new ArrayList();
        this.handlers = new ArrayList();
    }

    public /* synthetic */ Route(Route route, RouteSelector routeSelector, ApplicationEnvironment applicationEnvironment, int i10, h hVar) {
        this(route, routeSelector, (i10 & 4) != 0 ? null : applicationEnvironment);
    }

    @e
    public /* synthetic */ Route(Route route, RouteSelector routeSelector, ApplicationEnvironment applicationEnvironment) {
        this(route, routeSelector, false, applicationEnvironment);
    }
}
