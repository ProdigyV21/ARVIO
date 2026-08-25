package p9;

import com.google.common.util.concurrent.r0;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.collections.x;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f21196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n[] f21197c;

    public a(String str, n[] nVarArr) {
        this.f21196b = str;
        this.f21197c = nVarArr;
    }

    @Override // p9.n
    public final Set a() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (n nVar : this.f21197c) {
            x.b0(linkedHashSet, nVar.a());
        }
        return linkedHashSet;
    }

    @Override // p9.n
    public final Set b() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (n nVar : this.f21197c) {
            x.b0(linkedHashSet, nVar.b());
        }
        return linkedHashSet;
    }

    @Override // p9.n
    public final Collection c(f9.f fVar, o8.b bVar) {
        n[] nVarArr = this.f21197c;
        int length = nVarArr.length;
        if (length == 0) {
            return z.f19728i;
        }
        if (length == 1) {
            return nVarArr[0].c(fVar, bVar);
        }
        Collection collectionH = null;
        for (n nVar : nVarArr) {
            collectionH = r0.h(collectionH, nVar.c(fVar, bVar));
        }
        return collectionH == null ? b0.f19686i : collectionH;
    }

    @Override // p9.n
    public final Set d() {
        n[] nVarArr = this.f21197c;
        return xc.d.h0(nVarArr.length == 0 ? z.f19728i : new ab.k(nVarArr, 2));
    }

    @Override // p9.p
    public final g8.h e(f9.f fVar, o8.b bVar) {
        g8.h hVar = null;
        for (n nVar : this.f21197c) {
            g8.h hVarE = nVar.e(fVar, bVar);
            if (hVarE != null) {
                if (!(hVarE instanceof g8.i) || !((g8.i) hVarE).i0()) {
                    return hVarE;
                }
                if (hVar == null) {
                    hVar = hVarE;
                }
            }
        }
        return hVar;
    }

    @Override // p9.p
    public final Collection f(f fVar, r7.l lVar) {
        n[] nVarArr = this.f21197c;
        int length = nVarArr.length;
        if (length == 0) {
            return z.f19728i;
        }
        if (length == 1) {
            return nVarArr[0].f(fVar, lVar);
        }
        Collection collectionH = null;
        for (n nVar : nVarArr) {
            collectionH = r0.h(collectionH, nVar.f(fVar, lVar));
        }
        return collectionH == null ? b0.f19686i : collectionH;
    }

    @Override // p9.n
    public final Collection g(f9.f fVar, o8.e eVar) {
        n[] nVarArr = this.f21197c;
        int length = nVarArr.length;
        if (length == 0) {
            return z.f19728i;
        }
        if (length == 1) {
            return nVarArr[0].g(fVar, eVar);
        }
        Collection collectionH = null;
        for (n nVar : nVarArr) {
            collectionH = r0.h(collectionH, nVar.g(fVar, eVar));
        }
        return collectionH == null ? b0.f19686i : collectionH;
    }

    public final String toString() {
        return this.f21196b;
    }
}
