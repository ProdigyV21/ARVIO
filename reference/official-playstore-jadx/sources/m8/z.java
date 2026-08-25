package m8;

import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends s implements w8.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.c f20297a;

    public z(f9.c cVar) {
        this.f20297a = cVar;
    }

    @Override // w8.t
    public final f9.c c() {
        return this.f20297a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof z) {
            return kotlin.jvm.internal.p.a(this.f20297a, ((z) obj).f20297a);
        }
        return false;
    }

    @Override // w8.d
    public final /* bridge */ /* synthetic */ Collection getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    public final int hashCode() {
        return this.f20297a.hashCode();
    }

    @Override // w8.d
    public final w8.a j(f9.c cVar) {
        return null;
    }

    public final String toString() {
        return z.class.getName() + ": " + this.f20297a;
    }
}
