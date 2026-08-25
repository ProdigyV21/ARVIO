package p9;

import g8.a1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f21226b;

    public i(n nVar) {
        this.f21226b = nVar;
    }

    @Override // p9.o, p9.n
    public final Set a() {
        return this.f21226b.a();
    }

    @Override // p9.o, p9.n
    public final Set b() {
        return this.f21226b.b();
    }

    @Override // p9.o, p9.n
    public final Set d() {
        return this.f21226b.d();
    }

    @Override // p9.o, p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        g8.h hVarE = this.f21226b.e(fVar, bVar);
        if (hVarE != null) {
            g8.f fVar2 = hVarE instanceof g8.f ? (g8.f) hVarE : null;
            if (fVar2 != null) {
                return fVar2;
            }
            if (hVarE instanceof a1) {
                return (a1) hVarE;
            }
        }
        return null;
    }

    @Override // p9.o, p9.p
    public final Collection f(f fVar, r7.l lVar) {
        int i10 = f.f21211l & fVar.f21220b;
        f fVar2 = i10 == 0 ? null : new f(i10, fVar.f21219a);
        if (fVar2 == null) {
            return z.f19728i;
        }
        Collection collectionF = this.f21226b.f(fVar2, lVar);
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionF) {
            if (obj instanceof g8.i) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final String toString() {
        return "Classes from " + this.f21226b;
    }
}
