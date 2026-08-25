package s9;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.b f21690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f21691b;

    public f(f9.b bVar, d dVar) {
        this.f21690a = bVar;
        this.f21691b = dVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof f) {
            return kotlin.jvm.internal.p.a(this.f21690a, ((f) obj).f21690a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f21690a.hashCode();
    }
}
