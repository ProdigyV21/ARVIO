package k9;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v9.w f19537a;

    public p(v9.w wVar) {
        this.f19537a = wVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p) && kotlin.jvm.internal.p.a(this.f19537a, ((p) obj).f19537a);
    }

    public final int hashCode() {
        return this.f19537a.hashCode();
    }

    public final String toString() {
        return "LocalClass(type=" + this.f19537a + ')';
    }
}
