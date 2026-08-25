package x6;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final short f22603i;

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return kotlin.jvm.internal.p.c(this.f22603i & 65535, ((r0) obj).f22603i & 65535);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r0) {
            return this.f22603i == ((r0) obj).f22603i;
        }
        return false;
    }

    public final int hashCode() {
        return this.f22603i;
    }

    public final String toString() {
        return String.valueOf(this.f22603i & 65535);
    }
}
