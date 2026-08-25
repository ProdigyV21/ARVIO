package io.ktor.server.routing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010!\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0012\u001a\u00020\u000b2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000f2\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lio/ktor/server/routing/RoutingResolveTraceEntry;", "", "Lio/ktor/server/routing/Route;", "route", "", "segmentIndex", "Lio/ktor/server/routing/RoutingResolveResult;", "result", "<init>", "(Lio/ktor/server/routing/Route;ILio/ktor/server/routing/RoutingResolveResult;)V", "item", "Lx6/t0;", "append", "(Lio/ktor/server/routing/RoutingResolveTraceEntry;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "indent", "buildText", "(Ljava/lang/StringBuilder;I)V", "", "toString", "()Ljava/lang/String;", "Lio/ktor/server/routing/Route;", "getRoute", "()Lio/ktor/server/routing/Route;", "I", "getSegmentIndex", "()I", "Lio/ktor/server/routing/RoutingResolveResult;", "getResult", "()Lio/ktor/server/routing/RoutingResolveResult;", "setResult", "(Lio/ktor/server/routing/RoutingResolveResult;)V", "", "children", "Ljava/util/List;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class RoutingResolveTraceEntry {
    private List<RoutingResolveTraceEntry> children;
    private RoutingResolveResult result;
    private final Route route;
    private final int segmentIndex;

    public RoutingResolveTraceEntry(Route route, int i10, RoutingResolveResult routingResolveResult) {
        this.route = route;
        this.segmentIndex = i10;
        this.result = routingResolveResult;
    }

    public final void append(RoutingResolveTraceEntry item) {
        List arrayList = this.children;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.children = arrayList;
        }
        arrayList.add(item);
    }

    public void buildText(StringBuilder builder, int indent) {
        builder.append(u.N(indent, "  ") + this);
        builder.append('\n');
        List<RoutingResolveTraceEntry> list = this.children;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((RoutingResolveTraceEntry) it.next()).buildText(builder, indent + 1);
            }
        }
    }

    public final RoutingResolveResult getResult() {
        return this.result;
    }

    public final Route getRoute() {
        return this.route;
    }

    public final int getSegmentIndex() {
        return this.segmentIndex;
    }

    public final void setResult(RoutingResolveResult routingResolveResult) {
        this.result = routingResolveResult;
    }

    public String toString() {
        return this.route + ", segment:" + this.segmentIndex + " -> " + this.result;
    }

    public /* synthetic */ RoutingResolveTraceEntry(Route route, int i10, RoutingResolveResult routingResolveResult, int i11, h hVar) {
        this(route, i10, (i11 & 4) != 0 ? null : routingResolveResult);
    }
}
