package h8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import v9.w0;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f15871i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final w0 f15872l;

    public l(h hVar, w0 w0Var) {
        this.f15871i = hVar;
        this.f15872l = w0Var;
    }

    @Override // h8.h
    public final boolean isEmpty() {
        h hVar = this.f15871i;
        if ((hVar instanceof Collection) && ((Collection) hVar).isEmpty()) {
            return false;
        }
        Iterator<b> it = hVar.iterator();
        while (it.hasNext()) {
            f9.c cVarC = it.next().c();
            if (cVarC != null && ((Boolean) this.f15872l.invoke(cVarC)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator<b> iterator() {
        ArrayList arrayList = new ArrayList();
        for (b bVar : this.f15871i) {
            f9.c cVarC = bVar.c();
            if (cVarC != null && ((Boolean) this.f15872l.invoke(cVarC)).booleanValue()) {
                arrayList.add(bVar);
            }
        }
        return arrayList.iterator();
    }

    @Override // h8.h
    public final b j(f9.c cVar) {
        if (((Boolean) this.f15872l.invoke(cVar)).booleanValue()) {
            return this.f15871i.j(cVar);
        }
        return null;
    }

    @Override // h8.h
    public final boolean x(f9.c cVar) {
        if (((Boolean) this.f15872l.invoke(cVar)).booleanValue()) {
            return this.f15871i.x(cVar);
        }
        return false;
    }
}
