package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h8.h f22212a;

    public h(h8.h hVar) {
        this.f22212a = hVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return kotlin.jvm.internal.p.a(((h) obj).f22212a, this.f22212a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f22212a.hashCode();
    }
}
