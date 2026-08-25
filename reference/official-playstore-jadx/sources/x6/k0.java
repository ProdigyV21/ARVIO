package x6;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final byte f22590i;

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return kotlin.jvm.internal.p.c(this.f22590i & 255, ((k0) obj).f22590i & 255);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k0) {
            return this.f22590i == ((k0) obj).f22590i;
        }
        return false;
    }

    public final int hashCode() {
        return this.f22590i;
    }

    public final String toString() {
        return String.valueOf(this.f22590i & 255);
    }
}
