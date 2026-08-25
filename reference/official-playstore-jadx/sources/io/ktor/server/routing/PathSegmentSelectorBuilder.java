package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/PathSegmentSelectorBuilder;", "", "()V", "parseConstant", "Lio/ktor/server/routing/RouteSelector;", "value", "", "hasTrailingSlash", "", "parseName", "parseParameter", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PathSegmentSelectorBuilder {
    public static final PathSegmentSelectorBuilder INSTANCE = new PathSegmentSelectorBuilder();

    private PathSegmentSelectorBuilder() {
    }

    public final RouteSelector parseConstant(String value) {
        return value.equals("*") ? PathSegmentWildcardRouteSelector.INSTANCE : new PathSegmentConstantRouteSelector(value);
    }

    public final String parseName(String value) {
        String strSubstring = value.substring(o.G0('{', value, "").length() + 1, (value.length() - o.E0('}', value, "").length()) - 1);
        return u.K(strSubstring, "?", false) ? o.W(1, strSubstring) : u.K(strSubstring, "...", false) ? o.W(3, strSubstring) : strSubstring;
    }

    public final RouteSelector parseParameter(String value) {
        int iE0 = o.e0(value, '{', 0, false, 6);
        int iK0 = o.k0(value, '}', 0, 6);
        String strSubstring = iE0 == 0 ? null : value.substring(0, iE0);
        String strSubstring2 = iK0 != value.length() - 1 ? value.substring(iK0 + 1) : null;
        String strSubstring3 = value.substring(iE0 + 1, iK0);
        if (u.K(strSubstring3, "?", false)) {
            return new PathSegmentOptionalParameterRouteSelector(o.W(1, strSubstring3), strSubstring, strSubstring2);
        }
        if (!u.K(strSubstring3, "...", false)) {
            return new PathSegmentParameterRouteSelector(strSubstring3, strSubstring, strSubstring2);
        }
        if (strSubstring2 != null && strSubstring2.length() > 0) {
            throw new IllegalArgumentException("Suffix after tailcard is not supported");
        }
        String strW = o.W(3, strSubstring3);
        if (strSubstring == null) {
            strSubstring = "";
        }
        return new PathSegmentTailcardRouteSelector(strW, strSubstring);
    }

    @e
    public final RouteSelector parseConstant(String value, boolean hasTrailingSlash) {
        return parseConstant(value);
    }

    @e
    public final RouteSelector parseParameter(String value, boolean hasTrailingSlash) {
        return parseParameter(value);
    }
}
