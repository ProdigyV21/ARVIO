package z2;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f23163a;

    public b(String str) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        this.f23163a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        return this.f23163a.equals(((b) obj).f23163a);
    }

    public final int hashCode() {
        return this.f23163a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return a0.c.p(new StringBuilder("Encoding{name=\""), this.f23163a, "\"}");
    }
}
