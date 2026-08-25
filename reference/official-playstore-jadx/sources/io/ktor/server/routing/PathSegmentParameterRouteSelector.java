package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\t\u0010\u001b\u001a\u00020\u001aHÖ\u0001J\b\u0010\u001c\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u001d"}, d2 = {"Lio/ktor/server/routing/PathSegmentParameterRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", ContentDisposition.Parameters.Name, "", "prefix", "suffix", "hasTrailingSlash", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPrefix", "getSuffix", "component1", "component2", "component3", "copy", "equals", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class PathSegmentParameterRouteSelector extends RouteSelector {
    private final String name;
    private final String prefix;
    private final String suffix;

    public /* synthetic */ PathSegmentParameterRouteSelector(String str, String str2, String str3, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3);
    }

    public static /* synthetic */ PathSegmentParameterRouteSelector copy$default(PathSegmentParameterRouteSelector pathSegmentParameterRouteSelector, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pathSegmentParameterRouteSelector.name;
        }
        if ((i10 & 2) != 0) {
            str2 = pathSegmentParameterRouteSelector.prefix;
        }
        if ((i10 & 4) != 0) {
            str3 = pathSegmentParameterRouteSelector.suffix;
        }
        return pathSegmentParameterRouteSelector.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPrefix() {
        return this.prefix;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSuffix() {
        return this.suffix;
    }

    public final PathSegmentParameterRouteSelector copy(String name, String prefix, String suffix) {
        return new PathSegmentParameterRouteSelector(name, prefix, suffix);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PathSegmentParameterRouteSelector)) {
            return false;
        }
        PathSegmentParameterRouteSelector pathSegmentParameterRouteSelector = (PathSegmentParameterRouteSelector) other;
        return p.a(this.name, pathSegmentParameterRouteSelector.name) && p.a(this.prefix, pathSegmentParameterRouteSelector.prefix) && p.a(this.suffix, pathSegmentParameterRouteSelector.suffix);
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        return RouteSelectorKt.evaluatePathSegmentParameter(context.getSegments(), segmentIndex, this.name, this.prefix, this.suffix, false);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    public final String getSuffix() {
        return this.suffix;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.prefix;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.suffix;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        String str = this.prefix;
        if (str == null) {
            str = "";
        }
        sb2.append(str);
        sb2.append('{');
        sb2.append(this.name);
        sb2.append('}');
        String str2 = this.suffix;
        sb2.append(str2 != null ? str2 : "");
        return sb2.toString();
    }

    public PathSegmentParameterRouteSelector(String str, String str2, String str3) {
        this.name = str;
        this.prefix = str2;
        this.suffix = str3;
    }

    public /* synthetic */ PathSegmentParameterRouteSelector(String str, String str2, String str3, boolean z, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, z);
    }

    @e
    public PathSegmentParameterRouteSelector(String str, String str2, String str3, boolean z) {
        this(str, str2, str3);
    }
}
