package s8;

import eb.v;
import ga.j;
import ga.m;
import ga.p;
import ga.t;
import java.util.Iterator;
import u9.r;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements h8.h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.core.provider.e f21671i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final w8.d f21672l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f21673m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r f21674n;

    public e(androidx.core.provider.e eVar, w8.d dVar, boolean z) {
        this.f21671i = eVar;
        this.f21672l = dVar;
        this.f21673m = z;
        this.f21674n = ((a) eVar.f2140l).f21645a.e(new d(this, 0));
    }

    @Override // h8.h
    public final boolean isEmpty() {
        return this.f21672l.getAnnotations().isEmpty();
    }

    @Override // java.lang.Iterable
    public final Iterator<h8.b> iterator() {
        j jVar;
        w8.d dVar = this.f21672l;
        int i10 = 3;
        t tVar = new t(new p(dVar.getAnnotations(), 3), this.f21674n);
        f9.f fVar = q8.c.f21338a;
        m mVarG = kotlin.collections.r.G(new m[]{tVar, new v(q8.c.a(d8.p.f14744m, dVar, this.f21671i), 1)});
        com.arflix.tv.util.a aVar = new com.arflix.tv.util.a(1);
        if (mVarG instanceof t) {
            t tVar2 = (t) mVarG;
            jVar = new j(tVar2.f15603a, tVar2.f15604b, aVar);
        } else {
            jVar = new j(mVarG, new com.arflix.tv.util.a(i10), aVar);
        }
        return new ga.h(ga.r.J(jVar, new com.arflix.tv.util.a(4)));
    }

    @Override // h8.h
    public final h8.b j(f9.c cVar) {
        h8.b bVar;
        w8.d dVar = this.f21672l;
        w8.a aVarJ = dVar.j(cVar);
        if (aVarJ != null && (bVar = (h8.b) this.f21674n.invoke(aVarJ)) != null) {
            return bVar;
        }
        f9.f fVar = q8.c.f21338a;
        return q8.c.a(cVar, dVar, this.f21671i);
    }

    @Override // h8.h
    public final boolean x(f9.c cVar) {
        return j(cVar) != null;
    }
}
