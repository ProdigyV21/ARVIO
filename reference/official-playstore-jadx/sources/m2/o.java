package m2;

/* JADX INFO: loaded from: classes3.dex */
public final class o extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f20212a = h.f20202c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        return this.f20212a.equals(((o) obj).f20212a);
    }

    public final int hashCode() {
        return this.f20212a.hashCode() + (o.class.getName().hashCode() * 31);
    }

    public final String toString() {
        return "Failure {mOutputData=" + this.f20212a + '}';
    }
}
