package t8;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.f f21967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w8.g f21968b;

    public q(f9.f fVar, w8.g gVar) {
        this.f21967a = fVar;
        this.f21968b = gVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof q) {
            return kotlin.jvm.internal.p.a(this.f21967a, ((q) obj).f21967a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f21967a.hashCode();
    }
}
