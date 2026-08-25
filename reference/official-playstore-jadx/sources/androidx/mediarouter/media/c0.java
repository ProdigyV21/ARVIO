package androidx.mediarouter.media;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 extends i0 {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final ArrayList f4309w;
    public final t.e x;

    public c0(g0 g0Var, String str, String str2) {
        super(g0Var, str, str2, false);
        this.f4309w = new ArrayList();
        this.x = new t.e(0);
    }

    public final void i(Collection collection) {
        i0 i0Var;
        this.f4406v.clear();
        ArrayList arrayList = this.f4309w;
        arrayList.clear();
        t.e eVar = this.x;
        eVar.clear();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            q qVar = (q) it.next();
            String strD = qVar.f4471a.d();
            Iterator it2 = this.f4386a.f4363b.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    i0Var = null;
                    break;
                } else {
                    i0Var = (i0) it2.next();
                    if (i0Var.f4387b.equals(strD)) {
                        break;
                    }
                }
            }
            if (i0Var != null) {
                arrayList.add(i0Var);
                eVar.put(i0Var.f4388c, qVar);
                int i10 = qVar.f4472b;
                if (i10 == 2 || i10 == 3) {
                    this.f4406v.add(i0Var);
                }
            }
        }
        k0.c().f4326a.b(259, this);
    }
}
