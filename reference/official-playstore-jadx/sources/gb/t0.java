package gb;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f15816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Proxy f15817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f15818c;

    public t0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        this.f15816a = aVar;
        this.f15817b = proxy;
        this.f15818c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof t0)) {
            return false;
        }
        t0 t0Var = (t0) obj;
        return kotlin.jvm.internal.p.a(t0Var.f15816a, this.f15816a) && kotlin.jvm.internal.p.a(t0Var.f15817b, this.f15817b) && kotlin.jvm.internal.p.a(t0Var.f15818c, this.f15818c);
    }

    public final int hashCode() {
        return this.f15818c.hashCode() + ((this.f15817b.hashCode() + ((this.f15816a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        return "Route{" + this.f15818c + '}';
    }
}
