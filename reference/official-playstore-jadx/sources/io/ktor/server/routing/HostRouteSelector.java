package io.ktor.server.routing;

import androidx.compose.material3.d;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B1\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J@\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00022\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0002HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010 \u001a\u0004\b!\u0010\u0014R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b\"\u0010\u0014R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b#\u0010\u0014¨\u0006%"}, d2 = {"Lio/ktor/server/routing/HostRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "", "", "hostList", "Lkotlin/text/m;", "hostPatterns", "", "portsList", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "Lio/ktor/server/routing/RoutingResolveContext;", "context", "segmentIndex", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "evaluate", "(Lio/ktor/server/routing/RoutingResolveContext;I)Lio/ktor/server/routing/RouteSelectorEvaluation;", "toString", "()Ljava/lang/String;", "component1", "()Ljava/util/List;", "component2", "component3", "copy", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lio/ktor/server/routing/HostRouteSelector;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getHostList", "getHostPatterns", "getPortsList", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HostRouteSelector extends RouteSelector {
    public static final String HostNameParameter = "$RequestHost";
    public static final String PortParameter = "$RequestPort";
    private final List<String> hostList;
    private final List<m> hostPatterns;
    private final List<Integer> portsList;

    public HostRouteSelector(List<String> list, List<m> list2, List<Integer> list3) {
        this.hostList = list;
        this.hostPatterns = list2;
        this.portsList = list3;
        if (list.isEmpty() && list2.isEmpty() && list3.isEmpty()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HostRouteSelector copy$default(HostRouteSelector hostRouteSelector, List list, List list2, List list3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = hostRouteSelector.hostList;
        }
        if ((i10 & 2) != 0) {
            list2 = hostRouteSelector.hostPatterns;
        }
        if ((i10 & 4) != 0) {
            list3 = hostRouteSelector.portsList;
        }
        return hostRouteSelector.copy(list, list2, list3);
    }

    public final List<String> component1() {
        return this.hostList;
    }

    public final List<m> component2() {
        return this.hostPatterns;
    }

    public final List<Integer> component3() {
        return this.portsList;
    }

    public final HostRouteSelector copy(List<String> hostList, List<m> hostPatterns, List<Integer> portsList) {
        return new HostRouteSelector(hostList, hostPatterns, portsList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostRouteSelector)) {
            return false;
        }
        HostRouteSelector hostRouteSelector = (HostRouteSelector) other;
        return p.a(this.hostList, hostRouteSelector.hostList) && p.a(this.hostPatterns, hostRouteSelector.hostPatterns) && p.a(this.portsList, hostRouteSelector.portsList);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        boolean z;
        List<m> list;
        String serverHost = OriginConnectionPointKt.getOrigin(context.getCall().getRequest()).getServerHost();
        int serverPort = OriginConnectionPointKt.getOrigin(context.getCall().getRequest()).getServerPort();
        if (!this.hostList.isEmpty() || !this.hostPatterns.isEmpty()) {
            boolean zContains = this.hostList.contains(serverHost);
            if (zContains || ((list = this.hostPatterns) != null && list.isEmpty())) {
                z = false;
                if (!zContains && !z) {
                    return RouteSelectorEvaluation.INSTANCE.getFailed();
                }
            } else {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((m) it.next()).e(serverHost)) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!zContains) {
                    return RouteSelectorEvaluation.INSTANCE.getFailed();
                }
            }
        }
        if (!this.portsList.isEmpty() && !this.portsList.contains(Integer.valueOf(serverPort))) {
            return RouteSelectorEvaluation.INSTANCE.getFailed();
        }
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parametersBuilderParametersBuilder$default.append(HostNameParameter, serverHost);
        parametersBuilderParametersBuilder$default.append(PortParameter, String.valueOf(serverPort));
        return new RouteSelectorEvaluation.Success(1.0d, parametersBuilderParametersBuilder$default.build(), 0, 4, null);
    }

    public final List<String> getHostList() {
        return this.hostList;
    }

    public final List<m> getHostPatterns() {
        return this.hostPatterns;
    }

    public final List<Integer> getPortsList() {
        return this.portsList;
    }

    public int hashCode() {
        return this.portsList.hashCode() + d.e(this.hostPatterns, this.hostList.hashCode() * 31, 31);
    }

    public String toString() {
        return "(" + this.hostList + ", " + this.hostPatterns + ", " + this.portsList + ')';
    }
}
