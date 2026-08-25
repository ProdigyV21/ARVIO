package io.ktor.server.routing;

import io.ktor.server.routing.RoutingResolveResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "path", "", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RoutingResolveTrace$buildText$1$2 extends r implements l<List<? extends RoutingResolveResult.Success>, CharSequence> {
    public static final RoutingResolveTrace$buildText$1$2 INSTANCE = new RoutingResolveTrace$buildText$1$2();

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingResolveTrace$buildText$1$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<RoutingResolveResult.Success, CharSequence> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final CharSequence invoke(RoutingResolveResult.Success success) {
            return "\"" + success.getRoute().getSelector() + '\"';
        }
    }

    public RoutingResolveTrace$buildText$1$2() {
        super(1);
    }

    @Override // r7.l
    public final CharSequence invoke(List<RoutingResolveResult.Success> list) {
        return x.u0(list, " -> ", "  ", null, AnonymousClass1.INSTANCE, 28);
    }
}
