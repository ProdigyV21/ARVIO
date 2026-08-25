package androidx.navigation;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k f4519a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4520b;

    public abstract d0 a();

    public final k b() {
        k kVar = this.f4519a;
        if (kVar != null) {
            return kVar;
        }
        throw new IllegalStateException("You cannot access the Navigator's state until the Navigator is attached");
    }

    public void d(List list, n0 n0Var) {
        ga.h hVar = new ga.h(ga.r.J(new ga.t(new ga.p(list, 3), new ab.h(this, n0Var)), new com.arflix.tv.util.a(4)));
        while (hVar.hasNext()) {
            b().f((i) hVar.next());
        }
    }

    public void e(i iVar, boolean z) {
        List list = (List) b().f4710e.f20648i.getValue();
        if (!list.contains(iVar)) {
            throw new IllegalStateException(("popBackStack was called with " + iVar + " which does not exist in back stack " + list).toString());
        }
        ListIterator listIterator = list.listIterator(list.size());
        i iVar2 = null;
        while (f()) {
            iVar2 = (i) listIterator.previous();
            if (kotlin.jvm.internal.p.a(iVar2, iVar)) {
                break;
            }
        }
        if (iVar2 != null) {
            b().c(iVar2, z);
        }
    }

    public boolean f() {
        return true;
    }

    public d0 c(d0 d0Var) {
        return d0Var;
    }
}
