package io.ktor.server.routing;

import io.ktor.http.CodecsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/server/routing/RoutingPathSegment;", "segment", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RoutingPath$Companion$parse$segments$2 extends r implements l<String, RoutingPathSegment> {
    public static final RoutingPath$Companion$parse$segments$2 INSTANCE = new RoutingPath$Companion$parse$segments$2();

    public RoutingPath$Companion$parse$segments$2() {
        super(1);
    }

    @Override // r7.l
    public final RoutingPathSegment invoke(String str) {
        return (o.U(str, '{') && o.U(str, '}')) ? new RoutingPathSegment(str, RoutingPathSegmentKind.Parameter) : new RoutingPathSegment(CodecsKt.decodeURLPart$default(str, 0, 0, null, 7, null), RoutingPathSegmentKind.Constant);
    }
}
