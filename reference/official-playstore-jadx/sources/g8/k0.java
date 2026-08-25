package g8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f15519a;

    public k0(ArrayList arrayList) {
        this.f15519a = arrayList;
    }

    @Override // g8.l0
    public final boolean a(f9.c cVar) {
        ArrayList arrayList = this.f15519a;
        if (arrayList.isEmpty()) {
            return true;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (kotlin.jvm.internal.p.a(((h0) it.next()).c(), cVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // g8.l0
    public final void b(f9.c cVar, ArrayList arrayList) {
        for (Object obj : this.f15519a) {
            if (kotlin.jvm.internal.p.a(((h0) obj).c(), cVar)) {
                arrayList.add(obj);
            }
        }
    }

    @Override // g8.i0
    public final List c(f9.c cVar) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.f15519a) {
            if (kotlin.jvm.internal.p.a(((h0) obj).c(), cVar)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // g8.i0
    public final Collection p(f9.c cVar, r7.l lVar) {
        return ga.r.U(ga.r.I(new ga.t(new ga.p(this.f15519a, 3), v.f15549m), new j0(cVar, 0)));
    }
}
