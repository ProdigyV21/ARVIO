package gb;

import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class k {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f15727c = new k(kotlin.collections.x.g1(new ArrayList()), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f15728a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t7.a f15729b;

    public k(Set set, t7.a aVar) {
        this.f15728a = set;
        this.f15729b = aVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return kotlin.jvm.internal.p.a(kVar.f15728a, this.f15728a) && kotlin.jvm.internal.p.a(kVar.f15729b, this.f15729b);
    }

    public final int hashCode() {
        int iHashCode = (this.f15728a.hashCode() + 1517) * 41;
        t7.a aVar = this.f15729b;
        return iHashCode + (aVar != null ? aVar.hashCode() : 0);
    }
}
