package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lio/ktor/server/routing/RoutingPathSegment;", "", "value", "", "kind", "Lio/ktor/server/routing/RoutingPathSegmentKind;", "(Ljava/lang/String;Lio/ktor/server/routing/RoutingPathSegmentKind;)V", "getKind", "()Lio/ktor/server/routing/RoutingPathSegmentKind;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class RoutingPathSegment {
    private final RoutingPathSegmentKind kind;
    private final String value;

    public RoutingPathSegment(String str, RoutingPathSegmentKind routingPathSegmentKind) {
        this.value = str;
        this.kind = routingPathSegmentKind;
    }

    public static /* synthetic */ RoutingPathSegment copy$default(RoutingPathSegment routingPathSegment, String str, RoutingPathSegmentKind routingPathSegmentKind, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = routingPathSegment.value;
        }
        if ((i10 & 2) != 0) {
            routingPathSegmentKind = routingPathSegment.kind;
        }
        return routingPathSegment.copy(str, routingPathSegmentKind);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final RoutingPathSegmentKind getKind() {
        return this.kind;
    }

    public final RoutingPathSegment copy(String value, RoutingPathSegmentKind kind) {
        return new RoutingPathSegment(value, kind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoutingPathSegment)) {
            return false;
        }
        RoutingPathSegment routingPathSegment = (RoutingPathSegment) other;
        return p.a(this.value, routingPathSegment.value) && this.kind == routingPathSegment.kind;
    }

    public final RoutingPathSegmentKind getKind() {
        return this.kind;
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.kind.hashCode() + (this.value.hashCode() * 31);
    }

    public String toString() {
        return "RoutingPathSegment(value=" + this.value + ", kind=" + this.kind + ')';
    }
}
