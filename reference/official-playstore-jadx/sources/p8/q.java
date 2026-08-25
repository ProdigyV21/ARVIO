package p8;

import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x8.h f21173a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Collection f21174b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f21175c;

    public q(x8.h hVar, Collection collection) {
        this(hVar, collection, hVar.f22652a == x8.g.f22650m);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return kotlin.jvm.internal.p.a(this.f21173a, qVar.f21173a) && kotlin.jvm.internal.p.a(this.f21174b, qVar.f21174b) && this.f21175c == qVar.f21175c;
    }

    public final int hashCode() {
        return ((this.f21174b.hashCode() + (this.f21173a.hashCode() * 31)) * 31) + (this.f21175c ? 1231 : 1237);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JavaDefaultQualifiers(nullabilityQualifier=");
        sb2.append(this.f21173a);
        sb2.append(", qualifierApplicabilityTypes=");
        sb2.append(this.f21174b);
        sb2.append(", definitelyNotNull=");
        return a0.c.r(sb2, this.f21175c, ')');
    }

    public q(x8.h hVar, Collection collection, boolean z) {
        this.f21173a = hVar;
        this.f21174b = collection;
        this.f21175c = z;
    }
}
