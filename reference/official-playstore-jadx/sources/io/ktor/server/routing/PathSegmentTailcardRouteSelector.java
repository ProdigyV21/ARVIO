package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import kotlin.text.u;
import t7.a;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B#\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0017HÖ\u0001J\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u001a"}, d2 = {"Lio/ktor/server/routing/PathSegmentTailcardRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", ContentDisposition.Parameters.Name, "", "prefix", "hasTrailingSlash", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPrefix", "component1", "component2", "copy", "equals", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PathSegmentTailcardRouteSelector extends RouteSelector {
    private final String name;
    private final String prefix;

    /* JADX WARN: Multi-variable type inference failed */
    public PathSegmentTailcardRouteSelector() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ PathSegmentTailcardRouteSelector copy$default(PathSegmentTailcardRouteSelector pathSegmentTailcardRouteSelector, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pathSegmentTailcardRouteSelector.name;
        }
        if ((i10 & 2) != 0) {
            str2 = pathSegmentTailcardRouteSelector.prefix;
        }
        return pathSegmentTailcardRouteSelector.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPrefix() {
        return this.prefix;
    }

    public final PathSegmentTailcardRouteSelector copy(String name, String prefix) {
        return new PathSegmentTailcardRouteSelector(name, prefix);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PathSegmentTailcardRouteSelector)) {
            return false;
        }
        PathSegmentTailcardRouteSelector pathSegmentTailcardRouteSelector = (PathSegmentTailcardRouteSelector) other;
        return p.a(this.name, pathSegmentTailcardRouteSelector.name) && p.a(this.prefix, pathSegmentTailcardRouteSelector.prefix);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        List listX0;
        Parameters parametersParametersOf;
        String str;
        List<String> segments = context.getSegments();
        if (segments.isEmpty()) {
            listX0 = z.f19728i;
        } else {
            ListIterator<String> listIterator = segments.listIterator(segments.size());
            while (listIterator.hasPrevious()) {
                if (listIterator.previous().length() != 0) {
                    listX0 = x.X0(segments, listIterator.nextIndex() + 1);
                    break;
                }
            }
            listX0 = z.f19728i;
        }
        int i10 = 0;
        if (this.prefix.length() > 0 && ((str = (String) x.p0(segmentIndex, listX0)) == null || !u.P(str, this.prefix, false))) {
            return RouteSelectorEvaluation.INSTANCE.getFailedPath();
        }
        if (this.name.length() == 0) {
            parametersParametersOf = ParametersKt.parametersOf();
        } else {
            String str2 = this.name;
            List listH0 = x.h0(listX0, segmentIndex);
            ArrayList arrayList = new ArrayList(s.U(listH0, 10));
            for (Object obj : listH0) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    a.Q();
                    throw null;
                }
                String strV = (String) obj;
                if (i10 == 0) {
                    strV = o.V(this.prefix.length(), strV);
                }
                arrayList.add(strV);
                i10 = i11;
            }
            parametersParametersOf = ParametersKt.parametersOf(str2, arrayList);
        }
        return new RouteSelectorEvaluation.Success(segmentIndex < listX0.size() ? 0.1d : 0.2d, parametersParametersOf, listX0.size() - segmentIndex);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public int hashCode() {
        return this.prefix.hashCode() + (this.name.hashCode() * 31);
    }

    public String toString() {
        return "{...}";
    }

    public /* synthetic */ PathSegmentTailcardRouteSelector(String str, String str2, int i10, h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2);
    }

    public PathSegmentTailcardRouteSelector(String str, String str2) {
        this.name = str;
        this.prefix = str2;
        for (int i10 = 0; i10 < str2.length(); i10++) {
            if (str2.charAt(i10) == '/') {
                throw new IllegalArgumentException("Multisegment prefix is not supported");
            }
        }
    }

    @e
    public PathSegmentTailcardRouteSelector(String str, String str2, boolean z) {
        this(str, str2);
    }

    public /* synthetic */ PathSegmentTailcardRouteSelector(String str, String str2, boolean z, int i10, h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, z);
    }
}
