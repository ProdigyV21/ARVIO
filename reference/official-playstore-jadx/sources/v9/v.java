package v9;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements o0, y9.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public w f22253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f22254b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22255c;

    public v(AbstractCollection abstractCollection) {
        abstractCollection.isEmpty();
        LinkedHashSet linkedHashSet = new LinkedHashSet(abstractCollection);
        this.f22254b = linkedHashSet;
        this.f22255c = linkedHashSet.hashCode();
    }

    public final b0 c() {
        k0.f22220l.getClass();
        return e.t(k0.f22221m, this, kotlin.collections.z.f19728i, false, ac.b.w("member scope for intersection type", this.f22254b), new s8.d(this, 10));
    }

    @Override // v9.o0
    public final Collection d() {
        return this.f22254b;
    }

    @Override // v9.o0
    public final g8.h e() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        return kotlin.jvm.internal.p.a(this.f22254b, ((v) obj).f22254b);
    }

    @Override // v9.o0
    public final boolean f() {
        return false;
    }

    public final String g(r7.l lVar) {
        return kotlin.collections.x.u0(kotlin.collections.x.W0(this.f22254b, new androidx.tv.foundation.lazy.grid.n(lVar, 2)), " & ", "{", "}", new s8.d(lVar, 11), 24);
    }

    @Override // v9.o0
    public final List getParameters() {
        return kotlin.collections.z.f19728i;
    }

    public final int hashCode() {
        return this.f22255c;
    }

    @Override // v9.o0
    public final d8.k j() {
        return ((w) this.f22254b.iterator().next()).I0().j();
    }

    public final String toString() {
        return g(u.f22252i);
    }
}
