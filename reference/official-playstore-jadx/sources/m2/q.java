package m2;

/* JADX INFO: loaded from: classes3.dex */
public final class q extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f20213a;

    public q(h hVar) {
        this.f20213a = hVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q.class != obj.getClass()) {
            return false;
        }
        return this.f20213a.equals(((q) obj).f20213a);
    }

    public final int hashCode() {
        return this.f20213a.hashCode() + (q.class.getName().hashCode() * 31);
    }

    public final String toString() {
        return "Success {mOutputData=" + this.f20213a + '}';
    }
}
