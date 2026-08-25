package k9;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f19538a;

    public q(f fVar) {
        this.f19538a = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof q) && kotlin.jvm.internal.p.a(this.f19538a, ((q) obj).f19538a);
    }

    public final int hashCode() {
        return this.f19538a.hashCode();
    }

    public final String toString() {
        return "NormalClass(value=" + this.f19538a + ')';
    }
}
