package io.ktor.server.routing;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arflix.tv.cast.a;
import ga.r;
import ga.t;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.text.o;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lio/ktor/server/routing/RoutingPath;", "", "parts", "", "Lio/ktor/server/routing/RoutingPathSegment;", "(Ljava/util/List;)V", "getParts", "()Ljava/util/List;", "toString", "", "Companion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RoutingPath {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoutingPath root = new RoutingPath(z.f19728i);
    private final List<RoutingPathSegment> parts;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/ktor/server/routing/RoutingPath$Companion;", "", "()V", "root", "Lio/ktor/server/routing/RoutingPath;", "getRoot", "()Lio/ktor/server/routing/RoutingPath;", "parse", "path", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final RoutingPath getRoot() {
            return RoutingPath.root;
        }

        public final RoutingPath parse(String path) {
            return path.equals(DomExceptionUtils.SEPARATOR) ? getRoot() : new RoutingPath(r.U(new t(r.I(new t(o.p0(path, new String[]{DomExceptionUtils.SEPARATOR}, 0), new a(path, 9)), RoutingPath$Companion$parse$segments$1.INSTANCE), RoutingPath$Companion$parse$segments$2.INSTANCE)), null);
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RoutingPath$toString$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/routing/RoutingPathSegment;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements l<RoutingPathSegment, CharSequence> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final CharSequence invoke(RoutingPathSegment routingPathSegment) {
            return routingPathSegment.getValue();
        }
    }

    public /* synthetic */ RoutingPath(List list, h hVar) {
        this(list);
    }

    public final List<RoutingPathSegment> getParts() {
        return this.parts;
    }

    public String toString() {
        return x.u0(this.parts, DomExceptionUtils.SEPARATOR, null, null, AnonymousClass1.INSTANCE, 30);
    }

    private RoutingPath(List<RoutingPathSegment> list) {
        this.parts = list;
    }
}
