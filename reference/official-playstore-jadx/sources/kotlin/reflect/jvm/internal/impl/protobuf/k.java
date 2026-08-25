package kotlin.reflect.jvm.internal.impl.protobuf;

import io.ktor.network.sockets.DatagramKt;

/* JADX INFO: loaded from: classes5.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f19802a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f19803b;

    public k(Object obj, int i10) {
        this.f19802a = obj;
        this.f19803b = i10;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.f19802a == kVar.f19802a && this.f19803b == kVar.f19803b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f19802a) * DatagramKt.MAX_DATAGRAM_SIZE) + this.f19803b;
    }
}
