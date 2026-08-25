package j8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements g8.l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f19325a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f19326b;

    public n(List list, String str) {
        this.f19325a = list;
        this.f19326b = str;
        list.size();
        kotlin.collections.x.g1(list).size();
    }

    @Override // g8.l0
    public final boolean a(f9.c cVar) {
        List list = this.f19325a;
        if (list.isEmpty()) {
            return true;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!g8.y.h((g8.i0) it.next(), cVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // g8.l0
    public final void b(f9.c cVar, ArrayList arrayList) {
        Iterator it = this.f19325a.iterator();
        while (it.hasNext()) {
            g8.y.b((g8.i0) it.next(), cVar, arrayList);
        }
    }

    @Override // g8.i0
    public final List c(f9.c cVar) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f19325a.iterator();
        while (it.hasNext()) {
            g8.y.b((g8.i0) it.next(), cVar, arrayList);
        }
        return kotlin.collections.x.c1(arrayList);
    }

    @Override // g8.i0
    public final Collection p(f9.c cVar, r7.l lVar) {
        HashSet hashSet = new HashSet();
        Iterator it = this.f19325a.iterator();
        while (it.hasNext()) {
            hashSet.addAll(((g8.i0) it.next()).p(cVar, lVar));
        }
        return hashSet;
    }

    public final String toString() {
        return this.f19326b;
    }
}
