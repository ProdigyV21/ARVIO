package p9;

import g8.u0;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o implements n {
    @Override // p9.n
    public Set a() {
        Collection collectionF = f(f.f21215p, ea.e.f15084i);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : collectionF) {
            if (obj instanceof u0) {
                linkedHashSet.add(((u0) obj).getName());
            }
        }
        return linkedHashSet;
    }

    @Override // p9.n
    public Set b() {
        Collection collectionF = f(f.f21216q, ea.e.f15084i);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : collectionF) {
            if (obj instanceof u0) {
                linkedHashSet.add(((u0) obj).getName());
            }
        }
        return linkedHashSet;
    }

    @Override // p9.n
    public Collection c(f9.f fVar, o8.b bVar) {
        return z.f19728i;
    }

    @Override // p9.n
    public Set d() {
        return null;
    }

    @Override // p9.p
    public g8.h e(f9.f fVar, o8.b bVar) {
        return null;
    }

    @Override // p9.p
    public Collection f(f fVar, r7.l lVar) {
        return z.f19728i;
    }

    @Override // p9.n
    public Collection g(f9.f fVar, o8.e eVar) {
        return z.f19728i;
    }
}
