package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.routing.RoutingResolveResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J%\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0018\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0005¢\u0006\u0004\b\u001d\u0010\u001cJ\u001b\u0010 \u001a\u00020\u000b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0004¢\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b&\u0010'R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010+\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u00148\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b-\u0010.R \u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00040/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010%¨\u00061"}, d2 = {"Lio/ktor/server/routing/RoutingResolveTrace;", "", "Lio/ktor/server/application/ApplicationCall;", "call", "", "", "segments", "<init>", "(Lio/ktor/server/application/ApplicationCall;Ljava/util/List;)V", "Lio/ktor/server/routing/RoutingResolveTraceEntry;", "entry", "Lx6/t0;", "register", "(Lio/ktor/server/routing/RoutingResolveTraceEntry;)V", "Lio/ktor/server/routing/Route;", "route", "", "segmentIndex", "begin", "(Lio/ktor/server/routing/Route;I)V", "Lio/ktor/server/routing/RoutingResolveResult;", "result", "finish", "(Lio/ktor/server/routing/Route;ILio/ktor/server/routing/RoutingResolveResult;)V", "skip", "registerFinalResult", "(Lio/ktor/server/routing/RoutingResolveResult;)V", "toString", "()Ljava/lang/String;", "buildText", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "trait", "addCandidate", "(Ljava/util/List;)V", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "Ljava/util/List;", "getSegments", "()Ljava/util/List;", "Lio/ktor/server/routing/Stack;", "stack", "Lio/ktor/server/routing/Stack;", "routing", "Lio/ktor/server/routing/RoutingResolveTraceEntry;", "finalResult", "Lio/ktor/server/routing/RoutingResolveResult;", "", "resolveCandidates", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RoutingResolveTrace {
    private final ApplicationCall call;
    private RoutingResolveResult finalResult;
    private RoutingResolveTraceEntry routing;
    private final List<String> segments;
    private final Stack<RoutingResolveTraceEntry> stack = new Stack<>();
    private final List<List<RoutingResolveResult.Success>> resolveCandidates = new ArrayList();

    public RoutingResolveTrace(ApplicationCall applicationCall, List<String> list) {
        this.call = applicationCall;
        this.segments = list;
    }

    private final void register(RoutingResolveTraceEntry entry) {
        if (this.stack.empty()) {
            this.routing = entry;
        } else {
            this.stack.peek().append(entry);
        }
    }

    public final void addCandidate(List<RoutingResolveResult.Success> trait) {
        int size = trait.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(trait.get(i10));
        }
        this.resolveCandidates.add(arrayList);
    }

    public final void begin(Route route, int segmentIndex) {
        this.stack.push(new RoutingResolveTraceEntry(route, segmentIndex, null, 4, null));
    }

    public final String buildText() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(toString());
        sb2.append('\n');
        RoutingResolveTraceEntry routingResolveTraceEntry = this.routing;
        if (routingResolveTraceEntry != null) {
            routingResolveTraceEntry.buildText(sb2, 0);
        }
        if (this.finalResult != null) {
            sb2.append("Matched routes:");
            sb2.append('\n');
            if (this.resolveCandidates.isEmpty()) {
                sb2.append("  No results");
                sb2.append('\n');
            } else {
                sb2.append(x.u0(this.resolveCandidates, "\n", null, null, RoutingResolveTrace$buildText$1$2.INSTANCE, 30));
                sb2.append('\n');
            }
            sb2.append("Route resolve result:");
            sb2.append('\n');
            StringBuilder sb3 = new StringBuilder("  ");
            RoutingResolveResult routingResolveResult = this.finalResult;
            if (routingResolveResult == null) {
                p.i("finalResult");
                throw null;
            }
            sb3.append(routingResolveResult);
            sb2.append(sb3.toString());
        }
        return sb2.toString();
    }

    public final void finish(Route route, int segmentIndex, RoutingResolveResult result) {
        RoutingResolveTraceEntry routingResolveTraceEntryPop = this.stack.pop();
        if (!p.a(routingResolveTraceEntryPop.getRoute(), route)) {
            throw new IllegalArgumentException("end should be called for the same route as begin");
        }
        if (routingResolveTraceEntryPop.getSegmentIndex() != segmentIndex) {
            throw new IllegalArgumentException("end should be called for the same segmentIndex as begin");
        }
        routingResolveTraceEntryPop.setResult(result);
        register(routingResolveTraceEntryPop);
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final List<String> getSegments() {
        return this.segments;
    }

    public final void registerFinalResult(RoutingResolveResult result) {
        this.finalResult = result;
    }

    public final void skip(Route route, int segmentIndex, RoutingResolveResult result) {
        register(new RoutingResolveTraceEntry(route, segmentIndex, result));
    }

    public String toString() {
        return "Trace for " + this.segments;
    }
}
