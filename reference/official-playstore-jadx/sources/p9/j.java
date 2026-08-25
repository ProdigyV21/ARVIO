package p9;

import androidx.tv.material3.s3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.collections.x;
import u9.v;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f21227b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f21228c;

    public j(n nVar) {
        this.f21228c = nVar;
    }

    @Override // p9.n
    public final Set a() {
        return l().a();
    }

    @Override // p9.n
    public final Set b() {
        return l().b();
    }

    @Override // p9.n
    public Collection c(f9.f fVar, o8.b bVar) {
        switch (this.f21227b) {
            case 1:
                return i9.q.o(j(fVar, bVar), k.f21231n);
            default:
                return j(fVar, bVar);
        }
    }

    @Override // p9.n
    public final Set d() {
        return l().d();
    }

    @Override // p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        return l().e(fVar, bVar);
    }

    @Override // p9.p
    public Collection f(f fVar, r7.l lVar) {
        switch (this.f21227b) {
            case 1:
                Collection collectionI = i(fVar, lVar);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : collectionI) {
                    if (((g8.k) obj) instanceof g8.a) {
                        arrayList.add(obj);
                    } else {
                        arrayList2.add(obj);
                    }
                }
                return x.I0(i9.q.o(arrayList, k.f21230m), arrayList2);
            default:
                return i(fVar, lVar);
        }
    }

    @Override // p9.n
    public Collection g(f9.f fVar, o8.e eVar) {
        switch (this.f21227b) {
            case 1:
                return i9.q.o(k(fVar, eVar), k.f21232o);
            default:
                return k(fVar, eVar);
        }
    }

    public final n h() {
        return l() instanceof j ? ((j) l()).h() : l();
    }

    public final Collection i(f fVar, r7.l lVar) {
        return l().f(fVar, lVar);
    }

    public final Collection j(f9.f fVar, o8.b bVar) {
        return l().c(fVar, bVar);
    }

    public final Collection k(f9.f fVar, o8.e eVar) {
        return l().g(fVar, eVar);
    }

    public final n l() {
        switch (this.f21227b) {
            case 0:
                return (n) ((u9.s) this.f21228c).invoke();
            default:
                return (n) this.f21228c;
        }
    }

    public j(v vVar, r7.a aVar) {
        this.f21228c = vVar.d(new s3(4, aVar));
    }
}
