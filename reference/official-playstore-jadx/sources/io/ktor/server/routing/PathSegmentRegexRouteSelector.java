package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import kotlin.Metadata;
import kotlin.collections.n0;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.f;
import kotlin.text.i;
import kotlin.text.j;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/PathSegmentRegexRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "Lkotlin/text/m;", "regex", "<init>", "(Lkotlin/text/m;)V", "Lkotlin/text/i;", "result", "", "lastSlashPosition", "", "prefix", "countSegments", "(Lkotlin/text/i;ILjava/lang/String;)I", "Lio/ktor/server/routing/RoutingResolveContext;", "context", "segmentIndex", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "evaluate", "(Lio/ktor/server/routing/RoutingResolveContext;I)Lio/ktor/server/routing/RouteSelectorEvaluation;", "toString", "()Ljava/lang/String;", "Lkotlin/text/m;", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PathSegmentRegexRouteSelector extends RouteSelector {
    private static final m GROUP_NAME_MATCHER = new m("(^|[^\\\\])\\(\\?<(\\p{Alpha}\\p{Alnum}*)>(.*?[^\\\\])?\\)");
    private final m regex;

    public PathSegmentRegexRouteSelector(m mVar) {
        this.regex = mVar;
    }

    private final int countSegments(i result, int lastSlashPosition, String prefix) {
        String strSubstring = result.getValue().substring(0, lastSlashPosition);
        int i10 = 0;
        for (int i11 = 0; i11 < strSubstring.length(); i11++) {
            if (strSubstring.charAt(i11) == '/') {
                i10++;
            }
        }
        return p.a(prefix, DomExceptionUtils.SEPARATOR) ? i10 : i10 + 1;
    }

    @Override // io.ktor.server.routing.RouteSelector
    public RouteSelectorEvaluation evaluate(RoutingResolveContext context, int segmentIndex) {
        int iCountSegments;
        String str = (o.A0(this.regex.f19951i.pattern(), '/') || u.P(this.regex.f19951i.pattern(), "\\/", false)) ? DomExceptionUtils.SEPARATOR : "";
        String strU0 = x.u0(x.h0(context.getSegments(), segmentIndex), DomExceptionUtils.SEPARATOR, str, (o.X(this.regex.f19951i.pattern(), '/') && IgnoreTrailingSlashKt.getIgnoreTrailingSlash(context.getCall())) ? DomExceptionUtils.SEPARATOR : "", null, 56);
        k kVarB = m.b(this.regex, strU0);
        if (kVarB == null) {
            return RouteSelectorEvaluation.INSTANCE.getFailed();
        }
        int length = kVarB.f19946a.group().length();
        if (strU0.length() == length) {
            iCountSegments = context.getSegments().size() - segmentIndex;
        } else {
            if (strU0.charAt(length) != '/') {
                if (length >= 1) {
                    int i10 = length - 1;
                    if (strU0.charAt(i10) == '/') {
                        iCountSegments = countSegments(kVarB, i10, str);
                    }
                }
                return RouteSelectorEvaluation.INSTANCE.getFailed();
            }
            iCountSegments = countSegments(kVarB, length, str);
        }
        j jVar = kVarB.f19948c;
        Parameters.Companion companion = Parameters.INSTANCE;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        ga.k kVar = new ga.k(m.c(this.regex.f19951i.pattern(), GROUP_NAME_MATCHER));
        while (kVar.hasNext()) {
            String str2 = (String) ((n0) ((k) ((i) kVar.next()).a().f19673l).b()).get(2);
            f fVar = jVar.get(str2);
            parametersBuilderParametersBuilder$default.append(str2, fVar != null ? fVar.f19943a : "");
        }
        return new RouteSelectorEvaluation.Success(1.0d, parametersBuilderParametersBuilder$default.build(), iCountSegments);
    }

    public String toString() {
        return "Regex(" + this.regex.f19951i.pattern() + ')';
    }
}
