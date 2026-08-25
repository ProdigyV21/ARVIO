package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import io.ktor.http.CodecsKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.http.URLDecodeException;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import io.ktor.server.routing.RoutingResolveResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.o;
import kotlin.text.u;
import r7.l;
import t7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0018\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0016\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010!\u001a\u00020 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u0006H\u0002¢\u0006\u0004\b!\u0010\"J/\u0010$\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020#2\u0016\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016H\u0002¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u001c¢\u0006\u0004\b&\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010*\u001a\u0004\b+\u0010,R&\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010-R\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020\r0\u00068\u0006¢\u0006\f\n\u0004\b.\u0010-\u001a\u0004\b/\u00100R\u0017\u00101\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0016\u00105\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R$\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0018\u00109\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<¨\u0006="}, d2 = {"Lio/ktor/server/routing/RoutingResolveContext;", "", "Lio/ktor/server/routing/Route;", "routing", "Lio/ktor/server/application/ApplicationCall;", "call", "", "Lkotlin/Function1;", "Lio/ktor/server/routing/RoutingResolveTrace;", "Lx6/t0;", "tracers", "<init>", "(Lio/ktor/server/routing/Route;Lio/ktor/server/application/ApplicationCall;Ljava/util/List;)V", "", "path", "parse", "(Ljava/lang/String;)Ljava/util/List;", "entry", "", "segmentIndex", "Ljava/util/ArrayList;", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "Lkotlin/collections/ArrayList;", "trait", "", "matchedQuality", "handleRoute", "(Lio/ktor/server/routing/Route;ILjava/util/ArrayList;D)D", "Lio/ktor/server/routing/RoutingResolveResult;", "findBestRoute", "()Lio/ktor/server/routing/RoutingResolveResult;", "new", "", "isBetterResolve", "(Ljava/util/List;)Z", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "updateFailedEvaluation", "(Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;Ljava/util/ArrayList;)V", "resolve", "Lio/ktor/server/routing/Route;", "getRouting", "()Lio/ktor/server/routing/Route;", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "Ljava/util/List;", "segments", "getSegments", "()Ljava/util/List;", "hasTrailingSlash", "Z", "getHasTrailingSlash", "()Z", "trace", "Lio/ktor/server/routing/RoutingResolveTrace;", "resolveResult", "Ljava/util/ArrayList;", "failedEvaluation", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "failedEvaluationDepth", "I", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RoutingResolveContext {
    private final ApplicationCall call;
    private int failedEvaluationDepth;
    private final boolean hasTrailingSlash;
    private final Route routing;
    private final List<String> segments;
    private final RoutingResolveTrace trace;
    private final List<l<RoutingResolveTrace, t0>> tracers;
    private final ArrayList<RoutingResolveResult.Success> resolveResult = new ArrayList<>(16);
    private RouteSelectorEvaluation.Failure failedEvaluation = RouteSelectorEvaluation.INSTANCE.getFailedPath();

    /* JADX WARN: Multi-variable type inference failed */
    public RoutingResolveContext(Route route, ApplicationCall applicationCall, List<? extends l<? super RoutingResolveTrace, t0>> list) throws BadRequestException {
        this.routing = route;
        this.call = applicationCall;
        this.tracers = list;
        this.hasTrailingSlash = o.X(ApplicationRequestPropertiesKt.path(applicationCall.getRequest()), '/');
        try {
            List<String> list2 = parse(ApplicationRequestPropertiesKt.path(applicationCall.getRequest()));
            this.segments = list2;
            this.trace = list.isEmpty() ? null : new RoutingResolveTrace(applicationCall, list2);
        } catch (URLDecodeException e5) {
            throw new BadRequestException("Url decode failed for " + ApplicationRequestPropertiesKt.getUri(this.call.getRequest()), e5);
        }
    }

    private final RoutingResolveResult findBestRoute() {
        HttpStatusCode notFound;
        ArrayList<RoutingResolveResult.Success> arrayList = this.resolveResult;
        if (arrayList.isEmpty()) {
            Route route = this.routing;
            RouteSelectorEvaluation.Failure failure = this.failedEvaluation;
            if (failure == null || (notFound = failure.getFailureStatusCode()) == null) {
                notFound = HttpStatusCode.INSTANCE.getNotFound();
            }
            return new RoutingResolveResult.Failure(route, "No matched subtrees found", notFound);
        }
        int i10 = 0;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        int iW = a.w(arrayList);
        double dMin = Double.MAX_VALUE;
        if (iW >= 0) {
            while (true) {
                RoutingResolveResult.Success success = arrayList.get(i10);
                parametersBuilderParametersBuilder$default.appendAll(success.getParameters());
                dMin = Math.min(dMin, success.getQuality() == -1.0d ? 1.0d : success.getQuality());
                if (i10 == iW) {
                    break;
                }
                i10++;
            }
        }
        return new RoutingResolveResult.Success(((RoutingResolveResult.Success) x.w0(arrayList)).getRoute(), parametersBuilderParametersBuilder$default.build(), dMin);
    }

    private final double handleRoute(Route entry, int segmentIndex, ArrayList<RoutingResolveResult.Success> trait, double matchedQuality) {
        double dMax;
        double d4;
        ArrayList<RoutingResolveResult.Success> arrayList = trait;
        RouteSelectorEvaluation routeSelectorEvaluationEvaluate = entry.getSelector().evaluate(this, segmentIndex);
        double d10 = -1.7976931348623157E308d;
        if (routeSelectorEvaluationEvaluate instanceof RouteSelectorEvaluation.Failure) {
            RoutingResolveTrace routingResolveTrace = this.trace;
            if (routingResolveTrace != null) {
                routingResolveTrace.skip(entry, segmentIndex, new RoutingResolveResult.Failure(entry, "Selector didn't match", ((RouteSelectorEvaluation.Failure) routeSelectorEvaluationEvaluate).getFailureStatusCode()));
            }
            if (segmentIndex == this.segments.size()) {
                updateFailedEvaluation((RouteSelectorEvaluation.Failure) routeSelectorEvaluationEvaluate, arrayList);
            }
            return -1.7976931348623157E308d;
        }
        if (!(routeSelectorEvaluationEvaluate instanceof RouteSelectorEvaluation.Success)) {
            throw new IllegalStateException("Check failed.");
        }
        RouteSelectorEvaluation.Success success = (RouteSelectorEvaluation.Success) routeSelectorEvaluationEvaluate;
        if (success.getQuality() != -1.0d && success.getQuality() < matchedQuality) {
            RoutingResolveTrace routingResolveTrace2 = this.trace;
            if (routingResolveTrace2 != null) {
                routingResolveTrace2.skip(entry, segmentIndex, new RoutingResolveResult.Failure(entry, "Better match was already found", HttpStatusCode.INSTANCE.getNotFound()));
            }
            return -1.7976931348623157E308d;
        }
        RoutingResolveResult.Success success2 = new RoutingResolveResult.Success(entry, success.getParameters(), success.getQuality());
        int segmentIncrement = success.getSegmentIncrement() + segmentIndex;
        if (entry.getChildren().isEmpty() && segmentIncrement != this.segments.size()) {
            RoutingResolveTrace routingResolveTrace3 = this.trace;
            if (routingResolveTrace3 != null) {
                routingResolveTrace3.skip(entry, segmentIncrement, new RoutingResolveResult.Failure(entry, "Not all segments matched", HttpStatusCode.INSTANCE.getNotFound()));
            }
            return -1.7976931348623157E308d;
        }
        RoutingResolveTrace routingResolveTrace4 = this.trace;
        if (routingResolveTrace4 != null) {
            routingResolveTrace4.begin(entry, segmentIncrement);
        }
        arrayList.add(success2);
        if (entry.getHandlers$ktor_server_core().isEmpty() || segmentIncrement != this.segments.size()) {
            dMax = -1.7976931348623157E308d;
        } else {
            if (this.resolveResult.isEmpty() || isBetterResolve(arrayList)) {
                dMax = success.getQuality();
                this.resolveResult.clear();
                this.resolveResult.addAll(arrayList);
                this.failedEvaluation = null;
            } else {
                dMax = -1.7976931348623157E308d;
            }
            RoutingResolveTrace routingResolveTrace5 = this.trace;
            if (routingResolveTrace5 != null) {
                routingResolveTrace5.addCandidate(arrayList);
            }
        }
        int iW = a.w(entry.getChildren());
        if (iW >= 0) {
            int i10 = 0;
            while (true) {
                d4 = d10;
                double dHandleRoute = handleRoute(entry.getChildren().get(i10), segmentIncrement, arrayList, dMax);
                if (dHandleRoute > 0.0d) {
                    dMax = Math.max(dMax, dHandleRoute);
                }
                if (i10 == iW) {
                    break;
                }
                i10++;
                arrayList = trait;
                d10 = d4;
            }
        } else {
            d4 = -1.7976931348623157E308d;
        }
        x.O0(trait);
        RoutingResolveTrace routingResolveTrace6 = this.trace;
        if (routingResolveTrace6 != null) {
            routingResolveTrace6.finish(entry, segmentIncrement, success2);
        }
        return dMax > 0.0d ? success.getQuality() : d4;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x009e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean isBetterResolve(java.util.List<io.ktor.server.routing.RoutingResolveResult.Success> r12) {
        /*
            r11 = this;
            java.util.ArrayList<io.ktor.server.routing.RoutingResolveResult$Success> r0 = r11.resolveResult
            r1 = 0
            r2 = r1
            r3 = r2
        L5:
            int r4 = r0.size()
            r5 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            if (r2 >= r4) goto L41
            int r4 = r12.size()
            if (r3 >= r4) goto L41
            java.lang.Object r4 = r0.get(r2)
            io.ktor.server.routing.RoutingResolveResult$Success r4 = (io.ktor.server.routing.RoutingResolveResult.Success) r4
            double r7 = r4.getQuality()
            java.lang.Object r4 = r12.get(r3)
            io.ktor.server.routing.RoutingResolveResult$Success r4 = (io.ktor.server.routing.RoutingResolveResult.Success) r4
            double r9 = r4.getQuality()
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L2e
            int r2 = r2 + 1
            goto L5
        L2e:
            int r4 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r4 != 0) goto L35
        L32:
            int r3 = r3 + 1
            goto L5
        L35:
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 != 0) goto L3c
            int r2 = r2 + 1
            goto L32
        L3c:
            int r12 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r12 <= 0) goto L9e
            goto L9c
        L41:
            boolean r2 = r0.isEmpty()
            r3 = 0
            if (r2 == 0) goto L4a
            r2 = r1
            goto L6d
        L4a:
            java.util.Iterator r0 = r0.iterator()
            r2 = r1
        L4f:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L6d
            java.lang.Object r4 = r0.next()
            io.ktor.server.routing.RoutingResolveResult$Success r4 = (io.ktor.server.routing.RoutingResolveResult.Success) r4
            double r7 = r4.getQuality()
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L64
            goto L4f
        L64:
            int r2 = r2 + 1
            if (r2 < 0) goto L69
            goto L4f
        L69:
            t7.a.P()
            throw r3
        L6d:
            if (r12 == 0) goto L77
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L77
            r0 = r1
            goto L9a
        L77:
            java.util.Iterator r12 = r12.iterator()
            r0 = r1
        L7c:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L9a
            java.lang.Object r4 = r12.next()
            io.ktor.server.routing.RoutingResolveResult$Success r4 = (io.ktor.server.routing.RoutingResolveResult.Success) r4
            double r7 = r4.getQuality()
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 != 0) goto L91
            goto L7c
        L91:
            int r0 = r0 + 1
            if (r0 < 0) goto L96
            goto L7c
        L96:
            t7.a.P()
            throw r3
        L9a:
            if (r0 <= r2) goto L9e
        L9c:
            r12 = 1
            return r12
        L9e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.routing.RoutingResolveContext.isBetterResolve(java.util.List):boolean");
    }

    private final List<String> parse(String path) {
        if (path.length() == 0 || path.equals(DomExceptionUtils.SEPARATOR)) {
            return z.f19728i;
        }
        int length = path.length();
        int i10 = 0;
        for (int i11 = 0; i11 < path.length(); i11++) {
            if (path.charAt(i11) == '/') {
                i10++;
            }
        }
        ArrayList arrayList = new ArrayList(i10);
        int i12 = 0;
        int i13 = 0;
        while (i12 < length) {
            int iE0 = o.e0(path, '/', i13, false, 4);
            int i14 = iE0 == -1 ? length : iE0;
            if (i14 == i13) {
                i13 = i14 + 1;
            } else {
                String str = path;
                arrayList.add(CodecsKt.decodeURLPart$default(str, i13, i14, null, 4, null));
                i13 = i14 + 1;
                path = str;
            }
            i12 = i14;
        }
        String str2 = path;
        if (!IgnoreTrailingSlashKt.getIgnoreTrailingSlash(this.call) && u.K(str2, DomExceptionUtils.SEPARATOR, false)) {
            arrayList.add("");
        }
        return arrayList;
    }

    private final void updateFailedEvaluation(RouteSelectorEvaluation.Failure failure, ArrayList<RoutingResolveResult.Success> trait) {
        RouteSelectorEvaluation.Failure failure2 = this.failedEvaluation;
        if (failure2 == null) {
            return;
        }
        if (failure2.getQuality() < failure.getQuality() || this.failedEvaluationDepth < trait.size()) {
            if (trait == null || !trait.isEmpty()) {
                for (RoutingResolveResult.Success success : trait) {
                    if (success.getQuality() != -1.0d && success.getQuality() != 1.0d) {
                        return;
                    }
                }
            }
            this.failedEvaluation = failure;
            this.failedEvaluationDepth = trait.size();
        }
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final boolean getHasTrailingSlash() {
        return this.hasTrailingSlash;
    }

    public final Route getRouting() {
        return this.routing;
    }

    public final List<String> getSegments() {
        return this.segments;
    }

    public final RoutingResolveResult resolve() {
        handleRoute(this.routing, 0, new ArrayList<>(), -1.7976931348623157E308d);
        RoutingResolveResult routingResolveResultFindBestRoute = findBestRoute();
        RoutingResolveTrace routingResolveTrace = this.trace;
        if (routingResolveTrace != null) {
            routingResolveTrace.registerFinalResult(routingResolveResultFindBestRoute);
        }
        RoutingResolveTrace routingResolveTrace2 = this.trace;
        if (routingResolveTrace2 != null) {
            Iterator<T> it = this.tracers.iterator();
            while (it.hasNext()) {
                ((l) it.next()).invoke(routingResolveTrace2);
            }
        }
        return routingResolveResultFindBestRoute;
    }
}
